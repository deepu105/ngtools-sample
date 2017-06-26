package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

import com.mycompany.myapp.domain.enumeration.DivisionType;

/**
 * A Division.
 */
@Entity
@Table(name = "division")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "division")
public class Division implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "short_name")
    private String shortName;

    @Column(name = "number_of_people")
    private Long numberOfPeople;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "division_type", nullable = false)
    private DivisionType divisionType;

    @Column(name = "color_background")
    private String colorBackground;

    @Column(name = "color_text")
    private String colorText;

    @OneToMany(mappedBy = "owner")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Place> divisionsPlaces = new HashSet<>();

    @ManyToMany(mappedBy = "preferredDivisions")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Place> preferredPlaces = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Division name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public Division shortName(String shortName) {
        this.shortName = shortName;
        return this;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public Long getNumberOfPeople() {
        return numberOfPeople;
    }

    public Division numberOfPeople(Long numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
        return this;
    }

    public void setNumberOfPeople(Long numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }

    public DivisionType getDivisionType() {
        return divisionType;
    }

    public Division divisionType(DivisionType divisionType) {
        this.divisionType = divisionType;
        return this;
    }

    public void setDivisionType(DivisionType divisionType) {
        this.divisionType = divisionType;
    }

    public String getColorBackground() {
        return colorBackground;
    }

    public Division colorBackground(String colorBackground) {
        this.colorBackground = colorBackground;
        return this;
    }

    public void setColorBackground(String colorBackground) {
        this.colorBackground = colorBackground;
    }

    public String getColorText() {
        return colorText;
    }

    public Division colorText(String colorText) {
        this.colorText = colorText;
        return this;
    }

    public void setColorText(String colorText) {
        this.colorText = colorText;
    }

    public Set<Place> getDivisionsPlaces() {
        return divisionsPlaces;
    }

    public Division divisionsPlaces(Set<Place> places) {
        this.divisionsPlaces = places;
        return this;
    }

    public Division addDivisionsPlace(Place place) {
        this.divisionsPlaces.add(place);
        place.setOwner(this);
        return this;
    }

    public Division removeDivisionsPlace(Place place) {
        this.divisionsPlaces.remove(place);
        place.setOwner(null);
        return this;
    }

    public void setDivisionsPlaces(Set<Place> places) {
        this.divisionsPlaces = places;
    }

    public Set<Place> getPreferredPlaces() {
        return preferredPlaces;
    }

    public Division preferredPlaces(Set<Place> places) {
        this.preferredPlaces = places;
        return this;
    }

    public Division addPreferredPlace(Place place) {
        this.preferredPlaces.add(place);
        place.getPreferredDivisions().add(this);
        return this;
    }

    public Division removePreferredPlace(Place place) {
        this.preferredPlaces.remove(place);
        place.getPreferredDivisions().remove(this);
        return this;
    }

    public void setPreferredPlaces(Set<Place> places) {
        this.preferredPlaces = places;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Division division = (Division) o;
        if (division.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), division.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Division{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", shortName='" + getShortName() + "'" +
            ", numberOfPeople='" + getNumberOfPeople() + "'" +
            ", divisionType='" + getDivisionType() + "'" +
            ", colorBackground='" + getColorBackground() + "'" +
            ", colorText='" + getColorText() + "'" +
            "}";
    }
}
