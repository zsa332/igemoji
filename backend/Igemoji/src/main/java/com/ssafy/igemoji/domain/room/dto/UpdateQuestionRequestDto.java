package com.ssafy.igemoji.domain.room.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UpdateQuestionRequestDto {
    private Integer memberId;
    private Integer roomId;
    private Integer questionNum;
}
