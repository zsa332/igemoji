package com.ssafy.igemoji.domain.game.dto;

import com.ssafy.igemoji.domain.room.dto.MessageType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StartResponseDto extends GameResponseDto {
    private String emoji;

    public StartResponseDto(Integer remainingTime, GameStatus gameStatus, String emoji) {
        super(remainingTime, gameStatus);
        this.emoji = emoji;
    }
}
