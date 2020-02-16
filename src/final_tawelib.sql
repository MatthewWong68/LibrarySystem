-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 10, 2018 at 11:03 AM
-- Server version: 10.1.28-MariaDB
-- PHP Version: 7.1.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `tawelib`
--

-- --------------------------------------------------------

--
-- Table structure for table `book`
--

CREATE TABLE `book` (
  `bid` int(11) NOT NULL,
  `rid` varchar(10) NOT NULL,
  `author` varchar(20) NOT NULL,
  `publisher` varchar(20) NOT NULL,
  `genre` varchar(20) NOT NULL,
  `isbn` varchar(10) NOT NULL,
  `language` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `borrowedresources`
--

CREATE TABLE `borrowedresources` (
  `username` varchar(20) NOT NULL,
  `rid` varchar(10) NOT NULL,
  `copyID` int(11) NOT NULL,
  `dateOut` bigint(20) DEFAULT NULL,
  `due` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `copy`
--

CREATE TABLE `copy` (
  `copyID` int(11) NOT NULL,
  `rid` varchar(10) NOT NULL,
  `available` tinyint(1) NOT NULL,
  `duration` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `copyhistory`
--

CREATE TABLE `copyhistory` (
  `chid` int(11) NOT NULL,
  `rid` varchar(10) NOT NULL,
  `cid` int(11) NOT NULL,
  `username` varchar(20) NOT NULL,
  `dateout` bigint(20) NOT NULL,
  `datein` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `dvd`
--

CREATE TABLE `dvd` (
  `did` int(11) NOT NULL,
  `rid` varchar(10) NOT NULL,
  `director` varchar(20) NOT NULL,
  `runtime` int(11) NOT NULL,
  `language` varchar(10) NOT NULL,
  `subLang` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `fine`
--

CREATE TABLE `fine` (
  `fid` int(11) NOT NULL,
  `username` varchar(20) NOT NULL,
  `rid` varchar(10) NOT NULL,
  `cid` int(11) NOT NULL,
  `setDate` bigint(20) NOT NULL,
  `amountDue` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `laptop`
--

CREATE TABLE `laptop` (
  `lid` int(11) NOT NULL,
  `rid` varchar(10) NOT NULL,
  `manufacturer` varchar(20) NOT NULL,
  `model` varchar(20) NOT NULL,
  `installedOS` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `requestedresources`
--

CREATE TABLE `requestedresources` (
  `username` varchar(20) NOT NULL,
  `rid` varchar(10) NOT NULL,
  `date` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `resource`
--

CREATE TABLE `resource` (
  `rid` varchar(10) NOT NULL,
  `title` varchar(20) NOT NULL,
  `year` int(11) NOT NULL,
  `imagePath` varchar(50) DEFAULT NULL,
  `numCopies` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `transactions`
--

CREATE TABLE `transactions` (
  `tid` int(11) NOT NULL,
  `fid` int(11) DEFAULT NULL,
  `date` bigint(20) NOT NULL,
  `amount` int(11) NOT NULL,
  `username` varchar(20) NOT NULL,
  `endDate` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `username` varchar(20) NOT NULL,
  `fname` varchar(25) NOT NULL,
  `lname` varchar(25) NOT NULL,
  `mobile` varchar(13) NOT NULL,
  `address` varchar(100) NOT NULL,
  `image` varchar(50) NOT NULL,
  `isLibrarian` tinyint(1) NOT NULL DEFAULT '0',
  `staffNo` int(11) DEFAULT NULL,
  `empDate` bigint(20) DEFAULT NULL,
  `initBalance` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`username`, `fname`, `lname`, `mobile`, `address`, `image`, `isLibrarian`, `staffNo`, `empDate`, `initBalance`) VALUES
('alpha', 'Eduard', 'Zakarian', '+44123456789', 'Sample Address\r\nSwansea\r\nSA1 2AE', 'Images/Avatar4_00000.png', 1, 11111, 1544400000, NULL),
('user1', 'John', 'Peet', '+1235', 'Student fAccommodationSwanseaSA1 8EP', 'bin/Images/user1CustomImage.png', 0, NULL, NULL, 1000),
('user2', 'Matt', 'Josh', '1234', 'Sample Address\r\n', 'Images/Avatar4_00000.png', 0, NULL, NULL, 0),
('user3', 'John', 'Johnny', '07512345678', '6 address lane', 'Images/Avatar_00000.png', 0, NULL, NULL, 0);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `book`
--
ALTER TABLE `book`
  ADD PRIMARY KEY (`bid`),
  ADD UNIQUE KEY `rid` (`rid`);

--
-- Indexes for table `borrowedresources`
--
ALTER TABLE `borrowedresources`
  ADD PRIMARY KEY (`username`,`rid`,`copyID`) USING BTREE,
  ADD KEY `borrowedresources_ibfk_2` (`rid`),
  ADD KEY `borrowedresources_ibfk_3` (`copyID`);

--
-- Indexes for table `copy`
--
ALTER TABLE `copy`
  ADD PRIMARY KEY (`copyID`),
  ADD KEY `rid` (`rid`);

--
-- Indexes for table `copyhistory`
--
ALTER TABLE `copyhistory`
  ADD PRIMARY KEY (`chid`),
  ADD KEY `rid` (`rid`),
  ADD KEY `cid` (`cid`),
  ADD KEY `username` (`username`);

--
-- Indexes for table `dvd`
--
ALTER TABLE `dvd`
  ADD PRIMARY KEY (`did`),
  ADD KEY `rid` (`rid`);

--
-- Indexes for table `fine`
--
ALTER TABLE `fine`
  ADD PRIMARY KEY (`fid`),
  ADD KEY `cid` (`cid`),
  ADD KEY `username` (`username`),
  ADD KEY `rid` (`rid`);

--
-- Indexes for table `laptop`
--
ALTER TABLE `laptop`
  ADD PRIMARY KEY (`lid`),
  ADD KEY `rid` (`rid`);

--
-- Indexes for table `requestedresources`
--
ALTER TABLE `requestedresources`
  ADD PRIMARY KEY (`username`,`rid`),
  ADD KEY `rid` (`rid`);

--
-- Indexes for table `resource`
--
ALTER TABLE `resource`
  ADD PRIMARY KEY (`rid`);

--
-- Indexes for table `transactions`
--
ALTER TABLE `transactions`
  ADD PRIMARY KEY (`tid`),
  ADD KEY `username` (`username`),
  ADD KEY `fid` (`fid`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`username`),
  ADD UNIQUE KEY `staffNo` (`staffNo`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `book`
--
ALTER TABLE `book`
  MODIFY `bid` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `copyhistory`
--
ALTER TABLE `copyhistory`
  MODIFY `chid` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `dvd`
--
ALTER TABLE `dvd`
  MODIFY `did` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `laptop`
--
ALTER TABLE `laptop`
  MODIFY `lid` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `transactions`
--
ALTER TABLE `transactions`
  MODIFY `tid` int(11) NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `book`
--
ALTER TABLE `book`
  ADD CONSTRAINT `book_ibfk_1` FOREIGN KEY (`rid`) REFERENCES `resource` (`rid`) ON DELETE CASCADE;

--
-- Constraints for table `borrowedresources`
--
ALTER TABLE `borrowedresources`
  ADD CONSTRAINT `borrowedresources_ibfk_1` FOREIGN KEY (`username`) REFERENCES `users` (`username`),
  ADD CONSTRAINT `borrowedresources_ibfk_2` FOREIGN KEY (`rid`) REFERENCES `resource` (`rid`),
  ADD CONSTRAINT `borrowedresources_ibfk_3` FOREIGN KEY (`copyID`) REFERENCES `copy` (`copyID`);

--
-- Constraints for table `copy`
--
ALTER TABLE `copy`
  ADD CONSTRAINT `copy_ibfk_1` FOREIGN KEY (`rid`) REFERENCES `resource` (`rid`);

--
-- Constraints for table `copyhistory`
--
ALTER TABLE `copyhistory`
  ADD CONSTRAINT `copyhistory_ibfk_1` FOREIGN KEY (`rid`) REFERENCES `resource` (`rid`),
  ADD CONSTRAINT `copyhistory_ibfk_2` FOREIGN KEY (`cid`) REFERENCES `copy` (`copyID`),
  ADD CONSTRAINT `copyhistory_ibfk_3` FOREIGN KEY (`username`) REFERENCES `users` (`username`);

--
-- Constraints for table `dvd`
--
ALTER TABLE `dvd`
  ADD CONSTRAINT `dvd_ibfk_1` FOREIGN KEY (`rid`) REFERENCES `resource` (`rid`);

--
-- Constraints for table `fine`
--
ALTER TABLE `fine`
  ADD CONSTRAINT `fine_ibfk_1` FOREIGN KEY (`cid`) REFERENCES `copy` (`copyID`),
  ADD CONSTRAINT `fine_ibfk_2` FOREIGN KEY (`username`) REFERENCES `users` (`username`),
  ADD CONSTRAINT `fine_ibfk_3` FOREIGN KEY (`rid`) REFERENCES `resource` (`rid`);

--
-- Constraints for table `laptop`
--
ALTER TABLE `laptop`
  ADD CONSTRAINT `laptop_ibfk_1` FOREIGN KEY (`rid`) REFERENCES `resource` (`rid`);

--
-- Constraints for table `requestedresources`
--
ALTER TABLE `requestedresources`
  ADD CONSTRAINT `requestedresources_ibfk_1` FOREIGN KEY (`username`) REFERENCES `users` (`username`),
  ADD CONSTRAINT `requestedresources_ibfk_2` FOREIGN KEY (`rid`) REFERENCES `resource` (`rid`);

--
-- Constraints for table `transactions`
--
ALTER TABLE `transactions`
  ADD CONSTRAINT `transactions_ibfk_1` FOREIGN KEY (`username`) REFERENCES `users` (`username`),
  ADD CONSTRAINT `transactions_ibfk_2` FOREIGN KEY (`fid`) REFERENCES `fine` (`fid`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
