package com.leagueofsummoners.model.dto.riotapi;

import com.leagueofsummoners.model.dto.ChampionDTO;
import lombok.Data;

@Data
public class RiotApiParticipantInfo {
    private String summonerName;
    private ChampionDTO champion;
    private RiotApiParticipantStats stats;
}
