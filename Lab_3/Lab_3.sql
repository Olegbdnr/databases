-- drop schema
DROP SCHEMA IF EXISTS `bodnar` ;

CREATE SCHEMA IF NOT EXISTS `bodnar` DEFAULT CHARACTER SET utf8 ;
USE `bodnar` ;

-- drop tables
DROP TABLE IF EXISTS `bodnar`.`location` ;
DROP TABLE IF EXISTS `bodnar`.`teritory` ;
DROP TABLE IF EXISTS `bodnar`.`employee` ;
DROP TABLE IF EXISTS `bodnar`.`manufacturer` ;
DROP TABLE IF EXISTS `bodnar`.`complete_set` ;
DROP TABLE IF EXISTS `bodnar`.`patrol_bot` ;
DROP TABLE IF EXISTS `bodnar`.`camera` ;
DROP TABLE IF EXISTS `bodnar`.`route` ;

-- location table
CREATE TABLE IF NOT EXISTS `bodnar`.`location` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `country` VARCHAR(45) NOT NULL,
  `city` VARCHAR(45) NOT NULL,
  `street` VARCHAR(45) NOT NULL,
  `building_number` VARCHAR(15) NULL,
  PRIMARY KEY (`id`));

-- teritory table
CREATE TABLE IF NOT EXISTS `bodnar`.`teritory` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `phone` VARCHAR(12) NOT NULL,
  `location_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_teritory_location1`
    FOREIGN KEY (`location_id`)
    REFERENCES `bodnar`.`location` (`id`));

-- employee table
CREATE TABLE IF NOT EXISTS `bodnar`.`employee` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `surname` VARCHAR(45) NOT NULL,
  `gender` VARCHAR(15) NOT NULL,
  `birthday` DATE NOT NULL,
  `teritory_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_employee_teritory1`
    FOREIGN KEY (`teritory_id`)
    REFERENCES `bodnar`.`teritory` (`id`));

-- manufacturer table
CREATE TABLE IF NOT EXISTS `bodnar`.`manufacturer` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`));

-- complete set table
CREATE TABLE IF NOT EXISTS `bodnar`.`complete_set` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `manufacturer_id` INT NOT NULL,
  `name` VARCHAR(50) NOT NULL,
  `battery_reserve` DECIMAL(3,2) NOT NULL,
  `average_speed` INT NOT NULL,
  `width` DECIMAL(3,2) NOT NULL,
  `length` DECIMAL(3,2) NOT NULL,
  `height` DECIMAL(3,2) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_complete_set_manufacturer1`
    FOREIGN KEY (`manufacturer_id`)
    REFERENCES `bodnar`.`manufacturer` (`id`));

-- patrol bot table
CREATE TABLE IF NOT EXISTS `bodnar`.`patrol_bot` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `complete_set_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_patrol_bot_complete_set1`
    FOREIGN KEY (`complete_set_id`)
    REFERENCES `bodnar`.`complete_set` (`id`));

-- camera table
CREATE TABLE IF NOT EXISTS `bodnar`.`camera` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `complete_set_id` INT NOT NULL,
  `name` VARCHAR(50) NOT NULL,
  `rotation_angle` INT(3) NULL,
  `night_mode` TINYINT NOT NULL,
  `face_recognition` TINYINT NOT NULL,
  `voice_recognition` TINYINT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_camera_complete_set1`
    FOREIGN KEY (`complete_set_id`)
    REFERENCES `bodnar`.`complete_set` (`id`));

-- location route
CREATE TABLE IF NOT EXISTS `bodnar`.`route` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `teritory_id` INT NOT NULL,
  `patrol_bot_id` INT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `length` DECIMAL(5,3) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_route_teritory1`
    FOREIGN KEY (`teritory_id`)
    REFERENCES `bodnar`.`teritory` (`id`),
  CONSTRAINT `fk_route_patrol_bot1`
    FOREIGN KEY (`patrol_bot_id`)
    REFERENCES `bodnar`.`patrol_bot` (`id`));
    
-- creating indexes
CREATE INDEX city_name_idx ON location(`city`);
CREATE INDEX employee_surname_name_idx ON employee(surname,`name`);
CREATE INDEX complete_set_name_idx ON complete_set(`name`);
CREATE INDEX manufacturer_name_idx ON manufacturer(`name`);

