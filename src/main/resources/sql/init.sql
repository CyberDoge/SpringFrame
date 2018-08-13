DROP DATABASE IF EXISTS frame;
CREATE DATABASE frame;
USE frame;
DROP TABLE IF EXISTS user;
CREATE TABLE user (
  user_id  int primary key auto_increment,
  username varchar(50) unique not null,
  password varchar(255)       not null
);
DROP TABLE IF EXISTS role;
CREATE TABLE role (
  role_id int(11) NOT NULL AUTO_INCREMENT,
  role    varchar(255)     DEFAULT NULL,
  PRIMARY KEY (role_id)
);

DROP TABLE IF EXISTS user_role;
CREATE TABLE user_role (
  user_id int(11) NOT NULL,
  role_id int(11) NOT NULL,
  PRIMARY KEY (user_id, role_id),
  KEY `FKa68196081fvovjhkek5m97n3y` (`role_id`),
  CONSTRAINT `FK859n2jvi8ivhui0rl0esws6o` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `FKa68196081fvovjhkek5m97n3y` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`)
);