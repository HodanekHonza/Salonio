package com.salonio.modules.business.domain;

import java.util.Objects;
import java.util.UUID;

public class BusinessCategory {

    private UUID id;
    private String name;
    private Integer numberOfBusinesses;

    public BusinessCategory(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumberOfBusinesses() {
        return numberOfBusinesses;
    }

    public void setNumberOfBusinesses(Integer numberOfBusinesses) {
        this.numberOfBusinesses = numberOfBusinesses;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof BusinessCategory that)) return false;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name)
                && Objects.equals(numberOfBusinesses, that.numberOfBusinesses);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                id,
                name,
                numberOfBusinesses
        );
    }

    @Override
    public String toString() {
        return "BusinessCategory{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", numberOfBusinesses=" + numberOfBusinesses +
                '}';
    }

}
