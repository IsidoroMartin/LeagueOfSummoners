package com.leagueofsummoners.persistence.dao;


import com.leagueofsummoners.interfaces.persistence.ChampionRepository;
import com.leagueofsummoners.model.dto.ChampionDTO;
import com.robrua.orianna.api.core.RiotAPI;
import com.robrua.orianna.type.core.summoner.Summoner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SummonerDAO {

    public Summoner getSummonerData(String summonerName) {
        Summoner sum = RiotAPI.getSummonerByName(summonerName);
        return sum;
    }

}
