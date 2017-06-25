package com.mycompany.myapp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.mycompany.myapp.domain.FieldTestPagerEntity;

import com.mycompany.myapp.repository.FieldTestPagerEntityRepository;
import com.mycompany.myapp.repository.search.FieldTestPagerEntitySearchRepository;
import com.mycompany.myapp.web.rest.util.HeaderUtil;
import com.mycompany.myapp.web.rest.util.PaginationUtil;
import io.swagger.annotations.ApiParam;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * REST controller for managing FieldTestPagerEntity.
 */
@RestController
@RequestMapping("/api")
public class FieldTestPagerEntityResource {

    private final Logger log = LoggerFactory.getLogger(FieldTestPagerEntityResource.class);

    private static final String ENTITY_NAME = "fieldTestPagerEntity";

    private final FieldTestPagerEntityRepository fieldTestPagerEntityRepository;

    private final FieldTestPagerEntitySearchRepository fieldTestPagerEntitySearchRepository;

    public FieldTestPagerEntityResource(FieldTestPagerEntityRepository fieldTestPagerEntityRepository, FieldTestPagerEntitySearchRepository fieldTestPagerEntitySearchRepository) {
        this.fieldTestPagerEntityRepository = fieldTestPagerEntityRepository;
        this.fieldTestPagerEntitySearchRepository = fieldTestPagerEntitySearchRepository;
    }

    /**
     * POST  /field-test-pager-entities : Create a new fieldTestPagerEntity.
     *
     * @param fieldTestPagerEntity the fieldTestPagerEntity to create
     * @return the ResponseEntity with status 201 (Created) and with body the new fieldTestPagerEntity, or with status 400 (Bad Request) if the fieldTestPagerEntity has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/field-test-pager-entities")
    @Timed
    public ResponseEntity<FieldTestPagerEntity> createFieldTestPagerEntity(@Valid @RequestBody FieldTestPagerEntity fieldTestPagerEntity) throws URISyntaxException {
        log.debug("REST request to save FieldTestPagerEntity : {}", fieldTestPagerEntity);
        if (fieldTestPagerEntity.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new fieldTestPagerEntity cannot already have an ID")).body(null);
        }
        FieldTestPagerEntity result = fieldTestPagerEntityRepository.save(fieldTestPagerEntity);
        fieldTestPagerEntitySearchRepository.save(result);
        return ResponseEntity.created(new URI("/api/field-test-pager-entities/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /field-test-pager-entities : Updates an existing fieldTestPagerEntity.
     *
     * @param fieldTestPagerEntity the fieldTestPagerEntity to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated fieldTestPagerEntity,
     * or with status 400 (Bad Request) if the fieldTestPagerEntity is not valid,
     * or with status 500 (Internal Server Error) if the fieldTestPagerEntity couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/field-test-pager-entities")
    @Timed
    public ResponseEntity<FieldTestPagerEntity> updateFieldTestPagerEntity(@Valid @RequestBody FieldTestPagerEntity fieldTestPagerEntity) throws URISyntaxException {
        log.debug("REST request to update FieldTestPagerEntity : {}", fieldTestPagerEntity);
        if (fieldTestPagerEntity.getId() == null) {
            return createFieldTestPagerEntity(fieldTestPagerEntity);
        }
        FieldTestPagerEntity result = fieldTestPagerEntityRepository.save(fieldTestPagerEntity);
        fieldTestPagerEntitySearchRepository.save(result);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, fieldTestPagerEntity.getId().toString()))
            .body(result);
    }

    /**
     * GET  /field-test-pager-entities : get all the fieldTestPagerEntities.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of fieldTestPagerEntities in body
     */
    @GetMapping("/field-test-pager-entities")
    @Timed
    public ResponseEntity<List<FieldTestPagerEntity>> getAllFieldTestPagerEntities(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of FieldTestPagerEntities");
        Page<FieldTestPagerEntity> page = fieldTestPagerEntityRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/field-test-pager-entities");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /field-test-pager-entities/:id : get the "id" fieldTestPagerEntity.
     *
     * @param id the id of the fieldTestPagerEntity to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the fieldTestPagerEntity, or with status 404 (Not Found)
     */
    @GetMapping("/field-test-pager-entities/{id}")
    @Timed
    public ResponseEntity<FieldTestPagerEntity> getFieldTestPagerEntity(@PathVariable Long id) {
        log.debug("REST request to get FieldTestPagerEntity : {}", id);
        FieldTestPagerEntity fieldTestPagerEntity = fieldTestPagerEntityRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(fieldTestPagerEntity));
    }

    /**
     * DELETE  /field-test-pager-entities/:id : delete the "id" fieldTestPagerEntity.
     *
     * @param id the id of the fieldTestPagerEntity to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/field-test-pager-entities/{id}")
    @Timed
    public ResponseEntity<Void> deleteFieldTestPagerEntity(@PathVariable Long id) {
        log.debug("REST request to delete FieldTestPagerEntity : {}", id);
        fieldTestPagerEntityRepository.delete(id);
        fieldTestPagerEntitySearchRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/field-test-pager-entities?query=:query : search for the fieldTestPagerEntity corresponding
     * to the query.
     *
     * @param query the query of the fieldTestPagerEntity search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/field-test-pager-entities")
    @Timed
    public ResponseEntity<List<FieldTestPagerEntity>> searchFieldTestPagerEntities(@RequestParam String query, @ApiParam Pageable pageable) {
        log.debug("REST request to search for a page of FieldTestPagerEntities for query {}", query);
        Page<FieldTestPagerEntity> page = fieldTestPagerEntitySearchRepository.search(queryStringQuery(query), pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/field-test-pager-entities");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
