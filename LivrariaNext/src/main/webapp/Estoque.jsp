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
        <script type="text/javascript" lang="javascript" src="/LivrariaNext/scripts/elementHelper.js"></script>
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
                    <table>
                        <thead>
                            <tr>
                                <th>EAN</th>
                                <th>Produto</th>
                                <th>Saldo</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${estoque.produtos}" var="produto">
                                <tr data-id="${produto.id}" produto-id="${produto.produto.id}" data-action="none" style="display:${produto.ativo ? "table-row" : "none"}">
                                    <td><c:out value="${produto.produto.ean}" /></td>
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
                        <input id="btn-popup" type="button" value="Adicionar Produto">
                    </div>
                </div>
            </form>
                        
            <div id="div-novo-item" class="content popup">
                <p>Incluir Produtos</p> 
                <div class="row">
                    Produto: <input type="text" id="search-produto" style="width: 400px" />
                    <input type="button" value="Buscar" id="btn-buscar-novo" />
                </div>
                <div class="row">
                    <table id="lista-novos-produtos">
                        <thead>
                            <th></th>
                            <th style="width:100px;">EAN</th>
                            <th style="width:300px;">NOME</th>
                            <th style="width:100px;">SALDO</th>
                        </thead>
                        <tbody>                            
                        </tbody>
                    </table>
                </div>
                <div class="row">                    
                    <input type="button" id="btn-add-produto" name="btn-add-produto" Value="Adicionar Produtos" />
                    <input type="button" id="btn-fechar-popup" name="btn-cancelar-add-produto" Value="Fechar" />
                </div>
            </div>
                    
                    
        </div>
    </body>
</html>
