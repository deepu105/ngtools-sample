package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.Jh4TestApp;

import com.mycompany.myapp.domain.EntityWithDTO;
import com.mycompany.myapp.repository.EntityWithDTORepository;
import com.mycompany.myapp.repository.search.EntityWithDTOSearchRepository;
import com.mycompany.myapp.service.dto.EntityWithDTODTO;
import com.mycompany.myapp.service.mapper.EntityWithDTOMapper;
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
 * Test class for the EntityWithDTOResource REST controller.
 *
 * @see EntityWithDTOResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Jh4TestApp.class)
public class EntityWithDTOResourceIntTest {

    private static final String DEFAULT_EMMA = "AAAAAAAAAA";
    private static final String UPDATED_EMMA = "BBBBBBBBBB";

    @Autowired
    private EntityWithDTORepository entityWithDTORepository;

    @Autowired
    private EntityWithDTOMapper entityWithDTOMapper;

    @Autowired
    private EntityWithDTOSearchRepository entityWithDTOSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restEntityWithDTOMockMvc;

    private EntityWithDTO entityWithDTO;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        EntityWithDTOResource entityWithDTOResource = new EntityWithDTOResource(entityWithDTORepository, entityWithDTOMapper, entityWithDTOSearchRepository);
        this.restEntityWithDTOMockMvc = MockMvcBuilders.standaloneSetup(entityWithDTOResource)
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
    public static EntityWithDTO createEntity(EntityManager em) {
        EntityWithDTO entityWithDTO = new EntityWithDTO()
            .emma(DEFAULT_EMMA);
        return entityWithDTO;
    }

    @Before
    public void initTest() {
        entityWithDTOSearchRepository.deleteAll();
        entityWithDTO = createEntity(em);
    }

