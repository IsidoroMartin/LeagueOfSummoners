package com.leagueofsummoners.model.interfaces.services;


import com.leagueofsummoners.model.dto.GenericDTO;

public interface IServicesSearch {

    public GenericDTO searchByChampionGuideOrProPlayer(String query);
}
