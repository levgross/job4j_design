create table body(
	id serial primary key,
	name varchar(255)
);

create table engine(
	id serial primary key,
	name varchar(255)
);

create table transmission(
	id serial primary key,
	name varchar(255)
);

create table car(
	id serial primary key,
	name varchar(255),
	body_id int references body(id),
	engine_id int references engine(id),
	transmission_id int references transmission(id)
);

insert into body(name) values ('sedan'), ('universal'), ('pick-up'), ('cross-country');
insert into engine(name) values ('1.8_gasoline'), ('2.0_diesel'), ('1.5_propan');
insert into transmission(name) values ('2WD'), ('AWD');
insert into car(name, body_id, engine_id, transmission_id) values
('L200', 3, 2, 2),
('Ceed', 1, 1, 1),
('Lada', 2, 1, 1);
insert into car(name, body_id, engine_id) values
('Spark', 2, 1);

select car.name, b.name, e.name, t.name from car
left join body as b on car.body_id = b.id
left join engine as e on car.engine_id = e.id
left join transmission t on car.transmission_id = t.id;

select b.name from body as b
left join car as c
on b.id = c.body_id
where c.id is null;

select e.name from engine as e
left join car as c
on e.id = c.engine_id
where c.id is null;

select t.name from transmission as t
left join car as c
on t.id = c.transmission_id
where c.id is null;