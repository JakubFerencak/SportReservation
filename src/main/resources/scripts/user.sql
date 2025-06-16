DROP DATABASE IF EXISTS sport_reservation_db;
DROP USER IF EXISTS 'mysqluser'@'%';

CREATE USER 'mysqluser'@'%' IDENTIFIED BY 'mysqluser';
CREATE DATABASE sport_reservation_db;
USE sport_reservation_db;

GRANT ALL PRIVILEGES ON sport_reservation_db.* TO 'mysqluser'@'%';
FLUSH PRIVILEGES;


CREATE TABLE IF NOT EXISTS `location` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  `address` VARCHAR(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;


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
  `location_id` INT NOT NULL,
  PRIMARY KEY (`id`),

  CONSTRAINT `fk_activity_location` FOREIGN KEY (`location_id`) REFERENCES `location` (`id`) ON DELETE CASCADE
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



INSERT INTO `location` (id, name, address) VALUES (1, 'Štadión A', 'Hlavná 1, Mesto');
INSERT INTO `location` (id, name, address) VALUES (2, 'Tenisové Kurty Relax', 'Parková 5, Mesto');
INSERT INTO `location` (id, name, address) VALUES (3, 'Štúdio Relax', 'Wellnes ulica 12, Mesto');


INSERT INTO `user` (id, first_name, last_name, email) VALUES (1, 'Jan', 'Novak', 'novak@email.com');
INSERT INTO `user` (id, first_name, last_name, email) VALUES (2, 'Eva', 'Bezna', 'bezna@email.com');


INSERT INTO `activity` (id, title, location_id) VALUES (1, 'Futbal', 1);
INSERT INTO `activity` (id, title, location_id) VALUES (2, 'Tenis', 2);
INSERT INTO `activity` (id, title, location_id) VALUES (3, 'Joga', 3);


INSERT INTO `reservation` (user_id, activity_id, reservation_time) VALUES (1, 1, '2025-02-07 18:00:00');
INSERT INTO `reservation` (user_id, activity_id, reservation_time) VALUES (2, 2, '2025-02-08 15:30:00');
INSERT INTO `reservation` (user_id, activity_id, reservation_time) VALUES (1, 3, '2025-02-09 10:00:00');
