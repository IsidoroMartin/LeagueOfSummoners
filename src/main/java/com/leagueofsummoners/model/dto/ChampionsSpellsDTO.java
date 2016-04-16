package com.leagueofsummoners.model.dto;

import com.mysema.query.sql.Column;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity(name = "championSpells")
@Table(name = "CHAMPION_SPELLS")
public class ChampionsSpellsDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column("ID_SPELL")
	private Long idSpell;
	@Id
	@Column("ID_CHAMPION")
	private Long idChampion;

	@Column("SPELL_NAME_EN")
	private String spellNameEn;
	@Column("SPELL_NAME_ES")
	private String spellNameEs;

	@Column("SPELL_COST")
	private String spellCost;

	@Column("SPELL_CD")
	private String spellCd;

	@Column("SPELL_DESCRIPTION_EN")
	private String spellDescriptionEn;

	@Column("SPELL_DESCRIPTION_ES")
	private String spellDescriptionEs;

	public ChampionsSpellsDTO() {
	}

	public Long getIdSpell() {
		return idSpell;
	}

	public void setIdSpell(Long idSpell) {
		this.idSpell = idSpell;
	}

	public Long getIdChampion() {
		return idChampion;
	}

	public void setIdChampion(Long idChampion) {
		this.idChampion = idChampion;
	}

	public String getSpellNameEn() {
		return spellNameEn;
	}

	public void setSpellNameEn(String spellNameEn) {
		this.spellNameEn = spellNameEn;
	}

	public String getSpellNameEs() {
		return spellNameEs;
	}

	public void setSpellNameEs(String spellNameEs) {
		this.spellNameEs = spellNameEs;
	}

	public String getSpellCost() {
		return spellCost;
	}

	public void setSpellCost(String spellCost) {
		this.spellCost = spellCost;
	}

	public String getSpellCd() {
		return spellCd;
	}

	public void setSpellCd(String spellCd) {
		this.spellCd = spellCd;
	}

	public String getSpellDescriptionEn() {
		return spellDescriptionEn;
	}

	public void setSpellDescriptionEn(String spellDescriptionEn) {
		this.spellDescriptionEn = spellDescriptionEn;
	}

	public String getSpellDescriptionEs() {
		return spellDescriptionEs;
	}

	public void setSpellDescriptionEs(String spellDescriptionEs) {
		this.spellDescriptionEs = spellDescriptionEs;
	}
}
