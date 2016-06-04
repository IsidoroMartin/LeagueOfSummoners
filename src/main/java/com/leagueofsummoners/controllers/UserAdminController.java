package com.leagueofsummoners.controllers;

import com.leagueofsummoners.ApplicationPaths;
import com.leagueofsummoners.SessionAtts;
import com.leagueofsummoners.model.interfaces.services.IServicesUsers;
import com.leagueofsummoners.security.annotations.LoginAdminRequired;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
 * Este es el controlador encargado de gestionar todo lo relaccionado con los
 * usuarios administradores.
 */
@Controller
public class UserAdminController {

	// Servicio de usuarios
	@Autowired
	private IServicesUsers servicioUsers;

	/**
	 * Este método devuelve la lista de usuarios de la aplicación
	 * 
	 * @param valores
	 * @param session
	 * @return la página con la lista de usuarios
	 */
	@LoginAdminRequired
	@RequestMapping(value = ApplicationPaths.USER_LIST, method = RequestMethod.GET)
	public String register(ModelMap valores, HttpSession session) {
		if ((Boolean) session.getAttribute(SessionAtts.SESSION_IS_ADMIN)) {
			valores.put("userList", this.servicioUsers.findAll());
			valores.put("pageName", "Lista de usuarios");
			return ApplicationPaths.USER_LIST;
		}
		valores.put("activeUserList", "active");
		return "redirect:profile";
	}

}
