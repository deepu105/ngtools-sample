package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.Jh4TestApp;

import com.mycompany.myapp.domain.FieldTestServiceClassEntity;
import com.mycompany.myapp.repository.FieldTestServiceClassEntityRepository;
import com.mycompany.myapp.service.FieldTestServiceClassEntityService;
import com.mycompany.myapp.repository.search.FieldTestServiceClassEntitySearchRepository;
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
 * Test class for the FieldTestServiceClassEntityResource REST controller.
 *
 * @see FieldTestServiceClassEntityResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Jh4TestApp.class)
public class FieldTestServiceClassEntityResourceIntTest {

    private static final String DEFAULT_STRING_BOB = "AAAAAAAAAA";
    private static final String UPDATED_STRING_BOB = "BBBBBBBBBB";

    private static final String DEFAULT_STRING_REQUIRED_BOB = "AAAAAAAAAA";
    private static final String UPDATED_STRING_REQUIRED_BOB = "BBBBBBBBBB";

    private static final String DEFAULT_STRING_MINLENGTH_BOB = "AAAAAAAAAA";
    private static final String UPDATED_STRING_MINLENGTH_BOB = "BBBBBBBBBB";

    private static final String DEFAULT_STRING_MAXLENGTH_BOB = "AAAAAAAAAA";
    private static final String UPDATED_STRING_MAXLENGTH_BOB = "BBBBBBBBBB";

    private static final String DEFAULT_STRING_PATTERN_BOB = "AAAAAAAAAA";
    private static final String UPDATED_STRING_PATTERN_BOB = "BBBBBBBBBB";

    private static final Integer DEFAULT_INTEGER_BOB = 1;
    private static final Integer UPDATED_INTEGER_BOB = 2;

    private static final Integer DEFAULT_INTEGER_REQUIRED_BOB = 1;
    private static final Integer UPDATED_INTEGER_REQUIRED_BOB = 2;

    private static final Integer DEFAULT_INTEGER_MIN_BOB = 0;
    private static final Integer UPDATED_INTEGER_MIN_BOB = 1;

    private static final Integer DEFAULT_INTEGER_MAX_BOB = 100;
    private static final Integer UPDATED_INTEGER_MAX_BOB = 99;

    private static final Long DEFAULT_LONG_BOB = 1L;
    private static final Long UPDATED_LONG_BOB = 2L;

    private static final Long DEFAULT_LONG_REQUIRED_BOB = 1L;
    private static final Long UPDATED_LONG_REQUIRED_BOB = 2L;

    private static final Long DEFAULT_LONG_MIN_BOB = 0L;
    private static final Long UPDATED_LONG_MIN_BOB = 1L;

    private static final Long DEFAULT_LONG_MAX_BOB = 100L;
    private static final Long UPDATED_LONG_MAX_BOB = 99L;

    private static final Float DEFAULT_FLOAT_BOB = 1F;
    private static final Float UPDATED_FLOAT_BOB = 2F;

    private static final Float DEFAULT_FLOAT_REQUIRED_BOB = 1F;
    private static final Float UPDATED_FLOAT_REQUIRED_BOB = 2F;

    private static final Float DEFAULT_FLOAT_MIN_BOB = 0F;
    private static final Float UPDATED_FLOAT_MIN_BOB = 1F;

    private static final Float DEFAULT_FLOAT_MAX_BOB = 100F;
    private static final Float UPDATED_FLOAT_MAX_BOB = 99F;

    private static final Double DEFAULT_DOUBLE_REQUIRED_BOB = 1D;
    private static final Double UPDATED_DOUBLE_REQUIRED_BOB = 2D;

    private static final Double DEFAULT_DOUBLE_MIN_BOB = 0D;
    private static final Double UPDATED_DOUBLE_MIN_BOB = 1D;

    private static final Double DEFAULT_DOUBLE_MAX_BOB = 100D;
    private static final Double UPDATED_DOUBLE_MAX_BOB = 99D;

    private static final BigDecimal DEFAULT_BIG_DECIMAL_REQUIRED_BOB = new BigDecimal(1);
    private static final BigDecimal UPDATED_BIG_DECIMAL_REQUIRED_BOB = new BigDecimal(2);

    private static final BigDecimal DEFAULT_BIG_DECIMAL_MIN_BOB = new BigDecimal(0);
    private static final BigDecimal UPDATED_BIG_DECIMAL_MIN_BOB = new BigDecimal(1);

    private static final BigDecimal DEFAULT_BIG_DECIMAL_MAX_BOB = new BigDecimal(100);
    private static final BigDecimal UPDATED_BIG_DECIMAL_MAX_BOB = new BigDecimal(99);

    private static final LocalDate DEFAULT_LOCAL_DATE_BOB = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_LOCAL_DATE_BOB = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_LOCAL_DATE_REQUIRED_BOB = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_LOCAL_DATE_REQUIRED_BOB = LocalDate.now(ZoneId.systemDefault());

    private static final Instant DEFAULT_INSTANT_BOB = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_INSTANT_BOB = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_INSTANTE_REQUIRED_BOB = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_INSTANTE_REQUIRED_BOB = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final ZonedDateTime DEFAULT_ZONED_DATE_TIME_BOB = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_ZONED_DATE_TIME_BOB = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_ZONED_DATE_TIME_REQUIRED_BOB = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_ZONED_DATE_TIME_REQUIRED_BOB = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final Boolean DEFAULT_BOOLEAN_BOB = false;
    private static final Boolean UPDATED_BOOLEAN_BOB = true;

    private static final Boolean DEFAULT_BOOLEAN_REQUIRED_BOB = false;
    private static final Boolean UPDATED_BOOLEAN_REQUIRED_BOB = true;

    private static final EnumFieldClass DEFAULT_ENUM_BOB = EnumFieldClass.ENUM_VALUE_1;
    private static final EnumFieldClass UPDATED_ENUM_BOB = EnumFieldClass.ENUM_VALUE_2;

    private static final EnumRequiredFieldClass DEFAULT_ENUM_REQUIRED_BOB = EnumRequiredFieldClass.ENUM_VALUE_1;
    private static final EnumRequiredFieldClass UPDATED_ENUM_REQUIRED_BOB = EnumRequiredFieldClass.ENUM_VALUE_2;

    private static final byte[] DEFAULT_BYTE_IMAGE_BOB = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_BYTE_IMAGE_BOB = TestUtil.createByteArray(2, "1");
    private static final String DEFAULT_BYTE_IMAGE_BOB_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_BYTE_IMAGE_BOB_CONTENT_TYPE = "image/png";

    private static final byte[] DEFAULT_BYTE_IMAGE_REQUIRED_BOB = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_BYTE_IMAGE_REQUIRED_BOB = TestUtil.createByteArray(2, "1");
    private static final String DEFAULT_BYTE_IMAGE_REQUIRED_BOB_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_BYTE_IMAGE_REQUIRED_BOB_CONTENT_TYPE = "image/png";

    private static final byte[] DEFAULT_BYTE_IMAGE_MINBYTES_BOB = TestUtil.createByteArray(0, "0");
    private static final byte[] UPDATED_BYTE_IMAGE_MINBYTES_BOB = TestUtil.createByteArray(0, "1");
    private static final String DEFAULT_BYTE_IMAGE_MINBYTES_BOB_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_BYTE_IMAGE_MINBYTES_BOB_CONTENT_TYPE = "image/png";

    private static final byte[] DEFAULT_BYTE_IMAGE_MAXBYTES_BOB = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_BYTE_IMAGE_MAXBYTES_BOB = TestUtil.createByteArray(10000, "1");
    private static final String DEFAULT_BYTE_IMAGE_MAXBYTES_BOB_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_BYTE_IMAGE_MAXBYTES_BOB_CONTENT_TYPE = "image/png";

    private static final byte[] DEFAULT_BYTE_ANY_BOB = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_BYTE_ANY_BOB = TestUtil.createByteArray(2, "1");
    private static final String DEFAULT_BYTE_ANY_BOB_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_BYTE_ANY_BOB_CONTENT_TYPE = "image/png";

    private static final byte[] DEFAULT_BYTE_ANY_REQUIRED_BOB = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_BYTE_ANY_REQUIRED_BOB = TestUtil.createByteArray(2, "1");
    private static final String DEFAULT_BYTE_ANY_REQUIRED_BOB_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_BYTE_ANY_REQUIRED_BOB_CONTENT_TYPE = "image/png";

    private static final byte[] DEFAULT_BYTE_ANY_MINBYTES_BOB = TestUtil.createByteArray(0, "0");
    private static final byte[] UPDATED_BYTE_ANY_MINBYTES_BOB = TestUtil.createByteArray(0, "1");
    private static final String DEFAULT_BYTE_ANY_MINBYTES_BOB_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_BYTE_ANY_MINBYTES_BOB_CONTENT_TYPE = "image/png";

    private static final byte[] DEFAULT_BYTE_ANY_MAXBYTES_BOB = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_BYTE_ANY_MAXBYTES_BOB = TestUtil.createByteArray(10000, "1");
    private static final String DEFAULT_BYTE_ANY_MAXBYTES_BOB_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_BYTE_ANY_MAXBYTES_BOB_CONTENT_TYPE = "image/png";

    private static final String DEFAULT_BYTE_TEXT_BOB = "AAAAAAAAAA";
    private static final String UPDATED_BYTE_TEXT_BOB = "BBBBBBBBBB";

    private static final String DEFAULT_BYTE_TEXT_REQUIRED_BOB = "AAAAAAAAAA";
    private static final String UPDATED_BYTE_TEXT_REQUIRED_BOB = "BBBBBBBBBB";

    private static final String DEFAULT_BYTE_TEXT_MINBYTES_BOB = "AAAAAAAAAA";
    private static final String UPDATED_BYTE_TEXT_MINBYTES_BOB = "BBBBBBBBBB";

