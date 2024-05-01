package com.ssafy.igemoji.domain.game.service;

import com.ssafy.igemoji.domain.game.GameRemaining;
import com.ssafy.igemoji.domain.game.dto.*;
import com.ssafy.igemoji.domain.movie.dto.MovieResponseDto;
import com.ssafy.igemoji.domain.movie.repository.MovieRepository;
import com.ssafy.igemoji.domain.room.dto.MessageType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;

@Component
@RequiredArgsConstructor
public class GameSocketService {

    private final SimpMessagingTemplate messagingTemplate;
    private final TaskScheduler taskScheduler;
    private final MovieRepository movieRepository;

    /* 게임 진행시 스레드 환경에서 자원이 공유되기 때문에 스케줄러를 방번호별로 관리해야함 */
    private final Map<Integer, ScheduledFuture<?>> scheduledFutures = new ConcurrentHashMap<>();
    private final Map<Integer, GameRemaining> timeMap = new HashMap<>();
    // 한글 문자열의 시작 유니코드 값
    private static final int HANGUL_UNICODE_START = 44032;



    /* 게임 종료 */
    public void stopGameScheduler(Integer roomId) {
        if (scheduledFutures.containsKey(roomId)) {
            scheduledFutures.get(roomId).cancel(false);
            scheduledFutures.remove(roomId);
            timeMap.remove(roomId);
        }
    }

    /* 게임 시작 세팅 */
    public void startGame(StartRequestDto requestDto) {
        // 스케줄러 생성 및 문제 가져오기
        if (!scheduledFutures.containsKey(requestDto.getRoomId()) || scheduledFutures.get(requestDto.getRoomId()).isCancelled()) {
            // 문제 랜덤으로 10개 뽑아오기
            List<MovieResponseDto> movieList = movieRepository.getRandMovieList(PageRequest.of(1, requestDto.getQuestionNum()))
                    .stream()
                    .map(MovieResponseDto::toDto)
                    .toList();
            // 방 관리용 데이터 (게임 시간, 총 라운드, 게임 상태, 문제 list)
            timeMap.put(requestDto.getRoomId(), new GameRemaining(60, 2, GameStatus.PROCEEDING, movieList));

            // 스케줄러 생성
            ScheduledFuture<?> scheduledFuture = taskScheduler.scheduleAtFixedRate(() -> sendRemainingTime(requestDto.getRoomId()), 1000);
            scheduledFutures.put(requestDto.getRoomId(), scheduledFuture);
        }

    }


    /* 게임 남은 시간 send */
    public void sendRemainingTime(Integer roomId) {
        GameRemaining gameRemaining = timeMap.get(roomId);
        switch(gameRemaining.getGameStatus()){
            // 게임 진행 중
            case PROCEEDING -> proceedingGame(gameRemaining, roomId);
            // 라운드 종료
            case ROUND_END -> endRound(gameRemaining, roomId);
            // 게임 대기 시간
            case WAITING -> waitGame(gameRemaining, roomId);
            // 게임 종료
            case GAME_END -> {
                stopGameScheduler(roomId);
            }
        }
    }

    /* 게임 진행 중 */
    public void proceedingGame(GameRemaining gameRemaining, Integer roomId){
        int remainingTime = gameRemaining.getRemainingTime();
        if(remainingTime == 60){
            messagingTemplate.convertAndSend("/topic/room/" + roomId , new StartResponseDto(gameRemaining.getRemainingTime(), GameStatus.PROCEEDING, MessageType.TIME_UPDATE, gameRemaining.getMovieList().get(gameRemaining.getRemainingRound()).getEmoji()));
        }
        else if(remainingTime == 30){
            messagingTemplate.convertAndSend("/topic/room/" + roomId , new HintResponseDto(gameRemaining.getRemainingTime(), GameStatus.PROCEEDING, MessageType.TIME_UPDATE, gameRemaining.getMovieList().get(gameRemaining.getRemainingRound()).getLine()));
        }
        else if(remainingTime == 15){
            messagingTemplate.convertAndSend("/topic/room/" + roomId , new HintResponseDto(gameRemaining.getRemainingTime(), GameStatus.PROCEEDING, MessageType.TIME_UPDATE, extractChosung(gameRemaining.getMovieList().get(gameRemaining.getRemainingRound()).getName())));
        }
        else {
            messagingTemplate.convertAndSend("/topic/room/" + roomId , new GameResponseDto(gameRemaining.getRemainingTime(), GameStatus.PROCEEDING, MessageType.TIME_UPDATE));
        }

        // 시간이 끝났을 때 라운드 종료
        if(gameRemaining.getRemainingTime() <= 0){
            gameRemaining.updateGameStatus(GameStatus.ROUND_END);
        }
        gameRemaining.decreaseTime(); // 남은 시간 감소
    }

    /* 게임 라운드 종료 */
    public void endRound(GameRemaining gameRemaining, Integer roomId){
        if(gameRemaining.getRemainingRound() == 0)
            gameRemaining.updateGameStatus(GameStatus.GAME_END);
        else
            gameRemaining.updateGameStatus(GameStatus.WAITING);

        messagingTemplate.convertAndSend("/topic/room/" + roomId , gameRemaining.getMovieList().get(gameRemaining.getRemainingRound()));

        gameRemaining.decreaseRound(); // 남은 라운드 감소
        gameRemaining.waitTime(); // 남은 시간 = 대기 시간
    }

    public void waitGame(GameRemaining gameRemaining, Integer roomId) {
        messagingTemplate.convertAndSend("/topic/room/" + roomId , new GameResponseDto(gameRemaining.getRemainingTime(), GameStatus.WAITING, MessageType.TIME_UPDATE));
        // 시간이 끝났을 때 라운드 종료
        if(gameRemaining.getRemainingTime() <= 0){
            gameRemaining.updateGameStatus(GameStatus.PROCEEDING);
            gameRemaining.newRound();
            return;
        }
        gameRemaining.decreaseTime();
    }

    // 초성 추출 메서드
    public static String extractChosung(String str) {
        StringBuilder result = new StringBuilder();
        for (char c : str.toCharArray()) {
            // 한글인 경우에만 처리
            if (isHangul(c)) {
                // 한글의 유니코드 값에서 시작 유니코드 값을 빼면 초성이 됨
                result.append((char) (((c - HANGUL_UNICODE_START) / 28 / 21) + 0x1100));
            } else {
                // 한글이 아닌 경우에는 그대로 추가
                result.append(c);
            }
        }
        return result.toString();
    }

    // 문자가 한글인지 확인하는 메서드
    private static boolean isHangul(char c) {
        // 한글은 유니코드 값이 44032 이상이고 55203 이하인 범위에 있음
        return c >= 44032 && c <= 55203;
    }

}