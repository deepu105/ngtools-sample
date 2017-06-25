package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.Jh4TestApp;

import com.mycompany.myapp.domain.FieldTestInfiniteScrollEntity;
import com.mycompany.myapp.repository.FieldTestInfiniteScrollEntityRepository;
import com.mycompany.myapp.repository.search.FieldTestInfiniteScrollEntitySearchRepository;
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
 * Test class for the FieldTestInfiniteScrollEntityResource REST controller.
 *
 * @see FieldTestInfiniteScrollEntityResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Jh4TestApp.class)
public class FieldTestInfiniteScrollEntityResourceIntTest {

    private static final String DEFAULT_STRING_HUGO = "AAAAAAAAAA";
    private static final String UPDATED_STRING_HUGO = "BBBBBBBBBB";

    private static final String DEFAULT_STRING_REQUIRED_HUGO = "AAAAAAAAAA";
    private static final String UPDATED_STRING_REQUIRED_HUGO = "BBBBBBBBBB";

    private static final String DEFAULT_STRING_MINLENGTH_HUGO = "AAAAAAAAAA";
    private static final String UPDATED_STRING_MINLENGTH_HUGO = "BBBBBBBBBB";

    private static final String DEFAULT_STRING_MAXLENGTH_HUGO = "AAAAAAAAAA";
    private static final String UPDATED_STRING_MAXLENGTH_HUGO = "BBBBBBBBBB";

    private static final String DEFAULT_STRING_PATTERN_HUGO = "AAAAAAAAAA";
    private static final String UPDATED_STRING_PATTERN_HUGO = "BBBBBBBBBB";

    private static final Integer DEFAULT_INTEGER_HUGO = 1;
    private static final Integer UPDATED_INTEGER_HUGO = 2;

    private static final Integer DEFAULT_INTEGER_REQUIRED_HUGO = 1;
    private static final Integer UPDATED_INTEGER_REQUIRED_HUGO = 2;

    private static final Integer DEFAULT_INTEGER_MIN_HUGO = 0;
    private static final Integer UPDATED_INTEGER_MIN_HUGO = 1;

    private static final Integer DEFAULT_INTEGER_MAX_HUGO = 100;
    private static final Integer UPDATED_INTEGER_MAX_HUGO = 99;

    private static final Long DEFAULT_LONG_HUGO = 1L;
    private static final Long UPDATED_LONG_HUGO = 2L;

    private static final Long DEFAULT_LONG_REQUIRED_HUGO = 1L;
    private static final Long UPDATED_LONG_REQUIRED_HUGO = 2L;

    private static final Long DEFAULT_LONG_MIN_HUGO = 0L;
    private static final Long UPDATED_LONG_MIN_HUGO = 1L;

    private static final Long DEFAULT_LONG_MAX_HUGO = 100L;
    private static final Long UPDATED_LONG_MAX_HUGO = 99L;

    private static final Float DEFAULT_FLOAT_HUGO = 1F;
    private static final Float UPDATED_FLOAT_HUGO = 2F;

    private static final Float DEFAULT_FLOAT_REQUIRED_HUGO = 1F;
    private static final Float UPDATED_FLOAT_REQUIRED_HUGO = 2F;

    private static final Float DEFAULT_FLOAT_MIN_HUGO = 0F;
    private static final Float UPDATED_FLOAT_MIN_HUGO = 1F;

    private static final Float DEFAULT_FLOAT_MAX_HUGO = 100F;
    private static final Float UPDATED_FLOAT_MAX_HUGO = 99F;

    private static final Double DEFAULT_DOUBLE_REQUIRED_HUGO = 1D;
    private static final Double UPDATED_DOUBLE_REQUIRED_HUGO = 2D;

    private static final Double DEFAULT_DOUBLE_MIN_HUGO = 0D;
    private static final Double UPDATED_DOUBLE_MIN_HUGO = 1D;

    private static final Double DEFAULT_DOUBLE_MAX_HUGO = 100D;
    private static final Double UPDATED_DOUBLE_MAX_HUGO = 99D;

    private static final BigDecimal DEFAULT_BIG_DECIMAL_REQUIRED_HUGO = new BigDecimal(1);
    private static final BigDecimal UPDATED_BIG_DECIMAL_REQUIRED_HUGO = new BigDecimal(2);

    private static final BigDecimal DEFAULT_BIG_DECIMAL_MIN_HUGO = new BigDecimal(0);
    private static final BigDecimal UPDATED_BIG_DECIMAL_MIN_HUGO = new BigDecimal(1);

    private static final BigDecimal DEFAULT_BIG_DECIMAL_MAX_HUGO = new BigDecimal(100);
    private static final BigDecimal UPDATED_BIG_DECIMAL_MAX_HUGO = new BigDecimal(99);

    private static final LocalDate DEFAULT_LOCAL_DATE_HUGO = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_LOCAL_DATE_HUGO = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_LOCAL_DATE_REQUIRED_HUGO = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_LOCAL_DATE_REQUIRED_HUGO = LocalDate.now(ZoneId.systemDefault());

    private static final Instant DEFAULT_INSTANT_HUGO = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_INSTANT_HUGO = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_INSTANTE_REQUIRED_HUGO = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_INSTANTE_REQUIRED_HUGO = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final ZonedDateTime DEFAULT_ZONED_DATE_TIME_HUGO = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_ZONED_DATE_TIME_HUGO = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_ZONED_DATE_TIME_REQUIRED_HUGO = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_ZONED_DATE_TIME_REQUIRED_HUGO = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final Boolean DEFAULT_BOOLEAN_HUGO = false;
    private static final Boolean UPDATED_BOOLEAN_HUGO = true;

    private static final Boolean DEFAULT_BOOLEAN_REQUIRED_HUGO = false;
    private static final Boolean UPDATED_BOOLEAN_REQUIRED_HUGO = true;

    private static final EnumFieldClass DEFAULT_ENUM_HUGO = EnumFieldClass.ENUM_VALUE_1;
    private static final EnumFieldClass UPDATED_ENUM_HUGO = EnumFieldClass.ENUM_VALUE_2;

    private static final EnumRequiredFieldClass DEFAULT_ENUM_REQUIRED_HUGO = EnumRequiredFieldClass.ENUM_VALUE_1;
    private static final EnumRequiredFieldClass UPDATED_ENUM_REQUIRED_HUGO = EnumRequiredFieldClass.ENUM_VALUE_2;

    private static final byte[] DEFAULT_BYTE_IMAGE_HUGO = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_BYTE_IMAGE_HUGO = TestUtil.createByteArray(2, "1");
    private static final String DEFAULT_BYTE_IMAGE_HUGO_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_BYTE_IMAGE_HUGO_CONTENT_TYPE = "image/png";

    private static final byte[] DEFAULT_BYTE_IMAGE_REQUIRED_HUGO = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_BYTE_IMAGE_REQUIRED_HUGO = TestUtil.createByteArray(2, "1");
    private static final String DEFAULT_BYTE_IMAGE_REQUIRED_HUGO_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_BYTE_IMAGE_REQUIRED_HUGO_CONTENT_TYPE = "image/png";

    private static final byte[] DEFAULT_BYTE_IMAGE_MINBYTES_HUGO = TestUtil.createByteArray(0, "0");
    private static final byte[] UPDATED_BYTE_IMAGE_MINBYTES_HUGO = TestUtil.createByteArray(0, "1");
    private static final String DEFAULT_BYTE_IMAGE_MINBYTES_HUGO_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_BYTE_IMAGE_MINBYTES_HUGO_CONTENT_TYPE = "image/png";

    private static final byte[] DEFAULT_BYTE_IMAGE_MAXBYTES_HUGO = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_BYTE_IMAGE_MAXBYTES_HUGO = TestUtil.createByteArray(10000, "1");
    private static final String DEFAULT_BYTE_IMAGE_MAXBYTES_HUGO_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_BYTE_IMAGE_MAXBYTES_HUGO_CONTENT_TYPE = "image/png";

    private static final byte[] DEFAULT_BYTE_ANY_HUGO = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_BYTE_ANY_HUGO = TestUtil.createByteArray(2, "1");
    private static final String DEFAULT_BYTE_ANY_HUGO_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_BYTE_ANY_HUGO_CONTENT_TYPE = "image/png";

    private static final byte[] DEFAULT_BYTE_ANY_REQUIRED_HUGO = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_BYTE_ANY_REQUIRED_HUGO = TestUtil.createByteArray(2, "1");
    private static final String DEFAULT_BYTE_ANY_REQUIRED_HUGO_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_BYTE_ANY_REQUIRED_HUGO_CONTENT_TYPE = "image/png";

    private static final byte[] DEFAULT_BYTE_ANY_MINBYTES_HUGO = TestUtil.createByteArray(0, "0");
    private static final byte[] UPDATED_BYTE_ANY_MINBYTES_HUGO = TestUtil.createByteArray(0, "1");
    private static final String DEFAULT_BYTE_ANY_MINBYTES_HUGO_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_BYTE_ANY_MINBYTES_HUGO_CONTENT_TYPE = "image/png";

    private static final byte[] DEFAULT_BYTE_ANY_MAXBYTES_HUGO = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_BYTE_ANY_MAXBYTES_HUGO = TestUtil.createByteArray(10000, "1");
    private static final String DEFAULT_BYTE_ANY_MAXBYTES_HUGO_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_BYTE_ANY_MAXBYTES_HUGO_CONTENT_TYPE = "image/png";

    private static final String DEFAULT_BYTE_TEXT_HUGO = "AAAAAAAAAA";
    private static final String UPDATED_BYTE_TEXT_HUGO = "BBBBBBBBBB";

    private static final String DEFAULT_BYTE_TEXT_REQUIRED_HUGO = "AAAAAAAAAA";
    private static final String UPDATED_BYTE_TEXT_REQUIRED_HUGO = "BBBBBBBBBB";

    private static final String DEFAULT_BYTE_TEXT_MINBYTES_HUGO = "AAAAAAAAAA";
    private static final String UPDATED_BYTE_TEXT_MINBYTES_HUGO = "BBBBBBBBBB";

    private static final String DEFAULT_BYTE_TEXT_MAXBYTES_HUGO = "AAAAAAAAAA";
    private static final String UPDATED_BYTE_TEXT_MAXBYTES_HUGO = "BBBBBBBBBB";

    @Autowired
    private FieldTestInfiniteScrollEntityRepository fieldTestInfiniteScrollEntityRepository;

    @Autowired
    private FieldTestInfiniteScrollEntitySearchRepository fieldTestInfiniteScrollEntitySearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restFieldTestInfiniteScrollEntityMockMvc;

