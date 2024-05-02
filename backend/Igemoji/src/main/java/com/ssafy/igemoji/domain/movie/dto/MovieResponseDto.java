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
    private String bgm;
    private String emoji;

    public static MovieResponseDto toDto(Movie movie){
        List<Emoji> emojiList = movie.getEmojiList();
        Collections.shuffle(emojiList);

        List<FamousLine> famousLineList = movie.getFamousLineList();
        Collections.shuffle(famousLineList);
        return MovieResponseDto.builder()
                .id(movie.getId())
                .name(movie.getName())
                .img(movie.getImg())
                .line(famousLineList.get(0).getLine())
                .bgm(movie.getBgm())
                .emoji(emojiList.isEmpty() ? "준비 중 입니다" : emojiList.get(0).getEmojiText())
                .build();
    }

}
