package com.ssafy.igemoji.domain.movie;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    @Column(name = "movie_line")
    private String line;

    @Column(name = "movie_bgm")
    private String bgm;
}
