-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 12, 2025 at 04:55 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `payroll_system`
--

-- --------------------------------------------------------

--
-- Table structure for table `reports`
--

CREATE TABLE `reports` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `content` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data for table `reports`
--

INSERT INTO `reports` (`id`, `user_id`, `content`) VALUES
(7, 3, 'repooorttt'),
(8, 9, 'reportt milos'),
(9, 15, 'report Amir');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `salary` double NOT NULL,
  `bonus` double DEFAULT NULL,
  `role` varchar(20) NOT NULL,
  `content` text DEFAULT NULL,
  `image_url` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `username`, `password`, `first_name`, `last_name`, `salary`, `bonus`, `role`, `content`, `image_url`) VALUES
(1, 'admin', 'admin', 'Admin', 'User', 100000, 5000, 'superadmin', '', NULL),
(2, 'mngr', 'mngr', 'Manager', 'User', 80000, 4000, 'manager', '', NULL),
(3, 'emp', 'emp', 'Employee', 'User', 60000, 3000, 'employee', '', NULL),
(4, 'test.test', 'password', 'test', 'test', 300, 200, 'employee', NULL, NULL),
(5, 'marko.markovic', 'pass', '', '', 0, NULL, 'employee', NULL, 'https://randomuser.me/api/portraits/men/2.jpg'),
(6, 'petar.petrovic', 'pass', '', '', 0, NULL, 'employee', NULL, 'https://randomuser.me/api/portraits/men/3.jpg'),
(7, 'nikola.nikolic', 'pass', '', '', 0, NULL, 'employee', NULL, 'https://randomuser.me/api/portraits/men/4.jpg'),
(8, 'stefan.stefanovic', 'pass', '', '', 0, NULL, 'employee', NULL, 'https://randomuser.me/api/portraits/men/5.jpg'),
(9, 'milos.milosevic', 'pass', '', '', 0, 100, 'employee', NULL, 'https://randomuser.me/api/portraits/men/6.jpg'),
(10, 'jovan.jovanovic', 'pass', '', '', 0, NULL, 'employee', NULL, 'https://randomuser.me/api/portraits/men/7.jpg'),
(11, 'dusan.dusanovic', 'pass', '', '', 0, NULL, 'employee', NULL, 'https://randomuser.me/api/portraits/men/8.jpg'),
(12, 'aleksandar.aleksandrovic', 'pass', '', '', 0, NULL, 'employee', NULL, 'https://randomuser.me/api/portraits/men/9.jpg'),
(13, 'uros.urosevic', 'pass', '', '', 0, NULL, 'employee', NULL, 'https://randomuser.me/api/portraits/men/10.jpg'),
(14, 'edin.edinovic', 'pass', '', '', 0, NULL, 'employee', NULL, 'https://randomuser.me/api/portraits/men/11.jpg'),
(15, 'amir.amirovic', 'pass', '', '', 0, NULL, 'employee', NULL, 'https://randomuser.me/api/portraits/men/12.jpg'),
(16, 'mirza.mirzic', 'pass', '', '', 0, NULL, 'employee', NULL, 'https://randomuser.me/api/portraits/men/13.jpg'),
(17, 'haris.harisovic', 'pass', '', '', 0, NULL, 'employee', NULL, 'https://randomuser.me/api/portraits/men/14.jpg'),
(18, 'dzemal.dzemalovic', 'pass', '', '', 0, NULL, 'employee', NULL, 'https://randomuser.me/api/portraits/men/15.jpg'),
(19, 'ana.anic', 'pass', '', '', 0, NULL, 'employee', NULL, 'https://randomuser.me/api/portraits/women/1.jpg'),
(20, 'milica.milic', 'pass', '', '', 0, NULL, 'employee', NULL, 'https://randomuser.me/api/portraits/women/2.jpg'),
(21, 'jelena.jelenic', 'pass', '', '', 0, NULL, 'employee', NULL, 'https://randomuser.me/api/portraits/women/3.jpg'),
(22, 'marija.maric', 'pass', '', '', 0, NULL, 'employee', NULL, 'https://randomuser.me/api/portraits/women/4.jpg'),
(23, 'sonja.sonjic', 'pass', '', '', 0, NULL, 'employee', NULL, 'https://randomuser.me/api/portraits/women/5.jpg'),
(24, 'ivana.ivanic', 'pass', '', '', 0, NULL, 'employee', NULL, 'https://randomuser.me/api/portraits/women/6.jpg'),
(25, 'katarina.kataric', 'pass', '', '', 0, NULL, 'employee', NULL, 'https://randomuser.me/api/portraits/women/7.jpg'),
(26, 'tijana.tijanic', 'pass', '', '', 0, NULL, 'employee', NULL, 'https://randomuser.me/api/portraits/women/8.jpg'),
(27, 'maja.majic', 'pass', '', '', 0, NULL, 'employee', NULL, 'https://randomuser.me/api/portraits/women/9.jpg'),
(28, 'nina.ninic', 'pass', '', '', 0, NULL, 'employee', NULL, 'https://randomuser.me/api/portraits/women/10.jpg'),
(29, 'leila.leilic', 'pass', '', '', 0, NULL, 'employee', NULL, 'https://randomuser.me/api/portraits/women/11.jpg'),
(30, 'selma.selmic', 'pass', '', '', 0, 0, 'employee', 'ushdzsgdzvd', 'https://randomuser.me/api/portraits/women/12.jpg'),
(31, 'ajla.ajlic', 'pass', '', '', 0, NULL, 'employee', NULL, 'https://randomuser.me/api/portraits/women/13.jpg'),
(32, 'nejra.nejric', 'pass', '', '', 0, NULL, 'employee', NULL, 'https://randomuser.me/api/portraits/women/14.jpg'),
(33, 'dzenita.dzenitic', 'pass', '', '', 0, NULL, 'employee', NULL, 'https://randomuser.me/api/portraits/women/15.jpg'),
(100, 'ivan.ivanovic', 'pass', 'ivan', 'ivanovic', 200, 100, 'employee', '', 'https://randomuser.me/api/portraits/men/1.jpg');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `reports`
--
ALTER TABLE `reports`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `reports`
--
ALTER TABLE `reports`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=101;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `reports`
--
ALTER TABLE `reports`
  ADD CONSTRAINT `reports_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
