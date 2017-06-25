package com.mycompany.myapp.service.dto;


import java.time.Instant;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;
import javax.persistence.Lob;
import com.mycompany.myapp.domain.enumeration.EnumFieldClass;
import com.mycompany.myapp.domain.enumeration.EnumRequiredFieldClass;

/**
 * A DTO for the FieldTestMapstructEntity entity.
 */
public class FieldTestMapstructEntityDTO implements Serializable {

    private Long id;

    private String stringEva;

    @NotNull
    private String stringRequiredEva;

    @Size(min = 0)
    private String stringMinlengthEva;

    @Size(max = 20)
    private String stringMaxlengthEva;

    @Pattern(regexp = "^[a-zA-Z0-9]*$")
    private String stringPatternEva;

    private Integer integerEva;

    @NotNull
    private Integer integerRequiredEva;

    @Min(value = 0)
    private Integer integerMinEva;

    @Max(value = 100)
    private Integer integerMaxEva;

    private Long longEva;

    @NotNull
    private Long longRequiredEva;

    @Min(value = 0)
    private Long longMinEva;

    @Max(value = 100)
    private Long longMaxEva;

    private Float floatEva;

    @NotNull
    private Float floatRequiredEva;

    @DecimalMin(value = "0")
    private Float floatMinEva;

    @DecimalMax(value = "100")
    private Float floatMaxEva;

    @NotNull
    private Double doubleRequiredEva;

    @DecimalMin(value = "0")
    private Double doubleMinEva;

    @DecimalMax(value = "100")
    private Double doubleMaxEva;

    @NotNull
    private BigDecimal bigDecimalRequiredEva;

    @DecimalMin(value = "0")
    private BigDecimal bigDecimalMinEva;

    @DecimalMax(value = "100")
    private BigDecimal bigDecimalMaxEva;

    private LocalDate localDateEva;

    @NotNull
    private LocalDate localDateRequiredEva;

    private Instant instantEva;

    @NotNull
    private Instant instanteRequiredEva;

    private ZonedDateTime zonedDateTimeEva;

    @NotNull
    private ZonedDateTime zonedDateTimeRequiredEva;

    private Boolean booleanEva;

    @NotNull
    private Boolean booleanRequiredEva;

    private EnumFieldClass enumEva;

    @NotNull
    private EnumRequiredFieldClass enumRequiredEva;

    @Lob
    private byte[] byteImageEva;
    private String byteImageEvaContentType;

    @NotNull
    @Lob
    private byte[] byteImageRequiredEva;
    private String byteImageRequiredEvaContentType;

    @Size(min = 0)
    @Lob
    private byte[] byteImageMinbytesEva;
    private String byteImageMinbytesEvaContentType;

    @Size(max = 10000)
    @Lob
    private byte[] byteImageMaxbytesEva;
    private String byteImageMaxbytesEvaContentType;

    @Lob
    private byte[] byteAnyEva;
    private String byteAnyEvaContentType;

    @NotNull
    @Lob
    private byte[] byteAnyRequiredEva;
    private String byteAnyRequiredEvaContentType;

    @Size(min = 0)
    @Lob
    private byte[] byteAnyMinbytesEva;
    private String byteAnyMinbytesEvaContentType;

    @Size(max = 10000)
    @Lob
    private byte[] byteAnyMaxbytesEva;
    private String byteAnyMaxbytesEvaContentType;

    @Lob
    private String byteTextEva;

    @NotNull
    @Lob
    private String byteTextRequiredEva;

    @Size(min = 0)
    @Lob
    private String byteTextMinbytesEva;

    @Size(max = 10000)
    @Lob
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

    public void setStringEva(String stringEva) {
        this.stringEva = stringEva;
    }

    public String getStringRequiredEva() {
        return stringRequiredEva;
    }

    public void setStringRequiredEva(String stringRequiredEva) {
        this.stringRequiredEva = stringRequiredEva;
    }

    public String getStringMinlengthEva() {
        return stringMinlengthEva;
    }

    public void setStringMinlengthEva(String stringMinlengthEva) {
        this.stringMinlengthEva = stringMinlengthEva;
    }

    public String getStringMaxlengthEva() {
        return stringMaxlengthEva;
    }

