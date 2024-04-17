package com.ssafy.igemoji.domain.room.controller;

import com.ssafy.igemoji.domain.room.dto.RoomRequestDto;
import com.ssafy.igemoji.domain.room.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@RequestMapping("/room")
public class RoomController {

    private final RoomService roomService;

    @PostMapping("/create")
    public Integer createRoom(RoomRequestDto requestDto){
        return roomService.save(requestDto);
    }

    @GetMapping("/enter")
    public void enterRoom(@RequestParam Integer roomId,
                          @RequestParam Integer memberId){
        roomService.enterRoom(roomId, memberId);
    }
}
