package com.leagueofsummoners.model.dto;

import com.leagueofsummoners.model.custom.validators.annotations.ValidateSummonerNameExists;
import com.leagueofsummoners.model.utils.ValidationRegEXP;

import lombok.Data;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * Este bean representa el objeto User de la base de datos
 *
 * @author Juanjors
 */
@Data
@Entity(name = "user")
@Table(name = "users")
public class UserDTO extends GenericDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "id_user")
	private Long idUser;

	@Column(nullable = false, name = "summoner_name", unique = true)
	@Length(min = 4, max = 20)
	@ValidateSummonerNameExists
	private String summonerName;

	@Transient
	private long summonerID;

	@Column(nullable = false, unique = true)
	@Length(min = 4, max = 12)
	@Pattern(regexp = ValidationRegEXP.VALIDATE_NAME)
	private String username;

	@Column(name = "MATCHES_PLAYED")
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
	@Column(nullable = true, name = "permission_level")
	private String permissionLevel;

	public UserDTO() {
		this.permissionLevel = "User";
	}

	public boolean isAdmin() {
		return (this.permissionLevel.equals("Admin"));
	}
}