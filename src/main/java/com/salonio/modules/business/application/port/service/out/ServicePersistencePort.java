package com.salonio.modules.business.application.port.service.out;

import com.salonio.modules.business.domain.Service;
import java.util.Optional;
import java.util.UUID;

public interface ServicePersistencePort {

    Service save(Service service);

    Optional<Service> findById(UUID id);

    void deleteById(UUID id);

}
