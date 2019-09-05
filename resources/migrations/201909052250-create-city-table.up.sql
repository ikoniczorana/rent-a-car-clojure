CREATE TABLE city(
  cityid int(11) NOT NULL AUTO_INCREMENT,
  name varchar(50) NOT NULL,
  country varchar(50) NOT NULL,
  PRIMARY KEY (cityid)
);
insert  into city(cityid,name,country) values
(1,'Belgrade','Serbia'),
(2,'Nis','Serbia'),
(3,'Novi Sad','Serbia');
