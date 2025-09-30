package com.salonio.modules.business.infrastructure.persistence.business;

import com.salonio.modules.business.api.dto.business.BusinessResponse;
import com.salonio.modules.business.domain.Business;

public class BusinessMapper {
    private BusinessMapper() {
        throw new UnsupportedOperationException("Utility class");
    }


    public static BusinessResponse toResponse(Business business) {
        return new BusinessResponse(
                business.getId(),
                business.getVersion(),
                business.getBusinessName(),
                business.getCreationDate(),
                business.getAddress(),
                business.getEmail(),
                business.getPhoneNumber(),
                business.getWebsiteUrl(),
                business.getTaxId(),
                business.getCity(),
                business.getPostalCode(),
                business.getCountry(),
                business.getLatitude(),
                business.getLongitude(),
                business.getBusinessType(),
                business.getOpeningTime(),
                business.getClosingTime(),
                business.isActive(),
                business.getRating(),
                business.getNumberOfReviews(),
                business.getTotalBookings(),
                business.getLogoUrl(),
                business.getDescription(),
                business.getCurrency()
        );
    }

    public static Business toDomain(BusinessJpaEntity entity) {
        return new Business(
                entity.getId(),
                entity.getVersion(),
                entity.getBusinessName(),
                entity.getCreationDate(),
                entity.getAddress(),
                entity.getEmail(),
                entity.getPhoneNumber(),
                entity.getWebsiteUrl(),
                entity.getTaxId(),
                entity.getCity(),
                entity.getPostalCode(),
                entity.getCountry(),
                entity.getLatitude(),
                entity.getLongitude(),
                entity.getBusinessType(),
                entity.getOpeningTime(),
                entity.getClosingTime(),
                entity.isActive(),
                entity.getRating(),
                entity.getNumberOfReviews(),
                entity.getTotalBookings(),
                entity.getLogoUrl(),
                entity.getDescription(),
                entity.getCurrency()
        );
    }

    public static BusinessJpaEntity fromDomain(Business business) {
        return new BusinessJpaEntity(
                business.getId(),
                business.getVersion(),
                business.getBusinessName(),
                business.getCreationDate(),
                business.getAddress(),
                business.getEmail(),
                business.getPhoneNumber(),
                business.getWebsiteUrl(),
                business.getTaxId(),
                business.getCity(),
                business.getPostalCode(),
                business.getCountry(),
                business.getLatitude(),
                business.getLongitude(),
                business.getBusinessType(),
                business.getOpeningTime(),
                business.getClosingTime(),
                business.isActive(),
                business.getRating(),
                business.getNumberOfReviews(),
                business.getTotalBookings(),
                business.getLogoUrl(),
                business.getDescription(),
                business.getCurrency()
        );
    }
}
