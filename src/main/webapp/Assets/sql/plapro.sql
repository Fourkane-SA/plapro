-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Hôte : localhost
-- Généré le : mar. 15 juin 2021 à 10:41
-- Version du serveur :  10.4.18-MariaDB
-- Version de PHP : 8.0.5

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `plapro`
--
CREATE DATABASE IF NOT EXISTS `plapro` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `plapro`;

-- --------------------------------------------------------

--
-- Structure de la table `activityProject`
--

DROP TABLE IF EXISTS `activityProject`;
CREATE TABLE `activityProject` (
  `id` int(11) NOT NULL,
  `idUser` int(11) NOT NULL,
  `link` varchar(255) NOT NULL,
  `type` varchar(50) NOT NULL,
  `created_at` datetime NOT NULL DEFAULT current_timestamp(),
  `text` varchar(255) NOT NULL,
  `idProject` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `activityProject`
--

INSERT INTO `activityProject` (`id`, `idUser`, `link`, `type`, `created_at`, `text`, `idProject`) VALUES
(1, 1, 'task?id=3', 'newTask', '2021-05-27 09:14:06', 'La tache \'Initialisation creation de projet\' a été créée', 1),
(2, 1, 'task?id=0', 'newTask', '2021-05-28 09:24:11', 'La tache Recherche a été créée par Fourkane', 1),
(3, 1, 'task?id=0', 'newTask', '2021-05-28 09:29:36', 'La tache Realisation d un systeme de message prives a été créée par Fourkane', 1),
(4, 1, 'task?id=7', 'newTask', '2021-05-28 09:36:37', 'La tache Creation d une tache a été créée par Fourkane', 1),
(5, 1, 'task?id=8', 'newTask', '2021-05-28 09:38:37', 'La tache Tableau de bord de projet a été créée par Fourkane', 1),
(6, 1, 'task?id=9', 'newTask', '2021-06-07 09:02:14', 'La tache Affichage diagramme des taches a été créée par Fourkane', 1),
(7, 1, 'task?id=10', 'newTask', '2021-06-07 09:03:18', 'La tache Mettre a jour une tache a été créée par Fourkane', 1),
(8, 1, 'task?id=11', 'newTask', '2021-06-07 09:04:58', 'La tache Affichage des taches en cours / a faire et non commence a été créée par Fourkane', 1),
(12, 1, 'task?id=10', 'editTask', '2021-06-07 10:00:46', 'La tache Mettre a jour une tache a été mis à jour par Fourkane', 1),
(13, 1, 'task?id=11', 'editTask', '2021-06-07 10:01:24', 'La tache Affichage des taches en cours / a faire et non commence a été mis à jour par Fourkane', 1),
(14, 1, 'task?id=12', 'newTask', '2021-06-07 10:17:40', 'La tache Editer un projet a été créée par Fourkane', 1),
(15, 1, 'task?id=12', 'editTask', '2021-06-07 10:24:17', 'La tache Informations personnelles du projet a été mis à jour par Fourkane', 1),
(16, 1, 'task?id=12', 'editTask', '2021-06-07 11:50:56', 'La tache Informations personnelles du projet a été mis à jour par Fourkane', 1),
(17, 1, 'task?id=13', 'newTask', '2021-06-08 08:25:02', 'La tache Modifier les informations d un projet a été créée par Fourkane', 1),
(18, 1, 'task?id=12', 'editTask', '2021-06-08 08:36:03', 'La tache Informations personnelles du projet a été mis à jour par Fourkane', 1),
(19, 1, 'task?id=12', 'editTask', '2021-06-08 08:36:33', 'La tache Informations personnelles du projet a été mis à jour par Fourkane', 1),
(20, 1, 'projectsetting?id=1', 'editProject', '2021-06-08 09:23:47', 'Les informations du projet PlaPro ont été mis à jour par Fourkane', 1),
(21, 1, 'task?id=13', 'editTask', '2021-06-08 09:25:43', 'La tache Modifier les informations d un projet a été mis à jour par Fourkane', 1),
(22, 1, 'task?id=14', 'newTask', '2021-06-08 09:29:18', 'La tache Affichage des prochaines taches a faire a été créée par Fourkane', 1),
(23, 1, 'task?id=8', 'editTask', '2021-06-08 09:32:18', 'La tache Tableau de bord de projet a été mis à jour par Fourkane', 1),
(24, 1, 'task?id=15', 'newTask', '2021-06-08 09:36:41', 'La tache Affichage des taches en retard a été créée par Fourkane', 1),
(25, 1, 'task?id=14', 'editTask', '2021-06-09 09:20:54', 'La tache Affichage des prochaines taches a faire a été mis à jour par Fourkane', 1),
(26, 1, 'task?id=16', 'newTask', '2021-06-09 09:22:52', 'La tache Modifier le format des dates a été créée par Fourkane', 1),
(27, 1, 'task?id=17', 'newTask', '2021-06-09 09:23:29', 'La tache Affichage des taches en retard a été créée par Fourkane', 1),
(28, 1, 'task?id=18', 'newTask', '2021-06-09 09:25:56', 'La tache Diagramme d activites quotidien a été créée par Fourkane', 1),
(29, 1, 'task?id=16', 'editTask', '2021-06-09 11:05:31', 'La tache Modifier le format des dates a été mis à jour par Fourkane', 1),
(30, 1, 'task?id=17', 'editTask', '2021-06-09 11:23:45', 'La tache Affichage des taches en retard a été mis à jour par Fourkane', 1),
(31, 1, 'task?id=18', 'editTask', '2021-06-10 09:52:32', 'La tache Diagramme d activites quotidien a été mis à jour par Fourkane', 1),
(32, 1, 'task?id=18', 'editTask', '2021-06-10 10:12:27', 'La tache Diagramme d activites quotidien a été mis à jour par Fourkane', 1),
(33, 1, 'task?id=8', 'editTask', '2021-06-10 10:13:31', 'La tache Tableau de bord de projet a été mis à jour par Fourkane', 1),
(34, 1, 'task?id=2', 'editTask', '2021-06-10 10:13:58', 'La tache Gestion de projet a été mis à jour par Fourkane', 1),
(35, 1, 'task?id=19', 'newTask', '2021-06-10 10:15:15', 'La tache Gestion de membres a été créée par Fourkane', 1),
(36, 1, 'task?id=8', 'editTask', '2021-06-10 10:15:54', 'La tache Tableau de bord de projet a été mis à jour par Fourkane', 1),
(37, 1, 'task?id=19', 'editTask', '2021-06-11 09:38:11', 'La tache Gestion de membres a été mis à jour par Fourkane', 1),
(38, 1, 'task?id=19', 'editTask', '2021-06-11 10:28:03', 'La tache Gestion de membres a été mis à jour par Fourkane', 1),
(39, 1, 'task?id=20', 'newTask', '2021-06-11 10:28:52', 'La tache Realisation d un diagramme de Gantt a été créée par Fourkane', 1),
(40, 1, 'task?id=21', 'newTask', '2021-06-11 10:30:10', 'La tache Realisation du rapport a été créée par Fourkane', 1),
(41, 1, 'publicprofile?id=6', 'newMemberProject', '2021-06-14 09:31:32', 'Test a été ajouté au projet par Fourkane', 1),
(42, 6, 'task?id=19', 'editTask', '2021-06-14 09:32:58', 'La tache Gestion de membres a été mis à jour par Test', 1),
(43, 1, 'publicprofile?id=6', 'deleteMemberProject', '2021-06-14 10:26:48', 'Test a été supprimé du projet par Fourkane', 1),
(44, 1, 'task?id=19', 'editTask', '2021-06-14 10:27:47', 'La tache Gestion de membres a été mis à jour par Fourkane', 1),
(45, 1, 'task?id=20', 'editTask', '2021-06-15 10:30:06', 'La tache Realisation d un diagramme de Gantt a été mis à jour par Fourkane', 1),
(46, 1, 'task?id=20', 'editTask', '2021-06-15 10:30:39', 'La tache Realisation d un diagramme de Gantt a été mis à jour par Fourkane', 1),
(47, 1, 'task?id=8', 'editTask', '2021-06-15 10:31:08', 'La tache Tableau de bord de projet a été mis à jour par Fourkane', 1),
(48, 1, 'task?id=2', 'editTask', '2021-06-15 10:31:27', 'La tache Gestion de projet a été mis à jour par Fourkane', 1);

-- --------------------------------------------------------

--
-- Structure de la table `conversation`
--

DROP TABLE IF EXISTS `conversation`;
CREATE TABLE `conversation` (
  `id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `conversation`
--

INSERT INTO `conversation` (`id`) VALUES
(1),
(3),
(4);

-- --------------------------------------------------------

--
-- Structure de la table `follow`
--

DROP TABLE IF EXISTS `follow`;
CREATE TABLE `follow` (
  `id` int(11) NOT NULL,
  `id_following` int(11) NOT NULL,
  `id_follower` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `follow`
--

INSERT INTO `follow` (`id`, `id_following`, `id_follower`) VALUES
(7, 1, 6),
(8, 6, 1);

-- --------------------------------------------------------

--
-- Structure de la table `groupProject`
--

DROP TABLE IF EXISTS `groupProject`;
CREATE TABLE `groupProject` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `groupProject`
--

INSERT INTO `groupProject` (`id`, `name`, `description`) VALUES
(1, 'Administrateur', 'Possède tous les droits sur le projet'),
(2, 'Contributeur', 'Contribue au projet'),
(3, 'Invité', 'Permet de voir le projet, mais ne peut faire aucune modification');

-- --------------------------------------------------------

--
-- Structure de la table `memberConversation`
--

DROP TABLE IF EXISTS `memberConversation`;
CREATE TABLE `memberConversation` (
  `id` int(11) NOT NULL,
  `idConversation` int(11) NOT NULL,
  `idUser` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `memberConversation`
--

INSERT INTO `memberConversation` (`id`, `idConversation`, `idUser`) VALUES
(1, 1, 1),
(2, 1, 6),
(3, 3, 1),
(4, 3, 7),
(5, 4, 6),
(6, 4, 7);

-- --------------------------------------------------------

--
-- Structure de la table `memberProject`
--

DROP TABLE IF EXISTS `memberProject`;
CREATE TABLE `memberProject` (
  `id` int(11) NOT NULL,
  `idProject` int(11) NOT NULL,
  `idUser` int(11) NOT NULL,
  `idGroup` int(11) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `memberProject`
--

INSERT INTO `memberProject` (`id`, `idProject`, `idUser`, `idGroup`) VALUES
(1, 1, 1, 1),
(2, 5, 6, 1);

-- --------------------------------------------------------

--
-- Structure de la table `message`
--

DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
  `id` int(11) NOT NULL,
  `idUserSender` int(11) NOT NULL,
  `text` varchar(255) NOT NULL,
  `time` datetime NOT NULL DEFAULT current_timestamp(),
  `isRead` tinyint(1) NOT NULL DEFAULT 0,
  `idConversation` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `message`
--

INSERT INTO `message` (`id`, `idUserSender`, `text`, `time`, `isRead`, `idConversation`) VALUES
(8, 1, 'Premier message', '2021-05-24 10:03:25', 0, 1),
(9, 6, 'Message reçu', '2021-05-24 10:03:39', 0, 1),
(10, 1, 'test', '2021-05-24 10:34:41', 0, 1),
(11, 1, 'second test', '2021-05-24 10:34:48', 0, 1),
(12, 1, 'test', '2021-05-24 10:48:39', 0, 3),
(13, 6, 'Message destiné à Zelda', '2021-05-24 11:12:58', 0, 4),
(14, 6, 'Message pour Fourkane', '2021-05-24 11:14:31', 0, 1);

-- --------------------------------------------------------

--
-- Structure de la table `Notifications`
--

DROP TABLE IF EXISTS `Notifications`;
CREATE TABLE `Notifications` (
  `id` int(11) NOT NULL,
  `idUser` int(11) NOT NULL,
  `link` varchar(255) NOT NULL,
  `text` varchar(255) NOT NULL,
  `type` varchar(50) NOT NULL,
  `seen` tinyint(1) NOT NULL DEFAULT 0,
  `created_at` datetime NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `Notifications`
--

INSERT INTO `Notifications` (`id`, `idUser`, `link`, `text`, `type`, `seen`, `created_at`) VALUES
(2, 6, 'publicprofile?id=1', 'Fourkane a commencé à vous suivre', 'newFollow', 1, '2021-05-17 09:41:20'),
(3, 1, 'publicprofile?id=6', 'Test a commencé à vous suivre', 'newFollow', 1, '2021-05-17 09:57:22'),
(4, 6, 'projectdashboard?id=0', 'Le projet Test a été créé avec succès', 'newProject', 1, '2021-05-17 10:17:03'),
(5, 6, 'publicprofile?id=1', 'Fourkane a commencé à vous suivre', 'newFollow', 1, '2021-05-18 10:35:51');

-- --------------------------------------------------------

--
-- Structure de la table `Project`
--

DROP TABLE IF EXISTS `Project`;
CREATE TABLE `Project` (
  `id` int(11) NOT NULL,
  `name` varchar(20) NOT NULL,
  `shortDescription` varchar(255) DEFAULT NULL,
  `visibility` varchar(10) NOT NULL,
  `status` varchar(20) NOT NULL,
  `creationDate` datetime NOT NULL DEFAULT current_timestamp(),
  `updateTime` datetime NOT NULL DEFAULT current_timestamp(),
  `description` varchar(5000) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `Project`
--

INSERT INTO `Project` (`id`, `name`, `shortDescription`, `visibility`, `status`, `creationDate`, `updateTime`, `description`) VALUES
(1, 'PlaPro', 'Une application de gestion de projet', 'public', 'en cours', '2021-05-13 10:40:45', '2021-06-15 10:31:27', 'Réalisation d\'un site web de gestion de projet en Java. Ce site permettra à un utilisateur connecté de créer un projet, d\'en assigner les différents membres et de créer des taches. Chaque tache possède une description, une deadline, un statut (non réalisé, en cours, terminé, en retard...) et des membres en charge de sa réalisation. Les utilisateurs ont également accès à un tableau de bord global du projet qui lui permet d\'avoir accès à des statistiques sur l\'avancée du projet, un diagramme de Gantt, le cahier des charges ainsi que des propositions sur de nouvelles fonctionnalités du projet. Il est également possible aux utilisateurs de communiquer avec les autres membres via un chat.'),
(5, 'Test', 'Un projet test', 'private', 'En cours', '2021-05-17 10:17:03', '2021-05-17 10:17:03', NULL);

-- --------------------------------------------------------

--
-- Structure de la table `taskProject`
--

DROP TABLE IF EXISTS `taskProject`;
CREATE TABLE `taskProject` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `start` varchar(20) NOT NULL,
  `end` varchar(20) NOT NULL,
  `progress` int(3) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `idGroup` int(11) NOT NULL,
  `finish` varchar(20) DEFAULT NULL,
  `idProject` int(11) NOT NULL,
  `created_at` datetime NOT NULL DEFAULT current_timestamp(),
  `idUser` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `taskProject`
--

INSERT INTO `taskProject` (`id`, `name`, `start`, `end`, `progress`, `description`, `idGroup`, `finish`, `idProject`, `created_at`, `idUser`) VALUES
(1, 'Inscription et connexion', '2021-05-11', '2021-05-12', 100, 'Realisation de l\'inscription et de la connexion', 1, '2021-05-12', 1, '2021-05-26 14:46:44', 1),
(2, 'Gestion de projet', '2021-05-13', '2021-06-18', 100, 'Realisation de l ensemble des fonctionnalites de gestion de projet', 1, '2021-06-15', 1, '2021-05-26 14:51:43', 1),
(3, 'Initialisation creation de projet', '2021-05-13', '2021-05-13', 100, 'Realisation de la creation d\'un projet avec une description', 1, '2021-05-13', 1, '2021-05-27 09:14:06', 1),
(4, 'Realisation de la page de profil d un utilisateur', '2021-05-14', '2021-05-16', 100, 'Realisation de la page de profil d\'un utilisateur avec l\'affichage de son pseudo, nom et prenom ainsi que l affichage de l\'ensemble de ses projets. Ajout egalement du nombre d abonnee et de la possibilite de s\'abonner a cet utilisateur.', 1, '2021-05-19', 1, '2021-05-28 09:12:57', 1),
(5, 'Réalisation d\'un système de notification', '2021-05-17', '2021-05-17', 100, 'Ajout de notifications lorsqu\'on s\'abonne à un utilisateur', 1, '2021-05-17', 1, '2021-05-28 09:19:00', 1),
(6, 'Realisation d un systeme de message prives', '2021-05-19', '2021-05-24', 100, 'Implementation de messages privees avec la possibilite d envoyer des messages a n importe quel autre utilisateur. Affichage des dernieres conversations de l utilisateur connecte dans la page de l onglet message.', 1, '2021-05-25', 1, '2021-05-28 09:29:36', 1),
(7, 'Creation d une tache', '2021-05-26', '2021-05-26', 100, 'Implementation de la fonctionnalite d ajout d une tache', 1, '2021-05-26', 1, '2021-05-28 09:36:37', 1),
(8, 'Tableau de bord de projet', '2021-05-13', '2021-06-14', 100, 'Realisation du tableau de bord du projet permettant l acces aux differentes pages de gestion de projet', 1, '2021-06-15', 1, '2021-05-28 09:38:37', 1),
(9, 'Affichage diagramme des taches', '2021-05-31', '2021-06-03', 100, 'Realisation d un diagramme permettant l affichage du statut du projet avec le nombre de taches realises, le nombre de taches en cours et le nombre de taches a realiser. Le diagramme est affiche dans le tableau de bord du projet.', 1, '2021-06-03', 1, '2021-06-07 09:02:13', 1),
(10, 'Mettre a jour une tache', '2021-06-07', '2021-06-07', 100, 'Permettre de modifier une tache', 1, '2021-06-07', 1, '2021-06-07 09:03:18', 1),
(11, 'Affichage des taches en cours / a faire et non commence', '2021-06-03', '2021-06-07', 100, 'Permettre depuis le tableau de bord d\'afficher séparément les tâches qui sont a faire, les tâches réalisées, et les tâches qui sont en cours.', 1, '2021-06-07', 1, '2021-06-07 09:04:58', 1),
(12, 'Informations personnelles du projet', '2021-06-07', '2021-06-08', 100, 'Permettre l affichage des informations  d un projet', 1, '2021-06-08', 1, '2021-06-07 10:17:40', 1),
(13, 'Modifier les informations d un projet', '2021-06-08', '2021-06-08', 100, 'Permettre de modifier les informations et le statut d un projet', 1, '2021-06-08', 1, '2021-06-08 08:25:02', 1),
(14, 'Affichage des prochaines taches a faire', '2021-06-08', '2021-06-09', 100, 'Affichage des prochaines taches a realiser depuis le tableau de bord', 1, '2021-06-09', 1, '2021-06-08 09:29:18', 1),
(16, 'Modifier le format des dates', '2021-06-09', '2021-06-09', 100, 'Convertir le format de l ensemble des pages en francais avec le jour, le mois, l annee et l heure quand c est necessaire.', 1, '2021-06-09', 1, '2021-06-09 09:22:52', 1),
(17, 'Affichage des taches en retard', '2021-06-09', '2021-06-09', 100, 'Affichage des taches en retard dans le tableau de bord du projet', 1, '2021-06-09', 1, '2021-06-09 09:23:29', 1),
(18, 'Diagramme d activites quotidien', '2021-06-09', '2021-06-10', 100, 'Affichage d un diagramme affichant le nombre de mises a jours effectuees chaque jours', 1, '2021-06-10', 1, '2021-06-09 09:25:56', 1),
(19, 'Gestion de membres', '2021-06-10', '2021-06-12', 100, 'Permettre d ajouter, de supprimer et de modifier les autorisations des membres du projet', 1, '2021-06-14', 1, '2021-06-10 10:15:15', 1),
(20, 'Realisation d un diagramme de Gantt', '2021-06-12', '2021-06-15', 100, 'Realisation du diagramme de GANTT du projet a l aide de l ensemble des taches realises du projet', 1, '2021-06-15', 1, '2021-06-11 10:28:52', 1),
(21, 'Realisation du rapport', '2021-06-14', '2021-06-20', 0, 'Realisation du rapport du projet', 1, 'null', 1, '2021-06-11 10:30:10', 1);

-- --------------------------------------------------------

--
-- Structure de la table `User`
--

DROP TABLE IF EXISTS `User`;
CREATE TABLE `User` (
  `id` int(11) NOT NULL,
  `pseudo` varchar(20) NOT NULL,
  `password` varchar(255) NOT NULL,
  `email` varchar(50) NOT NULL,
  `firstname` varchar(20) NOT NULL,
  `lastname` varchar(20) NOT NULL,
  `gender` varchar(5) NOT NULL,
  `birth` date NOT NULL,
  `created_at` datetime NOT NULL DEFAULT current_timestamp(),
  `description` varchar(5000) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `User`
--

INSERT INTO `User` (`id`, `pseudo`, `password`, `email`, `firstname`, `lastname`, `gender`, `birth`, `created_at`, `description`) VALUES
(1, 'Fourkane', 'unmotdepasse', 'fourkane@mail.fr', 'Fourkane', 'SAID ALI', 'man', '2000-08-13', '2021-05-11 19:55:52', 'Le developpeur du site PlaPro. Etudiant en 3eme annee de licence informatique.'),
(6, 'Test', 'test', 'test@test.com', 'test', 'test', 'woman', '2010-07-28', '2021-05-12 09:58:03', NULL),
(7, 'Zelda', 'madi', 'zelda@nintende.cool', 'Link', 'Bouffon', 'none', '1010-10-10', '2021-05-12 16:03:06', NULL);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `activityProject`
--
ALTER TABLE `activityProject`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idUser` (`idUser`),
  ADD KEY `idProject` (`idProject`);

--
-- Index pour la table `conversation`
--
ALTER TABLE `conversation`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `follow`
--
ALTER TABLE `follow`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_follower` (`id_follower`),
  ADD KEY `id_following` (`id_following`);

--
-- Index pour la table `groupProject`
--
ALTER TABLE `groupProject`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `memberConversation`
--
ALTER TABLE `memberConversation`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idConversation` (`idConversation`),
  ADD KEY `idUser` (`idUser`);

--
-- Index pour la table `memberProject`
--
ALTER TABLE `memberProject`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idProject` (`idProject`),
  ADD KEY `idUser` (`idUser`),
  ADD KEY `idGroup` (`idGroup`);

--
-- Index pour la table `message`
--
ALTER TABLE `message`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idUserSender` (`idUserSender`),
  ADD KEY `idConversation` (`idConversation`);

--
-- Index pour la table `Notifications`
--
ALTER TABLE `Notifications`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idUser` (`idUser`);

--
-- Index pour la table `Project`
--
ALTER TABLE `Project`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `taskProject`
--
ALTER TABLE `taskProject`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idGroup` (`idGroup`),
  ADD KEY `idProject` (`idProject`),
  ADD KEY `idUser` (`idUser`);

--
-- Index pour la table `User`
--
ALTER TABLE `User`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `pseudo` (`pseudo`),
  ADD UNIQUE KEY `email` (`email`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `activityProject`
--
ALTER TABLE `activityProject`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=49;

--
-- AUTO_INCREMENT pour la table `conversation`
--
ALTER TABLE `conversation`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT pour la table `follow`
--
ALTER TABLE `follow`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT pour la table `groupProject`
--
ALTER TABLE `groupProject`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT pour la table `memberConversation`
--
ALTER TABLE `memberConversation`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT pour la table `memberProject`
--
ALTER TABLE `memberProject`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT pour la table `message`
--
ALTER TABLE `message`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT pour la table `Notifications`
--
ALTER TABLE `Notifications`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT pour la table `Project`
--
ALTER TABLE `Project`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT pour la table `taskProject`
--
ALTER TABLE `taskProject`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT pour la table `User`
--
ALTER TABLE `User`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `activityProject`
--
ALTER TABLE `activityProject`
  ADD CONSTRAINT `activityProject_ibfk_1` FOREIGN KEY (`idUser`) REFERENCES `User` (`id`),
  ADD CONSTRAINT `activityProject_ibfk_2` FOREIGN KEY (`idProject`) REFERENCES `Project` (`id`);

--
-- Contraintes pour la table `follow`
--
ALTER TABLE `follow`
  ADD CONSTRAINT `follow_ibfk_1` FOREIGN KEY (`id_follower`) REFERENCES `User` (`id`),
  ADD CONSTRAINT `follow_ibfk_2` FOREIGN KEY (`id_following`) REFERENCES `User` (`id`);

--
-- Contraintes pour la table `memberConversation`
--
ALTER TABLE `memberConversation`
  ADD CONSTRAINT `memberConversation_ibfk_1` FOREIGN KEY (`idConversation`) REFERENCES `conversation` (`id`),
  ADD CONSTRAINT `memberConversation_ibfk_2` FOREIGN KEY (`idUser`) REFERENCES `User` (`id`);

--
-- Contraintes pour la table `memberProject`
--
ALTER TABLE `memberProject`
  ADD CONSTRAINT `memberProject_ibfk_1` FOREIGN KEY (`idProject`) REFERENCES `Project` (`id`),
  ADD CONSTRAINT `memberProject_ibfk_2` FOREIGN KEY (`idUser`) REFERENCES `User` (`id`),
  ADD CONSTRAINT `memberProject_ibfk_3` FOREIGN KEY (`idGroup`) REFERENCES `groupProject` (`id`);

--
-- Contraintes pour la table `message`
--
ALTER TABLE `message`
  ADD CONSTRAINT `message_ibfk_2` FOREIGN KEY (`idUserSender`) REFERENCES `User` (`id`),
  ADD CONSTRAINT `message_ibfk_3` FOREIGN KEY (`idConversation`) REFERENCES `conversation` (`id`);

--
-- Contraintes pour la table `Notifications`
--
ALTER TABLE `Notifications`
  ADD CONSTRAINT `Notifications_ibfk_1` FOREIGN KEY (`idUser`) REFERENCES `User` (`id`);

--
-- Contraintes pour la table `taskProject`
--
ALTER TABLE `taskProject`
  ADD CONSTRAINT `taskProject_ibfk_1` FOREIGN KEY (`idGroup`) REFERENCES `groupProject` (`id`),
  ADD CONSTRAINT `taskProject_ibfk_2` FOREIGN KEY (`idProject`) REFERENCES `Project` (`id`),
  ADD CONSTRAINT `taskProject_ibfk_3` FOREIGN KEY (`idUser`) REFERENCES `User` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
