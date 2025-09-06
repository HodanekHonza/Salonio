package com.salonio.booking.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class SavedBookingEvent {

    @Getter
    @Setter
    private String message;

}
