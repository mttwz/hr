-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost:8889
-- Generation Time: Jan 23, 2023 at 12:26 PM
-- Server version: 5.7.34
-- PHP Version: 7.4.21

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `teszttttttttt`
--

DELIMITER $$
--
-- Procedures
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `update_script` ()  BEGIN
DECLARE cursor_name VARCHAR(255);
DECLARE cursor_targetId int(11);
DECLARE done INT DEFAULT FALSE;

DECLARE cur CURSOR FOR SELECT DISTINCT
    CONCAT(`first_name`, " " ,`last_name`), `target_patient_id`
FROM
    `Patient`
LEFT JOIN `Connection` on `Connection`.`origin_patient_id` = `Patient`.`id`
WHERE `type` = "mother" AND `mothers_name` is NULL;

DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;

OPEN cur;
  myloop:LOOP
    FETCH cur INTO cursor_name, cursor_targetId;
    IF done THEN
      LEAVE myloop;
    END IF;
    UPDATE `Patient` SET `Patient`.`mothers_name` = cursor_name WHERE `Patient`.`id` = cursor_targetId;
   
    
  END LOOP;
CLOSE cur;

END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `Address`
--

CREATE TABLE `Address` (
  `id` int(11) NOT NULL,
  `postcode` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `street_name` varchar(255) DEFAULT NULL,
  `house_number` int(11) DEFAULT NULL,
  `apartment` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `Connection`
--

CREATE TABLE `Connection` (
  `id` int(11) NOT NULL,
  `type` enum('mother','father','sister','brother') DEFAULT NULL,
  `quality` enum('++','+','0','-','--') DEFAULT NULL,
  `distance` tinyint(4) DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `origin_patient_id` int(11) DEFAULT NULL,
  `target_patient_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `Connection`
--

INSERT INTO `Connection` (`id`, `type`, `quality`, `distance`, `start_date`, `end_date`, `origin_patient_id`, `target_patient_id`) VALUES
(8, 'mother', '++', NULL, '2222-11-11', NULL, 1, 4),
(9, 'mother', '++', NULL, '2222-11-11', NULL, 1, 5),
(10, 'mother', '++', NULL, '2222-11-11', NULL, 3, 6),
(11, 'mother', '++', NULL, NULL, NULL, 2, 7);

--
-- Triggers `Connection`
--
DELIMITER $$
CREATE TRIGGER `alter_conncetion_date` BEFORE INSERT ON `Connection` FOR EACH ROW BEGIN
SELECT `date_of_birth` into @birthDate FROM `Patient` WHERE `id` = new.`target_patient_id`;

IF date(@birthDate) > date(new.`start_date`) THEN
SET new.`start_date` = @birthDate;
END IF;


END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `Patient`
--

CREATE TABLE `Patient` (
  `id` int(11) NOT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `mothers_name` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `date_of_birth` date DEFAULT NULL,
  `date_of_death` date DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `address_id` int(11) DEFAULT NULL,
  `email_address` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `Patient`
--

INSERT INTO `Patient` (`id`, `first_name`, `last_name`, `mothers_name`, `gender`, `date_of_birth`, `date_of_death`, `phone_number`, `address_id`, `email_address`) VALUES
(1, 'mom1', 'mom1', NULL, 'female', NULL, NULL, '+36001234567', NULL, 'asd@asd.asd'),
(2, 'mom2', 'mom2', NULL, 'female', NULL, NULL, '+36001234567', NULL, 'asd@asd.asd'),
(3, 'mom3', 'mom3', NULL, 'female', NULL, NULL, '+36001234567', NULL, 'asd@asd.asd'),
(4, 'son1', 'son1', NULL, 'male', '2222-11-11', NULL, '+36001234567', NULL, 'asd@asd.asd'),
(5, 'son2', 'son2', NULL, 'male', '2222-11-11', NULL, '+36001234567', NULL, 'asd@asd.asd'),
(6, 'son3', 'son3', NULL, 'male', '2222-11-11', NULL, '+36001234567', NULL, 'asd@asd.asd'),
(7, 'son4', 'son4', NULL, 'male', '2222-11-11', NULL, '+36001234567', NULL, 'asd@asd.asd');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `Address`
--
ALTER TABLE `Address`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `id` (`id`);

--
-- Indexes for table `Connection`
--
ALTER TABLE `Connection`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `id` (`id`),
  ADD KEY `origin_patient_id` (`origin_patient_id`),
  ADD KEY `target_patient_id` (`target_patient_id`);

--
-- Indexes for table `Patient`
--
ALTER TABLE `Patient`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `id` (`id`),
  ADD KEY `address_id` (`address_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `Address`
--
ALTER TABLE `Address`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `Connection`
--
ALTER TABLE `Connection`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `Patient`
--
ALTER TABLE `Patient`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `Connection`
--
ALTER TABLE `Connection`
  ADD CONSTRAINT `connection_ibfk_1` FOREIGN KEY (`origin_patient_id`) REFERENCES `Patient` (`id`),
  ADD CONSTRAINT `connection_ibfk_2` FOREIGN KEY (`target_patient_id`) REFERENCES `Patient` (`id`);

--
-- Constraints for table `Patient`
--
ALTER TABLE `Patient`
  ADD CONSTRAINT `patient_ibfk_1` FOREIGN KEY (`address_id`) REFERENCES `Address` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
