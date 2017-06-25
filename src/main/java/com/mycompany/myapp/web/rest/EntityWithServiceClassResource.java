package com.mycompany.myapp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.mycompany.myapp.domain.EntityWithServiceClass;
import com.mycompany.myapp.service.EntityWithServiceClassService;
import com.mycompany.myapp.web.rest.util.HeaderUtil;
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
 * REST controller for managing EntityWithServiceClass.
 */
@RestController
@RequestMapping("/api")
public class EntityWithServiceClassResource {

    private final Logger log = LoggerFactory.getLogger(EntityWithServiceClassResource.class);

    private static final String ENTITY_NAME = "entityWithServiceClass";

    private final EntityWithServiceClassService entityWithServiceClassService;

    public EntityWithServiceClassResource(EntityWithServiceClassService entityWithServiceClassService) {
        this.entityWithServiceClassService = entityWithServiceClassService;
    }

    /**
     * POST  /entity-with-service-classes : Create a new entityWithServiceClass.
     *
     * @param entityWithServiceClass the entityWithServiceClass to create
     * @return the ResponseEntity with status 201 (Created) and with body the new entityWithServiceClass, or with status 400 (Bad Request) if the entityWithServiceClass has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/entity-with-service-classes")
    @Timed
    public ResponseEntity<EntityWithServiceClass> createEntityWithServiceClass(@RequestBody EntityWithServiceClass entityWithServiceClass) throws URISyntaxException {
        log.debug("REST request to save EntityWithServiceClass : {}", entityWithServiceClass);
        if (entityWithServiceClass.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new entityWithServiceClass cannot already have an ID")).body(null);
        }
        EntityWithServiceClass result = entityWithServiceClassService.save(entityWithServiceClass);
        return ResponseEntity.created(new URI("/api/entity-with-service-classes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /entity-with-service-classes : Updates an existing entityWithServiceClass.
     *
     * @param entityWithServiceClass the entityWithServiceClass to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated entityWithServiceClass,
     * or with status 400 (Bad Request) if the entityWithServiceClass is not valid,
     * or with status 500 (Internal Server Error) if the entityWithServiceClass couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/entity-with-service-classes")
    @Timed
    public ResponseEntity<EntityWithServiceClass> updateEntityWithServiceClass(@RequestBody EntityWithServiceClass entityWithServiceClass) throws URISyntaxException {
        log.debug("REST request to update EntityWithServiceClass : {}", entityWithServiceClass);
        if (entityWithServiceClass.getId() == null) {
            return createEntityWithServiceClass(entityWithServiceClass);
        }
        EntityWithServiceClass result = entityWithServiceClassService.save(entityWithServiceClass);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, entityWithServiceClass.getId().toString()))
            .body(result);
    }

    /**
     * GET  /entity-with-service-classes : get all the entityWithServiceClasses.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of entityWithServiceClasses in body
     */
    @GetMapping("/entity-with-service-classes")
    @Timed
    public List<EntityWithServiceClass> getAllEntityWithServiceClasses() {
        log.debug("REST request to get all EntityWithServiceClasses");
        return entityWithServiceClassService.findAll();
    }

    /**
     * GET  /entity-with-service-classes/:id : get the "id" entityWithServiceClass.
     *
     * @param id the id of the entityWithServiceClass to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the entityWithServiceClass, or with status 404 (Not Found)
     */
    @GetMapping("/entity-with-service-classes/{id}")
    @Timed
    public ResponseEntity<EntityWithServiceClass> getEntityWithServiceClass(@PathVariable Long id) {
        log.debug("REST request to get EntityWithServiceClass : {}", id);
        EntityWithServiceClass entityWithServiceClass = entityWithServiceClassService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(entityWithServiceClass));
    }

    /**
     * DELETE  /entity-with-service-classes/:id : delete the "id" entityWithServiceClass.
     *
     * @param id the id of the entityWithServiceClass to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/entity-with-service-classes/{id}")
    @Timed
    public ResponseEntity<Void> deleteEntityWithServiceClass(@PathVariable Long id) {
        log.debug("REST request to delete EntityWithServiceClass : {}", id);
        entityWithServiceClassService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/entity-with-service-classes?query=:query : search for the entityWithServiceClass corresponding
     * to the query.
     *
     * @param query the query of the entityWithServiceClass search
     * @return the result of the search
     */
    @GetMapping("/_search/entity-with-service-classes")
    @Timed
    public List<EntityWithServiceClass> searchEntityWithServiceClasses(@RequestParam String query) {
        log.debug("REST request to search EntityWithServiceClasses for query {}", query);
        return entityWithServiceClassService.search(query);
    }

}
