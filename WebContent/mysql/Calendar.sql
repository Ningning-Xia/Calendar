create database mycalendar;


create table User(
uid int,
userName varchar(255) Unique,
email varchar(255) Unique,
password varchar(255),
primary key (Uid));


//states = 1 means uid1 request to add uid2, but no feedback
//states = 2 means uid1 and uid2 are friends now
create table Friend(
fid int,
uid1 int,
uid2 int,
states int,
primary key (fid),
foreign key (uid1) references User (uid),
foreign key (uid2) references User (uid));



//privacy = 1 means the Event is private;
//privacy = 2 means the Event is public;
//privacy = 3 means the Event is invited_only;
create table Event(
eid int,
uid int,
ename varchar(255),
startTime varchar(255),
endTime varchar(255),
location varchar(255),
description varchar(2550),
video varchar(255),
pic varchar(255),
privacy int,
primary key (eid),
foreign key (uid) references User (uid));

//action = 0 means Default;
//action = 1 means Accept;
//action = 2 means Maybe;
//action = 3 means Decline;
create table Invitation(
eid int,
uid int,
action int,
primary key (eid, uid),
foreign key (eid) references Event (eid),
foreign key (uid) references User (uid));

create table EmailList(
emid int,
eid int,
emails varchar(2550));

