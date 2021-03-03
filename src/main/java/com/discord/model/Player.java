package com.discord.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class Player {

    @JsonProperty(value = "accountId")
    private String accountId;

    @JsonProperty(value = "stats")
    private List<Statistic> statistic;

    @JsonProperty(value = "epicUserHandle")
    private String name;


    private String rank;


    public double getHigherKd() {
        List<Statistic> statistics = getStatistic();
        double solo = Double.parseDouble(statistics.get(0).getKd());
        double duo = Double.parseDouble(statistics.get(1).getKd());
        double squad = Double.parseDouble(statistics.get(2).getKd());
        double tmp = Math.max(solo, duo);
        return Math.max(tmp, squad);
    }

}
