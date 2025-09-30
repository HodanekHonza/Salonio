package com.salonio.modules.business.application.port.business.out;

import com.salonio.modules.business.domain.Business;
import java.util.Optional;
import java.util.UUID;

public interface BusinessPersistencePort {

    Business save(Business availability);

    Optional<Business> findById(UUID id);

    void deleteById(UUID id);

}
