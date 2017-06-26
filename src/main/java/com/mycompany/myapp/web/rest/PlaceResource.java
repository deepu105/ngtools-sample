package com.mycompany.myapp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.mycompany.myapp.domain.Place;

import com.mycompany.myapp.repository.PlaceRepository;
import com.mycompany.myapp.repository.search.PlaceSearchRepository;
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
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * REST controller for managing Place.
 */
@RestController
@RequestMapping("/api")
public class PlaceResource {

    private final Logger log = LoggerFactory.getLogger(PlaceResource.class);

    private static final String ENTITY_NAME = "place";

    private final PlaceRepository placeRepository;

    private final PlaceSearchRepository placeSearchRepository;

    public PlaceResource(PlaceRepository placeRepository, PlaceSearchRepository placeSearchRepository) {
        this.placeRepository = placeRepository;
        this.placeSearchRepository = placeSearchRepository;
    }

    /**
     * POST  /places : Create a new place.
     *
     * @param place the place to create
     * @return the ResponseEntity with status 201 (Created) and with body the new place, or with status 400 (Bad Request) if the place has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/places")
    @Timed
    public ResponseEntity<Place> createPlace(@Valid @RequestBody Place place) throws URISyntaxException {
        log.debug("REST request to save Place : {}", place);
        if (place.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new place cannot already have an ID")).body(null);
        }
        Place result = placeRepository.save(place);
        placeSearchRepository.save(result);
        return ResponseEntity.created(new URI("/api/places/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /places : Updates an existing place.
     *
     * @param place the place to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated place,
     * or with status 400 (Bad Request) if the place is not valid,
     * or with status 500 (Internal Server Error) if the place couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/places")
    @Timed
    public ResponseEntity<Place> updatePlace(@Valid @RequestBody Place place) throws URISyntaxException {
        log.debug("REST request to update Place : {}", place);
        if (place.getId() == null) {
            return createPlace(place);
        }
        Place result = placeRepository.save(place);
        placeSearchRepository.save(result);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, place.getId().toString()))
            .body(result);
    }

    /**
     * GET  /places : get all the places.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of places in body
     */
    @GetMapping("/places")
    @Timed
    public List<Place> getAllPlaces() {
        log.debug("REST request to get all Places");
        return placeRepository.findAllWithEagerRelationships();
    }

    /**
     * GET  /places/:id : get the "id" place.
     *
     * @param id the id of the place to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the place, or with status 404 (Not Found)
     */
    @GetMapping("/places/{id}")
    @Timed
    public ResponseEntity<Place> getPlace(@PathVariable Long id) {
        log.debug("REST request to get Place : {}", id);
        Place place = placeRepository.findOneWithEagerRelationships(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(place));
    }

    /**
     * DELETE  /places/:id : delete the "id" place.
     *
     * @param id the id of the place to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/places/{id}")
    @Timed
    public ResponseEntity<Void> deletePlace(@PathVariable Long id) {
        log.debug("REST request to delete Place : {}", id);
        placeRepository.delete(id);
        placeSearchRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/places?query=:query : search for the place corresponding
     * to the query.
     *
     * @param query the query of the place search
     * @return the result of the search
     */
    @GetMapping("/_search/places")
    @Timed
    public List<Place> searchPlaces(@RequestParam String query) {
        log.debug("REST request to search Places for query {}", query);
        return StreamSupport
            .stream(placeSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .collect(Collectors.toList());
    }

}
