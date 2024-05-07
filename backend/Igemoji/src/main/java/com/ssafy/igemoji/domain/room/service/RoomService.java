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

    /* 모든 방 조회 */
    public List<RoomResponseDto> findAllRoom(int pageNum){
        return roomRepository.findAll(PageRequest.of(pageNum, 10)).getContent().stream().map(RoomResponseDto::toDto).toList();
    }

}
