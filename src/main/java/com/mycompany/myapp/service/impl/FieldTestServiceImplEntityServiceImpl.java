package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.FieldTestServiceImplEntityService;
import com.mycompany.myapp.domain.FieldTestServiceImplEntity;
import com.mycompany.myapp.repository.FieldTestServiceImplEntityRepository;
import com.mycompany.myapp.repository.search.FieldTestServiceImplEntitySearchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing FieldTestServiceImplEntity.
 */
@Service
@Transactional
public class FieldTestServiceImplEntityServiceImpl implements FieldTestServiceImplEntityService{

    private final Logger log = LoggerFactory.getLogger(FieldTestServiceImplEntityServiceImpl.class);

    private final FieldTestServiceImplEntityRepository fieldTestServiceImplEntityRepository;

    private final FieldTestServiceImplEntitySearchRepository fieldTestServiceImplEntitySearchRepository;

    public FieldTestServiceImplEntityServiceImpl(FieldTestServiceImplEntityRepository fieldTestServiceImplEntityRepository, FieldTestServiceImplEntitySearchRepository fieldTestServiceImplEntitySearchRepository) {
        this.fieldTestServiceImplEntityRepository = fieldTestServiceImplEntityRepository;
        this.fieldTestServiceImplEntitySearchRepository = fieldTestServiceImplEntitySearchRepository;
    }

    /**
     * Save a fieldTestServiceImplEntity.
     *
     * @param fieldTestServiceImplEntity the entity to save
     * @return the persisted entity
     */
    @Override
    public FieldTestServiceImplEntity save(FieldTestServiceImplEntity fieldTestServiceImplEntity) {
        log.debug("Request to save FieldTestServiceImplEntity : {}", fieldTestServiceImplEntity);
        FieldTestServiceImplEntity result = fieldTestServiceImplEntityRepository.save(fieldTestServiceImplEntity);
        fieldTestServiceImplEntitySearchRepository.save(result);
        return result;
    }

    /**
     *  Get all the fieldTestServiceImplEntities.
     *
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<FieldTestServiceImplEntity> findAll() {
        log.debug("Request to get all FieldTestServiceImplEntities");
        return fieldTestServiceImplEntityRepository.findAll();
    }

    /**
     *  Get one fieldTestServiceImplEntity by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public FieldTestServiceImplEntity findOne(Long id) {
        log.debug("Request to get FieldTestServiceImplEntity : {}", id);
        return fieldTestServiceImplEntityRepository.findOne(id);
    }

    /**
     *  Delete the  fieldTestServiceImplEntity by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete FieldTestServiceImplEntity : {}", id);
        fieldTestServiceImplEntityRepository.delete(id);
        fieldTestServiceImplEntitySearchRepository.delete(id);
    }

    /**
     * Search for the fieldTestServiceImplEntity corresponding to the query.
     *
     *  @param query the query of the search
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<FieldTestServiceImplEntity> search(String query) {
        log.debug("Request to search FieldTestServiceImplEntities for query {}", query);
        return StreamSupport
            .stream(fieldTestServiceImplEntitySearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .collect(Collectors.toList());
    }
}
