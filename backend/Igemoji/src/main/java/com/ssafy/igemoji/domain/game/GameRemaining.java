package com.ssafy.igemoji.domain.game;

import com.ssafy.igemoji.domain.game.dto.GameStatus;
import com.ssafy.igemoji.domain.movie.Movie;
import com.ssafy.igemoji.domain.movie.dto.MovieResponseDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class GameRemaining {
    private Integer remainingTime;
    private Integer remainingRound;
    private GameStatus gameStatus;
    private List<MovieResponseDto> movieList;


    public void waitTime(){
        this.remainingTime = 3;
    }

    public void newRound(){
        this.remainingTime = 60;
    }

    public void decreaseTime(){
        this.remainingTime--;
    }

    public void decreaseRound(){
        this.remainingRound--;
    }

    public void updateGameStatus(GameStatus gameStatus){
        this.gameStatus = gameStatus;
    }
}
