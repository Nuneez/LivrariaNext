<%-- 
    Document   : Produtos
    Created on : 01/05/2017, 16:03:32
    Author     : Elison
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="/LivrariaNext/css/usuarios.css">
        <link rel="stylesheet" type="text/css" href="/LivrariaNext/css/lista.css">
        <script type="text/javascript" lang="javascript" src="/LivrariaNext/scripts/lista.js"></script>
        <title>Produtos</title>
    </head>
    <body>
        <jsp:include page="/shared/menu.jsp"></jsp:include>
        <div class="content">            
            <form action="/LivrariaNext/ListarProdutos" method="get">
                <div class="session">
                    <div class="row">
                        <label for="nome">Titulo</label>
                        <input id="nomecomum" type="text" name="nome"/>
                        <label for="nome">Ativo</label>
                         <select name="ativo" id="ativo">
                            <option value="true" ${ativo ? 'selected' : ''}>Sim</option>
                            <option value="false" ${!ativo ? 'selected' : ''}>Não</option>
                        </select>
                        <label for="nome">EAN</label>
                        <input id="ean" type="text" name="ean"/>
                        <input id="btn-buscar" type="submit" value="Buscar">
                    </div>
                </div>
                <div class="session">
                    <table>
                      <tr>
                        <th>ID</th>
                        <th>Titulo</th>
                        <th>Ativo</th>
                        <th>Descrição</th>
                        <th></th>
                      </tr>
                      <c:forEach items="${produtos}" var="produto">
                        <tr>
                          <td><c:out value="${produto.id}" /></td>
                          <td><c:out value="${produto.nome}" /></td>                       
                          <td><c:out value="${produto.ativo?'SIM':'NÃO'}" /></td>
                          <td><c:out value="${produto.descricao}" /></td>
                          <td><input type="button" class="btn-editar" data-id="${produto.id}" Value="Editar" /></td>
                          <td><input type="button" class="btn-excluir" data-id="${produto.id}" Value="Excluir" /></td>
                        </tr>
                      </c:forEach>
                    </table>                            
                </div>
                <div class="session">
                    <div class="row">
                        <input id="btn-novo" type="button" value="Novo Produto" />
                    </div>
                </div>
            </form>
        </div>    
    </body>
</html>
