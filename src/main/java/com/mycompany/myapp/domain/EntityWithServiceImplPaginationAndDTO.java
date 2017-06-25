package com.mycompany.myapp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A EntityWithServiceImplPaginationAndDTO.
 */
@Entity
@Table(name = "entity_with_service_impl_pagination_and_dto")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "entitywithserviceimplpaginationanddto")
public class EntityWithServiceImplPaginationAndDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "theo")
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

    public EntityWithServiceImplPaginationAndDTO theo(String theo) {
        this.theo = theo;
        return this;
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
        EntityWithServiceImplPaginationAndDTO entityWithServiceImplPaginationAndDTO = (EntityWithServiceImplPaginationAndDTO) o;
        if (entityWithServiceImplPaginationAndDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), entityWithServiceImplPaginationAndDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EntityWithServiceImplPaginationAndDTO{" +
            "id=" + getId() +
            ", theo='" + getTheo() + "'" +
            "}";
    }
}
