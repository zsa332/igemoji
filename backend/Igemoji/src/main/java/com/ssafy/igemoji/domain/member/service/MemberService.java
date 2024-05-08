package com.ssafy.igemoji.domain.member.service;

import com.ssafy.igemoji.domain.member.Member;
import com.ssafy.igemoji.domain.member.dto.MemberRankDto;
import com.ssafy.igemoji.domain.member.dto.NicknameRequestDto;
import com.ssafy.igemoji.domain.member.dto.RankResponseDto;
import com.ssafy.igemoji.domain.member.exception.MemberErrorCode;
import com.ssafy.igemoji.domain.member.repository.MemberRepository;
import com.ssafy.igemoji.global.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public void updateNickname(NicknameRequestDto requestDto){
        Member member = memberRepository.findById(requestDto.getMemberId()).orElseThrow(
                () -> new CustomException(MemberErrorCode.NOT_FOUND_MEMBER)
        );
        if(memberRepository.existsByNickname(requestDto.getNickname())){
            throw new CustomException(MemberErrorCode.NICKNAME_DUPLICATE);
        }
        member.updateNickname(requestDto.getNickname());
        memberRepository.save(member);
    }

    public RankResponseDto getRank(Integer memberId){
        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new CustomException(MemberErrorCode.NOT_FOUND_MEMBER)
        );
        Integer rank = memberRepository.getMemberRank(member.getRating());
        MemberRankDto myRank = MemberRankDto.toDto(member, rank);

        List<Member> memberList = memberRepository.getTop10Members();
        List<MemberRankDto> memberRankDtoList = new ArrayList<>();
        for(int i = 0; i < memberList.size(); i++){
            memberRankDtoList.add(MemberRankDto.toDto(memberList.get(i), i+1));
        }

        return new RankResponseDto(myRank, memberRankDtoList);
    }

    public boolean exitNickname(String nickname) {
        return memberRepository.existsByNickname(nickname);
    }
}

