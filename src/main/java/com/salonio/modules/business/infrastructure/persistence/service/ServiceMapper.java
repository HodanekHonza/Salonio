package com.salonio.modules.business.infrastructure.persistence.service;

import com.salonio.modules.business.api.dto.service.ServiceResponse;
import com.salonio.modules.business.domain.Service;

public final class ServiceMapper {

    private ServiceMapper() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static ServiceResponse toResponse(final Service service) {
        if (service == null) {
            return null;
        }
        return new ServiceResponse(
                service.getId(),
                service.getVersion(),
                service.getName(),
                service.getDescription(),
                service.getPrice(),
                service.getDurationMinutes(),
                service.getActive(),
                service.getBusinessId()
        );
    }


    public static Service toDomain(ServiceJpaEntity entity) {
        return new Service(
                entity.getId(),
                entity.getVersion(),
                entity.getName(),
                entity.getDescription(),
                entity.getPrice(),
                entity.getDurationMinutes(),
                entity.getIsActive(),
                entity.getBusinessId()
        );
    }

    public static ServiceJpaEntity fromDomain(Service review) {
        return new ServiceJpaEntity(
                review.getId(),
                review.getVersion(),
                review.getName(),
                review.getDescription(),
                review.getPrice(),
                review.getDurationMinutes(),
                review.getActive(),
                review.getBusinessId()
        );
    }

}
