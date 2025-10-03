package com.salonio.modules.business.domain;

import com.salonio.modules.business.api.dto.category.business.BusinessCategoryUpdateRequest;
import org.apache.commons.lang3.builder.ToStringBuilder;
import java.util.Objects;
import java.util.UUID;

public class BusinessCategory {

    private UUID id;

    private Integer version;

    private com.salonio.modules.business.domain.enums.BusinessCategory name;

    private Integer numberOfBusinesses;

    public BusinessCategory(com.salonio.modules.business.domain.enums.BusinessCategory name) {
        this.id = UUID.randomUUID();
        this.name = name;
    }

    public BusinessCategory(UUID id, Integer version, com.salonio.modules.business.domain.enums.BusinessCategory name) {
        this.id = id;
        this.version = version;
        this.name = name;
    }

    public BusinessCategory updateEntity(BusinessCategoryUpdateRequest request) {
        this.name = request.businessCategory().getName();
        if (request.numberOfBusinesses() != null) this.numberOfBusinesses = request.numberOfBusinesses();
        return this;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public com.salonio.modules.business.domain.enums.BusinessCategory getName() {
        return name;
    }

    public void setName(com.salonio.modules.business.domain.enums.BusinessCategory name) {
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
        return Objects.equals(id, that.id)
                && Objects.equals(version, that.version)
                && name == that.name
                && Objects.equals(numberOfBusinesses, that.numberOfBusinesses);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                id,
                version,
                name,
                numberOfBusinesses
        );
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("version", version)
                .append("name", name)
                .append("numberOfBusinesses", numberOfBusinesses)
                .toString();
    }

}
