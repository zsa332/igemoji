package com.ssafy.igemoji.domain.room.dto;

import lombok.*;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RoomEnterRequestDto {
    private Integer memberId;
    private Integer roomId;
}

