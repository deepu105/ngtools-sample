package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.Jh4TestApp;

import com.mycompany.myapp.domain.EntityWithPaginationAndDTO;
import com.mycompany.myapp.repository.EntityWithPaginationAndDTORepository;
import com.mycompany.myapp.repository.search.EntityWithPaginationAndDTOSearchRepository;
import com.mycompany.myapp.service.dto.EntityWithPaginationAndDTODTO;
import com.mycompany.myapp.service.mapper.EntityWithPaginationAndDTOMapper;
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
 * Test class for the EntityWithPaginationAndDTOResource REST controller.
 *
 * @see EntityWithPaginationAndDTOResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Jh4TestApp.class)
public class EntityWithPaginationAndDTOResourceIntTest {

    private static final String DEFAULT_LEA = "AAAAAAAAAA";
    private static final String UPDATED_LEA = "BBBBBBBBBB";

    @Autowired
    private EntityWithPaginationAndDTORepository entityWithPaginationAndDTORepository;

    @Autowired
    private EntityWithPaginationAndDTOMapper entityWithPaginationAndDTOMapper;

    @Autowired
    private EntityWithPaginationAndDTOSearchRepository entityWithPaginationAndDTOSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restEntityWithPaginationAndDTOMockMvc;

    private EntityWithPaginationAndDTO entityWithPaginationAndDTO;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        EntityWithPaginationAndDTOResource entityWithPaginationAndDTOResource = new EntityWithPaginationAndDTOResource(entityWithPaginationAndDTORepository, entityWithPaginationAndDTOMapper, entityWithPaginationAndDTOSearchRepository);
        this.restEntityWithPaginationAndDTOMockMvc = MockMvcBuilders.standaloneSetup(entityWithPaginationAndDTOResource)
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
    public static EntityWithPaginationAndDTO createEntity(EntityManager em) {
        EntityWithPaginationAndDTO entityWithPaginationAndDTO = new EntityWithPaginationAndDTO()
            .lea(DEFAULT_LEA);
        return entityWithPaginationAndDTO;
    }

    @Before
    public void initTest() {
        entityWithPaginationAndDTOSearchRepository.deleteAll();
        entityWithPaginationAndDTO = createEntity(em);
    }

