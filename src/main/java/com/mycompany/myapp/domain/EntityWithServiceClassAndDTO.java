package com.mycompany.myapp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A EntityWithServiceClassAndDTO.
 */
@Entity
@Table(name = "entity_with_service_class_and_dto")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "entitywithserviceclassanddto")
public class EntityWithServiceClassAndDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "lucas")
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

    public EntityWithServiceClassAndDTO lucas(String lucas) {
        this.lucas = lucas;
        return this;
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
        EntityWithServiceClassAndDTO entityWithServiceClassAndDTO = (EntityWithServiceClassAndDTO) o;
        if (entityWithServiceClassAndDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), entityWithServiceClassAndDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EntityWithServiceClassAndDTO{" +
            "id=" + getId() +
            ", lucas='" + getLucas() + "'" +
            "}";
    }
}
