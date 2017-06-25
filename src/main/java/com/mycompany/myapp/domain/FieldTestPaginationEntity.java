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
 * A FieldTestPaginationEntity.
 */
@Entity
@Table(name = "field_test_pagination_entity")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "fieldtestpaginationentity")
public class FieldTestPaginationEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "string_alice")
    private String stringAlice;

    @NotNull
    @Column(name = "string_required_alice", nullable = false)
    private String stringRequiredAlice;

    @Size(min = 0)
    @Column(name = "string_minlength_alice")
    private String stringMinlengthAlice;

    @Size(max = 20)
    @Column(name = "string_maxlength_alice", length = 20)
    private String stringMaxlengthAlice;

    @Pattern(regexp = "^[a-zA-Z0-9]*$")
    @Column(name = "string_pattern_alice")
    private String stringPatternAlice;

    @Column(name = "integer_alice")
    private Integer integerAlice;

    @NotNull
    @Column(name = "integer_required_alice", nullable = false)
    private Integer integerRequiredAlice;

    @Min(value = 0)
    @Column(name = "integer_min_alice")
    private Integer integerMinAlice;

    @Max(value = 100)
    @Column(name = "integer_max_alice")
    private Integer integerMaxAlice;

    @Column(name = "long_alice")
    private Long longAlice;

    @NotNull
    @Column(name = "long_required_alice", nullable = false)
    private Long longRequiredAlice;

    @Min(value = 0)
    @Column(name = "long_min_alice")
    private Long longMinAlice;

    @Max(value = 100)
    @Column(name = "long_max_alice")
    private Long longMaxAlice;

    @Column(name = "float_alice")
    private Float floatAlice;

    @NotNull
    @Column(name = "float_required_alice", nullable = false)
    private Float floatRequiredAlice;

    @DecimalMin(value = "0")
    @Column(name = "float_min_alice")
    private Float floatMinAlice;

    @DecimalMax(value = "100")
    @Column(name = "float_max_alice")
    private Float floatMaxAlice;

    @NotNull
    @Column(name = "double_required_alice", nullable = false)
    private Double doubleRequiredAlice;

    @DecimalMin(value = "0")
    @Column(name = "double_min_alice")
    private Double doubleMinAlice;

    @DecimalMax(value = "100")
    @Column(name = "double_max_alice")
    private Double doubleMaxAlice;

    @NotNull
    @Column(name = "big_decimal_required_alice", precision=10, scale=2, nullable = false)
    private BigDecimal bigDecimalRequiredAlice;

    @DecimalMin(value = "0")
    @Column(name = "big_decimal_min_alice", precision=10, scale=2)
    private BigDecimal bigDecimalMinAlice;

    @DecimalMax(value = "100")
    @Column(name = "big_decimal_max_alice", precision=10, scale=2)
    private BigDecimal bigDecimalMaxAlice;

    @Column(name = "local_date_alice")
    private LocalDate localDateAlice;

    @NotNull
    @Column(name = "local_date_required_alice", nullable = false)
    private LocalDate localDateRequiredAlice;

    @Column(name = "instant_alice")
    private Instant instantAlice;

    @NotNull
    @Column(name = "instante_required_alice", nullable = false)
    private Instant instanteRequiredAlice;

    @Column(name = "zoned_date_time_alice")
    private ZonedDateTime zonedDateTimeAlice;

    @NotNull
    @Column(name = "zoned_date_time_required_alice", nullable = false)
    private ZonedDateTime zonedDateTimeRequiredAlice;

    @Column(name = "boolean_alice")
    private Boolean booleanAlice;

    @NotNull
    @Column(name = "boolean_required_alice", nullable = false)
    private Boolean booleanRequiredAlice;

    @Enumerated(EnumType.STRING)
    @Column(name = "enum_alice")
    private EnumFieldClass enumAlice;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "enum_required_alice", nullable = false)
    private EnumRequiredFieldClass enumRequiredAlice;

    @Lob
    @Column(name = "byte_image_alice")
    private byte[] byteImageAlice;

    @Column(name = "byte_image_alice_content_type")
    private String byteImageAliceContentType;

    @NotNull
    @Lob
    @Column(name = "byte_image_required_alice", nullable = false)
    private byte[] byteImageRequiredAlice;

    @Column(name = "byte_image_required_alice_content_type", nullable = false)
    private String byteImageRequiredAliceContentType;

    @Size(min = 0)
    @Lob
    @Column(name = "byte_image_minbytes_alice")
    private byte[] byteImageMinbytesAlice;

    @Column(name = "byte_image_minbytes_alice_content_type")
    private String byteImageMinbytesAliceContentType;

    @Size(max = 10000)
    @Lob
    @Column(name = "byte_image_maxbytes_alice")
    private byte[] byteImageMaxbytesAlice;

    @Column(name = "byte_image_maxbytes_alice_content_type")
    private String byteImageMaxbytesAliceContentType;

    @Lob
    @Column(name = "byte_any_alice")
    private byte[] byteAnyAlice;

    @Column(name = "byte_any_alice_content_type")
    private String byteAnyAliceContentType;

    @NotNull
    @Lob
    @Column(name = "byte_any_required_alice", nullable = false)
    private byte[] byteAnyRequiredAlice;

    @Column(name = "byte_any_required_alice_content_type", nullable = false)
    private String byteAnyRequiredAliceContentType;

    @Size(min = 0)
    @Lob
    @Column(name = "byte_any_minbytes_alice")
    private byte[] byteAnyMinbytesAlice;

    @Column(name = "byte_any_minbytes_alice_content_type")
    private String byteAnyMinbytesAliceContentType;

    @Size(max = 10000)
    @Lob
    @Column(name = "byte_any_maxbytes_alice")
    private byte[] byteAnyMaxbytesAlice;

    @Column(name = "byte_any_maxbytes_alice_content_type")
    private String byteAnyMaxbytesAliceContentType;

    @Lob
    @Column(name = "byte_text_alice")
    private String byteTextAlice;

    @NotNull
    @Lob
    @Column(name = "byte_text_required_alice", nullable = false)
    private String byteTextRequiredAlice;

    @Size(min = 0)
    @Lob
    @Column(name = "byte_text_minbytes_alice")
    private String byteTextMinbytesAlice;

    @Size(max = 10000)
    @Lob
    @Column(name = "byte_text_maxbytes_alice")
    private String byteTextMaxbytesAlice;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStringAlice() {
        return stringAlice;
    }

    public FieldTestPaginationEntity stringAlice(String stringAlice) {
        this.stringAlice = stringAlice;
        return this;
    }

    public void setStringAlice(String stringAlice) {
        this.stringAlice = stringAlice;
    }

    public String getStringRequiredAlice() {
        return stringRequiredAlice;
    }

    public FieldTestPaginationEntity stringRequiredAlice(String stringRequiredAlice) {
        this.stringRequiredAlice = stringRequiredAlice;
        return this;
    }

    public void setStringRequiredAlice(String stringRequiredAlice) {
        this.stringRequiredAlice = stringRequiredAlice;
    }

    public String getStringMinlengthAlice() {
        return stringMinlengthAlice;
    }

    public FieldTestPaginationEntity stringMinlengthAlice(String stringMinlengthAlice) {
        this.stringMinlengthAlice = stringMinlengthAlice;
        return this;
    }

    public void setStringMinlengthAlice(String stringMinlengthAlice) {
        this.stringMinlengthAlice = stringMinlengthAlice;
    }

    public String getStringMaxlengthAlice() {
        return stringMaxlengthAlice;
    }

    public FieldTestPaginationEntity stringMaxlengthAlice(String stringMaxlengthAlice) {
        this.stringMaxlengthAlice = stringMaxlengthAlice;
        return this;
    }

    public void setStringMaxlengthAlice(String stringMaxlengthAlice) {
        this.stringMaxlengthAlice = stringMaxlengthAlice;
    }

    public String getStringPatternAlice() {
        return stringPatternAlice;
    }

    public FieldTestPaginationEntity stringPatternAlice(String stringPatternAlice) {
        this.stringPatternAlice = stringPatternAlice;
        return this;
    }

    public void setStringPatternAlice(String stringPatternAlice) {
        this.stringPatternAlice = stringPatternAlice;
    }

    public Integer getIntegerAlice() {
        return integerAlice;
    }

    public FieldTestPaginationEntity integerAlice(Integer integerAlice) {
        this.integerAlice = integerAlice;
        return this;
    }

    public void setIntegerAlice(Integer integerAlice) {
        this.integerAlice = integerAlice;
    }

    public Integer getIntegerRequiredAlice() {
        return integerRequiredAlice;
    }

    public FieldTestPaginationEntity integerRequiredAlice(Integer integerRequiredAlice) {
        this.integerRequiredAlice = integerRequiredAlice;
        return this;
    }

    public void setIntegerRequiredAlice(Integer integerRequiredAlice) {
        this.integerRequiredAlice = integerRequiredAlice;
    }

    public Integer getIntegerMinAlice() {
        return integerMinAlice;
    }

    public FieldTestPaginationEntity integerMinAlice(Integer integerMinAlice) {
        this.integerMinAlice = integerMinAlice;
        return this;
    }

    public void setIntegerMinAlice(Integer integerMinAlice) {
        this.integerMinAlice = integerMinAlice;
    }

    public Integer getIntegerMaxAlice() {
        return integerMaxAlice;
    }

    public FieldTestPaginationEntity integerMaxAlice(Integer integerMaxAlice) {
        this.integerMaxAlice = integerMaxAlice;
        return this;
    }

    public void setIntegerMaxAlice(Integer integerMaxAlice) {
        this.integerMaxAlice = integerMaxAlice;
    }

    public Long getLongAlice() {
        return longAlice;
    }

    public FieldTestPaginationEntity longAlice(Long longAlice) {
        this.longAlice = longAlice;
        return this;
    }

    public void setLongAlice(Long longAlice) {
        this.longAlice = longAlice;
    }

    public Long getLongRequiredAlice() {
        return longRequiredAlice;
    }

    public FieldTestPaginationEntity longRequiredAlice(Long longRequiredAlice) {
        this.longRequiredAlice = longRequiredAlice;
        return this;
    }

    public void setLongRequiredAlice(Long longRequiredAlice) {
        this.longRequiredAlice = longRequiredAlice;
    }

    public Long getLongMinAlice() {
        return longMinAlice;
    }

    public FieldTestPaginationEntity longMinAlice(Long longMinAlice) {
        this.longMinAlice = longMinAlice;
        return this;
    }

    public void setLongMinAlice(Long longMinAlice) {
        this.longMinAlice = longMinAlice;
    }

    public Long getLongMaxAlice() {
        return longMaxAlice;
    }

    public FieldTestPaginationEntity longMaxAlice(Long longMaxAlice) {
        this.longMaxAlice = longMaxAlice;
        return this;
    }

    public void setLongMaxAlice(Long longMaxAlice) {
        this.longMaxAlice = longMaxAlice;
    }

    public Float getFloatAlice() {
        return floatAlice;
    }

    public FieldTestPaginationEntity floatAlice(Float floatAlice) {
        this.floatAlice = floatAlice;
        return this;
    }

    public void setFloatAlice(Float floatAlice) {
        this.floatAlice = floatAlice;
    }

    public Float getFloatRequiredAlice() {
        return floatRequiredAlice;
    }

    public FieldTestPaginationEntity floatRequiredAlice(Float floatRequiredAlice) {
        this.floatRequiredAlice = floatRequiredAlice;
        return this;
    }

    public void setFloatRequiredAlice(Float floatRequiredAlice) {
        this.floatRequiredAlice = floatRequiredAlice;
    }

    public Float getFloatMinAlice() {
        return floatMinAlice;
    }

    public FieldTestPaginationEntity floatMinAlice(Float floatMinAlice) {
        this.floatMinAlice = floatMinAlice;
        return this;
    }

    public void setFloatMinAlice(Float floatMinAlice) {
        this.floatMinAlice = floatMinAlice;
    }

    public Float getFloatMaxAlice() {
        return floatMaxAlice;
    }

    public FieldTestPaginationEntity floatMaxAlice(Float floatMaxAlice) {
        this.floatMaxAlice = floatMaxAlice;
        return this;
    }

    public void setFloatMaxAlice(Float floatMaxAlice) {
        this.floatMaxAlice = floatMaxAlice;
    }

    public Double getDoubleRequiredAlice() {
        return doubleRequiredAlice;
    }

    public FieldTestPaginationEntity doubleRequiredAlice(Double doubleRequiredAlice) {
        this.doubleRequiredAlice = doubleRequiredAlice;
        return this;
    }

    public void setDoubleRequiredAlice(Double doubleRequiredAlice) {
        this.doubleRequiredAlice = doubleRequiredAlice;
    }

    public Double getDoubleMinAlice() {
        return doubleMinAlice;
    }

    public FieldTestPaginationEntity doubleMinAlice(Double doubleMinAlice) {
        this.doubleMinAlice = doubleMinAlice;
        return this;
    }

    public void setDoubleMinAlice(Double doubleMinAlice) {
        this.doubleMinAlice = doubleMinAlice;
    }

    public Double getDoubleMaxAlice() {
        return doubleMaxAlice;
    }

    public FieldTestPaginationEntity doubleMaxAlice(Double doubleMaxAlice) {
        this.doubleMaxAlice = doubleMaxAlice;
        return this;
    }

    public void setDoubleMaxAlice(Double doubleMaxAlice) {
        this.doubleMaxAlice = doubleMaxAlice;
    }

    public BigDecimal getBigDecimalRequiredAlice() {
        return bigDecimalRequiredAlice;
    }

    public FieldTestPaginationEntity bigDecimalRequiredAlice(BigDecimal bigDecimalRequiredAlice) {
        this.bigDecimalRequiredAlice = bigDecimalRequiredAlice;
        return this;
    }

    public void setBigDecimalRequiredAlice(BigDecimal bigDecimalRequiredAlice) {
        this.bigDecimalRequiredAlice = bigDecimalRequiredAlice;
    }

    public BigDecimal getBigDecimalMinAlice() {
        return bigDecimalMinAlice;
    }

    public FieldTestPaginationEntity bigDecimalMinAlice(BigDecimal bigDecimalMinAlice) {
        this.bigDecimalMinAlice = bigDecimalMinAlice;
        return this;
    }

    public void setBigDecimalMinAlice(BigDecimal bigDecimalMinAlice) {
        this.bigDecimalMinAlice = bigDecimalMinAlice;
    }

    public BigDecimal getBigDecimalMaxAlice() {
        return bigDecimalMaxAlice;
    }

    public FieldTestPaginationEntity bigDecimalMaxAlice(BigDecimal bigDecimalMaxAlice) {
        this.bigDecimalMaxAlice = bigDecimalMaxAlice;
        return this;
    }

    public void setBigDecimalMaxAlice(BigDecimal bigDecimalMaxAlice) {
        this.bigDecimalMaxAlice = bigDecimalMaxAlice;
    }

    public LocalDate getLocalDateAlice() {
        return localDateAlice;
    }

    public FieldTestPaginationEntity localDateAlice(LocalDate localDateAlice) {
        this.localDateAlice = localDateAlice;
        return this;
    }

    public void setLocalDateAlice(LocalDate localDateAlice) {
        this.localDateAlice = localDateAlice;
    }

    public LocalDate getLocalDateRequiredAlice() {
        return localDateRequiredAlice;
    }

    public FieldTestPaginationEntity localDateRequiredAlice(LocalDate localDateRequiredAlice) {
        this.localDateRequiredAlice = localDateRequiredAlice;
        return this;
    }

    public void setLocalDateRequiredAlice(LocalDate localDateRequiredAlice) {
        this.localDateRequiredAlice = localDateRequiredAlice;
    }

    public Instant getInstantAlice() {
        return instantAlice;
    }

    public FieldTestPaginationEntity instantAlice(Instant instantAlice) {
        this.instantAlice = instantAlice;
        return this;
    }

    public void setInstantAlice(Instant instantAlice) {
        this.instantAlice = instantAlice;
    }

    public Instant getInstanteRequiredAlice() {
        return instanteRequiredAlice;
    }

    public FieldTestPaginationEntity instanteRequiredAlice(Instant instanteRequiredAlice) {
        this.instanteRequiredAlice = instanteRequiredAlice;
        return this;
    }

    public void setInstanteRequiredAlice(Instant instanteRequiredAlice) {
        this.instanteRequiredAlice = instanteRequiredAlice;
    }

    public ZonedDateTime getZonedDateTimeAlice() {
        return zonedDateTimeAlice;
    }

    public FieldTestPaginationEntity zonedDateTimeAlice(ZonedDateTime zonedDateTimeAlice) {
        this.zonedDateTimeAlice = zonedDateTimeAlice;
        return this;
    }

    public void setZonedDateTimeAlice(ZonedDateTime zonedDateTimeAlice) {
        this.zonedDateTimeAlice = zonedDateTimeAlice;
    }

    public ZonedDateTime getZonedDateTimeRequiredAlice() {
        return zonedDateTimeRequiredAlice;
    }

    public FieldTestPaginationEntity zonedDateTimeRequiredAlice(ZonedDateTime zonedDateTimeRequiredAlice) {
        this.zonedDateTimeRequiredAlice = zonedDateTimeRequiredAlice;
        return this;
    }

    public void setZonedDateTimeRequiredAlice(ZonedDateTime zonedDateTimeRequiredAlice) {
        this.zonedDateTimeRequiredAlice = zonedDateTimeRequiredAlice;
    }

    public Boolean isBooleanAlice() {
        return booleanAlice;
    }

    public FieldTestPaginationEntity booleanAlice(Boolean booleanAlice) {
        this.booleanAlice = booleanAlice;
        return this;
    }

    public void setBooleanAlice(Boolean booleanAlice) {
        this.booleanAlice = booleanAlice;
    }

    public Boolean isBooleanRequiredAlice() {
        return booleanRequiredAlice;
    }

    public FieldTestPaginationEntity booleanRequiredAlice(Boolean booleanRequiredAlice) {
        this.booleanRequiredAlice = booleanRequiredAlice;
        return this;
    }

    public void setBooleanRequiredAlice(Boolean booleanRequiredAlice) {
        this.booleanRequiredAlice = booleanRequiredAlice;
    }

    public EnumFieldClass getEnumAlice() {
        return enumAlice;
    }

    public FieldTestPaginationEntity enumAlice(EnumFieldClass enumAlice) {
        this.enumAlice = enumAlice;
        return this;
    }

    public void setEnumAlice(EnumFieldClass enumAlice) {
        this.enumAlice = enumAlice;
    }

    public EnumRequiredFieldClass getEnumRequiredAlice() {
        return enumRequiredAlice;
    }

    public FieldTestPaginationEntity enumRequiredAlice(EnumRequiredFieldClass enumRequiredAlice) {
        this.enumRequiredAlice = enumRequiredAlice;
        return this;
    }

    public void setEnumRequiredAlice(EnumRequiredFieldClass enumRequiredAlice) {
        this.enumRequiredAlice = enumRequiredAlice;
    }

    public byte[] getByteImageAlice() {
        return byteImageAlice;
    }

    public FieldTestPaginationEntity byteImageAlice(byte[] byteImageAlice) {
        this.byteImageAlice = byteImageAlice;
        return this;
    }

    public void setByteImageAlice(byte[] byteImageAlice) {
        this.byteImageAlice = byteImageAlice;
    }

    public String getByteImageAliceContentType() {
        return byteImageAliceContentType;
    }

    public FieldTestPaginationEntity byteImageAliceContentType(String byteImageAliceContentType) {
        this.byteImageAliceContentType = byteImageAliceContentType;
        return this;
    }

    public void setByteImageAliceContentType(String byteImageAliceContentType) {
        this.byteImageAliceContentType = byteImageAliceContentType;
    }

    public byte[] getByteImageRequiredAlice() {
        return byteImageRequiredAlice;
    }

    public FieldTestPaginationEntity byteImageRequiredAlice(byte[] byteImageRequiredAlice) {
        this.byteImageRequiredAlice = byteImageRequiredAlice;
        return this;
    }

    public void setByteImageRequiredAlice(byte[] byteImageRequiredAlice) {
        this.byteImageRequiredAlice = byteImageRequiredAlice;
    }

    public String getByteImageRequiredAliceContentType() {
        return byteImageRequiredAliceContentType;
    }

    public FieldTestPaginationEntity byteImageRequiredAliceContentType(String byteImageRequiredAliceContentType) {
        this.byteImageRequiredAliceContentType = byteImageRequiredAliceContentType;
        return this;
    }

    public void setByteImageRequiredAliceContentType(String byteImageRequiredAliceContentType) {
        this.byteImageRequiredAliceContentType = byteImageRequiredAliceContentType;
    }

    public byte[] getByteImageMinbytesAlice() {
        return byteImageMinbytesAlice;
    }

    public FieldTestPaginationEntity byteImageMinbytesAlice(byte[] byteImageMinbytesAlice) {
        this.byteImageMinbytesAlice = byteImageMinbytesAlice;
        return this;
    }

    public void setByteImageMinbytesAlice(byte[] byteImageMinbytesAlice) {
        this.byteImageMinbytesAlice = byteImageMinbytesAlice;
    }

    public String getByteImageMinbytesAliceContentType() {
        return byteImageMinbytesAliceContentType;
    }

    public FieldTestPaginationEntity byteImageMinbytesAliceContentType(String byteImageMinbytesAliceContentType) {
        this.byteImageMinbytesAliceContentType = byteImageMinbytesAliceContentType;
        return this;
    }

    public void setByteImageMinbytesAliceContentType(String byteImageMinbytesAliceContentType) {
        this.byteImageMinbytesAliceContentType = byteImageMinbytesAliceContentType;
    }

    public byte[] getByteImageMaxbytesAlice() {
        return byteImageMaxbytesAlice;
    }

    public FieldTestPaginationEntity byteImageMaxbytesAlice(byte[] byteImageMaxbytesAlice) {
        this.byteImageMaxbytesAlice = byteImageMaxbytesAlice;
        return this;
    }

    public void setByteImageMaxbytesAlice(byte[] byteImageMaxbytesAlice) {
        this.byteImageMaxbytesAlice = byteImageMaxbytesAlice;
    }

    public String getByteImageMaxbytesAliceContentType() {
        return byteImageMaxbytesAliceContentType;
    }

    public FieldTestPaginationEntity byteImageMaxbytesAliceContentType(String byteImageMaxbytesAliceContentType) {
        this.byteImageMaxbytesAliceContentType = byteImageMaxbytesAliceContentType;
        return this;
    }

    public void setByteImageMaxbytesAliceContentType(String byteImageMaxbytesAliceContentType) {
        this.byteImageMaxbytesAliceContentType = byteImageMaxbytesAliceContentType;
    }

    public byte[] getByteAnyAlice() {
        return byteAnyAlice;
    }

    public FieldTestPaginationEntity byteAnyAlice(byte[] byteAnyAlice) {
        this.byteAnyAlice = byteAnyAlice;
        return this;
    }

    public void setByteAnyAlice(byte[] byteAnyAlice) {
        this.byteAnyAlice = byteAnyAlice;
    }

    public String getByteAnyAliceContentType() {
        return byteAnyAliceContentType;
    }

    public FieldTestPaginationEntity byteAnyAliceContentType(String byteAnyAliceContentType) {
        this.byteAnyAliceContentType = byteAnyAliceContentType;
        return this;
    }

    public void setByteAnyAliceContentType(String byteAnyAliceContentType) {
        this.byteAnyAliceContentType = byteAnyAliceContentType;
    }

    public byte[] getByteAnyRequiredAlice() {
        return byteAnyRequiredAlice;
    }

    public FieldTestPaginationEntity byteAnyRequiredAlice(byte[] byteAnyRequiredAlice) {
        this.byteAnyRequiredAlice = byteAnyRequiredAlice;
        return this;
    }

    public void setByteAnyRequiredAlice(byte[] byteAnyRequiredAlice) {
        this.byteAnyRequiredAlice = byteAnyRequiredAlice;
    }

    public String getByteAnyRequiredAliceContentType() {
        return byteAnyRequiredAliceContentType;
    }

    public FieldTestPaginationEntity byteAnyRequiredAliceContentType(String byteAnyRequiredAliceContentType) {
        this.byteAnyRequiredAliceContentType = byteAnyRequiredAliceContentType;
        return this;
    }

    public void setByteAnyRequiredAliceContentType(String byteAnyRequiredAliceContentType) {
        this.byteAnyRequiredAliceContentType = byteAnyRequiredAliceContentType;
    }

    public byte[] getByteAnyMinbytesAlice() {
        return byteAnyMinbytesAlice;
    }

    public FieldTestPaginationEntity byteAnyMinbytesAlice(byte[] byteAnyMinbytesAlice) {
        this.byteAnyMinbytesAlice = byteAnyMinbytesAlice;
        return this;
    }

    public void setByteAnyMinbytesAlice(byte[] byteAnyMinbytesAlice) {
        this.byteAnyMinbytesAlice = byteAnyMinbytesAlice;
    }

    public String getByteAnyMinbytesAliceContentType() {
        return byteAnyMinbytesAliceContentType;
    }

    public FieldTestPaginationEntity byteAnyMinbytesAliceContentType(String byteAnyMinbytesAliceContentType) {
        this.byteAnyMinbytesAliceContentType = byteAnyMinbytesAliceContentType;
        return this;
    }

    public void setByteAnyMinbytesAliceContentType(String byteAnyMinbytesAliceContentType) {
        this.byteAnyMinbytesAliceContentType = byteAnyMinbytesAliceContentType;
    }

    public byte[] getByteAnyMaxbytesAlice() {
        return byteAnyMaxbytesAlice;
    }

    public FieldTestPaginationEntity byteAnyMaxbytesAlice(byte[] byteAnyMaxbytesAlice) {
        this.byteAnyMaxbytesAlice = byteAnyMaxbytesAlice;
        return this;
    }

    public void setByteAnyMaxbytesAlice(byte[] byteAnyMaxbytesAlice) {
        this.byteAnyMaxbytesAlice = byteAnyMaxbytesAlice;
    }

    public String getByteAnyMaxbytesAliceContentType() {
        return byteAnyMaxbytesAliceContentType;
    }

    public FieldTestPaginationEntity byteAnyMaxbytesAliceContentType(String byteAnyMaxbytesAliceContentType) {
        this.byteAnyMaxbytesAliceContentType = byteAnyMaxbytesAliceContentType;
        return this;
    }

    public void setByteAnyMaxbytesAliceContentType(String byteAnyMaxbytesAliceContentType) {
        this.byteAnyMaxbytesAliceContentType = byteAnyMaxbytesAliceContentType;
    }

    public String getByteTextAlice() {
        return byteTextAlice;
    }

    public FieldTestPaginationEntity byteTextAlice(String byteTextAlice) {
        this.byteTextAlice = byteTextAlice;
        return this;
    }

    public void setByteTextAlice(String byteTextAlice) {
        this.byteTextAlice = byteTextAlice;
    }

    public String getByteTextRequiredAlice() {
        return byteTextRequiredAlice;
    }

    public FieldTestPaginationEntity byteTextRequiredAlice(String byteTextRequiredAlice) {
        this.byteTextRequiredAlice = byteTextRequiredAlice;
        return this;
    }

    public void setByteTextRequiredAlice(String byteTextRequiredAlice) {
        this.byteTextRequiredAlice = byteTextRequiredAlice;
    }

    public String getByteTextMinbytesAlice() {
        return byteTextMinbytesAlice;
    }

    public FieldTestPaginationEntity byteTextMinbytesAlice(String byteTextMinbytesAlice) {
        this.byteTextMinbytesAlice = byteTextMinbytesAlice;
        return this;
    }

    public void setByteTextMinbytesAlice(String byteTextMinbytesAlice) {
        this.byteTextMinbytesAlice = byteTextMinbytesAlice;
    }

    public String getByteTextMaxbytesAlice() {
        return byteTextMaxbytesAlice;
    }

    public FieldTestPaginationEntity byteTextMaxbytesAlice(String byteTextMaxbytesAlice) {
        this.byteTextMaxbytesAlice = byteTextMaxbytesAlice;
        return this;
    }

    public void setByteTextMaxbytesAlice(String byteTextMaxbytesAlice) {
        this.byteTextMaxbytesAlice = byteTextMaxbytesAlice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FieldTestPaginationEntity fieldTestPaginationEntity = (FieldTestPaginationEntity) o;
        if (fieldTestPaginationEntity.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), fieldTestPaginationEntity.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "FieldTestPaginationEntity{" +
            "id=" + getId() +
            ", stringAlice='" + getStringAlice() + "'" +
            ", stringRequiredAlice='" + getStringRequiredAlice() + "'" +
            ", stringMinlengthAlice='" + getStringMinlengthAlice() + "'" +
            ", stringMaxlengthAlice='" + getStringMaxlengthAlice() + "'" +
            ", stringPatternAlice='" + getStringPatternAlice() + "'" +
            ", integerAlice='" + getIntegerAlice() + "'" +
            ", integerRequiredAlice='" + getIntegerRequiredAlice() + "'" +
            ", integerMinAlice='" + getIntegerMinAlice() + "'" +
            ", integerMaxAlice='" + getIntegerMaxAlice() + "'" +
            ", longAlice='" + getLongAlice() + "'" +
            ", longRequiredAlice='" + getLongRequiredAlice() + "'" +
            ", longMinAlice='" + getLongMinAlice() + "'" +
            ", longMaxAlice='" + getLongMaxAlice() + "'" +
            ", floatAlice='" + getFloatAlice() + "'" +
            ", floatRequiredAlice='" + getFloatRequiredAlice() + "'" +
            ", floatMinAlice='" + getFloatMinAlice() + "'" +
            ", floatMaxAlice='" + getFloatMaxAlice() + "'" +
            ", doubleRequiredAlice='" + getDoubleRequiredAlice() + "'" +
            ", doubleMinAlice='" + getDoubleMinAlice() + "'" +
            ", doubleMaxAlice='" + getDoubleMaxAlice() + "'" +
            ", bigDecimalRequiredAlice='" + getBigDecimalRequiredAlice() + "'" +
            ", bigDecimalMinAlice='" + getBigDecimalMinAlice() + "'" +
            ", bigDecimalMaxAlice='" + getBigDecimalMaxAlice() + "'" +
            ", localDateAlice='" + getLocalDateAlice() + "'" +
            ", localDateRequiredAlice='" + getLocalDateRequiredAlice() + "'" +
            ", instantAlice='" + getInstantAlice() + "'" +
            ", instanteRequiredAlice='" + getInstanteRequiredAlice() + "'" +
            ", zonedDateTimeAlice='" + getZonedDateTimeAlice() + "'" +
            ", zonedDateTimeRequiredAlice='" + getZonedDateTimeRequiredAlice() + "'" +
            ", booleanAlice='" + isBooleanAlice() + "'" +
            ", booleanRequiredAlice='" + isBooleanRequiredAlice() + "'" +
            ", enumAlice='" + getEnumAlice() + "'" +
            ", enumRequiredAlice='" + getEnumRequiredAlice() + "'" +
            ", byteImageAlice='" + getByteImageAlice() + "'" +
            ", byteImageAliceContentType='" + byteImageAliceContentType + "'" +
            ", byteImageRequiredAlice='" + getByteImageRequiredAlice() + "'" +
            ", byteImageRequiredAliceContentType='" + byteImageRequiredAliceContentType + "'" +
            ", byteImageMinbytesAlice='" + getByteImageMinbytesAlice() + "'" +
            ", byteImageMinbytesAliceContentType='" + byteImageMinbytesAliceContentType + "'" +
            ", byteImageMaxbytesAlice='" + getByteImageMaxbytesAlice() + "'" +
            ", byteImageMaxbytesAliceContentType='" + byteImageMaxbytesAliceContentType + "'" +
            ", byteAnyAlice='" + getByteAnyAlice() + "'" +
            ", byteAnyAliceContentType='" + byteAnyAliceContentType + "'" +
            ", byteAnyRequiredAlice='" + getByteAnyRequiredAlice() + "'" +
            ", byteAnyRequiredAliceContentType='" + byteAnyRequiredAliceContentType + "'" +
            ", byteAnyMinbytesAlice='" + getByteAnyMinbytesAlice() + "'" +
            ", byteAnyMinbytesAliceContentType='" + byteAnyMinbytesAliceContentType + "'" +
            ", byteAnyMaxbytesAlice='" + getByteAnyMaxbytesAlice() + "'" +
            ", byteAnyMaxbytesAliceContentType='" + byteAnyMaxbytesAliceContentType + "'" +
            ", byteTextAlice='" + getByteTextAlice() + "'" +
            ", byteTextRequiredAlice='" + getByteTextRequiredAlice() + "'" +
            ", byteTextMinbytesAlice='" + getByteTextMinbytesAlice() + "'" +
            ", byteTextMaxbytesAlice='" + getByteTextMaxbytesAlice() + "'" +
            "}";
    }
}
