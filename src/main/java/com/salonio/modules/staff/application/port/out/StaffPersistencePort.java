package com.salonio.modules.staff.application.port.out;

import com.salonio.modules.staff.domain.Staff;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface StaffPersistencePort {

    Staff save(Staff staff);

    Optional<Staff> findById(UUID staffId);

    Page<Staff> findByBusiness(UUID staffId, Pageable pageable);

    void deleteById(UUID staffId);

}
