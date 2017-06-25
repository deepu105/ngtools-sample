package com.mycompany.myapp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.mycompany.myapp.domain.FieldTestInfiniteScrollEntity;

import com.mycompany.myapp.repository.FieldTestInfiniteScrollEntityRepository;
import com.mycompany.myapp.repository.search.FieldTestInfiniteScrollEntitySearchRepository;
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
 * REST controller for managing FieldTestInfiniteScrollEntity.
 */
@RestController
@RequestMapping("/api")
public class FieldTestInfiniteScrollEntityResource {

    private final Logger log = LoggerFactory.getLogger(FieldTestInfiniteScrollEntityResource.class);

    private static final String ENTITY_NAME = "fieldTestInfiniteScrollEntity";

    private final FieldTestInfiniteScrollEntityRepository fieldTestInfiniteScrollEntityRepository;

    private final FieldTestInfiniteScrollEntitySearchRepository fieldTestInfiniteScrollEntitySearchRepository;

    public FieldTestInfiniteScrollEntityResource(FieldTestInfiniteScrollEntityRepository fieldTestInfiniteScrollEntityRepository, FieldTestInfiniteScrollEntitySearchRepository fieldTestInfiniteScrollEntitySearchRepository) {
        this.fieldTestInfiniteScrollEntityRepository = fieldTestInfiniteScrollEntityRepository;
        this.fieldTestInfiniteScrollEntitySearchRepository = fieldTestInfiniteScrollEntitySearchRepository;
    }

    /**
     * POST  /field-test-infinite-scroll-entities : Create a new fieldTestInfiniteScrollEntity.
     *
     * @param fieldTestInfiniteScrollEntity the fieldTestInfiniteScrollEntity to create
     * @return the ResponseEntity with status 201 (Created) and with body the new fieldTestInfiniteScrollEntity, or with status 400 (Bad Request) if the fieldTestInfiniteScrollEntity has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/field-test-infinite-scroll-entities")
    @Timed
    public ResponseEntity<FieldTestInfiniteScrollEntity> createFieldTestInfiniteScrollEntity(@Valid @RequestBody FieldTestInfiniteScrollEntity fieldTestInfiniteScrollEntity) throws URISyntaxException {
        log.debug("REST request to save FieldTestInfiniteScrollEntity : {}", fieldTestInfiniteScrollEntity);
        if (fieldTestInfiniteScrollEntity.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new fieldTestInfiniteScrollEntity cannot already have an ID")).body(null);
        }
        FieldTestInfiniteScrollEntity result = fieldTestInfiniteScrollEntityRepository.save(fieldTestInfiniteScrollEntity);
        fieldTestInfiniteScrollEntitySearchRepository.save(result);
        return ResponseEntity.created(new URI("/api/field-test-infinite-scroll-entities/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /field-test-infinite-scroll-entities : Updates an existing fieldTestInfiniteScrollEntity.
     *
     * @param fieldTestInfiniteScrollEntity the fieldTestInfiniteScrollEntity to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated fieldTestInfiniteScrollEntity,
     * or with status 400 (Bad Request) if the fieldTestInfiniteScrollEntity is not valid,
     * or with status 500 (Internal Server Error) if the fieldTestInfiniteScrollEntity couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/field-test-infinite-scroll-entities")
    @Timed
    public ResponseEntity<FieldTestInfiniteScrollEntity> updateFieldTestInfiniteScrollEntity(@Valid @RequestBody FieldTestInfiniteScrollEntity fieldTestInfiniteScrollEntity) throws URISyntaxException {
        log.debug("REST request to update FieldTestInfiniteScrollEntity : {}", fieldTestInfiniteScrollEntity);
        if (fieldTestInfiniteScrollEntity.getId() == null) {
            return createFieldTestInfiniteScrollEntity(fieldTestInfiniteScrollEntity);
        }
        FieldTestInfiniteScrollEntity result = fieldTestInfiniteScrollEntityRepository.save(fieldTestInfiniteScrollEntity);
        fieldTestInfiniteScrollEntitySearchRepository.save(result);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, fieldTestInfiniteScrollEntity.getId().toString()))
            .body(result);
    }

    /**
     * GET  /field-test-infinite-scroll-entities : get all the fieldTestInfiniteScrollEntities.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of fieldTestInfiniteScrollEntities in body
     */
    @GetMapping("/field-test-infinite-scroll-entities")
    @Timed
    public ResponseEntity<List<FieldTestInfiniteScrollEntity>> getAllFieldTestInfiniteScrollEntities(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of FieldTestInfiniteScrollEntities");
        Page<FieldTestInfiniteScrollEntity> page = fieldTestInfiniteScrollEntityRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/field-test-infinite-scroll-entities");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /field-test-infinite-scroll-entities/:id : get the "id" fieldTestInfiniteScrollEntity.
     *
     * @param id the id of the fieldTestInfiniteScrollEntity to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the fieldTestInfiniteScrollEntity, or with status 404 (Not Found)
     */
    @GetMapping("/field-test-infinite-scroll-entities/{id}")
    @Timed
    public ResponseEntity<FieldTestInfiniteScrollEntity> getFieldTestInfiniteScrollEntity(@PathVariable Long id) {
        log.debug("REST request to get FieldTestInfiniteScrollEntity : {}", id);
        FieldTestInfiniteScrollEntity fieldTestInfiniteScrollEntity = fieldTestInfiniteScrollEntityRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(fieldTestInfiniteScrollEntity));
    }

    /**
     * DELETE  /field-test-infinite-scroll-entities/:id : delete the "id" fieldTestInfiniteScrollEntity.
     *
     * @param id the id of the fieldTestInfiniteScrollEntity to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/field-test-infinite-scroll-entities/{id}")
    @Timed
    public ResponseEntity<Void> deleteFieldTestInfiniteScrollEntity(@PathVariable Long id) {
        log.debug("REST request to delete FieldTestInfiniteScrollEntity : {}", id);
        fieldTestInfiniteScrollEntityRepository.delete(id);
        fieldTestInfiniteScrollEntitySearchRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/field-test-infinite-scroll-entities?query=:query : search for the fieldTestInfiniteScrollEntity corresponding
     * to the query.
     *
     * @param query the query of the fieldTestInfiniteScrollEntity search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/field-test-infinite-scroll-entities")
    @Timed
    public ResponseEntity<List<FieldTestInfiniteScrollEntity>> searchFieldTestInfiniteScrollEntities(@RequestParam String query, @ApiParam Pageable pageable) {
        log.debug("REST request to search for a page of FieldTestInfiniteScrollEntities for query {}", query);
        Page<FieldTestInfiniteScrollEntity> page = fieldTestInfiniteScrollEntitySearchRepository.search(queryStringQuery(query), pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/field-test-infinite-scroll-entities");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
