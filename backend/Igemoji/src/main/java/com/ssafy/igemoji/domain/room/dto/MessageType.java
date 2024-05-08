package com.ssafy.igemoji.domain.room.dto;

public enum MessageType {
    ENTER_SUCCESS, // 입장 성공
    LEAVE_ROOM, // 방 퇴장
    GAME_PROGRESS, // 게임 진행
    CHANGE_SET, // 방 세팅 변경
    GAME_CHAT,
    WATCH_CHAT,
    ROOM_CHAT
}
