package com.leagueofsummoners.controllers;

import com.leagueofsummoners.ApplicationPaths;
import com.leagueofsummoners.MailConfig;
import com.leagueofsummoners.model.utils.MailUtils;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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

@Controller
public class FooterPagesController {

	/**
	 * Página de política de cookies
	 * 
	 * @param map
	 * @return Página de política de cookies
	 */
	@RequestMapping(value = ApplicationPaths.COOKIES_PATH, method = RequestMethod.GET)
	public String cookies(ModelMap map) {
		map.put("pageName", "Política de cookies");
		return ApplicationPaths.COOKIES_PATH;
	}

	/**
	 * Página de política de privacidad
	 * 
	 * @param map
	 * @return Política de privacidad
	 */
	@RequestMapping(value = ApplicationPaths.P_PRIVACIDAD_PATH, method = RequestMethod.GET)
	public String p_privacidad(ModelMap map) {
		map.put("pageName", "Política de Privacidad y Protección de datos personales");
		return ApplicationPaths.P_PRIVACIDAD_PATH;
	}

	/***
	 * Página del mapa web
	 * 
	 * @param map
	 * @return Página de mapa web
	 */
	@RequestMapping(value = ApplicationPaths.MAPA_WEB, method = RequestMethod.GET)
	public String mapaWeb(ModelMap map) {
		map.put("pageName", "Mapa del sitio");
		return "/mapa_web";
	}

	/**
	 * Página de la licencia
	 * 
	 * @param map
	 * @return página de la licencia
	 */
	@RequestMapping(value = ApplicationPaths.LICENCIA, method = RequestMethod.GET)
	public String licencia(ModelMap map) {
		map.put("pageName", "Licencia");
		return ApplicationPaths.LICENCIA;
	}

	/**
	 * Página de ayuda
	 * 
	 * @param map
	 * @return página de ayuda
	 */
	@RequestMapping(value = ApplicationPaths.AYUDA, method = RequestMethod.GET)
	public String ayuda(ModelMap map) {
		map.put("pageName", "Ayuda");
		return ApplicationPaths.AYUDA;
	}

	/**
	 * Página de contacto
	 * 
	 * @param map
	 * @return página de contacto
	 */
	@RequestMapping(value = ApplicationPaths.CONTACT, method = RequestMethod.GET)
	public String contacto(ModelMap map) {
		map.put("pageName", "Contacto");
		return ApplicationPaths.CONTACT;
	}

	/**
	 * Página de contacto post
	 * 
	 * @return página de contacto
	 */
	@RequestMapping(value = ApplicationPaths.CONTACT, method = RequestMethod.POST)
	public String contactoPost(@RequestParam("nombre") String nombre, @RequestParam("email") String email,
			@RequestParam("content") String content) {
		String mensaje = "La solicitud no se podido enviar";
		if (StringUtils.isNotEmpty(nombre) && StringUtils.isNotEmpty(email) && StringUtils.isNotEmpty(content)) {
			MailUtils.sendEmail(MailConfig.EMAIL_USERNAME, "El usuario " + email + " Tiene una petición: " + "User:" + nombre, content);
			mensaje = "Solicitud enviada correctamente";
		}
		return "redirect:contact?success=" + mensaje;
	}
}
