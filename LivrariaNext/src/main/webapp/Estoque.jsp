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
                <input type="hidden" name="id" value="${estoque.id}" />
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
                    <div class="row">
                        <input type="text" id="search-produto" /><input type="button" id="btn-add-produto" name="btn-add-produto" Value="Adicionar Produto" />
                    </div>
                </div>
                <div class="session">
                    <table>
                        <tr>
                            <th>ID</th>
                            <th>Produto</th>
                            <th>Saldo</th>
                        </tr>
                        <c:forEach items="${estoque.produtos}" var="produto">
                            <tr>
                                <td><c:out value="${produto.produto.id}" /></td>
                                <td><c:out value="${produto.produto.nome}" /></td>
                                <td><c:out value="${produto.saldo}" /></td>                          
                            </tr>
                        </c:forEach>
                    </table>   
                </div>
                <div class="session">
                    <div class="row">
                        <input type="submit" value="Salvar">
                        <input id="cancelar" type='reset' value='Cancelar'>
                    </div>
                </div>
            </form>
        </div>
    </body>
</html>
