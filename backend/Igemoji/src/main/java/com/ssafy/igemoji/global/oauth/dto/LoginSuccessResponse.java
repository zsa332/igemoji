package com.ssafy.igemoji.global.oauth.dto;

import com.ssafy.igemoji.domain.member.dto.MemberResponseDto;
import com.ssafy.igemoji.global.jwt.AuthToken;
import jakarta.persistence.Entity;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LoginSuccessResponse {
    private MemberResponseDto memberInfo;
    private AuthToken authToken;
}
