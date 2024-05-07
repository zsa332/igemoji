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
public class WaitScoreResponseDto extends ScoreResponseDto {
    private Integer currentRound;
    private Integer totalRound;

    public WaitScoreResponseDto(MessageType message, GameStatus gameStatus, List<PlayerResponseDto> playerList, Integer currentRound, Integer totalRound) {
        super(message, gameStatus, playerList);
        this.currentRound = currentRound;
        this.totalRound = totalRound;
    }

}
