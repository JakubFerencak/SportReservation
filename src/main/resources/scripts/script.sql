DROP USER IF EXISTS 'mysqluser'@'%';

CREATE USER 'mysqluser'@'%' IDENTIFIED BY 'mysqluser';

GRANT ALL PRIVILEGES ON SportReservation.* TO 'mysqluser'@'%';

FLUSH PRIVILEGES;
