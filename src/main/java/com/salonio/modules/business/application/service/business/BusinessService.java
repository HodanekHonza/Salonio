package com.salonio.modules.business.application.service.business;

import com.salonio.modules.business.api.BusinessApi;
import com.salonio.modules.business.api.dto.business.BusinessCreateRequest;
import com.salonio.modules.business.api.dto.business.BusinessResponse;
import com.salonio.modules.business.api.dto.business.BusinessUpdateRequest;
import com.salonio.modules.business.api.dto.review.ReviewResponse;
import com.salonio.modules.business.application.factory.business.BusinessFactory;
import com.salonio.modules.business.application.port.business.out.BusinessPersistencePort;
import com.salonio.modules.business.domain.Business;
import com.salonio.modules.business.exception.business.BusinessExceptions;
import com.salonio.modules.business.infrastructure.persistence.business.BusinessMapper;
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
public class BusinessService implements BusinessApi {

    private final BusinessPersistencePort businessPersistencePort;

    private static final Logger logger = LoggerFactory.getLogger(BusinessService.class);

    @Override
    public BusinessResponse createBusiness(BusinessCreateRequest businessCreateRequest) {
        final var newBusiness = BusinessFactory.createBusiness(businessCreateRequest);
        final var savedBusiness = saveBusiness(newBusiness);
        return BusinessMapper.toResponse(savedBusiness);
    }

    @Override
    public BusinessResponse getBusiness(UUID id) {
        final Business business = findBusinessById(id);
        return BusinessMapper.toResponse(business);
    }

    @Override
    public Page<ReviewResponse> getBusinesses(String category, Pageable pageable) {
        return null;
    }

    @Override
    public BusinessResponse updateBusiness(UUID id, BusinessUpdateRequest businessUpdateRequest) {
        final Business existingBusiness = findBusinessById(id);

        final Business updatedBusiness = applyUpdate(businessUpdateRequest, existingBusiness);

        return BusinessMapper.toResponse(updatedBusiness);
    }

    @Override
    public void deleteBusiness(UUID id) {
        try {
            businessPersistencePort.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            logger.error("Deleting availability failed");
            throw new BusinessExceptions.BusinessNotFoundException("Business with id " + id + " not found");
        }
    }


    private Business saveBusiness(Business business) {
        try {
            return businessPersistencePort.save(business);
        } catch (OptimisticLockingFailureException e) {
            logger.error("Saving business failed");
            throw new BusinessExceptions.BusinessConflictException("Saving business conflict");
        }
    }

    private Business findBusinessById(UUID businessId) {
        return businessPersistencePort.findById(businessId)
                .orElseThrow(() -> {
                    logger.error("Finding business with id {} failed", businessId);
                    return new BusinessExceptions.BusinessNotFoundException(
                            "Business with id " + businessId + " not found");
                });
    }

    private Business applyUpdate(BusinessUpdateRequest updateAvailabilityRequest, Business existingBusiness) {
        try {
            return existingBusiness.updateEntity(updateAvailabilityRequest);
        } catch (ConcurrentModificationException e) {
            logger.error("Updating availability failed");
            throw new BusinessExceptions.BusinessConflictException(
                    "Availability with id was modified concurrently. Please retry.", e);
        }
    }

}
