package com.mycompany.myapp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.mycompany.myapp.domain.EntityWithPagination;

import com.mycompany.myapp.repository.EntityWithPaginationRepository;
import com.mycompany.myapp.repository.search.EntityWithPaginationSearchRepository;
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

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * REST controller for managing EntityWithPagination.
 */
@RestController
@RequestMapping("/api")
public class EntityWithPaginationResource {

    private final Logger log = LoggerFactory.getLogger(EntityWithPaginationResource.class);

    private static final String ENTITY_NAME = "entityWithPagination";

    private final EntityWithPaginationRepository entityWithPaginationRepository;

    private final EntityWithPaginationSearchRepository entityWithPaginationSearchRepository;

    public EntityWithPaginationResource(EntityWithPaginationRepository entityWithPaginationRepository, EntityWithPaginationSearchRepository entityWithPaginationSearchRepository) {
        this.entityWithPaginationRepository = entityWithPaginationRepository;
        this.entityWithPaginationSearchRepository = entityWithPaginationSearchRepository;
    }

    /**
     * POST  /entity-with-paginations : Create a new entityWithPagination.
     *
     * @param entityWithPagination the entityWithPagination to create
     * @return the ResponseEntity with status 201 (Created) and with body the new entityWithPagination, or with status 400 (Bad Request) if the entityWithPagination has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/entity-with-paginations")
    @Timed
    public ResponseEntity<EntityWithPagination> createEntityWithPagination(@RequestBody EntityWithPagination entityWithPagination) throws URISyntaxException {
        log.debug("REST request to save EntityWithPagination : {}", entityWithPagination);
        if (entityWithPagination.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new entityWithPagination cannot already have an ID")).body(null);
        }
        EntityWithPagination result = entityWithPaginationRepository.save(entityWithPagination);
        entityWithPaginationSearchRepository.save(result);
        return ResponseEntity.created(new URI("/api/entity-with-paginations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /entity-with-paginations : Updates an existing entityWithPagination.
     *
     * @param entityWithPagination the entityWithPagination to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated entityWithPagination,
     * or with status 400 (Bad Request) if the entityWithPagination is not valid,
     * or with status 500 (Internal Server Error) if the entityWithPagination couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/entity-with-paginations")
    @Timed
    public ResponseEntity<EntityWithPagination> updateEntityWithPagination(@RequestBody EntityWithPagination entityWithPagination) throws URISyntaxException {
        log.debug("REST request to update EntityWithPagination : {}", entityWithPagination);
        if (entityWithPagination.getId() == null) {
            return createEntityWithPagination(entityWithPagination);
        }
        EntityWithPagination result = entityWithPaginationRepository.save(entityWithPagination);
        entityWithPaginationSearchRepository.save(result);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, entityWithPagination.getId().toString()))
            .body(result);
    }

    /**
     * GET  /entity-with-paginations : get all the entityWithPaginations.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of entityWithPaginations in body
     */
    @GetMapping("/entity-with-paginations")
    @Timed
    public ResponseEntity<List<EntityWithPagination>> getAllEntityWithPaginations(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of EntityWithPaginations");
        Page<EntityWithPagination> page = entityWithPaginationRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/entity-with-paginations");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /entity-with-paginations/:id : get the "id" entityWithPagination.
     *
     * @param id the id of the entityWithPagination to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the entityWithPagination, or with status 404 (Not Found)
     */
    @GetMapping("/entity-with-paginations/{id}")
    @Timed
    public ResponseEntity<EntityWithPagination> getEntityWithPagination(@PathVariable Long id) {
        log.debug("REST request to get EntityWithPagination : {}", id);
        EntityWithPagination entityWithPagination = entityWithPaginationRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(entityWithPagination));
    }

    /**
     * DELETE  /entity-with-paginations/:id : delete the "id" entityWithPagination.
     *
     * @param id the id of the entityWithPagination to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/entity-with-paginations/{id}")
    @Timed
    public ResponseEntity<Void> deleteEntityWithPagination(@PathVariable Long id) {
        log.debug("REST request to delete EntityWithPagination : {}", id);
        entityWithPaginationRepository.delete(id);
        entityWithPaginationSearchRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/entity-with-paginations?query=:query : search for the entityWithPagination corresponding
     * to the query.
     *
     * @param query the query of the entityWithPagination search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/entity-with-paginations")
    @Timed
    public ResponseEntity<List<EntityWithPagination>> searchEntityWithPaginations(@RequestParam String query, @ApiParam Pageable pageable) {
        log.debug("REST request to search for a page of EntityWithPaginations for query {}", query);
        Page<EntityWithPagination> page = entityWithPaginationSearchRepository.search(queryStringQuery(query), pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/entity-with-paginations");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
