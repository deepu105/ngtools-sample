package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.Jh4TestApp;

import com.mycompany.myapp.domain.FieldTestServiceImplEntity;
import com.mycompany.myapp.repository.FieldTestServiceImplEntityRepository;
import com.mycompany.myapp.service.FieldTestServiceImplEntityService;
import com.mycompany.myapp.repository.search.FieldTestServiceImplEntitySearchRepository;
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
 * Test class for the FieldTestServiceImplEntityResource REST controller.
 *
 * @see FieldTestServiceImplEntityResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Jh4TestApp.class)
public class FieldTestServiceImplEntityResourceIntTest {

    private static final String DEFAULT_STRING_MIKA = "AAAAAAAAAA";
    private static final String UPDATED_STRING_MIKA = "BBBBBBBBBB";

    private static final String DEFAULT_STRING_REQUIRED_MIKA = "AAAAAAAAAA";
    private static final String UPDATED_STRING_REQUIRED_MIKA = "BBBBBBBBBB";

    private static final String DEFAULT_STRING_MINLENGTH_MIKA = "AAAAAAAAAA";
    private static final String UPDATED_STRING_MINLENGTH_MIKA = "BBBBBBBBBB";

    private static final String DEFAULT_STRING_MAXLENGTH_MIKA = "AAAAAAAAAA";
    private static final String UPDATED_STRING_MAXLENGTH_MIKA = "BBBBBBBBBB";

    private static final String DEFAULT_STRING_PATTERN_MIKA = "AAAAAAAAAA";
    private static final String UPDATED_STRING_PATTERN_MIKA = "BBBBBBBBBB";

    private static final Integer DEFAULT_INTEGER_MIKA = 1;
    private static final Integer UPDATED_INTEGER_MIKA = 2;

    private static final Integer DEFAULT_INTEGER_REQUIRED_MIKA = 1;
    private static final Integer UPDATED_INTEGER_REQUIRED_MIKA = 2;

    private static final Integer DEFAULT_INTEGER_MIN_MIKA = 0;
    private static final Integer UPDATED_INTEGER_MIN_MIKA = 1;

    private static final Integer DEFAULT_INTEGER_MAX_MIKA = 100;
    private static final Integer UPDATED_INTEGER_MAX_MIKA = 99;

    private static final Long DEFAULT_LONG_MIKA = 1L;
    private static final Long UPDATED_LONG_MIKA = 2L;

    private static final Long DEFAULT_LONG_REQUIRED_MIKA = 1L;
    private static final Long UPDATED_LONG_REQUIRED_MIKA = 2L;

    private static final Long DEFAULT_LONG_MIN_MIKA = 0L;
    private static final Long UPDATED_LONG_MIN_MIKA = 1L;

    private static final Long DEFAULT_LONG_MAX_MIKA = 100L;
    private static final Long UPDATED_LONG_MAX_MIKA = 99L;

    private static final Float DEFAULT_FLOAT_MIKA = 1F;
    private static final Float UPDATED_FLOAT_MIKA = 2F;

    private static final Float DEFAULT_FLOAT_REQUIRED_MIKA = 1F;
    private static final Float UPDATED_FLOAT_REQUIRED_MIKA = 2F;

    private static final Float DEFAULT_FLOAT_MIN_MIKA = 0F;
    private static final Float UPDATED_FLOAT_MIN_MIKA = 1F;

    private static final Float DEFAULT_FLOAT_MAX_MIKA = 100F;
    private static final Float UPDATED_FLOAT_MAX_MIKA = 99F;

    private static final Double DEFAULT_DOUBLE_REQUIRED_MIKA = 1D;
    private static final Double UPDATED_DOUBLE_REQUIRED_MIKA = 2D;

    private static final Double DEFAULT_DOUBLE_MIN_MIKA = 0D;
    private static final Double UPDATED_DOUBLE_MIN_MIKA = 1D;

    private static final Double DEFAULT_DOUBLE_MAX_MIKA = 100D;
    private static final Double UPDATED_DOUBLE_MAX_MIKA = 99D;

    private static final BigDecimal DEFAULT_BIG_DECIMAL_REQUIRED_MIKA = new BigDecimal(1);
    private static final BigDecimal UPDATED_BIG_DECIMAL_REQUIRED_MIKA = new BigDecimal(2);

    private static final BigDecimal DEFAULT_BIG_DECIMAL_MIN_MIKA = new BigDecimal(0);
    private static final BigDecimal UPDATED_BIG_DECIMAL_MIN_MIKA = new BigDecimal(1);

    private static final BigDecimal DEFAULT_BIG_DECIMAL_MAX_MIKA = new BigDecimal(100);
    private static final BigDecimal UPDATED_BIG_DECIMAL_MAX_MIKA = new BigDecimal(99);

    private static final LocalDate DEFAULT_LOCAL_DATE_MIKA = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_LOCAL_DATE_MIKA = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_LOCAL_DATE_REQUIRED_MIKA = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_LOCAL_DATE_REQUIRED_MIKA = LocalDate.now(ZoneId.systemDefault());

    private static final Instant DEFAULT_INSTANT_MIKA = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_INSTANT_MIKA = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_INSTANTE_REQUIRED_MIKA = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_INSTANTE_REQUIRED_MIKA = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final ZonedDateTime DEFAULT_ZONED_DATE_TIME_MIKA = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_ZONED_DATE_TIME_MIKA = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_ZONED_DATE_TIME_REQUIRED_MIKA = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_ZONED_DATE_TIME_REQUIRED_MIKA = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final Boolean DEFAULT_BOOLEAN_MIKA = false;
    private static final Boolean UPDATED_BOOLEAN_MIKA = true;

    private static final Boolean DEFAULT_BOOLEAN_REQUIRED_MIKA = false;
    private static final Boolean UPDATED_BOOLEAN_REQUIRED_MIKA = true;

    private static final EnumFieldClass DEFAULT_ENUM_MIKA = EnumFieldClass.ENUM_VALUE_1;
    private static final EnumFieldClass UPDATED_ENUM_MIKA = EnumFieldClass.ENUM_VALUE_2;

    private static final EnumRequiredFieldClass DEFAULT_ENUM_REQUIRED_MIKA = EnumRequiredFieldClass.ENUM_VALUE_1;
    private static final EnumRequiredFieldClass UPDATED_ENUM_REQUIRED_MIKA = EnumRequiredFieldClass.ENUM_VALUE_2;

    private static final byte[] DEFAULT_BYTE_IMAGE_MIKA = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_BYTE_IMAGE_MIKA = TestUtil.createByteArray(2, "1");
    private static final String DEFAULT_BYTE_IMAGE_MIKA_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_BYTE_IMAGE_MIKA_CONTENT_TYPE = "image/png";

    private static final byte[] DEFAULT_BYTE_IMAGE_REQUIRED_MIKA = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_BYTE_IMAGE_REQUIRED_MIKA = TestUtil.createByteArray(2, "1");
    private static final String DEFAULT_BYTE_IMAGE_REQUIRED_MIKA_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_BYTE_IMAGE_REQUIRED_MIKA_CONTENT_TYPE = "image/png";

    private static final byte[] DEFAULT_BYTE_IMAGE_MINBYTES_MIKA = TestUtil.createByteArray(0, "0");
    private static final byte[] UPDATED_BYTE_IMAGE_MINBYTES_MIKA = TestUtil.createByteArray(0, "1");
    private static final String DEFAULT_BYTE_IMAGE_MINBYTES_MIKA_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_BYTE_IMAGE_MINBYTES_MIKA_CONTENT_TYPE = "image/png";

    private static final byte[] DEFAULT_BYTE_IMAGE_MAXBYTES_MIKA = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_BYTE_IMAGE_MAXBYTES_MIKA = TestUtil.createByteArray(10000, "1");
    private static final String DEFAULT_BYTE_IMAGE_MAXBYTES_MIKA_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_BYTE_IMAGE_MAXBYTES_MIKA_CONTENT_TYPE = "image/png";

    private static final byte[] DEFAULT_BYTE_ANY_MIKA = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_BYTE_ANY_MIKA = TestUtil.createByteArray(2, "1");
    private static final String DEFAULT_BYTE_ANY_MIKA_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_BYTE_ANY_MIKA_CONTENT_TYPE = "image/png";

    private static final byte[] DEFAULT_BYTE_ANY_REQUIRED_MIKA = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_BYTE_ANY_REQUIRED_MIKA = TestUtil.createByteArray(2, "1");
    private static final String DEFAULT_BYTE_ANY_REQUIRED_MIKA_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_BYTE_ANY_REQUIRED_MIKA_CONTENT_TYPE = "image/png";

    private static final byte[] DEFAULT_BYTE_ANY_MINBYTES_MIKA = TestUtil.createByteArray(0, "0");
    private static final byte[] UPDATED_BYTE_ANY_MINBYTES_MIKA = TestUtil.createByteArray(0, "1");
    private static final String DEFAULT_BYTE_ANY_MINBYTES_MIKA_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_BYTE_ANY_MINBYTES_MIKA_CONTENT_TYPE = "image/png";

    private static final byte[] DEFAULT_BYTE_ANY_MAXBYTES_MIKA = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_BYTE_ANY_MAXBYTES_MIKA = TestUtil.createByteArray(10000, "1");
    private static final String DEFAULT_BYTE_ANY_MAXBYTES_MIKA_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_BYTE_ANY_MAXBYTES_MIKA_CONTENT_TYPE = "image/png";

    private static final String DEFAULT_BYTE_TEXT_MIKA = "AAAAAAAAAA";
    private static final String UPDATED_BYTE_TEXT_MIKA = "BBBBBBBBBB";

    private static final String DEFAULT_BYTE_TEXT_REQUIRED_MIKA = "AAAAAAAAAA";
    private static final String UPDATED_BYTE_TEXT_REQUIRED_MIKA = "BBBBBBBBBB";

    private static final String DEFAULT_BYTE_TEXT_MINBYTES_MIKA = "AAAAAAAAAA";
    private static final String UPDATED_BYTE_TEXT_MINBYTES_MIKA = "BBBBBBBBBB";

