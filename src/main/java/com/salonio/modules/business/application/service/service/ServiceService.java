package com.salonio.modules.business.application.service.service;

import com.salonio.modules.business.api.ServiceApi;
import com.salonio.modules.business.api.dto.service.ServiceCreateRequest;
import com.salonio.modules.business.api.dto.service.ServiceResponse;
import com.salonio.modules.business.api.dto.service.ServiceUpdateRequest;
import com.salonio.modules.business.application.factory.service.ServiceFactory;
import com.salonio.modules.business.application.port.service.out.ServicePersistencePort;
import com.salonio.modules.business.exception.review.ReviewExceptions;
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
    public ServiceResponse createService(ServiceCreateRequest reviewCreateRequest) {
        final com.salonio.modules.business.domain.Service newReview = ServiceFactory.createService(reviewCreateRequest);
        final com.salonio.modules.business.domain.Service savedReview = saveReview(newReview);
        return ServiceMapper.toResponse(savedReview);
    }

    @Override
    public ServiceResponse getService(UUID id) {
        final com.salonio.modules.business.domain.Service review = findReviewById(id);
        return ServiceMapper.toResponse(review);
    }

    @Override
    public Page<ServiceResponse> getServices(UUID business, Pageable pageable) {
        return null;
    }

    @Override
    public ServiceResponse updateService(UUID id, ServiceUpdateRequest reviewUpdateRequest) {
        final com.salonio.modules.business.domain.Service existingReview = findReviewById(id);

        final com.salonio.modules.business.domain.Service updatedReview = applyUpdate(reviewUpdateRequest, existingReview);

        return ServiceMapper.toResponse(updatedReview);
    }

    @Override
    public void deleteService(UUID id) {
        try {
            servicePersistencePort.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            logger.error("Deleting review failed");
            throw new ReviewExceptions.ReviewNotFoundException("Review with id " + id + " not found");
        }
    }


    private com.salonio.modules.business.domain.Service saveReview(com.salonio.modules.business.domain.Service review) {
        try {
            return servicePersistencePort.save(review);
        } catch (OptimisticLockingFailureException e) {
            logger.error("Saving service failed");
            throw new ReviewExceptions.ReviewConflictException("Saving service conflict");
        }
    }

    private com.salonio.modules.business.domain.Service findReviewById(UUID reviewId) {
        return servicePersistencePort.findById(reviewId)
                .orElseThrow(() -> {
                    logger.error("Finding service with id {} failed", reviewId);
                    return new ReviewExceptions.ReviewNotFoundException(
                            "Service with id " + reviewId + " not found");
                });
    }

    private com.salonio.modules.business.domain.Service applyUpdate(ServiceUpdateRequest serviceUpdateRequest,
            com.salonio.modules.business.domain.Service existingReview) {
        try {
            return existingReview.updateEntity(serviceUpdateRequest);
        } catch (ConcurrentModificationException e) {
            logger.error("Updating service failed");
            throw new ReviewExceptions.ReviewConflictException(
                    "Service with id was modified concurrently. Please retry.", e);
        }
    }

}
