create table driver(
	id serial primary key,
	name varchar(255)
);

create table license(
	id serial primary key,
	number int
);

create table driver_license(
	id serial primary key,
	driver_id int references driver(id) unique,
	license_id int references license(id) unique
);