package com.ssafy.igemoji.domain.room.service;

import com.ssafy.igemoji.domain.member.Member;
import com.ssafy.igemoji.domain.member.exception.MemberErrorCode;
import com.ssafy.igemoji.domain.member.repository.MemberRepository;
import com.ssafy.igemoji.domain.room.Room;
import com.ssafy.igemoji.domain.room.dto.RoomRequestDto;
import com.ssafy.igemoji.domain.room.dto.RoomResponseDto;
import com.ssafy.igemoji.domain.room.exception.RoomErrorCode;
import com.ssafy.igemoji.domain.room.repository.RoomRepository;
import com.ssafy.igemoji.global.exception.CustomException;
import com.sun.jdi.InternalException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
        Member member = memberRepository.findById(requestDto.getMemberId()).orElseThrow(
                () -> new CustomException(MemberErrorCode.NOT_FOUND_MEMBER)
        );

        Room room = Room.builder()
                .title(requestDto.getTitle())
                .isPublic(requestDto.getIsPublic())
                .password(requestDto.getPassword())
                .maxNum(requestDto.getMemberMaxNum())
                .genre("movie")
                .questionNum(10)
                .host(member)
                .build();

        roomRepository.save(room);
        return RoomResponseDto.toDto(room);
    }



    /* 방 입장 */
    @Transactional
    public void enterRoom(Integer roomId, Integer memberId){
        Room room = roomRepository.findById(roomId).orElseThrow(
                () -> new CustomException(RoomErrorCode.NOT_FOUND_ROOM)
        );

        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new CustomException(MemberErrorCode.NOT_FOUND_MEMBER)
        );

        member.enterRoom(room);
        // 이 후 소켓 연결
    }

    /* 랜덤 방 조회 및 입장 */
    public void enterRandomRoom(Integer memberId){
        List<Room> possibleRoomList = roomRepository.findPossibleRoom();
        Random random = new Random(System.currentTimeMillis());

        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new CustomException(MemberErrorCode.NOT_FOUND_MEMBER)
        );

        member.enterRoom(possibleRoomList.get(random.nextInt(possibleRoomList.size())));
        // 이후 소켓 연결
    }

    /* 모든 방 조회 */
    public List<RoomResponseDto> findAllRoom(int pageNum){
        PageRequest pageRequest = PageRequest.of(pageNum, 10);
        List<Room> roomList = roomRepository.findAllByRecent(pageRequest);
        List<RoomResponseDto> roomResponseDtoList = new ArrayList<>();

        roomList.forEach(room -> roomResponseDtoList.add(RoomResponseDto.toDto(room)));

        return roomResponseDtoList;

    }

}
