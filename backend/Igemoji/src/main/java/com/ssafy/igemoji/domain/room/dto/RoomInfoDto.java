package com.ssafy.igemoji.domain.room.dto;

import com.ssafy.igemoji.domain.member.dto.MemberResponseDto;
import com.ssafy.igemoji.domain.room.Room;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoomInfoDto {
    private Integer roomId;
    private MessageType message;
    private String title;
    private Boolean isPublic;
    private Boolean isProgress;
    private String genre;
    private Integer questionNum;
    private Integer memberMaxNum;
    private Integer memberCurNum;
    private MemberResponseDto host;
    private List<MemberResponseDto> memberList;
    private Integer senderId;
    private String senderNickname;

    public static RoomInfoDto toDto(Room room, Integer senderId, String senderNickname, MessageType messageType){
        return RoomInfoDto.builder()
                .roomId(room.getId())
                .message(messageType)
                .title(room.getTitle())
                .isPublic(room.getIsPublic())
                .isProgress(room.getIsProgress())
                .genre(room.getGenre())
                .questionNum(room.getQuestionNum())
                .memberMaxNum(room.getMaxNum())
                .memberCurNum(room.getMemberList().size())
                .host(MemberResponseDto.toDto(room.getHost()))

                .memberList(
                        room.getMemberList().stream()
                                .map(MemberResponseDto::toDto)
                                .collect(Collectors.toList())
                )
                .senderId(senderId)
                .senderNickname(senderNickname)
                .build();
    }
}
