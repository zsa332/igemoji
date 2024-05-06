package com.ssafy.igemoji.domain.game.dto;

import com.ssafy.igemoji.domain.member.Member;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PlayerResponseDto {
    private Integer memberId;
    private String nickname;
    private Integer score;
    private Integer rating;
    private Integer exp;
    private Integer level;

    public void increaseScore() { this.score++; }

    public static PlayerResponseDto toDto(Member member){
        return PlayerResponseDto.builder()
                .memberId(member.getId())
                .nickname(member.getNickname())
                .score(0)
                .rating(member.getRating())
                .exp(member.getExp())
                .level(member.getLevel())
                .build();
    }
}