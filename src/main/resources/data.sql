-- ================================
-- USERS
-- ================================
INSERT INTO users (id, username, password, user_type)
VALUES
    ('99999999-9999-9999-9999-999999999999', 'jana.novakova', 'hashed_password_123', 'STAFF'),
    ('88888888-8888-8888-8888-888888888888', 'eva.kralova', 'hashed_password_123', 'CLIENT');

-- ================================
-- BUSINESS
-- ================================
INSERT INTO business (id, version, business_name, creation_date, address, email, phone_number, website_url, tax_id, city, postal_code, country, latitude, longitude, business_type, opening_time, closing_time, is_active, rating, number_of_reviews, total_bookings, logo_url, description, currency)
VALUES (
           '11111111-1111-1111-1111-111111111111',
           0,
           'Salonio Beauty',
           '2024-10-01 10:00:00+00',
           '123 Main St',
           'info@salonio.com',
           '+420123456789',
           'https://salonio.com',
           'CZ12345678',
           'Prague',
           '11000',
           'Czechia',
           50.0755,
           14.4378,
           'Hair Salon',
           '09:00:00',
           '18:00:00',
           TRUE,
           4.8,
           120,
           500,
           'https://cdn.salonio.com/logo.png',
           'Premium hair and beauty salon in Prague.',
           'CZK'
       );

-- ================================
-- STAFF
-- ================================
INSERT INTO staff (id, version, user_id, first_name, last_name, business_id, phone_number, email, work_email, work_phone_number, role, specialization, is_active, hire_date, salary, employment_type, created_at, updated_at)
VALUES (
           '22222222-2222-2222-2222-222222222222',
           0,
           '99999999-9999-9999-9999-999999999999',
           'Jana',
           'Novakova',
           '11111111-1111-1111-1111-111111111111',
           '+420777111222',
           'jana.novakova@example.com',
           'jana@salonio.com',
           '+420777111333',
           'Hair Stylist',
           'Coloring',
           TRUE,
           '2023-05-15',
           30000,
           'Full-time',
           '2024-10-01 10:00:00+00',
           '2024-10-01 10:00:00+00'
       );

-- ================================
-- CLIENT
-- ================================
INSERT INTO client (id, version, user_id, first_name, last_name, phone_number, email, date_of_birth, gender, address_line, city, postal_code, country, is_active, notes, loyalty_points, created_at, updated_at)
VALUES (
           '55555555-5555-5555-5555-555555555555',
           0,
           '88888888-8888-8888-8888-888888888888',
           'Eva',
           'Kralova',
           '+420777555666',
           'eva.kralova@example.com',
           '1994-06-21',
           'Female',
           'Vodičkova 32',
           'Prague',
           '11000',
           'Czechia',
           TRUE,
           'Regular client who prefers morning appointments.',
           120,
           '2024-10-01 10:00:00+00',
           '2024-10-01 10:00:00+00'
       );

-- ================================
-- BOOKING
-- ================================
-- INSERT INTO booking (id, version, start_time, end_time, client_id, staff_id, service_type, status)
-- VALUES (
--            '33333333-3333-3333-3333-333333333333',
--            0,
--            '2024-10-02 09:00:00+00',
--            '2024-10-02 10:00:00+00',
--            '55555555-5555-5555-5555-555555555555',
--            '22222222-2222-2222-2222-222222222222',
--            'Haircut',
--         1
--        );

-- ================================
-- AVAILABILITY
-- ================================
INSERT INTO availability (id, version, start_time, end_time, staff_id, business_id, availability, booking_id, client_id)
VALUES (
           '44444444-4444-4444-4444-444444444444',
           0,
           '2024-10-02 09:00:00+00',
           '2024-10-02 10:00:00+00',
           '22222222-2222-2222-2222-222222222222',
           '11111111-1111-1111-1111-111111111111',
           TRUE,
           null,
           null
       );