    private FieldTestInfiniteScrollEntity fieldTestInfiniteScrollEntity;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        FieldTestInfiniteScrollEntityResource fieldTestInfiniteScrollEntityResource = new FieldTestInfiniteScrollEntityResource(fieldTestInfiniteScrollEntityRepository, fieldTestInfiniteScrollEntitySearchRepository);
        this.restFieldTestInfiniteScrollEntityMockMvc = MockMvcBuilders.standaloneSetup(fieldTestInfiniteScrollEntityResource)
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
    public static FieldTestInfiniteScrollEntity createEntity(EntityManager em) {
        FieldTestInfiniteScrollEntity fieldTestInfiniteScrollEntity = new FieldTestInfiniteScrollEntity()
            .stringHugo(DEFAULT_STRING_HUGO)
            .stringRequiredHugo(DEFAULT_STRING_REQUIRED_HUGO)
            .stringMinlengthHugo(DEFAULT_STRING_MINLENGTH_HUGO)
            .stringMaxlengthHugo(DEFAULT_STRING_MAXLENGTH_HUGO)
            .stringPatternHugo(DEFAULT_STRING_PATTERN_HUGO)
            .integerHugo(DEFAULT_INTEGER_HUGO)
            .integerRequiredHugo(DEFAULT_INTEGER_REQUIRED_HUGO)
            .integerMinHugo(DEFAULT_INTEGER_MIN_HUGO)
            .integerMaxHugo(DEFAULT_INTEGER_MAX_HUGO)
            .longHugo(DEFAULT_LONG_HUGO)
            .longRequiredHugo(DEFAULT_LONG_REQUIRED_HUGO)
            .longMinHugo(DEFAULT_LONG_MIN_HUGO)
            .longMaxHugo(DEFAULT_LONG_MAX_HUGO)
            .floatHugo(DEFAULT_FLOAT_HUGO)
            .floatRequiredHugo(DEFAULT_FLOAT_REQUIRED_HUGO)
            .floatMinHugo(DEFAULT_FLOAT_MIN_HUGO)
            .floatMaxHugo(DEFAULT_FLOAT_MAX_HUGO)
            .doubleRequiredHugo(DEFAULT_DOUBLE_REQUIRED_HUGO)
            .doubleMinHugo(DEFAULT_DOUBLE_MIN_HUGO)
            .doubleMaxHugo(DEFAULT_DOUBLE_MAX_HUGO)
            .bigDecimalRequiredHugo(DEFAULT_BIG_DECIMAL_REQUIRED_HUGO)
            .bigDecimalMinHugo(DEFAULT_BIG_DECIMAL_MIN_HUGO)
            .bigDecimalMaxHugo(DEFAULT_BIG_DECIMAL_MAX_HUGO)
            .localDateHugo(DEFAULT_LOCAL_DATE_HUGO)
            .localDateRequiredHugo(DEFAULT_LOCAL_DATE_REQUIRED_HUGO)
            .instantHugo(DEFAULT_INSTANT_HUGO)
            .instanteRequiredHugo(DEFAULT_INSTANTE_REQUIRED_HUGO)
            .zonedDateTimeHugo(DEFAULT_ZONED_DATE_TIME_HUGO)
            .zonedDateTimeRequiredHugo(DEFAULT_ZONED_DATE_TIME_REQUIRED_HUGO)
            .booleanHugo(DEFAULT_BOOLEAN_HUGO)
            .booleanRequiredHugo(DEFAULT_BOOLEAN_REQUIRED_HUGO)
            .enumHugo(DEFAULT_ENUM_HUGO)
            .enumRequiredHugo(DEFAULT_ENUM_REQUIRED_HUGO)
            .byteImageHugo(DEFAULT_BYTE_IMAGE_HUGO)
            .byteImageHugoContentType(DEFAULT_BYTE_IMAGE_HUGO_CONTENT_TYPE)
            .byteImageRequiredHugo(DEFAULT_BYTE_IMAGE_REQUIRED_HUGO)
            .byteImageRequiredHugoContentType(DEFAULT_BYTE_IMAGE_REQUIRED_HUGO_CONTENT_TYPE)
            .byteImageMinbytesHugo(DEFAULT_BYTE_IMAGE_MINBYTES_HUGO)
            .byteImageMinbytesHugoContentType(DEFAULT_BYTE_IMAGE_MINBYTES_HUGO_CONTENT_TYPE)
            .byteImageMaxbytesHugo(DEFAULT_BYTE_IMAGE_MAXBYTES_HUGO)
            .byteImageMaxbytesHugoContentType(DEFAULT_BYTE_IMAGE_MAXBYTES_HUGO_CONTENT_TYPE)
            .byteAnyHugo(DEFAULT_BYTE_ANY_HUGO)
            .byteAnyHugoContentType(DEFAULT_BYTE_ANY_HUGO_CONTENT_TYPE)
            .byteAnyRequiredHugo(DEFAULT_BYTE_ANY_REQUIRED_HUGO)
            .byteAnyRequiredHugoContentType(DEFAULT_BYTE_ANY_REQUIRED_HUGO_CONTENT_TYPE)
            .byteAnyMinbytesHugo(DEFAULT_BYTE_ANY_MINBYTES_HUGO)
            .byteAnyMinbytesHugoContentType(DEFAULT_BYTE_ANY_MINBYTES_HUGO_CONTENT_TYPE)
            .byteAnyMaxbytesHugo(DEFAULT_BYTE_ANY_MAXBYTES_HUGO)
            .byteAnyMaxbytesHugoContentType(DEFAULT_BYTE_ANY_MAXBYTES_HUGO_CONTENT_TYPE)
            .byteTextHugo(DEFAULT_BYTE_TEXT_HUGO)
            .byteTextRequiredHugo(DEFAULT_BYTE_TEXT_REQUIRED_HUGO)
            .byteTextMinbytesHugo(DEFAULT_BYTE_TEXT_MINBYTES_HUGO)
            .byteTextMaxbytesHugo(DEFAULT_BYTE_TEXT_MAXBYTES_HUGO);
        return fieldTestInfiniteScrollEntity;
    }

    @Before
    public void initTest() {
        fieldTestInfiniteScrollEntitySearchRepository.deleteAll();
        fieldTestInfiniteScrollEntity = createEntity(em);
    }

