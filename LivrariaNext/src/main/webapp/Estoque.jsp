<%-- 
    Document   : Estoque
    Created on : 13/05/2017, 13:06:32
    Author     : roger
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="/LivrariaNext/css/cliente.css">
        <script type="text/javascript" lang="javascript" src="/LivrariaNext/scripts/estoque.js"></script>
        <title>Estoque</title>
    </head>
    <body>
        <jsp:include page="/shared/menu.jsp"></jsp:include>
        <div>${erro == null? '': erro }</div>
        <div class="content">
            <form action="/LivrariaNext/ManterEstoques" method="post">
                <input type="hidden" id="id" name="id" value="${estoque.id}" />
                <div class="session">
                    <div class="row">
                        <label for="nome"><b>Loja</b> ${estoque.loja.nome}</label>
                    </div>
                </div>
                <div class="session">
                    <div class="row">
                        <label for="nome_produto">Produto: </label>
                        <input id="nome_produto" type="text" name="nome_produto" />
                        <label for="ean_produto">EAN: </label>
                        <input id="ean_produto" type="text" name="ean_produto" />
                        <button id="btn-buscar">Buscar</button>
                    </div>
                </div>
                <div id="div-novo-item" class="content" style="text-align: center;">
                    <p>Incluir novo Produto &#8628;</p> 
                    <div class="row" style="display: none;">
                        
                        Produto: <input type="text" id="search-produto" live-search="produto" />
                        Saldo Inicial: <input type="number" id="qtd-produto" />
                        <input type="button" id="btn-add-produto" name="btn-add-produto" Value="Adicionar Produto" />
                    </div>
                </div>
                <div class="session">
                    <table>
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Produto</th>
                                <th>Saldo</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${estoque.produtos}" var="produto">
                                <tr data-id="${produto.id}" data-produtoid="${produto.produto.id}" data-action="none">
                                    <td><c:out value="${produto.produto.id}" /></td>
                                    <td><c:out value="${produto.produto.nome}" /></td>
                                    <td><input type="number" value="${produto.saldo}" /></td>
                                    <td><input type="button" Value="Excluir" /></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>   
                </div>
                <div class="session">
                    <div class="row">
                        <input id="btn-salvar" type="button" value="Salvar">
                        <input id="cancelar" type='reset' value='Cancelar'>
                    </div>
                </div>
            </form>
        </div>
    </body>
</html>
