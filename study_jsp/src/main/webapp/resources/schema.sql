-- 2023.05.26 : member/board 테이블 생성
create table member (
	id varchar(100) not null primary key,
	password varchar(100) not null,
	name varchar(100) not null,
	email varchar(100),
	phone varchar(11),
	regdate datetime not null default now(),
	lastlogin datetime,
	auth char(1) default 0
);

create table board (
	bno int not null primary key auto_increment,
	title varchar(100) not null,
	writer varchar(100) not null,
	regdate datetime default now(),
	content text,
	readcount int default 0,
	image text
);

create table comment (
	cno int not null primary key auto_increment,
	bno int not null default 0,
	writer varchar(100) not null default '익명',
	content text not null,
	regdate datetime default now()
)
