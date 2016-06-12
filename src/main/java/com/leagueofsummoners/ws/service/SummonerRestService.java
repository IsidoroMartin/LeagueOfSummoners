package com.leagueofsummoners.ws.service;

import static com.leagueofsummoners.ApplicationPaths.*;

import com.leagueofsummoners.SessionAtts;
import com.leagueofsummoners.model.dto.MatchDTO;
import com.leagueofsummoners.model.dto.UserDTO;
import com.leagueofsummoners.model.interfaces.services.IServicesSummoner;
import com.leagueofsummoners.security.annotations.LoginRequired;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.ws.rs.core.MediaType;
import java.util.List;

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

@RestController
@RequestMapping(value = REST_API_SUMMONER)
@Slf4j
public class SummonerRestService {

	@Autowired
	private IServicesSummoner servicesSummoner;

	@LoginRequired
	@RequestMapping(value = REST_API_SUMMONER_MATCHLIST, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public List<MatchDTO> getUserMatches(HttpSession session,
			@RequestParam(name = "update", required = false) Boolean isUpdating) {
		UserDTO user = null;
		List<MatchDTO> matchs = null;
		System.out.println(isUpdating);
		try {
			user = (UserDTO) session.getAttribute(SessionAtts.SESSION_GET_USER_LOGGED);
			matchs = this.servicesSummoner.getLatestMatchesFromDB(user);
			if (isUpdating != null && isUpdating || matchs.isEmpty()) {
				matchs = this.servicesSummoner.getLatestMatchesSync(user, 10);
			}
		} catch (Exception e) {
			log.error("Se ha producido un error en: " + this.getClass().getName());
			matchs = this.servicesSummoner.getLatestMatchesFromDB(user);
		}
		return matchs;
	}
}
