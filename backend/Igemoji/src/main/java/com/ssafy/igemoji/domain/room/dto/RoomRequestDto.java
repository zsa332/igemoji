package com.ssafy.igemoji.domain.room.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomRequestDto {
    private Integer memberId;
    private String title;
    private Integer questionNum;
    private Boolean isOpen;
    private String password;
}
