package com.ssafy.igemoji.domain.game.controller;

import com.ssafy.igemoji.domain.game.dto.StartRequestDto;
import com.ssafy.igemoji.domain.game.service.GameSocketService;
import com.ssafy.igemoji.domain.room.dto.ChatRequestDto;
import com.ssafy.igemoji.domain.room.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class GameSocketController {

    private final GameSocketService gameSocketService;
    private final RoomRepository roomRepository;
    private final SimpMessageSendingOperations simpMessageSendingOperations;

    /* 게임 시작 */
    @MessageMapping("/game/start")
    public void gameStart(StartRequestDto requestDto){
//        Room room = roomRepository.findById(requestDto.getRoomId()).orElseThrow(
//                () -> new CustomException(RoomErrorCode.NOT_FOUND_ROOM)
//        );
//
//        // 이미 진행 중인 경우 또는 방장이 아닌 경우 종료
//        if(room.getIsProgress() || !room.getHost().getId().equals(requestDto.getSenderId())){
//            return;
//        }

        // 게임 초기 세팅
//        gameSocketService.initGame(requestDto);

        // 시간 카운트
        gameSocketService.startGame(requestDto);

    }

    /* 채팅 소켓 */
    @MessageMapping("/game/chat")
    public void socketChat(ChatRequestDto chatRequestDto) {
        gameSocketService.gameChat(chatRequestDto);
//        simpMessageSendingOperations.convertAndSend("/topic/room/"+ chat.getRoomId(), chat);
    }

}
