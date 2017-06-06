<%--
    Document   : menu
    Created on : 15/04/2017, 20:20:17
    Author     : Thiago
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript" lang="javascript" src="/LivrariaNext/scripts/ajaxHelper.js"></script>
<script type="text/javascript" lang="javascript" src="/LivrariaNext/scripts/liveSearch.js"></script>
<link href="https://fonts.googleapis.com/css?family=PT+Sans" rel="stylesheet">

<div id="header-space">
    <div id="logo-space">
        <a href="/LivrariaNext/Index"><img src="/LivrariaNext/imgs/logo.png"/></a>
    </div>
    <div class="exit">
        <a href="/LivrariaNext/Sessao?logout=true">Sair</a>
    </div>
    <nav>

        <!--
           1 insert into perfil (nome, ativo) values ('ADMINISTRADOR', TRUE);
           2 insert into perfil (nome, ativo) values ('GERENTE', TRUE);
           3 insert into perfil (nome, ativo) values ('BACKOFFICE', TRUE);
           4 insert into perfil (nome, ativo) values ('VENDEDOR', TRUE);
           5 insert into perfil (nome, ativo) values ('TI', TRUE);
        -->

        <c:if test="${sessionUser.perfil.id == 1}">
            <a href="/LivrariaNext/ListarClientes">Clientes</a>
            <a href="/LivrariaNext/ListarProdutos">Produtos</a>
            <a href="/LivrariaNext/Pedido">Vendas</a>
            <a href="/LivrariaNext/ListarUsuarios">Usuários</a>
            <a href="/LivrariaNext/ListarLojas">Lojas</a>
            <a href="/LivrariaNext/Relatorio">Relatório</a>
        </c:if>
        <c:if test="${sessionUser.perfil.id == 2}">
            <a href="/LivrariaNext/ListarClientes">Clientes</a>
            <a href="/LivrariaNext/ListarProdutos">Produtos</a>
            <a href="/LivrariaNext/Relatorio">Relatório</a>
        </c:if>
        <c:if test="${sessionUser.perfil.id == 3}">
            <a href="/LivrariaNext/ListarProdutos">Produtos</a>
            <a href="/LivrariaNext/Relatorio">Relatório</a>
        </c:if>
        <c:if test="${sessionUser.perfil.id == 4}">
            <a href="/LivrariaNext/ListarClientes">Clientes</a>
            <a href="/LivrariaNext/Pedido">Vendas</a>
        </c:if>
        <c:if test="${sessionUser.perfil.id == 5}">
            <a href="/LivrariaNext/ListarClientes">Clientes</a>
            <a href="/LivrariaNext/ListarProdutos">Produtos</a>
            <a href="/LivrariaNext/Pedido">Vendas</a>
            <a href="/LivrariaNext/ListarUsuarios">Usuários</a>
            <a href="/LivrariaNext/ListarLojas">Lojas</a>
            <a href="/LivrariaNext/Relatorio">Relatório</a>
        </c:if>
    </nav>
</div>
