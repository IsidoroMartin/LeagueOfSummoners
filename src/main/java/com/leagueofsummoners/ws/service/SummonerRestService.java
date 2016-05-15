package com.leagueofsummoners.ws.service;

import static com.leagueofsummoners.ApplicationPaths.*;

import com.leagueofsummoners.SessionAtts;
import com.leagueofsummoners.model.dto.MatchDTO;
import com.leagueofsummoners.model.dto.UserDTO;
import com.leagueofsummoners.model.interfaces.persistence.SummonerRepository;
import com.leagueofsummoners.model.interfaces.services.IServicesSummoner;
import com.leagueofsummoners.security.annotations.LoginRequired;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.ws.rs.core.MediaType;
import java.util.List;

@RestController
@RequestMapping(value = REST_API_SUMMONER)
public class SummonerRestService {

    @Autowired
    private IServicesSummoner servicesSummoner;


    @LoginRequired
    @RequestMapping(value = REST_API_SUMMONER_MATCHLIST, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
    public List<MatchDTO> getUserMatches(HttpSession session, @RequestParam(name = "update", required = false) Boolean isUpdating) {
        List<MatchDTO> matchs = null;
     /*   if (isUpdating != null && isUpdating ||  session.getAttribute(SessionAtts.SESSION_LATEST_MATCHES) == null) {
            matchs = this.servicesSummoner.getLatestMatches((UserDTO) session.getAttribute(SessionAtts.SESSION_GET_USER_LOGGED), 5);
            session.setAttribute(SessionAtts.SESSION_LATEST_MATCHES, matchs);
        } else {
            matchs = (List<MatchDTO>) session.getAttribute(SessionAtts.SESSION_LATEST_MATCHES);
        }*/

        UserDTO user = (UserDTO) session.getAttribute(SessionAtts.SESSION_GET_USER_LOGGED);

        matchs = this.servicesSummoner.getLatestMatchesFromDB(user);

        return matchs;
    }
}
