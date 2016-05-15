package com.leagueofsummoners.model.interfaces.persistence;


import com.leagueofsummoners.model.dto.ChampionDTO;
import com.leagueofsummoners.model.dto.MatchDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import java.util.List;

@org.springframework.stereotype.Repository
public interface SummonerRepository extends Repository<MatchDTO, Long> {

	List<MatchDTO> findAll();

	List<MatchDTO> findByIdChampion(Long idChampion);

	List<MatchDTO> findByIdUser(Long idChampion);


}
