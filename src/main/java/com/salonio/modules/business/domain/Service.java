package com.salonio.modules.business.domain;

import com.salonio.modules.business.api.dto.review.ReviewUpdateRequest;
import com.salonio.modules.business.api.dto.service.ServiceUpdateRequest;
import org.apache.commons.lang3.builder.ToStringBuilder;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

public class Service {

    private UUID id;

    private Integer version;

    private String name;

    private String description;

    private BigDecimal price;

    private Integer durationMinutes;

    private Boolean isActive;

    private UUID businessId;

    public Service(UUID id, Integer version, String name, String description, BigDecimal price, Integer durationMinutes, Boolean isActive, UUID businessId) {
        this.id = id;
        this.version = version;
        this.name = name;
        this.description = description;
        this.price = price;
        this.durationMinutes = durationMinutes;
        this.isActive = isActive;
        this.businessId = businessId;
    }

    public Service(String name, String description, BigDecimal price, Integer durationMinutes, Boolean isActive, UUID businessId) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.description = description;
        this.price = price;
        this.durationMinutes = durationMinutes;
        this.isActive = isActive;
        this.businessId = businessId;
    }

    public Service updateEntity(ServiceUpdateRequest request) {
        if (request.name() != null) this.name = request.name();
        if (request.description() != null) this.description = request.description();
        if (request.price() != null) this.price = request.price();
        if (request.durationMinutes() != null) this.durationMinutes = request.durationMinutes();
        if (request.isActive() != null) this.isActive = request.isActive();
        if (request.businessId() != null) this.businessId = request.businessId();
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getDurationMinutes() {
        return durationMinutes;
    }

    public void setDurationMinutes(Integer durationMinutes) {
        this.durationMinutes = durationMinutes;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public UUID getBusinessId() {
        return businessId;
    }

    public void setBusinessId(UUID businessId) {
        this.businessId = businessId;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Service service)) return false;
        return Objects.equals(id, service.id)
                && Objects.equals(name, service.name)
                && Objects.equals(description, service.description)
                && Objects.equals(price, service.price)
                && Objects.equals(durationMinutes, service.durationMinutes)
                && Objects.equals(isActive, service.isActive)
                && Objects.equals(businessId, service.businessId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                id,
                name,
                description,
                price,
                durationMinutes,
                isActive,
                businessId
        );
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("version", version)
                .append("name", name)
                .append("description", description)
                .append("price", price)
                .append("durationMinutes", durationMinutes)
                .append("isActive", isActive)
                .append("businessId", businessId)
                .toString();
    }

}
