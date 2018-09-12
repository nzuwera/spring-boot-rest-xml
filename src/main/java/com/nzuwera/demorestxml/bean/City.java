package com.nzuwera.demorestxml.bean;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "CITIES")
@JacksonXmlRootElement(localName = "City")
public class City implements Serializable {

    private static final long serialVersionUID = 21L;

    /*    @Id
        @JacksonXmlProperty(isAttribute = true)
        @NotNull
        @Column(name = "ID", nullable = false)
        @Type(type = "pg-uuid")
        @GeneratedValue(generator = "uuid2")
        @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")*/
    @NotNull
    @Id
    @Column(name = "ID", nullable = false)
    @Type(type = "pg-uuid")
    private UUID guid;

    @JacksonXmlProperty(isAttribute = true)
    private String name;

    @JacksonXmlProperty(isAttribute = true)
    private int population;

    public City() {
    }

    public City(@NotNull UUID guid, String name, int population) {
        this.guid = guid;
        this.name = name;
        this.population = population;
    }

    @NotNull
    public UUID getGuid() {
        return guid;
    }

    public void setGuid(@NotNull UUID guid) {
        this.guid = guid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return population == city.population &&
                Objects.equals(guid, city.guid) &&
                Objects.equals(name, city.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(guid, name, population);
    }

    @Override
    public String toString() {
        return "City{" +
                "guid=" + guid +
                ", name='" + name + '\'' +
                ", population=" + population +
                '}';
    }
}