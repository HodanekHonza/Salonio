package com.salonio.modules.staff.infrastructure.messaging;

import com.salonio.modules.staff.application.service.StaffDomainService;
import com.salonio.modules.staff.domain.event.CreateNewUserFromStaffEvent;
import lombok.AllArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class StaffEventListener {

    private final StaffDomainService staffDomainService;

    @EventListener
    public void createStaff(CreateNewUserFromStaffEvent event) {
        staffDomainService.saveStaffAfterUserEvent(event);
    }

}

