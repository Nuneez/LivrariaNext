/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  roger
 * Created: 29/04/2017
 */

CREATE TABLE PERFIL
(
    ID INTEGER not null primary key GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    NOME VARCHAR(100) not null,
    ATIVO INTEGER not null with default 1
);

insert into perfil (nome, ativo) values ('ADMINISTRADOR', 1);
insert into perfil (nome, ativo) values ('GERENTE', 1);
insert into perfil (nome, ativo) values ('BACKOFFICE', 1);
insert into perfil (nome, ativo) values ('VENDEDOR', 1);
insert into perfil (nome, ativo) values ('TI', 1);

create table USUARIO 
(
    ID INTEGER not null primary key
            GENERATED ALWAYS AS IDENTITY
            (START WITH 1, INCREMENT BY 1),
    NOME VARCHAR(100) not null,
    SOBRENOME VARCHAR(100) not null,
    USERNAME VARCHAR(10) not null,
    PASSWORD VARCHAR(10) not null,
    EMAIL VARCHAR(50) not null,
    PERFIL INTEGER,
    LOJA INTEGER,
    ATIVO BOOLEAN not null
);

create table CLIENTE 
(
    ID INTEGER not null primary key
            GENERATED ALWAYS AS IDENTITY
            (START WITH 1, INCREMENT BY 1),
    NOME VARCHAR(100) not null,
    SOBRENOME VARCHAR(100) not null,
    CPF VARCHAR (14) not null,
    RG VARCHAR (13) not null,
    DTNASC DATE not null,
    SEXO VARCHAR (10) not null,
    ENDERECO VARCHAR (150) not null,
    NUMERO VARCHAR (10) not null,
    BAIRRO VARCHAR (50) not null,
    EMAIL VARCHAR(100) not null,
    TELEFONE VARCHAR(14) not null
);

create table PRODUTO 
(
    ID INTEGER not null primary key
            GENERATED ALWAYS AS IDENTITY
            (START WITH 1, INCREMENT BY 1),
    NOMECOMUM VARCHAR(100) not null,
    DESCRICAO VARCHAR(100) null,
    CUSTO DOUBLE not null,
    PRECOMEDIO DOUBLE not null,
    EAN VARCHAR(13) not null,
    ATIVO BOOLEAN not null
);

create table SERVICO 
(
    ID INTEGER not null primary key
            GENERATED ALWAYS AS IDENTITY
            (START WITH 1, INCREMENT BY 1),
    NOME VARCHAR(100) not null,
    DESCRICAO VARCHAR(100) null,
    PRECOMEDIO DOUBLE not null,
    MATERIAIS VARCHAR(255) null
);

create table LOJA
(
    ID INTEGER not null primary key
            GENERATED ALWAYS AS IDENTITY
            (START WITH 1, INCREMENT BY 1),
    NOME VARCHAR(100) not null,
    EHFILIAL BOOLEAN not null,
    CNPJ VARCHAR(15) not null,
    RAZAOSOCIAL VARCHAR(150) not null,
    IE VARCHAR(100) not null,
    ENDERECO VARCHAR (150) not null,
    NUMERO VARCHAR (10) not null,
    CIDADE VARCHAR (50) not null,
    ESTADO VARCHAR (50) not null,
    TELEFONE VARCHAR(14) not null
);
