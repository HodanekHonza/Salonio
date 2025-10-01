package com.salonio.modules.business.application.port.category.business.out;

import com.salonio.modules.business.domain.BusinessCategory;
import java.util.Optional;
import java.util.UUID;

public interface BusinessCategoryPersistencePort {

    BusinessCategory save(BusinessCategory serviceCategory);

    Optional<BusinessCategory> findById(UUID id);

    void deleteById(UUID id);

}
