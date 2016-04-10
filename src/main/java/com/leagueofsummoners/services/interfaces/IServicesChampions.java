package com.leagueofsummoners.services.interfaces;

import java.util.List;

import com.leagueofsummoners.model.dto.ChampionDTO;
import com.leagueofsummoners.model.dto.UserDTO;

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
	 * @param id
	 * @return El usuario con la id o null.
	 */
	ChampionDTO findByChampionName(String championName);
	
	ChampionDTO findByIdChampion(Long idChampion);

}
