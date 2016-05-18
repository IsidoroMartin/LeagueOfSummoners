package com.leagueofsummoners.controllers;

import com.leagueofsummoners.model.interfaces.services.IServicesChampions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import static com.leagueofsummoners.ApplicationPaths.*;

/**
 * Created by isi on 16/05/2016.
 */
@Controller
public class ChampionsController {
    @Autowired
    private IServicesChampions servicioChampions;

    @RequestMapping(value = {CHAMPIONS_PATH, CHAMPIONS_HTML_PATH}, method = RequestMethod.GET)
    public String champions(ModelAndView model) {

        return "champions";
    }
}