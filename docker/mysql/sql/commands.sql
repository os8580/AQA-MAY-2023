CREATE TABLE Persons
(
    PersonID  int UNIQUE PRIMARY KEY AUTO_INCREMENT,
    LastName  varchar(255),
    FirstName varchar(255),
    Title     varchar(255),
    Gender    varchar(255)
);

CREATE TABLE BadPersons
(
    PersonID  int UNIQUE PRIMARY KEY AUTO_INCREMENT,
    LastName  varchar(255),
    FirstName varchar(255),
    Title     varchar(255),
    Gender    varchar(255)
);

CREATE TABLE Phones
(
    PhoneID int UNIQUE PRIMARY KEY AUTO_INCREMENT,
    PersonID int,
    Phone varchar(255)
);

alter table Phones add column email varchar(255);
alter table Phones drop column email;

alter table Phones add foreign key (PersonID) references Persons(PersonID);

insert into Persons (LastName, FirstName, Title, Gender) VALUES ('John', 'Doe', 'Mr', 'male');
insert into Persons (LastName, FirstName, Title, Gender) VALUES ('Jane', 'Doe', 'Mrs', 'female');
insert into Persons (LastName, FirstName, Title, Gender) VALUES ('Johnny', 'Depp', 'Mr', 'male');

insert into BadPersons (LastName, FirstName, Title, Gender) VALUES ('John', 'Doe', 'Mr', 'male');

insert into Phones (PersonID, Phone) VALUES (1, '+5555555');
insert into Phones (PersonID, Phone) VALUES (2, '+7777777');
insert into Phones (PersonID, Phone) VALUES (3, '+5577777');


select * from Persons;
select * from Phones;

select * from Persons where LastName in (select LastName from BadPersons);
select * from Persons where PersonID BETWEEN -10 AND 2;

select p.LastName, p.FirstName, bp.Gender from Persons p INNER join BadPersons bp on p.PersonID != bp.PersonID;

SELECT PersonID FROM Persons union all SELECT PersonID FROM Phones;

UPDATE Phones SET Phone = '+66778899' WHERE PersonID = 3;

SELECT COUNT(*) from Persons where FirstName = 'Doe';
SELECT DISTINCT (FirstName) from Persons;

select FirstName as 'Clients Name' FROM Persons;
