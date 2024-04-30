package com.ssafy.igemoji.domain.game.service;

import com.ssafy.igemoji.domain.game.dto.GameStatus;
import com.ssafy.igemoji.domain.game.dto.GameTimerResponseDto;
import com.ssafy.igemoji.domain.room.dto.MessageType;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;

@Component
@RequiredArgsConstructor
public class GameTimerService {

    private final SimpMessagingTemplate messagingTemplate;
    private final TaskScheduler taskScheduler;

    /* 게임 진행시 스레드 환경에서 자원이 공유되기 때문에 스케줄러를 방번호별로 관리해야함 */
    private Map<String, ScheduledFuture<?>> scheduledFutures = new ConcurrentHashMap<>();

    private ScheduledFuture<?> scheduledFuture;
    private int totalRounds = 2; // 총 라운드
    private int remainingRounds = totalRounds;
    private int roundDuration = 60; // 라운드의 총 시간 (60초)
    private int roundInterval = 3; // 라운드 간격 (3초)
    private int remainingTime = roundDuration; // 라운드 시작 시간
    private String sendRoomPath; // /topic/room/{roomId}
    private GameStatus gameStatus; // 게임 상태

    /* 게임 종료 */
    public void stopGameScheduler() {
        sendRoomPath = null;
        if (scheduledFuture != null) {
            scheduledFuture.cancel(false);
        }
    }

    /* 게임 시간 카운트 시작 */
    public void startGameScheduler(Integer roomId) {
        remainingRounds = totalRounds;
        remainingTime = roundDuration;
        gameStatus = GameStatus.PROCEEDING;
        sendRoomPath = "/topic/room/" + roomId;
        if (scheduledFuture == null || scheduledFuture.isCancelled()) {
            scheduledFuture = taskScheduler.scheduleAtFixedRate(this::sendRemainingTime, 1000);
        }
    }


    /* 게임 남은 시간 send */
    public void sendRemainingTime() {
        switch(gameStatus){
            // 게임 진행 중
            case PROCEEDING -> {
                messagingTemplate.convertAndSend(sendRoomPath , new GameTimerResponseDto(remainingTime, GameStatus.PROCEEDING, MessageType.TIME_UPDATE));
                // 시간이 끝났을 때 라운드 종료
                if(remainingTime <= 0){
                    gameStatus = GameStatus.ROUND_END;
                }
                remainingTime--; // 남은 시간 감소
            }

            case ROUND_END -> {
                if(remainingRounds == 0)
                    gameStatus = GameStatus.GAME_END;
                else
                    gameStatus = GameStatus.WAITING;

                remainingRounds--; // 남은 라운드 감소
                remainingTime = roundInterval; // 남은 시간 = 대기 시간
            }

            case WAITING -> {
                messagingTemplate.convertAndSend(sendRoomPath , new GameTimerResponseDto(remainingTime, GameStatus.WAITING, MessageType.TIME_UPDATE));
                // 시간이 끝났을 때 라운드 종료
                if(remainingTime <= 0){
                    gameStatus = GameStatus.PROCEEDING;
                    remainingTime = roundDuration;
                    break;
                }
                remainingTime--;
            }

            case GAME_END -> {
                stopGameScheduler();
            }
        }
    }


    // 문제의 정답을 받았을 때 호출되는 메소드
    public void answerReceived() {
        if (remainingTime > roundInterval) {
            // 남은 시간이 준비 시간보다 크면 준비 시간으로 조정
            remainingTime = roundInterval;
        }
    }
}