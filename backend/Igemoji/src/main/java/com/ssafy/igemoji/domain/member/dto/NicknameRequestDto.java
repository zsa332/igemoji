package com.ssafy.igemoji.domain.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class NicknameRequestDto {
    private Integer memberId;
    private String nickname;
}
