drop database if exists EjercicioProyecto;
create database if not exists EjercicioProyecto;
use EjercicioProyecto;

CREATE TABLE usuarios (
    id_usuario int auto_increment primary key unique,
    nombre varchar(20) not null,
    correo varchar(20) unique not null unique,
    contraseña varchar(20) not null
);

CREATE TABLE organizadores (
    id_organizador int auto_increment primary key unique,
    nombre varchar(20) not null,
    contacto_email varchar(20) not null unique,
    contacto_tlf varchar(20) not null unique
);

CREATE TABLE tipos_eventos (
    id_tipo int auto_increment primary key unique,
    tipo varchar(20) unique not null
);

CREATE TABLE eventos (
    id_evento int auto_increment primary key unique,
    nombre varchar(20) not null,
    fecha date not null,
    duracion int not null,
    ubicacion varchar(20) not null,
    id_tipo int unique,
    id_organizador int unique,
    foreign key (id_tipo) references tipos_eventos(id_tipo) on delete set null,
    foreign key (id_organizador) references organizadores(id_organizador) on delete set null
);

CREATE TABLE categorias (
    id_categoria int auto_increment primary key unique,
    nombre_categoria varchar(20) unique not null
);

CREATE TABLE inscripciones (
    id_inscripcion int auto_increment primary key unique,
    id_usuario int unique,
    id_evento int unique,
    fecha_inscripcion varchar(20),
    foreign key (id_usuario) references usuarios(id_usuario) on delete cascade,
    foreign key (id_evento) references eventos(id_evento) on delete cascade
);