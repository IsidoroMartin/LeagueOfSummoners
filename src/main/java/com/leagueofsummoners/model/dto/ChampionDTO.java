package com.leagueofsummoners.model.dto;




import com.robrua.orianna.type.core.staticdata.Champion;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

import static com.leagueofsummoners.model.dao.tables.TableNames.*;

/**
 * Este bean representa el objeto Champion de la base de datos
 * @author Juanjors
 */
@Data
@Entity(name = "champion")
@Table(name = TABLE_CHAMPIONS)
public class ChampionDTO extends GenericDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = COLUMN_CHAMPIONS_ID_CHAMPION)
	private Long idChampion;

	@Column(nullable = false, name = COLUMN_CHAMPIONS_CHAMPION_NAME)
	private String championName;

	@Column(nullable = false, name = COLUMN_CHAMPIONS_CHAMPION_TITLE_ES)
	private String championTitleES;

	@Column(nullable = false, name = COLUMN_CHAMPIONS_CHAMPION_TITLE_EN)
	private String championTitleEN;

	@Column(name = COLUMN_CHAMPIONS_CHAMPION_ICON_NAME)
	private String championIconName;

	@Column(nullable = false, name = COLUMN_CHAMPIONS_ID_PASSIVE)
	private Long idPassive;

	@Column(nullable = false, name = COLUMN_CHAMPIONS_CHAMPION_LORE_ES)
	private String championLoreES;

	@Column(nullable = false, name = COLUMN_CHAMPIONS_CHAMPION_LORE_EN)
	private String championLoreEN;

	@Column(nullable = false, name = COLUMN_CHAMPIONS_CHAMPION_INFO)
	private String championInfo;

	@Transient
	private List<ChampionsSpellsDTO> spellsList;

	@Transient
	private ChampionsPassivesDTO passive;


	public static ChampionDTO buildBasicChampionDTO(Champion champion){
		ChampionDTO champ = new ChampionDTO();
		champ.idChampion = champion.getID();
		champ.championName = champion.getName();
		champ.championIconName= "http://ddragon.leagueoflegends.com/cdn/6.6.1/img/champion/"+champion.getImage().getFull();
		return champ;
	}
}