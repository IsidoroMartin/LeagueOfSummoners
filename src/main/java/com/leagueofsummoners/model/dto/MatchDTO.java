package com.leagueofsummoners.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.leagueofsummoners.model.dto.riotapi.RiotAPIMatch;
import com.leagueofsummoners.model.dto.riotapi.RiotApiParticipantInfo;
import com.robrua.orianna.type.core.staticdata.Item;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity(name = "MATCH")
@Table(name = "MATCHS")
public class MatchDTO {

    @Id
    @Column(name = "ID_MATCH")
    private long idMatch;

    @Column(name = "ID_USER")
    private long idUser;

    @Column(name = "MATCH_DURATION")
    private long matchDuration;

    @Column(name = "MATCH_WIN")
    private boolean winner;

    @Column(name = "ID_CHAMPION")
    private long idChampion;

    @Transient
    private String summonerName;

    @Transient
    private String championName;

    @Column(name = "KILLS")
    private Long kills;

    @Column(name = "DEATHS")
    private Long deaths;

    @Column(name = "ASSISTS")
    private Long assists;

    @Column(name = "GOLD_EARNED")
    private Long goldEarned;

    @Column(name = "ITEM0")
    private Long item0;

    @Column(name = "ITEM1")
    private Long item1;

    @Column(name = "ITEM2")
    private Long item2;

    @Column(name = "ITEM3")
    private Long item3;

    @Column(name = "ITEM4")
    private Long item4;

    @Column(name = "ITEM5")
    private Long item5;

    @Column(name = "ITEM6")
    private Long item6;

    @Transient
    private String stats;

    @Transient
    private String durationMins;

    @Transient
    private ItemDTO itemDto0;
    @Transient
    private ItemDTO itemDto1;
    @Transient
    private ItemDTO itemDto2;
    @Transient
    private ItemDTO itemDto3;
    @Transient
    private ItemDTO itemDto4;
    @Transient
    private ItemDTO itemDto5;
    @Transient
    private ItemDTO itemDto6;

    @Transient
    private String champMatchGlyphIcon;

    public String getChampMatchGlyphIcon(){
        return ((this.championName.contains(" ")) ? this.championName.replace(" ", "-") : this.championName).toLowerCase();
    }

    public String getStats() {
        return this.kills + "/" + this.deaths + "/" + this.assists;
    }

    public String getDurationMins() {
        return this.matchDuration / 60 + "''";
    }


    public MatchDTO parseToMatchDTO(RiotAPIMatch match, RiotApiParticipantInfo info) {
        this.idMatch = match.getMatchId();
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
        this.championName = info.getChampion().getChampionName();
        this.idChampion = info.getChampion().getIdChampion();
        return this;
    }

}