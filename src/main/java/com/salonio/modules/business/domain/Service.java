package com.salonio.modules.business.domain;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

public class Service {

    private UUID id;
    private String name;
    private String description;
    private BigDecimal price;
    private Integer durationMinutes;
    private Boolean isActive;
    private UUID offeringId;
    private UUID businessId;

    public Service(UUID id, String name, String description, BigDecimal price, Integer durationMinutes, Boolean isActive, UUID offeringId, UUID businessId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.durationMinutes = durationMinutes;
        this.isActive = isActive;
        this.offeringId = offeringId;
        this.businessId = businessId;
    }

    public Service(String name, String description, BigDecimal price, Integer durationMinutes, Boolean isActive, UUID offeringId, UUID businessId) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.description = description;
        this.price = price;
        this.durationMinutes = durationMinutes;
        this.isActive = isActive;
        this.offeringId = offeringId;
        this.businessId = businessId;
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

    public UUID getOfferingId() {
        return offeringId;
    }

    public void setOfferingId(UUID offeringId) {
        this.offeringId = offeringId;
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
        return Objects.equals(id, service.id) && Objects.equals(name, service.name) && Objects.equals(description, service.description)
                && Objects.equals(price, service.price) && Objects.equals(durationMinutes, service.durationMinutes)
                && Objects.equals(isActive, service.isActive) && Objects.equals(offeringId, service.offeringId)
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
                offeringId,
                businessId
        );
    }

    @Override
    public String toString() {
        return "Service{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", durationMinutes=" + durationMinutes +
                ", isActive=" + isActive +
                ", offeringId=" + offeringId +
                ", businessId=" + businessId +
                '}';
    }

}
