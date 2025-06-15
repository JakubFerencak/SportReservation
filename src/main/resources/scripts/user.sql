DROP USER IF EXISTS 'mysqluser'@'%';
CREATE USER 'mysqluser'@'%' IDENTIFIED BY 'mysqluser';


CREATE DATABASE IF NOT EXISTS sport_reservation_db;
USE sport_reservation_db;

GRANT ALL PRIVILEGES ON sport_reservation_db.* TO 'mysqluser'@'%';
FLUSH PRIVILEGES;


CREATE TABLE IF NOT EXISTS `user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(45) DEFAULT NULL,
  `last_name` VARCHAR(45) DEFAULT NULL,
  `email` VARCHAR(45) NOT NULL UNIQUE,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;


CREATE TABLE IF NOT EXISTS `activity` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(100) NOT NULL,
  `location` VARCHAR(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;


CREATE TABLE IF NOT EXISTS `reservation` (
 `id` INT NOT NULL AUTO_INCREMENT,
 `user_id` INT NOT NULL,
 `activity_id` INT NOT NULL,
 `reservation_time` DATETIME NOT NULL,
 PRIMARY KEY (`id`),
 CONSTRAINT `fk_reservation_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE,
 CONSTRAINT `fk_reservation_activity` FOREIGN KEY (`activity_id`) REFERENCES `activity` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;


INSERT INTO `user` (first_name, last_name, email) VALUES ('Jan', 'Novak', 'novak@email.com');
INSERT INTO `user` (first_name, last_name, email) VALUES ('Eva', 'Bezna', 'bezna@email.com');

INSERT INTO `activity` (title, location) VALUES ('Futbal', 'Štadión A');
INSERT INTO `activity` (title, location) VALUES ('Tenis', 'Kurt 1');
INSERT INTO `activity` (title, location) VALUES ('Joga', 'Štúdio Relax');

INSERT INTO `reservation` (user_id, activity_id, reservation_time) VALUES (1, 1, '2025-02-07 18:00:00');
INSERT INTO `reservation` (user_id, activity_id, reservation_time) VALUES (2, 2, '2025-02-08 15:30:00');
INSERT INTO `reservation` (user_id, activity_id, reservation_time) VALUES (1, 3, '2025-02-09 10:00:00');