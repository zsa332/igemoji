package com.ssafy.igemoji.domain.movie.repository;

import com.ssafy.igemoji.domain.movie.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Integer> {
}
