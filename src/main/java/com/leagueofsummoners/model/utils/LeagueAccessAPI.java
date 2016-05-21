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

    public static String LEAGUE_OF_LEGENDS_KEY = "863dd8a4-3747-47cc-9628-72cbd46a826e";// Juanjo
  /*  public static String LEAGUE_OF_LEGENDS_KEY = "731bd096-290a-4e20-aae8-0c5ff78522a6";//Isi*/


    /**
     * Riot API Urls
     */

//    public static final String RIOT_API_OBTAIN_SUMMONER_MATCHES = "https://euw.api.pvp.net/api/lol/euw/v2.2/matchlist/by-summoner/{summonerID}?seasons=SEASON2016&api_key=" + LeagueAccessAPI.LEAGUE_OF_LEGENDS_KEY;
    public static final String RIOT_API_OBTAIN_SUMMONER_MATCHES = "https://euw.api.pvp.net/api/lol/euw/v2.2/matchlist/by-summoner/{summonerID}?seasons=SEASON2016&api_key=" + LeagueAccessAPI.LEAGUE_OF_LEGENDS_KEY;
    public static final String RIOT_API_OBTAIN_INFO_MATCHES = "https://euw.api.pvp.net/api/lol/euw/v2.2/match/{matchId}?rankedQueues=RANKED_SOLO_5x5&seasons=SEASON2016&beginTime=1451602800000&api_key=" + LeagueAccessAPI.LEAGUE_OF_LEGENDS_KEY;
    public static final String RIOT_API_SUMMONER_PROFILE_ICON_PATH = "http://ddragon.leagueoflegends.com/cdn/6.6.1/img/profileicon/";
    public static final String RIOT_API_TEEMO_ICON = "http://ddragon.leagueoflegends.com/cdn/6.9.1/img/champion/Teemo.png";
    public static final String RIOT_API_ITEMS_PATH = "http://ddragon.leagueoflegends.com/cdn/6.9.1/img/item/";
    
    public static final String RIOT_API_DEFAULT_ROTATION = "[{\"idChampion\":35,\"championName\":\"Shaco\",\"championIconName\":\"http://ddragon.leagueoflegends.com/cdn/6.6.1/img/champion/Shaco.png\"},{\"idChampion\":103,\"championName\":\"Ahri\",\"championIconName\":\"http://ddragon.leagueoflegends.com/cdn/6.6.1/img/champion/Ahri.png\"},{\"idChampion\":23,\"championName\":\"Tryndamere\",\"championIconName\":\"http://ddragon.leagueoflegends.com/cdn/6.6.1/img/champion/Tryndamere.png\"},{\"idChampion\":104,\"championName\":\"Graves\",\"championIconName\":\"http://ddragon.leagueoflegends.com/cdn/6.6.1/img/champion/Graves.png\"},{\"idChampion\":120,\"championName\":\"Hecarim\",\"championIconName\":\"http://ddragon.leagueoflegends.com/cdn/6.6.1/img/champion/Hecarim.png\"},{\"idChampion\":89,\"championName\":\"Leona\",\"championIconName\":\"http://ddragon.leagueoflegends.com/cdn/6.6.1/img/champion/Leona.png\"},{\"idChampion\":44,\"championName\":\"Taric\",\"championIconName\":\"http://ddragon.leagueoflegends.com/cdn/6.6.1/img/champion/Taric.png\"},{\"idChampion\":110,\"championName\":\"Varus\",\"championIconName\":\"http://ddragon.leagueoflegends.com/cdn/6.6.1/img/champion/Varus.png\"},{\"idChampion\":63,\"championName\":\"Brand\",\"championIconName\":\"http://ddragon.leagueoflegends.com/cdn/6.6.1/img/champion/Brand.png\"},{\"idChampion\":32,\"championName\":\"Amumu\",\"championIconName\":\"http://ddragon.leagueoflegends.com/cdn/6.6.1/img/champion/Amumu.png\"}]";



    public static void initRIOTAPI() {
        LeagueofsummonersApplication.LOGGER.debug("La riot key que se está utilizando es: " + LEAGUE_OF_LEGENDS_KEY);
        RiotAPI.setRegion(Region.EUW);
        RiotAPI.setAPIKey(LEAGUE_OF_LEGENDS_KEY);
    }


    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Value("${riot.api.key}")
    public void setRiotAPI(String riotAPI) {
        LEAGUE_OF_LEGENDS_KEY = riotAPI;
    }
}

