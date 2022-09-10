-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: May 26, 2022 at 07:26 AM
-- Server version: 5.7.31
-- PHP Version: 7.3.21
create database rcs;

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

create database rcs;
use rcs;

-- Database: `rcs`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
CREATE TABLE IF NOT EXISTS `admin` (
  `s_no` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `uid` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `admin_id` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(150) COLLATE utf8_unicode_ci NOT NULL,
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`s_no`),
  UNIQUE KEY `uid` (`uid`),
  UNIQUE KEY `admin_id` (`admin_id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`s_no`, `uid`, `admin_id`, `password`, `created`, `modified`) VALUES
(1, 'cd8afbe1-db5c-48c8-af12-8f8cd1bc69c8', 'admin', '$2a$10$FObTFzujaSEfRANBFfuIOeTXJUfOYe3ZyE5/nlmJpgGP9Dd.M7JhG', '2022-05-19 15:07:58', '2022-05-19 15:07:58');

-- --------------------------------------------------------

--
-- Table structure for table `cleaners`
--

DROP TABLE IF EXISTS `cleaners`;
CREATE TABLE IF NOT EXISTS `cleaners` (
  `s_no` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `uid` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `cleaner_id` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(150) COLLATE utf8_unicode_ci NOT NULL,
  `first` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `last` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `dob` varchar(40) COLLATE utf8_unicode_ci NOT NULL,
  `gender` varchar(40) COLLATE utf8_unicode_ci NOT NULL,
  `phone` varchar(40) COLLATE utf8_unicode_ci NOT NULL,
  `profile_status` varchar(100) COLLATE utf8_unicode_ci NOT NULL DEFAULT 'pending',
  `service_status` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `admin_read` tinyint(1) NOT NULL DEFAULT '0',
  `user_read` tinyint(1) NOT NULL DEFAULT '0',
  `cleaner_read` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`uid`,`s_no`),
  UNIQUE KEY `cleaner_id` (`cleaner_id`),
  UNIQUE KEY `phone` (`phone`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `cleaners`
--

INSERT INTO `cleaners` (`s_no`, `uid`, `cleaner_id`, `password`, `first`, `last`, `dob`, `gender`, `phone`, `profile_status`, `service_status`, `created`, `modified`, `admin_read`, `user_read`, `cleaner_read`) VALUES
(1, '07dc2ab4-8d95-4ec7-9a2c-685f1231c1cb', 'test1', '$2a$10$XfE2bkxFHcnYhFjf8A0kIu3nEBPHRJ2Xcs6TrhtWvh2QfVhHtY5Ta', 'Keerthi', 'Rajan T', '2022-05-04', 'male', '08220199731', 'approved', NULL, '2022-05-19 19:11:00', '2022-05-25 08:25:03', 1, 0, 0),
(2, 'ddaf07fa-4d15-4022-b92e-74b028dacd19', 'cleaner1', '$2a$10$Up4a1XZVGCNhcOCL8YAS8./JoKJnzPQy6oouPgIJlA3dzmLEp5jym', 'Keerthi', 'Rajan T', '2022-05-11', 'male', '8220199730', 'pending', NULL, '2022-05-23 13:30:25', '2022-05-23 13:30:25', 0, 0, 0),
(3, '4b94c03a-0fce-4534-bbd3-75846dac6388', 'cleaner2', '$2a$10$8Id8A6QneSjopHOeleSFZe1/XPbU7iwkRC48XxN0p4Bdq.q41EiI2', 'Keerthi', 'Rajan T', '2022-05-10', 'male', '8220199729', 'pending', NULL, '2022-05-23 13:31:13', '2022-05-23 13:31:13', 0, 0, 0),
(4, 'b31ae82c-3fe6-48fe-991b-39cc056133e4', 'cleaner3', '$2a$10$ASSXgZec0Pmmwff0YZG24eYNAQb8bi6DTvO9NFwoily0SA.bW87o.', 'Keerthi', 'Rajan T', '2022-05-24', 'male', '08220199728', 'approved', NULL, '2022-05-23 13:31:55', '2022-05-25 08:16:45', 0, 0, 1);

-- --------------------------------------------------------

--
-- Table structure for table `feedbacks`
--

DROP TABLE IF EXISTS `feedbacks`;
CREATE TABLE IF NOT EXISTS `feedbacks` (
  `s_no` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `service_id` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `cleaner_uid` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `user_uid` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `rating` int(2) NOT NULL DEFAULT '0',
  `feedback` text COLLATE utf8_unicode_ci NOT NULL,
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `status` varchar(30) COLLATE utf8_unicode_ci NOT NULL DEFAULT 'not-given',
  `admin_read` tinyint(1) NOT NULL DEFAULT '0',
  `user_read` tinyint(1) NOT NULL DEFAULT '0',
  `cleaner_read` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`s_no`),
  UNIQUE KEY `service_id` (`service_id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `feedbacks`
--

INSERT INTO `feedbacks` (`s_no`, `service_id`, `cleaner_uid`, `user_uid`, `rating`, `feedback`, `created`, `modified`, `status`, `admin_read`, `user_read`, `cleaner_read`) VALUES
(1, '2245de01-5fba-4f88-a519-1ef029asdb545', 'b31ae82c-3fe6-48fe-991b-39cc056133e4', '2245de01-5fba-4f88-a519-1ef0297fb545', 2, 'Not Given', '2022-05-23 21:17:14', '2022-05-25 14:56:54', 'not-given', 0, 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `questions`
--

DROP TABLE IF EXISTS `questions`;
CREATE TABLE IF NOT EXISTS `questions` (
  `s_no` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `question_id` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `service_id` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `cleaner_uid` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `user_uid` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `question` text COLLATE utf8_unicode_ci NOT NULL,
  `answer` text COLLATE utf8_unicode_ci NOT NULL,
  `status` varchar(50) COLLATE utf8_unicode_ci NOT NULL DEFAULT 'not-answered',
  `admin_read` tinyint(1) NOT NULL DEFAULT '0',
  `user_read` tinyint(1) NOT NULL DEFAULT '0',
  `cleaner_read` tinyint(1) NOT NULL DEFAULT '0',
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`s_no`),
  UNIQUE KEY `s_no` (`s_no`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `questions`
--

INSERT INTO `questions` (`s_no`, `question_id`, `service_id`, `cleaner_uid`, `user_uid`, `question`, `answer`, `status`, `admin_read`, `user_read`, `cleaner_read`, `created`, `modified`) VALUES
(1, '7c226e20-43f4-4099-a990-910b0771d540', '2245de01-5fba-4f88-a519-1ef029asdb545', 'b31ae82c-3fe6-48fe-991b-39cc056133e4', '2245de01-5fba-4f88-a519-1ef0297fb545', 'Please complete the feedback', 'Not Answered', 'not-answered', 0, 0, 0, '2022-05-24 05:09:42', '2022-05-24 05:09:42'),
(2, '83f9e0ac-4ac7-411f-8152-ffdb2d5aa5fe', '2245de01-5fba-4f88-a519-1ef029asdb545', 'b31ae82c-3fe6-48fe-991b-39cc056133e4', '2245de01-5fba-4f88-a519-1ef0297fb545', 'How was the service?', 'Not Answered', 'not-answered', 0, 0, 0, '2022-05-24 13:28:26', '2022-05-24 13:28:26'),
(3, 'c65e7fc1-aae3-468d-b7d1-cc7c2cb8d531', '2245de01-5fba-4f88-a519-1ef029asdb545', 'b31ae82c-3fe6-48fe-991b-39cc056133e4', '2245de01-5fba-4f88-a519-1ef0297fb545', 'How was the service\n', 'Not Answered', 'not-answered', 0, 1, 0, '2022-05-24 13:29:29', '2022-05-25 05:14:56'),
(4, 'b47541b8-1aa9-49cf-b3b3-6096e16455b6', '2245de01-5fba-4f88-a519-1ef029asdb545', 'b31ae82c-3fe6-48fe-991b-39cc056133e4', '2245de01-5fba-4f88-a519-1ef0297fb545', 'How was the service ??', 'Okey', 'answered', 0, 0, 0, '2022-05-24 13:30:02', '2022-05-24 17:36:53');

-- --------------------------------------------------------

--
-- Table structure for table `services`
--

DROP TABLE IF EXISTS `services`;
CREATE TABLE IF NOT EXISTS `services` (
  `s_no` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `service_id` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `user_uid` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `cleaner_uid` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `room_count` int(11) NOT NULL,
  `address` varchar(150) COLLATE utf8_unicode_ci NOT NULL,
  `pincode` int(11) NOT NULL,
  `time_from` timestamp NOT NULL,
  `time_to` timestamp NOT NULL,
  `contact_number` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `status` varchar(50) COLLATE utf8_unicode_ci NOT NULL DEFAULT 'pending',
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `admin_read` tinyint(1) NOT NULL DEFAULT '0',
  `user_read` tinyint(1) NOT NULL DEFAULT '0',
  `cleaner_read` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`s_no`),
  UNIQUE KEY `service_id` (`service_id`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `services`
--

INSERT INTO `services` (`s_no`, `service_id`, `user_uid`, `cleaner_uid`, `room_count`, `address`, `pincode`, `time_from`, `time_to`, `contact_number`, `status`, `created`, `modified`, `admin_read`, `user_read`, `cleaner_read`) VALUES
(1, 'cd8afbe1-db5c-48c8-af12-8f8cd1b3469c5\r\n', 'cd8afbe1-db5c-48c8-af12-8f8cd1bc69c5', 'b31ae82c-3fe6-48fe-991b-39cc056133e4', 2, '23,south street,chennai', 600000, '2022-05-25 07:04:13', '2022-05-25 07:04:13', '8220199731', 'booked', '2022-05-23 15:42:07', '2022-05-25 08:22:57', 0, 0, 1),
(2, '5a37e32c-4a20-4cb2-8749-a861a95a9bds', '5a37e32c-4a20-4cb2-8749-a861a95a9b23', 'b31ae82c-3fe6-48fe-991b-39cc056133e4', 4, '38,south street', 600000, '2022-05-26 07:04:13', '2022-05-26 07:04:13', '8220199731', 'pending', '2022-05-23 15:42:07', '2022-05-23 18:44:28', 0, 0, 0),
(3, '2245de01-5fba-4f88-a519-1ef029asdb545', '2245de01-5fba-4f88-a519-1ef0297fb545', 'b31ae82c-3fe6-48fe-991b-39cc056133e4', 3, '23,north street', 233423, '2022-05-27 15:31:10', '2022-05-27 15:31:10', '8220199731', 'completed', '2022-05-23 15:42:07', '2022-05-25 14:56:30', 0, 1, 1),
(4, 'f007c83e-90c3-4708-9611-eabf6b322474', '2245de01-5fba-4f88-a519-1ef0297fb545', 'b31ae82c-3fe6-48fe-991b-39cc056133e4', 3, 'west street', 12312, '2022-05-24 12:32:00', '2022-05-24 07:37:00', '12323', 'booked', '2022-05-24 07:32:50', '2022-05-25 14:58:47', 0, 1, 1),
(5, '16d6cd36-db63-4cba-93d7-21e7661939ff', '2245de01-5fba-4f88-a519-1ef0297fb545', 'b31ae82c-3fe6-48fe-991b-39cc056133e4', 2, '23, West street', 123456, '2022-05-26 11:55:45', '2022-05-27 06:25:45', '8220199731', 'booked', '2022-05-25 17:28:14', '2022-05-25 20:19:37', 0, 1, 0);

-- --------------------------------------------------------

--
-- Table structure for table `tickets`
--

DROP TABLE IF EXISTS `tickets`;
CREATE TABLE IF NOT EXISTS `tickets` (
  `s_no` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `ticket_id` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `user_uid` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `issue` text COLLATE utf8_unicode_ci NOT NULL,
  `description` text COLLATE utf8_unicode_ci NOT NULL,
  `status` varchar(50) COLLATE utf8_unicode_ci NOT NULL DEFAULT 'not-resolved',
  `admin_read` tinyint(1) NOT NULL DEFAULT '0',
  `user_read` tinyint(1) NOT NULL DEFAULT '0',
  `cleaner_read` tinyint(1) NOT NULL DEFAULT '0',
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `solution` text COLLATE utf8_unicode_ci,
  PRIMARY KEY (`s_no`),
  UNIQUE KEY `ticket_id` (`ticket_id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `tickets`
--

INSERT INTO `tickets` (`s_no`, `ticket_id`, `user_uid`, `issue`, `description`, `status`, `admin_read`, `user_read`, `cleaner_read`, `created`, `modified`, `solution`) VALUES
(1, '49238cf2-f108-4030-8311-5cfcedeb4697', 'cd8afbe1-db5c-48c8-af12-8f8cd1bc69c5', 'NetWork', 'Not connected', 'resolved', 0, 0, 0, '2022-05-20 05:56:51', '2022-05-24 13:52:35', 'Check your network '),
(2, '78cff376-4d71-4932-868e-b6fb134e8df7', '2245de01-5fba-4f88-a519-1ef0297fb545', 'NetWork', 'Not connected', 'resolved', 0, 1, 0, '2022-05-24 07:17:21', '2022-05-25 14:54:42', 'Wait for network connection'),
(3, 'a1e2dcc7-9f00-4ed4-8fc9-584028520557', '2245de01-5fba-4f88-a519-1ef0297fb545', 'Network1', 'Not connected', 'not-resolved', 0, 0, 0, '2022-05-24 13:03:17', '2022-05-24 13:03:17', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `transactions`
--

DROP TABLE IF EXISTS `transactions`;
CREATE TABLE IF NOT EXISTS `transactions` (
  `s_no` int(11) NOT NULL AUTO_INCREMENT,
  `service_id` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `payment_id` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `payer_id` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `user_uid` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `status` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `amount` float NOT NULL,
  PRIMARY KEY (`s_no`),
  UNIQUE KEY `service_id` (`service_id`),
  UNIQUE KEY `payment_id` (`payment_id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `transactions`
--

INSERT INTO `transactions` (`s_no`, `service_id`, `payment_id`, `payer_id`, `user_uid`, `status`, `created`, `amount`) VALUES
(1, '16d6cd36-db63-4cba-93d7-21e7661939ff', 'PAYID-MKHIX3I9WX37346RP675562T', 'B3ZLG6FSULV6G', '2245de01-5fba-4f88-a519-1ef0297fb545', 'approved', '2022-05-25 20:07:49', 10);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
  `s_no` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `uid` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `user_id` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(150) COLLATE utf8_unicode_ci NOT NULL,
  `first` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `last` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `dob` varchar(40) COLLATE utf8_unicode_ci NOT NULL,
  `gender` varchar(40) COLLATE utf8_unicode_ci NOT NULL,
  `phone` varchar(40) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `question1` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `question2` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `question3` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`s_no`,`uid`),
  UNIQUE KEY `user_id` (`user_id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`s_no`, `uid`, `user_id`, `password`, `first`, `last`, `dob`, `gender`, `phone`, `email`, `created`, `modified`, `question1`, `question2`, `question3`) VALUES
(1, 'cd8afbe1-db5c-48c8-af12-8f8cd1bc69c5', 'keerthi', '$2a$10$FObTFzujaSEfRANBFfuIOeTXJUfOYe3ZyE5/nlmJpgGP9Dd.M7JhG', 'Keerthi', 'Rajan', '2001-01-26', 'male', '91 8220199731', 'capkeerthi@gmail.com', '2022-05-18 07:04:13', '2022-05-18 07:04:13', 'user', 'user', 'user'),
(2, '5a37e32c-4a20-4cb2-8749-a861a95a9b23', 'test1', '$2a$10$cykZlbpx0TCGQnUT9vxUUe7OPA2uK0SQfZWljPKe2zGhY71rK9VJ2', 'Keerthi', 'Rajan', '2001-01-26', 'male', '1234567890', 'capkeerth@gmail.com', '2022-05-19 10:30:57', '2022-05-19 10:30:57', 'user', 'user', 'user'),
(3, '2245de01-5fba-4f88-a519-1ef0297fb545', 'user1', '$2a$10$lDk3IjqeDKB6q.CVvbV69OHvAgnYWkzubZyfMKszcZGz5e/pwlqPG', 'Keerthi', 'Rajan T', '2022-05-12', 'male', '08220199731', 'user1@gmail.com', '2022-05-23 15:31:10', '2022-05-23 15:31:10', 'user', 'user', 'user'),
(4, 'f448c18a-2a75-428a-aeca-d9e945e145dd', 'user2', '$2a$10$F7VV4KGO6cSuQtijU/1FNOyafCvPTj95tY/.Y8LWNuWk3Bk8./JWG', 'Keerthi', 'Rajan T', '2022-05-12', 'male', '08220199731', 'user2@gmail.com', '2022-05-23 15:31:36', '2022-05-23 15:31:36', 'user', 'user', 'user');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
