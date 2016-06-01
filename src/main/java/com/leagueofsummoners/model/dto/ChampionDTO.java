package com.leagueofsummoners.model.dto;

import com.leagueofsummoners.model.utils.LeagueAccessAPI;
import com.leagueofsummoners.model.utils.RiotUtils;
import com.robrua.orianna.type.core.staticdata.Champion;
import lombok.Data;

import javax.persistence.*;


import java.io.Serializable;
import java.util.List;

import static com.leagueofsummoners.model.dao.tables.TableNames.*;

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
 * Este bean representa representa la entidad Champion de la base de datos
 */
@Data
@Entity(name = TABLE_CHAMPIONS_ENTITY)
@Table(name = TABLE_CHAMPIONS)
public class ChampionDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = COLUMN_CHAMPIONS_ID_CHAMPION)
	private Long idChampion;

	@Column(nullable = false, name = COLUMN_CHAMPIONS_CHAMPION_NAME)
	private String championName;

	@Column(nullable = false, name = COLUMN_CHAMPIONS_CHAMPION_TITLE_ES)
	private String championTitleES;

	@Column(name = COLUMN_CHAMPIONS_CHAMPION_ICON_NAME)
	private String championIconName;

	@Column(nullable = false, name = COLUMN_CHAMPIONS_ID_PASSIVE)
	private Long idPassive;

	@Column(nullable = false, name = COLUMN_CHAMPIONS_CHAMPION_LORE_ES)
	private String championLoreES;

	@Column(nullable = false, name = COLUMN_CHAMPIONS_CHAMPION_TYPE)
	private String championType;

	@Column(nullable = false, name = COLUMN_CHAMPIONS_CHAMPION_INFO)
	private String championInfo;

	@Transient
	private List<ChampionSpellDTO> spellsList;

	@Transient
	private ChampionPassiveDTO passive;

	@Transient
	private String championNormalized;
	
	@Transient
	private String championLinkNormalized;

	@Transient
	private String splashArtUri;

	public String toString() {
		return this.championName;
	}

	public String getChampionNormalized() {
		return RiotUtils.normalizeChampion(this.championName.toLowerCase());
	}
	
	public String getChampionLinkNormalized(){
		return RiotUtils.normalizeChampionForLink(this.championName.toLowerCase());
	}
	
	public void setChampionName(String championName){
		this.championName = championName;
	}

	public static ChampionDTO buildBasicChampionDTO(Champion champion) {
		ChampionDTO champ = new ChampionDTO();
		champ.idChampion = champion.getID();
		champ.championName = champion.getName();
		champ.championIconName = LeagueAccessAPI.RIOT_API_CHAMPIONS + champion.getImage().getFull();
		return champ;
	}
}