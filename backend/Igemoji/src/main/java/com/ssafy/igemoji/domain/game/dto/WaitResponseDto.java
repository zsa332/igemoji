package com.ssafy.igemoji.domain.game.dto;

import com.ssafy.igemoji.domain.room.dto.MessageType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class WaitResponseDto extends GameResponseDto{
    private List<PlayerResponseDto> playerList;

    public WaitResponseDto(Integer remainingTime, GameStatus gameStatus, MessageType message, List<PlayerResponseDto> playerList) {
        super(remainingTime, gameStatus, message);
        this.playerList = playerList;
    }
}
