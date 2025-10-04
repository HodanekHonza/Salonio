package com.salonio.modules.business.domain;

import com.salonio.modules.business.api.dto.category.service.ServiceCategoryUpdateRequest;
import org.apache.commons.lang3.builder.ToStringBuilder;
import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

public class ServiceCategory {

    private UUID id;

    private Integer version;

    private String name;

    private String description;

    private boolean active;

    private Instant createdAt;

    private Instant updatedAt;

    private String icon;

    private String colorCode;

    public ServiceCategory(UUID id, Integer version, String name, String description, boolean active, Instant createdAt,
                           Instant updatedAt, String icon, String colorCode) {
        this.id = id;
        this.version = version;
        this.name = name;
        this.description = description;
        this.active = active;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.icon = icon;
        this.colorCode = colorCode;
    }

    public ServiceCategory(String name, String description, boolean active, Instant createdAt,
                           Instant updatedAt, String icon, String colorCode) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.description = description;
        this.active = active;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.icon = icon;
        this.colorCode = colorCode;
    }

    public ServiceCategory updateEntity(ServiceCategoryUpdateRequest request) {
        if (request.name() != null) this.name = request.name();
        if (request.description() != null) this.description = request.description();
        this.active = request.active();
        if (request.createdAt() != null) this.createdAt = request.createdAt();
        if (request.updatedAt() != null) this.updatedAt = request.updatedAt();
        if (request.icon() != null) this.icon = request.icon();
        if (request.colorCode() != null) this.colorCode = request.colorCode();
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getColorCode() {
        return colorCode;
    }

    public void setColorCode(String colorCode) {
        this.colorCode = colorCode;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ServiceCategory that)) return false;
        return active == that.active
                && Objects.equals(id, that.id)
                && Objects.equals(name, that.name)
                && Objects.equals(description, that.description)
                && Objects.equals(createdAt, that.createdAt)
                && Objects.equals(updatedAt, that.updatedAt)
                && Objects.equals(icon, that.icon)
                && Objects.equals(colorCode, that.colorCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                id,
                name,
                description,
                active,
                createdAt,
                updatedAt,
                icon,
                colorCode
        );
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("name", name)
                .append("description", description)
                .append("active", active)
                .append("createdAt", createdAt)
                .append("updatedAt", updatedAt)
                .append("icon", icon)
                .append("colorCode", colorCode)
                .toString();
    }

}
