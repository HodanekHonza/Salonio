package com.salonio.booking.internal;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

interface BookingRepository extends JpaRepository<Booking, UUID> {
    List<Booking> findByServiceType(String name);
}