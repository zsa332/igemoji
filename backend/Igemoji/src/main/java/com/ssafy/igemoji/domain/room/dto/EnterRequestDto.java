package com.ssafy.igemoji.domain.room.dto;

import lombok.Getter;

@Getter
public class EnterRequestDto {
    private Integer memberId;
    private Integer roomId;
    private String password;
}