    public void setStringMaxlengthEva(String stringMaxlengthEva) {
        this.stringMaxlengthEva = stringMaxlengthEva;
    }

    public String getStringPatternEva() {
        return stringPatternEva;
    }

    public void setStringPatternEva(String stringPatternEva) {
        this.stringPatternEva = stringPatternEva;
    }

    public Integer getIntegerEva() {
        return integerEva;
    }

    public void setIntegerEva(Integer integerEva) {
        this.integerEva = integerEva;
    }

    public Integer getIntegerRequiredEva() {
        return integerRequiredEva;
    }

    public void setIntegerRequiredEva(Integer integerRequiredEva) {
        this.integerRequiredEva = integerRequiredEva;
    }

    public Integer getIntegerMinEva() {
        return integerMinEva;
    }

    public void setIntegerMinEva(Integer integerMinEva) {
        this.integerMinEva = integerMinEva;
    }

    public Integer getIntegerMaxEva() {
        return integerMaxEva;
    }

    public void setIntegerMaxEva(Integer integerMaxEva) {
        this.integerMaxEva = integerMaxEva;
    }

    public Long getLongEva() {
        return longEva;
    }

    public void setLongEva(Long longEva) {
        this.longEva = longEva;
    }

    public Long getLongRequiredEva() {
        return longRequiredEva;
    }

    public void setLongRequiredEva(Long longRequiredEva) {
        this.longRequiredEva = longRequiredEva;
    }

    public Long getLongMinEva() {
        return longMinEva;
    }

    public void setLongMinEva(Long longMinEva) {
        this.longMinEva = longMinEva;
    }

    public Long getLongMaxEva() {
        return longMaxEva;
    }

    public void setLongMaxEva(Long longMaxEva) {
        this.longMaxEva = longMaxEva;
    }

    public Float getFloatEva() {
        return floatEva;
    }

    public void setFloatEva(Float floatEva) {
        this.floatEva = floatEva;
    }

    public Float getFloatRequiredEva() {
        return floatRequiredEva;
    }

    public void setFloatRequiredEva(Float floatRequiredEva) {
        this.floatRequiredEva = floatRequiredEva;
    }

    public Float getFloatMinEva() {
        return floatMinEva;
    }

    public void setFloatMinEva(Float floatMinEva) {
        this.floatMinEva = floatMinEva;
    }

    public Float getFloatMaxEva() {
        return floatMaxEva;
    }

    public void setFloatMaxEva(Float floatMaxEva) {
        this.floatMaxEva = floatMaxEva;
    }

    public Double getDoubleRequiredEva() {
        return doubleRequiredEva;
    }

    public void setDoubleRequiredEva(Double doubleRequiredEva) {
        this.doubleRequiredEva = doubleRequiredEva;
    }

    public Double getDoubleMinEva() {
        return doubleMinEva;
    }

    public void setDoubleMinEva(Double doubleMinEva) {
        this.doubleMinEva = doubleMinEva;
    }

    public Double getDoubleMaxEva() {
        return doubleMaxEva;
    }

    public void setDoubleMaxEva(Double doubleMaxEva) {
        this.doubleMaxEva = doubleMaxEva;
    }

    public BigDecimal getBigDecimalRequiredEva() {
        return bigDecimalRequiredEva;
    }

    public void setBigDecimalRequiredEva(BigDecimal bigDecimalRequiredEva) {
        this.bigDecimalRequiredEva = bigDecimalRequiredEva;
    }

    public BigDecimal getBigDecimalMinEva() {
        return bigDecimalMinEva;
    }

    public void setBigDecimalMinEva(BigDecimal bigDecimalMinEva) {
        this.bigDecimalMinEva = bigDecimalMinEva;
    }

    public BigDecimal getBigDecimalMaxEva() {
        return bigDecimalMaxEva;
    }

    public void setBigDecimalMaxEva(BigDecimal bigDecimalMaxEva) {
        this.bigDecimalMaxEva = bigDecimalMaxEva;
    }

    public LocalDate getLocalDateEva() {
        return localDateEva;
    }

    public void setLocalDateEva(LocalDate localDateEva) {
        this.localDateEva = localDateEva;
    }

