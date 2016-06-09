package com.leagueofsummoners.model.utils;

import com.leagueofsummoners.LeagueofsummonersApplication;
import com.robrua.orianna.api.core.RiotAPI;
import com.robrua.orianna.type.core.common.Region;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;


/*
Autores= Juan José Ramírez & Isidoro Martín
Fecha= Junio de 2016
Licencia=  gp130
Version= 1.0
Descripcion= Proyecto final desarrollo de aplicaciones web. League of Summoners es una aplicación
enfocada a los jugadores del popular juego League of Legends, usando esta aplicación podrán acceder
a guías, detalles sobre campeones e incluso sus últimas partidas.

Copyright (C) 2016 Juan José Ramírez & Isidoro Martín
This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.
This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.
You should have received a copy of the GNU General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/


/**
 * Esta clase contiene métodos útiles y rutas de riot games
 */
public class LeagueAccessAPI {

	// public static String LEAGUE_OF_LEGENDS_KEY =
	// "863dd8a4-3747-47cc-9628-72cbd46a826e";// Juanjo
	public static String LEAGUE_OF_LEGENDS_KEY = "731bd096-290a-4e20-aae8-0c5ff78522a6";// Isi

	/**
	 * Riot API Urls
	 */

	// public static final String RIOT_API_OBTAIN_SUMMONER_MATCHES =
	// "https://euw.api.pvp.net/api/lol/euw/v2.2/matchlist/by-summoner/{summonerID}?seasons=SEASON2016&api_key="
	// + LeagueAccessAPI.LEAGUE_OF_LEGENDS_KEY;
	public static final String RIOT_API_OBTAIN_SUMMONER_MATCHES = "https://euw.api.pvp.net/api/lol/euw/v2.2/matchlist/by-summoner/{summonerID}?seasons=SEASON2016&api_key="
			+ LeagueAccessAPI.LEAGUE_OF_LEGENDS_KEY;
	public static final String RIOT_API_OBTAIN_INFO_MATCHES = "https://euw.api.pvp.net/api/lol/euw/v2.2/match/{matchId}?rankedQueues=RANKED_SOLO_5x5&seasons=SEASON2016&beginTime=1451602800000&api_key="
			+ LeagueAccessAPI.LEAGUE_OF_LEGENDS_KEY;
	public static final String RIOT_API_SUMMONER_PROFILE_ICON_PATH = "http://ddragon.leagueoflegends.com/cdn/6.6.1/img/profileicon/";
	public static final String RIOT_API_TEEMO_ICON = "http://ddragon.leagueoflegends.com/cdn/6.11.1/img/champion/Teemo.png";
	public static final String RIOT_API_ITEMS_PATH = "http://ddragon.leagueoflegends.com/cdn/6.11.1/img/item/";
	public static final String RIOT_API_SPLASH_ART = "http://ddragon.leagueoflegends.com/cdn/img/champion/splash/";
	public static final String RIOT_API_CHAMPIONS = "http://ddragon.leagueoflegends.com/cdn/6.11.1/img/champion/";
	public static final String RIOT_API_NOT_FOUND = "img/notfoundimg.png";

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
