drop table if exists black_list;
drop table if exists orders;
drop table if exists user;
drop table if exists auth_user;
drop table if exists books;
create schema if not exists dao_test collate utf8_general_ci;

create table if not exists auth_user
(
	id int auto_increment
		primary key,
	username varchar(45) not null,
	password varchar(45) not null,
	role enum('USER', 'ADMIN') default 'USER' not null,
	constraint username_UNIQUE
		unique (username)
);

INSERT INTO auth_user(username, password, role) VALUES('myTestUser', '123456', 'USER');
INSERT INTO auth_user(username, password, role) VALUES('myAdmin', 'admin', 'ADMIN');
INSERT INTO auth_user(username, password, role) VALUES('admin', 'admin', 'ADMIN');
INSERT INTO auth_user(username, password, role) VALUES('user', 'user', 'ADMIN');

create table if not exists black_list
(
	id int auto_increment
		primary key,
	date_block date not null,
	auth_user_id int not null,
	constraint auth_user_id_UNIQUE
		unique (auth_user_id),
	constraint fk_Black_List_Auth_User
		foreign key (auth_user_id) references auth_user (id)
);

INSERT INTO  black_list(date_block, auth_user_id) values ('2020-04-12', 1);


create table if not exists books
(
	id int auto_increment
		primary key,
	name varchar(45) not null,
	author varchar(45) not null
);

INSERT INTO  books(name, author) values ('First book', 'KrylovichVI');
INSERT INTO  books(name, author) values ('Second book', 'KrylovichVI');


create table if not exists orders
(
	id int auto_increment
		primary key,
	name varchar(60) not null,
	auth_user_id int not null,
	date date not null,
	status enum('IN_PROCESSING', 'CONFIRMED', 'CANCELED') default 'IN_PROCESSING' not null,
	constraint fk_Order_Auth_User
		foreign key (auth_user_id) references auth_user (id)
);

INSERT INTO orders (name, auth_user_id, date) VALUES ('My First Order', 3, '2020-03-12');
INSERT INTO orders (name, auth_user_id, date) VALUES ('My Second Order', 4, '2020-06-14');

create index fk_Order_Auth_User_idx
	on orders (auth_user_id);

create table if not exists user
(
	id int auto_increment
		primary key,
	first_name varchar(45) not null,
	last_name varchar(45) not null,
	phone varchar(45) null,
	email varchar(45) not null,
	auth_id int not null,
	constraint auth_id_UNIQUE
		unique (auth_id),
	constraint fk_User_Auth_User
		foreign key (auth_id) references auth_user (id)
);

 INSERT INTO user(first_name, last_name, phone, email, auth_id) VALUES('MyTestName','TestLastName','80295644428','1@mail.ru', 1);
INSERT INTO user(first_name, last_name, phone, email, auth_id) VALUES('Vitaly','Krylovich','+375295644327','KrylovichVI@mail.ru',2);
