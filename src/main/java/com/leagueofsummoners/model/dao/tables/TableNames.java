package com.leagueofsummoners.model.dao.tables;

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
 * Esta clase contiene los nombres de las tablas para que hibernate pueda
 * mappear los valores (Estos valores no están externalizados porque tiene que
 * ser un valor final, si viene de un fichero no lo es).
 */
public class TableNames {

	/* Tabla campeones */
	public final static String TABLE_CHAMPIONS_ENTITY = "champion";
	public final static String TABLE_CHAMPIONS = "CHAMPIONS";
	public final static String COLUMN_CHAMPIONS_ID_CHAMPION = "ID_CHAMPION";
	public final static String COLUMN_CHAMPIONS_CHAMPION_NAME = "CHAMPION_NAME";
	public final static String COLUMN_CHAMPIONS_CHAMPION_TITLE_ES = "CHAMPION_TITLE_ES";
	public final static String COLUMN_CHAMPIONS_ID_PASSIVE = "ID_PASSIVE";
	public final static String COLUMN_CHAMPIONS_CHAMPION_LORE_ES = "CHAMPION_LORE_ES";
	public final static String COLUMN_CHAMPIONS_CHAMPION_TYPE = "CHAMPION_TYPE";
	public final static String COLUMN_CHAMPIONS_CHAMPION_INFO = "CHAMPION_INFO";
	public static final String COLUMN_CHAMPIONS_CHAMPION_ICON_NAME = "CHAMPION_ICON_NAME";

	/* Tabla Guides */
	public static final String TABLE_GUIDES_ENTITY = "guides";
	public static final String TABLE_GUIDES = "GUIDES";
	public static final String COLUMN_GUIDES_ID_GUIDE = "ID_GUIDE";
	public static final String COLUMN_GUIDES_ID_USER = "ID_USER";
	public static final String COLUMN_GUIDES_ID_CHAMPION = "ID_CHAMPION";
	public static final String COLUMN_GUIDES_TITLE = "GUIDE_TITLE";
	public static final String COLUMN_GUIDES_CONTENT = "GUIDE_CONTENT";
	public static final String COLUMN_GUIDES_LANG = "GUIDE_LANG";
	public static final String COLUMN_GUIDES_DATE = "GUIDE_DATE";
	public static final String COLUMN_GUIDES_VISITS = "GUIDE_VISITAS";

	/* Tabla pasivas */
	public static final String TABLE_PASSIVES_ENTITY = "championPassives";
	public static final String TABLE_PASSIVES = "CHAMPIONS_PASSIVES";
	public static final String COLUMN_PASSIVES_ID_PASSIVE = "ID_PASSIVE";
	public static final String COLUMN_PASSIVES_PASSIVE_DESCRIPTION_ES = "PASSIVE_DESCRIPTION_ES";
	public static final String COLUMN_PASSIVES_PASSIVE_ICON = "PASSIVE_ICON";

	/* Tabla de spells */
	public static final String TABLE_CHAMPION_SPELLS_ENTITY = "championSpells";
	public static final String TABLE_CHAMPION_SPELLS = "CHAMPIONS_SPELLS";
	public static final String COLUMN_CHAMPION_SPELLS_ID_SPELL = "ID_SPELL";
	public static final String COLUMN_CHAMPION_SPELLS_ID_CHAMPION = "ID_CHAMPION";
	public static final String COLUMN_CHAMPION_SPELLS_SPELL_NAME_ES = "SPELL_NAME_ES";
	public static final String COLUMN_CHAMPION_SPELLS_SPELL_COST = "SPELL_COST";
	public static final String COLUMN_CHAMPION_SPELLS_SPELL_CD = "SPELL_CD";
	public static final String COLUMN_CHAMPION_SPELLS_SPELL_ICON = "SPELL_ICON";
	public static final String COLUMN_CHAMPION_SPELLS_SPELL_DESCRIPTION_ES = "SPELL_DESCRIPTION_ES";

	/* Tabla de items */
	public static final String TABLE_ITEMS_ENTITY = "item";
	public static final String TABLE_ITEMS = "ITEMS";
	public static final String COLUMN_ITEMS_ITEM_ID = "ID_ITEM";
	public static final String COLUMN_ITEMS_ITEM_NAME_ES = "ITEM_NAME_ES";
	public static final String COLUMN_ITEMS_ITEM_DESC_ES = "ITEM_DESC_ES";
	public static final String COLUMN_ITEMS_ITEM_ICON = "ITEM_ICON";

	/* Tabla de Matchs */

	public static final String TABLE_MATCHS_ENTITY = "MATCH";
	public static final String TABLE_MATCHS = "MATCHS";
	public static final String COLUMN_MATCHS_ID_MATCH = "ID_MATCH";
	public static final String COLUMN_MATCHS_ID_USER = "ID_USER";
	public static final String COLUMN_MATCHS_MATCH_DURATION = "MATCH_DURATION";
	public static final String COLUMN_MATCHS_MATCH_WIN = "MATCH_WIN";
	public static final String COLUMN_MATCHS_ID_CHAMPION = "ID_CHAMPION";
	public static final String COLUMN_MATCHS_KILLS = "KILLS";
	public static final String COLUMN_MATCHS_DEATHS = "DEATHS";
	public static final String COLUMN_MATCHS_ASSISTS = "ASSISTS";
	public static final String COLUMN_MATCHS_GOLD_EARNED = "GOLD_EARNED";
	public static final String COLUMN_MATCHS_ITEM0 = "ITEM0";
	public static final String COLUMN_MATCHS_ITEM1 = "ITEM1";
	public static final String COLUMN_MATCHS_ITEM2 = "ITEM2";
	public static final String COLUMN_MATCHS_ITEM3 = "ITEM3";
	public static final String COLUMN_MATCHS_ITEM4 = "ITEM4";
	public static final String COLUMN_MATCHS_ITEM5 = "ITEM5";
	public static final String COLUMN_MATCHS_ITEM6 = "ITEM6";
	
	/* Tabla Users */
	public static final String TABLE_USERS_ENTITY = "user";
	public static final String TABLE_USERS = "users";
	public static final String COLUMN_USERS_ID_USER = "id_user";
	public static final String COLUMN_USERS_SUMMONER_NAME = "summoner_name";
	public static final String COLUMN_USERS_USERNAME = "username";
	public static final String COLUMN_USERS_MATCHES_PLAYED = "MATCHES_PLAYED";
	public static final String COLUMN_USERS_PASSWORD = "password";
	public static final String COLUMN_USERS_EMAIL= "email";
	public static final String COLUMN_USERS_AVATAR= "avatar";
	public static final String COLUMN_USERS_FIRMA= "firma";
	public static final String COLUMN_USERS_PERMISSION_LEVEL= "permission_level";

}
