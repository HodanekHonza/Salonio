package com.salonio.modules.business.application.service.service;

import com.salonio.modules.business.api.ServiceApi;
import com.salonio.modules.business.api.dto.service.ServiceCreateRequest;
import com.salonio.modules.business.api.dto.service.ServiceResponse;
import com.salonio.modules.business.api.dto.service.ServiceUpdateRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public class ServiceService implements ServiceApi {
    @Override
    public ServiceResponse createReview(ServiceCreateRequest serviceCreateRequest) {
        return null;
    }

    @Override
    public ServiceResponse getService(UUID id) {
        return null;
    }

    @Override
    public Page<ServiceResponse> getServices(UUID businessId, Pageable pageable) {
        return null;
    }

    @Override
    public ServiceResponse updateService(UUID id, ServiceUpdateRequest serviceUpdateRequest) {
        return null;
    }

    @Override
    public void deleteService(UUID id) {

    }
}
