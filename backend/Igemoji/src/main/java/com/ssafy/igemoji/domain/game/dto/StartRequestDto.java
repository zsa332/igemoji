package com.ssafy.igemoji.domain.game.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StartRequestDto {
    private Integer roomId;
    private Integer memberId;
}
