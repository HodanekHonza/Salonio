package com.salonio.modules.staff.application.service;

import com.salonio.modules.staff.api.StaffApi;
import com.salonio.modules.staff.api.dto.StaffResponse;
import com.salonio.modules.staff.api.dto.StaffUpdateRequest;
import com.salonio.modules.staff.application.port.out.StaffPersistencePort;
import com.salonio.modules.staff.domain.Staff;
import com.salonio.modules.staff.exception.StaffExceptions;
import com.salonio.modules.staff.infrastructure.persistence.StaffMapper;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ConcurrentModificationException;
import java.util.UUID;

@AllArgsConstructor
@Service
public class StaffService implements StaffApi {

    private final StaffPersistencePort staffPersistencePort;

    private static final Logger logger = LoggerFactory.getLogger(StaffService.class);

    @Override
    public StaffResponse getStaff(UUID staffId) {
        final Staff staff = findStaffById(staffId);
        return StaffMapper.toResponse(staff);
    }

    public Page<StaffResponse> findStaffByBusiness(UUID businessId, Pageable pageable) {
        final Page<Staff> foundBusinesses = staffPersistencePort.findByBusiness(businessId, pageable);
        return foundBusinesses.map(StaffMapper::toResponse);
    }

    @Transactional
    @Override
    public StaffResponse updateStaff(UUID staffId, StaffUpdateRequest staffUpdateRequest) {
        final Staff existingStaff = findStaffById(staffId);

        final Staff updatedStaff = applyUpdate(staffUpdateRequest, existingStaff);
        final Staff savedStaff = saveStaff(updatedStaff);

        return StaffMapper.toResponse(savedStaff);
    }

    @Transactional
    @Override
    public void deleteStaff(UUID staffId) {
        try {
            staffPersistencePort.deleteById(staffId);
        } catch (EmptyResultDataAccessException e) {
            logger.error("Deleting staff failed");
            throw new StaffExceptions.StaffNotFoundException("Staff with id " + staffId + " not found");
        }
    }

    private Staff saveStaff(Staff review) {
        try {
            return staffPersistencePort.save(review);
        } catch (OptimisticLockingFailureException e) {
            logger.error("Saving staff failed");
            throw new StaffExceptions.StaffConflictException("Saving staff conflict");
        }
    }

    private Staff findStaffById(UUID staffId) {
        return staffPersistencePort.findById(staffId)
                .orElseThrow(() -> {
                    logger.error("Finding staff with id {} failed", staffId);
                    return new StaffExceptions.StaffNotFoundException(
                            "Staff with id " + staffId + " not found");
                });
    }

    private Staff applyUpdate(StaffUpdateRequest staffUpdateRequest, Staff staff) {
        try {
            return staff.updateEntity(staffUpdateRequest);
        } catch (ConcurrentModificationException e) {
            logger.error("Updating staff failed");
            throw new StaffExceptions.StaffConflictException(
                    "Staff with id was modified concurrently. Please retry.", e);
        }
    }

}
