package com.leagueofsummoners.model.interfaces.services;


import com.leagueofsummoners.model.dto.GenericDTO;

public interface IServicesSearch {

    GenericDTO searchByChampionGuideOrProPlayer(String query);
}
