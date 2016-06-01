package com.leagueofsummoners.model.dao;

import com.leagueofsummoners.LeagueofsummonersApplication;
import com.leagueofsummoners.model.interfaces.persistence.UserRepository;
import com.leagueofsummoners.model.dto.UserDTO;
import com.leagueofsummoners.model.utils.PasswordHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
 * Capa de acceso a los datos de las usuarios.
 */

@Component
public class UserDAO {

	// Repositorio de los usuarios
	@Autowired
	private UserRepository userRepository;

	/**
	 * Busca un usuario por nombre
	 * 
	 * @param username
	 * @return el usuario o null si no existe
	 */
	public UserDTO findByUsernameIgnoringCase(String username) {
		return this.userRepository.findByUsernameIgnoringCase(username);
	}

	/**
	 * Obtiene la lista de usuarios registrados en la aplicación
	 * 
	 * @return La lista de usuarios
	 */
	public List<UserDTO> getUserList() {
		return this.userRepository.findAll();
	}

	/**
	 * Comprueba si el login del usuario es válido
	 * 
	 * @param username
	 * @param password
	 * @return devuelve el usuario loggeado o null.
	 */
	public UserDTO checkValidLogin(String username, String password) {
		UserDTO user = this.findByUsernameIgnoringCase(username);
		if (user != null && PasswordHash.validatePassword(password, user.getPassword())) {
			return user;
		}
		return null;
	}

	/**
	 * Comprueba si el correo del usuario esta disponible o no
	 * 
	 * @param email
	 * @return true si está disponible false si no lo esta
	 */
	public boolean checkIfEmailAvailable(String email) {
		UserDTO user = this.userRepository.findByEmailIgnoringCase(email);
		return user == null;
	}

	/*
	 * Guarda un usuario en la BD
	 */
	public UserDTO save(UserDTO usuario) {
		LeagueofsummonersApplication.LOGGER.debug(usuario.toString());
		return this.userRepository.save(usuario);
	}

	/**
	 * Actualizar el permiso de un usuario en la BD
	 * 
	 * @param userID
	 * @param userToChange
	 * @return
	 */
	public int updateUserPermission(String newPermissionLevel, String userToChange) {
		return this.userRepository.updateUserPermission(newPermissionLevel, userToChange);
	}

	/**
	 * Borrar a un usuario por nombre
	 * @param userToDelete
	 */
	@Transactional
	public void deleteByUsername(String userToDelete) {
		this.userRepository.deleteByUsername(userToDelete);
	}
}
