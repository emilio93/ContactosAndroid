SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "-06:00";
CREATE DATABASE IF NOT EXISTS `contactos` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `contactos`;

--.....................Procedimientos.Almacenados
--...............................................
--...............................................

DELIMITER $

PROCEDURE `addContacto`
(IN `pnombre` VARCHAR(255), IN `papellido` VARCHAR(255),
IN `pdireccion` VARCHAR(255), IN `pimagen` VARCHAR(255))
BEGIN
    INSERT INTO contactos(nombre, apellido, direccion, imagen)
    VALUES(pnombre, papellido, pdireccion, pimagen);
END$

PROCEDURE `addEmail`
(IN pidContacto INT, IN pemail varchar(255),
IN ptipo varchar(255))
BEGIN
    INSERT INTO emails(idContacto, email, tipo)
    VALUES(pidContacto, pemail, ptipo);
END$

PROCEDURE `addTelefono`
(IN pidContacto INT, IN pemail varchar(255), IN ptipo varchar(255))
BEGIN
    INSERT INTO telefonos(idContacto, email, tipo)
    VALUES(pidContacto, pemail, ptipo);
END$

PROCEDURE `deleteContacto`
(IN `pid` INT)
BEGIN
    DELETE FROM contactos
    WHERE id = pid;
END$

PROCEDURE `getContactos`()
BEGIN
    SELECT * FROM contactos;
END$

PROCEDURE `getEmails`
(IN `pidContacto` INT)
BEGIN
    SELECT tipo, email FROM emails
    WHERE idContacto = pidContacto;
END$

PROCEDURE `getTelefonos`
(IN `pidContacto` INT)
BEGIN
    SELECT telefono, tipo FROM telefonos
    WHERE idContacto = pidContacto;
END$

PROCEDURE `updateContacto`
(IN `pid` INT, IN `pnombre` VARCHAR(255), IN `papellido` VARCHAR(255),
IN `pdireccion` VARCHAR(255), IN `pimagen` VARCHAR(255))
BEGIN
    UPDATE contactos
    SET nombre=pnombre, apellido=papellido,
        direccion=pdireccion, imagen=pimagen
    WHERE id = pid;
END$

PROCEDURE `updateEmails`
(IN `pid` INT, IN `pemail` VARCHAR(255),
IN `ptipo` VARCHAR(255))
BEGIN
    UPDATE emails
    SET email=pemail, tipo=ptipo
    WHERE id=pid;
END$

PROCEDURE `updateTelefono`
(IN `pid` INT, IN `ptelefono` VARCHAR(255),
IN `ptipo` VARCHAR(255))
BEGIN
    UPDATE telefonos
    SET telefono=ptelefono, tipo=ptipo
    WHERE id=pid;
END$

DELIMITER ;
--.....................Procedimientos.Almacenados
--...............................................
--...............................................

DROP TABLE IF EXISTS `contactos`;
CREATE TABLE IF NOT EXISTS `contactos` (
    `id` int(11) NOT NULL,
    `nombre` varchar(255) NOT NULL,
    `apellido` varchar(255) NOT NULL,
    `direccion` text,
    `imagen` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `emails`;
CREATE TABLE IF NOT EXISTS `emails` (
    `id` int(11) NOT NULL,
    `idContacto` int(11) NOT NULL,
    `tipo` varchar(255) NOT NULL,
    `email` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `telefonos`;
CREATE TABLE IF NOT EXISTS `telefonos` (
    `id` int(11) NOT NULL,
    `idContacto` int(11) NOT NULL,
    `tipo` varchar(255) NOT NULL,
    `telefono` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


ALTER TABLE `contactos`
 ADD PRIMARY KEY (`id`), ADD UNIQUE KEY `uqContacto` (`nombre`,`apellido`);

ALTER TABLE `emails`
 ADD PRIMARY KEY (`id`), ADD KEY `fkContactoEmails` (`idContacto`);

ALTER TABLE `telefonos`
 ADD PRIMARY KEY (`id`), ADD KEY `fkContactoTelefonos` (`idContacto`);

ALTER TABLE `contactos`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
ALTER TABLE `emails`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
ALTER TABLE `telefonos`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

ALTER TABLE `emails`
ADD CONSTRAINT `fkContactoEmails` FOREIGN KEY (`idContacto`)
REFERENCES `contactos` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE `telefonos`
ADD CONSTRAINT `fkContactoTelefonos` FOREIGN KEY (`idContacto`)
REFERENCES `contactos` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
