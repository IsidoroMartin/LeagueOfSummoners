package com.leagueofsummoners.model.dao;

import com.leagueofsummoners.model.interfaces.persistence.ChampionRepository;
import com.leagueofsummoners.model.interfaces.persistence.PassivesRepository;
import com.leagueofsummoners.model.interfaces.persistence.SpellsRepository;
import com.leagueofsummoners.model.utils.LeagueAccessAPI;
import com.leagueofsummoners.model.utils.RiotUtils;
import com.leagueofsummoners.model.dto.ChampionDTO;
import com.leagueofsummoners.model.dto.ChampionPassiveDTO;
import com.robrua.orianna.api.core.RiotAPI;
import com.robrua.orianna.type.core.champion.ChampionStatus;
import com.robrua.orianna.type.core.staticdata.Champion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class ChampionDAO {
	@Autowired
	private ChampionRepository championRepository;

	@Autowired
	PassivesRepository passiveRepository;

	@Autowired
	SpellsRepository spellRepository;

	public ChampionDTO findByChampionName(String championName) {
		return this.championRepository.findBychampionNameIgnoringCase(championName);
	}

	/**
	 * Devuelve una lista con todos los campeones
	 * @param fullInfo Indica si en la respuesta debe ir todala información de los campeones (Skills, spells etc)
	 * @return lista con todos los campeones
	 */
	public List<ChampionDTO> getChampionList(boolean fullInfo) {
		List<ChampionDTO> champions = this.championRepository.findAll();
		if (fullInfo) {
			for (ChampionDTO champion : champions) {
				champion.setSpellsList(this.spellRepository.findByIdChampion(champion.getIdChampion()));
				champion.setPassive(this.passiveRepository.findByIdPassive(champion.getIdPassive()));
				champion.setSplashArtUri(LeagueAccessAPI.RIOT_API_SPLASH_ART + 
						RiotUtils.determineSpecialChampionNames(RiotUtils.normalizeChampion(champion.getChampionName())) + "_0.jpg");
			}
		}
		return champions;
	}

	public List<ChampionDTO> getChampionRotation() {
		Map<Champion, ChampionStatus> championRotation = RiotAPI.getChampionStatuses(true);
		ArrayList<ChampionDTO> championsList = new ArrayList<ChampionDTO>();
		for (Map.Entry<Champion, ChampionStatus> entry : championRotation.entrySet()) {
			championsList.add(ChampionDTO.buildBasicChampionDTO((Champion) entry.getKey()));
		}
		return championsList;
	}

	public ChampionDTO getChampionByName(String championName) {
		return this.championRepository.findBychampionNameIgnoringCase(championName);
	}

}
