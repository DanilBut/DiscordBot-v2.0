package com.discord.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class Statistic {

    private String score;
    private String kd;
    private String kills;
    private String winRatio;
    private String winGames;
    private String games;
    private String rank;


    public Statistic(String score, String kd, String kills, String winRatio, String winGames, String games) {
        this.score = score;
        this.kd = kd;
        this.kills = kills;
        this.winRatio = winRatio;
        this.winGames = winGames;
        this.games = games;
    }
}
