package com.mycompany.myapp.service.dto;


import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the EntityWithDTO entity.
 */
public class EntityWithDTODTO implements Serializable {

    private Long id;

    private String emma;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmma() {
        return emma;
    }

    public void setEmma(String emma) {
        this.emma = emma;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EntityWithDTODTO entityWithDTODTO = (EntityWithDTODTO) o;
        if(entityWithDTODTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), entityWithDTODTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EntityWithDTODTO{" +
            "id=" + getId() +
            ", emma='" + getEmma() + "'" +
            "}";
    }
}
