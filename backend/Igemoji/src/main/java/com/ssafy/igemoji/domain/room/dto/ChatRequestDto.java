package com.ssafy.igemoji.domain.room.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class ChatRequestDto {
    private Integer roomId;
    private Integer memberId;
    private String content;
}