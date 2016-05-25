package com.leagueofsummoners.model.services;

import com.leagueofsummoners.LeagueofsummonersApplication;
import static com.leagueofsummoners.SessionAtts.*;
import com.leagueofsummoners.model.interfaces.services.IServicesUsers;
import com.leagueofsummoners.model.dto.GuideDTO;
import com.leagueofsummoners.model.dto.MatchDTO;
import com.leagueofsummoners.model.dto.UserDTO;
import com.leagueofsummoners.model.utils.LeagueAccessAPI;
import com.leagueofsummoners.model.utils.PasswordHash;
import com.leagueofsummoners.model.utils.UploadUtils;
import com.leagueofsummoners.model.dao.SummonerDAO;
import com.leagueofsummoners.model.dao.UserDAO;
import com.robrua.orianna.api.core.RiotAPI;
import com.robrua.orianna.type.core.summoner.Summoner;

import groovy.util.logging.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.List;

@Slf4j
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

	@Override
	public boolean checkIfUserExists(String username) {
		return false;
	}

	@Override
	public boolean registrarUser(UserDTO userdto, MultipartFile[] file, String galeriaIcon) {
		if (!this.checkIfUserExists(userdto.getUsername())) {
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

	
	

}
