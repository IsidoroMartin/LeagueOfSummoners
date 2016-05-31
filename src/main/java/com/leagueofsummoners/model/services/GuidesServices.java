package com.leagueofsummoners.model.services;

import com.leagueofsummoners.model.interfaces.services.IServicesGuides;
import com.leagueofsummoners.model.dto.GuideDTO;
import com.leagueofsummoners.model.dao.GuidesDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
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
@Service
public class GuidesServices implements IServicesGuides, Serializable {

	private static final long serialVersionUID = 1L;
	@Autowired
	private GuidesDAO guidesDAO;
	

	public GuidesServices() {
	}


	/**
	 * Obtiene todas las guías
	 * @return La lista  o null
	 */
	@Override
	public List<GuideDTO> findAll() {
		return this.guidesDAO.findAll();
	}


	/**
	 * Busca una guía por su título
	 * @param guideName
	 * @return la guía
	 */
	@Override
	public GuideDTO findByGuideTitleIgnoringCase(String guideName) {
		return this.guidesDAO.findByGuideTitleIgnoringCase(guideName);
	}

	/**
	 * Busca por ID guía
	 * @param idGuide
	 * @return la guía o null
	 */
	@Override
	public GuideDTO findByIdGuide(Long idGuide) {
		return this.guidesDAO.findByIdGuide(idGuide);
	}

	/**
	 * Busca la guía por id campeón
	 * @param idChampion
	 * @return la guía o null
	 */
	@Override
	public List<GuideDTO> findByIdChampion(Long idChampion) {
		return this.guidesDAO.findByIdChampion(idChampion);
	}

	/**
	 * Busca las guías por idUser
	 * @param idUser
	 * @return la guía o null
	 */
	@Override
	public List<GuideDTO> findByIdUser(Long idChampion) {
		return this.guidesDAO.findByIdUser(idChampion);
	}


	/*
	 * Elimina la guía por id y por username
	 */
	@Override
	public void deleteByIdGuide(Long idGuide, Long idUsername) {
		this.guidesDAO.deleteByIdGuide(idGuide,idUsername);
	}


}
