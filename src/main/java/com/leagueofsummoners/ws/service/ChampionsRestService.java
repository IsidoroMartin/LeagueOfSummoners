package com.leagueofsummoners.ws.service;

import com.leagueofsummoners.interfaces.services.IServicesChampions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * En esta clase están todos los servicios REST relaccionadas con los usuarios
 * @author Juanjors
 */
@RestController
@RequestMapping(value = "/api/champions")
public class ChampionsRestService {

	@Autowired
	private IServicesChampions servicioChampions;

	@RequestMapping(value = "/championsnames", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON)
	public List<String> getChampionsIconNames() {
		return this.servicioChampions.getChampionsIconsNamesList();
	}


}
