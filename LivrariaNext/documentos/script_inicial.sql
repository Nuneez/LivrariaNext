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
<!--
insert into perfil (nome, ativo) values ('ADMINISTRADOR', TRUE);
insert into perfil (nome, ativo) values ('GERENTE', TRUE);
insert into perfil (nome, ativo) values ('BACKOFFICE', TRUE);
insert into perfil (nome, ativo) values ('VENDEDOR', TRUE);
insert into perfil (nome, ativo) values ('TI', TRUE);
-->
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

INSERT INTO USUARIO (NOME,SOBRENOME,USERNAME,PASSWORD,EMAIL,PERFIL_ID,LOJA,ATIVO) VALUES ('ERICK','MARQUES','MARQUES','123','ERICK@ASTEC.COM',4,1,TRUE);
INSERT INTO USUARIO (NOME,SOBRENOME,USERNAME,PASSWORD,EMAIL,PERFIL_ID,LOJA,ATIVO) VALUES ('PAULO','NUNES','NUNEEZ','123','NUNEEZ@ASTEC.COM',1,1,TRUE);
INSERT INTO USUARIO (NOME,SOBRENOME,USERNAME,PASSWORD,EMAIL,PERFIL_ID,LOJA,ATIVO) VALUES ('ROGER','OLIVEIRA','IKARI','123','OLIVEIRA@ASTEC.COM',2,1,TRUE);
INSERT INTO USUARIO (NOME,SOBRENOME,USERNAME,PASSWORD,EMAIL,PERFIL_ID,LOJA,ATIVO) VALUES ('ELISON','SOUZA','ELISON','123','ELSOUZA@ASTEC.COM',3,1,TRUE);
INSERT INTO USUARIO (NOME,SOBRENOME,USERNAME,PASSWORD,EMAIL,PERFIL_ID,LOJA,ATIVO) VALUES ('THIAGO','MESSIAS','MESSIAS','123','MESSIAS@ASTEC.COM',5,1,TRUE);
INSERT INTO USUARIO (NOME,SOBRENOME,USERNAME,PASSWORD,EMAIL,PERFIL_ID,LOJA,ATIVO) VALUES ('ALINE','MORATO','ALINE','123','ALINE@ASTEC.COM',5,1,TRUE);

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

insert into cliente (nome,sobrenome,cpf,rg,sexo,endereco,numero,bairro,email,telefone,ativo)values('JOAO','SOUZA','73553368861','5896325741','M','RUA AZEVEDO MACEDO','20','VILA MARIANA','JOAO@GMAIL.COM','11931475866',TRUE);
insert into cliente (nome,sobrenome,cpf,rg,sexo,endereco,numero,bairro,email,telefone,ativo)values('MARIA','PINHEIRO','81847884105','429434121','F','RUA JUCELINO','199','JD SAO BERNADO','MARIA@GMAIL.COM','1158772503',TRUE);
insert into cliente (nome,sobrenome,cpf,rg,sexo,endereco,numero,bairro,email,telefone,ativo)values('HENRIQUE','MARTINS','83113475732','418757896','M','RUA DA PAZ','80','JD JACIRA','HENRIQUE@GMAIL.COM','11976515793',TRUE);
insert into cliente (nome,sobrenome,cpf,rg,sexo,endereco,numero,bairro,email,telefone,ativo)values('JULIO','BEIJAMIN','94231211442','2977269','M','RUA ESMERALDA','54','JD COCAIA','JULIO@GMAIL.COM','1158623324',TRUE);
insert into cliente (nome,sobrenome,cpf,rg,sexo,endereco,numero,bairro,email,telefone,ativo)values('MARCOS','TSUDA','24745139497','403289440','M','RUA MIRAFLORES','157','VILA PRUDENTE','MARCOS@GMAIL.COM','11924066674',TRUE);


create table PRODUTO
(
    ID INTEGER not null primary key
            GENERATED ALWAYS AS IDENTITY
            (START WITH 1, INCREMENT BY 1),
    NOMECOMUM VARCHAR(100) not null,
    DESCRICAO VARCHAR(100) not null,
    CUSTO DOUBLE not null,
    PRECOMEDIO DOUBLE not null,
    EAN VARCHAR(10) not null,
    ATIVO BOOLEAN not null with DEFAULT TRUE
);

CREATE UNIQUE INDEX IDX_PRODUTO ON PRODUTO(EAN);

insert into produto (nomecomum, descricao, custo, precomedio, ean, ativo) values ('IPHONE 7', 'SMARTPHONE 64GB DE MEMÓRIA', 4000, 4000, '00001', true);
insert into produto (nomecomum, descricao, custo, precomedio, ean, ativo) values ('SAMSUNG GALAXY S8', 'SMARTPHONE 32GB', 4000, 4000, '00002', true);
insert into produto (nomecomum, descricao, custo, precomedio, ean, ativo) values ('PLAYSTATION 4', 'VIDEOGAME SONY', 1700, 1700, '00003', true);
insert into produto (nomecomum, descricao, custo, precomedio, ean, ativo) values ('XBOX ONE', 'VIDEOGAME MICROSOFT', 1500, 1500, '00004', true);
insert into produto (nomecomum, descricao, custo, precomedio, ean, ativo) values ('MACBOOK PRO', 'NOTEBOOK', 6000, 6000, '00005', true);

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
insert into loja(nome,filial,cnpj,razao_social,inscricao_estadual,endereco,numero,cidade,estado,telefone,email,ativo) values ('LIVRARIA BENQ',TRUE,'92152264000189','ASTEC','855698595736','AVENIDA ATLANTICA','666','SAO PAULO','SP','1158935000','BENQ@ASTEC.COM',TRUE);
insert into loja(nome,filial,cnpj,razao_social,inscricao_estadual,endereco,numero,cidade,estado,telefone,email,ativo) values ('LIVRARIA ROMAR',TRUE,'45665318000120','ASTEC','968498595736','AVENIDA OTAVIO FARIAS','7589','PORTO ALEGRE','RS','5458617721','ECO@ASTEC.COM',TRUE);
insert into loja(nome,filial,cnpj,razao_social,inscricao_estadual,endereco,numero,cidade,estado,telefone,email,ativo) values ('LIVRARIA MASSONI',TRUE,'70853231000123','ASTEC','785677635736','AVENIDA SOCRATES','5569','RECIFE','PE','8154937721','CONEXAO@ASTEC.COM',TRUE);
insert into loja(nome,filial,cnpj,razao_social,inscricao_estadual,endereco,numero,cidade,estado,telefone,email,ativo) values ('LIVRARIA IMAGINARIUM',TRUE,'82055417000116','ASTEC','685698598895','RUA DAMASCO','28','SAO PAULO','SP','1153725488','IMAGINARIUM@ASTEC.COM',TRUE);
insert into loja(nome,filial,cnpj,razao_social,inscricao_estadual,endereco,numero,cidade,estado,telefone,email,ativo) values ('LIVRARIA NEW ERA',TRUE,'25891069000183','ASTEC','6193595736','AVENIDA DOS PARAIBA','111','RECIFE','PE','8154337210','NWE@ASTEC.COM',TRUE);

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
