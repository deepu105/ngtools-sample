package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.EntityWithServiceImplPaginationAndDTOService;
import com.mycompany.myapp.domain.EntityWithServiceImplPaginationAndDTO;
import com.mycompany.myapp.repository.EntityWithServiceImplPaginationAndDTORepository;
import com.mycompany.myapp.repository.search.EntityWithServiceImplPaginationAndDTOSearchRepository;
import com.mycompany.myapp.service.dto.EntityWithServiceImplPaginationAndDTODTO;
import com.mycompany.myapp.service.mapper.EntityWithServiceImplPaginationAndDTOMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing EntityWithServiceImplPaginationAndDTO.
 */
@Service
@Transactional
public class EntityWithServiceImplPaginationAndDTOServiceImpl implements EntityWithServiceImplPaginationAndDTOService{

    private final Logger log = LoggerFactory.getLogger(EntityWithServiceImplPaginationAndDTOServiceImpl.class);

    private final EntityWithServiceImplPaginationAndDTORepository entityWithServiceImplPaginationAndDTORepository;

    private final EntityWithServiceImplPaginationAndDTOMapper entityWithServiceImplPaginationAndDTOMapper;

    private final EntityWithServiceImplPaginationAndDTOSearchRepository entityWithServiceImplPaginationAndDTOSearchRepository;

    public EntityWithServiceImplPaginationAndDTOServiceImpl(EntityWithServiceImplPaginationAndDTORepository entityWithServiceImplPaginationAndDTORepository, EntityWithServiceImplPaginationAndDTOMapper entityWithServiceImplPaginationAndDTOMapper, EntityWithServiceImplPaginationAndDTOSearchRepository entityWithServiceImplPaginationAndDTOSearchRepository) {
        this.entityWithServiceImplPaginationAndDTORepository = entityWithServiceImplPaginationAndDTORepository;
        this.entityWithServiceImplPaginationAndDTOMapper = entityWithServiceImplPaginationAndDTOMapper;
        this.entityWithServiceImplPaginationAndDTOSearchRepository = entityWithServiceImplPaginationAndDTOSearchRepository;
    }

    /**
     * Save a entityWithServiceImplPaginationAndDTO.
     *
     * @param entityWithServiceImplPaginationAndDTODTO the entity to save
     * @return the persisted entity
     */
    @Override
    public EntityWithServiceImplPaginationAndDTODTO save(EntityWithServiceImplPaginationAndDTODTO entityWithServiceImplPaginationAndDTODTO) {
        log.debug("Request to save EntityWithServiceImplPaginationAndDTO : {}", entityWithServiceImplPaginationAndDTODTO);
        EntityWithServiceImplPaginationAndDTO entityWithServiceImplPaginationAndDTO = entityWithServiceImplPaginationAndDTOMapper.toEntity(entityWithServiceImplPaginationAndDTODTO);
        entityWithServiceImplPaginationAndDTO = entityWithServiceImplPaginationAndDTORepository.save(entityWithServiceImplPaginationAndDTO);
        EntityWithServiceImplPaginationAndDTODTO result = entityWithServiceImplPaginationAndDTOMapper.toDto(entityWithServiceImplPaginationAndDTO);
        entityWithServiceImplPaginationAndDTOSearchRepository.save(entityWithServiceImplPaginationAndDTO);
        return result;
    }

    /**
     *  Get all the entityWithServiceImplPaginationAndDTOS.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<EntityWithServiceImplPaginationAndDTODTO> findAll(Pageable pageable) {
        log.debug("Request to get all EntityWithServiceImplPaginationAndDTOS");
        return entityWithServiceImplPaginationAndDTORepository.findAll(pageable)
            .map(entityWithServiceImplPaginationAndDTOMapper::toDto);
    }

    /**
     *  Get one entityWithServiceImplPaginationAndDTO by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public EntityWithServiceImplPaginationAndDTODTO findOne(Long id) {
        log.debug("Request to get EntityWithServiceImplPaginationAndDTO : {}", id);
        EntityWithServiceImplPaginationAndDTO entityWithServiceImplPaginationAndDTO = entityWithServiceImplPaginationAndDTORepository.findOne(id);
        return entityWithServiceImplPaginationAndDTOMapper.toDto(entityWithServiceImplPaginationAndDTO);
    }

    /**
     *  Delete the  entityWithServiceImplPaginationAndDTO by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete EntityWithServiceImplPaginationAndDTO : {}", id);
        entityWithServiceImplPaginationAndDTORepository.delete(id);
        entityWithServiceImplPaginationAndDTOSearchRepository.delete(id);
    }

    /**
     * Search for the entityWithServiceImplPaginationAndDTO corresponding to the query.
     *
     *  @param query the query of the search
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<EntityWithServiceImplPaginationAndDTODTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of EntityWithServiceImplPaginationAndDTOS for query {}", query);
        Page<EntityWithServiceImplPaginationAndDTO> result = entityWithServiceImplPaginationAndDTOSearchRepository.search(queryStringQuery(query), pageable);
        return result.map(entityWithServiceImplPaginationAndDTOMapper::toDto);
    }
}
