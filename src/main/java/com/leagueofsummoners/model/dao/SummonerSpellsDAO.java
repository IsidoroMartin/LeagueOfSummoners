package com.leagueofsummoners.model.dao;

import com.leagueofsummoners.model.dto.SummonerSpellDTO;
import com.leagueofsummoners.model.interfaces.persistence.SummonerSpellsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
 * Capa de acceso a los datos de las habilidades de invocador
 */
@Component
public class SummonerSpellsDAO {
    //Repositorio de los de las habilidades de invocador
    @Autowired
    private SummonerSpellsRepository summonerSpellRepository;

    /**
     * Obtiene la habilidad de invocador de la BBDD que contiene el id pasado por parámetro
     * @param id Integer
     * @return SummonerSpellDTO
     */
    public SummonerSpellDTO getSummonerSpell(Long id){
        return summonerSpellRepository.findById(id);
    }
}
