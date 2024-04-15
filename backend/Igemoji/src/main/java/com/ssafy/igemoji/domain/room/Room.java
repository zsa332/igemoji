package com.ssafy.igemoji.domain.room;

import com.ssafy.igemoji.domain.member.Member;
import com.ssafy.igemoji.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "room")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Room extends BaseEntity {
    @Id
    @Column(name = "room_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "room_name")
    private String name;

    @Column(name = "max_num")
    private Integer maxNum;

    @Column(name = "room_status")
    private Boolean status;

    @Column(name = "is_open")
    private Boolean isOpen;

    @Column(name = "room_password")
    private String password;

    @Column(name = "question_num")
    private Integer questionNum;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
}
