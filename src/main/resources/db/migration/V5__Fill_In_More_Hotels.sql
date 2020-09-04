INSERT INTO locations (id, country, city, address_line)
VALUES (4, 'Belarus', 'Minsk', '20 Pobediteley Avenue Minsk BY-HM'),
       (5, 'Belarus', 'Minsk', 'Prospekte Pobeditelei 9'),
       (6, 'Spain', 'Teneriffe', 'Av. de Bruselas, s/n, 38660 Costa Adeje'),
       (7, 'Belarus', 'Minsk', '20 Pobediteley Avenue Minsk BY-HM'),
       (8, 'Belarus', 'Minsk', 'Prospekte Pobeditelei 9'),
       (9, 'Spain', 'Teneriffe', 'Av. de Bruselas, s/n, 38660 Costa Adeje'),
       (10, 'Belarus', 'Minsk', '20 Pobediteley Avenue Minsk BY-HM'),
       (11, 'Belarus', 'Minsk', 'Prospekte Pobeditelei 9');

INSERT INTO hotels (id, title, location_id, owner_id)
VALUES (4, 'DoubleTree', 4, 999),
       (5, 'Bahía del Duque Resort', 5, 999),
       (6, 'DoubleTree', 6, 999),
       (7, 'Bahía del Duque Resort', 7, 999),
       (8, 'DoubleTree', 8, 999),
       (9, 'Bahía del Duque Resort', 9, 999),
       (10, 'DoubleTree', 10, 999),
       (11, 'Bahía del Duque Resort', 11, 999);

INSERT INTO hotel_price (id, hotel_id, price, pricing_plan_id)
VALUES (4, 4, 465, 1),
       (5, 5, 776, 1),
       (6, 6, 334, 1),
       (7, 7, 465, 1),
       (8, 8, 776, 1),
       (9, 9, 334, 1),
       (10, 10, 465, 1),
       (11, 11, 776, 1);