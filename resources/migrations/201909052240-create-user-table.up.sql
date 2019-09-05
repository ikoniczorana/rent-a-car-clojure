CREATE TABLE user(
  userid int(11) NOT NULL AUTO_INCREMENT,
  firstname varchar(50) NOT NULL,
  lastname varchar(50) NOT NULL,
  username varchar(50) NOT NULL,
  password varchar(50) NOT NULL,
  email varchar(50) NOT NULL,
  assignedrole varchar(10) NOT NULL,
  PRIMARY KEY (userid)
);


insert into user(userid,firstname,lastname,username,password,email,assignedrole) values
(1,'Zorana','Ikonic','zokano','z123','ikonic.zorana@gmail.com','admin'),
(2,'Jelena','Ikonic','jeca','j123','ikonic.jelena@gmail.com','user'),
(3,'Marija','Sljuka','msljuka','m123','msljuka@gmail.com','user');

