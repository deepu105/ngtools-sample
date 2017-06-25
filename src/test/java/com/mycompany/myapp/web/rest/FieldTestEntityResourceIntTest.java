package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.Jh4TestApp;

import com.mycompany.myapp.domain.FieldTestEntity;
import com.mycompany.myapp.repository.FieldTestEntityRepository;
import com.mycompany.myapp.repository.search.FieldTestEntitySearchRepository;
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
 * Test class for the FieldTestEntityResource REST controller.
 *
 * @see FieldTestEntityResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Jh4TestApp.class)
public class FieldTestEntityResourceIntTest {

    private static final String DEFAULT_STRING_TOM = "AAAAAAAAAA";
    private static final String UPDATED_STRING_TOM = "BBBBBBBBBB";

    private static final String DEFAULT_STRING_REQUIRED_TOM = "AAAAAAAAAA";
    private static final String UPDATED_STRING_REQUIRED_TOM = "BBBBBBBBBB";

    private static final String DEFAULT_STRING_MINLENGTH_TOM = "AAAAAAAAAA";
    private static final String UPDATED_STRING_MINLENGTH_TOM = "BBBBBBBBBB";

    private static final String DEFAULT_STRING_MAXLENGTH_TOM = "AAAAAAAAAA";
    private static final String UPDATED_STRING_MAXLENGTH_TOM = "BBBBBBBBBB";

    private static final String DEFAULT_STRING_PATTERN_TOM = "AAAAAAAAAA";
    private static final String UPDATED_STRING_PATTERN_TOM = "BBBBBBBBBB";

    private static final Integer DEFAULT_INTEGER_TOM = 1;
    private static final Integer UPDATED_INTEGER_TOM = 2;

    private static final Integer DEFAULT_INTEGER_REQUIRED_TOM = 1;
    private static final Integer UPDATED_INTEGER_REQUIRED_TOM = 2;

    private static final Integer DEFAULT_INTEGER_MIN_TOM = 0;
    private static final Integer UPDATED_INTEGER_MIN_TOM = 1;

    private static final Integer DEFAULT_INTEGER_MAX_TOM = 100;
    private static final Integer UPDATED_INTEGER_MAX_TOM = 99;

    private static final Long DEFAULT_LONG_TOM = 1L;
    private static final Long UPDATED_LONG_TOM = 2L;

    private static final Long DEFAULT_LONG_REQUIRED_TOM = 1L;
    private static final Long UPDATED_LONG_REQUIRED_TOM = 2L;

    private static final Long DEFAULT_LONG_MIN_TOM = 0L;
    private static final Long UPDATED_LONG_MIN_TOM = 1L;

    private static final Long DEFAULT_LONG_MAX_TOM = 100L;
    private static final Long UPDATED_LONG_MAX_TOM = 99L;

    private static final Float DEFAULT_FLOAT_TOM = 1F;
    private static final Float UPDATED_FLOAT_TOM = 2F;

    private static final Float DEFAULT_FLOAT_REQUIRED_TOM = 1F;
    private static final Float UPDATED_FLOAT_REQUIRED_TOM = 2F;

    private static final Float DEFAULT_FLOAT_MIN_TOM = 0F;
    private static final Float UPDATED_FLOAT_MIN_TOM = 1F;

    private static final Float DEFAULT_FLOAT_MAX_TOM = 100F;
    private static final Float UPDATED_FLOAT_MAX_TOM = 99F;

    private static final Double DEFAULT_DOUBLE_REQUIRED_TOM = 1D;
    private static final Double UPDATED_DOUBLE_REQUIRED_TOM = 2D;

    private static final Double DEFAULT_DOUBLE_MIN_TOM = 0D;
    private static final Double UPDATED_DOUBLE_MIN_TOM = 1D;

    private static final Double DEFAULT_DOUBLE_MAX_TOM = 100D;
    private static final Double UPDATED_DOUBLE_MAX_TOM = 99D;

    private static final BigDecimal DEFAULT_BIG_DECIMAL_REQUIRED_TOM = new BigDecimal(1);
    private static final BigDecimal UPDATED_BIG_DECIMAL_REQUIRED_TOM = new BigDecimal(2);

    private static final BigDecimal DEFAULT_BIG_DECIMAL_MIN_TOM = new BigDecimal(0);
    private static final BigDecimal UPDATED_BIG_DECIMAL_MIN_TOM = new BigDecimal(1);

    private static final BigDecimal DEFAULT_BIG_DECIMAL_MAX_TOM = new BigDecimal(100);
    private static final BigDecimal UPDATED_BIG_DECIMAL_MAX_TOM = new BigDecimal(99);

    private static final LocalDate DEFAULT_LOCAL_DATE_TOM = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_LOCAL_DATE_TOM = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_LOCAL_DATE_REQUIRED_TOM = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_LOCAL_DATE_REQUIRED_TOM = LocalDate.now(ZoneId.systemDefault());

    private static final Instant DEFAULT_INSTANT_TOM = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_INSTANT_TOM = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_INSTANTE_REQUIRED_TOM = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_INSTANTE_REQUIRED_TOM = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final ZonedDateTime DEFAULT_ZONED_DATE_TIME_TOM = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_ZONED_DATE_TIME_TOM = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_ZONED_DATE_TIME_REQUIRED_TOM = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_ZONED_DATE_TIME_REQUIRED_TOM = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final Boolean DEFAULT_BOOLEAN_TOM = false;
    private static final Boolean UPDATED_BOOLEAN_TOM = true;

    private static final Boolean DEFAULT_BOOLEAN_REQUIRED_TOM = false;
    private static final Boolean UPDATED_BOOLEAN_REQUIRED_TOM = true;

    private static final EnumFieldClass DEFAULT_ENUM_TOM = EnumFieldClass.ENUM_VALUE_1;
    private static final EnumFieldClass UPDATED_ENUM_TOM = EnumFieldClass.ENUM_VALUE_2;

    private static final EnumRequiredFieldClass DEFAULT_ENUM_REQUIRED_TOM = EnumRequiredFieldClass.ENUM_VALUE_1;
    private static final EnumRequiredFieldClass UPDATED_ENUM_REQUIRED_TOM = EnumRequiredFieldClass.ENUM_VALUE_2;

    private static final byte[] DEFAULT_BYTE_IMAGE_TOM = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_BYTE_IMAGE_TOM = TestUtil.createByteArray(2, "1");
    private static final String DEFAULT_BYTE_IMAGE_TOM_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_BYTE_IMAGE_TOM_CONTENT_TYPE = "image/png";

    private static final byte[] DEFAULT_BYTE_IMAGE_REQUIRED_TOM = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_BYTE_IMAGE_REQUIRED_TOM = TestUtil.createByteArray(2, "1");
    private static final String DEFAULT_BYTE_IMAGE_REQUIRED_TOM_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_BYTE_IMAGE_REQUIRED_TOM_CONTENT_TYPE = "image/png";

    private static final byte[] DEFAULT_BYTE_IMAGE_MINBYTES_TOM = TestUtil.createByteArray(0, "0");
    private static final byte[] UPDATED_BYTE_IMAGE_MINBYTES_TOM = TestUtil.createByteArray(0, "1");
    private static final String DEFAULT_BYTE_IMAGE_MINBYTES_TOM_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_BYTE_IMAGE_MINBYTES_TOM_CONTENT_TYPE = "image/png";

    private static final byte[] DEFAULT_BYTE_IMAGE_MAXBYTES_TOM = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_BYTE_IMAGE_MAXBYTES_TOM = TestUtil.createByteArray(10000, "1");
    private static final String DEFAULT_BYTE_IMAGE_MAXBYTES_TOM_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_BYTE_IMAGE_MAXBYTES_TOM_CONTENT_TYPE = "image/png";

    private static final byte[] DEFAULT_BYTE_ANY_TOM = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_BYTE_ANY_TOM = TestUtil.createByteArray(2, "1");
    private static final String DEFAULT_BYTE_ANY_TOM_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_BYTE_ANY_TOM_CONTENT_TYPE = "image/png";

    private static final byte[] DEFAULT_BYTE_ANY_REQUIRED_TOM = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_BYTE_ANY_REQUIRED_TOM = TestUtil.createByteArray(2, "1");
    private static final String DEFAULT_BYTE_ANY_REQUIRED_TOM_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_BYTE_ANY_REQUIRED_TOM_CONTENT_TYPE = "image/png";

    private static final byte[] DEFAULT_BYTE_ANY_MINBYTES_TOM = TestUtil.createByteArray(0, "0");
    private static final byte[] UPDATED_BYTE_ANY_MINBYTES_TOM = TestUtil.createByteArray(0, "1");
    private static final String DEFAULT_BYTE_ANY_MINBYTES_TOM_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_BYTE_ANY_MINBYTES_TOM_CONTENT_TYPE = "image/png";

    private static final byte[] DEFAULT_BYTE_ANY_MAXBYTES_TOM = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_BYTE_ANY_MAXBYTES_TOM = TestUtil.createByteArray(10000, "1");
    private static final String DEFAULT_BYTE_ANY_MAXBYTES_TOM_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_BYTE_ANY_MAXBYTES_TOM_CONTENT_TYPE = "image/png";

    private static final String DEFAULT_BYTE_TEXT_TOM = "AAAAAAAAAA";
    private static final String UPDATED_BYTE_TEXT_TOM = "BBBBBBBBBB";

