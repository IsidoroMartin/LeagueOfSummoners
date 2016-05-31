package com.leagueofsummoners.model.dto;

import com.leagueofsummoners.model.dao.tables.TableNames;
import com.mysema.query.sql.Column;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

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
 * Este bean representa la entidad  champion_spells de la base de datos
 */

@Data
@Entity(name = TableNames.TABLE_CHAMPION_SPELLS_ENTITY)
@Table(name = TableNames.TABLE_CHAMPION_SPELLS)
public class ChampionSpellDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Column(TableNames.COLUMN_CHAMPION_SPELLS_ID_SPELL)
	private Long idSpell;
	
	@Column(TableNames.COLUMN_CHAMPION_SPELLS_ID_CHAMPION)
	private Long idChampion;

	@Column(TableNames.COLUMN_CHAMPION_SPELLS_SPELL_NAME_ES)
	private String spellNameEs;

	@Column(TableNames.COLUMN_CHAMPION_SPELLS_SPELL_COST)
	private String spellCost;

	@Column(TableNames.COLUMN_CHAMPION_SPELLS_SPELL_CD)
	private String spellCd;
	
	@Column(TableNames.COLUMN_CHAMPION_SPELLS_SPELL_ICON)
	private String spellIcon;

	@Column(TableNames.COLUMN_CHAMPION_SPELLS_SPELL_DESCRIPTION_ES)
	private String spellDescriptionEs;

}
