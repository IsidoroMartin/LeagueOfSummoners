package com.leagueofsummoners.ws.service;

import com.leagueofsummoners.ApplicationPaths;
import com.leagueofsummoners.SessionAtts;
import com.leagueofsummoners.model.dto.GenericJsonValidator;
import com.leagueofsummoners.model.dto.UserDTO;
import com.leagueofsummoners.model.interfaces.services.IServicesGuides;
import com.leagueofsummoners.security.annotations.LoginRequired;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
 * En esta clase están todos los servicios REST relaccionadas con los usuarios
 * @author Juanjors
 */
@RestController
@RequestMapping(value = "/api/guides")
public class GuidesRestService {

	@Autowired
	private IServicesGuides serviceGuides;

	@LoginRequired
	@RequestMapping(value = { ApplicationPaths.GUIDES_BORRAR_PATH }, method = RequestMethod.DELETE)
	@ResponseBody
	public GenericJsonValidator deleteGuide(@PathVariable("id") Long guideId, HttpSession session) {
		if (guideId != null) {
			UserDTO user = (UserDTO) session.getAttribute(SessionAtts.SESSION_GET_USER_LOGGED);
			this.serviceGuides.deleteByIdGuide(guideId, user.getIdUser());
		}
		return new GenericJsonValidator(this.serviceGuides.findByIdGuide(guideId, false) == null);
	}

}
