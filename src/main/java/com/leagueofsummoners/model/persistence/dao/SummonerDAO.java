package com.leagueofsummoners.model.persistence.dao;


import com.leagueofsummoners.LeagueofsummonersApplication;
import com.robrua.orianna.api.core.RiotAPI;
import com.robrua.orianna.type.core.summoner.Summoner;
import org.springframework.stereotype.Component;

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
