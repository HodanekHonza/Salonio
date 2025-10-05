package com.salonio.modules.business.application.service.service;

import com.salonio.modules.business.api.ServiceApi;
import com.salonio.modules.business.api.dto.service.ServiceCreateRequest;
import com.salonio.modules.business.api.dto.service.ServiceResponse;
import com.salonio.modules.business.api.dto.service.ServiceUpdateRequest;
import com.salonio.modules.business.application.factory.service.ServiceFactory;
import com.salonio.modules.business.application.port.service.out.ServicePersistencePort;
import com.salonio.modules.business.exception.service.ServiceExceptions;
import com.salonio.modules.business.infrastructure.persistence.service.ServiceMapper;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ConcurrentModificationException;
import java.util.UUID;

@AllArgsConstructor
@Service
public class ServiceService implements ServiceApi {

    private final ServicePersistencePort servicePersistencePort;

    private static final Logger logger = LoggerFactory.getLogger(ServiceService.class);

    @Transactional
    @Override
    public ServiceResponse createService(UUID businessId, ServiceCreateRequest serviceCreateRequest) {
        final var newService = ServiceFactory.createService(serviceCreateRequest);
        final var savedService = saveService(businessId, newService);
        return ServiceMapper.toResponse(savedService);
    }

    @Override
    public ServiceResponse getService(UUID businessId, UUID id) {
        final var service = findServiceById(id);
        return ServiceMapper.toResponse(service);
    }

    @Override
    public Page<ServiceResponse> listServicesByBusiness(UUID businessId, Pageable pageable) {
        final var foundServices = findServiceByBusinessId(businessId, pageable);
        return foundServices.map(ServiceMapper::toResponse);
    }

    @Transactional
    @Override
    public ServiceResponse updateService(UUID id, ServiceUpdateRequest serviceUpdateRequest) {
        final var existingService = findServiceById(id);

        final var updatedService = applyUpdate(serviceUpdateRequest, existingService);
        final var savedService = saveService(id, updatedService);

        return ServiceMapper.toResponse(savedService);
    }

    @Transactional
    @Override
    public void deleteService(UUID id) {
        try {
            servicePersistencePort.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            logger.error("Deleting service failed");
            throw new ServiceExceptions.ServiceNotFoundException("Service with id " + id + " not found");
        }
    }


    private com.salonio.modules.business.domain.Service saveService(UUID businessId, com.salonio.modules.business.domain.Service service) {
        try {
            return servicePersistencePort.save(service);
        } catch (OptimisticLockingFailureException e) {
            logger.error("Saving service failed");
            throw new ServiceExceptions.ServiceConflictException("Saving service conflict");
        }
    }

    private com.salonio.modules.business.domain.Service findServiceById(UUID serviceId) {
        return servicePersistencePort.findById(serviceId)
                .orElseThrow(() -> {
                    logger.error("Finding service with id {} failed", serviceId);
                    return new ServiceExceptions.ServiceNotFoundException(
                            "Service with id " + serviceId + " not found");
                });
    }

    private Page<com.salonio.modules.business.domain.Service> findServiceByBusinessId(UUID businessId, Pageable pageable) {
        try {
            return servicePersistencePort.findServiceByBusinessId(businessId, pageable);
        } catch (EmptyResultDataAccessException e) {
            logger.error("Listing services failed");
            throw new ServiceExceptions.ServiceNotFoundException("Services with business id:" + businessId + "not found");
        }
    }

    private com.salonio.modules.business.domain.Service applyUpdate(ServiceUpdateRequest serviceUpdateRequest,
            com.salonio.modules.business.domain.Service existingService) {
        try {
            return existingService.updateEntity(serviceUpdateRequest);
        } catch (ConcurrentModificationException e) {
            logger.error("Updating service failed");
            throw new ServiceExceptions.ServiceConflictException(
                    "Service with id was modified concurrently. Please retry.", e);
        }
    }

}
