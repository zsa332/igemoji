package com.ssafy.igemoji.domain.member;

import com.ssafy.igemoji.domain.level.Level;
import com.ssafy.igemoji.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "member")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {
    @Id
    @Column(name = "member_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="member_nickname")
    private String nickname;

    @Column(name="member_score")
    private Integer score;

    @Column(name="member_exp")
    private Integer exp;

    @OneToMany(mappedBy = "friend")
    private List<MemberFriend> friendList = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="level_id")
    private Level level;
}
