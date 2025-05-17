-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: May 17, 2025 at 09:34 AM
-- Server version: 9.1.0
-- PHP Version: 8.3.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `pillcheck_database`
--

-- --------------------------------------------------------

--
-- Table structure for table `patient`
--

DROP TABLE IF EXISTS `patient`;
CREATE TABLE IF NOT EXISTS `patient` (
  `cin` varchar(20) NOT NULL,
  `nom` varchar(100) DEFAULT NULL,
  `prenom` varchar(100) DEFAULT NULL,
  `date_naissance` date DEFAULT NULL,
  `telephone` varchar(20) DEFAULT NULL,
  `etat` varchar(50) DEFAULT NULL,
  `sexe` varchar(20) DEFAULT NULL,
  `traitement` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`cin`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `patient`
--

INSERT INTO `patient` (`cin`, `nom`, `prenom`, `date_naissance`, `telephone`, `etat`, `sexe`, `traitement`) VALUES
('234567', 'hfdkfjk', 'jdki', '2025-05-06', '2345678', 'kjhgdfij', 'Femme', 'Traitement C');

-- --------------------------------------------------------

--
-- Table structure for table `rdv`
--

DROP TABLE IF EXISTS `rdv`;
CREATE TABLE IF NOT EXISTS `rdv` (
  `ID_RDV` int NOT NULL,
  `DATE_RDV` datetime DEFAULT NULL,
  `MOTIF_RDV` varchar(30) DEFAULT NULL,
  `STATUT` enum('confirmé','en attente','annulée') DEFAULT NULL,
  `CIN` varchar(7) DEFAULT NULL,
  PRIMARY KEY (`ID_RDV`),
  KEY `CIN` (`CIN`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `traitement`
--

DROP TABLE IF EXISTS `traitement`;
CREATE TABLE IF NOT EXISTS `traitement` (
  `NOM_TRAITEMENT` varchar(20) NOT NULL,
  `TYPE_Traitement` varchar(20) DEFAULT NULL,
  `MALADIE` varchar(30) DEFAULT NULL,
  `DESCRIPTION` varchar(100) DEFAULT NULL,
  `DATE_DEBUT` date DEFAULT NULL,
  `DATE_FIN` date DEFAULT NULL,
  `DUREE_ESTIMEE` varchar(20) DEFAULT NULL,
  `POSOLOGIE` varchar(10) DEFAULT NULL,
  `EFFETS_SECONDAIRES` varchar(100) DEFAULT NULL,
  `ETAT` enum('en cours','suspendue','terminé') DEFAULT NULL,
  `cin` varchar(7) DEFAULT NULL,
  PRIMARY KEY (`NOM_TRAITEMENT`),
  KEY `cin` (`cin`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `utilisateur`
--

DROP TABLE IF EXISTS `utilisateur`;
CREATE TABLE IF NOT EXISTS `utilisateur` (
  `ID_User` int NOT NULL,
  `NOM` varchar(10) NOT NULL,
  `EMAIL` varchar(20) NOT NULL,
  `MOT_DE_PASSE` varchar(50) NOT NULL,
  `ROLE` enum('medecin','infermiere','secretaire') DEFAULT NULL,
  PRIMARY KEY (`ID_User`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
