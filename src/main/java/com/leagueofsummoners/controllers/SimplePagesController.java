package com.leagueofsummoners.controllers;

import com.leagueofsummoners.ApplicationPaths;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by isi on 27/05/2016.
 */
@Controller
public class SimplePagesController {
    @RequestMapping(value = ApplicationPaths.COOKIES_PATH, method = RequestMethod.GET)
    public String cookies() {
        return "/cookies";
    }
    @RequestMapping(value = ApplicationPaths.P_PRIVACIDAD_PATH, method = RequestMethod.GET)
    public String p_privacidad() {
        return "/p_privacidad";
    }
}
