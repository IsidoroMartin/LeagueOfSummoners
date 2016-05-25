package com.leagueofsummoners.model.services;

import com.leagueofsummoners.model.dao.SummonerDAO;
import com.leagueofsummoners.model.dto.UserDTO;
import com.leagueofsummoners.model.interfaces.services.IServicesGuides;
import com.leagueofsummoners.model.dto.GuideDTO;
import com.leagueofsummoners.model.dao.ChampionDAO;
import com.leagueofsummoners.model.dao.GuidesDAO;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

@Service
public class GuidesServices implements IServicesGuides, Serializable {

	@Autowired
	private ChampionDAO championDAO;
	@Autowired
	private SummonerDAO summonerDAO;
	@Autowired
	private GuidesDAO guidesDAO;
	

	public GuidesServices() {
	}


	@Override
	public List<GuideDTO> findAll() {
		return this.guidesDAO.findAll();
	}


	@Override
	public GuideDTO findByGuideTitleIgnoringCase(String guideName) {
		return this.guidesDAO.findByGuideTitleIgnoringCase(guideName);
	}


	@Override
	public GuideDTO findByIdGuide(Long idGuide) {
		return this.guidesDAO.findByIdGuide(idGuide);
	}


	@Override
	public List<GuideDTO> findByIdChampion(Long idChampion) {
		return this.guidesDAO.findByIdChampion(idChampion);
	}

	@Override
	public List<GuideDTO> findByIdUser(Long idChampion) {
		return this.guidesDAO.findByIdUser(idChampion);
	}


	@Override
	public void deleteByIdGuide(Long idGuide, Long idUsername) {
		this.guidesDAO.deleteByIdGuide(idGuide,idUsername);
	}


}
