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
        );
    }

}
