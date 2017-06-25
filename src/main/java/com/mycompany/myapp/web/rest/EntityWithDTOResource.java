package com.mycompany.myapp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.mycompany.myapp.domain.EntityWithDTO;

import com.mycompany.myapp.repository.EntityWithDTORepository;
import com.mycompany.myapp.repository.search.EntityWithDTOSearchRepository;
import com.mycompany.myapp.web.rest.util.HeaderUtil;
import com.mycompany.myapp.service.dto.EntityWithDTODTO;
import com.mycompany.myapp.service.mapper.EntityWithDTOMapper;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
 * REST controller for managing EntityWithDTO.
 */
@RestController
@RequestMapping("/api")
public class EntityWithDTOResource {

    private final Logger log = LoggerFactory.getLogger(EntityWithDTOResource.class);

    private static final String ENTITY_NAME = "entityWithDTO";

    private final EntityWithDTORepository entityWithDTORepository;

    private final EntityWithDTOMapper entityWithDTOMapper;

    private final EntityWithDTOSearchRepository entityWithDTOSearchRepository;

    public EntityWithDTOResource(EntityWithDTORepository entityWithDTORepository, EntityWithDTOMapper entityWithDTOMapper, EntityWithDTOSearchRepository entityWithDTOSearchRepository) {
        this.entityWithDTORepository = entityWithDTORepository;
        this.entityWithDTOMapper = entityWithDTOMapper;
        this.entityWithDTOSearchRepository = entityWithDTOSearchRepository;
    }

    /**
     * POST  /entity-with-dtos : Create a new entityWithDTO.
     *
     * @param entityWithDTODTO the entityWithDTODTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new entityWithDTODTO, or with status 400 (Bad Request) if the entityWithDTO has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/entity-with-dtos")
    @Timed
    public ResponseEntity<EntityWithDTODTO> createEntityWithDTO(@RequestBody EntityWithDTODTO entityWithDTODTO) throws URISyntaxException {
        log.debug("REST request to save EntityWithDTO : {}", entityWithDTODTO);
        if (entityWithDTODTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new entityWithDTO cannot already have an ID")).body(null);
        }
        EntityWithDTO entityWithDTO = entityWithDTOMapper.toEntity(entityWithDTODTO);
        entityWithDTO = entityWithDTORepository.save(entityWithDTO);
        EntityWithDTODTO result = entityWithDTOMapper.toDto(entityWithDTO);
        entityWithDTOSearchRepository.save(entityWithDTO);
        return ResponseEntity.created(new URI("/api/entity-with-dtos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /entity-with-dtos : Updates an existing entityWithDTO.
     *
     * @param entityWithDTODTO the entityWithDTODTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated entityWithDTODTO,
     * or with status 400 (Bad Request) if the entityWithDTODTO is not valid,
     * or with status 500 (Internal Server Error) if the entityWithDTODTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/entity-with-dtos")
    @Timed
    public ResponseEntity<EntityWithDTODTO> updateEntityWithDTO(@RequestBody EntityWithDTODTO entityWithDTODTO) throws URISyntaxException {
        log.debug("REST request to update EntityWithDTO : {}", entityWithDTODTO);
        if (entityWithDTODTO.getId() == null) {
            return createEntityWithDTO(entityWithDTODTO);
        }
        EntityWithDTO entityWithDTO = entityWithDTOMapper.toEntity(entityWithDTODTO);
        entityWithDTO = entityWithDTORepository.save(entityWithDTO);
        EntityWithDTODTO result = entityWithDTOMapper.toDto(entityWithDTO);
        entityWithDTOSearchRepository.save(entityWithDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, entityWithDTODTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /entity-with-dtos : get all the entityWithDTOS.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of entityWithDTOS in body
     */
    @GetMapping("/entity-with-dtos")
    @Timed
    public List<EntityWithDTODTO> getAllEntityWithDTOS() {
        log.debug("REST request to get all EntityWithDTOS");
        List<EntityWithDTO> entityWithDTOS = entityWithDTORepository.findAll();
        return entityWithDTOMapper.toDto(entityWithDTOS);
    }

    /**
     * GET  /entity-with-dtos/:id : get the "id" entityWithDTO.
     *
     * @param id the id of the entityWithDTODTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the entityWithDTODTO, or with status 404 (Not Found)
     */
    @GetMapping("/entity-with-dtos/{id}")
    @Timed
    public ResponseEntity<EntityWithDTODTO> getEntityWithDTO(@PathVariable Long id) {
        log.debug("REST request to get EntityWithDTO : {}", id);
        EntityWithDTO entityWithDTO = entityWithDTORepository.findOne(id);
        EntityWithDTODTO entityWithDTODTO = entityWithDTOMapper.toDto(entityWithDTO);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(entityWithDTODTO));
    }

    /**
     * DELETE  /entity-with-dtos/:id : delete the "id" entityWithDTO.
     *
     * @param id the id of the entityWithDTODTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/entity-with-dtos/{id}")
    @Timed
    public ResponseEntity<Void> deleteEntityWithDTO(@PathVariable Long id) {
        log.debug("REST request to delete EntityWithDTO : {}", id);
        entityWithDTORepository.delete(id);
        entityWithDTOSearchRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/entity-with-dtos?query=:query : search for the entityWithDTO corresponding
     * to the query.
     *
     * @param query the query of the entityWithDTO search
     * @return the result of the search
     */
    @GetMapping("/_search/entity-with-dtos")
    @Timed
    public List<EntityWithDTODTO> searchEntityWithDTOS(@RequestParam String query) {
        log.debug("REST request to search EntityWithDTOS for query {}", query);
        return StreamSupport
            .stream(entityWithDTOSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(entityWithDTOMapper::toDto)
            .collect(Collectors.toList());
    }

}