-- insert to location
INSERT INTO `bodnar`.`location` (`id`, `country`, `city`, `street`, `building_number`) VALUES (1, 'Ukraine', 'Lviv', 'Gorodotska', '12');
INSERT INTO `bodnar`.`location` (`id`, `country`, `city`, `street`, `building_number`) VALUES (2, 'Ukraine', 'Uzhhorod', 'Pidvalna', '2');
INSERT INTO `bodnar`.`location` (`id`, `country`, `city`, `street`, `building_number`) VALUES (3, 'Ukraine', 'Kiyiv', 'Zelena', '5');
INSERT INTO `bodnar`.`location` (`id`, `country`, `city`, `street`, `building_number`) VALUES (4, 'Ukraine', 'Lviv', 'Zelena', '144');
INSERT INTO `bodnar`.`location` (`id`, `country`, `city`, `street`, `building_number`) VALUES (5, 'Ukraine', 'Lviv', 'Naukova', '32');
INSERT INTO `bodnar`.`location` (`id`, `country`, `city`, `street`, `building_number`) VALUES (6, 'Ukraine', 'Ivano-Frankivsk', 'Striyska', '31');
INSERT INTO `bodnar`.`location` (`id`, `country`, `city`, `street`, `building_number`) VALUES (7, 'Ukraine', 'Chernigiv', 'Sikhivska', '17');
INSERT INTO `bodnar`.`location` (`id`, `country`, `city`, `street`, `building_number`) VALUES (8, 'Ukraine', 'Kharkiv', 'Gorodotska', '15');
INSERT INTO `bodnar`.`location` (`id`, `country`, `city`, `street`, `building_number`) VALUES (9, 'Ukraine', 'Lviv', 'Shevchenka', '2');
INSERT INTO `bodnar`.`location` (`id`, `country`, `city`, `street`, `building_number`) VALUES (10, 'Ukraine', 'Kiyiv', 'Shevchenka', '10');

-- insert to teritory
INSERT INTO `bodnar`.`teritory` (`id`, `phone`, `location_id`) VALUES (1, '0934949499', 1);
INSERT INTO `bodnar`.`teritory` (`id`, `phone`, `location_id`) VALUES (2, '0934949498', 2);
INSERT INTO `bodnar`.`teritory` (`id`, `phone`, `location_id`) VALUES (3, '0934949497', 3);
INSERT INTO `bodnar`.`teritory` (`id`, `phone`, `location_id`) VALUES (4, '0934949496', 4);
INSERT INTO `bodnar`.`teritory` (`id`, `phone`, `location_id`) VALUES (5, '0934949495', 5);
INSERT INTO `bodnar`.`teritory` (`id`, `phone`, `location_id`) VALUES (6, '0934949494', 6);
INSERT INTO `bodnar`.`teritory` (`id`, `phone`, `location_id`) VALUES (7, '0934949493', 7);
INSERT INTO `bodnar`.`teritory` (`id`, `phone`, `location_id`) VALUES (8, '0934949492', 8);
INSERT INTO `bodnar`.`teritory` (`id`, `phone`, `location_id`) VALUES (9, '0934949491', 9);
INSERT INTO `bodnar`.`teritory` (`id`, `phone`, `location_id`) VALUES (10, '0934949490', 10);

-- insert to employee
INSERT INTO `bodnar`.`employee` (`id`, `name`, `surname`, `gender`, `birthday`, `teritory_id`) VALUES (1, 'Oleh', 'Bodnar', 'male', '2004-04-13', 1);
INSERT INTO `bodnar`.`employee` (`id`, `name`, `surname`, `gender`, `birthday`, `teritory_id`) VALUES (2, 'Oleh', 'Starytskiy', 'male', '2004-04-11', 2);
INSERT INTO `bodnar`.`employee` (`id`, `name`, `surname`, `gender`, `birthday`, `teritory_id`) VALUES (3, 'Oleh', 'Seneta', 'male', '2004-04-12', 3);
INSERT INTO `bodnar`.`employee` (`id`, `name`, `surname`, `gender`, `birthday`, `teritory_id`) VALUES (4, 'Andriy', 'Pavelchak', 'male', '2004-04-14', 4);
INSERT INTO `bodnar`.`employee` (`id`, `name`, `surname`, `gender`, `birthday`, `teritory_id`) VALUES (5, 'Andriy', 'Kubai', 'male', '2004-04-15', 5);
INSERT INTO `bodnar`.`employee` (`id`, `name`, `surname`, `gender`, `birthday`, `teritory_id`) VALUES (6, 'Natalia', 'Seneta', 'female', '2004-04-16', 6);
INSERT INTO `bodnar`.`employee` (`id`, `name`, `surname`, `gender`, `birthday`, `teritory_id`) VALUES (7, 'Oksana', 'Zaluzhna', 'female', '2004-04-17', 7);
INSERT INTO `bodnar`.`employee` (`id`, `name`, `surname`, `gender`, `birthday`, `teritory_id`) VALUES (8, 'Petro', 'Shevchenko', 'male', '2004-04-18', 8);
INSERT INTO `bodnar`.`employee` (`id`, `name`, `surname`, `gender`, `birthday`, `teritory_id`) VALUES (9, 'Kyrylo', 'Dziak', 'male', '2004-04-19', 9);
INSERT INTO `bodnar`.`employee` (`id`, `name`, `surname`, `gender`, `birthday`, `teritory_id`) VALUES (10, 'Denis', 'Vedernikov', 'male', '2004-04-20', 10);


