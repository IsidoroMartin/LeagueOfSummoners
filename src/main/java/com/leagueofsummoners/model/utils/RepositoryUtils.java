package com.leagueofsummoners.model.utils;

import com.leagueofsummoners.model.dto.ItemDTO;
import com.leagueofsummoners.model.dto.MatchDTO;
import com.leagueofsummoners.model.dto.riotapi.RiotAPIMatch;
import com.leagueofsummoners.model.dto.riotapi.RiotAPIMatches;
import com.leagueofsummoners.model.interfaces.persistence.ItemRepository;

import java.util.List;

/**
 * Created by juanj on 16/05/2016.
 */
public class RepositoryUtils {

    public static int riotAPIGetNextMatchPosition(List<RiotAPIMatch> riotAPIMatches, long id){
        int position = 0;
        for (RiotAPIMatch riotAPIMatch : riotAPIMatches) {
            if(riotAPIMatch.getMatchId() == id)
                return position + 1;
            position++;
        }
        return 0;
    }

    public static void setItemsInMatchDTO(ItemRepository itemsRepository, MatchDTO matchDTO){
        matchDTO.setItemDto0(itemsRepository.findByIdItem(matchDTO.getItem0()));
        matchDTO.setItemDto1(itemsRepository.findByIdItem(matchDTO.getItem1()));
        matchDTO.setItemDto2(itemsRepository.findByIdItem(matchDTO.getItem2()));
        matchDTO.setItemDto3(itemsRepository.findByIdItem(matchDTO.getItem3()));
        matchDTO.setItemDto4(itemsRepository.findByIdItem(matchDTO.getItem4()));
        matchDTO.setItemDto5(itemsRepository.findByIdItem(matchDTO.getItem5()));
        matchDTO.setItemDto6(itemsRepository.findByIdItem(matchDTO.getItem6()));
    }
}