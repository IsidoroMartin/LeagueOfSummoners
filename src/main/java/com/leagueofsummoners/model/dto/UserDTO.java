package com.leagueofsummoners.model.dto;

import static com.leagueofsummoners.model.dao.tables.TableNames.*;
import com.leagueofsummoners.model.utils.ValidationRegEXP;

import lombok.Data;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.io.Serializable;


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
 * Este bean representa la entidad users de la base de datos
 */
@Data
@Entity(name = TABLE_USERS_ENTITY)
@Table(name = TABLE_USERS)
public class UserDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = COLUMN_USERS_ID_USER)
	private Long idUser;

	@Column(nullable = false, name = COLUMN_USERS_SUMMONER_NAME, unique = true)
	@Length(min = 4, max = 20)
	private String summonerName;

	@Transient
	private long summonerID;

	@Column(nullable = false, unique = true)
	@Length(min = 4, max = 12)
	@Pattern(regexp = ValidationRegEXP.VALIDATE_NAME)
	private String username;

	@Column(name = COLUMN_USERS_MATCHES_PLAYED)
	private int matchesPlayed;

	@Column(nullable = false)
	@Length(min = 8, max = 300)
	private String password;

	@Email
	@Column(nullable = false, unique = true)
	private String email;

	@Column(nullable = false)
	private String avatar;
	@Column(nullable = true)
	private String firma;
	@Column(nullable = true, name = COLUMN_USERS_PERMISSION_LEVEL)
	private String permissionLevel;
	
	@Transient
	private int guidesNumber;

	public UserDTO() {
		this.permissionLevel = "User";
	}

	public boolean isAdmin() {
		return (this.permissionLevel.equals("Admin"));
	}
}