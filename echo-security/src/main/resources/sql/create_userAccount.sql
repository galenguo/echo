create table eht_user(
	id varchar(30) not null,
	create_time timestamp not null,
	create_by varchar(50),
	last_modify_time timestamp not null,
	last_modify_by varchar(50),
	account varchar(30) not null,
	password varchar(30) not null,
	password_hash varchar(100) not null,
	primary key(id)
);