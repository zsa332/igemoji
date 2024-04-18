package com.ssafy.igemoji.global.oauth.controller;

import com.ssafy.igemoji.global.jwt.AuthToken;
import com.ssafy.igemoji.global.oauth.dto.KakaoLoginRequest;
import com.ssafy.igemoji.global.oauth.service.OAuthLoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/oauth")
public class OAuthController {
    private final OAuthLoginService oAuthLoginService;

    @PostMapping("/kakao")
    public ResponseEntity<AuthToken> loginKakao(@RequestBody KakaoLoginRequest request) {
        return ResponseEntity.ok(oAuthLoginService.login(request));
    }

}