    @Test
    @Transactional
    public void createEntityWithDTO() throws Exception {
        int databaseSizeBeforeCreate = entityWithDTORepository.findAll().size();

        // Create the EntityWithDTO
        EntityWithDTODTO entityWithDTODTO = entityWithDTOMapper.toDto(entityWithDTO);
        restEntityWithDTOMockMvc.perform(post("/api/entity-with-dtos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(entityWithDTODTO)))
            .andExpect(status().isCreated());

        // Validate the EntityWithDTO in the database
        List<EntityWithDTO> entityWithDTOList = entityWithDTORepository.findAll();
        assertThat(entityWithDTOList).hasSize(databaseSizeBeforeCreate + 1);
        EntityWithDTO testEntityWithDTO = entityWithDTOList.get(entityWithDTOList.size() - 1);
        assertThat(testEntityWithDTO.getEmma()).isEqualTo(DEFAULT_EMMA);

        // Validate the EntityWithDTO in Elasticsearch
        EntityWithDTO entityWithDTOEs = entityWithDTOSearchRepository.findOne(testEntityWithDTO.getId());
        assertThat(entityWithDTOEs).isEqualToComparingFieldByField(testEntityWithDTO);
    }

    @Test
    @Transactional
    public void createEntityWithDTOWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = entityWithDTORepository.findAll().size();

        // Create the EntityWithDTO with an existing ID
        entityWithDTO.setId(1L);
        EntityWithDTODTO entityWithDTODTO = entityWithDTOMapper.toDto(entityWithDTO);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEntityWithDTOMockMvc.perform(post("/api/entity-with-dtos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(entityWithDTODTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<EntityWithDTO> entityWithDTOList = entityWithDTORepository.findAll();
        assertThat(entityWithDTOList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllEntityWithDTOS() throws Exception {
        // Initialize the database
        entityWithDTORepository.saveAndFlush(entityWithDTO);

        // Get all the entityWithDTOList
        restEntityWithDTOMockMvc.perform(get("/api/entity-with-dtos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(entityWithDTO.getId().intValue())))
            .andExpect(jsonPath("$.[*].emma").value(hasItem(DEFAULT_EMMA.toString())));
    }

    @Test
    @Transactional
    public void getEntityWithDTO() throws Exception {
        // Initialize the database
        entityWithDTORepository.saveAndFlush(entityWithDTO);

        // Get the entityWithDTO
        restEntityWithDTOMockMvc.perform(get("/api/entity-with-dtos/{id}", entityWithDTO.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(entityWithDTO.getId().intValue()))
            .andExpect(jsonPath("$.emma").value(DEFAULT_EMMA.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingEntityWithDTO() throws Exception {
        // Get the entityWithDTO
        restEntityWithDTOMockMvc.perform(get("/api/entity-with-dtos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEntityWithDTO() throws Exception {
        // Initialize the database
        entityWithDTORepository.saveAndFlush(entityWithDTO);
        entityWithDTOSearchRepository.save(entityWithDTO);
        int databaseSizeBeforeUpdate = entityWithDTORepository.findAll().size();

        // Update the entityWithDTO
        EntityWithDTO updatedEntityWithDTO = entityWithDTORepository.findOne(entityWithDTO.getId());
        updatedEntityWithDTO
            .emma(UPDATED_EMMA);
        EntityWithDTODTO entityWithDTODTO = entityWithDTOMapper.toDto(updatedEntityWithDTO);

        restEntityWithDTOMockMvc.perform(put("/api/entity-with-dtos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(entityWithDTODTO)))
            .andExpect(status().isOk());

        // Validate the EntityWithDTO in the database
        List<EntityWithDTO> entityWithDTOList = entityWithDTORepository.findAll();
        assertThat(entityWithDTOList).hasSize(databaseSizeBeforeUpdate);
        EntityWithDTO testEntityWithDTO = entityWithDTOList.get(entityWithDTOList.size() - 1);
        assertThat(testEntityWithDTO.getEmma()).isEqualTo(UPDATED_EMMA);

        // Validate the EntityWithDTO in Elasticsearch
        EntityWithDTO entityWithDTOEs = entityWithDTOSearchRepository.findOne(testEntityWithDTO.getId());
        assertThat(entityWithDTOEs).isEqualToComparingFieldByField(testEntityWithDTO);
    }

    @Test
    @Transactional
    public void updateNonExistingEntityWithDTO() throws Exception {
        int databaseSizeBeforeUpdate = entityWithDTORepository.findAll().size();

        // Create the EntityWithDTO
        EntityWithDTODTO entityWithDTODTO = entityWithDTOMapper.toDto(entityWithDTO);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restEntityWithDTOMockMvc.perform(put("/api/entity-with-dtos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(entityWithDTODTO)))
            .andExpect(status().isCreated());

        // Validate the EntityWithDTO in the database
        List<EntityWithDTO> entityWithDTOList = entityWithDTORepository.findAll();
        assertThat(entityWithDTOList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteEntityWithDTO() throws Exception {
        // Initialize the database
        entityWithDTORepository.saveAndFlush(entityWithDTO);
        entityWithDTOSearchRepository.save(entityWithDTO);
        int databaseSizeBeforeDelete = entityWithDTORepository.findAll().size();

        // Get the entityWithDTO
        restEntityWithDTOMockMvc.perform(delete("/api/entity-with-dtos/{id}", entityWithDTO.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate Elasticsearch is empty
        boolean entityWithDTOExistsInEs = entityWithDTOSearchRepository.exists(entityWithDTO.getId());
        assertThat(entityWithDTOExistsInEs).isFalse();

        // Validate the database is empty
        List<EntityWithDTO> entityWithDTOList = entityWithDTORepository.findAll();
        assertThat(entityWithDTOList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void searchEntityWithDTO() throws Exception {
        // Initialize the database
        entityWithDTORepository.saveAndFlush(entityWithDTO);
        entityWithDTOSearchRepository.save(entityWithDTO);

        // Search the entityWithDTO
        restEntityWithDTOMockMvc.perform(get("/api/_search/entity-with-dtos?query=id:" + entityWithDTO.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(entityWithDTO.getId().intValue())))
            .andExpect(jsonPath("$.[*].emma").value(hasItem(DEFAULT_EMMA.toString())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EntityWithDTO.class);
        EntityWithDTO entityWithDTO1 = new EntityWithDTO();
        entityWithDTO1.setId(1L);
        EntityWithDTO entityWithDTO2 = new EntityWithDTO();
        entityWithDTO2.setId(entityWithDTO1.getId());
        assertThat(entityWithDTO1).isEqualTo(entityWithDTO2);
        entityWithDTO2.setId(2L);
        assertThat(entityWithDTO1).isNotEqualTo(entityWithDTO2);
        entityWithDTO1.setId(null);
        assertThat(entityWithDTO1).isNotEqualTo(entityWithDTO2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EntityWithDTODTO.class);
        EntityWithDTODTO entityWithDTODTO1 = new EntityWithDTODTO();
        entityWithDTODTO1.setId(1L);
        EntityWithDTODTO entityWithDTODTO2 = new EntityWithDTODTO();
        assertThat(entityWithDTODTO1).isNotEqualTo(entityWithDTODTO2);
        entityWithDTODTO2.setId(entityWithDTODTO1.getId());
        assertThat(entityWithDTODTO1).isEqualTo(entityWithDTODTO2);
        entityWithDTODTO2.setId(2L);
        assertThat(entityWithDTODTO1).isNotEqualTo(entityWithDTODTO2);
        entityWithDTODTO1.setId(null);
        assertThat(entityWithDTODTO1).isNotEqualTo(entityWithDTODTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(entityWithDTOMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(entityWithDTOMapper.fromId(null)).isNull();
    }
}
