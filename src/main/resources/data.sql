-- -- This script inserts availability records by the hour for two staff members
-- -- for the period between Saturday, September 6, 2025, and Friday, September 19, 2025.
-- --
-- -- Each record has a unique ID, start and end times, a staff member's ID, and a boolean availability status.
-- -- availability = TRUE means the slot is available.
-- -- availability = FALSE means the slot is already booked.
-- --
--
-- INSERT INTO availability (id, start_time, end_time, staff_id, availability, version)
-- VALUES
-- -- Data for Saturday, September 6, 2025
-- ('e9b0b46d-3652-45e0-94e8-8a8f4c28f323', '2025-09-06T09:00:00', '2025-09-06T10:00:00', '1a2b3c4d-5e6f-7a8b-9c0d-1e2f3a4b5c6d', TRUE, 1),
-- ('707842c1-8402-468f-9d95-b9034293e506', '2025-09-06T10:00:00', '2025-09-06T11:00:00', '1a2b3c4d-5e6f-7a8b-9c0d-1e2f3a4b5c6d', TRUE, 1),
-- ('d404d03e-7973-4537-8051-419b6ef943e8', '2025-09-06T11:00:00', '2025-09-06T12:00:00', '1a2b3c4d-5e6f-7a8b-9c0d-1e2f3a4b5c6d', TRUE, 1),
-- ('c7913331-50e5-4a6c-9419-75a02e604006', '2025-09-06T12:00:00', '2025-09-06T13:00:00', '1a2b3c4d-5e6f-7a8b-9c0d-1e2f3a4b5c6d', TRUE, 1),
-- ('16194b62-1204-4c64-9840-02551044490f', '2025-09-06T13:00:00', '2025-09-06T14:00:00', '1a2b3c4d-5e6f-7a8b-9c0d-1e2f3a4b5c6d', TRUE, 1),
-- ('0897a9f8-b30a-426c-829d-640a233b49e6', '2025-09-06T14:00:00', '2025-09-06T15:00:00', '1a2b3c4d-5e6f-7a8b-9c0d-1e2f3a4b5c6d', FALSE, 1),
-- ('399f9797-0361-4603-b0e2-63b7100e46a7', '2025-09-06T15:00:00', '2025-09-06T16:00:00', '1a2b3c4d-5e6f-7a8b-9c0d-1e2f3a4b5c6d', TRUE, 1),
-- ('e9d0e261-2e63-494b-a178-5509a2503254', '2025-09-06T16:00:00', '2025-09-06T17:00:00', '1a2b3c4d-5e6f-7a8b-9c0d-1e2f3a4b5c6d', TRUE, 1),
-- ('48b59e31-5a02-45e0-94d7-0100d0774a38', '2025-09-06T09:00:00', '2025-09-06T10:00:00', 'd4c3b2a1-f6e5-d7c8-b9a0-f1e2d3c4b5a6', TRUE, 1),
-- ('34a5d848-132d-4224-8140-5b651010376d', '2025-09-06T10:00:00', '2025-09-06T11:00:00', 'd4c3b2a1-f6e5-d7c8-b9a0-f1e2d3c4b5a6', FALSE, 1),
-- ('8d8b15d2-f674-4b55-a044-f2a89c933b9c', '2025-09-06T11:00:00', '2025-09-06T12:00:00', 'd4c3b2a1-f6e5-d7c8-b9a0-f1e2d3c4b5a6', TRUE, 1),
-- ('28e94a82-f5f2-494d-9051-512b07e77b63', '2025-09-06T12:00:00', '2025-09-06T13:00:00', 'd4c3b2a1-f6e5-d7c8-b9a0-f1e2d3c4b5a6', TRUE, 1),
-- ('16666873-1002-40a2-b91c-1f6b553c306d', '2025-09-06T13:00:00', '2025-09-06T14:00:00', 'd4c3b2a1-f6e5-d7c8-b9a0-f1e2d3c4b5a6', TRUE, 1),
-- ('249e0c3e-8219-482d-88f6-b118b63e8a3a', '2025-09-06T14:00:00', '2025-09-06T15:00:00', 'd4c3b2a1-f6e5-d7c8-b9a0-f1e2d3c4b5a6', TRUE, 1),
-- ('1f074a36-1e64-42b3-9097-f58c733331b2', '2025-09-06T15:00:00', '2025-09-06T16:00:00', 'd4c3b2a1-f6e5-d7c8-b9a0-f1e2d3c4b5a6', TRUE, 1),
-- ('c7908b98-0c61-464a-a70d-327c9c0b6216', '2025-09-06T16:00:00', '2025-09-06T17:00:00', 'd4c3b2a1-f6e5-d7c8-b9a0-f1e2d3c4b5a6', FALSE, 1);
-- This script inserts availability records by the hour for two staff members
-- for the period between Saturday, September 6, 2025, and Friday, September 19, 2025.
--
-- Each record has a unique ID, start and end times, a staff member's ID, a boolean availability status,
-- and optional booking_id and client_id for booked slots.

