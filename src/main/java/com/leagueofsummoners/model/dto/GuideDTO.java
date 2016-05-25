package com.leagueofsummoners.model.dto;

import javax.persistence.*;

import lombok.Data;

import static com.leagueofsummoners.model.dao.tables.TableNames.*;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

@Data
@Entity(name = "guides")
@Table(name = TABLE_GUIDES)
public class GuideDTO implements Comparator<GuideDTO> {

	@Id
	@GeneratedValue
	@Column(name = COLUMN_GUIDES_ID_GUIDE)
	private Long idGuide;
	@Column(name = COLUMN_GUIDES_ID_USER)
	private Long idUser;
	@Column(name = COLUMN_GUIDES_ID_CHAMPION)
	private Long idChampion;
	@Column(name = COLUMN_GUIDES_TITLE)
	private String guideTitle;
	@Column(name = COLUMN_GUIDES_CONTENT)
	private String guideContent;
	@Column(name = COLUMN_GUIDES_DATE)
	private Date guideDate;
	
	@Column(name = COLUMN_GUIDES_VISITS)
	private int visitas;
	
	@Transient
	private ChampionDTO champion;
	
	@Transient
	private UserDTO user;
	
	@Transient
	private String formattedDay;

	
	public String getFormattedDay(){
		return new SimpleDateFormat("dd/MM/yyyy").format(this.guideDate);
	}

	@Override
	public String toString() {
		return "Guía de " + this.champion.getChampionName() + " by you. - " + this.visitas + " visitas";
	}


	@Override
	public int compare(GuideDTO o1, GuideDTO o2) {
		return o2.visitas - o1.visitas;
	}

}
