insert into User value(1,"test1","test1@gmail.com","123456");
insert into User value(2,"test2","test2@gmail.com","123456");
insert into User value(3,"test3","test3@gmail.com","123456");
insert into User value(4,"test4","test4@gmail.com","123456");
insert into User value(5,"test5","test5@gmail.com","123456");


insert into Friend value(1, 1, 2, 1);
insert into Friend value(2, 1, 3, 2);
insert into Friend value(3, 1, 4, 2);
insert into Friend value(4, 2 ,3, 1);
insert into Friend value(5, 2 ,5, 2);

insert into Event value(1,1,'test1_Event', '04-13-2013 9:00', '04-13-2013 10:00', 'Columbia University', 'description',null,null,1);
insert into Event value(2,2,'test2_Event', '04-13-2013 9:00', '04-13-2013 10:00', 'Columbia University', 'description',null,null,2);
insert into Event value(3,3,'test3_Event', '04-13-2013 9:00', '04-13-2013 10:00', 'Columbia University', 'description',null,null,3);

insert into Invitation value(1,2,0);
insert into Invitation value(1,3,1);
insert into Invitation value(1,4,2);
insert into Invitation value(2,4,3);
insert into Invitation value(2,5,0);