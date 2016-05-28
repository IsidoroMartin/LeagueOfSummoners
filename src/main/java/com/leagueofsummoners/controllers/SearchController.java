package com.leagueofsummoners.controllers;

import com.leagueofsummoners.ApplicationPaths;
import com.leagueofsummoners.model.dto.ChampionDTO;
import com.leagueofsummoners.model.services.ChampionServices;
import com.leagueofsummoners.model.utils.RiotUtils;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.Locale;


@Controller
public class SearchController {
	
	@Autowired
	private ChampionServices champSrvices;

    @RequestMapping(value = ApplicationPaths.SEARCH_PATH, method = RequestMethod.GET)
    public
    @ResponseBody
    ModelAndView index(ModelMap valores, Locale locale, @RequestParam(value = "search_input") String search_input) {
        String view = "";
        String[] chmapionNames =  this.champSrvices.getStringChampionList();


        if (RiotUtils.containsCaseInsensitive(search_input, Arrays.asList(chmapionNames))) {
            view = "redirect:/champions#" + RiotUtils.normalizeChampionForLink(search_input);
        } else {
            view = "redirect:/guides?search_input=" + search_input;
        }
        
        return new ModelAndView(view);
    }
}
