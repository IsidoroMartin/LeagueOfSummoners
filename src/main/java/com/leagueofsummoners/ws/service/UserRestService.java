package com.leagueofsummoners.ws.service;

import com.leagueofsummoners.interfaces.services.IServicesUsers;
import com.leagueofsummoners.model.dto.GenericJSONValid;
import com.leagueofsummoners.model.dto.UserDTO;
import com.leagueofsummoners.security.annotations.LoginAdminRequired;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * En esta clase están todos los servicios REST relaccionadas con los usuarios
 *
 * @author Juanjors
 */
@RestController
@RequestMapping(value = "/api/user")
public class UserRestService {

    @Autowired
    private IServicesUsers servicioUsers;


    /**
     * Este método comprueba si el nombre de usuario esta disponible (Para
     * informar a tiempo real al usuario que se está registrando
     *
     * @param username
     * @return si está disponible devuelve true, si no false
     */
    @RequestMapping(value = "/username/{username}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
    public boolean checkIfUserAvailable(@PathVariable String username) {
        return this.servicioUsers.checkIfUsernameAvailable(username);
    }

    @RequestMapping(value = "/userlist", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
    public List<UserDTO> getListOfUsers() {
        return this.servicioUsers.getUserList();
    }

    /**
     * Este método comprueba si el email esta disponible e (Para informar a
     * tiempo real al usuario que se está registrando
     *
     * @param email
     * @return si esta disponible devuelve true, si no false.
     */
    @LoginAdminRequired
    @RequestMapping(value = "/email/{email:.+}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
    public Boolean checkIfEmailExists(@PathVariable String email) {
        return this.servicioUsers.checkIfEmailAvailable(email);
    }

    /**
     * Este método comprueba si el summonerName está disponible en la BD (Para
     * informar a tiempo real al usuario que se está registrando
     *
     * @param summonerName
     * @return si está disponible devuelve true, si no false.
     */
    @RequestMapping(value = "/summonername", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON)
    public GenericJSONValid checkSummonerExists(@RequestParam("summonerName") String summonerName) {
        return new GenericJSONValid(summonerName.length() >= 4 && this.servicioUsers.checkIfSummonerNameExists(summonerName));
    }
}