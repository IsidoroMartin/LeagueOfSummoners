package com.leagueofsummoners.model.interfaces.services;


import com.leagueofsummoners.model.dto.GenericDTO;
import com.leagueofsummoners.model.dto.MatchDTO;
import com.leagueofsummoners.model.dto.UserDTO;

import java.util.List;

public interface IServicesSummoner {

    List<MatchDTO> getLatestMatches(UserDTO userlogged, int nMatches);

    List<MatchDTO> getLatestMatchesFromDB(UserDTO userlogged);
}
