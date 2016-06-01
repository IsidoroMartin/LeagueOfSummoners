package com.leagueofsummoners.controllers;

import com.leagueofsummoners.ApplicationPaths;
import com.leagueofsummoners.model.services.ChampionServices;
import com.leagueofsummoners.model.utils.RiotUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.Locale;

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
 * Este controlador gestiona las búsquedas del home. 
 */
@Controller
public class SearchController {
	
	//Servicio de campeones
	@Autowired
	private ChampionServices champSrvices;
	
	/**
	 * Este método comprueba si hay alguna coincidencia en la lista de campeones con el input introducido
	 * por el usuario, si la hay le redirge a la página de campeones a ese campéon, si no a la página de guías
	 * @param valores Mapa de valores
	 * @param locale idioma de la aplicación
	 * @param search_input búsqueda del usuario
	 * @return La página correspondiente a la redirección
	 */
    @RequestMapping(value = ApplicationPaths.SEARCH_PATH, method = RequestMethod.GET)
    public @ResponseBody ModelAndView serach(ModelMap valores, Locale locale, @RequestParam(value = "search_input") String search_input) {
        String view = "";
        search_input = search_input.toLowerCase();
        String[] chmapionNames =  this.champSrvices.getStringChampionList();

        if (RiotUtils.containsCaseInsensitive(search_input, Arrays.asList(chmapionNames))) {
            view = "redirect:/champions#" + RiotUtils.normalizeChampionForLink(search_input);
        } else {
        	search_input = replaceToCorrectSearchInput(search_input);
            view = "redirect:/guides?search_input=" + search_input;
        }
        
        return new ModelAndView(view);
    }
    
    private String replaceToCorrectSearchInput(String search_input){
     	search_input = search_input.replace("guia", "").trim();
    	search_input = search_input.replace("guía", "").trim();
    	search_input = search_input.replace("build", "").trim();
    	return search_input;
    }
}
