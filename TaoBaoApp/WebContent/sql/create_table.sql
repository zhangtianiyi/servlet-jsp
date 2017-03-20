--create database taobaoapp

create table store
(
       sid   int(11) primary key auto_increment,
       sname varchar(20) not null,
       snum int(11)
)charset=utf8;

create table goods
(
    gid int(11) primary key auto_increment,
    gname   varchar(20) not null,
    gmade   varchar(20),
    gprice  decimal(18,2) not null,
    gbalance int(11),
    gstore int(11),
    gnum int(11),
    foreign key(gstore) references store(sid)
)charset=utf8;

create table buyrecord 
(
     bid int(10) primary key auto_increment,
     cname varchar(20) not null,
     gname varchar(20) not null,
     gprice decimal(18,2) not null,
     bnum int(10) 
)charset=utf8;


create table customer
(
       cname varchar(20) primary key,
       cpass varchar(20) not null,
       cphone    varchar(20),
       caddress  varchar(20),
       crealname varchar(20) 
)charset=utf8;
