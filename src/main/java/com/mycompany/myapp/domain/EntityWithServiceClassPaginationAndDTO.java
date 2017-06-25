package com.mycompany.myapp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A EntityWithServiceClassPaginationAndDTO.
 */
@Entity
@Table(name = "entity_with_service_class_pagination_and_dto")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "entitywithserviceclasspaginationanddto")
public class EntityWithServiceClassPaginationAndDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "lena")
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

    public EntityWithServiceClassPaginationAndDTO lena(String lena) {
        this.lena = lena;
        return this;
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
        EntityWithServiceClassPaginationAndDTO entityWithServiceClassPaginationAndDTO = (EntityWithServiceClassPaginationAndDTO) o;
        if (entityWithServiceClassPaginationAndDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), entityWithServiceClassPaginationAndDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EntityWithServiceClassPaginationAndDTO{" +
            "id=" + getId() +
            ", lena='" + getLena() + "'" +
            "}";
    }
}
