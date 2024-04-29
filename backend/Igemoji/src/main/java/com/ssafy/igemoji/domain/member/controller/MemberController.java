package com.ssafy.igemoji.domain.member.controller;

import com.ssafy.igemoji.domain.member.dto.NicknameRequestDto;
import com.ssafy.igemoji.domain.member.service.MemberService;
import com.ssafy.igemoji.global.common.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<ResponseDto> updateNickname(@RequestBody NicknameRequestDto requestDto){
        memberService.updateNickname(requestDto);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto("성공적으로 닉네임을 업데이트하였습니다."));
    }
}

