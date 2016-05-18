package com.leagueofsummoners.controllers;

import com.leagueofsummoners.ApplicationPaths;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Locale;

import static com.leagueofsummoners.ApplicationPaths.GUIDES_HTML_PATH;
import static com.leagueofsummoners.ApplicationPaths.GUIDES_PATH;

@Controller
public class SearchController {

    @RequestMapping(value = ApplicationPaths.SEARCH_PATH, method = RequestMethod.GET)
    public
    @ResponseBody
    ModelAndView index(ModelMap valores, Locale locale, @RequestParam(value = "search_input") String search_input) {
        String view = "";

        //Comprobar si es un invocador y redirigir a la pagina si lo es
        if (false) {
            view = "redirect:/summonermatchstadistics?search_input=" + search_input;
        } else {
            view = "redirect:/guides?search_input=" + search_input;
        }
        // Si no redirijo a la de guías
        return new ModelAndView(view);
    }
}
