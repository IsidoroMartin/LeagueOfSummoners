package com.leagueofsummoners.model.dto.riotapi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RiotAPIParticipantIdentity {

    private RiotAPIPlayer player;
}
