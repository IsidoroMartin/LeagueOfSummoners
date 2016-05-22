package com.leagueofsummoners.model.interfaces.persistence;


import com.leagueofsummoners.model.dto.ChampionPassiveDTO;
import com.leagueofsummoners.model.dto.ChampionSpellDTO;
import org.springframework.data.repository.Repository;

import java.util.List;

@org.springframework.stereotype.Repository
public interface PassivesRepository extends Repository<ChampionPassiveDTO, Long> {

	List<ChampionPassiveDTO> findAll();

	ChampionPassiveDTO findByIdPassive(Long passiveId);

}
