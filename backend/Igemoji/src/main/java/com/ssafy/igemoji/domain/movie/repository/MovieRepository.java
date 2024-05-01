package com.ssafy.igemoji.domain.movie.repository;

import com.ssafy.igemoji.domain.movie.Movie;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Integer> {
    @Query("SELECT m " +
            "FROM Movie m " +
            "JOIN FETCH m.emojiList e " +
            "ORDER BY RAND()")
    List<Movie> getRandMovieList(Pageable pageable);
}
