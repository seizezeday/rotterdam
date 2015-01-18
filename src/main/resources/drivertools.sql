DROP TABLE IF EXISTS `USERROLE`;

CREATE TABLE `USERROLE` (
  `idUserRole` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(75) DEFAULT NULL,
  PRIMARY KEY (`idUserRole`),
  UNIQUE KEY `idUserCategory_UNIQUE` (`idUserRole`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

INSERT INTO
  `USERROLE` (`idUserRole`, `name`)
VALUES
  ('1', 'Admin'), ('2', 'Moderator'), ('3', 'Driver'), ('4', 'Unpaid');

DROP TABLE IF EXISTS `USER`;

CREATE TABLE `USER` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `firstname` text,
  `surname` text,
  `zipcode` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `idUserRole` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `idUserCategory_idx_idx` (`idUserRole`),
  CONSTRAINT `idUseRole_idx` FOREIGN KEY (`idUserRole`) REFERENCES `USERROLE` (`idUserRole`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `SESSION`;

CREATE TABLE `SESSION` (
  `sessionId` varchar(100) NOT NULL,
  `idUser` int(11) DEFAULT NULL,
  `createTime` timestamp NULL DEFAULT NULL,
  `lastAccessedTime` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`sessionId`),
  UNIQUE KEY `sessionId_UNIQUE` (`sessionId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `RULETYPE`;

CREATE TABLE `RULETYPE` (
  `idRuleType` int(11) NOT NULL,
  `name` varchar(75) DEFAULT NULL,
  `enabled` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`idRuleType`),
  UNIQUE KEY `idRuleType_UNIQUE` (`idRuleType`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `RoleRule`;


CREATE TABLE `RoleRule` (
  `idRoleRule` int(11) NOT NULL AUTO_INCREMENT,
  `idRuleType` int(11) DEFAULT NULL,
  `idUserRole` int(11) DEFAULT NULL,
  `enabled` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idRoleRule`),
  UNIQUE KEY `idCategoryRule_UNIQUE` (`idRoleRule`),
  KEY `idUserCategoty_idx` (`idUserRole`),
  KEY `idRuleType_idx` (`idRuleType`),
  CONSTRAINT `idUserCategoty` FOREIGN KEY (`idUserRole`) REFERENCES `USERROLE` (`idUserRole`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `idRuleType` FOREIGN KEY (`idRuleType`) REFERENCES `RULETYPE` (`idRuleType`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `WORKHOURS`;
CREATE TABLE `WORKHOURS` (
  `idWorkHours` bigint(20) NOT NULL AUTO_INCREMENT,
  `date` date NOT NULL,
  `startWorkingTime` time NOT NULL,
  `endWorkingTime` time NOT NULL,
  `restTime` int(11) NOT NULL,
  `rideType` varchar(255) NOT NULL,
  `idUser` int(11) NOT NULL,
  PRIMARY KEY (`idWorkHours`),
  UNIQUE KEY `idWorkHours_UNIQUE` (`idWorkHours`),
  KEY `fk_workhours_user` (`idUser`),
  CONSTRAINT `fk_workhours_user` FOREIGN KEY (`idUser`) REFERENCES `USER` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8;