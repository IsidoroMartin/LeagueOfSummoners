package com.leagueofsummoners.controllers;

import com.google.gson.Gson;
import com.leagueofsummoners.LeagueofsummonersApplication;
import com.leagueofsummoners.model.dto.ChampionDTO;
import com.leagueofsummoners.model.interfaces.services.IServicesChampions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static com.leagueofsummoners.ApplicationPaths.*;

/**
 * Created by isi on 16/05/2016.
 */
@Controller
public class GuidesController {

    @Autowired
    private IServicesChampions servicioChampions;

    @RequestMapping(value = {GUIDES_HTML_PATH, GUIDES_PATH}, method = RequestMethod.GET)
    public
    @ResponseBody
    ModelAndView guides(ModelMap model, @RequestParam(value = "search_input", required = false) String search_input) {
        if (search_input != null) {

        }
        String[] championsList = servicioChampions.getStringChampionList();
        Gson gson = new Gson();
        String champions = gson.toJson(championsList, String[].class);
        model.addAttribute("champion_list",champions);
        //Como se setea la view¿?
        return new ModelAndView("guides");
    }

}
