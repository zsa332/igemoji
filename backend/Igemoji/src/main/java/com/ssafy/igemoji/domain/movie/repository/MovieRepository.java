package com.ssafy.igemoji.domain.movie.repository;

import com.ssafy.igemoji.domain.movie.Movie;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {
    @Query("SELECT m " +
            "FROM Movie m " +
            "ORDER BY RAND()")
    List<Movie> getRandMovieList(Pageable pageable);
}
