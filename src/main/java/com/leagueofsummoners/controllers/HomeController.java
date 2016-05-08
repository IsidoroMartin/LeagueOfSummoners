package com.leagueofsummoners.controllers;


import com.leagueofsummoners.model.dto.ChampionDTO;
import com.leagueofsummoners.model.interfaces.services.IServicesChampions;
import com.robrua.orianna.type.core.staticdata.Champion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Locale;

import static com.leagueofsummoners.ApplicationPaths.*;

/**
 * Created by isi on 07/05/2016.
 */
@Controller
public class HomeController {
    @Autowired
    private IServicesChampions servicioChampions;

    @RequestMapping(value = {ROOT_PATH, INDEX_HTML_PATH, INDEX_PATH, HOME_PATH}, method = RequestMethod.GET)
    public String index(ModelMap model, HttpSession session, Locale locale) {
        List<ChampionDTO> championRotation = servicioChampions.getChampionRotation();
        model.addAttribute("championRotation", championRotation);
        return "index";
    }
}
