package com.leagueofsummoners.model.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Este bean representa el objeto User de la base de datos
 * 
 * @author Juanjors
 */
@Entity(name = "user")
@Table(name = "users")
public class UserDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id_user")
	private Long idUser;

	@Column(nullable = false, name = "summoner_name", unique = true)
	private String summonerName;
	
	@Column(nullable = false, unique = true)
	private String username;
	@Column(nullable = false)
	private String password;
	@Column(nullable = false, unique = true)
	private String email;
	@Column(nullable = false)
	private String avatar;
	@Column(nullable = false)
	private String firma;
	@Column(nullable = false, name = "permission_level")
	private String permissionLevel;

	public UserDTO() {
	}
	
	public UserDTO(UserDTO user) {
		this.idUser = user.idUser;
		this.summonerName = user.summonerName;
		this.username = user.username;
		this.password = user.password;
		this.email = user.email;
		this.avatar = user.avatar;
		this.firma = user.firma;
		this.permissionLevel = user.permissionLevel;
	}
	
	public boolean isAdmin(){
		return (this.permissionLevel == "Admin");
	}

	@Override
	public String toString() {
		return this.username;
	}

	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}

	public String getSummonerName() {
		return summonerName;
	}

	public void setSummonerName(String summonerName) {
		this.summonerName = summonerName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getFirma() {
		return firma;
	}

	public void setFirma(String firma) {
		this.firma = firma;
	}

	public String getPermissionLevel() {
		return permissionLevel;
	}

	public void setPermissionLevel(String permissionLevel) {
		this.permissionLevel = permissionLevel;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public UserDTO(Long idUser, String summonerName, String username, String password, String email, String avatar,
			String firma, String permissionLevel) {
		super();
		this.idUser = idUser;
		this.summonerName = summonerName;
		this.username = username;
		this.password = password;
		this.email = email;
		this.avatar = avatar;
		this.firma = firma;
		this.permissionLevel = permissionLevel;
	}

}