    private static final String DEFAULT_BYTE_TEXT_MAXBYTES_BOB = "AAAAAAAAAA";
    private static final String UPDATED_BYTE_TEXT_MAXBYTES_BOB = "BBBBBBBBBB";

    @Autowired
    private FieldTestServiceClassEntityRepository fieldTestServiceClassEntityRepository;

    @Autowired
    private FieldTestServiceClassEntityService fieldTestServiceClassEntityService;

    @Autowired
    private FieldTestServiceClassEntitySearchRepository fieldTestServiceClassEntitySearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restFieldTestServiceClassEntityMockMvc;

    private FieldTestServiceClassEntity fieldTestServiceClassEntity;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        FieldTestServiceClassEntityResource fieldTestServiceClassEntityResource = new FieldTestServiceClassEntityResource(fieldTestServiceClassEntityService);
        this.restFieldTestServiceClassEntityMockMvc = MockMvcBuilders.standaloneSetup(fieldTestServiceClassEntityResource)
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
    public static FieldTestServiceClassEntity createEntity(EntityManager em) {
        FieldTestServiceClassEntity fieldTestServiceClassEntity = new FieldTestServiceClassEntity()
            .stringBob(DEFAULT_STRING_BOB)
            .stringRequiredBob(DEFAULT_STRING_REQUIRED_BOB)
            .stringMinlengthBob(DEFAULT_STRING_MINLENGTH_BOB)
            .stringMaxlengthBob(DEFAULT_STRING_MAXLENGTH_BOB)
            .stringPatternBob(DEFAULT_STRING_PATTERN_BOB)
            .integerBob(DEFAULT_INTEGER_BOB)
            .integerRequiredBob(DEFAULT_INTEGER_REQUIRED_BOB)
            .integerMinBob(DEFAULT_INTEGER_MIN_BOB)
            .integerMaxBob(DEFAULT_INTEGER_MAX_BOB)
            .longBob(DEFAULT_LONG_BOB)
            .longRequiredBob(DEFAULT_LONG_REQUIRED_BOB)
            .longMinBob(DEFAULT_LONG_MIN_BOB)
            .longMaxBob(DEFAULT_LONG_MAX_BOB)
            .floatBob(DEFAULT_FLOAT_BOB)
            .floatRequiredBob(DEFAULT_FLOAT_REQUIRED_BOB)
            .floatMinBob(DEFAULT_FLOAT_MIN_BOB)
            .floatMaxBob(DEFAULT_FLOAT_MAX_BOB)
            .doubleRequiredBob(DEFAULT_DOUBLE_REQUIRED_BOB)
            .doubleMinBob(DEFAULT_DOUBLE_MIN_BOB)
            .doubleMaxBob(DEFAULT_DOUBLE_MAX_BOB)
            .bigDecimalRequiredBob(DEFAULT_BIG_DECIMAL_REQUIRED_BOB)
            .bigDecimalMinBob(DEFAULT_BIG_DECIMAL_MIN_BOB)
            .bigDecimalMaxBob(DEFAULT_BIG_DECIMAL_MAX_BOB)
            .localDateBob(DEFAULT_LOCAL_DATE_BOB)
            .localDateRequiredBob(DEFAULT_LOCAL_DATE_REQUIRED_BOB)
            .instantBob(DEFAULT_INSTANT_BOB)
            .instanteRequiredBob(DEFAULT_INSTANTE_REQUIRED_BOB)
            .zonedDateTimeBob(DEFAULT_ZONED_DATE_TIME_BOB)
            .zonedDateTimeRequiredBob(DEFAULT_ZONED_DATE_TIME_REQUIRED_BOB)
            .booleanBob(DEFAULT_BOOLEAN_BOB)
            .booleanRequiredBob(DEFAULT_BOOLEAN_REQUIRED_BOB)
            .enumBob(DEFAULT_ENUM_BOB)
            .enumRequiredBob(DEFAULT_ENUM_REQUIRED_BOB)
            .byteImageBob(DEFAULT_BYTE_IMAGE_BOB)
            .byteImageBobContentType(DEFAULT_BYTE_IMAGE_BOB_CONTENT_TYPE)
            .byteImageRequiredBob(DEFAULT_BYTE_IMAGE_REQUIRED_BOB)
            .byteImageRequiredBobContentType(DEFAULT_BYTE_IMAGE_REQUIRED_BOB_CONTENT_TYPE)
            .byteImageMinbytesBob(DEFAULT_BYTE_IMAGE_MINBYTES_BOB)
            .byteImageMinbytesBobContentType(DEFAULT_BYTE_IMAGE_MINBYTES_BOB_CONTENT_TYPE)
            .byteImageMaxbytesBob(DEFAULT_BYTE_IMAGE_MAXBYTES_BOB)
            .byteImageMaxbytesBobContentType(DEFAULT_BYTE_IMAGE_MAXBYTES_BOB_CONTENT_TYPE)
            .byteAnyBob(DEFAULT_BYTE_ANY_BOB)
            .byteAnyBobContentType(DEFAULT_BYTE_ANY_BOB_CONTENT_TYPE)
            .byteAnyRequiredBob(DEFAULT_BYTE_ANY_REQUIRED_BOB)
            .byteAnyRequiredBobContentType(DEFAULT_BYTE_ANY_REQUIRED_BOB_CONTENT_TYPE)
            .byteAnyMinbytesBob(DEFAULT_BYTE_ANY_MINBYTES_BOB)
            .byteAnyMinbytesBobContentType(DEFAULT_BYTE_ANY_MINBYTES_BOB_CONTENT_TYPE)
            .byteAnyMaxbytesBob(DEFAULT_BYTE_ANY_MAXBYTES_BOB)
            .byteAnyMaxbytesBobContentType(DEFAULT_BYTE_ANY_MAXBYTES_BOB_CONTENT_TYPE)
            .byteTextBob(DEFAULT_BYTE_TEXT_BOB)
            .byteTextRequiredBob(DEFAULT_BYTE_TEXT_REQUIRED_BOB)
            .byteTextMinbytesBob(DEFAULT_BYTE_TEXT_MINBYTES_BOB)
            .byteTextMaxbytesBob(DEFAULT_BYTE_TEXT_MAXBYTES_BOB);
        return fieldTestServiceClassEntity;
    }

    @Before
    public void initTest() {
        fieldTestServiceClassEntitySearchRepository.deleteAll();
        fieldTestServiceClassEntity = createEntity(em);
    }

