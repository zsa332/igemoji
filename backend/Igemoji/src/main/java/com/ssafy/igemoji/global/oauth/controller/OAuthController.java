package com.ssafy.igemoji.global.oauth.controller;

import com.ssafy.igemoji.global.common.dto.ResponseFactory;
import com.ssafy.igemoji.global.oauth.dto.KakaoLoginRequest;
import com.ssafy.igemoji.global.oauth.dto.LoginSuccessResponse;
import com.ssafy.igemoji.global.oauth.service.OAuthLoginService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/oauth")
@Tag(name = "소셜 로그인 API", description = "소셜 로그인 관련 API")
public class OAuthController {
    private final OAuthLoginService oAuthLoginService;

    @GetMapping("/kakao")
    @Operation(summary = "카카오 로그인", description = "카카오 로그인 API")
    public ResponseEntity<?> loginKakao(@RequestParam String code) {
        KakaoLoginRequest request = KakaoLoginRequest.builder().authorizationCode(code).build(); // 코드 입력
        LoginSuccessResponse response = oAuthLoginService.login(request);
        String message = response.getMemberInfo().getNickname().isEmpty() ? "닉네임 설정 필요" : "로그인 완료"; // 닉네임 확인
        return ResponseFactory.success(message, response);
    }

}
