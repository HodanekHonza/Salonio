package com.salonio.modules.client.domain.event;

import java.util.UUID;

public record CreateNewUserFromClientEvent(
        UUID userId
) {
}
