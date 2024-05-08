package com.ssafy.igemoji.domain.room.controller;

import com.ssafy.igemoji.domain.room.dto.*;
import com.ssafy.igemoji.domain.room.service.RoomSocketService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Slf4j
@Controller
@RequiredArgsConstructor
public class RoomSocketController {

    private final SimpMessageSendingOperations simpMessageSendingOperations;
    private final RoomSocketService roomSocketService;

    /* 방 퇴장 */
    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        RoomInfoDto roomInfoDto = roomSocketService.leaveRoom(headerAccessor.getSessionId());
        simpMessageSendingOperations.convertAndSend("/topic/room/"+roomInfoDto.getRoomId() , roomInfoDto);
    }

    /* 방 입장 */
    @MessageMapping("/room/enter")
    public void socketEnter(RoomEnterRequestDto roomEnterRequestDto, SimpMessageHeaderAccessor headerAccessor) {
        System.out.println(roomEnterRequestDto);
        simpMessageSendingOperations.convertAndSend("/topic/room/"+roomEnterRequestDto.getRoomId() , roomSocketService.enterRoom(roomEnterRequestDto, headerAccessor.getSessionId()));
    }

    /* 방 입장 */
    @MessageMapping("/room/question")
    public void updateQuestion(UpdateQuestionRequestDto updateQuestionRequestDto) {
        simpMessageSendingOperations.convertAndSend("/topic/room/"+updateQuestionRequestDto.getRoomId() , roomSocketService.updateQuestion(updateQuestionRequestDto));
    }

    /* 채팅 소켓 */
    @MessageMapping("/room/chat")
    public void roomChat(ChatRequestDto chatRequestDto) {
        simpMessageSendingOperations.convertAndSend("/topic/room/"+ chatRequestDto.getRoomId(), roomSocketService.sendChat(chatRequestDto, MessageType.ROOM_CHAT));
    }

    @MessageMapping("/watch/chat")
    public void watchChat(ChatRequestDto chatRequestDto) {
        simpMessageSendingOperations.convertAndSend("/topic/room/"+ chatRequestDto.getRoomId(), roomSocketService.sendChat(chatRequestDto, MessageType.WATCH_CHAT));
    }
}