INSERT INTO availability (id, start_time, end_time, staff_id, availability, version, booking_id, client_id)
VALUES
-- Data for Saturday, September 6, 2025
('e9b0b46d-3652-45e0-94e8-8a8f4c28f323', '2025-09-06T09:00:00', '2025-09-06T10:00:00',
 '1a2b3c4d-5e6f-7a8b-9c0d-1e2f3a4b5c6d', TRUE, 1, NULL, NULL),
('707842c1-8402-468f-9d95-b9034293e506', '2025-09-06T10:00:00', '2025-09-06T11:00:00',
 '1a2b3c4d-5e6f-7a8b-9c0d-1e2f3a4b5c6d', TRUE, 1, NULL, NULL),
('d404d03e-7973-4537-8051-419b6ef943e8', '2025-09-06T11:00:00', '2025-09-06T12:00:00',
 '1a2b3c4d-5e6f-7a8b-9c0d-1e2f3a4b5c6d', TRUE, 1, NULL, NULL),
('c7913331-50e5-4a6c-9419-75a02e604006', '2025-09-06T12:00:00', '2025-09-06T13:00:00',
 '1a2b3c4d-5e6f-7a8b-9c0d-1e2f3a4b5c6d', TRUE, 1, NULL, NULL),
('16194b62-1204-4c64-9840-02551044490f', '2025-09-06T13:00:00', '2025-09-06T14:00:00',
 '1a2b3c4d-5e6f-7a8b-9c0d-1e2f3a4b5c6d', TRUE, 1, NULL, NULL),
('0897a9f8-b30a-426c-829d-640a233b49e6', '2025-09-06T14:00:00', '2025-09-06T15:00:00',
 '1a2b3c4d-5e6f-7a8b-9c0d-1e2f3a4b5c6d', FALSE, 1,
 'f47ac10b-58cc-4372-a567-0e02b2c3d479', '3fa85f64-5717-4562-b3fc-2c963f66afa6'),
('34a5d848-132d-4224-8140-5b651010376d', '2025-09-06T10:00:00', '2025-09-06T11:00:00',
 'd4c3b2a1-f6e5-d7c8-b9a0-f1e2d3c4b5a6', FALSE, 1,
 '6fa459ea-ee8a-3ca4-894e-db77e160355e', '16fd2706-8baf-433b-82eb-8c7fada847da'),
('c7908b98-0c61-464a-a70d-327c9c0b6216', '2025-09-06T16:00:00', '2025-09-06T17:00:00',
 'd4c3b2a1-f6e5-d7c8-b9a0-f1e2d3c4b5a6', FALSE, 1,
 '123e4567-e89b-12d3-a456-426614174000', '987fbc97-4bed-5078-af07-9141ba07c9f3'),
('399f9797-0361-4603-b0e2-63b7100e46a7', '2025-09-06T15:00:00', '2025-09-06T16:00:00',
 '1a2b3c4d-5e6f-7a8b-9c0d-1e2f3a4b5c6d', TRUE, 1, NULL, NULL),
('e9d0e261-2e63-494b-a178-5509a2503254', '2025-09-06T16:00:00', '2025-09-06T17:00:00',
 '1a2b3c4d-5e6f-7a8b-9c0d-1e2f3a4b5c6d', TRUE, 1, NULL, NULL),
('48b59e31-5a02-45e0-94d7-0100d0774a38', '2025-09-06T09:00:00', '2025-09-06T10:00:00',
 'd4c3b2a1-f6e5-d7c8-b9a0-f1e2d3c4b5a6', TRUE, 1, NULL, NULL),
('8d8b15d2-f674-4b55-a044-f2a89c933b9c', '2025-09-06T11:00:00', '2025-09-06T12:00:00',
 'd4c3b2a1-f6e5-d7c8-b9a0-f1e2d3c4b5a6', TRUE, 1, NULL, NULL),
('28e94a82-f5f2-494d-9051-512b07e77b63', '2025-09-06T12:00:00', '2025-09-06T13:00:00',
 'd4c3b2a1-f6e5-d7c8-b9a0-f1e2d3c4b5a6', TRUE, 1, NULL, NULL),
('16666873-1002-40a2-b91c-1f6b553c306d', '2025-09-06T13:00:00', '2025-09-06T14:00:00',
 'd4c3b2a1-f6e5-d7c8-b9a0-f1e2d3c4b5a6', TRUE, 1, NULL, NULL),
('249e0c3e-8219-482d-88f6-b118b63e8a3a', '2025-09-06T14:00:00', '2025-09-06T15:00:00',
 'd4c3b2a1-f6e5-d7c8-b9a0-f1e2d3c4b5a6', TRUE, 1, NULL, NULL),
('1f074a36-1e64-42b3-9097-f58c733331b2', '2025-09-06T15:00:00', '2025-09-06T16:00:00',
 'd4c3b2a1-f6e5-d7c8-b9a0-f1e2d3c4b5a6', TRUE, 1, NULL, NULL);
