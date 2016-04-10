package com.leagueofsummoners.persistence.dao;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.leagueofsummoners.model.dto.ChampionDTO;
import com.leagueofsummoners.persistence.interfaces.ChampionRepository;

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
