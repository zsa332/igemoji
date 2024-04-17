package com.ssafy.igemoji.domain.room.controller;

import com.ssafy.igemoji.domain.room.dto.ChatSocketDto;
import com.ssafy.igemoji.domain.room.dto.RoomSocketDto;
import com.ssafy.igemoji.domain.room.service.RoomSocketService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;


@Controller
@RequiredArgsConstructor
public class RoomSocketController {

    private final SimpMessageSendingOperations simpMessageSendingOperations;
    private final RoomSocketService roomSocketService;

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    private ScheduledFuture<?> scheduledFuture;

    @MessageMapping("/enter")
    public void socketEnter(Integer roomId) {
        RoomSocketDto roomSocketDto = roomSocketService.getRoomInfo(roomId);
        simpMessageSendingOperations.convertAndSend("/topic/room/"+roomId , roomSocketDto);
    }

    @MessageMapping("/chat")
    public void socketChat(ChatSocketDto chat) {
        simpMessageSendingOperations.convertAndSend("/topic/room/"+ chat.getRoomId(), chat);
    }

}
