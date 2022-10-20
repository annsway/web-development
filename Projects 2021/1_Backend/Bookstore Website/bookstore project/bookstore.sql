/*
MySQL for HW bookstore: Database - bookstore
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`bookstore` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `bookstore`;

/*Table structure for table `books` */

DROP TABLE IF EXISTS `books`;

CREATE TABLE `books` (
  `id` varchar(200) NOT NULL,
  `name` varchar(100) NOT NULL,
  `price` double DEFAULT NULL,
  `pnum` int(11) DEFAULT NULL,
  `category` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `books` */

insert  into `books`(`id`,`name`,`price`,`pnum`,`category`) values 
('1001','Java Programming',98,100,'Computer Science')
,('1002','A Tale of Two Cities',10,20,'Novel')
,('1003','The Lord of the Rings',20,30,'Fiction')
,('1004','The Little Prince',19.8,50,'Fiction')
,('1005','The Hobbit',16.6,80,'Fiction')
,('1006','Harry Potter and the Philosopher\'s Stone',9.8,50,'Fiction')
,('1007','The Great Gatsby',28,100,'Novel')
,('1008','Algorithms to Live By: The Computer Science of Human Decisions',9.8,50,'Computer Science')
,('1009','JavaScript: The Good Parts',55,80,'Computer Science')
,('1010','Introduction to Algorithms',15,100,'Computer Science');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `id` int(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`username`,`password`,`id`) values ('annz','123456',0);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
