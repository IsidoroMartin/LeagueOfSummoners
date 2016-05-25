package com.leagueofsummoners.model.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.leagueofsummoners.model.dto.GuideDTO;
import com.leagueofsummoners.model.interfaces.persistence.ChampionRepository;
import com.leagueofsummoners.model.interfaces.persistence.GuidesRepository;
import com.leagueofsummoners.model.interfaces.persistence.SummonerRepository;
import com.leagueofsummoners.model.interfaces.persistence.UserRepository;

@Component
public class GuidesDAO {

	@Autowired
	private ChampionRepository championRepository;

	@Autowired
	private SummonerRepository summonerRepository;
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private GuidesRepository guidesRepository;

	public List<GuideDTO> findAll() {
		List<GuideDTO> guides = this.guidesRepository.findAll();
		for (GuideDTO guide : guides) {
			guide.setChampion(this.championRepository.findByIdChampion(guide.getIdChampion()));
			guide.setUser(this.userRepository.findByIdUser(guide.getIdUser()));
		}
		return guides;
	}

	public GuideDTO findByGuideTitleIgnoringCase(String guideName) {
		return this.guidesRepository.findByGuideTitleIgnoringCase(guideName);
	}

	public GuideDTO findByIdGuide(Long idGuide) {
		return this.guidesRepository.findByIdGuide(idGuide);
	}

	public List<GuideDTO> findByIdChampion(Long idChampion) {
		List<GuideDTO> guides = this.findByIdChampion(idChampion);

		for (GuideDTO guide : guides) {
			guide.setChampion(this.championRepository.findByIdChampion(guide.getIdChampion()));
		}

		return guides;
	}

	public List<GuideDTO> findByIdUser(Long idUser) {
		List<GuideDTO> guides = this.guidesRepository.findByIdUser(idUser);

		for (GuideDTO guide : guides) {
			guide.setChampion(this.championRepository.findByIdChampion(guide.getIdChampion()));
		}
		return guides;
	}

	@Transactional
	public void deleteByIdGuide(Long idGuide, Long idUsername) {
		this.guidesRepository.deleteByIdGuideAndIdUser(idGuide, idUsername);
	}

}
