package com.leagueofsummoners.controllers;

import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.leagueofsummoners.interfaces.services.IServicesChampions;
import com.leagueofsummoners.interfaces.services.IServicesUsers;
import com.leagueofsummoners.model.dto.ChampionDTO;
import com.leagueofsummoners.model.dto.UserDTO;
import com.leagueofsummoners.model.utils.i18n.DetermineLanguageExport;
import com.leagueofsummoners.utils.FileUtils;

@Controller
public class UserController {

	@Autowired
	private IServicesChampions servicioChampions;
	@Autowired
	private IServicesUsers servicioUsers;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(ModelMap valores, HttpSession session, Locale locale,
			@RequestParam(name = "champName", defaultValue = "Syndra", required = false) String championName) {
		valores.put("nombre", "Juanjo");
		ChampionDTO champ = this.servicioChampions.findByChampionName(championName);
		Object lore = DetermineLanguageExport.getProperLanguage(champ, locale, "getChampionLore");
		Object title = DetermineLanguageExport.getProperLanguage(champ, locale, "getChampionTitle");
		valores.put("lore", lore);
		valores.put("title", title);
		valores.put("championName", champ.getChampionName());
		return "index";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register(UserDTO userdto,ModelMap valores) {
		valores.put("listaNamesChamps", this.servicioChampions.getChampionsIconsNamesList());
		return "register";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registerToDb(UserDTO userdto, BindingResult bindingResult, Model model,
			@RequestParam("img-avatar") MultipartFile[] file, @RequestParam("galeria") String galeriaIcon) {
		System.out.println(userdto);
		System.out.println(FileUtils.saveImg(file[0], userdto.getUsername()));
		return "register";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(ModelMap valores, HttpSession session, @RequestParam(name = "username") String username,
			@RequestParam(name = "password") String password) {
		return (this.servicioUsers.checkValidLoginSetSessionStatus(username, password, session)) ? "logged" : "notlogged";
	}

	@RequestMapping(value = "/notlogged", method = RequestMethod.GET)
	public String notLogged() {
		return "notlogged";
	}
	
	@RequestMapping(value = "/forbbiden", method = RequestMethod.GET)
	public String forbbiden() {
		return "forbbiden";
	}
}
