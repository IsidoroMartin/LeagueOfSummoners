package com.leagueofsummoners;

import com.robrua.orianna.api.core.RiotAPI;
import com.robrua.orianna.type.core.common.Region;
import com.robrua.orianna.type.core.currentgame.CurrentGame;
import com.robrua.orianna.type.core.summoner.Summoner;

/**
 * Created by juanj on 01/05/2016.
 */
public class Test {
    public static final String LEAGUEOFLEGENDSKEY = "863dd8a4-3747-47cc-9628-72cbd46a826e";

    public static void initRIOTAPI() {
        RiotAPI.setRegion(Region.EUW);
        RiotAPI.setAPIKey(LEAGUEOFLEGENDSKEY);
    }

    public static void main(String[] args) {
        initRIOTAPI();
        Summoner summ = RiotAPI.getSummonerByName("airgonar");
        System.out.println("Nombre" + summ.getID());
        CurrentGame game = summ.getCurrentGame();
    }
}


