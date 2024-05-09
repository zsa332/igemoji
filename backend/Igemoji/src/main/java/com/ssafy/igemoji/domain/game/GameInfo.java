package com.ssafy.igemoji.domain.game;

import com.ssafy.igemoji.domain.game.dto.GameStatus;
import com.ssafy.igemoji.domain.game.dto.PlayerResponseDto;
import com.ssafy.igemoji.domain.member.dto.MemberResponseDto;
import com.ssafy.igemoji.domain.movie.Movie;
import com.ssafy.igemoji.domain.movie.dto.MovieResponseDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class GameInfo {
    private Integer roomId;
    private String title;
    private Boolean isPublic;
    private String genre;
    private Integer hostId;
    private Integer remainingTime;
    private Integer remainingRound;
    private Integer questionNum;
    private GameStatus gameStatus;
    private List<MovieResponseDto> movieList;
    private Map<Integer, PlayerResponseDto> players;


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

    public void correctAnswer(String nickname){ this.movieList.get(remainingRound).setCorrectMember(nickname); }

    public String currentAnswer(){ return this.movieList.get(remainingRound).getName(); }

    public String chatPlayer(Integer playerId){ return players.get(playerId).getNickname(); }

    public void increasePlayerScore(Integer playerId){ players.get(playerId).increaseScore(); }

    public GameInfo(Integer remainingTime, Integer remainingRound, Integer questionNum, GameStatus gameStatus, List<MovieResponseDto> movieList, Map<Integer, PlayerResponseDto> players){
        this.remainingTime = remainingTime;
        this.remainingRound = remainingRound;
        this.questionNum = questionNum;
        this.gameStatus = gameStatus;
        this.movieList = movieList;
        this.players = players;
    }

}
