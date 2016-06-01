package com.leagueofsummoners.model.services;

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
import java.util.ArrayList;
import java.util.List;

/*
Autores= Juan José Ramírez & Isidoro Martín
Fecha= Junio de 2016
Licencia=  gp130
Version= 1.0
Descripcion= Proyecto final desarrollo de aplicaciones web. League of Summoners es una aplicación
enfocada a los jugadores del popular juego League of Legends, usando esta aplicación podrán acceder
a guías, detalles sobre campeones e incluso sus últimas partidas.

Copyright (C) 2016 Juan José Ramírez & Isidoro Martín
This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.
This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.
You should have received a copy of the GNU General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/


/**
 * Servicio campeones, esta es la capa intermedia entre los controlladores y los 
 * objetos DAO.
 */
@Slf4j
@Service
public class ChampionServices implements IServicesChampions, Serializable {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private ChampionDAO championDAO;

	public ChampionServices() {
	}
	

    /**
     * Devuelve una lista de todos los campeones
     * @return Lista con los campeones
     */
	@Override
	public List<ChampionDTO> getChampionList(boolean fullInfo) {
		return this.championDAO.getChampionList(fullInfo);
	}

	 /**
     * Devuelve un String[] con los nombres de los campeones
     * @return String[] con elnombre de los campeones
     */
	@Override
	public String[] getStringChampionList() {
		return RiotUtils.parseRiotListToString(this.championDAO.getChampionList(false));
	}

	 /**
     * Obtiene una lista con la rotación de estasemana
     * @return La lista o null
     */
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

	 /**
     * Encuentra a un usuario por nombre
     *
     * @param championName
     * @return El usuario con la nombre o null.
     */
	@Override
	public ChampionDTO findByChampionName(String championName) {
		return this.championDAO.findByChampionName(championName);
	}

	 /**
     * Obtiene los nombres de la lista de iconos de los campeones
     * @return la lista de nombres o null
     */
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
