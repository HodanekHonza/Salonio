package com.salonio.modules.booking.application.port.out;

import com.salonio.modules.booking.domain.Booking;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.Optional;
import java.util.UUID;

public interface BookingPersistencePort {

    Booking save(Booking booking);

    void deleteById(UUID id);

    Optional<Booking> findById(UUID id);

    Page<Booking> findByClientId(UUID clientId, Pageable pageable);

    Page<Booking> findByStaffId(UUID clientId, Pageable pageable);

}
