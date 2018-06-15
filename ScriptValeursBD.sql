-- phpMyAdmin SQL Dump
-- version 4.7.9
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  mar. 15 mai 2018 à 22:39
-- Version du serveur :  5.7.21
-- Version de PHP :  5.6.35

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `libiavelo`
--

DROP TABLE IF EXISTS `ordre_transport`;
DROP TABLE IF EXISTS `fiche_reparation`;
DROP TABLE IF EXISTS `employe`;
DROP TABLE IF EXISTS `historique`;
DROP TABLE IF EXISTS `atelier`;
DROP TABLE IF EXISTS `velo`;
DROP TABLE IF EXISTS `entreprise`;
DROP TABLE IF EXISTS `station`;
DROP TABLE IF EXISTS `localite`;

-- --------------------------------------------------------

--
-- Structure de la table `atelier`
--

CREATE TABLE IF NOT EXISTS `atelier` (
  `idAtelier` int(11) NOT NULL AUTO_INCREMENT,
  `lieu` varchar(45) NOT NULL,
  PRIMARY KEY (`idAtelier`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `atelier`
--

INSERT INTO `atelier` (`idAtelier`, `lieu`) VALUES
(1, 'Bomel'),
(2, 'Salzinne');

-- --------------------------------------------------------

--
-- Structure de la table `employe`
--

CREATE TABLE IF NOT EXISTS `employe` (
  `idEmploye` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(45) NOT NULL,
  `prenom` varchar(45) NOT NULL,
  `initialPrenomSupp` char(3) DEFAULT NULL,
  `dateEmbauche` date NOT NULL,
  `telephone` int(11) DEFAULT NULL,
  `telephonePro` int(11) NOT NULL,
  `email` varchar(45) DEFAULT NULL,
  `dateNaissance` date NOT NULL,
  `estATempsPartiel` tinyint(1) NOT NULL,
  `libelleRue` varchar(45) NOT NULL,
  `numRue` int(11) NOT NULL,
  `typeEmploi` varchar(45) NOT NULL,
  `nbVeloDeclasse` int(11) DEFAULT NULL,
  `permiPlateau` tinyint(1) DEFAULT NULL,
  `estResponsableZone` tinyint(1) DEFAULT NULL,
  `id_Atelier` int(11) DEFAULT NULL,
  `id_Localite` int(11) NOT NULL,
  `id_Station` int(11) DEFAULT NULL,
  `responsable_Employe` int(11) DEFAULT NULL,
  PRIMARY KEY (`idEmploye`),
  KEY `Employe_identifiant_Atelier_fk` (`id_Atelier`),
  KEY `Employe_id_Station_fk` (`id_Station`),
  KEY `Employe_id_Localite_fk` (`id_Localite`),
  KEY `Employe_responsable_Employe_fk` (`responsable_Employe`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `employe`
--

INSERT INTO `employe` (`idEmploye`, `nom`, `prenom`, `initialPrenomSupp`, `dateEmbauche`, `telephone`, `telephonePro`, `email`, `dateNaissance`, `estATempsPartiel`, `libelleRue`, `numRue`, `typeEmploi`, `nbVeloDeclasse`, `permiPlateau`, `estResponsableZone`, `id_Atelier`, `id_Localite`, `id_Station`, `responsable_Employe`) VALUES
(1, 'Culot', 'Jet', 'LF', '2010-09-01', 485194837, 81695000, 'jet@gmail.com', '1993-12-21', 0, 'Rue du Cimetiere', 2, 'Chef Atelier', NULL, NULL, NULL, 1, 3, NULL, NULL),
(2, 'Carlier', 'Carlus', NULL, '2012-03-30', NULL, 81725000, NULL, '1994-02-07', 0, 'Rue Roger de Keyser', 6, 'Reparateur', 83, 0, 0, 1, 4, NULL, 1),
(3, 'Ginet', 'Max', 'C', '2011-10-14', 486245167, 81715000, 'max.ginett@gmail.com', '1993-05-27', 1, 'Rue de Bruxelles', 45, 'Livreur', NULL, 1, 0, NULL, 1, NULL, NULL),
(4, 'LaSaumure', 'Dodo', NULL, '2017-07-15', 492451256, 81745000, 'dodo@gmail.com', '1996-01-22', 0, 'Quai Ferdinand Courtois', 1, 'Prepose', NULL, NULL, 1, NULL, 1, 1, 3);

-- --------------------------------------------------------

--
-- Structure de la table `entreprise`
--

CREATE TABLE IF NOT EXISTS `entreprise` (
  `idEntreprise` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(45) NOT NULL,
  `lieu` varchar(45) NOT NULL,
  PRIMARY KEY (`idEntreprise`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `entreprise`
--

INSERT INTO `entreprise` (`idEntreprise`, `nom`, `lieu`) VALUES
(1, 'Scott', 'Liège'),
(2, 'Focus', 'Chimay'),
(3, 'GT', 'Mons'),
(4, 'Rockrider', 'Anvers');

-- --------------------------------------------------------

--
-- Structure de la table `fiche_reparation`
--

CREATE TABLE IF NOT EXISTS `fiche_reparation` (
  `idFiche` int(11) NOT NULL AUTO_INCREMENT,
  `dateDebut` date NOT NULL,
  `dateFin` date NOT NULL,
  `remarque` varchar(45) DEFAULT NULL,
  `ordreTravail` varchar(45) DEFAULT NULL,
  `estDeclasse` tinyint(1) NOT NULL,
  `id_Velo` int(11) NOT NULL,
  `id_Atelier` int(11) DEFAULT NULL,
  `valide` tinyint(1) NOT NULL,
  PRIMARY KEY (`idFiche`),
  KEY `Fiche_Reparation_numVelo_Velo_fk` (`id_Velo`),
  KEY `Fiche_Reparation_identifiant_Atelier_fk` (`id_Atelier`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `fiche_reparation`
--

INSERT INTO `fiche_reparation` (`idFiche`, `dateDebut`, `dateFin`, `remarque`, `ordreTravail`, `estDeclasse`, `id_Velo`, `id_Atelier`, `valide`) VALUES
(1, '2018-04-05', '2018-04-06', NULL, 'Changer plaquettes freins', 0, 1, 1, 0),
(2, '2012-12-22', '2012-12-22', 'usure du temps', 'réparer le cadre', 1, 2, 2, 0);

-- --------------------------------------------------------

--
-- Structure de la table `historique`
--

CREATE TABLE IF NOT EXISTS `historique` (
  `idHistorique` int(11) NOT NULL AUTO_INCREMENT,
  `dateArrivee` date NOT NULL,
  `dateDepart` date NOT NULL,
  `id_Station` int(11) NOT NULL,
  `id_Velo` int(11) NOT NULL,
  PRIMARY KEY (`idHistorique`),
  KEY `Historique_numVelo_Velo_fk` (`id_Velo`),
  KEY `Historique_identifiant_Station_fk` (`id_Station`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `historique`
--

INSERT INTO `historique` (`idHistorique`, `dateArrivee`, `dateDepart`, `id_Station`, `id_Velo`) VALUES
(1, '2015-04-17', '2017-04-18', 1, 1),
(2, '2018-01-05', '2018-01-05', 1, 2),
(3, '2017-02-15', '2017-02-21', 2, 3),
(4, '2012-02-22', '2017-02-22', 3, 3);

-- --------------------------------------------------------

--
-- Structure de la table `localite`
--

CREATE TABLE IF NOT EXISTS `localite` (
  `idLocalite` int(11) NOT NULL AUTO_INCREMENT,
  `libelle` varchar(45) NOT NULL,
  `codePostal` int(11) NOT NULL,
  PRIMARY KEY (`idLocalite`),
  UNIQUE KEY `localite_libelleCodePostal_unique` (`libelle`,`codePostal`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `localite`
--

INSERT INTO `localite` (`idLocalite`, `libelle`, `codePostal`) VALUES
(4, 'Beez', 5000),
(1, 'Bouge', 5000),
(5, 'Jambes', 5000),
(2, 'Namur', 5000),
(3, 'Salzinnes', 5000);

-- --------------------------------------------------------

--
-- Structure de la table `ordre_transport`
--

CREATE TABLE IF NOT EXISTS `ordre_transport` (
  `idTransport` int(11) NOT NULL AUTO_INCREMENT,
  `dateTransport` date NOT NULL,
  `id_Velo` int(11) NOT NULL,
  `id_Employe` int(11) DEFAULT NULL,
  `id_Station_emettrice` int(11)DEFAULT NULL,
  `id_Station_origine` int(11) DEFAULT NULL,
  `id_Station_destination` int(11) DEFAULT NULL,
  PRIMARY KEY (`idTransport`),
  KEY `Ordre_Transport_numVelo_Velo_fk` (`id_Velo`),
  KEY `Ordre_Transport_id_Station_emettrice_fk` (`id_Station_emettrice`),
  KEY `Ordre_Transport_id_Station_origine_fk` (`id_Station_origine`),
  KEY `Ordre_Transport_id_Station_destination_fk` (`id_Station_destination`),
  KEY `Ordre_Transport_identifiant_Employe_fk` (`id_Employe`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `ordre_transport`
--

INSERT INTO `ordre_transport` (`idTransport`, `dateTransport`, `id_Velo`, `id_Employe`, `id_Station_emettrice`, `id_Station_origine`, `id_Station_destination`) VALUES
(1, '2018-03-06', 1, 3, 4, NULL, 1),
(2, '2018-05-09', 2, 3, 3, NULL, 4);

-- --------------------------------------------------------

--
-- Structure de la table `station`
--

CREATE TABLE IF NOT EXISTS `station` (
  `idStation` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(45) NOT NULL,
  `nbVeloMinNorm` int(11) NOT NULL,
  `nbVeloMinEx` int(11) NOT NULL,
  `nbVeloMaxNorm` int(11) NOT NULL,
  `nbVeloMaxEx` int(11) NOT NULL,
  `dateCreation` date NOT NULL,
  `estCouverte` tinyint(1) NOT NULL,
  `latitude` varchar(10) DEFAULT NULL,
  `longitude` varchar(10)DEFAULT NULL,
  `libelleRue` varchar(45) NOT NULL,
  `id_Localite` int(11) NOT NULL,
  PRIMARY KEY (`idStation`),
  KEY `Station_id_Localite_FK` (`id_Localite`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `station`
--

INSERT INTO `station` (`idStation`, `nom`, `nbVeloMinNorm`, `nbVeloMinEx`, `nbVeloMaxNorm`, `nbVeloMaxEx`, `dateCreation`, `estCouverte`, `latitude`, `longitude`, `libelleRue`, `id_Localite`) VALUES
(1, 'Grognon', 5, 2, 10, 14, '2001-03-10', 1, null, null, 'Rue Notre Dame', 1),
(2, 'Sainte Margueritte', 4, 2, 8, 10, '2016-08-22', 0, '', '', 'Rue Sainte Margueritte', 3),
(3, 'Place Arme', 8, 4, 15, 19, '2012-12-02', 0, '74s', '45e', 'Rue de la Monnaie', 4),
(4, 'Acinapolis', 6, 4, 18, 22, '2017-09-17', 1, '47s', '45,78e', 'Rue de la Confiturerie', 5),
(33, 'Test', 35, 30, 40, 45, '2018-05-15', 1, '41s', '45,55e', 'Test', 4);

-- --------------------------------------------------------

--
-- Structure de la table `velo`
--

CREATE TABLE IF NOT EXISTS `velo` (
  `idVelo` int(11) NOT NULL AUTO_INCREMENT,
  `estEndommage` tinyint(1) NOT NULL,
  `dateAchat` date NOT NULL,
  `id_Station` int(11) DEFAULT NULL,
  `id_Entreprise` int(11) NOT NULL,
  PRIMARY KEY (`idVelo`),
  KEY `Velo_identifiant_Station_fk` (`id_Station`),
  KEY `Velo_nom_Entreprise_fk` (`id_Entreprise`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `velo`
--

INSERT INTO `velo` (`idVelo`, `estEndommage`, `dateAchat`, `id_Station`, `id_Entreprise`) VALUES
(1, 0, '2015-12-22', 1, 1),
(2, 0, '2015-12-22', 2, 1),
(3, 1, '2006-10-10', 3, 2),
(4, 0, '2018-02-12', NULL, 3),
(5, 1, '2001-12-22', NULL, 4);

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `employe`
--
ALTER TABLE `employe`
  ADD CONSTRAINT `Employe_id_Localite_fk` FOREIGN KEY (`id_Localite`) REFERENCES `localite` (`idLocalite`),
  ADD CONSTRAINT `Employe_id_Station_fk` FOREIGN KEY (`id_Station`) REFERENCES `station` (`idStation`),
  ADD CONSTRAINT `Employe_identifiant_Atelier_fk` FOREIGN KEY (`id_Atelier`) REFERENCES `atelier` (`idAtelier`),
  ADD CONSTRAINT `Employe_responsable_Employe_fk` FOREIGN KEY (`responsable_Employe`) REFERENCES `employe` (`idEmploye`);

--
-- Contraintes pour la table `fiche_reparation`
--
ALTER TABLE `fiche_reparation`
  ADD CONSTRAINT `Fiche_Reparation_identifiant_Atelier_fk` FOREIGN KEY (`id_Atelier`) REFERENCES `atelier` (`idAtelier`),
  ADD CONSTRAINT `Fiche_Reparation_numVelo_Velo_fk` FOREIGN KEY (`id_Velo`) REFERENCES `velo` (`idVelo`);

--
-- Contraintes pour la table `historique`
--
ALTER TABLE `historique`
  ADD CONSTRAINT `Historique_identifiant_Station_fk` FOREIGN KEY (`id_Station`) REFERENCES `station` (`idStation`),
  ADD CONSTRAINT `Historique_numVelo_Velo_fk` FOREIGN KEY (`id_Velo`) REFERENCES `velo` (`idVelo`);

--
-- Contraintes pour la table `ordre_transport`
--
ALTER TABLE `ordre_transport`
  ADD CONSTRAINT `Ordre_Transport_id_Station_destination_fk` FOREIGN KEY (`id_Station_destination`) REFERENCES `station` (`idStation`),
  ADD CONSTRAINT `Ordre_Transport_id_Station_emettrice_fk` FOREIGN KEY (`id_Station_emettrice`) REFERENCES `station` (`idStation`),
  ADD CONSTRAINT `Ordre_Transport_id_Station_origine_fk` FOREIGN KEY (`id_Station_origine`) REFERENCES `station` (`idStation`),
  ADD CONSTRAINT `Ordre_Transport_identifiant_Employe_fk` FOREIGN KEY (`id_Employe`) REFERENCES `employe` (`idEmploye`),
  ADD CONSTRAINT `Ordre_Transport_numVelo_Velo_fk` FOREIGN KEY (`id_Velo`) REFERENCES `velo` (`idVelo`);

--
-- Contraintes pour la table `station`
--
ALTER TABLE `station`
  ADD CONSTRAINT `Station_id_Localite_FK` FOREIGN KEY (`id_Localite`) REFERENCES `localite` (`idLocalite`);

--
-- Contraintes pour la table `velo`
--
ALTER TABLE `velo`
  ADD CONSTRAINT `Velo_identifiant_Station_fk` FOREIGN KEY (`id_Station`) REFERENCES `station` (`idStation`),
  ADD CONSTRAINT `Velo_nom_Entreprise_fk` FOREIGN KEY (`id_Entreprise`) REFERENCES `entreprise` (`idEntreprise`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
