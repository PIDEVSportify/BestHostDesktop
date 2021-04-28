-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : mer. 28 avr. 2021 à 07:57
-- Version du serveur :  5.7.31
-- Version de PHP : 7.4.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `testoauth`
--

-- --------------------------------------------------------

--
-- Structure de la table `activity`
--

DROP TABLE IF EXISTS `activity`;
CREATE TABLE IF NOT EXISTS `activity` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_act` varchar(16) COLLATE utf8mb4_unicode_ci NOT NULL,
  `type` varchar(16) COLLATE utf8mb4_unicode_ci NOT NULL,
  `description` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `date_val` date NOT NULL,
  `categorie` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `id_gerant` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_Email` (`id_gerant`),
  KEY `id_act` (`id_act`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `activity`
--

INSERT INTO `activity` (`id`, `id_act`, `type`, `description`, `date_val`, `categorie`, `id_gerant`) VALUES
(7, 'jaajejea', 'jejajej', 'jajaejjae', '2021-04-29', 'Match foot', 'ahmed.jelassi@esprit.tn');

-- --------------------------------------------------------

--
-- Structure de la table `act_like`
--

DROP TABLE IF EXISTS `act_like`;
CREATE TABLE IF NOT EXISTS `act_like` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `post_id` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `user_id` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_act` (`post_id`),
  KEY `FK_user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `doctrine_migration_versions`
--

DROP TABLE IF EXISTS `doctrine_migration_versions`;
CREATE TABLE IF NOT EXISTS `doctrine_migration_versions` (
  `version` varchar(191) COLLATE utf8_unicode_ci NOT NULL,
  `executed_at` datetime DEFAULT NULL,
  `execution_time` int(11) DEFAULT NULL,
  PRIMARY KEY (`version`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `doctrine_migration_versions`
--

INSERT INTO `doctrine_migration_versions` (`version`, `executed_at`, `execution_time`) VALUES
('DoctrineMigrations\\Version20210227145350', '2021-03-29 14:26:45', 175),
('DoctrineMigrations\\Version20210227154457', '2021-03-29 14:26:45', 67),
('DoctrineMigrations\\Version20210228163245', '2021-03-29 14:26:45', 44),
('DoctrineMigrations\\Version20210303113727', '2021-03-29 14:26:45', 52),
('DoctrineMigrations\\Version20210304200749', '2021-03-29 14:29:51', 71),
('DoctrineMigrations\\Version20210310091545', '2021-03-29 14:29:51', 72),
('DoctrineMigrations\\Version20210311182430', '2021-03-29 14:29:51', 21),
('DoctrineMigrations\\Version20210320043611', '2021-03-29 14:29:51', 48),
('DoctrineMigrations\\Version20210329150609', '2021-03-29 15:06:22', 324);

-- --------------------------------------------------------

--
-- Structure de la table `gerant`
--

DROP TABLE IF EXISTS `gerant`;
CREATE TABLE IF NOT EXISTS `gerant` (
  `id_gerant` varchar(16) COLLATE utf8mb4_unicode_ci NOT NULL,
  `nom` varchar(10) COLLATE utf8mb4_unicode_ci NOT NULL,
  `prenom` varchar(10) COLLATE utf8mb4_unicode_ci NOT NULL,
  `date_nais` date NOT NULL,
  `ad_email` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `cin` varchar(8) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id_gerant`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `gerant`
--

INSERT INTO `gerant` (`id_gerant`, `nom`, `prenom`, `date_nais`, `ad_email`, `cin`) VALUES
('AZJRARJ', 'AJEJARJAJ', 'JRJAJRJA', '2016-01-01', 'ahmed.jlassi@hotmail.com', '123412'),
('jaejaj', 'jeajajejj', 'jaejajejaj', '2016-01-01', 'ahmed.jlassi@hotmail.com', '221414'),
('jajeajej', 'ejjaeja', 'zajjeajej', '2016-01-01', 'ahmed.jlassi@hotmail.fr', '1020230'),
('kezek', 'azeka', 'aeke', '2016-01-01', 'ahmed.jlassi@hotmail.com', '1212344'),
('zejjz', 'jazjejaj', 'ajeaejaj', '2016-01-01', 'ahmed.jlassi@hotmail.fr', '1213141');

-- --------------------------------------------------------

--
-- Structure de la table `maison`
--

DROP TABLE IF EXISTS `maison`;
CREATE TABLE IF NOT EXISTS `maison` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `image` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `reset_password_request`
--

DROP TABLE IF EXISTS `reset_password_request`;
CREATE TABLE IF NOT EXISTS `reset_password_request` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `selector` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL,
  `hashed_token` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `requested_at` datetime NOT NULL COMMENT '(DC2Type:datetime_immutable)',
  `expires_at` datetime NOT NULL COMMENT '(DC2Type:datetime_immutable)',
  PRIMARY KEY (`id`),
  KEY `IDX_7CE748AA76ED395` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `first_name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `last_name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `password` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `google_id` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `avatar` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `roles` json DEFAULT NULL,
  `cin` int(11) DEFAULT NULL,
  `created_at` datetime NOT NULL,
  `facebook_id` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `is_banned` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `user`
--

INSERT INTO `user` (`id`, `email`, `first_name`, `last_name`, `password`, `google_id`, `avatar`, `roles`, `cin`, `created_at`, `facebook_id`, `is_banned`) VALUES
(1, 'ahmed.jlassi24@yahoo.fr', 'ahmed', 'jlassi', '$2y$13$fco.aZpvLqd8gNEuGr9cp./0WpuD.thmcTkM4YtqcWzX1Y9PcinHy', NULL, 'uploads/ExewaHnWYAMXmZv0303292921212115153535thth.jpg', '[\"ROLE_ADMIN\"]', NULL, '2021-03-29 15:35:14', NULL, 0),
(2, 'ahmed.jlassi@yahoo.fr', 'ahmed', 'jlassi', '$2y$13$nY7nag4UJg8vaQ7i4gxso.ej81IoiDp/9kHsv/X//fnHv0NdvL2/G', NULL, NULL, '[\"ROLE_USER\"]', NULL, '2021-03-29 17:28:21', NULL, 0),
(3, 'ahmed.jelassi@esprit.tn', 'jelassi', 'ahmed', '$2y$13$D19rRk1VDQ4uLfhouMMrxe7/BYpIYWyNw6ZVhfEmShjIuZALnyylq', NULL, NULL, '[\"ROLE_ADMIN\"]', 15014670, '2021-04-22 04:09:29', NULL, 0);

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `activity`
--
ALTER TABLE `activity`
  ADD CONSTRAINT `FK_Email` FOREIGN KEY (`id_gerant`) REFERENCES `user` (`email`);

--
-- Contraintes pour la table `act_like`
--
ALTER TABLE `act_like`
  ADD CONSTRAINT `FK_act` FOREIGN KEY (`post_id`) REFERENCES `activity` (`id_act`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`email`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `reset_password_request`
--
ALTER TABLE `reset_password_request`
  ADD CONSTRAINT `FK_7CE748AA76ED395` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
