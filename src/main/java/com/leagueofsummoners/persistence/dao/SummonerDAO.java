package com.leagueofsummoners.persistence.dao;


import com.leagueofsummoners.LeagueofsummonersApplication;
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
        Summoner sum = null;
        try {
             sum = RiotAPI.getSummonerByName(summonerName);
        } catch (Exception e) {
            LeagueofsummonersApplication.LOGGER.error("Error obteniendo el summonername, probablemente no exista. " + e.getMessage());
        }
        return sum;
    }

}
