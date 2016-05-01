package com.leagueofsummoners.model.interfaces.services;

import com.leagueofsummoners.model.dto.ChampionDTO;

import java.util.List;

public interface IServicesChampions  {

	/**
	 * Devuelve una lista de todos los campeones
	 * 
	 * @return Lista con los campeones
	 */
	List<ChampionDTO> getChampionList();

	/**
	 * Encuentra a un usuario por id
	 * 
	 * @param championName
	 * @return El usuario con la id o null.
	 */
	ChampionDTO findByChampionName(String championName);
	
	ChampionDTO findByIdChampion(Long idChampion);

	List<String> getChampionsIconsNamesList();

}
