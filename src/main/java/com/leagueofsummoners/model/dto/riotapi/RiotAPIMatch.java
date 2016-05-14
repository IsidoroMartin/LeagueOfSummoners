package com.leagueofsummoners.model.dto.riotapi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RiotAPIMatch {

    private long matchId;
    private long matchDuration;
    private List<RiotApiParticipant> participants;
    private List<RiotAPIParticipantIdentity> participantIdentities;

}
