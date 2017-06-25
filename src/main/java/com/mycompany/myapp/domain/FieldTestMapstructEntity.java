package com.mycompany.myapp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Objects;

import com.mycompany.myapp.domain.enumeration.EnumFieldClass;

import com.mycompany.myapp.domain.enumeration.EnumRequiredFieldClass;

/**
 * A FieldTestMapstructEntity.
 */
@Entity
@Table(name = "field_test_mapstruct_entity")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "fieldtestmapstructentity")
public class FieldTestMapstructEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "string_eva")
    private String stringEva;

    @NotNull
    @Column(name = "string_required_eva", nullable = false)
    private String stringRequiredEva;

    @Size(min = 0)
    @Column(name = "string_minlength_eva")
    private String stringMinlengthEva;

    @Size(max = 20)
    @Column(name = "string_maxlength_eva", length = 20)
    private String stringMaxlengthEva;

    @Pattern(regexp = "^[a-zA-Z0-9]*$")
    @Column(name = "string_pattern_eva")
    private String stringPatternEva;

    @Column(name = "integer_eva")
    private Integer integerEva;

    @NotNull
    @Column(name = "integer_required_eva", nullable = false)
    private Integer integerRequiredEva;

    @Min(value = 0)
    @Column(name = "integer_min_eva")
    private Integer integerMinEva;

    @Max(value = 100)
    @Column(name = "integer_max_eva")
    private Integer integerMaxEva;

    @Column(name = "long_eva")
    private Long longEva;

    @NotNull
    @Column(name = "long_required_eva", nullable = false)
    private Long longRequiredEva;

    @Min(value = 0)
    @Column(name = "long_min_eva")
    private Long longMinEva;

    @Max(value = 100)
    @Column(name = "long_max_eva")
    private Long longMaxEva;

    @Column(name = "float_eva")
    private Float floatEva;

    @NotNull
    @Column(name = "float_required_eva", nullable = false)
    private Float floatRequiredEva;

    @DecimalMin(value = "0")
    @Column(name = "float_min_eva")
    private Float floatMinEva;

    @DecimalMax(value = "100")
    @Column(name = "float_max_eva")
    private Float floatMaxEva;

    @NotNull
    @Column(name = "double_required_eva", nullable = false)
    private Double doubleRequiredEva;

    @DecimalMin(value = "0")
    @Column(name = "double_min_eva")
    private Double doubleMinEva;

    @DecimalMax(value = "100")
    @Column(name = "double_max_eva")
    private Double doubleMaxEva;

    @NotNull
    @Column(name = "big_decimal_required_eva", precision=10, scale=2, nullable = false)
    private BigDecimal bigDecimalRequiredEva;

    @DecimalMin(value = "0")
    @Column(name = "big_decimal_min_eva", precision=10, scale=2)
    private BigDecimal bigDecimalMinEva;

    @DecimalMax(value = "100")
    @Column(name = "big_decimal_max_eva", precision=10, scale=2)
    private BigDecimal bigDecimalMaxEva;

    @Column(name = "local_date_eva")
    private LocalDate localDateEva;

    @NotNull
    @Column(name = "local_date_required_eva", nullable = false)
    private LocalDate localDateRequiredEva;

    @Column(name = "instant_eva")
    private Instant instantEva;

    @NotNull
    @Column(name = "instante_required_eva", nullable = false)
    private Instant instanteRequiredEva;

    @Column(name = "zoned_date_time_eva")
    private ZonedDateTime zonedDateTimeEva;

    @NotNull
    @Column(name = "zoned_date_time_required_eva", nullable = false)
    private ZonedDateTime zonedDateTimeRequiredEva;

    @Column(name = "boolean_eva")
    private Boolean booleanEva;

    @NotNull
    @Column(name = "boolean_required_eva", nullable = false)
    private Boolean booleanRequiredEva;

    @Enumerated(EnumType.STRING)
    @Column(name = "enum_eva")
    private EnumFieldClass enumEva;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "enum_required_eva", nullable = false)
    private EnumRequiredFieldClass enumRequiredEva;

    @Lob
    @Column(name = "byte_image_eva")
    private byte[] byteImageEva;

    @Column(name = "byte_image_eva_content_type")
    private String byteImageEvaContentType;

    @NotNull
    @Lob
    @Column(name = "byte_image_required_eva", nullable = false)
    private byte[] byteImageRequiredEva;

    @Column(name = "byte_image_required_eva_content_type", nullable = false)
    private String byteImageRequiredEvaContentType;

    @Size(min = 0)
    @Lob
    @Column(name = "byte_image_minbytes_eva")
    private byte[] byteImageMinbytesEva;

    @Column(name = "byte_image_minbytes_eva_content_type")
    private String byteImageMinbytesEvaContentType;

    @Size(max = 10000)
    @Lob
    @Column(name = "byte_image_maxbytes_eva")
    private byte[] byteImageMaxbytesEva;

    @Column(name = "byte_image_maxbytes_eva_content_type")
    private String byteImageMaxbytesEvaContentType;

    @Lob
    @Column(name = "byte_any_eva")
    private byte[] byteAnyEva;

    @Column(name = "byte_any_eva_content_type")
    private String byteAnyEvaContentType;

    @NotNull
    @Lob
    @Column(name = "byte_any_required_eva", nullable = false)
    private byte[] byteAnyRequiredEva;

    @Column(name = "byte_any_required_eva_content_type", nullable = false)
    private String byteAnyRequiredEvaContentType;

    @Size(min = 0)
    @Lob
    @Column(name = "byte_any_minbytes_eva")
    private byte[] byteAnyMinbytesEva;

    @Column(name = "byte_any_minbytes_eva_content_type")
    private String byteAnyMinbytesEvaContentType;

    @Size(max = 10000)
    @Lob
    @Column(name = "byte_any_maxbytes_eva")
    private byte[] byteAnyMaxbytesEva;

    @Column(name = "byte_any_maxbytes_eva_content_type")
    private String byteAnyMaxbytesEvaContentType;

    @Lob
    @Column(name = "byte_text_eva")
    private String byteTextEva;

    @NotNull
    @Lob
    @Column(name = "byte_text_required_eva", nullable = false)
    private String byteTextRequiredEva;

    @Size(min = 0)
    @Lob
    @Column(name = "byte_text_minbytes_eva")
    private String byteTextMinbytesEva;

    @Size(max = 10000)
    @Lob
    @Column(name = "byte_text_maxbytes_eva")
    private String byteTextMaxbytesEva;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStringEva() {
        return stringEva;
    }

    public FieldTestMapstructEntity stringEva(String stringEva) {
        this.stringEva = stringEva;
        return this;
    }

    public void setStringEva(String stringEva) {
        this.stringEva = stringEva;
    }

    public String getStringRequiredEva() {
        return stringRequiredEva;
    }

    public FieldTestMapstructEntity stringRequiredEva(String stringRequiredEva) {
        this.stringRequiredEva = stringRequiredEva;
        return this;
    }

    public void setStringRequiredEva(String stringRequiredEva) {
        this.stringRequiredEva = stringRequiredEva;
    }

    public String getStringMinlengthEva() {
        return stringMinlengthEva;
    }

    public FieldTestMapstructEntity stringMinlengthEva(String stringMinlengthEva) {
        this.stringMinlengthEva = stringMinlengthEva;
        return this;
    }

    public void setStringMinlengthEva(String stringMinlengthEva) {
        this.stringMinlengthEva = stringMinlengthEva;
    }

    public String getStringMaxlengthEva() {
        return stringMaxlengthEva;
    }

    public FieldTestMapstructEntity stringMaxlengthEva(String stringMaxlengthEva) {
        this.stringMaxlengthEva = stringMaxlengthEva;
        return this;
    }

    public void setStringMaxlengthEva(String stringMaxlengthEva) {
        this.stringMaxlengthEva = stringMaxlengthEva;
    }

    public String getStringPatternEva() {
        return stringPatternEva;
    }

    public FieldTestMapstructEntity stringPatternEva(String stringPatternEva) {
        this.stringPatternEva = stringPatternEva;
        return this;
    }

    public void setStringPatternEva(String stringPatternEva) {
        this.stringPatternEva = stringPatternEva;
    }

    public Integer getIntegerEva() {
        return integerEva;
    }

    public FieldTestMapstructEntity integerEva(Integer integerEva) {
        this.integerEva = integerEva;
        return this;
    }

    public void setIntegerEva(Integer integerEva) {
        this.integerEva = integerEva;
    }

    public Integer getIntegerRequiredEva() {
        return integerRequiredEva;
    }

    public FieldTestMapstructEntity integerRequiredEva(Integer integerRequiredEva) {
        this.integerRequiredEva = integerRequiredEva;
        return this;
    }

    public void setIntegerRequiredEva(Integer integerRequiredEva) {
        this.integerRequiredEva = integerRequiredEva;
    }

    public Integer getIntegerMinEva() {
        return integerMinEva;
    }

    public FieldTestMapstructEntity integerMinEva(Integer integerMinEva) {
        this.integerMinEva = integerMinEva;
        return this;
    }

    public void setIntegerMinEva(Integer integerMinEva) {
        this.integerMinEva = integerMinEva;
    }

    public Integer getIntegerMaxEva() {
        return integerMaxEva;
    }

    public FieldTestMapstructEntity integerMaxEva(Integer integerMaxEva) {
        this.integerMaxEva = integerMaxEva;
        return this;
    }

    public void setIntegerMaxEva(Integer integerMaxEva) {
        this.integerMaxEva = integerMaxEva;
    }

    public Long getLongEva() {
        return longEva;
    }

    public FieldTestMapstructEntity longEva(Long longEva) {
        this.longEva = longEva;
        return this;
    }

    public void setLongEva(Long longEva) {
        this.longEva = longEva;
    }

    public Long getLongRequiredEva() {
        return longRequiredEva;
    }

    public FieldTestMapstructEntity longRequiredEva(Long longRequiredEva) {
        this.longRequiredEva = longRequiredEva;
        return this;
    }

    public void setLongRequiredEva(Long longRequiredEva) {
        this.longRequiredEva = longRequiredEva;
    }

    public Long getLongMinEva() {
        return longMinEva;
    }

    public FieldTestMapstructEntity longMinEva(Long longMinEva) {
        this.longMinEva = longMinEva;
        return this;
    }

    public void setLongMinEva(Long longMinEva) {
        this.longMinEva = longMinEva;
    }

    public Long getLongMaxEva() {
        return longMaxEva;
    }

    public FieldTestMapstructEntity longMaxEva(Long longMaxEva) {
        this.longMaxEva = longMaxEva;
        return this;
    }

    public void setLongMaxEva(Long longMaxEva) {
        this.longMaxEva = longMaxEva;
    }

    public Float getFloatEva() {
        return floatEva;
    }

    public FieldTestMapstructEntity floatEva(Float floatEva) {
        this.floatEva = floatEva;
        return this;
    }

    public void setFloatEva(Float floatEva) {
        this.floatEva = floatEva;
    }

    public Float getFloatRequiredEva() {
        return floatRequiredEva;
    }

    public FieldTestMapstructEntity floatRequiredEva(Float floatRequiredEva) {
        this.floatRequiredEva = floatRequiredEva;
        return this;
    }

    public void setFloatRequiredEva(Float floatRequiredEva) {
        this.floatRequiredEva = floatRequiredEva;
    }

    public Float getFloatMinEva() {
        return floatMinEva;
    }

    public FieldTestMapstructEntity floatMinEva(Float floatMinEva) {
        this.floatMinEva = floatMinEva;
        return this;
    }

    public void setFloatMinEva(Float floatMinEva) {
        this.floatMinEva = floatMinEva;
    }

    public Float getFloatMaxEva() {
        return floatMaxEva;
    }

    public FieldTestMapstructEntity floatMaxEva(Float floatMaxEva) {
        this.floatMaxEva = floatMaxEva;
        return this;
    }

    public void setFloatMaxEva(Float floatMaxEva) {
        this.floatMaxEva = floatMaxEva;
    }

    public Double getDoubleRequiredEva() {
        return doubleRequiredEva;
    }

    public FieldTestMapstructEntity doubleRequiredEva(Double doubleRequiredEva) {
        this.doubleRequiredEva = doubleRequiredEva;
        return this;
    }

    public void setDoubleRequiredEva(Double doubleRequiredEva) {
        this.doubleRequiredEva = doubleRequiredEva;
    }

    public Double getDoubleMinEva() {
        return doubleMinEva;
    }

    public FieldTestMapstructEntity doubleMinEva(Double doubleMinEva) {
        this.doubleMinEva = doubleMinEva;
        return this;
    }

    public void setDoubleMinEva(Double doubleMinEva) {
        this.doubleMinEva = doubleMinEva;
    }

    public Double getDoubleMaxEva() {
        return doubleMaxEva;
    }

    public FieldTestMapstructEntity doubleMaxEva(Double doubleMaxEva) {
        this.doubleMaxEva = doubleMaxEva;
        return this;
    }

    public void setDoubleMaxEva(Double doubleMaxEva) {
        this.doubleMaxEva = doubleMaxEva;
    }

    public BigDecimal getBigDecimalRequiredEva() {
        return bigDecimalRequiredEva;
    }

    public FieldTestMapstructEntity bigDecimalRequiredEva(BigDecimal bigDecimalRequiredEva) {
        this.bigDecimalRequiredEva = bigDecimalRequiredEva;
        return this;
    }

    public void setBigDecimalRequiredEva(BigDecimal bigDecimalRequiredEva) {
        this.bigDecimalRequiredEva = bigDecimalRequiredEva;
    }

    public BigDecimal getBigDecimalMinEva() {
        return bigDecimalMinEva;
    }

    public FieldTestMapstructEntity bigDecimalMinEva(BigDecimal bigDecimalMinEva) {
        this.bigDecimalMinEva = bigDecimalMinEva;
        return this;
    }

    public void setBigDecimalMinEva(BigDecimal bigDecimalMinEva) {
        this.bigDecimalMinEva = bigDecimalMinEva;
    }

    public BigDecimal getBigDecimalMaxEva() {
        return bigDecimalMaxEva;
    }

    public FieldTestMapstructEntity bigDecimalMaxEva(BigDecimal bigDecimalMaxEva) {
        this.bigDecimalMaxEva = bigDecimalMaxEva;
        return this;
    }

    public void setBigDecimalMaxEva(BigDecimal bigDecimalMaxEva) {
        this.bigDecimalMaxEva = bigDecimalMaxEva;
    }

    public LocalDate getLocalDateEva() {
        return localDateEva;
    }

    public FieldTestMapstructEntity localDateEva(LocalDate localDateEva) {
        this.localDateEva = localDateEva;
        return this;
    }

    public void setLocalDateEva(LocalDate localDateEva) {
        this.localDateEva = localDateEva;
    }

    public LocalDate getLocalDateRequiredEva() {
        return localDateRequiredEva;
    }

    public FieldTestMapstructEntity localDateRequiredEva(LocalDate localDateRequiredEva) {
        this.localDateRequiredEva = localDateRequiredEva;
        return this;
    }

    public void setLocalDateRequiredEva(LocalDate localDateRequiredEva) {
        this.localDateRequiredEva = localDateRequiredEva;
    }

    public Instant getInstantEva() {
        return instantEva;
    }

    public FieldTestMapstructEntity instantEva(Instant instantEva) {
        this.instantEva = instantEva;
        return this;
    }

    public void setInstantEva(Instant instantEva) {
        this.instantEva = instantEva;
    }

    public Instant getInstanteRequiredEva() {
        return instanteRequiredEva;
    }

    public FieldTestMapstructEntity instanteRequiredEva(Instant instanteRequiredEva) {
        this.instanteRequiredEva = instanteRequiredEva;
        return this;
    }

    public void setInstanteRequiredEva(Instant instanteRequiredEva) {
        this.instanteRequiredEva = instanteRequiredEva;
    }

    public ZonedDateTime getZonedDateTimeEva() {
        return zonedDateTimeEva;
    }

    public FieldTestMapstructEntity zonedDateTimeEva(ZonedDateTime zonedDateTimeEva) {
        this.zonedDateTimeEva = zonedDateTimeEva;
        return this;
    }

    public void setZonedDateTimeEva(ZonedDateTime zonedDateTimeEva) {
        this.zonedDateTimeEva = zonedDateTimeEva;
    }

    public ZonedDateTime getZonedDateTimeRequiredEva() {
        return zonedDateTimeRequiredEva;
    }

    public FieldTestMapstructEntity zonedDateTimeRequiredEva(ZonedDateTime zonedDateTimeRequiredEva) {
        this.zonedDateTimeRequiredEva = zonedDateTimeRequiredEva;
        return this;
    }

    public void setZonedDateTimeRequiredEva(ZonedDateTime zonedDateTimeRequiredEva) {
        this.zonedDateTimeRequiredEva = zonedDateTimeRequiredEva;
    }

    public Boolean isBooleanEva() {
        return booleanEva;
    }

    public FieldTestMapstructEntity booleanEva(Boolean booleanEva) {
        this.booleanEva = booleanEva;
        return this;
    }

    public void setBooleanEva(Boolean booleanEva) {
        this.booleanEva = booleanEva;
    }

    public Boolean isBooleanRequiredEva() {
        return booleanRequiredEva;
    }

    public FieldTestMapstructEntity booleanRequiredEva(Boolean booleanRequiredEva) {
        this.booleanRequiredEva = booleanRequiredEva;
        return this;
    }

    public void setBooleanRequiredEva(Boolean booleanRequiredEva) {
        this.booleanRequiredEva = booleanRequiredEva;
    }

    public EnumFieldClass getEnumEva() {
        return enumEva;
    }

    public FieldTestMapstructEntity enumEva(EnumFieldClass enumEva) {
        this.enumEva = enumEva;
        return this;
    }

    public void setEnumEva(EnumFieldClass enumEva) {
        this.enumEva = enumEva;
    }

    public EnumRequiredFieldClass getEnumRequiredEva() {
        return enumRequiredEva;
    }

    public FieldTestMapstructEntity enumRequiredEva(EnumRequiredFieldClass enumRequiredEva) {
        this.enumRequiredEva = enumRequiredEva;
        return this;
    }

    public void setEnumRequiredEva(EnumRequiredFieldClass enumRequiredEva) {
        this.enumRequiredEva = enumRequiredEva;
    }

    public byte[] getByteImageEva() {
        return byteImageEva;
    }

    public FieldTestMapstructEntity byteImageEva(byte[] byteImageEva) {
        this.byteImageEva = byteImageEva;
        return this;
    }

    public void setByteImageEva(byte[] byteImageEva) {
        this.byteImageEva = byteImageEva;
    }

    public String getByteImageEvaContentType() {
        return byteImageEvaContentType;
    }

    public FieldTestMapstructEntity byteImageEvaContentType(String byteImageEvaContentType) {
        this.byteImageEvaContentType = byteImageEvaContentType;
        return this;
    }

    public void setByteImageEvaContentType(String byteImageEvaContentType) {
        this.byteImageEvaContentType = byteImageEvaContentType;
    }

    public byte[] getByteImageRequiredEva() {
        return byteImageRequiredEva;
    }

    public FieldTestMapstructEntity byteImageRequiredEva(byte[] byteImageRequiredEva) {
        this.byteImageRequiredEva = byteImageRequiredEva;
        return this;
    }

    public void setByteImageRequiredEva(byte[] byteImageRequiredEva) {
        this.byteImageRequiredEva = byteImageRequiredEva;
    }

    public String getByteImageRequiredEvaContentType() {
        return byteImageRequiredEvaContentType;
    }

    public FieldTestMapstructEntity byteImageRequiredEvaContentType(String byteImageRequiredEvaContentType) {
        this.byteImageRequiredEvaContentType = byteImageRequiredEvaContentType;
        return this;
    }

    public void setByteImageRequiredEvaContentType(String byteImageRequiredEvaContentType) {
        this.byteImageRequiredEvaContentType = byteImageRequiredEvaContentType;
    }

    public byte[] getByteImageMinbytesEva() {
        return byteImageMinbytesEva;
    }

    public FieldTestMapstructEntity byteImageMinbytesEva(byte[] byteImageMinbytesEva) {
        this.byteImageMinbytesEva = byteImageMinbytesEva;
        return this;
    }

    public void setByteImageMinbytesEva(byte[] byteImageMinbytesEva) {
        this.byteImageMinbytesEva = byteImageMinbytesEva;
    }

    public String getByteImageMinbytesEvaContentType() {
        return byteImageMinbytesEvaContentType;
    }

    public FieldTestMapstructEntity byteImageMinbytesEvaContentType(String byteImageMinbytesEvaContentType) {
        this.byteImageMinbytesEvaContentType = byteImageMinbytesEvaContentType;
        return this;
    }

    public void setByteImageMinbytesEvaContentType(String byteImageMinbytesEvaContentType) {
        this.byteImageMinbytesEvaContentType = byteImageMinbytesEvaContentType;
    }

    public byte[] getByteImageMaxbytesEva() {
        return byteImageMaxbytesEva;
    }

    public FieldTestMapstructEntity byteImageMaxbytesEva(byte[] byteImageMaxbytesEva) {
        this.byteImageMaxbytesEva = byteImageMaxbytesEva;
        return this;
    }

    public void setByteImageMaxbytesEva(byte[] byteImageMaxbytesEva) {
        this.byteImageMaxbytesEva = byteImageMaxbytesEva;
    }

    public String getByteImageMaxbytesEvaContentType() {
        return byteImageMaxbytesEvaContentType;
    }

    public FieldTestMapstructEntity byteImageMaxbytesEvaContentType(String byteImageMaxbytesEvaContentType) {
        this.byteImageMaxbytesEvaContentType = byteImageMaxbytesEvaContentType;
        return this;
    }

    public void setByteImageMaxbytesEvaContentType(String byteImageMaxbytesEvaContentType) {
        this.byteImageMaxbytesEvaContentType = byteImageMaxbytesEvaContentType;
    }

    public byte[] getByteAnyEva() {
        return byteAnyEva;
    }

    public FieldTestMapstructEntity byteAnyEva(byte[] byteAnyEva) {
        this.byteAnyEva = byteAnyEva;
        return this;
    }

    public void setByteAnyEva(byte[] byteAnyEva) {
        this.byteAnyEva = byteAnyEva;
    }

    public String getByteAnyEvaContentType() {
        return byteAnyEvaContentType;
    }

    public FieldTestMapstructEntity byteAnyEvaContentType(String byteAnyEvaContentType) {
        this.byteAnyEvaContentType = byteAnyEvaContentType;
        return this;
    }

    public void setByteAnyEvaContentType(String byteAnyEvaContentType) {
        this.byteAnyEvaContentType = byteAnyEvaContentType;
    }

    public byte[] getByteAnyRequiredEva() {
        return byteAnyRequiredEva;
    }

    public FieldTestMapstructEntity byteAnyRequiredEva(byte[] byteAnyRequiredEva) {
        this.byteAnyRequiredEva = byteAnyRequiredEva;
        return this;
    }

    public void setByteAnyRequiredEva(byte[] byteAnyRequiredEva) {
        this.byteAnyRequiredEva = byteAnyRequiredEva;
    }

    public String getByteAnyRequiredEvaContentType() {
        return byteAnyRequiredEvaContentType;
    }

    public FieldTestMapstructEntity byteAnyRequiredEvaContentType(String byteAnyRequiredEvaContentType) {
        this.byteAnyRequiredEvaContentType = byteAnyRequiredEvaContentType;
        return this;
    }

    public void setByteAnyRequiredEvaContentType(String byteAnyRequiredEvaContentType) {
        this.byteAnyRequiredEvaContentType = byteAnyRequiredEvaContentType;
    }

    public byte[] getByteAnyMinbytesEva() {
        return byteAnyMinbytesEva;
    }

    public FieldTestMapstructEntity byteAnyMinbytesEva(byte[] byteAnyMinbytesEva) {
        this.byteAnyMinbytesEva = byteAnyMinbytesEva;
        return this;
    }

    public void setByteAnyMinbytesEva(byte[] byteAnyMinbytesEva) {
        this.byteAnyMinbytesEva = byteAnyMinbytesEva;
    }

    public String getByteAnyMinbytesEvaContentType() {
        return byteAnyMinbytesEvaContentType;
    }

    public FieldTestMapstructEntity byteAnyMinbytesEvaContentType(String byteAnyMinbytesEvaContentType) {
        this.byteAnyMinbytesEvaContentType = byteAnyMinbytesEvaContentType;
        return this;
    }

    public void setByteAnyMinbytesEvaContentType(String byteAnyMinbytesEvaContentType) {
        this.byteAnyMinbytesEvaContentType = byteAnyMinbytesEvaContentType;
    }

    public byte[] getByteAnyMaxbytesEva() {
        return byteAnyMaxbytesEva;
    }

    public FieldTestMapstructEntity byteAnyMaxbytesEva(byte[] byteAnyMaxbytesEva) {
        this.byteAnyMaxbytesEva = byteAnyMaxbytesEva;
        return this;
    }

    public void setByteAnyMaxbytesEva(byte[] byteAnyMaxbytesEva) {
        this.byteAnyMaxbytesEva = byteAnyMaxbytesEva;
    }

    public String getByteAnyMaxbytesEvaContentType() {
        return byteAnyMaxbytesEvaContentType;
    }

    public FieldTestMapstructEntity byteAnyMaxbytesEvaContentType(String byteAnyMaxbytesEvaContentType) {
        this.byteAnyMaxbytesEvaContentType = byteAnyMaxbytesEvaContentType;
        return this;
    }

    public void setByteAnyMaxbytesEvaContentType(String byteAnyMaxbytesEvaContentType) {
        this.byteAnyMaxbytesEvaContentType = byteAnyMaxbytesEvaContentType;
    }

    public String getByteTextEva() {
        return byteTextEva;
    }

    public FieldTestMapstructEntity byteTextEva(String byteTextEva) {
        this.byteTextEva = byteTextEva;
        return this;
    }

    public void setByteTextEva(String byteTextEva) {
        this.byteTextEva = byteTextEva;
    }

    public String getByteTextRequiredEva() {
        return byteTextRequiredEva;
    }

    public FieldTestMapstructEntity byteTextRequiredEva(String byteTextRequiredEva) {
        this.byteTextRequiredEva = byteTextRequiredEva;
        return this;
    }

    public void setByteTextRequiredEva(String byteTextRequiredEva) {
        this.byteTextRequiredEva = byteTextRequiredEva;
    }

    public String getByteTextMinbytesEva() {
        return byteTextMinbytesEva;
    }

    public FieldTestMapstructEntity byteTextMinbytesEva(String byteTextMinbytesEva) {
        this.byteTextMinbytesEva = byteTextMinbytesEva;
        return this;
    }

    public void setByteTextMinbytesEva(String byteTextMinbytesEva) {
        this.byteTextMinbytesEva = byteTextMinbytesEva;
    }

    public String getByteTextMaxbytesEva() {
        return byteTextMaxbytesEva;
    }

    public FieldTestMapstructEntity byteTextMaxbytesEva(String byteTextMaxbytesEva) {
        this.byteTextMaxbytesEva = byteTextMaxbytesEva;
        return this;
    }

    public void setByteTextMaxbytesEva(String byteTextMaxbytesEva) {
        this.byteTextMaxbytesEva = byteTextMaxbytesEva;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FieldTestMapstructEntity fieldTestMapstructEntity = (FieldTestMapstructEntity) o;
        if (fieldTestMapstructEntity.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), fieldTestMapstructEntity.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "FieldTestMapstructEntity{" +
            "id=" + getId() +
            ", stringEva='" + getStringEva() + "'" +
            ", stringRequiredEva='" + getStringRequiredEva() + "'" +
            ", stringMinlengthEva='" + getStringMinlengthEva() + "'" +
            ", stringMaxlengthEva='" + getStringMaxlengthEva() + "'" +
            ", stringPatternEva='" + getStringPatternEva() + "'" +
            ", integerEva='" + getIntegerEva() + "'" +
            ", integerRequiredEva='" + getIntegerRequiredEva() + "'" +
            ", integerMinEva='" + getIntegerMinEva() + "'" +
            ", integerMaxEva='" + getIntegerMaxEva() + "'" +
            ", longEva='" + getLongEva() + "'" +
            ", longRequiredEva='" + getLongRequiredEva() + "'" +
            ", longMinEva='" + getLongMinEva() + "'" +
            ", longMaxEva='" + getLongMaxEva() + "'" +
            ", floatEva='" + getFloatEva() + "'" +
            ", floatRequiredEva='" + getFloatRequiredEva() + "'" +
            ", floatMinEva='" + getFloatMinEva() + "'" +
            ", floatMaxEva='" + getFloatMaxEva() + "'" +
            ", doubleRequiredEva='" + getDoubleRequiredEva() + "'" +
            ", doubleMinEva='" + getDoubleMinEva() + "'" +
            ", doubleMaxEva='" + getDoubleMaxEva() + "'" +
            ", bigDecimalRequiredEva='" + getBigDecimalRequiredEva() + "'" +
            ", bigDecimalMinEva='" + getBigDecimalMinEva() + "'" +
            ", bigDecimalMaxEva='" + getBigDecimalMaxEva() + "'" +
            ", localDateEva='" + getLocalDateEva() + "'" +
            ", localDateRequiredEva='" + getLocalDateRequiredEva() + "'" +
            ", instantEva='" + getInstantEva() + "'" +
            ", instanteRequiredEva='" + getInstanteRequiredEva() + "'" +
            ", zonedDateTimeEva='" + getZonedDateTimeEva() + "'" +
            ", zonedDateTimeRequiredEva='" + getZonedDateTimeRequiredEva() + "'" +
            ", booleanEva='" + isBooleanEva() + "'" +
            ", booleanRequiredEva='" + isBooleanRequiredEva() + "'" +
            ", enumEva='" + getEnumEva() + "'" +
            ", enumRequiredEva='" + getEnumRequiredEva() + "'" +
            ", byteImageEva='" + getByteImageEva() + "'" +
            ", byteImageEvaContentType='" + byteImageEvaContentType + "'" +
            ", byteImageRequiredEva='" + getByteImageRequiredEva() + "'" +
            ", byteImageRequiredEvaContentType='" + byteImageRequiredEvaContentType + "'" +
            ", byteImageMinbytesEva='" + getByteImageMinbytesEva() + "'" +
            ", byteImageMinbytesEvaContentType='" + byteImageMinbytesEvaContentType + "'" +
            ", byteImageMaxbytesEva='" + getByteImageMaxbytesEva() + "'" +
            ", byteImageMaxbytesEvaContentType='" + byteImageMaxbytesEvaContentType + "'" +
            ", byteAnyEva='" + getByteAnyEva() + "'" +
            ", byteAnyEvaContentType='" + byteAnyEvaContentType + "'" +
            ", byteAnyRequiredEva='" + getByteAnyRequiredEva() + "'" +
            ", byteAnyRequiredEvaContentType='" + byteAnyRequiredEvaContentType + "'" +
            ", byteAnyMinbytesEva='" + getByteAnyMinbytesEva() + "'" +
            ", byteAnyMinbytesEvaContentType='" + byteAnyMinbytesEvaContentType + "'" +
            ", byteAnyMaxbytesEva='" + getByteAnyMaxbytesEva() + "'" +
            ", byteAnyMaxbytesEvaContentType='" + byteAnyMaxbytesEvaContentType + "'" +
            ", byteTextEva='" + getByteTextEva() + "'" +
            ", byteTextRequiredEva='" + getByteTextRequiredEva() + "'" +
            ", byteTextMinbytesEva='" + getByteTextMinbytesEva() + "'" +
            ", byteTextMaxbytesEva='" + getByteTextMaxbytesEva() + "'" +
            "}";
    }
}
