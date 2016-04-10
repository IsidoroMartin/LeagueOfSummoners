package com.leagueofsummoners.model.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.leagueofsummoners.model.dto.ChampionDTO;
import com.leagueofsummoners.model.dto.GuideDTO;
import com.leagueofsummoners.model.dto.UserDTO;
import com.leagueofsummoners.persistence.dao.ChampionDAO;
import com.leagueofsummoners.persistence.dao.UserDAO;
import com.leagueofsummoners.persistence.interfaces.UserRepository;
import com.leagueofsummoners.services.interfaces.IServicesChampions;
import com.leagueofsummoners.services.interfaces.IServicesUsers;

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


	@Override
	public ChampionDTO findByChampionName(String championName) {
		return this.championDAO.getChampionByName(championName);
	}


	@Override
	public ChampionDTO findByIdChampion(Long idChampion) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
