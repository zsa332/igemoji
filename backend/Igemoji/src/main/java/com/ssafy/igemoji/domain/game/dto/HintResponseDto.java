package com.ssafy.igemoji.domain.game.dto;

import com.ssafy.igemoji.domain.room.dto.MessageType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class HintResponseDto extends GameResponseDto {
    private String hint;

    public HintResponseDto(Integer remainingTime, GameStatus gameStatus, String hint) {
        super(remainingTime, gameStatus);
        this.hint = hint;
    }
}