package com.ssafy.igemoji.domain.movie;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "famous_line")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FamousLine {
    @Id
    @Column(name = "famous_line_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @Column(name = "line")
    private String line;
}
