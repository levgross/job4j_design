insert into devices (name, price) values ('fork', 100.5), ('spoon', 150), ('knife', 1000);
insert into people (name) values ('John'), ('Bob'), ('Sam');
insert into devices_people (device_id, people_id) values (1, 1), (3, 1), (2, 2), (1, 3), (2, 3), (3, 3);

select avg(price) from devices;

select p.name, avg(d.price)
from devices_people as dp 
join people as p 
on dp.people_id = p.id
join devices as d
on dp.device_id = d.id
group by p.name;

select p.name, avg(d.price)
from devices_people as dp 
join people as p 
on dp.people_id = p.id
join devices as d
on dp.device_id = d.id
group by p.name
having avg(d.price) > 5000;