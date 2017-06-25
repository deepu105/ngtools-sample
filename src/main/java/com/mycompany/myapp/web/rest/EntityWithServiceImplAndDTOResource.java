package com.mycompany.myapp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.mycompany.myapp.service.EntityWithServiceImplAndDTOService;
import com.mycompany.myapp.web.rest.util.HeaderUtil;
import com.mycompany.myapp.service.dto.EntityWithServiceImplAndDTODTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * REST controller for managing EntityWithServiceImplAndDTO.
 */
@RestController
@RequestMapping("/api")
public class EntityWithServiceImplAndDTOResource {

    private final Logger log = LoggerFactory.getLogger(EntityWithServiceImplAndDTOResource.class);

    private static final String ENTITY_NAME = "entityWithServiceImplAndDTO";

    private final EntityWithServiceImplAndDTOService entityWithServiceImplAndDTOService;

    public EntityWithServiceImplAndDTOResource(EntityWithServiceImplAndDTOService entityWithServiceImplAndDTOService) {
        this.entityWithServiceImplAndDTOService = entityWithServiceImplAndDTOService;
    }

    /**
     * POST  /entity-with-service-impl-and-dtos : Create a new entityWithServiceImplAndDTO.
     *
     * @param entityWithServiceImplAndDTODTO the entityWithServiceImplAndDTODTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new entityWithServiceImplAndDTODTO, or with status 400 (Bad Request) if the entityWithServiceImplAndDTO has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/entity-with-service-impl-and-dtos")
    @Timed
    public ResponseEntity<EntityWithServiceImplAndDTODTO> createEntityWithServiceImplAndDTO(@RequestBody EntityWithServiceImplAndDTODTO entityWithServiceImplAndDTODTO) throws URISyntaxException {
        log.debug("REST request to save EntityWithServiceImplAndDTO : {}", entityWithServiceImplAndDTODTO);
        if (entityWithServiceImplAndDTODTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new entityWithServiceImplAndDTO cannot already have an ID")).body(null);
        }
        EntityWithServiceImplAndDTODTO result = entityWithServiceImplAndDTOService.save(entityWithServiceImplAndDTODTO);
        return ResponseEntity.created(new URI("/api/entity-with-service-impl-and-dtos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /entity-with-service-impl-and-dtos : Updates an existing entityWithServiceImplAndDTO.
     *
     * @param entityWithServiceImplAndDTODTO the entityWithServiceImplAndDTODTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated entityWithServiceImplAndDTODTO,
     * or with status 400 (Bad Request) if the entityWithServiceImplAndDTODTO is not valid,
     * or with status 500 (Internal Server Error) if the entityWithServiceImplAndDTODTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/entity-with-service-impl-and-dtos")
    @Timed
    public ResponseEntity<EntityWithServiceImplAndDTODTO> updateEntityWithServiceImplAndDTO(@RequestBody EntityWithServiceImplAndDTODTO entityWithServiceImplAndDTODTO) throws URISyntaxException {
        log.debug("REST request to update EntityWithServiceImplAndDTO : {}", entityWithServiceImplAndDTODTO);
        if (entityWithServiceImplAndDTODTO.getId() == null) {
            return createEntityWithServiceImplAndDTO(entityWithServiceImplAndDTODTO);
        }
        EntityWithServiceImplAndDTODTO result = entityWithServiceImplAndDTOService.save(entityWithServiceImplAndDTODTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, entityWithServiceImplAndDTODTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /entity-with-service-impl-and-dtos : get all the entityWithServiceImplAndDTOS.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of entityWithServiceImplAndDTOS in body
     */
    @GetMapping("/entity-with-service-impl-and-dtos")
    @Timed
    public List<EntityWithServiceImplAndDTODTO> getAllEntityWithServiceImplAndDTOS() {
        log.debug("REST request to get all EntityWithServiceImplAndDTOS");
        return entityWithServiceImplAndDTOService.findAll();
    }

    /**
     * GET  /entity-with-service-impl-and-dtos/:id : get the "id" entityWithServiceImplAndDTO.
     *
     * @param id the id of the entityWithServiceImplAndDTODTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the entityWithServiceImplAndDTODTO, or with status 404 (Not Found)
     */
    @GetMapping("/entity-with-service-impl-and-dtos/{id}")
    @Timed
    public ResponseEntity<EntityWithServiceImplAndDTODTO> getEntityWithServiceImplAndDTO(@PathVariable Long id) {
        log.debug("REST request to get EntityWithServiceImplAndDTO : {}", id);
        EntityWithServiceImplAndDTODTO entityWithServiceImplAndDTODTO = entityWithServiceImplAndDTOService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(entityWithServiceImplAndDTODTO));
    }

    /**
     * DELETE  /entity-with-service-impl-and-dtos/:id : delete the "id" entityWithServiceImplAndDTO.
     *
     * @param id the id of the entityWithServiceImplAndDTODTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/entity-with-service-impl-and-dtos/{id}")
    @Timed
    public ResponseEntity<Void> deleteEntityWithServiceImplAndDTO(@PathVariable Long id) {
        log.debug("REST request to delete EntityWithServiceImplAndDTO : {}", id);
        entityWithServiceImplAndDTOService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/entity-with-service-impl-and-dtos?query=:query : search for the entityWithServiceImplAndDTO corresponding
     * to the query.
     *
     * @param query the query of the entityWithServiceImplAndDTO search
     * @return the result of the search
     */
    @GetMapping("/_search/entity-with-service-impl-and-dtos")
    @Timed
    public List<EntityWithServiceImplAndDTODTO> searchEntityWithServiceImplAndDTOS(@RequestParam String query) {
        log.debug("REST request to search EntityWithServiceImplAndDTOS for query {}", query);
        return entityWithServiceImplAndDTOService.search(query);
    }

}
