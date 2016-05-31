package com.leagueofsummoners.ws.service;

import static com.leagueofsummoners.ApplicationPaths.*;
import com.leagueofsummoners.model.interfaces.services.IServicesUsers;
import com.leagueofsummoners.security.annotations.LoginAdminRequired;
import com.leagueofsummoners.SessionAtts;
import com.leagueofsummoners.model.dto.GenericJsonValidator;
import com.leagueofsummoners.model.dto.UserDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.ws.rs.core.MediaType;

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
 *
 * @author Juanjors
 */
@RestController
@RequestMapping(value = REST_API_USER)
public class UserRestService {

	@Autowired
	private IServicesUsers servicioUsers;

	/**
	 * Este método comprueba si el nombre de usuario esta disponible (Para
	 * informar al usuario mediante una petición asincrona)
	 *
	 * @param username
	 * @return si está disponible devuelve true, si no false
	 */
	@RequestMapping(value = REST_API_USER_USERNAME, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON)
	public GenericJsonValidator checkIfUserAvailable(@RequestParam("username") String username) {
		return new GenericJsonValidator(
				username.length() >= 4 && this.servicioUsers.checkIfUsernameAvailable(username));
	}

	/**
	 * Este método comprueba si el email esta disponible e (Para informar a
	 * tiempo real al usuario que se está registrando
	 *
	 * @param email
	 * @return si esta disponible devuelve true, si no false.
	 */
	@RequestMapping(value = REST_API_USER_EMAIL, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON)
	public GenericJsonValidator checkIfEmailExists(@RequestParam("email") String email) {
		return new GenericJsonValidator(this.servicioUsers.checkIfEmailAvailable(email));
	}

	/**
	 * Este método comprueba si el summonerName está presente en la BD de RIOT
	 * 
	 * @param summonerName
	 * @return si está presente devuelve true, si no false.
	 */
	@RequestMapping(value = REST_API_USER_SUMMONERNAME, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON)
	public GenericJsonValidator checkSummonerExists(@RequestParam("summonerName") String summonerName,
			HttpSession session) {
		int summonerLength = summonerName.length();
		return new GenericJsonValidator(summonerLength >= 4 && summonerLength <= 24
				&& this.servicioUsers.checkIfSummonerNameExists(summonerName, session));
	}

	/**
	 * Actualiza los permisos del usuario
	 * 
	 * @param newPermissionLevel
	 * @param userToChange
	 * @return 1 si se han cambiado 0 si no.
	 */
	@LoginAdminRequired
	@RequestMapping(value = REST_API_USER_UPDATE_PERMISSION_LEVEL, method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON)
	public int updateSummonerPermission(@RequestParam("permission") String newPermissionLevel,
			@RequestParam("username") String userToChange,HttpSession session) {
		if (newPermissionLevel.equals("Admin") || newPermissionLevel.equals("Mod")
				|| newPermissionLevel.equals("User") && userToChange != "") {
			UserDTO dto = (UserDTO)session.getAttribute(SessionAtts.SESSION_GET_USER_LOGGED);
			if(userToChange.equals(dto.getUsername())) 
				session.setAttribute(SessionAtts.SESSION_IS_ADMIN, newPermissionLevel.equals("Admin") ? true : false);
			return this.servicioUsers.updateUserPermission(newPermissionLevel, userToChange);
		}
		return 0;
	}

	/**
	 * Elimina un usuario en la BD
	 * @param userToDelete
	 * @param session
	 * @return ok si ha ido bien ko si no.
	 */
	@LoginAdminRequired
	@RequestMapping(value = REST_API_USER_DELETE_USER, method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON)
	public String deleteByUsername(@PathVariable("username") String userToDelete, HttpSession session) {
		if ((Boolean) session.getAttribute(SessionAtts.SESSION_IS_ADMIN)) {
			this.servicioUsers.deleteUserByUsername(userToDelete);
			return "ok";
		}
		return "ko";
	}

}