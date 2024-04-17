package com.ssafy.igemoji.domain.room.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatSocketDto {
    private String roomId;
    private String nickname;
    private String content;
}