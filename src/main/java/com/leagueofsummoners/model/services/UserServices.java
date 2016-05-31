package com.leagueofsummoners.model.services;

import com.leagueofsummoners.LeagueofsummonersApplication;
import static com.leagueofsummoners.SessionAtts.*;
import com.leagueofsummoners.model.interfaces.services.IServicesUsers;
import com.leagueofsummoners.model.dto.GuideDTO;
import com.leagueofsummoners.model.dto.UserDTO;
import com.leagueofsummoners.model.utils.LeagueAccessAPI;
import com.leagueofsummoners.model.utils.PasswordHash;
import com.leagueofsummoners.model.utils.UploadUtils;
import com.leagueofsummoners.model.dao.GuidesDAO;
import com.leagueofsummoners.model.dao.SummonerDAO;
import com.leagueofsummoners.model.dao.UserDAO;
import com.robrua.orianna.api.core.RiotAPI;
import com.robrua.orianna.type.core.summoner.Summoner;

import groovy.util.logging.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
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
 * Esta clase contiene los servicios del usuario. 
 * Implementa su interfaz correspondiente donde está la definición de 
 * los métodos y su explicación. (Comentarios)
 */
@Slf4j
@Service
public class UserServices implements IServicesUsers {

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private SummonerDAO summonerDAO;
	
	@Autowired
	private GuidesDAO guidesDAO;

	public UserServices() {
	}

	/**
	 * Mira si el username parametrizado esta disponible
	 *
	 * @param username
	 * @return true si está disponible, false si no lo esta
	 */
	@Override
	public boolean checkIfUsernameAvailable(String username) {
		UserDTO user = this.userDAO.findByUsernameIgnoringCase(username);
		return user == null;
	}

	/**
	 * Mira si el email parametrizado esta disponible
	 * 
	 * @param email
	 * @return true si está disponible, false si no lo esta
	 */
	@Override
	public boolean checkIfEmailAvailable(String email) {
		return this.userDAO.checkIfEmailAvailable(email);
	}

	/**
	 * Mira si el nombre de invocador parametrizado esta disponible
	 * 
	 * @param summonerName
	 * @return true si está disponible, false si no lo esta
	 */
	@Override
	public boolean checkIfSummonerNameExists(String summonerName, HttpSession session) {
		boolean summonerExists = this.summonerDAO.getSummonerData(summonerName) != null;
		session.setAttribute(SESSION_SUMMONER_EXISTS, summonerExists);
		return summonerExists;
	}


	/**
	 * Devuelve una lista de todos los usuarios
	 * 
	 * @return Lista con los usuarios
	 */
	@Override
	public UserDTO save(UserDTO usuario) {
		return this.userDAO.save(usuario);
	}

	/**
	 * Encuentra a un usuario por id
	 * 
	 * @param id
	 * @return El usuario con la id o null.
	 */
	@Override
	public List<UserDTO> getUserList() {
		return this.userDAO.getUserList();
	}
	
	/**
	 * Crea un usuario en la BD
	 * 
	 * @param usuario
	 * @return Devuelve el usuario creado o null si hay algún error
	 */
	@Override
	public boolean checkValidLoginCreateSession(String username, String password, HttpSession session) {
		UserDTO user = this.userDAO.checkValidLogin(username, password);
		boolean userNotNull = user != null;
		if (userNotNull) {
			this.createUserSession(session, user);
		}
		return userNotNull;
	}
	
	/**
	 * Crea la sessión del usuario
	 * @param session
	 * @param user
	 */
	private void createUserSession(HttpSession session, UserDTO user) {
		LeagueofsummonersApplication.LOGGER.info("Usuario " + user.getUsername() + " logged");
		// La sessión expirará en 1h
		session.setMaxInactiveInterval(60 * 60); // La session expirará en
		// El usuario userLogged contiene el usuario con todos sus atts.
		session.setAttribute(SESSION_GET_USER_LOGGED, user);
		// Boolean si el usuario es logged
		session.setAttribute(SESSION_IS_LOGGED, true);
		// Saber si el user es Admin
		session.setAttribute(SESSION_IS_ADMIN, user.isAdmin());
		try {
			long summonerID = RiotAPI.getSummonerByName(user.getSummonerName()).getID();
			user.setSummonerID(summonerID);
		} catch (Exception e) {
			LeagueofsummonersApplication.LOGGER.error("Error obteniendo el ID del summoner");
			e.printStackTrace();
		}

	}

	/**
	 * Registra al usuario
	 * @param user
	 * @param file
	 * @param galeriaIcon
	 * @return true si es registado correctamente o false si no
	 */
	@Override
	public boolean registrarUser(UserDTO userdto, MultipartFile[] file, String galeriaIcon) {
		if (!this.checkIfUsernameAvailable(userdto.getUsername())) {
			if (galeriaIcon.equals("") && file.length == 1 && !file[0].isEmpty()) {
				String savePath = "img" + File.separator + "avatars"
						+ UploadUtils.saveImg(file[0], userdto.getUsername());
				userdto.setAvatar(savePath);
			} else if (galeriaIcon.equals("")) {
				galeriaIcon = LeagueAccessAPI.RIOT_API_TEEMO_ICON;
				userdto.setAvatar(galeriaIcon);
			} else {
				userdto.setAvatar(galeriaIcon);
			}

			String passwordHash = PasswordHash.createHash(userdto.getPassword());
			userdto.setPassword((passwordHash));
			UserDTO user = this.save(userdto);
			if (user != null)
				this.summonerDAO.getLatestMatchesFromRiotAsync(user, 5);
			// Devuelve true si el usuario se ha guardado bien, false si no
			return user != null;
		}
		return false;
	}

	/**
	 * Obtiene los datos de este summonername del API de RIOT
	 * @param summonerName
	 * @return
	 */
	@Override
	public Summoner getSummonerData(String summonerName) {
		Summoner summ = null;
		try {
			summ = this.summonerDAO.getSummonerData(summonerName);
		} catch (Exception e) {
			com.robrua.orianna.type.dto.summoner.Summoner summData = new com.robrua.orianna.type.dto.summoner.Summoner();
			summData.setName("No disponible");
			summData.setSummonerLevel(0L);
			summData.setProfileIconId(2001);
			summ = new Summoner(summData);
			e.printStackTrace();
		}
		return summ;
	}
	
	/**
	 * Actualiza el permiso del usuario
	 * @param newPermissionLevel
	 * @param userToChange
	 * @return el número de columnas actualizadas
	 */
	@Override
	public int updateUserPermission(String newPermissionLevel, String userToChange) {
		return this.userDAO.updateUserPermission(newPermissionLevel,userToChange);
	}

	/**
	 * Obtiene todos los usuarios
	 */
	@Override
	public List<UserDTO> findAll() {
		List<UserDTO> users = this.userDAO.getUserList();
		for (UserDTO userDTO : users) {
			userDTO.setGuidesNumber(this.guidesDAO.findByIdUser(userDTO.getIdUser()).size());
		}
		return this.userDAO.getUserList();
	}
	
	/**
	 * Elimina por username
	 */
	@Override
	public void deleteUserByUsername(String userToDelete) {
		this.userDAO.deleteByUsername(userToDelete);
	}

	
	/**
	 * Estos métodos aun no están implementados
	 */
	

	@Override
	public UserDTO findById(int id) {
		return null;
	}
	
	@Override
	public List<GuideDTO> listGuidesFromUser(int userId) {
		return null;
	}

	


}
