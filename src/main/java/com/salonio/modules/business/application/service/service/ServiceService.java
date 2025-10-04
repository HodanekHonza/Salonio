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
import java.util.ConcurrentModificationException;
import java.util.UUID;

@AllArgsConstructor
@Service
public class ServiceService implements ServiceApi {

    private final ServicePersistencePort servicePersistencePort;

    private static final Logger logger = LoggerFactory.getLogger(ServiceService.class);

    @Override
    public ServiceResponse createService(ServiceCreateRequest serviceCreateRequest) {
        final com.salonio.modules.business.domain.Service newService = ServiceFactory.createService(serviceCreateRequest);
        final com.salonio.modules.business.domain.Service savedService = saveService(newService);
        return ServiceMapper.toResponse(savedService);
    }

    @Override
    public ServiceResponse getService(UUID id) {
        final com.salonio.modules.business.domain.Service service = findServiceById(id);
        return ServiceMapper.toResponse(service);
    }

    @Override
    public Page<ServiceResponse> getServices(UUID business, Pageable pageable) {
        return null;
    }

    @Override
    public ServiceResponse updateService(UUID id, ServiceUpdateRequest serviceUpdateRequest) {
        final com.salonio.modules.business.domain.Service existingService = findServiceById(id);

        final com.salonio.modules.business.domain.Service updatedService = applyUpdate(serviceUpdateRequest, existingService);

        return ServiceMapper.toResponse(updatedService);
    }

    @Override
    public void deleteService(UUID id) {
        try {
            servicePersistencePort.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            logger.error("Deleting service failed");
            throw new ServiceExceptions.ServiceNotFoundException("Service with id " + id + " not found");
        }
    }


    private com.salonio.modules.business.domain.Service saveService(com.salonio.modules.business.domain.Service service) {
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
