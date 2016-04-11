package com.leagueofsummoners.ws.service;

import java.util.List;

import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.leagueofsummoners.interceptors.LoginRequired;
import com.leagueofsummoners.model.dto.ChampionDTO;
import com.leagueofsummoners.model.dto.UserDTO;
import com.leagueofsummoners.services.interfaces.IServicesChampions;
import com.leagueofsummoners.services.interfaces.IServicesUsers;

/**
 * En esta clase están todos los servicios REST relaccionadas con los usuarios
 * 
 * @author Juanjors
 */
@RestController
@RequestMapping(value = "/api/user")
public class UserRestService {

	@Autowired
	private IServicesUsers servicioUsers;
	

	/**
	 * Este método comprueba si el nombre de usuario esta disponible (Para
	 * informar a tiempo real al usuario que se está registrando
	 * 
	 * @param username
	 * @return si está disponible devuelve true, si no false
	 */
	@RequestMapping(value = "/username/{username}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public boolean checkIfUserAvailable(@PathVariable String username) {
		return this.servicioUsers.checkIfUsernameAvailable(username);
	}
	
	@LoginRequired
	@RequestMapping(value = "/userlist", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public List<UserDTO> getListOfUsers(){
		return this.servicioUsers.getUserList();
	}

	/**
	 * Este método comprueba si el email esta disponible e (Para informar a
	 * tiempo real al usuario que se está registrando
	 * 
	 * @param email
	 * @return si esta disponible devuelve true, si no false.
	 */
	@RequestMapping(value = "/email/{email}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<UserDTO> checkIfEmailExists(@PathVariable String email) {

		return new ResponseEntity<UserDTO>(new UserDTO(), HttpStatus.OK);
	}

	/**
	 * Este método comprueba si el summonerName está disponible en la BD (Para
	 * informar a tiempo real al usuario que se está registrando
	 * 
	 * @param summonerName
	 * @return si está disponible devuelve true, si no false.
	 */
	@RequestMapping(value = "/summonername/{summonerName}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<UserDTO> checkIfSummonerNameExists(@PathVariable String summonerName) {
		return new ResponseEntity<UserDTO>(new UserDTO(), HttpStatus.OK);
	}
}
