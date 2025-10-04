package com.salonio.modules.business.infrastructure.persistence.category.service;

import com.salonio.modules.business.application.port.category.service.out.ServiceCategoryPersistencePort;
import com.salonio.modules.business.domain.ServiceCategory;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Service
public class ServiceCategoryRepositoryAdapter implements ServiceCategoryPersistencePort {
    private final ServiceCategoryRepository serviceCategoryRepository;

    private static final Logger logger = LoggerFactory.getLogger(ServiceCategoryRepositoryAdapter.class);

    @Override
    public ServiceCategory save(ServiceCategory serviceCategory) {
        logger.debug("Saving service category: {}", serviceCategory);
        final ServiceCategoryJpaEntity serviceCategoryJpaEntity = ServiceCategoryMapper.fromDomain(serviceCategory);
        final ServiceCategoryJpaEntity saved = serviceCategoryRepository.save(serviceCategoryJpaEntity);
        logger.debug("Service category saved with id: {}", saved.getId());
        return ServiceCategoryMapper.toDomain(saved);
    }

    @Override
    public Optional<ServiceCategory> findById(UUID id) {
        logger.debug("Finding service category with id: {}", id);
        return serviceCategoryRepository.findById(id)
                .map(ServiceCategoryMapper::toDomain);
    }

    @Override
    public void deleteById(UUID id) {
        logger.debug("Deleting service category with id: {}", id);
        serviceCategoryRepository.deleteById(id);
    }

}
