package com.ssafy.igemoji.domain.room.dto;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChatResponseDto {
    private Integer roomId;
    private String nickname;
    private String content;
    private MessageType message;
}