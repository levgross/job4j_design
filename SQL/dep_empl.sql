create table departments(
	id serial primary key,
	name varchar(255)
);

create table employees(
	id serial primary key,
	name varchar(255),
	department_id int references departments(id)
);

insert into departments(name) 
values ('Sales'), ('IT'), ('Finance');
insert into employees(name, department_id)
values ('Bob', 1), ('John', 2), ('Sarah', 2), ('Ann', 1), ('Sam', 2);
insert into employees(name) values ('Michel');

select * from employees as e
left join departments as d
on e.department_id = d.id;

select * from employees as e
right join departments as d
on e.department_id = d.id;

select * from employees as e
full join departments as d
on e.department_id = d.id;

select * from employees
cross join departments;

select d.name from departments as d
left join employees as e
on e.department_id = d.id
where e.id is null;

select e.name, d.name from employees as e
left join departments as d
on e.department_id = d.id
where d.name is not null;

select e.name, d.name from departments as d
right join employees as e
on e.department_id = d.id
where d.name is not null;