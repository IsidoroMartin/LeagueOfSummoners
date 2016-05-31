package com.leagueofsummoners.model.utils;

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
 * Esta clase contiene métodos utiles para normalizar nombres de campeones para
 * links.
 */
public class RiotUtils {

	public static String[] parseRiotListToString(List<?> list) {
		String[] stringList = new String[list.size()];
		for (int i = 0; i < stringList.length; i++) {
			stringList[i] = list.get(i).toString();
		}
		return stringList;
	}

	public static String normalizeChampion(String championName) {
		championName = championName.replace(" ", "");
		championName = championName.replace("'", "");
		championName = championName.replace(".", "");
		return championName;
	}

	/**
	 * Este método normaliza el nombre de los campeones apra que sean usables en
	 * los links, no esta parametrizado con el otro método porque se llama desde
	 * thymleaf y es más sencillo así.
	 * 
	 * @param championName
	 * @return nombre normalizzado
	 */
	public static String normalizeChampionForLink(String championName) {
		championName = championName.replace(" ", "");
		championName = championName.replace("'", "");
		championName = championName.replace(".", "-");
		return championName;
	}

	public static boolean containsCaseInsensitive(String s, List<String> l) {
		for (String string : l) {
			if (string.equalsIgnoreCase(s)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Casos especiales de campeones
	 * 
	 * @param championName
	 * @return el nombre normalizado del campeón
	 */
	public static String determineSpecialChampionNames(String championName) {
		switch (championName) {
		case "Wukong":
			championName = "MonkeyKing";
			break;
		case "Fiddlesticks":
			championName = "FiddleSticks";
			break;
		case "KhaZix":
			championName = "Khazix";
			break;
		case "VelKoz":
			championName = "Velkoz";
			break;
		case "ChoGath":
			championName = "Chogath";
			break;
		case "LeBlanc":
			championName = "Leblanc";
			break;
		}
		return championName;
	}

}
