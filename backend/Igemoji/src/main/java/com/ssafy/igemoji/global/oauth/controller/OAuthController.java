package com.ssafy.igemoji.global.oauth.controller;

import com.ssafy.igemoji.global.common.dto.ResponseFactory;
import com.ssafy.igemoji.global.oauth.dto.KakaoLoginRequest;
import com.ssafy.igemoji.global.oauth.service.OAuthLoginService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/oauth")
@Tag(name = "소셜 로그인 API", description = "소셜 로그인 관련 API")
public class OAuthController {
    private final OAuthLoginService oAuthLoginService;

    @PostMapping("/kakao")
    @Operation(summary = "카카오 로그인", description = "카카오 로그인 API")
    @Parameter(name = "authorizationCode", description = "카카오 로그인시 redirect Url에서 code부분만 추출해서 입력해주세요.")
    @Parameter(name = "oauthProvider", description = "입력하지 않아도 됩니다.")
    public ResponseEntity<?> loginKakao(@RequestBody KakaoLoginRequest request) {
        return ResponseFactory.success("로그인 완료", oAuthLoginService.login(request));
    }

}
