package com.mycompany.myapp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.mycompany.myapp.domain.EntityWithServiceImplAndPagination;
import com.mycompany.myapp.service.EntityWithServiceImplAndPaginationService;
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
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * REST controller for managing EntityWithServiceImplAndPagination.
 */
@RestController
@RequestMapping("/api")
public class EntityWithServiceImplAndPaginationResource {

    private final Logger log = LoggerFactory.getLogger(EntityWithServiceImplAndPaginationResource.class);

    private static final String ENTITY_NAME = "entityWithServiceImplAndPagination";

    private final EntityWithServiceImplAndPaginationService entityWithServiceImplAndPaginationService;

    public EntityWithServiceImplAndPaginationResource(EntityWithServiceImplAndPaginationService entityWithServiceImplAndPaginationService) {
        this.entityWithServiceImplAndPaginationService = entityWithServiceImplAndPaginationService;
    }

    /**
     * POST  /entity-with-service-impl-and-paginations : Create a new entityWithServiceImplAndPagination.
     *
     * @param entityWithServiceImplAndPagination the entityWithServiceImplAndPagination to create
     * @return the ResponseEntity with status 201 (Created) and with body the new entityWithServiceImplAndPagination, or with status 400 (Bad Request) if the entityWithServiceImplAndPagination has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/entity-with-service-impl-and-paginations")
    @Timed
    public ResponseEntity<EntityWithServiceImplAndPagination> createEntityWithServiceImplAndPagination(@RequestBody EntityWithServiceImplAndPagination entityWithServiceImplAndPagination) throws URISyntaxException {
        log.debug("REST request to save EntityWithServiceImplAndPagination : {}", entityWithServiceImplAndPagination);
        if (entityWithServiceImplAndPagination.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new entityWithServiceImplAndPagination cannot already have an ID")).body(null);
        }
        EntityWithServiceImplAndPagination result = entityWithServiceImplAndPaginationService.save(entityWithServiceImplAndPagination);
        return ResponseEntity.created(new URI("/api/entity-with-service-impl-and-paginations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /entity-with-service-impl-and-paginations : Updates an existing entityWithServiceImplAndPagination.
     *
     * @param entityWithServiceImplAndPagination the entityWithServiceImplAndPagination to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated entityWithServiceImplAndPagination,
     * or with status 400 (Bad Request) if the entityWithServiceImplAndPagination is not valid,
     * or with status 500 (Internal Server Error) if the entityWithServiceImplAndPagination couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/entity-with-service-impl-and-paginations")
    @Timed
    public ResponseEntity<EntityWithServiceImplAndPagination> updateEntityWithServiceImplAndPagination(@RequestBody EntityWithServiceImplAndPagination entityWithServiceImplAndPagination) throws URISyntaxException {
        log.debug("REST request to update EntityWithServiceImplAndPagination : {}", entityWithServiceImplAndPagination);
        if (entityWithServiceImplAndPagination.getId() == null) {
            return createEntityWithServiceImplAndPagination(entityWithServiceImplAndPagination);
        }
        EntityWithServiceImplAndPagination result = entityWithServiceImplAndPaginationService.save(entityWithServiceImplAndPagination);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, entityWithServiceImplAndPagination.getId().toString()))
            .body(result);
    }

    /**
     * GET  /entity-with-service-impl-and-paginations : get all the entityWithServiceImplAndPaginations.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of entityWithServiceImplAndPaginations in body
     */
    @GetMapping("/entity-with-service-impl-and-paginations")
    @Timed
    public ResponseEntity<List<EntityWithServiceImplAndPagination>> getAllEntityWithServiceImplAndPaginations(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of EntityWithServiceImplAndPaginations");
        Page<EntityWithServiceImplAndPagination> page = entityWithServiceImplAndPaginationService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/entity-with-service-impl-and-paginations");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /entity-with-service-impl-and-paginations/:id : get the "id" entityWithServiceImplAndPagination.
     *
     * @param id the id of the entityWithServiceImplAndPagination to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the entityWithServiceImplAndPagination, or with status 404 (Not Found)
     */
    @GetMapping("/entity-with-service-impl-and-paginations/{id}")
    @Timed
    public ResponseEntity<EntityWithServiceImplAndPagination> getEntityWithServiceImplAndPagination(@PathVariable Long id) {
        log.debug("REST request to get EntityWithServiceImplAndPagination : {}", id);
        EntityWithServiceImplAndPagination entityWithServiceImplAndPagination = entityWithServiceImplAndPaginationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(entityWithServiceImplAndPagination));
    }

    /**
     * DELETE  /entity-with-service-impl-and-paginations/:id : delete the "id" entityWithServiceImplAndPagination.
     *
     * @param id the id of the entityWithServiceImplAndPagination to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/entity-with-service-impl-and-paginations/{id}")
    @Timed
    public ResponseEntity<Void> deleteEntityWithServiceImplAndPagination(@PathVariable Long id) {
        log.debug("REST request to delete EntityWithServiceImplAndPagination : {}", id);
        entityWithServiceImplAndPaginationService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/entity-with-service-impl-and-paginations?query=:query : search for the entityWithServiceImplAndPagination corresponding
     * to the query.
     *
     * @param query the query of the entityWithServiceImplAndPagination search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/entity-with-service-impl-and-paginations")
    @Timed
    public ResponseEntity<List<EntityWithServiceImplAndPagination>> searchEntityWithServiceImplAndPaginations(@RequestParam String query, @ApiParam Pageable pageable) {
        log.debug("REST request to search for a page of EntityWithServiceImplAndPaginations for query {}", query);
        Page<EntityWithServiceImplAndPagination> page = entityWithServiceImplAndPaginationService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/entity-with-service-impl-and-paginations");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