    public LocalDate getLocalDateRequiredEva() {
        return localDateRequiredEva;
    }

    public void setLocalDateRequiredEva(LocalDate localDateRequiredEva) {
        this.localDateRequiredEva = localDateRequiredEva;
    }

    public Instant getInstantEva() {
        return instantEva;
    }

    public void setInstantEva(Instant instantEva) {
        this.instantEva = instantEva;
    }

    public Instant getInstanteRequiredEva() {
        return instanteRequiredEva;
    }

    public void setInstanteRequiredEva(Instant instanteRequiredEva) {
        this.instanteRequiredEva = instanteRequiredEva;
    }

    public ZonedDateTime getZonedDateTimeEva() {
        return zonedDateTimeEva;
    }

    public void setZonedDateTimeEva(ZonedDateTime zonedDateTimeEva) {
        this.zonedDateTimeEva = zonedDateTimeEva;
    }

    public ZonedDateTime getZonedDateTimeRequiredEva() {
        return zonedDateTimeRequiredEva;
    }

    public void setZonedDateTimeRequiredEva(ZonedDateTime zonedDateTimeRequiredEva) {
        this.zonedDateTimeRequiredEva = zonedDateTimeRequiredEva;
    }

    public Boolean isBooleanEva() {
        return booleanEva;
    }

    public void setBooleanEva(Boolean booleanEva) {
        this.booleanEva = booleanEva;
    }

    public Boolean isBooleanRequiredEva() {
        return booleanRequiredEva;
    }

    public void setBooleanRequiredEva(Boolean booleanRequiredEva) {
        this.booleanRequiredEva = booleanRequiredEva;
    }

    public EnumFieldClass getEnumEva() {
        return enumEva;
    }

    public void setEnumEva(EnumFieldClass enumEva) {
        this.enumEva = enumEva;
    }

    public EnumRequiredFieldClass getEnumRequiredEva() {
        return enumRequiredEva;
    }

    public void setEnumRequiredEva(EnumRequiredFieldClass enumRequiredEva) {
        this.enumRequiredEva = enumRequiredEva;
    }

    public byte[] getByteImageEva() {
        return byteImageEva;
    }

    public void setByteImageEva(byte[] byteImageEva) {
        this.byteImageEva = byteImageEva;
    }

    public String getByteImageEvaContentType() {
        return byteImageEvaContentType;
    }

    public void setByteImageEvaContentType(String byteImageEvaContentType) {
        this.byteImageEvaContentType = byteImageEvaContentType;
    }

    public byte[] getByteImageRequiredEva() {
        return byteImageRequiredEva;
    }

    public void setByteImageRequiredEva(byte[] byteImageRequiredEva) {
        this.byteImageRequiredEva = byteImageRequiredEva;
    }

    public String getByteImageRequiredEvaContentType() {
        return byteImageRequiredEvaContentType;
    }

    public void setByteImageRequiredEvaContentType(String byteImageRequiredEvaContentType) {
        this.byteImageRequiredEvaContentType = byteImageRequiredEvaContentType;
    }

    public byte[] getByteImageMinbytesEva() {
        return byteImageMinbytesEva;
    }

    public void setByteImageMinbytesEva(byte[] byteImageMinbytesEva) {
        this.byteImageMinbytesEva = byteImageMinbytesEva;
    }

    public String getByteImageMinbytesEvaContentType() {
        return byteImageMinbytesEvaContentType;
    }

    public void setByteImageMinbytesEvaContentType(String byteImageMinbytesEvaContentType) {
        this.byteImageMinbytesEvaContentType = byteImageMinbytesEvaContentType;
    }

    public byte[] getByteImageMaxbytesEva() {
        return byteImageMaxbytesEva;
    }

    public void setByteImageMaxbytesEva(byte[] byteImageMaxbytesEva) {
        this.byteImageMaxbytesEva = byteImageMaxbytesEva;
    }

    public String getByteImageMaxbytesEvaContentType() {
        return byteImageMaxbytesEvaContentType;
    }

    public void setByteImageMaxbytesEvaContentType(String byteImageMaxbytesEvaContentType) {
        this.byteImageMaxbytesEvaContentType = byteImageMaxbytesEvaContentType;
    }

