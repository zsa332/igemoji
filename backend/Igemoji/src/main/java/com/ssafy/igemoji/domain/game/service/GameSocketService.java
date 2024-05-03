package com.ssafy.igemoji.domain.game.service;

import com.ssafy.igemoji.domain.game.GameInfo;
import com.ssafy.igemoji.domain.game.dto.*;
import com.ssafy.igemoji.domain.movie.dto.MovieResponseDto;
import com.ssafy.igemoji.domain.movie.service.MovieService;
import com.ssafy.igemoji.domain.room.dto.MessageType;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;

@Service
@RequiredArgsConstructor
public class GameSocketService {

    private final SimpMessagingTemplate messagingTemplate;
    private final TaskScheduler taskScheduler;
    private final MovieService movieService;

    private final Map<Integer, ScheduledFuture<?>> scheduledFutures = new ConcurrentHashMap<>();
    private final Map<Integer, GameInfo> gameInfoMap = new HashMap<>();



    /* 스케줄러 종료 */
    public void stopGameScheduler(Integer roomId) {
        if (scheduledFutures.containsKey(roomId)) {
            scheduledFutures.get(roomId).cancel(false);
            scheduledFutures.remove(roomId);
            gameInfoMap.remove(roomId);
        }
    }

    /* 게임 시작 세팅 */
    public void startGame(StartRequestDto requestDto) {
        // 스케줄러 생성 및 문제 가져오기
        if (!scheduledFutures.containsKey(requestDto.getRoomId()) || scheduledFutures.get(requestDto.getRoomId()).isCancelled()) {
            // 문제 랜덤으로 10개 뽑아오기
            List<MovieResponseDto> movieList = movieService.getRandMovieList(requestDto.getQuestionNum());
            System.out.println(movieList);
            // 방 관리용 데이터 (게임 시간, 총 라운드, 게임 상태, 문제 list)
            gameInfoMap.put(requestDto.getRoomId(), new GameInfo(60, requestDto.getQuestionNum()-1, GameStatus.PROCEEDING, movieList));
            // 스케줄러 생성
            ScheduledFuture<?> scheduledFuture = taskScheduler.scheduleAtFixedRate(() -> sendRemainingTime(requestDto.getRoomId()), 1000);
            scheduledFutures.put(requestDto.getRoomId(), scheduledFuture);
        }
    }

    /* 게임 남은 시간 send */
    public void sendRemainingTime(Integer roomId) {
        GameInfo GameInfo = gameInfoMap.get(roomId);
        switch(GameInfo.getGameStatus()){
            // 게임 진행 중
            case PROCEEDING -> proceedingGame(GameInfo, roomId);
            // 정답 출력
            case PRINT_ANSWER -> printAnswer(GameInfo, roomId);
            // 라운드 종료
            case ROUND_END -> endRound(GameInfo, roomId);
            // 게임 대기 시간
            case WAITING -> waitGame(GameInfo, roomId);
            // 게임 종료
            case GAME_END -> endGame(GameInfo, roomId);
        }
    }

    /* 게임 종료 */
    private void endGame(GameInfo gameInfo, Integer roomId) {
        /* 게임 최종 스코어 및 rating 점수 send */
        stopGameScheduler(roomId);
    }

    /* 게임 진행 중 */
    public void proceedingGame(GameInfo gameInfo, Integer roomId){
        int remainingTime = gameInfo.getRemainingTime();
        MovieResponseDto movie = gameInfo.getMovieList().get(gameInfo.getRemainingRound());

        GameResponseDto gameResponseDto = new GameResponseDto(remainingTime, GameStatus.PROCEEDING, MessageType.GAME_PROGRESS);
        if(remainingTime == 60) // 문제 이모지 send
            gameResponseDto = new StartResponseDto(remainingTime, GameStatus.PROCEEDING, MessageType.GAME_PROGRESS, movie.getEmoji());
        else if(remainingTime == 30) // 첫번째 힌트 명대사 send
            gameResponseDto = new HintResponseDto(remainingTime, GameStatus.PROCEEDING, MessageType.GAME_PROGRESS, movie.getLine());
        else if(remainingTime == 15) // 두번째 힌트 초성 send
            gameResponseDto = new HintResponseDto(remainingTime, GameStatus.PROCEEDING, MessageType.GAME_PROGRESS, movie.getChosung());

        sendMessage(gameResponseDto, roomId);

        // 시간이 끝났을 때 라운드 종료
        if(gameInfo.getRemainingTime() <= 0) {
            gameInfo.updateGameStatus(GameStatus.PRINT_ANSWER);
            gameInfo.waitTime();
        }
        else gameInfo.decreaseTime(); // 남은 시간 감소
    }

    /* 정답 출력 */
    private void printAnswer(GameInfo gameInfo, Integer roomId) {
        int remainingTime = gameInfo.getRemainingTime();
        GameResponseDto gameResponseDto = new GameResponseDto(remainingTime, GameStatus.PRINT_ANSWER, MessageType.GAME_PROGRESS);
        if(remainingTime == 3){ // 정답 출력 3초
            MovieResponseDto movie = gameInfo.getMovieList().get(gameInfo.getRemainingRound());
            gameResponseDto = new AnswerResponseDto(remainingTime, GameStatus.PRINT_ANSWER, MessageType.GAME_PROGRESS, movie.getName(), movie.getImg(), movie.getCorrectMember());
        }
        sendMessage(gameResponseDto, roomId);
        if(gameInfo.getRemainingTime() <= 0)
            gameInfo.updateGameStatus(GameStatus.ROUND_END);
        else gameInfo.decreaseTime(); // 남은 시간 감소
    }

    /* 게임 라운드 종료 */
    public void endRound(GameInfo gameInfo, Integer roomId){
        if(gameInfo.getRemainingRound() == 0)
            gameInfo.updateGameStatus(GameStatus.GAME_END);
        else
            gameInfo.updateGameStatus(GameStatus.WAITING);

        /* 맴버 현재 스코어 send */

        gameInfo.decreaseRound(); // 남은 라운드 감소
        gameInfo.waitTime(); // 남은 시간 = 대기 시간
    }

    public void waitGame(GameInfo gameInfo, Integer roomId) {
        sendMessage(new GameResponseDto(gameInfo.getRemainingTime(), GameStatus.WAITING, MessageType.GAME_PROGRESS), roomId);
        // 시간이 끝났을 때 라운드 종료
        if(gameInfo.getRemainingTime() <= 0){
            gameInfo.updateGameStatus(GameStatus.PROCEEDING);
            gameInfo.newRound();
        }
        else gameInfo.decreaseTime();
    }

    public void sendMessage(Object data, Integer roomId){
        messagingTemplate.convertAndSend("/topic/room/" + roomId , data);
    }
}