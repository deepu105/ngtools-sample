package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.EntityWithServiceImplAndPaginationService;
import com.mycompany.myapp.domain.EntityWithServiceImplAndPagination;
import com.mycompany.myapp.repository.EntityWithServiceImplAndPaginationRepository;
import com.mycompany.myapp.repository.search.EntityWithServiceImplAndPaginationSearchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing EntityWithServiceImplAndPagination.
 */
@Service
@Transactional
public class EntityWithServiceImplAndPaginationServiceImpl implements EntityWithServiceImplAndPaginationService{

    private final Logger log = LoggerFactory.getLogger(EntityWithServiceImplAndPaginationServiceImpl.class);

    private final EntityWithServiceImplAndPaginationRepository entityWithServiceImplAndPaginationRepository;

    private final EntityWithServiceImplAndPaginationSearchRepository entityWithServiceImplAndPaginationSearchRepository;

    public EntityWithServiceImplAndPaginationServiceImpl(EntityWithServiceImplAndPaginationRepository entityWithServiceImplAndPaginationRepository, EntityWithServiceImplAndPaginationSearchRepository entityWithServiceImplAndPaginationSearchRepository) {
        this.entityWithServiceImplAndPaginationRepository = entityWithServiceImplAndPaginationRepository;
        this.entityWithServiceImplAndPaginationSearchRepository = entityWithServiceImplAndPaginationSearchRepository;
    }

    /**
     * Save a entityWithServiceImplAndPagination.
     *
     * @param entityWithServiceImplAndPagination the entity to save
     * @return the persisted entity
     */
    @Override
    public EntityWithServiceImplAndPagination save(EntityWithServiceImplAndPagination entityWithServiceImplAndPagination) {
        log.debug("Request to save EntityWithServiceImplAndPagination : {}", entityWithServiceImplAndPagination);
        EntityWithServiceImplAndPagination result = entityWithServiceImplAndPaginationRepository.save(entityWithServiceImplAndPagination);
        entityWithServiceImplAndPaginationSearchRepository.save(result);
        return result;
    }

    /**
     *  Get all the entityWithServiceImplAndPaginations.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<EntityWithServiceImplAndPagination> findAll(Pageable pageable) {
        log.debug("Request to get all EntityWithServiceImplAndPaginations");
        return entityWithServiceImplAndPaginationRepository.findAll(pageable);
    }

    /**
     *  Get one entityWithServiceImplAndPagination by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public EntityWithServiceImplAndPagination findOne(Long id) {
        log.debug("Request to get EntityWithServiceImplAndPagination : {}", id);
        return entityWithServiceImplAndPaginationRepository.findOne(id);
    }

    /**
     *  Delete the  entityWithServiceImplAndPagination by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete EntityWithServiceImplAndPagination : {}", id);
        entityWithServiceImplAndPaginationRepository.delete(id);
        entityWithServiceImplAndPaginationSearchRepository.delete(id);
    }

    /**
     * Search for the entityWithServiceImplAndPagination corresponding to the query.
     *
     *  @param query the query of the search
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<EntityWithServiceImplAndPagination> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of EntityWithServiceImplAndPaginations for query {}", query);
        Page<EntityWithServiceImplAndPagination> result = entityWithServiceImplAndPaginationSearchRepository.search(queryStringQuery(query), pageable);
        return result;
    }
}
