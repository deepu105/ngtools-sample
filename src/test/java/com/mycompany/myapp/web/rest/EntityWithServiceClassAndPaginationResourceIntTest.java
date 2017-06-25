package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.Jh4TestApp;

import com.mycompany.myapp.domain.EntityWithServiceClassAndPagination;
import com.mycompany.myapp.repository.EntityWithServiceClassAndPaginationRepository;
import com.mycompany.myapp.service.EntityWithServiceClassAndPaginationService;
import com.mycompany.myapp.repository.search.EntityWithServiceClassAndPaginationSearchRepository;
import com.mycompany.myapp.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the EntityWithServiceClassAndPaginationResource REST controller.
 *
 * @see EntityWithServiceClassAndPaginationResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Jh4TestApp.class)
public class EntityWithServiceClassAndPaginationResourceIntTest {

    private static final String DEFAULT_ENZO = "AAAAAAAAAA";
    private static final String UPDATED_ENZO = "BBBBBBBBBB";

    @Autowired
    private EntityWithServiceClassAndPaginationRepository entityWithServiceClassAndPaginationRepository;

    @Autowired
    private EntityWithServiceClassAndPaginationService entityWithServiceClassAndPaginationService;

    @Autowired
    private EntityWithServiceClassAndPaginationSearchRepository entityWithServiceClassAndPaginationSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restEntityWithServiceClassAndPaginationMockMvc;

