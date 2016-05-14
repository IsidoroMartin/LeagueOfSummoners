package com.leagueofsummoners.model.dto.riotapi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class RiotApiParticipant {

    private long championId;
    private RiotApiParticipantStats stats;
}