    @Test
    @Transactional
    public void createEntityWithPaginationAndDTO() throws Exception {
        int databaseSizeBeforeCreate = entityWithPaginationAndDTORepository.findAll().size();

        // Create the EntityWithPaginationAndDTO
        EntityWithPaginationAndDTODTO entityWithPaginationAndDTODTO = entityWithPaginationAndDTOMapper.toDto(entityWithPaginationAndDTO);
        restEntityWithPaginationAndDTOMockMvc.perform(post("/api/entity-with-pagination-and-dtos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(entityWithPaginationAndDTODTO)))
            .andExpect(status().isCreated());

        // Validate the EntityWithPaginationAndDTO in the database
        List<EntityWithPaginationAndDTO> entityWithPaginationAndDTOList = entityWithPaginationAndDTORepository.findAll();
        assertThat(entityWithPaginationAndDTOList).hasSize(databaseSizeBeforeCreate + 1);
        EntityWithPaginationAndDTO testEntityWithPaginationAndDTO = entityWithPaginationAndDTOList.get(entityWithPaginationAndDTOList.size() - 1);
        assertThat(testEntityWithPaginationAndDTO.getLea()).isEqualTo(DEFAULT_LEA);

        // Validate the EntityWithPaginationAndDTO in Elasticsearch
        EntityWithPaginationAndDTO entityWithPaginationAndDTOEs = entityWithPaginationAndDTOSearchRepository.findOne(testEntityWithPaginationAndDTO.getId());
        assertThat(entityWithPaginationAndDTOEs).isEqualToComparingFieldByField(testEntityWithPaginationAndDTO);
    }

    @Test
    @Transactional
    public void createEntityWithPaginationAndDTOWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = entityWithPaginationAndDTORepository.findAll().size();

        // Create the EntityWithPaginationAndDTO with an existing ID
        entityWithPaginationAndDTO.setId(1L);
        EntityWithPaginationAndDTODTO entityWithPaginationAndDTODTO = entityWithPaginationAndDTOMapper.toDto(entityWithPaginationAndDTO);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEntityWithPaginationAndDTOMockMvc.perform(post("/api/entity-with-pagination-and-dtos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(entityWithPaginationAndDTODTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<EntityWithPaginationAndDTO> entityWithPaginationAndDTOList = entityWithPaginationAndDTORepository.findAll();
        assertThat(entityWithPaginationAndDTOList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllEntityWithPaginationAndDTOS() throws Exception {
        // Initialize the database
        entityWithPaginationAndDTORepository.saveAndFlush(entityWithPaginationAndDTO);

        // Get all the entityWithPaginationAndDTOList
        restEntityWithPaginationAndDTOMockMvc.perform(get("/api/entity-with-pagination-and-dtos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(entityWithPaginationAndDTO.getId().intValue())))
            .andExpect(jsonPath("$.[*].lea").value(hasItem(DEFAULT_LEA.toString())));
    }

    @Test
    @Transactional
    public void getEntityWithPaginationAndDTO() throws Exception {
        // Initialize the database
        entityWithPaginationAndDTORepository.saveAndFlush(entityWithPaginationAndDTO);

        // Get the entityWithPaginationAndDTO
        restEntityWithPaginationAndDTOMockMvc.perform(get("/api/entity-with-pagination-and-dtos/{id}", entityWithPaginationAndDTO.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(entityWithPaginationAndDTO.getId().intValue()))
            .andExpect(jsonPath("$.lea").value(DEFAULT_LEA.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingEntityWithPaginationAndDTO() throws Exception {
        // Get the entityWithPaginationAndDTO
        restEntityWithPaginationAndDTOMockMvc.perform(get("/api/entity-with-pagination-and-dtos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEntityWithPaginationAndDTO() throws Exception {
        // Initialize the database
        entityWithPaginationAndDTORepository.saveAndFlush(entityWithPaginationAndDTO);
        entityWithPaginationAndDTOSearchRepository.save(entityWithPaginationAndDTO);
        int databaseSizeBeforeUpdate = entityWithPaginationAndDTORepository.findAll().size();

        // Update the entityWithPaginationAndDTO
        EntityWithPaginationAndDTO updatedEntityWithPaginationAndDTO = entityWithPaginationAndDTORepository.findOne(entityWithPaginationAndDTO.getId());
        updatedEntityWithPaginationAndDTO
            .lea(UPDATED_LEA);
        EntityWithPaginationAndDTODTO entityWithPaginationAndDTODTO = entityWithPaginationAndDTOMapper.toDto(updatedEntityWithPaginationAndDTO);

        restEntityWithPaginationAndDTOMockMvc.perform(put("/api/entity-with-pagination-and-dtos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(entityWithPaginationAndDTODTO)))
            .andExpect(status().isOk());

        // Validate the EntityWithPaginationAndDTO in the database
        List<EntityWithPaginationAndDTO> entityWithPaginationAndDTOList = entityWithPaginationAndDTORepository.findAll();
        assertThat(entityWithPaginationAndDTOList).hasSize(databaseSizeBeforeUpdate);
        EntityWithPaginationAndDTO testEntityWithPaginationAndDTO = entityWithPaginationAndDTOList.get(entityWithPaginationAndDTOList.size() - 1);
        assertThat(testEntityWithPaginationAndDTO.getLea()).isEqualTo(UPDATED_LEA);

        // Validate the EntityWithPaginationAndDTO in Elasticsearch
        EntityWithPaginationAndDTO entityWithPaginationAndDTOEs = entityWithPaginationAndDTOSearchRepository.findOne(testEntityWithPaginationAndDTO.getId());
        assertThat(entityWithPaginationAndDTOEs).isEqualToComparingFieldByField(testEntityWithPaginationAndDTO);
    }

    @Test
    @Transactional
    public void updateNonExistingEntityWithPaginationAndDTO() throws Exception {
        int databaseSizeBeforeUpdate = entityWithPaginationAndDTORepository.findAll().size();

        // Create the EntityWithPaginationAndDTO
        EntityWithPaginationAndDTODTO entityWithPaginationAndDTODTO = entityWithPaginationAndDTOMapper.toDto(entityWithPaginationAndDTO);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restEntityWithPaginationAndDTOMockMvc.perform(put("/api/entity-with-pagination-and-dtos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(entityWithPaginationAndDTODTO)))
            .andExpect(status().isCreated());

        // Validate the EntityWithPaginationAndDTO in the database
        List<EntityWithPaginationAndDTO> entityWithPaginationAndDTOList = entityWithPaginationAndDTORepository.findAll();
        assertThat(entityWithPaginationAndDTOList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteEntityWithPaginationAndDTO() throws Exception {
        // Initialize the database
        entityWithPaginationAndDTORepository.saveAndFlush(entityWithPaginationAndDTO);
        entityWithPaginationAndDTOSearchRepository.save(entityWithPaginationAndDTO);
        int databaseSizeBeforeDelete = entityWithPaginationAndDTORepository.findAll().size();

        // Get the entityWithPaginationAndDTO
        restEntityWithPaginationAndDTOMockMvc.perform(delete("/api/entity-with-pagination-and-dtos/{id}", entityWithPaginationAndDTO.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate Elasticsearch is empty
        boolean entityWithPaginationAndDTOExistsInEs = entityWithPaginationAndDTOSearchRepository.exists(entityWithPaginationAndDTO.getId());
        assertThat(entityWithPaginationAndDTOExistsInEs).isFalse();

        // Validate the database is empty
        List<EntityWithPaginationAndDTO> entityWithPaginationAndDTOList = entityWithPaginationAndDTORepository.findAll();
        assertThat(entityWithPaginationAndDTOList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void searchEntityWithPaginationAndDTO() throws Exception {
        // Initialize the database
        entityWithPaginationAndDTORepository.saveAndFlush(entityWithPaginationAndDTO);
        entityWithPaginationAndDTOSearchRepository.save(entityWithPaginationAndDTO);

        // Search the entityWithPaginationAndDTO
        restEntityWithPaginationAndDTOMockMvc.perform(get("/api/_search/entity-with-pagination-and-dtos?query=id:" + entityWithPaginationAndDTO.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(entityWithPaginationAndDTO.getId().intValue())))
            .andExpect(jsonPath("$.[*].lea").value(hasItem(DEFAULT_LEA.toString())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EntityWithPaginationAndDTO.class);
        EntityWithPaginationAndDTO entityWithPaginationAndDTO1 = new EntityWithPaginationAndDTO();
        entityWithPaginationAndDTO1.setId(1L);
        EntityWithPaginationAndDTO entityWithPaginationAndDTO2 = new EntityWithPaginationAndDTO();
        entityWithPaginationAndDTO2.setId(entityWithPaginationAndDTO1.getId());
        assertThat(entityWithPaginationAndDTO1).isEqualTo(entityWithPaginationAndDTO2);
        entityWithPaginationAndDTO2.setId(2L);
        assertThat(entityWithPaginationAndDTO1).isNotEqualTo(entityWithPaginationAndDTO2);
        entityWithPaginationAndDTO1.setId(null);
        assertThat(entityWithPaginationAndDTO1).isNotEqualTo(entityWithPaginationAndDTO2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EntityWithPaginationAndDTODTO.class);
        EntityWithPaginationAndDTODTO entityWithPaginationAndDTODTO1 = new EntityWithPaginationAndDTODTO();
        entityWithPaginationAndDTODTO1.setId(1L);
        EntityWithPaginationAndDTODTO entityWithPaginationAndDTODTO2 = new EntityWithPaginationAndDTODTO();
        assertThat(entityWithPaginationAndDTODTO1).isNotEqualTo(entityWithPaginationAndDTODTO2);
        entityWithPaginationAndDTODTO2.setId(entityWithPaginationAndDTODTO1.getId());
        assertThat(entityWithPaginationAndDTODTO1).isEqualTo(entityWithPaginationAndDTODTO2);
        entityWithPaginationAndDTODTO2.setId(2L);
        assertThat(entityWithPaginationAndDTODTO1).isNotEqualTo(entityWithPaginationAndDTODTO2);
        entityWithPaginationAndDTODTO1.setId(null);
        assertThat(entityWithPaginationAndDTODTO1).isNotEqualTo(entityWithPaginationAndDTODTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(entityWithPaginationAndDTOMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(entityWithPaginationAndDTOMapper.fromId(null)).isNull();
    }
}
