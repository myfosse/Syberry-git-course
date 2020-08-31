INSERT INTO locations (id, country, city, address_line)
VALUES (1, 'Belarus', 'Minsk', '20 Pobediteley Avenue Minsk BY-HM'),
       (2, 'Belarus', 'Minsk', 'Prospekte Pobeditelei 9'),
       (3, 'Spain', 'Teneriffe', 'Av. de Bruselas, s/n, 38660 Costa Adeje');


INSERT INTO pricing (id, type)
VALUES (1, 'ONE_DAY');

INSERT INTO hotels (id, title, location_id)
VALUES (1, 'DoubleTree', 1),
       (2, 'Bahía del Duque Resort', 3),
       (3, 'Marriott', 2);

INSERT INTO hotel_price (id, hotel_id, price, pricing_plan_id)
VALUES (1, 1, 465, 1),
       (2, 2, 776, 1),
       (3, 3, 334, 1);

INSERT INTO files (id, external_id)
VALUES (1, 'https://lh3.googleusercontent.com/p/AF1QipPmgy0YVTzYyHj6AWgNDOlAgOYJyB7jrwgUFTgW=w296-h202-n-k-rw-no-v1'),
       (2, 'https://lh3.googleusercontent.com/p/AF1QipNo5Fwlu4TK0spsUW8t5naOcsMsVQtoD734QWgx=w296-h202-n-k-rw-no-v1'),
       (3, 'https://lh3.googleusercontent.com/proxy/KbWqPYgQaND8HVnZQUaSFcT_F9-ofhcTVztlsRkrJUIPgUT9n2HwV9l1rDZTyP-gFPwnz0vAcK6Eh_n13jeicR9UEC2FId9sb8RrYz30gksedItCWAwKRHhg-DgsYdGZ0atlFvPnsatgh75j9w49nfwKhTwP304=w296-h202-n-k-rw-no-v1'),
       (4, 'https://lh3.googleusercontent.com/proxy/7Zr7VVSbgZtLULWsSUzaQJrlNt3HIvq3VSQzl1WJmB8HFbiLYleHj4OEe42F8S9jeIU-Y2DmR2bBsujXNQn1RHZYLF6EfqoWgcODvuBXk_DlgtAlCSiJ_rkGeHG5nduLLMt-CK9ozXwbpfBIl5Lh6oiRDEOuug=w296-h202-n-k-rw-no-v1');

INSERT INTO hotel_file (id, is_main, file_id, hotel_id)
VALUES (2, true, 2, 3),
       (3, true, 3, 2);
