package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.EntityWithServiceImplAndDTOService;
import com.mycompany.myapp.domain.EntityWithServiceImplAndDTO;
import com.mycompany.myapp.repository.EntityWithServiceImplAndDTORepository;
import com.mycompany.myapp.repository.search.EntityWithServiceImplAndDTOSearchRepository;
import com.mycompany.myapp.service.dto.EntityWithServiceImplAndDTODTO;
import com.mycompany.myapp.service.mapper.EntityWithServiceImplAndDTOMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing EntityWithServiceImplAndDTO.
 */
@Service
@Transactional
public class EntityWithServiceImplAndDTOServiceImpl implements EntityWithServiceImplAndDTOService{

    private final Logger log = LoggerFactory.getLogger(EntityWithServiceImplAndDTOServiceImpl.class);

    private final EntityWithServiceImplAndDTORepository entityWithServiceImplAndDTORepository;

    private final EntityWithServiceImplAndDTOMapper entityWithServiceImplAndDTOMapper;

    private final EntityWithServiceImplAndDTOSearchRepository entityWithServiceImplAndDTOSearchRepository;

    public EntityWithServiceImplAndDTOServiceImpl(EntityWithServiceImplAndDTORepository entityWithServiceImplAndDTORepository, EntityWithServiceImplAndDTOMapper entityWithServiceImplAndDTOMapper, EntityWithServiceImplAndDTOSearchRepository entityWithServiceImplAndDTOSearchRepository) {
        this.entityWithServiceImplAndDTORepository = entityWithServiceImplAndDTORepository;
        this.entityWithServiceImplAndDTOMapper = entityWithServiceImplAndDTOMapper;
        this.entityWithServiceImplAndDTOSearchRepository = entityWithServiceImplAndDTOSearchRepository;
    }

    /**
     * Save a entityWithServiceImplAndDTO.
     *
     * @param entityWithServiceImplAndDTODTO the entity to save
     * @return the persisted entity
     */
    @Override
    public EntityWithServiceImplAndDTODTO save(EntityWithServiceImplAndDTODTO entityWithServiceImplAndDTODTO) {
        log.debug("Request to save EntityWithServiceImplAndDTO : {}", entityWithServiceImplAndDTODTO);
        EntityWithServiceImplAndDTO entityWithServiceImplAndDTO = entityWithServiceImplAndDTOMapper.toEntity(entityWithServiceImplAndDTODTO);
        entityWithServiceImplAndDTO = entityWithServiceImplAndDTORepository.save(entityWithServiceImplAndDTO);
        EntityWithServiceImplAndDTODTO result = entityWithServiceImplAndDTOMapper.toDto(entityWithServiceImplAndDTO);
        entityWithServiceImplAndDTOSearchRepository.save(entityWithServiceImplAndDTO);
        return result;
    }

    /**
     *  Get all the entityWithServiceImplAndDTOS.
     *
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<EntityWithServiceImplAndDTODTO> findAll() {
        log.debug("Request to get all EntityWithServiceImplAndDTOS");
        return entityWithServiceImplAndDTORepository.findAll().stream()
            .map(entityWithServiceImplAndDTOMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     *  Get one entityWithServiceImplAndDTO by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public EntityWithServiceImplAndDTODTO findOne(Long id) {
        log.debug("Request to get EntityWithServiceImplAndDTO : {}", id);
        EntityWithServiceImplAndDTO entityWithServiceImplAndDTO = entityWithServiceImplAndDTORepository.findOne(id);
        return entityWithServiceImplAndDTOMapper.toDto(entityWithServiceImplAndDTO);
    }

    /**
     *  Delete the  entityWithServiceImplAndDTO by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete EntityWithServiceImplAndDTO : {}", id);
        entityWithServiceImplAndDTORepository.delete(id);
        entityWithServiceImplAndDTOSearchRepository.delete(id);
    }

    /**
     * Search for the entityWithServiceImplAndDTO corresponding to the query.
     *
     *  @param query the query of the search
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<EntityWithServiceImplAndDTODTO> search(String query) {
        log.debug("Request to search EntityWithServiceImplAndDTOS for query {}", query);
        return StreamSupport
            .stream(entityWithServiceImplAndDTOSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(entityWithServiceImplAndDTOMapper::toDto)
            .collect(Collectors.toList());
    }
}
