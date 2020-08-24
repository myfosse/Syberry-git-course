INSERT INTO hotel_characteristic_groups (id, name, slug)
VALUES (1, 'amenities', 'amenities'),
       (2, 'rules', 'rules');

INSERT INTO hotel_characteristic (id, value, hotel_id, group_id)
VALUES
(1, 'Wi-Fi', 1, 1),
(2, 'Parking', 1, 1),
(3, 'Air conditioning', 1, 1),
(4, 'Breakfast', 1, 1),
(5, 'No pets', 1, 2),
(6, 'Non-smoking', 1, 2),
(7, 'Beach access', 2, 1),
(8, 'Pool', 2, 1),
(9, 'Spa', 2, 1),
(10, 'Pets allowed', 2, 1),
(11, 'Payment at checkout', 2, 2),
(12, 'No swimming after 10:00 PM', 2, 2),
(13, 'Spa', 3, 1),
(14, 'Pool', 3, 1),
(15, 'Wi-Fi', 3, 1),
(16, 'Parking', 3, 1),
(17, 'No pets', 3, 2),
(18, 'No swimming after 9:00 PM', 3, 2);
