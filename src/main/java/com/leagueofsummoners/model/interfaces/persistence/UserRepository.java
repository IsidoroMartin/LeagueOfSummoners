package com.leagueofsummoners.model.interfaces.persistence;


import com.leagueofsummoners.model.dto.UserDTO;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.leagueofsummoners.model.dao.tables.Querys;

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
 * Este bean obtiene los datos de la entidad Users de la base de datos
 */

@org.springframework.stereotype.Repository
public interface UserRepository extends Repository<UserDTO, Long> {

	/**
	 * Obtiene una lista con todos los usuarios
	 * @return todos los usuarios
	 */
	List<UserDTO> findAll();
	
	/**
	 * Obtiene un usuario por username
	 * @param username
	 * @return EL usuario o null
	 */
	UserDTO findByUsernameIgnoringCase(String username);
		
	/**
	 * Obtiene un usuario por email
	 * @param email
	 * @return El usuario o null.
	 */
	UserDTO findByEmailIgnoringCase(String email);
	
	/**
	 * Obtiene un usuario por id
	 * @param idUser
	 * @return El usuario o null
	 */
	UserDTO findByIdUser(Long idUser);

	/**
	 * Guarda un usuario en la BD
	 * @param user
	 * @return El usuario o null si hay error.
	 */
	UserDTO save(UserDTO user);
	
	@Transactional
	@Modifying
	@Query(Querys.UPDATE_USER_PERMISSION_LEVEL)
	int updateUserPermission(String newPermissionLevel, String username);
	
	/**
	 * Borra un usuario por su nombre
	 */
	public void deleteByUsername(String username);
	
}
