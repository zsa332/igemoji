package com.ssafy.igemoji.domain.oauth.service;

import com.ssafy.igemoji.domain.oauth.client.OAuthApiClient;
import com.ssafy.igemoji.domain.oauth.dto.OAuthInfoResponse;
import com.ssafy.igemoji.domain.oauth.dto.OAuthLoginRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class RequestOAuthInfoService {
    private final Map<String, OAuthApiClient> clients;

    public RequestOAuthInfoService(List<OAuthApiClient> clients) {
        this.clients = clients.stream().collect(
                Collectors.toUnmodifiableMap(OAuthApiClient::getOAuthProvider, Function.identity())
        );
    }

    public OAuthInfoResponse request(OAuthLoginRequest request) {
        OAuthApiClient client = clients.get(request.getOAuthProvider());
        String accessToken = client.requestAccessToken(request);
        return client.requestOauthInfo(accessToken);
    }
}
