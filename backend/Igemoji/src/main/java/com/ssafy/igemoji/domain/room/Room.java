package com.ssafy.igemoji.domain.room;

import com.ssafy.igemoji.domain.member.Member;
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
@Table(name = "room")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Room extends BaseEntity {
    @Id
    @Column(name = "room_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "room_title")
    private String title;

    @Column(name = "is_public")
    private Boolean isPublic;

    @Column(name = "is_progress")
    private Boolean isProgress;

    @Column(name = "room_password")
    private String password;

    @Column(name = "room_max_num")
    private Integer maxNum;

    @Column(name = "room_genre")
    private String genre;

    @Column(name = "question_num")
    private Integer questionNum;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member host;

    @OneToMany(mappedBy = "room")
    private List<Member> memberList = new ArrayList<>();

    public void updateQuestionNum(Integer questionNum){
        this.questionNum = questionNum;
    }
    public void updateHost(Member member){
        this.host = member;
    }

    @Builder
    public Room(String title, String password, Integer maxNum, Boolean isPublic, Boolean isProgress, String genre, Integer questionNum,Member host){
        this.title = title;
        this.password = password;
        this.maxNum = maxNum;
        this.isPublic = isPublic;
        this.isProgress = isProgress;
        this.genre = genre;
        this.questionNum = questionNum;
        this.host = host;
    }

}
