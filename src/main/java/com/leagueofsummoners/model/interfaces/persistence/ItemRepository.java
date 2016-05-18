package com.leagueofsummoners.model.interfaces.persistence;


import com.leagueofsummoners.model.dto.ItemDTO;
import com.leagueofsummoners.model.dto.UserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import java.util.List;

@org.springframework.stereotype.Repository
public interface ItemRepository extends Repository<ItemDTO, Long> {


	List<ItemDTO> findAll();

	ItemDTO findByIdItem(Long idItem);

}
