package com.leagueofsummoners.model.utils;

import com.leagueofsummoners.model.dto.UserDTO;
import com.robrua.orianna.api.core.RiotAPI;
import com.robrua.orianna.type.core.common.QueueType;
import com.robrua.orianna.type.core.common.Region;
import com.robrua.orianna.type.core.league.League;
import com.robrua.orianna.type.core.staticdata.Champion;
import com.robrua.orianna.type.core.summoner.Summoner;

import java.util.List;

/**
 * Created by juanj on 16/04/2016.
 */
public class LeagueAccessAPI {

    public static final String LEAGUEOFLEGENDSKEY = "863dd8a4-3747-47cc-9628-72cbd46a826e";

    public static void initRIOTAPI() {
        RiotAPI.setRegion(Region.EUW);
        RiotAPI.setAPIKey(LEAGUEOFLEGENDSKEY);
    }

/*
    public static void main(String[] args) {
        initRIOTAPI();
        Summoner summ = RiotAPI.getSummonerByName("zrows");
        System.out.println("Nombre" + summ.getName());
        System.out.println("CurrentGame: " + summ.getCurrentGame());
        List<League> lista = summ.getLeagueEntries();

        for (int i = 0; i < lista.size(); i++) {
            System.out.println(lista.get(i).getTier());
        }
        
        System.out.println("Level" + summ.getLevel());
        System.out.println("Icon ID " + summ.getProfileIconID());
    }
}
*/
}
