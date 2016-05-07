package com.leagueofsummoners.controllers;

import com.leagueofsummoners.LeagueofsummonersApplication;
import com.leagueofsummoners.model.dto.ChampionDTO;
import com.leagueofsummoners.model.interfaces.services.IServicesChampions;
import com.robrua.orianna.type.core.staticdata.Champion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Locale;

/**
 * Created by isi on 07/05/2016.
 */
@Controller
public class HomeController {
    @Autowired
    private IServicesChampions servicioChampions;

    @RequestMapping(value = {"/", "/index.html","/index","/home"}, method = RequestMethod.GET)
    public String index(ModelMap model, HttpSession session, Locale locale) {

        List<Champion> championRotation = servicioChampions.getChampionRotation();
            model.addAttribute("championRotation",championRotation);
        return "index";
    }
}
