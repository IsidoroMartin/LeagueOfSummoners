package com.leagueofsummoners.controllers;

import com.leagueofsummoners.ApplicationPaths;
import com.leagueofsummoners.LeagueofsummonersApplication;

import static com.leagueofsummoners.SessionAtts.*;

import com.leagueofsummoners.SessionAtts;
import com.leagueofsummoners.model.interfaces.services.IServicesChampions;
import com.leagueofsummoners.model.interfaces.services.IServicesGuides;
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

/*
Autores= Juan José Ramírez & Isidoro Martín
Fecha= Junio de 2016
Licencia=  gp130
Version= 1.0
Descripcion= Proyecto final desarrollo de aplicaciones web. League of Summoners es una aplicación
enfocada a los jugadores del popular juego League of Legends, usando esta aplicación podrán acceder
a guías, detalles sobre campeones e incluso sus últimas partidas.

Copyright (C) 2016 Juan José Ramírez & Isidoro Martín
This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.
This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.
You should have received a copy of the GNU General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

/**
 * Este es el controlador encargado de gestionar todo 
 * lo relaccionado con los usuarios. (Login, registro)
 */
@Controller
@Slf4j
public class UserController {
	
	//Servicio de campeones
	@Autowired
	private IServicesChampions servicioChampions;
	
	//Servicio de usuarios
	@Autowired
	private IServicesUsers servicioUsers;

	//Servicio de guías
	@Autowired
	private IServicesGuides servicioGuias;

	/**
	 * Método encargado de mostrar la página de registro (Si no estas loggeado).
	 * @param valores
	 * @param session
	 * @return la página de registro o de profile.
	 */
	@RequestMapping(value = ApplicationPaths.REGISTER_PATH, method = RequestMethod.GET)
	public String register(ModelMap valores, HttpSession session) {
		valores.put("listaChamps", this.servicioChampions.getChampionList(false));
		return (session.getAttribute(SessionAtts.SESSION_IS_LOGGED) == null) ? "register" : "redirect:profile";
	}
	
	/**
	 * Registra un usuario en laaplicación
	 * @param userdto El objeto user con los parametros rellenados en el registro
	 * @param bindingResult El resultado de la valicación
	 * @param model 
	 * @param file Si hay algún archivo en la petición (Si el usuario ha subido su propio avatar)
	 * @param galeriaIcon El icono de la galeria si el usuario ha escogido un icono
	 * @return La página de login si el registro es correcto
	 */
	@RequestMapping(value = ApplicationPaths.REGISTER_PATH, method = RequestMethod.POST)
	public String registerUser(@Valid UserDTO userdto, BindingResult bindingResult, Model model,
			@RequestParam("img-avatar") MultipartFile[] file, @RequestParam("galeria") String galeriaIcon) {
		String page = "redirect:/login?error_message=Ha habido un error al registrar al usuario.";
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

	/**
	 * Sirve la página de login siempre que el usuario no este loggeado.
	 * @param valores
	 * @param session
	 * @param locale
	 * @return la página de login o perfil (Si el usuario esta loggeado)
	 */
	@RequestMapping(value = ApplicationPaths.LOGIN_PATH, method = RequestMethod.GET)
	public String loginPage(ModelMap valores, HttpSession session, Locale locale) {
		return (session.getAttribute(SessionAtts.SESSION_IS_LOGGED) == null) ? "login" : "redirect:profile";
	}

	/**
	 * Loggea a un usuario en la aplicación 
	 * @param valores
	 * @param session
	 * @param username
	 * @param password
	 * @return La página de perfil si el usuario ha sido loggeado corerctamente
	 */
	@RequestMapping(value = ApplicationPaths.LOGIN_PATH, method = RequestMethod.POST)
	public String loginProcess(ModelMap valores, HttpSession session, @RequestParam(name = "username") String username,
			@RequestParam(name = "password") String password) {

		return (this.servicioUsers.checkValidLoginCreateSession(username, password, session)) ? "redirect:/profile"
				: "redirect:/login?error_message=El usuario introducido no esta registrado o la clave no es correcta.";
	}
	
	/**
	 * Muestra el perfil del usuario y obtiene los datos del api de riot a travesdel servicio
	 * @param values
	 * @param session
	 * @return perfil con los valores setteados.
	 */
	@SuppressWarnings("unchecked")
	@LoginRequired
	@RequestMapping(value = ApplicationPaths.PROFILE_PATH, method = RequestMethod.GET)
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
		values.put("activeProfile", "active");
		return "profile";
	}
	
	/**
	 * Destruye la sessión del usuario
	 * @param session
	 * @return la página de login
	 */
	@RequestMapping(value = ApplicationPaths.PROFILE_LOGOUT, method = RequestMethod.GET)
	public String logOut(HttpSession session) {
		session.invalidate();
		return "redirect:index?logout=true";
	}

}
