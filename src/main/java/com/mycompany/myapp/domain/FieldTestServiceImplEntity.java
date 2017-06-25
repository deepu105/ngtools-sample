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
 * A FieldTestServiceImplEntity.
 */
@Entity
@Table(name = "field_test_service_impl_entity")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "fieldtestserviceimplentity")
public class FieldTestServiceImplEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "string_mika")
    private String stringMika;

    @NotNull
    @Column(name = "string_required_mika", nullable = false)
    private String stringRequiredMika;

    @Size(min = 0)
    @Column(name = "string_minlength_mika")
    private String stringMinlengthMika;

    @Size(max = 20)
    @Column(name = "string_maxlength_mika", length = 20)
    private String stringMaxlengthMika;

    @Pattern(regexp = "^[a-zA-Z0-9]*$")
    @Column(name = "string_pattern_mika")
    private String stringPatternMika;

    @Column(name = "integer_mika")
    private Integer integerMika;

    @NotNull
    @Column(name = "integer_required_mika", nullable = false)
    private Integer integerRequiredMika;

    @Min(value = 0)
    @Column(name = "integer_min_mika")
    private Integer integerMinMika;

    @Max(value = 100)
    @Column(name = "integer_max_mika")
    private Integer integerMaxMika;

    @Column(name = "long_mika")
    private Long longMika;

    @NotNull
    @Column(name = "long_required_mika", nullable = false)
    private Long longRequiredMika;

    @Min(value = 0)
    @Column(name = "long_min_mika")
    private Long longMinMika;

    @Max(value = 100)
    @Column(name = "long_max_mika")
    private Long longMaxMika;

    @Column(name = "float_mika")
    private Float floatMika;

    @NotNull
    @Column(name = "float_required_mika", nullable = false)
    private Float floatRequiredMika;

    @DecimalMin(value = "0")
    @Column(name = "float_min_mika")
    private Float floatMinMika;

    @DecimalMax(value = "100")
    @Column(name = "float_max_mika")
    private Float floatMaxMika;

    @NotNull
    @Column(name = "double_required_mika", nullable = false)
    private Double doubleRequiredMika;

    @DecimalMin(value = "0")
    @Column(name = "double_min_mika")
    private Double doubleMinMika;

    @DecimalMax(value = "100")
    @Column(name = "double_max_mika")
    private Double doubleMaxMika;

    @NotNull
    @Column(name = "big_decimal_required_mika", precision=10, scale=2, nullable = false)
    private BigDecimal bigDecimalRequiredMika;

    @DecimalMin(value = "0")
    @Column(name = "big_decimal_min_mika", precision=10, scale=2)
    private BigDecimal bigDecimalMinMika;

    @DecimalMax(value = "100")
    @Column(name = "big_decimal_max_mika", precision=10, scale=2)
    private BigDecimal bigDecimalMaxMika;

    @Column(name = "local_date_mika")
    private LocalDate localDateMika;

    @NotNull
    @Column(name = "local_date_required_mika", nullable = false)
    private LocalDate localDateRequiredMika;

    @Column(name = "instant_mika")
    private Instant instantMika;

    @NotNull
    @Column(name = "instante_required_mika", nullable = false)
    private Instant instanteRequiredMika;

    @Column(name = "zoned_date_time_mika")
    private ZonedDateTime zonedDateTimeMika;

    @NotNull
    @Column(name = "zoned_date_time_required_mika", nullable = false)
    private ZonedDateTime zonedDateTimeRequiredMika;

    @Column(name = "boolean_mika")
    private Boolean booleanMika;

    @NotNull
    @Column(name = "boolean_required_mika", nullable = false)
    private Boolean booleanRequiredMika;

    @Enumerated(EnumType.STRING)
    @Column(name = "enum_mika")
    private EnumFieldClass enumMika;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "enum_required_mika", nullable = false)
    private EnumRequiredFieldClass enumRequiredMika;

    @Lob
    @Column(name = "byte_image_mika")
    private byte[] byteImageMika;

    @Column(name = "byte_image_mika_content_type")
    private String byteImageMikaContentType;

    @NotNull
    @Lob
    @Column(name = "byte_image_required_mika", nullable = false)
    private byte[] byteImageRequiredMika;

    @Column(name = "byte_image_required_mika_content_type", nullable = false)
    private String byteImageRequiredMikaContentType;

    @Size(min = 0)
    @Lob
    @Column(name = "byte_image_minbytes_mika")
    private byte[] byteImageMinbytesMika;

    @Column(name = "byte_image_minbytes_mika_content_type")
    private String byteImageMinbytesMikaContentType;

    @Size(max = 10000)
    @Lob
    @Column(name = "byte_image_maxbytes_mika")
    private byte[] byteImageMaxbytesMika;

    @Column(name = "byte_image_maxbytes_mika_content_type")
    private String byteImageMaxbytesMikaContentType;

    @Lob
    @Column(name = "byte_any_mika")
    private byte[] byteAnyMika;

    @Column(name = "byte_any_mika_content_type")
    private String byteAnyMikaContentType;

    @NotNull
    @Lob
    @Column(name = "byte_any_required_mika", nullable = false)
    private byte[] byteAnyRequiredMika;

    @Column(name = "byte_any_required_mika_content_type", nullable = false)
    private String byteAnyRequiredMikaContentType;

    @Size(min = 0)
    @Lob
    @Column(name = "byte_any_minbytes_mika")
    private byte[] byteAnyMinbytesMika;

    @Column(name = "byte_any_minbytes_mika_content_type")
    private String byteAnyMinbytesMikaContentType;

    @Size(max = 10000)
    @Lob
    @Column(name = "byte_any_maxbytes_mika")
    private byte[] byteAnyMaxbytesMika;

    @Column(name = "byte_any_maxbytes_mika_content_type")
    private String byteAnyMaxbytesMikaContentType;

    @Lob
    @Column(name = "byte_text_mika")
    private String byteTextMika;

    @NotNull
    @Lob
    @Column(name = "byte_text_required_mika", nullable = false)
    private String byteTextRequiredMika;

    @Size(min = 0)
    @Lob
    @Column(name = "byte_text_minbytes_mika")
    private String byteTextMinbytesMika;

    @Size(max = 10000)
    @Lob
    @Column(name = "byte_text_maxbytes_mika")
    private String byteTextMaxbytesMika;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStringMika() {
        return stringMika;
    }

    public FieldTestServiceImplEntity stringMika(String stringMika) {
        this.stringMika = stringMika;
        return this;
    }

    public void setStringMika(String stringMika) {
        this.stringMika = stringMika;
    }

    public String getStringRequiredMika() {
        return stringRequiredMika;
    }

    public FieldTestServiceImplEntity stringRequiredMika(String stringRequiredMika) {
        this.stringRequiredMika = stringRequiredMika;
        return this;
    }

    public void setStringRequiredMika(String stringRequiredMika) {
        this.stringRequiredMika = stringRequiredMika;
    }

    public String getStringMinlengthMika() {
        return stringMinlengthMika;
    }

    public FieldTestServiceImplEntity stringMinlengthMika(String stringMinlengthMika) {
        this.stringMinlengthMika = stringMinlengthMika;
        return this;
    }

    public void setStringMinlengthMika(String stringMinlengthMika) {
        this.stringMinlengthMika = stringMinlengthMika;
    }

    public String getStringMaxlengthMika() {
        return stringMaxlengthMika;
    }

    public FieldTestServiceImplEntity stringMaxlengthMika(String stringMaxlengthMika) {
        this.stringMaxlengthMika = stringMaxlengthMika;
        return this;
    }

    public void setStringMaxlengthMika(String stringMaxlengthMika) {
        this.stringMaxlengthMika = stringMaxlengthMika;
    }

    public String getStringPatternMika() {
        return stringPatternMika;
    }

    public FieldTestServiceImplEntity stringPatternMika(String stringPatternMika) {
        this.stringPatternMika = stringPatternMika;
        return this;
    }

    public void setStringPatternMika(String stringPatternMika) {
        this.stringPatternMika = stringPatternMika;
    }

    public Integer getIntegerMika() {
        return integerMika;
    }

    public FieldTestServiceImplEntity integerMika(Integer integerMika) {
        this.integerMika = integerMika;
        return this;
    }

    public void setIntegerMika(Integer integerMika) {
        this.integerMika = integerMika;
    }

    public Integer getIntegerRequiredMika() {
        return integerRequiredMika;
    }

    public FieldTestServiceImplEntity integerRequiredMika(Integer integerRequiredMika) {
        this.integerRequiredMika = integerRequiredMika;
        return this;
    }

    public void setIntegerRequiredMika(Integer integerRequiredMika) {
        this.integerRequiredMika = integerRequiredMika;
    }

    public Integer getIntegerMinMika() {
        return integerMinMika;
    }

    public FieldTestServiceImplEntity integerMinMika(Integer integerMinMika) {
        this.integerMinMika = integerMinMika;
        return this;
    }

    public void setIntegerMinMika(Integer integerMinMika) {
        this.integerMinMika = integerMinMika;
    }

    public Integer getIntegerMaxMika() {
        return integerMaxMika;
    }

    public FieldTestServiceImplEntity integerMaxMika(Integer integerMaxMika) {
        this.integerMaxMika = integerMaxMika;
        return this;
    }

    public void setIntegerMaxMika(Integer integerMaxMika) {
        this.integerMaxMika = integerMaxMika;
    }

    public Long getLongMika() {
        return longMika;
    }

    public FieldTestServiceImplEntity longMika(Long longMika) {
        this.longMika = longMika;
        return this;
    }

    public void setLongMika(Long longMika) {
        this.longMika = longMika;
    }

    public Long getLongRequiredMika() {
        return longRequiredMika;
    }

    public FieldTestServiceImplEntity longRequiredMika(Long longRequiredMika) {
        this.longRequiredMika = longRequiredMika;
        return this;
    }

    public void setLongRequiredMika(Long longRequiredMika) {
        this.longRequiredMika = longRequiredMika;
    }

    public Long getLongMinMika() {
        return longMinMika;
    }

    public FieldTestServiceImplEntity longMinMika(Long longMinMika) {
        this.longMinMika = longMinMika;
        return this;
    }

    public void setLongMinMika(Long longMinMika) {
        this.longMinMika = longMinMika;
    }

    public Long getLongMaxMika() {
        return longMaxMika;
    }

    public FieldTestServiceImplEntity longMaxMika(Long longMaxMika) {
        this.longMaxMika = longMaxMika;
        return this;
    }

    public void setLongMaxMika(Long longMaxMika) {
        this.longMaxMika = longMaxMika;
    }

    public Float getFloatMika() {
        return floatMika;
    }

    public FieldTestServiceImplEntity floatMika(Float floatMika) {
        this.floatMika = floatMika;
        return this;
    }

    public void setFloatMika(Float floatMika) {
        this.floatMika = floatMika;
    }

    public Float getFloatRequiredMika() {
        return floatRequiredMika;
    }

    public FieldTestServiceImplEntity floatRequiredMika(Float floatRequiredMika) {
        this.floatRequiredMika = floatRequiredMika;
        return this;
    }

    public void setFloatRequiredMika(Float floatRequiredMika) {
        this.floatRequiredMika = floatRequiredMika;
    }

    public Float getFloatMinMika() {
        return floatMinMika;
    }

    public FieldTestServiceImplEntity floatMinMika(Float floatMinMika) {
        this.floatMinMika = floatMinMika;
        return this;
    }

    public void setFloatMinMika(Float floatMinMika) {
        this.floatMinMika = floatMinMika;
    }

    public Float getFloatMaxMika() {
        return floatMaxMika;
    }

    public FieldTestServiceImplEntity floatMaxMika(Float floatMaxMika) {
        this.floatMaxMika = floatMaxMika;
        return this;
    }

    public void setFloatMaxMika(Float floatMaxMika) {
        this.floatMaxMika = floatMaxMika;
    }

    public Double getDoubleRequiredMika() {
        return doubleRequiredMika;
    }

    public FieldTestServiceImplEntity doubleRequiredMika(Double doubleRequiredMika) {
        this.doubleRequiredMika = doubleRequiredMika;
        return this;
    }

    public void setDoubleRequiredMika(Double doubleRequiredMika) {
        this.doubleRequiredMika = doubleRequiredMika;
    }

    public Double getDoubleMinMika() {
        return doubleMinMika;
    }

    public FieldTestServiceImplEntity doubleMinMika(Double doubleMinMika) {
        this.doubleMinMika = doubleMinMika;
        return this;
    }

    public void setDoubleMinMika(Double doubleMinMika) {
        this.doubleMinMika = doubleMinMika;
    }

    public Double getDoubleMaxMika() {
        return doubleMaxMika;
    }

    public FieldTestServiceImplEntity doubleMaxMika(Double doubleMaxMika) {
        this.doubleMaxMika = doubleMaxMika;
        return this;
    }

    public void setDoubleMaxMika(Double doubleMaxMika) {
        this.doubleMaxMika = doubleMaxMika;
    }

    public BigDecimal getBigDecimalRequiredMika() {
        return bigDecimalRequiredMika;
    }

    public FieldTestServiceImplEntity bigDecimalRequiredMika(BigDecimal bigDecimalRequiredMika) {
        this.bigDecimalRequiredMika = bigDecimalRequiredMika;
        return this;
    }

    public void setBigDecimalRequiredMika(BigDecimal bigDecimalRequiredMika) {
        this.bigDecimalRequiredMika = bigDecimalRequiredMika;
    }

    public BigDecimal getBigDecimalMinMika() {
        return bigDecimalMinMika;
    }

    public FieldTestServiceImplEntity bigDecimalMinMika(BigDecimal bigDecimalMinMika) {
        this.bigDecimalMinMika = bigDecimalMinMika;
        return this;
    }

    public void setBigDecimalMinMika(BigDecimal bigDecimalMinMika) {
        this.bigDecimalMinMika = bigDecimalMinMika;
    }

    public BigDecimal getBigDecimalMaxMika() {
        return bigDecimalMaxMika;
    }

    public FieldTestServiceImplEntity bigDecimalMaxMika(BigDecimal bigDecimalMaxMika) {
        this.bigDecimalMaxMika = bigDecimalMaxMika;
        return this;
    }

    public void setBigDecimalMaxMika(BigDecimal bigDecimalMaxMika) {
        this.bigDecimalMaxMika = bigDecimalMaxMika;
    }

    public LocalDate getLocalDateMika() {
        return localDateMika;
    }

    public FieldTestServiceImplEntity localDateMika(LocalDate localDateMika) {
        this.localDateMika = localDateMika;
        return this;
    }

    public void setLocalDateMika(LocalDate localDateMika) {
        this.localDateMika = localDateMika;
    }

    public LocalDate getLocalDateRequiredMika() {
        return localDateRequiredMika;
    }

    public FieldTestServiceImplEntity localDateRequiredMika(LocalDate localDateRequiredMika) {
        this.localDateRequiredMika = localDateRequiredMika;
        return this;
    }

    public void setLocalDateRequiredMika(LocalDate localDateRequiredMika) {
        this.localDateRequiredMika = localDateRequiredMika;
    }

    public Instant getInstantMika() {
        return instantMika;
    }

    public FieldTestServiceImplEntity instantMika(Instant instantMika) {
        this.instantMika = instantMika;
        return this;
    }

    public void setInstantMika(Instant instantMika) {
        this.instantMika = instantMika;
    }

    public Instant getInstanteRequiredMika() {
        return instanteRequiredMika;
    }

    public FieldTestServiceImplEntity instanteRequiredMika(Instant instanteRequiredMika) {
        this.instanteRequiredMika = instanteRequiredMika;
        return this;
    }

    public void setInstanteRequiredMika(Instant instanteRequiredMika) {
        this.instanteRequiredMika = instanteRequiredMika;
    }

    public ZonedDateTime getZonedDateTimeMika() {
        return zonedDateTimeMika;
    }

    public FieldTestServiceImplEntity zonedDateTimeMika(ZonedDateTime zonedDateTimeMika) {
        this.zonedDateTimeMika = zonedDateTimeMika;
        return this;
    }

    public void setZonedDateTimeMika(ZonedDateTime zonedDateTimeMika) {
        this.zonedDateTimeMika = zonedDateTimeMika;
    }

    public ZonedDateTime getZonedDateTimeRequiredMika() {
        return zonedDateTimeRequiredMika;
    }

    public FieldTestServiceImplEntity zonedDateTimeRequiredMika(ZonedDateTime zonedDateTimeRequiredMika) {
        this.zonedDateTimeRequiredMika = zonedDateTimeRequiredMika;
        return this;
    }

    public void setZonedDateTimeRequiredMika(ZonedDateTime zonedDateTimeRequiredMika) {
        this.zonedDateTimeRequiredMika = zonedDateTimeRequiredMika;
    }

    public Boolean isBooleanMika() {
        return booleanMika;
    }

    public FieldTestServiceImplEntity booleanMika(Boolean booleanMika) {
        this.booleanMika = booleanMika;
        return this;
    }

    public void setBooleanMika(Boolean booleanMika) {
        this.booleanMika = booleanMika;
    }

    public Boolean isBooleanRequiredMika() {
        return booleanRequiredMika;
    }

    public FieldTestServiceImplEntity booleanRequiredMika(Boolean booleanRequiredMika) {
        this.booleanRequiredMika = booleanRequiredMika;
        return this;
    }

    public void setBooleanRequiredMika(Boolean booleanRequiredMika) {
        this.booleanRequiredMika = booleanRequiredMika;
    }

    public EnumFieldClass getEnumMika() {
        return enumMika;
    }

    public FieldTestServiceImplEntity enumMika(EnumFieldClass enumMika) {
        this.enumMika = enumMika;
        return this;
    }

    public void setEnumMika(EnumFieldClass enumMika) {
        this.enumMika = enumMika;
    }

    public EnumRequiredFieldClass getEnumRequiredMika() {
        return enumRequiredMika;
    }

    public FieldTestServiceImplEntity enumRequiredMika(EnumRequiredFieldClass enumRequiredMika) {
        this.enumRequiredMika = enumRequiredMika;
        return this;
    }

    public void setEnumRequiredMika(EnumRequiredFieldClass enumRequiredMika) {
        this.enumRequiredMika = enumRequiredMika;
    }

    public byte[] getByteImageMika() {
        return byteImageMika;
    }

    public FieldTestServiceImplEntity byteImageMika(byte[] byteImageMika) {
        this.byteImageMika = byteImageMika;
        return this;
    }

    public void setByteImageMika(byte[] byteImageMika) {
        this.byteImageMika = byteImageMika;
    }

    public String getByteImageMikaContentType() {
        return byteImageMikaContentType;
    }

    public FieldTestServiceImplEntity byteImageMikaContentType(String byteImageMikaContentType) {
        this.byteImageMikaContentType = byteImageMikaContentType;
        return this;
    }

    public void setByteImageMikaContentType(String byteImageMikaContentType) {
        this.byteImageMikaContentType = byteImageMikaContentType;
    }

    public byte[] getByteImageRequiredMika() {
        return byteImageRequiredMika;
    }

    public FieldTestServiceImplEntity byteImageRequiredMika(byte[] byteImageRequiredMika) {
        this.byteImageRequiredMika = byteImageRequiredMika;
        return this;
    }

    public void setByteImageRequiredMika(byte[] byteImageRequiredMika) {
        this.byteImageRequiredMika = byteImageRequiredMika;
    }

    public String getByteImageRequiredMikaContentType() {
        return byteImageRequiredMikaContentType;
    }

    public FieldTestServiceImplEntity byteImageRequiredMikaContentType(String byteImageRequiredMikaContentType) {
        this.byteImageRequiredMikaContentType = byteImageRequiredMikaContentType;
        return this;
    }

    public void setByteImageRequiredMikaContentType(String byteImageRequiredMikaContentType) {
        this.byteImageRequiredMikaContentType = byteImageRequiredMikaContentType;
    }

    public byte[] getByteImageMinbytesMika() {
        return byteImageMinbytesMika;
    }

    public FieldTestServiceImplEntity byteImageMinbytesMika(byte[] byteImageMinbytesMika) {
        this.byteImageMinbytesMika = byteImageMinbytesMika;
        return this;
    }

    public void setByteImageMinbytesMika(byte[] byteImageMinbytesMika) {
        this.byteImageMinbytesMika = byteImageMinbytesMika;
    }

    public String getByteImageMinbytesMikaContentType() {
        return byteImageMinbytesMikaContentType;
    }

    public FieldTestServiceImplEntity byteImageMinbytesMikaContentType(String byteImageMinbytesMikaContentType) {
        this.byteImageMinbytesMikaContentType = byteImageMinbytesMikaContentType;
        return this;
    }

    public void setByteImageMinbytesMikaContentType(String byteImageMinbytesMikaContentType) {
        this.byteImageMinbytesMikaContentType = byteImageMinbytesMikaContentType;
    }

    public byte[] getByteImageMaxbytesMika() {
        return byteImageMaxbytesMika;
    }

    public FieldTestServiceImplEntity byteImageMaxbytesMika(byte[] byteImageMaxbytesMika) {
        this.byteImageMaxbytesMika = byteImageMaxbytesMika;
        return this;
    }

    public void setByteImageMaxbytesMika(byte[] byteImageMaxbytesMika) {
        this.byteImageMaxbytesMika = byteImageMaxbytesMika;
    }

    public String getByteImageMaxbytesMikaContentType() {
        return byteImageMaxbytesMikaContentType;
    }

    public FieldTestServiceImplEntity byteImageMaxbytesMikaContentType(String byteImageMaxbytesMikaContentType) {
        this.byteImageMaxbytesMikaContentType = byteImageMaxbytesMikaContentType;
        return this;
    }

    public void setByteImageMaxbytesMikaContentType(String byteImageMaxbytesMikaContentType) {
        this.byteImageMaxbytesMikaContentType = byteImageMaxbytesMikaContentType;
    }

    public byte[] getByteAnyMika() {
        return byteAnyMika;
    }

    public FieldTestServiceImplEntity byteAnyMika(byte[] byteAnyMika) {
        this.byteAnyMika = byteAnyMika;
        return this;
    }

    public void setByteAnyMika(byte[] byteAnyMika) {
        this.byteAnyMika = byteAnyMika;
    }

    public String getByteAnyMikaContentType() {
        return byteAnyMikaContentType;
    }

    public FieldTestServiceImplEntity byteAnyMikaContentType(String byteAnyMikaContentType) {
        this.byteAnyMikaContentType = byteAnyMikaContentType;
        return this;
    }

    public void setByteAnyMikaContentType(String byteAnyMikaContentType) {
        this.byteAnyMikaContentType = byteAnyMikaContentType;
    }

    public byte[] getByteAnyRequiredMika() {
        return byteAnyRequiredMika;
    }

    public FieldTestServiceImplEntity byteAnyRequiredMika(byte[] byteAnyRequiredMika) {
        this.byteAnyRequiredMika = byteAnyRequiredMika;
        return this;
    }

    public void setByteAnyRequiredMika(byte[] byteAnyRequiredMika) {
        this.byteAnyRequiredMika = byteAnyRequiredMika;
    }

    public String getByteAnyRequiredMikaContentType() {
        return byteAnyRequiredMikaContentType;
    }

    public FieldTestServiceImplEntity byteAnyRequiredMikaContentType(String byteAnyRequiredMikaContentType) {
        this.byteAnyRequiredMikaContentType = byteAnyRequiredMikaContentType;
        return this;
    }

    public void setByteAnyRequiredMikaContentType(String byteAnyRequiredMikaContentType) {
        this.byteAnyRequiredMikaContentType = byteAnyRequiredMikaContentType;
    }

    public byte[] getByteAnyMinbytesMika() {
        return byteAnyMinbytesMika;
    }

    public FieldTestServiceImplEntity byteAnyMinbytesMika(byte[] byteAnyMinbytesMika) {
        this.byteAnyMinbytesMika = byteAnyMinbytesMika;
        return this;
    }

    public void setByteAnyMinbytesMika(byte[] byteAnyMinbytesMika) {
        this.byteAnyMinbytesMika = byteAnyMinbytesMika;
    }

    public String getByteAnyMinbytesMikaContentType() {
        return byteAnyMinbytesMikaContentType;
    }

    public FieldTestServiceImplEntity byteAnyMinbytesMikaContentType(String byteAnyMinbytesMikaContentType) {
        this.byteAnyMinbytesMikaContentType = byteAnyMinbytesMikaContentType;
        return this;
    }

    public void setByteAnyMinbytesMikaContentType(String byteAnyMinbytesMikaContentType) {
        this.byteAnyMinbytesMikaContentType = byteAnyMinbytesMikaContentType;
    }

    public byte[] getByteAnyMaxbytesMika() {
        return byteAnyMaxbytesMika;
    }

    public FieldTestServiceImplEntity byteAnyMaxbytesMika(byte[] byteAnyMaxbytesMika) {
        this.byteAnyMaxbytesMika = byteAnyMaxbytesMika;
        return this;
    }

    public void setByteAnyMaxbytesMika(byte[] byteAnyMaxbytesMika) {
        this.byteAnyMaxbytesMika = byteAnyMaxbytesMika;
    }

    public String getByteAnyMaxbytesMikaContentType() {
        return byteAnyMaxbytesMikaContentType;
    }

    public FieldTestServiceImplEntity byteAnyMaxbytesMikaContentType(String byteAnyMaxbytesMikaContentType) {
        this.byteAnyMaxbytesMikaContentType = byteAnyMaxbytesMikaContentType;
        return this;
    }

    public void setByteAnyMaxbytesMikaContentType(String byteAnyMaxbytesMikaContentType) {
        this.byteAnyMaxbytesMikaContentType = byteAnyMaxbytesMikaContentType;
    }

    public String getByteTextMika() {
        return byteTextMika;
    }

    public FieldTestServiceImplEntity byteTextMika(String byteTextMika) {
        this.byteTextMika = byteTextMika;
        return this;
    }

    public void setByteTextMika(String byteTextMika) {
        this.byteTextMika = byteTextMika;
    }

    public String getByteTextRequiredMika() {
        return byteTextRequiredMika;
    }

    public FieldTestServiceImplEntity byteTextRequiredMika(String byteTextRequiredMika) {
        this.byteTextRequiredMika = byteTextRequiredMika;
        return this;
    }

    public void setByteTextRequiredMika(String byteTextRequiredMika) {
        this.byteTextRequiredMika = byteTextRequiredMika;
    }

    public String getByteTextMinbytesMika() {
        return byteTextMinbytesMika;
    }

    public FieldTestServiceImplEntity byteTextMinbytesMika(String byteTextMinbytesMika) {
        this.byteTextMinbytesMika = byteTextMinbytesMika;
        return this;
    }

    public void setByteTextMinbytesMika(String byteTextMinbytesMika) {
        this.byteTextMinbytesMika = byteTextMinbytesMika;
    }

    public String getByteTextMaxbytesMika() {
        return byteTextMaxbytesMika;
    }

    public FieldTestServiceImplEntity byteTextMaxbytesMika(String byteTextMaxbytesMika) {
        this.byteTextMaxbytesMika = byteTextMaxbytesMika;
        return this;
    }

    public void setByteTextMaxbytesMika(String byteTextMaxbytesMika) {
        this.byteTextMaxbytesMika = byteTextMaxbytesMika;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FieldTestServiceImplEntity fieldTestServiceImplEntity = (FieldTestServiceImplEntity) o;
        if (fieldTestServiceImplEntity.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), fieldTestServiceImplEntity.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "FieldTestServiceImplEntity{" +
            "id=" + getId() +
            ", stringMika='" + getStringMika() + "'" +
            ", stringRequiredMika='" + getStringRequiredMika() + "'" +
            ", stringMinlengthMika='" + getStringMinlengthMika() + "'" +
            ", stringMaxlengthMika='" + getStringMaxlengthMika() + "'" +
            ", stringPatternMika='" + getStringPatternMika() + "'" +
            ", integerMika='" + getIntegerMika() + "'" +
            ", integerRequiredMika='" + getIntegerRequiredMika() + "'" +
            ", integerMinMika='" + getIntegerMinMika() + "'" +
            ", integerMaxMika='" + getIntegerMaxMika() + "'" +
            ", longMika='" + getLongMika() + "'" +
            ", longRequiredMika='" + getLongRequiredMika() + "'" +
            ", longMinMika='" + getLongMinMika() + "'" +
            ", longMaxMika='" + getLongMaxMika() + "'" +
            ", floatMika='" + getFloatMika() + "'" +
            ", floatRequiredMika='" + getFloatRequiredMika() + "'" +
            ", floatMinMika='" + getFloatMinMika() + "'" +
            ", floatMaxMika='" + getFloatMaxMika() + "'" +
            ", doubleRequiredMika='" + getDoubleRequiredMika() + "'" +
            ", doubleMinMika='" + getDoubleMinMika() + "'" +
            ", doubleMaxMika='" + getDoubleMaxMika() + "'" +
            ", bigDecimalRequiredMika='" + getBigDecimalRequiredMika() + "'" +
            ", bigDecimalMinMika='" + getBigDecimalMinMika() + "'" +
            ", bigDecimalMaxMika='" + getBigDecimalMaxMika() + "'" +
            ", localDateMika='" + getLocalDateMika() + "'" +
            ", localDateRequiredMika='" + getLocalDateRequiredMika() + "'" +
            ", instantMika='" + getInstantMika() + "'" +
            ", instanteRequiredMika='" + getInstanteRequiredMika() + "'" +
            ", zonedDateTimeMika='" + getZonedDateTimeMika() + "'" +
            ", zonedDateTimeRequiredMika='" + getZonedDateTimeRequiredMika() + "'" +
            ", booleanMika='" + isBooleanMika() + "'" +
            ", booleanRequiredMika='" + isBooleanRequiredMika() + "'" +
            ", enumMika='" + getEnumMika() + "'" +
            ", enumRequiredMika='" + getEnumRequiredMika() + "'" +
            ", byteImageMika='" + getByteImageMika() + "'" +
            ", byteImageMikaContentType='" + byteImageMikaContentType + "'" +
            ", byteImageRequiredMika='" + getByteImageRequiredMika() + "'" +
            ", byteImageRequiredMikaContentType='" + byteImageRequiredMikaContentType + "'" +
            ", byteImageMinbytesMika='" + getByteImageMinbytesMika() + "'" +
            ", byteImageMinbytesMikaContentType='" + byteImageMinbytesMikaContentType + "'" +
            ", byteImageMaxbytesMika='" + getByteImageMaxbytesMika() + "'" +
            ", byteImageMaxbytesMikaContentType='" + byteImageMaxbytesMikaContentType + "'" +
            ", byteAnyMika='" + getByteAnyMika() + "'" +
            ", byteAnyMikaContentType='" + byteAnyMikaContentType + "'" +
            ", byteAnyRequiredMika='" + getByteAnyRequiredMika() + "'" +
            ", byteAnyRequiredMikaContentType='" + byteAnyRequiredMikaContentType + "'" +
            ", byteAnyMinbytesMika='" + getByteAnyMinbytesMika() + "'" +
            ", byteAnyMinbytesMikaContentType='" + byteAnyMinbytesMikaContentType + "'" +
            ", byteAnyMaxbytesMika='" + getByteAnyMaxbytesMika() + "'" +
            ", byteAnyMaxbytesMikaContentType='" + byteAnyMaxbytesMikaContentType + "'" +
            ", byteTextMika='" + getByteTextMika() + "'" +
            ", byteTextRequiredMika='" + getByteTextRequiredMika() + "'" +
            ", byteTextMinbytesMika='" + getByteTextMinbytesMika() + "'" +
            ", byteTextMaxbytesMika='" + getByteTextMaxbytesMika() + "'" +
            "}";
    }
}
