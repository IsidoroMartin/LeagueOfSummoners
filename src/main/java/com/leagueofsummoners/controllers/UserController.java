package com.leagueofsummoners.controllers;

import com.leagueofsummoners.LeagueofsummonersApplication;
import com.leagueofsummoners.model.interfaces.services.IServicesChampions;
import com.leagueofsummoners.model.interfaces.services.IServicesUsers;
import com.leagueofsummoners.model.dto.UserDTO;
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
import java.util.Locale;

@Controller
public class UserController {

    @Autowired
    private IServicesChampions servicioChampions;
    @Autowired
    private IServicesUsers servicioUsers;

    @RequestMapping(value = {"/", "/index.html","/index","/home"}, method = RequestMethod.GET)
    public String index(ModelMap valores, HttpSession session, Locale locale) {
        //AQUI DEBERIA DE IR LA PETICION A LOS CHAMPIONS DE ROTATION
        return "index";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(UserDTO userdto, ModelMap valores) {
        valores.put("listaNamesChamps", this.servicioChampions.getChampionsIconsNamesList());
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerUser(@Valid UserDTO userdto, BindingResult bindingResult, Model model,
                               @RequestParam("img-avatar") MultipartFile[] file, @RequestParam("galeria") String galeriaIcon) {
        String page = "redirect:index.html?action=form-error";
        String avatar = "/img/champion_icons/" + galeriaIcon;
        if (!bindingResult.hasErrors()) {
            if (this.servicioUsers.registrarUser(userdto, file[0], galeriaIcon))
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
    public String profile(ModelMap valores, HttpSession session) {
        Summoner summ = this.servicioUsers.getSummonerData(((UserDTO) session.getAttribute("userlogged")).getSummonerName());
        valores.put("summ_level", summ.getLevel());
        valores.put("summ_name", summ.getName());
        valores.put("summ_tier", "No tien");
        valores.put("summ_iconID", summ.getProfileIconID());
        valores.put("summ_playing", summ.getCurrentGame());
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
