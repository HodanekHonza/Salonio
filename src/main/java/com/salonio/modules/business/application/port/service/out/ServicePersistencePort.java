package com.salonio.modules.business.application.port.service.out;

import com.salonio.modules.business.domain.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.Optional;
import java.util.UUID;

public interface ServicePersistencePort {

    Service save(Service service);

    Optional<Service> findById(UUID id);

    Page<Service> findServiceByBusinessId(UUID businessId, Pageable pageable);

    void deleteById(UUID id);

}
