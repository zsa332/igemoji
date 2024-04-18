package com.ssafy.igemoji.domain.room.controller;

import com.ssafy.igemoji.domain.room.dto.RoomRequestDto;
import com.ssafy.igemoji.domain.room.dto.RoomResponseDto;
import com.ssafy.igemoji.domain.room.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/room")
public class RoomController {

    private final RoomService roomService;

    /* 방 생성 */
    @PostMapping("/create")
    public Integer createRoom(RoomRequestDto requestDto){
        return roomService.save(requestDto);
    }

    /* 방 입장 */
    @GetMapping("/enter")
    public void enterRoom(@RequestParam Integer roomId,
                          @RequestParam Integer memberId){
        roomService.enterRoom(roomId, memberId);
    }

    /* 빠른 입장 */
    @GetMapping("/random")
    public void enterRoom(@RequestParam Integer memberId){
        roomService.enterRandomRoom(memberId);
    }

    /* 모든 방 조회 */
    @GetMapping("/list")
    public List<RoomResponseDto> findAllRoom(@RequestParam int offset){
        return roomService.findAllRoom(offset);
    }
}
