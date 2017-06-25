package com.mycompany.myapp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A EntityWithDTO.
 */
@Entity
@Table(name = "entity_with_dto")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "entitywithdto")
public class EntityWithDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "emma")
    private String emma;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmma() {
        return emma;
    }

    public EntityWithDTO emma(String emma) {
        this.emma = emma;
        return this;
    }

    public void setEmma(String emma) {
        this.emma = emma;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        EntityWithDTO entityWithDTO = (EntityWithDTO) o;
        if (entityWithDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), entityWithDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EntityWithDTO{" +
            "id=" + getId() +
            ", emma='" + getEmma() + "'" +
            "}";
    }
}
