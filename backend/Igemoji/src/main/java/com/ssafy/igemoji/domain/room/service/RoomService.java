package com.ssafy.igemoji.domain.room.service;

import com.ssafy.igemoji.domain.member.Member;
import com.ssafy.igemoji.domain.member.exception.MemberErrorCode;
import com.ssafy.igemoji.domain.member.repository.MemberRepository;
import com.ssafy.igemoji.domain.room.Room;
import com.ssafy.igemoji.domain.room.dto.EnterRequestDto;
import com.ssafy.igemoji.domain.room.dto.RoomRequestDto;
import com.ssafy.igemoji.domain.room.dto.RoomResponseDto;
import com.ssafy.igemoji.domain.room.exception.RoomErrorCode;
import com.ssafy.igemoji.domain.room.repository.RoomRepository;
import com.ssafy.igemoji.global.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RoomService {

    private final MemberRepository memberRepository;
    private final RoomRepository roomRepository;

    /* 방 생성 */
    @Transactional
    public RoomResponseDto createRoom(RoomRequestDto requestDto){
        Member member = memberRepository.findById(requestDto.getMemberId())
                .orElseThrow(() -> new CustomException(MemberErrorCode.NOT_FOUND_MEMBER));

        Room room = Room.builder()
                .title(requestDto.getTitle())
                .isPublic(requestDto.getIsPublic())
                .isProgress(false)
                .password(requestDto.getPassword())
                .maxNum(requestDto.getMemberMaxNum())
                .genre("movie")
                .questionNum(10)
                .host(member)
                .build();

        roomRepository.save(room);
        return RoomResponseDto.toDto(room);
    }

    /* 모든 방 조회 */
    public List<RoomResponseDto> findAllRoom(int pageNum){
        return roomRepository.findAll(PageRequest.of(pageNum, 10)).getContent().stream().map(RoomResponseDto::toDto).toList();
    }

    /* 방 입장 가능 여부 조회 */
    @Transactional
    public String roomEnter(EnterRequestDto enterRequestDto) {
        Room room = roomRepository.findById(enterRequestDto.getRoomId())
                .orElseThrow(() -> new CustomException(RoomErrorCode.NOT_FOUND_ROOM));

        if(room.getMemberList().size() >= room.getMaxNum())
            throw new CustomException(RoomErrorCode.ROOM_FULL);

        if(!room.getPassword().isEmpty() && !room.getPassword().equals(enterRequestDto.getPassword()))
            throw new CustomException(RoomErrorCode.PASSWORD_INCORRECT);

        Member member = memberRepository.findById(enterRequestDto.getMemberId())
                .orElseThrow(() -> new CustomException(MemberErrorCode.NOT_FOUND_MEMBER));

        member.enterRoom(room); // 맴버가 입장한 방 입력
        memberRepository.save(member);
        room.getMemberList().add(member); // 현재 방 맴버 추가
        roomRepository.save(room);

        return "ENTER_POSSIBLE";
    }

    /* 방 찾기 */
    public RoomResponseDto searchRoom(int roomId) {
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new CustomException(RoomErrorCode.NOT_FOUND_ROOM));

        if(room.getMemberList().size() >= room.getMaxNum())
            throw new CustomException(RoomErrorCode.ROOM_FULL);

        return RoomResponseDto.toDto(room);
    }

    /* 빠른 입장 방 찾기 */
    public RoomResponseDto fastRoom() {
        Room room = roomRepository.findFastRoom()
                .orElseThrow(() -> new CustomException(RoomErrorCode.NOT_FOUND_ROOM));

        return RoomResponseDto.toDto(room);
    }
}
