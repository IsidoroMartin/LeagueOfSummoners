package com.leagueofsummoners.model.dto;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.mysema.query.sql.Column;

@Entity(name = "championPassives")
@Table(name = "CHAMPION_PASSIVES")
public class ChampionsPassivesDTO implements Serializable {

	private static final long serialVersionUID = -8937457657957212292L;
	
	@Id
	@Column("ID_PASSIVE")
	private Long idPassive;
	@Column("PASSIVE_NAME_EN")
	private String passiveNameEn;
	@Column("PASSIVE_NAME_ES")
	private String passiveNameES;
	@Column("PASSIVE_DESCRIPTION_EN")
	private String passiveDescriptionEn;
	@Column("PASSIVE_DESCRIPTION_ES")
	private String passiveDescriptionEs;
	@Column("PASSIVE_ICON")
	private String passiveIcon;

	public ChampionsPassivesDTO() {
	}

	public Long getIdPassive() {
		return idPassive;
	}

	public void setIdPassive(Long idPassive) {
		this.idPassive = idPassive;
	}

	public String getPassiveNameEn() {
		return passiveNameEn;
	}

	public void setPassiveNameEn(String passiveNameEn) {
		this.passiveNameEn = passiveNameEn;
	}

	public String getPassiveNameES() {
		return passiveNameES;
	}

	public void setPassiveNameES(String passiveNameES) {
		this.passiveNameES = passiveNameES;
	}

	public String getPassiveDescriptionEn() {
		return passiveDescriptionEn;
	}

	public void setPassiveDescriptionEn(String passiveDescriptionEn) {
		this.passiveDescriptionEn = passiveDescriptionEn;
	}

	public String getPassiveDescriptionEs() {
		return passiveDescriptionEs;
	}

	public void setPassiveDescriptionEs(String passiveDescriptionEs) {
		this.passiveDescriptionEs = passiveDescriptionEs;
	}

	public String getPassiveIcon() {
		return passiveIcon;
	}

	public void setPassiveIcon(String passiveIcon) {
		this.passiveIcon = passiveIcon;
	}
}
