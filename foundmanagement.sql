-- phpMyAdmin SQL Dump
-- version 5.0.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3307
-- Generation Time: May 14, 2021 at 07:52 PM
-- Server version: 10.4.14-MariaDB
-- PHP Version: 7.2.34

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `foundmanagement`
--

-- --------------------------------------------------------

--
-- Table structure for table `discountforbuyers`
--

CREATE TABLE `discountforbuyers` (
  `fundID` int(50) NOT NULL,
  `projectID` int(50) NOT NULL,
  `projectName` varchar(50) NOT NULL,
  `receiverID` int(50) NOT NULL,
  `timeRangeDays` varchar(50) DEFAULT NULL,
  `amountNew` double DEFAULT NULL,
  `statuse` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `fundmanagment`
--

CREATE TABLE `fundmanagment` (
  `FundID` int(50) NOT NULL,
  `ProjectID` int(50) NOT NULL,
  `ProjectName` varchar(50) NOT NULL,
  `ReceiverID` int(50) NOT NULL,
  `TimeRange` varchar(50) DEFAULT NULL,
  `TotalAmount` double DEFAULT NULL,
  `Status` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `fundmanagment`
--

INSERT INTO `fundmanagment` (`FundID`, `ProjectID`, `ProjectName`, `ReceiverID`, `TimeRange`, `TotalAmount`, `Status`) VALUES
(354655, 2, 'er diagram', 2, '1-2', 1200, NULL),
(354656, 5, 'ER', 3, '1-2', 1200, NULL),
(354657, 6, 'web project', 3, '10-15', 12000, NULL),
(354658, 6, 'web project', 3, '10-15', 12000, NULL),
(354659, 7, 'Dynamic web project', 3, '10', 15000, NULL);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `discountforbuyers`
--
ALTER TABLE `discountforbuyers`
  ADD PRIMARY KEY (`fundID`);

--
-- Indexes for table `fundmanagment`
--
ALTER TABLE `fundmanagment`
  ADD PRIMARY KEY (`FundID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `discountforbuyers`
--
ALTER TABLE `discountforbuyers`
  MODIFY `fundID` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `fundmanagment`
--
ALTER TABLE `fundmanagment`
  MODIFY `FundID` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=354660;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
