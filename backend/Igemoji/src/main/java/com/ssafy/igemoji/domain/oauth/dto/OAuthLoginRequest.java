package com.ssafy.igemoji.domain.oauth.dto;

import org.springframework.util.MultiValueMap;

public interface OAuthLoginRequest {
    String getOAuthProvider();
    MultiValueMap<String, String> makeBody();
}
