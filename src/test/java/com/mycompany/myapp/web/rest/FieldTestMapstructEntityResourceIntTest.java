package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.Jh4TestApp;

import com.mycompany.myapp.domain.FieldTestMapstructEntity;
import com.mycompany.myapp.repository.FieldTestMapstructEntityRepository;
import com.mycompany.myapp.repository.search.FieldTestMapstructEntitySearchRepository;
import com.mycompany.myapp.service.dto.FieldTestMapstructEntityDTO;
import com.mycompany.myapp.service.mapper.FieldTestMapstructEntityMapper;
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
import org.springframework.util.Base64Utils;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.ZoneOffset;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static com.mycompany.myapp.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycompany.myapp.domain.enumeration.EnumFieldClass;
import com.mycompany.myapp.domain.enumeration.EnumRequiredFieldClass;
/**
 * Test class for the FieldTestMapstructEntityResource REST controller.
 *
 * @see FieldTestMapstructEntityResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Jh4TestApp.class)
public class FieldTestMapstructEntityResourceIntTest {

    private static final String DEFAULT_STRING_EVA = "AAAAAAAAAA";
    private static final String UPDATED_STRING_EVA = "BBBBBBBBBB";

    private static final String DEFAULT_STRING_REQUIRED_EVA = "AAAAAAAAAA";
    private static final String UPDATED_STRING_REQUIRED_EVA = "BBBBBBBBBB";

    private static final String DEFAULT_STRING_MINLENGTH_EVA = "AAAAAAAAAA";
    private static final String UPDATED_STRING_MINLENGTH_EVA = "BBBBBBBBBB";

    private static final String DEFAULT_STRING_MAXLENGTH_EVA = "AAAAAAAAAA";
    private static final String UPDATED_STRING_MAXLENGTH_EVA = "BBBBBBBBBB";

    private static final String DEFAULT_STRING_PATTERN_EVA = "AAAAAAAAAA";
    private static final String UPDATED_STRING_PATTERN_EVA = "BBBBBBBBBB";

    private static final Integer DEFAULT_INTEGER_EVA = 1;
    private static final Integer UPDATED_INTEGER_EVA = 2;

    private static final Integer DEFAULT_INTEGER_REQUIRED_EVA = 1;
    private static final Integer UPDATED_INTEGER_REQUIRED_EVA = 2;

    private static final Integer DEFAULT_INTEGER_MIN_EVA = 0;
    private static final Integer UPDATED_INTEGER_MIN_EVA = 1;

    private static final Integer DEFAULT_INTEGER_MAX_EVA = 100;
    private static final Integer UPDATED_INTEGER_MAX_EVA = 99;

    private static final Long DEFAULT_LONG_EVA = 1L;
    private static final Long UPDATED_LONG_EVA = 2L;

    private static final Long DEFAULT_LONG_REQUIRED_EVA = 1L;
    private static final Long UPDATED_LONG_REQUIRED_EVA = 2L;

    private static final Long DEFAULT_LONG_MIN_EVA = 0L;
    private static final Long UPDATED_LONG_MIN_EVA = 1L;

    private static final Long DEFAULT_LONG_MAX_EVA = 100L;
    private static final Long UPDATED_LONG_MAX_EVA = 99L;

    private static final Float DEFAULT_FLOAT_EVA = 1F;
    private static final Float UPDATED_FLOAT_EVA = 2F;

    private static final Float DEFAULT_FLOAT_REQUIRED_EVA = 1F;
    private static final Float UPDATED_FLOAT_REQUIRED_EVA = 2F;

    private static final Float DEFAULT_FLOAT_MIN_EVA = 0F;
    private static final Float UPDATED_FLOAT_MIN_EVA = 1F;

    private static final Float DEFAULT_FLOAT_MAX_EVA = 100F;
    private static final Float UPDATED_FLOAT_MAX_EVA = 99F;

    private static final Double DEFAULT_DOUBLE_REQUIRED_EVA = 1D;
    private static final Double UPDATED_DOUBLE_REQUIRED_EVA = 2D;

    private static final Double DEFAULT_DOUBLE_MIN_EVA = 0D;
    private static final Double UPDATED_DOUBLE_MIN_EVA = 1D;

    private static final Double DEFAULT_DOUBLE_MAX_EVA = 100D;
    private static final Double UPDATED_DOUBLE_MAX_EVA = 99D;

    private static final BigDecimal DEFAULT_BIG_DECIMAL_REQUIRED_EVA = new BigDecimal(1);
    private static final BigDecimal UPDATED_BIG_DECIMAL_REQUIRED_EVA = new BigDecimal(2);

    private static final BigDecimal DEFAULT_BIG_DECIMAL_MIN_EVA = new BigDecimal(0);
    private static final BigDecimal UPDATED_BIG_DECIMAL_MIN_EVA = new BigDecimal(1);

    private static final BigDecimal DEFAULT_BIG_DECIMAL_MAX_EVA = new BigDecimal(100);
    private static final BigDecimal UPDATED_BIG_DECIMAL_MAX_EVA = new BigDecimal(99);

    private static final LocalDate DEFAULT_LOCAL_DATE_EVA = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_LOCAL_DATE_EVA = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_LOCAL_DATE_REQUIRED_EVA = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_LOCAL_DATE_REQUIRED_EVA = LocalDate.now(ZoneId.systemDefault());

    private static final Instant DEFAULT_INSTANT_EVA = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_INSTANT_EVA = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_INSTANTE_REQUIRED_EVA = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_INSTANTE_REQUIRED_EVA = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final ZonedDateTime DEFAULT_ZONED_DATE_TIME_EVA = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_ZONED_DATE_TIME_EVA = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_ZONED_DATE_TIME_REQUIRED_EVA = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_ZONED_DATE_TIME_REQUIRED_EVA = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final Boolean DEFAULT_BOOLEAN_EVA = false;
    private static final Boolean UPDATED_BOOLEAN_EVA = true;

    private static final Boolean DEFAULT_BOOLEAN_REQUIRED_EVA = false;
    private static final Boolean UPDATED_BOOLEAN_REQUIRED_EVA = true;

    private static final EnumFieldClass DEFAULT_ENUM_EVA = EnumFieldClass.ENUM_VALUE_1;
    private static final EnumFieldClass UPDATED_ENUM_EVA = EnumFieldClass.ENUM_VALUE_2;

    private static final EnumRequiredFieldClass DEFAULT_ENUM_REQUIRED_EVA = EnumRequiredFieldClass.ENUM_VALUE_1;
    private static final EnumRequiredFieldClass UPDATED_ENUM_REQUIRED_EVA = EnumRequiredFieldClass.ENUM_VALUE_2;

    private static final byte[] DEFAULT_BYTE_IMAGE_EVA = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_BYTE_IMAGE_EVA = TestUtil.createByteArray(2, "1");
    private static final String DEFAULT_BYTE_IMAGE_EVA_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_BYTE_IMAGE_EVA_CONTENT_TYPE = "image/png";

    private static final byte[] DEFAULT_BYTE_IMAGE_REQUIRED_EVA = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_BYTE_IMAGE_REQUIRED_EVA = TestUtil.createByteArray(2, "1");
    private static final String DEFAULT_BYTE_IMAGE_REQUIRED_EVA_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_BYTE_IMAGE_REQUIRED_EVA_CONTENT_TYPE = "image/png";

    private static final byte[] DEFAULT_BYTE_IMAGE_MINBYTES_EVA = TestUtil.createByteArray(0, "0");
    private static final byte[] UPDATED_BYTE_IMAGE_MINBYTES_EVA = TestUtil.createByteArray(0, "1");
    private static final String DEFAULT_BYTE_IMAGE_MINBYTES_EVA_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_BYTE_IMAGE_MINBYTES_EVA_CONTENT_TYPE = "image/png";

    private static final byte[] DEFAULT_BYTE_IMAGE_MAXBYTES_EVA = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_BYTE_IMAGE_MAXBYTES_EVA = TestUtil.createByteArray(10000, "1");
    private static final String DEFAULT_BYTE_IMAGE_MAXBYTES_EVA_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_BYTE_IMAGE_MAXBYTES_EVA_CONTENT_TYPE = "image/png";

    private static final byte[] DEFAULT_BYTE_ANY_EVA = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_BYTE_ANY_EVA = TestUtil.createByteArray(2, "1");
    private static final String DEFAULT_BYTE_ANY_EVA_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_BYTE_ANY_EVA_CONTENT_TYPE = "image/png";

    private static final byte[] DEFAULT_BYTE_ANY_REQUIRED_EVA = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_BYTE_ANY_REQUIRED_EVA = TestUtil.createByteArray(2, "1");
    private static final String DEFAULT_BYTE_ANY_REQUIRED_EVA_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_BYTE_ANY_REQUIRED_EVA_CONTENT_TYPE = "image/png";

    private static final byte[] DEFAULT_BYTE_ANY_MINBYTES_EVA = TestUtil.createByteArray(0, "0");
    private static final byte[] UPDATED_BYTE_ANY_MINBYTES_EVA = TestUtil.createByteArray(0, "1");
    private static final String DEFAULT_BYTE_ANY_MINBYTES_EVA_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_BYTE_ANY_MINBYTES_EVA_CONTENT_TYPE = "image/png";

    private static final byte[] DEFAULT_BYTE_ANY_MAXBYTES_EVA = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_BYTE_ANY_MAXBYTES_EVA = TestUtil.createByteArray(10000, "1");
    private static final String DEFAULT_BYTE_ANY_MAXBYTES_EVA_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_BYTE_ANY_MAXBYTES_EVA_CONTENT_TYPE = "image/png";

    private static final String DEFAULT_BYTE_TEXT_EVA = "AAAAAAAAAA";
    private static final String UPDATED_BYTE_TEXT_EVA = "BBBBBBBBBB";

    private static final String DEFAULT_BYTE_TEXT_REQUIRED_EVA = "AAAAAAAAAA";
    private static final String UPDATED_BYTE_TEXT_REQUIRED_EVA = "BBBBBBBBBB";

    private static final String DEFAULT_BYTE_TEXT_MINBYTES_EVA = "AAAAAAAAAA";
    private static final String UPDATED_BYTE_TEXT_MINBYTES_EVA = "BBBBBBBBBB";

    private static final String DEFAULT_BYTE_TEXT_MAXBYTES_EVA = "AAAAAAAAAA";
    private static final String UPDATED_BYTE_TEXT_MAXBYTES_EVA = "BBBBBBBBBB";

    @Autowired
    private FieldTestMapstructEntityRepository fieldTestMapstructEntityRepository;

    @Autowired
    private FieldTestMapstructEntityMapper fieldTestMapstructEntityMapper;

    @Autowired
    private FieldTestMapstructEntitySearchRepository fieldTestMapstructEntitySearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restFieldTestMapstructEntityMockMvc;

    private FieldTestMapstructEntity fieldTestMapstructEntity;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        FieldTestMapstructEntityResource fieldTestMapstructEntityResource = new FieldTestMapstructEntityResource(fieldTestMapstructEntityRepository, fieldTestMapstructEntityMapper, fieldTestMapstructEntitySearchRepository);
        this.restFieldTestMapstructEntityMockMvc = MockMvcBuilders.standaloneSetup(fieldTestMapstructEntityResource)
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
    public static FieldTestMapstructEntity createEntity(EntityManager em) {
        FieldTestMapstructEntity fieldTestMapstructEntity = new FieldTestMapstructEntity()
            .stringEva(DEFAULT_STRING_EVA)
            .stringRequiredEva(DEFAULT_STRING_REQUIRED_EVA)
            .stringMinlengthEva(DEFAULT_STRING_MINLENGTH_EVA)
            .stringMaxlengthEva(DEFAULT_STRING_MAXLENGTH_EVA)
            .stringPatternEva(DEFAULT_STRING_PATTERN_EVA)
            .integerEva(DEFAULT_INTEGER_EVA)
            .integerRequiredEva(DEFAULT_INTEGER_REQUIRED_EVA)
            .integerMinEva(DEFAULT_INTEGER_MIN_EVA)
            .integerMaxEva(DEFAULT_INTEGER_MAX_EVA)
            .longEva(DEFAULT_LONG_EVA)
            .longRequiredEva(DEFAULT_LONG_REQUIRED_EVA)
            .longMinEva(DEFAULT_LONG_MIN_EVA)
            .longMaxEva(DEFAULT_LONG_MAX_EVA)
            .floatEva(DEFAULT_FLOAT_EVA)
            .floatRequiredEva(DEFAULT_FLOAT_REQUIRED_EVA)
            .floatMinEva(DEFAULT_FLOAT_MIN_EVA)
            .floatMaxEva(DEFAULT_FLOAT_MAX_EVA)
            .doubleRequiredEva(DEFAULT_DOUBLE_REQUIRED_EVA)
            .doubleMinEva(DEFAULT_DOUBLE_MIN_EVA)
            .doubleMaxEva(DEFAULT_DOUBLE_MAX_EVA)
            .bigDecimalRequiredEva(DEFAULT_BIG_DECIMAL_REQUIRED_EVA)
            .bigDecimalMinEva(DEFAULT_BIG_DECIMAL_MIN_EVA)
            .bigDecimalMaxEva(DEFAULT_BIG_DECIMAL_MAX_EVA)
            .localDateEva(DEFAULT_LOCAL_DATE_EVA)
            .localDateRequiredEva(DEFAULT_LOCAL_DATE_REQUIRED_EVA)
            .instantEva(DEFAULT_INSTANT_EVA)
            .instanteRequiredEva(DEFAULT_INSTANTE_REQUIRED_EVA)
            .zonedDateTimeEva(DEFAULT_ZONED_DATE_TIME_EVA)
            .zonedDateTimeRequiredEva(DEFAULT_ZONED_DATE_TIME_REQUIRED_EVA)
            .booleanEva(DEFAULT_BOOLEAN_EVA)
            .booleanRequiredEva(DEFAULT_BOOLEAN_REQUIRED_EVA)
            .enumEva(DEFAULT_ENUM_EVA)
            .enumRequiredEva(DEFAULT_ENUM_REQUIRED_EVA)
            .byteImageEva(DEFAULT_BYTE_IMAGE_EVA)
            .byteImageEvaContentType(DEFAULT_BYTE_IMAGE_EVA_CONTENT_TYPE)
            .byteImageRequiredEva(DEFAULT_BYTE_IMAGE_REQUIRED_EVA)
            .byteImageRequiredEvaContentType(DEFAULT_BYTE_IMAGE_REQUIRED_EVA_CONTENT_TYPE)
            .byteImageMinbytesEva(DEFAULT_BYTE_IMAGE_MINBYTES_EVA)
            .byteImageMinbytesEvaContentType(DEFAULT_BYTE_IMAGE_MINBYTES_EVA_CONTENT_TYPE)
            .byteImageMaxbytesEva(DEFAULT_BYTE_IMAGE_MAXBYTES_EVA)
            .byteImageMaxbytesEvaContentType(DEFAULT_BYTE_IMAGE_MAXBYTES_EVA_CONTENT_TYPE)
            .byteAnyEva(DEFAULT_BYTE_ANY_EVA)
            .byteAnyEvaContentType(DEFAULT_BYTE_ANY_EVA_CONTENT_TYPE)
            .byteAnyRequiredEva(DEFAULT_BYTE_ANY_REQUIRED_EVA)
            .byteAnyRequiredEvaContentType(DEFAULT_BYTE_ANY_REQUIRED_EVA_CONTENT_TYPE)
            .byteAnyMinbytesEva(DEFAULT_BYTE_ANY_MINBYTES_EVA)
            .byteAnyMinbytesEvaContentType(DEFAULT_BYTE_ANY_MINBYTES_EVA_CONTENT_TYPE)
            .byteAnyMaxbytesEva(DEFAULT_BYTE_ANY_MAXBYTES_EVA)
            .byteAnyMaxbytesEvaContentType(DEFAULT_BYTE_ANY_MAXBYTES_EVA_CONTENT_TYPE)
            .byteTextEva(DEFAULT_BYTE_TEXT_EVA)
            .byteTextRequiredEva(DEFAULT_BYTE_TEXT_REQUIRED_EVA)
            .byteTextMinbytesEva(DEFAULT_BYTE_TEXT_MINBYTES_EVA)
            .byteTextMaxbytesEva(DEFAULT_BYTE_TEXT_MAXBYTES_EVA);
        return fieldTestMapstructEntity;
    }

    @Before
    public void initTest() {
        fieldTestMapstructEntitySearchRepository.deleteAll();
        fieldTestMapstructEntity = createEntity(em);
    }

    @Test
    @Transactional
    public void createFieldTestMapstructEntity() throws Exception {
        int databaseSizeBeforeCreate = fieldTestMapstructEntityRepository.findAll().size();

        // Create the FieldTestMapstructEntity
        FieldTestMapstructEntityDTO fieldTestMapstructEntityDTO = fieldTestMapstructEntityMapper.toDto(fieldTestMapstructEntity);
        restFieldTestMapstructEntityMockMvc.perform(post("/api/field-test-mapstruct-entities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fieldTestMapstructEntityDTO)))
            .andExpect(status().isCreated());

        // Validate the FieldTestMapstructEntity in the database
        List<FieldTestMapstructEntity> fieldTestMapstructEntityList = fieldTestMapstructEntityRepository.findAll();
        assertThat(fieldTestMapstructEntityList).hasSize(databaseSizeBeforeCreate + 1);
        FieldTestMapstructEntity testFieldTestMapstructEntity = fieldTestMapstructEntityList.get(fieldTestMapstructEntityList.size() - 1);
        assertThat(testFieldTestMapstructEntity.getStringEva()).isEqualTo(DEFAULT_STRING_EVA);
        assertThat(testFieldTestMapstructEntity.getStringRequiredEva()).isEqualTo(DEFAULT_STRING_REQUIRED_EVA);
        assertThat(testFieldTestMapstructEntity.getStringMinlengthEva()).isEqualTo(DEFAULT_STRING_MINLENGTH_EVA);
        assertThat(testFieldTestMapstructEntity.getStringMaxlengthEva()).isEqualTo(DEFAULT_STRING_MAXLENGTH_EVA);
        assertThat(testFieldTestMapstructEntity.getStringPatternEva()).isEqualTo(DEFAULT_STRING_PATTERN_EVA);
        assertThat(testFieldTestMapstructEntity.getIntegerEva()).isEqualTo(DEFAULT_INTEGER_EVA);
        assertThat(testFieldTestMapstructEntity.getIntegerRequiredEva()).isEqualTo(DEFAULT_INTEGER_REQUIRED_EVA);
        assertThat(testFieldTestMapstructEntity.getIntegerMinEva()).isEqualTo(DEFAULT_INTEGER_MIN_EVA);
        assertThat(testFieldTestMapstructEntity.getIntegerMaxEva()).isEqualTo(DEFAULT_INTEGER_MAX_EVA);
        assertThat(testFieldTestMapstructEntity.getLongEva()).isEqualTo(DEFAULT_LONG_EVA);
        assertThat(testFieldTestMapstructEntity.getLongRequiredEva()).isEqualTo(DEFAULT_LONG_REQUIRED_EVA);
        assertThat(testFieldTestMapstructEntity.getLongMinEva()).isEqualTo(DEFAULT_LONG_MIN_EVA);
        assertThat(testFieldTestMapstructEntity.getLongMaxEva()).isEqualTo(DEFAULT_LONG_MAX_EVA);
        assertThat(testFieldTestMapstructEntity.getFloatEva()).isEqualTo(DEFAULT_FLOAT_EVA);
        assertThat(testFieldTestMapstructEntity.getFloatRequiredEva()).isEqualTo(DEFAULT_FLOAT_REQUIRED_EVA);
        assertThat(testFieldTestMapstructEntity.getFloatMinEva()).isEqualTo(DEFAULT_FLOAT_MIN_EVA);
        assertThat(testFieldTestMapstructEntity.getFloatMaxEva()).isEqualTo(DEFAULT_FLOAT_MAX_EVA);
        assertThat(testFieldTestMapstructEntity.getDoubleRequiredEva()).isEqualTo(DEFAULT_DOUBLE_REQUIRED_EVA);
        assertThat(testFieldTestMapstructEntity.getDoubleMinEva()).isEqualTo(DEFAULT_DOUBLE_MIN_EVA);
        assertThat(testFieldTestMapstructEntity.getDoubleMaxEva()).isEqualTo(DEFAULT_DOUBLE_MAX_EVA);
        assertThat(testFieldTestMapstructEntity.getBigDecimalRequiredEva()).isEqualTo(DEFAULT_BIG_DECIMAL_REQUIRED_EVA);
        assertThat(testFieldTestMapstructEntity.getBigDecimalMinEva()).isEqualTo(DEFAULT_BIG_DECIMAL_MIN_EVA);
        assertThat(testFieldTestMapstructEntity.getBigDecimalMaxEva()).isEqualTo(DEFAULT_BIG_DECIMAL_MAX_EVA);
        assertThat(testFieldTestMapstructEntity.getLocalDateEva()).isEqualTo(DEFAULT_LOCAL_DATE_EVA);
        assertThat(testFieldTestMapstructEntity.getLocalDateRequiredEva()).isEqualTo(DEFAULT_LOCAL_DATE_REQUIRED_EVA);
        assertThat(testFieldTestMapstructEntity.getInstantEva()).isEqualTo(DEFAULT_INSTANT_EVA);
        assertThat(testFieldTestMapstructEntity.getInstanteRequiredEva()).isEqualTo(DEFAULT_INSTANTE_REQUIRED_EVA);
        assertThat(testFieldTestMapstructEntity.getZonedDateTimeEva()).isEqualTo(DEFAULT_ZONED_DATE_TIME_EVA);
        assertThat(testFieldTestMapstructEntity.getZonedDateTimeRequiredEva()).isEqualTo(DEFAULT_ZONED_DATE_TIME_REQUIRED_EVA);
        assertThat(testFieldTestMapstructEntity.isBooleanEva()).isEqualTo(DEFAULT_BOOLEAN_EVA);
        assertThat(testFieldTestMapstructEntity.isBooleanRequiredEva()).isEqualTo(DEFAULT_BOOLEAN_REQUIRED_EVA);
        assertThat(testFieldTestMapstructEntity.getEnumEva()).isEqualTo(DEFAULT_ENUM_EVA);
        assertThat(testFieldTestMapstructEntity.getEnumRequiredEva()).isEqualTo(DEFAULT_ENUM_REQUIRED_EVA);
        assertThat(testFieldTestMapstructEntity.getByteImageEva()).isEqualTo(DEFAULT_BYTE_IMAGE_EVA);
        assertThat(testFieldTestMapstructEntity.getByteImageEvaContentType()).isEqualTo(DEFAULT_BYTE_IMAGE_EVA_CONTENT_TYPE);
        assertThat(testFieldTestMapstructEntity.getByteImageRequiredEva()).isEqualTo(DEFAULT_BYTE_IMAGE_REQUIRED_EVA);
        assertThat(testFieldTestMapstructEntity.getByteImageRequiredEvaContentType()).isEqualTo(DEFAULT_BYTE_IMAGE_REQUIRED_EVA_CONTENT_TYPE);
        assertThat(testFieldTestMapstructEntity.getByteImageMinbytesEva()).isEqualTo(DEFAULT_BYTE_IMAGE_MINBYTES_EVA);
        assertThat(testFieldTestMapstructEntity.getByteImageMinbytesEvaContentType()).isEqualTo(DEFAULT_BYTE_IMAGE_MINBYTES_EVA_CONTENT_TYPE);
        assertThat(testFieldTestMapstructEntity.getByteImageMaxbytesEva()).isEqualTo(DEFAULT_BYTE_IMAGE_MAXBYTES_EVA);
        assertThat(testFieldTestMapstructEntity.getByteImageMaxbytesEvaContentType()).isEqualTo(DEFAULT_BYTE_IMAGE_MAXBYTES_EVA_CONTENT_TYPE);
        assertThat(testFieldTestMapstructEntity.getByteAnyEva()).isEqualTo(DEFAULT_BYTE_ANY_EVA);
        assertThat(testFieldTestMapstructEntity.getByteAnyEvaContentType()).isEqualTo(DEFAULT_BYTE_ANY_EVA_CONTENT_TYPE);
        assertThat(testFieldTestMapstructEntity.getByteAnyRequiredEva()).isEqualTo(DEFAULT_BYTE_ANY_REQUIRED_EVA);
        assertThat(testFieldTestMapstructEntity.getByteAnyRequiredEvaContentType()).isEqualTo(DEFAULT_BYTE_ANY_REQUIRED_EVA_CONTENT_TYPE);
        assertThat(testFieldTestMapstructEntity.getByteAnyMinbytesEva()).isEqualTo(DEFAULT_BYTE_ANY_MINBYTES_EVA);
        assertThat(testFieldTestMapstructEntity.getByteAnyMinbytesEvaContentType()).isEqualTo(DEFAULT_BYTE_ANY_MINBYTES_EVA_CONTENT_TYPE);
        assertThat(testFieldTestMapstructEntity.getByteAnyMaxbytesEva()).isEqualTo(DEFAULT_BYTE_ANY_MAXBYTES_EVA);
        assertThat(testFieldTestMapstructEntity.getByteAnyMaxbytesEvaContentType()).isEqualTo(DEFAULT_BYTE_ANY_MAXBYTES_EVA_CONTENT_TYPE);
        assertThat(testFieldTestMapstructEntity.getByteTextEva()).isEqualTo(DEFAULT_BYTE_TEXT_EVA);
        assertThat(testFieldTestMapstructEntity.getByteTextRequiredEva()).isEqualTo(DEFAULT_BYTE_TEXT_REQUIRED_EVA);
        assertThat(testFieldTestMapstructEntity.getByteTextMinbytesEva()).isEqualTo(DEFAULT_BYTE_TEXT_MINBYTES_EVA);
        assertThat(testFieldTestMapstructEntity.getByteTextMaxbytesEva()).isEqualTo(DEFAULT_BYTE_TEXT_MAXBYTES_EVA);

        // Validate the FieldTestMapstructEntity in Elasticsearch
        FieldTestMapstructEntity fieldTestMapstructEntityEs = fieldTestMapstructEntitySearchRepository.findOne(testFieldTestMapstructEntity.getId());
        assertThat(fieldTestMapstructEntityEs).isEqualToComparingFieldByField(testFieldTestMapstructEntity);
    }

    @Test
    @Transactional
    public void createFieldTestMapstructEntityWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = fieldTestMapstructEntityRepository.findAll().size();

        // Create the FieldTestMapstructEntity with an existing ID
        fieldTestMapstructEntity.setId(1L);
        FieldTestMapstructEntityDTO fieldTestMapstructEntityDTO = fieldTestMapstructEntityMapper.toDto(fieldTestMapstructEntity);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFieldTestMapstructEntityMockMvc.perform(post("/api/field-test-mapstruct-entities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fieldTestMapstructEntityDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<FieldTestMapstructEntity> fieldTestMapstructEntityList = fieldTestMapstructEntityRepository.findAll();
        assertThat(fieldTestMapstructEntityList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkStringRequiredEvaIsRequired() throws Exception {
        int databaseSizeBeforeTest = fieldTestMapstructEntityRepository.findAll().size();
        // set the field null
        fieldTestMapstructEntity.setStringRequiredEva(null);

        // Create the FieldTestMapstructEntity, which fails.
        FieldTestMapstructEntityDTO fieldTestMapstructEntityDTO = fieldTestMapstructEntityMapper.toDto(fieldTestMapstructEntity);

        restFieldTestMapstructEntityMockMvc.perform(post("/api/field-test-mapstruct-entities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fieldTestMapstructEntityDTO)))
            .andExpect(status().isBadRequest());

        List<FieldTestMapstructEntity> fieldTestMapstructEntityList = fieldTestMapstructEntityRepository.findAll();
        assertThat(fieldTestMapstructEntityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIntegerRequiredEvaIsRequired() throws Exception {
        int databaseSizeBeforeTest = fieldTestMapstructEntityRepository.findAll().size();
        // set the field null
        fieldTestMapstructEntity.setIntegerRequiredEva(null);

        // Create the FieldTestMapstructEntity, which fails.
        FieldTestMapstructEntityDTO fieldTestMapstructEntityDTO = fieldTestMapstructEntityMapper.toDto(fieldTestMapstructEntity);

        restFieldTestMapstructEntityMockMvc.perform(post("/api/field-test-mapstruct-entities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fieldTestMapstructEntityDTO)))
            .andExpect(status().isBadRequest());

        List<FieldTestMapstructEntity> fieldTestMapstructEntityList = fieldTestMapstructEntityRepository.findAll();
        assertThat(fieldTestMapstructEntityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLongRequiredEvaIsRequired() throws Exception {
        int databaseSizeBeforeTest = fieldTestMapstructEntityRepository.findAll().size();
        // set the field null
        fieldTestMapstructEntity.setLongRequiredEva(null);

        // Create the FieldTestMapstructEntity, which fails.
        FieldTestMapstructEntityDTO fieldTestMapstructEntityDTO = fieldTestMapstructEntityMapper.toDto(fieldTestMapstructEntity);

        restFieldTestMapstructEntityMockMvc.perform(post("/api/field-test-mapstruct-entities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fieldTestMapstructEntityDTO)))
            .andExpect(status().isBadRequest());

        List<FieldTestMapstructEntity> fieldTestMapstructEntityList = fieldTestMapstructEntityRepository.findAll();
        assertThat(fieldTestMapstructEntityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFloatRequiredEvaIsRequired() throws Exception {
        int databaseSizeBeforeTest = fieldTestMapstructEntityRepository.findAll().size();
        // set the field null
        fieldTestMapstructEntity.setFloatRequiredEva(null);

        // Create the FieldTestMapstructEntity, which fails.
        FieldTestMapstructEntityDTO fieldTestMapstructEntityDTO = fieldTestMapstructEntityMapper.toDto(fieldTestMapstructEntity);

        restFieldTestMapstructEntityMockMvc.perform(post("/api/field-test-mapstruct-entities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fieldTestMapstructEntityDTO)))
            .andExpect(status().isBadRequest());

        List<FieldTestMapstructEntity> fieldTestMapstructEntityList = fieldTestMapstructEntityRepository.findAll();
        assertThat(fieldTestMapstructEntityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDoubleRequiredEvaIsRequired() throws Exception {
        int databaseSizeBeforeTest = fieldTestMapstructEntityRepository.findAll().size();
        // set the field null
        fieldTestMapstructEntity.setDoubleRequiredEva(null);

        // Create the FieldTestMapstructEntity, which fails.
        FieldTestMapstructEntityDTO fieldTestMapstructEntityDTO = fieldTestMapstructEntityMapper.toDto(fieldTestMapstructEntity);

        restFieldTestMapstructEntityMockMvc.perform(post("/api/field-test-mapstruct-entities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fieldTestMapstructEntityDTO)))
            .andExpect(status().isBadRequest());

        List<FieldTestMapstructEntity> fieldTestMapstructEntityList = fieldTestMapstructEntityRepository.findAll();
        assertThat(fieldTestMapstructEntityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkBigDecimalRequiredEvaIsRequired() throws Exception {
        int databaseSizeBeforeTest = fieldTestMapstructEntityRepository.findAll().size();
        // set the field null
        fieldTestMapstructEntity.setBigDecimalRequiredEva(null);

        // Create the FieldTestMapstructEntity, which fails.
        FieldTestMapstructEntityDTO fieldTestMapstructEntityDTO = fieldTestMapstructEntityMapper.toDto(fieldTestMapstructEntity);

        restFieldTestMapstructEntityMockMvc.perform(post("/api/field-test-mapstruct-entities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fieldTestMapstructEntityDTO)))
            .andExpect(status().isBadRequest());

        List<FieldTestMapstructEntity> fieldTestMapstructEntityList = fieldTestMapstructEntityRepository.findAll();
        assertThat(fieldTestMapstructEntityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLocalDateRequiredEvaIsRequired() throws Exception {
        int databaseSizeBeforeTest = fieldTestMapstructEntityRepository.findAll().size();
        // set the field null
        fieldTestMapstructEntity.setLocalDateRequiredEva(null);

        // Create the FieldTestMapstructEntity, which fails.
        FieldTestMapstructEntityDTO fieldTestMapstructEntityDTO = fieldTestMapstructEntityMapper.toDto(fieldTestMapstructEntity);

        restFieldTestMapstructEntityMockMvc.perform(post("/api/field-test-mapstruct-entities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fieldTestMapstructEntityDTO)))
            .andExpect(status().isBadRequest());

        List<FieldTestMapstructEntity> fieldTestMapstructEntityList = fieldTestMapstructEntityRepository.findAll();
        assertThat(fieldTestMapstructEntityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkInstanteRequiredEvaIsRequired() throws Exception {
        int databaseSizeBeforeTest = fieldTestMapstructEntityRepository.findAll().size();
        // set the field null
        fieldTestMapstructEntity.setInstanteRequiredEva(null);

        // Create the FieldTestMapstructEntity, which fails.
        FieldTestMapstructEntityDTO fieldTestMapstructEntityDTO = fieldTestMapstructEntityMapper.toDto(fieldTestMapstructEntity);

        restFieldTestMapstructEntityMockMvc.perform(post("/api/field-test-mapstruct-entities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fieldTestMapstructEntityDTO)))
            .andExpect(status().isBadRequest());

        List<FieldTestMapstructEntity> fieldTestMapstructEntityList = fieldTestMapstructEntityRepository.findAll();
        assertThat(fieldTestMapstructEntityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkZonedDateTimeRequiredEvaIsRequired() throws Exception {
        int databaseSizeBeforeTest = fieldTestMapstructEntityRepository.findAll().size();
        // set the field null
        fieldTestMapstructEntity.setZonedDateTimeRequiredEva(null);

        // Create the FieldTestMapstructEntity, which fails.
        FieldTestMapstructEntityDTO fieldTestMapstructEntityDTO = fieldTestMapstructEntityMapper.toDto(fieldTestMapstructEntity);

        restFieldTestMapstructEntityMockMvc.perform(post("/api/field-test-mapstruct-entities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fieldTestMapstructEntityDTO)))
            .andExpect(status().isBadRequest());

        List<FieldTestMapstructEntity> fieldTestMapstructEntityList = fieldTestMapstructEntityRepository.findAll();
        assertThat(fieldTestMapstructEntityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkBooleanRequiredEvaIsRequired() throws Exception {
        int databaseSizeBeforeTest = fieldTestMapstructEntityRepository.findAll().size();
        // set the field null
        fieldTestMapstructEntity.setBooleanRequiredEva(null);

        // Create the FieldTestMapstructEntity, which fails.
        FieldTestMapstructEntityDTO fieldTestMapstructEntityDTO = fieldTestMapstructEntityMapper.toDto(fieldTestMapstructEntity);

        restFieldTestMapstructEntityMockMvc.perform(post("/api/field-test-mapstruct-entities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fieldTestMapstructEntityDTO)))
            .andExpect(status().isBadRequest());

        List<FieldTestMapstructEntity> fieldTestMapstructEntityList = fieldTestMapstructEntityRepository.findAll();
        assertThat(fieldTestMapstructEntityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEnumRequiredEvaIsRequired() throws Exception {
        int databaseSizeBeforeTest = fieldTestMapstructEntityRepository.findAll().size();
        // set the field null
        fieldTestMapstructEntity.setEnumRequiredEva(null);

        // Create the FieldTestMapstructEntity, which fails.
        FieldTestMapstructEntityDTO fieldTestMapstructEntityDTO = fieldTestMapstructEntityMapper.toDto(fieldTestMapstructEntity);

        restFieldTestMapstructEntityMockMvc.perform(post("/api/field-test-mapstruct-entities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fieldTestMapstructEntityDTO)))
            .andExpect(status().isBadRequest());

        List<FieldTestMapstructEntity> fieldTestMapstructEntityList = fieldTestMapstructEntityRepository.findAll();
        assertThat(fieldTestMapstructEntityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkByteImageRequiredEvaIsRequired() throws Exception {
        int databaseSizeBeforeTest = fieldTestMapstructEntityRepository.findAll().size();
        // set the field null
        fieldTestMapstructEntity.setByteImageRequiredEva(null);

        // Create the FieldTestMapstructEntity, which fails.
        FieldTestMapstructEntityDTO fieldTestMapstructEntityDTO = fieldTestMapstructEntityMapper.toDto(fieldTestMapstructEntity);

        restFieldTestMapstructEntityMockMvc.perform(post("/api/field-test-mapstruct-entities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fieldTestMapstructEntityDTO)))
            .andExpect(status().isBadRequest());

        List<FieldTestMapstructEntity> fieldTestMapstructEntityList = fieldTestMapstructEntityRepository.findAll();
        assertThat(fieldTestMapstructEntityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkByteAnyRequiredEvaIsRequired() throws Exception {
        int databaseSizeBeforeTest = fieldTestMapstructEntityRepository.findAll().size();
        // set the field null
        fieldTestMapstructEntity.setByteAnyRequiredEva(null);

        // Create the FieldTestMapstructEntity, which fails.
        FieldTestMapstructEntityDTO fieldTestMapstructEntityDTO = fieldTestMapstructEntityMapper.toDto(fieldTestMapstructEntity);

        restFieldTestMapstructEntityMockMvc.perform(post("/api/field-test-mapstruct-entities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fieldTestMapstructEntityDTO)))
            .andExpect(status().isBadRequest());

        List<FieldTestMapstructEntity> fieldTestMapstructEntityList = fieldTestMapstructEntityRepository.findAll();
        assertThat(fieldTestMapstructEntityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkByteTextRequiredEvaIsRequired() throws Exception {
        int databaseSizeBeforeTest = fieldTestMapstructEntityRepository.findAll().size();
        // set the field null
        fieldTestMapstructEntity.setByteTextRequiredEva(null);

        // Create the FieldTestMapstructEntity, which fails.
        FieldTestMapstructEntityDTO fieldTestMapstructEntityDTO = fieldTestMapstructEntityMapper.toDto(fieldTestMapstructEntity);

        restFieldTestMapstructEntityMockMvc.perform(post("/api/field-test-mapstruct-entities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fieldTestMapstructEntityDTO)))
            .andExpect(status().isBadRequest());

        List<FieldTestMapstructEntity> fieldTestMapstructEntityList = fieldTestMapstructEntityRepository.findAll();
        assertThat(fieldTestMapstructEntityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllFieldTestMapstructEntities() throws Exception {
        // Initialize the database
        fieldTestMapstructEntityRepository.saveAndFlush(fieldTestMapstructEntity);

        // Get all the fieldTestMapstructEntityList
        restFieldTestMapstructEntityMockMvc.perform(get("/api/field-test-mapstruct-entities?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(fieldTestMapstructEntity.getId().intValue())))
            .andExpect(jsonPath("$.[*].stringEva").value(hasItem(DEFAULT_STRING_EVA.toString())))
            .andExpect(jsonPath("$.[*].stringRequiredEva").value(hasItem(DEFAULT_STRING_REQUIRED_EVA.toString())))
            .andExpect(jsonPath("$.[*].stringMinlengthEva").value(hasItem(DEFAULT_STRING_MINLENGTH_EVA.toString())))
            .andExpect(jsonPath("$.[*].stringMaxlengthEva").value(hasItem(DEFAULT_STRING_MAXLENGTH_EVA.toString())))
            .andExpect(jsonPath("$.[*].stringPatternEva").value(hasItem(DEFAULT_STRING_PATTERN_EVA.toString())))
            .andExpect(jsonPath("$.[*].integerEva").value(hasItem(DEFAULT_INTEGER_EVA)))
            .andExpect(jsonPath("$.[*].integerRequiredEva").value(hasItem(DEFAULT_INTEGER_REQUIRED_EVA)))
            .andExpect(jsonPath("$.[*].integerMinEva").value(hasItem(DEFAULT_INTEGER_MIN_EVA)))
            .andExpect(jsonPath("$.[*].integerMaxEva").value(hasItem(DEFAULT_INTEGER_MAX_EVA)))
            .andExpect(jsonPath("$.[*].longEva").value(hasItem(DEFAULT_LONG_EVA.intValue())))
            .andExpect(jsonPath("$.[*].longRequiredEva").value(hasItem(DEFAULT_LONG_REQUIRED_EVA.intValue())))
            .andExpect(jsonPath("$.[*].longMinEva").value(hasItem(DEFAULT_LONG_MIN_EVA.intValue())))
            .andExpect(jsonPath("$.[*].longMaxEva").value(hasItem(DEFAULT_LONG_MAX_EVA.intValue())))
            .andExpect(jsonPath("$.[*].floatEva").value(hasItem(DEFAULT_FLOAT_EVA.doubleValue())))
            .andExpect(jsonPath("$.[*].floatRequiredEva").value(hasItem(DEFAULT_FLOAT_REQUIRED_EVA.doubleValue())))
            .andExpect(jsonPath("$.[*].floatMinEva").value(hasItem(DEFAULT_FLOAT_MIN_EVA.doubleValue())))
            .andExpect(jsonPath("$.[*].floatMaxEva").value(hasItem(DEFAULT_FLOAT_MAX_EVA.doubleValue())))
            .andExpect(jsonPath("$.[*].doubleRequiredEva").value(hasItem(DEFAULT_DOUBLE_REQUIRED_EVA.doubleValue())))
            .andExpect(jsonPath("$.[*].doubleMinEva").value(hasItem(DEFAULT_DOUBLE_MIN_EVA.doubleValue())))
            .andExpect(jsonPath("$.[*].doubleMaxEva").value(hasItem(DEFAULT_DOUBLE_MAX_EVA.doubleValue())))
            .andExpect(jsonPath("$.[*].bigDecimalRequiredEva").value(hasItem(DEFAULT_BIG_DECIMAL_REQUIRED_EVA.intValue())))
            .andExpect(jsonPath("$.[*].bigDecimalMinEva").value(hasItem(DEFAULT_BIG_DECIMAL_MIN_EVA.intValue())))
            .andExpect(jsonPath("$.[*].bigDecimalMaxEva").value(hasItem(DEFAULT_BIG_DECIMAL_MAX_EVA.intValue())))
            .andExpect(jsonPath("$.[*].localDateEva").value(hasItem(DEFAULT_LOCAL_DATE_EVA.toString())))
            .andExpect(jsonPath("$.[*].localDateRequiredEva").value(hasItem(DEFAULT_LOCAL_DATE_REQUIRED_EVA.toString())))
            .andExpect(jsonPath("$.[*].instantEva").value(hasItem(DEFAULT_INSTANT_EVA.toString())))
            .andExpect(jsonPath("$.[*].instanteRequiredEva").value(hasItem(DEFAULT_INSTANTE_REQUIRED_EVA.toString())))
            .andExpect(jsonPath("$.[*].zonedDateTimeEva").value(hasItem(sameInstant(DEFAULT_ZONED_DATE_TIME_EVA))))
            .andExpect(jsonPath("$.[*].zonedDateTimeRequiredEva").value(hasItem(sameInstant(DEFAULT_ZONED_DATE_TIME_REQUIRED_EVA))))
            .andExpect(jsonPath("$.[*].booleanEva").value(hasItem(DEFAULT_BOOLEAN_EVA.booleanValue())))
            .andExpect(jsonPath("$.[*].booleanRequiredEva").value(hasItem(DEFAULT_BOOLEAN_REQUIRED_EVA.booleanValue())))
            .andExpect(jsonPath("$.[*].enumEva").value(hasItem(DEFAULT_ENUM_EVA.toString())))
            .andExpect(jsonPath("$.[*].enumRequiredEva").value(hasItem(DEFAULT_ENUM_REQUIRED_EVA.toString())))
            .andExpect(jsonPath("$.[*].byteImageEvaContentType").value(hasItem(DEFAULT_BYTE_IMAGE_EVA_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].byteImageEva").value(hasItem(Base64Utils.encodeToString(DEFAULT_BYTE_IMAGE_EVA))))
            .andExpect(jsonPath("$.[*].byteImageRequiredEvaContentType").value(hasItem(DEFAULT_BYTE_IMAGE_REQUIRED_EVA_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].byteImageRequiredEva").value(hasItem(Base64Utils.encodeToString(DEFAULT_BYTE_IMAGE_REQUIRED_EVA))))
            .andExpect(jsonPath("$.[*].byteImageMinbytesEvaContentType").value(hasItem(DEFAULT_BYTE_IMAGE_MINBYTES_EVA_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].byteImageMinbytesEva").value(hasItem(Base64Utils.encodeToString(DEFAULT_BYTE_IMAGE_MINBYTES_EVA))))
            .andExpect(jsonPath("$.[*].byteImageMaxbytesEvaContentType").value(hasItem(DEFAULT_BYTE_IMAGE_MAXBYTES_EVA_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].byteImageMaxbytesEva").value(hasItem(Base64Utils.encodeToString(DEFAULT_BYTE_IMAGE_MAXBYTES_EVA))))
            .andExpect(jsonPath("$.[*].byteAnyEvaContentType").value(hasItem(DEFAULT_BYTE_ANY_EVA_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].byteAnyEva").value(hasItem(Base64Utils.encodeToString(DEFAULT_BYTE_ANY_EVA))))
            .andExpect(jsonPath("$.[*].byteAnyRequiredEvaContentType").value(hasItem(DEFAULT_BYTE_ANY_REQUIRED_EVA_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].byteAnyRequiredEva").value(hasItem(Base64Utils.encodeToString(DEFAULT_BYTE_ANY_REQUIRED_EVA))))
            .andExpect(jsonPath("$.[*].byteAnyMinbytesEvaContentType").value(hasItem(DEFAULT_BYTE_ANY_MINBYTES_EVA_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].byteAnyMinbytesEva").value(hasItem(Base64Utils.encodeToString(DEFAULT_BYTE_ANY_MINBYTES_EVA))))
            .andExpect(jsonPath("$.[*].byteAnyMaxbytesEvaContentType").value(hasItem(DEFAULT_BYTE_ANY_MAXBYTES_EVA_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].byteAnyMaxbytesEva").value(hasItem(Base64Utils.encodeToString(DEFAULT_BYTE_ANY_MAXBYTES_EVA))))
            .andExpect(jsonPath("$.[*].byteTextEva").value(hasItem(DEFAULT_BYTE_TEXT_EVA.toString())))
            .andExpect(jsonPath("$.[*].byteTextRequiredEva").value(hasItem(DEFAULT_BYTE_TEXT_REQUIRED_EVA.toString())))
            .andExpect(jsonPath("$.[*].byteTextMinbytesEva").value(hasItem(DEFAULT_BYTE_TEXT_MINBYTES_EVA.toString())))
            .andExpect(jsonPath("$.[*].byteTextMaxbytesEva").value(hasItem(DEFAULT_BYTE_TEXT_MAXBYTES_EVA.toString())));
    }

    @Test
    @Transactional
    public void getFieldTestMapstructEntity() throws Exception {
        // Initialize the database
        fieldTestMapstructEntityRepository.saveAndFlush(fieldTestMapstructEntity);

        // Get the fieldTestMapstructEntity
        restFieldTestMapstructEntityMockMvc.perform(get("/api/field-test-mapstruct-entities/{id}", fieldTestMapstructEntity.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(fieldTestMapstructEntity.getId().intValue()))
            .andExpect(jsonPath("$.stringEva").value(DEFAULT_STRING_EVA.toString()))
            .andExpect(jsonPath("$.stringRequiredEva").value(DEFAULT_STRING_REQUIRED_EVA.toString()))
            .andExpect(jsonPath("$.stringMinlengthEva").value(DEFAULT_STRING_MINLENGTH_EVA.toString()))
            .andExpect(jsonPath("$.stringMaxlengthEva").value(DEFAULT_STRING_MAXLENGTH_EVA.toString()))
            .andExpect(jsonPath("$.stringPatternEva").value(DEFAULT_STRING_PATTERN_EVA.toString()))
            .andExpect(jsonPath("$.integerEva").value(DEFAULT_INTEGER_EVA))
            .andExpect(jsonPath("$.integerRequiredEva").value(DEFAULT_INTEGER_REQUIRED_EVA))
            .andExpect(jsonPath("$.integerMinEva").value(DEFAULT_INTEGER_MIN_EVA))
            .andExpect(jsonPath("$.integerMaxEva").value(DEFAULT_INTEGER_MAX_EVA))
            .andExpect(jsonPath("$.longEva").value(DEFAULT_LONG_EVA.intValue()))
            .andExpect(jsonPath("$.longRequiredEva").value(DEFAULT_LONG_REQUIRED_EVA.intValue()))
            .andExpect(jsonPath("$.longMinEva").value(DEFAULT_LONG_MIN_EVA.intValue()))
            .andExpect(jsonPath("$.longMaxEva").value(DEFAULT_LONG_MAX_EVA.intValue()))
            .andExpect(jsonPath("$.floatEva").value(DEFAULT_FLOAT_EVA.doubleValue()))
            .andExpect(jsonPath("$.floatRequiredEva").value(DEFAULT_FLOAT_REQUIRED_EVA.doubleValue()))
            .andExpect(jsonPath("$.floatMinEva").value(DEFAULT_FLOAT_MIN_EVA.doubleValue()))
            .andExpect(jsonPath("$.floatMaxEva").value(DEFAULT_FLOAT_MAX_EVA.doubleValue()))
            .andExpect(jsonPath("$.doubleRequiredEva").value(DEFAULT_DOUBLE_REQUIRED_EVA.doubleValue()))
            .andExpect(jsonPath("$.doubleMinEva").value(DEFAULT_DOUBLE_MIN_EVA.doubleValue()))
            .andExpect(jsonPath("$.doubleMaxEva").value(DEFAULT_DOUBLE_MAX_EVA.doubleValue()))
            .andExpect(jsonPath("$.bigDecimalRequiredEva").value(DEFAULT_BIG_DECIMAL_REQUIRED_EVA.intValue()))
            .andExpect(jsonPath("$.bigDecimalMinEva").value(DEFAULT_BIG_DECIMAL_MIN_EVA.intValue()))
            .andExpect(jsonPath("$.bigDecimalMaxEva").value(DEFAULT_BIG_DECIMAL_MAX_EVA.intValue()))
            .andExpect(jsonPath("$.localDateEva").value(DEFAULT_LOCAL_DATE_EVA.toString()))
            .andExpect(jsonPath("$.localDateRequiredEva").value(DEFAULT_LOCAL_DATE_REQUIRED_EVA.toString()))
            .andExpect(jsonPath("$.instantEva").value(DEFAULT_INSTANT_EVA.toString()))
            .andExpect(jsonPath("$.instanteRequiredEva").value(DEFAULT_INSTANTE_REQUIRED_EVA.toString()))
            .andExpect(jsonPath("$.zonedDateTimeEva").value(sameInstant(DEFAULT_ZONED_DATE_TIME_EVA)))
            .andExpect(jsonPath("$.zonedDateTimeRequiredEva").value(sameInstant(DEFAULT_ZONED_DATE_TIME_REQUIRED_EVA)))
            .andExpect(jsonPath("$.booleanEva").value(DEFAULT_BOOLEAN_EVA.booleanValue()))
            .andExpect(jsonPath("$.booleanRequiredEva").value(DEFAULT_BOOLEAN_REQUIRED_EVA.booleanValue()))
            .andExpect(jsonPath("$.enumEva").value(DEFAULT_ENUM_EVA.toString()))
            .andExpect(jsonPath("$.enumRequiredEva").value(DEFAULT_ENUM_REQUIRED_EVA.toString()))
            .andExpect(jsonPath("$.byteImageEvaContentType").value(DEFAULT_BYTE_IMAGE_EVA_CONTENT_TYPE))
            .andExpect(jsonPath("$.byteImageEva").value(Base64Utils.encodeToString(DEFAULT_BYTE_IMAGE_EVA)))
            .andExpect(jsonPath("$.byteImageRequiredEvaContentType").value(DEFAULT_BYTE_IMAGE_REQUIRED_EVA_CONTENT_TYPE))
            .andExpect(jsonPath("$.byteImageRequiredEva").value(Base64Utils.encodeToString(DEFAULT_BYTE_IMAGE_REQUIRED_EVA)))
            .andExpect(jsonPath("$.byteImageMinbytesEvaContentType").value(DEFAULT_BYTE_IMAGE_MINBYTES_EVA_CONTENT_TYPE))
            .andExpect(jsonPath("$.byteImageMinbytesEva").value(Base64Utils.encodeToString(DEFAULT_BYTE_IMAGE_MINBYTES_EVA)))
            .andExpect(jsonPath("$.byteImageMaxbytesEvaContentType").value(DEFAULT_BYTE_IMAGE_MAXBYTES_EVA_CONTENT_TYPE))
            .andExpect(jsonPath("$.byteImageMaxbytesEva").value(Base64Utils.encodeToString(DEFAULT_BYTE_IMAGE_MAXBYTES_EVA)))
            .andExpect(jsonPath("$.byteAnyEvaContentType").value(DEFAULT_BYTE_ANY_EVA_CONTENT_TYPE))
            .andExpect(jsonPath("$.byteAnyEva").value(Base64Utils.encodeToString(DEFAULT_BYTE_ANY_EVA)))
            .andExpect(jsonPath("$.byteAnyRequiredEvaContentType").value(DEFAULT_BYTE_ANY_REQUIRED_EVA_CONTENT_TYPE))
            .andExpect(jsonPath("$.byteAnyRequiredEva").value(Base64Utils.encodeToString(DEFAULT_BYTE_ANY_REQUIRED_EVA)))
            .andExpect(jsonPath("$.byteAnyMinbytesEvaContentType").value(DEFAULT_BYTE_ANY_MINBYTES_EVA_CONTENT_TYPE))
            .andExpect(jsonPath("$.byteAnyMinbytesEva").value(Base64Utils.encodeToString(DEFAULT_BYTE_ANY_MINBYTES_EVA)))
            .andExpect(jsonPath("$.byteAnyMaxbytesEvaContentType").value(DEFAULT_BYTE_ANY_MAXBYTES_EVA_CONTENT_TYPE))
            .andExpect(jsonPath("$.byteAnyMaxbytesEva").value(Base64Utils.encodeToString(DEFAULT_BYTE_ANY_MAXBYTES_EVA)))
            .andExpect(jsonPath("$.byteTextEva").value(DEFAULT_BYTE_TEXT_EVA.toString()))
            .andExpect(jsonPath("$.byteTextRequiredEva").value(DEFAULT_BYTE_TEXT_REQUIRED_EVA.toString()))
            .andExpect(jsonPath("$.byteTextMinbytesEva").value(DEFAULT_BYTE_TEXT_MINBYTES_EVA.toString()))
            .andExpect(jsonPath("$.byteTextMaxbytesEva").value(DEFAULT_BYTE_TEXT_MAXBYTES_EVA.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingFieldTestMapstructEntity() throws Exception {
        // Get the fieldTestMapstructEntity
        restFieldTestMapstructEntityMockMvc.perform(get("/api/field-test-mapstruct-entities/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFieldTestMapstructEntity() throws Exception {
        // Initialize the database
        fieldTestMapstructEntityRepository.saveAndFlush(fieldTestMapstructEntity);
        fieldTestMapstructEntitySearchRepository.save(fieldTestMapstructEntity);
        int databaseSizeBeforeUpdate = fieldTestMapstructEntityRepository.findAll().size();

        // Update the fieldTestMapstructEntity
        FieldTestMapstructEntity updatedFieldTestMapstructEntity = fieldTestMapstructEntityRepository.findOne(fieldTestMapstructEntity.getId());
        updatedFieldTestMapstructEntity
            .stringEva(UPDATED_STRING_EVA)
            .stringRequiredEva(UPDATED_STRING_REQUIRED_EVA)
            .stringMinlengthEva(UPDATED_STRING_MINLENGTH_EVA)
            .stringMaxlengthEva(UPDATED_STRING_MAXLENGTH_EVA)
            .stringPatternEva(UPDATED_STRING_PATTERN_EVA)
            .integerEva(UPDATED_INTEGER_EVA)
            .integerRequiredEva(UPDATED_INTEGER_REQUIRED_EVA)
            .integerMinEva(UPDATED_INTEGER_MIN_EVA)
            .integerMaxEva(UPDATED_INTEGER_MAX_EVA)
            .longEva(UPDATED_LONG_EVA)
            .longRequiredEva(UPDATED_LONG_REQUIRED_EVA)
            .longMinEva(UPDATED_LONG_MIN_EVA)
            .longMaxEva(UPDATED_LONG_MAX_EVA)
            .floatEva(UPDATED_FLOAT_EVA)
            .floatRequiredEva(UPDATED_FLOAT_REQUIRED_EVA)
            .floatMinEva(UPDATED_FLOAT_MIN_EVA)
            .floatMaxEva(UPDATED_FLOAT_MAX_EVA)
            .doubleRequiredEva(UPDATED_DOUBLE_REQUIRED_EVA)
            .doubleMinEva(UPDATED_DOUBLE_MIN_EVA)
            .doubleMaxEva(UPDATED_DOUBLE_MAX_EVA)
            .bigDecimalRequiredEva(UPDATED_BIG_DECIMAL_REQUIRED_EVA)
            .bigDecimalMinEva(UPDATED_BIG_DECIMAL_MIN_EVA)
            .bigDecimalMaxEva(UPDATED_BIG_DECIMAL_MAX_EVA)
            .localDateEva(UPDATED_LOCAL_DATE_EVA)
            .localDateRequiredEva(UPDATED_LOCAL_DATE_REQUIRED_EVA)
            .instantEva(UPDATED_INSTANT_EVA)
            .instanteRequiredEva(UPDATED_INSTANTE_REQUIRED_EVA)
            .zonedDateTimeEva(UPDATED_ZONED_DATE_TIME_EVA)
            .zonedDateTimeRequiredEva(UPDATED_ZONED_DATE_TIME_REQUIRED_EVA)
            .booleanEva(UPDATED_BOOLEAN_EVA)
            .booleanRequiredEva(UPDATED_BOOLEAN_REQUIRED_EVA)
            .enumEva(UPDATED_ENUM_EVA)
            .enumRequiredEva(UPDATED_ENUM_REQUIRED_EVA)
            .byteImageEva(UPDATED_BYTE_IMAGE_EVA)
            .byteImageEvaContentType(UPDATED_BYTE_IMAGE_EVA_CONTENT_TYPE)
            .byteImageRequiredEva(UPDATED_BYTE_IMAGE_REQUIRED_EVA)
            .byteImageRequiredEvaContentType(UPDATED_BYTE_IMAGE_REQUIRED_EVA_CONTENT_TYPE)
            .byteImageMinbytesEva(UPDATED_BYTE_IMAGE_MINBYTES_EVA)
            .byteImageMinbytesEvaContentType(UPDATED_BYTE_IMAGE_MINBYTES_EVA_CONTENT_TYPE)
            .byteImageMaxbytesEva(UPDATED_BYTE_IMAGE_MAXBYTES_EVA)
            .byteImageMaxbytesEvaContentType(UPDATED_BYTE_IMAGE_MAXBYTES_EVA_CONTENT_TYPE)
            .byteAnyEva(UPDATED_BYTE_ANY_EVA)
            .byteAnyEvaContentType(UPDATED_BYTE_ANY_EVA_CONTENT_TYPE)
            .byteAnyRequiredEva(UPDATED_BYTE_ANY_REQUIRED_EVA)
            .byteAnyRequiredEvaContentType(UPDATED_BYTE_ANY_REQUIRED_EVA_CONTENT_TYPE)
            .byteAnyMinbytesEva(UPDATED_BYTE_ANY_MINBYTES_EVA)
            .byteAnyMinbytesEvaContentType(UPDATED_BYTE_ANY_MINBYTES_EVA_CONTENT_TYPE)
            .byteAnyMaxbytesEva(UPDATED_BYTE_ANY_MAXBYTES_EVA)
            .byteAnyMaxbytesEvaContentType(UPDATED_BYTE_ANY_MAXBYTES_EVA_CONTENT_TYPE)
            .byteTextEva(UPDATED_BYTE_TEXT_EVA)
            .byteTextRequiredEva(UPDATED_BYTE_TEXT_REQUIRED_EVA)
            .byteTextMinbytesEva(UPDATED_BYTE_TEXT_MINBYTES_EVA)
            .byteTextMaxbytesEva(UPDATED_BYTE_TEXT_MAXBYTES_EVA);
        FieldTestMapstructEntityDTO fieldTestMapstructEntityDTO = fieldTestMapstructEntityMapper.toDto(updatedFieldTestMapstructEntity);

        restFieldTestMapstructEntityMockMvc.perform(put("/api/field-test-mapstruct-entities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fieldTestMapstructEntityDTO)))
            .andExpect(status().isOk());

        // Validate the FieldTestMapstructEntity in the database
        List<FieldTestMapstructEntity> fieldTestMapstructEntityList = fieldTestMapstructEntityRepository.findAll();
        assertThat(fieldTestMapstructEntityList).hasSize(databaseSizeBeforeUpdate);
        FieldTestMapstructEntity testFieldTestMapstructEntity = fieldTestMapstructEntityList.get(fieldTestMapstructEntityList.size() - 1);
        assertThat(testFieldTestMapstructEntity.getStringEva()).isEqualTo(UPDATED_STRING_EVA);
        assertThat(testFieldTestMapstructEntity.getStringRequiredEva()).isEqualTo(UPDATED_STRING_REQUIRED_EVA);
        assertThat(testFieldTestMapstructEntity.getStringMinlengthEva()).isEqualTo(UPDATED_STRING_MINLENGTH_EVA);
        assertThat(testFieldTestMapstructEntity.getStringMaxlengthEva()).isEqualTo(UPDATED_STRING_MAXLENGTH_EVA);
        assertThat(testFieldTestMapstructEntity.getStringPatternEva()).isEqualTo(UPDATED_STRING_PATTERN_EVA);
        assertThat(testFieldTestMapstructEntity.getIntegerEva()).isEqualTo(UPDATED_INTEGER_EVA);
        assertThat(testFieldTestMapstructEntity.getIntegerRequiredEva()).isEqualTo(UPDATED_INTEGER_REQUIRED_EVA);
        assertThat(testFieldTestMapstructEntity.getIntegerMinEva()).isEqualTo(UPDATED_INTEGER_MIN_EVA);
        assertThat(testFieldTestMapstructEntity.getIntegerMaxEva()).isEqualTo(UPDATED_INTEGER_MAX_EVA);
        assertThat(testFieldTestMapstructEntity.getLongEva()).isEqualTo(UPDATED_LONG_EVA);
        assertThat(testFieldTestMapstructEntity.getLongRequiredEva()).isEqualTo(UPDATED_LONG_REQUIRED_EVA);
        assertThat(testFieldTestMapstructEntity.getLongMinEva()).isEqualTo(UPDATED_LONG_MIN_EVA);
        assertThat(testFieldTestMapstructEntity.getLongMaxEva()).isEqualTo(UPDATED_LONG_MAX_EVA);
        assertThat(testFieldTestMapstructEntity.getFloatEva()).isEqualTo(UPDATED_FLOAT_EVA);
        assertThat(testFieldTestMapstructEntity.getFloatRequiredEva()).isEqualTo(UPDATED_FLOAT_REQUIRED_EVA);
        assertThat(testFieldTestMapstructEntity.getFloatMinEva()).isEqualTo(UPDATED_FLOAT_MIN_EVA);
        assertThat(testFieldTestMapstructEntity.getFloatMaxEva()).isEqualTo(UPDATED_FLOAT_MAX_EVA);
        assertThat(testFieldTestMapstructEntity.getDoubleRequiredEva()).isEqualTo(UPDATED_DOUBLE_REQUIRED_EVA);
        assertThat(testFieldTestMapstructEntity.getDoubleMinEva()).isEqualTo(UPDATED_DOUBLE_MIN_EVA);
        assertThat(testFieldTestMapstructEntity.getDoubleMaxEva()).isEqualTo(UPDATED_DOUBLE_MAX_EVA);
        assertThat(testFieldTestMapstructEntity.getBigDecimalRequiredEva()).isEqualTo(UPDATED_BIG_DECIMAL_REQUIRED_EVA);
        assertThat(testFieldTestMapstructEntity.getBigDecimalMinEva()).isEqualTo(UPDATED_BIG_DECIMAL_MIN_EVA);
        assertThat(testFieldTestMapstructEntity.getBigDecimalMaxEva()).isEqualTo(UPDATED_BIG_DECIMAL_MAX_EVA);
        assertThat(testFieldTestMapstructEntity.getLocalDateEva()).isEqualTo(UPDATED_LOCAL_DATE_EVA);
        assertThat(testFieldTestMapstructEntity.getLocalDateRequiredEva()).isEqualTo(UPDATED_LOCAL_DATE_REQUIRED_EVA);
        assertThat(testFieldTestMapstructEntity.getInstantEva()).isEqualTo(UPDATED_INSTANT_EVA);
        assertThat(testFieldTestMapstructEntity.getInstanteRequiredEva()).isEqualTo(UPDATED_INSTANTE_REQUIRED_EVA);
        assertThat(testFieldTestMapstructEntity.getZonedDateTimeEva()).isEqualTo(UPDATED_ZONED_DATE_TIME_EVA);
        assertThat(testFieldTestMapstructEntity.getZonedDateTimeRequiredEva()).isEqualTo(UPDATED_ZONED_DATE_TIME_REQUIRED_EVA);
        assertThat(testFieldTestMapstructEntity.isBooleanEva()).isEqualTo(UPDATED_BOOLEAN_EVA);
        assertThat(testFieldTestMapstructEntity.isBooleanRequiredEva()).isEqualTo(UPDATED_BOOLEAN_REQUIRED_EVA);
        assertThat(testFieldTestMapstructEntity.getEnumEva()).isEqualTo(UPDATED_ENUM_EVA);
        assertThat(testFieldTestMapstructEntity.getEnumRequiredEva()).isEqualTo(UPDATED_ENUM_REQUIRED_EVA);
        assertThat(testFieldTestMapstructEntity.getByteImageEva()).isEqualTo(UPDATED_BYTE_IMAGE_EVA);
        assertThat(testFieldTestMapstructEntity.getByteImageEvaContentType()).isEqualTo(UPDATED_BYTE_IMAGE_EVA_CONTENT_TYPE);
        assertThat(testFieldTestMapstructEntity.getByteImageRequiredEva()).isEqualTo(UPDATED_BYTE_IMAGE_REQUIRED_EVA);
        assertThat(testFieldTestMapstructEntity.getByteImageRequiredEvaContentType()).isEqualTo(UPDATED_BYTE_IMAGE_REQUIRED_EVA_CONTENT_TYPE);
        assertThat(testFieldTestMapstructEntity.getByteImageMinbytesEva()).isEqualTo(UPDATED_BYTE_IMAGE_MINBYTES_EVA);
        assertThat(testFieldTestMapstructEntity.getByteImageMinbytesEvaContentType()).isEqualTo(UPDATED_BYTE_IMAGE_MINBYTES_EVA_CONTENT_TYPE);
        assertThat(testFieldTestMapstructEntity.getByteImageMaxbytesEva()).isEqualTo(UPDATED_BYTE_IMAGE_MAXBYTES_EVA);
        assertThat(testFieldTestMapstructEntity.getByteImageMaxbytesEvaContentType()).isEqualTo(UPDATED_BYTE_IMAGE_MAXBYTES_EVA_CONTENT_TYPE);
        assertThat(testFieldTestMapstructEntity.getByteAnyEva()).isEqualTo(UPDATED_BYTE_ANY_EVA);
        assertThat(testFieldTestMapstructEntity.getByteAnyEvaContentType()).isEqualTo(UPDATED_BYTE_ANY_EVA_CONTENT_TYPE);
        assertThat(testFieldTestMapstructEntity.getByteAnyRequiredEva()).isEqualTo(UPDATED_BYTE_ANY_REQUIRED_EVA);
        assertThat(testFieldTestMapstructEntity.getByteAnyRequiredEvaContentType()).isEqualTo(UPDATED_BYTE_ANY_REQUIRED_EVA_CONTENT_TYPE);
        assertThat(testFieldTestMapstructEntity.getByteAnyMinbytesEva()).isEqualTo(UPDATED_BYTE_ANY_MINBYTES_EVA);
        assertThat(testFieldTestMapstructEntity.getByteAnyMinbytesEvaContentType()).isEqualTo(UPDATED_BYTE_ANY_MINBYTES_EVA_CONTENT_TYPE);
        assertThat(testFieldTestMapstructEntity.getByteAnyMaxbytesEva()).isEqualTo(UPDATED_BYTE_ANY_MAXBYTES_EVA);
        assertThat(testFieldTestMapstructEntity.getByteAnyMaxbytesEvaContentType()).isEqualTo(UPDATED_BYTE_ANY_MAXBYTES_EVA_CONTENT_TYPE);
        assertThat(testFieldTestMapstructEntity.getByteTextEva()).isEqualTo(UPDATED_BYTE_TEXT_EVA);
        assertThat(testFieldTestMapstructEntity.getByteTextRequiredEva()).isEqualTo(UPDATED_BYTE_TEXT_REQUIRED_EVA);
        assertThat(testFieldTestMapstructEntity.getByteTextMinbytesEva()).isEqualTo(UPDATED_BYTE_TEXT_MINBYTES_EVA);
        assertThat(testFieldTestMapstructEntity.getByteTextMaxbytesEva()).isEqualTo(UPDATED_BYTE_TEXT_MAXBYTES_EVA);

        // Validate the FieldTestMapstructEntity in Elasticsearch
        FieldTestMapstructEntity fieldTestMapstructEntityEs = fieldTestMapstructEntitySearchRepository.findOne(testFieldTestMapstructEntity.getId());
        assertThat(fieldTestMapstructEntityEs).isEqualToComparingFieldByField(testFieldTestMapstructEntity);
    }

    @Test
    @Transactional
    public void updateNonExistingFieldTestMapstructEntity() throws Exception {
        int databaseSizeBeforeUpdate = fieldTestMapstructEntityRepository.findAll().size();

        // Create the FieldTestMapstructEntity
        FieldTestMapstructEntityDTO fieldTestMapstructEntityDTO = fieldTestMapstructEntityMapper.toDto(fieldTestMapstructEntity);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restFieldTestMapstructEntityMockMvc.perform(put("/api/field-test-mapstruct-entities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fieldTestMapstructEntityDTO)))
            .andExpect(status().isCreated());

        // Validate the FieldTestMapstructEntity in the database
        List<FieldTestMapstructEntity> fieldTestMapstructEntityList = fieldTestMapstructEntityRepository.findAll();
        assertThat(fieldTestMapstructEntityList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteFieldTestMapstructEntity() throws Exception {
        // Initialize the database
        fieldTestMapstructEntityRepository.saveAndFlush(fieldTestMapstructEntity);
        fieldTestMapstructEntitySearchRepository.save(fieldTestMapstructEntity);
        int databaseSizeBeforeDelete = fieldTestMapstructEntityRepository.findAll().size();

        // Get the fieldTestMapstructEntity
        restFieldTestMapstructEntityMockMvc.perform(delete("/api/field-test-mapstruct-entities/{id}", fieldTestMapstructEntity.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate Elasticsearch is empty
        boolean fieldTestMapstructEntityExistsInEs = fieldTestMapstructEntitySearchRepository.exists(fieldTestMapstructEntity.getId());
        assertThat(fieldTestMapstructEntityExistsInEs).isFalse();

        // Validate the database is empty
        List<FieldTestMapstructEntity> fieldTestMapstructEntityList = fieldTestMapstructEntityRepository.findAll();
        assertThat(fieldTestMapstructEntityList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void searchFieldTestMapstructEntity() throws Exception {
        // Initialize the database
        fieldTestMapstructEntityRepository.saveAndFlush(fieldTestMapstructEntity);
        fieldTestMapstructEntitySearchRepository.save(fieldTestMapstructEntity);

        // Search the fieldTestMapstructEntity
        restFieldTestMapstructEntityMockMvc.perform(get("/api/_search/field-test-mapstruct-entities?query=id:" + fieldTestMapstructEntity.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(fieldTestMapstructEntity.getId().intValue())))
            .andExpect(jsonPath("$.[*].stringEva").value(hasItem(DEFAULT_STRING_EVA.toString())))
            .andExpect(jsonPath("$.[*].stringRequiredEva").value(hasItem(DEFAULT_STRING_REQUIRED_EVA.toString())))
            .andExpect(jsonPath("$.[*].stringMinlengthEva").value(hasItem(DEFAULT_STRING_MINLENGTH_EVA.toString())))
            .andExpect(jsonPath("$.[*].stringMaxlengthEva").value(hasItem(DEFAULT_STRING_MAXLENGTH_EVA.toString())))
            .andExpect(jsonPath("$.[*].stringPatternEva").value(hasItem(DEFAULT_STRING_PATTERN_EVA.toString())))
            .andExpect(jsonPath("$.[*].integerEva").value(hasItem(DEFAULT_INTEGER_EVA)))
            .andExpect(jsonPath("$.[*].integerRequiredEva").value(hasItem(DEFAULT_INTEGER_REQUIRED_EVA)))
            .andExpect(jsonPath("$.[*].integerMinEva").value(hasItem(DEFAULT_INTEGER_MIN_EVA)))
            .andExpect(jsonPath("$.[*].integerMaxEva").value(hasItem(DEFAULT_INTEGER_MAX_EVA)))
            .andExpect(jsonPath("$.[*].longEva").value(hasItem(DEFAULT_LONG_EVA.intValue())))
            .andExpect(jsonPath("$.[*].longRequiredEva").value(hasItem(DEFAULT_LONG_REQUIRED_EVA.intValue())))
            .andExpect(jsonPath("$.[*].longMinEva").value(hasItem(DEFAULT_LONG_MIN_EVA.intValue())))
            .andExpect(jsonPath("$.[*].longMaxEva").value(hasItem(DEFAULT_LONG_MAX_EVA.intValue())))
            .andExpect(jsonPath("$.[*].floatEva").value(hasItem(DEFAULT_FLOAT_EVA.doubleValue())))
            .andExpect(jsonPath("$.[*].floatRequiredEva").value(hasItem(DEFAULT_FLOAT_REQUIRED_EVA.doubleValue())))
            .andExpect(jsonPath("$.[*].floatMinEva").value(hasItem(DEFAULT_FLOAT_MIN_EVA.doubleValue())))
            .andExpect(jsonPath("$.[*].floatMaxEva").value(hasItem(DEFAULT_FLOAT_MAX_EVA.doubleValue())))
            .andExpect(jsonPath("$.[*].doubleRequiredEva").value(hasItem(DEFAULT_DOUBLE_REQUIRED_EVA.doubleValue())))
            .andExpect(jsonPath("$.[*].doubleMinEva").value(hasItem(DEFAULT_DOUBLE_MIN_EVA.doubleValue())))
            .andExpect(jsonPath("$.[*].doubleMaxEva").value(hasItem(DEFAULT_DOUBLE_MAX_EVA.doubleValue())))
            .andExpect(jsonPath("$.[*].bigDecimalRequiredEva").value(hasItem(DEFAULT_BIG_DECIMAL_REQUIRED_EVA.intValue())))
            .andExpect(jsonPath("$.[*].bigDecimalMinEva").value(hasItem(DEFAULT_BIG_DECIMAL_MIN_EVA.intValue())))
            .andExpect(jsonPath("$.[*].bigDecimalMaxEva").value(hasItem(DEFAULT_BIG_DECIMAL_MAX_EVA.intValue())))
            .andExpect(jsonPath("$.[*].localDateEva").value(hasItem(DEFAULT_LOCAL_DATE_EVA.toString())))
            .andExpect(jsonPath("$.[*].localDateRequiredEva").value(hasItem(DEFAULT_LOCAL_DATE_REQUIRED_EVA.toString())))
            .andExpect(jsonPath("$.[*].instantEva").value(hasItem(DEFAULT_INSTANT_EVA.toString())))
            .andExpect(jsonPath("$.[*].instanteRequiredEva").value(hasItem(DEFAULT_INSTANTE_REQUIRED_EVA.toString())))
            .andExpect(jsonPath("$.[*].zonedDateTimeEva").value(hasItem(sameInstant(DEFAULT_ZONED_DATE_TIME_EVA))))
            .andExpect(jsonPath("$.[*].zonedDateTimeRequiredEva").value(hasItem(sameInstant(DEFAULT_ZONED_DATE_TIME_REQUIRED_EVA))))
            .andExpect(jsonPath("$.[*].booleanEva").value(hasItem(DEFAULT_BOOLEAN_EVA.booleanValue())))
            .andExpect(jsonPath("$.[*].booleanRequiredEva").value(hasItem(DEFAULT_BOOLEAN_REQUIRED_EVA.booleanValue())))
            .andExpect(jsonPath("$.[*].enumEva").value(hasItem(DEFAULT_ENUM_EVA.toString())))
            .andExpect(jsonPath("$.[*].enumRequiredEva").value(hasItem(DEFAULT_ENUM_REQUIRED_EVA.toString())))
            .andExpect(jsonPath("$.[*].byteImageEvaContentType").value(hasItem(DEFAULT_BYTE_IMAGE_EVA_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].byteImageEva").value(hasItem(Base64Utils.encodeToString(DEFAULT_BYTE_IMAGE_EVA))))
            .andExpect(jsonPath("$.[*].byteImageRequiredEvaContentType").value(hasItem(DEFAULT_BYTE_IMAGE_REQUIRED_EVA_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].byteImageRequiredEva").value(hasItem(Base64Utils.encodeToString(DEFAULT_BYTE_IMAGE_REQUIRED_EVA))))
            .andExpect(jsonPath("$.[*].byteImageMinbytesEvaContentType").value(hasItem(DEFAULT_BYTE_IMAGE_MINBYTES_EVA_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].byteImageMinbytesEva").value(hasItem(Base64Utils.encodeToString(DEFAULT_BYTE_IMAGE_MINBYTES_EVA))))
            .andExpect(jsonPath("$.[*].byteImageMaxbytesEvaContentType").value(hasItem(DEFAULT_BYTE_IMAGE_MAXBYTES_EVA_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].byteImageMaxbytesEva").value(hasItem(Base64Utils.encodeToString(DEFAULT_BYTE_IMAGE_MAXBYTES_EVA))))
            .andExpect(jsonPath("$.[*].byteAnyEvaContentType").value(hasItem(DEFAULT_BYTE_ANY_EVA_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].byteAnyEva").value(hasItem(Base64Utils.encodeToString(DEFAULT_BYTE_ANY_EVA))))
            .andExpect(jsonPath("$.[*].byteAnyRequiredEvaContentType").value(hasItem(DEFAULT_BYTE_ANY_REQUIRED_EVA_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].byteAnyRequiredEva").value(hasItem(Base64Utils.encodeToString(DEFAULT_BYTE_ANY_REQUIRED_EVA))))
            .andExpect(jsonPath("$.[*].byteAnyMinbytesEvaContentType").value(hasItem(DEFAULT_BYTE_ANY_MINBYTES_EVA_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].byteAnyMinbytesEva").value(hasItem(Base64Utils.encodeToString(DEFAULT_BYTE_ANY_MINBYTES_EVA))))
            .andExpect(jsonPath("$.[*].byteAnyMaxbytesEvaContentType").value(hasItem(DEFAULT_BYTE_ANY_MAXBYTES_EVA_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].byteAnyMaxbytesEva").value(hasItem(Base64Utils.encodeToString(DEFAULT_BYTE_ANY_MAXBYTES_EVA))))
            .andExpect(jsonPath("$.[*].byteTextEva").value(hasItem(DEFAULT_BYTE_TEXT_EVA.toString())))
            .andExpect(jsonPath("$.[*].byteTextRequiredEva").value(hasItem(DEFAULT_BYTE_TEXT_REQUIRED_EVA.toString())))
            .andExpect(jsonPath("$.[*].byteTextMinbytesEva").value(hasItem(DEFAULT_BYTE_TEXT_MINBYTES_EVA.toString())))
            .andExpect(jsonPath("$.[*].byteTextMaxbytesEva").value(hasItem(DEFAULT_BYTE_TEXT_MAXBYTES_EVA.toString())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(FieldTestMapstructEntity.class);
        FieldTestMapstructEntity fieldTestMapstructEntity1 = new FieldTestMapstructEntity();
        fieldTestMapstructEntity1.setId(1L);
        FieldTestMapstructEntity fieldTestMapstructEntity2 = new FieldTestMapstructEntity();
        fieldTestMapstructEntity2.setId(fieldTestMapstructEntity1.getId());
        assertThat(fieldTestMapstructEntity1).isEqualTo(fieldTestMapstructEntity2);
        fieldTestMapstructEntity2.setId(2L);
        assertThat(fieldTestMapstructEntity1).isNotEqualTo(fieldTestMapstructEntity2);
        fieldTestMapstructEntity1.setId(null);
        assertThat(fieldTestMapstructEntity1).isNotEqualTo(fieldTestMapstructEntity2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(FieldTestMapstructEntityDTO.class);
        FieldTestMapstructEntityDTO fieldTestMapstructEntityDTO1 = new FieldTestMapstructEntityDTO();
        fieldTestMapstructEntityDTO1.setId(1L);
        FieldTestMapstructEntityDTO fieldTestMapstructEntityDTO2 = new FieldTestMapstructEntityDTO();
        assertThat(fieldTestMapstructEntityDTO1).isNotEqualTo(fieldTestMapstructEntityDTO2);
        fieldTestMapstructEntityDTO2.setId(fieldTestMapstructEntityDTO1.getId());
        assertThat(fieldTestMapstructEntityDTO1).isEqualTo(fieldTestMapstructEntityDTO2);
        fieldTestMapstructEntityDTO2.setId(2L);
        assertThat(fieldTestMapstructEntityDTO1).isNotEqualTo(fieldTestMapstructEntityDTO2);
        fieldTestMapstructEntityDTO1.setId(null);
        assertThat(fieldTestMapstructEntityDTO1).isNotEqualTo(fieldTestMapstructEntityDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(fieldTestMapstructEntityMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(fieldTestMapstructEntityMapper.fromId(null)).isNull();
    }
}
