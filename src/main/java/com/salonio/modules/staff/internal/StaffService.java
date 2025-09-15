package com.salonio.modules.staff.internal;

import com.salonio.modules.staff.StaffApi;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class StaffService implements StaffApi {

    private final StaffRepository staffRepository;

    public StaffService(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    void createStaff() {}

    void getStaff(UUID staffId) {
        final Staff availability = staffRepository.findById(staffId)
                .orElseThrow(() -> new EntityNotFoundException("Staff with id " + staffId + " not found"));
//        return availabilityMapper.toResponse(availability);
    }

    void  updateStaff() {}

    void deleteStaff() {}
}
