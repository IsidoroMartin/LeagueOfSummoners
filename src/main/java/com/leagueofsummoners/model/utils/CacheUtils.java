package com.leagueofsummoners.model.utils;

import org.springframework.ui.ModelMap;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
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
 * Esta clase contiee métodos utiles para cachear respuestas del servidor
 */
public class CacheUtils {

	/**
	 * Settea los valores al model map y este en la session
	 * @param mapToSet
	 * @param map
	 * @param session
	 */
	public static void setValuesToModelMap(HashMap<String, Object> mapToSet, ModelMap map, HttpSession session) {
		if (mapToSet != null && map != null && session != null) {
			for (Map.Entry<String, Object> entry : mapToSet.entrySet()) {
				if (entry.getKey() != null && entry.getValue() != null)
					map.put(entry.getKey(), entry.getValue());
			}
		}
	}

}
