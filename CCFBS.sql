-- phpMyAdmin SQL Dump
-- version 4.4.14
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Feb 21, 2016 at 08:24 PM
-- Server version: 5.6.26
-- PHP Version: 5.6.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `CCFBS`
--

-- --------------------------------------------------------

--
-- Table structure for table `facility`
--

CREATE TABLE IF NOT EXISTS `facility` (
  `id` int(11) NOT NULL,
  `FacilityName` varchar(50) NOT NULL,
  `FacilityType` varchar(20) NOT NULL,
  `PriceAtPeak` int(11) NOT NULL,
  `PriceAtNonPeak` int(11) NOT NULL,
  `Capacity` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `facility`
--

INSERT INTO `facility` (`id`, `FacilityName`, `FacilityType`, `PriceAtPeak`, `PriceAtNonPeak`, `Capacity`) VALUES
(1, 'Brizo', 'restaurant', 10, 5, 100),
(2, 'Audacity', 'ktv', 30, 20, 10),
(3, 'Rendevouz', 'restaurant', 140, 120, 80);

-- --------------------------------------------------------

--
-- Table structure for table `membershiptier`
--

CREATE TABLE IF NOT EXISTS `membershiptier` (
  `id` int(11) NOT NULL,
  `Rank` varchar(20) NOT NULL,
  `Fee` int(11) NOT NULL,
  `DiscountPercentage` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `membershiptier`
--

INSERT INTO `membershiptier` (`id`, `Rank`, `Fee`, `DiscountPercentage`) VALUES
(1, 'Platinum', 400, 50),
(2, 'Gold', 300, 30),
(3, 'Silver', 100, 10),
(4, 'Basic', 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `timeslot`
--

CREATE TABLE IF NOT EXISTS `timeslot` (
  `id` int(11) NOT NULL,
  `SlotNumber` int(11) NOT NULL,
  `Description` varchar(50) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `timeslot`
--

INSERT INTO `timeslot` (`id`, `SlotNumber`, `Description`) VALUES
(1, 1, '10AM - 12PM'),
(2, 2, '12PM - 2PM'),
(3, 3, '2PM - 4PM'),
(4, 4, '4PM - 6PM'),
(5, 5, '6PM - 8PM'),
(6, 6, '8PM - 10PM');

-- --------------------------------------------------------

--
-- Table structure for table `transaction`
--

CREATE TABLE IF NOT EXISTS `transaction` (
  `id` int(11) NOT NULL,
  `TransactionDate` date NOT NULL,
  `MemberUserID` int(11) NOT NULL,
  `FacilityID` int(11) NOT NULL,
  `TimeSlot` int(11) NOT NULL,
  `PaxCount` int(11) NOT NULL,
  `Status` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `transaction`
--

INSERT INTO `transaction` (`id`, `TransactionDate`, `MemberUserID`, `FacilityID`, `TimeSlot`, `PaxCount`, `Status`) VALUES
(1, '2016-10-09', 1, 2, 2, 3, 1),
(2, '2016-10-09', 1, 2, 2, 3, 1),
(3, '2016-10-09', 1, 2, 2, 3, 1),
(4, '2016-10-09', 2, 2, 3, 12, 1),
(5, '2016-10-09', -1, 1, 5, 23, 1);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `firstname` varchar(50) NOT NULL,
  `lastname` varchar(50) NOT NULL,
  `membership` int(11) NOT NULL,
  `role` varchar(20) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `username`, `password`, `firstname`, `lastname`, `membership`, `role`) VALUES
(1, 'admin', 'admin', 'FirstName', 'LastName', 0, 'admin'),
(3, 'nick', 'password', 'Nick', 'Nguyen', 1, 'member');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `facility`
--
ALTER TABLE `facility`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `membershiptier`
--
ALTER TABLE `membershiptier`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `timeslot`
--
ALTER TABLE `timeslot`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `transaction`
--
ALTER TABLE `transaction`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `facility`
--
ALTER TABLE `facility`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `membershiptier`
--
ALTER TABLE `membershiptier`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `timeslot`
--
ALTER TABLE `timeslot`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT for table `transaction`
--
ALTER TABLE `transaction`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=4;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
