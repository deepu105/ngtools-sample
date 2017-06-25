package com.mycompany.myapp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A EntityWithServiceClass.
 */
@Entity
@Table(name = "entity_with_service_class")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "entitywithserviceclass")
public class EntityWithServiceClass implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "zoe")
    private String zoe;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getZoe() {
        return zoe;
    }

    public EntityWithServiceClass zoe(String zoe) {
        this.zoe = zoe;
        return this;
    }

    public void setZoe(String zoe) {
        this.zoe = zoe;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        EntityWithServiceClass entityWithServiceClass = (EntityWithServiceClass) o;
        if (entityWithServiceClass.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), entityWithServiceClass.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EntityWithServiceClass{" +
            "id=" + getId() +
            ", zoe='" + getZoe() + "'" +
            "}";
    }
}
