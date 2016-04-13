package com.leagueofsummoners.persistence.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;
import com.leagueofsummoners.interfaces.persistence.UserRepository;
import com.leagueofsummoners.model.dto.UserDTO;

@Component
public class UserDAO {

	@Autowired
	private UserRepository userRepository;

	public UserDTO findByUsernameIgnoringCase(String username) {
		return this.userRepository.findByUsernameIgnoringCase(username);
	}

	public List<UserDTO> getUserList() {
		return this.userRepository.findAll();
	}

	public UserDTO checkValidLogin(String username, String password) {
		UserDTO user = this.findByUsernameIgnoringCase(username);
		if (user != null && user.getPassword().equals(password)) {
			return user;
		}
		return null;
	}

	public boolean checkIfEmailAvailable(String email) {
		UserDTO user = this.userRepository.findByEmailIgnoringCase(email);
		System.out.println(email);
		return user == null;
	}
}
