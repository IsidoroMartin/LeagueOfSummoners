package com.leagueofsummoners.model.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.leagueofsummoners.model.dto.GuideDTO;
import com.leagueofsummoners.model.dto.GuideItemsDTO;
import com.leagueofsummoners.model.dto.ItemDTO;
import com.leagueofsummoners.model.interfaces.persistence.ChampionRepository;
import com.leagueofsummoners.model.interfaces.persistence.GuidesItemsRepository;
import com.leagueofsummoners.model.interfaces.persistence.GuidesRepository;
import com.leagueofsummoners.model.interfaces.persistence.ItemRepository;
import com.leagueofsummoners.model.interfaces.persistence.SummonerSpellsRepository;
import com.leagueofsummoners.model.interfaces.persistence.UserRepository;

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
 * Capa de acceso a los datos de las guías.
 */
@Component
public class GuidesDAO {

	// Repositorio de campeones
	@Autowired
	private ChampionRepository championRepository;

	// Repositorio de usuarios
	@Autowired
	private UserRepository userRepository;

	// Repositorio de guías
	@Autowired
	private GuidesRepository guidesRepository;
	// Repositorio de guideItems
	@Autowired
	private GuidesItemsRepository guidesItemsRepository;
	// Repositorio de Items
	@Autowired
	private ItemRepository itemsRepository;
	// Repositorio de los de las habilidades de invocador
	@Autowired
	private SummonerSpellsRepository summonerSpellRepository;

	/**
	 * Obtiene todas las guías de la BD
	 *
	 * @return List<GuideDTO> lista de guías
	 */
	public List<GuideDTO> findAll() {
		List<GuideDTO> guides = this.guidesRepository.findAll();
		for (GuideDTO guide : guides) {
			guide.setChampion(this.championRepository.findByIdChampion(guide.getIdChampion()));
			guide.setUser(this.userRepository.findByIdUser(guide.getIdUser()));
		}
		return guides;
	}

	/**
	 * Busca una guía por el titulo
	 *
	 * @param guideName
	 * @return La guía con el titulo coincidente al string parametrizado
	 */
	public GuideDTO findByGuideTitleIgnoringCase(String guideName) {
		return this.guidesRepository.findByGuideTitleIgnoringCase(guideName);
	}

	/**
	 * Busca una guía por ID
	 *
	 * @param idGuide
	 * @return la guía con el id parametrizada
	 */
	public GuideDTO findByIdGuide(Long idGuide, boolean full) {
		GuideDTO guide = this.guidesRepository.findByIdGuide(idGuide);
		if (full) {
			guide.setChampion(this.championRepository.findByIdChampion(guide.getIdChampion()));
			guide.setUser(userRepository.findByIdUser(guide.getIdUser()));
			guide.setSummonerSpellD(summonerSpellRepository.findById(guide.getId_summ_spell_d()));
			guide.setSummonerSpellF(summonerSpellRepository.findById(guide.getId_summ_spell_f()));
			List<GuideItemsDTO> guidesItemsList = guidesItemsRepository.findByIdGuide(guide.getIdGuide());
			List<ItemDTO> guideItemsDTO = new ArrayList<ItemDTO>();
			for (GuideItemsDTO guideItem : guidesItemsList) {
				guideItemsDTO.add(itemsRepository.findByIdItem(guideItem.getIdItem()));
			}
			guide.setGuideItems(guideItemsDTO);
		}
		return guide;
	}

	/**
	 * Busca las guías por id de cmapeón
	 *
	 * @param idChampion
	 * @return las guías con el id parametrizado.
	 */
	public List<GuideDTO> findByIdChampion(Long idChampion) {
		List<GuideDTO> guides = this.findByIdChampion(idChampion);

		for (GuideDTO guide : guides) {
			guide.setChampion(this.championRepository.findByIdChampion(guide.getIdChampion()));
		}

		return guides;
	}

	/**
	 * Obtien las guías por el idUsuario parametrizado (Autor)
	 *
	 * @param idUser
	 * @return Las guías con ese idUser
	 */
	public List<GuideDTO> findByIdUser(Long idUser) {
		List<GuideDTO> guides = this.guidesRepository.findByIdUser(idUser);
		for (GuideDTO guide : guides) {
			guide.setChampion(this.championRepository.findByIdChampion(guide.getIdChampion()));
		}
		return guides;
	}

	/**
	 * Elimina una guía por su ID y por su username.
	 *
	 * @param idGuide
	 * @param idUsername
	 */
	@Transactional
	public void deleteByIdGuide(Long idGuide, Long idUsername) {
		this.guidesRepository.deleteByIdGuideAndIdUser(idGuide, idUsername);
	}

}
