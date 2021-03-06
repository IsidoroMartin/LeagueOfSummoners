package com.leagueofsummoners.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

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
 * El controlador de los errores de la pagina
 */
@Controller
public class ErrorController {
    @RequestMapping(value = {"404"})
    public String error404(ModelMap model, HttpSession session) {
        return "error404";
    }

    @RequestMapping(value = {"500"})
    public String error500(ModelMap model, HttpSession session) {
        return "error500";
    }

    @RequestMapping(value = {"forbidden"})
    @ResponseBody
    public ModelAndView forbidden(ModelMap model, @RequestParam(value = "url") String url) {
        model.addAttribute("url", url);
        return new ModelAndView("forbidden");
    }
}