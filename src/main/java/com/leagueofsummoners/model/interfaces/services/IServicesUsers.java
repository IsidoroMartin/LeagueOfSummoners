package com.leagueofsummoners.model.interfaces.services;

import com.leagueofsummoners.model.dto.GuideDTO;

import com.leagueofsummoners.model.dto.UserDTO;
import com.robrua.orianna.type.core.summoner.Summoner;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
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
 * Servicio users, esta es la capa intermedia entre los controlladores y los 
 * objetos DAO.
 */

public interface IServicesUsers {

	/**
	 * Mira si el username parametrizado esta disponible
	 *
	 * @param username
	 * @return true si está disponible, false si no lo esta
	 */
	boolean checkIfUsernameAvailable(String username);

	/**
	 * Mira si el email parametrizado esta disponible
	 * 
	 * @param email
	 * @return true si está disponible, false si no lo esta
	 */
	boolean checkIfEmailAvailable(String email);

	/**
	 * Mira si el nombre de invocador parametrizado esta disponible
	 * 
	 * @param summonerName
	 * @return true si está disponible, false si no lo esta
	 */
	boolean checkIfSummonerNameExists(String summonerName, HttpSession session);

	/**
	 * Devuelve una lista de todos los usuarios
	 * 
	 * @return Lista con los usuarios
	 */
	List<UserDTO> findAll();

	/**
	 * Encuentra a un usuario por id
	 * 
	 * @param id
	 * @return El usuario con la id o null.
	 */
	UserDTO findById(int id);

	/**
	 * Crea un usuario en la BD
	 * 
	 * @param usuario
	 * @return Devuelve el usuario creado o null si hay algún error
	 */
	UserDTO save(UserDTO usuario);

	/**
	 * Lista todos los posts de un usuario
	 * 
	 * @param userId
	 * @return Lista de los posts de un usuario
	 */
	List<GuideDTO> listGuidesFromUser(int userId);

	/**
	 * Obtiene una lista con todos los usuarios
	 * @return
	 */
	List<UserDTO> getUserList();

	/**
	 * Mira si el login es correcto
	 * @param username
	 * @param password
	 * @param session
	 * @return true o false si es correcto o no
	 */
	boolean checkValidLoginCreateSession(String username, String password, HttpSession session);
	

	/**
	 * Registra al usuario
	 * @param user
	 * @param file
	 * @param galeriaIcon
	 * @return true si es registado correctamente o false si no
	 */
	boolean registrarUser(UserDTO user, MultipartFile[] file, String galeriaIcon);

	/**
	 * Obtiene los datos de este summonername del API de RIOT
	 * @param summonerName
	 * @return
	 */
	Summoner getSummonerData(String summonerName);

	/**
	 * Actualiza el permiso del usuario
	 * @param newPermissionLevel
	 * @param userToChange
	 * @return el número de columnas actualizadas
	 */
	int updateUserPermission(String newPermissionLevel, String userToChange);

	/**
	 * Elimina por username
	 * @param userToDelete
	 */
	void deleteUserByUsername(String userToDelete);
	
	
	
}
