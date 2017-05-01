<%-- 
    Document   : Produto
    Created on : 01/05/2017, 14:50:31
    Author     : Elison
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="/LivrariaNext/css/cliente.css">
        <script type="text/javascript" lang="javascript" src="/LivrariaNext/scripts/formulario.js"></script>
        <title>Produto</title>
    </head>
    <body>
        <jsp:include page="/shared/menu.jsp"></jsp:include>
            <div class="content">
                <form action="/LivrariaNext/ManterProdutos" method="post">
                    <input type="hidden" name="id" value="${produto.id}" />
                <div class="session">
                    <div class="row">
                        <label for="nome">Titulo</label>
                        <input id="nome" type="text" name="titulo" value="${produto.nomecomun}" />
                    </div>
                    <div class="row">
                        <label for="ean">EAN</label>
                        <input type="number" name="ean" id="ean" value="${produto.ean}">
                        <label for="rg">Preço</label>
                        <input type="text" name="custo" id="custo" value="${produto.custo}">
                    </div>
                    <div class="row">
                        <label for="atividade">Ativo</label>
                        <select name="ativo" id="ativo">
                            <option value="true" ${produto.ativo ? 'selected' : ''}>Sim</option>
                            <option value="false" ${!produto.ativo ? 'selected' : ''}>Não</option>
                        </select>
                    </div>
                </div>
                <hr>
                <div class="row">
                    <label for="descricao">Descrição</label>
                    <textarea rows="4" cols="50" name="descriacao" id="descricao" value="${produto.descricao}">
                    </textarea>
                </div>                         
                <hr> 
                <div class="row">
                    <input type="submit" value="Enviar">
                    <input id="cancelar" type='reset' value='Cancelar'>
                </div>
            </form>
        </div>
        <jsp:include page="/shared/footer.jsp"></jsp:include>
    </body>
</html>