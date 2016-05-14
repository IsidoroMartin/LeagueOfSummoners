package com.leagueofsummoners.model.services;

import com.leagueofsummoners.LeagueofsummonersApplication;
import static com.leagueofsummoners.SessionAtts.*;
import com.leagueofsummoners.model.interfaces.services.IServicesUsers;
import com.leagueofsummoners.model.dto.GuideDTO;
import com.leagueofsummoners.model.dto.UserDTO;
import com.leagueofsummoners.model.utils.LeagueAccessAPI;
import com.leagueofsummoners.model.utils.PasswordHash;
import com.leagueofsummoners.model.utils.UploadUtils;
import com.leagueofsummoners.model.dao.SummonerDAO;
import com.leagueofsummoners.model.dao.UserDAO;
import com.robrua.orianna.api.core.RiotAPI;
import com.robrua.orianna.type.core.summoner.Summoner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
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
        UserDTO user = this.userDAO.findByUsernameIgnoringCase(username);
        return user == null;
    }

    @Override
    public boolean checkIfEmailAvailable(String email) {
        return this.userDAO.checkIfEmailAvailable(email);
    }

    @Override
    public boolean checkIfSummonerNameExists(String summonerName, HttpSession session) {
        boolean summonerExists = this.summonerDAO.getSummonerData(summonerName) != null;
        session.setAttribute(SESSION_SUMMONER_EXISTS, summonerExists);
        return summonerExists;
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
        LeagueofsummonersApplication.LOGGER.info("Usuario " + user.getUsername() + " logged");
        long summonerID = RiotAPI.getSummonerByName(user.getSummonerName()).getID();
        user.setSummonerID(summonerID);
        session.setMaxInactiveInterval(60 * 60); //La session expirará en 1h
        session.setAttribute(SESSION_GET_USER_LOGGED, user); //El atributo userlogged contiene el usuario con todos sus atributos
        session.setAttribute(SESSION_IS_LOGGED, true); //Un atributo logged para saber si esta logged o no
        session.setAttribute(SESSION_IS_ADMIN, user.isAdmin()); //Un atributo admin para saber si el usuario es admin

    }

    @Override
    public boolean checkIfUserExists(String username) {
        return false;
    }

    @Override
    public boolean registrarUser(UserDTO userdto, MultipartFile[] file, String galeriaIcon) {
        if (!this.checkIfUserExists(userdto.getUsername())) {
            if (galeriaIcon.equals("") && file.length == 1 && !file[0].isEmpty()) {
                String savePath = "img" + File.separator + "avatars" + UploadUtils.saveImg(file[0], userdto.getUsername());
                userdto.setAvatar(savePath);
            } else if (galeriaIcon.equals("")) {
                galeriaIcon = LeagueAccessAPI.RIOT_API_TEEMO_ICON;
            } else {
                userdto.setAvatar(galeriaIcon);
            }
            String passwordHash = PasswordHash.createHash(userdto.getPassword());
            userdto.setPassword((passwordHash));
            return this.save(userdto) != null; //Devuelve true si el usuario es distinto de nulo
        }
        return false;
    }

    @Override
    public Summoner getSummonerData(String summonerName) {
        return this.summonerDAO.getSummonerData(summonerName);
    }


}
