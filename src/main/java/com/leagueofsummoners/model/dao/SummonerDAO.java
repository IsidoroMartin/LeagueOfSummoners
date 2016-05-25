package com.leagueofsummoners.model.dao;

import com.leagueofsummoners.LeagueofsummonersApplication;
import com.leagueofsummoners.model.dto.ChampionDTO;
import com.leagueofsummoners.model.dto.MatchDTO;
import com.leagueofsummoners.model.dto.UserDTO;
import com.leagueofsummoners.model.dto.riotapi.*;
import com.leagueofsummoners.model.interfaces.persistence.ChampionRepository;
import com.leagueofsummoners.model.interfaces.persistence.ItemRepository;
import com.leagueofsummoners.model.interfaces.persistence.SummonerRepository;
import com.leagueofsummoners.model.utils.LeagueAccessAPI;
import com.leagueofsummoners.model.utils.RepositoryUtils;
import com.robrua.orianna.api.core.RiotAPI;
import com.robrua.orianna.type.core.summoner.Summoner;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

@Component
@Slf4j
public class SummonerDAO {

	@Autowired
	private ChampionRepository championRepository;

	@Autowired
	private SummonerRepository summonerRepository;

	@Autowired
	private ItemRepository itemsRepository;

	private RestTemplate rest;

	public SummonerDAO() {
		this.rest = new RestTemplate();
	}

	/**
	 * Obtiene un summoner de la BD de riot
	 *
	 * @param summonerName
	 * @return Summoner (invocador)
	 */
	public Summoner getSummonerData(String summonerName) {
		Summoner sum = null;
		try {
			sum = RiotAPI.getSummonerByName(summonerName);
		} catch (Exception e) {
			LeagueofsummonersApplication.LOGGER
					.error("Error obteniendo el summonername, probablemente no exista. " + e.getMessage());
		}
		return sum;
	}
	
	@Async
	public Future<List<MatchDTO>> getLatestMatchesFromRiotAsync(UserDTO user, int matchesNumber) {
		return this.getLatestMatchesFromRiot(user, matchesNumber);
	}


	/**
	 * Obtiene las últimas partidas de la Base de datos de RIOT
	 *
	 * @param summonerId
	 * @param summonerName
	 * @param matchesNumber
	 * @return Lista con las ultimas partidas del servidor de RIOT
	 */
	public Future<List<MatchDTO>> getLatestMatchesFromRiot(UserDTO user, int matchesNumber) {
		List<RiotAPIMatch> matchesAPI = this.getUserMatches(user);
		List<MatchDTO> matchList = new ArrayList<>();
		int matchSize = matchesAPI.size();
		if (matchSize > 0 && !checkIfLastMatchSavedIsLastRiotSaved(this.summonerRepository.idLastSavedMatch(user.getIdUser()),
						matchesAPI.get(0).getMatchId())) {
			// Si la i es menor que los 'match' pedidos y además los match
			// pedidos son menos que los match recibidos (Para evitar salir del
			// array)
			for (int i = 0; i < matchesNumber && matchesNumber < matchSize; i++) {
				try {
					MatchDTO matchDTO = new MatchDTO();
					RiotAPIMatch match = this.rest.getForObject(LeagueAccessAPI.RIOT_API_OBTAIN_INFO_MATCHES,
							RiotAPIMatch.class, matchesAPI.get(i).getMatchId());
					RiotApiParticipantInfo info = this.filterParticipantsBySummonerName(user.getSummonerName(),
							match.getParticipantIdentities(), match.getParticipants());
					matchList.add(matchDTO.parseToMatchDTO(match, info));
					matchDTO.setIdUser(user.getIdUser());
					matchDTO.setIdChampion(this.championRepository
							.findBychampionNameIgnoringCase(matchDTO.getChampionName()).getIdChampion());
					RepositoryUtils.setItemsInMatchDTO(this.itemsRepository, matchDTO);
					this.summonerRepository.save(matchDTO);
				} catch (Exception e) {
					log.error("Error obteniendo partida de " + user.getSummonerID() + " \n " + e.getMessage());
					e.printStackTrace();
				}
			}
		} else {
			matchList = this.getLatestMatchesFromDB(user);
		}
		return new AsyncResult<List<MatchDTO>>(matchList);
	}

	private boolean checkIfLastMatchSavedIsLastRiotSaved(Long idMatchBD, Long idMatchRiot) {
		if (idMatchBD != null && idMatchRiot != null) {
			if (idMatchBD.equals(idMatchRiot))
				return true;
		}
		return false;
	}

	/**
	 * Filtra por summonerName los participantes de la partida para que muestre
	 * el de el usuario que lo pide
	 *
	 * @param summonerName
	 * @param identity
	 * @param participants
	 * @return RiotApiParticipantInfo
	 */
	private RiotApiParticipantInfo filterParticipantsBySummonerName(String summonerName,
			List<RiotAPIParticipantIdentity> identity, List<RiotApiParticipant> participants) {
		RiotApiParticipantInfo info = new RiotApiParticipantInfo();
		int participantsSize = identity.size();
		int indexParticipant = 0;
		for (int i = 0; i < participantsSize; i++) {
			if (summonerName.equalsIgnoreCase(identity.get(i).getPlayer().getSummonerName())) {
				indexParticipant = i;
				break;
			}
		}
		info.setSummonerName(identity.get(indexParticipant).getPlayer().getSummonerName());
		info.setStats(participants.get(indexParticipant).getStats());
		info.setChampion(this.championRepository.findByIdChampion(participants.get(indexParticipant).getChampionId()));
		return info;
	}

	/**
	 * Este método obtiene las partidas guardadas en la BD
	 *
	 * @param userlogged
	 * @return Lista de las paritdas
	 */
	public List<MatchDTO> getLatestMatchesFromDB(UserDTO userlogged) {
		List<MatchDTO> matchs = this.summonerRepository.findTop10ByIdUser(userlogged.getIdUser());
		for (MatchDTO matchDTO : matchs) {
			ChampionDTO champion = this.championRepository.findByIdChampion(matchDTO.getIdChampion());
			matchDTO.setIdUser(userlogged.getIdUser());
			matchDTO.setChampionName(champion.getChampionName());
			matchDTO.setIdChampion(champion.getIdChampion());
			matchDTO.setSummonerName(userlogged.getSummonerName());
			RepositoryUtils.setItemsInMatchDTO(this.itemsRepository, matchDTO);
		}
		return matchs;
	}



	/**
	 * Devuelve las partidas del usuario
	 * 
	 * @param idSummoner
	 * @return La lista de partidas
	 */
	private List<RiotAPIMatch> getUserMatches(UserDTO user) {
		try {
			Summoner summ = null;
			String url = LeagueAccessAPI.RIOT_API_OBTAIN_SUMMONER_MATCHES;
			if (user.getSummonerID() == 0) {
				summ = RiotAPI.getSummonerByName(user.getSummonerName());
				user.setSummonerID(summ.getID());
			}
			url = url.replace("{summonerID}", "" + user.getSummonerID());
			List<RiotAPIMatch> matches = this.rest.getForObject(url,
					RiotAPIMatches.class).getMatches();
			return matches;
		} catch (Exception e) {
			e.printStackTrace();
			LeagueofsummonersApplication.LOGGER.error("Error obteniendo las partidas del invocador " + e.getMessage());
		}
		return new ArrayList<>();
	}

}
