package com.salonio.modules.business.infrastructure.persistence.category.business;

import com.salonio.modules.business.api.dto.category.business.BusinessCategoryResponse;
import com.salonio.modules.business.domain.BusinessCategory;

public final class BusinessCategoryMapper {
    private BusinessCategoryMapper() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static BusinessCategoryResponse toResponse(final BusinessCategory businessCategory) {
        if (businessCategory == null) {
            return null;
        }
        return new BusinessCategoryResponse(
                businessCategory.getId(),
                businessCategory.getName()
        );
    }

    public static BusinessCategory toDomain(BusinessCategoryJpaEntity entity) {
        return new BusinessCategory(
                entity.getId(),
                entity.getVersion(),
                entity.getName()

        );
    }

    public static BusinessCategoryJpaEntity fromDomain(BusinessCategory review) {
        return new BusinessCategoryJpaEntity(
                review.getId(),
                review.getVersion(),
                review.getName(),
                review.getNumberOfBusinesses()
        );
    }

}
