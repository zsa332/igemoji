package com.ssafy.igemoji.domain.game.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AnswerResponseDto extends GameResponseDto{
    private String name;
    private String img;
    private String correctMember;

    public AnswerResponseDto(Integer remainingTime, GameStatus gameStatus, String name, String img, String correctMember) {
        super(remainingTime, gameStatus);
        this.name = name;
        this.img = img;
        this.correctMember = correctMember;
    }
}