    private static final String DEFAULT_BYTE_TEXT_MAXBYTES_MIKA = "AAAAAAAAAA";
    private static final String UPDATED_BYTE_TEXT_MAXBYTES_MIKA = "BBBBBBBBBB";

    @Autowired
    private FieldTestServiceImplEntityRepository fieldTestServiceImplEntityRepository;

    @Autowired
    private FieldTestServiceImplEntityService fieldTestServiceImplEntityService;

    @Autowired
    private FieldTestServiceImplEntitySearchRepository fieldTestServiceImplEntitySearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restFieldTestServiceImplEntityMockMvc;

    private FieldTestServiceImplEntity fieldTestServiceImplEntity;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        FieldTestServiceImplEntityResource fieldTestServiceImplEntityResource = new FieldTestServiceImplEntityResource(fieldTestServiceImplEntityService);
        this.restFieldTestServiceImplEntityMockMvc = MockMvcBuilders.standaloneSetup(fieldTestServiceImplEntityResource)
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
    public static FieldTestServiceImplEntity createEntity(EntityManager em) {
        FieldTestServiceImplEntity fieldTestServiceImplEntity = new FieldTestServiceImplEntity()
            .stringMika(DEFAULT_STRING_MIKA)
            .stringRequiredMika(DEFAULT_STRING_REQUIRED_MIKA)
            .stringMinlengthMika(DEFAULT_STRING_MINLENGTH_MIKA)
            .stringMaxlengthMika(DEFAULT_STRING_MAXLENGTH_MIKA)
            .stringPatternMika(DEFAULT_STRING_PATTERN_MIKA)
            .integerMika(DEFAULT_INTEGER_MIKA)
            .integerRequiredMika(DEFAULT_INTEGER_REQUIRED_MIKA)
            .integerMinMika(DEFAULT_INTEGER_MIN_MIKA)
            .integerMaxMika(DEFAULT_INTEGER_MAX_MIKA)
            .longMika(DEFAULT_LONG_MIKA)
            .longRequiredMika(DEFAULT_LONG_REQUIRED_MIKA)
            .longMinMika(DEFAULT_LONG_MIN_MIKA)
            .longMaxMika(DEFAULT_LONG_MAX_MIKA)
            .floatMika(DEFAULT_FLOAT_MIKA)
            .floatRequiredMika(DEFAULT_FLOAT_REQUIRED_MIKA)
            .floatMinMika(DEFAULT_FLOAT_MIN_MIKA)
            .floatMaxMika(DEFAULT_FLOAT_MAX_MIKA)
            .doubleRequiredMika(DEFAULT_DOUBLE_REQUIRED_MIKA)
            .doubleMinMika(DEFAULT_DOUBLE_MIN_MIKA)
            .doubleMaxMika(DEFAULT_DOUBLE_MAX_MIKA)
            .bigDecimalRequiredMika(DEFAULT_BIG_DECIMAL_REQUIRED_MIKA)
            .bigDecimalMinMika(DEFAULT_BIG_DECIMAL_MIN_MIKA)
            .bigDecimalMaxMika(DEFAULT_BIG_DECIMAL_MAX_MIKA)
            .localDateMika(DEFAULT_LOCAL_DATE_MIKA)
            .localDateRequiredMika(DEFAULT_LOCAL_DATE_REQUIRED_MIKA)
            .instantMika(DEFAULT_INSTANT_MIKA)
            .instanteRequiredMika(DEFAULT_INSTANTE_REQUIRED_MIKA)
            .zonedDateTimeMika(DEFAULT_ZONED_DATE_TIME_MIKA)
            .zonedDateTimeRequiredMika(DEFAULT_ZONED_DATE_TIME_REQUIRED_MIKA)
            .booleanMika(DEFAULT_BOOLEAN_MIKA)
            .booleanRequiredMika(DEFAULT_BOOLEAN_REQUIRED_MIKA)
            .enumMika(DEFAULT_ENUM_MIKA)
            .enumRequiredMika(DEFAULT_ENUM_REQUIRED_MIKA)
            .byteImageMika(DEFAULT_BYTE_IMAGE_MIKA)
            .byteImageMikaContentType(DEFAULT_BYTE_IMAGE_MIKA_CONTENT_TYPE)
            .byteImageRequiredMika(DEFAULT_BYTE_IMAGE_REQUIRED_MIKA)
            .byteImageRequiredMikaContentType(DEFAULT_BYTE_IMAGE_REQUIRED_MIKA_CONTENT_TYPE)
            .byteImageMinbytesMika(DEFAULT_BYTE_IMAGE_MINBYTES_MIKA)
            .byteImageMinbytesMikaContentType(DEFAULT_BYTE_IMAGE_MINBYTES_MIKA_CONTENT_TYPE)
            .byteImageMaxbytesMika(DEFAULT_BYTE_IMAGE_MAXBYTES_MIKA)
            .byteImageMaxbytesMikaContentType(DEFAULT_BYTE_IMAGE_MAXBYTES_MIKA_CONTENT_TYPE)
            .byteAnyMika(DEFAULT_BYTE_ANY_MIKA)
            .byteAnyMikaContentType(DEFAULT_BYTE_ANY_MIKA_CONTENT_TYPE)
            .byteAnyRequiredMika(DEFAULT_BYTE_ANY_REQUIRED_MIKA)
            .byteAnyRequiredMikaContentType(DEFAULT_BYTE_ANY_REQUIRED_MIKA_CONTENT_TYPE)
            .byteAnyMinbytesMika(DEFAULT_BYTE_ANY_MINBYTES_MIKA)
            .byteAnyMinbytesMikaContentType(DEFAULT_BYTE_ANY_MINBYTES_MIKA_CONTENT_TYPE)
            .byteAnyMaxbytesMika(DEFAULT_BYTE_ANY_MAXBYTES_MIKA)
            .byteAnyMaxbytesMikaContentType(DEFAULT_BYTE_ANY_MAXBYTES_MIKA_CONTENT_TYPE)
            .byteTextMika(DEFAULT_BYTE_TEXT_MIKA)
            .byteTextRequiredMika(DEFAULT_BYTE_TEXT_REQUIRED_MIKA)
            .byteTextMinbytesMika(DEFAULT_BYTE_TEXT_MINBYTES_MIKA)
            .byteTextMaxbytesMika(DEFAULT_BYTE_TEXT_MAXBYTES_MIKA);
        return fieldTestServiceImplEntity;
    }

    @Before
    public void initTest() {
        fieldTestServiceImplEntitySearchRepository.deleteAll();
        fieldTestServiceImplEntity = createEntity(em);
    }

