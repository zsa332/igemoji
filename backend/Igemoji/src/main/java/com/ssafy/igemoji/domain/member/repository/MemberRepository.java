package com.ssafy.igemoji.domain.member.repository;

import com.ssafy.igemoji.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Integer> {
    Optional<Member> findByOauthId(String oauthId);
    boolean existsByNickname(String nickname);
}
