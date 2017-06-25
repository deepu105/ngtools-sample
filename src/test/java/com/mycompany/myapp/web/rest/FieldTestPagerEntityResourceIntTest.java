package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.Jh4TestApp;

import com.mycompany.myapp.domain.FieldTestPagerEntity;
import com.mycompany.myapp.repository.FieldTestPagerEntityRepository;
import com.mycompany.myapp.repository.search.FieldTestPagerEntitySearchRepository;
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
 * Test class for the FieldTestPagerEntityResource REST controller.
 *
 * @see FieldTestPagerEntityResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Jh4TestApp.class)
public class FieldTestPagerEntityResourceIntTest {

    private static final String DEFAULT_STRING_JADE = "AAAAAAAAAA";
    private static final String UPDATED_STRING_JADE = "BBBBBBBBBB";

    private static final String DEFAULT_STRING_REQUIRED_JADE = "AAAAAAAAAA";
    private static final String UPDATED_STRING_REQUIRED_JADE = "BBBBBBBBBB";

    private static final String DEFAULT_STRING_MINLENGTH_JADE = "AAAAAAAAAA";
    private static final String UPDATED_STRING_MINLENGTH_JADE = "BBBBBBBBBB";

    private static final String DEFAULT_STRING_MAXLENGTH_JADE = "AAAAAAAAAA";
    private static final String UPDATED_STRING_MAXLENGTH_JADE = "BBBBBBBBBB";

    private static final String DEFAULT_STRING_PATTERN_JADE = "AAAAAAAAAA";
    private static final String UPDATED_STRING_PATTERN_JADE = "BBBBBBBBBB";

    private static final Integer DEFAULT_INTEGER_JADE = 1;
    private static final Integer UPDATED_INTEGER_JADE = 2;

    private static final Integer DEFAULT_INTEGER_REQUIRED_JADE = 1;
    private static final Integer UPDATED_INTEGER_REQUIRED_JADE = 2;

    private static final Integer DEFAULT_INTEGER_MIN_JADE = 0;
    private static final Integer UPDATED_INTEGER_MIN_JADE = 1;

    private static final Integer DEFAULT_INTEGER_MAX_JADE = 100;
    private static final Integer UPDATED_INTEGER_MAX_JADE = 99;

    private static final Long DEFAULT_LONG_JADE = 1L;
    private static final Long UPDATED_LONG_JADE = 2L;

    private static final Long DEFAULT_LONG_REQUIRED_JADE = 1L;
    private static final Long UPDATED_LONG_REQUIRED_JADE = 2L;

    private static final Long DEFAULT_LONG_MIN_JADE = 0L;
    private static final Long UPDATED_LONG_MIN_JADE = 1L;

    private static final Long DEFAULT_LONG_MAX_JADE = 100L;
    private static final Long UPDATED_LONG_MAX_JADE = 99L;

    private static final Float DEFAULT_FLOAT_JADE = 1F;
    private static final Float UPDATED_FLOAT_JADE = 2F;

    private static final Float DEFAULT_FLOAT_REQUIRED_JADE = 1F;
    private static final Float UPDATED_FLOAT_REQUIRED_JADE = 2F;

    private static final Float DEFAULT_FLOAT_MIN_JADE = 0F;
    private static final Float UPDATED_FLOAT_MIN_JADE = 1F;

    private static final Float DEFAULT_FLOAT_MAX_JADE = 100F;
    private static final Float UPDATED_FLOAT_MAX_JADE = 99F;

    private static final Double DEFAULT_DOUBLE_REQUIRED_JADE = 1D;
    private static final Double UPDATED_DOUBLE_REQUIRED_JADE = 2D;

    private static final Double DEFAULT_DOUBLE_MIN_JADE = 0D;
    private static final Double UPDATED_DOUBLE_MIN_JADE = 1D;

    private static final Double DEFAULT_DOUBLE_MAX_JADE = 100D;
    private static final Double UPDATED_DOUBLE_MAX_JADE = 99D;

    private static final BigDecimal DEFAULT_BIG_DECIMAL_REQUIRED_JADE = new BigDecimal(1);
    private static final BigDecimal UPDATED_BIG_DECIMAL_REQUIRED_JADE = new BigDecimal(2);

    private static final BigDecimal DEFAULT_BIG_DECIMAL_MIN_JADE = new BigDecimal(0);
    private static final BigDecimal UPDATED_BIG_DECIMAL_MIN_JADE = new BigDecimal(1);

    private static final BigDecimal DEFAULT_BIG_DECIMAL_MAX_JADE = new BigDecimal(100);
    private static final BigDecimal UPDATED_BIG_DECIMAL_MAX_JADE = new BigDecimal(99);

    private static final LocalDate DEFAULT_LOCAL_DATE_JADE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_LOCAL_DATE_JADE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_LOCAL_DATE_REQUIRED_JADE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_LOCAL_DATE_REQUIRED_JADE = LocalDate.now(ZoneId.systemDefault());

    private static final Instant DEFAULT_INSTANT_JADE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_INSTANT_JADE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_INSTANTE_REQUIRED_JADE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_INSTANTE_REQUIRED_JADE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final ZonedDateTime DEFAULT_ZONED_DATE_TIME_JADE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_ZONED_DATE_TIME_JADE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_ZONED_DATE_TIME_REQUIRED_JADE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_ZONED_DATE_TIME_REQUIRED_JADE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final Boolean DEFAULT_BOOLEAN_JADE = false;
    private static final Boolean UPDATED_BOOLEAN_JADE = true;

    private static final Boolean DEFAULT_BOOLEAN_REQUIRED_JADE = false;
    private static final Boolean UPDATED_BOOLEAN_REQUIRED_JADE = true;

    private static final EnumFieldClass DEFAULT_ENUM_JADE = EnumFieldClass.ENUM_VALUE_1;
    private static final EnumFieldClass UPDATED_ENUM_JADE = EnumFieldClass.ENUM_VALUE_2;

    private static final EnumRequiredFieldClass DEFAULT_ENUM_REQUIRED_JADE = EnumRequiredFieldClass.ENUM_VALUE_1;
    private static final EnumRequiredFieldClass UPDATED_ENUM_REQUIRED_JADE = EnumRequiredFieldClass.ENUM_VALUE_2;

    private static final byte[] DEFAULT_BYTE_IMAGE_JADE = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_BYTE_IMAGE_JADE = TestUtil.createByteArray(2, "1");
    private static final String DEFAULT_BYTE_IMAGE_JADE_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_BYTE_IMAGE_JADE_CONTENT_TYPE = "image/png";

    private static final byte[] DEFAULT_BYTE_IMAGE_REQUIRED_JADE = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_BYTE_IMAGE_REQUIRED_JADE = TestUtil.createByteArray(2, "1");
    private static final String DEFAULT_BYTE_IMAGE_REQUIRED_JADE_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_BYTE_IMAGE_REQUIRED_JADE_CONTENT_TYPE = "image/png";

    private static final byte[] DEFAULT_BYTE_IMAGE_MINBYTES_JADE = TestUtil.createByteArray(0, "0");
    private static final byte[] UPDATED_BYTE_IMAGE_MINBYTES_JADE = TestUtil.createByteArray(0, "1");
    private static final String DEFAULT_BYTE_IMAGE_MINBYTES_JADE_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_BYTE_IMAGE_MINBYTES_JADE_CONTENT_TYPE = "image/png";

    private static final byte[] DEFAULT_BYTE_IMAGE_MAXBYTES_JADE = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_BYTE_IMAGE_MAXBYTES_JADE = TestUtil.createByteArray(10000, "1");
    private static final String DEFAULT_BYTE_IMAGE_MAXBYTES_JADE_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_BYTE_IMAGE_MAXBYTES_JADE_CONTENT_TYPE = "image/png";

    private static final byte[] DEFAULT_BYTE_ANY_JADE = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_BYTE_ANY_JADE = TestUtil.createByteArray(2, "1");
    private static final String DEFAULT_BYTE_ANY_JADE_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_BYTE_ANY_JADE_CONTENT_TYPE = "image/png";

    private static final byte[] DEFAULT_BYTE_ANY_REQUIRED_JADE = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_BYTE_ANY_REQUIRED_JADE = TestUtil.createByteArray(2, "1");
    private static final String DEFAULT_BYTE_ANY_REQUIRED_JADE_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_BYTE_ANY_REQUIRED_JADE_CONTENT_TYPE = "image/png";

    private static final byte[] DEFAULT_BYTE_ANY_MINBYTES_JADE = TestUtil.createByteArray(0, "0");
    private static final byte[] UPDATED_BYTE_ANY_MINBYTES_JADE = TestUtil.createByteArray(0, "1");
    private static final String DEFAULT_BYTE_ANY_MINBYTES_JADE_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_BYTE_ANY_MINBYTES_JADE_CONTENT_TYPE = "image/png";

    private static final byte[] DEFAULT_BYTE_ANY_MAXBYTES_JADE = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_BYTE_ANY_MAXBYTES_JADE = TestUtil.createByteArray(10000, "1");
    private static final String DEFAULT_BYTE_ANY_MAXBYTES_JADE_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_BYTE_ANY_MAXBYTES_JADE_CONTENT_TYPE = "image/png";

    private static final String DEFAULT_BYTE_TEXT_JADE = "AAAAAAAAAA";
    private static final String UPDATED_BYTE_TEXT_JADE = "BBBBBBBBBB";

    private static final String DEFAULT_BYTE_TEXT_REQUIRED_JADE = "AAAAAAAAAA";
    private static final String UPDATED_BYTE_TEXT_REQUIRED_JADE = "BBBBBBBBBB";

