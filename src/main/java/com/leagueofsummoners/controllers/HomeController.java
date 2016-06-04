package com.leagueofsummoners.controllers;


import com.google.gson.Gson;
import com.leagueofsummoners.model.dto.ChampionDTO;
import com.leagueofsummoners.model.interfaces.services.IServicesChampions;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;

import static com.leagueofsummoners.ApplicationPaths.*;

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
 * Esta clase gestiona las peticiones del home
 */
@Controller
public class HomeController {
	//Servicio de campeones
    @Autowired
    private IServicesChampions servicioChampions;
    //Comprueba si los campeones han sido actualizados.
    boolean hasBeenUpdated = false;
    //Variable que contendrá la rotación de campeones
    List<ChampionDTO> championRotation = null;
    
    /**
     * Método encargado de gestionar las peticiones a /home
     * @param model
     * @param session
     * @param locale
     * Settea en el contexto la rotación de campeones actual
     */
    @RequestMapping(value = {ROOT_PATH, INDEX_HTML_PATH, INDEX_PATH, HOME_PATH}, method = RequestMethod.GET)
    public String index(ModelMap model, HttpSession session, Locale locale) {
        LocalDateTime timePoint = LocalDateTime.now();
        DayOfWeek dia = timePoint.getDayOfWeek();
        // Compruebo que la rotación esté inicializada y si no lo esta la inicializo
        // Por otro lado los martes actualizo la lista de campeones
        if (championRotation == null || (dia == DayOfWeek.TUESDAY && !hasBeenUpdated)) {
            if (dia == DayOfWeek.TUESDAY) hasBeenUpdated = true;
            championRotation = servicioChampions.getChampionRotation();
        } else if (dia == DayOfWeek.WEDNESDAY && hasBeenUpdated) hasBeenUpdated = false;
        
        String[] championsList = servicioChampions.getStringChampionList();
		Gson gson = new Gson();
		String champions = gson.toJson(championsList, String[].class);
		model.addAttribute("champion_list", champions);
        model.addAttribute("championRotation", championRotation);
        model.addAttribute("activeHome", "active");
        return "index";
    }
}
