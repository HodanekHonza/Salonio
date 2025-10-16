package com.salonio.modules.business.application.port.business.out;

import com.salonio.modules.business.domain.Business;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BusinessPersistencePort {

    Business save(Business business);

    Optional<Business> findById(UUID id);

    Page<Business> listBusinesses(String category, Pageable pageable);

    List<UUID> listBusinessesByScheduling(boolean isScheduling);

    void deleteById(UUID id);

}
