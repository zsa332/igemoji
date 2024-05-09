package com.ssafy.igemoji.domain.room.dto;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Builder
public class RoomSocketDto {
    private String title;
    private Integer questionNum;
    private Integer participantNum;
}
