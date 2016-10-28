SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema phonebook
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `phonebook` DEFAULT CHARACTER SET utf8 ;
USE `phonebook` ;

-- -----------------------------------------------------
-- Table `phonebook`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `phonebook`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(32) NOT NULL,
  `password` VARCHAR(16) NOT NULL,
  `fullname` VARCHAR(255) NOT NULL,
  `role` VARCHAR(32) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `phonebook`.`info`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `phonebook`.`info` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `firstname` VARCHAR(255) NOT NULL,
  `secondname` VARCHAR(255) NOT NULL,
  `middlename` VARCHAR(255) NOT NULL,
  `mobilephone` VARCHAR(15) NOT NULL,
  `homephone` VARCHAR(20) NULL,
  `email` VARCHAR(255) NULL,
  `adress` VARCHAR(255) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_info_user_idx` (`user_id` ASC),
  CONSTRAINT `fk_info_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `phonebook`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
