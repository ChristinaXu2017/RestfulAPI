create table request
(
	id bigint not null,
	theType varchar(50) not null,
	poriority varchar(20) not null,
	description varchar(500) not null,
	primary key (id)

);