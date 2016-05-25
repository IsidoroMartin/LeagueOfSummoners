package com.leagueofsummoners.ws.service;

import com.leagueofsummoners.ApplicationPaths;
import com.leagueofsummoners.SessionAtts;
import com.leagueofsummoners.model.dto.GenericJsonValidator;
import com.leagueofsummoners.model.dto.UserDTO;
import com.leagueofsummoners.model.interfaces.services.IServicesChampions;
import com.leagueofsummoners.model.interfaces.services.IServicesGuides;
import com.leagueofsummoners.security.annotations.LoginRequired;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * En esta clase están todos los servicios REST relaccionadas con los usuarios
 * @author Juanjors
 */
@RestController
@RequestMapping(value = "/api/guides")
public class GuidesRestService {

	@Autowired
	private IServicesGuides serviceGuides;

	@LoginRequired
	@RequestMapping(value = { ApplicationPaths.GUIDES_BORRAR_PATH }, method = RequestMethod.DELETE)
	@ResponseBody
	public GenericJsonValidator deleteGuide(@PathVariable("id") Long guideId, HttpSession session) {
		if (guideId != null) {
			UserDTO user = (UserDTO) session.getAttribute(SessionAtts.SESSION_GET_USER_LOGGED);
			this.serviceGuides.deleteByIdGuide(guideId, user.getIdUser());
		}
		return new GenericJsonValidator(this.serviceGuides.findByIdGuide(guideId) == null);
	}

}
