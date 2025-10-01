package com.salonio.modules.business.infrastructure.persistence.service;

import com.salonio.modules.business.application.port.service.out.ServicePersistencePort;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Service
public class ServiceRepositoryAdapter implements ServicePersistencePort {
    private final ServiceRepository reviewRepository;

    private static final Logger logger = LoggerFactory.getLogger(ServiceRepositoryAdapter.class);

    @Override
    public com.salonio.modules.business.domain.Service save(com.salonio.modules.business.domain.Service review) {
        logger.debug("Saving booking: {}", review);
        final ServiceJpaEntity reviewJpaEntity = ServiceMapper.fromDomain(review);
        final ServiceJpaEntity saved = reviewRepository.save(reviewJpaEntity);
        logger.debug("Booking saved with id: {}", saved.getId());
        return ServiceMapper.toDomain(saved);
    }

    @Override
    public Optional<com.salonio.modules.business.domain.Service> findById(UUID id) {
        logger.debug("Finding booking with id: {}", id);
        return reviewRepository.findById(id)
                .map(ServiceMapper::toDomain);
    }

    @Override
    public void deleteById(UUID id) {
        logger.debug("Deleting booking with id: {}", id);
        reviewRepository.deleteById(id);
    }

}
