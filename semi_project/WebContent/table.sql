
/* Drop Tables */

DROP TABLE bag CASCADE CONSTRAINTS;
DROP TABLE coupon CASCADE CONSTRAINTS;
DROP TABLE demand CASCADE CONSTRAINTS;
DROP TABLE itemImg CASCADE CONSTRAINTS;
DROP TABLE itemsize CASCADE CONSTRAINTS;
DROP TABLE Look CASCADE CONSTRAINTS;
DROP TABLE item CASCADE CONSTRAINTS;
DROP TABLE noticeBoard CASCADE CONSTRAINTS;
DROP TABLE qnaboard CASCADE CONSTRAINTS;
DROP TABLE reviewboard CASCADE CONSTRAINTS;
DROP TABLE Users CASCADE CONSTRAINTS;




/* Create Tables */

CREATE TABLE bag
(
	bagNum number(10,0) NOT NULL,
	orderAmount number(3,0),
	code varchar2(20) NOT NULL,
	totalPrice number(10,0),
	num number(3,0) NOT NULL,
	email varchar2(30) NOT NULL,
	PRIMARY KEY (bagNum)
);


CREATE TABLE coupon
(
	couponName varchar2(30) NOT NULL,
	email varchar2(30) NOT NULL,
	couponState varchar2(20),
	offerDate date,
	endDate date,
	PRIMARY KEY (couponName, email)
);


CREATE TABLE demand
(
	orderNum number(10,0) NOT NULL,
	orderDate date,
	orderAmount number(3,0),
	state varchar2(15),
	email varchar2(30) NOT NULL,
	code varchar2(20) NOT NULL,
	num number(3,0) NOT NULL,
	addr varchar2(30),
	method varchar2(20),
	totalPrice number(10,0),
	payMoney number(10,0),
	PRIMARY KEY (bagNum)
);


CREATE TABLE item
(
	code varchar2(20) NOT NULL,
	price number(10,0),
	itemName varchar2(20),
	description varchar2(200),
	PRIMARY KEY (code)
);


CREATE TABLE itemImg
(
	imgType varchar2(15) NOT NULL,
	code varchar2(20) NOT NULL,
	imgSrc varchar2(20),
	PRIMARY KEY (imgType, code),
	UNIQUE (imgType, code)
);


CREATE TABLE itemsize
(
	num number(3,0) NOT NULL,
	isize varchar2(15),
	code varchar2(20) NOT NULL,
	amount number(3,0),
	PRIMARY KEY (num)
);


CREATE TABLE Look
(
	num number(3,0) NOT NULL,
	lookCode varchar2(20) NOT NULL,
	code varchar2(20) NOT NULL,
	lookFront varchar2(20),
	lookBack varchar2(20),
	PRIMARY KEY (num)
);


CREATE TABLE noticeBoard
(
	num number(7,0) NOT NULL,
	title varchar2(30),
	content varchar2(60),
	email varchar2(30) NOT NULL,
	PRIMARY KEY (bagNum)
);


CREATE TABLE qnaboard
(
	num number(7,0) NOT NULL,
	title varchar2(30),
	content varchar2(60),
	grp number(4,0),
	lev number(3,0),
	step number(3,0),
	email varchar2(30) NOT NULL,
	PRIMARY KEY (bagNum)
);


CREATE TABLE reviewboard
(
	num number(7,0) NOT NULL,
	title varchar2(30),
	content varchar2(60),
	height number(4,0),
	weight number(3,0),
	email varchar2(30) NOT NULL,
	PRIMARY KEY (bagNum)
);


CREATE TABLE Users
(
	email varchar2(30) NOT NULL,
	password varchar2(20),
	phone varchar2(20),
	addr varchar2(30),
	name varchar2(20),
	regdate date,
	coupon number(10,0),
	point number(20),
	flag number(10,0),
	PRIMARY KEY (email)
);



/* Create Foreign Keys */

ALTER TABLE itemImg
	ADD FOREIGN KEY (code)
	REFERENCES item (code)
;


ALTER TABLE itemsize
	ADD FOREIGN KEY (code)
	REFERENCES item (code)
;


ALTER TABLE Look
	ADD FOREIGN KEY (code)
	REFERENCES item (code)
;


ALTER TABLE bag
	ADD FOREIGN KEY (num)
	REFERENCES itemsize (num)
;


ALTER TABLE demand
	ADD FOREIGN KEY (num)
	REFERENCES itemsize (num)
;


ALTER TABLE bag
	ADD FOREIGN KEY (email)
	REFERENCES Users (email)
;


ALTER TABLE coupon
	ADD FOREIGN KEY (email)
	REFERENCES Users (email)
;


ALTER TABLE demand
	ADD FOREIGN KEY (email)
	REFERENCES Users (email)
;


ALTER TABLE noticeBoard
	ADD FOREIGN KEY (email)
	REFERENCES Users (email)
;


ALTER TABLE qnaboard
	ADD FOREIGN KEY (email)
	REFERENCES Users (email)
;


ALTER TABLE reviewboard
	ADD FOREIGN KEY (email)
	REFERENCES Users (email)
;

