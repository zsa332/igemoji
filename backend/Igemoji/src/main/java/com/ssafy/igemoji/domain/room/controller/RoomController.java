package com.ssafy.igemoji.domain.room.controller;

import com.ssafy.igemoji.domain.room.dto.RoomRequestDto;
import com.ssafy.igemoji.domain.room.dto.RoomResponseDto;
import com.ssafy.igemoji.domain.room.service.RoomService;
import com.ssafy.igemoji.global.common.dto.ResponseFactory;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/room")
@Tag(name = "게임 방 API", description = "게임 방 관련 API")
public class RoomController {

    private final RoomService roomService;

    /* 방 생성 */
    @PostMapping("/create")
    @Operation(summary = "방 생성", description = "방 생성 API")
    public ResponseEntity<?> createRoom(@RequestBody RoomRequestDto requestDto){
        System.out.println(requestDto);
        return ResponseFactory.success("방 생성 완료", roomService.createRoom(requestDto));
    }

    /* 방 입장 */
    @GetMapping("/enter")
    @Operation(summary = "사라질 예정입니다.", description = "사라질 예정입니다.")
    public void enterRoom(@RequestParam Integer roomId,
                          @RequestParam Integer memberId){
        roomService.enterRoom(roomId, memberId);
    }

    /* 빠른 입장 */
    @GetMapping("/random")
    @Operation(summary = "사라질 예정입니다.", description = "사라질 예정입니다.")
    public void enterRoom(@RequestParam Integer memberId){
        roomService.enterRandomRoom(memberId);
    }

    /* 모든 방 조회 */
    @GetMapping("/list")
    @Operation(summary = "방 목록 조회", description = "각 페이지별 10개씩 반환됩니다.")
    @Parameter(name = "pageNum", description = "페이지 번호")
    public List<RoomResponseDto> findAllRoom(@RequestParam int pageNum){
        return roomService.findAllRoom(pageNum);
    }
}
