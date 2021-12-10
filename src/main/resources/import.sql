INSERT INTO db_springboot_backend.users(username, password, enabled, name, lastname, email) VALUES ('sa','$2a$10$7P4henxiT85TUReiBqnUTukeXWTvYTCGJj8l9cK3VcOAlnmBB9Bg.',1, 'Jose','Prieto','twlster.mk@gmail.com');
INSERT INTO db_springboot_backend.users(username, password, enabled, name, lastname, email) VALUES ('admin','$2a$10$4hAq246kr9fAulSc/vQdGuDFVsfsXN14T6ynGRbANj6cgOsJnAZ5C',1, 'Pepe','Jimenez','p.jimenez@gmail.com');

INSERT INTO db_springboot_backend.roles(name) VALUES ('ROLE_USER');
INSERT INTO db_springboot_backend.roles(name) VALUES ('ROLE_ADMIN');

INSERT INTO db_springboot_backend.users_roles(user_id, roles_id) VALUES (1,1);
INSERT INTO db_springboot_backend.users_roles(user_id, roles_id) VALUES (2,1);
INSERT INTO db_springboot_backend.users_roles(user_id, roles_id) VALUES (2,2);

INSERT INTO db_springboot_backend.regions(id,name) VALUES (1,'North America');
INSERT INTO db_springboot_backend.regions(id,name) VALUES (2,'Central America');
INSERT INTO db_springboot_backend.regions(id,name) VALUES (3,'South America');
INSERT INTO db_springboot_backend.regions(id,name) VALUES (4,'Eastern Europe');
INSERT INTO db_springboot_backend.regions(id,name) VALUES (5,'Central Europe');
INSERT INTO db_springboot_backend.regions(id,name) VALUES (6,'Western Europe');
INSERT INTO db_springboot_backend.regions(id,name) VALUES (7,'Asia');
INSERT INTO db_springboot_backend.regions(id,name) VALUES (8,'Africa');
INSERT INTO db_springboot_backend.regions(id,name) VALUES (9,'Oceany');


