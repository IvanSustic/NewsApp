
insert into USERS(username, password,first_name,last_name) values('user', '$2a$10$GRLdNijSQMUvl/au9ofL.eDwmoohzzS7.rmNSJZ.0FxO/BTk76klW','Ivo','Ivić'); --password
insert into USERS(username, password,first_name,last_name) values('admin', '$2a$10$GRLdNijSQMUvl/au9ofL.eDwmoohzzS7.rmNSJZ.0FxO/BTk76klW','Pero','Perić'); --password

insert into roles(name) values('USER');
insert into roles(name) values('ADMIN');



insert into users_roles(users_id, roles_id) values(1, 1);
insert into users_roles(users_id, roles_id) values(2, 2);

