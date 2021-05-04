create table owner_car(
	id serial primary key,
	owner_name varchar(255),
	model varchar(255)
);

create table garage(
	id serial primary key,
	master varchar(255),
	owner_car_id int references owner_car(id)
);

insert into owner_car(owner_name, model) values('Nikolay', 'Opel');
insert into owner_car(owner_name, model) values('Diana', 'BMW');
insert into owner_car(owner_name, model) values('Misha', 'Lada');

insert into garage(master, owner_car_id) values('Stepan', 1);
insert into garage(master, owner_car_id) values('Vlad', 2);
insert into garage(master, owner_car_id) values('Petrovich', 3);

select * from garage join owner_car o on garage.owner_car_id = o.id;
select gar.master, o.model, o.owner_name from garage as gar join owner_car as o on gar.owner_car_id = o.id;
select gar.master as Мастер, o.model as Авто, o.owner_name as Владелец from garage as gar join owner_car as o on gar.owner_car_id = o.id;