    private EntityWithServiceClassAndPagination entityWithServiceClassAndPagination;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        EntityWithServiceClassAndPaginationResource entityWithServiceClassAndPaginationResource = new EntityWithServiceClassAndPaginationResource(entityWithServiceClassAndPaginationService);
        this.restEntityWithServiceClassAndPaginationMockMvc = MockMvcBuilders.standaloneSetup(entityWithServiceClassAndPaginationResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EntityWithServiceClassAndPagination createEntity(EntityManager em) {
        EntityWithServiceClassAndPagination entityWithServiceClassAndPagination = new EntityWithServiceClassAndPagination()
            .enzo(DEFAULT_ENZO);
        return entityWithServiceClassAndPagination;
    }

    @Before
    public void initTest() {
        entityWithServiceClassAndPaginationSearchRepository.deleteAll();
        entityWithServiceClassAndPagination = createEntity(em);
    }

    @Test
    @Transactional
    public void createEntityWithServiceClassAndPagination() throws Exception {
        int databaseSizeBeforeCreate = entityWithServiceClassAndPaginationRepository.findAll().size();

        // Create the EntityWithServiceClassAndPagination
        restEntityWithServiceClassAndPaginationMockMvc.perform(post("/api/entity-with-service-class-and-paginations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(entityWithServiceClassAndPagination)))
            .andExpect(status().isCreated());

        // Validate the EntityWithServiceClassAndPagination in the database
        List<EntityWithServiceClassAndPagination> entityWithServiceClassAndPaginationList = entityWithServiceClassAndPaginationRepository.findAll();
        assertThat(entityWithServiceClassAndPaginationList).hasSize(databaseSizeBeforeCreate + 1);
        EntityWithServiceClassAndPagination testEntityWithServiceClassAndPagination = entityWithServiceClassAndPaginationList.get(entityWithServiceClassAndPaginationList.size() - 1);
        assertThat(testEntityWithServiceClassAndPagination.getEnzo()).isEqualTo(DEFAULT_ENZO);

        // Validate the EntityWithServiceClassAndPagination in Elasticsearch
        EntityWithServiceClassAndPagination entityWithServiceClassAndPaginationEs = entityWithServiceClassAndPaginationSearchRepository.findOne(testEntityWithServiceClassAndPagination.getId());
        assertThat(entityWithServiceClassAndPaginationEs).isEqualToComparingFieldByField(testEntityWithServiceClassAndPagination);
    }

    @Test
    @Transactional
    public void createEntityWithServiceClassAndPaginationWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = entityWithServiceClassAndPaginationRepository.findAll().size();

        // Create the EntityWithServiceClassAndPagination with an existing ID
        entityWithServiceClassAndPagination.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEntityWithServiceClassAndPaginationMockMvc.perform(post("/api/entity-with-service-class-and-paginations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(entityWithServiceClassAndPagination)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<EntityWithServiceClassAndPagination> entityWithServiceClassAndPaginationList = entityWithServiceClassAndPaginationRepository.findAll();
        assertThat(entityWithServiceClassAndPaginationList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllEntityWithServiceClassAndPaginations() throws Exception {
        // Initialize the database
        entityWithServiceClassAndPaginationRepository.saveAndFlush(entityWithServiceClassAndPagination);

        // Get all the entityWithServiceClassAndPaginationList
        restEntityWithServiceClassAndPaginationMockMvc.perform(get("/api/entity-with-service-class-and-paginations?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(entityWithServiceClassAndPagination.getId().intValue())))
            .andExpect(jsonPath("$.[*].enzo").value(hasItem(DEFAULT_ENZO.toString())));
    }

    @Test
    @Transactional
    public void getEntityWithServiceClassAndPagination() throws Exception {
        // Initialize the database
        entityWithServiceClassAndPaginationRepository.saveAndFlush(entityWithServiceClassAndPagination);

        // Get the entityWithServiceClassAndPagination
        restEntityWithServiceClassAndPaginationMockMvc.perform(get("/api/entity-with-service-class-and-paginations/{id}", entityWithServiceClassAndPagination.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(entityWithServiceClassAndPagination.getId().intValue()))
            .andExpect(jsonPath("$.enzo").value(DEFAULT_ENZO.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingEntityWithServiceClassAndPagination() throws Exception {
        // Get the entityWithServiceClassAndPagination
        restEntityWithServiceClassAndPaginationMockMvc.perform(get("/api/entity-with-service-class-and-paginations/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEntityWithServiceClassAndPagination() throws Exception {
        // Initialize the database
        entityWithServiceClassAndPaginationService.save(entityWithServiceClassAndPagination);

        int databaseSizeBeforeUpdate = entityWithServiceClassAndPaginationRepository.findAll().size();

        // Update the entityWithServiceClassAndPagination
        EntityWithServiceClassAndPagination updatedEntityWithServiceClassAndPagination = entityWithServiceClassAndPaginationRepository.findOne(entityWithServiceClassAndPagination.getId());
        updatedEntityWithServiceClassAndPagination
            .enzo(UPDATED_ENZO);

        restEntityWithServiceClassAndPaginationMockMvc.perform(put("/api/entity-with-service-class-and-paginations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedEntityWithServiceClassAndPagination)))
            .andExpect(status().isOk());

        // Validate the EntityWithServiceClassAndPagination in the database
        List<EntityWithServiceClassAndPagination> entityWithServiceClassAndPaginationList = entityWithServiceClassAndPaginationRepository.findAll();
        assertThat(entityWithServiceClassAndPaginationList).hasSize(databaseSizeBeforeUpdate);
        EntityWithServiceClassAndPagination testEntityWithServiceClassAndPagination = entityWithServiceClassAndPaginationList.get(entityWithServiceClassAndPaginationList.size() - 1);
        assertThat(testEntityWithServiceClassAndPagination.getEnzo()).isEqualTo(UPDATED_ENZO);

        // Validate the EntityWithServiceClassAndPagination in Elasticsearch
        EntityWithServiceClassAndPagination entityWithServiceClassAndPaginationEs = entityWithServiceClassAndPaginationSearchRepository.findOne(testEntityWithServiceClassAndPagination.getId());
        assertThat(entityWithServiceClassAndPaginationEs).isEqualToComparingFieldByField(testEntityWithServiceClassAndPagination);
    }

    @Test
    @Transactional
    public void updateNonExistingEntityWithServiceClassAndPagination() throws Exception {
        int databaseSizeBeforeUpdate = entityWithServiceClassAndPaginationRepository.findAll().size();

        // Create the EntityWithServiceClassAndPagination

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restEntityWithServiceClassAndPaginationMockMvc.perform(put("/api/entity-with-service-class-and-paginations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(entityWithServiceClassAndPagination)))
            .andExpect(status().isCreated());

        // Validate the EntityWithServiceClassAndPagination in the database
        List<EntityWithServiceClassAndPagination> entityWithServiceClassAndPaginationList = entityWithServiceClassAndPaginationRepository.findAll();
        assertThat(entityWithServiceClassAndPaginationList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteEntityWithServiceClassAndPagination() throws Exception {
        // Initialize the database
        entityWithServiceClassAndPaginationService.save(entityWithServiceClassAndPagination);

        int databaseSizeBeforeDelete = entityWithServiceClassAndPaginationRepository.findAll().size();

        // Get the entityWithServiceClassAndPagination
        restEntityWithServiceClassAndPaginationMockMvc.perform(delete("/api/entity-with-service-class-and-paginations/{id}", entityWithServiceClassAndPagination.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate Elasticsearch is empty
        boolean entityWithServiceClassAndPaginationExistsInEs = entityWithServiceClassAndPaginationSearchRepository.exists(entityWithServiceClassAndPagination.getId());
        assertThat(entityWithServiceClassAndPaginationExistsInEs).isFalse();

        // Validate the database is empty
        List<EntityWithServiceClassAndPagination> entityWithServiceClassAndPaginationList = entityWithServiceClassAndPaginationRepository.findAll();
        assertThat(entityWithServiceClassAndPaginationList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void searchEntityWithServiceClassAndPagination() throws Exception {
        // Initialize the database
        entityWithServiceClassAndPaginationService.save(entityWithServiceClassAndPagination);

        // Search the entityWithServiceClassAndPagination
        restEntityWithServiceClassAndPaginationMockMvc.perform(get("/api/_search/entity-with-service-class-and-paginations?query=id:" + entityWithServiceClassAndPagination.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(entityWithServiceClassAndPagination.getId().intValue())))
            .andExpect(jsonPath("$.[*].enzo").value(hasItem(DEFAULT_ENZO.toString())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EntityWithServiceClassAndPagination.class);
        EntityWithServiceClassAndPagination entityWithServiceClassAndPagination1 = new EntityWithServiceClassAndPagination();
        entityWithServiceClassAndPagination1.setId(1L);
        EntityWithServiceClassAndPagination entityWithServiceClassAndPagination2 = new EntityWithServiceClassAndPagination();
        entityWithServiceClassAndPagination2.setId(entityWithServiceClassAndPagination1.getId());
        assertThat(entityWithServiceClassAndPagination1).isEqualTo(entityWithServiceClassAndPagination2);
        entityWithServiceClassAndPagination2.setId(2L);
        assertThat(entityWithServiceClassAndPagination1).isNotEqualTo(entityWithServiceClassAndPagination2);
        entityWithServiceClassAndPagination1.setId(null);
        assertThat(entityWithServiceClassAndPagination1).isNotEqualTo(entityWithServiceClassAndPagination2);
    }
}
