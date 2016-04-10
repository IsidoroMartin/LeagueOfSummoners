package com.leagueofsummoners.model.dto;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Este bean representa el objeto Champion de la base de datos
 * 
 * @author Juanjors
 */
@Entity(name = "champion")
@Table(name = "CHAMPIONS")
public class ChampionDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID_CHAMPION")
	private Long idChampion;

	@Column(nullable = false, name = "CHAMPION_NAME")
	private String championName;

	@Column(nullable = false, name = "CHAMPION_TITLE_ES")
	private String championTitleES;

	@Column(nullable = false, name = "CHAMPION_TITLE_EN")
	private String championTitleEN;

	@Column(name = "CHAMPION_ICON_NAME")
	private String championIconName;

	@Column(nullable = false, name = "ID_PASSIVE")
	private Long idPassive;

	@Column(nullable = false, name = "CHAMPION_LORE_ES")
	private String championLoreES;

	@Column(nullable = false, name = "CHAMPION_LORE_EN")
	private String championLoreEN;

	@Column(nullable = false, name = "CHAMPION_INFO")
	private String championInfo;

	@Transient 
	private List<ChampionsSpellsDTO> spellsList;

	@Transient 
	private ChampionsPassivesDTO passive;

	public ChampionDTO() {
	}

	public Long getIdChampion() {
		return idChampion;
	}

	public void setIdChampion(Long idChampion) {
		this.idChampion = idChampion;
	}

	public String getChampionName() {
		return championName;
	}

	public void setChampionName(String championName) {
		this.championName = championName;
	}

	public String getChampionIconName() {
		return championIconName;
	}

	public void setChampionIconName(String championIconName) {
		this.championIconName = championIconName;
	}

	public Long getIdPassive() {
		return idPassive;
	}

	public void setIdPassive(Long idPassive) {
		this.idPassive = idPassive;
	}

	public String getChampionTitleES() {
		return championTitleES;
	}

	public void setChampionTitleES(String championTitleES) {
		this.championTitleES = championTitleES;
	}

	public String getChampionTitleEN() {
		return championTitleEN;
	}

	public void setChampionTitleEN(String championTitleEN) {
		this.championTitleEN = championTitleEN;
	}

	public String getChampionLoreES() {
		return championLoreES;
	}

	public void setChampionLoreES(String championLoreES) {
		this.championLoreES = championLoreES;
	}

	public String getChampionLoreEN() {
		return championLoreEN;
	}

	public void setChampionLoreEN(String championLoreEN) {
		this.championLoreEN = championLoreEN;
	}

	public String getChampionInfo() {
		return championInfo;
	}

	public void setChampionInfo(String championInfo) {
		this.championInfo = championInfo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<ChampionsSpellsDTO> getSpellsList() {
		return spellsList;
	}

	public void setSpellsList(List<ChampionsSpellsDTO> spellsList) {
		this.spellsList = spellsList;
	}

	public ChampionsPassivesDTO getPassive() {
		return passive;
	}

	public void setPassive(ChampionsPassivesDTO passive) {
		this.passive = passive;
	}

}