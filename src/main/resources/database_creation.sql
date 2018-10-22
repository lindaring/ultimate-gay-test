-- phpMyAdmin SQL Dump
-- version 4.7.7
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Oct 22, 2018 at 11:03 AM
-- Server version: 5.7.21
-- PHP Version: 7.1.13

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_ultimate_gay_test`
--
CREATE DATABASE IF NOT EXISTS `db_ultimate_gay_test` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `db_ultimate_gay_test`;

-- --------------------------------------------------------

--
-- Table structure for table `tbl_answers`
--

CREATE TABLE `tbl_answers` (
  `id` int(9) NOT NULL,
  `answer` varchar(255) NOT NULL,
  `background` varchar(255) DEFAULT NULL,
  `point` int(4) NOT NULL,
  `question_id` int(9) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Truncate table before insert `tbl_answers`
--

TRUNCATE TABLE `tbl_answers`;
--
-- Dumping data for table `tbl_answers`
--

INSERT INTO `tbl_answers` (`id`, `answer`, `background`, `point`, `question_id`) VALUES
(12, 'red', NULL, 0, 4),
(13, 'yellow', NULL, 0, 4),
(14, 'pink', NULL, 0, 4),
(15, 'blue', NULL, 0, 4),
(16, 'green', NULL, 0, 4),
(17, 'purple', NULL, 0, 4),
(18, 'kim kardashian', NULL, 0, 5),
(19, 'beyonce', NULL, 0, 5),
(20, 'sandra bullock', NULL, 0, 5),
(21, 'oprah winfrey', NULL, 0, 5),
(22, 'nicki minaj', NULL, 0, 5),
(23, 'jada pinkett smith', NULL, 0, 5),
(24, 'will smith', NULL, 2, 6),
(25, 'denzel washington', NULL, 6, 6),
(26, 'usher', NULL, 10, 6),
(27, 'brad pitt', NULL, 7, 6),
(28, 'justin bieber', NULL, 10, 6),
(29, 'dwayne johnson', NULL, 2, 6),
(30, 'johnny depp', NULL, 8, 6),
(31, 'feet', NULL, 8, 7),
(32, 'back', NULL, 10, 7),
(33, 'stomach', NULL, 4, 7),
(34, 'breast', NULL, 1, 7),
(35, 'butt', NULL, 3, 7),
(36, 'legs', NULL, 1, 7),
(43, 'beer', NULL, 5, 8),
(44, 'gin & tonic', NULL, 8, 8),
(45, 'white wine', NULL, 10, 8),
(46, 'red wine', NULL, 7, 8),
(47, 'cocktails', NULL, 10, 8),
(48, 'whiskey', NULL, 2, 8),
(49, 'night in with showmax/netflix', NULL, 2, 9),
(50, 'house party', NULL, 6, 9),
(51, 'strip club', NULL, 1, 9),
(52, 'quite drink with friends', NULL, 9, 9),
(53, 'clubbing', NULL, 5, 9),
(54, 'dinner & theatre', NULL, 10, 9),
(55, 'jacob zuma', NULL, 1, 10),
(56, 'nelson mandela', NULL, 3, 10),
(57, 'robert mugabe', NULL, 10, 10),
(58, 'donald trump', NULL, 2, 10),
(59, 'abraham lincoln', NULL, 7, 10),
(60, 'barack obama', NULL, 4, 10),
(61, 'blueberries', NULL, 10, 11),
(62, 'apple', NULL, 7, 11),
(63, 'cucumber', NULL, 4, 11),
(64, 'carrot', NULL, 9, 11),
(65, 'butternut', NULL, 3, 11),
(66, 'banana', NULL, 10, 11);

-- --------------------------------------------------------

--
-- Table structure for table `tbl_questions`
--

CREATE TABLE `tbl_questions` (
  `id` int(9) NOT NULL,
  `question` varchar(255) NOT NULL,
  `background` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Truncate table before insert `tbl_questions`
--

TRUNCATE TABLE `tbl_questions`;
--
-- Dumping data for table `tbl_questions`
--

INSERT INTO `tbl_questions` (`id`, `question`, `background`) VALUES
(4, 'pick a colour', NULL),
(5, 'pick a female celebrity', NULL),
(6, 'pick a male celebrity', NULL),
(7, 'choose best female body part', NULL),
(8, 'what is your drink of choice', NULL),
(9, 'what is your ideal Friday night', NULL),
(10, 'which president do you identify with the most', NULL),
(11, 'which is the best shaped fruit/veg', NULL);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tbl_answers`
--
ALTER TABLE `tbl_answers`
  ADD PRIMARY KEY (`id`),
  ADD KEY `question_id` (`question_id`);

--
-- Indexes for table `tbl_questions`
--
ALTER TABLE `tbl_questions`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tbl_answers`
--
ALTER TABLE `tbl_answers`
  MODIFY `id` int(9) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=67;

--
-- AUTO_INCREMENT for table `tbl_questions`
--
ALTER TABLE `tbl_questions`
  MODIFY `id` int(9) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `tbl_answers`
--
ALTER TABLE `tbl_answers`
  ADD CONSTRAINT `tbl_answers_ibfk_1` FOREIGN KEY (`question_id`) REFERENCES `tbl_questions` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
