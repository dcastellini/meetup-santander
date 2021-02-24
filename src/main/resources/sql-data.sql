--date: "2020-12-31T13:40:00"

 create database meetup
    with 
    owner = postgres
    encoding = 'utf8'
    lc_collate = 'spanish_argentina.1252'
    lc_ctype = 'spanish_argentina.1252'
    tablespace = pg_default
    connection limit = -1; 

-- -----------------------------------------------------
-- table "privilegios"
-- -----------------------------------------------------
create table  privilegios (
  "id_privilegio" serial not null,
  "descripcion" varchar(250) not null,
  primary key ("id_privilegio"));

insert into privilegios values (1, 'admin');
insert into privilegios values (2, 'usuario');

-- -----------------------------------------------------
-- table "asistencia"
-- -----------------------------------------------------
create table  asistencias (
  "id_asistencia" serial not null,
  "descripcion" varchar(250) not null,
  primary key ("id_asistencia"));

insert into asistencias values (0, 'pendiente');
insert into asistencias values (1, 'confirmado');
insert into asistencias values (2, 'asistio');


-- -----------------------------------------------------
-- table "usuario"
-- -----------------------------------------------------
create table  usuarios (
  "id_usuario" serial not null,
  "nombre" varchar(250) not null,
  "apellido" varchar(250) not null,
  "usuario" varchar(250) not null,
  "password" varchar(250) not null,
  "email" varchar(250) not null,
  "privilegio" int not null,
  constraint "fk_privilegios_usuarios"
    foreign key ("privilegio")
    references privilegios ("id_privilegio")
    on delete no action
    on update no action,
  primary key ("id_usuario"));

-- -----------------------------------------------------
-- table "meetups"
-- -----------------------------------------------------
create table  meetups (
  "id_meetup" serial not null,
  "fecha" timestamp not null,
  "latitud" double precision not null,
  "longitud" double precision not null,
  "temperatura" decimal(3,1) not null,
  "cantidad_cerveza" int not null,
  "id_usuario_creador" int not null,
 constraint "fk_meetups_usuarios"
    foreign key ("id_usuario_creador")
    references usuarios ("id_usuario")
    on delete no action
    on update no action,
  primary key ("id_meetup"));


-- -----------------------------------------------------
-- table "meetups_x_usuarios"
-- -----------------------------------------------------

create table  meetups_usuarios (
  "id_meetup" int not null,
  "id_usuario" int not null,
  "id_asistencia" int not null, 
 constraint "fk_meetups_usuarios_usuarios"
    foreign key ("id_usuario")
    references usuarios ("id_usuario")
    on delete no action
    on update no action,
 constraint "fk_meetups_usuarios_meetups"
    foreign key ("id_meetup")
    references meetups ("id_meetup")
    on delete no action
    on update no action,
 constraint "fk_meetups_usuarios_asistencia"
    foreign key ("id_asistencia")
    references asistencias ("id_asistencia")
    on delete no action
    on update no action,
 primary key ("id_meetup","id_usuario"));


-- -----------------------------------------------------
-- table "parametros"
-- -----------------------------------------------------
create table  parametros (
  "id_parametro" serial not null,
  "temperatura_minima" decimal(3,1) not null,
  "temperatura_maxima" decimal (3,1) not null,
  "cantidad_cerveza_persona" decimal(3,2) not null,
  primary key ("id_parametro"));

insert into parametros values (1,20.0,24.0,1.00);
insert into parametros values (2,-99.9,19.9,0.75);
insert into parametros values (3,24.1,99.9,2.00);