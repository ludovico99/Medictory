-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema medictory
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `medictory` ;

-- -----------------------------------------------------
-- Schema medictory
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `medictory` DEFAULT CHARACTER SET utf8 ;
USE `medictory` ;

-- -----------------------------------------------------
-- Table `medictory`.`Utenti`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `medictory`.`Utenti` ;

CREATE TABLE IF NOT EXISTS `medictory`.`Utenti` (
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `email` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`username`))
ENGINE = InnoDB;

CREATE UNIQUE INDEX `email_UNIQUE` ON `medictory`.`Utenti` (`email` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `medictory`.`Farmacia`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `medictory`.`Farmacia` ;

CREATE TABLE IF NOT EXISTS `medictory`.`Farmacia` (
  `username` VARCHAR(45) NOT NULL,
  `nome` VARCHAR(45) NOT NULL,
  `indirizzo` VARCHAR(200) NOT NULL,
  PRIMARY KEY (`username`),
  CONSTRAINT `username_farma`
    FOREIGN KEY (`username`)
    REFERENCES `medictory`.`Utenti` (`username`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `medictory`.`Cliente`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `medictory`.`Cliente` ;

CREATE TABLE IF NOT EXISTS `medictory`.`Cliente` (
  `username` VARCHAR(45) NOT NULL,
  `farmacia associata` VARCHAR(45) NOT NULL,
  `punti` INT NOT NULL DEFAULT 0,
  `livello` INT NOT NULL DEFAULT 1,
  PRIMARY KEY (`username`),
  CONSTRAINT `username_cliente`
    FOREIGN KEY (`username`)
    REFERENCES `medictory`.`Utenti` (`username`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `farma_associata`
    FOREIGN KEY (`farmacia associata`)
    REFERENCES `medictory`.`Farmacia` (`username`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `farma_associata_idx` ON `medictory`.`Cliente` (`farmacia associata` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `medictory`.`Farmaco Farmacia`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `medictory`.`Farmaco Farmacia` ;

CREATE TABLE IF NOT EXISTS `medictory`.`Farmaco Farmacia` (
  `farmaco` VARCHAR(45) NOT NULL,
  `possessore` VARCHAR(45) NOT NULL,
  `quantitativo` INT NOT NULL,
  `descrizione` VARCHAR(500) NOT NULL,
  `scadenza` DATE NOT NULL,
  PRIMARY KEY (`farmaco`, `possessore`, `scadenza`),
  CONSTRAINT `fk_utente`
    FOREIGN KEY (`possessore`)
    REFERENCES `medictory`.`Farmacia` (`username`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_utente_idx` ON `medictory`.`Farmaco Farmacia` (`possessore` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `medictory`.`Farmaco Cliente`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `medictory`.`Farmaco Cliente` ;

CREATE TABLE IF NOT EXISTS `medictory`.`Farmaco Cliente` (
  `farmaco` VARCHAR(45) NOT NULL,
  `possessore` VARCHAR(45) NOT NULL,
  `scadenza` DATE NOT NULL,
  `descrizione` VARCHAR(500) NOT NULL,
  `quantitativo` INT NOT NULL,
  `stato` ENUM('utilizzabile', 'scaduto', 'smaltito', 'verificato') NULL,
  PRIMARY KEY (`farmaco`, `possessore`, `scadenza`),
  CONSTRAINT `istanza2`
    FOREIGN KEY (`possessore`)
    REFERENCES `medictory`.`Cliente` (`username`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `istanza2_idx` ON `medictory`.`Farmaco Cliente` (`possessore` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `medictory`.`Riciclo`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `medictory`.`Riciclo` ;

CREATE TABLE IF NOT EXISTS `medictory`.`Riciclo` (
  `cliente` VARCHAR(45) NOT NULL,
  `farmaco` VARCHAR(45) NOT NULL,
  `scadenza` DATE NOT NULL,
  `verifica` ENUM('si', 'no') NULL,
  PRIMARY KEY (`cliente`, `farmaco`, `scadenza`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `medictory`.`Evento`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `medictory`.`Evento` ;

CREATE TABLE IF NOT EXISTS `medictory`.`Evento` (
  `nome` VARCHAR(100) NOT NULL,
  `farmacia` VARCHAR(45) NOT NULL,
  `descrizione` VARCHAR(500) NOT NULL,
  `inizio` DATE NOT NULL,
  `fine` DATE NOT NULL,
  `premio` VARCHAR(200) NOT NULL,
  `livello richiesto` INT NOT NULL DEFAULT 0,
  `vincitore` VARCHAR(45) NULL,
  PRIMARY KEY (`nome`, `farmacia`),
  CONSTRAINT `ev1`
    FOREIGN KEY (`vincitore`)
    REFERENCES `medictory`.`Cliente` (`username`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `ev2`
    FOREIGN KEY (`farmacia`)
    REFERENCES `medictory`.`Farmacia` (`username`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `ev2_idx` ON `medictory`.`Evento` (`farmacia` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `medictory`.`Adesioni Evento`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `medictory`.`Adesioni Evento` ;

CREATE TABLE IF NOT EXISTS `medictory`.`Adesioni Evento` (
  `cliente` VARCHAR(45) NOT NULL,
  `evento` VARCHAR(100) NOT NULL,
  `organizzatore` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`cliente`, `evento`, `organizzatore`),
  CONSTRAINT `ad1`
    FOREIGN KEY (`cliente`)
    REFERENCES `medictory`.`Cliente` (`username`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `ad2`
    FOREIGN KEY (`evento` , `organizzatore`)
    REFERENCES `medictory`.`Evento` (`nome` , `farmacia`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `ad2_idx` ON `medictory`.`Adesioni Evento` (`evento` ASC, `organizzatore` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `medictory`.`Ritiro`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `medictory`.`Ritiro` ;

CREATE TABLE IF NOT EXISTS `medictory`.`Ritiro` (
  `nome` VARCHAR(45) NOT NULL,
  `citta` VARCHAR(45) NOT NULL,
  `indirizzo` VARCHAR(45) NOT NULL,
  `data` VARCHAR(45) NOT NULL,
  `farmacia` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`nome`, `citta`, `indirizzo`, `data`, `farmacia`, `email`),
  CONSTRAINT `r1`
    FOREIGN KEY (`nome`)
    REFERENCES `medictory`.`Cliente` (`username`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `r2`
    FOREIGN KEY (`farmacia`)
    REFERENCES `medictory`.`Farmacia` (`username`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = '	';

CREATE INDEX `r2_idx` ON `medictory`.`Ritiro` (`farmacia` ASC) VISIBLE;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `medictory`.`Utenti`
-- -----------------------------------------------------
START TRANSACTION;
USE `medictory`;
INSERT INTO `medictory`.`Utenti` (`username`, `password`, `email`) VALUES ('ludovico', 'ludovico', 'ludovico@gmail.com');
INSERT INTO `medictory`.`Utenti` (`username`, `password`, `email`) VALUES ('elisa', 'elisa', 'elisa@gmail.com');
INSERT INTO `medictory`.`Utenti` (`username`, `password`, `email`) VALUES ('marina', 'marina', 'marina@gmail.com');
INSERT INTO `medictory`.`Utenti` (`username`, `password`, `email`) VALUES ('francesca', 'francesca', 'francesca@gmail.com');
INSERT INTO `medictory`.`Utenti` (`username`, `password`, `email`) VALUES ('myfarma', 'Myfarma', 'myfarma@gmail.com');

COMMIT;


-- -----------------------------------------------------
-- Data for table `medictory`.`Farmacia`
-- -----------------------------------------------------
START TRANSACTION;
USE `medictory`;
INSERT INTO `medictory`.`Farmacia` (`username`, `nome`, `indirizzo`) VALUES ('francesca', 'francesca', 'viale san domenico');
INSERT INTO `medictory`.`Farmacia` (`username`, `nome`, `indirizzo`) VALUES ('myfarma', 'MyFarma', 'via garibaldi');

COMMIT;


-- -----------------------------------------------------
-- Data for table `medictory`.`Cliente`
-- -----------------------------------------------------
START TRANSACTION;
USE `medictory`;
INSERT INTO `medictory`.`Cliente` (`username`, `farmacia associata`, `punti`, `livello`) VALUES ('elisa', 'francesca', 20, 1);
INSERT INTO `medictory`.`Cliente` (`username`, `farmacia associata`, `punti`, `livello`) VALUES ('ludovico', 'myfarma', 25, 1);
INSERT INTO `medictory`.`Cliente` (`username`, `farmacia associata`, `punti`, `livello`) VALUES ('marina', 'myfarma', 50, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `medictory`.`Farmaco Farmacia`
-- -----------------------------------------------------
START TRANSACTION;
USE `medictory`;
INSERT INTO `medictory`.`Farmaco Farmacia` (`farmaco`, `possessore`, `quantitativo`, `descrizione`, `scadenza`) VALUES ('OKI', 'francesca', 550, 'antidolorifico', '2022-06-05');
INSERT INTO `medictory`.`Farmaco Farmacia` (`farmaco`, `possessore`, `quantitativo`, `descrizione`, `scadenza`) VALUES ('OKI', 'myfarma', 250, 'antidolorifici', '2023-09-14');
INSERT INTO `medictory`.`Farmaco Farmacia` (`farmaco`, `possessore`, `quantitativo`, `descrizione`, `scadenza`) VALUES ('TACHIFLUDEC', 'francesca', 25, 'raffreddore', '2022-01-01');
INSERT INTO `medictory`.`Farmaco Farmacia` (`farmaco`, `possessore`, `quantitativo`, `descrizione`, `scadenza`) VALUES ('TACHIFLUDEC', 'myfarma', 15, 'raff', '2001-01-01');

COMMIT;


-- -----------------------------------------------------
-- Data for table `medictory`.`Farmaco Cliente`
-- -----------------------------------------------------
START TRANSACTION;
USE `medictory`;
INSERT INTO `medictory`.`Farmaco Cliente` (`farmaco`, `possessore`, `scadenza`, `descrizione`, `quantitativo`, `stato`) VALUES ('OKITASK', 'ludovico', '2022-10-10', 'mal di testa', 10, 'utilizzabile');
INSERT INTO `medictory`.`Farmaco Cliente` (`farmaco`, `possessore`, `scadenza`, `descrizione`, `quantitativo`, `stato`) VALUES ('TACHIPIRINA', 'ludovico', '2020-12-30', 'antipiretico e analgesico', 2, 'scaduto');
INSERT INTO `medictory`.`Farmaco Cliente` (`farmaco`, `possessore`, `scadenza`, `descrizione`, `quantitativo`, `stato`) VALUES ('BIAFIN', 'ludovico', '2021-12-12', 'irritazioni cutanee', 1, 'utilizzabile');
INSERT INTO `medictory`.`Farmaco Cliente` (`farmaco`, `possessore`, `scadenza`, `descrizione`, `quantitativo`, `stato`) VALUES ('AUGMENTIN', 'ludovico', '2023-08-16', 'antibiotico', 3, 'utilizzabile');
INSERT INTO `medictory`.`Farmaco Cliente` (`farmaco`, `possessore`, `scadenza`, `descrizione`, `quantitativo`, `stato`) VALUES ('AUGMENTIN', 'ludovico', '2020-09-06', 'antibiotico', 2, 'smaltito');
INSERT INTO `medictory`.`Farmaco Cliente` (`farmaco`, `possessore`, `scadenza`, `descrizione`, `quantitativo`, `stato`) VALUES ('EUTIROX', 'ludovico', '2018-10-07', 'pasticche per ipotiroidismo', 10, 'verificato');
INSERT INTO `medictory`.`Farmaco Cliente` (`farmaco`, `possessore`, `scadenza`, `descrizione`, `quantitativo`, `stato`) VALUES ('REFLUXAN', 'elisa', '2001-01-01', 'reflusso', 2, 'utilizzabile');
INSERT INTO `medictory`.`Farmaco Cliente` (`farmaco`, `possessore`, `scadenza`, `descrizione`, `quantitativo`, `stato`) VALUES ('BUSCOFEN', 'elisa', '2022-04-06', 'dolori mestruali e mal di testa', 3, 'utilizzabile');
INSERT INTO `medictory`.`Farmaco Cliente` (`farmaco`, `possessore`, `scadenza`, `descrizione`, `quantitativo`, `stato`) VALUES ('BUSCOPAN', 'elisa', '2020-12-13', 'disturbi dello stomaco e delle vie biliari', 5, 'verificato');
INSERT INTO `medictory`.`Farmaco Cliente` (`farmaco`, `possessore`, `scadenza`, `descrizione`, `quantitativo`, `stato`) VALUES ('EUBELL', 'marina', '2021-05-01', 'crema geloni', 1, 'utilizzabile');
INSERT INTO `medictory`.`Farmaco Cliente` (`farmaco`, `possessore`, `scadenza`, `descrizione`, `quantitativo`, `stato`) VALUES ('FLUIMUCIL', 'marina', '2020-01-01', 'raffreddore e influenza', 1, 'utilizzabile');
INSERT INTO `medictory`.`Farmaco Cliente` (`farmaco`, `possessore`, `scadenza`, `descrizione`, `quantitativo`, `stato`) VALUES ('VIVINC', 'marina', '2021-01-06', 'mal di testa', 9, 'scaduto');

COMMIT;


-- -----------------------------------------------------
-- Data for table `medictory`.`Evento`
-- -----------------------------------------------------
START TRANSACTION;
USE `medictory`;
INSERT INTO `medictory`.`Evento` (`nome`, `farmacia`, `descrizione`, `inizio`, `fine`, `premio`, `livello richiesto`, `vincitore`) VALUES ('Natale', 'myfarma', 'woooow', '2020-12-25', '2021-12-25', 'fiori', 0, NULL);
INSERT INTO `medictory`.`Evento` (`nome`, `farmacia`, `descrizione`, `inizio`, `fine`, `premio`, `livello richiesto`, `vincitore`) VALUES ('Capodanno', 'francesca', 'lol', '2020-12-30', '2021-12-30', 'integratori', 5, NULL);
INSERT INTO `medictory`.`Evento` (`nome`, `farmacia`, `descrizione`, `inizio`, `fine`, `premio`, `livello richiesto`, `vincitore`) VALUES ('Pasqua', 'francesca', 'descrivimi', '2021-04-04', '2022-04-04', 'uovo di Pasqua', 0, NULL);
INSERT INTO `medictory`.`Evento` (`nome`, `farmacia`, `descrizione`, `inizio`, `fine`, `premio`, `livello richiesto`, `vincitore`) VALUES ('BlackFriday', 'myfarma', 'scontiii', '2021-11-01', '2022-11-11', 'sconto50%', 0, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `medictory`.`Adesioni Evento`
-- -----------------------------------------------------
START TRANSACTION;
USE `medictory`;
INSERT INTO `medictory`.`Adesioni Evento` (`cliente`, `evento`, `organizzatore`) VALUES ('ludovico', 'Natale', 'myfarma');
INSERT INTO `medictory`.`Adesioni Evento` (`cliente`, `evento`, `organizzatore`) VALUES ('elisa', 'Capodanno', 'francesca');
INSERT INTO `medictory`.`Adesioni Evento` (`cliente`, `evento`, `organizzatore`) VALUES ('marina', 'BlackFriday', 'myfarma');

COMMIT;

