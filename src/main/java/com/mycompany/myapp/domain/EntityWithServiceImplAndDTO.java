package com.mycompany.myapp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A EntityWithServiceImplAndDTO.
 */
@Entity
@Table(name = "entity_with_service_impl_and_dto")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "entitywithserviceimplanddto")
public class EntityWithServiceImplAndDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "louis")
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

    public EntityWithServiceImplAndDTO louis(String louis) {
        this.louis = louis;
        return this;
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
        EntityWithServiceImplAndDTO entityWithServiceImplAndDTO = (EntityWithServiceImplAndDTO) o;
        if (entityWithServiceImplAndDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), entityWithServiceImplAndDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EntityWithServiceImplAndDTO{" +
            "id=" + getId() +
            ", louis='" + getLouis() + "'" +
            "}";
    }
}
