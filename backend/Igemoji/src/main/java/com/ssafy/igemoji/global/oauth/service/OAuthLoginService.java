package com.ssafy.igemoji.global.oauth.service;

import com.ssafy.igemoji.domain.member.Member;
import com.ssafy.igemoji.domain.member.repository.MemberRepository;
import com.ssafy.igemoji.global.jwt.AuthToken;
import com.ssafy.igemoji.global.jwt.AuthTokenGenerator;
import com.ssafy.igemoji.global.oauth.dto.OAuthInfoResponse;
import com.ssafy.igemoji.global.oauth.dto.OAuthLoginRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OAuthLoginService {
    private final MemberRepository memberRepository;
    private final AuthTokenGenerator authTokensGenerator;
    private final RequestOAuthInfoService requestOAuthInfoService;

    public AuthToken login(OAuthLoginRequest request) {
        OAuthInfoResponse oAuthInfoResponse = requestOAuthInfoService.request(request);
        Integer memberId = findOrCreateMember(oAuthInfoResponse);
        return authTokensGenerator.generate(memberId);
    }

    private Integer findOrCreateMember(OAuthInfoResponse oAuthInfoResponse) {
        return memberRepository.findByOauthId(oAuthInfoResponse.getOAuthProvider()+"_"+oAuthInfoResponse.getOAuthProviderId())
                .map(Member::getId)
                .orElseGet(() -> newMember(oAuthInfoResponse));
    }

    private Integer newMember(OAuthInfoResponse oAuthInfoResponse) {
        Member member = Member.builder()
                .oauthId(oAuthInfoResponse.getOAuthProvider()+"_"+oAuthInfoResponse.getOAuthProviderId())
                .build();

        return memberRepository.save(member).getId();
    }
}