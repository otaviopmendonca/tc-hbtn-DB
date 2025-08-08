-- TABLE
CREATE TABLE 'database_admin' ('c1' TEXT,'c2' TEXT);
CREATE TABLE Pessoa (
       id  integer,
        cpf varchar(255),
        data_nascimento date,
        email varchar(255),
        idade integer not null,
        nome varchar(255),
        primary key (id)
    );
CREATE TABLE Produto (
       id  integer,
        nome varchar(255),
        preco double precision not null,
        quantidade integer not null,
        status boolean not null,
        primary key (id)
    );
 
-- INDEX
 
-- TRIGGER
 
-- VIEW
 
-- LINK
 
