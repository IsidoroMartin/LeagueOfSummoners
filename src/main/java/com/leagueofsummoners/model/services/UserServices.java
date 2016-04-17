package com.leagueofsummoners.model.services;

import com.leagueofsummoners.LeagueofsummonersApplication;
import com.leagueofsummoners.interfaces.services.IServicesUsers;
import com.leagueofsummoners.model.dto.GuideDTO;
import com.leagueofsummoners.model.dto.UserDTO;
import com.leagueofsummoners.model.utils.PasswordHash;
import com.leagueofsummoners.model.utils.UploadUtils;
import com.leagueofsummoners.persistence.dao.SummonerDAO;
import com.leagueofsummoners.persistence.dao.UserDAO;
import com.robrua.orianna.type.core.summoner.Summoner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class UserServices implements IServicesUsers {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private SummonerDAO summonerDAO;

    public UserServices() {
    }

    @Override
    public boolean checkIfUsernameAvailable(String username) {
        return this.userDAO.findByUsernameIgnoringCase(username).getUsername().equals(username);
    }

    @Override
    public boolean checkIfEmailAvailable(String email) {
        return this.userDAO.checkIfEmailAvailable(email);
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
    public UserDTO save(UserDTO usuario) {
        return this.userDAO.save(usuario);
    }


    @Override
    public List<GuideDTO> listGuidesFromUser(int userId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<UserDTO> getUserList() {
        return this.userDAO.getUserList();
    }

    @Override
    public boolean checkValidLoginCreateSession(String username, String password, HttpSession session) {
        UserDTO user = this.userDAO.checkValidLogin(username, password);
        boolean userNotNull = user != null;
        if (userNotNull) {
            this.createUserSession(session, user);
        }
        return userNotNull;
    }


    private void createUserSession(HttpSession session, UserDTO user) {
        LeagueofsummonersApplication.LOGGER.debug("Usuario " + user.getUsername() + " logged");
        session.setMaxInactiveInterval(60 * 60); //La session expirará en 1h
        session.setAttribute("userlogged", user); //El atributo userlogged contiene el usuario con todos sus atributos
        session.setAttribute("logged", true); //Un atributo logged para saber si esta logged o no
        session.setAttribute("admin", user.isAdmin()); //Un atributo admin para saber si el usuario es admin
    }

    @Override
    public boolean checkIfUserExists(String username) {
        return false;
    }

    @Override
    public boolean registrarUser(UserDTO userdto, MultipartFile file, String galeriaIcon) {
        if (!this.checkIfUserExists(userdto.getUsername())) {
            String savePath = null;
            savePath = UploadUtils.saveImg(file, userdto.getUsername());
            if (savePath != null) {
                userdto.setAvatar(savePath);
            } else {
                userdto.setAvatar(galeriaIcon);
            }
            String passwordHash = PasswordHash.createHash(userdto.getPassword());
            userdto.setPassword((passwordHash));
            return this.save(userdto) != null; //Devuelve un usuario si este ha sido insertado, si no, null
        }
        return false;
    }

    @Override
    public Summoner getSummonerData(String summonerName) {
        return this.summonerDAO.getSummonerData(summonerName);
    }


}
