----Table----

CREATE TABLE `searchat_user` (
	`email` VARCHAR(255) NULL DEFAULT NULL COLLATE 'latin1_general_ci',
	`firstname` VARCHAR(255) NULL DEFAULT NULL COLLATE 'latin1_general_ci',
	`lastname` VARCHAR(255) NULL DEFAULT NULL COLLATE 'latin1_general_ci',
	`password` VARCHAR(255) NULL DEFAULT NULL COLLATE 'latin1_general_ci',
	`profile_information_username` VARCHAR(255) NOT NULL COLLATE 'latin1_general_ci',
	PRIMARY KEY (`profile_information_username`)
)

CREATE TABLE `searchat_activity` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`date` DATETIME NULL DEFAULT NULL,
	`description` VARCHAR(255) NULL DEFAULT NULL COLLATE 'latin1_general_ci',
	`location` VARCHAR(255) NULL DEFAULT NULL COLLATE 'latin1_general_ci',
	`name` VARCHAR(255) NULL DEFAULT NULL COLLATE 'latin1_general_ci',
	`price` VARCHAR(255) NULL DEFAULT NULL COLLATE 'latin1_general_ci',
	`administrator_profile_information_username` VARCHAR(255) NULL DEFAULT NULL COLLATE 'latin1_general_ci',
	PRIMARY KEY (`id`),
	INDEX `FKreeanqgq36q8qv57b4hnd75v8` (`administrator_profile_information_username`)
)

CREATE TABLE `searchat_profile_information` (
	`username` VARCHAR(255) NOT NULL COLLATE 'latin1_general_ci',
	`about_you` VARCHAR(255) NULL DEFAULT NULL COLLATE 'latin1_general_ci',
	`image` LONGBLOB NULL,
	`nationality` VARCHAR(255) NULL DEFAULT NULL COLLATE 'latin1_general_ci',
	PRIMARY KEY (`username`)
)

CREATE TABLE `searchat_lenguages` (
	`lenguage` VARCHAR(255) NOT NULL COLLATE 'latin1_general_ci',
	PRIMARY KEY (`lenguage`)
)


CREATE TABLE `searchat_activities_lenguages` (
	`activities_id` INT(11) NOT NULL,
	`lenguage_lenguage` VARCHAR(255) NOT NULL COLLATE 'latin1_general_ci',
	INDEX `FKdgdqopij3x5mqqavbb4q70dx0` (`lenguage_lenguage`),
	INDEX `FKj6nyv263k40pm5w5wifelbi2g` (`activities_id`)
)

CREATE TABLE `searchat_profile_information` (
	`username` VARCHAR(255) NOT NULL COLLATE 'latin1_general_ci',
	`about_you` VARCHAR(255) NULL DEFAULT NULL COLLATE 'latin1_general_ci',
	`image` LONGBLOB NULL,
	`nationality` VARCHAR(255) NULL DEFAULT NULL COLLATE 'latin1_general_ci',
	PRIMARY KEY (`username`)
)

CREATE TABLE `searchat_activities_users` (
	`activities_id` INT(11) NOT NULL,
	`user_username` VARCHAR(255) NOT NULL COLLATE 'latin1_general_ci',
	INDEX `FKg0ll76ry1qghk9wklshqnf2s2` (`user_username`),
	INDEX `FKkovwftorvkl3ae1amwmx1d1e2` (`activities_id`)
)

CREATE TABLE `searchat_profile_informations_lenguages` (
	`profile_username` VARCHAR(255) NOT NULL COLLATE 'latin1_general_ci',
	`lenguage_lenguage` VARCHAR(255) NOT NULL COLLATE 'latin1_general_ci',
	INDEX `FK2u6d28ok2i0vvewbuqqoi40ts` (`lenguage_lenguage`),
	INDEX `FKa1d4rthyqbev67s3855qi7df` (`profile_username`)
)

--drop tables--
drop table searchat_profile_informations_lenguages;
drop table searchat_activities_users;
drop table searchat_activities_lenguages;
drop table searchat_profile_information;
drop table searchat_user;
drop table searchat_activity;
drop table searchat_lenguages;