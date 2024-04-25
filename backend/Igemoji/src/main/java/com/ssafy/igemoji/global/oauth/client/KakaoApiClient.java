package com.ssafy.igemoji.global.oauth.client;


import com.ssafy.igemoji.global.jwt.KakaoToken;
import com.ssafy.igemoji.global.oauth.dto.KakaoInfoResponse;
import com.ssafy.igemoji.global.oauth.dto.OAuthInfoResponse;
import com.ssafy.igemoji.global.oauth.dto.OAuthLoginRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
@RequiredArgsConstructor
public class KakaoApiClient implements OAuthApiClient{

    @Value("${oauth2.client.registration.kakao.authorization-grant-type}")
    private String GRANT_TYPE;
    @Value("${oauth2.client.registration.kakao.client-id}")
    private String CLIENT_ID;
    @Value("${oauth2.client.registration.kakao.client-secret}")
    private String CLIENT_SECRET;
    @Value("${oauth2.client.provider.kakao.token-uri}")
    private String TOKEN_URI;
    @Value("${oauth2.client.provider.kakao.user-info-uri}")
    private String USER_INFO_URI;

    private final RestTemplate restTemplate;

    @Override
    public String getOAuthProvider() {
        return "kakao";
    }

    @Override
    public String requestAccessToken(OAuthLoginRequest request) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> body = request.makeBody();
        body.add("grant_type", GRANT_TYPE);
        body.add("client_id", CLIENT_ID);
        body.add("client_secret", CLIENT_SECRET);
        HttpEntity<?> kakaoRequest = new HttpEntity<>(body, httpHeaders);

        KakaoToken response = restTemplate.postForObject(TOKEN_URI, kakaoRequest, KakaoToken.class);

        assert response != null;
        return response.getAccessToken();
    }

    @Override
    public OAuthInfoResponse requestOauthInfo(String accessToken) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        httpHeaders.set("Authorization", "Bearer " + accessToken);

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("property_keys", "[\"kakao_account.email\", \"kakao_account.profile\"]");

        HttpEntity<?> request = new HttpEntity<>(body, httpHeaders);

        return restTemplate.postForObject(USER_INFO_URI, request, KakaoInfoResponse.class);
    }
}
