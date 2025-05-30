-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: May 30, 2025 at 02:20 PM
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
  `id` int NOT NULL AUTO_INCREMENT,
  `cin` varchar(20) DEFAULT NULL,
  `nom` varchar(100) DEFAULT NULL,
  `prenom` varchar(100) DEFAULT NULL,
  `date_naissance` date DEFAULT NULL,
  `telephone` varchar(20) DEFAULT NULL,
  `etat` varchar(50) DEFAULT NULL,
  `sexe` varchar(20) DEFAULT NULL,
  `traitement` varchar(255) DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`)
) ENGINE=MyISAM AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `patient`
--

INSERT INTO `patient` (`id`, `cin`, `nom`, `prenom`, `date_naissance`, `telephone`, `etat`, `sexe`, `traitement`, `user_id`) VALUES
(3, '88888', 'Merzak', 'Romaissae', '2025-05-06', '66666', 'me3gaza', 'Femme', 'Spasfon', 1),
(4, '33333333', 'Tajmout', 'fatima', '2025-05-02', '0000000', 'Lbsala', 'Homme', 'Spasfon', 1),
(5, '9999', 'elkkhayir', 'samira', '2025-05-19', '55555', 'lmoooot', 'Femme', 'Spasfon', 1),
(6, '9999999', 'bouaaza', 'Hfissa', '2025-05-07', '7777', 'cuteness', 'Femme', 'Spasfon', 1),
(7, '555555555', 'sadouki', 'meryem', '2025-07-24', '777777', 'Ne3asa', 'Femme', 'Doliprane', 1),
(11, '33333333', 'EEEEEEEE', 'EEEEE', '2025-05-21', '888888888', 'BBBB', 'Femme', 'Doliprane', 0),
(9, '88888888', 'bellafkih', 'safae', '2005-01-15', '0000000000', 'migraine', 'Femme', 'Spasfon', 1);

-- --------------------------------------------------------

--
-- Table structure for table `rdv`
--

DROP TABLE IF EXISTS `rdv`;
CREATE TABLE IF NOT EXISTS `rdv` (
  `id` int NOT NULL AUTO_INCREMENT,
  `DATE_RDV` datetime DEFAULT NULL,
  `MOTIF_RDV` varchar(30) DEFAULT NULL,
  `STATUT` enum('confirmé','en attente','annulée') DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `traitement`
--

DROP TABLE IF EXISTS `traitement`;
CREATE TABLE IF NOT EXISTS `traitement` (
  `id` int NOT NULL AUTO_INCREMENT,
  `NOM_TRAITEMENT` varchar(20) DEFAULT NULL,
  `TYPE_Traitement` varchar(20) DEFAULT NULL,
  `MALADIE` varchar(30) DEFAULT NULL,
  `DESCRIPTION` varchar(100) DEFAULT NULL,
  `DATE_DEBUT` date DEFAULT NULL,
  `DATE_FIN` date DEFAULT NULL,
  `DUREE_ESTIMEE` varchar(20) DEFAULT NULL,
  `POSOLOGIE` varchar(10) DEFAULT NULL,
  `EFFETS_SECONDAIRES` varchar(100) DEFAULT NULL,
  `ETAT` enum('en cours','suspendue','terminé') DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `traitement`
--

INSERT INTO `traitement` (`id`, `NOM_TRAITEMENT`, `TYPE_Traitement`, `MALADIE`, `DESCRIPTION`, `DATE_DEBUT`, `DATE_FIN`, `DUREE_ESTIMEE`, `POSOLOGIE`, `EFFETS_SECONDAIRES`, `ETAT`, `user_id`) VALUES
(1, 'zzzzzzz', 'zzzzzzz', 'zzzzzzzz', 'zzzzzz', '2025-05-29', '2025-06-05', 'zzzzzz', 'zzzzz', 'zzzzzzzz', 'suspendue', 1),
(2, 'PPPPPPP', 'GGGGGGG', 'GGGGGGGG', 'GGGGGGG', '2025-05-30', '2025-06-06', 'GGGGGG', 'GGGGGGG', 'GGGGGGG', 'suspendue', 3);

-- --------------------------------------------------------

--
-- Table structure for table `utilisateur`
--

DROP TABLE IF EXISTS `utilisateur`;
CREATE TABLE IF NOT EXISTS `utilisateur` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nom` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `motDePasse` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `utilisateur`
--

INSERT INTO `utilisateur` (`id`, `nom`, `email`, `motDePasse`) VALUES
(1, 'afnane', 'afnane@gmail.com', 'afnanecoco');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
