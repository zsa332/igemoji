package com.ssafy.igemoji.global.common.dto;

import lombok.Getter;

@Getter
public class SuccessResponseDto {

    private String message;
    private Object data;

    public SuccessResponseDto(String message, Object data) {
        this.message = message;
        this.data = data;
    }
}

