package com.ssafy.igemoji.domain.movie.repository;

import com.ssafy.igemoji.domain.movie.Emoji;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmojiRepository extends JpaRepository<Emoji, Integer> {
    @Query("SELECT e " +
            "FROM Emoji e " +
            "WHERE e.movie.id = :movieId " +
            "ORDER BY RAND() " +
            "LIMIT 1")
    Optional<Emoji> getRandByMovieId(Integer movieId);
}
