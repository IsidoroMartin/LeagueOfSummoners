package com.leagueofsummoners.model.interfaces.persistence;


import com.leagueofsummoners.model.dao.tables.Querys;
import com.leagueofsummoners.model.dto.MatchDTO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/*
Autores= Juan José Ramírez & Isidoro Martín
Fecha= Junio de 2016
Licencia=  gp130
Version= 1.0
Descripcion= Proyecto final desarrollo de aplicaciones web. League of Summoners es una aplicación
enfocada a los jugadores del popular juego League of Legends, usando esta aplicación podrán acceder
a guías, detalles sobre campeones e incluso sus últimas partidas.

Copyright (C) 2016 Juan José Ramírez & Isidoro Martín
This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.
This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.
You should have received a copy of the GNU General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

/**
 * Este bean obtiene los datos de la entidad Matchs de la base de datos
 */

@org.springframework.stereotype.Repository
public interface SummonerRepository extends Repository<MatchDTO, Long> {

	/**
	 * Obtiene todas las partidas almacenadas en la BD
	 * @return
	 */
	List<MatchDTO> findAll();
	
	
	/**
	 * Obtiene todas las partidas de un determinado campeón
	 * @param idChampion
	 * @return Las partidas de ese campeón
	 */
	List<MatchDTO> findByIdChampion(Long idChampion);
	
	/**
	 * Obtiene las primeras 10 partidas de el usuario parametrizado
	 * @param idUser
	 * @return Lista de partidas
	 */
	List<MatchDTO> findTop10ByIdUser(Long idUser);

	/**
	 * Guarda una partida en la BD
	 * @param match
	 * @return La partida guardada o null
	 */
	MatchDTO save(MatchDTO match);
	
	
	/**
	 * Obtiene el id de la última partida almacenada en la BD.
	 * @param userId
	 * @return La partida o null.
	 */
	//Query construida: SELECT MAX(e.ID_MATCH) FROM Matchs e WHERE ID_USER = :idUser
    @Query(value = Querys.SUMMONERS_QUERY_GET_LAST_MATCH_ID, nativeQuery = true)
    Long idLastSavedMatch(@Param("idUser") Long userId);


}
