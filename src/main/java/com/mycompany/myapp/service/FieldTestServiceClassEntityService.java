package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.FieldTestServiceClassEntity;
import com.mycompany.myapp.repository.FieldTestServiceClassEntityRepository;
import com.mycompany.myapp.repository.search.FieldTestServiceClassEntitySearchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing FieldTestServiceClassEntity.
 */
@Service
@Transactional
public class FieldTestServiceClassEntityService {

    private final Logger log = LoggerFactory.getLogger(FieldTestServiceClassEntityService.class);

    private final FieldTestServiceClassEntityRepository fieldTestServiceClassEntityRepository;

    private final FieldTestServiceClassEntitySearchRepository fieldTestServiceClassEntitySearchRepository;

    public FieldTestServiceClassEntityService(FieldTestServiceClassEntityRepository fieldTestServiceClassEntityRepository, FieldTestServiceClassEntitySearchRepository fieldTestServiceClassEntitySearchRepository) {
        this.fieldTestServiceClassEntityRepository = fieldTestServiceClassEntityRepository;
        this.fieldTestServiceClassEntitySearchRepository = fieldTestServiceClassEntitySearchRepository;
    }

    /**
     * Save a fieldTestServiceClassEntity.
     *
     * @param fieldTestServiceClassEntity the entity to save
     * @return the persisted entity
     */
    public FieldTestServiceClassEntity save(FieldTestServiceClassEntity fieldTestServiceClassEntity) {
        log.debug("Request to save FieldTestServiceClassEntity : {}", fieldTestServiceClassEntity);
        FieldTestServiceClassEntity result = fieldTestServiceClassEntityRepository.save(fieldTestServiceClassEntity);
        fieldTestServiceClassEntitySearchRepository.save(result);
        return result;
    }

    /**
     *  Get all the fieldTestServiceClassEntities.
     *
     *  @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<FieldTestServiceClassEntity> findAll() {
        log.debug("Request to get all FieldTestServiceClassEntities");
        return fieldTestServiceClassEntityRepository.findAll();
    }

    /**
     *  Get one fieldTestServiceClassEntity by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true)
    public FieldTestServiceClassEntity findOne(Long id) {
        log.debug("Request to get FieldTestServiceClassEntity : {}", id);
        return fieldTestServiceClassEntityRepository.findOne(id);
    }

    /**
     *  Delete the  fieldTestServiceClassEntity by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete FieldTestServiceClassEntity : {}", id);
        fieldTestServiceClassEntityRepository.delete(id);
        fieldTestServiceClassEntitySearchRepository.delete(id);
    }

    /**
     * Search for the fieldTestServiceClassEntity corresponding to the query.
     *
     *  @param query the query of the search
     *  @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<FieldTestServiceClassEntity> search(String query) {
        log.debug("Request to search FieldTestServiceClassEntities for query {}", query);
        return StreamSupport
            .stream(fieldTestServiceClassEntitySearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .collect(Collectors.toList());
    }
}
