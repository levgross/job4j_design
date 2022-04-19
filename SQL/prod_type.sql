create table type(
	id serial primary key,
	name varchar(255)
);

create table product(
	id serial primary key,
	name varchar(255),
	type_id int references type(id),
	expired_date date,
	price float
);

insert into type(name) values ('СЫР'), ('МОЛОКО'), ('МОРОЖЕНОЕ');
insert into product(name, type_id, expired_date, price) values
('Сыр плавленный', 1, '01.05.2022', 150.4),
('Сыр моцарелла', 1, '01.04.2022', 500.9),
('Молоко бут.', 2, '21.04.2022', 50),
('Молоко пак.', 2, '18.04.2022', 45.55),
('Мороженое эскимо', 3, '01.05.2022', 38),
('Мороженое стак.', 3, '09.10.2022', 48),
('Сыр российский', 1, '01.01.2022', 350.4),
('Сыр Дорблю', 1, '25.09.2022', 1050);

select * from product where type_id = 1;
select * from product where name LIKE '%Мороженое%';
select * from product where expired_date < current_date;
select * from product order by price DESC limit(1);

select t.name, count(p.name) 
from type as t
join product as p
on t.id = p.type_id
group by t.name;

select * from product as p
join type as t
on p.type_id = t.id
where t.name = 'СЫР' or t.name = 'МОЛОКО';

select t.name, count(p.name) 
from type as t
join product as p
on t.id = p.type_id
group by t.name
having count(p.name) < 10;

select p.name, t.name 
from product as p
join type as t
on p.type_id = t.id;

