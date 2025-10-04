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
    private final ServiceRepository serviceRepository;

    private static final Logger logger = LoggerFactory.getLogger(ServiceRepositoryAdapter.class);

    @Override
    public com.salonio.modules.business.domain.Service save(com.salonio.modules.business.domain.Service service) {
        logger.debug("Saving service: {}", service);
        final ServiceJpaEntity serviceJpaEntity = ServiceMapper.fromDomain(service);
        final ServiceJpaEntity savedService = serviceRepository.save(serviceJpaEntity);
        logger.debug("Service saved with id: {}", savedService.getId());
        return ServiceMapper.toDomain(savedService);
    }

    @Override
    public Optional<com.salonio.modules.business.domain.Service> findById(UUID id) {
        logger.debug("Finding service with id: {}", id);
        return serviceRepository.findById(id)
                .map(ServiceMapper::toDomain);
    }

    @Override
    public void deleteById(UUID id) {
        logger.debug("Deleting service with id: {}", id);
        serviceRepository.deleteById(id);
    }

}
