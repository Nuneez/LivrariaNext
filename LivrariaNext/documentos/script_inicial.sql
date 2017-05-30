/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  roger
 * Created: 29/04/2017
 */

DROP TABLE ITEM_PEDIDO;
DROP TABLE PEDIDO;
DROP TABLE ESTOQUE_PRODUTO;
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

CREATE UNIQUE INDEX IDX_CLIENTE ON CLIENTE(CPF);

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

CREATE UNIQUE INDEX IDX_PRODUTO ON PRODUTO(EAN);

insert into produto (nomecomum, descricao, custo, precomedio, ean, ativo) values ('IPHONE SE', 'SMARTPHONE 2GB DE MEMÓRIA BLUETOOTH ETC', 2000, 2000, '123456789123', true);
insert into produto (nomecomum, descricao, custo, precomedio, ean, ativo) values ('IPHONE 6', 'SMARTPHONE 2GB DE MEMÓRIA SEM BLUETOOTH ETC', 3000, 3000, '123456789124', true);
insert into produto (nomecomum, descricao, custo, precomedio, ean, ativo) values ('SAMSUNG GALAXY S7', 'SMARTPHONE 4GB DE MEMÓRIA BLUETOOTH ETC', 2000, 2000, '123456789125', true);
insert into produto (nomecomum, descricao, custo, precomedio, ean, ativo) values ('SAMSUNG GALAXY S8', 'SMARTPHONE 8GB DE MEMÓRIA BLUETOOTH ETC', 4000, 4000, '123456789126', true);
insert into produto (nomecomum, descricao, custo, precomedio, ean, ativo) values ('SONY PLAYSTATION 4', 'VIDEOGAME DE ULTIMA GERAÇÃO', 1700, 1700, '123456789127', true);
insert into produto (nomecomum, descricao, custo, precomedio, ean, ativo) values ('MICROSOFT XBOX ONE', 'VIDEOGAME DE ULTIMA GERAÇÃO', 1500, 1500, '123456789128', true);
insert into produto (nomecomum, descricao, custo, precomedio, ean, ativo) values ('NOTEBOOK DELL VOSTRO 3000', 'NOTEBOOK I7', 4000, 4000, '123456789129', true);
insert into produto (nomecomum, descricao, custo, precomedio, ean, ativo) values ('NOTEBOOK MACBOOK PRO', 'NOTEBOOK', 6000, 6000, '123456789130', true);

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
    EMAIL VARCHAR(100) NOT NULL,
    ATIVO BOOLEAN NOT NULL WITH DEFAULT TRUE
);

CREATE UNIQUE INDEX IDX_LOJA ON LOJA(CNPJ);

create table ESTOQUE
(
    ID INTEGER not null primary key GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    ID_LOJA INTEGER NOT NULL references LOJA(ID),
    ATIVO BOOLEAN NOT NULL WITH DEFAULT TRUE
);

create table ESTOQUE_PRODUTO
(
    ID INTEGER not null primary key GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    ID_ESTOQUE INTEGER NOT NULL references ESTOQUE(ID),
    ID_PRODUTO INTEGER NOT NULL references PRODUTO(ID),
    QTD_SALDO DOUBLE NOT NULL,
    ATIVO BOOLEAN NOT NULL WITH DEFAULT TRUE
);

create table PEDIDO
(
    ID INTEGER not null primary key GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    ID_LOJA INTEGER NOT NULL references LOJA(ID),
    ID_VENDEDOR INTEGER NOT NULL references USUARIO(ID),
    ID_CLIENTE INTEGER NOT NULL references CLIENTE(ID),
    DATA_PEDIDO DATE NOT NULL WITH DEFAULT CURRENT_DATE
);

create table ITEM_PEDIDO
(
    ID INTEGER not null primary key GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    ID_PEDIDO INTEGER NOT NULL references PEDIDO(ID),
    ID_PRODUTO INTEGER NOT NULL references PRODUTO(ID),
    QTD_PRODUTO DOUBLE NOT NULL,
    VAL_UNITARIO DOUBLE NOT NULL
);
