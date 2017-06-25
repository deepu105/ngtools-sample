package com.mycompany.myapp.service.dto;


import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the EntityWithServiceClassAndDTO entity.
 */
public class EntityWithServiceClassAndDTODTO implements Serializable {

    private Long id;

    private String lucas;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLucas() {
        return lucas;
    }

    public void setLucas(String lucas) {
        this.lucas = lucas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EntityWithServiceClassAndDTODTO entityWithServiceClassAndDTODTO = (EntityWithServiceClassAndDTODTO) o;
        if(entityWithServiceClassAndDTODTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), entityWithServiceClassAndDTODTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EntityWithServiceClassAndDTODTO{" +
            "id=" + getId() +
            ", lucas='" + getLucas() + "'" +
            "}";
    }
}
