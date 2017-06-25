package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.EntityWithServiceClassAndPagination;
import com.mycompany.myapp.repository.EntityWithServiceClassAndPaginationRepository;
import com.mycompany.myapp.repository.search.EntityWithServiceClassAndPaginationSearchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing EntityWithServiceClassAndPagination.
 */
@Service
@Transactional
public class EntityWithServiceClassAndPaginationService {

    private final Logger log = LoggerFactory.getLogger(EntityWithServiceClassAndPaginationService.class);

    private final EntityWithServiceClassAndPaginationRepository entityWithServiceClassAndPaginationRepository;

    private final EntityWithServiceClassAndPaginationSearchRepository entityWithServiceClassAndPaginationSearchRepository;

    public EntityWithServiceClassAndPaginationService(EntityWithServiceClassAndPaginationRepository entityWithServiceClassAndPaginationRepository, EntityWithServiceClassAndPaginationSearchRepository entityWithServiceClassAndPaginationSearchRepository) {
        this.entityWithServiceClassAndPaginationRepository = entityWithServiceClassAndPaginationRepository;
        this.entityWithServiceClassAndPaginationSearchRepository = entityWithServiceClassAndPaginationSearchRepository;
    }

    /**
     * Save a entityWithServiceClassAndPagination.
     *
     * @param entityWithServiceClassAndPagination the entity to save
     * @return the persisted entity
     */
    public EntityWithServiceClassAndPagination save(EntityWithServiceClassAndPagination entityWithServiceClassAndPagination) {
        log.debug("Request to save EntityWithServiceClassAndPagination : {}", entityWithServiceClassAndPagination);
        EntityWithServiceClassAndPagination result = entityWithServiceClassAndPaginationRepository.save(entityWithServiceClassAndPagination);
        entityWithServiceClassAndPaginationSearchRepository.save(result);
        return result;
    }

    /**
     *  Get all the entityWithServiceClassAndPaginations.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<EntityWithServiceClassAndPagination> findAll(Pageable pageable) {
        log.debug("Request to get all EntityWithServiceClassAndPaginations");
        return entityWithServiceClassAndPaginationRepository.findAll(pageable);
    }

    /**
     *  Get one entityWithServiceClassAndPagination by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true)
    public EntityWithServiceClassAndPagination findOne(Long id) {
        log.debug("Request to get EntityWithServiceClassAndPagination : {}", id);
        return entityWithServiceClassAndPaginationRepository.findOne(id);
    }

    /**
     *  Delete the  entityWithServiceClassAndPagination by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete EntityWithServiceClassAndPagination : {}", id);
        entityWithServiceClassAndPaginationRepository.delete(id);
        entityWithServiceClassAndPaginationSearchRepository.delete(id);
    }

    /**
     * Search for the entityWithServiceClassAndPagination corresponding to the query.
     *
     *  @param query the query of the search
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<EntityWithServiceClassAndPagination> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of EntityWithServiceClassAndPaginations for query {}", query);
        Page<EntityWithServiceClassAndPagination> result = entityWithServiceClassAndPaginationSearchRepository.search(queryStringQuery(query), pageable);
        return result;
    }
}
