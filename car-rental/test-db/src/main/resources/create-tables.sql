DROP TABLE IF EXISTS car;
CREATE TABLE car (
  car_id INT NOT NULL AUTO_INCREMENT,
  car_brand VARCHAR(255) NOT NULL,
  car_year INT NOT NULL AUTO_INCREMENT,
  car_engine VARCHAR(255) NOT NULL,
  car_gearbox VARCHAR(255) NOT NULL,
  car_class VARCHAR(255) NOT NULL,
  PRIMARY KEY (car_id)
);

DROP TABLE IF EXISTS rental;
CREATE TABLE rental (
  rental_id INT NOT NULL AUTO_INCREMENT,
  rental_days INT NOT NULL,
  rental_rate VARCHAR(255) NOT NULL,
  rental_price INT NOT NULL,
  car_id INT NOT NULL
  PRIMARY KEY (rental_id),
  FOREIGN KEY (car_id) REFERENCES car(car_id)
);