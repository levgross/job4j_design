create table teens(
	name varchar(255),
	gender bool
);

insert into teens(name, gender) values
('Bob', true),
('Sam', true),
('John', true),
('Ann', false),
('Mary', false),
('Sarah', false);

select n1.name, n2.name 
from teens n1 cross join teens n2
where n1.gender != n2.gender;