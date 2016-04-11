package com.leagueofsummoners.services.interfaces;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.leagueofsummoners.model.dto.GuideDTO;
import com.leagueofsummoners.model.dto.UserDTO;

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
	 * @param username
	 * @return true si está disponible, false si no lo esta
	 */
	boolean checkIfEmailAvailable(String email);

	/**
	 * Mira si el nombre de invocador parametrizado esta disponible
	 * 
	 * @param username
	 * @return true si está disponible, false si no lo esta
	 */
	boolean checkIfSummonerNameAvailable(String summonerName);

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
	UserDTO createUser(UserDTO usuario);

	/**
	 * Lista todos los posts de un usuario
	 * 
	 * @param userId
	 * @return Lista de los posts de un usuario
	 */
	List<GuideDTO> listGuidesFromUser(int userId);

	List<UserDTO> getUserList();

	boolean checkValidLogin(String username, String password, HttpSession session);

}
