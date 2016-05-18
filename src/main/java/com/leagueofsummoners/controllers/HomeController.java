package com.leagueofsummoners.controllers;


import com.leagueofsummoners.model.dto.ChampionDTO;
import com.leagueofsummoners.model.interfaces.services.IServicesChampions;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
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

/**
 * Created by isi on 07/05/2016.
 */
@Controller
public class HomeController {
    @Autowired
    private IServicesChampions servicioChampions;
    boolean hasBeenUpdated = false;
    List<ChampionDTO> championRotation = null;
    @Autowired
    private ApplicationContext appContext;

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

        model.addAttribute("championRotation", championRotation);
        return "index";
    }
}
