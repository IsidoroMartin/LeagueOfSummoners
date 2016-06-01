package com.leagueofsummoners.model.interfaces.services;

import com.leagueofsummoners.model.dto.ChampionDTO;

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
 * Servicio campeones, esta es la capa intermedia entre los controlladores y los 
 * objetos DAO.
 */
public interface IServicesChampions {

    /**
     * Devuelve una lista de todos los campeones
     * @return Lista con los campeones
     */
    List<ChampionDTO> getChampionList(boolean fullInfo);
    
    /**
     * Devuelve un String[] con los nombres de los campeones
     * @return String[] con elnombre de los campeones
     */
    String[] getStringChampionList();

    /**
     * Obtiene una lista con la rotación de estasemana
     * @return La lista o null
     */
    List<ChampionDTO> getChampionRotation();

    /**
     * Encuentra a un usuario por nombre
     *
     * @param championName
     * @return El usuario con la nombre o null.
     */
    ChampionDTO findByChampionName(String championName);
    
    /**
     * Encuentra un campeón por su ID
     * @param idChampion
     * @return el campeón
     */
//    ChampionDTO findByIdChampion(Long idChampion);

    /**
     * Obtiene los nombres de la lista de iconos de los campeones
     * @return la lista de nombres o null
     */
    List<String> getChampionsIconsNamesList();

}
