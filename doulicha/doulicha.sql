-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : mar. 28 fév. 2023 à 20:00
-- Version du serveur : 10.4.27-MariaDB
-- Version de PHP : 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `doulicha`
--

-- --------------------------------------------------------

--
-- Structure de la table `avis`
--

CREATE TABLE `avis` (
  `ID_avis` int(11) NOT NULL,
  `ID_categorie` int(11) NOT NULL,
  `ID_user` int(11) NOT NULL,
  `ID_event` int(11) NOT NULL,
  `ID_logement` int(11) NOT NULL,
  `note_avis` int(11) NOT NULL,
  `contenu_avis` varchar(500) NOT NULL,
  `type_avis` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `categorie_avis`
--

CREATE TABLE `categorie_avis` (
  `ID_categorie` int(11) NOT NULL,
  `nom_categorie` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `commande_produit`
--

CREATE TABLE `commande_produit` (
  `ID_commande` int(11) NOT NULL,
  `ID_user` int(11) NOT NULL,
  `date_commande` date NOT NULL,
  `etat_commande` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `discussion`
--

CREATE TABLE `discussion` (
  `ID_discussion` int(11) NOT NULL,
  `ID_user` int(11) NOT NULL,
  `titre_discussion` varchar(100) NOT NULL,
  `contenu_discussion` varchar(500) NOT NULL,
  `date_discussion` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `discussion`
--

INSERT INTO `discussion` (`ID_discussion`, `ID_user`, `titre_discussion`, `contenu_discussion`, `date_discussion`) VALUES
(1, 1, 'AAAA', 'fddgfdfgfdgfdfgd\n', '2023-02-28'),
(2, 1, 'AAAA', 'dssd', '2023-02-27'),
(3, 2, 'NNNNN', 'AN DDDDDDDDDDDD', '2023-02-20'),
(4, 2, 'WWWWWWWWWW', 'dffdgfdgfdgfd', '2023-02-21'),
(5, 1, 'dfdsgsgsg', 'gfdgfdgdfgfd', '2023-02-20'),
(6, 1, 'fdsfdsfdsf', 'rttrtrytrthhtth', '2023-02-21'),
(7, 1, 'ddkskkdksd', 'sdddsssd', '2023-02-20'),
(8, 1, 'njssqhh', 'qsqs', '2023-02-21'),
(9, 1, 'jshjqhjkjq', 'sqqshsqhlsq', '2023-02-20'),
(10, 1, 'sqdsq', 'sqdsq', '2023-02-20'),
(11, 1, 'sdqsdqds', 'sdqdsds', '2023-02-20'),
(12, 1, 'sqdsqdsdsqd', 'sdsqdsqdsq', '2023-02-20'),
(13, 1, 'dsdqds', 'qsdqssq', '2023-02-20'),
(14, 1, 'dsqdqdsq', 'sqdqdsqd', '2023-02-20'),
(15, 1, 'xwkcknxkncxknlcxklcxn', 'cxxcncxncxncxncxkncxnk', '2023-02-20'),
(16, 1, 'vhhjjvjvjhvj', 'gughhhgjhjh', '2023-02-20'),
(17, 1, 'rezefefef', 'efefefef', '2023-02-20'),
(18, 1, 'erggergergergre', 'rgrdgergergergergege', '2023-02-20'),
(19, 1, 'xsccdcddccdcdc', 'rthfhfghgfjgj', '2023-02-25'),
(20, 1, 'Titre de discussion', 'sccdcdkckcd dcdc cddcdc dcdcd dcdcd    dcdc  dcdc   dcdc  cdcdcd cdcdc cdcdd cdcdc dcdcd dcdcdcc dcdc dcdc dcc dcd cdc dcd dcd dcd cdc dc dcd cdc dc dcd c', '2023-02-21'),
(21, 1, 'Titre Discussion', 'ssdcddcd dcdcd dcdcd dcdc dcdc dcdc dcdcd dcd dcdc dcdc dcdc dcd dcdc dcdcd dcdc dcd cdc dcdc dc dc dc cc c d cd dc dcd c dc dc dc dc dc dc d c dc d d cd c dc dd c dc d cd cd c d cd c dc d cd c d cd c dc dc dc cd c  dc', '2023-02-21'),
(22, 1, 'nnjjkknjkjkj', 'iojjkjkkkkj', '2023-02-21'),
(23, 1, 'dgfgfgfg', 'bvbvbvb', '2023-02-21'),
(24, 1, 'lmllmlm;lml', 'lmll;l;;;;;;;;;;', '2023-02-21'),
(25, 1, 'dsffdsxdv', 'dgsgfdfdgfdg', '2023-02-21'),
(26, 1, 'xcwdcdd', 'dsvdsvsdvd', '2023-02-21'),
(27, 1, 'vdvdvdvvd', 'vdvdvdvdvdvdcv', '2023-02-21'),
(28, 1, 'ddvvdvdsv', 'vdvdvvcxvcx', '2023-02-21'),
(29, 1, 'cxxxxx', 'xcxcxxx', '2023-02-21'),
(30, 1, 'titre', 'contenu', '2023-02-21'),
(31, 1, 'gfgffdgfd', 'gfgffhgdgfd', '2023-02-21'),
(32, 1, 'rgdhdh', 'fdhdhgfdhfg', '2023-02-25'),
(33, 1, 'cxccx', 'xccxxc', '2023-02-26'),
(34, 1, 'fuck you', 'fils de pute', '2023-02-26'),
(35, 1, 'hvhhjhgj', 'fuck', '2023-02-26'),
(36, 1, 'TITLE', 'fsfdssf', '2023-02-26'),
(37, 1, 'vcvcv', 'hitler', '2023-02-26'),
(38, 1, 'cvc', 'what do you want man', '2023-02-26'),
(39, 1, 'sddfdsds', 'fgfgfgfgfd', '2023-02-28'),
(40, 1, 'fddfffd', 'dfdfffdfdfd', '2023-02-27'),
(41, 1, 'dddvdv', 'hello guys', '2023-02-27'),
(42, 1, 'cxcxcxc', 'xcxcxcxcxcc', '2023-02-27'),
(43, 1, 'c', 'c', '2023-02-27'),
(44, 1, 'ccvcv', 'cvcvcv', '2023-02-27'),
(45, 1, 'ccxvcxv', 'vcxvcx', '2023-02-27'),
(46, 1, 'ojjjji', 'hello', '2023-02-27'),
(47, 1, 'cxcx', 'fhgfhgfhgfhgf', '2023-02-28'),
(48, 1, 'Demande d\'information sur un logement', 'Je suis à la recherche d\'un logement et j\'ai trouvé une annonce pour un appartement sur votre site. Je voudrais en savoir plus sur ce logement avant de prendre une décision.\n\nEst-ce que vous pourriez me donner plus d\'informations sur la taille de l\'appartement, le nombre de chambres et de salles de bain, ainsi que sur les équipements disponibles ? J\'aimerais également savoir si l\'appartement est meublé ou non.', '2023-02-28');

-- --------------------------------------------------------

--
-- Structure de la table `don`
--

CREATE TABLE `don` (
  `ID_don` int(11) NOT NULL,
  `ID_user` int(11) NOT NULL,
  `ID_projet` int(11) NOT NULL,
  `valeur_don` double NOT NULL,
  `date_don` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `evenement`
--

CREATE TABLE `evenement` (
  `ID_event` int(11) NOT NULL,
  `nom_event` varchar(30) NOT NULL,
  `description_event` varchar(150) NOT NULL,
  `lieu_event` varchar(30) NOT NULL,
  `type_event` varchar(30) NOT NULL,
  `dateDebut_event` date NOT NULL,
  `dateFin_event` date NOT NULL,
  `capacite_event` int(11) NOT NULL,
  `nombreActuel_event` int(11) NOT NULL,
  `image_event` varchar(500) NOT NULL,
  `prix_event` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `ligne_commande`
--

CREATE TABLE `ligne_commande` (
  `ID_ligne` int(11) NOT NULL,
  `ID_commande` int(11) NOT NULL,
  `ID_produit` int(11) NOT NULL,
  `quantite_achete_ligne` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `logement`
--

CREATE TABLE `logement` (
  `ID_logement` int(11) NOT NULL,
  `nom_logement` varchar(30) NOT NULL,
  `description_logement` varchar(500) NOT NULL,
  `adresse_logement` varchar(500) NOT NULL,
  `prixNuitee_logement` double NOT NULL,
  `capacite_logement` int(11) NOT NULL,
  `type_logement` varchar(30) NOT NULL,
  `etat_logement` int(11) NOT NULL,
  `image_logement` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `participation_evenement`
--

CREATE TABLE `participation_evenement` (
  `ID_participation` int(11) NOT NULL,
  `ID_user` int(11) NOT NULL,
  `ID_event` int(11) NOT NULL,
  `date_participation` date NOT NULL,
  `nombre_participation` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `produit`
--

CREATE TABLE `produit` (
  `ID_produit` int(11) NOT NULL,
  `libelle_produit` varchar(40) NOT NULL,
  `quantite_produit` int(11) NOT NULL,
  `prixUachat_produit` double NOT NULL,
  `prixUvente_produit` double NOT NULL,
  `categorie_produit` varchar(40) NOT NULL,
  `image_produit` varchar(300) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `projet`
--

CREATE TABLE `projet` (
  `ID_projet` int(11) NOT NULL,
  `nom_projet` varchar(30) NOT NULL,
  `description_projet` varchar(500) NOT NULL,
  `objectif_projet` double NOT NULL,
  `etat_projet` int(11) NOT NULL,
  `image_projet` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `reclamation`
--

CREATE TABLE `reclamation` (
  `ID_reclamation` int(11) NOT NULL,
  `ID_user` int(11) NOT NULL,
  `contenu_reclamation` varchar(500) NOT NULL,
  `date_reclamation` date NOT NULL,
  `etat_reclamation` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `reponse`
--

CREATE TABLE `reponse` (
  `ID_reponse` int(11) NOT NULL,
  `ID_user` int(11) NOT NULL,
  `ID_discussion` int(11) NOT NULL,
  `contenu_reponse` varchar(500) NOT NULL,
  `date_reponse` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `reponse`
--

INSERT INTO `reponse` (`ID_reponse`, `ID_user`, `ID_discussion`, `contenu_reponse`, `date_reponse`) VALUES
(1, 1, 1, 'sefsfsdgdfgdfg', '2023-02-21'),
(2, 2, 1, 'sdssdsds', '2023-02-21'),
(3, 1, 1, 'dfdvdvvd', '2023-02-21'),
(4, 2, 1, 'BBBBBBBBBB', '2023-02-23'),
(9, 1, 1, 'ccxcxxxxxxxxxxxxx', '2023-02-21'),
(10, 2, 1, 'DSTEZYETYRTU56', '2023-02-20'),
(11, 1, 1, 'GFJGITUIUYOIY', '2023-02-20'),
(12, 2, 1, 'hjjhgjvjghg', '2023-02-20'),
(13, 1, 1, 'vdvdvdvv', '2023-02-21'),
(14, 1, 1, 'zedezdzde', '2023-02-20'),
(15, 1, 1, 'sdgdsgdsfds', '2023-02-20'),
(18, 1, 1, 'dzsddsddsds', '2023-02-20'),
(20, 1, 1, ',k,;,;,', '2023-02-20'),
(21, 1, 1, 'ffdfssssssss', '2023-02-20'),
(22, 1, 1, 'fgfgffffg', '2023-02-21'),
(23, 1, 1, 'fggggggggggggggggggg', '2023-02-21'),
(24, 1, 1, 'dvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv', '2023-02-21'),
(25, 1, 1, 'ghasssseneeeee', '2023-02-21'),
(26, 1, 1, 'fgrgf', '2023-02-21'),
(27, 1, 1, 'effdff', '2023-02-21'),
(28, 1, 1, 'fdfdfdfdfd', '2023-02-21'),
(29, 1, 1, ' c c c c c  c', '2023-02-21'),
(30, 1, 1, ',nnbibbibibiby', '2023-02-21'),
(31, 1, 1, ';,,,,,,,,,,,,,,,,,,,', '2023-02-21'),
(32, 1, 2, 'nhiuhuhuhub', '2023-02-21'),
(33, 1, 1, ',jnknjnjknjkkjn', '2023-02-21'),
(34, 1, 2, 'kppppppppppppppppppppp', '2023-02-21'),
(35, 1, 3, 'fbbfbbg', '2023-02-21'),
(36, 1, 7, 'fgfbfgfgfg', '2023-02-21'),
(37, 1, 1, 'dccccc', '2023-02-21'),
(38, 1, 1, 'dvdbdbfgnfgnghfn', '2023-02-25'),
(39, 1, 14, 'bhbbbjkjk', '2023-02-25'),
(40, 1, 3, 'rgfgfgf', '2023-02-27'),
(41, 1, 1, 'cccc', '2023-02-27'),
(42, 1, 48, 'Bien sûr, nous serions ravis de vous donner plus d\'informations sur l\'appartement qui vous intéresse. Il s\'agit d\'un appartement de 3 pièces, comprenant deux chambres et deux salles de bain. L\'appartement est équipé d\'une cuisine équipée, d\'un lave-linge et d\'un sèche-linge. Malheureusement, l\'appartement n\'est pas meublé.', '2023-02-28'),
(43, 1, 48, 'Nous serions heureux de répondre à toutes vos questions concernant l\'appartement en question. Il s\'agit d\'un appartement de 4 pièces, comprenant trois chambres et deux salles de bain. L\'appartement dispose d\'une cuisine équipée, d\'un balcon et d\'un accès à une piscine commune. L\'appartement est partiellement meublé avec des meubles de base.', '2023-02-28'),
(44, 1, 48, 'Nous sommes heureux que vous soyez intéressé par notre annonce. L\'appartement en question est un studio avec une salle de bain. Il dispose d\'une kitchenette équipée, d\'un balcon avec vue sur la ville et d\'une buanderie commune. L\'appartement est entièrement meublé avec des meubles modernes et fonctionnels.\n', '2023-02-28'),
(45, 1, 48, 'JESUS IS PERVERT', '2023-02-28');

-- --------------------------------------------------------

--
-- Structure de la table `reservation_logement`
--

CREATE TABLE `reservation_logement` (
  `ID_reservation` int(11) NOT NULL,
  `ID_user` int(11) NOT NULL,
  `ID_logement` int(11) NOT NULL,
  `dateArrivee_reservation` date NOT NULL,
  `dateDepart_reservation` date NOT NULL,
  `nbPersonnes_reservation` int(11) NOT NULL,
  `montantTotal_reservation` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

CREATE TABLE `utilisateur` (
  `ID_user` int(11) NOT NULL,
  `nom_user` varchar(30) NOT NULL,
  `prenom_user` varchar(30) NOT NULL,
  `email_user` varchar(30) NOT NULL,
  `mdp_user` varchar(30) NOT NULL,
  `role_user` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `utilisateur`
--

INSERT INTO `utilisateur` (`ID_user`, `nom_user`, `prenom_user`, `email_user`, `mdp_user`, `role_user`) VALUES
(1, 'Nom', 'Prenom', 'D@D.c', 'DKIOIJIJNNIJIJ', 'client'),
(2, 'DD', 'DD', 'D@D.c', 'DKIOIJIJNNIJIJ', 'client');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `avis`
--
ALTER TABLE `avis`
  ADD PRIMARY KEY (`ID_avis`),
  ADD KEY `fk_avis_utilisateur` (`ID_user`),
  ADD KEY `fk_avis_logement` (`ID_logement`),
  ADD KEY `fk_avis_evenement` (`ID_event`),
  ADD KEY `fk_avis_categorie` (`ID_categorie`);

--
-- Index pour la table `categorie_avis`
--
ALTER TABLE `categorie_avis`
  ADD PRIMARY KEY (`ID_categorie`);

--
-- Index pour la table `commande_produit`
--
ALTER TABLE `commande_produit`
  ADD PRIMARY KEY (`ID_commande`),
  ADD KEY `fk_commande_utilisateur` (`ID_user`);

--
-- Index pour la table `discussion`
--
ALTER TABLE `discussion`
  ADD PRIMARY KEY (`ID_discussion`),
  ADD KEY `fk_discussion_utilisateur` (`ID_user`);

--
-- Index pour la table `don`
--
ALTER TABLE `don`
  ADD PRIMARY KEY (`ID_don`),
  ADD KEY `fk_don_projet` (`ID_projet`),
  ADD KEY `fk_don_utilisateur` (`ID_user`);

--
-- Index pour la table `evenement`
--
ALTER TABLE `evenement`
  ADD PRIMARY KEY (`ID_event`);

--
-- Index pour la table `ligne_commande`
--
ALTER TABLE `ligne_commande`
  ADD PRIMARY KEY (`ID_ligne`),
  ADD KEY `fk_ligne_produit` (`ID_produit`),
  ADD KEY `fk_ligne_commande` (`ID_commande`);

--
-- Index pour la table `logement`
--
ALTER TABLE `logement`
  ADD PRIMARY KEY (`ID_logement`);

--
-- Index pour la table `participation_evenement`
--
ALTER TABLE `participation_evenement`
  ADD PRIMARY KEY (`ID_participation`),
  ADD KEY `fk_participation_evenement` (`ID_event`),
  ADD KEY `fk_participation_utilisateur` (`ID_user`);

--
-- Index pour la table `produit`
--
ALTER TABLE `produit`
  ADD PRIMARY KEY (`ID_produit`);

--
-- Index pour la table `projet`
--
ALTER TABLE `projet`
  ADD PRIMARY KEY (`ID_projet`);

--
-- Index pour la table `reclamation`
--
ALTER TABLE `reclamation`
  ADD PRIMARY KEY (`ID_reclamation`),
  ADD KEY `fk_reclamation_utilisateur` (`ID_user`);

--
-- Index pour la table `reponse`
--
ALTER TABLE `reponse`
  ADD PRIMARY KEY (`ID_reponse`),
  ADD KEY `fk_reponse_utilisateur` (`ID_user`),
  ADD KEY `fk_reponse_discussion` (`ID_discussion`);

--
-- Index pour la table `reservation_logement`
--
ALTER TABLE `reservation_logement`
  ADD PRIMARY KEY (`ID_reservation`),
  ADD KEY `fk_reservation_utilisateur` (`ID_user`),
  ADD KEY `fk_reservation_logement` (`ID_logement`);

--
-- Index pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  ADD PRIMARY KEY (`ID_user`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `avis`
--
ALTER TABLE `avis`
  MODIFY `ID_avis` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `categorie_avis`
--
ALTER TABLE `categorie_avis`
  MODIFY `ID_categorie` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `commande_produit`
--
ALTER TABLE `commande_produit`
  MODIFY `ID_commande` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `discussion`
--
ALTER TABLE `discussion`
  MODIFY `ID_discussion` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=49;

--
-- AUTO_INCREMENT pour la table `don`
--
ALTER TABLE `don`
  MODIFY `ID_don` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `evenement`
--
ALTER TABLE `evenement`
  MODIFY `ID_event` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `ligne_commande`
--
ALTER TABLE `ligne_commande`
  MODIFY `ID_ligne` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `logement`
--
ALTER TABLE `logement`
  MODIFY `ID_logement` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `participation_evenement`
--
ALTER TABLE `participation_evenement`
  MODIFY `ID_participation` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `produit`
--
ALTER TABLE `produit`
  MODIFY `ID_produit` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `projet`
--
ALTER TABLE `projet`
  MODIFY `ID_projet` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `reclamation`
--
ALTER TABLE `reclamation`
  MODIFY `ID_reclamation` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `reponse`
--
ALTER TABLE `reponse`
  MODIFY `ID_reponse` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=46;

--
-- AUTO_INCREMENT pour la table `reservation_logement`
--
ALTER TABLE `reservation_logement`
  MODIFY `ID_reservation` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  MODIFY `ID_user` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `avis`
--
ALTER TABLE `avis`
  ADD CONSTRAINT `fk_avis_categorie` FOREIGN KEY (`ID_categorie`) REFERENCES `categorie_avis` (`ID_categorie`) ON DELETE CASCADE,
  ADD CONSTRAINT `fk_avis_evenement` FOREIGN KEY (`ID_event`) REFERENCES `evenement` (`ID_event`) ON DELETE CASCADE,
  ADD CONSTRAINT `fk_avis_logement` FOREIGN KEY (`ID_logement`) REFERENCES `logement` (`ID_logement`) ON DELETE CASCADE,
  ADD CONSTRAINT `fk_avis_utilisateur` FOREIGN KEY (`ID_user`) REFERENCES `utilisateur` (`ID_user`) ON DELETE CASCADE;

--
-- Contraintes pour la table `commande_produit`
--
ALTER TABLE `commande_produit`
  ADD CONSTRAINT `fk_commande_utilisateur` FOREIGN KEY (`ID_user`) REFERENCES `utilisateur` (`ID_user`) ON DELETE CASCADE;

--
-- Contraintes pour la table `discussion`
--
ALTER TABLE `discussion`
  ADD CONSTRAINT `fk_discussion_utilisateur` FOREIGN KEY (`ID_user`) REFERENCES `utilisateur` (`ID_user`) ON DELETE CASCADE;

--
-- Contraintes pour la table `don`
--
ALTER TABLE `don`
  ADD CONSTRAINT `fk_don_projet` FOREIGN KEY (`ID_projet`) REFERENCES `projet` (`ID_projet`) ON DELETE CASCADE,
  ADD CONSTRAINT `fk_don_utilisateur` FOREIGN KEY (`ID_user`) REFERENCES `utilisateur` (`ID_user`) ON DELETE CASCADE;

--
-- Contraintes pour la table `ligne_commande`
--
ALTER TABLE `ligne_commande`
  ADD CONSTRAINT `fk_ligne_commande` FOREIGN KEY (`ID_commande`) REFERENCES `commande_produit` (`ID_commande`) ON DELETE CASCADE,
  ADD CONSTRAINT `fk_ligne_produit` FOREIGN KEY (`ID_produit`) REFERENCES `produit` (`ID_produit`) ON DELETE CASCADE;

--
-- Contraintes pour la table `participation_evenement`
--
ALTER TABLE `participation_evenement`
  ADD CONSTRAINT `fk_participation_evenement` FOREIGN KEY (`ID_event`) REFERENCES `evenement` (`ID_event`) ON DELETE CASCADE,
  ADD CONSTRAINT `fk_participation_utilisateur` FOREIGN KEY (`ID_user`) REFERENCES `utilisateur` (`ID_user`) ON DELETE CASCADE;

--
-- Contraintes pour la table `reclamation`
--
ALTER TABLE `reclamation`
  ADD CONSTRAINT `fk_reclamation_utilisateur` FOREIGN KEY (`ID_user`) REFERENCES `utilisateur` (`ID_user`) ON DELETE CASCADE;

--
-- Contraintes pour la table `reponse`
--
ALTER TABLE `reponse`
  ADD CONSTRAINT `fk_reponse_discussion` FOREIGN KEY (`ID_discussion`) REFERENCES `discussion` (`ID_discussion`) ON DELETE CASCADE,
  ADD CONSTRAINT `fk_reponse_utilisateur` FOREIGN KEY (`ID_user`) REFERENCES `utilisateur` (`ID_user`) ON DELETE CASCADE;

--
-- Contraintes pour la table `reservation_logement`
--
ALTER TABLE `reservation_logement`
  ADD CONSTRAINT `fk_reservation_logement` FOREIGN KEY (`ID_logement`) REFERENCES `logement` (`ID_logement`) ON DELETE CASCADE,
  ADD CONSTRAINT `fk_reservation_utilisateur` FOREIGN KEY (`ID_user`) REFERENCES `utilisateur` (`ID_user`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
