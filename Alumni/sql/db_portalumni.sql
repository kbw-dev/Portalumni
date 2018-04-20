-- phpMyAdmin SQL Dump
-- version 4.7.7
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Erstellungszeit: 20. Apr 2018 um 08:55
-- Server-Version: 10.1.30-MariaDB
-- PHP-Version: 7.2.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Datenbank: `db_portalumni`
--

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `agenda`
--

CREATE TABLE `agenda` (
  `beitragID` int(11) NOT NULL,
  `title` varchar(64) NOT NULL,
  `datum` date NOT NULL,
  `ort` varchar(255) NOT NULL,
  `content` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Daten für Tabelle `agenda`
--

INSERT INTO `agenda` (`beitragID`, `title`, `datum`, `ort`, `content`) VALUES
(1, 'yxgadgd', '2018-04-27', 'asgsag', 'sagasgsagsagsa');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `benutzer`
--

CREATE TABLE `benutzer` (
  `userID` int(10) UNSIGNED NOT NULL,
  `FirmenVerwaltung_idFirmenVerwaltung` int(11) NOT NULL,
  `Vorname` varchar(45) NOT NULL,
  `Nachname` varchar(45) NOT NULL,
  `Username` varchar(40) NOT NULL,
  `Passwort` varchar(45) NOT NULL,
  `Email` varchar(45) NOT NULL,
  `EmailPassword` varchar(100) NOT NULL,
  `Admin` tinyint(1) NOT NULL,
  `Newsletter` tinyint(1) NOT NULL,
  `zugelassen` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Daten für Tabelle `benutzer`
--

INSERT INTO `benutzer` (`userID`, `FirmenVerwaltung_idFirmenVerwaltung`, `Vorname`, `Nachname`, `Username`, `Passwort`, `Email`, `EmailPassword`, `Admin`, `Newsletter`, `zugelassen`) VALUES
(2, 2, 'Silvan', 'Baach', 'silvBaach', 'Navlis3', 'Navlis@gmx.com', '', 0, 1, 1),
(10, 5, 'Adel', 'Patkovic', 'adelo', 'adel', 'asfF', 'ASGASG', 1, 1, 1),
(345, 3, 'Maxime', 'Mustermann', 'aslfasölgkö', 'asgsgsa', 'asgasgsa', 'asgsgagsa', 0, 0, 0),
(690, 5, 'Maxi', 'Mustermann', 'maxiMusti', 'KSFKÖLSAKF', 'asffaga', 'asgasg', 0, 0, 0);

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
-- Indizes für die Tabelle `agenda`
--
ALTER TABLE `agenda`
  ADD PRIMARY KEY (`beitragID`);

--
-- Indizes für die Tabelle `benutzer`
--
ALTER TABLE `benutzer`
  ADD PRIMARY KEY (`userID`,`FirmenVerwaltung_idFirmenVerwaltung`),
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
-- AUTO_INCREMENT für exportierte Tabellen
--

--
-- AUTO_INCREMENT für Tabelle `agenda`
--
ALTER TABLE `agenda`
  MODIFY `beitragID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

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
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
