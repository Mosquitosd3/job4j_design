create table roles (
	id serial primary key,
	title varchar(255)
);

create table rules (
	id serial primary key,
	description text
);

create table users (
	id serial primary key,
	name varchar(255),
	roles_id int references roles(id)
);

create table role_and_rules (
	id serial primary key,
	roles_id int references roles(id),
	rules_id int references rules(id)
);

create table category (
	id serial primary key,
	application_categories text
);

create table states (
	id serial primary key,
	definition varchar(255)
);

create table item (
	id serial primary key,
	article text,
	users_id int references users(id),
	category_id int references category(id),
	states_id int references states(id)
);

create table comments (
	id serial primary key,
	comment text,
	items_id int references item(id)
); 

create table attachs (
	id serial primary key,
	Attached varchar(255),
	items_id int references item(id)
);


