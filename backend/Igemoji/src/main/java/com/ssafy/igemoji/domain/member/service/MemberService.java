package com.ssafy.igemoji.domain.member.service;

import com.ssafy.igemoji.domain.member.Member;
import com.ssafy.igemoji.domain.member.dto.NicknameRequestDto;
import com.ssafy.igemoji.domain.member.repository.MemberRepository;
import com.sun.jdi.InternalException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public void updateNickname(NicknameRequestDto requestDto){
        Member member = memberRepository.findById(requestDto.getMemberId()).orElseThrow(
                () -> new InternalException("예외  처리 예정")
        );

        member.updateNickname(requestDto.getNickname());

        memberRepository.save(member);
    }
}

