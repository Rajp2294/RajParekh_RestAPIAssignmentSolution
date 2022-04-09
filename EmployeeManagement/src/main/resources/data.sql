insert into users (user_id, password, username) values (1, '$2a$12$o.zClvW80KHoJjid8Y0hkOth7qxWR43cEJX6f1NezTYdPgaHzqkkK', 'admin');
insert into roles (role_id, name) values (1, 'ADMIN');
insert into users_roles (user_id, role_id) values (1,1);

insert into employees (id, first_name, last_name, email) values ( 1, 'Antony', 'Blinkin', 'antony@foreignsec.us');
insert into employees (id, first_name, last_name, email) values ( 2, 'Liz', 'Truss', 'liz@foreignmin.uk');
insert into employees (id, first_name, last_name, email) values ( 3, 'Hayashi', 'Yoshimasa', 'yoshimasa@foreignsec.com');
insert into employees (id, first_name, last_name, email) values ( 4, 'Ajit', 'Doval', 'ajitdoval@nsa.in');