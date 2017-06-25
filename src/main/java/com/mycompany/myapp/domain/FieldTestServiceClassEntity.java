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
 * A FieldTestServiceClassEntity.
 */
@Entity
@Table(name = "field_test_service_class_entity")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "fieldtestserviceclassentity")
public class FieldTestServiceClassEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "string_bob")
    private String stringBob;

    @NotNull
    @Column(name = "string_required_bob", nullable = false)
    private String stringRequiredBob;

    @Size(min = 0)
    @Column(name = "string_minlength_bob")
    private String stringMinlengthBob;

    @Size(max = 20)
    @Column(name = "string_maxlength_bob", length = 20)
    private String stringMaxlengthBob;

    @Pattern(regexp = "^[a-zA-Z0-9]*$")
    @Column(name = "string_pattern_bob")
    private String stringPatternBob;

    @Column(name = "integer_bob")
    private Integer integerBob;

    @NotNull
    @Column(name = "integer_required_bob", nullable = false)
    private Integer integerRequiredBob;

    @Min(value = 0)
    @Column(name = "integer_min_bob")
    private Integer integerMinBob;

    @Max(value = 100)
    @Column(name = "integer_max_bob")
    private Integer integerMaxBob;

    @Column(name = "long_bob")
    private Long longBob;

    @NotNull
    @Column(name = "long_required_bob", nullable = false)
    private Long longRequiredBob;

    @Min(value = 0)
    @Column(name = "long_min_bob")
    private Long longMinBob;

    @Max(value = 100)
    @Column(name = "long_max_bob")
    private Long longMaxBob;

    @Column(name = "float_bob")
    private Float floatBob;

    @NotNull
    @Column(name = "float_required_bob", nullable = false)
    private Float floatRequiredBob;

    @DecimalMin(value = "0")
    @Column(name = "float_min_bob")
    private Float floatMinBob;

    @DecimalMax(value = "100")
    @Column(name = "float_max_bob")
    private Float floatMaxBob;

    @NotNull
    @Column(name = "double_required_bob", nullable = false)
    private Double doubleRequiredBob;

    @DecimalMin(value = "0")
    @Column(name = "double_min_bob")
    private Double doubleMinBob;

    @DecimalMax(value = "100")
    @Column(name = "double_max_bob")
    private Double doubleMaxBob;

    @NotNull
    @Column(name = "big_decimal_required_bob", precision=10, scale=2, nullable = false)
    private BigDecimal bigDecimalRequiredBob;

    @DecimalMin(value = "0")
    @Column(name = "big_decimal_min_bob", precision=10, scale=2)
    private BigDecimal bigDecimalMinBob;

    @DecimalMax(value = "100")
    @Column(name = "big_decimal_max_bob", precision=10, scale=2)
    private BigDecimal bigDecimalMaxBob;

    @Column(name = "local_date_bob")
    private LocalDate localDateBob;

    @NotNull
    @Column(name = "local_date_required_bob", nullable = false)
    private LocalDate localDateRequiredBob;

    @Column(name = "instant_bob")
    private Instant instantBob;

    @NotNull
    @Column(name = "instante_required_bob", nullable = false)
    private Instant instanteRequiredBob;

    @Column(name = "zoned_date_time_bob")
    private ZonedDateTime zonedDateTimeBob;

    @NotNull
    @Column(name = "zoned_date_time_required_bob", nullable = false)
    private ZonedDateTime zonedDateTimeRequiredBob;

    @Column(name = "boolean_bob")
    private Boolean booleanBob;

    @NotNull
    @Column(name = "boolean_required_bob", nullable = false)
    private Boolean booleanRequiredBob;

    @Enumerated(EnumType.STRING)
    @Column(name = "enum_bob")
    private EnumFieldClass enumBob;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "enum_required_bob", nullable = false)
    private EnumRequiredFieldClass enumRequiredBob;

    @Lob
    @Column(name = "byte_image_bob")
    private byte[] byteImageBob;

    @Column(name = "byte_image_bob_content_type")
    private String byteImageBobContentType;

    @NotNull
    @Lob
    @Column(name = "byte_image_required_bob", nullable = false)
    private byte[] byteImageRequiredBob;

    @Column(name = "byte_image_required_bob_content_type", nullable = false)
    private String byteImageRequiredBobContentType;

    @Size(min = 0)
    @Lob
    @Column(name = "byte_image_minbytes_bob")
    private byte[] byteImageMinbytesBob;

    @Column(name = "byte_image_minbytes_bob_content_type")
    private String byteImageMinbytesBobContentType;

    @Size(max = 10000)
    @Lob
    @Column(name = "byte_image_maxbytes_bob")
    private byte[] byteImageMaxbytesBob;

    @Column(name = "byte_image_maxbytes_bob_content_type")
    private String byteImageMaxbytesBobContentType;

    @Lob
    @Column(name = "byte_any_bob")
    private byte[] byteAnyBob;

    @Column(name = "byte_any_bob_content_type")
    private String byteAnyBobContentType;

    @NotNull
    @Lob
    @Column(name = "byte_any_required_bob", nullable = false)
    private byte[] byteAnyRequiredBob;

    @Column(name = "byte_any_required_bob_content_type", nullable = false)
    private String byteAnyRequiredBobContentType;

    @Size(min = 0)
    @Lob
    @Column(name = "byte_any_minbytes_bob")
    private byte[] byteAnyMinbytesBob;

    @Column(name = "byte_any_minbytes_bob_content_type")
    private String byteAnyMinbytesBobContentType;

    @Size(max = 10000)
    @Lob
    @Column(name = "byte_any_maxbytes_bob")
    private byte[] byteAnyMaxbytesBob;

    @Column(name = "byte_any_maxbytes_bob_content_type")
    private String byteAnyMaxbytesBobContentType;

    @Lob
    @Column(name = "byte_text_bob")
    private String byteTextBob;

    @NotNull
    @Lob
    @Column(name = "byte_text_required_bob", nullable = false)
    private String byteTextRequiredBob;

    @Size(min = 0)
    @Lob
    @Column(name = "byte_text_minbytes_bob")
    private String byteTextMinbytesBob;

    @Size(max = 10000)
    @Lob
    @Column(name = "byte_text_maxbytes_bob")
    private String byteTextMaxbytesBob;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStringBob() {
        return stringBob;
    }

    public FieldTestServiceClassEntity stringBob(String stringBob) {
        this.stringBob = stringBob;
        return this;
    }

    public void setStringBob(String stringBob) {
        this.stringBob = stringBob;
    }

    public String getStringRequiredBob() {
        return stringRequiredBob;
    }

    public FieldTestServiceClassEntity stringRequiredBob(String stringRequiredBob) {
        this.stringRequiredBob = stringRequiredBob;
        return this;
    }

    public void setStringRequiredBob(String stringRequiredBob) {
        this.stringRequiredBob = stringRequiredBob;
    }

    public String getStringMinlengthBob() {
        return stringMinlengthBob;
    }

    public FieldTestServiceClassEntity stringMinlengthBob(String stringMinlengthBob) {
        this.stringMinlengthBob = stringMinlengthBob;
        return this;
    }

    public void setStringMinlengthBob(String stringMinlengthBob) {
        this.stringMinlengthBob = stringMinlengthBob;
    }

    public String getStringMaxlengthBob() {
        return stringMaxlengthBob;
    }

    public FieldTestServiceClassEntity stringMaxlengthBob(String stringMaxlengthBob) {
        this.stringMaxlengthBob = stringMaxlengthBob;
        return this;
    }

    public void setStringMaxlengthBob(String stringMaxlengthBob) {
        this.stringMaxlengthBob = stringMaxlengthBob;
    }

    public String getStringPatternBob() {
        return stringPatternBob;
    }

    public FieldTestServiceClassEntity stringPatternBob(String stringPatternBob) {
        this.stringPatternBob = stringPatternBob;
        return this;
    }

    public void setStringPatternBob(String stringPatternBob) {
        this.stringPatternBob = stringPatternBob;
    }

    public Integer getIntegerBob() {
        return integerBob;
    }

    public FieldTestServiceClassEntity integerBob(Integer integerBob) {
        this.integerBob = integerBob;
        return this;
    }

    public void setIntegerBob(Integer integerBob) {
        this.integerBob = integerBob;
    }

    public Integer getIntegerRequiredBob() {
        return integerRequiredBob;
    }

    public FieldTestServiceClassEntity integerRequiredBob(Integer integerRequiredBob) {
        this.integerRequiredBob = integerRequiredBob;
        return this;
    }

    public void setIntegerRequiredBob(Integer integerRequiredBob) {
        this.integerRequiredBob = integerRequiredBob;
    }

    public Integer getIntegerMinBob() {
        return integerMinBob;
    }

    public FieldTestServiceClassEntity integerMinBob(Integer integerMinBob) {
        this.integerMinBob = integerMinBob;
        return this;
    }

    public void setIntegerMinBob(Integer integerMinBob) {
        this.integerMinBob = integerMinBob;
    }

    public Integer getIntegerMaxBob() {
        return integerMaxBob;
    }

    public FieldTestServiceClassEntity integerMaxBob(Integer integerMaxBob) {
        this.integerMaxBob = integerMaxBob;
        return this;
    }

    public void setIntegerMaxBob(Integer integerMaxBob) {
        this.integerMaxBob = integerMaxBob;
    }

    public Long getLongBob() {
        return longBob;
    }

    public FieldTestServiceClassEntity longBob(Long longBob) {
        this.longBob = longBob;
        return this;
    }

    public void setLongBob(Long longBob) {
        this.longBob = longBob;
    }

    public Long getLongRequiredBob() {
        return longRequiredBob;
    }

    public FieldTestServiceClassEntity longRequiredBob(Long longRequiredBob) {
        this.longRequiredBob = longRequiredBob;
        return this;
    }

    public void setLongRequiredBob(Long longRequiredBob) {
        this.longRequiredBob = longRequiredBob;
    }

    public Long getLongMinBob() {
        return longMinBob;
    }

    public FieldTestServiceClassEntity longMinBob(Long longMinBob) {
        this.longMinBob = longMinBob;
        return this;
    }

    public void setLongMinBob(Long longMinBob) {
        this.longMinBob = longMinBob;
    }

    public Long getLongMaxBob() {
        return longMaxBob;
    }

    public FieldTestServiceClassEntity longMaxBob(Long longMaxBob) {
        this.longMaxBob = longMaxBob;
        return this;
    }

    public void setLongMaxBob(Long longMaxBob) {
        this.longMaxBob = longMaxBob;
    }

    public Float getFloatBob() {
        return floatBob;
    }

    public FieldTestServiceClassEntity floatBob(Float floatBob) {
        this.floatBob = floatBob;
        return this;
    }

    public void setFloatBob(Float floatBob) {
        this.floatBob = floatBob;
    }

    public Float getFloatRequiredBob() {
        return floatRequiredBob;
    }

    public FieldTestServiceClassEntity floatRequiredBob(Float floatRequiredBob) {
        this.floatRequiredBob = floatRequiredBob;
        return this;
    }

    public void setFloatRequiredBob(Float floatRequiredBob) {
        this.floatRequiredBob = floatRequiredBob;
    }

    public Float getFloatMinBob() {
        return floatMinBob;
    }

    public FieldTestServiceClassEntity floatMinBob(Float floatMinBob) {
        this.floatMinBob = floatMinBob;
        return this;
    }

    public void setFloatMinBob(Float floatMinBob) {
        this.floatMinBob = floatMinBob;
    }

    public Float getFloatMaxBob() {
        return floatMaxBob;
    }

    public FieldTestServiceClassEntity floatMaxBob(Float floatMaxBob) {
        this.floatMaxBob = floatMaxBob;
        return this;
    }

    public void setFloatMaxBob(Float floatMaxBob) {
        this.floatMaxBob = floatMaxBob;
    }

    public Double getDoubleRequiredBob() {
        return doubleRequiredBob;
    }

    public FieldTestServiceClassEntity doubleRequiredBob(Double doubleRequiredBob) {
        this.doubleRequiredBob = doubleRequiredBob;
        return this;
    }

    public void setDoubleRequiredBob(Double doubleRequiredBob) {
        this.doubleRequiredBob = doubleRequiredBob;
    }

    public Double getDoubleMinBob() {
        return doubleMinBob;
    }

    public FieldTestServiceClassEntity doubleMinBob(Double doubleMinBob) {
        this.doubleMinBob = doubleMinBob;
        return this;
    }

    public void setDoubleMinBob(Double doubleMinBob) {
        this.doubleMinBob = doubleMinBob;
    }

    public Double getDoubleMaxBob() {
        return doubleMaxBob;
    }

    public FieldTestServiceClassEntity doubleMaxBob(Double doubleMaxBob) {
        this.doubleMaxBob = doubleMaxBob;
        return this;
    }

    public void setDoubleMaxBob(Double doubleMaxBob) {
        this.doubleMaxBob = doubleMaxBob;
    }

    public BigDecimal getBigDecimalRequiredBob() {
        return bigDecimalRequiredBob;
    }

    public FieldTestServiceClassEntity bigDecimalRequiredBob(BigDecimal bigDecimalRequiredBob) {
        this.bigDecimalRequiredBob = bigDecimalRequiredBob;
        return this;
    }

    public void setBigDecimalRequiredBob(BigDecimal bigDecimalRequiredBob) {
        this.bigDecimalRequiredBob = bigDecimalRequiredBob;
    }

    public BigDecimal getBigDecimalMinBob() {
        return bigDecimalMinBob;
    }

    public FieldTestServiceClassEntity bigDecimalMinBob(BigDecimal bigDecimalMinBob) {
        this.bigDecimalMinBob = bigDecimalMinBob;
        return this;
    }

    public void setBigDecimalMinBob(BigDecimal bigDecimalMinBob) {
        this.bigDecimalMinBob = bigDecimalMinBob;
    }

    public BigDecimal getBigDecimalMaxBob() {
        return bigDecimalMaxBob;
    }

    public FieldTestServiceClassEntity bigDecimalMaxBob(BigDecimal bigDecimalMaxBob) {
        this.bigDecimalMaxBob = bigDecimalMaxBob;
        return this;
    }

    public void setBigDecimalMaxBob(BigDecimal bigDecimalMaxBob) {
        this.bigDecimalMaxBob = bigDecimalMaxBob;
    }

    public LocalDate getLocalDateBob() {
        return localDateBob;
    }

    public FieldTestServiceClassEntity localDateBob(LocalDate localDateBob) {
        this.localDateBob = localDateBob;
        return this;
    }

    public void setLocalDateBob(LocalDate localDateBob) {
        this.localDateBob = localDateBob;
    }

    public LocalDate getLocalDateRequiredBob() {
        return localDateRequiredBob;
    }

    public FieldTestServiceClassEntity localDateRequiredBob(LocalDate localDateRequiredBob) {
        this.localDateRequiredBob = localDateRequiredBob;
        return this;
    }

    public void setLocalDateRequiredBob(LocalDate localDateRequiredBob) {
        this.localDateRequiredBob = localDateRequiredBob;
    }

    public Instant getInstantBob() {
        return instantBob;
    }

    public FieldTestServiceClassEntity instantBob(Instant instantBob) {
        this.instantBob = instantBob;
        return this;
    }

    public void setInstantBob(Instant instantBob) {
        this.instantBob = instantBob;
    }

    public Instant getInstanteRequiredBob() {
        return instanteRequiredBob;
    }

    public FieldTestServiceClassEntity instanteRequiredBob(Instant instanteRequiredBob) {
        this.instanteRequiredBob = instanteRequiredBob;
        return this;
    }

    public void setInstanteRequiredBob(Instant instanteRequiredBob) {
        this.instanteRequiredBob = instanteRequiredBob;
    }

    public ZonedDateTime getZonedDateTimeBob() {
        return zonedDateTimeBob;
    }

    public FieldTestServiceClassEntity zonedDateTimeBob(ZonedDateTime zonedDateTimeBob) {
        this.zonedDateTimeBob = zonedDateTimeBob;
        return this;
    }

    public void setZonedDateTimeBob(ZonedDateTime zonedDateTimeBob) {
        this.zonedDateTimeBob = zonedDateTimeBob;
    }

    public ZonedDateTime getZonedDateTimeRequiredBob() {
        return zonedDateTimeRequiredBob;
    }

    public FieldTestServiceClassEntity zonedDateTimeRequiredBob(ZonedDateTime zonedDateTimeRequiredBob) {
        this.zonedDateTimeRequiredBob = zonedDateTimeRequiredBob;
        return this;
    }

    public void setZonedDateTimeRequiredBob(ZonedDateTime zonedDateTimeRequiredBob) {
        this.zonedDateTimeRequiredBob = zonedDateTimeRequiredBob;
    }

    public Boolean isBooleanBob() {
        return booleanBob;
    }

    public FieldTestServiceClassEntity booleanBob(Boolean booleanBob) {
        this.booleanBob = booleanBob;
        return this;
    }

    public void setBooleanBob(Boolean booleanBob) {
        this.booleanBob = booleanBob;
    }

    public Boolean isBooleanRequiredBob() {
        return booleanRequiredBob;
    }

    public FieldTestServiceClassEntity booleanRequiredBob(Boolean booleanRequiredBob) {
        this.booleanRequiredBob = booleanRequiredBob;
        return this;
    }

    public void setBooleanRequiredBob(Boolean booleanRequiredBob) {
        this.booleanRequiredBob = booleanRequiredBob;
    }

    public EnumFieldClass getEnumBob() {
        return enumBob;
    }

    public FieldTestServiceClassEntity enumBob(EnumFieldClass enumBob) {
        this.enumBob = enumBob;
        return this;
    }

    public void setEnumBob(EnumFieldClass enumBob) {
        this.enumBob = enumBob;
    }

    public EnumRequiredFieldClass getEnumRequiredBob() {
        return enumRequiredBob;
    }

    public FieldTestServiceClassEntity enumRequiredBob(EnumRequiredFieldClass enumRequiredBob) {
        this.enumRequiredBob = enumRequiredBob;
        return this;
    }

    public void setEnumRequiredBob(EnumRequiredFieldClass enumRequiredBob) {
        this.enumRequiredBob = enumRequiredBob;
    }

    public byte[] getByteImageBob() {
        return byteImageBob;
    }

    public FieldTestServiceClassEntity byteImageBob(byte[] byteImageBob) {
        this.byteImageBob = byteImageBob;
        return this;
    }

    public void setByteImageBob(byte[] byteImageBob) {
        this.byteImageBob = byteImageBob;
    }

    public String getByteImageBobContentType() {
        return byteImageBobContentType;
    }

    public FieldTestServiceClassEntity byteImageBobContentType(String byteImageBobContentType) {
        this.byteImageBobContentType = byteImageBobContentType;
        return this;
    }

    public void setByteImageBobContentType(String byteImageBobContentType) {
        this.byteImageBobContentType = byteImageBobContentType;
    }

    public byte[] getByteImageRequiredBob() {
        return byteImageRequiredBob;
    }

    public FieldTestServiceClassEntity byteImageRequiredBob(byte[] byteImageRequiredBob) {
        this.byteImageRequiredBob = byteImageRequiredBob;
        return this;
    }

    public void setByteImageRequiredBob(byte[] byteImageRequiredBob) {
        this.byteImageRequiredBob = byteImageRequiredBob;
    }

    public String getByteImageRequiredBobContentType() {
        return byteImageRequiredBobContentType;
    }

    public FieldTestServiceClassEntity byteImageRequiredBobContentType(String byteImageRequiredBobContentType) {
        this.byteImageRequiredBobContentType = byteImageRequiredBobContentType;
        return this;
    }

    public void setByteImageRequiredBobContentType(String byteImageRequiredBobContentType) {
        this.byteImageRequiredBobContentType = byteImageRequiredBobContentType;
    }

    public byte[] getByteImageMinbytesBob() {
        return byteImageMinbytesBob;
    }

    public FieldTestServiceClassEntity byteImageMinbytesBob(byte[] byteImageMinbytesBob) {
        this.byteImageMinbytesBob = byteImageMinbytesBob;
        return this;
    }

    public void setByteImageMinbytesBob(byte[] byteImageMinbytesBob) {
        this.byteImageMinbytesBob = byteImageMinbytesBob;
    }

    public String getByteImageMinbytesBobContentType() {
        return byteImageMinbytesBobContentType;
    }

    public FieldTestServiceClassEntity byteImageMinbytesBobContentType(String byteImageMinbytesBobContentType) {
        this.byteImageMinbytesBobContentType = byteImageMinbytesBobContentType;
        return this;
    }

    public void setByteImageMinbytesBobContentType(String byteImageMinbytesBobContentType) {
        this.byteImageMinbytesBobContentType = byteImageMinbytesBobContentType;
    }

    public byte[] getByteImageMaxbytesBob() {
        return byteImageMaxbytesBob;
    }

    public FieldTestServiceClassEntity byteImageMaxbytesBob(byte[] byteImageMaxbytesBob) {
        this.byteImageMaxbytesBob = byteImageMaxbytesBob;
        return this;
    }

    public void setByteImageMaxbytesBob(byte[] byteImageMaxbytesBob) {
        this.byteImageMaxbytesBob = byteImageMaxbytesBob;
    }

    public String getByteImageMaxbytesBobContentType() {
        return byteImageMaxbytesBobContentType;
    }

    public FieldTestServiceClassEntity byteImageMaxbytesBobContentType(String byteImageMaxbytesBobContentType) {
        this.byteImageMaxbytesBobContentType = byteImageMaxbytesBobContentType;
        return this;
    }

    public void setByteImageMaxbytesBobContentType(String byteImageMaxbytesBobContentType) {
        this.byteImageMaxbytesBobContentType = byteImageMaxbytesBobContentType;
    }

    public byte[] getByteAnyBob() {
        return byteAnyBob;
    }

    public FieldTestServiceClassEntity byteAnyBob(byte[] byteAnyBob) {
        this.byteAnyBob = byteAnyBob;
        return this;
    }

    public void setByteAnyBob(byte[] byteAnyBob) {
        this.byteAnyBob = byteAnyBob;
    }

    public String getByteAnyBobContentType() {
        return byteAnyBobContentType;
    }

    public FieldTestServiceClassEntity byteAnyBobContentType(String byteAnyBobContentType) {
        this.byteAnyBobContentType = byteAnyBobContentType;
        return this;
    }

    public void setByteAnyBobContentType(String byteAnyBobContentType) {
        this.byteAnyBobContentType = byteAnyBobContentType;
    }

    public byte[] getByteAnyRequiredBob() {
        return byteAnyRequiredBob;
    }

    public FieldTestServiceClassEntity byteAnyRequiredBob(byte[] byteAnyRequiredBob) {
        this.byteAnyRequiredBob = byteAnyRequiredBob;
        return this;
    }

    public void setByteAnyRequiredBob(byte[] byteAnyRequiredBob) {
        this.byteAnyRequiredBob = byteAnyRequiredBob;
    }

    public String getByteAnyRequiredBobContentType() {
        return byteAnyRequiredBobContentType;
    }

    public FieldTestServiceClassEntity byteAnyRequiredBobContentType(String byteAnyRequiredBobContentType) {
        this.byteAnyRequiredBobContentType = byteAnyRequiredBobContentType;
        return this;
    }

    public void setByteAnyRequiredBobContentType(String byteAnyRequiredBobContentType) {
        this.byteAnyRequiredBobContentType = byteAnyRequiredBobContentType;
    }

    public byte[] getByteAnyMinbytesBob() {
        return byteAnyMinbytesBob;
    }

    public FieldTestServiceClassEntity byteAnyMinbytesBob(byte[] byteAnyMinbytesBob) {
        this.byteAnyMinbytesBob = byteAnyMinbytesBob;
        return this;
    }

    public void setByteAnyMinbytesBob(byte[] byteAnyMinbytesBob) {
        this.byteAnyMinbytesBob = byteAnyMinbytesBob;
    }

    public String getByteAnyMinbytesBobContentType() {
        return byteAnyMinbytesBobContentType;
    }

    public FieldTestServiceClassEntity byteAnyMinbytesBobContentType(String byteAnyMinbytesBobContentType) {
        this.byteAnyMinbytesBobContentType = byteAnyMinbytesBobContentType;
        return this;
    }

    public void setByteAnyMinbytesBobContentType(String byteAnyMinbytesBobContentType) {
        this.byteAnyMinbytesBobContentType = byteAnyMinbytesBobContentType;
    }

    public byte[] getByteAnyMaxbytesBob() {
        return byteAnyMaxbytesBob;
    }

    public FieldTestServiceClassEntity byteAnyMaxbytesBob(byte[] byteAnyMaxbytesBob) {
        this.byteAnyMaxbytesBob = byteAnyMaxbytesBob;
        return this;
    }

    public void setByteAnyMaxbytesBob(byte[] byteAnyMaxbytesBob) {
        this.byteAnyMaxbytesBob = byteAnyMaxbytesBob;
    }

    public String getByteAnyMaxbytesBobContentType() {
        return byteAnyMaxbytesBobContentType;
    }

    public FieldTestServiceClassEntity byteAnyMaxbytesBobContentType(String byteAnyMaxbytesBobContentType) {
        this.byteAnyMaxbytesBobContentType = byteAnyMaxbytesBobContentType;
        return this;
    }

    public void setByteAnyMaxbytesBobContentType(String byteAnyMaxbytesBobContentType) {
        this.byteAnyMaxbytesBobContentType = byteAnyMaxbytesBobContentType;
    }

    public String getByteTextBob() {
        return byteTextBob;
    }

    public FieldTestServiceClassEntity byteTextBob(String byteTextBob) {
        this.byteTextBob = byteTextBob;
        return this;
    }

    public void setByteTextBob(String byteTextBob) {
        this.byteTextBob = byteTextBob;
    }

    public String getByteTextRequiredBob() {
        return byteTextRequiredBob;
    }

    public FieldTestServiceClassEntity byteTextRequiredBob(String byteTextRequiredBob) {
        this.byteTextRequiredBob = byteTextRequiredBob;
        return this;
    }

    public void setByteTextRequiredBob(String byteTextRequiredBob) {
        this.byteTextRequiredBob = byteTextRequiredBob;
    }

    public String getByteTextMinbytesBob() {
        return byteTextMinbytesBob;
    }

    public FieldTestServiceClassEntity byteTextMinbytesBob(String byteTextMinbytesBob) {
        this.byteTextMinbytesBob = byteTextMinbytesBob;
        return this;
    }

    public void setByteTextMinbytesBob(String byteTextMinbytesBob) {
        this.byteTextMinbytesBob = byteTextMinbytesBob;
    }

    public String getByteTextMaxbytesBob() {
        return byteTextMaxbytesBob;
    }

    public FieldTestServiceClassEntity byteTextMaxbytesBob(String byteTextMaxbytesBob) {
        this.byteTextMaxbytesBob = byteTextMaxbytesBob;
        return this;
    }

    public void setByteTextMaxbytesBob(String byteTextMaxbytesBob) {
        this.byteTextMaxbytesBob = byteTextMaxbytesBob;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FieldTestServiceClassEntity fieldTestServiceClassEntity = (FieldTestServiceClassEntity) o;
        if (fieldTestServiceClassEntity.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), fieldTestServiceClassEntity.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "FieldTestServiceClassEntity{" +
            "id=" + getId() +
            ", stringBob='" + getStringBob() + "'" +
            ", stringRequiredBob='" + getStringRequiredBob() + "'" +
            ", stringMinlengthBob='" + getStringMinlengthBob() + "'" +
            ", stringMaxlengthBob='" + getStringMaxlengthBob() + "'" +
            ", stringPatternBob='" + getStringPatternBob() + "'" +
            ", integerBob='" + getIntegerBob() + "'" +
            ", integerRequiredBob='" + getIntegerRequiredBob() + "'" +
            ", integerMinBob='" + getIntegerMinBob() + "'" +
            ", integerMaxBob='" + getIntegerMaxBob() + "'" +
            ", longBob='" + getLongBob() + "'" +
            ", longRequiredBob='" + getLongRequiredBob() + "'" +
            ", longMinBob='" + getLongMinBob() + "'" +
            ", longMaxBob='" + getLongMaxBob() + "'" +
            ", floatBob='" + getFloatBob() + "'" +
            ", floatRequiredBob='" + getFloatRequiredBob() + "'" +
            ", floatMinBob='" + getFloatMinBob() + "'" +
            ", floatMaxBob='" + getFloatMaxBob() + "'" +
            ", doubleRequiredBob='" + getDoubleRequiredBob() + "'" +
            ", doubleMinBob='" + getDoubleMinBob() + "'" +
            ", doubleMaxBob='" + getDoubleMaxBob() + "'" +
            ", bigDecimalRequiredBob='" + getBigDecimalRequiredBob() + "'" +
            ", bigDecimalMinBob='" + getBigDecimalMinBob() + "'" +
            ", bigDecimalMaxBob='" + getBigDecimalMaxBob() + "'" +
            ", localDateBob='" + getLocalDateBob() + "'" +
            ", localDateRequiredBob='" + getLocalDateRequiredBob() + "'" +
            ", instantBob='" + getInstantBob() + "'" +
            ", instanteRequiredBob='" + getInstanteRequiredBob() + "'" +
            ", zonedDateTimeBob='" + getZonedDateTimeBob() + "'" +
            ", zonedDateTimeRequiredBob='" + getZonedDateTimeRequiredBob() + "'" +
            ", booleanBob='" + isBooleanBob() + "'" +
            ", booleanRequiredBob='" + isBooleanRequiredBob() + "'" +
            ", enumBob='" + getEnumBob() + "'" +
            ", enumRequiredBob='" + getEnumRequiredBob() + "'" +
            ", byteImageBob='" + getByteImageBob() + "'" +
            ", byteImageBobContentType='" + byteImageBobContentType + "'" +
            ", byteImageRequiredBob='" + getByteImageRequiredBob() + "'" +
            ", byteImageRequiredBobContentType='" + byteImageRequiredBobContentType + "'" +
            ", byteImageMinbytesBob='" + getByteImageMinbytesBob() + "'" +
            ", byteImageMinbytesBobContentType='" + byteImageMinbytesBobContentType + "'" +
            ", byteImageMaxbytesBob='" + getByteImageMaxbytesBob() + "'" +
            ", byteImageMaxbytesBobContentType='" + byteImageMaxbytesBobContentType + "'" +
            ", byteAnyBob='" + getByteAnyBob() + "'" +
            ", byteAnyBobContentType='" + byteAnyBobContentType + "'" +
            ", byteAnyRequiredBob='" + getByteAnyRequiredBob() + "'" +
            ", byteAnyRequiredBobContentType='" + byteAnyRequiredBobContentType + "'" +
            ", byteAnyMinbytesBob='" + getByteAnyMinbytesBob() + "'" +
            ", byteAnyMinbytesBobContentType='" + byteAnyMinbytesBobContentType + "'" +
            ", byteAnyMaxbytesBob='" + getByteAnyMaxbytesBob() + "'" +
            ", byteAnyMaxbytesBobContentType='" + byteAnyMaxbytesBobContentType + "'" +
            ", byteTextBob='" + getByteTextBob() + "'" +
            ", byteTextRequiredBob='" + getByteTextRequiredBob() + "'" +
            ", byteTextMinbytesBob='" + getByteTextMinbytesBob() + "'" +
            ", byteTextMaxbytesBob='" + getByteTextMaxbytesBob() + "'" +
            "}";
    }
}
