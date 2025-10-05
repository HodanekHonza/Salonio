package com.salonio.modules.staff.domain.event;

import java.util.UUID;

public record CreateNewUserFromStaffEvent(
        UUID userId
) {
}
