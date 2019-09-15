CREATE TABLE request(
  requestid int(11) NOT NULL AUTO_INCREMENT,
  name varchar(50) NOT NULL,
  birthyear int(11) NOT NULL,
  idnumber varchar(50) NOT NULL,
  driverlicence varchar(50) NOT NULL,
  beginningyear int(11) NOT NULL,
  fromdate date ,
  todate date ,
  carid int(11) NOT NULL,
  userid int(11) NOT NULL,
  PRIMARY KEY (requestid),
  FOREIGN KEY(carid) REFERENCES car(carid) ON UPDATE CASCADE ON DELETE CASCADE,
  FOREIGN KEY(userid) REFERENCES user(userid) ON UPDATE CASCADE ON DELETE CASCADE

);
insert  into request(requestid, name, birthyear, idnumber, driverlicence, beginningyear,fromDate, toDate, carid, userid) values
(2,'Petar Martinovic',1988,'798547','000004608',2010,'2019-07-05.','2019-07-15.',14,2),
(5,'Mire Sljivic',1985,'795112','000004721','2019-07-25.','2019-07-27',2002,5,2);
