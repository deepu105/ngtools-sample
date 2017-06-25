package com.mycompany.myapp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.mycompany.myapp.domain.FieldTestServiceImplEntity;
import com.mycompany.myapp.service.FieldTestServiceImplEntityService;
import com.mycompany.myapp.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * REST controller for managing FieldTestServiceImplEntity.
 */
@RestController
@RequestMapping("/api")
public class FieldTestServiceImplEntityResource {

    private final Logger log = LoggerFactory.getLogger(FieldTestServiceImplEntityResource.class);

    private static final String ENTITY_NAME = "fieldTestServiceImplEntity";

    private final FieldTestServiceImplEntityService fieldTestServiceImplEntityService;

    public FieldTestServiceImplEntityResource(FieldTestServiceImplEntityService fieldTestServiceImplEntityService) {
        this.fieldTestServiceImplEntityService = fieldTestServiceImplEntityService;
    }

    /**
     * POST  /field-test-service-impl-entities : Create a new fieldTestServiceImplEntity.
     *
     * @param fieldTestServiceImplEntity the fieldTestServiceImplEntity to create
     * @return the ResponseEntity with status 201 (Created) and with body the new fieldTestServiceImplEntity, or with status 400 (Bad Request) if the fieldTestServiceImplEntity has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/field-test-service-impl-entities")
    @Timed
    public ResponseEntity<FieldTestServiceImplEntity> createFieldTestServiceImplEntity(@Valid @RequestBody FieldTestServiceImplEntity fieldTestServiceImplEntity) throws URISyntaxException {
        log.debug("REST request to save FieldTestServiceImplEntity : {}", fieldTestServiceImplEntity);
        if (fieldTestServiceImplEntity.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new fieldTestServiceImplEntity cannot already have an ID")).body(null);
        }
        FieldTestServiceImplEntity result = fieldTestServiceImplEntityService.save(fieldTestServiceImplEntity);
        return ResponseEntity.created(new URI("/api/field-test-service-impl-entities/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /field-test-service-impl-entities : Updates an existing fieldTestServiceImplEntity.
     *
     * @param fieldTestServiceImplEntity the fieldTestServiceImplEntity to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated fieldTestServiceImplEntity,
     * or with status 400 (Bad Request) if the fieldTestServiceImplEntity is not valid,
     * or with status 500 (Internal Server Error) if the fieldTestServiceImplEntity couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/field-test-service-impl-entities")
    @Timed
    public ResponseEntity<FieldTestServiceImplEntity> updateFieldTestServiceImplEntity(@Valid @RequestBody FieldTestServiceImplEntity fieldTestServiceImplEntity) throws URISyntaxException {
        log.debug("REST request to update FieldTestServiceImplEntity : {}", fieldTestServiceImplEntity);
        if (fieldTestServiceImplEntity.getId() == null) {
            return createFieldTestServiceImplEntity(fieldTestServiceImplEntity);
        }
        FieldTestServiceImplEntity result = fieldTestServiceImplEntityService.save(fieldTestServiceImplEntity);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, fieldTestServiceImplEntity.getId().toString()))
            .body(result);
    }

    /**
     * GET  /field-test-service-impl-entities : get all the fieldTestServiceImplEntities.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of fieldTestServiceImplEntities in body
     */
    @GetMapping("/field-test-service-impl-entities")
    @Timed
    public List<FieldTestServiceImplEntity> getAllFieldTestServiceImplEntities() {
        log.debug("REST request to get all FieldTestServiceImplEntities");
        return fieldTestServiceImplEntityService.findAll();
    }

    /**
     * GET  /field-test-service-impl-entities/:id : get the "id" fieldTestServiceImplEntity.
     *
     * @param id the id of the fieldTestServiceImplEntity to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the fieldTestServiceImplEntity, or with status 404 (Not Found)
     */
    @GetMapping("/field-test-service-impl-entities/{id}")
    @Timed
    public ResponseEntity<FieldTestServiceImplEntity> getFieldTestServiceImplEntity(@PathVariable Long id) {
        log.debug("REST request to get FieldTestServiceImplEntity : {}", id);
        FieldTestServiceImplEntity fieldTestServiceImplEntity = fieldTestServiceImplEntityService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(fieldTestServiceImplEntity));
    }

    /**
     * DELETE  /field-test-service-impl-entities/:id : delete the "id" fieldTestServiceImplEntity.
     *
     * @param id the id of the fieldTestServiceImplEntity to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/field-test-service-impl-entities/{id}")
    @Timed
    public ResponseEntity<Void> deleteFieldTestServiceImplEntity(@PathVariable Long id) {
        log.debug("REST request to delete FieldTestServiceImplEntity : {}", id);
        fieldTestServiceImplEntityService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/field-test-service-impl-entities?query=:query : search for the fieldTestServiceImplEntity corresponding
     * to the query.
     *
     * @param query the query of the fieldTestServiceImplEntity search
     * @return the result of the search
     */
    @GetMapping("/_search/field-test-service-impl-entities")
    @Timed
    public List<FieldTestServiceImplEntity> searchFieldTestServiceImplEntities(@RequestParam String query) {
        log.debug("REST request to search FieldTestServiceImplEntities for query {}", query);
        return fieldTestServiceImplEntityService.search(query);
    }

}