    public byte[] getByteAnyEva() {
        return byteAnyEva;
    }

    public void setByteAnyEva(byte[] byteAnyEva) {
        this.byteAnyEva = byteAnyEva;
    }

    public String getByteAnyEvaContentType() {
        return byteAnyEvaContentType;
    }

    public void setByteAnyEvaContentType(String byteAnyEvaContentType) {
        this.byteAnyEvaContentType = byteAnyEvaContentType;
    }

    public byte[] getByteAnyRequiredEva() {
        return byteAnyRequiredEva;
    }

    public void setByteAnyRequiredEva(byte[] byteAnyRequiredEva) {
        this.byteAnyRequiredEva = byteAnyRequiredEva;
    }

    public String getByteAnyRequiredEvaContentType() {
        return byteAnyRequiredEvaContentType;
    }

    public void setByteAnyRequiredEvaContentType(String byteAnyRequiredEvaContentType) {
        this.byteAnyRequiredEvaContentType = byteAnyRequiredEvaContentType;
    }

    public byte[] getByteAnyMinbytesEva() {
        return byteAnyMinbytesEva;
    }

    public void setByteAnyMinbytesEva(byte[] byteAnyMinbytesEva) {
        this.byteAnyMinbytesEva = byteAnyMinbytesEva;
    }

    public String getByteAnyMinbytesEvaContentType() {
        return byteAnyMinbytesEvaContentType;
    }

    public void setByteAnyMinbytesEvaContentType(String byteAnyMinbytesEvaContentType) {
        this.byteAnyMinbytesEvaContentType = byteAnyMinbytesEvaContentType;
    }

    public byte[] getByteAnyMaxbytesEva() {
        return byteAnyMaxbytesEva;
    }

    public void setByteAnyMaxbytesEva(byte[] byteAnyMaxbytesEva) {
        this.byteAnyMaxbytesEva = byteAnyMaxbytesEva;
    }

    public String getByteAnyMaxbytesEvaContentType() {
        return byteAnyMaxbytesEvaContentType;
    }

    public void setByteAnyMaxbytesEvaContentType(String byteAnyMaxbytesEvaContentType) {
        this.byteAnyMaxbytesEvaContentType = byteAnyMaxbytesEvaContentType;
    }

    public String getByteTextEva() {
        return byteTextEva;
    }

    public void setByteTextEva(String byteTextEva) {
        this.byteTextEva = byteTextEva;
    }

    public String getByteTextRequiredEva() {
        return byteTextRequiredEva;
    }

    public void setByteTextRequiredEva(String byteTextRequiredEva) {
        this.byteTextRequiredEva = byteTextRequiredEva;
    }

    public String getByteTextMinbytesEva() {
        return byteTextMinbytesEva;
    }

    public void setByteTextMinbytesEva(String byteTextMinbytesEva) {
        this.byteTextMinbytesEva = byteTextMinbytesEva;
    }

    public String getByteTextMaxbytesEva() {
        return byteTextMaxbytesEva;
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

        FieldTestMapstructEntityDTO fieldTestMapstructEntityDTO = (FieldTestMapstructEntityDTO) o;
        if(fieldTestMapstructEntityDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), fieldTestMapstructEntityDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "FieldTestMapstructEntityDTO{" +
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
            ", byteImageRequiredEva='" + getByteImageRequiredEva() + "'" +
            ", byteImageMinbytesEva='" + getByteImageMinbytesEva() + "'" +
            ", byteImageMaxbytesEva='" + getByteImageMaxbytesEva() + "'" +
            ", byteAnyEva='" + getByteAnyEva() + "'" +
            ", byteAnyRequiredEva='" + getByteAnyRequiredEva() + "'" +
            ", byteAnyMinbytesEva='" + getByteAnyMinbytesEva() + "'" +
            ", byteAnyMaxbytesEva='" + getByteAnyMaxbytesEva() + "'" +
            ", byteTextEva='" + getByteTextEva() + "'" +
            ", byteTextRequiredEva='" + getByteTextRequiredEva() + "'" +
            ", byteTextMinbytesEva='" + getByteTextMinbytesEva() + "'" +
            ", byteTextMaxbytesEva='" + getByteTextMaxbytesEva() + "'" +
            "}";
    }
}
