create table speciality(
	id serial primary key,
	name varchar(255),
	professor varchar(255),
	active bool
);

create table student(
	id serial primary key,
	name varchar(255),
	course int,
	speciality_id int references speciality(id)
);

insert into speciality (name, professor, active) values ('E2', 'Smith', true);
insert into speciality (name, professor, active) values ('M1', 'Johnson', true);
insert into speciality (name, professor, active) values ('M2', 'Morgan', false);
insert into student (name, course, speciality_id) values ('Bob', 1, 2);
insert into student (name, course, speciality_id) values ('Andrew', 2, 1);
insert into student (name, course, speciality_id) values ('Jim', 2, 3);

select * from student as st join speciality as sp on st.speciality_id = sp.id;
select st.name as student, sp.name as spec, sp.professor from student as st join speciality as sp on st.speciality_id = sp.id;
select sp.professor, sp.name as spec, sp.active, st.course from student as st join speciality as sp on st.speciality_id = sp.id;