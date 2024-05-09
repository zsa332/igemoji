package com.ssafy.igemoji.domain.member.dto;

import lombok.*;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RankResponseDto {
    private MemberRankDto myRank;
    private List<MemberRankDto> ranks;
}
