package com.leagueofsummoners.controllers;

import com.google.gson.Gson;
import com.leagueofsummoners.model.dto.ChampionDTO;
import com.leagueofsummoners.model.interfaces.services.IServicesChampions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import static com.leagueofsummoners.ApplicationPaths.*;

import java.util.List;

import javax.servlet.http.HttpSession;

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
 * El controlador de la página de campeones
 */
@Controller
public class ChampionsController {
	//Servicio de campeones
	@Autowired
	private IServicesChampions servicioChampions;

	//Lista de campoenes persistente para que no se tenga que hacer una petición a la BD siempre
	private static List<ChampionDTO> listaChampions;
	//Lista de los campeones en JSON (mock)
	private static String championsJson;
	
	/**
	 * Este es el método que gestiona la petición del cliente a la página champions
	 * @param model
	 * @param session
	 * Antes de devolver la página seteamos en el modelo los campeones (objeto java y en json)
	 * y ademas el nombre de la página para las  migas de pan
	 */
	@RequestMapping(value = { CHAMPIONS_PATH, CHAMPIONS_HTML_PATH }, method = RequestMethod.GET)
	public String champions(ModelMap model, HttpSession session) {
		if (listaChampions == null || championsJson == null) {
			listaChampions = this.servicioChampions.getChampionList(true);
			championsJson = new Gson().toJson(listaChampions); 
		}
		model.put("champions", listaChampions);
		model.put("champions_json",championsJson);
		model.put("pageName","Campeones");
		return "champions";
	}
}