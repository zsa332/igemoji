package com.ssafy.igemoji.domain.room.service;

import com.ssafy.igemoji.domain.member.Member;
import com.ssafy.igemoji.domain.member.repository.MemberRepository;
import com.ssafy.igemoji.domain.room.Room;
import com.ssafy.igemoji.domain.room.dto.RoomRequestDto;
import com.ssafy.igemoji.domain.room.repository.RoomRepository;
import com.sun.jdi.InternalException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RoomService {

    private final MemberRepository memberRepository;
    private final RoomRepository roomRepository;

    @Transactional
    public Integer save(RoomRequestDto requestDto){

        Member member = memberRepository.findById(requestDto.getMemberId()).orElseThrow(
                () -> new InternalException("예외 처리 예정")
        );

        Room room = Room.builder()
                .title(requestDto.getTitle())
                .questionNum(requestDto.getQuestionNum())
                .isOpen(requestDto.getIsOpen())
                .password(requestDto.getPassword())
                .build();

        roomRepository.save(room);
        member.saveRoom(room);
        return room.getId();
    }

    @Transactional
    public void enterRoom(Integer roomId, Integer memberId){
        Room room = roomRepository.findById(roomId).orElseThrow(
                () -> new InternalException("예외 처리 예정")
        );

        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new InternalException("예외 처리 예정")
        );

        member.enterRoom(room);
    }
}
