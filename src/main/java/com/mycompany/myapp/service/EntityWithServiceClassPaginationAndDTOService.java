package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.EntityWithServiceClassPaginationAndDTO;
import com.mycompany.myapp.repository.EntityWithServiceClassPaginationAndDTORepository;
import com.mycompany.myapp.repository.search.EntityWithServiceClassPaginationAndDTOSearchRepository;
import com.mycompany.myapp.service.dto.EntityWithServiceClassPaginationAndDTODTO;
import com.mycompany.myapp.service.mapper.EntityWithServiceClassPaginationAndDTOMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing EntityWithServiceClassPaginationAndDTO.
 */
@Service
@Transactional
public class EntityWithServiceClassPaginationAndDTOService {

    private final Logger log = LoggerFactory.getLogger(EntityWithServiceClassPaginationAndDTOService.class);

    private final EntityWithServiceClassPaginationAndDTORepository entityWithServiceClassPaginationAndDTORepository;

    private final EntityWithServiceClassPaginationAndDTOMapper entityWithServiceClassPaginationAndDTOMapper;

    private final EntityWithServiceClassPaginationAndDTOSearchRepository entityWithServiceClassPaginationAndDTOSearchRepository;

    public EntityWithServiceClassPaginationAndDTOService(EntityWithServiceClassPaginationAndDTORepository entityWithServiceClassPaginationAndDTORepository, EntityWithServiceClassPaginationAndDTOMapper entityWithServiceClassPaginationAndDTOMapper, EntityWithServiceClassPaginationAndDTOSearchRepository entityWithServiceClassPaginationAndDTOSearchRepository) {
        this.entityWithServiceClassPaginationAndDTORepository = entityWithServiceClassPaginationAndDTORepository;
        this.entityWithServiceClassPaginationAndDTOMapper = entityWithServiceClassPaginationAndDTOMapper;
        this.entityWithServiceClassPaginationAndDTOSearchRepository = entityWithServiceClassPaginationAndDTOSearchRepository;
    }

    /**
     * Save a entityWithServiceClassPaginationAndDTO.
     *
     * @param entityWithServiceClassPaginationAndDTODTO the entity to save
     * @return the persisted entity
     */
    public EntityWithServiceClassPaginationAndDTODTO save(EntityWithServiceClassPaginationAndDTODTO entityWithServiceClassPaginationAndDTODTO) {
        log.debug("Request to save EntityWithServiceClassPaginationAndDTO : {}", entityWithServiceClassPaginationAndDTODTO);
        EntityWithServiceClassPaginationAndDTO entityWithServiceClassPaginationAndDTO = entityWithServiceClassPaginationAndDTOMapper.toEntity(entityWithServiceClassPaginationAndDTODTO);
        entityWithServiceClassPaginationAndDTO = entityWithServiceClassPaginationAndDTORepository.save(entityWithServiceClassPaginationAndDTO);
        EntityWithServiceClassPaginationAndDTODTO result = entityWithServiceClassPaginationAndDTOMapper.toDto(entityWithServiceClassPaginationAndDTO);
        entityWithServiceClassPaginationAndDTOSearchRepository.save(entityWithServiceClassPaginationAndDTO);
        return result;
    }

    /**
     *  Get all the entityWithServiceClassPaginationAndDTOS.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<EntityWithServiceClassPaginationAndDTODTO> findAll(Pageable pageable) {
        log.debug("Request to get all EntityWithServiceClassPaginationAndDTOS");
        return entityWithServiceClassPaginationAndDTORepository.findAll(pageable)
            .map(entityWithServiceClassPaginationAndDTOMapper::toDto);
    }

    /**
     *  Get one entityWithServiceClassPaginationAndDTO by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true)
    public EntityWithServiceClassPaginationAndDTODTO findOne(Long id) {
        log.debug("Request to get EntityWithServiceClassPaginationAndDTO : {}", id);
        EntityWithServiceClassPaginationAndDTO entityWithServiceClassPaginationAndDTO = entityWithServiceClassPaginationAndDTORepository.findOne(id);
        return entityWithServiceClassPaginationAndDTOMapper.toDto(entityWithServiceClassPaginationAndDTO);
    }

    /**
     *  Delete the  entityWithServiceClassPaginationAndDTO by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete EntityWithServiceClassPaginationAndDTO : {}", id);
        entityWithServiceClassPaginationAndDTORepository.delete(id);
        entityWithServiceClassPaginationAndDTOSearchRepository.delete(id);
    }

    /**
     * Search for the entityWithServiceClassPaginationAndDTO corresponding to the query.
     *
     *  @param query the query of the search
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<EntityWithServiceClassPaginationAndDTODTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of EntityWithServiceClassPaginationAndDTOS for query {}", query);
        Page<EntityWithServiceClassPaginationAndDTO> result = entityWithServiceClassPaginationAndDTOSearchRepository.search(queryStringQuery(query), pageable);
        return result.map(entityWithServiceClassPaginationAndDTOMapper::toDto);
    }
}
