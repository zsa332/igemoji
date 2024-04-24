package com.ssafy.igemoji.domain.room.service;

import com.ssafy.igemoji.domain.room.Room;
import com.ssafy.igemoji.domain.room.dto.RoomRequestDto;
import com.ssafy.igemoji.domain.room.dto.RoomSocketDto;
import com.ssafy.igemoji.domain.room.repository.RoomRepository;
import com.sun.jdi.InternalException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoomSocketService {

    private final RoomRepository roomRepository;

    public RoomSocketDto getRoomInfo(Integer roomId){
        Room room = roomRepository.findByIdByFetch(roomId).orElseThrow(
                () -> new InternalException("예외 처리 예정")
        );

        return RoomSocketDto.builder()
                .title(room.getTitle())
//                .questionNum(room.getQuestionNum())
                .participantNum(room.getMemberSet().size())
                .build();
    }
}
