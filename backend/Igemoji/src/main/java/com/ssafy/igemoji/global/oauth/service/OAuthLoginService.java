package com.ssafy.igemoji.global.oauth.service;

import com.ssafy.igemoji.domain.level.Level;
import com.ssafy.igemoji.domain.level.repository.LevelRepository;
import com.ssafy.igemoji.domain.member.Member;
import com.ssafy.igemoji.domain.member.dto.MemberResponseDto;
import com.ssafy.igemoji.domain.member.repository.MemberRepository;
import com.ssafy.igemoji.global.jwt.AuthTokenGenerator;
import com.ssafy.igemoji.global.oauth.dto.LoginSuccessResponse;
import com.ssafy.igemoji.global.oauth.dto.OAuthInfoResponse;
import com.ssafy.igemoji.global.oauth.dto.OAuthLoginRequest;
import com.sun.jdi.InternalException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OAuthLoginService {
    private final MemberRepository memberRepository;
    private final LevelRepository levelRepository;
    private final AuthTokenGenerator authTokensGenerator;
    private final RequestOAuthInfoService requestOAuthInfoService;

    /* 로그인 시 유저 정보 및 토큰 반환 */
    public LoginSuccessResponse login(OAuthLoginRequest request) {
        OAuthInfoResponse oAuthInfoResponse = requestOAuthInfoService.request(request);
        Member member = findOrCreateMember(oAuthInfoResponse); // 초기 로그인 시 save

        return LoginSuccessResponse.builder()
                .memberInfo(MemberResponseDto.toDto(member))
                .authToken(authTokensGenerator.generate(member.getId()))
                .build();
    }

    /* OAuth 정보를 통해 member 탐색 */
    private Member findOrCreateMember(OAuthInfoResponse oAuthInfoResponse) {
        return memberRepository.findByOauthId(oAuthInfoResponse.getOAuthProvider()+"_"+oAuthInfoResponse.getOAuthProviderId())
                .orElseGet(() -> newMember(oAuthInfoResponse)); // 초기 로그인이라면 DB에 save
    }

    /* 초기 로그인 member save */
    private Member newMember(OAuthInfoResponse oAuthInfoResponse) {
        Member member = Member.builder()
                .oauthId(oAuthInfoResponse.getOAuthProvider()+"_"+oAuthInfoResponse.getOAuthProviderId())
                .level(1)
                .rating(0)
                .exp(0)
                .build();

        return memberRepository.save(member);
    }
}