    @Test
    @Transactional
    public void createFieldTestServiceImplEntity() throws Exception {
        int databaseSizeBeforeCreate = fieldTestServiceImplEntityRepository.findAll().size();

        // Create the FieldTestServiceImplEntity
        restFieldTestServiceImplEntityMockMvc.perform(post("/api/field-test-service-impl-entities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fieldTestServiceImplEntity)))
            .andExpect(status().isCreated());

        // Validate the FieldTestServiceImplEntity in the database
        List<FieldTestServiceImplEntity> fieldTestServiceImplEntityList = fieldTestServiceImplEntityRepository.findAll();
        assertThat(fieldTestServiceImplEntityList).hasSize(databaseSizeBeforeCreate + 1);
        FieldTestServiceImplEntity testFieldTestServiceImplEntity = fieldTestServiceImplEntityList.get(fieldTestServiceImplEntityList.size() - 1);
        assertThat(testFieldTestServiceImplEntity.getStringMika()).isEqualTo(DEFAULT_STRING_MIKA);
        assertThat(testFieldTestServiceImplEntity.getStringRequiredMika()).isEqualTo(DEFAULT_STRING_REQUIRED_MIKA);
        assertThat(testFieldTestServiceImplEntity.getStringMinlengthMika()).isEqualTo(DEFAULT_STRING_MINLENGTH_MIKA);
        assertThat(testFieldTestServiceImplEntity.getStringMaxlengthMika()).isEqualTo(DEFAULT_STRING_MAXLENGTH_MIKA);
        assertThat(testFieldTestServiceImplEntity.getStringPatternMika()).isEqualTo(DEFAULT_STRING_PATTERN_MIKA);
        assertThat(testFieldTestServiceImplEntity.getIntegerMika()).isEqualTo(DEFAULT_INTEGER_MIKA);
        assertThat(testFieldTestServiceImplEntity.getIntegerRequiredMika()).isEqualTo(DEFAULT_INTEGER_REQUIRED_MIKA);
        assertThat(testFieldTestServiceImplEntity.getIntegerMinMika()).isEqualTo(DEFAULT_INTEGER_MIN_MIKA);
        assertThat(testFieldTestServiceImplEntity.getIntegerMaxMika()).isEqualTo(DEFAULT_INTEGER_MAX_MIKA);
        assertThat(testFieldTestServiceImplEntity.getLongMika()).isEqualTo(DEFAULT_LONG_MIKA);
        assertThat(testFieldTestServiceImplEntity.getLongRequiredMika()).isEqualTo(DEFAULT_LONG_REQUIRED_MIKA);
        assertThat(testFieldTestServiceImplEntity.getLongMinMika()).isEqualTo(DEFAULT_LONG_MIN_MIKA);
        assertThat(testFieldTestServiceImplEntity.getLongMaxMika()).isEqualTo(DEFAULT_LONG_MAX_MIKA);
        assertThat(testFieldTestServiceImplEntity.getFloatMika()).isEqualTo(DEFAULT_FLOAT_MIKA);
        assertThat(testFieldTestServiceImplEntity.getFloatRequiredMika()).isEqualTo(DEFAULT_FLOAT_REQUIRED_MIKA);
        assertThat(testFieldTestServiceImplEntity.getFloatMinMika()).isEqualTo(DEFAULT_FLOAT_MIN_MIKA);
        assertThat(testFieldTestServiceImplEntity.getFloatMaxMika()).isEqualTo(DEFAULT_FLOAT_MAX_MIKA);
        assertThat(testFieldTestServiceImplEntity.getDoubleRequiredMika()).isEqualTo(DEFAULT_DOUBLE_REQUIRED_MIKA);
        assertThat(testFieldTestServiceImplEntity.getDoubleMinMika()).isEqualTo(DEFAULT_DOUBLE_MIN_MIKA);
        assertThat(testFieldTestServiceImplEntity.getDoubleMaxMika()).isEqualTo(DEFAULT_DOUBLE_MAX_MIKA);
        assertThat(testFieldTestServiceImplEntity.getBigDecimalRequiredMika()).isEqualTo(DEFAULT_BIG_DECIMAL_REQUIRED_MIKA);
        assertThat(testFieldTestServiceImplEntity.getBigDecimalMinMika()).isEqualTo(DEFAULT_BIG_DECIMAL_MIN_MIKA);
        assertThat(testFieldTestServiceImplEntity.getBigDecimalMaxMika()).isEqualTo(DEFAULT_BIG_DECIMAL_MAX_MIKA);
        assertThat(testFieldTestServiceImplEntity.getLocalDateMika()).isEqualTo(DEFAULT_LOCAL_DATE_MIKA);
        assertThat(testFieldTestServiceImplEntity.getLocalDateRequiredMika()).isEqualTo(DEFAULT_LOCAL_DATE_REQUIRED_MIKA);
        assertThat(testFieldTestServiceImplEntity.getInstantMika()).isEqualTo(DEFAULT_INSTANT_MIKA);
        assertThat(testFieldTestServiceImplEntity.getInstanteRequiredMika()).isEqualTo(DEFAULT_INSTANTE_REQUIRED_MIKA);
        assertThat(testFieldTestServiceImplEntity.getZonedDateTimeMika()).isEqualTo(DEFAULT_ZONED_DATE_TIME_MIKA);
        assertThat(testFieldTestServiceImplEntity.getZonedDateTimeRequiredMika()).isEqualTo(DEFAULT_ZONED_DATE_TIME_REQUIRED_MIKA);
        assertThat(testFieldTestServiceImplEntity.isBooleanMika()).isEqualTo(DEFAULT_BOOLEAN_MIKA);
        assertThat(testFieldTestServiceImplEntity.isBooleanRequiredMika()).isEqualTo(DEFAULT_BOOLEAN_REQUIRED_MIKA);
        assertThat(testFieldTestServiceImplEntity.getEnumMika()).isEqualTo(DEFAULT_ENUM_MIKA);
        assertThat(testFieldTestServiceImplEntity.getEnumRequiredMika()).isEqualTo(DEFAULT_ENUM_REQUIRED_MIKA);
        assertThat(testFieldTestServiceImplEntity.getByteImageMika()).isEqualTo(DEFAULT_BYTE_IMAGE_MIKA);
        assertThat(testFieldTestServiceImplEntity.getByteImageMikaContentType()).isEqualTo(DEFAULT_BYTE_IMAGE_MIKA_CONTENT_TYPE);
        assertThat(testFieldTestServiceImplEntity.getByteImageRequiredMika()).isEqualTo(DEFAULT_BYTE_IMAGE_REQUIRED_MIKA);
        assertThat(testFieldTestServiceImplEntity.getByteImageRequiredMikaContentType()).isEqualTo(DEFAULT_BYTE_IMAGE_REQUIRED_MIKA_CONTENT_TYPE);
        assertThat(testFieldTestServiceImplEntity.getByteImageMinbytesMika()).isEqualTo(DEFAULT_BYTE_IMAGE_MINBYTES_MIKA);
        assertThat(testFieldTestServiceImplEntity.getByteImageMinbytesMikaContentType()).isEqualTo(DEFAULT_BYTE_IMAGE_MINBYTES_MIKA_CONTENT_TYPE);
        assertThat(testFieldTestServiceImplEntity.getByteImageMaxbytesMika()).isEqualTo(DEFAULT_BYTE_IMAGE_MAXBYTES_MIKA);
        assertThat(testFieldTestServiceImplEntity.getByteImageMaxbytesMikaContentType()).isEqualTo(DEFAULT_BYTE_IMAGE_MAXBYTES_MIKA_CONTENT_TYPE);
        assertThat(testFieldTestServiceImplEntity.getByteAnyMika()).isEqualTo(DEFAULT_BYTE_ANY_MIKA);
        assertThat(testFieldTestServiceImplEntity.getByteAnyMikaContentType()).isEqualTo(DEFAULT_BYTE_ANY_MIKA_CONTENT_TYPE);
        assertThat(testFieldTestServiceImplEntity.getByteAnyRequiredMika()).isEqualTo(DEFAULT_BYTE_ANY_REQUIRED_MIKA);
        assertThat(testFieldTestServiceImplEntity.getByteAnyRequiredMikaContentType()).isEqualTo(DEFAULT_BYTE_ANY_REQUIRED_MIKA_CONTENT_TYPE);
        assertThat(testFieldTestServiceImplEntity.getByteAnyMinbytesMika()).isEqualTo(DEFAULT_BYTE_ANY_MINBYTES_MIKA);
        assertThat(testFieldTestServiceImplEntity.getByteAnyMinbytesMikaContentType()).isEqualTo(DEFAULT_BYTE_ANY_MINBYTES_MIKA_CONTENT_TYPE);
        assertThat(testFieldTestServiceImplEntity.getByteAnyMaxbytesMika()).isEqualTo(DEFAULT_BYTE_ANY_MAXBYTES_MIKA);
        assertThat(testFieldTestServiceImplEntity.getByteAnyMaxbytesMikaContentType()).isEqualTo(DEFAULT_BYTE_ANY_MAXBYTES_MIKA_CONTENT_TYPE);
        assertThat(testFieldTestServiceImplEntity.getByteTextMika()).isEqualTo(DEFAULT_BYTE_TEXT_MIKA);
        assertThat(testFieldTestServiceImplEntity.getByteTextRequiredMika()).isEqualTo(DEFAULT_BYTE_TEXT_REQUIRED_MIKA);
        assertThat(testFieldTestServiceImplEntity.getByteTextMinbytesMika()).isEqualTo(DEFAULT_BYTE_TEXT_MINBYTES_MIKA);
        assertThat(testFieldTestServiceImplEntity.getByteTextMaxbytesMika()).isEqualTo(DEFAULT_BYTE_TEXT_MAXBYTES_MIKA);

        // Validate the FieldTestServiceImplEntity in Elasticsearch
        FieldTestServiceImplEntity fieldTestServiceImplEntityEs = fieldTestServiceImplEntitySearchRepository.findOne(testFieldTestServiceImplEntity.getId());
        assertThat(fieldTestServiceImplEntityEs).isEqualToComparingFieldByField(testFieldTestServiceImplEntity);
    }

    @Test
    @Transactional
    public void createFieldTestServiceImplEntityWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = fieldTestServiceImplEntityRepository.findAll().size();

        // Create the FieldTestServiceImplEntity with an existing ID
        fieldTestServiceImplEntity.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFieldTestServiceImplEntityMockMvc.perform(post("/api/field-test-service-impl-entities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fieldTestServiceImplEntity)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<FieldTestServiceImplEntity> fieldTestServiceImplEntityList = fieldTestServiceImplEntityRepository.findAll();
        assertThat(fieldTestServiceImplEntityList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkStringRequiredMikaIsRequired() throws Exception {
        int databaseSizeBeforeTest = fieldTestServiceImplEntityRepository.findAll().size();
        // set the field null
        fieldTestServiceImplEntity.setStringRequiredMika(null);

        // Create the FieldTestServiceImplEntity, which fails.

        restFieldTestServiceImplEntityMockMvc.perform(post("/api/field-test-service-impl-entities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fieldTestServiceImplEntity)))
            .andExpect(status().isBadRequest());

        List<FieldTestServiceImplEntity> fieldTestServiceImplEntityList = fieldTestServiceImplEntityRepository.findAll();
        assertThat(fieldTestServiceImplEntityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIntegerRequiredMikaIsRequired() throws Exception {
        int databaseSizeBeforeTest = fieldTestServiceImplEntityRepository.findAll().size();
        // set the field null
        fieldTestServiceImplEntity.setIntegerRequiredMika(null);

        // Create the FieldTestServiceImplEntity, which fails.

        restFieldTestServiceImplEntityMockMvc.perform(post("/api/field-test-service-impl-entities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fieldTestServiceImplEntity)))
            .andExpect(status().isBadRequest());

        List<FieldTestServiceImplEntity> fieldTestServiceImplEntityList = fieldTestServiceImplEntityRepository.findAll();
        assertThat(fieldTestServiceImplEntityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLongRequiredMikaIsRequired() throws Exception {
        int databaseSizeBeforeTest = fieldTestServiceImplEntityRepository.findAll().size();
        // set the field null
        fieldTestServiceImplEntity.setLongRequiredMika(null);

        // Create the FieldTestServiceImplEntity, which fails.

        restFieldTestServiceImplEntityMockMvc.perform(post("/api/field-test-service-impl-entities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fieldTestServiceImplEntity)))
            .andExpect(status().isBadRequest());

        List<FieldTestServiceImplEntity> fieldTestServiceImplEntityList = fieldTestServiceImplEntityRepository.findAll();
        assertThat(fieldTestServiceImplEntityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFloatRequiredMikaIsRequired() throws Exception {
        int databaseSizeBeforeTest = fieldTestServiceImplEntityRepository.findAll().size();
        // set the field null
        fieldTestServiceImplEntity.setFloatRequiredMika(null);

        // Create the FieldTestServiceImplEntity, which fails.

        restFieldTestServiceImplEntityMockMvc.perform(post("/api/field-test-service-impl-entities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fieldTestServiceImplEntity)))
            .andExpect(status().isBadRequest());

        List<FieldTestServiceImplEntity> fieldTestServiceImplEntityList = fieldTestServiceImplEntityRepository.findAll();
        assertThat(fieldTestServiceImplEntityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDoubleRequiredMikaIsRequired() throws Exception {
        int databaseSizeBeforeTest = fieldTestServiceImplEntityRepository.findAll().size();
        // set the field null
        fieldTestServiceImplEntity.setDoubleRequiredMika(null);

        // Create the FieldTestServiceImplEntity, which fails.

        restFieldTestServiceImplEntityMockMvc.perform(post("/api/field-test-service-impl-entities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fieldTestServiceImplEntity)))
            .andExpect(status().isBadRequest());

        List<FieldTestServiceImplEntity> fieldTestServiceImplEntityList = fieldTestServiceImplEntityRepository.findAll();
        assertThat(fieldTestServiceImplEntityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkBigDecimalRequiredMikaIsRequired() throws Exception {
        int databaseSizeBeforeTest = fieldTestServiceImplEntityRepository.findAll().size();
        // set the field null
        fieldTestServiceImplEntity.setBigDecimalRequiredMika(null);

        // Create the FieldTestServiceImplEntity, which fails.

        restFieldTestServiceImplEntityMockMvc.perform(post("/api/field-test-service-impl-entities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fieldTestServiceImplEntity)))
            .andExpect(status().isBadRequest());

        List<FieldTestServiceImplEntity> fieldTestServiceImplEntityList = fieldTestServiceImplEntityRepository.findAll();
        assertThat(fieldTestServiceImplEntityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLocalDateRequiredMikaIsRequired() throws Exception {
        int databaseSizeBeforeTest = fieldTestServiceImplEntityRepository.findAll().size();
        // set the field null
        fieldTestServiceImplEntity.setLocalDateRequiredMika(null);

        // Create the FieldTestServiceImplEntity, which fails.

        restFieldTestServiceImplEntityMockMvc.perform(post("/api/field-test-service-impl-entities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fieldTestServiceImplEntity)))
            .andExpect(status().isBadRequest());

        List<FieldTestServiceImplEntity> fieldTestServiceImplEntityList = fieldTestServiceImplEntityRepository.findAll();
        assertThat(fieldTestServiceImplEntityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkInstanteRequiredMikaIsRequired() throws Exception {
        int databaseSizeBeforeTest = fieldTestServiceImplEntityRepository.findAll().size();
        // set the field null
        fieldTestServiceImplEntity.setInstanteRequiredMika(null);

        // Create the FieldTestServiceImplEntity, which fails.

        restFieldTestServiceImplEntityMockMvc.perform(post("/api/field-test-service-impl-entities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fieldTestServiceImplEntity)))
            .andExpect(status().isBadRequest());

        List<FieldTestServiceImplEntity> fieldTestServiceImplEntityList = fieldTestServiceImplEntityRepository.findAll();
        assertThat(fieldTestServiceImplEntityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkZonedDateTimeRequiredMikaIsRequired() throws Exception {
        int databaseSizeBeforeTest = fieldTestServiceImplEntityRepository.findAll().size();
        // set the field null
        fieldTestServiceImplEntity.setZonedDateTimeRequiredMika(null);

        // Create the FieldTestServiceImplEntity, which fails.

        restFieldTestServiceImplEntityMockMvc.perform(post("/api/field-test-service-impl-entities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fieldTestServiceImplEntity)))
            .andExpect(status().isBadRequest());

        List<FieldTestServiceImplEntity> fieldTestServiceImplEntityList = fieldTestServiceImplEntityRepository.findAll();
        assertThat(fieldTestServiceImplEntityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkBooleanRequiredMikaIsRequired() throws Exception {
        int databaseSizeBeforeTest = fieldTestServiceImplEntityRepository.findAll().size();
        // set the field null
        fieldTestServiceImplEntity.setBooleanRequiredMika(null);

        // Create the FieldTestServiceImplEntity, which fails.

        restFieldTestServiceImplEntityMockMvc.perform(post("/api/field-test-service-impl-entities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fieldTestServiceImplEntity)))
            .andExpect(status().isBadRequest());

        List<FieldTestServiceImplEntity> fieldTestServiceImplEntityList = fieldTestServiceImplEntityRepository.findAll();
        assertThat(fieldTestServiceImplEntityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEnumRequiredMikaIsRequired() throws Exception {
        int databaseSizeBeforeTest = fieldTestServiceImplEntityRepository.findAll().size();
        // set the field null
        fieldTestServiceImplEntity.setEnumRequiredMika(null);

        // Create the FieldTestServiceImplEntity, which fails.

        restFieldTestServiceImplEntityMockMvc.perform(post("/api/field-test-service-impl-entities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fieldTestServiceImplEntity)))
            .andExpect(status().isBadRequest());

        List<FieldTestServiceImplEntity> fieldTestServiceImplEntityList = fieldTestServiceImplEntityRepository.findAll();
        assertThat(fieldTestServiceImplEntityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkByteImageRequiredMikaIsRequired() throws Exception {
        int databaseSizeBeforeTest = fieldTestServiceImplEntityRepository.findAll().size();
        // set the field null
        fieldTestServiceImplEntity.setByteImageRequiredMika(null);

        // Create the FieldTestServiceImplEntity, which fails.

        restFieldTestServiceImplEntityMockMvc.perform(post("/api/field-test-service-impl-entities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fieldTestServiceImplEntity)))
            .andExpect(status().isBadRequest());

        List<FieldTestServiceImplEntity> fieldTestServiceImplEntityList = fieldTestServiceImplEntityRepository.findAll();
        assertThat(fieldTestServiceImplEntityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkByteAnyRequiredMikaIsRequired() throws Exception {
        int databaseSizeBeforeTest = fieldTestServiceImplEntityRepository.findAll().size();
        // set the field null
        fieldTestServiceImplEntity.setByteAnyRequiredMika(null);

        // Create the FieldTestServiceImplEntity, which fails.

        restFieldTestServiceImplEntityMockMvc.perform(post("/api/field-test-service-impl-entities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fieldTestServiceImplEntity)))
            .andExpect(status().isBadRequest());

        List<FieldTestServiceImplEntity> fieldTestServiceImplEntityList = fieldTestServiceImplEntityRepository.findAll();
        assertThat(fieldTestServiceImplEntityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkByteTextRequiredMikaIsRequired() throws Exception {
        int databaseSizeBeforeTest = fieldTestServiceImplEntityRepository.findAll().size();
        // set the field null
        fieldTestServiceImplEntity.setByteTextRequiredMika(null);

        // Create the FieldTestServiceImplEntity, which fails.

        restFieldTestServiceImplEntityMockMvc.perform(post("/api/field-test-service-impl-entities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fieldTestServiceImplEntity)))
            .andExpect(status().isBadRequest());

        List<FieldTestServiceImplEntity> fieldTestServiceImplEntityList = fieldTestServiceImplEntityRepository.findAll();
        assertThat(fieldTestServiceImplEntityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllFieldTestServiceImplEntities() throws Exception {
        // Initialize the database
        fieldTestServiceImplEntityRepository.saveAndFlush(fieldTestServiceImplEntity);

        // Get all the fieldTestServiceImplEntityList
        restFieldTestServiceImplEntityMockMvc.perform(get("/api/field-test-service-impl-entities?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(fieldTestServiceImplEntity.getId().intValue())))
            .andExpect(jsonPath("$.[*].stringMika").value(hasItem(DEFAULT_STRING_MIKA.toString())))
            .andExpect(jsonPath("$.[*].stringRequiredMika").value(hasItem(DEFAULT_STRING_REQUIRED_MIKA.toString())))
            .andExpect(jsonPath("$.[*].stringMinlengthMika").value(hasItem(DEFAULT_STRING_MINLENGTH_MIKA.toString())))
            .andExpect(jsonPath("$.[*].stringMaxlengthMika").value(hasItem(DEFAULT_STRING_MAXLENGTH_MIKA.toString())))
            .andExpect(jsonPath("$.[*].stringPatternMika").value(hasItem(DEFAULT_STRING_PATTERN_MIKA.toString())))
            .andExpect(jsonPath("$.[*].integerMika").value(hasItem(DEFAULT_INTEGER_MIKA)))
            .andExpect(jsonPath("$.[*].integerRequiredMika").value(hasItem(DEFAULT_INTEGER_REQUIRED_MIKA)))
            .andExpect(jsonPath("$.[*].integerMinMika").value(hasItem(DEFAULT_INTEGER_MIN_MIKA)))
            .andExpect(jsonPath("$.[*].integerMaxMika").value(hasItem(DEFAULT_INTEGER_MAX_MIKA)))
            .andExpect(jsonPath("$.[*].longMika").value(hasItem(DEFAULT_LONG_MIKA.intValue())))
            .andExpect(jsonPath("$.[*].longRequiredMika").value(hasItem(DEFAULT_LONG_REQUIRED_MIKA.intValue())))
            .andExpect(jsonPath("$.[*].longMinMika").value(hasItem(DEFAULT_LONG_MIN_MIKA.intValue())))
            .andExpect(jsonPath("$.[*].longMaxMika").value(hasItem(DEFAULT_LONG_MAX_MIKA.intValue())))
            .andExpect(jsonPath("$.[*].floatMika").value(hasItem(DEFAULT_FLOAT_MIKA.doubleValue())))
            .andExpect(jsonPath("$.[*].floatRequiredMika").value(hasItem(DEFAULT_FLOAT_REQUIRED_MIKA.doubleValue())))
            .andExpect(jsonPath("$.[*].floatMinMika").value(hasItem(DEFAULT_FLOAT_MIN_MIKA.doubleValue())))
            .andExpect(jsonPath("$.[*].floatMaxMika").value(hasItem(DEFAULT_FLOAT_MAX_MIKA.doubleValue())))
            .andExpect(jsonPath("$.[*].doubleRequiredMika").value(hasItem(DEFAULT_DOUBLE_REQUIRED_MIKA.doubleValue())))
            .andExpect(jsonPath("$.[*].doubleMinMika").value(hasItem(DEFAULT_DOUBLE_MIN_MIKA.doubleValue())))
            .andExpect(jsonPath("$.[*].doubleMaxMika").value(hasItem(DEFAULT_DOUBLE_MAX_MIKA.doubleValue())))
            .andExpect(jsonPath("$.[*].bigDecimalRequiredMika").value(hasItem(DEFAULT_BIG_DECIMAL_REQUIRED_MIKA.intValue())))
            .andExpect(jsonPath("$.[*].bigDecimalMinMika").value(hasItem(DEFAULT_BIG_DECIMAL_MIN_MIKA.intValue())))
            .andExpect(jsonPath("$.[*].bigDecimalMaxMika").value(hasItem(DEFAULT_BIG_DECIMAL_MAX_MIKA.intValue())))
            .andExpect(jsonPath("$.[*].localDateMika").value(hasItem(DEFAULT_LOCAL_DATE_MIKA.toString())))
            .andExpect(jsonPath("$.[*].localDateRequiredMika").value(hasItem(DEFAULT_LOCAL_DATE_REQUIRED_MIKA.toString())))
            .andExpect(jsonPath("$.[*].instantMika").value(hasItem(DEFAULT_INSTANT_MIKA.toString())))
            .andExpect(jsonPath("$.[*].instanteRequiredMika").value(hasItem(DEFAULT_INSTANTE_REQUIRED_MIKA.toString())))
            .andExpect(jsonPath("$.[*].zonedDateTimeMika").value(hasItem(sameInstant(DEFAULT_ZONED_DATE_TIME_MIKA))))
            .andExpect(jsonPath("$.[*].zonedDateTimeRequiredMika").value(hasItem(sameInstant(DEFAULT_ZONED_DATE_TIME_REQUIRED_MIKA))))
            .andExpect(jsonPath("$.[*].booleanMika").value(hasItem(DEFAULT_BOOLEAN_MIKA.booleanValue())))
            .andExpect(jsonPath("$.[*].booleanRequiredMika").value(hasItem(DEFAULT_BOOLEAN_REQUIRED_MIKA.booleanValue())))
            .andExpect(jsonPath("$.[*].enumMika").value(hasItem(DEFAULT_ENUM_MIKA.toString())))
            .andExpect(jsonPath("$.[*].enumRequiredMika").value(hasItem(DEFAULT_ENUM_REQUIRED_MIKA.toString())))
            .andExpect(jsonPath("$.[*].byteImageMikaContentType").value(hasItem(DEFAULT_BYTE_IMAGE_MIKA_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].byteImageMika").value(hasItem(Base64Utils.encodeToString(DEFAULT_BYTE_IMAGE_MIKA))))
            .andExpect(jsonPath("$.[*].byteImageRequiredMikaContentType").value(hasItem(DEFAULT_BYTE_IMAGE_REQUIRED_MIKA_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].byteImageRequiredMika").value(hasItem(Base64Utils.encodeToString(DEFAULT_BYTE_IMAGE_REQUIRED_MIKA))))
            .andExpect(jsonPath("$.[*].byteImageMinbytesMikaContentType").value(hasItem(DEFAULT_BYTE_IMAGE_MINBYTES_MIKA_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].byteImageMinbytesMika").value(hasItem(Base64Utils.encodeToString(DEFAULT_BYTE_IMAGE_MINBYTES_MIKA))))
            .andExpect(jsonPath("$.[*].byteImageMaxbytesMikaContentType").value(hasItem(DEFAULT_BYTE_IMAGE_MAXBYTES_MIKA_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].byteImageMaxbytesMika").value(hasItem(Base64Utils.encodeToString(DEFAULT_BYTE_IMAGE_MAXBYTES_MIKA))))
            .andExpect(jsonPath("$.[*].byteAnyMikaContentType").value(hasItem(DEFAULT_BYTE_ANY_MIKA_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].byteAnyMika").value(hasItem(Base64Utils.encodeToString(DEFAULT_BYTE_ANY_MIKA))))
            .andExpect(jsonPath("$.[*].byteAnyRequiredMikaContentType").value(hasItem(DEFAULT_BYTE_ANY_REQUIRED_MIKA_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].byteAnyRequiredMika").value(hasItem(Base64Utils.encodeToString(DEFAULT_BYTE_ANY_REQUIRED_MIKA))))
            .andExpect(jsonPath("$.[*].byteAnyMinbytesMikaContentType").value(hasItem(DEFAULT_BYTE_ANY_MINBYTES_MIKA_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].byteAnyMinbytesMika").value(hasItem(Base64Utils.encodeToString(DEFAULT_BYTE_ANY_MINBYTES_MIKA))))
            .andExpect(jsonPath("$.[*].byteAnyMaxbytesMikaContentType").value(hasItem(DEFAULT_BYTE_ANY_MAXBYTES_MIKA_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].byteAnyMaxbytesMika").value(hasItem(Base64Utils.encodeToString(DEFAULT_BYTE_ANY_MAXBYTES_MIKA))))
            .andExpect(jsonPath("$.[*].byteTextMika").value(hasItem(DEFAULT_BYTE_TEXT_MIKA.toString())))
            .andExpect(jsonPath("$.[*].byteTextRequiredMika").value(hasItem(DEFAULT_BYTE_TEXT_REQUIRED_MIKA.toString())))
            .andExpect(jsonPath("$.[*].byteTextMinbytesMika").value(hasItem(DEFAULT_BYTE_TEXT_MINBYTES_MIKA.toString())))
            .andExpect(jsonPath("$.[*].byteTextMaxbytesMika").value(hasItem(DEFAULT_BYTE_TEXT_MAXBYTES_MIKA.toString())));
    }

    @Test
    @Transactional
    public void getFieldTestServiceImplEntity() throws Exception {
        // Initialize the database
        fieldTestServiceImplEntityRepository.saveAndFlush(fieldTestServiceImplEntity);

        // Get the fieldTestServiceImplEntity
        restFieldTestServiceImplEntityMockMvc.perform(get("/api/field-test-service-impl-entities/{id}", fieldTestServiceImplEntity.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(fieldTestServiceImplEntity.getId().intValue()))
            .andExpect(jsonPath("$.stringMika").value(DEFAULT_STRING_MIKA.toString()))
            .andExpect(jsonPath("$.stringRequiredMika").value(DEFAULT_STRING_REQUIRED_MIKA.toString()))
            .andExpect(jsonPath("$.stringMinlengthMika").value(DEFAULT_STRING_MINLENGTH_MIKA.toString()))
            .andExpect(jsonPath("$.stringMaxlengthMika").value(DEFAULT_STRING_MAXLENGTH_MIKA.toString()))
            .andExpect(jsonPath("$.stringPatternMika").value(DEFAULT_STRING_PATTERN_MIKA.toString()))
            .andExpect(jsonPath("$.integerMika").value(DEFAULT_INTEGER_MIKA))
            .andExpect(jsonPath("$.integerRequiredMika").value(DEFAULT_INTEGER_REQUIRED_MIKA))
            .andExpect(jsonPath("$.integerMinMika").value(DEFAULT_INTEGER_MIN_MIKA))
            .andExpect(jsonPath("$.integerMaxMika").value(DEFAULT_INTEGER_MAX_MIKA))
            .andExpect(jsonPath("$.longMika").value(DEFAULT_LONG_MIKA.intValue()))
            .andExpect(jsonPath("$.longRequiredMika").value(DEFAULT_LONG_REQUIRED_MIKA.intValue()))
            .andExpect(jsonPath("$.longMinMika").value(DEFAULT_LONG_MIN_MIKA.intValue()))
            .andExpect(jsonPath("$.longMaxMika").value(DEFAULT_LONG_MAX_MIKA.intValue()))
            .andExpect(jsonPath("$.floatMika").value(DEFAULT_FLOAT_MIKA.doubleValue()))
            .andExpect(jsonPath("$.floatRequiredMika").value(DEFAULT_FLOAT_REQUIRED_MIKA.doubleValue()))
            .andExpect(jsonPath("$.floatMinMika").value(DEFAULT_FLOAT_MIN_MIKA.doubleValue()))
            .andExpect(jsonPath("$.floatMaxMika").value(DEFAULT_FLOAT_MAX_MIKA.doubleValue()))
            .andExpect(jsonPath("$.doubleRequiredMika").value(DEFAULT_DOUBLE_REQUIRED_MIKA.doubleValue()))
            .andExpect(jsonPath("$.doubleMinMika").value(DEFAULT_DOUBLE_MIN_MIKA.doubleValue()))
            .andExpect(jsonPath("$.doubleMaxMika").value(DEFAULT_DOUBLE_MAX_MIKA.doubleValue()))
            .andExpect(jsonPath("$.bigDecimalRequiredMika").value(DEFAULT_BIG_DECIMAL_REQUIRED_MIKA.intValue()))
            .andExpect(jsonPath("$.bigDecimalMinMika").value(DEFAULT_BIG_DECIMAL_MIN_MIKA.intValue()))
            .andExpect(jsonPath("$.bigDecimalMaxMika").value(DEFAULT_BIG_DECIMAL_MAX_MIKA.intValue()))
            .andExpect(jsonPath("$.localDateMika").value(DEFAULT_LOCAL_DATE_MIKA.toString()))
            .andExpect(jsonPath("$.localDateRequiredMika").value(DEFAULT_LOCAL_DATE_REQUIRED_MIKA.toString()))
            .andExpect(jsonPath("$.instantMika").value(DEFAULT_INSTANT_MIKA.toString()))
            .andExpect(jsonPath("$.instanteRequiredMika").value(DEFAULT_INSTANTE_REQUIRED_MIKA.toString()))
            .andExpect(jsonPath("$.zonedDateTimeMika").value(sameInstant(DEFAULT_ZONED_DATE_TIME_MIKA)))
            .andExpect(jsonPath("$.zonedDateTimeRequiredMika").value(sameInstant(DEFAULT_ZONED_DATE_TIME_REQUIRED_MIKA)))
            .andExpect(jsonPath("$.booleanMika").value(DEFAULT_BOOLEAN_MIKA.booleanValue()))
            .andExpect(jsonPath("$.booleanRequiredMika").value(DEFAULT_BOOLEAN_REQUIRED_MIKA.booleanValue()))
            .andExpect(jsonPath("$.enumMika").value(DEFAULT_ENUM_MIKA.toString()))
            .andExpect(jsonPath("$.enumRequiredMika").value(DEFAULT_ENUM_REQUIRED_MIKA.toString()))
            .andExpect(jsonPath("$.byteImageMikaContentType").value(DEFAULT_BYTE_IMAGE_MIKA_CONTENT_TYPE))
            .andExpect(jsonPath("$.byteImageMika").value(Base64Utils.encodeToString(DEFAULT_BYTE_IMAGE_MIKA)))
            .andExpect(jsonPath("$.byteImageRequiredMikaContentType").value(DEFAULT_BYTE_IMAGE_REQUIRED_MIKA_CONTENT_TYPE))
            .andExpect(jsonPath("$.byteImageRequiredMika").value(Base64Utils.encodeToString(DEFAULT_BYTE_IMAGE_REQUIRED_MIKA)))
            .andExpect(jsonPath("$.byteImageMinbytesMikaContentType").value(DEFAULT_BYTE_IMAGE_MINBYTES_MIKA_CONTENT_TYPE))
            .andExpect(jsonPath("$.byteImageMinbytesMika").value(Base64Utils.encodeToString(DEFAULT_BYTE_IMAGE_MINBYTES_MIKA)))
            .andExpect(jsonPath("$.byteImageMaxbytesMikaContentType").value(DEFAULT_BYTE_IMAGE_MAXBYTES_MIKA_CONTENT_TYPE))
            .andExpect(jsonPath("$.byteImageMaxbytesMika").value(Base64Utils.encodeToString(DEFAULT_BYTE_IMAGE_MAXBYTES_MIKA)))
            .andExpect(jsonPath("$.byteAnyMikaContentType").value(DEFAULT_BYTE_ANY_MIKA_CONTENT_TYPE))
            .andExpect(jsonPath("$.byteAnyMika").value(Base64Utils.encodeToString(DEFAULT_BYTE_ANY_MIKA)))
            .andExpect(jsonPath("$.byteAnyRequiredMikaContentType").value(DEFAULT_BYTE_ANY_REQUIRED_MIKA_CONTENT_TYPE))
            .andExpect(jsonPath("$.byteAnyRequiredMika").value(Base64Utils.encodeToString(DEFAULT_BYTE_ANY_REQUIRED_MIKA)))
            .andExpect(jsonPath("$.byteAnyMinbytesMikaContentType").value(DEFAULT_BYTE_ANY_MINBYTES_MIKA_CONTENT_TYPE))
            .andExpect(jsonPath("$.byteAnyMinbytesMika").value(Base64Utils.encodeToString(DEFAULT_BYTE_ANY_MINBYTES_MIKA)))
            .andExpect(jsonPath("$.byteAnyMaxbytesMikaContentType").value(DEFAULT_BYTE_ANY_MAXBYTES_MIKA_CONTENT_TYPE))
            .andExpect(jsonPath("$.byteAnyMaxbytesMika").value(Base64Utils.encodeToString(DEFAULT_BYTE_ANY_MAXBYTES_MIKA)))
            .andExpect(jsonPath("$.byteTextMika").value(DEFAULT_BYTE_TEXT_MIKA.toString()))
            .andExpect(jsonPath("$.byteTextRequiredMika").value(DEFAULT_BYTE_TEXT_REQUIRED_MIKA.toString()))
            .andExpect(jsonPath("$.byteTextMinbytesMika").value(DEFAULT_BYTE_TEXT_MINBYTES_MIKA.toString()))
            .andExpect(jsonPath("$.byteTextMaxbytesMika").value(DEFAULT_BYTE_TEXT_MAXBYTES_MIKA.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingFieldTestServiceImplEntity() throws Exception {
        // Get the fieldTestServiceImplEntity
        restFieldTestServiceImplEntityMockMvc.perform(get("/api/field-test-service-impl-entities/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFieldTestServiceImplEntity() throws Exception {
        // Initialize the database
        fieldTestServiceImplEntityService.save(fieldTestServiceImplEntity);

        int databaseSizeBeforeUpdate = fieldTestServiceImplEntityRepository.findAll().size();

        // Update the fieldTestServiceImplEntity
        FieldTestServiceImplEntity updatedFieldTestServiceImplEntity = fieldTestServiceImplEntityRepository.findOne(fieldTestServiceImplEntity.getId());
        updatedFieldTestServiceImplEntity
            .stringMika(UPDATED_STRING_MIKA)
            .stringRequiredMika(UPDATED_STRING_REQUIRED_MIKA)
            .stringMinlengthMika(UPDATED_STRING_MINLENGTH_MIKA)
            .stringMaxlengthMika(UPDATED_STRING_MAXLENGTH_MIKA)
            .stringPatternMika(UPDATED_STRING_PATTERN_MIKA)
            .integerMika(UPDATED_INTEGER_MIKA)
            .integerRequiredMika(UPDATED_INTEGER_REQUIRED_MIKA)
            .integerMinMika(UPDATED_INTEGER_MIN_MIKA)
            .integerMaxMika(UPDATED_INTEGER_MAX_MIKA)
            .longMika(UPDATED_LONG_MIKA)
            .longRequiredMika(UPDATED_LONG_REQUIRED_MIKA)
            .longMinMika(UPDATED_LONG_MIN_MIKA)
            .longMaxMika(UPDATED_LONG_MAX_MIKA)
            .floatMika(UPDATED_FLOAT_MIKA)
            .floatRequiredMika(UPDATED_FLOAT_REQUIRED_MIKA)
            .floatMinMika(UPDATED_FLOAT_MIN_MIKA)
            .floatMaxMika(UPDATED_FLOAT_MAX_MIKA)
            .doubleRequiredMika(UPDATED_DOUBLE_REQUIRED_MIKA)
            .doubleMinMika(UPDATED_DOUBLE_MIN_MIKA)
            .doubleMaxMika(UPDATED_DOUBLE_MAX_MIKA)
            .bigDecimalRequiredMika(UPDATED_BIG_DECIMAL_REQUIRED_MIKA)
            .bigDecimalMinMika(UPDATED_BIG_DECIMAL_MIN_MIKA)
            .bigDecimalMaxMika(UPDATED_BIG_DECIMAL_MAX_MIKA)
            .localDateMika(UPDATED_LOCAL_DATE_MIKA)
            .localDateRequiredMika(UPDATED_LOCAL_DATE_REQUIRED_MIKA)
            .instantMika(UPDATED_INSTANT_MIKA)
            .instanteRequiredMika(UPDATED_INSTANTE_REQUIRED_MIKA)
            .zonedDateTimeMika(UPDATED_ZONED_DATE_TIME_MIKA)
            .zonedDateTimeRequiredMika(UPDATED_ZONED_DATE_TIME_REQUIRED_MIKA)
            .booleanMika(UPDATED_BOOLEAN_MIKA)
            .booleanRequiredMika(UPDATED_BOOLEAN_REQUIRED_MIKA)
            .enumMika(UPDATED_ENUM_MIKA)
            .enumRequiredMika(UPDATED_ENUM_REQUIRED_MIKA)
            .byteImageMika(UPDATED_BYTE_IMAGE_MIKA)
            .byteImageMikaContentType(UPDATED_BYTE_IMAGE_MIKA_CONTENT_TYPE)
            .byteImageRequiredMika(UPDATED_BYTE_IMAGE_REQUIRED_MIKA)
            .byteImageRequiredMikaContentType(UPDATED_BYTE_IMAGE_REQUIRED_MIKA_CONTENT_TYPE)
            .byteImageMinbytesMika(UPDATED_BYTE_IMAGE_MINBYTES_MIKA)
            .byteImageMinbytesMikaContentType(UPDATED_BYTE_IMAGE_MINBYTES_MIKA_CONTENT_TYPE)
            .byteImageMaxbytesMika(UPDATED_BYTE_IMAGE_MAXBYTES_MIKA)
            .byteImageMaxbytesMikaContentType(UPDATED_BYTE_IMAGE_MAXBYTES_MIKA_CONTENT_TYPE)
            .byteAnyMika(UPDATED_BYTE_ANY_MIKA)
            .byteAnyMikaContentType(UPDATED_BYTE_ANY_MIKA_CONTENT_TYPE)
            .byteAnyRequiredMika(UPDATED_BYTE_ANY_REQUIRED_MIKA)
            .byteAnyRequiredMikaContentType(UPDATED_BYTE_ANY_REQUIRED_MIKA_CONTENT_TYPE)
            .byteAnyMinbytesMika(UPDATED_BYTE_ANY_MINBYTES_MIKA)
            .byteAnyMinbytesMikaContentType(UPDATED_BYTE_ANY_MINBYTES_MIKA_CONTENT_TYPE)
            .byteAnyMaxbytesMika(UPDATED_BYTE_ANY_MAXBYTES_MIKA)
            .byteAnyMaxbytesMikaContentType(UPDATED_BYTE_ANY_MAXBYTES_MIKA_CONTENT_TYPE)
            .byteTextMika(UPDATED_BYTE_TEXT_MIKA)
            .byteTextRequiredMika(UPDATED_BYTE_TEXT_REQUIRED_MIKA)
            .byteTextMinbytesMika(UPDATED_BYTE_TEXT_MINBYTES_MIKA)
            .byteTextMaxbytesMika(UPDATED_BYTE_TEXT_MAXBYTES_MIKA);

        restFieldTestServiceImplEntityMockMvc.perform(put("/api/field-test-service-impl-entities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedFieldTestServiceImplEntity)))
            .andExpect(status().isOk());

        // Validate the FieldTestServiceImplEntity in the database
        List<FieldTestServiceImplEntity> fieldTestServiceImplEntityList = fieldTestServiceImplEntityRepository.findAll();
        assertThat(fieldTestServiceImplEntityList).hasSize(databaseSizeBeforeUpdate);
        FieldTestServiceImplEntity testFieldTestServiceImplEntity = fieldTestServiceImplEntityList.get(fieldTestServiceImplEntityList.size() - 1);
        assertThat(testFieldTestServiceImplEntity.getStringMika()).isEqualTo(UPDATED_STRING_MIKA);
        assertThat(testFieldTestServiceImplEntity.getStringRequiredMika()).isEqualTo(UPDATED_STRING_REQUIRED_MIKA);
        assertThat(testFieldTestServiceImplEntity.getStringMinlengthMika()).isEqualTo(UPDATED_STRING_MINLENGTH_MIKA);
        assertThat(testFieldTestServiceImplEntity.getStringMaxlengthMika()).isEqualTo(UPDATED_STRING_MAXLENGTH_MIKA);
        assertThat(testFieldTestServiceImplEntity.getStringPatternMika()).isEqualTo(UPDATED_STRING_PATTERN_MIKA);
        assertThat(testFieldTestServiceImplEntity.getIntegerMika()).isEqualTo(UPDATED_INTEGER_MIKA);
        assertThat(testFieldTestServiceImplEntity.getIntegerRequiredMika()).isEqualTo(UPDATED_INTEGER_REQUIRED_MIKA);
        assertThat(testFieldTestServiceImplEntity.getIntegerMinMika()).isEqualTo(UPDATED_INTEGER_MIN_MIKA);
        assertThat(testFieldTestServiceImplEntity.getIntegerMaxMika()).isEqualTo(UPDATED_INTEGER_MAX_MIKA);
        assertThat(testFieldTestServiceImplEntity.getLongMika()).isEqualTo(UPDATED_LONG_MIKA);
        assertThat(testFieldTestServiceImplEntity.getLongRequiredMika()).isEqualTo(UPDATED_LONG_REQUIRED_MIKA);
        assertThat(testFieldTestServiceImplEntity.getLongMinMika()).isEqualTo(UPDATED_LONG_MIN_MIKA);
        assertThat(testFieldTestServiceImplEntity.getLongMaxMika()).isEqualTo(UPDATED_LONG_MAX_MIKA);
        assertThat(testFieldTestServiceImplEntity.getFloatMika()).isEqualTo(UPDATED_FLOAT_MIKA);
        assertThat(testFieldTestServiceImplEntity.getFloatRequiredMika()).isEqualTo(UPDATED_FLOAT_REQUIRED_MIKA);
        assertThat(testFieldTestServiceImplEntity.getFloatMinMika()).isEqualTo(UPDATED_FLOAT_MIN_MIKA);
        assertThat(testFieldTestServiceImplEntity.getFloatMaxMika()).isEqualTo(UPDATED_FLOAT_MAX_MIKA);
        assertThat(testFieldTestServiceImplEntity.getDoubleRequiredMika()).isEqualTo(UPDATED_DOUBLE_REQUIRED_MIKA);
        assertThat(testFieldTestServiceImplEntity.getDoubleMinMika()).isEqualTo(UPDATED_DOUBLE_MIN_MIKA);
        assertThat(testFieldTestServiceImplEntity.getDoubleMaxMika()).isEqualTo(UPDATED_DOUBLE_MAX_MIKA);
        assertThat(testFieldTestServiceImplEntity.getBigDecimalRequiredMika()).isEqualTo(UPDATED_BIG_DECIMAL_REQUIRED_MIKA);
        assertThat(testFieldTestServiceImplEntity.getBigDecimalMinMika()).isEqualTo(UPDATED_BIG_DECIMAL_MIN_MIKA);
        assertThat(testFieldTestServiceImplEntity.getBigDecimalMaxMika()).isEqualTo(UPDATED_BIG_DECIMAL_MAX_MIKA);
        assertThat(testFieldTestServiceImplEntity.getLocalDateMika()).isEqualTo(UPDATED_LOCAL_DATE_MIKA);
        assertThat(testFieldTestServiceImplEntity.getLocalDateRequiredMika()).isEqualTo(UPDATED_LOCAL_DATE_REQUIRED_MIKA);
        assertThat(testFieldTestServiceImplEntity.getInstantMika()).isEqualTo(UPDATED_INSTANT_MIKA);
        assertThat(testFieldTestServiceImplEntity.getInstanteRequiredMika()).isEqualTo(UPDATED_INSTANTE_REQUIRED_MIKA);
        assertThat(testFieldTestServiceImplEntity.getZonedDateTimeMika()).isEqualTo(UPDATED_ZONED_DATE_TIME_MIKA);
        assertThat(testFieldTestServiceImplEntity.getZonedDateTimeRequiredMika()).isEqualTo(UPDATED_ZONED_DATE_TIME_REQUIRED_MIKA);
        assertThat(testFieldTestServiceImplEntity.isBooleanMika()).isEqualTo(UPDATED_BOOLEAN_MIKA);
        assertThat(testFieldTestServiceImplEntity.isBooleanRequiredMika()).isEqualTo(UPDATED_BOOLEAN_REQUIRED_MIKA);
        assertThat(testFieldTestServiceImplEntity.getEnumMika()).isEqualTo(UPDATED_ENUM_MIKA);
        assertThat(testFieldTestServiceImplEntity.getEnumRequiredMika()).isEqualTo(UPDATED_ENUM_REQUIRED_MIKA);
        assertThat(testFieldTestServiceImplEntity.getByteImageMika()).isEqualTo(UPDATED_BYTE_IMAGE_MIKA);
        assertThat(testFieldTestServiceImplEntity.getByteImageMikaContentType()).isEqualTo(UPDATED_BYTE_IMAGE_MIKA_CONTENT_TYPE);
        assertThat(testFieldTestServiceImplEntity.getByteImageRequiredMika()).isEqualTo(UPDATED_BYTE_IMAGE_REQUIRED_MIKA);
        assertThat(testFieldTestServiceImplEntity.getByteImageRequiredMikaContentType()).isEqualTo(UPDATED_BYTE_IMAGE_REQUIRED_MIKA_CONTENT_TYPE);
        assertThat(testFieldTestServiceImplEntity.getByteImageMinbytesMika()).isEqualTo(UPDATED_BYTE_IMAGE_MINBYTES_MIKA);
        assertThat(testFieldTestServiceImplEntity.getByteImageMinbytesMikaContentType()).isEqualTo(UPDATED_BYTE_IMAGE_MINBYTES_MIKA_CONTENT_TYPE);
        assertThat(testFieldTestServiceImplEntity.getByteImageMaxbytesMika()).isEqualTo(UPDATED_BYTE_IMAGE_MAXBYTES_MIKA);
        assertThat(testFieldTestServiceImplEntity.getByteImageMaxbytesMikaContentType()).isEqualTo(UPDATED_BYTE_IMAGE_MAXBYTES_MIKA_CONTENT_TYPE);
        assertThat(testFieldTestServiceImplEntity.getByteAnyMika()).isEqualTo(UPDATED_BYTE_ANY_MIKA);
        assertThat(testFieldTestServiceImplEntity.getByteAnyMikaContentType()).isEqualTo(UPDATED_BYTE_ANY_MIKA_CONTENT_TYPE);
        assertThat(testFieldTestServiceImplEntity.getByteAnyRequiredMika()).isEqualTo(UPDATED_BYTE_ANY_REQUIRED_MIKA);
        assertThat(testFieldTestServiceImplEntity.getByteAnyRequiredMikaContentType()).isEqualTo(UPDATED_BYTE_ANY_REQUIRED_MIKA_CONTENT_TYPE);
        assertThat(testFieldTestServiceImplEntity.getByteAnyMinbytesMika()).isEqualTo(UPDATED_BYTE_ANY_MINBYTES_MIKA);
        assertThat(testFieldTestServiceImplEntity.getByteAnyMinbytesMikaContentType()).isEqualTo(UPDATED_BYTE_ANY_MINBYTES_MIKA_CONTENT_TYPE);
        assertThat(testFieldTestServiceImplEntity.getByteAnyMaxbytesMika()).isEqualTo(UPDATED_BYTE_ANY_MAXBYTES_MIKA);
        assertThat(testFieldTestServiceImplEntity.getByteAnyMaxbytesMikaContentType()).isEqualTo(UPDATED_BYTE_ANY_MAXBYTES_MIKA_CONTENT_TYPE);
        assertThat(testFieldTestServiceImplEntity.getByteTextMika()).isEqualTo(UPDATED_BYTE_TEXT_MIKA);
        assertThat(testFieldTestServiceImplEntity.getByteTextRequiredMika()).isEqualTo(UPDATED_BYTE_TEXT_REQUIRED_MIKA);
        assertThat(testFieldTestServiceImplEntity.getByteTextMinbytesMika()).isEqualTo(UPDATED_BYTE_TEXT_MINBYTES_MIKA);
        assertThat(testFieldTestServiceImplEntity.getByteTextMaxbytesMika()).isEqualTo(UPDATED_BYTE_TEXT_MAXBYTES_MIKA);

        // Validate the FieldTestServiceImplEntity in Elasticsearch
        FieldTestServiceImplEntity fieldTestServiceImplEntityEs = fieldTestServiceImplEntitySearchRepository.findOne(testFieldTestServiceImplEntity.getId());
        assertThat(fieldTestServiceImplEntityEs).isEqualToComparingFieldByField(testFieldTestServiceImplEntity);
    }

    @Test
    @Transactional
    public void updateNonExistingFieldTestServiceImplEntity() throws Exception {
        int databaseSizeBeforeUpdate = fieldTestServiceImplEntityRepository.findAll().size();

        // Create the FieldTestServiceImplEntity

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restFieldTestServiceImplEntityMockMvc.perform(put("/api/field-test-service-impl-entities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fieldTestServiceImplEntity)))
            .andExpect(status().isCreated());

        // Validate the FieldTestServiceImplEntity in the database
        List<FieldTestServiceImplEntity> fieldTestServiceImplEntityList = fieldTestServiceImplEntityRepository.findAll();
        assertThat(fieldTestServiceImplEntityList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteFieldTestServiceImplEntity() throws Exception {
        // Initialize the database
        fieldTestServiceImplEntityService.save(fieldTestServiceImplEntity);

        int databaseSizeBeforeDelete = fieldTestServiceImplEntityRepository.findAll().size();

        // Get the fieldTestServiceImplEntity
        restFieldTestServiceImplEntityMockMvc.perform(delete("/api/field-test-service-impl-entities/{id}", fieldTestServiceImplEntity.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate Elasticsearch is empty
        boolean fieldTestServiceImplEntityExistsInEs = fieldTestServiceImplEntitySearchRepository.exists(fieldTestServiceImplEntity.getId());
        assertThat(fieldTestServiceImplEntityExistsInEs).isFalse();

        // Validate the database is empty
        List<FieldTestServiceImplEntity> fieldTestServiceImplEntityList = fieldTestServiceImplEntityRepository.findAll();
        assertThat(fieldTestServiceImplEntityList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void searchFieldTestServiceImplEntity() throws Exception {
        // Initialize the database
        fieldTestServiceImplEntityService.save(fieldTestServiceImplEntity);

        // Search the fieldTestServiceImplEntity
        restFieldTestServiceImplEntityMockMvc.perform(get("/api/_search/field-test-service-impl-entities?query=id:" + fieldTestServiceImplEntity.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(fieldTestServiceImplEntity.getId().intValue())))
            .andExpect(jsonPath("$.[*].stringMika").value(hasItem(DEFAULT_STRING_MIKA.toString())))
            .andExpect(jsonPath("$.[*].stringRequiredMika").value(hasItem(DEFAULT_STRING_REQUIRED_MIKA.toString())))
            .andExpect(jsonPath("$.[*].stringMinlengthMika").value(hasItem(DEFAULT_STRING_MINLENGTH_MIKA.toString())))
            .andExpect(jsonPath("$.[*].stringMaxlengthMika").value(hasItem(DEFAULT_STRING_MAXLENGTH_MIKA.toString())))
            .andExpect(jsonPath("$.[*].stringPatternMika").value(hasItem(DEFAULT_STRING_PATTERN_MIKA.toString())))
            .andExpect(jsonPath("$.[*].integerMika").value(hasItem(DEFAULT_INTEGER_MIKA)))
            .andExpect(jsonPath("$.[*].integerRequiredMika").value(hasItem(DEFAULT_INTEGER_REQUIRED_MIKA)))
            .andExpect(jsonPath("$.[*].integerMinMika").value(hasItem(DEFAULT_INTEGER_MIN_MIKA)))
            .andExpect(jsonPath("$.[*].integerMaxMika").value(hasItem(DEFAULT_INTEGER_MAX_MIKA)))
            .andExpect(jsonPath("$.[*].longMika").value(hasItem(DEFAULT_LONG_MIKA.intValue())))
            .andExpect(jsonPath("$.[*].longRequiredMika").value(hasItem(DEFAULT_LONG_REQUIRED_MIKA.intValue())))
            .andExpect(jsonPath("$.[*].longMinMika").value(hasItem(DEFAULT_LONG_MIN_MIKA.intValue())))
            .andExpect(jsonPath("$.[*].longMaxMika").value(hasItem(DEFAULT_LONG_MAX_MIKA.intValue())))
            .andExpect(jsonPath("$.[*].floatMika").value(hasItem(DEFAULT_FLOAT_MIKA.doubleValue())))
            .andExpect(jsonPath("$.[*].floatRequiredMika").value(hasItem(DEFAULT_FLOAT_REQUIRED_MIKA.doubleValue())))
            .andExpect(jsonPath("$.[*].floatMinMika").value(hasItem(DEFAULT_FLOAT_MIN_MIKA.doubleValue())))
            .andExpect(jsonPath("$.[*].floatMaxMika").value(hasItem(DEFAULT_FLOAT_MAX_MIKA.doubleValue())))
            .andExpect(jsonPath("$.[*].doubleRequiredMika").value(hasItem(DEFAULT_DOUBLE_REQUIRED_MIKA.doubleValue())))
            .andExpect(jsonPath("$.[*].doubleMinMika").value(hasItem(DEFAULT_DOUBLE_MIN_MIKA.doubleValue())))
            .andExpect(jsonPath("$.[*].doubleMaxMika").value(hasItem(DEFAULT_DOUBLE_MAX_MIKA.doubleValue())))
            .andExpect(jsonPath("$.[*].bigDecimalRequiredMika").value(hasItem(DEFAULT_BIG_DECIMAL_REQUIRED_MIKA.intValue())))
            .andExpect(jsonPath("$.[*].bigDecimalMinMika").value(hasItem(DEFAULT_BIG_DECIMAL_MIN_MIKA.intValue())))
            .andExpect(jsonPath("$.[*].bigDecimalMaxMika").value(hasItem(DEFAULT_BIG_DECIMAL_MAX_MIKA.intValue())))
            .andExpect(jsonPath("$.[*].localDateMika").value(hasItem(DEFAULT_LOCAL_DATE_MIKA.toString())))
            .andExpect(jsonPath("$.[*].localDateRequiredMika").value(hasItem(DEFAULT_LOCAL_DATE_REQUIRED_MIKA.toString())))
            .andExpect(jsonPath("$.[*].instantMika").value(hasItem(DEFAULT_INSTANT_MIKA.toString())))
            .andExpect(jsonPath("$.[*].instanteRequiredMika").value(hasItem(DEFAULT_INSTANTE_REQUIRED_MIKA.toString())))
            .andExpect(jsonPath("$.[*].zonedDateTimeMika").value(hasItem(sameInstant(DEFAULT_ZONED_DATE_TIME_MIKA))))
            .andExpect(jsonPath("$.[*].zonedDateTimeRequiredMika").value(hasItem(sameInstant(DEFAULT_ZONED_DATE_TIME_REQUIRED_MIKA))))
            .andExpect(jsonPath("$.[*].booleanMika").value(hasItem(DEFAULT_BOOLEAN_MIKA.booleanValue())))
            .andExpect(jsonPath("$.[*].booleanRequiredMika").value(hasItem(DEFAULT_BOOLEAN_REQUIRED_MIKA.booleanValue())))
            .andExpect(jsonPath("$.[*].enumMika").value(hasItem(DEFAULT_ENUM_MIKA.toString())))
            .andExpect(jsonPath("$.[*].enumRequiredMika").value(hasItem(DEFAULT_ENUM_REQUIRED_MIKA.toString())))
            .andExpect(jsonPath("$.[*].byteImageMikaContentType").value(hasItem(DEFAULT_BYTE_IMAGE_MIKA_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].byteImageMika").value(hasItem(Base64Utils.encodeToString(DEFAULT_BYTE_IMAGE_MIKA))))
            .andExpect(jsonPath("$.[*].byteImageRequiredMikaContentType").value(hasItem(DEFAULT_BYTE_IMAGE_REQUIRED_MIKA_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].byteImageRequiredMika").value(hasItem(Base64Utils.encodeToString(DEFAULT_BYTE_IMAGE_REQUIRED_MIKA))))
            .andExpect(jsonPath("$.[*].byteImageMinbytesMikaContentType").value(hasItem(DEFAULT_BYTE_IMAGE_MINBYTES_MIKA_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].byteImageMinbytesMika").value(hasItem(Base64Utils.encodeToString(DEFAULT_BYTE_IMAGE_MINBYTES_MIKA))))
            .andExpect(jsonPath("$.[*].byteImageMaxbytesMikaContentType").value(hasItem(DEFAULT_BYTE_IMAGE_MAXBYTES_MIKA_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].byteImageMaxbytesMika").value(hasItem(Base64Utils.encodeToString(DEFAULT_BYTE_IMAGE_MAXBYTES_MIKA))))
            .andExpect(jsonPath("$.[*].byteAnyMikaContentType").value(hasItem(DEFAULT_BYTE_ANY_MIKA_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].byteAnyMika").value(hasItem(Base64Utils.encodeToString(DEFAULT_BYTE_ANY_MIKA))))
            .andExpect(jsonPath("$.[*].byteAnyRequiredMikaContentType").value(hasItem(DEFAULT_BYTE_ANY_REQUIRED_MIKA_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].byteAnyRequiredMika").value(hasItem(Base64Utils.encodeToString(DEFAULT_BYTE_ANY_REQUIRED_MIKA))))
            .andExpect(jsonPath("$.[*].byteAnyMinbytesMikaContentType").value(hasItem(DEFAULT_BYTE_ANY_MINBYTES_MIKA_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].byteAnyMinbytesMika").value(hasItem(Base64Utils.encodeToString(DEFAULT_BYTE_ANY_MINBYTES_MIKA))))
            .andExpect(jsonPath("$.[*].byteAnyMaxbytesMikaContentType").value(hasItem(DEFAULT_BYTE_ANY_MAXBYTES_MIKA_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].byteAnyMaxbytesMika").value(hasItem(Base64Utils.encodeToString(DEFAULT_BYTE_ANY_MAXBYTES_MIKA))))
            .andExpect(jsonPath("$.[*].byteTextMika").value(hasItem(DEFAULT_BYTE_TEXT_MIKA.toString())))
            .andExpect(jsonPath("$.[*].byteTextRequiredMika").value(hasItem(DEFAULT_BYTE_TEXT_REQUIRED_MIKA.toString())))
            .andExpect(jsonPath("$.[*].byteTextMinbytesMika").value(hasItem(DEFAULT_BYTE_TEXT_MINBYTES_MIKA.toString())))
            .andExpect(jsonPath("$.[*].byteTextMaxbytesMika").value(hasItem(DEFAULT_BYTE_TEXT_MAXBYTES_MIKA.toString())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(FieldTestServiceImplEntity.class);
        FieldTestServiceImplEntity fieldTestServiceImplEntity1 = new FieldTestServiceImplEntity();
        fieldTestServiceImplEntity1.setId(1L);
        FieldTestServiceImplEntity fieldTestServiceImplEntity2 = new FieldTestServiceImplEntity();
        fieldTestServiceImplEntity2.setId(fieldTestServiceImplEntity1.getId());
        assertThat(fieldTestServiceImplEntity1).isEqualTo(fieldTestServiceImplEntity2);
        fieldTestServiceImplEntity2.setId(2L);
        assertThat(fieldTestServiceImplEntity1).isNotEqualTo(fieldTestServiceImplEntity2);
        fieldTestServiceImplEntity1.setId(null);
        assertThat(fieldTestServiceImplEntity1).isNotEqualTo(fieldTestServiceImplEntity2);
    }
}
