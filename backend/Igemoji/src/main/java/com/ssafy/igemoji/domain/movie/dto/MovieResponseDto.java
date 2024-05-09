package com.ssafy.igemoji.domain.movie.dto;

import com.ssafy.igemoji.domain.movie.Emoji;
import com.ssafy.igemoji.domain.movie.FamousLine;
import com.ssafy.igemoji.domain.movie.Movie;
import lombok.*;

import java.util.Collections;
import java.util.List;

@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MovieResponseDto {
    private Integer id;
    private String name;
    private String img;
    private String line;
    private String emoji;
    private String chosung;
    private String correctMember;

    public static MovieResponseDto toDto(Movie movie){
        return MovieResponseDto.builder()
                .id(movie.getId())
                .name(movie.getName())
                .img(movie.getImg())
                .chosung(movie.getChosung())
                .build();
    }

    public void setCorrectMember(String correctMember){
        this.correctMember = correctMember;
    }
    public void setRandLine(String line){
        this.line = line;
    }
    public void setRandEmoji(String emoji){
        this.emoji = emoji;
    }

}
