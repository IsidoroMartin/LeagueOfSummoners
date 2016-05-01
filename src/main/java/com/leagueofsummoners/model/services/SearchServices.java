package com.leagueofsummoners.model.services;

import com.leagueofsummoners.model.interfaces.services.IServicesSearch;
import com.leagueofsummoners.model.dto.GenericDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SearchServices implements IServicesSearch {

	@Autowired
	private ChampionServices championServices;

    @Autowired
	private UserServices userServices;



	public SearchServices() {
	}


	@Override
	public GenericDTO searchByChampionGuideOrProPlayer(String query) {
		return null;
	}
}
