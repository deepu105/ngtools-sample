package com.mycompany.myapp.service.dto;


import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the EntityWithServiceClassPaginationAndDTO entity.
 */
public class EntityWithServiceClassPaginationAndDTODTO implements Serializable {

    private Long id;

    private String lena;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLena() {
        return lena;
    }

    public void setLena(String lena) {
        this.lena = lena;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EntityWithServiceClassPaginationAndDTODTO entityWithServiceClassPaginationAndDTODTO = (EntityWithServiceClassPaginationAndDTODTO) o;
        if(entityWithServiceClassPaginationAndDTODTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), entityWithServiceClassPaginationAndDTODTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EntityWithServiceClassPaginationAndDTODTO{" +
            "id=" + getId() +
            ", lena='" + getLena() + "'" +
            "}";
    }
}
