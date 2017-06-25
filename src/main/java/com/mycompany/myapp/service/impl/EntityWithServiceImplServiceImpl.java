package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.EntityWithServiceImplService;
import com.mycompany.myapp.domain.EntityWithServiceImpl;
import com.mycompany.myapp.repository.EntityWithServiceImplRepository;
import com.mycompany.myapp.repository.search.EntityWithServiceImplSearchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing EntityWithServiceImpl.
 */
@Service
@Transactional
public class EntityWithServiceImplServiceImpl implements EntityWithServiceImplService{

    private final Logger log = LoggerFactory.getLogger(EntityWithServiceImplServiceImpl.class);

    private final EntityWithServiceImplRepository entityWithServiceImplRepository;

    private final EntityWithServiceImplSearchRepository entityWithServiceImplSearchRepository;

    public EntityWithServiceImplServiceImpl(EntityWithServiceImplRepository entityWithServiceImplRepository, EntityWithServiceImplSearchRepository entityWithServiceImplSearchRepository) {
        this.entityWithServiceImplRepository = entityWithServiceImplRepository;
        this.entityWithServiceImplSearchRepository = entityWithServiceImplSearchRepository;
    }

    /**
     * Save a entityWithServiceImpl.
     *
     * @param entityWithServiceImpl the entity to save
     * @return the persisted entity
     */
    @Override
    public EntityWithServiceImpl save(EntityWithServiceImpl entityWithServiceImpl) {
        log.debug("Request to save EntityWithServiceImpl : {}", entityWithServiceImpl);
        EntityWithServiceImpl result = entityWithServiceImplRepository.save(entityWithServiceImpl);
        entityWithServiceImplSearchRepository.save(result);
        return result;
    }

    /**
     *  Get all the entityWithServiceImpls.
     *
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<EntityWithServiceImpl> findAll() {
        log.debug("Request to get all EntityWithServiceImpls");
        return entityWithServiceImplRepository.findAll();
    }

    /**
     *  Get one entityWithServiceImpl by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public EntityWithServiceImpl findOne(Long id) {
        log.debug("Request to get EntityWithServiceImpl : {}", id);
        return entityWithServiceImplRepository.findOne(id);
    }

    /**
     *  Delete the  entityWithServiceImpl by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete EntityWithServiceImpl : {}", id);
        entityWithServiceImplRepository.delete(id);
        entityWithServiceImplSearchRepository.delete(id);
    }

    /**
     * Search for the entityWithServiceImpl corresponding to the query.
     *
     *  @param query the query of the search
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<EntityWithServiceImpl> search(String query) {
        log.debug("Request to search EntityWithServiceImpls for query {}", query);
        return StreamSupport
            .stream(entityWithServiceImplSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .collect(Collectors.toList());
    }
}
