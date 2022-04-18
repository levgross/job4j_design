create table sportsman(
	id serial primary key,
	name varchar(255)
);

create table sports(
	id serial primary key,
	name varchar(255)
);

create table sportsman_sports(
	id serial primary key,
	sportsman_id int references sportsman(id),
	sports_id int references sports(id)
);