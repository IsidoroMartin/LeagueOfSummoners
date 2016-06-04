package com.leagueofsummoners.controllers;

import com.google.gson.Gson;
import com.leagueofsummoners.model.dto.ChampionDTO;
import com.leagueofsummoners.model.dto.GuideDTO;
import com.leagueofsummoners.model.interfaces.services.IServicesChampions;
import com.leagueofsummoners.model.interfaces.services.IServicesGuides;
import com.leagueofsummoners.model.interfaces.services.IServicesUsers;
import com.leagueofsummoners.model.utils.CacheUtils;
import com.leagueofsummoners.model.utils.LeagueAccessAPI;
import com.leagueofsummoners.model.utils.RiotUtils;
import com.robrua.orianna.type.core.league.League;
import com.robrua.orianna.type.core.summoner.Summoner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

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
    
  //Servicio de usuarios - Inyección de dependencias
    @Autowired
    private IServicesUsers servicioUsers;

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
        model.addAttribute("activeGuides", "active");
        return new ModelAndView("guides");
    }

    @RequestMapping(value = {VIEW_GUIDE_HTML_PATH, VIEW_GUIDE_PATH}, method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView viewguide(ModelMap model, @RequestParam(value = "idGuide") Long idGuide, HttpSession session) {
        GuideDTO guia = serviceGuides.findByIdGuide(idGuide);
        ChampionDTO champ = guia.getChampion();
        champ.setSplashArtUri(LeagueAccessAPI.RIOT_API_SPLASH_ART +
                RiotUtils.determineSpecialChampionNames(RiotUtils.normalizeChampion(champ.getChampionName())) + "_1.jpg");
        HashMap<String, Object> valores = new HashMap<>();
        Long level = null;
        String name = null;
        Summoner summ = null;
        try {
            try {
                summ = this.servicioUsers.getSummonerData(guia.getUser().getSummonerName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                name = summ.getName();
                valores.put("summ_name", (name != null) ? name: "No name");
            } catch (Exception e) {
                e.printStackTrace();
                valores.put("summ_name", "No name");
            }
            try {
                level = summ.getLevel();
                valores.put("summ_level", (level != null) ? level : "0");
            } catch (Exception e) {
                e.printStackTrace();
                valores.put("summ_level", "0");
            }
            try {
                List<League> listEntries = summ.getLeagueEntries();
                valores.put("summ_tier", (listEntries != null && !listEntries.isEmpty())
                        ? summ.getLeagueEntries().get(0).getTier() : "UNRANKED");
            } catch (Exception e) {
                e.printStackTrace();
                valores.put("summ_tier", "UNRANKED");
            }
            valores.put("summoner_avatar", LeagueAccessAPI.RIOT_API_SUMMONER_PROFILE_ICON_PATH + summ.getProfileIconID() + ".png");
        } catch (Exception e) {
            e.printStackTrace();
            valores.put("summoner_avatar", LeagueAccessAPI.RIOT_API_TEEMO_ICON);
        }

        model.addAttribute("summoner_avatar", LeagueAccessAPI.RIOT_API_SUMMONER_PROFILE_ICON_PATH + summ.getProfileIconID() + ".png");
        CacheUtils.setValuesToModelMap(valores, model, session);
        model.addAttribute("guia", guia);
        return new ModelAndView("view_guide");
    }
}
    
