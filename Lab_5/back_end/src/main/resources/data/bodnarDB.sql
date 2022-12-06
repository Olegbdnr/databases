SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema bodnar
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema bodnar
-- -----------------------------------------------------
DROP SCHEMA `bodnar`;
CREATE SCHEMA IF NOT EXISTS `bodnar` DEFAULT CHARACTER SET utf8mb3 ;
USE `bodnar` ;

-- -----------------------------------------------------
-- Table `bodnar`.`manufacturer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bodnar`.`manufacturer` (
                                                       `id` INT NOT NULL AUTO_INCREMENT,
                                                       `name` VARCHAR(50) NOT NULL,
    PRIMARY KEY (`id`),
    INDEX `manufacturer_name_idx` (`name` ASC) VISIBLE)
    ENGINE = InnoDB
    AUTO_INCREMENT = 8
    DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `bodnar`.`complete_set`
-- -----------------------------------------------------
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
    INDEX `fk_complete_set_manufacturer1` (`manufacturer_id` ASC) VISIBLE,
    INDEX `complete_set_name_idx` (`name` ASC) VISIBLE,
    CONSTRAINT `fk_complete_set_manufacturer1`
    FOREIGN KEY (`manufacturer_id`)
    REFERENCES `bodnar`.`manufacturer` (`id`))
    ENGINE = InnoDB
    AUTO_INCREMENT = 14
    DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `bodnar`.`camera`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bodnar`.`camera` (
                                                 `id` INT NOT NULL AUTO_INCREMENT,
                                                 `complete_set_id` INT NOT NULL,
                                                 `name` VARCHAR(50) NOT NULL,
    `rotation_angle` INT NULL DEFAULT NULL,
    `night_mode` TINYINT NOT NULL,
    `face_recognition` TINYINT NOT NULL,
    `voice_recognition` TINYINT NOT NULL,
    PRIMARY KEY (`id`),
    INDEX `fk_camera_complete_set1` (`complete_set_id` ASC) VISIBLE,
    CONSTRAINT `fk_camera_complete_set1`
    FOREIGN KEY (`complete_set_id`)
    REFERENCES `bodnar`.`complete_set` (`id`))
    ENGINE = InnoDB
    AUTO_INCREMENT = 13
    DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `bodnar`.`location`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bodnar`.`location` (
                                                   `id` INT NOT NULL AUTO_INCREMENT,
                                                   `country` VARCHAR(45) NOT NULL,
    `city` VARCHAR(45) NOT NULL,
    `street` VARCHAR(45) NOT NULL,
    `building_number` VARCHAR(15) NULL DEFAULT NULL,
    PRIMARY KEY (`id`),
    INDEX `city_name_idx` (`city` ASC) VISIBLE)
    ENGINE = InnoDB
    AUTO_INCREMENT = 14
    DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `bodnar`.`teritory`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bodnar`.`teritory` (
                                                   `id` INT NOT NULL AUTO_INCREMENT,
                                                   `phone` VARCHAR(12) NOT NULL,
    `location_id` INT NOT NULL,
    PRIMARY KEY (`id`),
    INDEX `fk_teritory_location1` (`location_id` ASC) VISIBLE,
    CONSTRAINT `fk_teritory_location1`
    FOREIGN KEY (`location_id`)
    REFERENCES `bodnar`.`location` (`id`))
    ENGINE = InnoDB
    AUTO_INCREMENT = 14
    DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `bodnar`.`employee`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bodnar`.`employee` (
                                                   `id` INT NOT NULL AUTO_INCREMENT,
                                                   `name` VARCHAR(45) NOT NULL,
    `surname` VARCHAR(45) NOT NULL,
    `gender` VARCHAR(15) NOT NULL,
    `birthday` DATE NOT NULL,
    `teritory_id` INT NOT NULL,
    PRIMARY KEY (`id`),
    INDEX `fk_employee_teritory1` (`teritory_id` ASC) VISIBLE,
    INDEX `employee_surname_name_idx` (`surname` ASC, `name` ASC) VISIBLE,
    CONSTRAINT `fk_employee_teritory1`
    FOREIGN KEY (`teritory_id`)
    REFERENCES `bodnar`.`teritory` (`id`))
    ENGINE = InnoDB
    AUTO_INCREMENT = 15
    DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `bodnar`.`patrol_bot`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bodnar`.`patrol_bot` (
                                                     `id` INT NOT NULL AUTO_INCREMENT,
                                                     `complete_set_id` INT NOT NULL,
                                                     `teritory_id` INT NOT NULL,
                                                     PRIMARY KEY (`id`),
    INDEX `fk_patrol_bot_complete_set1` (`complete_set_id` ASC) VISIBLE,
    INDEX `fk_patrol_bot_teritory1_idx` (`teritory_id` ASC) VISIBLE,
    CONSTRAINT `fk_patrol_bot_complete_set1`
    FOREIGN KEY (`complete_set_id`)
    REFERENCES `bodnar`.`complete_set` (`id`),
    CONSTRAINT `fk_patrol_bot_teritory1`
    FOREIGN KEY (`teritory_id`)
    REFERENCES `bodnar`.`teritory` (`id`))
    ENGINE = InnoDB
    AUTO_INCREMENT = 13
    DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `bodnar`.`route`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bodnar`.`route` (
                                                `id` INT NOT NULL AUTO_INCREMENT,
                                                `name` VARCHAR(45) NOT NULL,
    `length` DECIMAL(5,3) NOT NULL,
    `teritory_id` INT NOT NULL,
    PRIMARY KEY (`id`),
    INDEX `fk_route_teritory1_idx` (`teritory_id` ASC) VISIBLE,
    CONSTRAINT `fk_route_teritory1`
    FOREIGN KEY (`teritory_id`)
    REFERENCES `bodnar`.`teritory` (`id`))
    ENGINE = InnoDB
    AUTO_INCREMENT = 15
    DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `bodnar`.`storage`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bodnar`.`storage` (
                                                  `id` INT NOT NULL AUTO_INCREMENT,
                                                  `name` VARCHAR(45) NOT NULL,
    `locationId` INT NOT NULL,
    PRIMARY KEY (`id`))
    ENGINE = InnoDB;


DELIMITER //
CREATE PROCEDURE `insert_random_data_to_location` ()
BEGIN
INSERT INTO location (country, city, street, building_number)
VALUES('Ukraine', 'Lviv', 'Zubrivska', '103a'),
      ('Ukraine', 'Lviv', 'Pidvalna', '17'),
      ('Ukraine', 'Lviv', 'Gorodotska', '12');
END
//

DELIMITER //

CREATE FUNCTION CalcMaxMileage (average_speed INT, battery_reserve DECIMAL)
    RETURNS DECIMAL
    READS SQL DATA
    DETERMINISTIC
BEGIN

   DECLARE result DECIMAL;

   SET result = average_speed * battery_reserve;

RETURN result;

END; //

DELIMITER ;

DELIMITER //

CREATE TRIGGER BeforeInsertLocationCity
    BEFORE INSERT
    ON location FOR EACH ROW
BEGIN
    IF (LENGTH(new.country) < 3)
    THEN SIGNAL SQLSTATE'45000'
    SET MESSAGE_TEXT = 'CHECK error for Insert';
END IF;
END; //
DELIMITER ;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;