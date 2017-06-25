package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.EntityWithServiceClass;
import com.mycompany.myapp.repository.EntityWithServiceClassRepository;
import com.mycompany.myapp.repository.search.EntityWithServiceClassSearchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing EntityWithServiceClass.
 */
@Service
@Transactional
public class EntityWithServiceClassService {

    private final Logger log = LoggerFactory.getLogger(EntityWithServiceClassService.class);

    private final EntityWithServiceClassRepository entityWithServiceClassRepository;

    private final EntityWithServiceClassSearchRepository entityWithServiceClassSearchRepository;

    public EntityWithServiceClassService(EntityWithServiceClassRepository entityWithServiceClassRepository, EntityWithServiceClassSearchRepository entityWithServiceClassSearchRepository) {
        this.entityWithServiceClassRepository = entityWithServiceClassRepository;
        this.entityWithServiceClassSearchRepository = entityWithServiceClassSearchRepository;
    }

    /**
     * Save a entityWithServiceClass.
     *
     * @param entityWithServiceClass the entity to save
     * @return the persisted entity
     */
    public EntityWithServiceClass save(EntityWithServiceClass entityWithServiceClass) {
        log.debug("Request to save EntityWithServiceClass : {}", entityWithServiceClass);
        EntityWithServiceClass result = entityWithServiceClassRepository.save(entityWithServiceClass);
        entityWithServiceClassSearchRepository.save(result);
        return result;
    }

    /**
     *  Get all the entityWithServiceClasses.
     *
     *  @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<EntityWithServiceClass> findAll() {
        log.debug("Request to get all EntityWithServiceClasses");
        return entityWithServiceClassRepository.findAll();
    }

    /**
     *  Get one entityWithServiceClass by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true)
    public EntityWithServiceClass findOne(Long id) {
        log.debug("Request to get EntityWithServiceClass : {}", id);
        return entityWithServiceClassRepository.findOne(id);
    }

    /**
     *  Delete the  entityWithServiceClass by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete EntityWithServiceClass : {}", id);
        entityWithServiceClassRepository.delete(id);
        entityWithServiceClassSearchRepository.delete(id);
    }

    /**
     * Search for the entityWithServiceClass corresponding to the query.
     *
     *  @param query the query of the search
     *  @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<EntityWithServiceClass> search(String query) {
        log.debug("Request to search EntityWithServiceClasses for query {}", query);
        return StreamSupport
            .stream(entityWithServiceClassSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .collect(Collectors.toList());
    }
}
