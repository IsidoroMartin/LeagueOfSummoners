package com.leagueofsummoners.model.dto;

import com.leagueofsummoners.model.enums.LanguageSelected;

public class GuideDTO {

	private int id_guide;
	private int id_user;
	private int id_champion;
	private String guideTitle;
	private String guideContent;
	private LanguageSelected languageSelected;

	public GuideDTO() {
	}

	public GuideDTO(int id_guide, int id_user, int id_champion, String guideTitle, String guideContent,
			LanguageSelected languageSelected) {
		this.id_guide = id_guide;
		this.id_user = id_user;
		this.id_champion = id_champion;
		this.guideTitle = guideTitle;
		this.guideContent = guideContent;
		this.languageSelected = languageSelected;
	}

	public int getId_guide() {
		return id_guide;
	}

	public void setId_guide(int id_guide) {
		this.id_guide = id_guide;
	}

	public int getId_user() {
		return id_user;
	}

	public void setId_user(int id_user) {
		this.id_user = id_user;
	}

	public int getId_champion() {
		return id_champion;
	}

	public void setId_champion(int id_champion) {
		this.id_champion = id_champion;
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

	public LanguageSelected getLanguageSelected() {
		return languageSelected;
	}

	public void setLanguageSelected(LanguageSelected languageSelected) {
		this.languageSelected = languageSelected;
	};

}
