package com.ssafy.igemoji.domain.member;

import com.ssafy.igemoji.domain.level.Level;
import com.ssafy.igemoji.domain.room.Room;
import com.ssafy.igemoji.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
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

    @Column(name="member_rating")
    private Integer rating;

    @Column(name="member_exp")
    private Integer exp;

    @Column(name = "oauth_id", unique = true)
    private String oauthId;

    @Column(name = "level")
    private Integer level;

    @OneToMany(mappedBy = "friend")
    private List<MemberFriend> friendList = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;

    public void saveRoom(Room room){
        room.getMemberList().add(this);
        room.updateHost(this);
        this.room = room;
    }

    public void enterRoom(Room room){
        this.room = room;
    }
    public void leaveRoom(){
        this.room = null;
    }
    public void updateNickname(String nickname){
        this.nickname = nickname;
    }

    @Builder
    public Member(Integer id, String nickname, Integer rating, Integer exp, String oauthId, List<MemberFriend> friendList, Room room, Integer level) {
        this.id = id;
        this.nickname = nickname;
        this.rating = rating;
        this.exp = exp;
        this.oauthId = oauthId;
        this.friendList = friendList;
        this.room = room;
        this.level = level;
    }
}

