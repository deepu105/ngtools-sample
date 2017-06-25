package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.Jh4TestApp;

import com.mycompany.myapp.domain.EntityWithServiceImplPaginationAndDTO;
import com.mycompany.myapp.repository.EntityWithServiceImplPaginationAndDTORepository;
import com.mycompany.myapp.service.EntityWithServiceImplPaginationAndDTOService;
import com.mycompany.myapp.repository.search.EntityWithServiceImplPaginationAndDTOSearchRepository;
import com.mycompany.myapp.service.dto.EntityWithServiceImplPaginationAndDTODTO;
import com.mycompany.myapp.service.mapper.EntityWithServiceImplPaginationAndDTOMapper;
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
 * Test class for the EntityWithServiceImplPaginationAndDTOResource REST controller.
 *
 * @see EntityWithServiceImplPaginationAndDTOResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Jh4TestApp.class)
public class EntityWithServiceImplPaginationAndDTOResourceIntTest {

    private static final String DEFAULT_THEO = "AAAAAAAAAA";
    private static final String UPDATED_THEO = "BBBBBBBBBB";

    @Autowired
    private EntityWithServiceImplPaginationAndDTORepository entityWithServiceImplPaginationAndDTORepository;

    @Autowired
    private EntityWithServiceImplPaginationAndDTOMapper entityWithServiceImplPaginationAndDTOMapper;

    @Autowired
    private EntityWithServiceImplPaginationAndDTOService entityWithServiceImplPaginationAndDTOService;

    @Autowired
    private EntityWithServiceImplPaginationAndDTOSearchRepository entityWithServiceImplPaginationAndDTOSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restEntityWithServiceImplPaginationAndDTOMockMvc;

    private EntityWithServiceImplPaginationAndDTO entityWithServiceImplPaginationAndDTO;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        EntityWithServiceImplPaginationAndDTOResource entityWithServiceImplPaginationAndDTOResource = new EntityWithServiceImplPaginationAndDTOResource(entityWithServiceImplPaginationAndDTOService);
        this.restEntityWithServiceImplPaginationAndDTOMockMvc = MockMvcBuilders.standaloneSetup(entityWithServiceImplPaginationAndDTOResource)
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
    public static EntityWithServiceImplPaginationAndDTO createEntity(EntityManager em) {
        EntityWithServiceImplPaginationAndDTO entityWithServiceImplPaginationAndDTO = new EntityWithServiceImplPaginationAndDTO()
            .theo(DEFAULT_THEO);
        return entityWithServiceImplPaginationAndDTO;
    }

    @Before
    public void initTest() {
        entityWithServiceImplPaginationAndDTOSearchRepository.deleteAll();
        entityWithServiceImplPaginationAndDTO = createEntity(em);
    }

