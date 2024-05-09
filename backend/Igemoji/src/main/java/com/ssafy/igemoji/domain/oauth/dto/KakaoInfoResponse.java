package com.ssafy.igemoji.domain.oauth.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class KakaoInfoResponse implements OAuthInfoResponse{

    @JsonProperty("id")
    private Long oAuthProviderId;

    @Override
    public String getOAuthProvider() {
        return "kakao";
    }

    @Override
    public Long getOAuthProviderId() {
        return oAuthProviderId;
    }
}
