package com.salonio.modules.business.domain;

import com.salonio.modules.business.api.dto.category.service.ServiceCategoryUpdateRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
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
