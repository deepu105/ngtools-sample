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
 * A FieldTestInfiniteScrollEntity.
 */
@Entity
@Table(name = "field_test_infinite_scroll_entity")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "fieldtestinfinitescrollentity")
public class FieldTestInfiniteScrollEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "string_hugo")
    private String stringHugo;

    @NotNull
    @Column(name = "string_required_hugo", nullable = false)
    private String stringRequiredHugo;

    @Size(min = 0)
    @Column(name = "string_minlength_hugo")
    private String stringMinlengthHugo;

    @Size(max = 20)
    @Column(name = "string_maxlength_hugo", length = 20)
    private String stringMaxlengthHugo;

    @Pattern(regexp = "^[a-zA-Z0-9]*$")
    @Column(name = "string_pattern_hugo")
    private String stringPatternHugo;

    @Column(name = "integer_hugo")
    private Integer integerHugo;

    @NotNull
    @Column(name = "integer_required_hugo", nullable = false)
    private Integer integerRequiredHugo;

    @Min(value = 0)
    @Column(name = "integer_min_hugo")
    private Integer integerMinHugo;

    @Max(value = 100)
    @Column(name = "integer_max_hugo")
    private Integer integerMaxHugo;

    @Column(name = "long_hugo")
    private Long longHugo;

    @NotNull
    @Column(name = "long_required_hugo", nullable = false)
    private Long longRequiredHugo;

    @Min(value = 0)
    @Column(name = "long_min_hugo")
    private Long longMinHugo;

    @Max(value = 100)
    @Column(name = "long_max_hugo")
    private Long longMaxHugo;

    @Column(name = "float_hugo")
    private Float floatHugo;

    @NotNull
    @Column(name = "float_required_hugo", nullable = false)
    private Float floatRequiredHugo;

    @DecimalMin(value = "0")
    @Column(name = "float_min_hugo")
    private Float floatMinHugo;

    @DecimalMax(value = "100")
    @Column(name = "float_max_hugo")
    private Float floatMaxHugo;

    @NotNull
    @Column(name = "double_required_hugo", nullable = false)
    private Double doubleRequiredHugo;

    @DecimalMin(value = "0")
    @Column(name = "double_min_hugo")
    private Double doubleMinHugo;

    @DecimalMax(value = "100")
    @Column(name = "double_max_hugo")
    private Double doubleMaxHugo;

    @NotNull
    @Column(name = "big_decimal_required_hugo", precision=10, scale=2, nullable = false)
    private BigDecimal bigDecimalRequiredHugo;

    @DecimalMin(value = "0")
    @Column(name = "big_decimal_min_hugo", precision=10, scale=2)
    private BigDecimal bigDecimalMinHugo;

    @DecimalMax(value = "100")
    @Column(name = "big_decimal_max_hugo", precision=10, scale=2)
    private BigDecimal bigDecimalMaxHugo;

    @Column(name = "local_date_hugo")
    private LocalDate localDateHugo;

    @NotNull
    @Column(name = "local_date_required_hugo", nullable = false)
    private LocalDate localDateRequiredHugo;

    @Column(name = "instant_hugo")
    private Instant instantHugo;

    @NotNull
    @Column(name = "instante_required_hugo", nullable = false)
    private Instant instanteRequiredHugo;

    @Column(name = "zoned_date_time_hugo")
    private ZonedDateTime zonedDateTimeHugo;

    @NotNull
    @Column(name = "zoned_date_time_required_hugo", nullable = false)
    private ZonedDateTime zonedDateTimeRequiredHugo;

    @Column(name = "boolean_hugo")
    private Boolean booleanHugo;

    @NotNull
    @Column(name = "boolean_required_hugo", nullable = false)
    private Boolean booleanRequiredHugo;

    @Enumerated(EnumType.STRING)
    @Column(name = "enum_hugo")
    private EnumFieldClass enumHugo;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "enum_required_hugo", nullable = false)
    private EnumRequiredFieldClass enumRequiredHugo;

    @Lob
    @Column(name = "byte_image_hugo")
    private byte[] byteImageHugo;

    @Column(name = "byte_image_hugo_content_type")
    private String byteImageHugoContentType;

    @NotNull
    @Lob
    @Column(name = "byte_image_required_hugo", nullable = false)
    private byte[] byteImageRequiredHugo;

    @Column(name = "byte_image_required_hugo_content_type", nullable = false)
    private String byteImageRequiredHugoContentType;

    @Size(min = 0)
    @Lob
    @Column(name = "byte_image_minbytes_hugo")
    private byte[] byteImageMinbytesHugo;

    @Column(name = "byte_image_minbytes_hugo_content_type")
    private String byteImageMinbytesHugoContentType;

    @Size(max = 10000)
    @Lob
    @Column(name = "byte_image_maxbytes_hugo")
    private byte[] byteImageMaxbytesHugo;

    @Column(name = "byte_image_maxbytes_hugo_content_type")
    private String byteImageMaxbytesHugoContentType;

    @Lob
    @Column(name = "byte_any_hugo")
    private byte[] byteAnyHugo;

    @Column(name = "byte_any_hugo_content_type")
    private String byteAnyHugoContentType;

    @NotNull
    @Lob
    @Column(name = "byte_any_required_hugo", nullable = false)
    private byte[] byteAnyRequiredHugo;

    @Column(name = "byte_any_required_hugo_content_type", nullable = false)
    private String byteAnyRequiredHugoContentType;

    @Size(min = 0)
    @Lob
    @Column(name = "byte_any_minbytes_hugo")
    private byte[] byteAnyMinbytesHugo;

    @Column(name = "byte_any_minbytes_hugo_content_type")
    private String byteAnyMinbytesHugoContentType;

    @Size(max = 10000)
    @Lob
    @Column(name = "byte_any_maxbytes_hugo")
    private byte[] byteAnyMaxbytesHugo;

    @Column(name = "byte_any_maxbytes_hugo_content_type")
    private String byteAnyMaxbytesHugoContentType;

    @Lob
    @Column(name = "byte_text_hugo")
    private String byteTextHugo;

    @NotNull
    @Lob
    @Column(name = "byte_text_required_hugo", nullable = false)
    private String byteTextRequiredHugo;

    @Size(min = 0)
    @Lob
    @Column(name = "byte_text_minbytes_hugo")
    private String byteTextMinbytesHugo;

    @Size(max = 10000)
    @Lob
    @Column(name = "byte_text_maxbytes_hugo")
    private String byteTextMaxbytesHugo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStringHugo() {
        return stringHugo;
    }

    public FieldTestInfiniteScrollEntity stringHugo(String stringHugo) {
        this.stringHugo = stringHugo;
        return this;
    }

    public void setStringHugo(String stringHugo) {
        this.stringHugo = stringHugo;
    }

    public String getStringRequiredHugo() {
        return stringRequiredHugo;
    }

    public FieldTestInfiniteScrollEntity stringRequiredHugo(String stringRequiredHugo) {
        this.stringRequiredHugo = stringRequiredHugo;
        return this;
    }

    public void setStringRequiredHugo(String stringRequiredHugo) {
        this.stringRequiredHugo = stringRequiredHugo;
    }

    public String getStringMinlengthHugo() {
        return stringMinlengthHugo;
    }

    public FieldTestInfiniteScrollEntity stringMinlengthHugo(String stringMinlengthHugo) {
        this.stringMinlengthHugo = stringMinlengthHugo;
        return this;
    }

    public void setStringMinlengthHugo(String stringMinlengthHugo) {
        this.stringMinlengthHugo = stringMinlengthHugo;
    }

    public String getStringMaxlengthHugo() {
        return stringMaxlengthHugo;
    }

    public FieldTestInfiniteScrollEntity stringMaxlengthHugo(String stringMaxlengthHugo) {
        this.stringMaxlengthHugo = stringMaxlengthHugo;
        return this;
    }

    public void setStringMaxlengthHugo(String stringMaxlengthHugo) {
        this.stringMaxlengthHugo = stringMaxlengthHugo;
    }

    public String getStringPatternHugo() {
        return stringPatternHugo;
    }

    public FieldTestInfiniteScrollEntity stringPatternHugo(String stringPatternHugo) {
        this.stringPatternHugo = stringPatternHugo;
        return this;
    }

    public void setStringPatternHugo(String stringPatternHugo) {
        this.stringPatternHugo = stringPatternHugo;
    }

    public Integer getIntegerHugo() {
        return integerHugo;
    }

    public FieldTestInfiniteScrollEntity integerHugo(Integer integerHugo) {
        this.integerHugo = integerHugo;
        return this;
    }

    public void setIntegerHugo(Integer integerHugo) {
        this.integerHugo = integerHugo;
    }

    public Integer getIntegerRequiredHugo() {
        return integerRequiredHugo;
    }

    public FieldTestInfiniteScrollEntity integerRequiredHugo(Integer integerRequiredHugo) {
        this.integerRequiredHugo = integerRequiredHugo;
        return this;
    }

    public void setIntegerRequiredHugo(Integer integerRequiredHugo) {
        this.integerRequiredHugo = integerRequiredHugo;
    }

    public Integer getIntegerMinHugo() {
        return integerMinHugo;
    }

    public FieldTestInfiniteScrollEntity integerMinHugo(Integer integerMinHugo) {
        this.integerMinHugo = integerMinHugo;
        return this;
    }

    public void setIntegerMinHugo(Integer integerMinHugo) {
        this.integerMinHugo = integerMinHugo;
    }

    public Integer getIntegerMaxHugo() {
        return integerMaxHugo;
    }

    public FieldTestInfiniteScrollEntity integerMaxHugo(Integer integerMaxHugo) {
        this.integerMaxHugo = integerMaxHugo;
        return this;
    }

    public void setIntegerMaxHugo(Integer integerMaxHugo) {
        this.integerMaxHugo = integerMaxHugo;
    }

    public Long getLongHugo() {
        return longHugo;
    }

    public FieldTestInfiniteScrollEntity longHugo(Long longHugo) {
        this.longHugo = longHugo;
        return this;
    }

    public void setLongHugo(Long longHugo) {
        this.longHugo = longHugo;
    }

    public Long getLongRequiredHugo() {
        return longRequiredHugo;
    }

    public FieldTestInfiniteScrollEntity longRequiredHugo(Long longRequiredHugo) {
        this.longRequiredHugo = longRequiredHugo;
        return this;
    }

    public void setLongRequiredHugo(Long longRequiredHugo) {
        this.longRequiredHugo = longRequiredHugo;
    }

    public Long getLongMinHugo() {
        return longMinHugo;
    }

    public FieldTestInfiniteScrollEntity longMinHugo(Long longMinHugo) {
        this.longMinHugo = longMinHugo;
        return this;
    }

    public void setLongMinHugo(Long longMinHugo) {
        this.longMinHugo = longMinHugo;
    }

    public Long getLongMaxHugo() {
        return longMaxHugo;
    }

    public FieldTestInfiniteScrollEntity longMaxHugo(Long longMaxHugo) {
        this.longMaxHugo = longMaxHugo;
        return this;
    }

    public void setLongMaxHugo(Long longMaxHugo) {
        this.longMaxHugo = longMaxHugo;
    }

    public Float getFloatHugo() {
        return floatHugo;
    }

    public FieldTestInfiniteScrollEntity floatHugo(Float floatHugo) {
        this.floatHugo = floatHugo;
        return this;
    }

    public void setFloatHugo(Float floatHugo) {
        this.floatHugo = floatHugo;
    }

    public Float getFloatRequiredHugo() {
        return floatRequiredHugo;
    }

    public FieldTestInfiniteScrollEntity floatRequiredHugo(Float floatRequiredHugo) {
        this.floatRequiredHugo = floatRequiredHugo;
        return this;
    }

    public void setFloatRequiredHugo(Float floatRequiredHugo) {
        this.floatRequiredHugo = floatRequiredHugo;
    }

    public Float getFloatMinHugo() {
        return floatMinHugo;
    }

    public FieldTestInfiniteScrollEntity floatMinHugo(Float floatMinHugo) {
        this.floatMinHugo = floatMinHugo;
        return this;
    }

    public void setFloatMinHugo(Float floatMinHugo) {
        this.floatMinHugo = floatMinHugo;
    }

    public Float getFloatMaxHugo() {
        return floatMaxHugo;
    }

    public FieldTestInfiniteScrollEntity floatMaxHugo(Float floatMaxHugo) {
        this.floatMaxHugo = floatMaxHugo;
        return this;
    }

    public void setFloatMaxHugo(Float floatMaxHugo) {
        this.floatMaxHugo = floatMaxHugo;
    }

    public Double getDoubleRequiredHugo() {
        return doubleRequiredHugo;
    }

    public FieldTestInfiniteScrollEntity doubleRequiredHugo(Double doubleRequiredHugo) {
        this.doubleRequiredHugo = doubleRequiredHugo;
        return this;
    }

    public void setDoubleRequiredHugo(Double doubleRequiredHugo) {
        this.doubleRequiredHugo = doubleRequiredHugo;
    }

    public Double getDoubleMinHugo() {
        return doubleMinHugo;
    }

    public FieldTestInfiniteScrollEntity doubleMinHugo(Double doubleMinHugo) {
        this.doubleMinHugo = doubleMinHugo;
        return this;
    }

    public void setDoubleMinHugo(Double doubleMinHugo) {
        this.doubleMinHugo = doubleMinHugo;
    }

    public Double getDoubleMaxHugo() {
        return doubleMaxHugo;
    }

    public FieldTestInfiniteScrollEntity doubleMaxHugo(Double doubleMaxHugo) {
        this.doubleMaxHugo = doubleMaxHugo;
        return this;
    }

    public void setDoubleMaxHugo(Double doubleMaxHugo) {
        this.doubleMaxHugo = doubleMaxHugo;
    }

    public BigDecimal getBigDecimalRequiredHugo() {
        return bigDecimalRequiredHugo;
    }

    public FieldTestInfiniteScrollEntity bigDecimalRequiredHugo(BigDecimal bigDecimalRequiredHugo) {
        this.bigDecimalRequiredHugo = bigDecimalRequiredHugo;
        return this;
    }

    public void setBigDecimalRequiredHugo(BigDecimal bigDecimalRequiredHugo) {
        this.bigDecimalRequiredHugo = bigDecimalRequiredHugo;
    }

    public BigDecimal getBigDecimalMinHugo() {
        return bigDecimalMinHugo;
    }

    public FieldTestInfiniteScrollEntity bigDecimalMinHugo(BigDecimal bigDecimalMinHugo) {
        this.bigDecimalMinHugo = bigDecimalMinHugo;
        return this;
    }

    public void setBigDecimalMinHugo(BigDecimal bigDecimalMinHugo) {
        this.bigDecimalMinHugo = bigDecimalMinHugo;
    }

    public BigDecimal getBigDecimalMaxHugo() {
        return bigDecimalMaxHugo;
    }

    public FieldTestInfiniteScrollEntity bigDecimalMaxHugo(BigDecimal bigDecimalMaxHugo) {
        this.bigDecimalMaxHugo = bigDecimalMaxHugo;
        return this;
    }

    public void setBigDecimalMaxHugo(BigDecimal bigDecimalMaxHugo) {
        this.bigDecimalMaxHugo = bigDecimalMaxHugo;
    }

    public LocalDate getLocalDateHugo() {
        return localDateHugo;
    }

    public FieldTestInfiniteScrollEntity localDateHugo(LocalDate localDateHugo) {
        this.localDateHugo = localDateHugo;
        return this;
    }

    public void setLocalDateHugo(LocalDate localDateHugo) {
        this.localDateHugo = localDateHugo;
    }

    public LocalDate getLocalDateRequiredHugo() {
        return localDateRequiredHugo;
    }

    public FieldTestInfiniteScrollEntity localDateRequiredHugo(LocalDate localDateRequiredHugo) {
        this.localDateRequiredHugo = localDateRequiredHugo;
        return this;
    }

    public void setLocalDateRequiredHugo(LocalDate localDateRequiredHugo) {
        this.localDateRequiredHugo = localDateRequiredHugo;
    }

    public Instant getInstantHugo() {
        return instantHugo;
    }

    public FieldTestInfiniteScrollEntity instantHugo(Instant instantHugo) {
        this.instantHugo = instantHugo;
        return this;
    }

    public void setInstantHugo(Instant instantHugo) {
        this.instantHugo = instantHugo;
    }

    public Instant getInstanteRequiredHugo() {
        return instanteRequiredHugo;
    }

    public FieldTestInfiniteScrollEntity instanteRequiredHugo(Instant instanteRequiredHugo) {
        this.instanteRequiredHugo = instanteRequiredHugo;
        return this;
    }

    public void setInstanteRequiredHugo(Instant instanteRequiredHugo) {
        this.instanteRequiredHugo = instanteRequiredHugo;
    }

    public ZonedDateTime getZonedDateTimeHugo() {
        return zonedDateTimeHugo;
    }

    public FieldTestInfiniteScrollEntity zonedDateTimeHugo(ZonedDateTime zonedDateTimeHugo) {
        this.zonedDateTimeHugo = zonedDateTimeHugo;
        return this;
    }

    public void setZonedDateTimeHugo(ZonedDateTime zonedDateTimeHugo) {
        this.zonedDateTimeHugo = zonedDateTimeHugo;
    }

    public ZonedDateTime getZonedDateTimeRequiredHugo() {
        return zonedDateTimeRequiredHugo;
    }

    public FieldTestInfiniteScrollEntity zonedDateTimeRequiredHugo(ZonedDateTime zonedDateTimeRequiredHugo) {
        this.zonedDateTimeRequiredHugo = zonedDateTimeRequiredHugo;
        return this;
    }

    public void setZonedDateTimeRequiredHugo(ZonedDateTime zonedDateTimeRequiredHugo) {
        this.zonedDateTimeRequiredHugo = zonedDateTimeRequiredHugo;
    }

    public Boolean isBooleanHugo() {
        return booleanHugo;
    }

    public FieldTestInfiniteScrollEntity booleanHugo(Boolean booleanHugo) {
        this.booleanHugo = booleanHugo;
        return this;
    }

    public void setBooleanHugo(Boolean booleanHugo) {
        this.booleanHugo = booleanHugo;
    }

    public Boolean isBooleanRequiredHugo() {
        return booleanRequiredHugo;
    }

    public FieldTestInfiniteScrollEntity booleanRequiredHugo(Boolean booleanRequiredHugo) {
        this.booleanRequiredHugo = booleanRequiredHugo;
        return this;
    }

    public void setBooleanRequiredHugo(Boolean booleanRequiredHugo) {
        this.booleanRequiredHugo = booleanRequiredHugo;
    }

    public EnumFieldClass getEnumHugo() {
        return enumHugo;
    }

    public FieldTestInfiniteScrollEntity enumHugo(EnumFieldClass enumHugo) {
        this.enumHugo = enumHugo;
        return this;
    }

    public void setEnumHugo(EnumFieldClass enumHugo) {
        this.enumHugo = enumHugo;
    }

    public EnumRequiredFieldClass getEnumRequiredHugo() {
        return enumRequiredHugo;
    }

    public FieldTestInfiniteScrollEntity enumRequiredHugo(EnumRequiredFieldClass enumRequiredHugo) {
        this.enumRequiredHugo = enumRequiredHugo;
        return this;
    }

    public void setEnumRequiredHugo(EnumRequiredFieldClass enumRequiredHugo) {
        this.enumRequiredHugo = enumRequiredHugo;
    }

    public byte[] getByteImageHugo() {
        return byteImageHugo;
    }

    public FieldTestInfiniteScrollEntity byteImageHugo(byte[] byteImageHugo) {
        this.byteImageHugo = byteImageHugo;
        return this;
    }

    public void setByteImageHugo(byte[] byteImageHugo) {
        this.byteImageHugo = byteImageHugo;
    }

    public String getByteImageHugoContentType() {
        return byteImageHugoContentType;
    }

    public FieldTestInfiniteScrollEntity byteImageHugoContentType(String byteImageHugoContentType) {
        this.byteImageHugoContentType = byteImageHugoContentType;
        return this;
    }

    public void setByteImageHugoContentType(String byteImageHugoContentType) {
        this.byteImageHugoContentType = byteImageHugoContentType;
    }

    public byte[] getByteImageRequiredHugo() {
        return byteImageRequiredHugo;
    }

    public FieldTestInfiniteScrollEntity byteImageRequiredHugo(byte[] byteImageRequiredHugo) {
        this.byteImageRequiredHugo = byteImageRequiredHugo;
        return this;
    }

    public void setByteImageRequiredHugo(byte[] byteImageRequiredHugo) {
        this.byteImageRequiredHugo = byteImageRequiredHugo;
    }

    public String getByteImageRequiredHugoContentType() {
        return byteImageRequiredHugoContentType;
    }

    public FieldTestInfiniteScrollEntity byteImageRequiredHugoContentType(String byteImageRequiredHugoContentType) {
        this.byteImageRequiredHugoContentType = byteImageRequiredHugoContentType;
        return this;
    }

    public void setByteImageRequiredHugoContentType(String byteImageRequiredHugoContentType) {
        this.byteImageRequiredHugoContentType = byteImageRequiredHugoContentType;
    }

    public byte[] getByteImageMinbytesHugo() {
        return byteImageMinbytesHugo;
    }

    public FieldTestInfiniteScrollEntity byteImageMinbytesHugo(byte[] byteImageMinbytesHugo) {
        this.byteImageMinbytesHugo = byteImageMinbytesHugo;
        return this;
    }

    public void setByteImageMinbytesHugo(byte[] byteImageMinbytesHugo) {
        this.byteImageMinbytesHugo = byteImageMinbytesHugo;
    }

    public String getByteImageMinbytesHugoContentType() {
        return byteImageMinbytesHugoContentType;
    }

    public FieldTestInfiniteScrollEntity byteImageMinbytesHugoContentType(String byteImageMinbytesHugoContentType) {
        this.byteImageMinbytesHugoContentType = byteImageMinbytesHugoContentType;
        return this;
    }

    public void setByteImageMinbytesHugoContentType(String byteImageMinbytesHugoContentType) {
        this.byteImageMinbytesHugoContentType = byteImageMinbytesHugoContentType;
    }

    public byte[] getByteImageMaxbytesHugo() {
        return byteImageMaxbytesHugo;
    }

    public FieldTestInfiniteScrollEntity byteImageMaxbytesHugo(byte[] byteImageMaxbytesHugo) {
        this.byteImageMaxbytesHugo = byteImageMaxbytesHugo;
        return this;
    }

    public void setByteImageMaxbytesHugo(byte[] byteImageMaxbytesHugo) {
        this.byteImageMaxbytesHugo = byteImageMaxbytesHugo;
    }

    public String getByteImageMaxbytesHugoContentType() {
        return byteImageMaxbytesHugoContentType;
    }

    public FieldTestInfiniteScrollEntity byteImageMaxbytesHugoContentType(String byteImageMaxbytesHugoContentType) {
        this.byteImageMaxbytesHugoContentType = byteImageMaxbytesHugoContentType;
        return this;
    }

    public void setByteImageMaxbytesHugoContentType(String byteImageMaxbytesHugoContentType) {
        this.byteImageMaxbytesHugoContentType = byteImageMaxbytesHugoContentType;
    }

    public byte[] getByteAnyHugo() {
        return byteAnyHugo;
    }

    public FieldTestInfiniteScrollEntity byteAnyHugo(byte[] byteAnyHugo) {
        this.byteAnyHugo = byteAnyHugo;
        return this;
    }

    public void setByteAnyHugo(byte[] byteAnyHugo) {
        this.byteAnyHugo = byteAnyHugo;
    }

    public String getByteAnyHugoContentType() {
        return byteAnyHugoContentType;
    }

    public FieldTestInfiniteScrollEntity byteAnyHugoContentType(String byteAnyHugoContentType) {
        this.byteAnyHugoContentType = byteAnyHugoContentType;
        return this;
    }

    public void setByteAnyHugoContentType(String byteAnyHugoContentType) {
        this.byteAnyHugoContentType = byteAnyHugoContentType;
    }

    public byte[] getByteAnyRequiredHugo() {
        return byteAnyRequiredHugo;
    }

    public FieldTestInfiniteScrollEntity byteAnyRequiredHugo(byte[] byteAnyRequiredHugo) {
        this.byteAnyRequiredHugo = byteAnyRequiredHugo;
        return this;
    }

    public void setByteAnyRequiredHugo(byte[] byteAnyRequiredHugo) {
        this.byteAnyRequiredHugo = byteAnyRequiredHugo;
    }

    public String getByteAnyRequiredHugoContentType() {
        return byteAnyRequiredHugoContentType;
    }

    public FieldTestInfiniteScrollEntity byteAnyRequiredHugoContentType(String byteAnyRequiredHugoContentType) {
        this.byteAnyRequiredHugoContentType = byteAnyRequiredHugoContentType;
        return this;
    }

    public void setByteAnyRequiredHugoContentType(String byteAnyRequiredHugoContentType) {
        this.byteAnyRequiredHugoContentType = byteAnyRequiredHugoContentType;
    }

    public byte[] getByteAnyMinbytesHugo() {
        return byteAnyMinbytesHugo;
    }

    public FieldTestInfiniteScrollEntity byteAnyMinbytesHugo(byte[] byteAnyMinbytesHugo) {
        this.byteAnyMinbytesHugo = byteAnyMinbytesHugo;
        return this;
    }

    public void setByteAnyMinbytesHugo(byte[] byteAnyMinbytesHugo) {
        this.byteAnyMinbytesHugo = byteAnyMinbytesHugo;
    }

    public String getByteAnyMinbytesHugoContentType() {
        return byteAnyMinbytesHugoContentType;
    }

    public FieldTestInfiniteScrollEntity byteAnyMinbytesHugoContentType(String byteAnyMinbytesHugoContentType) {
        this.byteAnyMinbytesHugoContentType = byteAnyMinbytesHugoContentType;
        return this;
    }

    public void setByteAnyMinbytesHugoContentType(String byteAnyMinbytesHugoContentType) {
        this.byteAnyMinbytesHugoContentType = byteAnyMinbytesHugoContentType;
    }

    public byte[] getByteAnyMaxbytesHugo() {
        return byteAnyMaxbytesHugo;
    }

    public FieldTestInfiniteScrollEntity byteAnyMaxbytesHugo(byte[] byteAnyMaxbytesHugo) {
        this.byteAnyMaxbytesHugo = byteAnyMaxbytesHugo;
        return this;
    }

    public void setByteAnyMaxbytesHugo(byte[] byteAnyMaxbytesHugo) {
        this.byteAnyMaxbytesHugo = byteAnyMaxbytesHugo;
    }

    public String getByteAnyMaxbytesHugoContentType() {
        return byteAnyMaxbytesHugoContentType;
    }

    public FieldTestInfiniteScrollEntity byteAnyMaxbytesHugoContentType(String byteAnyMaxbytesHugoContentType) {
        this.byteAnyMaxbytesHugoContentType = byteAnyMaxbytesHugoContentType;
        return this;
    }

    public void setByteAnyMaxbytesHugoContentType(String byteAnyMaxbytesHugoContentType) {
        this.byteAnyMaxbytesHugoContentType = byteAnyMaxbytesHugoContentType;
    }

    public String getByteTextHugo() {
        return byteTextHugo;
    }

    public FieldTestInfiniteScrollEntity byteTextHugo(String byteTextHugo) {
        this.byteTextHugo = byteTextHugo;
        return this;
    }

    public void setByteTextHugo(String byteTextHugo) {
        this.byteTextHugo = byteTextHugo;
    }

    public String getByteTextRequiredHugo() {
        return byteTextRequiredHugo;
    }

    public FieldTestInfiniteScrollEntity byteTextRequiredHugo(String byteTextRequiredHugo) {
        this.byteTextRequiredHugo = byteTextRequiredHugo;
        return this;
    }

    public void setByteTextRequiredHugo(String byteTextRequiredHugo) {
        this.byteTextRequiredHugo = byteTextRequiredHugo;
    }

    public String getByteTextMinbytesHugo() {
        return byteTextMinbytesHugo;
    }

    public FieldTestInfiniteScrollEntity byteTextMinbytesHugo(String byteTextMinbytesHugo) {
        this.byteTextMinbytesHugo = byteTextMinbytesHugo;
        return this;
    }

    public void setByteTextMinbytesHugo(String byteTextMinbytesHugo) {
        this.byteTextMinbytesHugo = byteTextMinbytesHugo;
    }

    public String getByteTextMaxbytesHugo() {
        return byteTextMaxbytesHugo;
    }

    public FieldTestInfiniteScrollEntity byteTextMaxbytesHugo(String byteTextMaxbytesHugo) {
        this.byteTextMaxbytesHugo = byteTextMaxbytesHugo;
        return this;
    }

    public void setByteTextMaxbytesHugo(String byteTextMaxbytesHugo) {
        this.byteTextMaxbytesHugo = byteTextMaxbytesHugo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FieldTestInfiniteScrollEntity fieldTestInfiniteScrollEntity = (FieldTestInfiniteScrollEntity) o;
        if (fieldTestInfiniteScrollEntity.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), fieldTestInfiniteScrollEntity.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "FieldTestInfiniteScrollEntity{" +
            "id=" + getId() +
            ", stringHugo='" + getStringHugo() + "'" +
            ", stringRequiredHugo='" + getStringRequiredHugo() + "'" +
            ", stringMinlengthHugo='" + getStringMinlengthHugo() + "'" +
            ", stringMaxlengthHugo='" + getStringMaxlengthHugo() + "'" +
            ", stringPatternHugo='" + getStringPatternHugo() + "'" +
            ", integerHugo='" + getIntegerHugo() + "'" +
            ", integerRequiredHugo='" + getIntegerRequiredHugo() + "'" +
            ", integerMinHugo='" + getIntegerMinHugo() + "'" +
            ", integerMaxHugo='" + getIntegerMaxHugo() + "'" +
            ", longHugo='" + getLongHugo() + "'" +
            ", longRequiredHugo='" + getLongRequiredHugo() + "'" +
            ", longMinHugo='" + getLongMinHugo() + "'" +
            ", longMaxHugo='" + getLongMaxHugo() + "'" +
            ", floatHugo='" + getFloatHugo() + "'" +
            ", floatRequiredHugo='" + getFloatRequiredHugo() + "'" +
            ", floatMinHugo='" + getFloatMinHugo() + "'" +
            ", floatMaxHugo='" + getFloatMaxHugo() + "'" +
            ", doubleRequiredHugo='" + getDoubleRequiredHugo() + "'" +
            ", doubleMinHugo='" + getDoubleMinHugo() + "'" +
            ", doubleMaxHugo='" + getDoubleMaxHugo() + "'" +
            ", bigDecimalRequiredHugo='" + getBigDecimalRequiredHugo() + "'" +
            ", bigDecimalMinHugo='" + getBigDecimalMinHugo() + "'" +
            ", bigDecimalMaxHugo='" + getBigDecimalMaxHugo() + "'" +
            ", localDateHugo='" + getLocalDateHugo() + "'" +
            ", localDateRequiredHugo='" + getLocalDateRequiredHugo() + "'" +
            ", instantHugo='" + getInstantHugo() + "'" +
            ", instanteRequiredHugo='" + getInstanteRequiredHugo() + "'" +
            ", zonedDateTimeHugo='" + getZonedDateTimeHugo() + "'" +
            ", zonedDateTimeRequiredHugo='" + getZonedDateTimeRequiredHugo() + "'" +
            ", booleanHugo='" + isBooleanHugo() + "'" +
            ", booleanRequiredHugo='" + isBooleanRequiredHugo() + "'" +
            ", enumHugo='" + getEnumHugo() + "'" +
            ", enumRequiredHugo='" + getEnumRequiredHugo() + "'" +
            ", byteImageHugo='" + getByteImageHugo() + "'" +
            ", byteImageHugoContentType='" + byteImageHugoContentType + "'" +
            ", byteImageRequiredHugo='" + getByteImageRequiredHugo() + "'" +
            ", byteImageRequiredHugoContentType='" + byteImageRequiredHugoContentType + "'" +
            ", byteImageMinbytesHugo='" + getByteImageMinbytesHugo() + "'" +
            ", byteImageMinbytesHugoContentType='" + byteImageMinbytesHugoContentType + "'" +
            ", byteImageMaxbytesHugo='" + getByteImageMaxbytesHugo() + "'" +
            ", byteImageMaxbytesHugoContentType='" + byteImageMaxbytesHugoContentType + "'" +
            ", byteAnyHugo='" + getByteAnyHugo() + "'" +
            ", byteAnyHugoContentType='" + byteAnyHugoContentType + "'" +
            ", byteAnyRequiredHugo='" + getByteAnyRequiredHugo() + "'" +
            ", byteAnyRequiredHugoContentType='" + byteAnyRequiredHugoContentType + "'" +
            ", byteAnyMinbytesHugo='" + getByteAnyMinbytesHugo() + "'" +
            ", byteAnyMinbytesHugoContentType='" + byteAnyMinbytesHugoContentType + "'" +
            ", byteAnyMaxbytesHugo='" + getByteAnyMaxbytesHugo() + "'" +
            ", byteAnyMaxbytesHugoContentType='" + byteAnyMaxbytesHugoContentType + "'" +
            ", byteTextHugo='" + getByteTextHugo() + "'" +
            ", byteTextRequiredHugo='" + getByteTextRequiredHugo() + "'" +
            ", byteTextMinbytesHugo='" + getByteTextMinbytesHugo() + "'" +
            ", byteTextMaxbytesHugo='" + getByteTextMaxbytesHugo() + "'" +
            "}";
    }
}
