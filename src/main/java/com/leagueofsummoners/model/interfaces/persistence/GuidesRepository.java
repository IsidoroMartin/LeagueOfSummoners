package com.leagueofsummoners.model.interfaces.persistence;


import com.leagueofsummoners.model.dto.GuideDTO;
import org.springframework.data.repository.Repository;

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
 * Este bean obtiene los datos de la entidad Guides de la base de datos
 */
@org.springframework.stereotype.Repository
public interface GuidesRepository extends Repository<GuideDTO, Long> {

	/**
	 * Obtiene una lista con todas las guías
	 * @return Lista de guías
	 */
	List<GuideDTO> findAll();

	/**
	 * Busca una guía por el título
	 * @param guideName
	 * @return la guía con el título parametrizado
	 */
	GuideDTO findByGuideTitleIgnoringCase(String guideName);

	/**
	 * Busca una guía por ID
	 * @param idGuide
	 * @return Una guía con el id parametrizado
	 */
	GuideDTO findByIdGuide(Long idGuide);

	/**
	 * Busca una guía por id campeón
	 * @param idChampion
	 * @return Todas las guías de ese campeón
	 */
	List<GuideDTO> findByIdChampion(Long idChampion);

	/**
	 * Busca las guías por el id del usuario
	 * @param idChampion
	 * @return La guías de ese usuario 
	 */
	List<GuideDTO> findByIdUser(Long idChampion);
	
	/**
	 * Borra una guía por id y por username (Para controlar que el usuario es el que dice ser).
	 * @param idGuide
	 * @param idUsername
	 */
	public void deleteByIdGuideAndIdUser(Long idGuide, Long idUsername);

}
