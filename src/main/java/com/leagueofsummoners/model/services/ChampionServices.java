package com.leagueofsummoners.model.services;

import com.leagueofsummoners.model.dao.SummonerDAO;
import com.leagueofsummoners.model.dto.MatchDTO;
import com.leagueofsummoners.model.dto.UserDTO;
import com.leagueofsummoners.model.interfaces.services.IServicesChampions;
import com.leagueofsummoners.model.dto.ChampionDTO;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.leagueofsummoners.model.dao.ChampionDAO;
import com.leagueofsummoners.model.utils.LeagueAccessAPI;
import com.leagueofsummoners.model.utils.RiotUtils;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class ChampionServices implements IServicesChampions, Serializable {

	@Autowired
	private ChampionDAO championDAO;
	@Autowired
	private SummonerDAO summonerDAO;

	public ChampionServices() {
	}

	@Override
	public List<ChampionDTO> getChampionList(boolean fullInfo) {
		return this.championDAO.getChampionList(fullInfo);
	}

	@Override
	public String[] getStringChampionList() {
		return RiotUtils.parseRiotListToString(this.championDAO.getChampionList(false));
	}

	public List<ChampionDTO> getChampionRotation() {
		List<ChampionDTO> champions = null;
		try {
			champions = this.championDAO.getChampionRotation();
		} catch (Exception e) {
			Gson gson = new Gson();
			champions = gson.fromJson(LeagueAccessAPI.RIOT_API_DEFAULT_ROTATION, new TypeToken<ArrayList<ChampionDTO>>(){}.getType());
			log.error("Error obteniendo la rotación de campeones, obteniendo campeones por defecto");
		}

		return champions;
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

	@Override
	public List<String> getChampionsIconsNamesList() {
		List<ChampionDTO> championsList = this.championDAO.getChampionList(false);
		List<String> championNames = new ArrayList<>();
		for (ChampionDTO championDTO : championsList) {
			championNames.add(championDTO.getChampionIconName());
		}
		return championNames;
	}

}