    private static final String DEFAULT_BYTE_TEXT_MINBYTES_JADE = "AAAAAAAAAA";
    private static final String UPDATED_BYTE_TEXT_MINBYTES_JADE = "BBBBBBBBBB";

    private static final String DEFAULT_BYTE_TEXT_MAXBYTES_JADE = "AAAAAAAAAA";
    private static final String UPDATED_BYTE_TEXT_MAXBYTES_JADE = "BBBBBBBBBB";

    @Autowired
    private FieldTestPagerEntityRepository fieldTestPagerEntityRepository;

    @Autowired
    private FieldTestPagerEntitySearchRepository fieldTestPagerEntitySearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restFieldTestPagerEntityMockMvc;

    private FieldTestPagerEntity fieldTestPagerEntity;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        FieldTestPagerEntityResource fieldTestPagerEntityResource = new FieldTestPagerEntityResource(fieldTestPagerEntityRepository, fieldTestPagerEntitySearchRepository);
        this.restFieldTestPagerEntityMockMvc = MockMvcBuilders.standaloneSetup(fieldTestPagerEntityResource)
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
    public static FieldTestPagerEntity createEntity(EntityManager em) {
        FieldTestPagerEntity fieldTestPagerEntity = new FieldTestPagerEntity()
            .stringJade(DEFAULT_STRING_JADE)
            .stringRequiredJade(DEFAULT_STRING_REQUIRED_JADE)
            .stringMinlengthJade(DEFAULT_STRING_MINLENGTH_JADE)
            .stringMaxlengthJade(DEFAULT_STRING_MAXLENGTH_JADE)
            .stringPatternJade(DEFAULT_STRING_PATTERN_JADE)
            .integerJade(DEFAULT_INTEGER_JADE)
            .integerRequiredJade(DEFAULT_INTEGER_REQUIRED_JADE)
            .integerMinJade(DEFAULT_INTEGER_MIN_JADE)
            .integerMaxJade(DEFAULT_INTEGER_MAX_JADE)
            .longJade(DEFAULT_LONG_JADE)
            .longRequiredJade(DEFAULT_LONG_REQUIRED_JADE)
            .longMinJade(DEFAULT_LONG_MIN_JADE)
            .longMaxJade(DEFAULT_LONG_MAX_JADE)
            .floatJade(DEFAULT_FLOAT_JADE)
            .floatRequiredJade(DEFAULT_FLOAT_REQUIRED_JADE)
            .floatMinJade(DEFAULT_FLOAT_MIN_JADE)
            .floatMaxJade(DEFAULT_FLOAT_MAX_JADE)
            .doubleRequiredJade(DEFAULT_DOUBLE_REQUIRED_JADE)
            .doubleMinJade(DEFAULT_DOUBLE_MIN_JADE)
            .doubleMaxJade(DEFAULT_DOUBLE_MAX_JADE)
            .bigDecimalRequiredJade(DEFAULT_BIG_DECIMAL_REQUIRED_JADE)
            .bigDecimalMinJade(DEFAULT_BIG_DECIMAL_MIN_JADE)
            .bigDecimalMaxJade(DEFAULT_BIG_DECIMAL_MAX_JADE)
            .localDateJade(DEFAULT_LOCAL_DATE_JADE)
            .localDateRequiredJade(DEFAULT_LOCAL_DATE_REQUIRED_JADE)
            .instantJade(DEFAULT_INSTANT_JADE)
            .instanteRequiredJade(DEFAULT_INSTANTE_REQUIRED_JADE)
            .zonedDateTimeJade(DEFAULT_ZONED_DATE_TIME_JADE)
            .zonedDateTimeRequiredJade(DEFAULT_ZONED_DATE_TIME_REQUIRED_JADE)
            .booleanJade(DEFAULT_BOOLEAN_JADE)
            .booleanRequiredJade(DEFAULT_BOOLEAN_REQUIRED_JADE)
            .enumJade(DEFAULT_ENUM_JADE)
            .enumRequiredJade(DEFAULT_ENUM_REQUIRED_JADE)
            .byteImageJade(DEFAULT_BYTE_IMAGE_JADE)
            .byteImageJadeContentType(DEFAULT_BYTE_IMAGE_JADE_CONTENT_TYPE)
            .byteImageRequiredJade(DEFAULT_BYTE_IMAGE_REQUIRED_JADE)
            .byteImageRequiredJadeContentType(DEFAULT_BYTE_IMAGE_REQUIRED_JADE_CONTENT_TYPE)
            .byteImageMinbytesJade(DEFAULT_BYTE_IMAGE_MINBYTES_JADE)
            .byteImageMinbytesJadeContentType(DEFAULT_BYTE_IMAGE_MINBYTES_JADE_CONTENT_TYPE)
            .byteImageMaxbytesJade(DEFAULT_BYTE_IMAGE_MAXBYTES_JADE)
            .byteImageMaxbytesJadeContentType(DEFAULT_BYTE_IMAGE_MAXBYTES_JADE_CONTENT_TYPE)
            .byteAnyJade(DEFAULT_BYTE_ANY_JADE)
            .byteAnyJadeContentType(DEFAULT_BYTE_ANY_JADE_CONTENT_TYPE)
            .byteAnyRequiredJade(DEFAULT_BYTE_ANY_REQUIRED_JADE)
            .byteAnyRequiredJadeContentType(DEFAULT_BYTE_ANY_REQUIRED_JADE_CONTENT_TYPE)
            .byteAnyMinbytesJade(DEFAULT_BYTE_ANY_MINBYTES_JADE)
            .byteAnyMinbytesJadeContentType(DEFAULT_BYTE_ANY_MINBYTES_JADE_CONTENT_TYPE)
            .byteAnyMaxbytesJade(DEFAULT_BYTE_ANY_MAXBYTES_JADE)
            .byteAnyMaxbytesJadeContentType(DEFAULT_BYTE_ANY_MAXBYTES_JADE_CONTENT_TYPE)
            .byteTextJade(DEFAULT_BYTE_TEXT_JADE)
            .byteTextRequiredJade(DEFAULT_BYTE_TEXT_REQUIRED_JADE)
            .byteTextMinbytesJade(DEFAULT_BYTE_TEXT_MINBYTES_JADE)
            .byteTextMaxbytesJade(DEFAULT_BYTE_TEXT_MAXBYTES_JADE);
        return fieldTestPagerEntity;
    }

    @Before
    public void initTest() {
        fieldTestPagerEntitySearchRepository.deleteAll();
        fieldTestPagerEntity = createEntity(em);
    }

