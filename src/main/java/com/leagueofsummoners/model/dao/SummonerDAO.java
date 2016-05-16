package com.leagueofsummoners.model.dao;


import com.leagueofsummoners.LeagueofsummonersApplication;
import com.leagueofsummoners.model.dto.ChampionDTO;
import com.leagueofsummoners.model.dto.MatchDTO;
import com.leagueofsummoners.model.dto.UserDTO;
import com.leagueofsummoners.model.dto.riotapi.*;
import com.leagueofsummoners.model.interfaces.persistence.ChampionRepository;
import com.leagueofsummoners.model.interfaces.persistence.ItemRepository;
import com.leagueofsummoners.model.interfaces.persistence.SummonerRepository;
import com.leagueofsummoners.model.utils.LeagueAccessAPI;
import com.leagueofsummoners.model.utils.RepositoryUtils;
import com.robrua.orianna.api.core.RiotAPI;
import com.robrua.orianna.type.core.summoner.Summoner;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class SummonerDAO {

    @Autowired
    private ChampionRepository championRepository;

    @Autowired
    private SummonerRepository summonerRepository;

    @Autowired
    private ItemRepository itemsRepository;

    private RestTemplate rest;

    public SummonerDAO() {
        this.rest = new RestTemplate();
    }

    /**
     * Obtiene un summoner de la BD de riot
     *
     * @param summonerName
     * @return Summoner (invocador)
     */
    public Summoner getSummonerData(String summonerName) {
        Summoner sum = null;
        try {
            sum = RiotAPI.getSummonerByName(summonerName);
        } catch (Exception e) {
            LeagueofsummonersApplication.LOGGER.error("Error obteniendo el summonername, probablemente no exista. " + e.getMessage());
        }
        return sum;
    }

    /**
     * Obtiene las últimas partidas de la Base de datos de RIOT
     *
     * @param summonerId
     * @param summonerName
     * @param matchesNumber
     * @return Lista con las ultimas partidas del servidor de RIOT
     */
    public List<MatchDTO> getLatestMatchesFromRiot(long summonerId, String summonerName, int matchesNumber) {
        List<RiotAPIMatch> matchesAPI = this.getUserMatches(summonerId);
        List<MatchDTO> matchList = null;
        int matchSize = matchesAPI.size();
        if (matchSize > 0) {
            matchList = new ArrayList<>();
            //Si la i es menor que los 'match' pedidos y además los match pedidos son menos que los match recibidos (Para evitar salir del array)
            for (int i = 0; i < matchesNumber && matchesNumber < matchSize; i++) {
                try {
                    MatchDTO matchdto = new MatchDTO();
                    RiotAPIMatch match = this.rest.getForObject(LeagueAccessAPI.RIOT_API_OBTAIN_INFO_MATCHES, RiotAPIMatch.class, matchesAPI.get(i).getMatchId());
                    RiotApiParticipantInfo info = this.filterParticipantsBySummonerName(summonerName, match.getParticipantIdentities(), match.getParticipants());
                    matchList.add(matchdto.parseToMatchDTO(match, info));
                }catch(Exception e){
                    log.error("Error obteniendo partida de " + summonerName + " \n " + e.getMessage());
                }
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


    /**
     * Este método obtiene las partidas guardadas en la BD
     *
     * @param userlogged
     * @return Lista de las paritdas
     */
    public List<MatchDTO> getLatestMatchesFromDb(UserDTO userlogged) {
        List<MatchDTO> matchs = this.summonerRepository.findTop10ByIdUser(userlogged.getIdUser());
        for (MatchDTO matchDTO : matchs) {
            ChampionDTO champion = this.championRepository.findByIdChampion(matchDTO.getIdChampion());
            matchDTO.setIdUser(userlogged.getIdUser());
            matchDTO.setChampionName(champion.getChampionName());
            matchDTO.setIdChampion(champion.getIdChampion());
            matchDTO.setSummonerName(userlogged.getSummonerName());
            RepositoryUtils.setItemsInMatchDTO(this.itemsRepository,matchDTO);
        }
        return matchs;
    }

    /**
     * Este es el método que se invoca la primera vez que un usuario loggea a su cuenta, para almacenar en nuestra BD las primeras 5 partidas
     *
     * @param userlogged
     * @return Lista con las 5 últimas partidas
     */
    public List<MatchDTO> getMatches(UserDTO userlogged) {
        List<MatchDTO> matchesFromDB = this.getLatestMatchesFromDb(userlogged);
        List<MatchDTO> matchesFromRiot = null;
        if (matchesFromDB.isEmpty()) {
            matchesFromRiot = this.getLatestMatchesFromRiot(userlogged.getSummonerID(), userlogged.getSummonerName(), 5);
            for (MatchDTO matchDTO : matchesFromRiot) {
                matchDTO.setIdUser(userlogged.getIdUser());
                matchDTO.setIdChampion(this.championRepository.findBychampionNameIgnoringCase(matchDTO.getChampionName()).getIdChampion());
                RepositoryUtils.setItemsInMatchDTO(this.itemsRepository,matchDTO);
                this.summonerRepository.save(matchDTO);
            }
            matchesFromDB.addAll(matchesFromRiot);
        }
        return matchesFromDB;
    }

    /**
     * Obtiene las siguientes 5 partidas de un jugador basandose en la última almacenada en la base de datos.
     *
     * @param userdto
     * @param matchesNumber
     * @return Lista con los siguientes 5 partidas
     */
    public List<MatchDTO> getNextMatches(UserDTO userdto, int matchesNumber) {
        Long lastMatchId = this.summonerRepository.idLastSavedMatch(userdto.getIdUser());
        List<MatchDTO> matchList = null;
        if (lastMatchId != null) {
            List<RiotAPIMatch> matchesAPI = this.getUserMatches(userdto.getSummonerID());
            int matchSize = matchesAPI.size();
            if (matchSize > 0) {
                matchList = new ArrayList<>();
                int matchPosition = RepositoryUtils.riotAPIGetNextMatchPosition(matchesAPI, lastMatchId); //Obtengo el siguiente match
                if (matchPosition > 0) {
                    matchesNumber += matchPosition;
                    //Si la i es menor que los 'match' pedidos y además los match pedidos son menos que los match recibidos (Para evitar salir del array)
                    for (int i = matchPosition; i < matchesNumber && matchesNumber < matchSize; i++) {
                        MatchDTO matchDTO = new MatchDTO();
                        RiotAPIMatch match = this.rest.getForObject(LeagueAccessAPI.RIOT_API_OBTAIN_INFO_MATCHES, RiotAPIMatch.class, matchesAPI.get(i).getMatchId());
                        matchDTO.setIdUser(userdto.getIdUser());
                        RiotApiParticipantInfo info = this.filterParticipantsBySummonerName(userdto.getSummonerName(), match.getParticipantIdentities(), match.getParticipants());
                        RepositoryUtils.setItemsInMatchDTO(this.itemsRepository,matchDTO);
                        matchList.add(matchDTO.parseToMatchDTO(match, info));
                        this.summonerRepository.save(matchDTO);
                    }
                }
            }
        } else {
            matchList = this.getMatches(userdto);
        }

        return matchList;
    }


    private List<RiotAPIMatch> getUserMatches(Long idSummoner) {
        try {
            return this.rest.getForObject(LeagueAccessAPI.RIOT_API_OBTAIN_SUMMONER_MATCHES, RiotAPIMatches.class, idSummoner).getMatches();
        } catch (Exception e) {
            e.printStackTrace();
            LeagueofsummonersApplication.LOGGER.error("Error obteniendo las partidas del invocador " + e.getMessage());
        }
        return null;
    }


}
