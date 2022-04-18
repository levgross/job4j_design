select * from pets;
delete from pets;
select * from pets;
update pets set age = 5;
select * from pets;
insert into pets(name, age, sex, food) values('Гав', 3, true, 'meat and dry dog food');
create table pets(
	id serial primary key,
	name varchar (255),
	age int,
	sex bool, 
	food text
);