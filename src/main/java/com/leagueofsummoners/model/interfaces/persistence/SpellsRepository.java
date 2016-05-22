package com.leagueofsummoners.model.interfaces.persistence;


import com.leagueofsummoners.model.dto.ChampionSpellDTO;
import org.springframework.data.repository.Repository;

import java.util.List;

@org.springframework.stereotype.Repository
public interface SpellsRepository extends Repository<ChampionSpellDTO, Long> {

	List<ChampionSpellDTO> findAll();

	List<ChampionSpellDTO> findByIdChampion(Long idChampion);

}
