package com.leagueofsummoners.model.utils;

import com.leagueofsummoners.LeagueofsummonersApplication;
import com.robrua.orianna.api.core.RiotAPI;
import com.robrua.orianna.type.core.common.Region;
import com.robrua.orianna.type.core.currentgame.CurrentGame;
import com.robrua.orianna.type.core.league.League;
import com.robrua.orianna.type.core.match.Match;
import com.robrua.orianna.type.core.matchlist.MatchReference;
import com.robrua.orianna.type.core.summoner.Summoner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by juanj on 16/04/2016.
 */
public class LeagueAccessAPI {

//        public static String LEAGUE_OF_LEGENDS_KEY = "863dd8a4-3747-47cc-9628-72cbd46a826e";// Juanjo
    public static String LEAGUE_OF_LEGENDS_KEY = "731bd096-290a-4e20-aae8-0c5ff78522a6";//Isi


    /**
     * Riot API Urls
     */

    public static final String RIOT_API_OBTAIN_SUMMONER_MATCHES = "https://euw.api.pvp.net/api/lol/euw/v2.2/matchlist/by-summoner/{summonerID}?seasons=SEASON2016&api_key=" + LeagueAccessAPI.LEAGUE_OF_LEGENDS_KEY;
    public static final String RIOT_API_OBTAIN_INFO_MATCHES = "https://euw.api.pvp.net/api/lol/euw/v2.2/match/{matchId}?api_key=" + LeagueAccessAPI.LEAGUE_OF_LEGENDS_KEY;
    public static final String RIOT_API_SUMMONER_PROFILE_ICON_PATH = "http://lkimg.zamimg.com/images/v2/summoner/icons/size64x64/";
    public static final String RIOT_API_TEEMO_ICON = "http://ddragon.leagueoflegends.com/cdn/6.9.1/img/champion/Teemo.png";


    public static void initRIOTAPI() {
        LeagueofsummonersApplication.LOGGER.debug("La riot key que se está utilizando es: " + LEAGUE_OF_LEGENDS_KEY);
        RiotAPI.setRegion(Region.EUW);
        RiotAPI.setAPIKey(LEAGUE_OF_LEGENDS_KEY);
    }

   /* public static void main(String[] args) {
        initRIOTAPI();
        Summoner summ = RiotAPI.getSummonerByName("zrows");
        System.out.println(summ.getMatchList(10,0));
       *//* List<MatchReference> lista =*//*
*//*
        System.out.println(lista.size());

        for (int i = 0; i < lista.size(); i++) {
            System.out.println(lista.get(i).getChampion());
        }
*//*

    }*/

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Value("${riot.api.key}")
    public void setRiotAPI(String riotAPI) {
        LEAGUE_OF_LEGENDS_KEY = riotAPI;
    }
}

