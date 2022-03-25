#Section 1
CREATE SCHEMA stc;
USE stc;

CREATE TABLE addresses (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL
);

CREATE TABLE categories (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(10) NOT NULL
);


CREATE TABLE clients (
    id INT PRIMARY KEY AUTO_INCREMENT,
    full_name VARCHAR(50) NOT NULL UNIQUE,
    phone_number VARCHAR(20) NOT NULL
);

CREATE TABLE drivers (
    id INT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(30) NOT NULL,
    last_name VARCHAR(30) NOT NULL,
    age INT NOT NULL,
    rating FLOAT DEFAULT 5.5
);

CREATE TABLE cars (
    id INT PRIMARY KEY AUTO_INCREMENT,
    make VARCHAR(20) NOT NULL,
    model VARCHAR(20),
    `year` INT DEFAULT 0 NOT NULL,
    mileage INT DEFAULT 0,
    `condition` CHAR NOT NULL,
    category_id INT NOT NULL,
    CONSTRAINT fk_cars_categories FOREIGN KEY (category_id)
        REFERENCES categories (id)
);

CREATE TABLE courses (
    id INT PRIMARY KEY AUTO_INCREMENT,
    from_address_id INT NOT NULL,
    `start` DATETIME NOT NULL,
    bill DECIMAL(10 , 2 ) DEFAULT 10,
    car_id INT NOT NULL,
    client_id INT NOT NULL,
    CONSTRAINT fk_courses_cars FOREIGN KEY (car_id)
        REFERENCES cars (id),
    CONSTRAINT fk_courses_addresses FOREIGN KEY (from_address_id)
        REFERENCES addresses (id),
    CONSTRAINT fk_courses_clients FOREIGN KEY (client_id)
        REFERENCES clients (id)
);

CREATE TABLE cars_drivers (
    car_id INT NOT NULL,
    driver_id INT NOT NULL,
    CONSTRAINT fk_cd_cars FOREIGN KEY (car_id)
        REFERENCES cars (id),
    CONSTRAINT fk_cd_drivers FOREIGN KEY (driver_id)
        REFERENCES drivers (id),
    CONSTRAINT pk PRIMARY KEY (car_id , driver_id)
);

#Section 2
INSERT INTO clients (full_name, phone_number)
SELECT 
	concat(first_name, ' ', last_name),
    concat('(088) 9999', id * 2)
FROM drivers
WHERE id BETWEEN 10 AND 20;

##
UPDATE cars 
SET 
    `condition` = 'C'
WHERE
    (mileage >= 800000 OR mileage IS NULL)
        AND `year` <= 2010
        AND make != 'Mercedes-Benz';
        
##
DELETE cl
FROM
    clients AS cl
        LEFT JOIN
    courses AS co ON co.client_id = cl.id
WHERE
    co.id IS NULL
        AND CHAR_LENGTH(cl.full_name) > 3;

#Section 3
SELECT
	make,
    model,
    `condition`
FROM
	cars
ORDER BY id;

##
SELECT 
    d.first_name, d.last_name, c.make, c.model, c.mileage
FROM
    drivers AS d
        JOIN
    cars_drivers AS cd ON d.id = cd.driver_id
        JOIN
    cars AS c ON c.id = cd.car_id
WHERE
    c.mileage IS NOT NULL
ORDER BY c.mileage DESC , d.first_name ASC;

##
SELECT 
    c.id,
    c.make,
    c.mileage,
    COUNT(co.id) AS count_of_courses,
    round(AVG(co.bill), 2) AS avg_bill
FROM
    cars AS c
       LEFT JOIN
    courses AS co ON c.id = co.car_id
GROUP BY c.id
HAVING count_of_courses != 2
ORDER BY count_of_courses DESC , c.id ASC;

##
SELECT 
    c.full_name,
    COUNT(car.id) AS count_of_cars,
    SUM(co.bill) AS total_sum
FROM
    clients AS c
        JOIN
    courses AS co ON c.id = co.client_id
        JOIN
    cars AS car ON car.id = co.car_id
WHERE
    c.full_name LIKE '_a%'
GROUP BY c.id
HAVING count_of_cars > 1
ORDER BY c.full_name; 

##
SELECT 
    a.name,
    CASE
        WHEN TIME(co.start) BETWEEN '06:00:00' AND '20:00:00' THEN 'Day'
        ELSE 'Night'
    END AS day_time,
    co.bill,
    cl.full_name,
    car.make,
    car.model,
    cat.name
FROM
    courses AS co
        JOIN
    addresses AS a ON co.from_address_id = a.id
        JOIN
    clients AS cl ON co.client_id = cl.id
        JOIN
    cars AS car ON co.car_id = car.id
        JOIN
    categories AS cat ON car.category_id = cat.id
ORDER BY co.id;

#Section 4
DELIMITER $$
CREATE FUNCTION udf_courses_by_client (phone_num VARCHAR (20))
RETURNS INT
DETERMINISTIC
BEGIN
RETURN
	(SELECT 
		count(co.id)
	FROM
		clients AS cl
			RIGHT JOIN
		courses AS co ON cl.id = co.client_id
		WHERE cl.phone_number = phone_num
		GROUP BY cl.id);
END $$
DELIMITER ;

##
DELIMITER $$
CREATE PROCEDURE udp_courses_by_address (address_name VARCHAR(100))
BEGIN
	SELECT 
    a.`name`,
    cl.full_name as full_names,
    CASE 
		WHEN co.bill <= 20 THEN 'Low'
		WHEN co.bill <= 30 THEN 'Medium'
		ELSE 'High'
	END AS 'level_of_bill',
    car.make,
    car.`condition`,
    cat.`name`
FROM
    courses AS co
        JOIN
    addresses AS a ON co.from_address_id = a.id
        JOIN
    clients AS cl ON cl.id = co.client_id
        JOIN
    cars AS car ON co.car_id = car.id
        JOIN
    categories AS cat ON car.category_id = cat.id
WHERE a.`name` = address_name
ORDER BY car.make, cl.full_name; 
END$$  
DELIMITER ;











