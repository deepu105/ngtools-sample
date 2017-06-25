package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.EntityWithServiceClassAndDTO;
import com.mycompany.myapp.repository.EntityWithServiceClassAndDTORepository;
import com.mycompany.myapp.repository.search.EntityWithServiceClassAndDTOSearchRepository;
import com.mycompany.myapp.service.dto.EntityWithServiceClassAndDTODTO;
import com.mycompany.myapp.service.mapper.EntityWithServiceClassAndDTOMapper;
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
 * Service Implementation for managing EntityWithServiceClassAndDTO.
 */
@Service
@Transactional
public class EntityWithServiceClassAndDTOService {

    private final Logger log = LoggerFactory.getLogger(EntityWithServiceClassAndDTOService.class);

    private final EntityWithServiceClassAndDTORepository entityWithServiceClassAndDTORepository;

    private final EntityWithServiceClassAndDTOMapper entityWithServiceClassAndDTOMapper;

    private final EntityWithServiceClassAndDTOSearchRepository entityWithServiceClassAndDTOSearchRepository;

    public EntityWithServiceClassAndDTOService(EntityWithServiceClassAndDTORepository entityWithServiceClassAndDTORepository, EntityWithServiceClassAndDTOMapper entityWithServiceClassAndDTOMapper, EntityWithServiceClassAndDTOSearchRepository entityWithServiceClassAndDTOSearchRepository) {
        this.entityWithServiceClassAndDTORepository = entityWithServiceClassAndDTORepository;
        this.entityWithServiceClassAndDTOMapper = entityWithServiceClassAndDTOMapper;
        this.entityWithServiceClassAndDTOSearchRepository = entityWithServiceClassAndDTOSearchRepository;
    }

    /**
     * Save a entityWithServiceClassAndDTO.
     *
     * @param entityWithServiceClassAndDTODTO the entity to save
     * @return the persisted entity
     */
    public EntityWithServiceClassAndDTODTO save(EntityWithServiceClassAndDTODTO entityWithServiceClassAndDTODTO) {
        log.debug("Request to save EntityWithServiceClassAndDTO : {}", entityWithServiceClassAndDTODTO);
        EntityWithServiceClassAndDTO entityWithServiceClassAndDTO = entityWithServiceClassAndDTOMapper.toEntity(entityWithServiceClassAndDTODTO);
        entityWithServiceClassAndDTO = entityWithServiceClassAndDTORepository.save(entityWithServiceClassAndDTO);
        EntityWithServiceClassAndDTODTO result = entityWithServiceClassAndDTOMapper.toDto(entityWithServiceClassAndDTO);
        entityWithServiceClassAndDTOSearchRepository.save(entityWithServiceClassAndDTO);
        return result;
    }

    /**
     *  Get all the entityWithServiceClassAndDTOS.
     *
     *  @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<EntityWithServiceClassAndDTODTO> findAll() {
        log.debug("Request to get all EntityWithServiceClassAndDTOS");
        return entityWithServiceClassAndDTORepository.findAll().stream()
            .map(entityWithServiceClassAndDTOMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     *  Get one entityWithServiceClassAndDTO by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true)
    public EntityWithServiceClassAndDTODTO findOne(Long id) {
        log.debug("Request to get EntityWithServiceClassAndDTO : {}", id);
        EntityWithServiceClassAndDTO entityWithServiceClassAndDTO = entityWithServiceClassAndDTORepository.findOne(id);
        return entityWithServiceClassAndDTOMapper.toDto(entityWithServiceClassAndDTO);
    }

    /**
     *  Delete the  entityWithServiceClassAndDTO by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete EntityWithServiceClassAndDTO : {}", id);
        entityWithServiceClassAndDTORepository.delete(id);
        entityWithServiceClassAndDTOSearchRepository.delete(id);
    }

    /**
     * Search for the entityWithServiceClassAndDTO corresponding to the query.
     *
     *  @param query the query of the search
     *  @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<EntityWithServiceClassAndDTODTO> search(String query) {
        log.debug("Request to search EntityWithServiceClassAndDTOS for query {}", query);
        return StreamSupport
            .stream(entityWithServiceClassAndDTOSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(entityWithServiceClassAndDTOMapper::toDto)
            .collect(Collectors.toList());
    }
}