-- insert to manufacturer
INSERT INTO `bodnar`.`manufacturer` (`id`, `name`) VALUES (1, 'Robotics');
INSERT INTO `bodnar`.`manufacturer` (`id`, `name`) VALUES (2, 'Mech');
INSERT INTO `bodnar`.`manufacturer` (`id`, `name`) VALUES (3, 'ABT');

-- insert to complete set
INSERT INTO `bodnar`.`complete_set` (`id`, `manufacturer_id`, `name`, `battery_reserve`, `average_speed`, `width`, `length`, `height`) VALUES (1, 1, 'ZK-301', 9.30, 15, 0.30, 0.40, 0.50);
INSERT INTO `bodnar`.`complete_set` (`id`, `manufacturer_id`, `name`, `battery_reserve`, `average_speed`, `width`, `length`, `height`) VALUES (2, 1, 'ZI-12', 8.20, 18, 1.20, 1.00, 1.00);
INSERT INTO `bodnar`.`complete_set` (`id`, `manufacturer_id`, `name`, `battery_reserve`, `average_speed`, `width`, `length`, `height`) VALUES (3, 1, 'ZR-1', 7.00, 10, 0.95, 0.95, 1.30);
INSERT INTO `bodnar`.`complete_set` (`id`, `manufacturer_id`, `name`, `battery_reserve`, `average_speed`, `width`, `length`, `height`) VALUES (4, 1, 'ZS-22', 8.00, 13, 1.00, 1.00, 1.20);
INSERT INTO `bodnar`.`complete_set` (`id`, `manufacturer_id`, `name`, `battery_reserve`, `average_speed`, `width`, `length`, `height`) VALUES (5, 2, 'KA-103', 9.00, 13, 0.75, 0.80, 1.00);
INSERT INTO `bodnar`.`complete_set` (`id`, `manufacturer_id`, `name`, `battery_reserve`, `average_speed`, `width`, `length`, `height`) VALUES (6, 2, 'KA-21', 8.00, 14, 0.90, 0.90, 1.30);
INSERT INTO `bodnar`.`complete_set` (`id`, `manufacturer_id`, `name`, `battery_reserve`, `average_speed`, `width`, `length`, `height`) VALUES (7, 2, 'KA-301', 9.50, 15, 1.00, 1.00, 1.00);
INSERT INTO `bodnar`.`complete_set` (`id`, `manufacturer_id`, `name`, `battery_reserve`, `average_speed`, `width`, `length`, `height`) VALUES (8, 3, 'TU-11', 8.30, 16, 0.85, 0.90, 1.10);
INSERT INTO `bodnar`.`complete_set` (`id`, `manufacturer_id`, `name`, `battery_reserve`, `average_speed`, `width`, `length`, `height`) VALUES (9, 3, 'TU-25', 6.00, 10, 0.50, 0.50, 1.00);
INSERT INTO `bodnar`.`complete_set` (`id`, `manufacturer_id`, `name`, `battery_reserve`, `average_speed`, `width`, `length`, `height`) VALUES (10, 3, 'TU-3', 7.00, 12, 0.90, 1.00, 1.25);

-- insert to patrol bot
INSERT INTO `bodnar`.`patrol_bot` (`id`, `complete_set_id`) VALUES (1, 1);
INSERT INTO `bodnar`.`patrol_bot` (`id`, `complete_set_id`) VALUES (2, 1);
INSERT INTO `bodnar`.`patrol_bot` (`id`, `complete_set_id`) VALUES (3, 2);
INSERT INTO `bodnar`.`patrol_bot` (`id`, `complete_set_id`) VALUES (4, 3);
INSERT INTO `bodnar`.`patrol_bot` (`id`, `complete_set_id`) VALUES (5, 4);
INSERT INTO `bodnar`.`patrol_bot` (`id`, `complete_set_id`) VALUES (6, 4);
INSERT INTO `bodnar`.`patrol_bot` (`id`, `complete_set_id`) VALUES (7, 1);
INSERT INTO `bodnar`.`patrol_bot` (`id`, `complete_set_id`) VALUES (8, 5);
INSERT INTO `bodnar`.`patrol_bot` (`id`, `complete_set_id`) VALUES (9, 7);
INSERT INTO `bodnar`.`patrol_bot` (`id`, `complete_set_id`) VALUES (10, 7);

