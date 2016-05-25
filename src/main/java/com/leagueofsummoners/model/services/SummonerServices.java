package com.leagueofsummoners.model.services;

import com.leagueofsummoners.model.dao.SummonerDAO;
import com.leagueofsummoners.model.dto.MatchDTO;
import com.leagueofsummoners.model.dto.UserDTO;
import com.leagueofsummoners.model.interfaces.services.IServicesSummoner;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class SummonerServices implements IServicesSummoner {

	@Autowired
	private SummonerDAO summonerDAO;

	@Override
	public List<MatchDTO> getLatestMatchesAsync(UserDTO userlogged, int nMatches) throws Exception {
		List<MatchDTO> lista = null;
		try {
			lista = this.summonerDAO.getLatestMatchesFromRiotAsync(userlogged, nMatches).get();
			lista = this.summonerDAO.getLatestMatchesFromDB(userlogged);
		} catch (Exception e) {
			lista = this.summonerDAO.getLatestMatchesFromDB(userlogged);
			log.error("Error en GetLatestMatches " + e.getMessage());
		}
		return lista;
	}

	@Override
	public List<MatchDTO> getLatestMatchesFromDB(UserDTO userlogged) {
		List<MatchDTO> lista = null;
		lista = this.summonerDAO.getLatestMatchesFromDB(userlogged);
		return lista;
	}

	@Override
	public List<MatchDTO> getLatestMatchesSync(UserDTO userlogged, int nMatches) throws Exception {
		List<MatchDTO> lista = null;
		try {
			lista = this.summonerDAO.getLatestMatchesFromRiot(userlogged, nMatches).get();
			lista = this.summonerDAO.getLatestMatchesFromDB(userlogged);
		} catch (Exception e) {
			lista = this.summonerDAO.getLatestMatchesFromDB(userlogged);
			log.error("Error en GetLatestMatches " + e.getMessage());
		}
		return lista;
	}
}
