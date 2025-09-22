package com.salonio.modules.availability.domain.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class AvailabilitySlotNotFoundEvent {

    private UUID bookingId;

}
