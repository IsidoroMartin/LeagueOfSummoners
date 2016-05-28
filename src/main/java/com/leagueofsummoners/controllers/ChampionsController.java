package com.leagueofsummoners.controllers;

import com.google.gson.Gson;
import com.leagueofsummoners.model.dto.ChampionDTO;
import com.leagueofsummoners.model.interfaces.services.IServicesChampions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import static com.leagueofsummoners.ApplicationPaths.*;

import java.util.List;

import javax.servlet.http.HttpSession;

@Controller
public class ChampionsController {
	@Autowired
	private IServicesChampions servicioChampions;

	private static List<ChampionDTO> listaChampions;
	private static String championsJson;

	@RequestMapping(value = { CHAMPIONS_PATH, CHAMPIONS_HTML_PATH }, method = RequestMethod.GET)
	public String champions(ModelMap model, HttpSession session) {
		if (listaChampions == null || championsJson == null) {
			listaChampions = this.servicioChampions.getChampionList(true);
			championsJson = new Gson().toJson(listaChampions); 
		}
		model.put("champions", listaChampions);
		model.put("champions_json",championsJson);
		model.put("pageName","Campeones");
		return "champions";
	}
}