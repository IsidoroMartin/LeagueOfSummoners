package com.leagueofsummoners.model.dto;

import com.mysema.query.sql.Column;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity(name = "championPassives")
@Table(name = "CHAMPIONS_PASSIVES")
public class ChampionPassiveDTO extends GenericDTO implements Serializable {

	private static final long serialVersionUID = -8937457657957212292L;
	
	@Id
	@Column("ID_PASSIVE")
	private Long idPassive;
	
//	@Column("PASSIVE_NAME")
//	private String passiveNameES;
	
	@Column("PASSIVE_DESCRIPTION_ES")
	private String passiveDescriptionEs;
	@Column("PASSIVE_ICON")
	private String passiveIcon;

	
}
