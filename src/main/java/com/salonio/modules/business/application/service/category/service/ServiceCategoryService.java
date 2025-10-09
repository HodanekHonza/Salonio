package com.salonio.modules.business.application.service.category.service;

import com.salonio.modules.business.api.ServiceCategoryApi;
import com.salonio.modules.business.api.dto.category.service.ServiceCategoryCreateRequest;
import com.salonio.modules.business.api.dto.category.service.ServiceCategoryResponse;
import com.salonio.modules.business.api.dto.category.service.ServiceCategoryUpdateRequest;
import com.salonio.modules.business.application.factory.category.service.ServiceCategoryFactory;
import com.salonio.modules.business.application.port.category.service.out.ServiceCategoryPersistencePort;
import com.salonio.modules.business.domain.ServiceCategory;
import com.salonio.modules.business.exception.category.service.ServiceCategoryExceptions;
import com.salonio.modules.business.infrastructure.persistence.category.service.ServiceCategoryMapper;
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
public class ServiceCategoryService implements ServiceCategoryApi {
    private final ServiceCategoryPersistencePort serviceCategoryPersistencePort;

    private static final Logger logger = LoggerFactory.getLogger(ServiceCategoryService.class);

    @Transactional
    @Override
    public ServiceCategoryResponse createServiceCategory(ServiceCategoryCreateRequest serviceCategoryCreateRequest) {
        final var newServiceCategory = ServiceCategoryFactory.createServiceCategory(serviceCategoryCreateRequest);
        final var savedServiceCategory = saveServiceCategory(newServiceCategory);
        return ServiceCategoryMapper.toResponse(savedServiceCategory);
    }

    @Override
    public ServiceCategoryResponse getServiceCategory(UUID businessId, UUID id) {
        final ServiceCategory serviceCategory = findServiceCategoryById(id);
        return ServiceCategoryMapper.toResponse(serviceCategory);
    }

    @Override
    public Page<ServiceCategoryResponse> getServiceCategories(UUID businessId, Pageable pageable) {
        return null;
    }

    @Transactional
    @Override
    public ServiceCategoryResponse updateServiceCategory(UUID id, ServiceCategoryUpdateRequest serviceCategoryUpdateRequest) {
        final ServiceCategory existingServiceCategory = findServiceCategoryById(id);

        final ServiceCategory updatedServiceCategory = applyUpdate(serviceCategoryUpdateRequest, existingServiceCategory);
        final ServiceCategory savedServiceCategory = saveServiceCategory(updatedServiceCategory);

        return ServiceCategoryMapper.toResponse(savedServiceCategory);
    }

    @Transactional
    @Override
    public void deleteServiceCategory(UUID id) {
        try {
            serviceCategoryPersistencePort.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            logger.error("Deleting service category failed");
            throw new ServiceCategoryExceptions.ServiceCategoryNotFoundException("Service category with id " + id + " not found");
        }
    }


    private ServiceCategory saveServiceCategory(ServiceCategory serviceCategory) {
        try {
            return serviceCategoryPersistencePort.save(serviceCategory);
        } catch (OptimisticLockingFailureException e) {
            logger.error("Saving service category failed");
            throw new ServiceCategoryExceptions.ServiceCategoryConflictException("Saving service category conflict");
        }
    }

    private ServiceCategory findServiceCategoryById(UUID serviceCategoryId) {
        return serviceCategoryPersistencePort.findById(serviceCategoryId)
                .orElseThrow(() -> {
                    logger.error("Finding service category with id {} failed", serviceCategoryId);
                    return new ServiceCategoryExceptions.ServiceCategoryNotFoundException(
                            "Service category with id " + serviceCategoryId + " not found");
                });
    }

    private ServiceCategory applyUpdate(ServiceCategoryUpdateRequest serviceCategoryUpdateRequest,
                                        ServiceCategory existingServiceCategory) {
        try {
            return existingServiceCategory.updateEntity(serviceCategoryUpdateRequest);
        } catch (ConcurrentModificationException e) {
            logger.error("Updating service category failed");
            throw new ServiceCategoryExceptions.ServiceCategoryConflictException(
                    "Service category with id was modified concurrently. Please retry.", e);
        }
    }

}
