package com.salonio.modules.business.infrastructure.persistence.business;

import com.salonio.modules.business.application.port.business.out.BusinessPersistencePort;
import com.salonio.modules.business.domain.Business;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Service
public class BusinessRepositoryAdapter implements BusinessPersistencePort {

    private final BusinessRepository businessRepository;

    private static final Logger logger = LoggerFactory.getLogger(BusinessRepositoryAdapter.class);

    @Override
    public Business save(Business business) {
        logger.debug("Saving booking: {}", business);
        final BusinessJpaEntity bookingJpaEntity = BusinessMapper.fromDomain(business);
        final BusinessJpaEntity saved = businessRepository.save(bookingJpaEntity);
        logger.debug("Booking saved with id: {}", saved.getId());
        return BusinessMapper.toDomain(saved);
    }

    @Override
    public Optional<Business> findById(UUID id) {
        logger.debug("Finding business with id: {}", id);
        return businessRepository.findById(id)
                .map(BusinessMapper::toDomain);
    }

    @Override
    public void deleteById(UUID id) {
        logger.debug("Deleting business with id: {}", id);
        businessRepository.deleteById(id);
    }

}
