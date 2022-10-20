/*
SQLyog Ultimate v11.25 (64 bit)
MySQL - 5.6.21-log 
*********************************************************************
*/
/* !40101 SET NAMES utf8 */;

use hw; 
show tables;
create table `t_user` (
	`id` int (11),
	`name` varchar (765),
	`age` int (11),
	`edu` varchar (765),
	`gender` varchar (765)
); 
insert into `t_user` (`id`, `name`, `age`, `edu`, `gender`) values('1','tom','20','BS','male');
insert into `t_user` (`id`, `name`, `age`, `edu`, `gender`) values('2','jerry','19','BS','male');
insert into `t_user` (`id`, `name`, `age`, `edu`, `gender`) values('3','jack','22','AA','male');
insert into `t_user` (`id`, `name`, `age`, `edu`, `gender`) values('4','rose','24','AA','female');

select * from t_user; 