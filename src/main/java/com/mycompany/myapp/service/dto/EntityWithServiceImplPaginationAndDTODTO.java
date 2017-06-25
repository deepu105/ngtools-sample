package com.mycompany.myapp.service.dto;


import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the EntityWithServiceImplPaginationAndDTO entity.
 */
public class EntityWithServiceImplPaginationAndDTODTO implements Serializable {

    private Long id;

    private String theo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTheo() {
        return theo;
    }

    public void setTheo(String theo) {
        this.theo = theo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EntityWithServiceImplPaginationAndDTODTO entityWithServiceImplPaginationAndDTODTO = (EntityWithServiceImplPaginationAndDTODTO) o;
        if(entityWithServiceImplPaginationAndDTODTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), entityWithServiceImplPaginationAndDTODTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EntityWithServiceImplPaginationAndDTODTO{" +
            "id=" + getId() +
            ", theo='" + getTheo() + "'" +
            "}";
    }
}
