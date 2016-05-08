package com.leagueofsummoners.model.persistence.dao;



import com.leagueofsummoners.model.interfaces.persistence.ChampionRepository;
import com.leagueofsummoners.model.dto.ChampionDTO;
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

    public ChampionDTO findByChampionName(String championName) {
		return this.championRepository.findBychampionNameIgnoringCase(championName);
	}

	public List<ChampionDTO> getChampionList() {
		return this.championRepository.findAll();
	}
	public List<ChampionDTO> getChampionRotation() {
		Map<Champion,ChampionStatus> championRotation = RiotAPI.getChampionStatuses(true);
		ArrayList<ChampionDTO>championsList = new ArrayList<ChampionDTO>();
		for (Map.Entry<Champion, ChampionStatus> entry :championRotation.entrySet()) {
			championsList.add(ChampionDTO.buildBasicChampionDTO((Champion)entry.getKey()));
		}
		return championsList;
	}

	public ChampionDTO getChampionByName(String championName) {
		return this.championRepository.findBychampionNameIgnoringCase(championName);
	}

}
