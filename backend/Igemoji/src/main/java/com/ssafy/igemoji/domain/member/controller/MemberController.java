package com.ssafy.igemoji.domain.member.controller;

import com.ssafy.igemoji.domain.member.dto.NicknameRequestDto;
import com.ssafy.igemoji.domain.member.service.MemberService;
import com.ssafy.igemoji.global.common.dto.ResponseFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/nickname")
    public ResponseEntity<?> updateNickname(@RequestBody NicknameRequestDto requestDto){
        memberService.updateNickname(requestDto);
        return ResponseFactory.success("닉네임 수정 완료");
    }
}

