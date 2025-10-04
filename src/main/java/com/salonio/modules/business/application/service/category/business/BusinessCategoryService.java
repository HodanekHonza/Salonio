package com.salonio.modules.business.application.service.category.business;

import com.salonio.modules.business.api.BusinessCategoryApi;
import com.salonio.modules.business.api.dto.category.business.BusinessCategoryCreateRequest;
import com.salonio.modules.business.api.dto.category.business.BusinessCategoryResponse;
import com.salonio.modules.business.api.dto.category.business.BusinessCategoryUpdateRequest;
import com.salonio.modules.business.application.factory.category.business.BusinessCategoryFactory;
import com.salonio.modules.business.application.port.category.business.out.BusinessCategoryPersistencePort;
import com.salonio.modules.business.domain.BusinessCategory;
import com.salonio.modules.business.exception.category.business.BusinessCategoryExceptions;
import com.salonio.modules.business.infrastructure.persistence.category.business.BusinessCategoryMapper;
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
public class BusinessCategoryService implements BusinessCategoryApi {

    private final BusinessCategoryPersistencePort businessCategoryPersistencePort;

    private static final Logger logger = LoggerFactory.getLogger(BusinessCategoryService.class);

    @Override
    public BusinessCategoryResponse createBusinessCategory(BusinessCategoryCreateRequest businessCategoryCreateRequest) {
        final BusinessCategory businessCategory = BusinessCategoryFactory.createBusinessCategory(businessCategoryCreateRequest);
        final BusinessCategory savedBusinessCategory = saveBusinessCategory(businessCategory);
        return BusinessCategoryMapper.toResponse(savedBusinessCategory);
    }

    @Override
    public BusinessCategoryResponse getBusinessCategory(UUID businessId, UUID id) {
        final BusinessCategory businessCategory = findBusinessCategoryById(id);
        return BusinessCategoryMapper.toResponse(businessCategory);
    }

    @Override
    public Page<BusinessCategoryResponse> getBusinessCategories(Pageable pageable) {
        return null;
    }

    @Override
    public BusinessCategoryResponse updateBusinessCategory(UUID id, BusinessCategoryUpdateRequest businessCategoryUpdateRequest) {
        final BusinessCategory existingBusinessCategory = findBusinessCategoryById(id);

        final BusinessCategory updatedBusinessCategory = applyUpdate(businessCategoryUpdateRequest, existingBusinessCategory);

        return BusinessCategoryMapper.toResponse(updatedBusinessCategory);
    }

    @Override
    public void deleteBusinessCategory(UUID id) {
        try {
            businessCategoryPersistencePort.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            logger.error("Deleting business category failed");
            throw new BusinessCategoryExceptions.BusinessCategoryNotFoundException("Business category with id " + id + " not found");
        }
    }


    private BusinessCategory saveBusinessCategory(BusinessCategory businessCategory) {
        try {
            return businessCategoryPersistencePort.save(businessCategory);
        } catch (OptimisticLockingFailureException e) {
            logger.error("Saving business category failed");
            throw new BusinessCategoryExceptions.BusinessCategoryConflictException("Saving business category conflict");
        }
    }

    private BusinessCategory findBusinessCategoryById(UUID businessCategoryId) {
        return businessCategoryPersistencePort.findById(businessCategoryId)
                .orElseThrow(() -> {
                    logger.error("Finding business category with id {} failed", businessCategoryId);
                    return new BusinessCategoryExceptions.BusinessCategoryNotFoundException(
                            "Business category with id " + businessCategoryId + " not found");
                });
    }

    private BusinessCategory applyUpdate(BusinessCategoryUpdateRequest businessCategoryUpdateRequest,
                                         BusinessCategory existingBusinessCategory) {
        try {
            return existingBusinessCategory.updateEntity(businessCategoryUpdateRequest);
        } catch (ConcurrentModificationException e) {
            logger.error("Updating business category failed");
            throw new BusinessCategoryExceptions.BusinessCategoryConflictException(
                    "Business category with id was modified concurrently. Please retry.", e);
        }
    }

}
