package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.Jh4TestApp;

import com.mycompany.myapp.domain.EntityWithServiceClassAndDTO;
import com.mycompany.myapp.repository.EntityWithServiceClassAndDTORepository;
import com.mycompany.myapp.service.EntityWithServiceClassAndDTOService;
import com.mycompany.myapp.repository.search.EntityWithServiceClassAndDTOSearchRepository;
import com.mycompany.myapp.service.dto.EntityWithServiceClassAndDTODTO;
import com.mycompany.myapp.service.mapper.EntityWithServiceClassAndDTOMapper;
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
 * Test class for the EntityWithServiceClassAndDTOResource REST controller.
 *
 * @see EntityWithServiceClassAndDTOResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Jh4TestApp.class)
public class EntityWithServiceClassAndDTOResourceIntTest {

    private static final String DEFAULT_LUCAS = "AAAAAAAAAA";
    private static final String UPDATED_LUCAS = "BBBBBBBBBB";

    @Autowired
    private EntityWithServiceClassAndDTORepository entityWithServiceClassAndDTORepository;

    @Autowired
    private EntityWithServiceClassAndDTOMapper entityWithServiceClassAndDTOMapper;

    @Autowired
    private EntityWithServiceClassAndDTOService entityWithServiceClassAndDTOService;

    @Autowired
    private EntityWithServiceClassAndDTOSearchRepository entityWithServiceClassAndDTOSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restEntityWithServiceClassAndDTOMockMvc;

    private EntityWithServiceClassAndDTO entityWithServiceClassAndDTO;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        EntityWithServiceClassAndDTOResource entityWithServiceClassAndDTOResource = new EntityWithServiceClassAndDTOResource(entityWithServiceClassAndDTOService);
        this.restEntityWithServiceClassAndDTOMockMvc = MockMvcBuilders.standaloneSetup(entityWithServiceClassAndDTOResource)
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
    public static EntityWithServiceClassAndDTO createEntity(EntityManager em) {
        EntityWithServiceClassAndDTO entityWithServiceClassAndDTO = new EntityWithServiceClassAndDTO()
            .lucas(DEFAULT_LUCAS);
        return entityWithServiceClassAndDTO;
    }

    @Before
    public void initTest() {
        entityWithServiceClassAndDTOSearchRepository.deleteAll();
        entityWithServiceClassAndDTO = createEntity(em);
    }

