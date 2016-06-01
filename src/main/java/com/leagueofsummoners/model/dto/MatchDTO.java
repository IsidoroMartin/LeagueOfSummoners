package com.leagueofsummoners.model.dto;

import static com.leagueofsummoners.model.dao.tables.TableNames.*;
import com.leagueofsummoners.model.dto.riotapi.RiotAPIMatch;
import com.leagueofsummoners.model.dto.riotapi.RiotApiParticipantInfo;
import lombok.Data;

import javax.persistence.*;


/*
Autores= Juan José Ramírez & Isidoro Martín
Fecha= Junio de 2016
Licencia=  gp130
Version= 1.0
Descripcion= Proyecto final desarrollo de aplicaciones web. League of Summoners es una aplicación
enfocada a los jugadores del popular juego League of Legends, usando esta aplicación podrán acceder
a guías, detalles sobre campeones e incluso sus últimas partidas.

Copyright (C) 2016 Juan José Ramírez & Isidoro Martín
This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.
This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.
You should have received a copy of the GNU General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

/**
 * Este bean representa la entidad matchs de la base de datos
 */

@Data
@Entity(name = TABLE_MATCHS_ENTITY)
@Table(name = TABLE_MATCHS)
public class MatchDTO {

    @Id
    @Column(name = COLUMN_MATCHS_ID_MATCH)
    private Long idMatch;

    @Column(name = COLUMN_MATCHS_ID_USER)
    private Long idUser;

    @Column(name = COLUMN_MATCHS_MATCH_DURATION)
    private Long matchDuration;

    @Column(name = COLUMN_MATCHS_MATCH_WIN)
    private boolean winner;

    @Column(name = COLUMN_MATCHS_ID_CHAMPION)
    private Long idChampion;

    @Transient
    private String summonerName;

    @Transient
    private String championName;

    @Column(name = COLUMN_MATCHS_KILLS)
    private Long kills;

    @Column(name = COLUMN_MATCHS_DEATHS)
    private Long deaths;

    @Column(name = COLUMN_MATCHS_ASSISTS)
    private Long assists;

    @Column(name = COLUMN_MATCHS_GOLD_EARNED)
    private Long goldEarned;

    @Column(name = COLUMN_MATCHS_ITEM0)
    private Long item0;

    @Column(name = COLUMN_MATCHS_ITEM1)
    private Long item1;

    @Column(name = COLUMN_MATCHS_ITEM2)
    private Long item2;

    @Column(name = COLUMN_MATCHS_ITEM3)
    private Long item3;

    @Column(name = COLUMN_MATCHS_ITEM4)
    private Long item4;

    @Column(name = COLUMN_MATCHS_ITEM5)
    private Long item5;

    @Column(name = COLUMN_MATCHS_ITEM6)
    private Long item6;

    @Transient
    private String stats;

    @Transient
    private String durationMins;

    @Transient
    private ItemDTO itemDto0;
    @Transient
    private ItemDTO itemDto1;
    @Transient
    private ItemDTO itemDto2;
    @Transient
    private ItemDTO itemDto3;
    @Transient
    private ItemDTO itemDto4;
    @Transient
    private ItemDTO itemDto5;
    @Transient
    private ItemDTO itemDto6;

    @Transient
    private String champMatchGlyphIcon;

    public String getChampMatchGlyphIcon(){
        return ((this.championName.contains(" ")) ? this.championName.replace(" ", "-") : this.championName).toLowerCase();
    }

    public String getStats() {
        return this.kills + "/" + this.deaths + "/" + this.assists;
    }

    public String getDurationMins() {
        return this.matchDuration / 60 + "''";
    }


    public MatchDTO parseToMatchDTO(RiotAPIMatch match, RiotApiParticipantInfo info) {
        this.idMatch = match.getMatchId();
        this.matchDuration = match.getMatchDuration();
        this.winner = info.getStats().isWinner();
        this.summonerName = info.getSummonerName();
        this.kills = info.getStats().getKills();
        this.deaths = info.getStats().getDeaths();
        this.assists = info.getStats().getAssists();
        this.goldEarned = info.getStats().getGoldEarned();
        this.item0 = info.getStats().getItem0();
        this.item1 = info.getStats().getItem1();
        this.item2 = info.getStats().getItem2();
        this.item3 = info.getStats().getItem3();
        this.item4 = info.getStats().getItem4();
        this.item5 = info.getStats().getItem5();
        this.item6 = info.getStats().getItem6();
        this.championName = info.getChampion().getChampionName();
        this.idChampion = info.getChampion().getIdChampion();
        return this;
    }

}