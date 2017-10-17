create table TravelBetter.FeatureSpot
(
	sid int auto_increment
		primary key,
	title varchar(50) null,
	score varchar(5) null,
	address varchar(200) null,
	time varchar(1024) null,
	price varchar(1024) null,
	description varchar(7500) null,
	tip varchar(7500) null,
	constraint FeatureSpot_title_uindex
		unique (title)
)
;

create table TravelBetter.Find
(
	fid int auto_increment
		primary key,
	title varchar(50) not null,
	content varchar(500) null,
	uid int not null,
	time datetime not null,
	like_num int default '0' not null
)
;

create index Find_User_uid_fk
	on Find (uid)
;

create table TravelBetter.FindComment
(
	cid int auto_increment
		primary key,
	fid int not null,
	uid int not null,
	content varchar(200) not null,
	time datetime not null
)
;

create index FindComment_User_uid_fk
	on FindComment (uid)
;

create table TravelBetter.FindLike
(
	lid int auto_increment
		primary key,
	fid int not null,
	uid int not null
)
;

create table TravelBetter.FindPicture
(
	pid int auto_increment
		primary key,
	path varchar(40) not null,
	fid int not null,
	constraint FindPicture_Find_fid_fk
		foreign key (fid) references TravelBetter.Find (fid)
)
;

create index FindPicture_Find_fid_fk
	on FindPicture (fid)
;

create table TravelBetter.Hotel
(
	hid int auto_increment
		primary key,
	title varchar(200) null,
	score varchar(5) null,
	address varchar(200) null
)
;

create table TravelBetter.Other
(
	name varchar(50) not null,
	address varchar(300) not null,
	phone varchar(50) not null,
	score varchar(10) not null,
	a int not null,
	b double not null,
	c int not null,
	oid int auto_increment
		primary key
)
;

create table TravelBetter.User
(
	uid int auto_increment
		primary key,
	username varchar(20) not null,
	password varchar(40) not null,
	gender int default '0' not null,
	nickname varchar(20) null,
	sign varchar(50) null,
	avatar varchar(40) not null,
	email varchar(320) null,
	birthday date null,
	address varchar(50) null,
	token varchar(40) not null,
	constraint User_username_uindex
		unique (username),
	constraint User_token_uindex
		unique (token)
)
;

alter table Find
	add constraint Find_User_uid_fk
		foreign key (uid) references TravelBetter.User (uid)
;

alter table FindComment
	add constraint FindComment_User_uid_fk
		foreign key (uid) references TravelBetter.User (uid)
;


