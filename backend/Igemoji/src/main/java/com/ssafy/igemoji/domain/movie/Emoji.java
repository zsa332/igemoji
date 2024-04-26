package com.ssafy.igemoji.domain.movie;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "emoji")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Emoji {
    @Id
    @Column(name = "emoji_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @Column(name = "emoji_text")
    private String emojiText;
}
