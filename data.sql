-- MySQL Script generated by MySQL Workbench
-- 10/31/16 14:37:32
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS = @@UNIQUE_CHECKS, UNIQUE_CHECKS = 0;
SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0;
SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE = 'TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema phonebook
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `phonebook`;
-- -----------------------------------------------------
-- Schema phonebook
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `phonebook`
  DEFAULT CHARACTER SET utf8;
USE `phonebook`;

-- -----------------------------------------------------
-- Table `phonebook`.`users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `phonebook`.`users`;

CREATE TABLE IF NOT EXISTS `phonebook`.`users` (
  `username`  VARCHAR(45)  NOT NULL,
  `password`  VARCHAR(45)  NOT NULL,
  `fullname`  VARCHAR(255) NOT NULL,
  `authority` VARCHAR(45)  NOT NULL,
  `enabled`   TINYINT(1)   NOT NULL,
  UNIQUE INDEX `username_UNIQUE` (`username` ASC),
  PRIMARY KEY (`username`)
)
  ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `phonebook`.`info`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `phonebook`.`info`;

CREATE TABLE IF NOT EXISTS `phonebook`.`info` (
  `id`          INT          NOT NULL AUTO_INCREMENT,
  `firstname`   VARCHAR(255) NOT NULL,
  `secondname`  VARCHAR(255) NOT NULL,
  `middlename`  VARCHAR(255) NOT NULL,
  `mobilephone` VARCHAR(45)  NOT NULL,
  `homephone`   VARCHAR(45)  NULL,
  `adress`      VARCHAR(255) NULL,
  `email`       VARCHAR(255) NULL,
  `users_name`  VARCHAR(45)  NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  INDEX `fk_info_users_idx` (`users_name` ASC),
  CONSTRAINT `fk_info_users`
  FOREIGN KEY (`users_name`)
  REFERENCES `phonebook`.`users` (`username`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
)
  ENGINE = InnoDB;


SET SQL_MODE = @OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS;
