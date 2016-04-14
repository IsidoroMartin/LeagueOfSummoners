package com.leagueofsummoners.interfaces.persistence;



import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import com.leagueofsummoners.model.dto.ChampionDTO;
import com.leagueofsummoners.model.dto.UserDTO;

@org.springframework.stereotype.Repository
public interface ChampionRepository extends Repository<ChampionDTO, Long> {

	Page<ChampionDTO> findAll(Pageable pageable);
	
	List<ChampionDTO> findAll();

	ChampionDTO findBychampionNameIgnoringCase(String championName);
	
	ChampionDTO findByIdChampion(Long idChampion);

}
