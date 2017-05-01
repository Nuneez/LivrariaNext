/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  roger
 * Created: 29/04/2017
 */

DROP TABLE ESTOQUE;
DROP TABLE SERVICO;
DROP TABLE PRODUTO;
DROP TABLE LOJA;
DROP TABLE CLIENTE;
DROP TABLE USUARIO;
DROP TABLE PERFIL;

CREATE TABLE PERFIL
(
    ID INTEGER not null primary key GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    NOME VARCHAR(100) not null,
    ATIVO BOOLEAN not null with default TRUE
);

insert into perfil (nome, ativo) values ('ADMINISTRADOR', TRUE);
insert into perfil (nome, ativo) values ('GERENTE', TRUE);
insert into perfil (nome, ativo) values ('BACKOFFICE', TRUE);
insert into perfil (nome, ativo) values ('VENDEDOR', TRUE);
insert into perfil (nome, ativo) values ('TI', TRUE);

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
    PERFIL_ID INTEGER NOT NULL references PERFIL(ID),
    LOJA INTEGER,
    ATIVO BOOLEAN not null with DEFAULT TRUE
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
    /*DTNASC DATE not null,*/
    SEXO VARCHAR (10) not null,
    ENDERECO VARCHAR (150) not null,
    NUMERO VARCHAR (10) not null,
    BAIRRO VARCHAR (50) not null,
    EMAIL VARCHAR(100) not null,
    TELEFONE VARCHAR(14) not null,
    ATIVO BOOLEAN not null with DEFAULT TRUE
);

create table PRODUTO 
(
    ID INTEGER not null primary key
            GENERATED ALWAYS AS IDENTITY
            (START WITH 1, INCREMENT BY 1),
    NOMECOMUM VARCHAR(100) not null,
    DESCRICAO VARCHAR(100) not null,
    CUSTO DOUBLE not null,
    PRECOMEDIO DOUBLE not null,
    EAN VARCHAR(13) not null,
    ATIVO BOOLEAN not null with DEFAULT TRUE
);

create table SERVICO 
(
    ID INTEGER not null primary key
            GENERATED ALWAYS AS IDENTITY
            (START WITH 1, INCREMENT BY 1),
    NOME VARCHAR(100) not null,
    DESCRICAO VARCHAR(100) not null,
    PRECOMEDIO DOUBLE not null,
    MATERIAIS VARCHAR(255) not null,
    ATIVO BOOLEAN not null with DEFAULT TRUE
);

create table LOJA
(
    ID INTEGER not null primary key GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    NOME VARCHAR(100) not null,
    FILIAL BOOLEAN not null,
    CNPJ VARCHAR(15) not null,
    RAZAO_SOCIAL VARCHAR(150) not null,
    INSCRICAO_ESTADUAL VARCHAR(100) not null,
    ENDERECO VARCHAR (150) not null,
    NUMERO VARCHAR (10) not null,
    CIDADE VARCHAR (50) not null,
    ESTADO VARCHAR (50) not null,
    TELEFONE VARCHAR(14) not null,
    EMAIL VARCHAR(20) NOT NULL,
    ATIVO BOOLEAN NOT NULL WITH DEFAULT TRUE
);

create table ESTOQUE
(
    ID INTEGER not null primary key GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    ID_LOJA INTEGER NOT NULL references LOJA(ID),
    ID_PRODUTO INTEGER NOT NULL references PRODUTO(ID),
    QTD_SALDO DOUBLE NOT NULL
);