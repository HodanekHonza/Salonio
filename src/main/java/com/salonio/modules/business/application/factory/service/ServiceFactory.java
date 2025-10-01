package com.salonio.modules.business.application.factory.service;

import com.salonio.modules.business.api.dto.service.ServiceCreateRequest;
import com.salonio.modules.business.domain.Service;

public final class ServiceFactory {

    private ServiceFactory() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static Service createService(ServiceCreateRequest serviceCreateRequest) {
        return new Service(
                serviceCreateRequest.name(),
                serviceCreateRequest.description(),
                serviceCreateRequest.price(),
                serviceCreateRequest.durationMinutes(),
                serviceCreateRequest.isActive(),
                serviceCreateRequest.businessId()
        );
    }

}