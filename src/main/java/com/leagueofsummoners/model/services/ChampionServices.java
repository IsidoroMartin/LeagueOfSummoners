package com.leagueofsummoners.model.services;

import com.leagueofsummoners.model.interfaces.services.IServicesChampions;
import com.leagueofsummoners.model.dto.ChampionDTO;
import com.leagueofsummoners.model.persistence.dao.ChampionDAO;

import com.robrua.orianna.type.core.staticdata.Champion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChampionServices implements IServicesChampions {

	@Autowired
	private ChampionDAO championDAO;

	public ChampionServices() {
	}

	@Override
	public List<ChampionDTO> getChampionList() {
		return this.championDAO.getChampionList();
	}

	public List<ChampionDTO> getChampionRotation() {return this.championDAO.getChampionRotation();}

	@Override
	public ChampionDTO findByChampionName(String championName) {
		return this.championDAO.getChampionByName(championName);
	}

	@Override
	public ChampionDTO findByIdChampion(Long idChampion) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<String> getChampionsIconsNamesList() {
		List<ChampionDTO> championsList = this.championDAO.getChampionList();
		List<String> championNames = new ArrayList<>();
		for (ChampionDTO championDTO : championsList) {
			championNames.add(championDTO.getChampionIconName());
		}
		return championNames;
	}

}
