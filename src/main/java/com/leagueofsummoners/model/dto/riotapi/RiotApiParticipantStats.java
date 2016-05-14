package com.leagueofsummoners.model.dto.riotapi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RiotApiParticipantStats {

    private boolean winner;
    private long assists;
    private long champLevel;
    private long deaths;
    private long kills;
    private long goldEarned;
    private long item0;
    private long item1;
    private long item2;
    private long item3;
    private long item4;
    private long item5;
    private long item6;
}
