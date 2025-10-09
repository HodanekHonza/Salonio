package com.salonio.modules.business.infrastructure.persistence.category.service;

import com.salonio.modules.business.api.dto.category.service.ServiceCategoryResponse;
import com.salonio.modules.business.domain.ServiceCategory;

public final class ServiceCategoryMapper {

    private ServiceCategoryMapper() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static ServiceCategoryResponse toResponse(ServiceCategory serviceCategory) {
        if (serviceCategory == null) {
            return null;
        }
        return new ServiceCategoryResponse(
                serviceCategory.getId(),
                serviceCategory.getVersion(),
                serviceCategory.getName(),
                serviceCategory.getDescription(),
                serviceCategory.isActive(),
                serviceCategory.getCreatedAt(),
                serviceCategory.getUpdatedAt(),
                serviceCategory.getIcon(),
                serviceCategory.getColorCode()
        );
    }

    public static ServiceCategory toDomain(ServiceCategoryJpaEntity entity) {
        return new ServiceCategory(
                entity.getId(),
                entity.getVersion(),
                entity.getName(),
                entity.getDescription(),
                entity.isActive(),
                entity.getCreatedAt(),
                entity.getUpdatedAt(),
                entity.getIcon(),
                entity.getColorCode()

        );
    }

    public static ServiceCategoryJpaEntity fromDomain(ServiceCategory serviceCategory) {
        return new ServiceCategoryJpaEntity(
                serviceCategory.getId(),
                serviceCategory.getVersion(),
                serviceCategory.getName(),
                serviceCategory.getDescription(),
                serviceCategory.isActive(),
                serviceCategory.getCreatedAt(),
                serviceCategory.getUpdatedAt(),
                serviceCategory.getIcon(),
                serviceCategory.getColorCode()
        );
    }

}