    @Test
    @Transactional
    public void createFieldTestInfiniteScrollEntity() throws Exception {
        int databaseSizeBeforeCreate = fieldTestInfiniteScrollEntityRepository.findAll().size();

        // Create the FieldTestInfiniteScrollEntity
        restFieldTestInfiniteScrollEntityMockMvc.perform(post("/api/field-test-infinite-scroll-entities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fieldTestInfiniteScrollEntity)))
            .andExpect(status().isCreated());

        // Validate the FieldTestInfiniteScrollEntity in the database
        List<FieldTestInfiniteScrollEntity> fieldTestInfiniteScrollEntityList = fieldTestInfiniteScrollEntityRepository.findAll();
        assertThat(fieldTestInfiniteScrollEntityList).hasSize(databaseSizeBeforeCreate + 1);
        FieldTestInfiniteScrollEntity testFieldTestInfiniteScrollEntity = fieldTestInfiniteScrollEntityList.get(fieldTestInfiniteScrollEntityList.size() - 1);
        assertThat(testFieldTestInfiniteScrollEntity.getStringHugo()).isEqualTo(DEFAULT_STRING_HUGO);
        assertThat(testFieldTestInfiniteScrollEntity.getStringRequiredHugo()).isEqualTo(DEFAULT_STRING_REQUIRED_HUGO);
        assertThat(testFieldTestInfiniteScrollEntity.getStringMinlengthHugo()).isEqualTo(DEFAULT_STRING_MINLENGTH_HUGO);
        assertThat(testFieldTestInfiniteScrollEntity.getStringMaxlengthHugo()).isEqualTo(DEFAULT_STRING_MAXLENGTH_HUGO);
        assertThat(testFieldTestInfiniteScrollEntity.getStringPatternHugo()).isEqualTo(DEFAULT_STRING_PATTERN_HUGO);
        assertThat(testFieldTestInfiniteScrollEntity.getIntegerHugo()).isEqualTo(DEFAULT_INTEGER_HUGO);
        assertThat(testFieldTestInfiniteScrollEntity.getIntegerRequiredHugo()).isEqualTo(DEFAULT_INTEGER_REQUIRED_HUGO);
        assertThat(testFieldTestInfiniteScrollEntity.getIntegerMinHugo()).isEqualTo(DEFAULT_INTEGER_MIN_HUGO);
        assertThat(testFieldTestInfiniteScrollEntity.getIntegerMaxHugo()).isEqualTo(DEFAULT_INTEGER_MAX_HUGO);
        assertThat(testFieldTestInfiniteScrollEntity.getLongHugo()).isEqualTo(DEFAULT_LONG_HUGO);
        assertThat(testFieldTestInfiniteScrollEntity.getLongRequiredHugo()).isEqualTo(DEFAULT_LONG_REQUIRED_HUGO);
        assertThat(testFieldTestInfiniteScrollEntity.getLongMinHugo()).isEqualTo(DEFAULT_LONG_MIN_HUGO);
        assertThat(testFieldTestInfiniteScrollEntity.getLongMaxHugo()).isEqualTo(DEFAULT_LONG_MAX_HUGO);
        assertThat(testFieldTestInfiniteScrollEntity.getFloatHugo()).isEqualTo(DEFAULT_FLOAT_HUGO);
        assertThat(testFieldTestInfiniteScrollEntity.getFloatRequiredHugo()).isEqualTo(DEFAULT_FLOAT_REQUIRED_HUGO);
        assertThat(testFieldTestInfiniteScrollEntity.getFloatMinHugo()).isEqualTo(DEFAULT_FLOAT_MIN_HUGO);
        assertThat(testFieldTestInfiniteScrollEntity.getFloatMaxHugo()).isEqualTo(DEFAULT_FLOAT_MAX_HUGO);
        assertThat(testFieldTestInfiniteScrollEntity.getDoubleRequiredHugo()).isEqualTo(DEFAULT_DOUBLE_REQUIRED_HUGO);
        assertThat(testFieldTestInfiniteScrollEntity.getDoubleMinHugo()).isEqualTo(DEFAULT_DOUBLE_MIN_HUGO);
        assertThat(testFieldTestInfiniteScrollEntity.getDoubleMaxHugo()).isEqualTo(DEFAULT_DOUBLE_MAX_HUGO);
        assertThat(testFieldTestInfiniteScrollEntity.getBigDecimalRequiredHugo()).isEqualTo(DEFAULT_BIG_DECIMAL_REQUIRED_HUGO);
        assertThat(testFieldTestInfiniteScrollEntity.getBigDecimalMinHugo()).isEqualTo(DEFAULT_BIG_DECIMAL_MIN_HUGO);
        assertThat(testFieldTestInfiniteScrollEntity.getBigDecimalMaxHugo()).isEqualTo(DEFAULT_BIG_DECIMAL_MAX_HUGO);
        assertThat(testFieldTestInfiniteScrollEntity.getLocalDateHugo()).isEqualTo(DEFAULT_LOCAL_DATE_HUGO);
        assertThat(testFieldTestInfiniteScrollEntity.getLocalDateRequiredHugo()).isEqualTo(DEFAULT_LOCAL_DATE_REQUIRED_HUGO);
        assertThat(testFieldTestInfiniteScrollEntity.getInstantHugo()).isEqualTo(DEFAULT_INSTANT_HUGO);
        assertThat(testFieldTestInfiniteScrollEntity.getInstanteRequiredHugo()).isEqualTo(DEFAULT_INSTANTE_REQUIRED_HUGO);
        assertThat(testFieldTestInfiniteScrollEntity.getZonedDateTimeHugo()).isEqualTo(DEFAULT_ZONED_DATE_TIME_HUGO);
        assertThat(testFieldTestInfiniteScrollEntity.getZonedDateTimeRequiredHugo()).isEqualTo(DEFAULT_ZONED_DATE_TIME_REQUIRED_HUGO);
        assertThat(testFieldTestInfiniteScrollEntity.isBooleanHugo()).isEqualTo(DEFAULT_BOOLEAN_HUGO);
        assertThat(testFieldTestInfiniteScrollEntity.isBooleanRequiredHugo()).isEqualTo(DEFAULT_BOOLEAN_REQUIRED_HUGO);
        assertThat(testFieldTestInfiniteScrollEntity.getEnumHugo()).isEqualTo(DEFAULT_ENUM_HUGO);
        assertThat(testFieldTestInfiniteScrollEntity.getEnumRequiredHugo()).isEqualTo(DEFAULT_ENUM_REQUIRED_HUGO);
        assertThat(testFieldTestInfiniteScrollEntity.getByteImageHugo()).isEqualTo(DEFAULT_BYTE_IMAGE_HUGO);
        assertThat(testFieldTestInfiniteScrollEntity.getByteImageHugoContentType()).isEqualTo(DEFAULT_BYTE_IMAGE_HUGO_CONTENT_TYPE);
        assertThat(testFieldTestInfiniteScrollEntity.getByteImageRequiredHugo()).isEqualTo(DEFAULT_BYTE_IMAGE_REQUIRED_HUGO);
        assertThat(testFieldTestInfiniteScrollEntity.getByteImageRequiredHugoContentType()).isEqualTo(DEFAULT_BYTE_IMAGE_REQUIRED_HUGO_CONTENT_TYPE);
        assertThat(testFieldTestInfiniteScrollEntity.getByteImageMinbytesHugo()).isEqualTo(DEFAULT_BYTE_IMAGE_MINBYTES_HUGO);
        assertThat(testFieldTestInfiniteScrollEntity.getByteImageMinbytesHugoContentType()).isEqualTo(DEFAULT_BYTE_IMAGE_MINBYTES_HUGO_CONTENT_TYPE);
        assertThat(testFieldTestInfiniteScrollEntity.getByteImageMaxbytesHugo()).isEqualTo(DEFAULT_BYTE_IMAGE_MAXBYTES_HUGO);
        assertThat(testFieldTestInfiniteScrollEntity.getByteImageMaxbytesHugoContentType()).isEqualTo(DEFAULT_BYTE_IMAGE_MAXBYTES_HUGO_CONTENT_TYPE);
        assertThat(testFieldTestInfiniteScrollEntity.getByteAnyHugo()).isEqualTo(DEFAULT_BYTE_ANY_HUGO);
        assertThat(testFieldTestInfiniteScrollEntity.getByteAnyHugoContentType()).isEqualTo(DEFAULT_BYTE_ANY_HUGO_CONTENT_TYPE);
        assertThat(testFieldTestInfiniteScrollEntity.getByteAnyRequiredHugo()).isEqualTo(DEFAULT_BYTE_ANY_REQUIRED_HUGO);
        assertThat(testFieldTestInfiniteScrollEntity.getByteAnyRequiredHugoContentType()).isEqualTo(DEFAULT_BYTE_ANY_REQUIRED_HUGO_CONTENT_TYPE);
        assertThat(testFieldTestInfiniteScrollEntity.getByteAnyMinbytesHugo()).isEqualTo(DEFAULT_BYTE_ANY_MINBYTES_HUGO);
        assertThat(testFieldTestInfiniteScrollEntity.getByteAnyMinbytesHugoContentType()).isEqualTo(DEFAULT_BYTE_ANY_MINBYTES_HUGO_CONTENT_TYPE);
        assertThat(testFieldTestInfiniteScrollEntity.getByteAnyMaxbytesHugo()).isEqualTo(DEFAULT_BYTE_ANY_MAXBYTES_HUGO);
        assertThat(testFieldTestInfiniteScrollEntity.getByteAnyMaxbytesHugoContentType()).isEqualTo(DEFAULT_BYTE_ANY_MAXBYTES_HUGO_CONTENT_TYPE);
        assertThat(testFieldTestInfiniteScrollEntity.getByteTextHugo()).isEqualTo(DEFAULT_BYTE_TEXT_HUGO);
        assertThat(testFieldTestInfiniteScrollEntity.getByteTextRequiredHugo()).isEqualTo(DEFAULT_BYTE_TEXT_REQUIRED_HUGO);
        assertThat(testFieldTestInfiniteScrollEntity.getByteTextMinbytesHugo()).isEqualTo(DEFAULT_BYTE_TEXT_MINBYTES_HUGO);
        assertThat(testFieldTestInfiniteScrollEntity.getByteTextMaxbytesHugo()).isEqualTo(DEFAULT_BYTE_TEXT_MAXBYTES_HUGO);

        // Validate the FieldTestInfiniteScrollEntity in Elasticsearch
        FieldTestInfiniteScrollEntity fieldTestInfiniteScrollEntityEs = fieldTestInfiniteScrollEntitySearchRepository.findOne(testFieldTestInfiniteScrollEntity.getId());
        assertThat(fieldTestInfiniteScrollEntityEs).isEqualToComparingFieldByField(testFieldTestInfiniteScrollEntity);
    }

    @Test
    @Transactional
    public void createFieldTestInfiniteScrollEntityWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = fieldTestInfiniteScrollEntityRepository.findAll().size();

        // Create the FieldTestInfiniteScrollEntity with an existing ID
        fieldTestInfiniteScrollEntity.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFieldTestInfiniteScrollEntityMockMvc.perform(post("/api/field-test-infinite-scroll-entities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fieldTestInfiniteScrollEntity)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<FieldTestInfiniteScrollEntity> fieldTestInfiniteScrollEntityList = fieldTestInfiniteScrollEntityRepository.findAll();
        assertThat(fieldTestInfiniteScrollEntityList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkStringRequiredHugoIsRequired() throws Exception {
        int databaseSizeBeforeTest = fieldTestInfiniteScrollEntityRepository.findAll().size();
        // set the field null
        fieldTestInfiniteScrollEntity.setStringRequiredHugo(null);

        // Create the FieldTestInfiniteScrollEntity, which fails.

        restFieldTestInfiniteScrollEntityMockMvc.perform(post("/api/field-test-infinite-scroll-entities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fieldTestInfiniteScrollEntity)))
            .andExpect(status().isBadRequest());

        List<FieldTestInfiniteScrollEntity> fieldTestInfiniteScrollEntityList = fieldTestInfiniteScrollEntityRepository.findAll();
        assertThat(fieldTestInfiniteScrollEntityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIntegerRequiredHugoIsRequired() throws Exception {
        int databaseSizeBeforeTest = fieldTestInfiniteScrollEntityRepository.findAll().size();
        // set the field null
        fieldTestInfiniteScrollEntity.setIntegerRequiredHugo(null);

        // Create the FieldTestInfiniteScrollEntity, which fails.

        restFieldTestInfiniteScrollEntityMockMvc.perform(post("/api/field-test-infinite-scroll-entities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fieldTestInfiniteScrollEntity)))
            .andExpect(status().isBadRequest());

        List<FieldTestInfiniteScrollEntity> fieldTestInfiniteScrollEntityList = fieldTestInfiniteScrollEntityRepository.findAll();
        assertThat(fieldTestInfiniteScrollEntityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLongRequiredHugoIsRequired() throws Exception {
        int databaseSizeBeforeTest = fieldTestInfiniteScrollEntityRepository.findAll().size();
        // set the field null
        fieldTestInfiniteScrollEntity.setLongRequiredHugo(null);

        // Create the FieldTestInfiniteScrollEntity, which fails.

        restFieldTestInfiniteScrollEntityMockMvc.perform(post("/api/field-test-infinite-scroll-entities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fieldTestInfiniteScrollEntity)))
            .andExpect(status().isBadRequest());

        List<FieldTestInfiniteScrollEntity> fieldTestInfiniteScrollEntityList = fieldTestInfiniteScrollEntityRepository.findAll();
        assertThat(fieldTestInfiniteScrollEntityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFloatRequiredHugoIsRequired() throws Exception {
        int databaseSizeBeforeTest = fieldTestInfiniteScrollEntityRepository.findAll().size();
        // set the field null
        fieldTestInfiniteScrollEntity.setFloatRequiredHugo(null);

        // Create the FieldTestInfiniteScrollEntity, which fails.

        restFieldTestInfiniteScrollEntityMockMvc.perform(post("/api/field-test-infinite-scroll-entities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fieldTestInfiniteScrollEntity)))
            .andExpect(status().isBadRequest());

        List<FieldTestInfiniteScrollEntity> fieldTestInfiniteScrollEntityList = fieldTestInfiniteScrollEntityRepository.findAll();
        assertThat(fieldTestInfiniteScrollEntityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDoubleRequiredHugoIsRequired() throws Exception {
        int databaseSizeBeforeTest = fieldTestInfiniteScrollEntityRepository.findAll().size();
        // set the field null
        fieldTestInfiniteScrollEntity.setDoubleRequiredHugo(null);

        // Create the FieldTestInfiniteScrollEntity, which fails.

        restFieldTestInfiniteScrollEntityMockMvc.perform(post("/api/field-test-infinite-scroll-entities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fieldTestInfiniteScrollEntity)))
            .andExpect(status().isBadRequest());

        List<FieldTestInfiniteScrollEntity> fieldTestInfiniteScrollEntityList = fieldTestInfiniteScrollEntityRepository.findAll();
        assertThat(fieldTestInfiniteScrollEntityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkBigDecimalRequiredHugoIsRequired() throws Exception {
        int databaseSizeBeforeTest = fieldTestInfiniteScrollEntityRepository.findAll().size();
        // set the field null
        fieldTestInfiniteScrollEntity.setBigDecimalRequiredHugo(null);

        // Create the FieldTestInfiniteScrollEntity, which fails.

        restFieldTestInfiniteScrollEntityMockMvc.perform(post("/api/field-test-infinite-scroll-entities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fieldTestInfiniteScrollEntity)))
            .andExpect(status().isBadRequest());

        List<FieldTestInfiniteScrollEntity> fieldTestInfiniteScrollEntityList = fieldTestInfiniteScrollEntityRepository.findAll();
        assertThat(fieldTestInfiniteScrollEntityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLocalDateRequiredHugoIsRequired() throws Exception {
        int databaseSizeBeforeTest = fieldTestInfiniteScrollEntityRepository.findAll().size();
        // set the field null
        fieldTestInfiniteScrollEntity.setLocalDateRequiredHugo(null);

        // Create the FieldTestInfiniteScrollEntity, which fails.

        restFieldTestInfiniteScrollEntityMockMvc.perform(post("/api/field-test-infinite-scroll-entities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fieldTestInfiniteScrollEntity)))
            .andExpect(status().isBadRequest());

        List<FieldTestInfiniteScrollEntity> fieldTestInfiniteScrollEntityList = fieldTestInfiniteScrollEntityRepository.findAll();
        assertThat(fieldTestInfiniteScrollEntityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkInstanteRequiredHugoIsRequired() throws Exception {
        int databaseSizeBeforeTest = fieldTestInfiniteScrollEntityRepository.findAll().size();
        // set the field null
        fieldTestInfiniteScrollEntity.setInstanteRequiredHugo(null);

        // Create the FieldTestInfiniteScrollEntity, which fails.

        restFieldTestInfiniteScrollEntityMockMvc.perform(post("/api/field-test-infinite-scroll-entities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fieldTestInfiniteScrollEntity)))
            .andExpect(status().isBadRequest());

        List<FieldTestInfiniteScrollEntity> fieldTestInfiniteScrollEntityList = fieldTestInfiniteScrollEntityRepository.findAll();
        assertThat(fieldTestInfiniteScrollEntityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkZonedDateTimeRequiredHugoIsRequired() throws Exception {
        int databaseSizeBeforeTest = fieldTestInfiniteScrollEntityRepository.findAll().size();
        // set the field null
        fieldTestInfiniteScrollEntity.setZonedDateTimeRequiredHugo(null);

        // Create the FieldTestInfiniteScrollEntity, which fails.

        restFieldTestInfiniteScrollEntityMockMvc.perform(post("/api/field-test-infinite-scroll-entities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fieldTestInfiniteScrollEntity)))
            .andExpect(status().isBadRequest());

        List<FieldTestInfiniteScrollEntity> fieldTestInfiniteScrollEntityList = fieldTestInfiniteScrollEntityRepository.findAll();
        assertThat(fieldTestInfiniteScrollEntityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkBooleanRequiredHugoIsRequired() throws Exception {
        int databaseSizeBeforeTest = fieldTestInfiniteScrollEntityRepository.findAll().size();
        // set the field null
        fieldTestInfiniteScrollEntity.setBooleanRequiredHugo(null);

        // Create the FieldTestInfiniteScrollEntity, which fails.

        restFieldTestInfiniteScrollEntityMockMvc.perform(post("/api/field-test-infinite-scroll-entities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fieldTestInfiniteScrollEntity)))
            .andExpect(status().isBadRequest());

        List<FieldTestInfiniteScrollEntity> fieldTestInfiniteScrollEntityList = fieldTestInfiniteScrollEntityRepository.findAll();
        assertThat(fieldTestInfiniteScrollEntityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEnumRequiredHugoIsRequired() throws Exception {
        int databaseSizeBeforeTest = fieldTestInfiniteScrollEntityRepository.findAll().size();
        // set the field null
        fieldTestInfiniteScrollEntity.setEnumRequiredHugo(null);

        // Create the FieldTestInfiniteScrollEntity, which fails.

        restFieldTestInfiniteScrollEntityMockMvc.perform(post("/api/field-test-infinite-scroll-entities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fieldTestInfiniteScrollEntity)))
            .andExpect(status().isBadRequest());

        List<FieldTestInfiniteScrollEntity> fieldTestInfiniteScrollEntityList = fieldTestInfiniteScrollEntityRepository.findAll();
        assertThat(fieldTestInfiniteScrollEntityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkByteImageRequiredHugoIsRequired() throws Exception {
        int databaseSizeBeforeTest = fieldTestInfiniteScrollEntityRepository.findAll().size();
        // set the field null
        fieldTestInfiniteScrollEntity.setByteImageRequiredHugo(null);

        // Create the FieldTestInfiniteScrollEntity, which fails.

        restFieldTestInfiniteScrollEntityMockMvc.perform(post("/api/field-test-infinite-scroll-entities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fieldTestInfiniteScrollEntity)))
            .andExpect(status().isBadRequest());

        List<FieldTestInfiniteScrollEntity> fieldTestInfiniteScrollEntityList = fieldTestInfiniteScrollEntityRepository.findAll();
        assertThat(fieldTestInfiniteScrollEntityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkByteAnyRequiredHugoIsRequired() throws Exception {
        int databaseSizeBeforeTest = fieldTestInfiniteScrollEntityRepository.findAll().size();
        // set the field null
        fieldTestInfiniteScrollEntity.setByteAnyRequiredHugo(null);

        // Create the FieldTestInfiniteScrollEntity, which fails.

        restFieldTestInfiniteScrollEntityMockMvc.perform(post("/api/field-test-infinite-scroll-entities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fieldTestInfiniteScrollEntity)))
            .andExpect(status().isBadRequest());

        List<FieldTestInfiniteScrollEntity> fieldTestInfiniteScrollEntityList = fieldTestInfiniteScrollEntityRepository.findAll();
        assertThat(fieldTestInfiniteScrollEntityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkByteTextRequiredHugoIsRequired() throws Exception {
        int databaseSizeBeforeTest = fieldTestInfiniteScrollEntityRepository.findAll().size();
        // set the field null
        fieldTestInfiniteScrollEntity.setByteTextRequiredHugo(null);

        // Create the FieldTestInfiniteScrollEntity, which fails.

        restFieldTestInfiniteScrollEntityMockMvc.perform(post("/api/field-test-infinite-scroll-entities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fieldTestInfiniteScrollEntity)))
            .andExpect(status().isBadRequest());

        List<FieldTestInfiniteScrollEntity> fieldTestInfiniteScrollEntityList = fieldTestInfiniteScrollEntityRepository.findAll();
        assertThat(fieldTestInfiniteScrollEntityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllFieldTestInfiniteScrollEntities() throws Exception {
        // Initialize the database
        fieldTestInfiniteScrollEntityRepository.saveAndFlush(fieldTestInfiniteScrollEntity);

        // Get all the fieldTestInfiniteScrollEntityList
        restFieldTestInfiniteScrollEntityMockMvc.perform(get("/api/field-test-infinite-scroll-entities?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(fieldTestInfiniteScrollEntity.getId().intValue())))
            .andExpect(jsonPath("$.[*].stringHugo").value(hasItem(DEFAULT_STRING_HUGO.toString())))
            .andExpect(jsonPath("$.[*].stringRequiredHugo").value(hasItem(DEFAULT_STRING_REQUIRED_HUGO.toString())))
            .andExpect(jsonPath("$.[*].stringMinlengthHugo").value(hasItem(DEFAULT_STRING_MINLENGTH_HUGO.toString())))
            .andExpect(jsonPath("$.[*].stringMaxlengthHugo").value(hasItem(DEFAULT_STRING_MAXLENGTH_HUGO.toString())))
            .andExpect(jsonPath("$.[*].stringPatternHugo").value(hasItem(DEFAULT_STRING_PATTERN_HUGO.toString())))
            .andExpect(jsonPath("$.[*].integerHugo").value(hasItem(DEFAULT_INTEGER_HUGO)))
            .andExpect(jsonPath("$.[*].integerRequiredHugo").value(hasItem(DEFAULT_INTEGER_REQUIRED_HUGO)))
            .andExpect(jsonPath("$.[*].integerMinHugo").value(hasItem(DEFAULT_INTEGER_MIN_HUGO)))
            .andExpect(jsonPath("$.[*].integerMaxHugo").value(hasItem(DEFAULT_INTEGER_MAX_HUGO)))
            .andExpect(jsonPath("$.[*].longHugo").value(hasItem(DEFAULT_LONG_HUGO.intValue())))
            .andExpect(jsonPath("$.[*].longRequiredHugo").value(hasItem(DEFAULT_LONG_REQUIRED_HUGO.intValue())))
            .andExpect(jsonPath("$.[*].longMinHugo").value(hasItem(DEFAULT_LONG_MIN_HUGO.intValue())))
            .andExpect(jsonPath("$.[*].longMaxHugo").value(hasItem(DEFAULT_LONG_MAX_HUGO.intValue())))
            .andExpect(jsonPath("$.[*].floatHugo").value(hasItem(DEFAULT_FLOAT_HUGO.doubleValue())))
            .andExpect(jsonPath("$.[*].floatRequiredHugo").value(hasItem(DEFAULT_FLOAT_REQUIRED_HUGO.doubleValue())))
            .andExpect(jsonPath("$.[*].floatMinHugo").value(hasItem(DEFAULT_FLOAT_MIN_HUGO.doubleValue())))
            .andExpect(jsonPath("$.[*].floatMaxHugo").value(hasItem(DEFAULT_FLOAT_MAX_HUGO.doubleValue())))
            .andExpect(jsonPath("$.[*].doubleRequiredHugo").value(hasItem(DEFAULT_DOUBLE_REQUIRED_HUGO.doubleValue())))
            .andExpect(jsonPath("$.[*].doubleMinHugo").value(hasItem(DEFAULT_DOUBLE_MIN_HUGO.doubleValue())))
            .andExpect(jsonPath("$.[*].doubleMaxHugo").value(hasItem(DEFAULT_DOUBLE_MAX_HUGO.doubleValue())))
            .andExpect(jsonPath("$.[*].bigDecimalRequiredHugo").value(hasItem(DEFAULT_BIG_DECIMAL_REQUIRED_HUGO.intValue())))
            .andExpect(jsonPath("$.[*].bigDecimalMinHugo").value(hasItem(DEFAULT_BIG_DECIMAL_MIN_HUGO.intValue())))
            .andExpect(jsonPath("$.[*].bigDecimalMaxHugo").value(hasItem(DEFAULT_BIG_DECIMAL_MAX_HUGO.intValue())))
            .andExpect(jsonPath("$.[*].localDateHugo").value(hasItem(DEFAULT_LOCAL_DATE_HUGO.toString())))
            .andExpect(jsonPath("$.[*].localDateRequiredHugo").value(hasItem(DEFAULT_LOCAL_DATE_REQUIRED_HUGO.toString())))
            .andExpect(jsonPath("$.[*].instantHugo").value(hasItem(DEFAULT_INSTANT_HUGO.toString())))
            .andExpect(jsonPath("$.[*].instanteRequiredHugo").value(hasItem(DEFAULT_INSTANTE_REQUIRED_HUGO.toString())))
            .andExpect(jsonPath("$.[*].zonedDateTimeHugo").value(hasItem(sameInstant(DEFAULT_ZONED_DATE_TIME_HUGO))))
            .andExpect(jsonPath("$.[*].zonedDateTimeRequiredHugo").value(hasItem(sameInstant(DEFAULT_ZONED_DATE_TIME_REQUIRED_HUGO))))
            .andExpect(jsonPath("$.[*].booleanHugo").value(hasItem(DEFAULT_BOOLEAN_HUGO.booleanValue())))
            .andExpect(jsonPath("$.[*].booleanRequiredHugo").value(hasItem(DEFAULT_BOOLEAN_REQUIRED_HUGO.booleanValue())))
            .andExpect(jsonPath("$.[*].enumHugo").value(hasItem(DEFAULT_ENUM_HUGO.toString())))
            .andExpect(jsonPath("$.[*].enumRequiredHugo").value(hasItem(DEFAULT_ENUM_REQUIRED_HUGO.toString())))
            .andExpect(jsonPath("$.[*].byteImageHugoContentType").value(hasItem(DEFAULT_BYTE_IMAGE_HUGO_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].byteImageHugo").value(hasItem(Base64Utils.encodeToString(DEFAULT_BYTE_IMAGE_HUGO))))
            .andExpect(jsonPath("$.[*].byteImageRequiredHugoContentType").value(hasItem(DEFAULT_BYTE_IMAGE_REQUIRED_HUGO_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].byteImageRequiredHugo").value(hasItem(Base64Utils.encodeToString(DEFAULT_BYTE_IMAGE_REQUIRED_HUGO))))
            .andExpect(jsonPath("$.[*].byteImageMinbytesHugoContentType").value(hasItem(DEFAULT_BYTE_IMAGE_MINBYTES_HUGO_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].byteImageMinbytesHugo").value(hasItem(Base64Utils.encodeToString(DEFAULT_BYTE_IMAGE_MINBYTES_HUGO))))
            .andExpect(jsonPath("$.[*].byteImageMaxbytesHugoContentType").value(hasItem(DEFAULT_BYTE_IMAGE_MAXBYTES_HUGO_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].byteImageMaxbytesHugo").value(hasItem(Base64Utils.encodeToString(DEFAULT_BYTE_IMAGE_MAXBYTES_HUGO))))
            .andExpect(jsonPath("$.[*].byteAnyHugoContentType").value(hasItem(DEFAULT_BYTE_ANY_HUGO_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].byteAnyHugo").value(hasItem(Base64Utils.encodeToString(DEFAULT_BYTE_ANY_HUGO))))
            .andExpect(jsonPath("$.[*].byteAnyRequiredHugoContentType").value(hasItem(DEFAULT_BYTE_ANY_REQUIRED_HUGO_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].byteAnyRequiredHugo").value(hasItem(Base64Utils.encodeToString(DEFAULT_BYTE_ANY_REQUIRED_HUGO))))
            .andExpect(jsonPath("$.[*].byteAnyMinbytesHugoContentType").value(hasItem(DEFAULT_BYTE_ANY_MINBYTES_HUGO_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].byteAnyMinbytesHugo").value(hasItem(Base64Utils.encodeToString(DEFAULT_BYTE_ANY_MINBYTES_HUGO))))
            .andExpect(jsonPath("$.[*].byteAnyMaxbytesHugoContentType").value(hasItem(DEFAULT_BYTE_ANY_MAXBYTES_HUGO_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].byteAnyMaxbytesHugo").value(hasItem(Base64Utils.encodeToString(DEFAULT_BYTE_ANY_MAXBYTES_HUGO))))
            .andExpect(jsonPath("$.[*].byteTextHugo").value(hasItem(DEFAULT_BYTE_TEXT_HUGO.toString())))
            .andExpect(jsonPath("$.[*].byteTextRequiredHugo").value(hasItem(DEFAULT_BYTE_TEXT_REQUIRED_HUGO.toString())))
            .andExpect(jsonPath("$.[*].byteTextMinbytesHugo").value(hasItem(DEFAULT_BYTE_TEXT_MINBYTES_HUGO.toString())))
            .andExpect(jsonPath("$.[*].byteTextMaxbytesHugo").value(hasItem(DEFAULT_BYTE_TEXT_MAXBYTES_HUGO.toString())));
    }

    @Test
    @Transactional
    public void getFieldTestInfiniteScrollEntity() throws Exception {
        // Initialize the database
        fieldTestInfiniteScrollEntityRepository.saveAndFlush(fieldTestInfiniteScrollEntity);

        // Get the fieldTestInfiniteScrollEntity
        restFieldTestInfiniteScrollEntityMockMvc.perform(get("/api/field-test-infinite-scroll-entities/{id}", fieldTestInfiniteScrollEntity.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(fieldTestInfiniteScrollEntity.getId().intValue()))
            .andExpect(jsonPath("$.stringHugo").value(DEFAULT_STRING_HUGO.toString()))
            .andExpect(jsonPath("$.stringRequiredHugo").value(DEFAULT_STRING_REQUIRED_HUGO.toString()))
            .andExpect(jsonPath("$.stringMinlengthHugo").value(DEFAULT_STRING_MINLENGTH_HUGO.toString()))
            .andExpect(jsonPath("$.stringMaxlengthHugo").value(DEFAULT_STRING_MAXLENGTH_HUGO.toString()))
            .andExpect(jsonPath("$.stringPatternHugo").value(DEFAULT_STRING_PATTERN_HUGO.toString()))
            .andExpect(jsonPath("$.integerHugo").value(DEFAULT_INTEGER_HUGO))
            .andExpect(jsonPath("$.integerRequiredHugo").value(DEFAULT_INTEGER_REQUIRED_HUGO))
            .andExpect(jsonPath("$.integerMinHugo").value(DEFAULT_INTEGER_MIN_HUGO))
            .andExpect(jsonPath("$.integerMaxHugo").value(DEFAULT_INTEGER_MAX_HUGO))
            .andExpect(jsonPath("$.longHugo").value(DEFAULT_LONG_HUGO.intValue()))
            .andExpect(jsonPath("$.longRequiredHugo").value(DEFAULT_LONG_REQUIRED_HUGO.intValue()))
            .andExpect(jsonPath("$.longMinHugo").value(DEFAULT_LONG_MIN_HUGO.intValue()))
            .andExpect(jsonPath("$.longMaxHugo").value(DEFAULT_LONG_MAX_HUGO.intValue()))
            .andExpect(jsonPath("$.floatHugo").value(DEFAULT_FLOAT_HUGO.doubleValue()))
            .andExpect(jsonPath("$.floatRequiredHugo").value(DEFAULT_FLOAT_REQUIRED_HUGO.doubleValue()))
            .andExpect(jsonPath("$.floatMinHugo").value(DEFAULT_FLOAT_MIN_HUGO.doubleValue()))
            .andExpect(jsonPath("$.floatMaxHugo").value(DEFAULT_FLOAT_MAX_HUGO.doubleValue()))
            .andExpect(jsonPath("$.doubleRequiredHugo").value(DEFAULT_DOUBLE_REQUIRED_HUGO.doubleValue()))
            .andExpect(jsonPath("$.doubleMinHugo").value(DEFAULT_DOUBLE_MIN_HUGO.doubleValue()))
            .andExpect(jsonPath("$.doubleMaxHugo").value(DEFAULT_DOUBLE_MAX_HUGO.doubleValue()))
            .andExpect(jsonPath("$.bigDecimalRequiredHugo").value(DEFAULT_BIG_DECIMAL_REQUIRED_HUGO.intValue()))
            .andExpect(jsonPath("$.bigDecimalMinHugo").value(DEFAULT_BIG_DECIMAL_MIN_HUGO.intValue()))
            .andExpect(jsonPath("$.bigDecimalMaxHugo").value(DEFAULT_BIG_DECIMAL_MAX_HUGO.intValue()))
            .andExpect(jsonPath("$.localDateHugo").value(DEFAULT_LOCAL_DATE_HUGO.toString()))
            .andExpect(jsonPath("$.localDateRequiredHugo").value(DEFAULT_LOCAL_DATE_REQUIRED_HUGO.toString()))
            .andExpect(jsonPath("$.instantHugo").value(DEFAULT_INSTANT_HUGO.toString()))
            .andExpect(jsonPath("$.instanteRequiredHugo").value(DEFAULT_INSTANTE_REQUIRED_HUGO.toString()))
            .andExpect(jsonPath("$.zonedDateTimeHugo").value(sameInstant(DEFAULT_ZONED_DATE_TIME_HUGO)))
            .andExpect(jsonPath("$.zonedDateTimeRequiredHugo").value(sameInstant(DEFAULT_ZONED_DATE_TIME_REQUIRED_HUGO)))
            .andExpect(jsonPath("$.booleanHugo").value(DEFAULT_BOOLEAN_HUGO.booleanValue()))
            .andExpect(jsonPath("$.booleanRequiredHugo").value(DEFAULT_BOOLEAN_REQUIRED_HUGO.booleanValue()))
            .andExpect(jsonPath("$.enumHugo").value(DEFAULT_ENUM_HUGO.toString()))
            .andExpect(jsonPath("$.enumRequiredHugo").value(DEFAULT_ENUM_REQUIRED_HUGO.toString()))
            .andExpect(jsonPath("$.byteImageHugoContentType").value(DEFAULT_BYTE_IMAGE_HUGO_CONTENT_TYPE))
            .andExpect(jsonPath("$.byteImageHugo").value(Base64Utils.encodeToString(DEFAULT_BYTE_IMAGE_HUGO)))
            .andExpect(jsonPath("$.byteImageRequiredHugoContentType").value(DEFAULT_BYTE_IMAGE_REQUIRED_HUGO_CONTENT_TYPE))
            .andExpect(jsonPath("$.byteImageRequiredHugo").value(Base64Utils.encodeToString(DEFAULT_BYTE_IMAGE_REQUIRED_HUGO)))
            .andExpect(jsonPath("$.byteImageMinbytesHugoContentType").value(DEFAULT_BYTE_IMAGE_MINBYTES_HUGO_CONTENT_TYPE))
            .andExpect(jsonPath("$.byteImageMinbytesHugo").value(Base64Utils.encodeToString(DEFAULT_BYTE_IMAGE_MINBYTES_HUGO)))
            .andExpect(jsonPath("$.byteImageMaxbytesHugoContentType").value(DEFAULT_BYTE_IMAGE_MAXBYTES_HUGO_CONTENT_TYPE))
            .andExpect(jsonPath("$.byteImageMaxbytesHugo").value(Base64Utils.encodeToString(DEFAULT_BYTE_IMAGE_MAXBYTES_HUGO)))
            .andExpect(jsonPath("$.byteAnyHugoContentType").value(DEFAULT_BYTE_ANY_HUGO_CONTENT_TYPE))
            .andExpect(jsonPath("$.byteAnyHugo").value(Base64Utils.encodeToString(DEFAULT_BYTE_ANY_HUGO)))
            .andExpect(jsonPath("$.byteAnyRequiredHugoContentType").value(DEFAULT_BYTE_ANY_REQUIRED_HUGO_CONTENT_TYPE))
            .andExpect(jsonPath("$.byteAnyRequiredHugo").value(Base64Utils.encodeToString(DEFAULT_BYTE_ANY_REQUIRED_HUGO)))
            .andExpect(jsonPath("$.byteAnyMinbytesHugoContentType").value(DEFAULT_BYTE_ANY_MINBYTES_HUGO_CONTENT_TYPE))
            .andExpect(jsonPath("$.byteAnyMinbytesHugo").value(Base64Utils.encodeToString(DEFAULT_BYTE_ANY_MINBYTES_HUGO)))
            .andExpect(jsonPath("$.byteAnyMaxbytesHugoContentType").value(DEFAULT_BYTE_ANY_MAXBYTES_HUGO_CONTENT_TYPE))
            .andExpect(jsonPath("$.byteAnyMaxbytesHugo").value(Base64Utils.encodeToString(DEFAULT_BYTE_ANY_MAXBYTES_HUGO)))
            .andExpect(jsonPath("$.byteTextHugo").value(DEFAULT_BYTE_TEXT_HUGO.toString()))
            .andExpect(jsonPath("$.byteTextRequiredHugo").value(DEFAULT_BYTE_TEXT_REQUIRED_HUGO.toString()))
            .andExpect(jsonPath("$.byteTextMinbytesHugo").value(DEFAULT_BYTE_TEXT_MINBYTES_HUGO.toString()))
            .andExpect(jsonPath("$.byteTextMaxbytesHugo").value(DEFAULT_BYTE_TEXT_MAXBYTES_HUGO.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingFieldTestInfiniteScrollEntity() throws Exception {
        // Get the fieldTestInfiniteScrollEntity
        restFieldTestInfiniteScrollEntityMockMvc.perform(get("/api/field-test-infinite-scroll-entities/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFieldTestInfiniteScrollEntity() throws Exception {
        // Initialize the database
        fieldTestInfiniteScrollEntityRepository.saveAndFlush(fieldTestInfiniteScrollEntity);
        fieldTestInfiniteScrollEntitySearchRepository.save(fieldTestInfiniteScrollEntity);
        int databaseSizeBeforeUpdate = fieldTestInfiniteScrollEntityRepository.findAll().size();

        // Update the fieldTestInfiniteScrollEntity
        FieldTestInfiniteScrollEntity updatedFieldTestInfiniteScrollEntity = fieldTestInfiniteScrollEntityRepository.findOne(fieldTestInfiniteScrollEntity.getId());
        updatedFieldTestInfiniteScrollEntity
            .stringHugo(UPDATED_STRING_HUGO)
            .stringRequiredHugo(UPDATED_STRING_REQUIRED_HUGO)
            .stringMinlengthHugo(UPDATED_STRING_MINLENGTH_HUGO)
            .stringMaxlengthHugo(UPDATED_STRING_MAXLENGTH_HUGO)
            .stringPatternHugo(UPDATED_STRING_PATTERN_HUGO)
            .integerHugo(UPDATED_INTEGER_HUGO)
            .integerRequiredHugo(UPDATED_INTEGER_REQUIRED_HUGO)
            .integerMinHugo(UPDATED_INTEGER_MIN_HUGO)
            .integerMaxHugo(UPDATED_INTEGER_MAX_HUGO)
            .longHugo(UPDATED_LONG_HUGO)
            .longRequiredHugo(UPDATED_LONG_REQUIRED_HUGO)
            .longMinHugo(UPDATED_LONG_MIN_HUGO)
            .longMaxHugo(UPDATED_LONG_MAX_HUGO)
            .floatHugo(UPDATED_FLOAT_HUGO)
            .floatRequiredHugo(UPDATED_FLOAT_REQUIRED_HUGO)
            .floatMinHugo(UPDATED_FLOAT_MIN_HUGO)
            .floatMaxHugo(UPDATED_FLOAT_MAX_HUGO)
            .doubleRequiredHugo(UPDATED_DOUBLE_REQUIRED_HUGO)
            .doubleMinHugo(UPDATED_DOUBLE_MIN_HUGO)
            .doubleMaxHugo(UPDATED_DOUBLE_MAX_HUGO)
            .bigDecimalRequiredHugo(UPDATED_BIG_DECIMAL_REQUIRED_HUGO)
            .bigDecimalMinHugo(UPDATED_BIG_DECIMAL_MIN_HUGO)
            .bigDecimalMaxHugo(UPDATED_BIG_DECIMAL_MAX_HUGO)
            .localDateHugo(UPDATED_LOCAL_DATE_HUGO)
            .localDateRequiredHugo(UPDATED_LOCAL_DATE_REQUIRED_HUGO)
            .instantHugo(UPDATED_INSTANT_HUGO)
            .instanteRequiredHugo(UPDATED_INSTANTE_REQUIRED_HUGO)
            .zonedDateTimeHugo(UPDATED_ZONED_DATE_TIME_HUGO)
            .zonedDateTimeRequiredHugo(UPDATED_ZONED_DATE_TIME_REQUIRED_HUGO)
            .booleanHugo(UPDATED_BOOLEAN_HUGO)
            .booleanRequiredHugo(UPDATED_BOOLEAN_REQUIRED_HUGO)
            .enumHugo(UPDATED_ENUM_HUGO)
            .enumRequiredHugo(UPDATED_ENUM_REQUIRED_HUGO)
            .byteImageHugo(UPDATED_BYTE_IMAGE_HUGO)
            .byteImageHugoContentType(UPDATED_BYTE_IMAGE_HUGO_CONTENT_TYPE)
            .byteImageRequiredHugo(UPDATED_BYTE_IMAGE_REQUIRED_HUGO)
            .byteImageRequiredHugoContentType(UPDATED_BYTE_IMAGE_REQUIRED_HUGO_CONTENT_TYPE)
            .byteImageMinbytesHugo(UPDATED_BYTE_IMAGE_MINBYTES_HUGO)
            .byteImageMinbytesHugoContentType(UPDATED_BYTE_IMAGE_MINBYTES_HUGO_CONTENT_TYPE)
            .byteImageMaxbytesHugo(UPDATED_BYTE_IMAGE_MAXBYTES_HUGO)
            .byteImageMaxbytesHugoContentType(UPDATED_BYTE_IMAGE_MAXBYTES_HUGO_CONTENT_TYPE)
            .byteAnyHugo(UPDATED_BYTE_ANY_HUGO)
            .byteAnyHugoContentType(UPDATED_BYTE_ANY_HUGO_CONTENT_TYPE)
            .byteAnyRequiredHugo(UPDATED_BYTE_ANY_REQUIRED_HUGO)
            .byteAnyRequiredHugoContentType(UPDATED_BYTE_ANY_REQUIRED_HUGO_CONTENT_TYPE)
            .byteAnyMinbytesHugo(UPDATED_BYTE_ANY_MINBYTES_HUGO)
            .byteAnyMinbytesHugoContentType(UPDATED_BYTE_ANY_MINBYTES_HUGO_CONTENT_TYPE)
            .byteAnyMaxbytesHugo(UPDATED_BYTE_ANY_MAXBYTES_HUGO)
            .byteAnyMaxbytesHugoContentType(UPDATED_BYTE_ANY_MAXBYTES_HUGO_CONTENT_TYPE)
            .byteTextHugo(UPDATED_BYTE_TEXT_HUGO)
            .byteTextRequiredHugo(UPDATED_BYTE_TEXT_REQUIRED_HUGO)
            .byteTextMinbytesHugo(UPDATED_BYTE_TEXT_MINBYTES_HUGO)
            .byteTextMaxbytesHugo(UPDATED_BYTE_TEXT_MAXBYTES_HUGO);

        restFieldTestInfiniteScrollEntityMockMvc.perform(put("/api/field-test-infinite-scroll-entities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedFieldTestInfiniteScrollEntity)))
            .andExpect(status().isOk());

        // Validate the FieldTestInfiniteScrollEntity in the database
        List<FieldTestInfiniteScrollEntity> fieldTestInfiniteScrollEntityList = fieldTestInfiniteScrollEntityRepository.findAll();
        assertThat(fieldTestInfiniteScrollEntityList).hasSize(databaseSizeBeforeUpdate);
        FieldTestInfiniteScrollEntity testFieldTestInfiniteScrollEntity = fieldTestInfiniteScrollEntityList.get(fieldTestInfiniteScrollEntityList.size() - 1);
        assertThat(testFieldTestInfiniteScrollEntity.getStringHugo()).isEqualTo(UPDATED_STRING_HUGO);
        assertThat(testFieldTestInfiniteScrollEntity.getStringRequiredHugo()).isEqualTo(UPDATED_STRING_REQUIRED_HUGO);
        assertThat(testFieldTestInfiniteScrollEntity.getStringMinlengthHugo()).isEqualTo(UPDATED_STRING_MINLENGTH_HUGO);
        assertThat(testFieldTestInfiniteScrollEntity.getStringMaxlengthHugo()).isEqualTo(UPDATED_STRING_MAXLENGTH_HUGO);
        assertThat(testFieldTestInfiniteScrollEntity.getStringPatternHugo()).isEqualTo(UPDATED_STRING_PATTERN_HUGO);
        assertThat(testFieldTestInfiniteScrollEntity.getIntegerHugo()).isEqualTo(UPDATED_INTEGER_HUGO);
        assertThat(testFieldTestInfiniteScrollEntity.getIntegerRequiredHugo()).isEqualTo(UPDATED_INTEGER_REQUIRED_HUGO);
        assertThat(testFieldTestInfiniteScrollEntity.getIntegerMinHugo()).isEqualTo(UPDATED_INTEGER_MIN_HUGO);
        assertThat(testFieldTestInfiniteScrollEntity.getIntegerMaxHugo()).isEqualTo(UPDATED_INTEGER_MAX_HUGO);
        assertThat(testFieldTestInfiniteScrollEntity.getLongHugo()).isEqualTo(UPDATED_LONG_HUGO);
        assertThat(testFieldTestInfiniteScrollEntity.getLongRequiredHugo()).isEqualTo(UPDATED_LONG_REQUIRED_HUGO);
        assertThat(testFieldTestInfiniteScrollEntity.getLongMinHugo()).isEqualTo(UPDATED_LONG_MIN_HUGO);
        assertThat(testFieldTestInfiniteScrollEntity.getLongMaxHugo()).isEqualTo(UPDATED_LONG_MAX_HUGO);
        assertThat(testFieldTestInfiniteScrollEntity.getFloatHugo()).isEqualTo(UPDATED_FLOAT_HUGO);
        assertThat(testFieldTestInfiniteScrollEntity.getFloatRequiredHugo()).isEqualTo(UPDATED_FLOAT_REQUIRED_HUGO);
        assertThat(testFieldTestInfiniteScrollEntity.getFloatMinHugo()).isEqualTo(UPDATED_FLOAT_MIN_HUGO);
        assertThat(testFieldTestInfiniteScrollEntity.getFloatMaxHugo()).isEqualTo(UPDATED_FLOAT_MAX_HUGO);
        assertThat(testFieldTestInfiniteScrollEntity.getDoubleRequiredHugo()).isEqualTo(UPDATED_DOUBLE_REQUIRED_HUGO);
        assertThat(testFieldTestInfiniteScrollEntity.getDoubleMinHugo()).isEqualTo(UPDATED_DOUBLE_MIN_HUGO);
        assertThat(testFieldTestInfiniteScrollEntity.getDoubleMaxHugo()).isEqualTo(UPDATED_DOUBLE_MAX_HUGO);
        assertThat(testFieldTestInfiniteScrollEntity.getBigDecimalRequiredHugo()).isEqualTo(UPDATED_BIG_DECIMAL_REQUIRED_HUGO);
        assertThat(testFieldTestInfiniteScrollEntity.getBigDecimalMinHugo()).isEqualTo(UPDATED_BIG_DECIMAL_MIN_HUGO);
        assertThat(testFieldTestInfiniteScrollEntity.getBigDecimalMaxHugo()).isEqualTo(UPDATED_BIG_DECIMAL_MAX_HUGO);
        assertThat(testFieldTestInfiniteScrollEntity.getLocalDateHugo()).isEqualTo(UPDATED_LOCAL_DATE_HUGO);
        assertThat(testFieldTestInfiniteScrollEntity.getLocalDateRequiredHugo()).isEqualTo(UPDATED_LOCAL_DATE_REQUIRED_HUGO);
        assertThat(testFieldTestInfiniteScrollEntity.getInstantHugo()).isEqualTo(UPDATED_INSTANT_HUGO);
        assertThat(testFieldTestInfiniteScrollEntity.getInstanteRequiredHugo()).isEqualTo(UPDATED_INSTANTE_REQUIRED_HUGO);
        assertThat(testFieldTestInfiniteScrollEntity.getZonedDateTimeHugo()).isEqualTo(UPDATED_ZONED_DATE_TIME_HUGO);
        assertThat(testFieldTestInfiniteScrollEntity.getZonedDateTimeRequiredHugo()).isEqualTo(UPDATED_ZONED_DATE_TIME_REQUIRED_HUGO);
        assertThat(testFieldTestInfiniteScrollEntity.isBooleanHugo()).isEqualTo(UPDATED_BOOLEAN_HUGO);
        assertThat(testFieldTestInfiniteScrollEntity.isBooleanRequiredHugo()).isEqualTo(UPDATED_BOOLEAN_REQUIRED_HUGO);
        assertThat(testFieldTestInfiniteScrollEntity.getEnumHugo()).isEqualTo(UPDATED_ENUM_HUGO);
        assertThat(testFieldTestInfiniteScrollEntity.getEnumRequiredHugo()).isEqualTo(UPDATED_ENUM_REQUIRED_HUGO);
        assertThat(testFieldTestInfiniteScrollEntity.getByteImageHugo()).isEqualTo(UPDATED_BYTE_IMAGE_HUGO);
        assertThat(testFieldTestInfiniteScrollEntity.getByteImageHugoContentType()).isEqualTo(UPDATED_BYTE_IMAGE_HUGO_CONTENT_TYPE);
        assertThat(testFieldTestInfiniteScrollEntity.getByteImageRequiredHugo()).isEqualTo(UPDATED_BYTE_IMAGE_REQUIRED_HUGO);
        assertThat(testFieldTestInfiniteScrollEntity.getByteImageRequiredHugoContentType()).isEqualTo(UPDATED_BYTE_IMAGE_REQUIRED_HUGO_CONTENT_TYPE);
        assertThat(testFieldTestInfiniteScrollEntity.getByteImageMinbytesHugo()).isEqualTo(UPDATED_BYTE_IMAGE_MINBYTES_HUGO);
        assertThat(testFieldTestInfiniteScrollEntity.getByteImageMinbytesHugoContentType()).isEqualTo(UPDATED_BYTE_IMAGE_MINBYTES_HUGO_CONTENT_TYPE);
        assertThat(testFieldTestInfiniteScrollEntity.getByteImageMaxbytesHugo()).isEqualTo(UPDATED_BYTE_IMAGE_MAXBYTES_HUGO);
        assertThat(testFieldTestInfiniteScrollEntity.getByteImageMaxbytesHugoContentType()).isEqualTo(UPDATED_BYTE_IMAGE_MAXBYTES_HUGO_CONTENT_TYPE);
        assertThat(testFieldTestInfiniteScrollEntity.getByteAnyHugo()).isEqualTo(UPDATED_BYTE_ANY_HUGO);
        assertThat(testFieldTestInfiniteScrollEntity.getByteAnyHugoContentType()).isEqualTo(UPDATED_BYTE_ANY_HUGO_CONTENT_TYPE);
        assertThat(testFieldTestInfiniteScrollEntity.getByteAnyRequiredHugo()).isEqualTo(UPDATED_BYTE_ANY_REQUIRED_HUGO);
        assertThat(testFieldTestInfiniteScrollEntity.getByteAnyRequiredHugoContentType()).isEqualTo(UPDATED_BYTE_ANY_REQUIRED_HUGO_CONTENT_TYPE);
        assertThat(testFieldTestInfiniteScrollEntity.getByteAnyMinbytesHugo()).isEqualTo(UPDATED_BYTE_ANY_MINBYTES_HUGO);
        assertThat(testFieldTestInfiniteScrollEntity.getByteAnyMinbytesHugoContentType()).isEqualTo(UPDATED_BYTE_ANY_MINBYTES_HUGO_CONTENT_TYPE);
        assertThat(testFieldTestInfiniteScrollEntity.getByteAnyMaxbytesHugo()).isEqualTo(UPDATED_BYTE_ANY_MAXBYTES_HUGO);
        assertThat(testFieldTestInfiniteScrollEntity.getByteAnyMaxbytesHugoContentType()).isEqualTo(UPDATED_BYTE_ANY_MAXBYTES_HUGO_CONTENT_TYPE);
        assertThat(testFieldTestInfiniteScrollEntity.getByteTextHugo()).isEqualTo(UPDATED_BYTE_TEXT_HUGO);
        assertThat(testFieldTestInfiniteScrollEntity.getByteTextRequiredHugo()).isEqualTo(UPDATED_BYTE_TEXT_REQUIRED_HUGO);
        assertThat(testFieldTestInfiniteScrollEntity.getByteTextMinbytesHugo()).isEqualTo(UPDATED_BYTE_TEXT_MINBYTES_HUGO);
        assertThat(testFieldTestInfiniteScrollEntity.getByteTextMaxbytesHugo()).isEqualTo(UPDATED_BYTE_TEXT_MAXBYTES_HUGO);

        // Validate the FieldTestInfiniteScrollEntity in Elasticsearch
        FieldTestInfiniteScrollEntity fieldTestInfiniteScrollEntityEs = fieldTestInfiniteScrollEntitySearchRepository.findOne(testFieldTestInfiniteScrollEntity.getId());
        assertThat(fieldTestInfiniteScrollEntityEs).isEqualToComparingFieldByField(testFieldTestInfiniteScrollEntity);
    }

    @Test
    @Transactional
    public void updateNonExistingFieldTestInfiniteScrollEntity() throws Exception {
        int databaseSizeBeforeUpdate = fieldTestInfiniteScrollEntityRepository.findAll().size();

        // Create the FieldTestInfiniteScrollEntity

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restFieldTestInfiniteScrollEntityMockMvc.perform(put("/api/field-test-infinite-scroll-entities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fieldTestInfiniteScrollEntity)))
            .andExpect(status().isCreated());

        // Validate the FieldTestInfiniteScrollEntity in the database
        List<FieldTestInfiniteScrollEntity> fieldTestInfiniteScrollEntityList = fieldTestInfiniteScrollEntityRepository.findAll();
        assertThat(fieldTestInfiniteScrollEntityList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteFieldTestInfiniteScrollEntity() throws Exception {
        // Initialize the database
        fieldTestInfiniteScrollEntityRepository.saveAndFlush(fieldTestInfiniteScrollEntity);
        fieldTestInfiniteScrollEntitySearchRepository.save(fieldTestInfiniteScrollEntity);
        int databaseSizeBeforeDelete = fieldTestInfiniteScrollEntityRepository.findAll().size();

        // Get the fieldTestInfiniteScrollEntity
        restFieldTestInfiniteScrollEntityMockMvc.perform(delete("/api/field-test-infinite-scroll-entities/{id}", fieldTestInfiniteScrollEntity.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate Elasticsearch is empty
        boolean fieldTestInfiniteScrollEntityExistsInEs = fieldTestInfiniteScrollEntitySearchRepository.exists(fieldTestInfiniteScrollEntity.getId());
        assertThat(fieldTestInfiniteScrollEntityExistsInEs).isFalse();

        // Validate the database is empty
        List<FieldTestInfiniteScrollEntity> fieldTestInfiniteScrollEntityList = fieldTestInfiniteScrollEntityRepository.findAll();
        assertThat(fieldTestInfiniteScrollEntityList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void searchFieldTestInfiniteScrollEntity() throws Exception {
        // Initialize the database
        fieldTestInfiniteScrollEntityRepository.saveAndFlush(fieldTestInfiniteScrollEntity);
        fieldTestInfiniteScrollEntitySearchRepository.save(fieldTestInfiniteScrollEntity);

        // Search the fieldTestInfiniteScrollEntity
        restFieldTestInfiniteScrollEntityMockMvc.perform(get("/api/_search/field-test-infinite-scroll-entities?query=id:" + fieldTestInfiniteScrollEntity.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(fieldTestInfiniteScrollEntity.getId().intValue())))
            .andExpect(jsonPath("$.[*].stringHugo").value(hasItem(DEFAULT_STRING_HUGO.toString())))
            .andExpect(jsonPath("$.[*].stringRequiredHugo").value(hasItem(DEFAULT_STRING_REQUIRED_HUGO.toString())))
            .andExpect(jsonPath("$.[*].stringMinlengthHugo").value(hasItem(DEFAULT_STRING_MINLENGTH_HUGO.toString())))
            .andExpect(jsonPath("$.[*].stringMaxlengthHugo").value(hasItem(DEFAULT_STRING_MAXLENGTH_HUGO.toString())))
            .andExpect(jsonPath("$.[*].stringPatternHugo").value(hasItem(DEFAULT_STRING_PATTERN_HUGO.toString())))
            .andExpect(jsonPath("$.[*].integerHugo").value(hasItem(DEFAULT_INTEGER_HUGO)))
            .andExpect(jsonPath("$.[*].integerRequiredHugo").value(hasItem(DEFAULT_INTEGER_REQUIRED_HUGO)))
            .andExpect(jsonPath("$.[*].integerMinHugo").value(hasItem(DEFAULT_INTEGER_MIN_HUGO)))
            .andExpect(jsonPath("$.[*].integerMaxHugo").value(hasItem(DEFAULT_INTEGER_MAX_HUGO)))
            .andExpect(jsonPath("$.[*].longHugo").value(hasItem(DEFAULT_LONG_HUGO.intValue())))
            .andExpect(jsonPath("$.[*].longRequiredHugo").value(hasItem(DEFAULT_LONG_REQUIRED_HUGO.intValue())))
            .andExpect(jsonPath("$.[*].longMinHugo").value(hasItem(DEFAULT_LONG_MIN_HUGO.intValue())))
            .andExpect(jsonPath("$.[*].longMaxHugo").value(hasItem(DEFAULT_LONG_MAX_HUGO.intValue())))
            .andExpect(jsonPath("$.[*].floatHugo").value(hasItem(DEFAULT_FLOAT_HUGO.doubleValue())))
            .andExpect(jsonPath("$.[*].floatRequiredHugo").value(hasItem(DEFAULT_FLOAT_REQUIRED_HUGO.doubleValue())))
            .andExpect(jsonPath("$.[*].floatMinHugo").value(hasItem(DEFAULT_FLOAT_MIN_HUGO.doubleValue())))
            .andExpect(jsonPath("$.[*].floatMaxHugo").value(hasItem(DEFAULT_FLOAT_MAX_HUGO.doubleValue())))
            .andExpect(jsonPath("$.[*].doubleRequiredHugo").value(hasItem(DEFAULT_DOUBLE_REQUIRED_HUGO.doubleValue())))
            .andExpect(jsonPath("$.[*].doubleMinHugo").value(hasItem(DEFAULT_DOUBLE_MIN_HUGO.doubleValue())))
            .andExpect(jsonPath("$.[*].doubleMaxHugo").value(hasItem(DEFAULT_DOUBLE_MAX_HUGO.doubleValue())))
            .andExpect(jsonPath("$.[*].bigDecimalRequiredHugo").value(hasItem(DEFAULT_BIG_DECIMAL_REQUIRED_HUGO.intValue())))
            .andExpect(jsonPath("$.[*].bigDecimalMinHugo").value(hasItem(DEFAULT_BIG_DECIMAL_MIN_HUGO.intValue())))
            .andExpect(jsonPath("$.[*].bigDecimalMaxHugo").value(hasItem(DEFAULT_BIG_DECIMAL_MAX_HUGO.intValue())))
            .andExpect(jsonPath("$.[*].localDateHugo").value(hasItem(DEFAULT_LOCAL_DATE_HUGO.toString())))
            .andExpect(jsonPath("$.[*].localDateRequiredHugo").value(hasItem(DEFAULT_LOCAL_DATE_REQUIRED_HUGO.toString())))
            .andExpect(jsonPath("$.[*].instantHugo").value(hasItem(DEFAULT_INSTANT_HUGO.toString())))
            .andExpect(jsonPath("$.[*].instanteRequiredHugo").value(hasItem(DEFAULT_INSTANTE_REQUIRED_HUGO.toString())))
            .andExpect(jsonPath("$.[*].zonedDateTimeHugo").value(hasItem(sameInstant(DEFAULT_ZONED_DATE_TIME_HUGO))))
            .andExpect(jsonPath("$.[*].zonedDateTimeRequiredHugo").value(hasItem(sameInstant(DEFAULT_ZONED_DATE_TIME_REQUIRED_HUGO))))
            .andExpect(jsonPath("$.[*].booleanHugo").value(hasItem(DEFAULT_BOOLEAN_HUGO.booleanValue())))
            .andExpect(jsonPath("$.[*].booleanRequiredHugo").value(hasItem(DEFAULT_BOOLEAN_REQUIRED_HUGO.booleanValue())))
            .andExpect(jsonPath("$.[*].enumHugo").value(hasItem(DEFAULT_ENUM_HUGO.toString())))
            .andExpect(jsonPath("$.[*].enumRequiredHugo").value(hasItem(DEFAULT_ENUM_REQUIRED_HUGO.toString())))
            .andExpect(jsonPath("$.[*].byteImageHugoContentType").value(hasItem(DEFAULT_BYTE_IMAGE_HUGO_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].byteImageHugo").value(hasItem(Base64Utils.encodeToString(DEFAULT_BYTE_IMAGE_HUGO))))
            .andExpect(jsonPath("$.[*].byteImageRequiredHugoContentType").value(hasItem(DEFAULT_BYTE_IMAGE_REQUIRED_HUGO_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].byteImageRequiredHugo").value(hasItem(Base64Utils.encodeToString(DEFAULT_BYTE_IMAGE_REQUIRED_HUGO))))
            .andExpect(jsonPath("$.[*].byteImageMinbytesHugoContentType").value(hasItem(DEFAULT_BYTE_IMAGE_MINBYTES_HUGO_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].byteImageMinbytesHugo").value(hasItem(Base64Utils.encodeToString(DEFAULT_BYTE_IMAGE_MINBYTES_HUGO))))
            .andExpect(jsonPath("$.[*].byteImageMaxbytesHugoContentType").value(hasItem(DEFAULT_BYTE_IMAGE_MAXBYTES_HUGO_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].byteImageMaxbytesHugo").value(hasItem(Base64Utils.encodeToString(DEFAULT_BYTE_IMAGE_MAXBYTES_HUGO))))
            .andExpect(jsonPath("$.[*].byteAnyHugoContentType").value(hasItem(DEFAULT_BYTE_ANY_HUGO_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].byteAnyHugo").value(hasItem(Base64Utils.encodeToString(DEFAULT_BYTE_ANY_HUGO))))
            .andExpect(jsonPath("$.[*].byteAnyRequiredHugoContentType").value(hasItem(DEFAULT_BYTE_ANY_REQUIRED_HUGO_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].byteAnyRequiredHugo").value(hasItem(Base64Utils.encodeToString(DEFAULT_BYTE_ANY_REQUIRED_HUGO))))
            .andExpect(jsonPath("$.[*].byteAnyMinbytesHugoContentType").value(hasItem(DEFAULT_BYTE_ANY_MINBYTES_HUGO_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].byteAnyMinbytesHugo").value(hasItem(Base64Utils.encodeToString(DEFAULT_BYTE_ANY_MINBYTES_HUGO))))
            .andExpect(jsonPath("$.[*].byteAnyMaxbytesHugoContentType").value(hasItem(DEFAULT_BYTE_ANY_MAXBYTES_HUGO_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].byteAnyMaxbytesHugo").value(hasItem(Base64Utils.encodeToString(DEFAULT_BYTE_ANY_MAXBYTES_HUGO))))
            .andExpect(jsonPath("$.[*].byteTextHugo").value(hasItem(DEFAULT_BYTE_TEXT_HUGO.toString())))
            .andExpect(jsonPath("$.[*].byteTextRequiredHugo").value(hasItem(DEFAULT_BYTE_TEXT_REQUIRED_HUGO.toString())))
            .andExpect(jsonPath("$.[*].byteTextMinbytesHugo").value(hasItem(DEFAULT_BYTE_TEXT_MINBYTES_HUGO.toString())))
            .andExpect(jsonPath("$.[*].byteTextMaxbytesHugo").value(hasItem(DEFAULT_BYTE_TEXT_MAXBYTES_HUGO.toString())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(FieldTestInfiniteScrollEntity.class);
        FieldTestInfiniteScrollEntity fieldTestInfiniteScrollEntity1 = new FieldTestInfiniteScrollEntity();
        fieldTestInfiniteScrollEntity1.setId(1L);
        FieldTestInfiniteScrollEntity fieldTestInfiniteScrollEntity2 = new FieldTestInfiniteScrollEntity();
        fieldTestInfiniteScrollEntity2.setId(fieldTestInfiniteScrollEntity1.getId());
        assertThat(fieldTestInfiniteScrollEntity1).isEqualTo(fieldTestInfiniteScrollEntity2);
        fieldTestInfiniteScrollEntity2.setId(2L);
        assertThat(fieldTestInfiniteScrollEntity1).isNotEqualTo(fieldTestInfiniteScrollEntity2);
        fieldTestInfiniteScrollEntity1.setId(null);
        assertThat(fieldTestInfiniteScrollEntity1).isNotEqualTo(fieldTestInfiniteScrollEntity2);
    }
}
