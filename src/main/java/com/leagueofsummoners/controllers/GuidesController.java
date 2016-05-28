package com.leagueofsummoners.controllers;

import com.google.gson.Gson;
import com.leagueofsummoners.LeagueofsummonersApplication;
import com.leagueofsummoners.SessionAtts;
import com.leagueofsummoners.model.dto.ChampionDTO;
import com.leagueofsummoners.model.dto.GuideDTO;
import com.leagueofsummoners.model.dto.UserDTO;
import com.leagueofsummoners.model.interfaces.services.IServicesChampions;
import com.leagueofsummoners.model.interfaces.services.IServicesGuides;
import com.leagueofsummoners.security.annotations.LoginRequired;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpSession;

import static com.leagueofsummoners.ApplicationPaths.*;

@Controller
public class GuidesController {

	@Autowired
	private IServicesChampions servicioChampions;
	
	@Autowired
	private IServicesGuides serviceGuides;

	@RequestMapping(value = { GUIDES_HTML_PATH, GUIDES_PATH }, method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView guides(ModelMap model, @RequestParam(value = "search_input", required = false) String search_input) {
		if (search_input != null) {

		}
		List<GuideDTO> guias = this.serviceGuides.findAll();
		Collections.sort(guias, new GuideDTO());
		
		String[] championsList = servicioChampions.getStringChampionList();
		Gson gson = new Gson();
		String champions = gson.toJson(championsList, String[].class);
		model.addAttribute("champion_list", champions);
		model.addAttribute("guias", guias);
		model.addAttribute("pageName", "Guías");
		// Como se setea la view¿?
		return new ModelAndView("guides");
	}


}
