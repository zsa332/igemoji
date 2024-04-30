package com.ssafy.igemoji.domain.game.dto;

import com.ssafy.igemoji.domain.room.dto.MessageType;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GameTimerResponseDto {
    private Integer remainingTime;
    private GameStatus gameStatus;
    private MessageType messageType;
}
