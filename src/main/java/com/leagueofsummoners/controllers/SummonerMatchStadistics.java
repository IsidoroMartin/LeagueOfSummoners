package com.leagueofsummoners.controllers;

import static com.leagueofsummoners.ApplicationPaths.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by isi on 16/05/2016.
 */
@Controller
public class SummonerMatchStadistics {

    @RequestMapping(value = {SUMMONER_MATCH_STADISTICS_HTML_PATH,SUMMONER_MATCH_STADISTICS_HTML_PATH +"/{search_input}", SUMMONER_MATCH_STADISTICS_PATH, SUMMONER_MATCH_STADISTICS_PATH+"/{search_input}"}, method = RequestMethod.GET)
    public String summonermatchstadistics(ModelAndView model, @PathVariable(value="search_input") String search_input){

        return "summonermatchstadistics";
    }
}
