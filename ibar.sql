SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for hibernate_sequence
-- ----------------------------
DROP TABLE IF EXISTS `hibernate_sequence`;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for income_info
-- ----------------------------
DROP TABLE IF EXISTS `income_info`;
CREATE TABLE `income_info` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `income` double(20,0) NOT NULL COMMENT 'for 12 months',
  `deductions` double(20,0) NOT NULL COMMENT 'for 12 months',
  `national_id` varchar(20) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `nat_id_u` (`national_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for kyc_info
-- ----------------------------
DROP TABLE IF EXISTS `kyc_info`;
CREATE TABLE `kyc_info` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) NOT NULL,
  `last_employer_name` varchar(255) NOT NULL,
  `last_emp_start_date` date NOT NULL,
  `national_id` varchar(255) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for loan_request
-- ----------------------------
DROP TABLE IF EXISTS `loan_request`;
CREATE TABLE `loan_request` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `national_id` varchar(255) NOT NULL,
  `loan_amount` double(20,0) NOT NULL,
  `period` int(20) NOT NULL,
  `date_created` date NOT NULL,
  `status` int(20) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `username_u` (`username`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `user_id` int(20) NOT NULL,
  `role_id` int(20) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `FK6371638621` (`role_id`) USING BTREE,
  KEY `FK55itppkw3i07do3h7qoclqd4k` (`user_id`) USING BTREE,
  CONSTRAINT `FK55itppkw3i07do3h7qoclqd4k` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FK6371638621` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

SET FOREIGN_KEY_CHECKS = 1;
