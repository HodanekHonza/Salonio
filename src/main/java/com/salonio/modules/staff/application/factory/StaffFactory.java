package com.salonio.modules.staff.application.factory;

import com.salonio.modules.staff.domain.Staff;
import com.salonio.modules.staff.domain.event.CreateNewUserFromStaffEvent;

public final class StaffFactory {

    private StaffFactory() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static Staff create(CreateNewUserFromStaffEvent createNewUserFromStaffEvent) {
        return new Staff(
                createNewUserFromStaffEvent.userId()
        );
    }

}
