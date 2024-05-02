package com.ssafy.igemoji.domain.movie.service;

import com.ssafy.igemoji.domain.movie.Emoji;
import com.ssafy.igemoji.domain.movie.FamousLine;
import com.ssafy.igemoji.domain.movie.dto.MovieResponseDto;
import com.ssafy.igemoji.domain.movie.repository.EmojiRepository;
import com.ssafy.igemoji.domain.movie.repository.FamousLineRepository;
import com.ssafy.igemoji.domain.movie.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.query.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;
    private final FamousLineRepository famousLineRepository;
    private final EmojiRepository emojiRepository;

    /* 영화 문제 랜덤 추출 */
    public List<MovieResponseDto> getRandMovieList(int pageSize){
        List<MovieResponseDto> movieList = movieRepository
                .getRandMovieList(PageRequest.of(0, pageSize))
                .stream()
                .map(MovieResponseDto::toDto)
                .toList();

        for(MovieResponseDto movie : movieList){
            FamousLine famousLine = famousLineRepository.getRandByMovieId(movie.getId())
                    .orElse(null); // 아직 준비되지 않은 경우
            movie.setRandLine(famousLine == null ? "준비 중 입니다" : famousLine.getLine());
            Emoji emoji = emojiRepository.getRandByMovieId(movie.getId())
                    .orElse(null); // 아직 준비되지 않은 경우
            movie.setRandEmoji(emoji == null ? "준비 중 입니다" : emoji.getEmojiText());
        }

        return movieList;
    }
}
