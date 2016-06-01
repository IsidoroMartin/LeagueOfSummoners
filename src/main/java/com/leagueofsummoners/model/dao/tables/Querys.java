package com.leagueofsummoners.model.dao.tables;
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
 * Esta clase contiene todas las consultas que no se pueden hacer a través de las querymethods
 */
public class Querys {

    public static final String SUMMONERS_QUERY_GET_LAST_MATCH_ID = "SELECT MAX(e.ID_MATCH) FROM Matchs e WHERE ID_USER = :idUser";
    public static final String UPDATE_USER_PERMISSION_LEVEL = "update user u set u.permissionLevel = ?1 where u.username = ?2";
    

}
