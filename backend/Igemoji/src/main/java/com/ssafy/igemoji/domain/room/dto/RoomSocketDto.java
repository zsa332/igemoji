package com.ssafy.igemoji.domain.room.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoomSocketDto {
    private String title;
    private Integer questionNum;
    private Integer participantNum;
}
