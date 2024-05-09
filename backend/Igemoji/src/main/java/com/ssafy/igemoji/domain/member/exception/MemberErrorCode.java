package com.ssafy.igemoji.domain.member.exception;

import com.ssafy.igemoji.global.exception.ErrorCode;

public enum MemberErrorCode implements ErrorCode {

    NOT_FOUND_MEMBER("해당 회원을 찾을 수 없습니다","MEM_001",404),
    INVALID_ID_TOKEN("유효하지 않는 idToken 입니다","MEM_002",400),
    EXPIRED_ID_TOKEN("만료된 idToken 입니다","MEM_003",400),
    NOT_SUPPORT_OAUTH_PROVIDER("지원하지 않는 OAuth 인증 방식입니다.","MEM_004",409),
    NICKNAME_DUPLICATE("중복된 닉네임입니다.","MEM_005",409),
    USER_ALREADY_HAS_ROOM("이미 방을 생성한 유저입니다", "MEM_006", 403);

    private final String message;
    private final String errorCode;
    private final int statusCode;

    MemberErrorCode(String message, String errorCode, int statusCode) {
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
