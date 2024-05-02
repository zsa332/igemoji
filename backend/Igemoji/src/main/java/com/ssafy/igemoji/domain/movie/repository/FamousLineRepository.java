package com.ssafy.igemoji.domain.movie.repository;

import com.ssafy.igemoji.domain.movie.Emoji;
import com.ssafy.igemoji.domain.movie.FamousLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface FamousLineRepository extends JpaRepository<Integer, FamousLine> {
    @Query("SELECT F " +
            "FROM FamousLine f " +
            "WHERE f.movie.id = :movieId " +
            "ORDER BY RAND() " +
            "LIMIT 1")
    Optional<Emoji> getRandByMovieId(Integer movieId);
}
