INSERT INTO car (car_id, car_brand, car_year, car_engine, car_gearbox, car_class) VALUES (1, 'AUDI', 1988, '2.3', 'MECHANICAL', 'BUSINESS');
INSERT INTO car (car_id, car_brand, car_year, car_engine, car_gearbox, car_class) VALUES (2, 'BMW', 2012, '4.0', 'MECHANICAL', 'SPORT');
INSERT INTO car (car_id, car_brand, car_year, car_engine, car_gearbox, car_class) VALUES (3, 'MERCEDES-BENZ', 1998, '2.0', 'MECHANICAL', 'BUSINESS');

INSERT INTO rental (rental_id, rental_days, rental_rate, rental_price, car_id) VALUES (1, 3, 'DAILY', 50, 1);
INSERT INTO rental (rental_id, rental_days, rental_rate, rental_price, car_id) VALUES (2, 1, 'MAIN', 30, 2);
INSERT INTO rental (rental_id, rental_days, rental_rate, rental_price, car_id) VALUES (3, 15, 'WEEKEND', 3);
INSERT INTO rental (rental_id, rental_days, rental_rate, rental_price, car_id) VALUES (4, 2, 'MAIN', 35, 4);
INSERT INTO rental (rental_id, rental_days, rental_rate, rental_price, car_id) VALUES (5, 5, 'DAILY', 60, 5);