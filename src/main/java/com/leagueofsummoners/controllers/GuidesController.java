package com.leagueofsummoners.controllers;

import com.google.gson.Gson;
import com.leagueofsummoners.model.dto.GuideDTO;
import com.leagueofsummoners.model.interfaces.services.IServicesChampions;
import com.leagueofsummoners.model.interfaces.services.IServicesGuides;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;
import java.util.List;

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
 * Controlador de guías. Este controlador gestiona
 * todo lo relaccionado con las guías.
 */
@Controller
public class GuidesController {

    //Servicio campeones - Inyección de depdencias
    @Autowired
    private IServicesChampions servicioChampions;

    //Servicio de guías - Inyección de dependencias
    @Autowired
    private IServicesGuides serviceGuides;

    /**
     * Método gestiona las peticionesa /guides, settea en el contexto una lista de campeoens, una lista de guias
     * y el nombre de la página.
     *
     * @param model
     * @return Página de visualización de guías
     */
    @RequestMapping(value = {GUIDES_HTML_PATH, GUIDES_PATH}, method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView guides(ModelMap model, @RequestParam(value = "search_input", required = false) String search_input) {
        List<GuideDTO> guias = this.serviceGuides.findAll();
        Collections.sort(guias, new GuideDTO());
        String[] championsList = servicioChampions.getStringChampionList();
        Gson gson = new Gson();
        String champions = gson.toJson(championsList, String[].class);
        model.addAttribute("champion_list", champions);
        model.addAttribute("guias", guias);
        model.addAttribute("pageName", "Guías");
        return new ModelAndView("guides");
    }

    @RequestMapping(value = {VIEW_GUIDE_HTML_PATH, VIEW_GUIDE_PATH}, method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView viewguide(ModelMap model, @RequestParam(value = "idGuide") Long idGuide) {
        model.addAttribute("guia", serviceGuides.findByIdGuide(idGuide));
        return new ModelAndView("view_guide");
    }
}
