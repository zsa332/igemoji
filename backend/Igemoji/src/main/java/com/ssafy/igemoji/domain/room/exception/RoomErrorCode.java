package com.ssafy.igemoji.domain.room.exception;

import com.ssafy.igemoji.global.exception.ErrorCode;

public enum RoomErrorCode implements ErrorCode {
    NOT_FOUND_ROOM("해당 방을 찾을 수 없습니다","ROM_001",404),
    ROOM_FULL("해당 방의 인원이 초과하였습니다", "ROM_002", 403),
    PASSWORD_INCORRECT("비밀번호가 틀려서 입장할 수 없습니다", "ROM_003", 403);


    private final String message;
    private final String errorCode;
    private final int statusCode;


    RoomErrorCode(String message, String errorCode, int statusCode) {
        this.message = message;
        this.errorCode = errorCode;
        this.statusCode = statusCode;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public String getErrorCode() {
        return errorCode;
    }

    @Override
    public int getStatusCode() {
        return statusCode;
    }

}
