package com.leagueofsummoners.model.interfaces.persistence;


import com.leagueofsummoners.model.dao.tables.Querys;
import com.leagueofsummoners.model.dto.ChampionDTO;
import com.leagueofsummoners.model.dto.MatchDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;

@org.springframework.stereotype.Repository
public interface SummonerRepository extends Repository<MatchDTO, Long> {

	List<MatchDTO> findAll();

	List<MatchDTO> findByIdChampion(Long idChampion);

	List<MatchDTO> findTop10ByIdUser(Long idChampion);

	MatchDTO save(MatchDTO match);

    @Query(value = Querys.SUMMONERS_QUERY_GET_LAST_MATCH_ID, nativeQuery = true)
    Long idLastSavedMatch(@Param("idUser") Long userId);


}
