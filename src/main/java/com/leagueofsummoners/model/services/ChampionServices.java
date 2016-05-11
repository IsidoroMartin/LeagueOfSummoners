package com.leagueofsummoners.model.services;

import com.leagueofsummoners.model.dto.MatchDTO;
import com.leagueofsummoners.model.dto.UserDTO;
import com.leagueofsummoners.model.interfaces.services.IServicesChampions;
import com.leagueofsummoners.model.dto.ChampionDTO;
import com.leagueofsummoners.model.persistence.dao.ChampionDAO;

import com.robrua.orianna.api.core.RiotAPI;
import com.robrua.orianna.type.core.match.Participant;
import com.robrua.orianna.type.core.match.ParticipantStats;
import com.robrua.orianna.type.core.matchlist.MatchReference;
import com.robrua.orianna.type.core.staticdata.Champion;
import com.robrua.orianna.type.core.summoner.Summoner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChampionServices implements IServicesChampions {

    @Autowired
    private ChampionDAO championDAO;

    public ChampionServices() {
    }

    @Override
    public List<ChampionDTO> getChampionList() {
        return this.championDAO.getChampionList();
    }

    public List<ChampionDTO> getChampionRotation() {
        return this.championDAO.getChampionRotation();
    }

    @Override
    public ChampionDTO findByChampionName(String championName) {
        return this.championDAO.getChampionByName(championName);
    }

    @Override
    public ChampionDTO findByIdChampion(Long idChampion) {
        // TODO Auto-generated method stub
        return null;
    }


    @Override
    public List<String> getChampionsIconsNamesList() {
        List<ChampionDTO> championsList = this.championDAO.getChampionList();
        List<String> championNames = new ArrayList<>();
        for (ChampionDTO championDTO : championsList) {
            championNames.add(championDTO.getChampionIconName());
        }
        return championNames;
    }

    @Override
    public List<MatchDTO> getLatestMatches(UserDTO userlogged) {
        Summoner summ = RiotAPI.getSummonerByName(userlogged.getSummonerName());
        List<MatchReference> matches = summ.getMatchList(10, 0);
        List<MatchDTO> matchesDTO = new ArrayList<>();
        for (int i = 0; i < matches.size(); i++) {
            try {
                MatchDTO match = new MatchDTO();
                Champion champ = matches.get(i).getChampion();
                match.setIconUri(champ.getImage().getFull());
                match.setMatchDuration((int) matches.get(i).getMatch().getDuration() / 60);
                Participant participant = matches.get(i).getMatch().getParticipants().get(0);
                ParticipantStats stats = participant.getStats();
                match.setDeaths((int) stats.getDeaths());
                match.setAssistences((int) stats.getAssists());
                match.setKills((int) stats.getKills());
                match.setLanePlayed(matches.get(i).getLane().name());
                match.setChampionName(champ.getName());
                matchesDTO.add(match);
            } catch (Exception e) {
                System.out.println("uff error" + i);
            }
        }
        return matchesDTO;
    }

}
