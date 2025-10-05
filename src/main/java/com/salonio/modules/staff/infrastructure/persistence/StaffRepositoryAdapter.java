package com.salonio.modules.staff.infrastructure.persistence;

import com.salonio.modules.staff.application.port.out.StaffPersistencePort;
import com.salonio.modules.staff.domain.Staff;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Service
public class StaffRepositoryAdapter implements StaffPersistencePort {

    private final StaffRepository staffRepository;

    private static final Logger logger = LoggerFactory.getLogger(StaffRepositoryAdapter.class);

    @Override
    public Staff save(Staff staff) {
        logger.debug("Saving staff: {}", staff);
        final StaffJpaEntity staffJpaEntity = StaffMapper.fromDomain(staff);
        final StaffJpaEntity saved = staffRepository.save(staffJpaEntity);
        logger.debug("Staff saved with id: {}", saved.getId());
        return StaffMapper.toDomain(saved);
    }

    @Override
    public Optional<Staff> findById(UUID staffId) {
        logger.debug("Finding staff with id: {}", staffId);
        return staffRepository.findById(staffId)
                .map(StaffMapper::toDomain);
    }

    @Override
    public Page<Staff> findByBusiness(UUID businessId, Pageable pageable) {
        logger.debug("Listing Staff for businessId: {}", businessId);
        return staffRepository.findByBusinessId(businessId, pageable)
                .map(StaffMapper::toDomain);
    }

    @Override
    public void deleteById(UUID staffId) {
        logger.debug("Deleting staff with id: {}", staffId);
        staffRepository.deleteById(staffId);
    }

}
