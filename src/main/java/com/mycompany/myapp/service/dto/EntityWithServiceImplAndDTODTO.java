package com.mycompany.myapp.service.dto;


import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the EntityWithServiceImplAndDTO entity.
 */
public class EntityWithServiceImplAndDTODTO implements Serializable {

    private Long id;

    private String louis;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLouis() {
        return louis;
    }

    public void setLouis(String louis) {
        this.louis = louis;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EntityWithServiceImplAndDTODTO entityWithServiceImplAndDTODTO = (EntityWithServiceImplAndDTODTO) o;
        if(entityWithServiceImplAndDTODTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), entityWithServiceImplAndDTODTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EntityWithServiceImplAndDTODTO{" +
            "id=" + getId() +
            ", louis='" + getLouis() + "'" +
            "}";
    }
}
