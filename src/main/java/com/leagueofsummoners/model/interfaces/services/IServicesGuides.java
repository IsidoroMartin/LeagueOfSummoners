package com.leagueofsummoners.model.interfaces.services;

import java.util.List;

import com.leagueofsummoners.model.dto.GuideDTO;

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
 * Servicio guías, esta es la capa intermedia entre los controlladores y los 
 * objetos DAO.
 */
public interface IServicesGuides {
	/**
	 * Obtiene todas las guías
	 * @return La lista  o null
	 */
	List<GuideDTO> findAll();

	/**
	 * Busca una guía por su título
	 * @param guideName
	 * @return la guía
	 */
	GuideDTO findByGuideTitleIgnoringCase(String guideName);

	/**
	 * Busca por ID guía
	 * @param idGuide
	 * @return la guía o null
	 */
	GuideDTO findByIdGuide(Long idGuide, boolean full);

	/**
	 * Busca la guía por id campeón
	 * @param idChampion
	 * @return la guía o null
	 */
	List<GuideDTO> findByIdChampion(Long idChampion);

	/**
	 * Busca las guías por idUser
	 * @param idUser
	 * @return la guía o null
	 */
	List<GuideDTO> findByIdUser(Long idUser);
	
	/*
	 * Elimina la guía por id y por username
	 */
	void deleteByIdGuide(Long idGuide, Long idUsername);
}
