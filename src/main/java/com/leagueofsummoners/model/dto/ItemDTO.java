package com.leagueofsummoners.model.dto;

import com.leagueofsummoners.model.dto.riotapi.RiotAPIMatch;
import com.leagueofsummoners.model.dto.riotapi.RiotApiParticipantInfo;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "item")
@Table(name = "items")
public class ItemDTO {

    @Id
    @Column(name = "ID_ITEM")
    private Long idItem;

    @Column(name = "ITEM_NAME_ES")
    private String itemNameEs;

    @Column(name = "ITEM_DESC_ES")
    private boolean itemDescEs;

    @Column(name = "ITEM_ICON")
    private String itemIcon;


}