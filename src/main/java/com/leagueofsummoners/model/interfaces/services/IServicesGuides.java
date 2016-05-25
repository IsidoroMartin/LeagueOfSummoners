package com.leagueofsummoners.model.interfaces.services;

import java.util.List;

import com.leagueofsummoners.model.dto.GuideDTO;

public interface IServicesGuides {
	List<GuideDTO> findAll();

	GuideDTO findByGuideTitleIgnoringCase(String guideName);

	GuideDTO findByIdGuide(Long idGuide);

	List<GuideDTO> findByIdChampion(Long idChampion);

	List<GuideDTO> findByIdUser(Long idUser);
	
	void deleteByIdGuide(Long idGuide, Long idUsername);
}