INSERT INTO db_springboot_backend.clients(name,last_name,email,birth_date, region_id) VALUES ('Jose','Prieto','twlster.mk@gmail.com','1989-05-19', 1);
INSERT INTO db_springboot_backend.clients(name,last_name,email,birth_date, region_id) VALUES ('Pepe','Jimenez','p.jimenez@gmail.com','1986-08-26', 2);
INSERT INTO db_springboot_backend.clients(name,last_name,email,birth_date, region_id) VALUES ('Maria','Constanza','maria.const89@gmail.com','1995-07-12', 3);
INSERT INTO db_springboot_backend.clients(name,last_name,email,birth_date, region_id) VALUES ('Manuel','Perez','mperez@gmail.com','1991-11-13', 4);
INSERT INTO db_springboot_backend.clients(name,last_name,email,birth_date, region_id) VALUES ('Jose2','Prieto','twlster.mk2@gmail.com','1989-05-19', 5);
INSERT INTO db_springboot_backend.clients(name,last_name,email,birth_date, region_id) VALUES ('Pepe2','Jimenez','p.jimenez2@gmail.com','1986-08-26', 6);
INSERT INTO db_springboot_backend.clients(name,last_name,email,birth_date, region_id) VALUES ('Maria2','Constanza','maria.const892@gmail.com','1995-07-12', 7);
INSERT INTO db_springboot_backend.clients(name,last_name,email,birth_date, region_id) VALUES ('Manuel2','Perez','mperez2@gmail.com','1991-11-13', 8);
INSERT INTO db_springboot_backend.clients(name,last_name,email,birth_date, region_id) VALUES ('Jose3','Prieto','twlster.mk3@gmail.com','1989-05-19', 9);
INSERT INTO db_springboot_backend.clients(name,last_name,email,birth_date, region_id) VALUES ('Pepe3','Jimenez','p.jimenez3@gmail.com','1986-08-26', 1);
INSERT INTO db_springboot_backend.clients(name,last_name,email,birth_date, region_id) VALUES ('Maria3','Constanza','maria.const893@gmail.com','1995-07-12', 2);
INSERT INTO db_springboot_backend.clients(name,last_name,email,birth_date, region_id) VALUES ('Manuel3','Perez','mperez3@gmail.com','1991-11-13', 3);
INSERT INTO db_springboot_backend.clients(name,last_name,email,birth_date, region_id) VALUES ('Jose23','Prieto','twlster.mk23@gmail.com','1989-05-19', 4);
INSERT INTO db_springboot_backend.clients(name,last_name,email,birth_date, region_id) VALUES ('Pepe23','Jimenez','p.jimenez23@gmail.com','1986-08-26', 5);
INSERT INTO db_springboot_backend.clients(name,last_name,email,birth_date, region_id) VALUES ('Maria23','Constanza','maria.const8923@gmail.com','1995-07-12', 6);
INSERT INTO db_springboot_backend.clients(name,last_name,email,birth_date, region_id) VALUES ('Manuel23','Perez','mperez23@gmail.com','1991-11-13', 7);
INSERT INTO db_springboot_backend.clients(name,last_name,email,birth_date, region_id) VALUES ('Jose','Prieto','twlster.mk4@gmail.com','1989-05-19', 8);
INSERT INTO db_springboot_backend.clients(name,last_name,email,birth_date, region_id) VALUES ('Pepe','Jimenez','p.jimenez4@gmail.com','1986-08-26', 9);
INSERT INTO db_springboot_backend.clients(name,last_name,email,birth_date, region_id) VALUES ('Maria','Constanza','maria.const894@gmail.com','1995-07-12', 1);
INSERT INTO db_springboot_backend.clients(name,last_name,email,birth_date, region_id) VALUES ('Manuel','Perez','mperez4@gmail.com','1991-11-13', 2);
INSERT INTO db_springboot_backend.clients(name,last_name,email,birth_date, region_id) VALUES ('Jose2','Prieto','twlster.mk25@gmail.com','1989-05-19', 3);
INSERT INTO db_springboot_backend.clients(name,last_name,email,birth_date, region_id) VALUES ('Pepe2','Jimenez','p.jimenez25@gmail.com','1986-08-26', 4);
INSERT INTO db_springboot_backend.clients(name,last_name,email,birth_date, region_id) VALUES ('Maria2','Constanza','maria.const8925@gmail.com','1995-07-12', 5);
INSERT INTO db_springboot_backend.clients(name,last_name,email,birth_date, region_id) VALUES ('Manuel2','Perez','mperez25@gmail.com','1991-11-13', 6);
INSERT INTO db_springboot_backend.clients(name,last_name,email,birth_date, region_id) VALUES ('Jose3','Prieto','twlster.mk36@gmail.com','1989-05-19', 7);
INSERT INTO db_springboot_backend.clients(name,last_name,email,birth_date, region_id) VALUES ('Pepe3','Jimenez','p.jimenez36@gmail.com','1986-08-26', 8);
INSERT INTO db_springboot_backend.clients(name,last_name,email,birth_date, region_id) VALUES ('Maria3','Constanza','maria.const8936@gmail.com','1995-07-12', 9);
INSERT INTO db_springboot_backend.clients(name,last_name,email,birth_date, region_id) VALUES ('Manuel3','Perez','mperez36@gmail.com','1991-11-13', 1);
INSERT INTO db_springboot_backend.clients(name,last_name,email,birth_date, region_id) VALUES ('Jose23','Prieto','twlster.mk236@gmail.com','1989-05-19', 2);
INSERT INTO db_springboot_backend.clients(name,last_name,email,birth_date, region_id) VALUES ('Pepe23','Jimenez','p.jimenez236@gmail.com','1986-08-26', 3);
INSERT INTO db_springboot_backend.clients(name,last_name,email,birth_date, region_id) VALUES ('Maria23','Constanza','maria.const89236@gmail.com','1995-07-12', 4);
INSERT INTO db_springboot_backend.clients(name,last_name,email,birth_date, region_id) VALUES ('Manuel23','Perez','mperez236@gmail.com','1991-11-13', 5);