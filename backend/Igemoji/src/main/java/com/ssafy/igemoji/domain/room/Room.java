package com.ssafy.igemoji.domain.room;

import com.ssafy.igemoji.domain.member.Member;
import com.ssafy.igemoji.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Entity
@Table(name = "room")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Room extends BaseEntity {
    @Id
    @Column(name = "room_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "room_title")
    private String title;

    @Column(name = "room_isPublic")
    private Boolean isPublic;

    @Column(name = "room_password")
    private String password;

    @Column(name = "room_max_num")
    private Integer maxNum;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member host;

    @OneToMany(mappedBy = "room")
    private List<Member> memberList = new ArrayList<>();

    public void updateHost(Member member){
        this.host = member;
    }

    @Builder
    public Room(String title, String password, Integer maxNum, Boolean isPublic, Member host){
        this.title = title;
        this.password = password;
        this.maxNum = maxNum;
        this.isPublic = isPublic;
        this.host = host;
    }

}
