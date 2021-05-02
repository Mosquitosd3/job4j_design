insert into roles(title) values ('Senior');
insert into roles(title) values ('Middle');
insert into roles(title) values ('Junior');

insert into rules(description) values ('create architecture');
insert into rules(description) values ('create programs');
insert into rules(description) values ('performs easy tasks');

insert into users(name, roles_id) values ('Nikolay', 1);
insert into users(name, roles_id) values ('misha', 2);
insert into users(name, roles_id) values ('Nikolay', 3);

insert into role_and_rules(roles_id, rules_id) values (1,1);
insert into role_and_rules(roles_id, rules_id) values (2,2);
insert into role_and_rules(roles_id, rules_id) values (3,3);

insert into category(application_categories) values('high');
insert into category(application_categories) values('medium');
insert into category(application_categories) values('low');

insert into states(definition) values('completed');
insert into states(definition) values('in progress');
insert into states(definition) values('failed');

insert into item(article, users_id, category_id, states_id) values ('create', 1, 1, 1);
insert into item(article, users_id, category_id, states_id) values ('init', 3, 2, 2);
insert into item(article, users_id, category_id, states_id) values ('insert', 2, 3, 3);

insert into comments(comment, items_id) values ('add new architecture', 1);
insert into comments(comment, items_id) values ('add regular expressions in method', 2);
insert into comments(comment, items_id) values ('create new method', 3);

insert into attachs(Attached, items_id) values ('Attached1', 1);
insert into attachs(Attached, items_id) values ('Attached1', 2);
insert into attachs(Attached, items_id) values ('Attached1', 3);

