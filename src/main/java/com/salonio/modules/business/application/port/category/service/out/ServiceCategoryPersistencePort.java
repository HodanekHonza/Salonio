package com.salonio.modules.business.application.port.category.service.out;

import com.salonio.modules.business.domain.ServiceCategory;
import java.util.Optional;
import java.util.UUID;

public interface ServiceCategoryPersistencePort {

    ServiceCategory save(ServiceCategory serviceCategory);

    Optional<ServiceCategory> findById(UUID id);

    void deleteById(UUID id);

}
