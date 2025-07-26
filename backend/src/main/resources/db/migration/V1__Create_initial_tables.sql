-- V1__Create_initial_tables.sql
-- Script de Creación de Tablas para foro_hub

-- -----------------------------------------------------
-- Tabla `Perfil`
-- -----------------------------------------------------
CREATE TABLE Perfil (
                        id BIGINT NOT NULL AUTO_INCREMENT,
                        nombre VARCHAR(100) NOT NULL UNIQUE,
                        PRIMARY KEY (id)
) ENGINE = InnoDB;


-- -----------------------------------------------------
-- Tabla `Usuario`
-- -----------------------------------------------------
CREATE TABLE Usuario (
                         id BIGINT NOT NULL AUTO_INCREMENT,
                         nombre VARCHAR(255) NOT NULL,
                         correo_electronico VARCHAR(255) NOT NULL UNIQUE, -- Se cambió a snake_case por convención
                         contrasena VARCHAR(255) NOT NULL,
                         PRIMARY KEY (id)
) ENGINE = InnoDB;

-- Tabla de relación N:M entre Usuario y Perfil
CREATE TABLE usuario_perfil (
                                usuario_id BIGINT NOT NULL,
                                perfil_id BIGINT NOT NULL,
                                PRIMARY KEY (usuario_id, perfil_id),
                                CONSTRAINT fk_usuario_perfil_usuario1
                                    FOREIGN KEY (usuario_id)
                                        REFERENCES Usuario (id)
                                        ON DELETE NO ACTION
                                        ON UPDATE NO ACTION,
                                CONSTRAINT fk_usuario_perfil_perfil1
                                    FOREIGN KEY (perfil_id)
                                        REFERENCES Perfil (id)
                                        ON DELETE NO ACTION
                                        ON UPDATE NO ACTION
) ENGINE = InnoDB;


-- -----------------------------------------------------
-- Tabla `Curso`
-- -----------------------------------------------------
CREATE TABLE Curso (
                       id BIGINT NOT NULL AUTO_INCREMENT,
                       nombre VARCHAR(255) NOT NULL UNIQUE,
                       categoria VARCHAR(255) NOT NULL,
                       PRIMARY KEY (id)
) ENGINE = InnoDB;


-- -----------------------------------------------------
-- Tabla `Topico`
-- -----------------------------------------------------
CREATE TABLE Topico (
                        id BIGINT NOT NULL AUTO_INCREMENT,
                        titulo VARCHAR(255) NOT NULL UNIQUE,
                        mensaje TEXT NOT NULL,
                        fecha_creacion DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP, -- Se cambió a snake_case
                        status VARCHAR(50) NOT NULL DEFAULT 'ACTIVO',
                        autor_id BIGINT NOT NULL,
                        curso_id BIGINT NOT NULL,
                        PRIMARY KEY (id),
                        CONSTRAINT fk_Topico_Usuario1
                            FOREIGN KEY (autor_id)
                                REFERENCES Usuario (id)
                                ON DELETE NO ACTION
                                ON UPDATE NO ACTION,
                        CONSTRAINT fk_Topico_Curso1
                            FOREIGN KEY (curso_id)
                                REFERENCES Curso (id)
                                ON DELETE NO ACTION
                                ON UPDATE NO ACTION
) ENGINE = InnoDB;


-- -----------------------------------------------------
-- Tabla `Respuesta`
-- -----------------------------------------------------
CREATE TABLE Respuesta (
                           id BIGINT NOT NULL AUTO_INCREMENT,
                           mensaje TEXT NOT NULL,
                           topico_id BIGINT NOT NULL,
                           fecha_creacion DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP, -- Se cambió a snake_case
                           autor_id BIGINT NOT NULL,
                           solucion TINYINT NOT NULL DEFAULT 0,
                           PRIMARY KEY (id),
                           CONSTRAINT fk_Respuesta_Topico1
                               FOREIGN KEY (topico_id)
                                   REFERENCES Topico (id)
                                   ON DELETE NO ACTION
                                   ON UPDATE NO ACTION,
                           CONSTRAINT fk_Respuesta_Usuario1
                               FOREIGN KEY (autor_id)
                                   REFERENCES Usuario (id)
                                   ON DELETE NO ACTION
                                   ON UPDATE NO ACTION
) ENGINE = InnoDB;