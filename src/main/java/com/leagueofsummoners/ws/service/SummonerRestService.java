package com.leagueofsummoners.ws.service;

import static com.leagueofsummoners.ApplicationPaths.*;

import com.leagueofsummoners.SessionAtts;
import com.leagueofsummoners.model.dto.MatchDTO;
import com.leagueofsummoners.model.dto.UserDTO;
import com.leagueofsummoners.model.interfaces.services.IServicesSummoner;
import com.leagueofsummoners.security.annotations.LoginRequired;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

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
				matchs = this.servicesSummoner.getLatestMatchesSync(user, 5);
			}
		} catch (Exception e) {
			log.error("Se ha producido un error en: " + this.getClass().getName());
			matchs = this.servicesSummoner.getLatestMatchesFromDB(user);
		}
		return matchs;
	}
}