    @Test
    @Transactional
    public void createFieldTestPagerEntity() throws Exception {
        int databaseSizeBeforeCreate = fieldTestPagerEntityRepository.findAll().size();

        // Create the FieldTestPagerEntity
        restFieldTestPagerEntityMockMvc.perform(post("/api/field-test-pager-entities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fieldTestPagerEntity)))
            .andExpect(status().isCreated());

        // Validate the FieldTestPagerEntity in the database
        List<FieldTestPagerEntity> fieldTestPagerEntityList = fieldTestPagerEntityRepository.findAll();
        assertThat(fieldTestPagerEntityList).hasSize(databaseSizeBeforeCreate + 1);
        FieldTestPagerEntity testFieldTestPagerEntity = fieldTestPagerEntityList.get(fieldTestPagerEntityList.size() - 1);
        assertThat(testFieldTestPagerEntity.getStringJade()).isEqualTo(DEFAULT_STRING_JADE);
        assertThat(testFieldTestPagerEntity.getStringRequiredJade()).isEqualTo(DEFAULT_STRING_REQUIRED_JADE);
        assertThat(testFieldTestPagerEntity.getStringMinlengthJade()).isEqualTo(DEFAULT_STRING_MINLENGTH_JADE);
        assertThat(testFieldTestPagerEntity.getStringMaxlengthJade()).isEqualTo(DEFAULT_STRING_MAXLENGTH_JADE);
        assertThat(testFieldTestPagerEntity.getStringPatternJade()).isEqualTo(DEFAULT_STRING_PATTERN_JADE);
        assertThat(testFieldTestPagerEntity.getIntegerJade()).isEqualTo(DEFAULT_INTEGER_JADE);
        assertThat(testFieldTestPagerEntity.getIntegerRequiredJade()).isEqualTo(DEFAULT_INTEGER_REQUIRED_JADE);
        assertThat(testFieldTestPagerEntity.getIntegerMinJade()).isEqualTo(DEFAULT_INTEGER_MIN_JADE);
        assertThat(testFieldTestPagerEntity.getIntegerMaxJade()).isEqualTo(DEFAULT_INTEGER_MAX_JADE);
        assertThat(testFieldTestPagerEntity.getLongJade()).isEqualTo(DEFAULT_LONG_JADE);
        assertThat(testFieldTestPagerEntity.getLongRequiredJade()).isEqualTo(DEFAULT_LONG_REQUIRED_JADE);
        assertThat(testFieldTestPagerEntity.getLongMinJade()).isEqualTo(DEFAULT_LONG_MIN_JADE);
        assertThat(testFieldTestPagerEntity.getLongMaxJade()).isEqualTo(DEFAULT_LONG_MAX_JADE);
        assertThat(testFieldTestPagerEntity.getFloatJade()).isEqualTo(DEFAULT_FLOAT_JADE);
        assertThat(testFieldTestPagerEntity.getFloatRequiredJade()).isEqualTo(DEFAULT_FLOAT_REQUIRED_JADE);
        assertThat(testFieldTestPagerEntity.getFloatMinJade()).isEqualTo(DEFAULT_FLOAT_MIN_JADE);
        assertThat(testFieldTestPagerEntity.getFloatMaxJade()).isEqualTo(DEFAULT_FLOAT_MAX_JADE);
        assertThat(testFieldTestPagerEntity.getDoubleRequiredJade()).isEqualTo(DEFAULT_DOUBLE_REQUIRED_JADE);
        assertThat(testFieldTestPagerEntity.getDoubleMinJade()).isEqualTo(DEFAULT_DOUBLE_MIN_JADE);
        assertThat(testFieldTestPagerEntity.getDoubleMaxJade()).isEqualTo(DEFAULT_DOUBLE_MAX_JADE);
        assertThat(testFieldTestPagerEntity.getBigDecimalRequiredJade()).isEqualTo(DEFAULT_BIG_DECIMAL_REQUIRED_JADE);
        assertThat(testFieldTestPagerEntity.getBigDecimalMinJade()).isEqualTo(DEFAULT_BIG_DECIMAL_MIN_JADE);
        assertThat(testFieldTestPagerEntity.getBigDecimalMaxJade()).isEqualTo(DEFAULT_BIG_DECIMAL_MAX_JADE);
        assertThat(testFieldTestPagerEntity.getLocalDateJade()).isEqualTo(DEFAULT_LOCAL_DATE_JADE);
        assertThat(testFieldTestPagerEntity.getLocalDateRequiredJade()).isEqualTo(DEFAULT_LOCAL_DATE_REQUIRED_JADE);
        assertThat(testFieldTestPagerEntity.getInstantJade()).isEqualTo(DEFAULT_INSTANT_JADE);
        assertThat(testFieldTestPagerEntity.getInstanteRequiredJade()).isEqualTo(DEFAULT_INSTANTE_REQUIRED_JADE);
        assertThat(testFieldTestPagerEntity.getZonedDateTimeJade()).isEqualTo(DEFAULT_ZONED_DATE_TIME_JADE);
        assertThat(testFieldTestPagerEntity.getZonedDateTimeRequiredJade()).isEqualTo(DEFAULT_ZONED_DATE_TIME_REQUIRED_JADE);
        assertThat(testFieldTestPagerEntity.isBooleanJade()).isEqualTo(DEFAULT_BOOLEAN_JADE);
        assertThat(testFieldTestPagerEntity.isBooleanRequiredJade()).isEqualTo(DEFAULT_BOOLEAN_REQUIRED_JADE);
        assertThat(testFieldTestPagerEntity.getEnumJade()).isEqualTo(DEFAULT_ENUM_JADE);
        assertThat(testFieldTestPagerEntity.getEnumRequiredJade()).isEqualTo(DEFAULT_ENUM_REQUIRED_JADE);
        assertThat(testFieldTestPagerEntity.getByteImageJade()).isEqualTo(DEFAULT_BYTE_IMAGE_JADE);
        assertThat(testFieldTestPagerEntity.getByteImageJadeContentType()).isEqualTo(DEFAULT_BYTE_IMAGE_JADE_CONTENT_TYPE);
        assertThat(testFieldTestPagerEntity.getByteImageRequiredJade()).isEqualTo(DEFAULT_BYTE_IMAGE_REQUIRED_JADE);
        assertThat(testFieldTestPagerEntity.getByteImageRequiredJadeContentType()).isEqualTo(DEFAULT_BYTE_IMAGE_REQUIRED_JADE_CONTENT_TYPE);
        assertThat(testFieldTestPagerEntity.getByteImageMinbytesJade()).isEqualTo(DEFAULT_BYTE_IMAGE_MINBYTES_JADE);
        assertThat(testFieldTestPagerEntity.getByteImageMinbytesJadeContentType()).isEqualTo(DEFAULT_BYTE_IMAGE_MINBYTES_JADE_CONTENT_TYPE);
        assertThat(testFieldTestPagerEntity.getByteImageMaxbytesJade()).isEqualTo(DEFAULT_BYTE_IMAGE_MAXBYTES_JADE);
        assertThat(testFieldTestPagerEntity.getByteImageMaxbytesJadeContentType()).isEqualTo(DEFAULT_BYTE_IMAGE_MAXBYTES_JADE_CONTENT_TYPE);
        assertThat(testFieldTestPagerEntity.getByteAnyJade()).isEqualTo(DEFAULT_BYTE_ANY_JADE);
        assertThat(testFieldTestPagerEntity.getByteAnyJadeContentType()).isEqualTo(DEFAULT_BYTE_ANY_JADE_CONTENT_TYPE);
        assertThat(testFieldTestPagerEntity.getByteAnyRequiredJade()).isEqualTo(DEFAULT_BYTE_ANY_REQUIRED_JADE);
        assertThat(testFieldTestPagerEntity.getByteAnyRequiredJadeContentType()).isEqualTo(DEFAULT_BYTE_ANY_REQUIRED_JADE_CONTENT_TYPE);
        assertThat(testFieldTestPagerEntity.getByteAnyMinbytesJade()).isEqualTo(DEFAULT_BYTE_ANY_MINBYTES_JADE);
        assertThat(testFieldTestPagerEntity.getByteAnyMinbytesJadeContentType()).isEqualTo(DEFAULT_BYTE_ANY_MINBYTES_JADE_CONTENT_TYPE);
        assertThat(testFieldTestPagerEntity.getByteAnyMaxbytesJade()).isEqualTo(DEFAULT_BYTE_ANY_MAXBYTES_JADE);
        assertThat(testFieldTestPagerEntity.getByteAnyMaxbytesJadeContentType()).isEqualTo(DEFAULT_BYTE_ANY_MAXBYTES_JADE_CONTENT_TYPE);
        assertThat(testFieldTestPagerEntity.getByteTextJade()).isEqualTo(DEFAULT_BYTE_TEXT_JADE);
        assertThat(testFieldTestPagerEntity.getByteTextRequiredJade()).isEqualTo(DEFAULT_BYTE_TEXT_REQUIRED_JADE);
        assertThat(testFieldTestPagerEntity.getByteTextMinbytesJade()).isEqualTo(DEFAULT_BYTE_TEXT_MINBYTES_JADE);
        assertThat(testFieldTestPagerEntity.getByteTextMaxbytesJade()).isEqualTo(DEFAULT_BYTE_TEXT_MAXBYTES_JADE);

        // Validate the FieldTestPagerEntity in Elasticsearch
        FieldTestPagerEntity fieldTestPagerEntityEs = fieldTestPagerEntitySearchRepository.findOne(testFieldTestPagerEntity.getId());
        assertThat(fieldTestPagerEntityEs).isEqualToComparingFieldByField(testFieldTestPagerEntity);
    }

    @Test
    @Transactional
    public void createFieldTestPagerEntityWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = fieldTestPagerEntityRepository.findAll().size();

        // Create the FieldTestPagerEntity with an existing ID
        fieldTestPagerEntity.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFieldTestPagerEntityMockMvc.perform(post("/api/field-test-pager-entities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fieldTestPagerEntity)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<FieldTestPagerEntity> fieldTestPagerEntityList = fieldTestPagerEntityRepository.findAll();
        assertThat(fieldTestPagerEntityList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkStringRequiredJadeIsRequired() throws Exception {
        int databaseSizeBeforeTest = fieldTestPagerEntityRepository.findAll().size();
        // set the field null
        fieldTestPagerEntity.setStringRequiredJade(null);

        // Create the FieldTestPagerEntity, which fails.

        restFieldTestPagerEntityMockMvc.perform(post("/api/field-test-pager-entities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fieldTestPagerEntity)))
            .andExpect(status().isBadRequest());

        List<FieldTestPagerEntity> fieldTestPagerEntityList = fieldTestPagerEntityRepository.findAll();
        assertThat(fieldTestPagerEntityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIntegerRequiredJadeIsRequired() throws Exception {
        int databaseSizeBeforeTest = fieldTestPagerEntityRepository.findAll().size();
        // set the field null
        fieldTestPagerEntity.setIntegerRequiredJade(null);

        // Create the FieldTestPagerEntity, which fails.

        restFieldTestPagerEntityMockMvc.perform(post("/api/field-test-pager-entities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fieldTestPagerEntity)))
            .andExpect(status().isBadRequest());

        List<FieldTestPagerEntity> fieldTestPagerEntityList = fieldTestPagerEntityRepository.findAll();
        assertThat(fieldTestPagerEntityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLongRequiredJadeIsRequired() throws Exception {
        int databaseSizeBeforeTest = fieldTestPagerEntityRepository.findAll().size();
        // set the field null
        fieldTestPagerEntity.setLongRequiredJade(null);

        // Create the FieldTestPagerEntity, which fails.

        restFieldTestPagerEntityMockMvc.perform(post("/api/field-test-pager-entities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fieldTestPagerEntity)))
            .andExpect(status().isBadRequest());

        List<FieldTestPagerEntity> fieldTestPagerEntityList = fieldTestPagerEntityRepository.findAll();
        assertThat(fieldTestPagerEntityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFloatRequiredJadeIsRequired() throws Exception {
        int databaseSizeBeforeTest = fieldTestPagerEntityRepository.findAll().size();
        // set the field null
        fieldTestPagerEntity.setFloatRequiredJade(null);

        // Create the FieldTestPagerEntity, which fails.

        restFieldTestPagerEntityMockMvc.perform(post("/api/field-test-pager-entities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fieldTestPagerEntity)))
            .andExpect(status().isBadRequest());

        List<FieldTestPagerEntity> fieldTestPagerEntityList = fieldTestPagerEntityRepository.findAll();
        assertThat(fieldTestPagerEntityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDoubleRequiredJadeIsRequired() throws Exception {
        int databaseSizeBeforeTest = fieldTestPagerEntityRepository.findAll().size();
        // set the field null
        fieldTestPagerEntity.setDoubleRequiredJade(null);

        // Create the FieldTestPagerEntity, which fails.

        restFieldTestPagerEntityMockMvc.perform(post("/api/field-test-pager-entities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fieldTestPagerEntity)))
            .andExpect(status().isBadRequest());

        List<FieldTestPagerEntity> fieldTestPagerEntityList = fieldTestPagerEntityRepository.findAll();
        assertThat(fieldTestPagerEntityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkBigDecimalRequiredJadeIsRequired() throws Exception {
        int databaseSizeBeforeTest = fieldTestPagerEntityRepository.findAll().size();
        // set the field null
        fieldTestPagerEntity.setBigDecimalRequiredJade(null);

        // Create the FieldTestPagerEntity, which fails.

        restFieldTestPagerEntityMockMvc.perform(post("/api/field-test-pager-entities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fieldTestPagerEntity)))
            .andExpect(status().isBadRequest());

        List<FieldTestPagerEntity> fieldTestPagerEntityList = fieldTestPagerEntityRepository.findAll();
        assertThat(fieldTestPagerEntityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLocalDateRequiredJadeIsRequired() throws Exception {
        int databaseSizeBeforeTest = fieldTestPagerEntityRepository.findAll().size();
        // set the field null
        fieldTestPagerEntity.setLocalDateRequiredJade(null);

        // Create the FieldTestPagerEntity, which fails.

        restFieldTestPagerEntityMockMvc.perform(post("/api/field-test-pager-entities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fieldTestPagerEntity)))
            .andExpect(status().isBadRequest());

        List<FieldTestPagerEntity> fieldTestPagerEntityList = fieldTestPagerEntityRepository.findAll();
        assertThat(fieldTestPagerEntityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkInstanteRequiredJadeIsRequired() throws Exception {
        int databaseSizeBeforeTest = fieldTestPagerEntityRepository.findAll().size();
        // set the field null
        fieldTestPagerEntity.setInstanteRequiredJade(null);

        // Create the FieldTestPagerEntity, which fails.

        restFieldTestPagerEntityMockMvc.perform(post("/api/field-test-pager-entities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fieldTestPagerEntity)))
            .andExpect(status().isBadRequest());

        List<FieldTestPagerEntity> fieldTestPagerEntityList = fieldTestPagerEntityRepository.findAll();
        assertThat(fieldTestPagerEntityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkZonedDateTimeRequiredJadeIsRequired() throws Exception {
        int databaseSizeBeforeTest = fieldTestPagerEntityRepository.findAll().size();
        // set the field null
        fieldTestPagerEntity.setZonedDateTimeRequiredJade(null);

        // Create the FieldTestPagerEntity, which fails.

        restFieldTestPagerEntityMockMvc.perform(post("/api/field-test-pager-entities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fieldTestPagerEntity)))
            .andExpect(status().isBadRequest());

        List<FieldTestPagerEntity> fieldTestPagerEntityList = fieldTestPagerEntityRepository.findAll();
        assertThat(fieldTestPagerEntityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkBooleanRequiredJadeIsRequired() throws Exception {
        int databaseSizeBeforeTest = fieldTestPagerEntityRepository.findAll().size();
        // set the field null
        fieldTestPagerEntity.setBooleanRequiredJade(null);

        // Create the FieldTestPagerEntity, which fails.

        restFieldTestPagerEntityMockMvc.perform(post("/api/field-test-pager-entities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fieldTestPagerEntity)))
            .andExpect(status().isBadRequest());

        List<FieldTestPagerEntity> fieldTestPagerEntityList = fieldTestPagerEntityRepository.findAll();
        assertThat(fieldTestPagerEntityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEnumRequiredJadeIsRequired() throws Exception {
        int databaseSizeBeforeTest = fieldTestPagerEntityRepository.findAll().size();
        // set the field null
        fieldTestPagerEntity.setEnumRequiredJade(null);

        // Create the FieldTestPagerEntity, which fails.

        restFieldTestPagerEntityMockMvc.perform(post("/api/field-test-pager-entities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fieldTestPagerEntity)))
            .andExpect(status().isBadRequest());

        List<FieldTestPagerEntity> fieldTestPagerEntityList = fieldTestPagerEntityRepository.findAll();
        assertThat(fieldTestPagerEntityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkByteImageRequiredJadeIsRequired() throws Exception {
        int databaseSizeBeforeTest = fieldTestPagerEntityRepository.findAll().size();
        // set the field null
        fieldTestPagerEntity.setByteImageRequiredJade(null);

        // Create the FieldTestPagerEntity, which fails.

        restFieldTestPagerEntityMockMvc.perform(post("/api/field-test-pager-entities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fieldTestPagerEntity)))
            .andExpect(status().isBadRequest());

        List<FieldTestPagerEntity> fieldTestPagerEntityList = fieldTestPagerEntityRepository.findAll();
        assertThat(fieldTestPagerEntityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkByteAnyRequiredJadeIsRequired() throws Exception {
        int databaseSizeBeforeTest = fieldTestPagerEntityRepository.findAll().size();
        // set the field null
        fieldTestPagerEntity.setByteAnyRequiredJade(null);

        // Create the FieldTestPagerEntity, which fails.

        restFieldTestPagerEntityMockMvc.perform(post("/api/field-test-pager-entities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fieldTestPagerEntity)))
            .andExpect(status().isBadRequest());

        List<FieldTestPagerEntity> fieldTestPagerEntityList = fieldTestPagerEntityRepository.findAll();
        assertThat(fieldTestPagerEntityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkByteTextRequiredJadeIsRequired() throws Exception {
        int databaseSizeBeforeTest = fieldTestPagerEntityRepository.findAll().size();
        // set the field null
        fieldTestPagerEntity.setByteTextRequiredJade(null);

        // Create the FieldTestPagerEntity, which fails.

        restFieldTestPagerEntityMockMvc.perform(post("/api/field-test-pager-entities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fieldTestPagerEntity)))
            .andExpect(status().isBadRequest());

        List<FieldTestPagerEntity> fieldTestPagerEntityList = fieldTestPagerEntityRepository.findAll();
        assertThat(fieldTestPagerEntityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllFieldTestPagerEntities() throws Exception {
        // Initialize the database
        fieldTestPagerEntityRepository.saveAndFlush(fieldTestPagerEntity);

        // Get all the fieldTestPagerEntityList
        restFieldTestPagerEntityMockMvc.perform(get("/api/field-test-pager-entities?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(fieldTestPagerEntity.getId().intValue())))
            .andExpect(jsonPath("$.[*].stringJade").value(hasItem(DEFAULT_STRING_JADE.toString())))
            .andExpect(jsonPath("$.[*].stringRequiredJade").value(hasItem(DEFAULT_STRING_REQUIRED_JADE.toString())))
            .andExpect(jsonPath("$.[*].stringMinlengthJade").value(hasItem(DEFAULT_STRING_MINLENGTH_JADE.toString())))
            .andExpect(jsonPath("$.[*].stringMaxlengthJade").value(hasItem(DEFAULT_STRING_MAXLENGTH_JADE.toString())))
            .andExpect(jsonPath("$.[*].stringPatternJade").value(hasItem(DEFAULT_STRING_PATTERN_JADE.toString())))
            .andExpect(jsonPath("$.[*].integerJade").value(hasItem(DEFAULT_INTEGER_JADE)))
            .andExpect(jsonPath("$.[*].integerRequiredJade").value(hasItem(DEFAULT_INTEGER_REQUIRED_JADE)))
            .andExpect(jsonPath("$.[*].integerMinJade").value(hasItem(DEFAULT_INTEGER_MIN_JADE)))
            .andExpect(jsonPath("$.[*].integerMaxJade").value(hasItem(DEFAULT_INTEGER_MAX_JADE)))
            .andExpect(jsonPath("$.[*].longJade").value(hasItem(DEFAULT_LONG_JADE.intValue())))
            .andExpect(jsonPath("$.[*].longRequiredJade").value(hasItem(DEFAULT_LONG_REQUIRED_JADE.intValue())))
            .andExpect(jsonPath("$.[*].longMinJade").value(hasItem(DEFAULT_LONG_MIN_JADE.intValue())))
            .andExpect(jsonPath("$.[*].longMaxJade").value(hasItem(DEFAULT_LONG_MAX_JADE.intValue())))
            .andExpect(jsonPath("$.[*].floatJade").value(hasItem(DEFAULT_FLOAT_JADE.doubleValue())))
            .andExpect(jsonPath("$.[*].floatRequiredJade").value(hasItem(DEFAULT_FLOAT_REQUIRED_JADE.doubleValue())))
            .andExpect(jsonPath("$.[*].floatMinJade").value(hasItem(DEFAULT_FLOAT_MIN_JADE.doubleValue())))
            .andExpect(jsonPath("$.[*].floatMaxJade").value(hasItem(DEFAULT_FLOAT_MAX_JADE.doubleValue())))
            .andExpect(jsonPath("$.[*].doubleRequiredJade").value(hasItem(DEFAULT_DOUBLE_REQUIRED_JADE.doubleValue())))
            .andExpect(jsonPath("$.[*].doubleMinJade").value(hasItem(DEFAULT_DOUBLE_MIN_JADE.doubleValue())))
            .andExpect(jsonPath("$.[*].doubleMaxJade").value(hasItem(DEFAULT_DOUBLE_MAX_JADE.doubleValue())))
            .andExpect(jsonPath("$.[*].bigDecimalRequiredJade").value(hasItem(DEFAULT_BIG_DECIMAL_REQUIRED_JADE.intValue())))
            .andExpect(jsonPath("$.[*].bigDecimalMinJade").value(hasItem(DEFAULT_BIG_DECIMAL_MIN_JADE.intValue())))
            .andExpect(jsonPath("$.[*].bigDecimalMaxJade").value(hasItem(DEFAULT_BIG_DECIMAL_MAX_JADE.intValue())))
            .andExpect(jsonPath("$.[*].localDateJade").value(hasItem(DEFAULT_LOCAL_DATE_JADE.toString())))
            .andExpect(jsonPath("$.[*].localDateRequiredJade").value(hasItem(DEFAULT_LOCAL_DATE_REQUIRED_JADE.toString())))
            .andExpect(jsonPath("$.[*].instantJade").value(hasItem(DEFAULT_INSTANT_JADE.toString())))
            .andExpect(jsonPath("$.[*].instanteRequiredJade").value(hasItem(DEFAULT_INSTANTE_REQUIRED_JADE.toString())))
            .andExpect(jsonPath("$.[*].zonedDateTimeJade").value(hasItem(sameInstant(DEFAULT_ZONED_DATE_TIME_JADE))))
            .andExpect(jsonPath("$.[*].zonedDateTimeRequiredJade").value(hasItem(sameInstant(DEFAULT_ZONED_DATE_TIME_REQUIRED_JADE))))
            .andExpect(jsonPath("$.[*].booleanJade").value(hasItem(DEFAULT_BOOLEAN_JADE.booleanValue())))
            .andExpect(jsonPath("$.[*].booleanRequiredJade").value(hasItem(DEFAULT_BOOLEAN_REQUIRED_JADE.booleanValue())))
            .andExpect(jsonPath("$.[*].enumJade").value(hasItem(DEFAULT_ENUM_JADE.toString())))
            .andExpect(jsonPath("$.[*].enumRequiredJade").value(hasItem(DEFAULT_ENUM_REQUIRED_JADE.toString())))
            .andExpect(jsonPath("$.[*].byteImageJadeContentType").value(hasItem(DEFAULT_BYTE_IMAGE_JADE_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].byteImageJade").value(hasItem(Base64Utils.encodeToString(DEFAULT_BYTE_IMAGE_JADE))))
            .andExpect(jsonPath("$.[*].byteImageRequiredJadeContentType").value(hasItem(DEFAULT_BYTE_IMAGE_REQUIRED_JADE_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].byteImageRequiredJade").value(hasItem(Base64Utils.encodeToString(DEFAULT_BYTE_IMAGE_REQUIRED_JADE))))
            .andExpect(jsonPath("$.[*].byteImageMinbytesJadeContentType").value(hasItem(DEFAULT_BYTE_IMAGE_MINBYTES_JADE_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].byteImageMinbytesJade").value(hasItem(Base64Utils.encodeToString(DEFAULT_BYTE_IMAGE_MINBYTES_JADE))))
            .andExpect(jsonPath("$.[*].byteImageMaxbytesJadeContentType").value(hasItem(DEFAULT_BYTE_IMAGE_MAXBYTES_JADE_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].byteImageMaxbytesJade").value(hasItem(Base64Utils.encodeToString(DEFAULT_BYTE_IMAGE_MAXBYTES_JADE))))
            .andExpect(jsonPath("$.[*].byteAnyJadeContentType").value(hasItem(DEFAULT_BYTE_ANY_JADE_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].byteAnyJade").value(hasItem(Base64Utils.encodeToString(DEFAULT_BYTE_ANY_JADE))))
            .andExpect(jsonPath("$.[*].byteAnyRequiredJadeContentType").value(hasItem(DEFAULT_BYTE_ANY_REQUIRED_JADE_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].byteAnyRequiredJade").value(hasItem(Base64Utils.encodeToString(DEFAULT_BYTE_ANY_REQUIRED_JADE))))
            .andExpect(jsonPath("$.[*].byteAnyMinbytesJadeContentType").value(hasItem(DEFAULT_BYTE_ANY_MINBYTES_JADE_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].byteAnyMinbytesJade").value(hasItem(Base64Utils.encodeToString(DEFAULT_BYTE_ANY_MINBYTES_JADE))))
            .andExpect(jsonPath("$.[*].byteAnyMaxbytesJadeContentType").value(hasItem(DEFAULT_BYTE_ANY_MAXBYTES_JADE_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].byteAnyMaxbytesJade").value(hasItem(Base64Utils.encodeToString(DEFAULT_BYTE_ANY_MAXBYTES_JADE))))
            .andExpect(jsonPath("$.[*].byteTextJade").value(hasItem(DEFAULT_BYTE_TEXT_JADE.toString())))
            .andExpect(jsonPath("$.[*].byteTextRequiredJade").value(hasItem(DEFAULT_BYTE_TEXT_REQUIRED_JADE.toString())))
            .andExpect(jsonPath("$.[*].byteTextMinbytesJade").value(hasItem(DEFAULT_BYTE_TEXT_MINBYTES_JADE.toString())))
            .andExpect(jsonPath("$.[*].byteTextMaxbytesJade").value(hasItem(DEFAULT_BYTE_TEXT_MAXBYTES_JADE.toString())));
    }

    @Test
    @Transactional
    public void getFieldTestPagerEntity() throws Exception {
        // Initialize the database
        fieldTestPagerEntityRepository.saveAndFlush(fieldTestPagerEntity);

        // Get the fieldTestPagerEntity
        restFieldTestPagerEntityMockMvc.perform(get("/api/field-test-pager-entities/{id}", fieldTestPagerEntity.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(fieldTestPagerEntity.getId().intValue()))
            .andExpect(jsonPath("$.stringJade").value(DEFAULT_STRING_JADE.toString()))
            .andExpect(jsonPath("$.stringRequiredJade").value(DEFAULT_STRING_REQUIRED_JADE.toString()))
            .andExpect(jsonPath("$.stringMinlengthJade").value(DEFAULT_STRING_MINLENGTH_JADE.toString()))
            .andExpect(jsonPath("$.stringMaxlengthJade").value(DEFAULT_STRING_MAXLENGTH_JADE.toString()))
            .andExpect(jsonPath("$.stringPatternJade").value(DEFAULT_STRING_PATTERN_JADE.toString()))
            .andExpect(jsonPath("$.integerJade").value(DEFAULT_INTEGER_JADE))
            .andExpect(jsonPath("$.integerRequiredJade").value(DEFAULT_INTEGER_REQUIRED_JADE))
            .andExpect(jsonPath("$.integerMinJade").value(DEFAULT_INTEGER_MIN_JADE))
            .andExpect(jsonPath("$.integerMaxJade").value(DEFAULT_INTEGER_MAX_JADE))
            .andExpect(jsonPath("$.longJade").value(DEFAULT_LONG_JADE.intValue()))
            .andExpect(jsonPath("$.longRequiredJade").value(DEFAULT_LONG_REQUIRED_JADE.intValue()))
            .andExpect(jsonPath("$.longMinJade").value(DEFAULT_LONG_MIN_JADE.intValue()))
            .andExpect(jsonPath("$.longMaxJade").value(DEFAULT_LONG_MAX_JADE.intValue()))
            .andExpect(jsonPath("$.floatJade").value(DEFAULT_FLOAT_JADE.doubleValue()))
            .andExpect(jsonPath("$.floatRequiredJade").value(DEFAULT_FLOAT_REQUIRED_JADE.doubleValue()))
            .andExpect(jsonPath("$.floatMinJade").value(DEFAULT_FLOAT_MIN_JADE.doubleValue()))
            .andExpect(jsonPath("$.floatMaxJade").value(DEFAULT_FLOAT_MAX_JADE.doubleValue()))
            .andExpect(jsonPath("$.doubleRequiredJade").value(DEFAULT_DOUBLE_REQUIRED_JADE.doubleValue()))
            .andExpect(jsonPath("$.doubleMinJade").value(DEFAULT_DOUBLE_MIN_JADE.doubleValue()))
            .andExpect(jsonPath("$.doubleMaxJade").value(DEFAULT_DOUBLE_MAX_JADE.doubleValue()))
            .andExpect(jsonPath("$.bigDecimalRequiredJade").value(DEFAULT_BIG_DECIMAL_REQUIRED_JADE.intValue()))
            .andExpect(jsonPath("$.bigDecimalMinJade").value(DEFAULT_BIG_DECIMAL_MIN_JADE.intValue()))
            .andExpect(jsonPath("$.bigDecimalMaxJade").value(DEFAULT_BIG_DECIMAL_MAX_JADE.intValue()))
            .andExpect(jsonPath("$.localDateJade").value(DEFAULT_LOCAL_DATE_JADE.toString()))
            .andExpect(jsonPath("$.localDateRequiredJade").value(DEFAULT_LOCAL_DATE_REQUIRED_JADE.toString()))
            .andExpect(jsonPath("$.instantJade").value(DEFAULT_INSTANT_JADE.toString()))
            .andExpect(jsonPath("$.instanteRequiredJade").value(DEFAULT_INSTANTE_REQUIRED_JADE.toString()))
            .andExpect(jsonPath("$.zonedDateTimeJade").value(sameInstant(DEFAULT_ZONED_DATE_TIME_JADE)))
            .andExpect(jsonPath("$.zonedDateTimeRequiredJade").value(sameInstant(DEFAULT_ZONED_DATE_TIME_REQUIRED_JADE)))
            .andExpect(jsonPath("$.booleanJade").value(DEFAULT_BOOLEAN_JADE.booleanValue()))
            .andExpect(jsonPath("$.booleanRequiredJade").value(DEFAULT_BOOLEAN_REQUIRED_JADE.booleanValue()))
            .andExpect(jsonPath("$.enumJade").value(DEFAULT_ENUM_JADE.toString()))
            .andExpect(jsonPath("$.enumRequiredJade").value(DEFAULT_ENUM_REQUIRED_JADE.toString()))
            .andExpect(jsonPath("$.byteImageJadeContentType").value(DEFAULT_BYTE_IMAGE_JADE_CONTENT_TYPE))
            .andExpect(jsonPath("$.byteImageJade").value(Base64Utils.encodeToString(DEFAULT_BYTE_IMAGE_JADE)))
            .andExpect(jsonPath("$.byteImageRequiredJadeContentType").value(DEFAULT_BYTE_IMAGE_REQUIRED_JADE_CONTENT_TYPE))
            .andExpect(jsonPath("$.byteImageRequiredJade").value(Base64Utils.encodeToString(DEFAULT_BYTE_IMAGE_REQUIRED_JADE)))
            .andExpect(jsonPath("$.byteImageMinbytesJadeContentType").value(DEFAULT_BYTE_IMAGE_MINBYTES_JADE_CONTENT_TYPE))
            .andExpect(jsonPath("$.byteImageMinbytesJade").value(Base64Utils.encodeToString(DEFAULT_BYTE_IMAGE_MINBYTES_JADE)))
            .andExpect(jsonPath("$.byteImageMaxbytesJadeContentType").value(DEFAULT_BYTE_IMAGE_MAXBYTES_JADE_CONTENT_TYPE))
            .andExpect(jsonPath("$.byteImageMaxbytesJade").value(Base64Utils.encodeToString(DEFAULT_BYTE_IMAGE_MAXBYTES_JADE)))
            .andExpect(jsonPath("$.byteAnyJadeContentType").value(DEFAULT_BYTE_ANY_JADE_CONTENT_TYPE))
            .andExpect(jsonPath("$.byteAnyJade").value(Base64Utils.encodeToString(DEFAULT_BYTE_ANY_JADE)))
            .andExpect(jsonPath("$.byteAnyRequiredJadeContentType").value(DEFAULT_BYTE_ANY_REQUIRED_JADE_CONTENT_TYPE))
            .andExpect(jsonPath("$.byteAnyRequiredJade").value(Base64Utils.encodeToString(DEFAULT_BYTE_ANY_REQUIRED_JADE)))
            .andExpect(jsonPath("$.byteAnyMinbytesJadeContentType").value(DEFAULT_BYTE_ANY_MINBYTES_JADE_CONTENT_TYPE))
            .andExpect(jsonPath("$.byteAnyMinbytesJade").value(Base64Utils.encodeToString(DEFAULT_BYTE_ANY_MINBYTES_JADE)))
            .andExpect(jsonPath("$.byteAnyMaxbytesJadeContentType").value(DEFAULT_BYTE_ANY_MAXBYTES_JADE_CONTENT_TYPE))
            .andExpect(jsonPath("$.byteAnyMaxbytesJade").value(Base64Utils.encodeToString(DEFAULT_BYTE_ANY_MAXBYTES_JADE)))
            .andExpect(jsonPath("$.byteTextJade").value(DEFAULT_BYTE_TEXT_JADE.toString()))
            .andExpect(jsonPath("$.byteTextRequiredJade").value(DEFAULT_BYTE_TEXT_REQUIRED_JADE.toString()))
            .andExpect(jsonPath("$.byteTextMinbytesJade").value(DEFAULT_BYTE_TEXT_MINBYTES_JADE.toString()))
            .andExpect(jsonPath("$.byteTextMaxbytesJade").value(DEFAULT_BYTE_TEXT_MAXBYTES_JADE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingFieldTestPagerEntity() throws Exception {
        // Get the fieldTestPagerEntity
        restFieldTestPagerEntityMockMvc.perform(get("/api/field-test-pager-entities/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFieldTestPagerEntity() throws Exception {
        // Initialize the database
        fieldTestPagerEntityRepository.saveAndFlush(fieldTestPagerEntity);
        fieldTestPagerEntitySearchRepository.save(fieldTestPagerEntity);
        int databaseSizeBeforeUpdate = fieldTestPagerEntityRepository.findAll().size();

        // Update the fieldTestPagerEntity
        FieldTestPagerEntity updatedFieldTestPagerEntity = fieldTestPagerEntityRepository.findOne(fieldTestPagerEntity.getId());
        updatedFieldTestPagerEntity
            .stringJade(UPDATED_STRING_JADE)
            .stringRequiredJade(UPDATED_STRING_REQUIRED_JADE)
            .stringMinlengthJade(UPDATED_STRING_MINLENGTH_JADE)
            .stringMaxlengthJade(UPDATED_STRING_MAXLENGTH_JADE)
            .stringPatternJade(UPDATED_STRING_PATTERN_JADE)
            .integerJade(UPDATED_INTEGER_JADE)
            .integerRequiredJade(UPDATED_INTEGER_REQUIRED_JADE)
            .integerMinJade(UPDATED_INTEGER_MIN_JADE)
            .integerMaxJade(UPDATED_INTEGER_MAX_JADE)
            .longJade(UPDATED_LONG_JADE)
            .longRequiredJade(UPDATED_LONG_REQUIRED_JADE)
            .longMinJade(UPDATED_LONG_MIN_JADE)
            .longMaxJade(UPDATED_LONG_MAX_JADE)
            .floatJade(UPDATED_FLOAT_JADE)
            .floatRequiredJade(UPDATED_FLOAT_REQUIRED_JADE)
            .floatMinJade(UPDATED_FLOAT_MIN_JADE)
            .floatMaxJade(UPDATED_FLOAT_MAX_JADE)
            .doubleRequiredJade(UPDATED_DOUBLE_REQUIRED_JADE)
            .doubleMinJade(UPDATED_DOUBLE_MIN_JADE)
            .doubleMaxJade(UPDATED_DOUBLE_MAX_JADE)
            .bigDecimalRequiredJade(UPDATED_BIG_DECIMAL_REQUIRED_JADE)
            .bigDecimalMinJade(UPDATED_BIG_DECIMAL_MIN_JADE)
            .bigDecimalMaxJade(UPDATED_BIG_DECIMAL_MAX_JADE)
            .localDateJade(UPDATED_LOCAL_DATE_JADE)
            .localDateRequiredJade(UPDATED_LOCAL_DATE_REQUIRED_JADE)
            .instantJade(UPDATED_INSTANT_JADE)
            .instanteRequiredJade(UPDATED_INSTANTE_REQUIRED_JADE)
            .zonedDateTimeJade(UPDATED_ZONED_DATE_TIME_JADE)
            .zonedDateTimeRequiredJade(UPDATED_ZONED_DATE_TIME_REQUIRED_JADE)
            .booleanJade(UPDATED_BOOLEAN_JADE)
            .booleanRequiredJade(UPDATED_BOOLEAN_REQUIRED_JADE)
            .enumJade(UPDATED_ENUM_JADE)
            .enumRequiredJade(UPDATED_ENUM_REQUIRED_JADE)
            .byteImageJade(UPDATED_BYTE_IMAGE_JADE)
            .byteImageJadeContentType(UPDATED_BYTE_IMAGE_JADE_CONTENT_TYPE)
            .byteImageRequiredJade(UPDATED_BYTE_IMAGE_REQUIRED_JADE)
            .byteImageRequiredJadeContentType(UPDATED_BYTE_IMAGE_REQUIRED_JADE_CONTENT_TYPE)
            .byteImageMinbytesJade(UPDATED_BYTE_IMAGE_MINBYTES_JADE)
            .byteImageMinbytesJadeContentType(UPDATED_BYTE_IMAGE_MINBYTES_JADE_CONTENT_TYPE)
            .byteImageMaxbytesJade(UPDATED_BYTE_IMAGE_MAXBYTES_JADE)
            .byteImageMaxbytesJadeContentType(UPDATED_BYTE_IMAGE_MAXBYTES_JADE_CONTENT_TYPE)
            .byteAnyJade(UPDATED_BYTE_ANY_JADE)
            .byteAnyJadeContentType(UPDATED_BYTE_ANY_JADE_CONTENT_TYPE)
            .byteAnyRequiredJade(UPDATED_BYTE_ANY_REQUIRED_JADE)
            .byteAnyRequiredJadeContentType(UPDATED_BYTE_ANY_REQUIRED_JADE_CONTENT_TYPE)
            .byteAnyMinbytesJade(UPDATED_BYTE_ANY_MINBYTES_JADE)
            .byteAnyMinbytesJadeContentType(UPDATED_BYTE_ANY_MINBYTES_JADE_CONTENT_TYPE)
            .byteAnyMaxbytesJade(UPDATED_BYTE_ANY_MAXBYTES_JADE)
            .byteAnyMaxbytesJadeContentType(UPDATED_BYTE_ANY_MAXBYTES_JADE_CONTENT_TYPE)
            .byteTextJade(UPDATED_BYTE_TEXT_JADE)
            .byteTextRequiredJade(UPDATED_BYTE_TEXT_REQUIRED_JADE)
            .byteTextMinbytesJade(UPDATED_BYTE_TEXT_MINBYTES_JADE)
            .byteTextMaxbytesJade(UPDATED_BYTE_TEXT_MAXBYTES_JADE);

        restFieldTestPagerEntityMockMvc.perform(put("/api/field-test-pager-entities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedFieldTestPagerEntity)))
            .andExpect(status().isOk());

        // Validate the FieldTestPagerEntity in the database
        List<FieldTestPagerEntity> fieldTestPagerEntityList = fieldTestPagerEntityRepository.findAll();
        assertThat(fieldTestPagerEntityList).hasSize(databaseSizeBeforeUpdate);
        FieldTestPagerEntity testFieldTestPagerEntity = fieldTestPagerEntityList.get(fieldTestPagerEntityList.size() - 1);
        assertThat(testFieldTestPagerEntity.getStringJade()).isEqualTo(UPDATED_STRING_JADE);
        assertThat(testFieldTestPagerEntity.getStringRequiredJade()).isEqualTo(UPDATED_STRING_REQUIRED_JADE);
        assertThat(testFieldTestPagerEntity.getStringMinlengthJade()).isEqualTo(UPDATED_STRING_MINLENGTH_JADE);
        assertThat(testFieldTestPagerEntity.getStringMaxlengthJade()).isEqualTo(UPDATED_STRING_MAXLENGTH_JADE);
        assertThat(testFieldTestPagerEntity.getStringPatternJade()).isEqualTo(UPDATED_STRING_PATTERN_JADE);
        assertThat(testFieldTestPagerEntity.getIntegerJade()).isEqualTo(UPDATED_INTEGER_JADE);
        assertThat(testFieldTestPagerEntity.getIntegerRequiredJade()).isEqualTo(UPDATED_INTEGER_REQUIRED_JADE);
        assertThat(testFieldTestPagerEntity.getIntegerMinJade()).isEqualTo(UPDATED_INTEGER_MIN_JADE);
        assertThat(testFieldTestPagerEntity.getIntegerMaxJade()).isEqualTo(UPDATED_INTEGER_MAX_JADE);
        assertThat(testFieldTestPagerEntity.getLongJade()).isEqualTo(UPDATED_LONG_JADE);
        assertThat(testFieldTestPagerEntity.getLongRequiredJade()).isEqualTo(UPDATED_LONG_REQUIRED_JADE);
        assertThat(testFieldTestPagerEntity.getLongMinJade()).isEqualTo(UPDATED_LONG_MIN_JADE);
        assertThat(testFieldTestPagerEntity.getLongMaxJade()).isEqualTo(UPDATED_LONG_MAX_JADE);
        assertThat(testFieldTestPagerEntity.getFloatJade()).isEqualTo(UPDATED_FLOAT_JADE);
        assertThat(testFieldTestPagerEntity.getFloatRequiredJade()).isEqualTo(UPDATED_FLOAT_REQUIRED_JADE);
        assertThat(testFieldTestPagerEntity.getFloatMinJade()).isEqualTo(UPDATED_FLOAT_MIN_JADE);
        assertThat(testFieldTestPagerEntity.getFloatMaxJade()).isEqualTo(UPDATED_FLOAT_MAX_JADE);
        assertThat(testFieldTestPagerEntity.getDoubleRequiredJade()).isEqualTo(UPDATED_DOUBLE_REQUIRED_JADE);
        assertThat(testFieldTestPagerEntity.getDoubleMinJade()).isEqualTo(UPDATED_DOUBLE_MIN_JADE);
        assertThat(testFieldTestPagerEntity.getDoubleMaxJade()).isEqualTo(UPDATED_DOUBLE_MAX_JADE);
        assertThat(testFieldTestPagerEntity.getBigDecimalRequiredJade()).isEqualTo(UPDATED_BIG_DECIMAL_REQUIRED_JADE);
        assertThat(testFieldTestPagerEntity.getBigDecimalMinJade()).isEqualTo(UPDATED_BIG_DECIMAL_MIN_JADE);
        assertThat(testFieldTestPagerEntity.getBigDecimalMaxJade()).isEqualTo(UPDATED_BIG_DECIMAL_MAX_JADE);
        assertThat(testFieldTestPagerEntity.getLocalDateJade()).isEqualTo(UPDATED_LOCAL_DATE_JADE);
        assertThat(testFieldTestPagerEntity.getLocalDateRequiredJade()).isEqualTo(UPDATED_LOCAL_DATE_REQUIRED_JADE);
        assertThat(testFieldTestPagerEntity.getInstantJade()).isEqualTo(UPDATED_INSTANT_JADE);
        assertThat(testFieldTestPagerEntity.getInstanteRequiredJade()).isEqualTo(UPDATED_INSTANTE_REQUIRED_JADE);
        assertThat(testFieldTestPagerEntity.getZonedDateTimeJade()).isEqualTo(UPDATED_ZONED_DATE_TIME_JADE);
        assertThat(testFieldTestPagerEntity.getZonedDateTimeRequiredJade()).isEqualTo(UPDATED_ZONED_DATE_TIME_REQUIRED_JADE);
        assertThat(testFieldTestPagerEntity.isBooleanJade()).isEqualTo(UPDATED_BOOLEAN_JADE);
        assertThat(testFieldTestPagerEntity.isBooleanRequiredJade()).isEqualTo(UPDATED_BOOLEAN_REQUIRED_JADE);
        assertThat(testFieldTestPagerEntity.getEnumJade()).isEqualTo(UPDATED_ENUM_JADE);
        assertThat(testFieldTestPagerEntity.getEnumRequiredJade()).isEqualTo(UPDATED_ENUM_REQUIRED_JADE);
        assertThat(testFieldTestPagerEntity.getByteImageJade()).isEqualTo(UPDATED_BYTE_IMAGE_JADE);
        assertThat(testFieldTestPagerEntity.getByteImageJadeContentType()).isEqualTo(UPDATED_BYTE_IMAGE_JADE_CONTENT_TYPE);
        assertThat(testFieldTestPagerEntity.getByteImageRequiredJade()).isEqualTo(UPDATED_BYTE_IMAGE_REQUIRED_JADE);
        assertThat(testFieldTestPagerEntity.getByteImageRequiredJadeContentType()).isEqualTo(UPDATED_BYTE_IMAGE_REQUIRED_JADE_CONTENT_TYPE);
        assertThat(testFieldTestPagerEntity.getByteImageMinbytesJade()).isEqualTo(UPDATED_BYTE_IMAGE_MINBYTES_JADE);
        assertThat(testFieldTestPagerEntity.getByteImageMinbytesJadeContentType()).isEqualTo(UPDATED_BYTE_IMAGE_MINBYTES_JADE_CONTENT_TYPE);
        assertThat(testFieldTestPagerEntity.getByteImageMaxbytesJade()).isEqualTo(UPDATED_BYTE_IMAGE_MAXBYTES_JADE);
        assertThat(testFieldTestPagerEntity.getByteImageMaxbytesJadeContentType()).isEqualTo(UPDATED_BYTE_IMAGE_MAXBYTES_JADE_CONTENT_TYPE);
        assertThat(testFieldTestPagerEntity.getByteAnyJade()).isEqualTo(UPDATED_BYTE_ANY_JADE);
        assertThat(testFieldTestPagerEntity.getByteAnyJadeContentType()).isEqualTo(UPDATED_BYTE_ANY_JADE_CONTENT_TYPE);
        assertThat(testFieldTestPagerEntity.getByteAnyRequiredJade()).isEqualTo(UPDATED_BYTE_ANY_REQUIRED_JADE);
        assertThat(testFieldTestPagerEntity.getByteAnyRequiredJadeContentType()).isEqualTo(UPDATED_BYTE_ANY_REQUIRED_JADE_CONTENT_TYPE);
        assertThat(testFieldTestPagerEntity.getByteAnyMinbytesJade()).isEqualTo(UPDATED_BYTE_ANY_MINBYTES_JADE);
        assertThat(testFieldTestPagerEntity.getByteAnyMinbytesJadeContentType()).isEqualTo(UPDATED_BYTE_ANY_MINBYTES_JADE_CONTENT_TYPE);
        assertThat(testFieldTestPagerEntity.getByteAnyMaxbytesJade()).isEqualTo(UPDATED_BYTE_ANY_MAXBYTES_JADE);
        assertThat(testFieldTestPagerEntity.getByteAnyMaxbytesJadeContentType()).isEqualTo(UPDATED_BYTE_ANY_MAXBYTES_JADE_CONTENT_TYPE);
        assertThat(testFieldTestPagerEntity.getByteTextJade()).isEqualTo(UPDATED_BYTE_TEXT_JADE);
        assertThat(testFieldTestPagerEntity.getByteTextRequiredJade()).isEqualTo(UPDATED_BYTE_TEXT_REQUIRED_JADE);
        assertThat(testFieldTestPagerEntity.getByteTextMinbytesJade()).isEqualTo(UPDATED_BYTE_TEXT_MINBYTES_JADE);
        assertThat(testFieldTestPagerEntity.getByteTextMaxbytesJade()).isEqualTo(UPDATED_BYTE_TEXT_MAXBYTES_JADE);

        // Validate the FieldTestPagerEntity in Elasticsearch
        FieldTestPagerEntity fieldTestPagerEntityEs = fieldTestPagerEntitySearchRepository.findOne(testFieldTestPagerEntity.getId());
        assertThat(fieldTestPagerEntityEs).isEqualToComparingFieldByField(testFieldTestPagerEntity);
    }

    @Test
    @Transactional
    public void updateNonExistingFieldTestPagerEntity() throws Exception {
        int databaseSizeBeforeUpdate = fieldTestPagerEntityRepository.findAll().size();

        // Create the FieldTestPagerEntity

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restFieldTestPagerEntityMockMvc.perform(put("/api/field-test-pager-entities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fieldTestPagerEntity)))
            .andExpect(status().isCreated());

        // Validate the FieldTestPagerEntity in the database
        List<FieldTestPagerEntity> fieldTestPagerEntityList = fieldTestPagerEntityRepository.findAll();
        assertThat(fieldTestPagerEntityList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteFieldTestPagerEntity() throws Exception {
        // Initialize the database
        fieldTestPagerEntityRepository.saveAndFlush(fieldTestPagerEntity);
        fieldTestPagerEntitySearchRepository.save(fieldTestPagerEntity);
        int databaseSizeBeforeDelete = fieldTestPagerEntityRepository.findAll().size();

        // Get the fieldTestPagerEntity
        restFieldTestPagerEntityMockMvc.perform(delete("/api/field-test-pager-entities/{id}", fieldTestPagerEntity.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate Elasticsearch is empty
        boolean fieldTestPagerEntityExistsInEs = fieldTestPagerEntitySearchRepository.exists(fieldTestPagerEntity.getId());
        assertThat(fieldTestPagerEntityExistsInEs).isFalse();

        // Validate the database is empty
        List<FieldTestPagerEntity> fieldTestPagerEntityList = fieldTestPagerEntityRepository.findAll();
        assertThat(fieldTestPagerEntityList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void searchFieldTestPagerEntity() throws Exception {
        // Initialize the database
        fieldTestPagerEntityRepository.saveAndFlush(fieldTestPagerEntity);
        fieldTestPagerEntitySearchRepository.save(fieldTestPagerEntity);

        // Search the fieldTestPagerEntity
        restFieldTestPagerEntityMockMvc.perform(get("/api/_search/field-test-pager-entities?query=id:" + fieldTestPagerEntity.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(fieldTestPagerEntity.getId().intValue())))
            .andExpect(jsonPath("$.[*].stringJade").value(hasItem(DEFAULT_STRING_JADE.toString())))
            .andExpect(jsonPath("$.[*].stringRequiredJade").value(hasItem(DEFAULT_STRING_REQUIRED_JADE.toString())))
            .andExpect(jsonPath("$.[*].stringMinlengthJade").value(hasItem(DEFAULT_STRING_MINLENGTH_JADE.toString())))
            .andExpect(jsonPath("$.[*].stringMaxlengthJade").value(hasItem(DEFAULT_STRING_MAXLENGTH_JADE.toString())))
            .andExpect(jsonPath("$.[*].stringPatternJade").value(hasItem(DEFAULT_STRING_PATTERN_JADE.toString())))
            .andExpect(jsonPath("$.[*].integerJade").value(hasItem(DEFAULT_INTEGER_JADE)))
            .andExpect(jsonPath("$.[*].integerRequiredJade").value(hasItem(DEFAULT_INTEGER_REQUIRED_JADE)))
            .andExpect(jsonPath("$.[*].integerMinJade").value(hasItem(DEFAULT_INTEGER_MIN_JADE)))
            .andExpect(jsonPath("$.[*].integerMaxJade").value(hasItem(DEFAULT_INTEGER_MAX_JADE)))
            .andExpect(jsonPath("$.[*].longJade").value(hasItem(DEFAULT_LONG_JADE.intValue())))
            .andExpect(jsonPath("$.[*].longRequiredJade").value(hasItem(DEFAULT_LONG_REQUIRED_JADE.intValue())))
            .andExpect(jsonPath("$.[*].longMinJade").value(hasItem(DEFAULT_LONG_MIN_JADE.intValue())))
            .andExpect(jsonPath("$.[*].longMaxJade").value(hasItem(DEFAULT_LONG_MAX_JADE.intValue())))
            .andExpect(jsonPath("$.[*].floatJade").value(hasItem(DEFAULT_FLOAT_JADE.doubleValue())))
            .andExpect(jsonPath("$.[*].floatRequiredJade").value(hasItem(DEFAULT_FLOAT_REQUIRED_JADE.doubleValue())))
            .andExpect(jsonPath("$.[*].floatMinJade").value(hasItem(DEFAULT_FLOAT_MIN_JADE.doubleValue())))
            .andExpect(jsonPath("$.[*].floatMaxJade").value(hasItem(DEFAULT_FLOAT_MAX_JADE.doubleValue())))
            .andExpect(jsonPath("$.[*].doubleRequiredJade").value(hasItem(DEFAULT_DOUBLE_REQUIRED_JADE.doubleValue())))
            .andExpect(jsonPath("$.[*].doubleMinJade").value(hasItem(DEFAULT_DOUBLE_MIN_JADE.doubleValue())))
            .andExpect(jsonPath("$.[*].doubleMaxJade").value(hasItem(DEFAULT_DOUBLE_MAX_JADE.doubleValue())))
            .andExpect(jsonPath("$.[*].bigDecimalRequiredJade").value(hasItem(DEFAULT_BIG_DECIMAL_REQUIRED_JADE.intValue())))
            .andExpect(jsonPath("$.[*].bigDecimalMinJade").value(hasItem(DEFAULT_BIG_DECIMAL_MIN_JADE.intValue())))
            .andExpect(jsonPath("$.[*].bigDecimalMaxJade").value(hasItem(DEFAULT_BIG_DECIMAL_MAX_JADE.intValue())))
            .andExpect(jsonPath("$.[*].localDateJade").value(hasItem(DEFAULT_LOCAL_DATE_JADE.toString())))
            .andExpect(jsonPath("$.[*].localDateRequiredJade").value(hasItem(DEFAULT_LOCAL_DATE_REQUIRED_JADE.toString())))
            .andExpect(jsonPath("$.[*].instantJade").value(hasItem(DEFAULT_INSTANT_JADE.toString())))
            .andExpect(jsonPath("$.[*].instanteRequiredJade").value(hasItem(DEFAULT_INSTANTE_REQUIRED_JADE.toString())))
            .andExpect(jsonPath("$.[*].zonedDateTimeJade").value(hasItem(sameInstant(DEFAULT_ZONED_DATE_TIME_JADE))))
            .andExpect(jsonPath("$.[*].zonedDateTimeRequiredJade").value(hasItem(sameInstant(DEFAULT_ZONED_DATE_TIME_REQUIRED_JADE))))
            .andExpect(jsonPath("$.[*].booleanJade").value(hasItem(DEFAULT_BOOLEAN_JADE.booleanValue())))
            .andExpect(jsonPath("$.[*].booleanRequiredJade").value(hasItem(DEFAULT_BOOLEAN_REQUIRED_JADE.booleanValue())))
            .andExpect(jsonPath("$.[*].enumJade").value(hasItem(DEFAULT_ENUM_JADE.toString())))
            .andExpect(jsonPath("$.[*].enumRequiredJade").value(hasItem(DEFAULT_ENUM_REQUIRED_JADE.toString())))
            .andExpect(jsonPath("$.[*].byteImageJadeContentType").value(hasItem(DEFAULT_BYTE_IMAGE_JADE_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].byteImageJade").value(hasItem(Base64Utils.encodeToString(DEFAULT_BYTE_IMAGE_JADE))))
            .andExpect(jsonPath("$.[*].byteImageRequiredJadeContentType").value(hasItem(DEFAULT_BYTE_IMAGE_REQUIRED_JADE_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].byteImageRequiredJade").value(hasItem(Base64Utils.encodeToString(DEFAULT_BYTE_IMAGE_REQUIRED_JADE))))
            .andExpect(jsonPath("$.[*].byteImageMinbytesJadeContentType").value(hasItem(DEFAULT_BYTE_IMAGE_MINBYTES_JADE_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].byteImageMinbytesJade").value(hasItem(Base64Utils.encodeToString(DEFAULT_BYTE_IMAGE_MINBYTES_JADE))))
            .andExpect(jsonPath("$.[*].byteImageMaxbytesJadeContentType").value(hasItem(DEFAULT_BYTE_IMAGE_MAXBYTES_JADE_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].byteImageMaxbytesJade").value(hasItem(Base64Utils.encodeToString(DEFAULT_BYTE_IMAGE_MAXBYTES_JADE))))
            .andExpect(jsonPath("$.[*].byteAnyJadeContentType").value(hasItem(DEFAULT_BYTE_ANY_JADE_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].byteAnyJade").value(hasItem(Base64Utils.encodeToString(DEFAULT_BYTE_ANY_JADE))))
            .andExpect(jsonPath("$.[*].byteAnyRequiredJadeContentType").value(hasItem(DEFAULT_BYTE_ANY_REQUIRED_JADE_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].byteAnyRequiredJade").value(hasItem(Base64Utils.encodeToString(DEFAULT_BYTE_ANY_REQUIRED_JADE))))
            .andExpect(jsonPath("$.[*].byteAnyMinbytesJadeContentType").value(hasItem(DEFAULT_BYTE_ANY_MINBYTES_JADE_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].byteAnyMinbytesJade").value(hasItem(Base64Utils.encodeToString(DEFAULT_BYTE_ANY_MINBYTES_JADE))))
            .andExpect(jsonPath("$.[*].byteAnyMaxbytesJadeContentType").value(hasItem(DEFAULT_BYTE_ANY_MAXBYTES_JADE_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].byteAnyMaxbytesJade").value(hasItem(Base64Utils.encodeToString(DEFAULT_BYTE_ANY_MAXBYTES_JADE))))
            .andExpect(jsonPath("$.[*].byteTextJade").value(hasItem(DEFAULT_BYTE_TEXT_JADE.toString())))
            .andExpect(jsonPath("$.[*].byteTextRequiredJade").value(hasItem(DEFAULT_BYTE_TEXT_REQUIRED_JADE.toString())))
            .andExpect(jsonPath("$.[*].byteTextMinbytesJade").value(hasItem(DEFAULT_BYTE_TEXT_MINBYTES_JADE.toString())))
            .andExpect(jsonPath("$.[*].byteTextMaxbytesJade").value(hasItem(DEFAULT_BYTE_TEXT_MAXBYTES_JADE.toString())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(FieldTestPagerEntity.class);
        FieldTestPagerEntity fieldTestPagerEntity1 = new FieldTestPagerEntity();
        fieldTestPagerEntity1.setId(1L);
        FieldTestPagerEntity fieldTestPagerEntity2 = new FieldTestPagerEntity();
        fieldTestPagerEntity2.setId(fieldTestPagerEntity1.getId());
        assertThat(fieldTestPagerEntity1).isEqualTo(fieldTestPagerEntity2);
        fieldTestPagerEntity2.setId(2L);
        assertThat(fieldTestPagerEntity1).isNotEqualTo(fieldTestPagerEntity2);
        fieldTestPagerEntity1.setId(null);
        assertThat(fieldTestPagerEntity1).isNotEqualTo(fieldTestPagerEntity2);
    }
}