    @Test
    @Transactional
    public void createEntityWithServiceClassAndDTO() throws Exception {
        int databaseSizeBeforeCreate = entityWithServiceClassAndDTORepository.findAll().size();

        // Create the EntityWithServiceClassAndDTO
        EntityWithServiceClassAndDTODTO entityWithServiceClassAndDTODTO = entityWithServiceClassAndDTOMapper.toDto(entityWithServiceClassAndDTO);
        restEntityWithServiceClassAndDTOMockMvc.perform(post("/api/entity-with-service-class-and-dtos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(entityWithServiceClassAndDTODTO)))
            .andExpect(status().isCreated());

        // Validate the EntityWithServiceClassAndDTO in the database
        List<EntityWithServiceClassAndDTO> entityWithServiceClassAndDTOList = entityWithServiceClassAndDTORepository.findAll();
        assertThat(entityWithServiceClassAndDTOList).hasSize(databaseSizeBeforeCreate + 1);
        EntityWithServiceClassAndDTO testEntityWithServiceClassAndDTO = entityWithServiceClassAndDTOList.get(entityWithServiceClassAndDTOList.size() - 1);
        assertThat(testEntityWithServiceClassAndDTO.getLucas()).isEqualTo(DEFAULT_LUCAS);

        // Validate the EntityWithServiceClassAndDTO in Elasticsearch
        EntityWithServiceClassAndDTO entityWithServiceClassAndDTOEs = entityWithServiceClassAndDTOSearchRepository.findOne(testEntityWithServiceClassAndDTO.getId());
        assertThat(entityWithServiceClassAndDTOEs).isEqualToComparingFieldByField(testEntityWithServiceClassAndDTO);
    }

    @Test
    @Transactional
    public void createEntityWithServiceClassAndDTOWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = entityWithServiceClassAndDTORepository.findAll().size();

        // Create the EntityWithServiceClassAndDTO with an existing ID
        entityWithServiceClassAndDTO.setId(1L);
        EntityWithServiceClassAndDTODTO entityWithServiceClassAndDTODTO = entityWithServiceClassAndDTOMapper.toDto(entityWithServiceClassAndDTO);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEntityWithServiceClassAndDTOMockMvc.perform(post("/api/entity-with-service-class-and-dtos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(entityWithServiceClassAndDTODTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<EntityWithServiceClassAndDTO> entityWithServiceClassAndDTOList = entityWithServiceClassAndDTORepository.findAll();
        assertThat(entityWithServiceClassAndDTOList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllEntityWithServiceClassAndDTOS() throws Exception {
        // Initialize the database
        entityWithServiceClassAndDTORepository.saveAndFlush(entityWithServiceClassAndDTO);

        // Get all the entityWithServiceClassAndDTOList
        restEntityWithServiceClassAndDTOMockMvc.perform(get("/api/entity-with-service-class-and-dtos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(entityWithServiceClassAndDTO.getId().intValue())))
            .andExpect(jsonPath("$.[*].lucas").value(hasItem(DEFAULT_LUCAS.toString())));
    }

    @Test
    @Transactional
    public void getEntityWithServiceClassAndDTO() throws Exception {
        // Initialize the database
        entityWithServiceClassAndDTORepository.saveAndFlush(entityWithServiceClassAndDTO);

        // Get the entityWithServiceClassAndDTO
        restEntityWithServiceClassAndDTOMockMvc.perform(get("/api/entity-with-service-class-and-dtos/{id}", entityWithServiceClassAndDTO.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(entityWithServiceClassAndDTO.getId().intValue()))
            .andExpect(jsonPath("$.lucas").value(DEFAULT_LUCAS.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingEntityWithServiceClassAndDTO() throws Exception {
        // Get the entityWithServiceClassAndDTO
        restEntityWithServiceClassAndDTOMockMvc.perform(get("/api/entity-with-service-class-and-dtos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEntityWithServiceClassAndDTO() throws Exception {
        // Initialize the database
        entityWithServiceClassAndDTORepository.saveAndFlush(entityWithServiceClassAndDTO);
        entityWithServiceClassAndDTOSearchRepository.save(entityWithServiceClassAndDTO);
        int databaseSizeBeforeUpdate = entityWithServiceClassAndDTORepository.findAll().size();

        // Update the entityWithServiceClassAndDTO
        EntityWithServiceClassAndDTO updatedEntityWithServiceClassAndDTO = entityWithServiceClassAndDTORepository.findOne(entityWithServiceClassAndDTO.getId());
        updatedEntityWithServiceClassAndDTO
            .lucas(UPDATED_LUCAS);
        EntityWithServiceClassAndDTODTO entityWithServiceClassAndDTODTO = entityWithServiceClassAndDTOMapper.toDto(updatedEntityWithServiceClassAndDTO);

        restEntityWithServiceClassAndDTOMockMvc.perform(put("/api/entity-with-service-class-and-dtos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(entityWithServiceClassAndDTODTO)))
            .andExpect(status().isOk());

        // Validate the EntityWithServiceClassAndDTO in the database
        List<EntityWithServiceClassAndDTO> entityWithServiceClassAndDTOList = entityWithServiceClassAndDTORepository.findAll();
        assertThat(entityWithServiceClassAndDTOList).hasSize(databaseSizeBeforeUpdate);
        EntityWithServiceClassAndDTO testEntityWithServiceClassAndDTO = entityWithServiceClassAndDTOList.get(entityWithServiceClassAndDTOList.size() - 1);
        assertThat(testEntityWithServiceClassAndDTO.getLucas()).isEqualTo(UPDATED_LUCAS);

        // Validate the EntityWithServiceClassAndDTO in Elasticsearch
        EntityWithServiceClassAndDTO entityWithServiceClassAndDTOEs = entityWithServiceClassAndDTOSearchRepository.findOne(testEntityWithServiceClassAndDTO.getId());
        assertThat(entityWithServiceClassAndDTOEs).isEqualToComparingFieldByField(testEntityWithServiceClassAndDTO);
    }

    @Test
    @Transactional
    public void updateNonExistingEntityWithServiceClassAndDTO() throws Exception {
        int databaseSizeBeforeUpdate = entityWithServiceClassAndDTORepository.findAll().size();

        // Create the EntityWithServiceClassAndDTO
        EntityWithServiceClassAndDTODTO entityWithServiceClassAndDTODTO = entityWithServiceClassAndDTOMapper.toDto(entityWithServiceClassAndDTO);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restEntityWithServiceClassAndDTOMockMvc.perform(put("/api/entity-with-service-class-and-dtos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(entityWithServiceClassAndDTODTO)))
            .andExpect(status().isCreated());

        // Validate the EntityWithServiceClassAndDTO in the database
        List<EntityWithServiceClassAndDTO> entityWithServiceClassAndDTOList = entityWithServiceClassAndDTORepository.findAll();
        assertThat(entityWithServiceClassAndDTOList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteEntityWithServiceClassAndDTO() throws Exception {
        // Initialize the database
        entityWithServiceClassAndDTORepository.saveAndFlush(entityWithServiceClassAndDTO);
        entityWithServiceClassAndDTOSearchRepository.save(entityWithServiceClassAndDTO);
        int databaseSizeBeforeDelete = entityWithServiceClassAndDTORepository.findAll().size();

        // Get the entityWithServiceClassAndDTO
        restEntityWithServiceClassAndDTOMockMvc.perform(delete("/api/entity-with-service-class-and-dtos/{id}", entityWithServiceClassAndDTO.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate Elasticsearch is empty
        boolean entityWithServiceClassAndDTOExistsInEs = entityWithServiceClassAndDTOSearchRepository.exists(entityWithServiceClassAndDTO.getId());
        assertThat(entityWithServiceClassAndDTOExistsInEs).isFalse();

        // Validate the database is empty
        List<EntityWithServiceClassAndDTO> entityWithServiceClassAndDTOList = entityWithServiceClassAndDTORepository.findAll();
        assertThat(entityWithServiceClassAndDTOList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void searchEntityWithServiceClassAndDTO() throws Exception {
        // Initialize the database
        entityWithServiceClassAndDTORepository.saveAndFlush(entityWithServiceClassAndDTO);
        entityWithServiceClassAndDTOSearchRepository.save(entityWithServiceClassAndDTO);

        // Search the entityWithServiceClassAndDTO
        restEntityWithServiceClassAndDTOMockMvc.perform(get("/api/_search/entity-with-service-class-and-dtos?query=id:" + entityWithServiceClassAndDTO.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(entityWithServiceClassAndDTO.getId().intValue())))
            .andExpect(jsonPath("$.[*].lucas").value(hasItem(DEFAULT_LUCAS.toString())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EntityWithServiceClassAndDTO.class);
        EntityWithServiceClassAndDTO entityWithServiceClassAndDTO1 = new EntityWithServiceClassAndDTO();
        entityWithServiceClassAndDTO1.setId(1L);
        EntityWithServiceClassAndDTO entityWithServiceClassAndDTO2 = new EntityWithServiceClassAndDTO();
        entityWithServiceClassAndDTO2.setId(entityWithServiceClassAndDTO1.getId());
        assertThat(entityWithServiceClassAndDTO1).isEqualTo(entityWithServiceClassAndDTO2);
        entityWithServiceClassAndDTO2.setId(2L);
        assertThat(entityWithServiceClassAndDTO1).isNotEqualTo(entityWithServiceClassAndDTO2);
        entityWithServiceClassAndDTO1.setId(null);
        assertThat(entityWithServiceClassAndDTO1).isNotEqualTo(entityWithServiceClassAndDTO2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EntityWithServiceClassAndDTODTO.class);
        EntityWithServiceClassAndDTODTO entityWithServiceClassAndDTODTO1 = new EntityWithServiceClassAndDTODTO();
        entityWithServiceClassAndDTODTO1.setId(1L);
        EntityWithServiceClassAndDTODTO entityWithServiceClassAndDTODTO2 = new EntityWithServiceClassAndDTODTO();
        assertThat(entityWithServiceClassAndDTODTO1).isNotEqualTo(entityWithServiceClassAndDTODTO2);
        entityWithServiceClassAndDTODTO2.setId(entityWithServiceClassAndDTODTO1.getId());
        assertThat(entityWithServiceClassAndDTODTO1).isEqualTo(entityWithServiceClassAndDTODTO2);
        entityWithServiceClassAndDTODTO2.setId(2L);
        assertThat(entityWithServiceClassAndDTODTO1).isNotEqualTo(entityWithServiceClassAndDTODTO2);
        entityWithServiceClassAndDTODTO1.setId(null);
        assertThat(entityWithServiceClassAndDTODTO1).isNotEqualTo(entityWithServiceClassAndDTODTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(entityWithServiceClassAndDTOMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(entityWithServiceClassAndDTOMapper.fromId(null)).isNull();
    }
}
