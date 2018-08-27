
/* Drop Tables */

DROP TABLE coupon CASCADE CONSTRAINTS;
DROP TABLE buy CASCADE CONSTRAINTS;
DROP TABLE pay CASCADE CONSTRAINTS;
DROP TABLE itemImg CASCADE CONSTRAINTS;
DROP TABLE itemsize CASCADE CONSTRAINTS;
DROP TABLE Look CASCADE CONSTRAINTS;
DROP TABLE item CASCADE CONSTRAINTS;
DROP TABLE noticeBoard CASCADE CONSTRAINTS;
DROP TABLE qnaboard CASCADE CONSTRAINTS;
DROP TABLE reviewboard CASCADE CONSTRAINTS;
DROP TABLE Users CASCADE CONSTRAINTS;




/* Create Tables */


CREATE TABLE coupon
(
	couponNum number(10,0) PRIMARY KEY NOT NULL,
	couponName varchar2(30) NOT NULL,
	email varchar2(30) NOT NULL,
	couponState varchar2(20),
	offerDate date,
	endDate date
	
);

CREATE TABLE BUY
(
	buyNum number(10,0) PRIMARY KEY NOT NULL,
	orderNum number(10,0),
	code varchar2(20),
	isize varchar2(15),
	orderAmount number(3,0),
	Price number(10,0)
);

CREATE TABLE PAY
(
	orderNum number(10,0) PRIMARY KEY NOT NULL,
	orderDate date,
	state varchar2(15),
	method varchar2(30),
	addr varchar2(100),
	email varchar2(30) NOT NULL,
	totalPrice number(10,0),
	payMoney number(10,0)
);

CREATE TABLE item
(
	code varchar2(20) PRIMARY KEY NOT NULL,
	price number(10,0),
	itemName varchar2(30),
	description varchar2(200)
	
);
CREATE TABLE itemImg
(
	imgType varchar2(20) NOT NULL,
	code varchar2(20) NOT NULL,
	imgSrc varchar2(40),
	PRIMARY KEY (imgType, code)
);
CREATE TABLE itemsize
(
	isize varchar2(15) NOT NULL,
	code varchar2(20) NOT NULL,
	amount number(3,0),
	PRIMARY KEY (isize,code)
);
CREATE TABLE Look
(
	num number(3,0) PRIMARY KEY NOT NULL,
	lookCode varchar2(20) NOT NULL,
	code varchar2(20) NOT NULL,
	lookFront varchar2(40),
	lookBack varchar2(40)
);
CREATE TABLE noticeBoard
(
	num number(7,0) PRIMARY KEY NOT NULL,
	name varchar2(15),
	email varchar2(30) NOT NULL,
	title varchar2(40),
	content varchar2(200),	
	hit number(3,0),
	regdate date
);


CREATE TABLE qnaboard
(
	num number(7,0) PRIMARY KEY NOT NULL,
	name varchar2(20),
	email varchar2(30) NOT NULL,	
	title varchar2(40),
	content varchar2(200),
	grp number(7,0),
	lev number(3,0),
	step number(3,0),
	hit number(3,0),
	regdate date
);


CREATE TABLE reviewboard
(
	num number(7,0) PRIMARY KEY NOT NULL,
	name varchar2(20),
	email varchar2(30) NOT NULL,
	title varchar2(40),
	content varchar2(200),
	height number(4,0),
	weight number(3,0),
	hit number(3,0),
	regdate date,
	img varchar2(40),
	itemImg varchar2(40),
	code varchar2(20)
);

CREATE TABLE Users
(
	email varchar2(30) PRIMARY KEY NOT NULL,
	password varchar2(20),
	question number(5,0),
	answer varchar2(30),
	phone varchar2(20),
	addr varchar2(100),
	name varchar2(20),
	regdate date,
	coupon number(10,0),
	flag number(1,0)
	
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


ALTER TABLE buy
	ADD FOREIGN KEY (isize, code)
	REFERENCES itemsize (isize, code)
;

ALTER TABLE buy
	ADD FOREIGN KEY (orderNum)
	REFERENCES pay (orderNum)
;

ALTER TABLE pay
	ADD FOREIGN KEY (email)
	REFERENCES users (email)
;


ALTER TABLE coupon
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


CREATE SEQUENCE look_seq;


CREATE OR REPLACE TRIGGER item_amount
AFTER INSERT ON BUY
FOR EACH ROW
BEGIN
	UPDATE ITEMSIZE SET AMOUNT=AMOUNT-:NEW.ORDERAMOUNT WHERE CODE=:NEW.CODE AND ISIZE=:NEW.ISIZE AND :NEW.ORDERNUM>0;
END;
/


CREATE OR REPLACE TRIGGER item_amount2
AFTER UPDATE ON BUY
FOR EACH ROW
BEGIN
	UPDATE ITEMSIZE SET AMOUNT=AMOUNT-:NEW.ORDERAMOUNT WHERE CODE=:NEW.CODE AND ISIZE=:NEW.ISIZE AND :NEW.ORDERNUM>0;
END;
/

CREATE OR REPLACE TRIGGER item_amount3
AFTER DELETE ON BUY
FOR EACH ROW
BEGIN
	UPDATE ITEMSIZE SET AMOUNT=AMOUNT+:NEW.ORDERAMOUNT WHERE CODE=:NEW.CODE AND ISIZE=:NEW.ISIZE AND :NEW.ORDERNUM>0;
END;
/


