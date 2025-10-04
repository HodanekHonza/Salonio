package com.salonio.modules.business.infrastructure.persistence.category.business;

import com.salonio.modules.business.application.port.category.business.out.BusinessCategoryPersistencePort;
import com.salonio.modules.business.domain.BusinessCategory;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Service
public class BusinessCategoryRepositoryAdapter implements BusinessCategoryPersistencePort {
    private final BusinessCategoryRepository businessCategoryRepository;

    private static final Logger logger = LoggerFactory.getLogger(BusinessCategoryRepositoryAdapter.class);

    @Override
    public BusinessCategory save(BusinessCategory businessCategory) {
        logger.debug("Saving business: {}", businessCategory);
        final BusinessCategoryJpaEntity businessCategoryJpaEntity = BusinessCategoryMapper.fromDomain(businessCategory);
        final BusinessCategoryJpaEntity saved = businessCategoryRepository.save(businessCategoryJpaEntity);
        logger.debug("Business category saved with id: {}", saved.getId());
        return BusinessCategoryMapper.toDomain(saved);
    }

    @Override
    public Optional<BusinessCategory> findById(UUID id) {
        logger.debug("Finding business category with id: {}", id);
        return businessCategoryRepository.findById(id)
                .map(BusinessCategoryMapper::toDomain);
    }

    @Override
    public void deleteById(UUID id) {
        logger.debug("Deleting business category with id: {}", id);
        businessCategoryRepository.deleteById(id);
    }

}
