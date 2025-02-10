CREATE DATABASE IF NOT EXISTS SportReservation;
USE SportReservation;

DROP TABLE IF EXISTS `User`;

CREATE TABLE `User`(
  id INT NOT NULL AUTO_INCREMENT,
  first_name VARCHAR(45) DEFAULT NULL,
  last_name VARCHAR(45) DEFAULT NULL,
  email VARCHAR(45) DEFAULT NULL,
  PRIMARY KEY (id)
) AUTO_INCREMENT=1 DEFAULT CHARSET=UTF8;

INSERT INTO User (id, first_name, last_name, email) VALUES (1, 'Jan', 'Novak', 'novak@email.com');
INSERT INTO User (id, first_name, last_name, email) VALUES (2, 'Eva', 'Bezna', 'bezna@email.com');


DROP TABLE IF EXISTS `Activity`;

CREATE TABLE Activity (
  id INT NOT NULL AUTO_INCREMENT,
  title VARCHAR(45) DEFAULT NULL,
  location VARCHAR(45) DEFAULT NULL,
  PRIMARY KEY (id)
) AUTO_INCREMENT=1 DEFAULT CHARSET=UTF8;

INSERT INTO Activity (id, title, location) VALUES (1, 'Football', 'Stadium A');
INSERT INTO Activity (id, title, location) VALUES (2, 'Tennis', 'Court 1');

DROP TABLE IF EXISTS 'Reservation';

CREATE TABLE Reservation (
 id INT NOT NULL AUTO_INCREMENT,
 user_id INT NOT NULL,
 time DATETIME NOT NULL,
 PRIMARY KEY (id),
 FOREIGN KEY (user_id) REFERENCES User(id) ON DELETE CASCADE ON UPDATE CASCADE
) AUTO_INCREMENT=1 DEFAULT CHARSET=UTF8;

INSERT INTO Reservation (id, user_id, time) VALUES (1, 1, '2025-02-07 18:00:00');
INSERT INTO Reservation (id, user_id, time) VALUES (2, 2, '2025-02-07 15:30:00');

DROP TABLE IF EXISTS 'ReservationActivity';

CREATE TABLE ReservationActivity (
 reservation_id INT NOT NULL,
 activity_id INT NOT NULL,
 PRIMARY KEY (reservation_id, activity_id),
 FOREIGN KEY (reservation_id) REFERENCES Reservation(id) ON DELETE CASCADE ON UPDATE CASCADE,
 FOREIGN KEY (activity_id) REFERENCES Activity(id) ON DELETE CASCADE ON UPDATE CASCADE
) DEFAULT CHARSET=UTF8;