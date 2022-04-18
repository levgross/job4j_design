create table carOwner(
	id serial primary key,
	name varchar(255)
);

create table car(
	id serial primary key,
	model varchar,
	regNumber varchar(255),
	carOwner_id int references carOwner(id)
);