    @Test
    @Transactional
    public void createEntityWithServiceImplPaginationAndDTO() throws Exception {
        int databaseSizeBeforeCreate = entityWithServiceImplPaginationAndDTORepository.findAll().size();

        // Create the EntityWithServiceImplPaginationAndDTO
        EntityWithServiceImplPaginationAndDTODTO entityWithServiceImplPaginationAndDTODTO = entityWithServiceImplPaginationAndDTOMapper.toDto(entityWithServiceImplPaginationAndDTO);
        restEntityWithServiceImplPaginationAndDTOMockMvc.perform(post("/api/entity-with-service-impl-pagination-and-dtos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(entityWithServiceImplPaginationAndDTODTO)))
            .andExpect(status().isCreated());

        // Validate the EntityWithServiceImplPaginationAndDTO in the database
        List<EntityWithServiceImplPaginationAndDTO> entityWithServiceImplPaginationAndDTOList = entityWithServiceImplPaginationAndDTORepository.findAll();
        assertThat(entityWithServiceImplPaginationAndDTOList).hasSize(databaseSizeBeforeCreate + 1);
        EntityWithServiceImplPaginationAndDTO testEntityWithServiceImplPaginationAndDTO = entityWithServiceImplPaginationAndDTOList.get(entityWithServiceImplPaginationAndDTOList.size() - 1);
        assertThat(testEntityWithServiceImplPaginationAndDTO.getTheo()).isEqualTo(DEFAULT_THEO);

        // Validate the EntityWithServiceImplPaginationAndDTO in Elasticsearch
        EntityWithServiceImplPaginationAndDTO entityWithServiceImplPaginationAndDTOEs = entityWithServiceImplPaginationAndDTOSearchRepository.findOne(testEntityWithServiceImplPaginationAndDTO.getId());
        assertThat(entityWithServiceImplPaginationAndDTOEs).isEqualToComparingFieldByField(testEntityWithServiceImplPaginationAndDTO);
    }

    @Test
    @Transactional
    public void createEntityWithServiceImplPaginationAndDTOWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = entityWithServiceImplPaginationAndDTORepository.findAll().size();

        // Create the EntityWithServiceImplPaginationAndDTO with an existing ID
        entityWithServiceImplPaginationAndDTO.setId(1L);
        EntityWithServiceImplPaginationAndDTODTO entityWithServiceImplPaginationAndDTODTO = entityWithServiceImplPaginationAndDTOMapper.toDto(entityWithServiceImplPaginationAndDTO);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEntityWithServiceImplPaginationAndDTOMockMvc.perform(post("/api/entity-with-service-impl-pagination-and-dtos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(entityWithServiceImplPaginationAndDTODTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<EntityWithServiceImplPaginationAndDTO> entityWithServiceImplPaginationAndDTOList = entityWithServiceImplPaginationAndDTORepository.findAll();
        assertThat(entityWithServiceImplPaginationAndDTOList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllEntityWithServiceImplPaginationAndDTOS() throws Exception {
        // Initialize the database
        entityWithServiceImplPaginationAndDTORepository.saveAndFlush(entityWithServiceImplPaginationAndDTO);

        // Get all the entityWithServiceImplPaginationAndDTOList
        restEntityWithServiceImplPaginationAndDTOMockMvc.perform(get("/api/entity-with-service-impl-pagination-and-dtos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(entityWithServiceImplPaginationAndDTO.getId().intValue())))
            .andExpect(jsonPath("$.[*].theo").value(hasItem(DEFAULT_THEO.toString())));
    }

    @Test
    @Transactional
    public void getEntityWithServiceImplPaginationAndDTO() throws Exception {
        // Initialize the database
        entityWithServiceImplPaginationAndDTORepository.saveAndFlush(entityWithServiceImplPaginationAndDTO);

        // Get the entityWithServiceImplPaginationAndDTO
        restEntityWithServiceImplPaginationAndDTOMockMvc.perform(get("/api/entity-with-service-impl-pagination-and-dtos/{id}", entityWithServiceImplPaginationAndDTO.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(entityWithServiceImplPaginationAndDTO.getId().intValue()))
            .andExpect(jsonPath("$.theo").value(DEFAULT_THEO.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingEntityWithServiceImplPaginationAndDTO() throws Exception {
        // Get the entityWithServiceImplPaginationAndDTO
        restEntityWithServiceImplPaginationAndDTOMockMvc.perform(get("/api/entity-with-service-impl-pagination-and-dtos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEntityWithServiceImplPaginationAndDTO() throws Exception {
        // Initialize the database
        entityWithServiceImplPaginationAndDTORepository.saveAndFlush(entityWithServiceImplPaginationAndDTO);
        entityWithServiceImplPaginationAndDTOSearchRepository.save(entityWithServiceImplPaginationAndDTO);
        int databaseSizeBeforeUpdate = entityWithServiceImplPaginationAndDTORepository.findAll().size();

        // Update the entityWithServiceImplPaginationAndDTO
        EntityWithServiceImplPaginationAndDTO updatedEntityWithServiceImplPaginationAndDTO = entityWithServiceImplPaginationAndDTORepository.findOne(entityWithServiceImplPaginationAndDTO.getId());
        updatedEntityWithServiceImplPaginationAndDTO
            .theo(UPDATED_THEO);
        EntityWithServiceImplPaginationAndDTODTO entityWithServiceImplPaginationAndDTODTO = entityWithServiceImplPaginationAndDTOMapper.toDto(updatedEntityWithServiceImplPaginationAndDTO);

        restEntityWithServiceImplPaginationAndDTOMockMvc.perform(put("/api/entity-with-service-impl-pagination-and-dtos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(entityWithServiceImplPaginationAndDTODTO)))
            .andExpect(status().isOk());

        // Validate the EntityWithServiceImplPaginationAndDTO in the database
        List<EntityWithServiceImplPaginationAndDTO> entityWithServiceImplPaginationAndDTOList = entityWithServiceImplPaginationAndDTORepository.findAll();
        assertThat(entityWithServiceImplPaginationAndDTOList).hasSize(databaseSizeBeforeUpdate);
        EntityWithServiceImplPaginationAndDTO testEntityWithServiceImplPaginationAndDTO = entityWithServiceImplPaginationAndDTOList.get(entityWithServiceImplPaginationAndDTOList.size() - 1);
        assertThat(testEntityWithServiceImplPaginationAndDTO.getTheo()).isEqualTo(UPDATED_THEO);

        // Validate the EntityWithServiceImplPaginationAndDTO in Elasticsearch
        EntityWithServiceImplPaginationAndDTO entityWithServiceImplPaginationAndDTOEs = entityWithServiceImplPaginationAndDTOSearchRepository.findOne(testEntityWithServiceImplPaginationAndDTO.getId());
        assertThat(entityWithServiceImplPaginationAndDTOEs).isEqualToComparingFieldByField(testEntityWithServiceImplPaginationAndDTO);
    }

    @Test
    @Transactional
    public void updateNonExistingEntityWithServiceImplPaginationAndDTO() throws Exception {
        int databaseSizeBeforeUpdate = entityWithServiceImplPaginationAndDTORepository.findAll().size();

        // Create the EntityWithServiceImplPaginationAndDTO
        EntityWithServiceImplPaginationAndDTODTO entityWithServiceImplPaginationAndDTODTO = entityWithServiceImplPaginationAndDTOMapper.toDto(entityWithServiceImplPaginationAndDTO);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restEntityWithServiceImplPaginationAndDTOMockMvc.perform(put("/api/entity-with-service-impl-pagination-and-dtos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(entityWithServiceImplPaginationAndDTODTO)))
            .andExpect(status().isCreated());

        // Validate the EntityWithServiceImplPaginationAndDTO in the database
        List<EntityWithServiceImplPaginationAndDTO> entityWithServiceImplPaginationAndDTOList = entityWithServiceImplPaginationAndDTORepository.findAll();
        assertThat(entityWithServiceImplPaginationAndDTOList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteEntityWithServiceImplPaginationAndDTO() throws Exception {
        // Initialize the database
        entityWithServiceImplPaginationAndDTORepository.saveAndFlush(entityWithServiceImplPaginationAndDTO);
        entityWithServiceImplPaginationAndDTOSearchRepository.save(entityWithServiceImplPaginationAndDTO);
        int databaseSizeBeforeDelete = entityWithServiceImplPaginationAndDTORepository.findAll().size();

        // Get the entityWithServiceImplPaginationAndDTO
        restEntityWithServiceImplPaginationAndDTOMockMvc.perform(delete("/api/entity-with-service-impl-pagination-and-dtos/{id}", entityWithServiceImplPaginationAndDTO.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate Elasticsearch is empty
        boolean entityWithServiceImplPaginationAndDTOExistsInEs = entityWithServiceImplPaginationAndDTOSearchRepository.exists(entityWithServiceImplPaginationAndDTO.getId());
        assertThat(entityWithServiceImplPaginationAndDTOExistsInEs).isFalse();

        // Validate the database is empty
        List<EntityWithServiceImplPaginationAndDTO> entityWithServiceImplPaginationAndDTOList = entityWithServiceImplPaginationAndDTORepository.findAll();
        assertThat(entityWithServiceImplPaginationAndDTOList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void searchEntityWithServiceImplPaginationAndDTO() throws Exception {
        // Initialize the database
        entityWithServiceImplPaginationAndDTORepository.saveAndFlush(entityWithServiceImplPaginationAndDTO);
        entityWithServiceImplPaginationAndDTOSearchRepository.save(entityWithServiceImplPaginationAndDTO);

        // Search the entityWithServiceImplPaginationAndDTO
        restEntityWithServiceImplPaginationAndDTOMockMvc.perform(get("/api/_search/entity-with-service-impl-pagination-and-dtos?query=id:" + entityWithServiceImplPaginationAndDTO.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(entityWithServiceImplPaginationAndDTO.getId().intValue())))
            .andExpect(jsonPath("$.[*].theo").value(hasItem(DEFAULT_THEO.toString())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EntityWithServiceImplPaginationAndDTO.class);
        EntityWithServiceImplPaginationAndDTO entityWithServiceImplPaginationAndDTO1 = new EntityWithServiceImplPaginationAndDTO();
        entityWithServiceImplPaginationAndDTO1.setId(1L);
        EntityWithServiceImplPaginationAndDTO entityWithServiceImplPaginationAndDTO2 = new EntityWithServiceImplPaginationAndDTO();
        entityWithServiceImplPaginationAndDTO2.setId(entityWithServiceImplPaginationAndDTO1.getId());
        assertThat(entityWithServiceImplPaginationAndDTO1).isEqualTo(entityWithServiceImplPaginationAndDTO2);
        entityWithServiceImplPaginationAndDTO2.setId(2L);
        assertThat(entityWithServiceImplPaginationAndDTO1).isNotEqualTo(entityWithServiceImplPaginationAndDTO2);
        entityWithServiceImplPaginationAndDTO1.setId(null);
        assertThat(entityWithServiceImplPaginationAndDTO1).isNotEqualTo(entityWithServiceImplPaginationAndDTO2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EntityWithServiceImplPaginationAndDTODTO.class);
        EntityWithServiceImplPaginationAndDTODTO entityWithServiceImplPaginationAndDTODTO1 = new EntityWithServiceImplPaginationAndDTODTO();
        entityWithServiceImplPaginationAndDTODTO1.setId(1L);
        EntityWithServiceImplPaginationAndDTODTO entityWithServiceImplPaginationAndDTODTO2 = new EntityWithServiceImplPaginationAndDTODTO();
        assertThat(entityWithServiceImplPaginationAndDTODTO1).isNotEqualTo(entityWithServiceImplPaginationAndDTODTO2);
        entityWithServiceImplPaginationAndDTODTO2.setId(entityWithServiceImplPaginationAndDTODTO1.getId());
        assertThat(entityWithServiceImplPaginationAndDTODTO1).isEqualTo(entityWithServiceImplPaginationAndDTODTO2);
        entityWithServiceImplPaginationAndDTODTO2.setId(2L);
        assertThat(entityWithServiceImplPaginationAndDTODTO1).isNotEqualTo(entityWithServiceImplPaginationAndDTODTO2);
        entityWithServiceImplPaginationAndDTODTO1.setId(null);
        assertThat(entityWithServiceImplPaginationAndDTODTO1).isNotEqualTo(entityWithServiceImplPaginationAndDTODTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(entityWithServiceImplPaginationAndDTOMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(entityWithServiceImplPaginationAndDTOMapper.fromId(null)).isNull();
    }
}
