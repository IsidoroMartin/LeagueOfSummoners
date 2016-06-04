package com.leagueofsummoners.model.dto;

import javax.persistence.*;
import javax.persistence.Column;

import com.leagueofsummoners.model.dao.tables.TableNames;
import com.mysema.query.sql.*;
import lombok.Data;

import static com.leagueofsummoners.model.dao.tables.TableNames.*;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
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
 * Este bean representa la entidad guias de la base de datos
 */

@Data
@Entity(name = TABLE_GUIDES_ENTITY)
@Table(name = TABLE_GUIDES)
public class GuideDTO implements Comparator<GuideDTO> {

	@Id
	@GeneratedValue
	@Column(name = COLUMN_GUIDES_ID_GUIDE)
	private Long idGuide;
	@Column(name = COLUMN_GUIDES_ID_USER)
	private Long idUser;
	@Column(name = COLUMN_GUIDES_ID_CHAMPION)
	private Long idChampion;
	@Column(name = COLUMN_GUIDES_SUMM_SPELL_D)
	private Long id_summ_spell_d;
	@Column(name = COLUMN_GUIDES_SUMM_SPELL_F)
	private Long id_summ_spell_f;
	@Column(name = COLUMN_GUIDES_TITLE)
	private String guideTitle;
	@Column(name = COLUMN_GUIDES_CONTENT)
	private String guideContent;
	@Column(name = COLUMN_GUIDES_DATE)
	private Date guideDate;
	
	@Column(name = COLUMN_GUIDES_VISITS)
	private int visitas;

	@Transient
	private List<ItemDTO> guideItems;

	@Transient
	private SummonerSpellDTO summonerSpellD;

	@Transient
	private SummonerSpellDTO summonerSpellF;

	@Transient
	private ChampionDTO champion;
	
	@Transient
	private UserDTO user;
	
	@Transient
	private String formattedDay;

	
	public String getFormattedDay(){
		return new SimpleDateFormat("dd/MM/yyyy").format(this.guideDate);
	}

	@Override
	public String toString() {
		return "Guía de " + this.champion.getChampionName() + " by you. - " + this.visitas + " visitas";
	}


	@Override
	public int compare(GuideDTO o1, GuideDTO o2) {
		return o2.visitas - o1.visitas;
	}

}