    @Test
    @Transactional
    public void createFieldTestServiceClassEntity() throws Exception {
        int databaseSizeBeforeCreate = fieldTestServiceClassEntityRepository.findAll().size();

        // Create the FieldTestServiceClassEntity
        restFieldTestServiceClassEntityMockMvc.perform(post("/api/field-test-service-class-entities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fieldTestServiceClassEntity)))
            .andExpect(status().isCreated());

        // Validate the FieldTestServiceClassEntity in the database
        List<FieldTestServiceClassEntity> fieldTestServiceClassEntityList = fieldTestServiceClassEntityRepository.findAll();
        assertThat(fieldTestServiceClassEntityList).hasSize(databaseSizeBeforeCreate + 1);
        FieldTestServiceClassEntity testFieldTestServiceClassEntity = fieldTestServiceClassEntityList.get(fieldTestServiceClassEntityList.size() - 1);
        assertThat(testFieldTestServiceClassEntity.getStringBob()).isEqualTo(DEFAULT_STRING_BOB);
        assertThat(testFieldTestServiceClassEntity.getStringRequiredBob()).isEqualTo(DEFAULT_STRING_REQUIRED_BOB);
        assertThat(testFieldTestServiceClassEntity.getStringMinlengthBob()).isEqualTo(DEFAULT_STRING_MINLENGTH_BOB);
        assertThat(testFieldTestServiceClassEntity.getStringMaxlengthBob()).isEqualTo(DEFAULT_STRING_MAXLENGTH_BOB);
        assertThat(testFieldTestServiceClassEntity.getStringPatternBob()).isEqualTo(DEFAULT_STRING_PATTERN_BOB);
        assertThat(testFieldTestServiceClassEntity.getIntegerBob()).isEqualTo(DEFAULT_INTEGER_BOB);
        assertThat(testFieldTestServiceClassEntity.getIntegerRequiredBob()).isEqualTo(DEFAULT_INTEGER_REQUIRED_BOB);
        assertThat(testFieldTestServiceClassEntity.getIntegerMinBob()).isEqualTo(DEFAULT_INTEGER_MIN_BOB);
        assertThat(testFieldTestServiceClassEntity.getIntegerMaxBob()).isEqualTo(DEFAULT_INTEGER_MAX_BOB);
        assertThat(testFieldTestServiceClassEntity.getLongBob()).isEqualTo(DEFAULT_LONG_BOB);
        assertThat(testFieldTestServiceClassEntity.getLongRequiredBob()).isEqualTo(DEFAULT_LONG_REQUIRED_BOB);
        assertThat(testFieldTestServiceClassEntity.getLongMinBob()).isEqualTo(DEFAULT_LONG_MIN_BOB);
        assertThat(testFieldTestServiceClassEntity.getLongMaxBob()).isEqualTo(DEFAULT_LONG_MAX_BOB);
        assertThat(testFieldTestServiceClassEntity.getFloatBob()).isEqualTo(DEFAULT_FLOAT_BOB);
        assertThat(testFieldTestServiceClassEntity.getFloatRequiredBob()).isEqualTo(DEFAULT_FLOAT_REQUIRED_BOB);
        assertThat(testFieldTestServiceClassEntity.getFloatMinBob()).isEqualTo(DEFAULT_FLOAT_MIN_BOB);
        assertThat(testFieldTestServiceClassEntity.getFloatMaxBob()).isEqualTo(DEFAULT_FLOAT_MAX_BOB);
        assertThat(testFieldTestServiceClassEntity.getDoubleRequiredBob()).isEqualTo(DEFAULT_DOUBLE_REQUIRED_BOB);
        assertThat(testFieldTestServiceClassEntity.getDoubleMinBob()).isEqualTo(DEFAULT_DOUBLE_MIN_BOB);
        assertThat(testFieldTestServiceClassEntity.getDoubleMaxBob()).isEqualTo(DEFAULT_DOUBLE_MAX_BOB);
        assertThat(testFieldTestServiceClassEntity.getBigDecimalRequiredBob()).isEqualTo(DEFAULT_BIG_DECIMAL_REQUIRED_BOB);
        assertThat(testFieldTestServiceClassEntity.getBigDecimalMinBob()).isEqualTo(DEFAULT_BIG_DECIMAL_MIN_BOB);
        assertThat(testFieldTestServiceClassEntity.getBigDecimalMaxBob()).isEqualTo(DEFAULT_BIG_DECIMAL_MAX_BOB);
        assertThat(testFieldTestServiceClassEntity.getLocalDateBob()).isEqualTo(DEFAULT_LOCAL_DATE_BOB);
        assertThat(testFieldTestServiceClassEntity.getLocalDateRequiredBob()).isEqualTo(DEFAULT_LOCAL_DATE_REQUIRED_BOB);
        assertThat(testFieldTestServiceClassEntity.getInstantBob()).isEqualTo(DEFAULT_INSTANT_BOB);
        assertThat(testFieldTestServiceClassEntity.getInstanteRequiredBob()).isEqualTo(DEFAULT_INSTANTE_REQUIRED_BOB);
        assertThat(testFieldTestServiceClassEntity.getZonedDateTimeBob()).isEqualTo(DEFAULT_ZONED_DATE_TIME_BOB);
        assertThat(testFieldTestServiceClassEntity.getZonedDateTimeRequiredBob()).isEqualTo(DEFAULT_ZONED_DATE_TIME_REQUIRED_BOB);
        assertThat(testFieldTestServiceClassEntity.isBooleanBob()).isEqualTo(DEFAULT_BOOLEAN_BOB);
        assertThat(testFieldTestServiceClassEntity.isBooleanRequiredBob()).isEqualTo(DEFAULT_BOOLEAN_REQUIRED_BOB);
        assertThat(testFieldTestServiceClassEntity.getEnumBob()).isEqualTo(DEFAULT_ENUM_BOB);
        assertThat(testFieldTestServiceClassEntity.getEnumRequiredBob()).isEqualTo(DEFAULT_ENUM_REQUIRED_BOB);
        assertThat(testFieldTestServiceClassEntity.getByteImageBob()).isEqualTo(DEFAULT_BYTE_IMAGE_BOB);
        assertThat(testFieldTestServiceClassEntity.getByteImageBobContentType()).isEqualTo(DEFAULT_BYTE_IMAGE_BOB_CONTENT_TYPE);
        assertThat(testFieldTestServiceClassEntity.getByteImageRequiredBob()).isEqualTo(DEFAULT_BYTE_IMAGE_REQUIRED_BOB);
        assertThat(testFieldTestServiceClassEntity.getByteImageRequiredBobContentType()).isEqualTo(DEFAULT_BYTE_IMAGE_REQUIRED_BOB_CONTENT_TYPE);
        assertThat(testFieldTestServiceClassEntity.getByteImageMinbytesBob()).isEqualTo(DEFAULT_BYTE_IMAGE_MINBYTES_BOB);
        assertThat(testFieldTestServiceClassEntity.getByteImageMinbytesBobContentType()).isEqualTo(DEFAULT_BYTE_IMAGE_MINBYTES_BOB_CONTENT_TYPE);
        assertThat(testFieldTestServiceClassEntity.getByteImageMaxbytesBob()).isEqualTo(DEFAULT_BYTE_IMAGE_MAXBYTES_BOB);
        assertThat(testFieldTestServiceClassEntity.getByteImageMaxbytesBobContentType()).isEqualTo(DEFAULT_BYTE_IMAGE_MAXBYTES_BOB_CONTENT_TYPE);
        assertThat(testFieldTestServiceClassEntity.getByteAnyBob()).isEqualTo(DEFAULT_BYTE_ANY_BOB);
        assertThat(testFieldTestServiceClassEntity.getByteAnyBobContentType()).isEqualTo(DEFAULT_BYTE_ANY_BOB_CONTENT_TYPE);
        assertThat(testFieldTestServiceClassEntity.getByteAnyRequiredBob()).isEqualTo(DEFAULT_BYTE_ANY_REQUIRED_BOB);
        assertThat(testFieldTestServiceClassEntity.getByteAnyRequiredBobContentType()).isEqualTo(DEFAULT_BYTE_ANY_REQUIRED_BOB_CONTENT_TYPE);
        assertThat(testFieldTestServiceClassEntity.getByteAnyMinbytesBob()).isEqualTo(DEFAULT_BYTE_ANY_MINBYTES_BOB);
        assertThat(testFieldTestServiceClassEntity.getByteAnyMinbytesBobContentType()).isEqualTo(DEFAULT_BYTE_ANY_MINBYTES_BOB_CONTENT_TYPE);
        assertThat(testFieldTestServiceClassEntity.getByteAnyMaxbytesBob()).isEqualTo(DEFAULT_BYTE_ANY_MAXBYTES_BOB);
        assertThat(testFieldTestServiceClassEntity.getByteAnyMaxbytesBobContentType()).isEqualTo(DEFAULT_BYTE_ANY_MAXBYTES_BOB_CONTENT_TYPE);
        assertThat(testFieldTestServiceClassEntity.getByteTextBob()).isEqualTo(DEFAULT_BYTE_TEXT_BOB);
        assertThat(testFieldTestServiceClassEntity.getByteTextRequiredBob()).isEqualTo(DEFAULT_BYTE_TEXT_REQUIRED_BOB);
        assertThat(testFieldTestServiceClassEntity.getByteTextMinbytesBob()).isEqualTo(DEFAULT_BYTE_TEXT_MINBYTES_BOB);
        assertThat(testFieldTestServiceClassEntity.getByteTextMaxbytesBob()).isEqualTo(DEFAULT_BYTE_TEXT_MAXBYTES_BOB);

        // Validate the FieldTestServiceClassEntity in Elasticsearch
        FieldTestServiceClassEntity fieldTestServiceClassEntityEs = fieldTestServiceClassEntitySearchRepository.findOne(testFieldTestServiceClassEntity.getId());
        assertThat(fieldTestServiceClassEntityEs).isEqualToComparingFieldByField(testFieldTestServiceClassEntity);
    }

    @Test
    @Transactional
    public void createFieldTestServiceClassEntityWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = fieldTestServiceClassEntityRepository.findAll().size();

        // Create the FieldTestServiceClassEntity with an existing ID
        fieldTestServiceClassEntity.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFieldTestServiceClassEntityMockMvc.perform(post("/api/field-test-service-class-entities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fieldTestServiceClassEntity)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<FieldTestServiceClassEntity> fieldTestServiceClassEntityList = fieldTestServiceClassEntityRepository.findAll();
        assertThat(fieldTestServiceClassEntityList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkStringRequiredBobIsRequired() throws Exception {
        int databaseSizeBeforeTest = fieldTestServiceClassEntityRepository.findAll().size();
        // set the field null
        fieldTestServiceClassEntity.setStringRequiredBob(null);

        // Create the FieldTestServiceClassEntity, which fails.

        restFieldTestServiceClassEntityMockMvc.perform(post("/api/field-test-service-class-entities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fieldTestServiceClassEntity)))
            .andExpect(status().isBadRequest());

        List<FieldTestServiceClassEntity> fieldTestServiceClassEntityList = fieldTestServiceClassEntityRepository.findAll();
        assertThat(fieldTestServiceClassEntityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIntegerRequiredBobIsRequired() throws Exception {
        int databaseSizeBeforeTest = fieldTestServiceClassEntityRepository.findAll().size();
        // set the field null
        fieldTestServiceClassEntity.setIntegerRequiredBob(null);

        // Create the FieldTestServiceClassEntity, which fails.

        restFieldTestServiceClassEntityMockMvc.perform(post("/api/field-test-service-class-entities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fieldTestServiceClassEntity)))
            .andExpect(status().isBadRequest());

        List<FieldTestServiceClassEntity> fieldTestServiceClassEntityList = fieldTestServiceClassEntityRepository.findAll();
        assertThat(fieldTestServiceClassEntityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLongRequiredBobIsRequired() throws Exception {
        int databaseSizeBeforeTest = fieldTestServiceClassEntityRepository.findAll().size();
        // set the field null
        fieldTestServiceClassEntity.setLongRequiredBob(null);

        // Create the FieldTestServiceClassEntity, which fails.

        restFieldTestServiceClassEntityMockMvc.perform(post("/api/field-test-service-class-entities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fieldTestServiceClassEntity)))
            .andExpect(status().isBadRequest());

        List<FieldTestServiceClassEntity> fieldTestServiceClassEntityList = fieldTestServiceClassEntityRepository.findAll();
        assertThat(fieldTestServiceClassEntityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFloatRequiredBobIsRequired() throws Exception {
        int databaseSizeBeforeTest = fieldTestServiceClassEntityRepository.findAll().size();
        // set the field null
        fieldTestServiceClassEntity.setFloatRequiredBob(null);

        // Create the FieldTestServiceClassEntity, which fails.

        restFieldTestServiceClassEntityMockMvc.perform(post("/api/field-test-service-class-entities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fieldTestServiceClassEntity)))
            .andExpect(status().isBadRequest());

        List<FieldTestServiceClassEntity> fieldTestServiceClassEntityList = fieldTestServiceClassEntityRepository.findAll();
        assertThat(fieldTestServiceClassEntityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDoubleRequiredBobIsRequired() throws Exception {
        int databaseSizeBeforeTest = fieldTestServiceClassEntityRepository.findAll().size();
        // set the field null
        fieldTestServiceClassEntity.setDoubleRequiredBob(null);

        // Create the FieldTestServiceClassEntity, which fails.

        restFieldTestServiceClassEntityMockMvc.perform(post("/api/field-test-service-class-entities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fieldTestServiceClassEntity)))
            .andExpect(status().isBadRequest());

        List<FieldTestServiceClassEntity> fieldTestServiceClassEntityList = fieldTestServiceClassEntityRepository.findAll();
        assertThat(fieldTestServiceClassEntityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkBigDecimalRequiredBobIsRequired() throws Exception {
        int databaseSizeBeforeTest = fieldTestServiceClassEntityRepository.findAll().size();
        // set the field null
        fieldTestServiceClassEntity.setBigDecimalRequiredBob(null);

        // Create the FieldTestServiceClassEntity, which fails.

        restFieldTestServiceClassEntityMockMvc.perform(post("/api/field-test-service-class-entities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fieldTestServiceClassEntity)))
            .andExpect(status().isBadRequest());

        List<FieldTestServiceClassEntity> fieldTestServiceClassEntityList = fieldTestServiceClassEntityRepository.findAll();
        assertThat(fieldTestServiceClassEntityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLocalDateRequiredBobIsRequired() throws Exception {
        int databaseSizeBeforeTest = fieldTestServiceClassEntityRepository.findAll().size();
        // set the field null
        fieldTestServiceClassEntity.setLocalDateRequiredBob(null);

        // Create the FieldTestServiceClassEntity, which fails.

        restFieldTestServiceClassEntityMockMvc.perform(post("/api/field-test-service-class-entities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fieldTestServiceClassEntity)))
            .andExpect(status().isBadRequest());

        List<FieldTestServiceClassEntity> fieldTestServiceClassEntityList = fieldTestServiceClassEntityRepository.findAll();
        assertThat(fieldTestServiceClassEntityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkInstanteRequiredBobIsRequired() throws Exception {
        int databaseSizeBeforeTest = fieldTestServiceClassEntityRepository.findAll().size();
        // set the field null
        fieldTestServiceClassEntity.setInstanteRequiredBob(null);

        // Create the FieldTestServiceClassEntity, which fails.

        restFieldTestServiceClassEntityMockMvc.perform(post("/api/field-test-service-class-entities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fieldTestServiceClassEntity)))
            .andExpect(status().isBadRequest());

        List<FieldTestServiceClassEntity> fieldTestServiceClassEntityList = fieldTestServiceClassEntityRepository.findAll();
        assertThat(fieldTestServiceClassEntityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkZonedDateTimeRequiredBobIsRequired() throws Exception {
        int databaseSizeBeforeTest = fieldTestServiceClassEntityRepository.findAll().size();
        // set the field null
        fieldTestServiceClassEntity.setZonedDateTimeRequiredBob(null);

        // Create the FieldTestServiceClassEntity, which fails.

        restFieldTestServiceClassEntityMockMvc.perform(post("/api/field-test-service-class-entities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fieldTestServiceClassEntity)))
            .andExpect(status().isBadRequest());

        List<FieldTestServiceClassEntity> fieldTestServiceClassEntityList = fieldTestServiceClassEntityRepository.findAll();
        assertThat(fieldTestServiceClassEntityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkBooleanRequiredBobIsRequired() throws Exception {
        int databaseSizeBeforeTest = fieldTestServiceClassEntityRepository.findAll().size();
        // set the field null
        fieldTestServiceClassEntity.setBooleanRequiredBob(null);

        // Create the FieldTestServiceClassEntity, which fails.

        restFieldTestServiceClassEntityMockMvc.perform(post("/api/field-test-service-class-entities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fieldTestServiceClassEntity)))
            .andExpect(status().isBadRequest());

        List<FieldTestServiceClassEntity> fieldTestServiceClassEntityList = fieldTestServiceClassEntityRepository.findAll();
        assertThat(fieldTestServiceClassEntityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEnumRequiredBobIsRequired() throws Exception {
        int databaseSizeBeforeTest = fieldTestServiceClassEntityRepository.findAll().size();
        // set the field null
        fieldTestServiceClassEntity.setEnumRequiredBob(null);

        // Create the FieldTestServiceClassEntity, which fails.

        restFieldTestServiceClassEntityMockMvc.perform(post("/api/field-test-service-class-entities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fieldTestServiceClassEntity)))
            .andExpect(status().isBadRequest());

        List<FieldTestServiceClassEntity> fieldTestServiceClassEntityList = fieldTestServiceClassEntityRepository.findAll();
        assertThat(fieldTestServiceClassEntityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkByteImageRequiredBobIsRequired() throws Exception {
        int databaseSizeBeforeTest = fieldTestServiceClassEntityRepository.findAll().size();
        // set the field null
        fieldTestServiceClassEntity.setByteImageRequiredBob(null);

        // Create the FieldTestServiceClassEntity, which fails.

        restFieldTestServiceClassEntityMockMvc.perform(post("/api/field-test-service-class-entities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fieldTestServiceClassEntity)))
            .andExpect(status().isBadRequest());

        List<FieldTestServiceClassEntity> fieldTestServiceClassEntityList = fieldTestServiceClassEntityRepository.findAll();
        assertThat(fieldTestServiceClassEntityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkByteAnyRequiredBobIsRequired() throws Exception {
        int databaseSizeBeforeTest = fieldTestServiceClassEntityRepository.findAll().size();
        // set the field null
        fieldTestServiceClassEntity.setByteAnyRequiredBob(null);

        // Create the FieldTestServiceClassEntity, which fails.

        restFieldTestServiceClassEntityMockMvc.perform(post("/api/field-test-service-class-entities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fieldTestServiceClassEntity)))
            .andExpect(status().isBadRequest());

        List<FieldTestServiceClassEntity> fieldTestServiceClassEntityList = fieldTestServiceClassEntityRepository.findAll();
        assertThat(fieldTestServiceClassEntityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkByteTextRequiredBobIsRequired() throws Exception {
        int databaseSizeBeforeTest = fieldTestServiceClassEntityRepository.findAll().size();
        // set the field null
        fieldTestServiceClassEntity.setByteTextRequiredBob(null);

        // Create the FieldTestServiceClassEntity, which fails.

        restFieldTestServiceClassEntityMockMvc.perform(post("/api/field-test-service-class-entities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fieldTestServiceClassEntity)))
            .andExpect(status().isBadRequest());

        List<FieldTestServiceClassEntity> fieldTestServiceClassEntityList = fieldTestServiceClassEntityRepository.findAll();
        assertThat(fieldTestServiceClassEntityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllFieldTestServiceClassEntities() throws Exception {
        // Initialize the database
        fieldTestServiceClassEntityRepository.saveAndFlush(fieldTestServiceClassEntity);

        // Get all the fieldTestServiceClassEntityList
        restFieldTestServiceClassEntityMockMvc.perform(get("/api/field-test-service-class-entities?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(fieldTestServiceClassEntity.getId().intValue())))
            .andExpect(jsonPath("$.[*].stringBob").value(hasItem(DEFAULT_STRING_BOB.toString())))
            .andExpect(jsonPath("$.[*].stringRequiredBob").value(hasItem(DEFAULT_STRING_REQUIRED_BOB.toString())))
            .andExpect(jsonPath("$.[*].stringMinlengthBob").value(hasItem(DEFAULT_STRING_MINLENGTH_BOB.toString())))
            .andExpect(jsonPath("$.[*].stringMaxlengthBob").value(hasItem(DEFAULT_STRING_MAXLENGTH_BOB.toString())))
            .andExpect(jsonPath("$.[*].stringPatternBob").value(hasItem(DEFAULT_STRING_PATTERN_BOB.toString())))
            .andExpect(jsonPath("$.[*].integerBob").value(hasItem(DEFAULT_INTEGER_BOB)))
            .andExpect(jsonPath("$.[*].integerRequiredBob").value(hasItem(DEFAULT_INTEGER_REQUIRED_BOB)))
            .andExpect(jsonPath("$.[*].integerMinBob").value(hasItem(DEFAULT_INTEGER_MIN_BOB)))
            .andExpect(jsonPath("$.[*].integerMaxBob").value(hasItem(DEFAULT_INTEGER_MAX_BOB)))
            .andExpect(jsonPath("$.[*].longBob").value(hasItem(DEFAULT_LONG_BOB.intValue())))
            .andExpect(jsonPath("$.[*].longRequiredBob").value(hasItem(DEFAULT_LONG_REQUIRED_BOB.intValue())))
            .andExpect(jsonPath("$.[*].longMinBob").value(hasItem(DEFAULT_LONG_MIN_BOB.intValue())))
            .andExpect(jsonPath("$.[*].longMaxBob").value(hasItem(DEFAULT_LONG_MAX_BOB.intValue())))
            .andExpect(jsonPath("$.[*].floatBob").value(hasItem(DEFAULT_FLOAT_BOB.doubleValue())))
            .andExpect(jsonPath("$.[*].floatRequiredBob").value(hasItem(DEFAULT_FLOAT_REQUIRED_BOB.doubleValue())))
            .andExpect(jsonPath("$.[*].floatMinBob").value(hasItem(DEFAULT_FLOAT_MIN_BOB.doubleValue())))
            .andExpect(jsonPath("$.[*].floatMaxBob").value(hasItem(DEFAULT_FLOAT_MAX_BOB.doubleValue())))
            .andExpect(jsonPath("$.[*].doubleRequiredBob").value(hasItem(DEFAULT_DOUBLE_REQUIRED_BOB.doubleValue())))
            .andExpect(jsonPath("$.[*].doubleMinBob").value(hasItem(DEFAULT_DOUBLE_MIN_BOB.doubleValue())))
            .andExpect(jsonPath("$.[*].doubleMaxBob").value(hasItem(DEFAULT_DOUBLE_MAX_BOB.doubleValue())))
            .andExpect(jsonPath("$.[*].bigDecimalRequiredBob").value(hasItem(DEFAULT_BIG_DECIMAL_REQUIRED_BOB.intValue())))
            .andExpect(jsonPath("$.[*].bigDecimalMinBob").value(hasItem(DEFAULT_BIG_DECIMAL_MIN_BOB.intValue())))
            .andExpect(jsonPath("$.[*].bigDecimalMaxBob").value(hasItem(DEFAULT_BIG_DECIMAL_MAX_BOB.intValue())))
            .andExpect(jsonPath("$.[*].localDateBob").value(hasItem(DEFAULT_LOCAL_DATE_BOB.toString())))
            .andExpect(jsonPath("$.[*].localDateRequiredBob").value(hasItem(DEFAULT_LOCAL_DATE_REQUIRED_BOB.toString())))
            .andExpect(jsonPath("$.[*].instantBob").value(hasItem(DEFAULT_INSTANT_BOB.toString())))
            .andExpect(jsonPath("$.[*].instanteRequiredBob").value(hasItem(DEFAULT_INSTANTE_REQUIRED_BOB.toString())))
            .andExpect(jsonPath("$.[*].zonedDateTimeBob").value(hasItem(sameInstant(DEFAULT_ZONED_DATE_TIME_BOB))))
            .andExpect(jsonPath("$.[*].zonedDateTimeRequiredBob").value(hasItem(sameInstant(DEFAULT_ZONED_DATE_TIME_REQUIRED_BOB))))
            .andExpect(jsonPath("$.[*].booleanBob").value(hasItem(DEFAULT_BOOLEAN_BOB.booleanValue())))
            .andExpect(jsonPath("$.[*].booleanRequiredBob").value(hasItem(DEFAULT_BOOLEAN_REQUIRED_BOB.booleanValue())))
            .andExpect(jsonPath("$.[*].enumBob").value(hasItem(DEFAULT_ENUM_BOB.toString())))
            .andExpect(jsonPath("$.[*].enumRequiredBob").value(hasItem(DEFAULT_ENUM_REQUIRED_BOB.toString())))
            .andExpect(jsonPath("$.[*].byteImageBobContentType").value(hasItem(DEFAULT_BYTE_IMAGE_BOB_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].byteImageBob").value(hasItem(Base64Utils.encodeToString(DEFAULT_BYTE_IMAGE_BOB))))
            .andExpect(jsonPath("$.[*].byteImageRequiredBobContentType").value(hasItem(DEFAULT_BYTE_IMAGE_REQUIRED_BOB_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].byteImageRequiredBob").value(hasItem(Base64Utils.encodeToString(DEFAULT_BYTE_IMAGE_REQUIRED_BOB))))
            .andExpect(jsonPath("$.[*].byteImageMinbytesBobContentType").value(hasItem(DEFAULT_BYTE_IMAGE_MINBYTES_BOB_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].byteImageMinbytesBob").value(hasItem(Base64Utils.encodeToString(DEFAULT_BYTE_IMAGE_MINBYTES_BOB))))
            .andExpect(jsonPath("$.[*].byteImageMaxbytesBobContentType").value(hasItem(DEFAULT_BYTE_IMAGE_MAXBYTES_BOB_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].byteImageMaxbytesBob").value(hasItem(Base64Utils.encodeToString(DEFAULT_BYTE_IMAGE_MAXBYTES_BOB))))
            .andExpect(jsonPath("$.[*].byteAnyBobContentType").value(hasItem(DEFAULT_BYTE_ANY_BOB_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].byteAnyBob").value(hasItem(Base64Utils.encodeToString(DEFAULT_BYTE_ANY_BOB))))
            .andExpect(jsonPath("$.[*].byteAnyRequiredBobContentType").value(hasItem(DEFAULT_BYTE_ANY_REQUIRED_BOB_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].byteAnyRequiredBob").value(hasItem(Base64Utils.encodeToString(DEFAULT_BYTE_ANY_REQUIRED_BOB))))
            .andExpect(jsonPath("$.[*].byteAnyMinbytesBobContentType").value(hasItem(DEFAULT_BYTE_ANY_MINBYTES_BOB_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].byteAnyMinbytesBob").value(hasItem(Base64Utils.encodeToString(DEFAULT_BYTE_ANY_MINBYTES_BOB))))
            .andExpect(jsonPath("$.[*].byteAnyMaxbytesBobContentType").value(hasItem(DEFAULT_BYTE_ANY_MAXBYTES_BOB_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].byteAnyMaxbytesBob").value(hasItem(Base64Utils.encodeToString(DEFAULT_BYTE_ANY_MAXBYTES_BOB))))
            .andExpect(jsonPath("$.[*].byteTextBob").value(hasItem(DEFAULT_BYTE_TEXT_BOB.toString())))
            .andExpect(jsonPath("$.[*].byteTextRequiredBob").value(hasItem(DEFAULT_BYTE_TEXT_REQUIRED_BOB.toString())))
            .andExpect(jsonPath("$.[*].byteTextMinbytesBob").value(hasItem(DEFAULT_BYTE_TEXT_MINBYTES_BOB.toString())))
            .andExpect(jsonPath("$.[*].byteTextMaxbytesBob").value(hasItem(DEFAULT_BYTE_TEXT_MAXBYTES_BOB.toString())));
    }

    @Test
    @Transactional
    public void getFieldTestServiceClassEntity() throws Exception {
        // Initialize the database
        fieldTestServiceClassEntityRepository.saveAndFlush(fieldTestServiceClassEntity);

        // Get the fieldTestServiceClassEntity
        restFieldTestServiceClassEntityMockMvc.perform(get("/api/field-test-service-class-entities/{id}", fieldTestServiceClassEntity.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(fieldTestServiceClassEntity.getId().intValue()))
            .andExpect(jsonPath("$.stringBob").value(DEFAULT_STRING_BOB.toString()))
            .andExpect(jsonPath("$.stringRequiredBob").value(DEFAULT_STRING_REQUIRED_BOB.toString()))
            .andExpect(jsonPath("$.stringMinlengthBob").value(DEFAULT_STRING_MINLENGTH_BOB.toString()))
            .andExpect(jsonPath("$.stringMaxlengthBob").value(DEFAULT_STRING_MAXLENGTH_BOB.toString()))
            .andExpect(jsonPath("$.stringPatternBob").value(DEFAULT_STRING_PATTERN_BOB.toString()))
            .andExpect(jsonPath("$.integerBob").value(DEFAULT_INTEGER_BOB))
            .andExpect(jsonPath("$.integerRequiredBob").value(DEFAULT_INTEGER_REQUIRED_BOB))
            .andExpect(jsonPath("$.integerMinBob").value(DEFAULT_INTEGER_MIN_BOB))
            .andExpect(jsonPath("$.integerMaxBob").value(DEFAULT_INTEGER_MAX_BOB))
            .andExpect(jsonPath("$.longBob").value(DEFAULT_LONG_BOB.intValue()))
            .andExpect(jsonPath("$.longRequiredBob").value(DEFAULT_LONG_REQUIRED_BOB.intValue()))
            .andExpect(jsonPath("$.longMinBob").value(DEFAULT_LONG_MIN_BOB.intValue()))
            .andExpect(jsonPath("$.longMaxBob").value(DEFAULT_LONG_MAX_BOB.intValue()))
            .andExpect(jsonPath("$.floatBob").value(DEFAULT_FLOAT_BOB.doubleValue()))
            .andExpect(jsonPath("$.floatRequiredBob").value(DEFAULT_FLOAT_REQUIRED_BOB.doubleValue()))
            .andExpect(jsonPath("$.floatMinBob").value(DEFAULT_FLOAT_MIN_BOB.doubleValue()))
            .andExpect(jsonPath("$.floatMaxBob").value(DEFAULT_FLOAT_MAX_BOB.doubleValue()))
            .andExpect(jsonPath("$.doubleRequiredBob").value(DEFAULT_DOUBLE_REQUIRED_BOB.doubleValue()))
            .andExpect(jsonPath("$.doubleMinBob").value(DEFAULT_DOUBLE_MIN_BOB.doubleValue()))
            .andExpect(jsonPath("$.doubleMaxBob").value(DEFAULT_DOUBLE_MAX_BOB.doubleValue()))
            .andExpect(jsonPath("$.bigDecimalRequiredBob").value(DEFAULT_BIG_DECIMAL_REQUIRED_BOB.intValue()))
            .andExpect(jsonPath("$.bigDecimalMinBob").value(DEFAULT_BIG_DECIMAL_MIN_BOB.intValue()))
            .andExpect(jsonPath("$.bigDecimalMaxBob").value(DEFAULT_BIG_DECIMAL_MAX_BOB.intValue()))
            .andExpect(jsonPath("$.localDateBob").value(DEFAULT_LOCAL_DATE_BOB.toString()))
            .andExpect(jsonPath("$.localDateRequiredBob").value(DEFAULT_LOCAL_DATE_REQUIRED_BOB.toString()))
            .andExpect(jsonPath("$.instantBob").value(DEFAULT_INSTANT_BOB.toString()))
            .andExpect(jsonPath("$.instanteRequiredBob").value(DEFAULT_INSTANTE_REQUIRED_BOB.toString()))
            .andExpect(jsonPath("$.zonedDateTimeBob").value(sameInstant(DEFAULT_ZONED_DATE_TIME_BOB)))
            .andExpect(jsonPath("$.zonedDateTimeRequiredBob").value(sameInstant(DEFAULT_ZONED_DATE_TIME_REQUIRED_BOB)))
            .andExpect(jsonPath("$.booleanBob").value(DEFAULT_BOOLEAN_BOB.booleanValue()))
            .andExpect(jsonPath("$.booleanRequiredBob").value(DEFAULT_BOOLEAN_REQUIRED_BOB.booleanValue()))
            .andExpect(jsonPath("$.enumBob").value(DEFAULT_ENUM_BOB.toString()))
            .andExpect(jsonPath("$.enumRequiredBob").value(DEFAULT_ENUM_REQUIRED_BOB.toString()))
            .andExpect(jsonPath("$.byteImageBobContentType").value(DEFAULT_BYTE_IMAGE_BOB_CONTENT_TYPE))
            .andExpect(jsonPath("$.byteImageBob").value(Base64Utils.encodeToString(DEFAULT_BYTE_IMAGE_BOB)))
            .andExpect(jsonPath("$.byteImageRequiredBobContentType").value(DEFAULT_BYTE_IMAGE_REQUIRED_BOB_CONTENT_TYPE))
            .andExpect(jsonPath("$.byteImageRequiredBob").value(Base64Utils.encodeToString(DEFAULT_BYTE_IMAGE_REQUIRED_BOB)))
            .andExpect(jsonPath("$.byteImageMinbytesBobContentType").value(DEFAULT_BYTE_IMAGE_MINBYTES_BOB_CONTENT_TYPE))
            .andExpect(jsonPath("$.byteImageMinbytesBob").value(Base64Utils.encodeToString(DEFAULT_BYTE_IMAGE_MINBYTES_BOB)))
            .andExpect(jsonPath("$.byteImageMaxbytesBobContentType").value(DEFAULT_BYTE_IMAGE_MAXBYTES_BOB_CONTENT_TYPE))
            .andExpect(jsonPath("$.byteImageMaxbytesBob").value(Base64Utils.encodeToString(DEFAULT_BYTE_IMAGE_MAXBYTES_BOB)))
            .andExpect(jsonPath("$.byteAnyBobContentType").value(DEFAULT_BYTE_ANY_BOB_CONTENT_TYPE))
            .andExpect(jsonPath("$.byteAnyBob").value(Base64Utils.encodeToString(DEFAULT_BYTE_ANY_BOB)))
            .andExpect(jsonPath("$.byteAnyRequiredBobContentType").value(DEFAULT_BYTE_ANY_REQUIRED_BOB_CONTENT_TYPE))
            .andExpect(jsonPath("$.byteAnyRequiredBob").value(Base64Utils.encodeToString(DEFAULT_BYTE_ANY_REQUIRED_BOB)))
            .andExpect(jsonPath("$.byteAnyMinbytesBobContentType").value(DEFAULT_BYTE_ANY_MINBYTES_BOB_CONTENT_TYPE))
            .andExpect(jsonPath("$.byteAnyMinbytesBob").value(Base64Utils.encodeToString(DEFAULT_BYTE_ANY_MINBYTES_BOB)))
            .andExpect(jsonPath("$.byteAnyMaxbytesBobContentType").value(DEFAULT_BYTE_ANY_MAXBYTES_BOB_CONTENT_TYPE))
            .andExpect(jsonPath("$.byteAnyMaxbytesBob").value(Base64Utils.encodeToString(DEFAULT_BYTE_ANY_MAXBYTES_BOB)))
            .andExpect(jsonPath("$.byteTextBob").value(DEFAULT_BYTE_TEXT_BOB.toString()))
            .andExpect(jsonPath("$.byteTextRequiredBob").value(DEFAULT_BYTE_TEXT_REQUIRED_BOB.toString()))
            .andExpect(jsonPath("$.byteTextMinbytesBob").value(DEFAULT_BYTE_TEXT_MINBYTES_BOB.toString()))
            .andExpect(jsonPath("$.byteTextMaxbytesBob").value(DEFAULT_BYTE_TEXT_MAXBYTES_BOB.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingFieldTestServiceClassEntity() throws Exception {
        // Get the fieldTestServiceClassEntity
        restFieldTestServiceClassEntityMockMvc.perform(get("/api/field-test-service-class-entities/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFieldTestServiceClassEntity() throws Exception {
        // Initialize the database
        fieldTestServiceClassEntityService.save(fieldTestServiceClassEntity);

        int databaseSizeBeforeUpdate = fieldTestServiceClassEntityRepository.findAll().size();

        // Update the fieldTestServiceClassEntity
        FieldTestServiceClassEntity updatedFieldTestServiceClassEntity = fieldTestServiceClassEntityRepository.findOne(fieldTestServiceClassEntity.getId());
        updatedFieldTestServiceClassEntity
            .stringBob(UPDATED_STRING_BOB)
            .stringRequiredBob(UPDATED_STRING_REQUIRED_BOB)
            .stringMinlengthBob(UPDATED_STRING_MINLENGTH_BOB)
            .stringMaxlengthBob(UPDATED_STRING_MAXLENGTH_BOB)
            .stringPatternBob(UPDATED_STRING_PATTERN_BOB)
            .integerBob(UPDATED_INTEGER_BOB)
            .integerRequiredBob(UPDATED_INTEGER_REQUIRED_BOB)
            .integerMinBob(UPDATED_INTEGER_MIN_BOB)
            .integerMaxBob(UPDATED_INTEGER_MAX_BOB)
            .longBob(UPDATED_LONG_BOB)
            .longRequiredBob(UPDATED_LONG_REQUIRED_BOB)
            .longMinBob(UPDATED_LONG_MIN_BOB)
            .longMaxBob(UPDATED_LONG_MAX_BOB)
            .floatBob(UPDATED_FLOAT_BOB)
            .floatRequiredBob(UPDATED_FLOAT_REQUIRED_BOB)
            .floatMinBob(UPDATED_FLOAT_MIN_BOB)
            .floatMaxBob(UPDATED_FLOAT_MAX_BOB)
            .doubleRequiredBob(UPDATED_DOUBLE_REQUIRED_BOB)
            .doubleMinBob(UPDATED_DOUBLE_MIN_BOB)
            .doubleMaxBob(UPDATED_DOUBLE_MAX_BOB)
            .bigDecimalRequiredBob(UPDATED_BIG_DECIMAL_REQUIRED_BOB)
            .bigDecimalMinBob(UPDATED_BIG_DECIMAL_MIN_BOB)
            .bigDecimalMaxBob(UPDATED_BIG_DECIMAL_MAX_BOB)
            .localDateBob(UPDATED_LOCAL_DATE_BOB)
            .localDateRequiredBob(UPDATED_LOCAL_DATE_REQUIRED_BOB)
            .instantBob(UPDATED_INSTANT_BOB)
            .instanteRequiredBob(UPDATED_INSTANTE_REQUIRED_BOB)
            .zonedDateTimeBob(UPDATED_ZONED_DATE_TIME_BOB)
            .zonedDateTimeRequiredBob(UPDATED_ZONED_DATE_TIME_REQUIRED_BOB)
            .booleanBob(UPDATED_BOOLEAN_BOB)
            .booleanRequiredBob(UPDATED_BOOLEAN_REQUIRED_BOB)
            .enumBob(UPDATED_ENUM_BOB)
            .enumRequiredBob(UPDATED_ENUM_REQUIRED_BOB)
            .byteImageBob(UPDATED_BYTE_IMAGE_BOB)
            .byteImageBobContentType(UPDATED_BYTE_IMAGE_BOB_CONTENT_TYPE)
            .byteImageRequiredBob(UPDATED_BYTE_IMAGE_REQUIRED_BOB)
            .byteImageRequiredBobContentType(UPDATED_BYTE_IMAGE_REQUIRED_BOB_CONTENT_TYPE)
            .byteImageMinbytesBob(UPDATED_BYTE_IMAGE_MINBYTES_BOB)
            .byteImageMinbytesBobContentType(UPDATED_BYTE_IMAGE_MINBYTES_BOB_CONTENT_TYPE)
            .byteImageMaxbytesBob(UPDATED_BYTE_IMAGE_MAXBYTES_BOB)
            .byteImageMaxbytesBobContentType(UPDATED_BYTE_IMAGE_MAXBYTES_BOB_CONTENT_TYPE)
            .byteAnyBob(UPDATED_BYTE_ANY_BOB)
            .byteAnyBobContentType(UPDATED_BYTE_ANY_BOB_CONTENT_TYPE)
            .byteAnyRequiredBob(UPDATED_BYTE_ANY_REQUIRED_BOB)
            .byteAnyRequiredBobContentType(UPDATED_BYTE_ANY_REQUIRED_BOB_CONTENT_TYPE)
            .byteAnyMinbytesBob(UPDATED_BYTE_ANY_MINBYTES_BOB)
            .byteAnyMinbytesBobContentType(UPDATED_BYTE_ANY_MINBYTES_BOB_CONTENT_TYPE)
            .byteAnyMaxbytesBob(UPDATED_BYTE_ANY_MAXBYTES_BOB)
            .byteAnyMaxbytesBobContentType(UPDATED_BYTE_ANY_MAXBYTES_BOB_CONTENT_TYPE)
            .byteTextBob(UPDATED_BYTE_TEXT_BOB)
            .byteTextRequiredBob(UPDATED_BYTE_TEXT_REQUIRED_BOB)
            .byteTextMinbytesBob(UPDATED_BYTE_TEXT_MINBYTES_BOB)
            .byteTextMaxbytesBob(UPDATED_BYTE_TEXT_MAXBYTES_BOB);

        restFieldTestServiceClassEntityMockMvc.perform(put("/api/field-test-service-class-entities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedFieldTestServiceClassEntity)))
            .andExpect(status().isOk());

        // Validate the FieldTestServiceClassEntity in the database
        List<FieldTestServiceClassEntity> fieldTestServiceClassEntityList = fieldTestServiceClassEntityRepository.findAll();
        assertThat(fieldTestServiceClassEntityList).hasSize(databaseSizeBeforeUpdate);
        FieldTestServiceClassEntity testFieldTestServiceClassEntity = fieldTestServiceClassEntityList.get(fieldTestServiceClassEntityList.size() - 1);
        assertThat(testFieldTestServiceClassEntity.getStringBob()).isEqualTo(UPDATED_STRING_BOB);
        assertThat(testFieldTestServiceClassEntity.getStringRequiredBob()).isEqualTo(UPDATED_STRING_REQUIRED_BOB);
        assertThat(testFieldTestServiceClassEntity.getStringMinlengthBob()).isEqualTo(UPDATED_STRING_MINLENGTH_BOB);
        assertThat(testFieldTestServiceClassEntity.getStringMaxlengthBob()).isEqualTo(UPDATED_STRING_MAXLENGTH_BOB);
        assertThat(testFieldTestServiceClassEntity.getStringPatternBob()).isEqualTo(UPDATED_STRING_PATTERN_BOB);
        assertThat(testFieldTestServiceClassEntity.getIntegerBob()).isEqualTo(UPDATED_INTEGER_BOB);
        assertThat(testFieldTestServiceClassEntity.getIntegerRequiredBob()).isEqualTo(UPDATED_INTEGER_REQUIRED_BOB);
        assertThat(testFieldTestServiceClassEntity.getIntegerMinBob()).isEqualTo(UPDATED_INTEGER_MIN_BOB);
        assertThat(testFieldTestServiceClassEntity.getIntegerMaxBob()).isEqualTo(UPDATED_INTEGER_MAX_BOB);
        assertThat(testFieldTestServiceClassEntity.getLongBob()).isEqualTo(UPDATED_LONG_BOB);
        assertThat(testFieldTestServiceClassEntity.getLongRequiredBob()).isEqualTo(UPDATED_LONG_REQUIRED_BOB);
        assertThat(testFieldTestServiceClassEntity.getLongMinBob()).isEqualTo(UPDATED_LONG_MIN_BOB);
        assertThat(testFieldTestServiceClassEntity.getLongMaxBob()).isEqualTo(UPDATED_LONG_MAX_BOB);
        assertThat(testFieldTestServiceClassEntity.getFloatBob()).isEqualTo(UPDATED_FLOAT_BOB);
        assertThat(testFieldTestServiceClassEntity.getFloatRequiredBob()).isEqualTo(UPDATED_FLOAT_REQUIRED_BOB);
        assertThat(testFieldTestServiceClassEntity.getFloatMinBob()).isEqualTo(UPDATED_FLOAT_MIN_BOB);
        assertThat(testFieldTestServiceClassEntity.getFloatMaxBob()).isEqualTo(UPDATED_FLOAT_MAX_BOB);
        assertThat(testFieldTestServiceClassEntity.getDoubleRequiredBob()).isEqualTo(UPDATED_DOUBLE_REQUIRED_BOB);
        assertThat(testFieldTestServiceClassEntity.getDoubleMinBob()).isEqualTo(UPDATED_DOUBLE_MIN_BOB);
        assertThat(testFieldTestServiceClassEntity.getDoubleMaxBob()).isEqualTo(UPDATED_DOUBLE_MAX_BOB);
        assertThat(testFieldTestServiceClassEntity.getBigDecimalRequiredBob()).isEqualTo(UPDATED_BIG_DECIMAL_REQUIRED_BOB);
        assertThat(testFieldTestServiceClassEntity.getBigDecimalMinBob()).isEqualTo(UPDATED_BIG_DECIMAL_MIN_BOB);
        assertThat(testFieldTestServiceClassEntity.getBigDecimalMaxBob()).isEqualTo(UPDATED_BIG_DECIMAL_MAX_BOB);
        assertThat(testFieldTestServiceClassEntity.getLocalDateBob()).isEqualTo(UPDATED_LOCAL_DATE_BOB);
        assertThat(testFieldTestServiceClassEntity.getLocalDateRequiredBob()).isEqualTo(UPDATED_LOCAL_DATE_REQUIRED_BOB);
        assertThat(testFieldTestServiceClassEntity.getInstantBob()).isEqualTo(UPDATED_INSTANT_BOB);
        assertThat(testFieldTestServiceClassEntity.getInstanteRequiredBob()).isEqualTo(UPDATED_INSTANTE_REQUIRED_BOB);
        assertThat(testFieldTestServiceClassEntity.getZonedDateTimeBob()).isEqualTo(UPDATED_ZONED_DATE_TIME_BOB);
        assertThat(testFieldTestServiceClassEntity.getZonedDateTimeRequiredBob()).isEqualTo(UPDATED_ZONED_DATE_TIME_REQUIRED_BOB);
        assertThat(testFieldTestServiceClassEntity.isBooleanBob()).isEqualTo(UPDATED_BOOLEAN_BOB);
        assertThat(testFieldTestServiceClassEntity.isBooleanRequiredBob()).isEqualTo(UPDATED_BOOLEAN_REQUIRED_BOB);
        assertThat(testFieldTestServiceClassEntity.getEnumBob()).isEqualTo(UPDATED_ENUM_BOB);
        assertThat(testFieldTestServiceClassEntity.getEnumRequiredBob()).isEqualTo(UPDATED_ENUM_REQUIRED_BOB);
        assertThat(testFieldTestServiceClassEntity.getByteImageBob()).isEqualTo(UPDATED_BYTE_IMAGE_BOB);
        assertThat(testFieldTestServiceClassEntity.getByteImageBobContentType()).isEqualTo(UPDATED_BYTE_IMAGE_BOB_CONTENT_TYPE);
        assertThat(testFieldTestServiceClassEntity.getByteImageRequiredBob()).isEqualTo(UPDATED_BYTE_IMAGE_REQUIRED_BOB);
        assertThat(testFieldTestServiceClassEntity.getByteImageRequiredBobContentType()).isEqualTo(UPDATED_BYTE_IMAGE_REQUIRED_BOB_CONTENT_TYPE);
        assertThat(testFieldTestServiceClassEntity.getByteImageMinbytesBob()).isEqualTo(UPDATED_BYTE_IMAGE_MINBYTES_BOB);
        assertThat(testFieldTestServiceClassEntity.getByteImageMinbytesBobContentType()).isEqualTo(UPDATED_BYTE_IMAGE_MINBYTES_BOB_CONTENT_TYPE);
        assertThat(testFieldTestServiceClassEntity.getByteImageMaxbytesBob()).isEqualTo(UPDATED_BYTE_IMAGE_MAXBYTES_BOB);
        assertThat(testFieldTestServiceClassEntity.getByteImageMaxbytesBobContentType()).isEqualTo(UPDATED_BYTE_IMAGE_MAXBYTES_BOB_CONTENT_TYPE);
        assertThat(testFieldTestServiceClassEntity.getByteAnyBob()).isEqualTo(UPDATED_BYTE_ANY_BOB);
        assertThat(testFieldTestServiceClassEntity.getByteAnyBobContentType()).isEqualTo(UPDATED_BYTE_ANY_BOB_CONTENT_TYPE);
        assertThat(testFieldTestServiceClassEntity.getByteAnyRequiredBob()).isEqualTo(UPDATED_BYTE_ANY_REQUIRED_BOB);
        assertThat(testFieldTestServiceClassEntity.getByteAnyRequiredBobContentType()).isEqualTo(UPDATED_BYTE_ANY_REQUIRED_BOB_CONTENT_TYPE);
        assertThat(testFieldTestServiceClassEntity.getByteAnyMinbytesBob()).isEqualTo(UPDATED_BYTE_ANY_MINBYTES_BOB);
        assertThat(testFieldTestServiceClassEntity.getByteAnyMinbytesBobContentType()).isEqualTo(UPDATED_BYTE_ANY_MINBYTES_BOB_CONTENT_TYPE);
        assertThat(testFieldTestServiceClassEntity.getByteAnyMaxbytesBob()).isEqualTo(UPDATED_BYTE_ANY_MAXBYTES_BOB);
        assertThat(testFieldTestServiceClassEntity.getByteAnyMaxbytesBobContentType()).isEqualTo(UPDATED_BYTE_ANY_MAXBYTES_BOB_CONTENT_TYPE);
        assertThat(testFieldTestServiceClassEntity.getByteTextBob()).isEqualTo(UPDATED_BYTE_TEXT_BOB);
        assertThat(testFieldTestServiceClassEntity.getByteTextRequiredBob()).isEqualTo(UPDATED_BYTE_TEXT_REQUIRED_BOB);
        assertThat(testFieldTestServiceClassEntity.getByteTextMinbytesBob()).isEqualTo(UPDATED_BYTE_TEXT_MINBYTES_BOB);
        assertThat(testFieldTestServiceClassEntity.getByteTextMaxbytesBob()).isEqualTo(UPDATED_BYTE_TEXT_MAXBYTES_BOB);

        // Validate the FieldTestServiceClassEntity in Elasticsearch
        FieldTestServiceClassEntity fieldTestServiceClassEntityEs = fieldTestServiceClassEntitySearchRepository.findOne(testFieldTestServiceClassEntity.getId());
        assertThat(fieldTestServiceClassEntityEs).isEqualToComparingFieldByField(testFieldTestServiceClassEntity);
    }

    @Test
    @Transactional
    public void updateNonExistingFieldTestServiceClassEntity() throws Exception {
        int databaseSizeBeforeUpdate = fieldTestServiceClassEntityRepository.findAll().size();

        // Create the FieldTestServiceClassEntity

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restFieldTestServiceClassEntityMockMvc.perform(put("/api/field-test-service-class-entities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fieldTestServiceClassEntity)))
            .andExpect(status().isCreated());

        // Validate the FieldTestServiceClassEntity in the database
        List<FieldTestServiceClassEntity> fieldTestServiceClassEntityList = fieldTestServiceClassEntityRepository.findAll();
        assertThat(fieldTestServiceClassEntityList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteFieldTestServiceClassEntity() throws Exception {
        // Initialize the database
        fieldTestServiceClassEntityService.save(fieldTestServiceClassEntity);

        int databaseSizeBeforeDelete = fieldTestServiceClassEntityRepository.findAll().size();

        // Get the fieldTestServiceClassEntity
        restFieldTestServiceClassEntityMockMvc.perform(delete("/api/field-test-service-class-entities/{id}", fieldTestServiceClassEntity.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate Elasticsearch is empty
        boolean fieldTestServiceClassEntityExistsInEs = fieldTestServiceClassEntitySearchRepository.exists(fieldTestServiceClassEntity.getId());
        assertThat(fieldTestServiceClassEntityExistsInEs).isFalse();

        // Validate the database is empty
        List<FieldTestServiceClassEntity> fieldTestServiceClassEntityList = fieldTestServiceClassEntityRepository.findAll();
        assertThat(fieldTestServiceClassEntityList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void searchFieldTestServiceClassEntity() throws Exception {
        // Initialize the database
        fieldTestServiceClassEntityService.save(fieldTestServiceClassEntity);

        // Search the fieldTestServiceClassEntity
        restFieldTestServiceClassEntityMockMvc.perform(get("/api/_search/field-test-service-class-entities?query=id:" + fieldTestServiceClassEntity.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(fieldTestServiceClassEntity.getId().intValue())))
            .andExpect(jsonPath("$.[*].stringBob").value(hasItem(DEFAULT_STRING_BOB.toString())))
            .andExpect(jsonPath("$.[*].stringRequiredBob").value(hasItem(DEFAULT_STRING_REQUIRED_BOB.toString())))
            .andExpect(jsonPath("$.[*].stringMinlengthBob").value(hasItem(DEFAULT_STRING_MINLENGTH_BOB.toString())))
            .andExpect(jsonPath("$.[*].stringMaxlengthBob").value(hasItem(DEFAULT_STRING_MAXLENGTH_BOB.toString())))
            .andExpect(jsonPath("$.[*].stringPatternBob").value(hasItem(DEFAULT_STRING_PATTERN_BOB.toString())))
            .andExpect(jsonPath("$.[*].integerBob").value(hasItem(DEFAULT_INTEGER_BOB)))
            .andExpect(jsonPath("$.[*].integerRequiredBob").value(hasItem(DEFAULT_INTEGER_REQUIRED_BOB)))
            .andExpect(jsonPath("$.[*].integerMinBob").value(hasItem(DEFAULT_INTEGER_MIN_BOB)))
            .andExpect(jsonPath("$.[*].integerMaxBob").value(hasItem(DEFAULT_INTEGER_MAX_BOB)))
            .andExpect(jsonPath("$.[*].longBob").value(hasItem(DEFAULT_LONG_BOB.intValue())))
            .andExpect(jsonPath("$.[*].longRequiredBob").value(hasItem(DEFAULT_LONG_REQUIRED_BOB.intValue())))
            .andExpect(jsonPath("$.[*].longMinBob").value(hasItem(DEFAULT_LONG_MIN_BOB.intValue())))
            .andExpect(jsonPath("$.[*].longMaxBob").value(hasItem(DEFAULT_LONG_MAX_BOB.intValue())))
            .andExpect(jsonPath("$.[*].floatBob").value(hasItem(DEFAULT_FLOAT_BOB.doubleValue())))
            .andExpect(jsonPath("$.[*].floatRequiredBob").value(hasItem(DEFAULT_FLOAT_REQUIRED_BOB.doubleValue())))
            .andExpect(jsonPath("$.[*].floatMinBob").value(hasItem(DEFAULT_FLOAT_MIN_BOB.doubleValue())))
            .andExpect(jsonPath("$.[*].floatMaxBob").value(hasItem(DEFAULT_FLOAT_MAX_BOB.doubleValue())))
            .andExpect(jsonPath("$.[*].doubleRequiredBob").value(hasItem(DEFAULT_DOUBLE_REQUIRED_BOB.doubleValue())))
            .andExpect(jsonPath("$.[*].doubleMinBob").value(hasItem(DEFAULT_DOUBLE_MIN_BOB.doubleValue())))
            .andExpect(jsonPath("$.[*].doubleMaxBob").value(hasItem(DEFAULT_DOUBLE_MAX_BOB.doubleValue())))
            .andExpect(jsonPath("$.[*].bigDecimalRequiredBob").value(hasItem(DEFAULT_BIG_DECIMAL_REQUIRED_BOB.intValue())))
            .andExpect(jsonPath("$.[*].bigDecimalMinBob").value(hasItem(DEFAULT_BIG_DECIMAL_MIN_BOB.intValue())))
            .andExpect(jsonPath("$.[*].bigDecimalMaxBob").value(hasItem(DEFAULT_BIG_DECIMAL_MAX_BOB.intValue())))
            .andExpect(jsonPath("$.[*].localDateBob").value(hasItem(DEFAULT_LOCAL_DATE_BOB.toString())))
            .andExpect(jsonPath("$.[*].localDateRequiredBob").value(hasItem(DEFAULT_LOCAL_DATE_REQUIRED_BOB.toString())))
            .andExpect(jsonPath("$.[*].instantBob").value(hasItem(DEFAULT_INSTANT_BOB.toString())))
            .andExpect(jsonPath("$.[*].instanteRequiredBob").value(hasItem(DEFAULT_INSTANTE_REQUIRED_BOB.toString())))
            .andExpect(jsonPath("$.[*].zonedDateTimeBob").value(hasItem(sameInstant(DEFAULT_ZONED_DATE_TIME_BOB))))
            .andExpect(jsonPath("$.[*].zonedDateTimeRequiredBob").value(hasItem(sameInstant(DEFAULT_ZONED_DATE_TIME_REQUIRED_BOB))))
            .andExpect(jsonPath("$.[*].booleanBob").value(hasItem(DEFAULT_BOOLEAN_BOB.booleanValue())))
            .andExpect(jsonPath("$.[*].booleanRequiredBob").value(hasItem(DEFAULT_BOOLEAN_REQUIRED_BOB.booleanValue())))
            .andExpect(jsonPath("$.[*].enumBob").value(hasItem(DEFAULT_ENUM_BOB.toString())))
            .andExpect(jsonPath("$.[*].enumRequiredBob").value(hasItem(DEFAULT_ENUM_REQUIRED_BOB.toString())))
            .andExpect(jsonPath("$.[*].byteImageBobContentType").value(hasItem(DEFAULT_BYTE_IMAGE_BOB_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].byteImageBob").value(hasItem(Base64Utils.encodeToString(DEFAULT_BYTE_IMAGE_BOB))))
            .andExpect(jsonPath("$.[*].byteImageRequiredBobContentType").value(hasItem(DEFAULT_BYTE_IMAGE_REQUIRED_BOB_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].byteImageRequiredBob").value(hasItem(Base64Utils.encodeToString(DEFAULT_BYTE_IMAGE_REQUIRED_BOB))))
            .andExpect(jsonPath("$.[*].byteImageMinbytesBobContentType").value(hasItem(DEFAULT_BYTE_IMAGE_MINBYTES_BOB_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].byteImageMinbytesBob").value(hasItem(Base64Utils.encodeToString(DEFAULT_BYTE_IMAGE_MINBYTES_BOB))))
            .andExpect(jsonPath("$.[*].byteImageMaxbytesBobContentType").value(hasItem(DEFAULT_BYTE_IMAGE_MAXBYTES_BOB_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].byteImageMaxbytesBob").value(hasItem(Base64Utils.encodeToString(DEFAULT_BYTE_IMAGE_MAXBYTES_BOB))))
            .andExpect(jsonPath("$.[*].byteAnyBobContentType").value(hasItem(DEFAULT_BYTE_ANY_BOB_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].byteAnyBob").value(hasItem(Base64Utils.encodeToString(DEFAULT_BYTE_ANY_BOB))))
            .andExpect(jsonPath("$.[*].byteAnyRequiredBobContentType").value(hasItem(DEFAULT_BYTE_ANY_REQUIRED_BOB_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].byteAnyRequiredBob").value(hasItem(Base64Utils.encodeToString(DEFAULT_BYTE_ANY_REQUIRED_BOB))))
            .andExpect(jsonPath("$.[*].byteAnyMinbytesBobContentType").value(hasItem(DEFAULT_BYTE_ANY_MINBYTES_BOB_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].byteAnyMinbytesBob").value(hasItem(Base64Utils.encodeToString(DEFAULT_BYTE_ANY_MINBYTES_BOB))))
            .andExpect(jsonPath("$.[*].byteAnyMaxbytesBobContentType").value(hasItem(DEFAULT_BYTE_ANY_MAXBYTES_BOB_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].byteAnyMaxbytesBob").value(hasItem(Base64Utils.encodeToString(DEFAULT_BYTE_ANY_MAXBYTES_BOB))))
            .andExpect(jsonPath("$.[*].byteTextBob").value(hasItem(DEFAULT_BYTE_TEXT_BOB.toString())))
            .andExpect(jsonPath("$.[*].byteTextRequiredBob").value(hasItem(DEFAULT_BYTE_TEXT_REQUIRED_BOB.toString())))
            .andExpect(jsonPath("$.[*].byteTextMinbytesBob").value(hasItem(DEFAULT_BYTE_TEXT_MINBYTES_BOB.toString())))
            .andExpect(jsonPath("$.[*].byteTextMaxbytesBob").value(hasItem(DEFAULT_BYTE_TEXT_MAXBYTES_BOB.toString())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(FieldTestServiceClassEntity.class);
        FieldTestServiceClassEntity fieldTestServiceClassEntity1 = new FieldTestServiceClassEntity();
        fieldTestServiceClassEntity1.setId(1L);
        FieldTestServiceClassEntity fieldTestServiceClassEntity2 = new FieldTestServiceClassEntity();
        fieldTestServiceClassEntity2.setId(fieldTestServiceClassEntity1.getId());
        assertThat(fieldTestServiceClassEntity1).isEqualTo(fieldTestServiceClassEntity2);
        fieldTestServiceClassEntity2.setId(2L);
        assertThat(fieldTestServiceClassEntity1).isNotEqualTo(fieldTestServiceClassEntity2);
        fieldTestServiceClassEntity1.setId(null);
        assertThat(fieldTestServiceClassEntity1).isNotEqualTo(fieldTestServiceClassEntity2);
    }
}
