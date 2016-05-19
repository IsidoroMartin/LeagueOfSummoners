package com.leagueofsummoners.model.dto;

import com.mysema.query.sql.Column;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity(name = "championSpells")
@Table(name = "CHAMPION_SPELLS")
public class ChampionsSpellsDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Column("ID_SPELL")
	private Long idSpell;
	@Id
	@Column("ID_CHAMPION")
	private Long idChampion;

	@Column("SPELL_NAME_ES")
	private String spellNameEs;

	@Column("SPELL_COST")
	private String spellCost;

	@Column("SPELL_CD")
	private String spellCd;

	@Column("SPELL_DESCRIPTION_ES")
	private String spellDescriptionEs;

}
