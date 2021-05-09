create table type(
	id serial primary key,
	name varchar(50)
);

create table product(
	id serial primary key,
	name varchar(100),
	expired_date date,
	price float,
	type_id int references type(id)
);

insert into type(name) values ('Сыр');
insert into type(name) values ('Молоко');
insert into type(name) values ('Мороженое');

insert into product(name, expired_date, price, type_id)
values ('Alti', '10.06.2021', 450.00, 1);
insert into product(name, expired_date, price, type_id)
values ('Bonfesto', '08.06.2021', 370.00, 1);
insert into product(name, expired_date, price, type_id)
values ('Amyga', '01.07.2021', 499.00, 1);
insert into product(name, expired_date, price, type_id)
values ('Comella', '22.07.2021', 475.00, 1);
insert into product(name, expired_date, price, type_id)
values ('DORBLU', '13.07.2021', 510.00, 1);
insert into product(name, expired_date, price, type_id)
values ('Emmi', '05.07.2021', 745.00, 1);
insert into product(name, expired_date, price, type_id)
values ('Granarolo', '02.06.2021', 390.00, 1);
insert into product(name, expired_date, price, type_id)
values ('Granarolo', '10.07.2021', 390.00, 1);

insert into product(name, expired_date, price, type_id)
values ('Асеньевская ферма', '10.06.2021', 37.00, 2);
insert into product(name, expired_date, price, type_id)
values ('ВкусВилл', '10.07.2021', 40.00, 2);
insert into product(name, expired_date, price, type_id)
values ('Домик в деревне', '09.06.2021', 43.00, 2);
insert into product(name, expired_date, price, type_id)
values ('Искренне Ваш', '08.06.2021', 45.00, 2);
insert into product(name, expired_date, price, type_id)
values ('Лакт', '10.08.2021', 47.00, 2);
insert into product(name, expired_date, price, type_id)
values ('Мещёрские росы', '10.06.2021', 43.00, 2);
insert into product(name, expired_date, price, type_id)
values ('Простоквашино', '12.07.2021', 50.00, 2);

insert into product(name, expired_date, price, type_id)
values ('Nestle', '25.11.2022', 40.00, 3);
insert into product(name, expired_date, price, type_id)
values ('AMORE', '17.09.2022', 35.00, 3);
insert into product(name, expired_date, price, type_id)
values ('Baskin Robbins', '29.12.2021', 45.00, 3);
insert into product(name, expired_date, price, type_id)
values ('Haagen Dazs', '01.02.2022', 37.00, 3);
insert into product(name, expired_date, price, type_id)
values ('KitKat', '09.06.2021', 110.00, 3);
insert into product(name, expired_date, price, type_id)
values ('M&Ms', '09.11.2021', 110.00, 3);
insert into product(name, expired_date, price, type_id)
values ('Артек', '19.06.2022', 32.00, 3);
insert into product(name, expired_date, price, type_id)
values ('Дом Вкусов', '18.03.2022', 27.00, 3);
insert into product(name, expired_date, price, type_id)
values ('Живое мороженое', '20.03.2022', 55.00, 3);
insert into product(name, expired_date, price, type_id)
values ('Жемчужина России', '10.04.2022', 48.00, 3);

--Запрос получение всех продуктов с типом "СЫР"
select t.name as "Тип продукта", p.name as "Фирма", p.expired_date as "Годен до", p.price as "Цена"
from type as t join product as p on t.id = p.type_id
where t.name = 'Сыр';

--Запрос получения всех продуктов, у кого в имени есть слово "мороженное"
select t.name as "Тип продукта", p.name as "Фирма", p.expired_date as "Годен до", p.price as "Цена"
from type as t join product as p on t.id = p.type_id
where p.name like'%Мороженно%';

--апрос, который выводит все продукты, срок годности которых заканчивается в следующем месяце.
select t.name as "Тип продукта", p.name as "Фирма", p.expired_date as "Годен до", p.price as "Цена"
from type as t join product as p on t.id = p.type_id
where extract(month from p.expired_date) = extract (month from current_date + interval '1 month');

--Запрос, который выводит самый дорогой продукт.
select t.name as "Тип продукта", p.name as "Фирма", p.expired_date as "Годен до", p.price as "Цена"
from type as t join product as p on t.id = p.type_id
order by p.price desc limit 1;

--Запрос, который выводит для каждого типа количество продуктов к нему принадлежащих. В виде имя_типа, количество
select t.name as "Тип продукта", count(p.name) as "Количество"
from type as t join product p on t.id = p.type_id
group by t.name;

--Запрос получение всех продуктов с типом "СЫР" и "МОЛОКО"
select t.name as "Тип продукта", p.name as "Фирма", p.expired_date as "Годен до", p.price as "Цена"
from type as t join product as p on t.id = p.type_id
where t.name in ('Сыр','Молоко');

--Запрос, который выводит тип продуктов, которых осталось меньше 10 штук.  
select t.name as "Тип продукта", count(p.name) as "Количество"
from type as t join product p on t.id = p.type_id
group by t.name
having count(p.name) < 10;

--Запрос: все продукты и их тип.
select t.name as "Тип продукта", p.name as "Фирма", p.expired_date as "Годен до", p.price as "Цена"
from type as t join product as p on t.id = p.type_id;