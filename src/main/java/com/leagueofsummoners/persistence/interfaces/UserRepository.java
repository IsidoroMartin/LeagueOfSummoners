package com.leagueofsummoners.persistence.interfaces;



import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import com.leagueofsummoners.model.dto.UserDTO;

@org.springframework.stereotype.Repository
public interface UserRepository extends Repository<UserDTO, Long> {

	Page<UserDTO> findAll(Pageable pageable);
	
	List<UserDTO> findAll();

	UserDTO findByUsernameIgnoringCase(String username);
	
	
	UserDTO findByIdUser(Long idUser);

}
