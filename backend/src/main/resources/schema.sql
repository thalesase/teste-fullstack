create table plano
(
   id integer not null AUTO_INCREMENT,
   nome varchar(255) not null ,
   valor float not null,
   primary key(id)
);

insert into plano (nome, valor) values ('plano de saude', 100);


create table usuario
(
   login varchar(255) not null ,
   nome varchar(255) not null ,
   senha varchar(255) not null,
   primary key(login)
);

insert into usuario (login, nome, senha) values ('admin', 'admin', 'admin');

create table beneficiario
(
   id integer not null AUTO_INCREMENT,
   nome varchar(255) not null ,
   cpf varchar(255) not null,
   email varchar(255) not null,
   idade integer not null,
   plano integer,
   primary key(id),
   FOREIGN KEY (plano) REFERENCES plano(id)
);

insert into beneficiario (nome, cpf, email, idade, plano) values ('Thales', '00100200304', 'teste@teste.com', 23, 1);
