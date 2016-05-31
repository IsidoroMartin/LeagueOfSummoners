package com.leagueofsummoners.model.utils;

import com.leagueofsummoners.model.dto.MatchDTO;
import com.leagueofsummoners.model.dto.riotapi.RiotAPIMatch;
import com.leagueofsummoners.model.interfaces.persistence.ItemRepository;

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
 * Esta clase contiene métodos utiles utilizados por lasclases dao
 */
public class RepositoryUtils {

    public static int riotAPIGetNextMatchPosition(List<RiotAPIMatch> riotAPIMatches, long id){
        int position = 0;
        for (RiotAPIMatch riotAPIMatch : riotAPIMatches) {
            if(riotAPIMatch.getMatchId() == id)
                return position + 1;
            position++;
        }
        return 0;
    }

    public static void setItemsInMatchDTO(ItemRepository itemsRepository, MatchDTO matchDTO){
        matchDTO.setItemDto0(itemsRepository.findByIdItem(matchDTO.getItem0()));
        matchDTO.setItemDto1(itemsRepository.findByIdItem(matchDTO.getItem1()));
        matchDTO.setItemDto2(itemsRepository.findByIdItem(matchDTO.getItem2()));
        matchDTO.setItemDto3(itemsRepository.findByIdItem(matchDTO.getItem3()));
        matchDTO.setItemDto4(itemsRepository.findByIdItem(matchDTO.getItem4()));
        matchDTO.setItemDto5(itemsRepository.findByIdItem(matchDTO.getItem5()));
        matchDTO.setItemDto6(itemsRepository.findByIdItem(matchDTO.getItem6()));
    }
}