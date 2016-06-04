package com.leagueofsummoners.model.dto;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import static com.leagueofsummoners.model.dao.tables.TableNames.*;

/**
 * Created by isi on 04/06/2016.
 */
@Data
@Entity(name = TABLE_SUMM_SPELLS_ENTITY)
@Table(name = TABLE_SUMM_SPELLS)
public class SummonerSpellDTO {

    @Id
    @Column(name = COLUMN_SUMMONER_SPELL_ID)
    private Long id;
    @Column(name = COLUMN_SUMMONER_SPELL_NAME)
    private String name;
    @Column(name = COLUMN_SUMMONER_SPELL_DESC)
    private String description;
    @Column(name = COLUMN_SUMMONER_SPELL_ICON)
    private String icon;
}
