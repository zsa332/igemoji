package com.ssafy.igemoji.domain.room.service;

import com.ssafy.igemoji.domain.member.Member;
import com.ssafy.igemoji.domain.member.exception.MemberErrorCode;
import com.ssafy.igemoji.domain.member.repository.MemberRepository;
import com.ssafy.igemoji.domain.room.Room;
import com.ssafy.igemoji.domain.room.dto.*;
import com.ssafy.igemoji.domain.room.exception.RoomErrorCode;
import com.ssafy.igemoji.domain.room.repository.RoomRepository;
import com.ssafy.igemoji.global.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class RoomSocketService {

    private final RoomRepository roomRepository;
    private final MemberRepository memberRepository;
    private static final Map<String, Integer> sessionMap = new HashMap<>();

    /* 방 입장 */
    public RoomInfoDto enterRoom(RoomEnterRequestDto roomEnterRequestDto, String sessionId){
        Room room = roomRepository.findById(roomEnterRequestDto.getRoomId()).orElseThrow(
                () -> new CustomException(RoomErrorCode.NOT_FOUND_ROOM)
        );

        if(room.getMaxNum() <= room.getMemberList().size())
            throw new CustomException(RoomErrorCode.ROOM_FULL);

        Member member = memberRepository.findById(roomEnterRequestDto.getMemberId()).orElseThrow(
                () -> new CustomException(MemberErrorCode.NOT_FOUND_MEMBER)
        );

        sessionMap.put(sessionId, member.getId());

        return RoomInfoDto.toDto(room, member.getId(),member.getNickname(), MessageType.ENTER_SUCCESS);
    }

    @Transactional
    public RoomInfoDto leaveRoom(String sessionId){
        Member member = memberRepository.findById(sessionMap.get(sessionId)).orElseThrow(
                () -> new CustomException(MemberErrorCode.NOT_FOUND_MEMBER)
        );

        Room room = member.getRoom();

        member.leaveRoom(); // 맴버 입장한 방 지우기
        room.getMemberList().remove(member); // 현재 방 맴버에서 제거

        if(room.getMemberList().isEmpty()){ // 빈 방이라면 제거
            roomRepository.delete(room);
        } else {
            if(room.getHost() == member){ // 호스트가 나간 경우 호스트 양도
                room.updateHost(room.getMemberList().get(0));
            }
            roomRepository.save(room);
        }

        sessionMap.remove(sessionId); // 퇴장한 맴버 session 제거

        return RoomInfoDto.toDto(room, member.getId(), member.getNickname(), MessageType.LEAVE_ROOM);
    }

    @Transactional
    public RoomInfoDto updateQuestion(UpdateQuestionRequestDto updateQuestionRequestDto) {
        Room room = roomRepository.findById(updateQuestionRequestDto.getRoomId()).orElseThrow(
                () -> new CustomException(RoomErrorCode.NOT_FOUND_ROOM)
        );

        room.updateQuestionNum(updateQuestionRequestDto.getQuestionNum());
        roomRepository.save(room);

        return RoomInfoDto.toDto(room, 0, "system", MessageType.CHANGE_SET);
    }

    public ChatResponseDto sendChat(ChatRequestDto chatRequestDto, MessageType message) {
        Member member = memberRepository.findById(chatRequestDto.getMemberId()).orElseThrow(
                () -> new CustomException(MemberErrorCode.NOT_FOUND_MEMBER)
        );

        return ChatResponseDto.builder()
                .roomId(chatRequestDto.getRoomId())
                .message(message)
                .nickname(member.getNickname())
                .content(chatRequestDto.getContent())
                .build();
    }
}
