package com.leagueofsummoners.model.interfaces.services;


import com.leagueofsummoners.model.dto.GenericDTO;
import com.leagueofsummoners.model.dto.MatchDTO;
import com.leagueofsummoners.model.dto.UserDTO;

import java.util.List;

public interface IServicesSummoner {

    List<MatchDTO> getLatestMatchesAsync(UserDTO user, int nMatches) throws Exception;
    
    List<MatchDTO> getLatestMatchesSync(UserDTO user, int nMatches) throws Exception;

    List<MatchDTO> getLatestMatchesFromDB(UserDTO user);
    
    

}
