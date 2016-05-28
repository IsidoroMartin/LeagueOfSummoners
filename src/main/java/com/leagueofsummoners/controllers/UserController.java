package com.leagueofsummoners.controllers;

import com.leagueofsummoners.LeagueofsummonersApplication;

import static com.leagueofsummoners.SessionAtts.*;

import com.leagueofsummoners.SessionAtts;
import com.leagueofsummoners.model.dto.MatchDTO;
import com.leagueofsummoners.model.interfaces.services.IServicesChampions;
import com.leagueofsummoners.model.interfaces.services.IServicesGuides;
import com.leagueofsummoners.model.interfaces.services.IServicesSummoner;
import com.leagueofsummoners.model.interfaces.services.IServicesUsers;
import com.leagueofsummoners.model.dto.UserDTO;
import com.leagueofsummoners.model.utils.CacheUtils;
import com.leagueofsummoners.model.utils.LeagueAccessAPI;
import com.leagueofsummoners.security.annotations.LoginRequired;
import com.robrua.orianna.type.core.league.League;
import com.robrua.orianna.type.core.summoner.Summoner;
import com.robrua.orianna.type.core.team.Team;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

@Controller
@Slf4j
public class UserController {

	@Autowired
	private IServicesChampions servicioChampions;

	@Autowired
	private IServicesUsers servicioUsers;

	@Autowired
	private IServicesSummoner servicioSummoners;

	@Autowired
	private IServicesGuides servicioGuias;

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register(UserDTO userdto, ModelMap valores, HttpSession session) {
		valores.put("listaChamps", this.servicioChampions.getChampionList(false));
		return (session.getAttribute(SessionAtts.SESSION_IS_LOGGED) == null) ? "register" : "redirect:profile";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registerUser(@Valid UserDTO userdto, BindingResult bindingResult, Model model,
			@RequestParam("img-avatar") MultipartFile[] file, @RequestParam("galeria") String galeriaIcon) {
		String page = "redirect:index.html?action=form-error";
		String avatar = galeriaIcon;
		if (!bindingResult.hasErrors()) {
			if (this.servicioUsers.registrarUser(userdto, file, galeriaIcon)) {
				page = "login";
			}
		} else {
			LeagueofsummonersApplication.LOGGER.warn(
					"Error registrando usuario " + userdto.getUsername() + " due: " + bindingResult.getAllErrors());
		}
		return page;
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage(ModelMap valores, HttpSession session, Locale locale) {
		return (session.getAttribute(SessionAtts.SESSION_IS_LOGGED) == null) ? "login" : "redirect:profile";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String loginProcess(ModelMap valores, HttpSession session, @RequestParam(name = "username") String username,
			@RequestParam(name = "password") String password) {

		return (this.servicioUsers.checkValidLoginCreateSession(username, password, session)) ? "redirect:/profile"
				: "redirect:/login?error_message=El usuario introducido no esta registrado o la clave no es correcta.";
	}

	@LoginRequired
	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public String profile(ModelMap values, HttpSession session) {
		HashMap<String, Object> valores = new HashMap<>();
		UserDTO user = (UserDTO) session.getAttribute(SESSION_GET_USER_LOGGED);
		Long level = null;
		Summoner summ = null;

		try {
			if (session.getAttribute(SESSION_MODEL_MAP) == null) {
				summ = this.servicioUsers.getSummonerData(user.getSummonerName());
				try {
					level = summ.getLevel();
					valores.put("summ_level", (level != null) ? level : "0");
				} catch (Exception e) {
					e.printStackTrace();
					valores.put("summ_level", "0");
				}
				try {
					List<League> listEntries = summ.getLeagueEntries();
					valores.put("summ_tier", (listEntries != null && !listEntries.isEmpty())
							? summ.getLeagueEntries().get(0).getTier() : "UNRANKED");
				} catch (Exception e) {
					e.printStackTrace();
					valores.put("summ_tier", "UNRANKED");
				}

				try {
					List<Team> teams = summ.getTeams();
					valores.put("team", (!teams.isEmpty()) ? teams.get(0).getName() : "Sin equipo");
				} catch (Exception e) {
					e.printStackTrace();
					valores.put("team", "Sin equipo");
				}

				valores.put("summoner_avatar",LeagueAccessAPI.RIOT_API_SUMMONER_PROFILE_ICON_PATH + summ.getProfileIconID() + ".png");
				valores.put("pageName", "Perfil");
				session.setAttribute(SESSION_MODEL_MAP, valores);
			}
		} catch (Exception e) {
			e.printStackTrace();
			valores.put("summoner_avatar", LeagueAccessAPI.RIOT_API_TEEMO_ICON);
			session.setAttribute(SESSION_MODEL_MAP, valores);
			log.error("Error obteniendo la información del usuario! " + e.getMessage());
		}
		CacheUtils.setValuesToModelMap((HashMap<String, Object>) session.getAttribute(SESSION_MODEL_MAP), values,
				session);
		values.put("user_guides", this.servicioGuias.findByIdUser(user.getIdUser()));
		return "profile";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logOut(HttpSession session) {
		session.invalidate();
		return "redirect:index?logout=true";
	}

	@RequestMapping(value = "/notlogged", method = RequestMethod.GET)
	public String notLogged() {
		return "notlogged";
	}

	@RequestMapping(value = "/forbbiden", method = RequestMethod.GET)
	public String forbbiden() {
		return "forbbiden";
	}

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String test() {
		return "test";
	}
}
