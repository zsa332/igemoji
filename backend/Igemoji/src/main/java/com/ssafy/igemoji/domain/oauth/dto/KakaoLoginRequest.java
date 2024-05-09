package com.ssafy.igemoji.domain.oauth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KakaoLoginRequest implements OAuthLoginRequest {
    private String authorizationCode;

    @Override
    public String getOAuthProvider() {
        return "kakao";
    }

    @Override
    public MultiValueMap<String, String> makeBody() {
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("code", authorizationCode);
        return body;
    }
}