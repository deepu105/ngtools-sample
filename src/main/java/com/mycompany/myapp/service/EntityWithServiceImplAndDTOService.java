package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.EntityWithServiceImplAndDTODTO;
import java.util.List;

/**
 * Service Interface for managing EntityWithServiceImplAndDTO.
 */
public interface EntityWithServiceImplAndDTOService {

    /**
     * Save a entityWithServiceImplAndDTO.
     *
     * @param entityWithServiceImplAndDTODTO the entity to save
     * @return the persisted entity
     */
    EntityWithServiceImplAndDTODTO save(EntityWithServiceImplAndDTODTO entityWithServiceImplAndDTODTO);

    /**
     *  Get all the entityWithServiceImplAndDTOS.
     *
     *  @return the list of entities
     */
    List<EntityWithServiceImplAndDTODTO> findAll();

    /**
     *  Get the "id" entityWithServiceImplAndDTO.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    EntityWithServiceImplAndDTODTO findOne(Long id);

    /**
     *  Delete the "id" entityWithServiceImplAndDTO.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the entityWithServiceImplAndDTO corresponding to the query.
     *
     *  @param query the query of the search
     *  
     *  @return the list of entities
     */
    List<EntityWithServiceImplAndDTODTO> search(String query);
}
