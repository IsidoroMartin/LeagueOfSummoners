package com.leagueofsummoners.model.dao;

import com.leagueofsummoners.model.interfaces.persistence.ChampionRepository;
import com.leagueofsummoners.model.interfaces.persistence.PassivesRepository;
import com.leagueofsummoners.model.interfaces.persistence.SpellsRepository;
import com.leagueofsummoners.model.utils.LeagueAccessAPI;
import com.leagueofsummoners.model.utils.RiotUtils;
import com.leagueofsummoners.model.dto.ChampionDTO;
import com.robrua.orianna.api.core.RiotAPI;
import com.robrua.orianna.type.core.champion.ChampionStatus;
import com.robrua.orianna.type.core.staticdata.Champion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
 * Capa de acceso a los datos de los campeones 
 */
@Component
public class ChampionDAO {
	//Repositorio de campeones
	@Autowired
	private ChampionRepository championRepository;

	//Repositorio de passivas
	@Autowired
	PassivesRepository passiveRepository;

	//Repositorio de spells
	@Autowired
	SpellsRepository spellRepository;

	/**
	 * Busca un campeón por nombre
	 * @param championName
	 * @return ChampionDTO o null.
	 */
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
	
	/**
	 * Obtiene la rotación de campeones del api de riot
	 * @return La lista de campeones o null.
	 */
	public List<ChampionDTO> getChampionRotation() {
		Map<Champion, ChampionStatus> championRotation = RiotAPI.getChampionStatuses(true);
		ArrayList<ChampionDTO> championsList = new ArrayList<ChampionDTO>();
		for (Map.Entry<Champion, ChampionStatus> entry : championRotation.entrySet()) {
			championsList.add(ChampionDTO.buildBasicChampionDTO((Champion) entry.getKey()));
		}
		return championsList;
	}

}
