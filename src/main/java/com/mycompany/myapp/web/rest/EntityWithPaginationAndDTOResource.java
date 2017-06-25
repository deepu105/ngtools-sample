package com.mycompany.myapp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.mycompany.myapp.domain.EntityWithPaginationAndDTO;

import com.mycompany.myapp.repository.EntityWithPaginationAndDTORepository;
import com.mycompany.myapp.repository.search.EntityWithPaginationAndDTOSearchRepository;
import com.mycompany.myapp.web.rest.util.HeaderUtil;
import com.mycompany.myapp.web.rest.util.PaginationUtil;
import com.mycompany.myapp.service.dto.EntityWithPaginationAndDTODTO;
import com.mycompany.myapp.service.mapper.EntityWithPaginationAndDTOMapper;
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
 * REST controller for managing EntityWithPaginationAndDTO.
 */
@RestController
@RequestMapping("/api")
public class EntityWithPaginationAndDTOResource {

    private final Logger log = LoggerFactory.getLogger(EntityWithPaginationAndDTOResource.class);

    private static final String ENTITY_NAME = "entityWithPaginationAndDTO";

    private final EntityWithPaginationAndDTORepository entityWithPaginationAndDTORepository;

    private final EntityWithPaginationAndDTOMapper entityWithPaginationAndDTOMapper;

    private final EntityWithPaginationAndDTOSearchRepository entityWithPaginationAndDTOSearchRepository;

    public EntityWithPaginationAndDTOResource(EntityWithPaginationAndDTORepository entityWithPaginationAndDTORepository, EntityWithPaginationAndDTOMapper entityWithPaginationAndDTOMapper, EntityWithPaginationAndDTOSearchRepository entityWithPaginationAndDTOSearchRepository) {
        this.entityWithPaginationAndDTORepository = entityWithPaginationAndDTORepository;
        this.entityWithPaginationAndDTOMapper = entityWithPaginationAndDTOMapper;
        this.entityWithPaginationAndDTOSearchRepository = entityWithPaginationAndDTOSearchRepository;
    }

    /**
     * POST  /entity-with-pagination-and-dtos : Create a new entityWithPaginationAndDTO.
     *
     * @param entityWithPaginationAndDTODTO the entityWithPaginationAndDTODTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new entityWithPaginationAndDTODTO, or with status 400 (Bad Request) if the entityWithPaginationAndDTO has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/entity-with-pagination-and-dtos")
    @Timed
    public ResponseEntity<EntityWithPaginationAndDTODTO> createEntityWithPaginationAndDTO(@RequestBody EntityWithPaginationAndDTODTO entityWithPaginationAndDTODTO) throws URISyntaxException {
        log.debug("REST request to save EntityWithPaginationAndDTO : {}", entityWithPaginationAndDTODTO);
        if (entityWithPaginationAndDTODTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new entityWithPaginationAndDTO cannot already have an ID")).body(null);
        }
        EntityWithPaginationAndDTO entityWithPaginationAndDTO = entityWithPaginationAndDTOMapper.toEntity(entityWithPaginationAndDTODTO);
        entityWithPaginationAndDTO = entityWithPaginationAndDTORepository.save(entityWithPaginationAndDTO);
        EntityWithPaginationAndDTODTO result = entityWithPaginationAndDTOMapper.toDto(entityWithPaginationAndDTO);
        entityWithPaginationAndDTOSearchRepository.save(entityWithPaginationAndDTO);
        return ResponseEntity.created(new URI("/api/entity-with-pagination-and-dtos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /entity-with-pagination-and-dtos : Updates an existing entityWithPaginationAndDTO.
     *
     * @param entityWithPaginationAndDTODTO the entityWithPaginationAndDTODTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated entityWithPaginationAndDTODTO,
     * or with status 400 (Bad Request) if the entityWithPaginationAndDTODTO is not valid,
     * or with status 500 (Internal Server Error) if the entityWithPaginationAndDTODTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/entity-with-pagination-and-dtos")
    @Timed
    public ResponseEntity<EntityWithPaginationAndDTODTO> updateEntityWithPaginationAndDTO(@RequestBody EntityWithPaginationAndDTODTO entityWithPaginationAndDTODTO) throws URISyntaxException {
        log.debug("REST request to update EntityWithPaginationAndDTO : {}", entityWithPaginationAndDTODTO);
        if (entityWithPaginationAndDTODTO.getId() == null) {
            return createEntityWithPaginationAndDTO(entityWithPaginationAndDTODTO);
        }
        EntityWithPaginationAndDTO entityWithPaginationAndDTO = entityWithPaginationAndDTOMapper.toEntity(entityWithPaginationAndDTODTO);
        entityWithPaginationAndDTO = entityWithPaginationAndDTORepository.save(entityWithPaginationAndDTO);
        EntityWithPaginationAndDTODTO result = entityWithPaginationAndDTOMapper.toDto(entityWithPaginationAndDTO);
        entityWithPaginationAndDTOSearchRepository.save(entityWithPaginationAndDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, entityWithPaginationAndDTODTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /entity-with-pagination-and-dtos : get all the entityWithPaginationAndDTOS.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of entityWithPaginationAndDTOS in body
     */
    @GetMapping("/entity-with-pagination-and-dtos")
    @Timed
    public ResponseEntity<List<EntityWithPaginationAndDTODTO>> getAllEntityWithPaginationAndDTOS(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of EntityWithPaginationAndDTOS");
        Page<EntityWithPaginationAndDTO> page = entityWithPaginationAndDTORepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/entity-with-pagination-and-dtos");
        return new ResponseEntity<>(entityWithPaginationAndDTOMapper.toDto(page.getContent()), headers, HttpStatus.OK);
    }

