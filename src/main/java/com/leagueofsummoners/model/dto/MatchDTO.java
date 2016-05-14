package com.leagueofsummoners.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.leagueofsummoners.model.dto.riotapi.RiotAPIMatch;
import com.leagueofsummoners.model.dto.riotapi.RiotApiParticipantInfo;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MatchDTO {

    private long matchId;
    private long matchDuration;
    private boolean winner;
    private String summonerName;
    private ChampionDTO champion;
    private long kills;
    private long deaths;
    private long assists;
    private long goldEarned;
    private long item0;
    private long item1;
    private long item2;
    private long item3;
    private long item4;
    private long item5;
    private long item6;
    private String stats;
    private String durationMins;


    public String getStats() {
        return this.kills + "/" + this.deaths + "/" + this.assists;
    }

    public String getDurationMins(){
        return this.matchDuration / 60 + "''";
    }


    public MatchDTO parseToMatchDTO(RiotAPIMatch match, RiotApiParticipantInfo info) {
        this.matchId = match.getMatchId();
        this.matchDuration = match.getMatchDuration();
        this.winner = info.getStats().isWinner();
        this.summonerName = info.getSummonerName();
        this.kills = info.getStats().getKills();
        this.deaths = info.getStats().getDeaths();
        this.assists = info.getStats().getAssists();
        this.goldEarned = info.getStats().getGoldEarned();
        this.item0 = info.getStats().getItem0();
        this.item1 = info.getStats().getItem1();
        this.item2 = info.getStats().getItem2();
        this.item3 = info.getStats().getItem3();
        this.item4 = info.getStats().getItem4();
        this.item5 = info.getStats().getItem5();
        this.item6 = info.getStats().getItem6();
        this.champion = info.getChampion();
        return this;
    }

}