    private static final String DEFAULT_BYTE_TEXT_REQUIRED_TOM = "AAAAAAAAAA";
    private static final String UPDATED_BYTE_TEXT_REQUIRED_TOM = "BBBBBBBBBB";

    private static final String DEFAULT_BYTE_TEXT_MINBYTES_TOM = "AAAAAAAAAA";
    private static final String UPDATED_BYTE_TEXT_MINBYTES_TOM = "BBBBBBBBBB";

    private static final String DEFAULT_BYTE_TEXT_MAXBYTES_TOM = "AAAAAAAAAA";
    private static final String UPDATED_BYTE_TEXT_MAXBYTES_TOM = "BBBBBBBBBB";

    @Autowired
    private FieldTestEntityRepository fieldTestEntityRepository;

    @Autowired
    private FieldTestEntitySearchRepository fieldTestEntitySearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restFieldTestEntityMockMvc;

    private FieldTestEntity fieldTestEntity;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        FieldTestEntityResource fieldTestEntityResource = new FieldTestEntityResource(fieldTestEntityRepository, fieldTestEntitySearchRepository);
        this.restFieldTestEntityMockMvc = MockMvcBuilders.standaloneSetup(fieldTestEntityResource)
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
    public static FieldTestEntity createEntity(EntityManager em) {
        FieldTestEntity fieldTestEntity = new FieldTestEntity()
            .stringTom(DEFAULT_STRING_TOM)
            .stringRequiredTom(DEFAULT_STRING_REQUIRED_TOM)
            .stringMinlengthTom(DEFAULT_STRING_MINLENGTH_TOM)
            .stringMaxlengthTom(DEFAULT_STRING_MAXLENGTH_TOM)
            .stringPatternTom(DEFAULT_STRING_PATTERN_TOM)
            .integerTom(DEFAULT_INTEGER_TOM)
            .integerRequiredTom(DEFAULT_INTEGER_REQUIRED_TOM)
            .integerMinTom(DEFAULT_INTEGER_MIN_TOM)
            .integerMaxTom(DEFAULT_INTEGER_MAX_TOM)
            .longTom(DEFAULT_LONG_TOM)
            .longRequiredTom(DEFAULT_LONG_REQUIRED_TOM)
            .longMinTom(DEFAULT_LONG_MIN_TOM)
            .longMaxTom(DEFAULT_LONG_MAX_TOM)
            .floatTom(DEFAULT_FLOAT_TOM)
            .floatRequiredTom(DEFAULT_FLOAT_REQUIRED_TOM)
            .floatMinTom(DEFAULT_FLOAT_MIN_TOM)
            .floatMaxTom(DEFAULT_FLOAT_MAX_TOM)
            .doubleRequiredTom(DEFAULT_DOUBLE_REQUIRED_TOM)
            .doubleMinTom(DEFAULT_DOUBLE_MIN_TOM)
            .doubleMaxTom(DEFAULT_DOUBLE_MAX_TOM)
            .bigDecimalRequiredTom(DEFAULT_BIG_DECIMAL_REQUIRED_TOM)
            .bigDecimalMinTom(DEFAULT_BIG_DECIMAL_MIN_TOM)
            .bigDecimalMaxTom(DEFAULT_BIG_DECIMAL_MAX_TOM)
            .localDateTom(DEFAULT_LOCAL_DATE_TOM)
            .localDateRequiredTom(DEFAULT_LOCAL_DATE_REQUIRED_TOM)
            .instantTom(DEFAULT_INSTANT_TOM)
            .instanteRequiredTom(DEFAULT_INSTANTE_REQUIRED_TOM)
            .zonedDateTimeTom(DEFAULT_ZONED_DATE_TIME_TOM)
            .zonedDateTimeRequiredTom(DEFAULT_ZONED_DATE_TIME_REQUIRED_TOM)
            .booleanTom(DEFAULT_BOOLEAN_TOM)
            .booleanRequiredTom(DEFAULT_BOOLEAN_REQUIRED_TOM)
            .enumTom(DEFAULT_ENUM_TOM)
            .enumRequiredTom(DEFAULT_ENUM_REQUIRED_TOM)
            .byteImageTom(DEFAULT_BYTE_IMAGE_TOM)
            .byteImageTomContentType(DEFAULT_BYTE_IMAGE_TOM_CONTENT_TYPE)
            .byteImageRequiredTom(DEFAULT_BYTE_IMAGE_REQUIRED_TOM)
            .byteImageRequiredTomContentType(DEFAULT_BYTE_IMAGE_REQUIRED_TOM_CONTENT_TYPE)
            .byteImageMinbytesTom(DEFAULT_BYTE_IMAGE_MINBYTES_TOM)
            .byteImageMinbytesTomContentType(DEFAULT_BYTE_IMAGE_MINBYTES_TOM_CONTENT_TYPE)
            .byteImageMaxbytesTom(DEFAULT_BYTE_IMAGE_MAXBYTES_TOM)
            .byteImageMaxbytesTomContentType(DEFAULT_BYTE_IMAGE_MAXBYTES_TOM_CONTENT_TYPE)
            .byteAnyTom(DEFAULT_BYTE_ANY_TOM)
            .byteAnyTomContentType(DEFAULT_BYTE_ANY_TOM_CONTENT_TYPE)
            .byteAnyRequiredTom(DEFAULT_BYTE_ANY_REQUIRED_TOM)
            .byteAnyRequiredTomContentType(DEFAULT_BYTE_ANY_REQUIRED_TOM_CONTENT_TYPE)
            .byteAnyMinbytesTom(DEFAULT_BYTE_ANY_MINBYTES_TOM)
            .byteAnyMinbytesTomContentType(DEFAULT_BYTE_ANY_MINBYTES_TOM_CONTENT_TYPE)
            .byteAnyMaxbytesTom(DEFAULT_BYTE_ANY_MAXBYTES_TOM)
            .byteAnyMaxbytesTomContentType(DEFAULT_BYTE_ANY_MAXBYTES_TOM_CONTENT_TYPE)
            .byteTextTom(DEFAULT_BYTE_TEXT_TOM)
            .byteTextRequiredTom(DEFAULT_BYTE_TEXT_REQUIRED_TOM)
            .byteTextMinbytesTom(DEFAULT_BYTE_TEXT_MINBYTES_TOM)
            .byteTextMaxbytesTom(DEFAULT_BYTE_TEXT_MAXBYTES_TOM);
        return fieldTestEntity;
    }

    @Before
    public void initTest() {
        fieldTestEntitySearchRepository.deleteAll();
        fieldTestEntity = createEntity(em);
    }

