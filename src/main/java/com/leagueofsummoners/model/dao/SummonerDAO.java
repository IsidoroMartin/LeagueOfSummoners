package com.leagueofsummoners.model.dao;


import com.leagueofsummoners.LeagueofsummonersApplication;
import com.leagueofsummoners.SessionAtts;
import com.leagueofsummoners.model.dto.MatchDTO;
import com.leagueofsummoners.model.dto.UserDTO;
import com.leagueofsummoners.model.dto.riotapi.*;
import com.leagueofsummoners.model.interfaces.persistence.ChampionRepository;
import com.leagueofsummoners.model.interfaces.persistence.SummonerRepository;
import com.leagueofsummoners.model.utils.LeagueAccessAPI;
import com.robrua.orianna.api.core.RiotAPI;
import com.robrua.orianna.type.core.summoner.Summoner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Component
public class SummonerDAO {

    @Autowired
    private ChampionRepository championRepository;

    @Autowired
    private SummonerRepository summonerRepository;

    private RestTemplate rest;

    public SummonerDAO() {
        this.rest = new RestTemplate();
    }

    public Summoner getSummonerData(String summonerName) {
        Summoner sum = null;
        try {
            sum = RiotAPI.getSummonerByName(summonerName);
        } catch (Exception e) {
            LeagueofsummonersApplication.LOGGER.error("Error obteniendo el summonername, probablemente no exista. " + e.getMessage());
        }
        return sum;
    }


    public List<MatchDTO> getLatestMatches(long summonerId, String summonerName, int matchesNumber) {
        List<RiotAPIMatch> matchesAPI = this.rest.getForObject(LeagueAccessAPI.RIOT_API_OBTAIN_SUMMONER_MATCHES, RiotAPIMatches.class, summonerId).getMatches();
        List<MatchDTO> matchList = null;
        int matchSize = matchesAPI.size();
        if (matchSize > 0) {
            matchList = new ArrayList<>();
            //Si la i es menor que los 'match' pedidos y además los match pedidos son menos que los match recibidos (Para evitar salir del array)
            for (int i = 0; i < matchesNumber && matchesNumber < matchSize; i++) {
                MatchDTO matchdto = new MatchDTO();
                RiotAPIMatch match = this.rest.getForObject(LeagueAccessAPI.RIOT_API_OBTAIN_INFO_MATCHES, RiotAPIMatch.class, matchesAPI.get(i).getMatchId());
                RiotApiParticipantInfo info = this.filterParticipantsBySummonerName(summonerName, match.getParticipantIdentities(), match.getParticipants());
                matchList.add(matchdto.parseToMatchDTO(match, info));
            }
        }
        return matchList;
    }

    /**
     * Filtra por summonerName los participantes de la partida para que muestre el de el usuario que lo pide
     *
     * @param summonerName
     * @param identity
     * @param participants
     * @return RiotApiParticipantInfo
     */
    private RiotApiParticipantInfo filterParticipantsBySummonerName(String summonerName, List<RiotAPIParticipantIdentity> identity, List<RiotApiParticipant> participants) {
        RiotApiParticipantInfo info = new RiotApiParticipantInfo();
        int participantsSize = identity.size();
        int indexParticipant = 0;
        for (int i = 0; i < participantsSize; i++) {
            if (summonerName.equals(identity.get(i).getPlayer().getSummonerName())) {
                indexParticipant = i;
                break;
            }
        }
        info.setSummonerName(identity.get(indexParticipant).getPlayer().getSummonerName());
        info.setStats(participants.get(indexParticipant).getStats());
        info.setChampion(this.championRepository.findByIdChampion(participants.get(indexParticipant).getChampionId()));
        return info;
    }


    public List<MatchDTO> getLatestMatchesFromDb(UserDTO userlogged) {
        List<MatchDTO> matchs = this.summonerRepository.findByIdUser(userlogged.getIdUser());
        for (MatchDTO matchDTO : matchs) {
            matchDTO.setChampionName(this.championRepository.findByIdChampion(matchDTO.getIdChampion()).getChampionName());
            matchDTO.setSummonerName(userlogged.getSummonerName());
        }
        return matchs;
    }
}
