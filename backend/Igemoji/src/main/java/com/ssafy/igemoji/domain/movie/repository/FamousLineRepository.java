package com.ssafy.igemoji.domain.movie.repository;

import com.ssafy.igemoji.domain.movie.Emoji;
import com.ssafy.igemoji.domain.movie.FamousLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FamousLineRepository extends JpaRepository<FamousLine, Integer> {
    @Query("SELECT f " +
            "FROM FamousLine f " +
            "WHERE f.movie.id = :movieId " +
            "ORDER BY RAND() " +
            "LIMIT 1")
    Optional<FamousLine> getRandByMovieId(Integer movieId);
}
