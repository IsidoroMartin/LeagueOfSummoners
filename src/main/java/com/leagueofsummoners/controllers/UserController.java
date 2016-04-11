package com.leagueofsummoners.controllers;

import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.leagueofsummoners.model.dto.ChampionDTO;
import com.leagueofsummoners.model.utils.i18n.DetermineLanguageExport;
import com.leagueofsummoners.services.interfaces.IServicesChampions;
import com.leagueofsummoners.services.interfaces.IServicesUsers;

@Controller
public class UserController {

	@Autowired
	private IServicesChampions servicioChampions;
	@Autowired
	private IServicesUsers servicioUsers;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(ModelMap valores, HttpSession session, Locale locale,
			@RequestParam("champName") String championName) {
		valores.put("nombre", "Juanjo");
		ChampionDTO champ = this.servicioChampions.findByChampionName(championName);
		Object lore = DetermineLanguageExport.getProperLanguage(champ, locale, "getChampionLore");
		Object title = DetermineLanguageExport.getProperLanguage(champ, locale, "getChampionTitle");
		valores.put("lore", lore);
		valores.put("title", title);
		valores.put("championName", champ.getChampionName());
		return "index";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(ModelMap valores, HttpSession session, @RequestParam(name = "username") String username,
			@RequestParam(name = "password") String password) {
		return (this.servicioUsers.checkValidLogin(username, password, session)) ? "logged" : "notlogged";
	}

	@RequestMapping(value = "/notlogged", method = RequestMethod.GET)
	public String notLogged() {
		return "notlogged";
	}
}
