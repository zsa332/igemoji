package com.ssafy.igemoji.domain.movie;

import com.ssafy.igemoji.domain.member.MemberFriend;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "movie")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Movie {
    @Id
    @Column(name = "movie_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "movie_name")
    private String name;

    @Column(name = "movie_img")
    private String img;

    @Column(name = "movie_bgm")
    private String bgm;

    @Column(name = "movie_chosung")
    private String chosung;

    @OneToMany(mappedBy = "movie")
    private List<Emoji> emojiList = new ArrayList<>();

    @OneToMany(mappedBy = "movie")
    private List<FamousLine> famousLineList = new ArrayList<>();
}
