package com.ssafy.igemoji.domain.member.repository;

import com.ssafy.igemoji.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Integer> {
    Optional<Member> findByOauthId(String oauthId);

    @Query("SELECT m " +
            "FROM Member m " +
            "ORDER BY m.rating DESC " +
            "LIMIT 10")
    List<Member> getTop10Members();

    @Query("SELECT COUNT(m)" +
            "FROM Member m " +
            "WHERE m.rating > :rating ")
    Integer getMemberRank(Integer rating);

    boolean existsByNickname(String nickname);
}
