CREATE TABLE `reto`.`candidates` (
	`id` BIGINT NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(45) NOT NULL COLLATE 'utf8mb3_general_ci',
	`email` VARCHAR(60) NOT NULL COLLATE 'utf8mb3_general_ci',
	`gender` VARCHAR(20) NOT NULL COLLATE 'utf8mb3_general_ci',
	`salary_expected` DOUBLE NOT NULL,
	`creation_date` DATE NOT NULL,
	`user_create` VARCHAR(45) NOT NULL COLLATE 'utf8mb3_general_ci',
	`update_date` DATE NULL DEFAULT NULL,
	`user_update` VARCHAR(45) NULL DEFAULT NULL COLLATE 'utf8mb3_general_ci',
	PRIMARY KEY (`id`) USING BTREE
);