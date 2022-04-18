create table pets(
	id serial primary key,
	name varchar (255),
	age int,
	sex bool, 
	food text
);
insert into pets(name, age, sex, food) values('Гав', 3, true, 'meat and dry dog food');
update pets set age = 5;
delete from pets;
select * from pets;