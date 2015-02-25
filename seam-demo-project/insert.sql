CREATE DATABASE `seam-demo-project` /*!40100 DEFAULT CHARACTER SET latin1 */;

CREATE TABLE `seam-demo-project`.`New Table` (
  `id` INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(200),
  `password` VARCHAR(200),
  `firstName` VARCHAR(200),
  `lastName` VARCHAR(200),
  `email` VARCHAR(200),
  `deleted` TINYINT(1) UNSIGNED DEFAULT 0,
  PRIMARY KEY (`id`)
)
ENGINE = InnoDB;

ALTER TABLE `seam-demo-project`.`new table` RENAME TO `seam-demo-project`.`tb_user`;

INSERT INTO `seam-demo-project`.`tb_user` (username, password, firstName, lastName, email) VALUES('admin@demo.com', 'eb1e2265ed44e7ca952564fe5ad2bd84', 'admin', 'admin', 'admin@demo.com');





