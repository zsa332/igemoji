package com.ssafy.igemoji.domain.game.dto;

import com.ssafy.igemoji.domain.room.dto.MessageType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProceedingResponseDto extends GameResponseDto{
    private String emoji;
    private String hint1;
    private String hint2;

    public ProceedingResponseDto(Integer remainingTime, GameStatus gameStatus, MessageType message, String emoji) {
        super(remainingTime, gameStatus, message);
        this.emoji = emoji;
    }

    public void updateHint1(String hint1){
        this.hint1 = hint1;
    }

    public void updateHint2(String hint2){
        this.hint2 = hint2;
    }
}
