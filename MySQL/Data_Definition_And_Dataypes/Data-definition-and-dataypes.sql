#ex0
CREATE DATABASE `minions`;
USE minions;

#ex1
CREATE TABLE `minions` (
`id` INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(30),
`age` INT
);
CREATE TABLE `towns` (
`town_id` INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(30)
);

ALTER TABLE towns RENAME COLUMN town_id TO id;

#ex2
USE minions;
ALTER TABLE `minions` 
ADD COLUMN `town_id` INT ;

ALTER TABLE `minions` 
ADD CONSTRAINT `fk_minions_towns`
FOREIGN KEY `minions`(`town_id`)
REFERENCES `towns`(`id`);

#ex3
INSERT INTO `towns` (`id`, `name`)
VALUES 
(1, "Sofia"),
(2, "Plovdiv"),
(3, "Varna");

INSERT INTO `minions` (`id`, `name`, `age`, `town_id`)
VALUES 
(1, "Kevin", 22, 1),
(2, "Bob", 15, 3),
(3, "Steward", NULL, 2);

#ex4
TRUNCATE TABLE `minions`;

#ex5
DROP TABLE `minions`;
DROP TABLE `towns`;

#ex6
CREATE TABLE `people` (
`id` INT PRIMARY KEY AUTO_INCREMENT,
`name` 	VARCHAR(200) NOT NULL,
`picture` BLOB,
`height` DOUBLE(10,2),
`weight` DOUBLE(10,2),
`gender` CHAR(1) NOT NULL,
`birthdate` DATE NOT NULL,
`biography` TEXT
);

INSERT INTO `people` (`name`, `gender`, `birthdate`)
VALUES 
('Test', 'M', DATE(NOW())),
('Test', 'M', DATE(NOW())),
('Test', 'M', DATE(NOW())),
('Test', 'M', DATE(NOW())),
('Test', 'M', DATE(NOW()));

#ex7



