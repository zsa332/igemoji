package com.ssafy.igemoji.domain.level;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "level")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Level {
    @Id
    @Column(name = "level_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "level_exp")
    private Integer exp;

    @Column(name = "level_img")
    private String img;
}
