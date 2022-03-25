create table usuario(
  id int primary key auto_increment,
  nome varchar(100) not null,
  email varvhar (100) not null,
  senha varchar(110)not null
);

insert into usuario(nome,email,senha)values('admin','admin@adin.com','123321');