    /**
     * GET  /entity-with-pagination-and-dtos/:id : get the "id" entityWithPaginationAndDTO.
     *
     * @param id the id of the entityWithPaginationAndDTODTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the entityWithPaginationAndDTODTO, or with status 404 (Not Found)
     */
    @GetMapping("/entity-with-pagination-and-dtos/{id}")
    @Timed
    public ResponseEntity<EntityWithPaginationAndDTODTO> getEntityWithPaginationAndDTO(@PathVariable Long id) {
        log.debug("REST request to get EntityWithPaginationAndDTO : {}", id);
        EntityWithPaginationAndDTO entityWithPaginationAndDTO = entityWithPaginationAndDTORepository.findOne(id);
        EntityWithPaginationAndDTODTO entityWithPaginationAndDTODTO = entityWithPaginationAndDTOMapper.toDto(entityWithPaginationAndDTO);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(entityWithPaginationAndDTODTO));
    }

    /**
     * DELETE  /entity-with-pagination-and-dtos/:id : delete the "id" entityWithPaginationAndDTO.
     *
     * @param id the id of the entityWithPaginationAndDTODTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/entity-with-pagination-and-dtos/{id}")
    @Timed
    public ResponseEntity<Void> deleteEntityWithPaginationAndDTO(@PathVariable Long id) {
        log.debug("REST request to delete EntityWithPaginationAndDTO : {}", id);
        entityWithPaginationAndDTORepository.delete(id);
        entityWithPaginationAndDTOSearchRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/entity-with-pagination-and-dtos?query=:query : search for the entityWithPaginationAndDTO corresponding
     * to the query.
     *
     * @param query the query of the entityWithPaginationAndDTO search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/entity-with-pagination-and-dtos")
    @Timed
    public ResponseEntity<List<EntityWithPaginationAndDTODTO>> searchEntityWithPaginationAndDTOS(@RequestParam String query, @ApiParam Pageable pageable) {
        log.debug("REST request to search for a page of EntityWithPaginationAndDTOS for query {}", query);
        Page<EntityWithPaginationAndDTO> page = entityWithPaginationAndDTOSearchRepository.search(queryStringQuery(query), pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/entity-with-pagination-and-dtos");
        return new ResponseEntity<>(entityWithPaginationAndDTOMapper.toDto(page.getContent()), headers, HttpStatus.OK);
    }

}
