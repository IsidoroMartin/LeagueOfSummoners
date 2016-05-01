package com.leagueofsummoners.model.interfaces.persistence;


import com.leagueofsummoners.model.dto.ChampionDTO;
import com.leagueofsummoners.model.dto.GuideDTO;
import org.springframework.data.repository.Repository;

import java.util.List;

@org.springframework.stereotype.Repository
public interface GuidesRepository extends Repository<GuideDTO, Long> {


	List<GuideDTO> findAll();

	GuideDTO findByGuideTitleIgnoringCase(String guideName);

	GuideDTO findByIdGuide(Long idGuide);

	List<GuideDTO> findByIdChampion(Long idChampion);

	List<GuideDTO> findByIdUser(Long idChampion);

}
