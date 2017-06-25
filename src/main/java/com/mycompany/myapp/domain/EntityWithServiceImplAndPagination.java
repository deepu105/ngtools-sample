package com.mycompany.myapp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A EntityWithServiceImplAndPagination.
 */
@Entity
@Table(name = "entity_with_service_impl_and_pagination")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "entitywithserviceimplandpagination")
public class EntityWithServiceImplAndPagination implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "hugo")
    private String hugo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHugo() {
        return hugo;
    }

    public EntityWithServiceImplAndPagination hugo(String hugo) {
        this.hugo = hugo;
        return this;
    }

    public void setHugo(String hugo) {
        this.hugo = hugo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        EntityWithServiceImplAndPagination entityWithServiceImplAndPagination = (EntityWithServiceImplAndPagination) o;
        if (entityWithServiceImplAndPagination.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), entityWithServiceImplAndPagination.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EntityWithServiceImplAndPagination{" +
            "id=" + getId() +
            ", hugo='" + getHugo() + "'" +
            "}";
    }
}
