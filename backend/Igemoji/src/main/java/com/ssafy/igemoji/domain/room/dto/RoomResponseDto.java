package com.ssafy.igemoji.domain.room.dto;


import com.ssafy.igemoji.domain.member.dto.MemberResponseDto;
import com.ssafy.igemoji.domain.room.Room;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Builder
public class RoomResponseDto {
    private Integer roomId;
    private String title;
    private Boolean isPublic;
    private MemberResponseDto host;
    private Integer memberMaxNum;
    private Integer memberNum;
    private String password;

    public static RoomResponseDto toDto(Room room){
        return RoomResponseDto.builder()
                .roomId(room.getId())
                .title(room.getTitle())
                .isPublic(room.getIsPublic())
                .host(MemberResponseDto.toDto(room.getHost()))
                .memberMaxNum(room.getMaxNum())
                .memberNum(room.getMemberList().size())
                .build();
    }

}