-- insert to camera
INSERT INTO `bodnar`.`camera` (`id`, `complete_set_id`, `name`, `rotation_angle`, `night_mode`, `face_recognition`, `voice_recognition`) VALUES (1, 1, 'KL', 160, 1, 1, 0);
INSERT INTO `bodnar`.`camera` (`id`, `complete_set_id`, `name`, `rotation_angle`, `night_mode`, `face_recognition`, `voice_recognition`) VALUES (2, 1, 'TI', 0, 0, 0, 1);
INSERT INTO `bodnar`.`camera` (`id`, `complete_set_id`, `name`, `rotation_angle`, `night_mode`, `face_recognition`, `voice_recognition`) VALUES (3, 3, 'KAT', 360, 0, 1, 0);
INSERT INTO `bodnar`.`camera` (`id`, `complete_set_id`, `name`, `rotation_angle`, `night_mode`, `face_recognition`, `voice_recognition`) VALUES (4, 2, 'KIR', 180, 1, 1, 1);
INSERT INTO `bodnar`.`camera` (`id`, `complete_set_id`, `name`, `rotation_angle`, `night_mode`, `face_recognition`, `voice_recognition`) VALUES (5, 2, 'KIR-2', 0, 1, 0, 0);
INSERT INTO `bodnar`.`camera` (`id`, `complete_set_id`, `name`, `rotation_angle`, `night_mode`, `face_recognition`, `voice_recognition`) VALUES (6, 3, 'KLTA', 160, 1, 1, 0);
INSERT INTO `bodnar`.`camera` (`id`, `complete_set_id`, `name`, `rotation_angle`, `night_mode`, `face_recognition`, `voice_recognition`) VALUES (7, 4, 'U-12', 0, 0, 0, 0);
INSERT INTO `bodnar`.`camera` (`id`, `complete_set_id`, `name`, `rotation_angle`, `night_mode`, `face_recognition`, `voice_recognition`) VALUES (8, 6, 'T-391', 360, 0, 0, 1);
INSERT INTO `bodnar`.`camera` (`id`, `complete_set_id`, `name`, `rotation_angle`, `night_mode`, `face_recognition`, `voice_recognition`) VALUES (9, 5, 'BT', 160, 1, 1, 0);
INSERT INTO `bodnar`.`camera` (`id`, `complete_set_id`, `name`, `rotation_angle`, `night_mode`, `face_recognition`, `voice_recognition`) VALUES (10, 5, 'AA-3', 180, 0, 1, 0);

-- insert to route
INSERT INTO `bodnar`.`route` (`id`, `teritory_id`, `patrol_bot_id`, `name`, `length`) VALUES (1, 1, 1, 'A', 7.305);
INSERT INTO `bodnar`.`route` (`id`, `teritory_id`, `patrol_bot_id`, `name`, `length`) VALUES (2, 1, 2, 'B', 2.100);
INSERT INTO `bodnar`.`route` (`id`, `teritory_id`, `patrol_bot_id`, `name`, `length`) VALUES (3, 1, 3, 'C', 1.500);
INSERT INTO `bodnar`.`route` (`id`, `teritory_id`, `patrol_bot_id`, `name`, `length`) VALUES (4, 2, 4, 'A', 2.300);
INSERT INTO `bodnar`.`route` (`id`, `teritory_id`, `patrol_bot_id`, `name`, `length`) VALUES (5, 2, 5, 'G', 1.157);
INSERT INTO `bodnar`.`route` (`id`, `teritory_id`, `patrol_bot_id`, `name`, `length`) VALUES (6, 3, 6, 'D', 4.500);
INSERT INTO `bodnar`.`route` (`id`, `teritory_id`, `patrol_bot_id`, `name`, `length`) VALUES (7, 4, 7, 'G', 7.305);
INSERT INTO `bodnar`.`route` (`id`, `teritory_id`, `patrol_bot_id`, `name`, `length`) VALUES (8, 5, 8, 'A', 2.100);
INSERT INTO `bodnar`.`route` (`id`, `teritory_id`, `patrol_bot_id`, `name`, `length`) VALUES (9, 6, 9, 'B', 1.500);
INSERT INTO `bodnar`.`route` (`id`, `teritory_id`, `patrol_bot_id`, `name`, `length`) VALUES (10, 7, 10, 'C', 1.157);