    @Test
    @Transactional
    public void createFieldTestEntity() throws Exception {
        int databaseSizeBeforeCreate = fieldTestEntityRepository.findAll().size();

        // Create the FieldTestEntity
        restFieldTestEntityMockMvc.perform(post("/api/field-test-entities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fieldTestEntity)))
            .andExpect(status().isCreated());

        // Validate the FieldTestEntity in the database
        List<FieldTestEntity> fieldTestEntityList = fieldTestEntityRepository.findAll();
        assertThat(fieldTestEntityList).hasSize(databaseSizeBeforeCreate + 1);
        FieldTestEntity testFieldTestEntity = fieldTestEntityList.get(fieldTestEntityList.size() - 1);
        assertThat(testFieldTestEntity.getStringTom()).isEqualTo(DEFAULT_STRING_TOM);
        assertThat(testFieldTestEntity.getStringRequiredTom()).isEqualTo(DEFAULT_STRING_REQUIRED_TOM);
        assertThat(testFieldTestEntity.getStringMinlengthTom()).isEqualTo(DEFAULT_STRING_MINLENGTH_TOM);
        assertThat(testFieldTestEntity.getStringMaxlengthTom()).isEqualTo(DEFAULT_STRING_MAXLENGTH_TOM);
        assertThat(testFieldTestEntity.getStringPatternTom()).isEqualTo(DEFAULT_STRING_PATTERN_TOM);
        assertThat(testFieldTestEntity.getIntegerTom()).isEqualTo(DEFAULT_INTEGER_TOM);
        assertThat(testFieldTestEntity.getIntegerRequiredTom()).isEqualTo(DEFAULT_INTEGER_REQUIRED_TOM);
        assertThat(testFieldTestEntity.getIntegerMinTom()).isEqualTo(DEFAULT_INTEGER_MIN_TOM);
        assertThat(testFieldTestEntity.getIntegerMaxTom()).isEqualTo(DEFAULT_INTEGER_MAX_TOM);
        assertThat(testFieldTestEntity.getLongTom()).isEqualTo(DEFAULT_LONG_TOM);
        assertThat(testFieldTestEntity.getLongRequiredTom()).isEqualTo(DEFAULT_LONG_REQUIRED_TOM);
        assertThat(testFieldTestEntity.getLongMinTom()).isEqualTo(DEFAULT_LONG_MIN_TOM);
        assertThat(testFieldTestEntity.getLongMaxTom()).isEqualTo(DEFAULT_LONG_MAX_TOM);
        assertThat(testFieldTestEntity.getFloatTom()).isEqualTo(DEFAULT_FLOAT_TOM);
        assertThat(testFieldTestEntity.getFloatRequiredTom()).isEqualTo(DEFAULT_FLOAT_REQUIRED_TOM);
        assertThat(testFieldTestEntity.getFloatMinTom()).isEqualTo(DEFAULT_FLOAT_MIN_TOM);
        assertThat(testFieldTestEntity.getFloatMaxTom()).isEqualTo(DEFAULT_FLOAT_MAX_TOM);
        assertThat(testFieldTestEntity.getDoubleRequiredTom()).isEqualTo(DEFAULT_DOUBLE_REQUIRED_TOM);
        assertThat(testFieldTestEntity.getDoubleMinTom()).isEqualTo(DEFAULT_DOUBLE_MIN_TOM);
        assertThat(testFieldTestEntity.getDoubleMaxTom()).isEqualTo(DEFAULT_DOUBLE_MAX_TOM);
        assertThat(testFieldTestEntity.getBigDecimalRequiredTom()).isEqualTo(DEFAULT_BIG_DECIMAL_REQUIRED_TOM);
        assertThat(testFieldTestEntity.getBigDecimalMinTom()).isEqualTo(DEFAULT_BIG_DECIMAL_MIN_TOM);
        assertThat(testFieldTestEntity.getBigDecimalMaxTom()).isEqualTo(DEFAULT_BIG_DECIMAL_MAX_TOM);
        assertThat(testFieldTestEntity.getLocalDateTom()).isEqualTo(DEFAULT_LOCAL_DATE_TOM);
        assertThat(testFieldTestEntity.getLocalDateRequiredTom()).isEqualTo(DEFAULT_LOCAL_DATE_REQUIRED_TOM);
        assertThat(testFieldTestEntity.getInstantTom()).isEqualTo(DEFAULT_INSTANT_TOM);
        assertThat(testFieldTestEntity.getInstanteRequiredTom()).isEqualTo(DEFAULT_INSTANTE_REQUIRED_TOM);
        assertThat(testFieldTestEntity.getZonedDateTimeTom()).isEqualTo(DEFAULT_ZONED_DATE_TIME_TOM);
        assertThat(testFieldTestEntity.getZonedDateTimeRequiredTom()).isEqualTo(DEFAULT_ZONED_DATE_TIME_REQUIRED_TOM);
        assertThat(testFieldTestEntity.isBooleanTom()).isEqualTo(DEFAULT_BOOLEAN_TOM);
        assertThat(testFieldTestEntity.isBooleanRequiredTom()).isEqualTo(DEFAULT_BOOLEAN_REQUIRED_TOM);
        assertThat(testFieldTestEntity.getEnumTom()).isEqualTo(DEFAULT_ENUM_TOM);
        assertThat(testFieldTestEntity.getEnumRequiredTom()).isEqualTo(DEFAULT_ENUM_REQUIRED_TOM);
        assertThat(testFieldTestEntity.getByteImageTom()).isEqualTo(DEFAULT_BYTE_IMAGE_TOM);
        assertThat(testFieldTestEntity.getByteImageTomContentType()).isEqualTo(DEFAULT_BYTE_IMAGE_TOM_CONTENT_TYPE);
        assertThat(testFieldTestEntity.getByteImageRequiredTom()).isEqualTo(DEFAULT_BYTE_IMAGE_REQUIRED_TOM);
        assertThat(testFieldTestEntity.getByteImageRequiredTomContentType()).isEqualTo(DEFAULT_BYTE_IMAGE_REQUIRED_TOM_CONTENT_TYPE);
        assertThat(testFieldTestEntity.getByteImageMinbytesTom()).isEqualTo(DEFAULT_BYTE_IMAGE_MINBYTES_TOM);
        assertThat(testFieldTestEntity.getByteImageMinbytesTomContentType()).isEqualTo(DEFAULT_BYTE_IMAGE_MINBYTES_TOM_CONTENT_TYPE);
        assertThat(testFieldTestEntity.getByteImageMaxbytesTom()).isEqualTo(DEFAULT_BYTE_IMAGE_MAXBYTES_TOM);
        assertThat(testFieldTestEntity.getByteImageMaxbytesTomContentType()).isEqualTo(DEFAULT_BYTE_IMAGE_MAXBYTES_TOM_CONTENT_TYPE);
        assertThat(testFieldTestEntity.getByteAnyTom()).isEqualTo(DEFAULT_BYTE_ANY_TOM);
        assertThat(testFieldTestEntity.getByteAnyTomContentType()).isEqualTo(DEFAULT_BYTE_ANY_TOM_CONTENT_TYPE);
        assertThat(testFieldTestEntity.getByteAnyRequiredTom()).isEqualTo(DEFAULT_BYTE_ANY_REQUIRED_TOM);
        assertThat(testFieldTestEntity.getByteAnyRequiredTomContentType()).isEqualTo(DEFAULT_BYTE_ANY_REQUIRED_TOM_CONTENT_TYPE);
        assertThat(testFieldTestEntity.getByteAnyMinbytesTom()).isEqualTo(DEFAULT_BYTE_ANY_MINBYTES_TOM);
        assertThat(testFieldTestEntity.getByteAnyMinbytesTomContentType()).isEqualTo(DEFAULT_BYTE_ANY_MINBYTES_TOM_CONTENT_TYPE);
        assertThat(testFieldTestEntity.getByteAnyMaxbytesTom()).isEqualTo(DEFAULT_BYTE_ANY_MAXBYTES_TOM);
        assertThat(testFieldTestEntity.getByteAnyMaxbytesTomContentType()).isEqualTo(DEFAULT_BYTE_ANY_MAXBYTES_TOM_CONTENT_TYPE);
        assertThat(testFieldTestEntity.getByteTextTom()).isEqualTo(DEFAULT_BYTE_TEXT_TOM);
        assertThat(testFieldTestEntity.getByteTextRequiredTom()).isEqualTo(DEFAULT_BYTE_TEXT_REQUIRED_TOM);
        assertThat(testFieldTestEntity.getByteTextMinbytesTom()).isEqualTo(DEFAULT_BYTE_TEXT_MINBYTES_TOM);
        assertThat(testFieldTestEntity.getByteTextMaxbytesTom()).isEqualTo(DEFAULT_BYTE_TEXT_MAXBYTES_TOM);

        // Validate the FieldTestEntity in Elasticsearch
        FieldTestEntity fieldTestEntityEs = fieldTestEntitySearchRepository.findOne(testFieldTestEntity.getId());
        assertThat(fieldTestEntityEs).isEqualToComparingFieldByField(testFieldTestEntity);
    }

    @Test
    @Transactional
    public void createFieldTestEntityWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = fieldTestEntityRepository.findAll().size();

        // Create the FieldTestEntity with an existing ID
        fieldTestEntity.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFieldTestEntityMockMvc.perform(post("/api/field-test-entities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fieldTestEntity)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<FieldTestEntity> fieldTestEntityList = fieldTestEntityRepository.findAll();
        assertThat(fieldTestEntityList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkStringRequiredTomIsRequired() throws Exception {
        int databaseSizeBeforeTest = fieldTestEntityRepository.findAll().size();
        // set the field null
        fieldTestEntity.setStringRequiredTom(null);

        // Create the FieldTestEntity, which fails.

        restFieldTestEntityMockMvc.perform(post("/api/field-test-entities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fieldTestEntity)))
            .andExpect(status().isBadRequest());

        List<FieldTestEntity> fieldTestEntityList = fieldTestEntityRepository.findAll();
        assertThat(fieldTestEntityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIntegerRequiredTomIsRequired() throws Exception {
        int databaseSizeBeforeTest = fieldTestEntityRepository.findAll().size();
        // set the field null
        fieldTestEntity.setIntegerRequiredTom(null);

        // Create the FieldTestEntity, which fails.

        restFieldTestEntityMockMvc.perform(post("/api/field-test-entities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fieldTestEntity)))
            .andExpect(status().isBadRequest());

        List<FieldTestEntity> fieldTestEntityList = fieldTestEntityRepository.findAll();
        assertThat(fieldTestEntityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLongRequiredTomIsRequired() throws Exception {
        int databaseSizeBeforeTest = fieldTestEntityRepository.findAll().size();
        // set the field null
        fieldTestEntity.setLongRequiredTom(null);

        // Create the FieldTestEntity, which fails.

        restFieldTestEntityMockMvc.perform(post("/api/field-test-entities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fieldTestEntity)))
            .andExpect(status().isBadRequest());

        List<FieldTestEntity> fieldTestEntityList = fieldTestEntityRepository.findAll();
        assertThat(fieldTestEntityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFloatRequiredTomIsRequired() throws Exception {
        int databaseSizeBeforeTest = fieldTestEntityRepository.findAll().size();
        // set the field null
        fieldTestEntity.setFloatRequiredTom(null);

        // Create the FieldTestEntity, which fails.

        restFieldTestEntityMockMvc.perform(post("/api/field-test-entities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fieldTestEntity)))
            .andExpect(status().isBadRequest());

        List<FieldTestEntity> fieldTestEntityList = fieldTestEntityRepository.findAll();
        assertThat(fieldTestEntityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDoubleRequiredTomIsRequired() throws Exception {
        int databaseSizeBeforeTest = fieldTestEntityRepository.findAll().size();
        // set the field null
        fieldTestEntity.setDoubleRequiredTom(null);

        // Create the FieldTestEntity, which fails.

        restFieldTestEntityMockMvc.perform(post("/api/field-test-entities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fieldTestEntity)))
            .andExpect(status().isBadRequest());

        List<FieldTestEntity> fieldTestEntityList = fieldTestEntityRepository.findAll();
        assertThat(fieldTestEntityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkBigDecimalRequiredTomIsRequired() throws Exception {
        int databaseSizeBeforeTest = fieldTestEntityRepository.findAll().size();
        // set the field null
        fieldTestEntity.setBigDecimalRequiredTom(null);

        // Create the FieldTestEntity, which fails.

        restFieldTestEntityMockMvc.perform(post("/api/field-test-entities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fieldTestEntity)))
            .andExpect(status().isBadRequest());

        List<FieldTestEntity> fieldTestEntityList = fieldTestEntityRepository.findAll();
        assertThat(fieldTestEntityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLocalDateRequiredTomIsRequired() throws Exception {
        int databaseSizeBeforeTest = fieldTestEntityRepository.findAll().size();
        // set the field null
        fieldTestEntity.setLocalDateRequiredTom(null);

        // Create the FieldTestEntity, which fails.

        restFieldTestEntityMockMvc.perform(post("/api/field-test-entities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fieldTestEntity)))
            .andExpect(status().isBadRequest());

        List<FieldTestEntity> fieldTestEntityList = fieldTestEntityRepository.findAll();
        assertThat(fieldTestEntityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkInstanteRequiredTomIsRequired() throws Exception {
        int databaseSizeBeforeTest = fieldTestEntityRepository.findAll().size();
        // set the field null
        fieldTestEntity.setInstanteRequiredTom(null);

        // Create the FieldTestEntity, which fails.

        restFieldTestEntityMockMvc.perform(post("/api/field-test-entities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fieldTestEntity)))
            .andExpect(status().isBadRequest());

        List<FieldTestEntity> fieldTestEntityList = fieldTestEntityRepository.findAll();
        assertThat(fieldTestEntityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkZonedDateTimeRequiredTomIsRequired() throws Exception {
        int databaseSizeBeforeTest = fieldTestEntityRepository.findAll().size();
        // set the field null
        fieldTestEntity.setZonedDateTimeRequiredTom(null);

        // Create the FieldTestEntity, which fails.

        restFieldTestEntityMockMvc.perform(post("/api/field-test-entities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fieldTestEntity)))
            .andExpect(status().isBadRequest());

        List<FieldTestEntity> fieldTestEntityList = fieldTestEntityRepository.findAll();
        assertThat(fieldTestEntityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkBooleanRequiredTomIsRequired() throws Exception {
        int databaseSizeBeforeTest = fieldTestEntityRepository.findAll().size();
        // set the field null
        fieldTestEntity.setBooleanRequiredTom(null);

        // Create the FieldTestEntity, which fails.

        restFieldTestEntityMockMvc.perform(post("/api/field-test-entities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fieldTestEntity)))
            .andExpect(status().isBadRequest());

        List<FieldTestEntity> fieldTestEntityList = fieldTestEntityRepository.findAll();
        assertThat(fieldTestEntityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEnumRequiredTomIsRequired() throws Exception {
        int databaseSizeBeforeTest = fieldTestEntityRepository.findAll().size();
        // set the field null
        fieldTestEntity.setEnumRequiredTom(null);

        // Create the FieldTestEntity, which fails.

        restFieldTestEntityMockMvc.perform(post("/api/field-test-entities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fieldTestEntity)))
            .andExpect(status().isBadRequest());

        List<FieldTestEntity> fieldTestEntityList = fieldTestEntityRepository.findAll();
        assertThat(fieldTestEntityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkByteImageRequiredTomIsRequired() throws Exception {
        int databaseSizeBeforeTest = fieldTestEntityRepository.findAll().size();
        // set the field null
        fieldTestEntity.setByteImageRequiredTom(null);

        // Create the FieldTestEntity, which fails.

        restFieldTestEntityMockMvc.perform(post("/api/field-test-entities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fieldTestEntity)))
            .andExpect(status().isBadRequest());

        List<FieldTestEntity> fieldTestEntityList = fieldTestEntityRepository.findAll();
        assertThat(fieldTestEntityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkByteAnyRequiredTomIsRequired() throws Exception {
        int databaseSizeBeforeTest = fieldTestEntityRepository.findAll().size();
        // set the field null
        fieldTestEntity.setByteAnyRequiredTom(null);

        // Create the FieldTestEntity, which fails.

        restFieldTestEntityMockMvc.perform(post("/api/field-test-entities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fieldTestEntity)))
            .andExpect(status().isBadRequest());

        List<FieldTestEntity> fieldTestEntityList = fieldTestEntityRepository.findAll();
        assertThat(fieldTestEntityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkByteTextRequiredTomIsRequired() throws Exception {
        int databaseSizeBeforeTest = fieldTestEntityRepository.findAll().size();
        // set the field null
        fieldTestEntity.setByteTextRequiredTom(null);

        // Create the FieldTestEntity, which fails.

        restFieldTestEntityMockMvc.perform(post("/api/field-test-entities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fieldTestEntity)))
            .andExpect(status().isBadRequest());

        List<FieldTestEntity> fieldTestEntityList = fieldTestEntityRepository.findAll();
        assertThat(fieldTestEntityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllFieldTestEntities() throws Exception {
        // Initialize the database
        fieldTestEntityRepository.saveAndFlush(fieldTestEntity);

        // Get all the fieldTestEntityList
        restFieldTestEntityMockMvc.perform(get("/api/field-test-entities?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(fieldTestEntity.getId().intValue())))
            .andExpect(jsonPath("$.[*].stringTom").value(hasItem(DEFAULT_STRING_TOM.toString())))
            .andExpect(jsonPath("$.[*].stringRequiredTom").value(hasItem(DEFAULT_STRING_REQUIRED_TOM.toString())))
            .andExpect(jsonPath("$.[*].stringMinlengthTom").value(hasItem(DEFAULT_STRING_MINLENGTH_TOM.toString())))
            .andExpect(jsonPath("$.[*].stringMaxlengthTom").value(hasItem(DEFAULT_STRING_MAXLENGTH_TOM.toString())))
            .andExpect(jsonPath("$.[*].stringPatternTom").value(hasItem(DEFAULT_STRING_PATTERN_TOM.toString())))
            .andExpect(jsonPath("$.[*].integerTom").value(hasItem(DEFAULT_INTEGER_TOM)))
            .andExpect(jsonPath("$.[*].integerRequiredTom").value(hasItem(DEFAULT_INTEGER_REQUIRED_TOM)))
            .andExpect(jsonPath("$.[*].integerMinTom").value(hasItem(DEFAULT_INTEGER_MIN_TOM)))
            .andExpect(jsonPath("$.[*].integerMaxTom").value(hasItem(DEFAULT_INTEGER_MAX_TOM)))
            .andExpect(jsonPath("$.[*].longTom").value(hasItem(DEFAULT_LONG_TOM.intValue())))
            .andExpect(jsonPath("$.[*].longRequiredTom").value(hasItem(DEFAULT_LONG_REQUIRED_TOM.intValue())))
            .andExpect(jsonPath("$.[*].longMinTom").value(hasItem(DEFAULT_LONG_MIN_TOM.intValue())))
            .andExpect(jsonPath("$.[*].longMaxTom").value(hasItem(DEFAULT_LONG_MAX_TOM.intValue())))
            .andExpect(jsonPath("$.[*].floatTom").value(hasItem(DEFAULT_FLOAT_TOM.doubleValue())))
            .andExpect(jsonPath("$.[*].floatRequiredTom").value(hasItem(DEFAULT_FLOAT_REQUIRED_TOM.doubleValue())))
            .andExpect(jsonPath("$.[*].floatMinTom").value(hasItem(DEFAULT_FLOAT_MIN_TOM.doubleValue())))
            .andExpect(jsonPath("$.[*].floatMaxTom").value(hasItem(DEFAULT_FLOAT_MAX_TOM.doubleValue())))
            .andExpect(jsonPath("$.[*].doubleRequiredTom").value(hasItem(DEFAULT_DOUBLE_REQUIRED_TOM.doubleValue())))
            .andExpect(jsonPath("$.[*].doubleMinTom").value(hasItem(DEFAULT_DOUBLE_MIN_TOM.doubleValue())))
            .andExpect(jsonPath("$.[*].doubleMaxTom").value(hasItem(DEFAULT_DOUBLE_MAX_TOM.doubleValue())))
            .andExpect(jsonPath("$.[*].bigDecimalRequiredTom").value(hasItem(DEFAULT_BIG_DECIMAL_REQUIRED_TOM.intValue())))
            .andExpect(jsonPath("$.[*].bigDecimalMinTom").value(hasItem(DEFAULT_BIG_DECIMAL_MIN_TOM.intValue())))
            .andExpect(jsonPath("$.[*].bigDecimalMaxTom").value(hasItem(DEFAULT_BIG_DECIMAL_MAX_TOM.intValue())))
            .andExpect(jsonPath("$.[*].localDateTom").value(hasItem(DEFAULT_LOCAL_DATE_TOM.toString())))
            .andExpect(jsonPath("$.[*].localDateRequiredTom").value(hasItem(DEFAULT_LOCAL_DATE_REQUIRED_TOM.toString())))
            .andExpect(jsonPath("$.[*].instantTom").value(hasItem(DEFAULT_INSTANT_TOM.toString())))
            .andExpect(jsonPath("$.[*].instanteRequiredTom").value(hasItem(DEFAULT_INSTANTE_REQUIRED_TOM.toString())))
            .andExpect(jsonPath("$.[*].zonedDateTimeTom").value(hasItem(sameInstant(DEFAULT_ZONED_DATE_TIME_TOM))))
            .andExpect(jsonPath("$.[*].zonedDateTimeRequiredTom").value(hasItem(sameInstant(DEFAULT_ZONED_DATE_TIME_REQUIRED_TOM))))
            .andExpect(jsonPath("$.[*].booleanTom").value(hasItem(DEFAULT_BOOLEAN_TOM.booleanValue())))
            .andExpect(jsonPath("$.[*].booleanRequiredTom").value(hasItem(DEFAULT_BOOLEAN_REQUIRED_TOM.booleanValue())))
            .andExpect(jsonPath("$.[*].enumTom").value(hasItem(DEFAULT_ENUM_TOM.toString())))
            .andExpect(jsonPath("$.[*].enumRequiredTom").value(hasItem(DEFAULT_ENUM_REQUIRED_TOM.toString())))
            .andExpect(jsonPath("$.[*].byteImageTomContentType").value(hasItem(DEFAULT_BYTE_IMAGE_TOM_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].byteImageTom").value(hasItem(Base64Utils.encodeToString(DEFAULT_BYTE_IMAGE_TOM))))
            .andExpect(jsonPath("$.[*].byteImageRequiredTomContentType").value(hasItem(DEFAULT_BYTE_IMAGE_REQUIRED_TOM_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].byteImageRequiredTom").value(hasItem(Base64Utils.encodeToString(DEFAULT_BYTE_IMAGE_REQUIRED_TOM))))
            .andExpect(jsonPath("$.[*].byteImageMinbytesTomContentType").value(hasItem(DEFAULT_BYTE_IMAGE_MINBYTES_TOM_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].byteImageMinbytesTom").value(hasItem(Base64Utils.encodeToString(DEFAULT_BYTE_IMAGE_MINBYTES_TOM))))
            .andExpect(jsonPath("$.[*].byteImageMaxbytesTomContentType").value(hasItem(DEFAULT_BYTE_IMAGE_MAXBYTES_TOM_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].byteImageMaxbytesTom").value(hasItem(Base64Utils.encodeToString(DEFAULT_BYTE_IMAGE_MAXBYTES_TOM))))
            .andExpect(jsonPath("$.[*].byteAnyTomContentType").value(hasItem(DEFAULT_BYTE_ANY_TOM_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].byteAnyTom").value(hasItem(Base64Utils.encodeToString(DEFAULT_BYTE_ANY_TOM))))
            .andExpect(jsonPath("$.[*].byteAnyRequiredTomContentType").value(hasItem(DEFAULT_BYTE_ANY_REQUIRED_TOM_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].byteAnyRequiredTom").value(hasItem(Base64Utils.encodeToString(DEFAULT_BYTE_ANY_REQUIRED_TOM))))
            .andExpect(jsonPath("$.[*].byteAnyMinbytesTomContentType").value(hasItem(DEFAULT_BYTE_ANY_MINBYTES_TOM_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].byteAnyMinbytesTom").value(hasItem(Base64Utils.encodeToString(DEFAULT_BYTE_ANY_MINBYTES_TOM))))
            .andExpect(jsonPath("$.[*].byteAnyMaxbytesTomContentType").value(hasItem(DEFAULT_BYTE_ANY_MAXBYTES_TOM_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].byteAnyMaxbytesTom").value(hasItem(Base64Utils.encodeToString(DEFAULT_BYTE_ANY_MAXBYTES_TOM))))
            .andExpect(jsonPath("$.[*].byteTextTom").value(hasItem(DEFAULT_BYTE_TEXT_TOM.toString())))
            .andExpect(jsonPath("$.[*].byteTextRequiredTom").value(hasItem(DEFAULT_BYTE_TEXT_REQUIRED_TOM.toString())))
            .andExpect(jsonPath("$.[*].byteTextMinbytesTom").value(hasItem(DEFAULT_BYTE_TEXT_MINBYTES_TOM.toString())))
            .andExpect(jsonPath("$.[*].byteTextMaxbytesTom").value(hasItem(DEFAULT_BYTE_TEXT_MAXBYTES_TOM.toString())));
    }

    @Test
    @Transactional
    public void getFieldTestEntity() throws Exception {
        // Initialize the database
        fieldTestEntityRepository.saveAndFlush(fieldTestEntity);

        // Get the fieldTestEntity
        restFieldTestEntityMockMvc.perform(get("/api/field-test-entities/{id}", fieldTestEntity.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(fieldTestEntity.getId().intValue()))
            .andExpect(jsonPath("$.stringTom").value(DEFAULT_STRING_TOM.toString()))
            .andExpect(jsonPath("$.stringRequiredTom").value(DEFAULT_STRING_REQUIRED_TOM.toString()))
            .andExpect(jsonPath("$.stringMinlengthTom").value(DEFAULT_STRING_MINLENGTH_TOM.toString()))
            .andExpect(jsonPath("$.stringMaxlengthTom").value(DEFAULT_STRING_MAXLENGTH_TOM.toString()))
            .andExpect(jsonPath("$.stringPatternTom").value(DEFAULT_STRING_PATTERN_TOM.toString()))
            .andExpect(jsonPath("$.integerTom").value(DEFAULT_INTEGER_TOM))
            .andExpect(jsonPath("$.integerRequiredTom").value(DEFAULT_INTEGER_REQUIRED_TOM))
            .andExpect(jsonPath("$.integerMinTom").value(DEFAULT_INTEGER_MIN_TOM))
            .andExpect(jsonPath("$.integerMaxTom").value(DEFAULT_INTEGER_MAX_TOM))
            .andExpect(jsonPath("$.longTom").value(DEFAULT_LONG_TOM.intValue()))
            .andExpect(jsonPath("$.longRequiredTom").value(DEFAULT_LONG_REQUIRED_TOM.intValue()))
            .andExpect(jsonPath("$.longMinTom").value(DEFAULT_LONG_MIN_TOM.intValue()))
            .andExpect(jsonPath("$.longMaxTom").value(DEFAULT_LONG_MAX_TOM.intValue()))
            .andExpect(jsonPath("$.floatTom").value(DEFAULT_FLOAT_TOM.doubleValue()))
            .andExpect(jsonPath("$.floatRequiredTom").value(DEFAULT_FLOAT_REQUIRED_TOM.doubleValue()))
            .andExpect(jsonPath("$.floatMinTom").value(DEFAULT_FLOAT_MIN_TOM.doubleValue()))
            .andExpect(jsonPath("$.floatMaxTom").value(DEFAULT_FLOAT_MAX_TOM.doubleValue()))
            .andExpect(jsonPath("$.doubleRequiredTom").value(DEFAULT_DOUBLE_REQUIRED_TOM.doubleValue()))
            .andExpect(jsonPath("$.doubleMinTom").value(DEFAULT_DOUBLE_MIN_TOM.doubleValue()))
            .andExpect(jsonPath("$.doubleMaxTom").value(DEFAULT_DOUBLE_MAX_TOM.doubleValue()))
            .andExpect(jsonPath("$.bigDecimalRequiredTom").value(DEFAULT_BIG_DECIMAL_REQUIRED_TOM.intValue()))
            .andExpect(jsonPath("$.bigDecimalMinTom").value(DEFAULT_BIG_DECIMAL_MIN_TOM.intValue()))
            .andExpect(jsonPath("$.bigDecimalMaxTom").value(DEFAULT_BIG_DECIMAL_MAX_TOM.intValue()))
            .andExpect(jsonPath("$.localDateTom").value(DEFAULT_LOCAL_DATE_TOM.toString()))
            .andExpect(jsonPath("$.localDateRequiredTom").value(DEFAULT_LOCAL_DATE_REQUIRED_TOM.toString()))
            .andExpect(jsonPath("$.instantTom").value(DEFAULT_INSTANT_TOM.toString()))
            .andExpect(jsonPath("$.instanteRequiredTom").value(DEFAULT_INSTANTE_REQUIRED_TOM.toString()))
            .andExpect(jsonPath("$.zonedDateTimeTom").value(sameInstant(DEFAULT_ZONED_DATE_TIME_TOM)))
            .andExpect(jsonPath("$.zonedDateTimeRequiredTom").value(sameInstant(DEFAULT_ZONED_DATE_TIME_REQUIRED_TOM)))
            .andExpect(jsonPath("$.booleanTom").value(DEFAULT_BOOLEAN_TOM.booleanValue()))
            .andExpect(jsonPath("$.booleanRequiredTom").value(DEFAULT_BOOLEAN_REQUIRED_TOM.booleanValue()))
            .andExpect(jsonPath("$.enumTom").value(DEFAULT_ENUM_TOM.toString()))
            .andExpect(jsonPath("$.enumRequiredTom").value(DEFAULT_ENUM_REQUIRED_TOM.toString()))
            .andExpect(jsonPath("$.byteImageTomContentType").value(DEFAULT_BYTE_IMAGE_TOM_CONTENT_TYPE))
            .andExpect(jsonPath("$.byteImageTom").value(Base64Utils.encodeToString(DEFAULT_BYTE_IMAGE_TOM)))
            .andExpect(jsonPath("$.byteImageRequiredTomContentType").value(DEFAULT_BYTE_IMAGE_REQUIRED_TOM_CONTENT_TYPE))
            .andExpect(jsonPath("$.byteImageRequiredTom").value(Base64Utils.encodeToString(DEFAULT_BYTE_IMAGE_REQUIRED_TOM)))
            .andExpect(jsonPath("$.byteImageMinbytesTomContentType").value(DEFAULT_BYTE_IMAGE_MINBYTES_TOM_CONTENT_TYPE))
            .andExpect(jsonPath("$.byteImageMinbytesTom").value(Base64Utils.encodeToString(DEFAULT_BYTE_IMAGE_MINBYTES_TOM)))
            .andExpect(jsonPath("$.byteImageMaxbytesTomContentType").value(DEFAULT_BYTE_IMAGE_MAXBYTES_TOM_CONTENT_TYPE))
            .andExpect(jsonPath("$.byteImageMaxbytesTom").value(Base64Utils.encodeToString(DEFAULT_BYTE_IMAGE_MAXBYTES_TOM)))
            .andExpect(jsonPath("$.byteAnyTomContentType").value(DEFAULT_BYTE_ANY_TOM_CONTENT_TYPE))
            .andExpect(jsonPath("$.byteAnyTom").value(Base64Utils.encodeToString(DEFAULT_BYTE_ANY_TOM)))
            .andExpect(jsonPath("$.byteAnyRequiredTomContentType").value(DEFAULT_BYTE_ANY_REQUIRED_TOM_CONTENT_TYPE))
            .andExpect(jsonPath("$.byteAnyRequiredTom").value(Base64Utils.encodeToString(DEFAULT_BYTE_ANY_REQUIRED_TOM)))
            .andExpect(jsonPath("$.byteAnyMinbytesTomContentType").value(DEFAULT_BYTE_ANY_MINBYTES_TOM_CONTENT_TYPE))
            .andExpect(jsonPath("$.byteAnyMinbytesTom").value(Base64Utils.encodeToString(DEFAULT_BYTE_ANY_MINBYTES_TOM)))
            .andExpect(jsonPath("$.byteAnyMaxbytesTomContentType").value(DEFAULT_BYTE_ANY_MAXBYTES_TOM_CONTENT_TYPE))
            .andExpect(jsonPath("$.byteAnyMaxbytesTom").value(Base64Utils.encodeToString(DEFAULT_BYTE_ANY_MAXBYTES_TOM)))
            .andExpect(jsonPath("$.byteTextTom").value(DEFAULT_BYTE_TEXT_TOM.toString()))
            .andExpect(jsonPath("$.byteTextRequiredTom").value(DEFAULT_BYTE_TEXT_REQUIRED_TOM.toString()))
            .andExpect(jsonPath("$.byteTextMinbytesTom").value(DEFAULT_BYTE_TEXT_MINBYTES_TOM.toString()))
            .andExpect(jsonPath("$.byteTextMaxbytesTom").value(DEFAULT_BYTE_TEXT_MAXBYTES_TOM.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingFieldTestEntity() throws Exception {
        // Get the fieldTestEntity
        restFieldTestEntityMockMvc.perform(get("/api/field-test-entities/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFieldTestEntity() throws Exception {
        // Initialize the database
        fieldTestEntityRepository.saveAndFlush(fieldTestEntity);
        fieldTestEntitySearchRepository.save(fieldTestEntity);
        int databaseSizeBeforeUpdate = fieldTestEntityRepository.findAll().size();

        // Update the fieldTestEntity
        FieldTestEntity updatedFieldTestEntity = fieldTestEntityRepository.findOne(fieldTestEntity.getId());
        updatedFieldTestEntity
            .stringTom(UPDATED_STRING_TOM)
            .stringRequiredTom(UPDATED_STRING_REQUIRED_TOM)
            .stringMinlengthTom(UPDATED_STRING_MINLENGTH_TOM)
            .stringMaxlengthTom(UPDATED_STRING_MAXLENGTH_TOM)
            .stringPatternTom(UPDATED_STRING_PATTERN_TOM)
            .integerTom(UPDATED_INTEGER_TOM)
            .integerRequiredTom(UPDATED_INTEGER_REQUIRED_TOM)
            .integerMinTom(UPDATED_INTEGER_MIN_TOM)
            .integerMaxTom(UPDATED_INTEGER_MAX_TOM)
            .longTom(UPDATED_LONG_TOM)
            .longRequiredTom(UPDATED_LONG_REQUIRED_TOM)
            .longMinTom(UPDATED_LONG_MIN_TOM)
            .longMaxTom(UPDATED_LONG_MAX_TOM)
            .floatTom(UPDATED_FLOAT_TOM)
            .floatRequiredTom(UPDATED_FLOAT_REQUIRED_TOM)
            .floatMinTom(UPDATED_FLOAT_MIN_TOM)
            .floatMaxTom(UPDATED_FLOAT_MAX_TOM)
            .doubleRequiredTom(UPDATED_DOUBLE_REQUIRED_TOM)
            .doubleMinTom(UPDATED_DOUBLE_MIN_TOM)
            .doubleMaxTom(UPDATED_DOUBLE_MAX_TOM)
            .bigDecimalRequiredTom(UPDATED_BIG_DECIMAL_REQUIRED_TOM)
            .bigDecimalMinTom(UPDATED_BIG_DECIMAL_MIN_TOM)
            .bigDecimalMaxTom(UPDATED_BIG_DECIMAL_MAX_TOM)
            .localDateTom(UPDATED_LOCAL_DATE_TOM)
            .localDateRequiredTom(UPDATED_LOCAL_DATE_REQUIRED_TOM)
            .instantTom(UPDATED_INSTANT_TOM)
            .instanteRequiredTom(UPDATED_INSTANTE_REQUIRED_TOM)
            .zonedDateTimeTom(UPDATED_ZONED_DATE_TIME_TOM)
            .zonedDateTimeRequiredTom(UPDATED_ZONED_DATE_TIME_REQUIRED_TOM)
            .booleanTom(UPDATED_BOOLEAN_TOM)
            .booleanRequiredTom(UPDATED_BOOLEAN_REQUIRED_TOM)
            .enumTom(UPDATED_ENUM_TOM)
            .enumRequiredTom(UPDATED_ENUM_REQUIRED_TOM)
            .byteImageTom(UPDATED_BYTE_IMAGE_TOM)
            .byteImageTomContentType(UPDATED_BYTE_IMAGE_TOM_CONTENT_TYPE)
            .byteImageRequiredTom(UPDATED_BYTE_IMAGE_REQUIRED_TOM)
            .byteImageRequiredTomContentType(UPDATED_BYTE_IMAGE_REQUIRED_TOM_CONTENT_TYPE)
            .byteImageMinbytesTom(UPDATED_BYTE_IMAGE_MINBYTES_TOM)
            .byteImageMinbytesTomContentType(UPDATED_BYTE_IMAGE_MINBYTES_TOM_CONTENT_TYPE)
            .byteImageMaxbytesTom(UPDATED_BYTE_IMAGE_MAXBYTES_TOM)
            .byteImageMaxbytesTomContentType(UPDATED_BYTE_IMAGE_MAXBYTES_TOM_CONTENT_TYPE)
            .byteAnyTom(UPDATED_BYTE_ANY_TOM)
            .byteAnyTomContentType(UPDATED_BYTE_ANY_TOM_CONTENT_TYPE)
            .byteAnyRequiredTom(UPDATED_BYTE_ANY_REQUIRED_TOM)
            .byteAnyRequiredTomContentType(UPDATED_BYTE_ANY_REQUIRED_TOM_CONTENT_TYPE)
            .byteAnyMinbytesTom(UPDATED_BYTE_ANY_MINBYTES_TOM)
            .byteAnyMinbytesTomContentType(UPDATED_BYTE_ANY_MINBYTES_TOM_CONTENT_TYPE)
            .byteAnyMaxbytesTom(UPDATED_BYTE_ANY_MAXBYTES_TOM)
            .byteAnyMaxbytesTomContentType(UPDATED_BYTE_ANY_MAXBYTES_TOM_CONTENT_TYPE)
            .byteTextTom(UPDATED_BYTE_TEXT_TOM)
            .byteTextRequiredTom(UPDATED_BYTE_TEXT_REQUIRED_TOM)
            .byteTextMinbytesTom(UPDATED_BYTE_TEXT_MINBYTES_TOM)
            .byteTextMaxbytesTom(UPDATED_BYTE_TEXT_MAXBYTES_TOM);

        restFieldTestEntityMockMvc.perform(put("/api/field-test-entities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedFieldTestEntity)))
            .andExpect(status().isOk());

        // Validate the FieldTestEntity in the database
        List<FieldTestEntity> fieldTestEntityList = fieldTestEntityRepository.findAll();
        assertThat(fieldTestEntityList).hasSize(databaseSizeBeforeUpdate);
        FieldTestEntity testFieldTestEntity = fieldTestEntityList.get(fieldTestEntityList.size() - 1);
        assertThat(testFieldTestEntity.getStringTom()).isEqualTo(UPDATED_STRING_TOM);
        assertThat(testFieldTestEntity.getStringRequiredTom()).isEqualTo(UPDATED_STRING_REQUIRED_TOM);
        assertThat(testFieldTestEntity.getStringMinlengthTom()).isEqualTo(UPDATED_STRING_MINLENGTH_TOM);
        assertThat(testFieldTestEntity.getStringMaxlengthTom()).isEqualTo(UPDATED_STRING_MAXLENGTH_TOM);
        assertThat(testFieldTestEntity.getStringPatternTom()).isEqualTo(UPDATED_STRING_PATTERN_TOM);
        assertThat(testFieldTestEntity.getIntegerTom()).isEqualTo(UPDATED_INTEGER_TOM);
        assertThat(testFieldTestEntity.getIntegerRequiredTom()).isEqualTo(UPDATED_INTEGER_REQUIRED_TOM);
        assertThat(testFieldTestEntity.getIntegerMinTom()).isEqualTo(UPDATED_INTEGER_MIN_TOM);
        assertThat(testFieldTestEntity.getIntegerMaxTom()).isEqualTo(UPDATED_INTEGER_MAX_TOM);
        assertThat(testFieldTestEntity.getLongTom()).isEqualTo(UPDATED_LONG_TOM);
        assertThat(testFieldTestEntity.getLongRequiredTom()).isEqualTo(UPDATED_LONG_REQUIRED_TOM);
        assertThat(testFieldTestEntity.getLongMinTom()).isEqualTo(UPDATED_LONG_MIN_TOM);
        assertThat(testFieldTestEntity.getLongMaxTom()).isEqualTo(UPDATED_LONG_MAX_TOM);
        assertThat(testFieldTestEntity.getFloatTom()).isEqualTo(UPDATED_FLOAT_TOM);
        assertThat(testFieldTestEntity.getFloatRequiredTom()).isEqualTo(UPDATED_FLOAT_REQUIRED_TOM);
        assertThat(testFieldTestEntity.getFloatMinTom()).isEqualTo(UPDATED_FLOAT_MIN_TOM);
        assertThat(testFieldTestEntity.getFloatMaxTom()).isEqualTo(UPDATED_FLOAT_MAX_TOM);
        assertThat(testFieldTestEntity.getDoubleRequiredTom()).isEqualTo(UPDATED_DOUBLE_REQUIRED_TOM);
        assertThat(testFieldTestEntity.getDoubleMinTom()).isEqualTo(UPDATED_DOUBLE_MIN_TOM);
        assertThat(testFieldTestEntity.getDoubleMaxTom()).isEqualTo(UPDATED_DOUBLE_MAX_TOM);
        assertThat(testFieldTestEntity.getBigDecimalRequiredTom()).isEqualTo(UPDATED_BIG_DECIMAL_REQUIRED_TOM);
        assertThat(testFieldTestEntity.getBigDecimalMinTom()).isEqualTo(UPDATED_BIG_DECIMAL_MIN_TOM);
        assertThat(testFieldTestEntity.getBigDecimalMaxTom()).isEqualTo(UPDATED_BIG_DECIMAL_MAX_TOM);
        assertThat(testFieldTestEntity.getLocalDateTom()).isEqualTo(UPDATED_LOCAL_DATE_TOM);
        assertThat(testFieldTestEntity.getLocalDateRequiredTom()).isEqualTo(UPDATED_LOCAL_DATE_REQUIRED_TOM);
        assertThat(testFieldTestEntity.getInstantTom()).isEqualTo(UPDATED_INSTANT_TOM);
        assertThat(testFieldTestEntity.getInstanteRequiredTom()).isEqualTo(UPDATED_INSTANTE_REQUIRED_TOM);
        assertThat(testFieldTestEntity.getZonedDateTimeTom()).isEqualTo(UPDATED_ZONED_DATE_TIME_TOM);
        assertThat(testFieldTestEntity.getZonedDateTimeRequiredTom()).isEqualTo(UPDATED_ZONED_DATE_TIME_REQUIRED_TOM);
        assertThat(testFieldTestEntity.isBooleanTom()).isEqualTo(UPDATED_BOOLEAN_TOM);
        assertThat(testFieldTestEntity.isBooleanRequiredTom()).isEqualTo(UPDATED_BOOLEAN_REQUIRED_TOM);
        assertThat(testFieldTestEntity.getEnumTom()).isEqualTo(UPDATED_ENUM_TOM);
        assertThat(testFieldTestEntity.getEnumRequiredTom()).isEqualTo(UPDATED_ENUM_REQUIRED_TOM);
        assertThat(testFieldTestEntity.getByteImageTom()).isEqualTo(UPDATED_BYTE_IMAGE_TOM);
        assertThat(testFieldTestEntity.getByteImageTomContentType()).isEqualTo(UPDATED_BYTE_IMAGE_TOM_CONTENT_TYPE);
        assertThat(testFieldTestEntity.getByteImageRequiredTom()).isEqualTo(UPDATED_BYTE_IMAGE_REQUIRED_TOM);
        assertThat(testFieldTestEntity.getByteImageRequiredTomContentType()).isEqualTo(UPDATED_BYTE_IMAGE_REQUIRED_TOM_CONTENT_TYPE);
        assertThat(testFieldTestEntity.getByteImageMinbytesTom()).isEqualTo(UPDATED_BYTE_IMAGE_MINBYTES_TOM);
        assertThat(testFieldTestEntity.getByteImageMinbytesTomContentType()).isEqualTo(UPDATED_BYTE_IMAGE_MINBYTES_TOM_CONTENT_TYPE);
        assertThat(testFieldTestEntity.getByteImageMaxbytesTom()).isEqualTo(UPDATED_BYTE_IMAGE_MAXBYTES_TOM);
        assertThat(testFieldTestEntity.getByteImageMaxbytesTomContentType()).isEqualTo(UPDATED_BYTE_IMAGE_MAXBYTES_TOM_CONTENT_TYPE);
        assertThat(testFieldTestEntity.getByteAnyTom()).isEqualTo(UPDATED_BYTE_ANY_TOM);
        assertThat(testFieldTestEntity.getByteAnyTomContentType()).isEqualTo(UPDATED_BYTE_ANY_TOM_CONTENT_TYPE);
        assertThat(testFieldTestEntity.getByteAnyRequiredTom()).isEqualTo(UPDATED_BYTE_ANY_REQUIRED_TOM);
        assertThat(testFieldTestEntity.getByteAnyRequiredTomContentType()).isEqualTo(UPDATED_BYTE_ANY_REQUIRED_TOM_CONTENT_TYPE);
        assertThat(testFieldTestEntity.getByteAnyMinbytesTom()).isEqualTo(UPDATED_BYTE_ANY_MINBYTES_TOM);
        assertThat(testFieldTestEntity.getByteAnyMinbytesTomContentType()).isEqualTo(UPDATED_BYTE_ANY_MINBYTES_TOM_CONTENT_TYPE);
        assertThat(testFieldTestEntity.getByteAnyMaxbytesTom()).isEqualTo(UPDATED_BYTE_ANY_MAXBYTES_TOM);
        assertThat(testFieldTestEntity.getByteAnyMaxbytesTomContentType()).isEqualTo(UPDATED_BYTE_ANY_MAXBYTES_TOM_CONTENT_TYPE);
        assertThat(testFieldTestEntity.getByteTextTom()).isEqualTo(UPDATED_BYTE_TEXT_TOM);
        assertThat(testFieldTestEntity.getByteTextRequiredTom()).isEqualTo(UPDATED_BYTE_TEXT_REQUIRED_TOM);
        assertThat(testFieldTestEntity.getByteTextMinbytesTom()).isEqualTo(UPDATED_BYTE_TEXT_MINBYTES_TOM);
        assertThat(testFieldTestEntity.getByteTextMaxbytesTom()).isEqualTo(UPDATED_BYTE_TEXT_MAXBYTES_TOM);

        // Validate the FieldTestEntity in Elasticsearch
        FieldTestEntity fieldTestEntityEs = fieldTestEntitySearchRepository.findOne(testFieldTestEntity.getId());
        assertThat(fieldTestEntityEs).isEqualToComparingFieldByField(testFieldTestEntity);
    }

    @Test
    @Transactional
    public void updateNonExistingFieldTestEntity() throws Exception {
        int databaseSizeBeforeUpdate = fieldTestEntityRepository.findAll().size();

        // Create the FieldTestEntity

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restFieldTestEntityMockMvc.perform(put("/api/field-test-entities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fieldTestEntity)))
            .andExpect(status().isCreated());

        // Validate the FieldTestEntity in the database
        List<FieldTestEntity> fieldTestEntityList = fieldTestEntityRepository.findAll();
        assertThat(fieldTestEntityList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteFieldTestEntity() throws Exception {
        // Initialize the database
        fieldTestEntityRepository.saveAndFlush(fieldTestEntity);
        fieldTestEntitySearchRepository.save(fieldTestEntity);
        int databaseSizeBeforeDelete = fieldTestEntityRepository.findAll().size();

        // Get the fieldTestEntity
        restFieldTestEntityMockMvc.perform(delete("/api/field-test-entities/{id}", fieldTestEntity.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate Elasticsearch is empty
        boolean fieldTestEntityExistsInEs = fieldTestEntitySearchRepository.exists(fieldTestEntity.getId());
        assertThat(fieldTestEntityExistsInEs).isFalse();

        // Validate the database is empty
        List<FieldTestEntity> fieldTestEntityList = fieldTestEntityRepository.findAll();
        assertThat(fieldTestEntityList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void searchFieldTestEntity() throws Exception {
        // Initialize the database
        fieldTestEntityRepository.saveAndFlush(fieldTestEntity);
        fieldTestEntitySearchRepository.save(fieldTestEntity);

        // Search the fieldTestEntity
        restFieldTestEntityMockMvc.perform(get("/api/_search/field-test-entities?query=id:" + fieldTestEntity.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(fieldTestEntity.getId().intValue())))
            .andExpect(jsonPath("$.[*].stringTom").value(hasItem(DEFAULT_STRING_TOM.toString())))
            .andExpect(jsonPath("$.[*].stringRequiredTom").value(hasItem(DEFAULT_STRING_REQUIRED_TOM.toString())))
            .andExpect(jsonPath("$.[*].stringMinlengthTom").value(hasItem(DEFAULT_STRING_MINLENGTH_TOM.toString())))
            .andExpect(jsonPath("$.[*].stringMaxlengthTom").value(hasItem(DEFAULT_STRING_MAXLENGTH_TOM.toString())))
            .andExpect(jsonPath("$.[*].stringPatternTom").value(hasItem(DEFAULT_STRING_PATTERN_TOM.toString())))
            .andExpect(jsonPath("$.[*].integerTom").value(hasItem(DEFAULT_INTEGER_TOM)))
            .andExpect(jsonPath("$.[*].integerRequiredTom").value(hasItem(DEFAULT_INTEGER_REQUIRED_TOM)))
            .andExpect(jsonPath("$.[*].integerMinTom").value(hasItem(DEFAULT_INTEGER_MIN_TOM)))
            .andExpect(jsonPath("$.[*].integerMaxTom").value(hasItem(DEFAULT_INTEGER_MAX_TOM)))
            .andExpect(jsonPath("$.[*].longTom").value(hasItem(DEFAULT_LONG_TOM.intValue())))
            .andExpect(jsonPath("$.[*].longRequiredTom").value(hasItem(DEFAULT_LONG_REQUIRED_TOM.intValue())))
            .andExpect(jsonPath("$.[*].longMinTom").value(hasItem(DEFAULT_LONG_MIN_TOM.intValue())))
            .andExpect(jsonPath("$.[*].longMaxTom").value(hasItem(DEFAULT_LONG_MAX_TOM.intValue())))
            .andExpect(jsonPath("$.[*].floatTom").value(hasItem(DEFAULT_FLOAT_TOM.doubleValue())))
            .andExpect(jsonPath("$.[*].floatRequiredTom").value(hasItem(DEFAULT_FLOAT_REQUIRED_TOM.doubleValue())))
            .andExpect(jsonPath("$.[*].floatMinTom").value(hasItem(DEFAULT_FLOAT_MIN_TOM.doubleValue())))
            .andExpect(jsonPath("$.[*].floatMaxTom").value(hasItem(DEFAULT_FLOAT_MAX_TOM.doubleValue())))
            .andExpect(jsonPath("$.[*].doubleRequiredTom").value(hasItem(DEFAULT_DOUBLE_REQUIRED_TOM.doubleValue())))
            .andExpect(jsonPath("$.[*].doubleMinTom").value(hasItem(DEFAULT_DOUBLE_MIN_TOM.doubleValue())))
            .andExpect(jsonPath("$.[*].doubleMaxTom").value(hasItem(DEFAULT_DOUBLE_MAX_TOM.doubleValue())))
            .andExpect(jsonPath("$.[*].bigDecimalRequiredTom").value(hasItem(DEFAULT_BIG_DECIMAL_REQUIRED_TOM.intValue())))
            .andExpect(jsonPath("$.[*].bigDecimalMinTom").value(hasItem(DEFAULT_BIG_DECIMAL_MIN_TOM.intValue())))
            .andExpect(jsonPath("$.[*].bigDecimalMaxTom").value(hasItem(DEFAULT_BIG_DECIMAL_MAX_TOM.intValue())))
            .andExpect(jsonPath("$.[*].localDateTom").value(hasItem(DEFAULT_LOCAL_DATE_TOM.toString())))
            .andExpect(jsonPath("$.[*].localDateRequiredTom").value(hasItem(DEFAULT_LOCAL_DATE_REQUIRED_TOM.toString())))
            .andExpect(jsonPath("$.[*].instantTom").value(hasItem(DEFAULT_INSTANT_TOM.toString())))
            .andExpect(jsonPath("$.[*].instanteRequiredTom").value(hasItem(DEFAULT_INSTANTE_REQUIRED_TOM.toString())))
            .andExpect(jsonPath("$.[*].zonedDateTimeTom").value(hasItem(sameInstant(DEFAULT_ZONED_DATE_TIME_TOM))))
            .andExpect(jsonPath("$.[*].zonedDateTimeRequiredTom").value(hasItem(sameInstant(DEFAULT_ZONED_DATE_TIME_REQUIRED_TOM))))
            .andExpect(jsonPath("$.[*].booleanTom").value(hasItem(DEFAULT_BOOLEAN_TOM.booleanValue())))
            .andExpect(jsonPath("$.[*].booleanRequiredTom").value(hasItem(DEFAULT_BOOLEAN_REQUIRED_TOM.booleanValue())))
            .andExpect(jsonPath("$.[*].enumTom").value(hasItem(DEFAULT_ENUM_TOM.toString())))
            .andExpect(jsonPath("$.[*].enumRequiredTom").value(hasItem(DEFAULT_ENUM_REQUIRED_TOM.toString())))
            .andExpect(jsonPath("$.[*].byteImageTomContentType").value(hasItem(DEFAULT_BYTE_IMAGE_TOM_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].byteImageTom").value(hasItem(Base64Utils.encodeToString(DEFAULT_BYTE_IMAGE_TOM))))
            .andExpect(jsonPath("$.[*].byteImageRequiredTomContentType").value(hasItem(DEFAULT_BYTE_IMAGE_REQUIRED_TOM_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].byteImageRequiredTom").value(hasItem(Base64Utils.encodeToString(DEFAULT_BYTE_IMAGE_REQUIRED_TOM))))
            .andExpect(jsonPath("$.[*].byteImageMinbytesTomContentType").value(hasItem(DEFAULT_BYTE_IMAGE_MINBYTES_TOM_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].byteImageMinbytesTom").value(hasItem(Base64Utils.encodeToString(DEFAULT_BYTE_IMAGE_MINBYTES_TOM))))
            .andExpect(jsonPath("$.[*].byteImageMaxbytesTomContentType").value(hasItem(DEFAULT_BYTE_IMAGE_MAXBYTES_TOM_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].byteImageMaxbytesTom").value(hasItem(Base64Utils.encodeToString(DEFAULT_BYTE_IMAGE_MAXBYTES_TOM))))
            .andExpect(jsonPath("$.[*].byteAnyTomContentType").value(hasItem(DEFAULT_BYTE_ANY_TOM_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].byteAnyTom").value(hasItem(Base64Utils.encodeToString(DEFAULT_BYTE_ANY_TOM))))
            .andExpect(jsonPath("$.[*].byteAnyRequiredTomContentType").value(hasItem(DEFAULT_BYTE_ANY_REQUIRED_TOM_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].byteAnyRequiredTom").value(hasItem(Base64Utils.encodeToString(DEFAULT_BYTE_ANY_REQUIRED_TOM))))
            .andExpect(jsonPath("$.[*].byteAnyMinbytesTomContentType").value(hasItem(DEFAULT_BYTE_ANY_MINBYTES_TOM_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].byteAnyMinbytesTom").value(hasItem(Base64Utils.encodeToString(DEFAULT_BYTE_ANY_MINBYTES_TOM))))
            .andExpect(jsonPath("$.[*].byteAnyMaxbytesTomContentType").value(hasItem(DEFAULT_BYTE_ANY_MAXBYTES_TOM_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].byteAnyMaxbytesTom").value(hasItem(Base64Utils.encodeToString(DEFAULT_BYTE_ANY_MAXBYTES_TOM))))
            .andExpect(jsonPath("$.[*].byteTextTom").value(hasItem(DEFAULT_BYTE_TEXT_TOM.toString())))
            .andExpect(jsonPath("$.[*].byteTextRequiredTom").value(hasItem(DEFAULT_BYTE_TEXT_REQUIRED_TOM.toString())))
            .andExpect(jsonPath("$.[*].byteTextMinbytesTom").value(hasItem(DEFAULT_BYTE_TEXT_MINBYTES_TOM.toString())))
            .andExpect(jsonPath("$.[*].byteTextMaxbytesTom").value(hasItem(DEFAULT_BYTE_TEXT_MAXBYTES_TOM.toString())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(FieldTestEntity.class);
        FieldTestEntity fieldTestEntity1 = new FieldTestEntity();
        fieldTestEntity1.setId(1L);
        FieldTestEntity fieldTestEntity2 = new FieldTestEntity();
        fieldTestEntity2.setId(fieldTestEntity1.getId());
        assertThat(fieldTestEntity1).isEqualTo(fieldTestEntity2);
        fieldTestEntity2.setId(2L);
        assertThat(fieldTestEntity1).isNotEqualTo(fieldTestEntity2);
        fieldTestEntity1.setId(null);
        assertThat(fieldTestEntity1).isNotEqualTo(fieldTestEntity2);
    }
}
