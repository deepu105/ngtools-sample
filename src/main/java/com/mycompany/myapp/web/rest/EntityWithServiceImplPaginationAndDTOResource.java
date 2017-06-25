package com.mycompany.myapp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.mycompany.myapp.service.EntityWithServiceImplPaginationAndDTOService;
import com.mycompany.myapp.web.rest.util.HeaderUtil;
import com.mycompany.myapp.web.rest.util.PaginationUtil;
import com.mycompany.myapp.service.dto.EntityWithServiceImplPaginationAndDTODTO;
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
 * REST controller for managing EntityWithServiceImplPaginationAndDTO.
 */
@RestController
@RequestMapping("/api")
public class EntityWithServiceImplPaginationAndDTOResource {

    private final Logger log = LoggerFactory.getLogger(EntityWithServiceImplPaginationAndDTOResource.class);

    private static final String ENTITY_NAME = "entityWithServiceImplPaginationAndDTO";

    private final EntityWithServiceImplPaginationAndDTOService entityWithServiceImplPaginationAndDTOService;

    public EntityWithServiceImplPaginationAndDTOResource(EntityWithServiceImplPaginationAndDTOService entityWithServiceImplPaginationAndDTOService) {
        this.entityWithServiceImplPaginationAndDTOService = entityWithServiceImplPaginationAndDTOService;
    }

    /**
     * POST  /entity-with-service-impl-pagination-and-dtos : Create a new entityWithServiceImplPaginationAndDTO.
     *
     * @param entityWithServiceImplPaginationAndDTODTO the entityWithServiceImplPaginationAndDTODTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new entityWithServiceImplPaginationAndDTODTO, or with status 400 (Bad Request) if the entityWithServiceImplPaginationAndDTO has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/entity-with-service-impl-pagination-and-dtos")
    @Timed
    public ResponseEntity<EntityWithServiceImplPaginationAndDTODTO> createEntityWithServiceImplPaginationAndDTO(@RequestBody EntityWithServiceImplPaginationAndDTODTO entityWithServiceImplPaginationAndDTODTO) throws URISyntaxException {
        log.debug("REST request to save EntityWithServiceImplPaginationAndDTO : {}", entityWithServiceImplPaginationAndDTODTO);
        if (entityWithServiceImplPaginationAndDTODTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new entityWithServiceImplPaginationAndDTO cannot already have an ID")).body(null);
        }
        EntityWithServiceImplPaginationAndDTODTO result = entityWithServiceImplPaginationAndDTOService.save(entityWithServiceImplPaginationAndDTODTO);
        return ResponseEntity.created(new URI("/api/entity-with-service-impl-pagination-and-dtos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /entity-with-service-impl-pagination-and-dtos : Updates an existing entityWithServiceImplPaginationAndDTO.
     *
     * @param entityWithServiceImplPaginationAndDTODTO the entityWithServiceImplPaginationAndDTODTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated entityWithServiceImplPaginationAndDTODTO,
     * or with status 400 (Bad Request) if the entityWithServiceImplPaginationAndDTODTO is not valid,
     * or with status 500 (Internal Server Error) if the entityWithServiceImplPaginationAndDTODTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/entity-with-service-impl-pagination-and-dtos")
    @Timed
    public ResponseEntity<EntityWithServiceImplPaginationAndDTODTO> updateEntityWithServiceImplPaginationAndDTO(@RequestBody EntityWithServiceImplPaginationAndDTODTO entityWithServiceImplPaginationAndDTODTO) throws URISyntaxException {
        log.debug("REST request to update EntityWithServiceImplPaginationAndDTO : {}", entityWithServiceImplPaginationAndDTODTO);
        if (entityWithServiceImplPaginationAndDTODTO.getId() == null) {
            return createEntityWithServiceImplPaginationAndDTO(entityWithServiceImplPaginationAndDTODTO);
        }
        EntityWithServiceImplPaginationAndDTODTO result = entityWithServiceImplPaginationAndDTOService.save(entityWithServiceImplPaginationAndDTODTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, entityWithServiceImplPaginationAndDTODTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /entity-with-service-impl-pagination-and-dtos : get all the entityWithServiceImplPaginationAndDTOS.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of entityWithServiceImplPaginationAndDTOS in body
     */
    @GetMapping("/entity-with-service-impl-pagination-and-dtos")
    @Timed
    public ResponseEntity<List<EntityWithServiceImplPaginationAndDTODTO>> getAllEntityWithServiceImplPaginationAndDTOS(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of EntityWithServiceImplPaginationAndDTOS");
        Page<EntityWithServiceImplPaginationAndDTODTO> page = entityWithServiceImplPaginationAndDTOService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/entity-with-service-impl-pagination-and-dtos");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /entity-with-service-impl-pagination-and-dtos/:id : get the "id" entityWithServiceImplPaginationAndDTO.
     *
     * @param id the id of the entityWithServiceImplPaginationAndDTODTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the entityWithServiceImplPaginationAndDTODTO, or with status 404 (Not Found)
     */
    @GetMapping("/entity-with-service-impl-pagination-and-dtos/{id}")
    @Timed
    public ResponseEntity<EntityWithServiceImplPaginationAndDTODTO> getEntityWithServiceImplPaginationAndDTO(@PathVariable Long id) {
        log.debug("REST request to get EntityWithServiceImplPaginationAndDTO : {}", id);
        EntityWithServiceImplPaginationAndDTODTO entityWithServiceImplPaginationAndDTODTO = entityWithServiceImplPaginationAndDTOService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(entityWithServiceImplPaginationAndDTODTO));
    }

    /**
     * DELETE  /entity-with-service-impl-pagination-and-dtos/:id : delete the "id" entityWithServiceImplPaginationAndDTO.
     *
     * @param id the id of the entityWithServiceImplPaginationAndDTODTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/entity-with-service-impl-pagination-and-dtos/{id}")
    @Timed
    public ResponseEntity<Void> deleteEntityWithServiceImplPaginationAndDTO(@PathVariable Long id) {
        log.debug("REST request to delete EntityWithServiceImplPaginationAndDTO : {}", id);
        entityWithServiceImplPaginationAndDTOService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/entity-with-service-impl-pagination-and-dtos?query=:query : search for the entityWithServiceImplPaginationAndDTO corresponding
     * to the query.
     *
     * @param query the query of the entityWithServiceImplPaginationAndDTO search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/entity-with-service-impl-pagination-and-dtos")
    @Timed
    public ResponseEntity<List<EntityWithServiceImplPaginationAndDTODTO>> searchEntityWithServiceImplPaginationAndDTOS(@RequestParam String query, @ApiParam Pageable pageable) {
        log.debug("REST request to search for a page of EntityWithServiceImplPaginationAndDTOS for query {}", query);
        Page<EntityWithServiceImplPaginationAndDTODTO> page = entityWithServiceImplPaginationAndDTOService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/entity-with-service-impl-pagination-and-dtos");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
