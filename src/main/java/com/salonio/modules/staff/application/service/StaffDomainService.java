package com.salonio.modules.staff.application.service;

import com.salonio.modules.staff.application.factory.StaffFactory;
import com.salonio.modules.staff.application.port.out.StaffPersistencePort;
import com.salonio.modules.staff.domain.Staff;
import com.salonio.modules.staff.domain.event.CreateNewUserFromStaffEvent;
import com.salonio.modules.staff.exception.StaffExceptions;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class StaffDomainService {

    //    private final StaffEventPort staffEventPort;
    private final StaffPersistencePort staffPersistencePort;

    private static final Logger logger = LoggerFactory.getLogger(StaffDomainService.class);

    public void saveStaffAfterUserEvent(CreateNewUserFromStaffEvent createNewUserFromStaffEvent) {
        final Staff newStaff = StaffFactory.create(createNewUserFromStaffEvent);
        final Staff savedStaff = saveStaff(newStaff);
        // TODO send event to notification module so we can notify the user about tne new account they created, change User param from username to Email
    }

    private Staff saveStaff(Staff staff) {
        try {
            return staffPersistencePort.save(staff);
        } catch (OptimisticLockingFailureException e) {
            logger.error("Saving client failed");
            throw new StaffExceptions.StaffConflictException("Saving staff conflict");
        }
    }

}

