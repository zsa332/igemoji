package com.ssafy.igemoji.domain.member.repository;

import com.ssafy.igemoji.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Integer> {
}
