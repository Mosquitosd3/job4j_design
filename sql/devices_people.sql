create table devices(
    id serial primary key,
    name varchar(255),
    price float
);

create table people(
    id serial primary key,
    name varchar(255)
);

create table devices_people(
    id serial primary key,
    device_id int references devices(id),
    people_id int references people(id)
);

insert into devices(name, price) values ('Наушники', 1500.00);
insert into devices(name, price) values ('Монитор', 10000.00);
insert into devices(name, price) values ('Клавиатура', 1000.00);

insert into people(name) values ('Аня');
insert into people(name) values ('Ваня');
insert into people(name) values ('Боря');

insert into devices_people(device_id, people_id) values (1,1);
insert into devices_people(device_id, people_id) values (1,2);
insert into devices_people(device_id, people_id) values (2,2);
insert into devices_people(device_id, people_id) values (2,3);
insert into devices_people(device_id, people_id) values (3,1);
insert into devices_people(device_id, people_id) values (3,2);
insert into devices_people(device_id, people_id) values (3,3);

select avg(price) from devices;

select p.name as "Покупатель", avg(d.price) as "Средняя цена устройств"
from people as p
join devices_people as dp on dp.people_id  = p.id
join devices as d on dp.device_id = d.id 
group by p.name;

select p.name as "Покупатель", avg(d.price) as "Средняя цена устройств"
from people as p
join devices_people as dp on dp.people_id  = p.id
join devices as d on dp.device_id = d.id 
group by p.name
having avg(d.price) > 5000;
