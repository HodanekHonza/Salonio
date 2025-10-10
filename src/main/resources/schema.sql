ALTER TABLE availability
ADD CONSTRAINT fk_booking_key
    FOREIGN KEY (booking_id) REFERENCES booking (id),
ADD CONSTRAINT fk_staff_key
    FOREIGN KEY (staff_id) REFERENCES staff (id),
ADD CONSTRAINT fk_business_key
    FOREIGN KEY (business_id) REFERENCES business (id);

ALTER TABLE booking
    ADD CONSTRAINT fk_client_key
        FOREIGN KEY (client_id) REFERENCES client (id),
ADD CONSTRAINT fk_staff_key
    FOREIGN KEY (staff_id) REFERENCES staff (id);

ALTER TABLE review
    ADD CONSTRAINT fk_client_key
        FOREIGN KEY (client_id) REFERENCES client (id),
    ADD CONSTRAINT fk_business_key
        FOREIGN KEY (business_id) REFERENCES business (id);

ALTER TABLE service
    ADD CONSTRAINT fk_business_key
        FOREIGN KEY (business_id) REFERENCES business (id);

ALTER TABLE client
    ADD CONSTRAINT fk_user_key
        FOREIGN KEY (user_id) REFERENCES users (id);

ALTER TABLE staff
    ADD CONSTRAINT fk_business_key
        FOREIGN KEY (business_id) REFERENCES business (id),
    ADD CONSTRAINT fk_user_key
        FOREIGN KEY (user_id) REFERENCES users (id);