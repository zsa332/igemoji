package com.ssafy.igemoji.domain.member.dto;

import com.ssafy.igemoji.domain.member.Member;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberRankDto {
    private Integer rank;
    private Integer memberId;
    private String nickname;
    private Integer level;
    private Integer rating;

    public static MemberRankDto toDto(Member member, Integer rank){
        return MemberRankDto.builder()
                .rank(rank)
                .memberId(member.getId())
                .nickname(member.getNickname())
                .level(member.getLevel())
                .rating(member.getRating())
                .build();
    }
}
