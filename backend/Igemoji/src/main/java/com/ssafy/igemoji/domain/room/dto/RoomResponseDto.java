package com.ssafy.igemoji.domain.room.dto;


import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Builder
public class RoomResponseDto {
    private Integer roomId;
    private String title;
    private Boolean status;
    private Integer memberNum;
    private String password;
}
