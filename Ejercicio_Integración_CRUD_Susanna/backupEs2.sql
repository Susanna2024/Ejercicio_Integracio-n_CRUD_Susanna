-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema Es2
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema Es2
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `Es2` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `Es2` ;

-- -----------------------------------------------------
-- Table `Es2`.`BaseDatosBibliografica`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Es2`.`BaseDatosBibliografica` (
  `base_datos_biblio` VARCHAR(255) NOT NULL,
  `fechaCreacion` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`base_datos_biblio`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `Es2`.`BaseDatosElearning`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Es2`.`BaseDatosElearning` (
  `base_datos_elearning` VARCHAR(255) NOT NULL,
  `fechaCreacion` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`base_datos_elearning`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `Es2`.`Curso`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Es2`.`Curso` (
  `id_curso` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(255) NULL DEFAULT NULL,
  `codigo` VARCHAR(255) NULL DEFAULT NULL,
  `aula` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id_curso`))
ENGINE = InnoDB
AUTO_INCREMENT = 11
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `Es2`.`Curso_BaseDatosBibliografica`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Es2`.`Curso_BaseDatosBibliografica` (
  `id_curso` INT NOT NULL,
  `base_datos_biblio` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id_curso`, `base_datos_biblio`),
  INDEX `base_datos_biblio` (`base_datos_biblio` ASC) VISIBLE,
  CONSTRAINT `curso_basedatosbibliografica_ibfk_1`
    FOREIGN KEY (`id_curso`)
    REFERENCES `Es2`.`Curso` (`id_curso`),
  CONSTRAINT `curso_basedatosbibliografica_ibfk_2`
    FOREIGN KEY (`base_datos_biblio`)
    REFERENCES `Es2`.`BaseDatosBibliografica` (`base_datos_biblio`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `Es2`.`Curso_BaseDatosElearning`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Es2`.`Curso_BaseDatosElearning` (
  `id_curso` INT NOT NULL,
  `base_datos_elearning` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id_curso`, `base_datos_elearning`),
  INDEX `base_datos_elearning` (`base_datos_elearning` ASC) VISIBLE,
  CONSTRAINT `curso_basedatoselearning_ibfk_1`
    FOREIGN KEY (`id_curso`)
    REFERENCES `Es2`.`Curso` (`id_curso`),
  CONSTRAINT `curso_basedatoselearning_ibfk_2`
    FOREIGN KEY (`base_datos_elearning`)
    REFERENCES `Es2`.`BaseDatosElearning` (`base_datos_elearning`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `Es2`.`Curso_Examen`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Es2`.`Curso_Examen` (
  `id_curso` INT NOT NULL,
  `id_examen` INT NOT NULL,
  PRIMARY KEY (`id_curso`, `id_examen`),
  INDEX `id_examen` (`id_examen` ASC) VISIBLE,
  CONSTRAINT `curso_examen_ibfk_1`
    FOREIGN KEY (`id_curso`)
    REFERENCES `Es2`.`Curso` (`id_curso`),
  CONSTRAINT `curso_examen_ibfk_2`
    FOREIGN KEY (`id_examen`)
    REFERENCES `Es2`.`examen` (`id_examen`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `Es2`.`Tarea`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Es2`.`Tarea` (
  `id_tarea` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(255) NULL DEFAULT NULL,
  `fechaInicio` DATETIME NULL DEFAULT NULL,
  `fechaEntrega` DATETIME NULL DEFAULT NULL,
  `ContenidoTarea` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id_tarea`))
ENGINE = InnoDB
AUTO_INCREMENT = 11
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `Es2`.`Curso_Tarea`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Es2`.`Curso_Tarea` (
  `id_curso` INT NOT NULL,
  `id_tarea` INT NOT NULL,
  PRIMARY KEY (`id_curso`, `id_tarea`),
  INDEX `fk_curso_tarea` (`id_tarea` ASC) VISIBLE,
  CONSTRAINT `curso_tarea_ibfk_1`
    FOREIGN KEY (`id_curso`)
    REFERENCES `Es2`.`Curso` (`id_curso`),
  CONSTRAINT `fk_curso_tarea`
    FOREIGN KEY (`id_tarea`)
    REFERENCES `Es2`.`Tarea` (`id_tarea`)
    ON DELETE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `Es2`.`Usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Es2`.`Usuario` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(250) NOT NULL,
  `apellido` VARCHAR(250) NOT NULL,
  `correo` VARCHAR(250) NOT NULL,
  `telefono` INT NOT NULL,
  `contrasena` VARCHAR(50) NOT NULL,
  `ROL` VARCHAR(50) NOT NULL,
  `autentificacionDosFactores` TINYINT(1) NULL DEFAULT NULL,
  `fechaCreacion` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 11
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `Es2`.`Curso_Usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Es2`.`Curso_Usuario` (
  `id_curso` INT NOT NULL,
  `id` INT NOT NULL,
  PRIMARY KEY (`id_curso`, `id`),
  INDEX `id` (`id` ASC) VISIBLE,
  CONSTRAINT `curso_usuario_ibfk_1`
    FOREIGN KEY (`id_curso`)
    REFERENCES `Es2`.`Curso` (`id_curso`),
  CONSTRAINT `curso_usuario_ibfk_2`
    FOREIGN KEY (`id`)
    REFERENCES `Es2`.`Usuario` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `Es2`.`EXAMEN`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Es2`.`EXAMEN` (
  `id_examen` INT NOT NULL AUTO_INCREMENT,
  `Fecha` DATE NULL DEFAULT NULL,
  `ExamenCalificado` FLOAT NULL DEFAULT NULL,
  PRIMARY KEY (`id_examen`))
ENGINE = InnoDB
AUTO_INCREMENT = 11
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `Es2`.`Profesor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Es2`.`Profesor` (
  `id_profesor` INT NOT NULL AUTO_INCREMENT,
  `Usuario_id` INT NOT NULL,
  PRIMARY KEY (`id_profesor`),
  INDEX `Usuario_id` (`Usuario_id` ASC) VISIBLE,
  CONSTRAINT `profesor_ibfk_1`
    FOREIGN KEY (`Usuario_id`)
    REFERENCES `Es2`.`Usuario` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 31
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `Es2`.`Examen_Profesor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Es2`.`Examen_Profesor` (
  `id_examen` INT NOT NULL,
  `id_profesor` INT NOT NULL,
  PRIMARY KEY (`id_examen`, `id_profesor`),
  INDEX `id_profesor` (`id_profesor` ASC) VISIBLE,
  CONSTRAINT `examen_profesor_ibfk_1`
    FOREIGN KEY (`id_examen`)
    REFERENCES `Es2`.`Examen` (`id_examen`),
  CONSTRAINT `examen_profesor_ibfk_2`
    FOREIGN KEY (`id_profesor`)
    REFERENCES `Es2`.`Profesor` (`id_profesor`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `Es2`.`Examen_Usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Es2`.`Examen_Usuario` (
  `id_examen` INT NOT NULL,
  `id` INT NOT NULL,
  PRIMARY KEY (`id_examen`, `id`),
  INDEX `id` (`id` ASC) VISIBLE,
  CONSTRAINT `examen_usuario_ibfk_1`
    FOREIGN KEY (`id_examen`)
    REFERENCES `Es2`.`Examen` (`id_examen`),
  CONSTRAINT `examen_usuario_ibfk_2`
    FOREIGN KEY (`id`)
    REFERENCES `Es2`.`Usuario` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `Es2`.`Foro`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Es2`.`Foro` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `autor_id` VARCHAR(100) NULL DEFAULT NULL,
  `contenido` VARCHAR(250) NULL DEFAULT NULL,
  `fecha_publicacion` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 11
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `Es2`.`Material`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Es2`.`Material` (
  `Id_material` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NULL DEFAULT NULL,
  `teoria` VARCHAR(255) NULL DEFAULT NULL,
  `fecha` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`Id_material`))
ENGINE = InnoDB
AUTO_INCREMENT = 11
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `Es2`.`Material_curso`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Es2`.`Material_curso` (
  `id_curso` INT NOT NULL,
  `id_material` INT NOT NULL,
  PRIMARY KEY (`id_curso`, `id_material`),
  INDEX `id_material` (`id_material` ASC) VISIBLE,
  CONSTRAINT `material_curso_ibfk_1`
    FOREIGN KEY (`id_curso`)
    REFERENCES `Es2`.`curso` (`id_curso`),
  CONSTRAINT `material_curso_ibfk_2`
    FOREIGN KEY (`id_material`)
    REFERENCES `Es2`.`material` (`Id_material`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `Es2`.`Usuario_Tarea`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Es2`.`Usuario_Tarea` (
  `id` INT NOT NULL,
  `id_tarea` INT NOT NULL,
  PRIMARY KEY (`id`, `id_tarea`),
  INDEX `id_tarea` (`id_tarea` ASC) VISIBLE,
  CONSTRAINT `usuario_tarea_ibfk_1`
    FOREIGN KEY (`id`)
    REFERENCES `Es2`.`Usuario` (`id`),
  CONSTRAINT `usuario_tarea_ibfk_2`
    FOREIGN KEY (`id_tarea`)
    REFERENCES `Es2`.`tarea` (`id_tarea`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
