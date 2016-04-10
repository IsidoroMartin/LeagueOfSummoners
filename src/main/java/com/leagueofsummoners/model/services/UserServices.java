package com.leagueofsummoners.model.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.leagueofsummoners.model.dto.GuideDTO;
import com.leagueofsummoners.model.dto.UserDTO;
import com.leagueofsummoners.persistence.dao.UserDAO;
import com.leagueofsummoners.persistence.interfaces.UserRepository;
import com.leagueofsummoners.services.interfaces.IServicesUsers;

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
		// TODO Auto-generated method stub
		return false;
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

}
