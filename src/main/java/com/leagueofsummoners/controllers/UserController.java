package com.leagueofsummoners.controllers;

import com.leagueofsummoners.LeagueofsummonersApplication;

import static com.leagueofsummoners.SessionAtts.*;

import com.leagueofsummoners.model.dto.MatchDTO;
import com.leagueofsummoners.model.interfaces.services.IServicesChampions;
import com.leagueofsummoners.model.interfaces.services.IServicesUsers;
import com.leagueofsummoners.model.dto.UserDTO;
import com.leagueofsummoners.model.utils.CacheUtils;
import com.leagueofsummoners.security.annotations.LoginRequired;
import com.robrua.orianna.type.core.summoner.Summoner;
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
public class UserController {

    @Autowired
    private IServicesChampions servicioChampions;
    @Autowired
    private IServicesUsers servicioUsers;


    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(UserDTO userdto, ModelMap valores) {
        valores.put("listaChamps", this.servicioChampions.getChampionList());
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerUser(@Valid UserDTO userdto, BindingResult bindingResult, Model model,
                               @RequestParam("img-avatar") MultipartFile[] file, @RequestParam("galeria") String galeriaIcon) {
        String page = "redirect:index.html?action=form-error";
        String avatar = galeriaIcon;
        if (!bindingResult.hasErrors()) {
            if (this.servicioUsers.registrarUser(userdto, file, galeriaIcon))
                page = "login";
        } else {
            LeagueofsummonersApplication.LOGGER.warn("Error registrando usuario " + userdto.getUsername() + " due: " + bindingResult.getAllErrors());
        }
        return page;
    }


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(ModelMap valores, HttpSession session, Locale locale) {
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginProcess(ModelMap valores, HttpSession session, @RequestParam(name = "username") String username,
                               @RequestParam(name = "password") String password) {
        return (this.servicioUsers.checkValidLoginCreateSession(username, password, session)) ? profile(valores, session) : "login";
    }

    @LoginRequired
    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String profile(ModelMap values, HttpSession session) {

        if (session.getAttribute(SESSION_MODEL_MAP) == null) {
            HashMap<String, Object> valores = new HashMap<>();
            UserDTO user = (UserDTO) session.getAttribute(SESSION_GET_USER_LOGGED);
            List<MatchDTO> matches = this.servicioChampions.getLatestMatches(user, 2);
            Summoner summ = this.servicioUsers.getSummonerData(user.getSummonerName());
            valores.put("summ_level", summ.getLevel());
            valores.put("summ_tier", (summ.getLeagueEntries().size() > 0) ? summ.getLeagueEntries().get(0).getTier() : "-");
            valores.put("summ_playing", (summ.getCurrentGame() != null) ? summ.getCurrentGame().getMap() : "-");
            valores.put("latestMatches", matches);
            session.setAttribute(SESSION_MODEL_MAP, valores);
        }

        CacheUtils.setValuesToModelMap((HashMap) session.getAttribute(SESSION_MODEL_MAP), values, session);
        return "profile";
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
