package com.leagueofsummoners.model.services;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leagueofsummoners.interfaces.services.IServicesUsers;
import com.leagueofsummoners.model.dto.GuideDTO;
import com.leagueofsummoners.model.dto.UserDTO;
import com.leagueofsummoners.persistence.dao.UserDAO;

@Service
public class UserServices implements IServicesUsers {

	@Autowired
	private UserDAO userdao;

	public UserServices() {
	}

	@Override
	public boolean checkIfUsernameAvailable(String username) {
		return this.userdao.findByUsernameIgnoringCase(username).getUsername().equals(username);
	}

	@Override
	public boolean checkIfEmailAvailable(String email) {
		return this.userdao.checkIfEmailAvailable(email);
	}

	@Override
	public boolean checkIfSummonerNameAvailable(String summonerName) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<UserDTO> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDTO findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDTO createUser(UserDTO usuario) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<GuideDTO> listGuidesFromUser(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserDTO> getUserList() {
		return this.userdao.getUserList();
	}

	@Override
	public boolean checkValidLoginSetSessionStatus(String username, String password, HttpSession session) {
		UserDTO user = this.userdao.checkValidLogin(username, password);
		boolean userNull = user != null;
		if (userNull){
			session.setMaxInactiveInterval(60*60); //La session expirará en 1h
			session.setAttribute("userlogged", user); //El atributo userlogged contiene el usuario con todos sus atributos
			session.setAttribute("logged", true); //Un atributo logged para saber si esta logged o no
			session.setAttribute("admin", user.isAdmin()); //Un atributo admin para saber si el usuario es admin
		}
		return user != null;
	}

}
