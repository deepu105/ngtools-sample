package com.mycompany.myapp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.mycompany.myapp.domain.FieldTestMapstructEntity;

import com.mycompany.myapp.repository.FieldTestMapstructEntityRepository;
import com.mycompany.myapp.repository.search.FieldTestMapstructEntitySearchRepository;
import com.mycompany.myapp.web.rest.util.HeaderUtil;
import com.mycompany.myapp.service.dto.FieldTestMapstructEntityDTO;
import com.mycompany.myapp.service.mapper.FieldTestMapstructEntityMapper;
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
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * REST controller for managing FieldTestMapstructEntity.
 */
@RestController
@RequestMapping("/api")
public class FieldTestMapstructEntityResource {

    private final Logger log = LoggerFactory.getLogger(FieldTestMapstructEntityResource.class);

    private static final String ENTITY_NAME = "fieldTestMapstructEntity";

    private final FieldTestMapstructEntityRepository fieldTestMapstructEntityRepository;

    private final FieldTestMapstructEntityMapper fieldTestMapstructEntityMapper;

    private final FieldTestMapstructEntitySearchRepository fieldTestMapstructEntitySearchRepository;

    public FieldTestMapstructEntityResource(FieldTestMapstructEntityRepository fieldTestMapstructEntityRepository, FieldTestMapstructEntityMapper fieldTestMapstructEntityMapper, FieldTestMapstructEntitySearchRepository fieldTestMapstructEntitySearchRepository) {
        this.fieldTestMapstructEntityRepository = fieldTestMapstructEntityRepository;
        this.fieldTestMapstructEntityMapper = fieldTestMapstructEntityMapper;
        this.fieldTestMapstructEntitySearchRepository = fieldTestMapstructEntitySearchRepository;
    }

    /**
     * POST  /field-test-mapstruct-entities : Create a new fieldTestMapstructEntity.
     *
     * @param fieldTestMapstructEntityDTO the fieldTestMapstructEntityDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new fieldTestMapstructEntityDTO, or with status 400 (Bad Request) if the fieldTestMapstructEntity has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/field-test-mapstruct-entities")
    @Timed
    public ResponseEntity<FieldTestMapstructEntityDTO> createFieldTestMapstructEntity(@Valid @RequestBody FieldTestMapstructEntityDTO fieldTestMapstructEntityDTO) throws URISyntaxException {
        log.debug("REST request to save FieldTestMapstructEntity : {}", fieldTestMapstructEntityDTO);
        if (fieldTestMapstructEntityDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new fieldTestMapstructEntity cannot already have an ID")).body(null);
        }
        FieldTestMapstructEntity fieldTestMapstructEntity = fieldTestMapstructEntityMapper.toEntity(fieldTestMapstructEntityDTO);
        fieldTestMapstructEntity = fieldTestMapstructEntityRepository.save(fieldTestMapstructEntity);
        FieldTestMapstructEntityDTO result = fieldTestMapstructEntityMapper.toDto(fieldTestMapstructEntity);
        fieldTestMapstructEntitySearchRepository.save(fieldTestMapstructEntity);
        return ResponseEntity.created(new URI("/api/field-test-mapstruct-entities/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /field-test-mapstruct-entities : Updates an existing fieldTestMapstructEntity.
     *
     * @param fieldTestMapstructEntityDTO the fieldTestMapstructEntityDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated fieldTestMapstructEntityDTO,
     * or with status 400 (Bad Request) if the fieldTestMapstructEntityDTO is not valid,
     * or with status 500 (Internal Server Error) if the fieldTestMapstructEntityDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/field-test-mapstruct-entities")
    @Timed
    public ResponseEntity<FieldTestMapstructEntityDTO> updateFieldTestMapstructEntity(@Valid @RequestBody FieldTestMapstructEntityDTO fieldTestMapstructEntityDTO) throws URISyntaxException {
        log.debug("REST request to update FieldTestMapstructEntity : {}", fieldTestMapstructEntityDTO);
        if (fieldTestMapstructEntityDTO.getId() == null) {
            return createFieldTestMapstructEntity(fieldTestMapstructEntityDTO);
        }
        FieldTestMapstructEntity fieldTestMapstructEntity = fieldTestMapstructEntityMapper.toEntity(fieldTestMapstructEntityDTO);
        fieldTestMapstructEntity = fieldTestMapstructEntityRepository.save(fieldTestMapstructEntity);
        FieldTestMapstructEntityDTO result = fieldTestMapstructEntityMapper.toDto(fieldTestMapstructEntity);
        fieldTestMapstructEntitySearchRepository.save(fieldTestMapstructEntity);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, fieldTestMapstructEntityDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /field-test-mapstruct-entities : get all the fieldTestMapstructEntities.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of fieldTestMapstructEntities in body
     */
    @GetMapping("/field-test-mapstruct-entities")
    @Timed
    public List<FieldTestMapstructEntityDTO> getAllFieldTestMapstructEntities() {
        log.debug("REST request to get all FieldTestMapstructEntities");
        List<FieldTestMapstructEntity> fieldTestMapstructEntities = fieldTestMapstructEntityRepository.findAll();
        return fieldTestMapstructEntityMapper.toDto(fieldTestMapstructEntities);
    }

    /**
     * GET  /field-test-mapstruct-entities/:id : get the "id" fieldTestMapstructEntity.
     *
     * @param id the id of the fieldTestMapstructEntityDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the fieldTestMapstructEntityDTO, or with status 404 (Not Found)
     */
    @GetMapping("/field-test-mapstruct-entities/{id}")
    @Timed
    public ResponseEntity<FieldTestMapstructEntityDTO> getFieldTestMapstructEntity(@PathVariable Long id) {
        log.debug("REST request to get FieldTestMapstructEntity : {}", id);
        FieldTestMapstructEntity fieldTestMapstructEntity = fieldTestMapstructEntityRepository.findOne(id);
        FieldTestMapstructEntityDTO fieldTestMapstructEntityDTO = fieldTestMapstructEntityMapper.toDto(fieldTestMapstructEntity);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(fieldTestMapstructEntityDTO));
    }

    /**
     * DELETE  /field-test-mapstruct-entities/:id : delete the "id" fieldTestMapstructEntity.
     *
     * @param id the id of the fieldTestMapstructEntityDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/field-test-mapstruct-entities/{id}")
    @Timed
    public ResponseEntity<Void> deleteFieldTestMapstructEntity(@PathVariable Long id) {
        log.debug("REST request to delete FieldTestMapstructEntity : {}", id);
        fieldTestMapstructEntityRepository.delete(id);
        fieldTestMapstructEntitySearchRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/field-test-mapstruct-entities?query=:query : search for the fieldTestMapstructEntity corresponding
     * to the query.
     *
     * @param query the query of the fieldTestMapstructEntity search
     * @return the result of the search
     */
    @GetMapping("/_search/field-test-mapstruct-entities")
    @Timed
    public List<FieldTestMapstructEntityDTO> searchFieldTestMapstructEntities(@RequestParam String query) {
        log.debug("REST request to search FieldTestMapstructEntities for query {}", query);
        return StreamSupport
            .stream(fieldTestMapstructEntitySearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(fieldTestMapstructEntityMapper::toDto)
            .collect(Collectors.toList());
    }

}
