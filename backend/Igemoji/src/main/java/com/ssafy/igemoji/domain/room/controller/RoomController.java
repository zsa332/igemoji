package com.ssafy.igemoji.domain.room.controller;

import com.ssafy.igemoji.domain.room.dto.EnterRequestDto;
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

    /* 모든 방 조회 */
    @GetMapping("/list")
    @Operation(summary = "방 목록 조회", description = "각 페이지별 10개씩 반환됩니다.")
    @Parameter(name = "pageNum", description = "페이지 번호")
    public List<RoomResponseDto> findAllRoom(@RequestParam int pageNum){
        return roomService.findAllRoom(pageNum);
    }

    /* 방 입장 가능 조회 */
    @GetMapping("/enter")
    @Operation(summary = "방 입장 가능 조회", description = "해당 방에 입장 가능한지 체크")
    @Parameter(name = "roomId", description = "입장 하려는 방")
    @Parameter(name = "memberId", description = "입장 하려는 맴버")
    @Parameter(name = "password", description = "방 비밀번호")
    public ResponseEntity<?> enterRoom(@RequestBody EnterRequestDto enterRequestDto){
        return ResponseFactory.success(roomService.roomEnter(enterRequestDto));
    }

    /* 방 찾기 */
    @GetMapping("/{roomId}")
    @Operation(summary = "방 찾기", description = "방번호를 이용해 방찾기")
    @Parameter(name = "roomId", description = "찾으려는 방 번호")
    public ResponseEntity<?> searchRoom(@PathVariable int roomId){
        return ResponseFactory.success("방찾기 완료", roomService.searchRoom(roomId));
    }

    /* 빠른 입장 방찾기 */
    @GetMapping("/fast")
    @Operation(summary = "빠른 입장 방 조회", description = "빠른 입장을 하기 위한 방 찾기")
    public ResponseEntity<?> fastRoom(){
        return ResponseFactory.success("빠른 입장 조회 완료", roomService.fastRoom());
    }

}
