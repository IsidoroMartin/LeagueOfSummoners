package com.leagueofsummoners.model.interfaces.persistence;


import com.leagueofsummoners.model.dto.UserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import java.util.List;

@org.springframework.stereotype.Repository
public interface UserRepository extends Repository<UserDTO, Long> {



	Page<UserDTO> findAll(Pageable pageable);
	
	List<UserDTO> findAll();

	UserDTO findByUsernameIgnoringCase(String username);
	
	UserDTO findByEmailIgnoringCase(String email);
	
	UserDTO findByIdUser(Long idUser);

	UserDTO save(UserDTO user);

}
