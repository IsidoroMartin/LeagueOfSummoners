package com.leagueofsummoners.persistence.dao;

import com.leagueofsummoners.LeagueofsummonersApplication;
import com.leagueofsummoners.interfaces.persistence.UserRepository;
import com.leagueofsummoners.model.dto.UserDTO;
import com.leagueofsummoners.model.utils.PasswordHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

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
        if (user != null && PasswordHash.validatePassword(password, user.getPassword())) {
            return user;
        }
        return null;
    }

    public boolean checkIfEmailAvailable(String email) {
        UserDTO user = this.userRepository.findByEmailIgnoringCase(email);
        return user == null;
    }

    public UserDTO save(UserDTO usuario) {
        LeagueofsummonersApplication.LOGGER.debug(usuario.toString());
        return this.userRepository.save(usuario);
    }
}
