create table carro(
    id bigint generated by default as identity,
    descricao varchar(100),
    latitude varchar(100),
    longitude varchar(100),
    nome varchar(100),
    tipo varchar(100),
    url_foto varchar(100),
    url_video varchar(100),
    primary key (id)
);