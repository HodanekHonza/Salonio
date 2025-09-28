ALTER TABLE availability
ADD CONSTRAINT fk_booking_key
    FOREIGN KEY (booking_id)
        REFERENCES Booking (id);