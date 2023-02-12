-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : jeu. 09 fév. 2023 à 17:17
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
  `image_event` varchar(500) NOT NULL
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
  `ID_evenement` int(11) NOT NULL,
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
  ADD KEY `fk_participation_evenement` (`ID_evenement`),
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
  MODIFY `ID_discussion` int(11) NOT NULL AUTO_INCREMENT;

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
  MODIFY `ID_reponse` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `reservation_logement`
--
ALTER TABLE `reservation_logement`
  MODIFY `ID_reservation` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  MODIFY `ID_user` int(11) NOT NULL AUTO_INCREMENT;

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
  ADD CONSTRAINT `fk_participation_evenement` FOREIGN KEY (`ID_evenement`) REFERENCES `evenement` (`ID_event`) ON DELETE CASCADE,
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