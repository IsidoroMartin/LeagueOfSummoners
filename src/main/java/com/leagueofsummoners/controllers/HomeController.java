package com.leagueofsummoners.controllers;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.leagueofsummoners.model.dto.ChampionDTO;
import com.leagueofsummoners.model.utils.i18n.DetermineLanguageExport;
import com.leagueofsummoners.services.interfaces.IServicesChampions;

@Controller
public class HomeController {

	@Autowired
	private IServicesChampions servicioChampions;

	@RequestMapping(value = "/")
	public String index(ModelMap valores, HttpSession session) {
		valores.put("nombre", "Juanjo");
		ChampionDTO champ = this.servicioChampions.findByChampionName("Riven");
		Object lore = DetermineLanguageExport.getProperLanguage(champ,LocaleContextHolder.getLocale().getLanguage(), "getChampionLore");
		Object title = DetermineLanguageExport.getProperLanguage(champ,LocaleContextHolder.getLocale().getLanguage(), "getChampionTitle");
		valores.put("lore", lore);
		valores.put("title", title);
		valores.put("championName", champ.getChampionName());
		
		return "index";
	}
}
