CREATE TABLE car(
  carid int(11) NOT NULL AUTO_INCREMENT,
  type varchar(50) NOT NULL,
  brand varchar(50) NOT NULL,
  cityid int(11) NOT NULL,
  PRIMARY KEY (carid),
  FOREIGN KEY(cityid) REFERENCES city(cityid) ON UPDATE CASCADE ON DELETE CASCADE

);

insert into car(carid,type,brand,cityid) values
(3,'Hatchback','Suzuki',1),
(4,'Hatchback','Audi',1),
(5,'MPV','Ford',1),
(6,'MPV','Mercedes Benz',2),
(7,'Coupe','Audi',1),
(8,'Coupe','Mercedes Benz',3),
(9,'Convertible','Audi',1),
(10,'Convertible','Volkswagen',1),
(11,'Convertible','Volkswagen',2),
(12,'Hatchback','Volkswagen',1),
(13,'Hatchback','Volkswagen',2),
(14,'Hatchback','Volkswagen',3);
