package com.leagueofsummoners.model.services;

import com.leagueofsummoners.model.dao.SummonerDAO;
import com.leagueofsummoners.model.dto.MatchDTO;
import com.leagueofsummoners.model.dto.UserDTO;
import com.leagueofsummoners.model.interfaces.services.IServicesSummoner;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
 * Esta clase contiene los servicios del usuario. 
 * Implementa su interfaz correspondiente donde está la definición de 
 * los métodos y su explicación. (Comentarios)
 */
@Slf4j
@Service
public class SummonerServices implements IServicesSummoner {

	@Autowired
	private SummonerDAO summonerDAO;

	/**
	 * Obtiene una lista de partidas de manera asincrona
	 * @param user
	 * @param nMatches
	 * @return Lista de partidas
	 * @throws Exception
	 */
	@Override
	public List<MatchDTO> getLatestMatchesAsync(UserDTO userlogged, int nMatches) throws Exception {
		List<MatchDTO> lista = null;
		try {
			lista = this.summonerDAO.getLatestMatchesFromRiotAsync(userlogged, nMatches).get();
			lista = this.summonerDAO.getLatestMatchesFromDB(userlogged);
		} catch (Exception e) {
			lista = this.summonerDAO.getLatestMatchesFromDB(userlogged);
			log.error("Error en GetLatestMatches " + e.getMessage());
		}
		return lista;
	}

    /**
     * Obtiene una lista de partidas de manera sincrona
     * @param user
     * @param nMatches
     * @return La lista de partidas
     * @throws Exception
     */
	@Override
	public List<MatchDTO> getLatestMatchesFromDB(UserDTO userlogged) {
		List<MatchDTO> lista = null;
		lista = this.summonerDAO.getLatestMatchesFromDB(userlogged);
		return lista;
	}


    /**
     * Obtiene las últimas partidas de un usuario de la BD
     * @param user
     * @return la lista de partidaas
     */
	@Override
	public List<MatchDTO> getLatestMatchesSync(UserDTO userlogged, int nMatches) throws Exception {
		List<MatchDTO> lista = null;
		try {
			lista = this.summonerDAO.getLatestMatchesFromRiot(userlogged, nMatches).get();
			lista = this.summonerDAO.getLatestMatchesFromDB(userlogged);
		} catch (Exception e) {
			lista = this.summonerDAO.getLatestMatchesFromDB(userlogged);
			log.error("Error en GetLatestMatches " + e.getMessage());
		}
		return lista;
	}
}
