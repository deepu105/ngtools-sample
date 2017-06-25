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
 * A FieldTestEntity.
 */
@Entity
@Table(name = "field_test_entity")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "fieldtestentity")
public class FieldTestEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "string_tom")
    private String stringTom;

    @NotNull
    @Column(name = "string_required_tom", nullable = false)
    private String stringRequiredTom;

    @Size(min = 0)
    @Column(name = "string_minlength_tom")
    private String stringMinlengthTom;

    @Size(max = 20)
    @Column(name = "string_maxlength_tom", length = 20)
    private String stringMaxlengthTom;

    @Pattern(regexp = "^[a-zA-Z0-9]*$")
    @Column(name = "string_pattern_tom")
    private String stringPatternTom;

    @Column(name = "integer_tom")
    private Integer integerTom;

    @NotNull
    @Column(name = "integer_required_tom", nullable = false)
    private Integer integerRequiredTom;

    @Min(value = 0)
    @Column(name = "integer_min_tom")
    private Integer integerMinTom;

    @Max(value = 100)
    @Column(name = "integer_max_tom")
    private Integer integerMaxTom;

    @Column(name = "long_tom")
    private Long longTom;

    @NotNull
    @Column(name = "long_required_tom", nullable = false)
    private Long longRequiredTom;

    @Min(value = 0)
    @Column(name = "long_min_tom")
    private Long longMinTom;

    @Max(value = 100)
    @Column(name = "long_max_tom")
    private Long longMaxTom;

    @Column(name = "float_tom")
    private Float floatTom;

    @NotNull
    @Column(name = "float_required_tom", nullable = false)
    private Float floatRequiredTom;

    @DecimalMin(value = "0")
    @Column(name = "float_min_tom")
    private Float floatMinTom;

    @DecimalMax(value = "100")
    @Column(name = "float_max_tom")
    private Float floatMaxTom;

    @NotNull
    @Column(name = "double_required_tom", nullable = false)
    private Double doubleRequiredTom;

    @DecimalMin(value = "0")
    @Column(name = "double_min_tom")
    private Double doubleMinTom;

    @DecimalMax(value = "100")
    @Column(name = "double_max_tom")
    private Double doubleMaxTom;

    @NotNull
    @Column(name = "big_decimal_required_tom", precision=10, scale=2, nullable = false)
    private BigDecimal bigDecimalRequiredTom;

    @DecimalMin(value = "0")
    @Column(name = "big_decimal_min_tom", precision=10, scale=2)
    private BigDecimal bigDecimalMinTom;

    @DecimalMax(value = "100")
    @Column(name = "big_decimal_max_tom", precision=10, scale=2)
    private BigDecimal bigDecimalMaxTom;

    @Column(name = "local_date_tom")
    private LocalDate localDateTom;

    @NotNull
    @Column(name = "local_date_required_tom", nullable = false)
    private LocalDate localDateRequiredTom;

    @Column(name = "instant_tom")
    private Instant instantTom;

    @NotNull
    @Column(name = "instante_required_tom", nullable = false)
    private Instant instanteRequiredTom;

    @Column(name = "zoned_date_time_tom")
    private ZonedDateTime zonedDateTimeTom;

    @NotNull
    @Column(name = "zoned_date_time_required_tom", nullable = false)
    private ZonedDateTime zonedDateTimeRequiredTom;

    @Column(name = "boolean_tom")
    private Boolean booleanTom;

    @NotNull
    @Column(name = "boolean_required_tom", nullable = false)
    private Boolean booleanRequiredTom;

    @Enumerated(EnumType.STRING)
    @Column(name = "enum_tom")
    private EnumFieldClass enumTom;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "enum_required_tom", nullable = false)
    private EnumRequiredFieldClass enumRequiredTom;

    @Lob
    @Column(name = "byte_image_tom")
    private byte[] byteImageTom;

    @Column(name = "byte_image_tom_content_type")
    private String byteImageTomContentType;

    @NotNull
    @Lob
    @Column(name = "byte_image_required_tom", nullable = false)
    private byte[] byteImageRequiredTom;

    @Column(name = "byte_image_required_tom_content_type", nullable = false)
    private String byteImageRequiredTomContentType;

    @Size(min = 0)
    @Lob
    @Column(name = "byte_image_minbytes_tom")
    private byte[] byteImageMinbytesTom;

    @Column(name = "byte_image_minbytes_tom_content_type")
    private String byteImageMinbytesTomContentType;

    @Size(max = 10000)
    @Lob
    @Column(name = "byte_image_maxbytes_tom")
    private byte[] byteImageMaxbytesTom;

    @Column(name = "byte_image_maxbytes_tom_content_type")
    private String byteImageMaxbytesTomContentType;

    @Lob
    @Column(name = "byte_any_tom")
    private byte[] byteAnyTom;

    @Column(name = "byte_any_tom_content_type")
    private String byteAnyTomContentType;

    @NotNull
    @Lob
    @Column(name = "byte_any_required_tom", nullable = false)
    private byte[] byteAnyRequiredTom;

    @Column(name = "byte_any_required_tom_content_type", nullable = false)
    private String byteAnyRequiredTomContentType;

    @Size(min = 0)
    @Lob
    @Column(name = "byte_any_minbytes_tom")
    private byte[] byteAnyMinbytesTom;

    @Column(name = "byte_any_minbytes_tom_content_type")
    private String byteAnyMinbytesTomContentType;

    @Size(max = 10000)
    @Lob
    @Column(name = "byte_any_maxbytes_tom")
    private byte[] byteAnyMaxbytesTom;

    @Column(name = "byte_any_maxbytes_tom_content_type")
    private String byteAnyMaxbytesTomContentType;

    @Lob
    @Column(name = "byte_text_tom")
    private String byteTextTom;

    @NotNull
    @Lob
    @Column(name = "byte_text_required_tom", nullable = false)
    private String byteTextRequiredTom;

    @Size(min = 0)
    @Lob
    @Column(name = "byte_text_minbytes_tom")
    private String byteTextMinbytesTom;

    @Size(max = 10000)
    @Lob
    @Column(name = "byte_text_maxbytes_tom")
    private String byteTextMaxbytesTom;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStringTom() {
        return stringTom;
    }

    public FieldTestEntity stringTom(String stringTom) {
        this.stringTom = stringTom;
        return this;
    }

    public void setStringTom(String stringTom) {
        this.stringTom = stringTom;
    }

    public String getStringRequiredTom() {
        return stringRequiredTom;
    }

    public FieldTestEntity stringRequiredTom(String stringRequiredTom) {
        this.stringRequiredTom = stringRequiredTom;
        return this;
    }

    public void setStringRequiredTom(String stringRequiredTom) {
        this.stringRequiredTom = stringRequiredTom;
    }

    public String getStringMinlengthTom() {
        return stringMinlengthTom;
    }

    public FieldTestEntity stringMinlengthTom(String stringMinlengthTom) {
        this.stringMinlengthTom = stringMinlengthTom;
        return this;
    }

    public void setStringMinlengthTom(String stringMinlengthTom) {
        this.stringMinlengthTom = stringMinlengthTom;
    }

    public String getStringMaxlengthTom() {
        return stringMaxlengthTom;
    }

    public FieldTestEntity stringMaxlengthTom(String stringMaxlengthTom) {
        this.stringMaxlengthTom = stringMaxlengthTom;
        return this;
    }

    public void setStringMaxlengthTom(String stringMaxlengthTom) {
        this.stringMaxlengthTom = stringMaxlengthTom;
    }

    public String getStringPatternTom() {
        return stringPatternTom;
    }

    public FieldTestEntity stringPatternTom(String stringPatternTom) {
        this.stringPatternTom = stringPatternTom;
        return this;
    }

    public void setStringPatternTom(String stringPatternTom) {
        this.stringPatternTom = stringPatternTom;
    }

    public Integer getIntegerTom() {
        return integerTom;
    }

    public FieldTestEntity integerTom(Integer integerTom) {
        this.integerTom = integerTom;
        return this;
    }

    public void setIntegerTom(Integer integerTom) {
        this.integerTom = integerTom;
    }

    public Integer getIntegerRequiredTom() {
        return integerRequiredTom;
    }

    public FieldTestEntity integerRequiredTom(Integer integerRequiredTom) {
        this.integerRequiredTom = integerRequiredTom;
        return this;
    }

    public void setIntegerRequiredTom(Integer integerRequiredTom) {
        this.integerRequiredTom = integerRequiredTom;
    }

    public Integer getIntegerMinTom() {
        return integerMinTom;
    }

    public FieldTestEntity integerMinTom(Integer integerMinTom) {
        this.integerMinTom = integerMinTom;
        return this;
    }

    public void setIntegerMinTom(Integer integerMinTom) {
        this.integerMinTom = integerMinTom;
    }

    public Integer getIntegerMaxTom() {
        return integerMaxTom;
    }

    public FieldTestEntity integerMaxTom(Integer integerMaxTom) {
        this.integerMaxTom = integerMaxTom;
        return this;
    }

    public void setIntegerMaxTom(Integer integerMaxTom) {
        this.integerMaxTom = integerMaxTom;
    }

    public Long getLongTom() {
        return longTom;
    }

    public FieldTestEntity longTom(Long longTom) {
        this.longTom = longTom;
        return this;
    }

    public void setLongTom(Long longTom) {
        this.longTom = longTom;
    }

    public Long getLongRequiredTom() {
        return longRequiredTom;
    }

    public FieldTestEntity longRequiredTom(Long longRequiredTom) {
        this.longRequiredTom = longRequiredTom;
        return this;
    }

    public void setLongRequiredTom(Long longRequiredTom) {
        this.longRequiredTom = longRequiredTom;
    }

    public Long getLongMinTom() {
        return longMinTom;
    }

    public FieldTestEntity longMinTom(Long longMinTom) {
        this.longMinTom = longMinTom;
        return this;
    }

    public void setLongMinTom(Long longMinTom) {
        this.longMinTom = longMinTom;
    }

    public Long getLongMaxTom() {
        return longMaxTom;
    }

    public FieldTestEntity longMaxTom(Long longMaxTom) {
        this.longMaxTom = longMaxTom;
        return this;
    }

    public void setLongMaxTom(Long longMaxTom) {
        this.longMaxTom = longMaxTom;
    }

    public Float getFloatTom() {
        return floatTom;
    }

    public FieldTestEntity floatTom(Float floatTom) {
        this.floatTom = floatTom;
        return this;
    }

    public void setFloatTom(Float floatTom) {
        this.floatTom = floatTom;
    }

    public Float getFloatRequiredTom() {
        return floatRequiredTom;
    }

    public FieldTestEntity floatRequiredTom(Float floatRequiredTom) {
        this.floatRequiredTom = floatRequiredTom;
        return this;
    }

    public void setFloatRequiredTom(Float floatRequiredTom) {
        this.floatRequiredTom = floatRequiredTom;
    }

    public Float getFloatMinTom() {
        return floatMinTom;
    }

    public FieldTestEntity floatMinTom(Float floatMinTom) {
        this.floatMinTom = floatMinTom;
        return this;
    }

    public void setFloatMinTom(Float floatMinTom) {
        this.floatMinTom = floatMinTom;
    }

    public Float getFloatMaxTom() {
        return floatMaxTom;
    }

    public FieldTestEntity floatMaxTom(Float floatMaxTom) {
        this.floatMaxTom = floatMaxTom;
        return this;
    }

    public void setFloatMaxTom(Float floatMaxTom) {
        this.floatMaxTom = floatMaxTom;
    }

    public Double getDoubleRequiredTom() {
        return doubleRequiredTom;
    }

    public FieldTestEntity doubleRequiredTom(Double doubleRequiredTom) {
        this.doubleRequiredTom = doubleRequiredTom;
        return this;
    }

    public void setDoubleRequiredTom(Double doubleRequiredTom) {
        this.doubleRequiredTom = doubleRequiredTom;
    }

    public Double getDoubleMinTom() {
        return doubleMinTom;
    }

    public FieldTestEntity doubleMinTom(Double doubleMinTom) {
        this.doubleMinTom = doubleMinTom;
        return this;
    }

    public void setDoubleMinTom(Double doubleMinTom) {
        this.doubleMinTom = doubleMinTom;
    }

    public Double getDoubleMaxTom() {
        return doubleMaxTom;
    }

    public FieldTestEntity doubleMaxTom(Double doubleMaxTom) {
        this.doubleMaxTom = doubleMaxTom;
        return this;
    }

    public void setDoubleMaxTom(Double doubleMaxTom) {
        this.doubleMaxTom = doubleMaxTom;
    }

    public BigDecimal getBigDecimalRequiredTom() {
        return bigDecimalRequiredTom;
    }

    public FieldTestEntity bigDecimalRequiredTom(BigDecimal bigDecimalRequiredTom) {
        this.bigDecimalRequiredTom = bigDecimalRequiredTom;
        return this;
    }

    public void setBigDecimalRequiredTom(BigDecimal bigDecimalRequiredTom) {
        this.bigDecimalRequiredTom = bigDecimalRequiredTom;
    }

    public BigDecimal getBigDecimalMinTom() {
        return bigDecimalMinTom;
    }

    public FieldTestEntity bigDecimalMinTom(BigDecimal bigDecimalMinTom) {
        this.bigDecimalMinTom = bigDecimalMinTom;
        return this;
    }

    public void setBigDecimalMinTom(BigDecimal bigDecimalMinTom) {
        this.bigDecimalMinTom = bigDecimalMinTom;
    }

    public BigDecimal getBigDecimalMaxTom() {
        return bigDecimalMaxTom;
    }

    public FieldTestEntity bigDecimalMaxTom(BigDecimal bigDecimalMaxTom) {
        this.bigDecimalMaxTom = bigDecimalMaxTom;
        return this;
    }

    public void setBigDecimalMaxTom(BigDecimal bigDecimalMaxTom) {
        this.bigDecimalMaxTom = bigDecimalMaxTom;
    }

    public LocalDate getLocalDateTom() {
        return localDateTom;
    }

    public FieldTestEntity localDateTom(LocalDate localDateTom) {
        this.localDateTom = localDateTom;
        return this;
    }

    public void setLocalDateTom(LocalDate localDateTom) {
        this.localDateTom = localDateTom;
    }

    public LocalDate getLocalDateRequiredTom() {
        return localDateRequiredTom;
    }

    public FieldTestEntity localDateRequiredTom(LocalDate localDateRequiredTom) {
        this.localDateRequiredTom = localDateRequiredTom;
        return this;
    }

    public void setLocalDateRequiredTom(LocalDate localDateRequiredTom) {
        this.localDateRequiredTom = localDateRequiredTom;
    }

    public Instant getInstantTom() {
        return instantTom;
    }

    public FieldTestEntity instantTom(Instant instantTom) {
        this.instantTom = instantTom;
        return this;
    }

    public void setInstantTom(Instant instantTom) {
        this.instantTom = instantTom;
    }

    public Instant getInstanteRequiredTom() {
        return instanteRequiredTom;
    }

    public FieldTestEntity instanteRequiredTom(Instant instanteRequiredTom) {
        this.instanteRequiredTom = instanteRequiredTom;
        return this;
    }

    public void setInstanteRequiredTom(Instant instanteRequiredTom) {
        this.instanteRequiredTom = instanteRequiredTom;
    }

    public ZonedDateTime getZonedDateTimeTom() {
        return zonedDateTimeTom;
    }

    public FieldTestEntity zonedDateTimeTom(ZonedDateTime zonedDateTimeTom) {
        this.zonedDateTimeTom = zonedDateTimeTom;
        return this;
    }

    public void setZonedDateTimeTom(ZonedDateTime zonedDateTimeTom) {
        this.zonedDateTimeTom = zonedDateTimeTom;
    }

    public ZonedDateTime getZonedDateTimeRequiredTom() {
        return zonedDateTimeRequiredTom;
    }

    public FieldTestEntity zonedDateTimeRequiredTom(ZonedDateTime zonedDateTimeRequiredTom) {
        this.zonedDateTimeRequiredTom = zonedDateTimeRequiredTom;
        return this;
    }

    public void setZonedDateTimeRequiredTom(ZonedDateTime zonedDateTimeRequiredTom) {
        this.zonedDateTimeRequiredTom = zonedDateTimeRequiredTom;
    }

    public Boolean isBooleanTom() {
        return booleanTom;
    }

    public FieldTestEntity booleanTom(Boolean booleanTom) {
        this.booleanTom = booleanTom;
        return this;
    }

    public void setBooleanTom(Boolean booleanTom) {
        this.booleanTom = booleanTom;
    }

    public Boolean isBooleanRequiredTom() {
        return booleanRequiredTom;
    }

    public FieldTestEntity booleanRequiredTom(Boolean booleanRequiredTom) {
        this.booleanRequiredTom = booleanRequiredTom;
        return this;
    }

    public void setBooleanRequiredTom(Boolean booleanRequiredTom) {
        this.booleanRequiredTom = booleanRequiredTom;
    }

    public EnumFieldClass getEnumTom() {
        return enumTom;
    }

    public FieldTestEntity enumTom(EnumFieldClass enumTom) {
        this.enumTom = enumTom;
        return this;
    }

    public void setEnumTom(EnumFieldClass enumTom) {
        this.enumTom = enumTom;
    }

    public EnumRequiredFieldClass getEnumRequiredTom() {
        return enumRequiredTom;
    }

    public FieldTestEntity enumRequiredTom(EnumRequiredFieldClass enumRequiredTom) {
        this.enumRequiredTom = enumRequiredTom;
        return this;
    }

    public void setEnumRequiredTom(EnumRequiredFieldClass enumRequiredTom) {
        this.enumRequiredTom = enumRequiredTom;
    }

    public byte[] getByteImageTom() {
        return byteImageTom;
    }

    public FieldTestEntity byteImageTom(byte[] byteImageTom) {
        this.byteImageTom = byteImageTom;
        return this;
    }

    public void setByteImageTom(byte[] byteImageTom) {
        this.byteImageTom = byteImageTom;
    }

    public String getByteImageTomContentType() {
        return byteImageTomContentType;
    }

    public FieldTestEntity byteImageTomContentType(String byteImageTomContentType) {
        this.byteImageTomContentType = byteImageTomContentType;
        return this;
    }

    public void setByteImageTomContentType(String byteImageTomContentType) {
        this.byteImageTomContentType = byteImageTomContentType;
    }

    public byte[] getByteImageRequiredTom() {
        return byteImageRequiredTom;
    }

    public FieldTestEntity byteImageRequiredTom(byte[] byteImageRequiredTom) {
        this.byteImageRequiredTom = byteImageRequiredTom;
        return this;
    }

    public void setByteImageRequiredTom(byte[] byteImageRequiredTom) {
        this.byteImageRequiredTom = byteImageRequiredTom;
    }

    public String getByteImageRequiredTomContentType() {
        return byteImageRequiredTomContentType;
    }

    public FieldTestEntity byteImageRequiredTomContentType(String byteImageRequiredTomContentType) {
        this.byteImageRequiredTomContentType = byteImageRequiredTomContentType;
        return this;
    }

    public void setByteImageRequiredTomContentType(String byteImageRequiredTomContentType) {
        this.byteImageRequiredTomContentType = byteImageRequiredTomContentType;
    }

    public byte[] getByteImageMinbytesTom() {
        return byteImageMinbytesTom;
    }

    public FieldTestEntity byteImageMinbytesTom(byte[] byteImageMinbytesTom) {
        this.byteImageMinbytesTom = byteImageMinbytesTom;
        return this;
    }

    public void setByteImageMinbytesTom(byte[] byteImageMinbytesTom) {
        this.byteImageMinbytesTom = byteImageMinbytesTom;
    }

    public String getByteImageMinbytesTomContentType() {
        return byteImageMinbytesTomContentType;
    }

    public FieldTestEntity byteImageMinbytesTomContentType(String byteImageMinbytesTomContentType) {
        this.byteImageMinbytesTomContentType = byteImageMinbytesTomContentType;
        return this;
    }

    public void setByteImageMinbytesTomContentType(String byteImageMinbytesTomContentType) {
        this.byteImageMinbytesTomContentType = byteImageMinbytesTomContentType;
    }

    public byte[] getByteImageMaxbytesTom() {
        return byteImageMaxbytesTom;
    }

    public FieldTestEntity byteImageMaxbytesTom(byte[] byteImageMaxbytesTom) {
        this.byteImageMaxbytesTom = byteImageMaxbytesTom;
        return this;
    }

    public void setByteImageMaxbytesTom(byte[] byteImageMaxbytesTom) {
        this.byteImageMaxbytesTom = byteImageMaxbytesTom;
    }

    public String getByteImageMaxbytesTomContentType() {
        return byteImageMaxbytesTomContentType;
    }

    public FieldTestEntity byteImageMaxbytesTomContentType(String byteImageMaxbytesTomContentType) {
        this.byteImageMaxbytesTomContentType = byteImageMaxbytesTomContentType;
        return this;
    }

    public void setByteImageMaxbytesTomContentType(String byteImageMaxbytesTomContentType) {
        this.byteImageMaxbytesTomContentType = byteImageMaxbytesTomContentType;
    }

    public byte[] getByteAnyTom() {
        return byteAnyTom;
    }

    public FieldTestEntity byteAnyTom(byte[] byteAnyTom) {
        this.byteAnyTom = byteAnyTom;
        return this;
    }

    public void setByteAnyTom(byte[] byteAnyTom) {
        this.byteAnyTom = byteAnyTom;
    }

    public String getByteAnyTomContentType() {
        return byteAnyTomContentType;
    }

    public FieldTestEntity byteAnyTomContentType(String byteAnyTomContentType) {
        this.byteAnyTomContentType = byteAnyTomContentType;
        return this;
    }

    public void setByteAnyTomContentType(String byteAnyTomContentType) {
        this.byteAnyTomContentType = byteAnyTomContentType;
    }

    public byte[] getByteAnyRequiredTom() {
        return byteAnyRequiredTom;
    }

    public FieldTestEntity byteAnyRequiredTom(byte[] byteAnyRequiredTom) {
        this.byteAnyRequiredTom = byteAnyRequiredTom;
        return this;
    }

    public void setByteAnyRequiredTom(byte[] byteAnyRequiredTom) {
        this.byteAnyRequiredTom = byteAnyRequiredTom;
    }

    public String getByteAnyRequiredTomContentType() {
        return byteAnyRequiredTomContentType;
    }

    public FieldTestEntity byteAnyRequiredTomContentType(String byteAnyRequiredTomContentType) {
        this.byteAnyRequiredTomContentType = byteAnyRequiredTomContentType;
        return this;
    }

    public void setByteAnyRequiredTomContentType(String byteAnyRequiredTomContentType) {
        this.byteAnyRequiredTomContentType = byteAnyRequiredTomContentType;
    }

    public byte[] getByteAnyMinbytesTom() {
        return byteAnyMinbytesTom;
    }

    public FieldTestEntity byteAnyMinbytesTom(byte[] byteAnyMinbytesTom) {
        this.byteAnyMinbytesTom = byteAnyMinbytesTom;
        return this;
    }

    public void setByteAnyMinbytesTom(byte[] byteAnyMinbytesTom) {
        this.byteAnyMinbytesTom = byteAnyMinbytesTom;
    }

    public String getByteAnyMinbytesTomContentType() {
        return byteAnyMinbytesTomContentType;
    }

    public FieldTestEntity byteAnyMinbytesTomContentType(String byteAnyMinbytesTomContentType) {
        this.byteAnyMinbytesTomContentType = byteAnyMinbytesTomContentType;
        return this;
    }

    public void setByteAnyMinbytesTomContentType(String byteAnyMinbytesTomContentType) {
        this.byteAnyMinbytesTomContentType = byteAnyMinbytesTomContentType;
    }

    public byte[] getByteAnyMaxbytesTom() {
        return byteAnyMaxbytesTom;
    }

    public FieldTestEntity byteAnyMaxbytesTom(byte[] byteAnyMaxbytesTom) {
        this.byteAnyMaxbytesTom = byteAnyMaxbytesTom;
        return this;
    }

    public void setByteAnyMaxbytesTom(byte[] byteAnyMaxbytesTom) {
        this.byteAnyMaxbytesTom = byteAnyMaxbytesTom;
    }

    public String getByteAnyMaxbytesTomContentType() {
        return byteAnyMaxbytesTomContentType;
    }

    public FieldTestEntity byteAnyMaxbytesTomContentType(String byteAnyMaxbytesTomContentType) {
        this.byteAnyMaxbytesTomContentType = byteAnyMaxbytesTomContentType;
        return this;
    }

    public void setByteAnyMaxbytesTomContentType(String byteAnyMaxbytesTomContentType) {
        this.byteAnyMaxbytesTomContentType = byteAnyMaxbytesTomContentType;
    }

    public String getByteTextTom() {
        return byteTextTom;
    }

    public FieldTestEntity byteTextTom(String byteTextTom) {
        this.byteTextTom = byteTextTom;
        return this;
    }

    public void setByteTextTom(String byteTextTom) {
        this.byteTextTom = byteTextTom;
    }

    public String getByteTextRequiredTom() {
        return byteTextRequiredTom;
    }

    public FieldTestEntity byteTextRequiredTom(String byteTextRequiredTom) {
        this.byteTextRequiredTom = byteTextRequiredTom;
        return this;
    }

    public void setByteTextRequiredTom(String byteTextRequiredTom) {
        this.byteTextRequiredTom = byteTextRequiredTom;
    }

    public String getByteTextMinbytesTom() {
        return byteTextMinbytesTom;
    }

    public FieldTestEntity byteTextMinbytesTom(String byteTextMinbytesTom) {
        this.byteTextMinbytesTom = byteTextMinbytesTom;
        return this;
    }

    public void setByteTextMinbytesTom(String byteTextMinbytesTom) {
        this.byteTextMinbytesTom = byteTextMinbytesTom;
    }

    public String getByteTextMaxbytesTom() {
        return byteTextMaxbytesTom;
    }

    public FieldTestEntity byteTextMaxbytesTom(String byteTextMaxbytesTom) {
        this.byteTextMaxbytesTom = byteTextMaxbytesTom;
        return this;
    }

    public void setByteTextMaxbytesTom(String byteTextMaxbytesTom) {
        this.byteTextMaxbytesTom = byteTextMaxbytesTom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FieldTestEntity fieldTestEntity = (FieldTestEntity) o;
        if (fieldTestEntity.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), fieldTestEntity.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "FieldTestEntity{" +
            "id=" + getId() +
            ", stringTom='" + getStringTom() + "'" +
            ", stringRequiredTom='" + getStringRequiredTom() + "'" +
            ", stringMinlengthTom='" + getStringMinlengthTom() + "'" +
            ", stringMaxlengthTom='" + getStringMaxlengthTom() + "'" +
            ", stringPatternTom='" + getStringPatternTom() + "'" +
            ", integerTom='" + getIntegerTom() + "'" +
            ", integerRequiredTom='" + getIntegerRequiredTom() + "'" +
            ", integerMinTom='" + getIntegerMinTom() + "'" +
            ", integerMaxTom='" + getIntegerMaxTom() + "'" +
            ", longTom='" + getLongTom() + "'" +
            ", longRequiredTom='" + getLongRequiredTom() + "'" +
            ", longMinTom='" + getLongMinTom() + "'" +
            ", longMaxTom='" + getLongMaxTom() + "'" +
            ", floatTom='" + getFloatTom() + "'" +
            ", floatRequiredTom='" + getFloatRequiredTom() + "'" +
            ", floatMinTom='" + getFloatMinTom() + "'" +
            ", floatMaxTom='" + getFloatMaxTom() + "'" +
            ", doubleRequiredTom='" + getDoubleRequiredTom() + "'" +
            ", doubleMinTom='" + getDoubleMinTom() + "'" +
            ", doubleMaxTom='" + getDoubleMaxTom() + "'" +
            ", bigDecimalRequiredTom='" + getBigDecimalRequiredTom() + "'" +
            ", bigDecimalMinTom='" + getBigDecimalMinTom() + "'" +
            ", bigDecimalMaxTom='" + getBigDecimalMaxTom() + "'" +
            ", localDateTom='" + getLocalDateTom() + "'" +
            ", localDateRequiredTom='" + getLocalDateRequiredTom() + "'" +
            ", instantTom='" + getInstantTom() + "'" +
            ", instanteRequiredTom='" + getInstanteRequiredTom() + "'" +
            ", zonedDateTimeTom='" + getZonedDateTimeTom() + "'" +
            ", zonedDateTimeRequiredTom='" + getZonedDateTimeRequiredTom() + "'" +
            ", booleanTom='" + isBooleanTom() + "'" +
            ", booleanRequiredTom='" + isBooleanRequiredTom() + "'" +
            ", enumTom='" + getEnumTom() + "'" +
            ", enumRequiredTom='" + getEnumRequiredTom() + "'" +
            ", byteImageTom='" + getByteImageTom() + "'" +
            ", byteImageTomContentType='" + byteImageTomContentType + "'" +
            ", byteImageRequiredTom='" + getByteImageRequiredTom() + "'" +
            ", byteImageRequiredTomContentType='" + byteImageRequiredTomContentType + "'" +
            ", byteImageMinbytesTom='" + getByteImageMinbytesTom() + "'" +
            ", byteImageMinbytesTomContentType='" + byteImageMinbytesTomContentType + "'" +
            ", byteImageMaxbytesTom='" + getByteImageMaxbytesTom() + "'" +
            ", byteImageMaxbytesTomContentType='" + byteImageMaxbytesTomContentType + "'" +
            ", byteAnyTom='" + getByteAnyTom() + "'" +
            ", byteAnyTomContentType='" + byteAnyTomContentType + "'" +
            ", byteAnyRequiredTom='" + getByteAnyRequiredTom() + "'" +
            ", byteAnyRequiredTomContentType='" + byteAnyRequiredTomContentType + "'" +
            ", byteAnyMinbytesTom='" + getByteAnyMinbytesTom() + "'" +
            ", byteAnyMinbytesTomContentType='" + byteAnyMinbytesTomContentType + "'" +
            ", byteAnyMaxbytesTom='" + getByteAnyMaxbytesTom() + "'" +
            ", byteAnyMaxbytesTomContentType='" + byteAnyMaxbytesTomContentType + "'" +
            ", byteTextTom='" + getByteTextTom() + "'" +
            ", byteTextRequiredTom='" + getByteTextRequiredTom() + "'" +
            ", byteTextMinbytesTom='" + getByteTextMinbytesTom() + "'" +
            ", byteTextMaxbytesTom='" + getByteTextMaxbytesTom() + "'" +
            "}";
    }
}
