DROP DATABASE IF EXISTS frame;
CREATE DATABASE frame;
USE frame;
DROP TABLE IF EXISTS user;
CREATE TABLE user (
  user_id  int primary key auto_increment,
  username varchar(50) unique not null,
  password varchar(255)       not null,
  enabled  tinyint(1)      default 1
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

insert into role
value (1, 'ADMIN');
insert into role
values (2, 'USER');
insert into role
values (3, 'POSTER');

use frame;
insert into user (username, password)
value ("admin", "$2a$10$S7ukTjY7CoR.O2uksyWrjuBDIOCEgBzMPyfAI1aXS36HXLJDKIcdO");
insert into user_role
value (1, 1);
insert into user_role
value (1, 2);

CREATE TABLE post (
  post_id      int primary key auto_increment,
  header       VARCHAR(50) unique,
  previewText  VARCHAR(20),
  posting_date BIGINT,
  post_text    TEXT
);