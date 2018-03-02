-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Erstellungszeit: 23. Feb 2018 um 08:15
-- Server-Version: 10.1.10-MariaDB
-- PHP-Version: 5.6.15

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Datenbank: `mydb`
--

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `benutzer`
--
CREATE DATABASE db_portalumni;
USE db_portalumni;

CREATE TABLE `benutzer` (
  `idBenutzer` int(10) UNSIGNED NOT NULL,
  `FirmenVerwaltung_idFirmenVerwaltung` int(11) NOT NULL,
  `Vorname` varchar(45) NOT NULL,
  `Nachname` varchar(45) NOT NULL,
  `Passwort` varchar(45) NOT NULL,
  `Email` varchar(45) NOT NULL,
  `Admin` tinyint(1) NOT NULL,
  `Newsletter` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Daten für Tabelle `benutzer`
--

INSERT INTO `benutzer` (`idBenutzer`, `FirmenVerwaltung_idFirmenVerwaltung`, `Vorname`, `Nachname`, `Passwort`, `Email`, `Admin`, `Newsletter`) VALUES
(1, 1, 'Admin', 'Absolutum', 'Admin', 'Admin@Admin.ch', 1, 1),
(2, 2, 'Silvan', 'Baach', 'Navlis3', 'Navlis@gmx.com', 0, 1),
(3, 2, 'Hannes', 'Kannes', 'usg45', 'hannes@hotmail.ch', 0, 0),
(4, 4, 'Markus Maria', 'Von Riedmatten', 'Rick&Morty99', 'MM99@gmail.com', 0, 1);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `firma`
--

CREATE TABLE `firma` (
  `idFirma` int(10) UNSIGNED NOT NULL,
  `FirmenVerwaltung_idFirmenVerwaltung` int(11) NOT NULL,
  `Firmenname` varchar(45) NOT NULL,
  `Firmenadresse` varchar(45) NOT NULL,
  `Ort` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Daten für Tabelle `firma`
--

INSERT INTO `firma` (`idFirma`, `FirmenVerwaltung_idFirmenVerwaltung`, `Firmenname`, `Firmenadresse`, `Ort`) VALUES
(1, 1, 'EinederbeidenSchulen', 'Fülldaten', 'Fülldaten'),
(2, 2, 'normalis', 'im Industrieviertel 2', 'innenstdtelhofen'),
(3, 3, 'Marktführer AG', 'Bahnhofstrasse 3', 'Oberhauptstadt'),
(4, 4, 'Monopol GmbH', 'Brückenstrasse 1', 'Oberhauptstadt'),
(5, 5, 'Winzfirma', 'grossstadtstrasse 10', 'Metropolis');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `firmenverwaltung`
--

CREATE TABLE `firmenverwaltung` (
  `idFirmenVerwaltung` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Daten für Tabelle `firmenverwaltung`
--

INSERT INTO `firmenverwaltung` (`idFirmenVerwaltung`) VALUES
(1),
(2),
(3),
(4),
(5);

--
-- Indizes der exportierten Tabellen
--

--
-- Indizes für die Tabelle `benutzer`
--
ALTER TABLE `benutzer`
  ADD PRIMARY KEY (`idBenutzer`,`FirmenVerwaltung_idFirmenVerwaltung`),
  ADD KEY `fk_Benutzer_FirmenVerwaltung_idx` (`FirmenVerwaltung_idFirmenVerwaltung`);

--
-- Indizes für die Tabelle `firma`
--
ALTER TABLE `firma`
  ADD PRIMARY KEY (`idFirma`),
  ADD UNIQUE KEY `Firmenname_UNIQUE` (`Firmenname`),
  ADD UNIQUE KEY `idFirmen_UNIQUE` (`idFirma`),
  ADD KEY `fk_Firma_FirmenVerwaltung1_idx` (`FirmenVerwaltung_idFirmenVerwaltung`);

--
-- Indizes für die Tabelle `firmenverwaltung`
--
ALTER TABLE `firmenverwaltung`
  ADD PRIMARY KEY (`idFirmenVerwaltung`);

--
-- Constraints der exportierten Tabellen
--

--
-- Constraints der Tabelle `benutzer`
--
ALTER TABLE `benutzer`
  ADD CONSTRAINT `fk_Benutzer_FirmenVerwaltung` FOREIGN KEY (`FirmenVerwaltung_idFirmenVerwaltung`) REFERENCES `firmenverwaltung` (`idFirmenVerwaltung`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints der Tabelle `firma`
--
ALTER TABLE `firma`
  ADD CONSTRAINT `fk_Firma_FirmenVerwaltung1` FOREIGN KEY (`FirmenVerwaltung_idFirmenVerwaltung`) REFERENCES `firmenverwaltung` (`idFirmenVerwaltung`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
