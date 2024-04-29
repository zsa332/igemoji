package com.ssafy.igemoji.domain.room.dto;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class RoomRequestDto {
    private Integer memberId;
    private String title;
    private Boolean isPublic;
    private String password;
    private Integer memberMaxNum;
}
