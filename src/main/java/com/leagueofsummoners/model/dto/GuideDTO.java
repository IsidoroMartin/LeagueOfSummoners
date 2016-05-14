package com.leagueofsummoners.model.dto;

import javax.persistence.*;

import static com.leagueofsummoners.model.dao.tables.TableNames.*;


@Entity(name = "guides")
@Table(name = TABLE_GUIDES)
public class GuideDTO extends GenericDTO {

	@Id
	@GeneratedValue
	@Column(name = COLUMN_GUIDES_ID_GUIDE)
	private int idGuide;
	@Column(name = COLUMN_GUIDES_ID_USER)
	private int idUser;
	@Column(name = COLUMN_GUIDES_ID_CHAMPION)
	private int idChampion;
	@Column(name = COLUMN_GUIDES_TITLE)
	private String guideTitle;
	@Column(name = COLUMN_GUIDES_CONTENT)
	private String guideContent;
	@Column(name = COLUMN_GUIDES_LANG)
	private String guideLang;

	public GuideDTO() {
	}


	public int getIdGuide() {
		return idGuide;
	}

	public void setIdGuide(int idGuide) {
		this.idGuide = idGuide;
	}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public int getIdChampion() {
		return idChampion;
	}

	public void setIdChampion(int idChampion) {
		this.idChampion = idChampion;
	}

	public String getGuideTitle() {
		return guideTitle;
	}

	public void setGuideTitle(String guideTitle) {
		this.guideTitle = guideTitle;
	}

	public String getGuideContent() {
		return guideContent;
	}

	public void setGuideContent(String guideContent) {
		this.guideContent = guideContent;
	}

	public String getGuideLang() {
		return guideLang;
	}

	public void setGuideLang(String guideLang) {
		this.guideLang = guideLang;
	}
}
