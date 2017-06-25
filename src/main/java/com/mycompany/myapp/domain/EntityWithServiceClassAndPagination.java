package com.mycompany.myapp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A EntityWithServiceClassAndPagination.
 */
@Entity
@Table(name = "entity_with_service_class_and_pagination")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "entitywithserviceclassandpagination")
public class EntityWithServiceClassAndPagination implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "enzo")
    private String enzo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEnzo() {
        return enzo;
    }

    public EntityWithServiceClassAndPagination enzo(String enzo) {
        this.enzo = enzo;
        return this;
    }

    public void setEnzo(String enzo) {
        this.enzo = enzo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        EntityWithServiceClassAndPagination entityWithServiceClassAndPagination = (EntityWithServiceClassAndPagination) o;
        if (entityWithServiceClassAndPagination.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), entityWithServiceClassAndPagination.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EntityWithServiceClassAndPagination{" +
            "id=" + getId() +
            ", enzo='" + getEnzo() + "'" +
            "}";
    }
}
