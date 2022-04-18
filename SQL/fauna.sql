select * from fauna where name LIKE '%fish%';
select * from fauna where avg_age <= 21000 and avg_age >= 10000;
select * from fauna where discovery_date is null;
select * from fauna where discovery_date < '01.01.1950';