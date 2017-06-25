package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.Label;
import com.mycompany.myapp.repository.LabelRepository;
import com.mycompany.myapp.repository.search.LabelSearchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing Label.
 */
@Service
@Transactional
public class LabelService {

    private final Logger log = LoggerFactory.getLogger(LabelService.class);

    private final LabelRepository labelRepository;

    private final LabelSearchRepository labelSearchRepository;

    public LabelService(LabelRepository labelRepository, LabelSearchRepository labelSearchRepository) {
        this.labelRepository = labelRepository;
        this.labelSearchRepository = labelSearchRepository;
    }

    /**
     * Save a label.
     *
     * @param label the entity to save
     * @return the persisted entity
     */
    public Label save(Label label) {
        log.debug("Request to save Label : {}", label);
        Label result = labelRepository.save(label);
        labelSearchRepository.save(result);
        return result;
    }

    /**
     *  Get all the labels.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<Label> findAll(Pageable pageable) {
        log.debug("Request to get all Labels");
        return labelRepository.findAll(pageable);
    }

    /**
     *  Get one label by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true)
    public Label findOne(Long id) {
        log.debug("Request to get Label : {}", id);
        return labelRepository.findOne(id);
    }

    /**
     *  Delete the  label by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Label : {}", id);
        labelRepository.delete(id);
        labelSearchRepository.delete(id);
    }

    /**
     * Search for the label corresponding to the query.
     *
     *  @param query the query of the search
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<Label> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of Labels for query {}", query);
        Page<Label> result = labelSearchRepository.search(queryStringQuery(query), pageable);
        return result;
    }
}
