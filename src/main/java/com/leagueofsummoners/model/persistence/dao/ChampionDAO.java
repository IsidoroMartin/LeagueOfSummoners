package com.leagueofsummoners.model.persistence.dao;


import com.leagueofsummoners.model.interfaces.persistence.ChampionRepository;
import com.leagueofsummoners.model.dto.ChampionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

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

	public ChampionDTO getChampionByName(String championName) {
		return this.championRepository.findBychampionNameIgnoringCase(championName);
	}

}
