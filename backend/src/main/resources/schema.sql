CREATE TABLE `sctest`.`quiz` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `content` VARCHAR(255) NOT NULL,
  `provided` VARCHAR(45) NOT NULL,
  `expected` VARCHAR(45) NULL,
  `lastupdate` DATE NULL,
  PRIMARY KEY (`id`));
  
  
  
  