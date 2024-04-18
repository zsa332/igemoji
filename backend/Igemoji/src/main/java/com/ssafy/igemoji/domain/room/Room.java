package com.ssafy.igemoji.domain.room;

import com.ssafy.igemoji.domain.member.Member;
import com.ssafy.igemoji.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashSet;
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

    @Column(name = "room_status")
    private Boolean status;

    @Column(name = "room_password")
    private String password;

    @Column(name = "room_manager")
    private Integer roomManager;

    @OneToMany(mappedBy = "room")
    private Set<Member> memberSet = new HashSet<>();

    @Builder
    public Room(
            String title,
            String password
    ){
        this.title = title;
        this.status = true;
        this.password = password;
    }

    public void updateRoomManager(Integer memberId){
        this.roomManager = memberId;
    }
}
