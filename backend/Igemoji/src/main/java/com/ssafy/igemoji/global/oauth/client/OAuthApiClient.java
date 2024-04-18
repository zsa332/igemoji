package com.ssafy.igemoji.global.oauth.client;

import com.ssafy.igemoji.global.oauth.dto.OAuthInfoResponse;
import com.ssafy.igemoji.global.oauth.dto.OAuthLoginRequest;

public interface OAuthApiClient {
    String getOAuthProvider();
    String requestAccessToken(OAuthLoginRequest request);
    OAuthInfoResponse requestOauthInfo(String accessToken);
}