package com.ssafy.igemoji.global.oauth.dto;

import org.springframework.util.MultiValueMap;

public interface OAuthLoginRequest {
    String getOAuthProvider();
    MultiValueMap<String, String> makeBody();
}
