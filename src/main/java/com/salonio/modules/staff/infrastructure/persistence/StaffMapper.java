package com.salonio.modules.staff.infrastructure.persistence;

import com.salonio.modules.staff.api.dto.StaffResponse;
import com.salonio.modules.staff.domain.Staff;

public final class StaffMapper {

    private StaffMapper() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static StaffResponse toResponse(Staff staff) {
        if (staff == null) {
            return null;
        }
        return new StaffResponse(
                staff.getId(),
                staff.getVersion(),
                staff.getUserId(),
                staff.getFirstName(),
                staff.getLastName(),
                staff.getBusinessId(),
                staff.getPhoneNumber(),
                staff.getEmail(),
                staff.getWorkEmail(),
                staff.getWorkPhoneNumber(),
                staff.getRole(),
                staff.getSpecialization(),
                staff.getIsActive(),
                staff.getHireDate(),
                staff.getTerminationDate(),
                staff.getSalary(),
                staff.getEmploymentType(),
                staff.getCreatedAt(),
                staff.getUpdatedAt()

        );
    }

    public static Staff toDomain(StaffJpaEntity entity) {
        if (entity == null) {
            return null;
        }
        return new Staff(
                entity.getId(),
                entity.getVersion(),
                entity.getUserId(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getBusinessId(),
                entity.getPhoneNumber(),
                entity.getEmail(),
                entity.getWorkEmail(),
                entity.getWorkPhoneNumber(),
                entity.getRole(),
                entity.getSpecialization(),
                entity.getIsActive(),
                entity.getHireDate(),
                entity.getTerminationDate(),
                entity.getSalary(),
                entity.getEmploymentType(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()

        );
    }

    public static StaffJpaEntity fromDomain(Staff staff) {
        if (staff == null) {
            return null;
        }
        return new StaffJpaEntity(
                staff.getId(),
                staff.getVersion(),
                staff.getUserId(),
                staff.getFirstName(),
                staff.getLastName(),
                staff.getBusinessId(),
                staff.getPhoneNumber(),
                staff.getEmail(),
                staff.getWorkEmail(),
                staff.getWorkPhoneNumber(),
                staff.getRole(),
                staff.getSpecialization(),
                staff.getIsActive(),
                staff.getHireDate(),
                staff.getTerminationDate(),
                staff.getSalary(),
                staff.getEmploymentType(),
                staff.getCreatedAt(),
                staff.getUpdatedAt()

        );
    }

}
