package com.ssafy.igemoji.domain.oauth.client;

import com.ssafy.igemoji.domain.oauth.dto.OAuthInfoResponse;
import com.ssafy.igemoji.domain.oauth.dto.OAuthLoginRequest;

public interface OAuthApiClient {
    String getOAuthProvider();
    String requestAccessToken(OAuthLoginRequest request);
    OAuthInfoResponse requestOauthInfo(String accessToken);
}