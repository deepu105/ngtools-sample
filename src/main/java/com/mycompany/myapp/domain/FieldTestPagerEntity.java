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
 * A FieldTestPagerEntity.
 */
@Entity
@Table(name = "field_test_pager_entity")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "fieldtestpagerentity")
public class FieldTestPagerEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "string_jade")
    private String stringJade;

    @NotNull
    @Column(name = "string_required_jade", nullable = false)
    private String stringRequiredJade;

    @Size(min = 0)
    @Column(name = "string_minlength_jade")
    private String stringMinlengthJade;

    @Size(max = 20)
    @Column(name = "string_maxlength_jade", length = 20)
    private String stringMaxlengthJade;

    @Pattern(regexp = "^[a-zA-Z0-9]*$")
    @Column(name = "string_pattern_jade")
    private String stringPatternJade;

    @Column(name = "integer_jade")
    private Integer integerJade;

    @NotNull
    @Column(name = "integer_required_jade", nullable = false)
    private Integer integerRequiredJade;

    @Min(value = 0)
    @Column(name = "integer_min_jade")
    private Integer integerMinJade;

    @Max(value = 100)
    @Column(name = "integer_max_jade")
    private Integer integerMaxJade;

    @Column(name = "long_jade")
    private Long longJade;

    @NotNull
    @Column(name = "long_required_jade", nullable = false)
    private Long longRequiredJade;

    @Min(value = 0)
    @Column(name = "long_min_jade")
    private Long longMinJade;

    @Max(value = 100)
    @Column(name = "long_max_jade")
    private Long longMaxJade;

    @Column(name = "float_jade")
    private Float floatJade;

    @NotNull
    @Column(name = "float_required_jade", nullable = false)
    private Float floatRequiredJade;

    @DecimalMin(value = "0")
    @Column(name = "float_min_jade")
    private Float floatMinJade;

    @DecimalMax(value = "100")
    @Column(name = "float_max_jade")
    private Float floatMaxJade;

    @NotNull
    @Column(name = "double_required_jade", nullable = false)
    private Double doubleRequiredJade;

    @DecimalMin(value = "0")
    @Column(name = "double_min_jade")
    private Double doubleMinJade;

    @DecimalMax(value = "100")
    @Column(name = "double_max_jade")
    private Double doubleMaxJade;

    @NotNull
    @Column(name = "big_decimal_required_jade", precision=10, scale=2, nullable = false)
    private BigDecimal bigDecimalRequiredJade;

    @DecimalMin(value = "0")
    @Column(name = "big_decimal_min_jade", precision=10, scale=2)
    private BigDecimal bigDecimalMinJade;

    @DecimalMax(value = "100")
    @Column(name = "big_decimal_max_jade", precision=10, scale=2)
    private BigDecimal bigDecimalMaxJade;

    @Column(name = "local_date_jade")
    private LocalDate localDateJade;

    @NotNull
    @Column(name = "local_date_required_jade", nullable = false)
    private LocalDate localDateRequiredJade;

    @Column(name = "instant_jade")
    private Instant instantJade;

    @NotNull
    @Column(name = "instante_required_jade", nullable = false)
    private Instant instanteRequiredJade;

    @Column(name = "zoned_date_time_jade")
    private ZonedDateTime zonedDateTimeJade;

    @NotNull
    @Column(name = "zoned_date_time_required_jade", nullable = false)
    private ZonedDateTime zonedDateTimeRequiredJade;

    @Column(name = "boolean_jade")
    private Boolean booleanJade;

    @NotNull
    @Column(name = "boolean_required_jade", nullable = false)
    private Boolean booleanRequiredJade;

    @Enumerated(EnumType.STRING)
    @Column(name = "enum_jade")
    private EnumFieldClass enumJade;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "enum_required_jade", nullable = false)
    private EnumRequiredFieldClass enumRequiredJade;

    @Lob
    @Column(name = "byte_image_jade")
    private byte[] byteImageJade;

    @Column(name = "byte_image_jade_content_type")
    private String byteImageJadeContentType;

    @NotNull
    @Lob
    @Column(name = "byte_image_required_jade", nullable = false)
    private byte[] byteImageRequiredJade;

    @Column(name = "byte_image_required_jade_content_type", nullable = false)
    private String byteImageRequiredJadeContentType;

    @Size(min = 0)
    @Lob
    @Column(name = "byte_image_minbytes_jade")
    private byte[] byteImageMinbytesJade;

    @Column(name = "byte_image_minbytes_jade_content_type")
    private String byteImageMinbytesJadeContentType;

    @Size(max = 10000)
    @Lob
    @Column(name = "byte_image_maxbytes_jade")
    private byte[] byteImageMaxbytesJade;

    @Column(name = "byte_image_maxbytes_jade_content_type")
    private String byteImageMaxbytesJadeContentType;

    @Lob
    @Column(name = "byte_any_jade")
    private byte[] byteAnyJade;

    @Column(name = "byte_any_jade_content_type")
    private String byteAnyJadeContentType;

    @NotNull
    @Lob
    @Column(name = "byte_any_required_jade", nullable = false)
    private byte[] byteAnyRequiredJade;

    @Column(name = "byte_any_required_jade_content_type", nullable = false)
    private String byteAnyRequiredJadeContentType;

    @Size(min = 0)
    @Lob
    @Column(name = "byte_any_minbytes_jade")
    private byte[] byteAnyMinbytesJade;

    @Column(name = "byte_any_minbytes_jade_content_type")
    private String byteAnyMinbytesJadeContentType;

    @Size(max = 10000)
    @Lob
    @Column(name = "byte_any_maxbytes_jade")
    private byte[] byteAnyMaxbytesJade;

    @Column(name = "byte_any_maxbytes_jade_content_type")
    private String byteAnyMaxbytesJadeContentType;

    @Lob
    @Column(name = "byte_text_jade")
    private String byteTextJade;

    @NotNull
    @Lob
    @Column(name = "byte_text_required_jade", nullable = false)
    private String byteTextRequiredJade;

    @Size(min = 0)
    @Lob
    @Column(name = "byte_text_minbytes_jade")
    private String byteTextMinbytesJade;

    @Size(max = 10000)
    @Lob
    @Column(name = "byte_text_maxbytes_jade")
    private String byteTextMaxbytesJade;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStringJade() {
        return stringJade;
    }

    public FieldTestPagerEntity stringJade(String stringJade) {
        this.stringJade = stringJade;
        return this;
    }

    public void setStringJade(String stringJade) {
        this.stringJade = stringJade;
    }

    public String getStringRequiredJade() {
        return stringRequiredJade;
    }

    public FieldTestPagerEntity stringRequiredJade(String stringRequiredJade) {
        this.stringRequiredJade = stringRequiredJade;
        return this;
    }

    public void setStringRequiredJade(String stringRequiredJade) {
        this.stringRequiredJade = stringRequiredJade;
    }

    public String getStringMinlengthJade() {
        return stringMinlengthJade;
    }

    public FieldTestPagerEntity stringMinlengthJade(String stringMinlengthJade) {
        this.stringMinlengthJade = stringMinlengthJade;
        return this;
    }

    public void setStringMinlengthJade(String stringMinlengthJade) {
        this.stringMinlengthJade = stringMinlengthJade;
    }

    public String getStringMaxlengthJade() {
        return stringMaxlengthJade;
    }

    public FieldTestPagerEntity stringMaxlengthJade(String stringMaxlengthJade) {
        this.stringMaxlengthJade = stringMaxlengthJade;
        return this;
    }

    public void setStringMaxlengthJade(String stringMaxlengthJade) {
        this.stringMaxlengthJade = stringMaxlengthJade;
    }

    public String getStringPatternJade() {
        return stringPatternJade;
    }

    public FieldTestPagerEntity stringPatternJade(String stringPatternJade) {
        this.stringPatternJade = stringPatternJade;
        return this;
    }

    public void setStringPatternJade(String stringPatternJade) {
        this.stringPatternJade = stringPatternJade;
    }

    public Integer getIntegerJade() {
        return integerJade;
    }

    public FieldTestPagerEntity integerJade(Integer integerJade) {
        this.integerJade = integerJade;
        return this;
    }

    public void setIntegerJade(Integer integerJade) {
        this.integerJade = integerJade;
    }

    public Integer getIntegerRequiredJade() {
        return integerRequiredJade;
    }

    public FieldTestPagerEntity integerRequiredJade(Integer integerRequiredJade) {
        this.integerRequiredJade = integerRequiredJade;
        return this;
    }

    public void setIntegerRequiredJade(Integer integerRequiredJade) {
        this.integerRequiredJade = integerRequiredJade;
    }

    public Integer getIntegerMinJade() {
        return integerMinJade;
    }

    public FieldTestPagerEntity integerMinJade(Integer integerMinJade) {
        this.integerMinJade = integerMinJade;
        return this;
    }

    public void setIntegerMinJade(Integer integerMinJade) {
        this.integerMinJade = integerMinJade;
    }

    public Integer getIntegerMaxJade() {
        return integerMaxJade;
    }

    public FieldTestPagerEntity integerMaxJade(Integer integerMaxJade) {
        this.integerMaxJade = integerMaxJade;
        return this;
    }

    public void setIntegerMaxJade(Integer integerMaxJade) {
        this.integerMaxJade = integerMaxJade;
    }

    public Long getLongJade() {
        return longJade;
    }

    public FieldTestPagerEntity longJade(Long longJade) {
        this.longJade = longJade;
        return this;
    }

    public void setLongJade(Long longJade) {
        this.longJade = longJade;
    }

    public Long getLongRequiredJade() {
        return longRequiredJade;
    }

    public FieldTestPagerEntity longRequiredJade(Long longRequiredJade) {
        this.longRequiredJade = longRequiredJade;
        return this;
    }

    public void setLongRequiredJade(Long longRequiredJade) {
        this.longRequiredJade = longRequiredJade;
    }

    public Long getLongMinJade() {
        return longMinJade;
    }

    public FieldTestPagerEntity longMinJade(Long longMinJade) {
        this.longMinJade = longMinJade;
        return this;
    }

    public void setLongMinJade(Long longMinJade) {
        this.longMinJade = longMinJade;
    }

    public Long getLongMaxJade() {
        return longMaxJade;
    }

    public FieldTestPagerEntity longMaxJade(Long longMaxJade) {
        this.longMaxJade = longMaxJade;
        return this;
    }

    public void setLongMaxJade(Long longMaxJade) {
        this.longMaxJade = longMaxJade;
    }

    public Float getFloatJade() {
        return floatJade;
    }

    public FieldTestPagerEntity floatJade(Float floatJade) {
        this.floatJade = floatJade;
        return this;
    }

    public void setFloatJade(Float floatJade) {
        this.floatJade = floatJade;
    }

    public Float getFloatRequiredJade() {
        return floatRequiredJade;
    }

    public FieldTestPagerEntity floatRequiredJade(Float floatRequiredJade) {
        this.floatRequiredJade = floatRequiredJade;
        return this;
    }

    public void setFloatRequiredJade(Float floatRequiredJade) {
        this.floatRequiredJade = floatRequiredJade;
    }

    public Float getFloatMinJade() {
        return floatMinJade;
    }

    public FieldTestPagerEntity floatMinJade(Float floatMinJade) {
        this.floatMinJade = floatMinJade;
        return this;
    }

    public void setFloatMinJade(Float floatMinJade) {
        this.floatMinJade = floatMinJade;
    }

    public Float getFloatMaxJade() {
        return floatMaxJade;
    }

    public FieldTestPagerEntity floatMaxJade(Float floatMaxJade) {
        this.floatMaxJade = floatMaxJade;
        return this;
    }

    public void setFloatMaxJade(Float floatMaxJade) {
        this.floatMaxJade = floatMaxJade;
    }

    public Double getDoubleRequiredJade() {
        return doubleRequiredJade;
    }

    public FieldTestPagerEntity doubleRequiredJade(Double doubleRequiredJade) {
        this.doubleRequiredJade = doubleRequiredJade;
        return this;
    }

    public void setDoubleRequiredJade(Double doubleRequiredJade) {
        this.doubleRequiredJade = doubleRequiredJade;
    }

    public Double getDoubleMinJade() {
        return doubleMinJade;
    }

    public FieldTestPagerEntity doubleMinJade(Double doubleMinJade) {
        this.doubleMinJade = doubleMinJade;
        return this;
    }

    public void setDoubleMinJade(Double doubleMinJade) {
        this.doubleMinJade = doubleMinJade;
    }

    public Double getDoubleMaxJade() {
        return doubleMaxJade;
    }

    public FieldTestPagerEntity doubleMaxJade(Double doubleMaxJade) {
        this.doubleMaxJade = doubleMaxJade;
        return this;
    }

    public void setDoubleMaxJade(Double doubleMaxJade) {
        this.doubleMaxJade = doubleMaxJade;
    }

    public BigDecimal getBigDecimalRequiredJade() {
        return bigDecimalRequiredJade;
    }

    public FieldTestPagerEntity bigDecimalRequiredJade(BigDecimal bigDecimalRequiredJade) {
        this.bigDecimalRequiredJade = bigDecimalRequiredJade;
        return this;
    }

    public void setBigDecimalRequiredJade(BigDecimal bigDecimalRequiredJade) {
        this.bigDecimalRequiredJade = bigDecimalRequiredJade;
    }

    public BigDecimal getBigDecimalMinJade() {
        return bigDecimalMinJade;
    }

    public FieldTestPagerEntity bigDecimalMinJade(BigDecimal bigDecimalMinJade) {
        this.bigDecimalMinJade = bigDecimalMinJade;
        return this;
    }

    public void setBigDecimalMinJade(BigDecimal bigDecimalMinJade) {
        this.bigDecimalMinJade = bigDecimalMinJade;
    }

    public BigDecimal getBigDecimalMaxJade() {
        return bigDecimalMaxJade;
    }

    public FieldTestPagerEntity bigDecimalMaxJade(BigDecimal bigDecimalMaxJade) {
        this.bigDecimalMaxJade = bigDecimalMaxJade;
        return this;
    }

    public void setBigDecimalMaxJade(BigDecimal bigDecimalMaxJade) {
        this.bigDecimalMaxJade = bigDecimalMaxJade;
    }

    public LocalDate getLocalDateJade() {
        return localDateJade;
    }

    public FieldTestPagerEntity localDateJade(LocalDate localDateJade) {
        this.localDateJade = localDateJade;
        return this;
    }

    public void setLocalDateJade(LocalDate localDateJade) {
        this.localDateJade = localDateJade;
    }

    public LocalDate getLocalDateRequiredJade() {
        return localDateRequiredJade;
    }

    public FieldTestPagerEntity localDateRequiredJade(LocalDate localDateRequiredJade) {
        this.localDateRequiredJade = localDateRequiredJade;
        return this;
    }

    public void setLocalDateRequiredJade(LocalDate localDateRequiredJade) {
        this.localDateRequiredJade = localDateRequiredJade;
    }

    public Instant getInstantJade() {
        return instantJade;
    }

    public FieldTestPagerEntity instantJade(Instant instantJade) {
        this.instantJade = instantJade;
        return this;
    }

    public void setInstantJade(Instant instantJade) {
        this.instantJade = instantJade;
    }

    public Instant getInstanteRequiredJade() {
        return instanteRequiredJade;
    }

    public FieldTestPagerEntity instanteRequiredJade(Instant instanteRequiredJade) {
        this.instanteRequiredJade = instanteRequiredJade;
        return this;
    }

    public void setInstanteRequiredJade(Instant instanteRequiredJade) {
        this.instanteRequiredJade = instanteRequiredJade;
    }

    public ZonedDateTime getZonedDateTimeJade() {
        return zonedDateTimeJade;
    }

    public FieldTestPagerEntity zonedDateTimeJade(ZonedDateTime zonedDateTimeJade) {
        this.zonedDateTimeJade = zonedDateTimeJade;
        return this;
    }

    public void setZonedDateTimeJade(ZonedDateTime zonedDateTimeJade) {
        this.zonedDateTimeJade = zonedDateTimeJade;
    }

    public ZonedDateTime getZonedDateTimeRequiredJade() {
        return zonedDateTimeRequiredJade;
    }

    public FieldTestPagerEntity zonedDateTimeRequiredJade(ZonedDateTime zonedDateTimeRequiredJade) {
        this.zonedDateTimeRequiredJade = zonedDateTimeRequiredJade;
        return this;
    }

    public void setZonedDateTimeRequiredJade(ZonedDateTime zonedDateTimeRequiredJade) {
        this.zonedDateTimeRequiredJade = zonedDateTimeRequiredJade;
    }

    public Boolean isBooleanJade() {
        return booleanJade;
    }

    public FieldTestPagerEntity booleanJade(Boolean booleanJade) {
        this.booleanJade = booleanJade;
        return this;
    }

    public void setBooleanJade(Boolean booleanJade) {
        this.booleanJade = booleanJade;
    }

    public Boolean isBooleanRequiredJade() {
        return booleanRequiredJade;
    }

    public FieldTestPagerEntity booleanRequiredJade(Boolean booleanRequiredJade) {
        this.booleanRequiredJade = booleanRequiredJade;
        return this;
    }

    public void setBooleanRequiredJade(Boolean booleanRequiredJade) {
        this.booleanRequiredJade = booleanRequiredJade;
    }

    public EnumFieldClass getEnumJade() {
        return enumJade;
    }

    public FieldTestPagerEntity enumJade(EnumFieldClass enumJade) {
        this.enumJade = enumJade;
        return this;
    }

    public void setEnumJade(EnumFieldClass enumJade) {
        this.enumJade = enumJade;
    }

    public EnumRequiredFieldClass getEnumRequiredJade() {
        return enumRequiredJade;
    }

    public FieldTestPagerEntity enumRequiredJade(EnumRequiredFieldClass enumRequiredJade) {
        this.enumRequiredJade = enumRequiredJade;
        return this;
    }

    public void setEnumRequiredJade(EnumRequiredFieldClass enumRequiredJade) {
        this.enumRequiredJade = enumRequiredJade;
    }

    public byte[] getByteImageJade() {
        return byteImageJade;
    }

    public FieldTestPagerEntity byteImageJade(byte[] byteImageJade) {
        this.byteImageJade = byteImageJade;
        return this;
    }

    public void setByteImageJade(byte[] byteImageJade) {
        this.byteImageJade = byteImageJade;
    }

    public String getByteImageJadeContentType() {
        return byteImageJadeContentType;
    }

    public FieldTestPagerEntity byteImageJadeContentType(String byteImageJadeContentType) {
        this.byteImageJadeContentType = byteImageJadeContentType;
        return this;
    }

    public void setByteImageJadeContentType(String byteImageJadeContentType) {
        this.byteImageJadeContentType = byteImageJadeContentType;
    }

    public byte[] getByteImageRequiredJade() {
        return byteImageRequiredJade;
    }

    public FieldTestPagerEntity byteImageRequiredJade(byte[] byteImageRequiredJade) {
        this.byteImageRequiredJade = byteImageRequiredJade;
        return this;
    }

    public void setByteImageRequiredJade(byte[] byteImageRequiredJade) {
        this.byteImageRequiredJade = byteImageRequiredJade;
    }

    public String getByteImageRequiredJadeContentType() {
        return byteImageRequiredJadeContentType;
    }

    public FieldTestPagerEntity byteImageRequiredJadeContentType(String byteImageRequiredJadeContentType) {
        this.byteImageRequiredJadeContentType = byteImageRequiredJadeContentType;
        return this;
    }

    public void setByteImageRequiredJadeContentType(String byteImageRequiredJadeContentType) {
        this.byteImageRequiredJadeContentType = byteImageRequiredJadeContentType;
    }

    public byte[] getByteImageMinbytesJade() {
        return byteImageMinbytesJade;
    }

    public FieldTestPagerEntity byteImageMinbytesJade(byte[] byteImageMinbytesJade) {
        this.byteImageMinbytesJade = byteImageMinbytesJade;
        return this;
    }

    public void setByteImageMinbytesJade(byte[] byteImageMinbytesJade) {
        this.byteImageMinbytesJade = byteImageMinbytesJade;
    }

    public String getByteImageMinbytesJadeContentType() {
        return byteImageMinbytesJadeContentType;
    }

    public FieldTestPagerEntity byteImageMinbytesJadeContentType(String byteImageMinbytesJadeContentType) {
        this.byteImageMinbytesJadeContentType = byteImageMinbytesJadeContentType;
        return this;
    }

    public void setByteImageMinbytesJadeContentType(String byteImageMinbytesJadeContentType) {
        this.byteImageMinbytesJadeContentType = byteImageMinbytesJadeContentType;
    }

    public byte[] getByteImageMaxbytesJade() {
        return byteImageMaxbytesJade;
    }

    public FieldTestPagerEntity byteImageMaxbytesJade(byte[] byteImageMaxbytesJade) {
        this.byteImageMaxbytesJade = byteImageMaxbytesJade;
        return this;
    }

    public void setByteImageMaxbytesJade(byte[] byteImageMaxbytesJade) {
        this.byteImageMaxbytesJade = byteImageMaxbytesJade;
    }

    public String getByteImageMaxbytesJadeContentType() {
        return byteImageMaxbytesJadeContentType;
    }

    public FieldTestPagerEntity byteImageMaxbytesJadeContentType(String byteImageMaxbytesJadeContentType) {
        this.byteImageMaxbytesJadeContentType = byteImageMaxbytesJadeContentType;
        return this;
    }

    public void setByteImageMaxbytesJadeContentType(String byteImageMaxbytesJadeContentType) {
        this.byteImageMaxbytesJadeContentType = byteImageMaxbytesJadeContentType;
    }

    public byte[] getByteAnyJade() {
        return byteAnyJade;
    }

    public FieldTestPagerEntity byteAnyJade(byte[] byteAnyJade) {
        this.byteAnyJade = byteAnyJade;
        return this;
    }

    public void setByteAnyJade(byte[] byteAnyJade) {
        this.byteAnyJade = byteAnyJade;
    }

    public String getByteAnyJadeContentType() {
        return byteAnyJadeContentType;
    }

    public FieldTestPagerEntity byteAnyJadeContentType(String byteAnyJadeContentType) {
        this.byteAnyJadeContentType = byteAnyJadeContentType;
        return this;
    }

    public void setByteAnyJadeContentType(String byteAnyJadeContentType) {
        this.byteAnyJadeContentType = byteAnyJadeContentType;
    }

    public byte[] getByteAnyRequiredJade() {
        return byteAnyRequiredJade;
    }

    public FieldTestPagerEntity byteAnyRequiredJade(byte[] byteAnyRequiredJade) {
        this.byteAnyRequiredJade = byteAnyRequiredJade;
        return this;
    }

    public void setByteAnyRequiredJade(byte[] byteAnyRequiredJade) {
        this.byteAnyRequiredJade = byteAnyRequiredJade;
    }

    public String getByteAnyRequiredJadeContentType() {
        return byteAnyRequiredJadeContentType;
    }

    public FieldTestPagerEntity byteAnyRequiredJadeContentType(String byteAnyRequiredJadeContentType) {
        this.byteAnyRequiredJadeContentType = byteAnyRequiredJadeContentType;
        return this;
    }

    public void setByteAnyRequiredJadeContentType(String byteAnyRequiredJadeContentType) {
        this.byteAnyRequiredJadeContentType = byteAnyRequiredJadeContentType;
    }

    public byte[] getByteAnyMinbytesJade() {
        return byteAnyMinbytesJade;
    }

    public FieldTestPagerEntity byteAnyMinbytesJade(byte[] byteAnyMinbytesJade) {
        this.byteAnyMinbytesJade = byteAnyMinbytesJade;
        return this;
    }

    public void setByteAnyMinbytesJade(byte[] byteAnyMinbytesJade) {
        this.byteAnyMinbytesJade = byteAnyMinbytesJade;
    }

    public String getByteAnyMinbytesJadeContentType() {
        return byteAnyMinbytesJadeContentType;
    }

    public FieldTestPagerEntity byteAnyMinbytesJadeContentType(String byteAnyMinbytesJadeContentType) {
        this.byteAnyMinbytesJadeContentType = byteAnyMinbytesJadeContentType;
        return this;
    }

    public void setByteAnyMinbytesJadeContentType(String byteAnyMinbytesJadeContentType) {
        this.byteAnyMinbytesJadeContentType = byteAnyMinbytesJadeContentType;
    }

    public byte[] getByteAnyMaxbytesJade() {
        return byteAnyMaxbytesJade;
    }

    public FieldTestPagerEntity byteAnyMaxbytesJade(byte[] byteAnyMaxbytesJade) {
        this.byteAnyMaxbytesJade = byteAnyMaxbytesJade;
        return this;
    }

    public void setByteAnyMaxbytesJade(byte[] byteAnyMaxbytesJade) {
        this.byteAnyMaxbytesJade = byteAnyMaxbytesJade;
    }

    public String getByteAnyMaxbytesJadeContentType() {
        return byteAnyMaxbytesJadeContentType;
    }

    public FieldTestPagerEntity byteAnyMaxbytesJadeContentType(String byteAnyMaxbytesJadeContentType) {
        this.byteAnyMaxbytesJadeContentType = byteAnyMaxbytesJadeContentType;
        return this;
    }

    public void setByteAnyMaxbytesJadeContentType(String byteAnyMaxbytesJadeContentType) {
        this.byteAnyMaxbytesJadeContentType = byteAnyMaxbytesJadeContentType;
    }

    public String getByteTextJade() {
        return byteTextJade;
    }

    public FieldTestPagerEntity byteTextJade(String byteTextJade) {
        this.byteTextJade = byteTextJade;
        return this;
    }

    public void setByteTextJade(String byteTextJade) {
        this.byteTextJade = byteTextJade;
    }

    public String getByteTextRequiredJade() {
        return byteTextRequiredJade;
    }

    public FieldTestPagerEntity byteTextRequiredJade(String byteTextRequiredJade) {
        this.byteTextRequiredJade = byteTextRequiredJade;
        return this;
    }

    public void setByteTextRequiredJade(String byteTextRequiredJade) {
        this.byteTextRequiredJade = byteTextRequiredJade;
    }

    public String getByteTextMinbytesJade() {
        return byteTextMinbytesJade;
    }

    public FieldTestPagerEntity byteTextMinbytesJade(String byteTextMinbytesJade) {
        this.byteTextMinbytesJade = byteTextMinbytesJade;
        return this;
    }

    public void setByteTextMinbytesJade(String byteTextMinbytesJade) {
        this.byteTextMinbytesJade = byteTextMinbytesJade;
    }

    public String getByteTextMaxbytesJade() {
        return byteTextMaxbytesJade;
    }

    public FieldTestPagerEntity byteTextMaxbytesJade(String byteTextMaxbytesJade) {
        this.byteTextMaxbytesJade = byteTextMaxbytesJade;
        return this;
    }

    public void setByteTextMaxbytesJade(String byteTextMaxbytesJade) {
        this.byteTextMaxbytesJade = byteTextMaxbytesJade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FieldTestPagerEntity fieldTestPagerEntity = (FieldTestPagerEntity) o;
        if (fieldTestPagerEntity.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), fieldTestPagerEntity.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "FieldTestPagerEntity{" +
            "id=" + getId() +
            ", stringJade='" + getStringJade() + "'" +
            ", stringRequiredJade='" + getStringRequiredJade() + "'" +
            ", stringMinlengthJade='" + getStringMinlengthJade() + "'" +
            ", stringMaxlengthJade='" + getStringMaxlengthJade() + "'" +
            ", stringPatternJade='" + getStringPatternJade() + "'" +
            ", integerJade='" + getIntegerJade() + "'" +
            ", integerRequiredJade='" + getIntegerRequiredJade() + "'" +
            ", integerMinJade='" + getIntegerMinJade() + "'" +
            ", integerMaxJade='" + getIntegerMaxJade() + "'" +
            ", longJade='" + getLongJade() + "'" +
            ", longRequiredJade='" + getLongRequiredJade() + "'" +
            ", longMinJade='" + getLongMinJade() + "'" +
            ", longMaxJade='" + getLongMaxJade() + "'" +
            ", floatJade='" + getFloatJade() + "'" +
            ", floatRequiredJade='" + getFloatRequiredJade() + "'" +
            ", floatMinJade='" + getFloatMinJade() + "'" +
            ", floatMaxJade='" + getFloatMaxJade() + "'" +
            ", doubleRequiredJade='" + getDoubleRequiredJade() + "'" +
            ", doubleMinJade='" + getDoubleMinJade() + "'" +
            ", doubleMaxJade='" + getDoubleMaxJade() + "'" +
            ", bigDecimalRequiredJade='" + getBigDecimalRequiredJade() + "'" +
            ", bigDecimalMinJade='" + getBigDecimalMinJade() + "'" +
            ", bigDecimalMaxJade='" + getBigDecimalMaxJade() + "'" +
            ", localDateJade='" + getLocalDateJade() + "'" +
            ", localDateRequiredJade='" + getLocalDateRequiredJade() + "'" +
            ", instantJade='" + getInstantJade() + "'" +
            ", instanteRequiredJade='" + getInstanteRequiredJade() + "'" +
            ", zonedDateTimeJade='" + getZonedDateTimeJade() + "'" +
            ", zonedDateTimeRequiredJade='" + getZonedDateTimeRequiredJade() + "'" +
            ", booleanJade='" + isBooleanJade() + "'" +
            ", booleanRequiredJade='" + isBooleanRequiredJade() + "'" +
            ", enumJade='" + getEnumJade() + "'" +
            ", enumRequiredJade='" + getEnumRequiredJade() + "'" +
            ", byteImageJade='" + getByteImageJade() + "'" +
            ", byteImageJadeContentType='" + byteImageJadeContentType + "'" +
            ", byteImageRequiredJade='" + getByteImageRequiredJade() + "'" +
            ", byteImageRequiredJadeContentType='" + byteImageRequiredJadeContentType + "'" +
            ", byteImageMinbytesJade='" + getByteImageMinbytesJade() + "'" +
            ", byteImageMinbytesJadeContentType='" + byteImageMinbytesJadeContentType + "'" +
            ", byteImageMaxbytesJade='" + getByteImageMaxbytesJade() + "'" +
            ", byteImageMaxbytesJadeContentType='" + byteImageMaxbytesJadeContentType + "'" +
            ", byteAnyJade='" + getByteAnyJade() + "'" +
            ", byteAnyJadeContentType='" + byteAnyJadeContentType + "'" +
            ", byteAnyRequiredJade='" + getByteAnyRequiredJade() + "'" +
            ", byteAnyRequiredJadeContentType='" + byteAnyRequiredJadeContentType + "'" +
            ", byteAnyMinbytesJade='" + getByteAnyMinbytesJade() + "'" +
            ", byteAnyMinbytesJadeContentType='" + byteAnyMinbytesJadeContentType + "'" +
            ", byteAnyMaxbytesJade='" + getByteAnyMaxbytesJade() + "'" +
            ", byteAnyMaxbytesJadeContentType='" + byteAnyMaxbytesJadeContentType + "'" +
            ", byteTextJade='" + getByteTextJade() + "'" +
            ", byteTextRequiredJade='" + getByteTextRequiredJade() + "'" +
            ", byteTextMinbytesJade='" + getByteTextMinbytesJade() + "'" +
            ", byteTextMaxbytesJade='" + getByteTextMaxbytesJade() + "'" +
            "}";
    }
}
