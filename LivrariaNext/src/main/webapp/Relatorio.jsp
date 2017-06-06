<%-- 
    Document   : Relatorio
    Created on : 06/06/2017, 10:34:41
    Author     : roger
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="/LivrariaNext/css/usuarios.css">
        <link rel="stylesheet" type="text/css" href="/LivrariaNext/css/lista.css">
        <title>Relatório de Vendas</title>
    </head>
    <body>
         <jsp:include page="/shared/menu.jsp"></jsp:include>
        <div class="content">            
            <form action="/LivrariaNext/Relatorio" method="GET">
                <div class="session">
                    <div class="row">
                        <label for="inicio">Início: </label>
                        <input id="inicio" type="text" maxlength="10" name="inicio"/>
                        <label for="fim">Fim: </label>
                        <input id="fim" type="text" maxlength="10" name="fim"/>
                        <label for="cliente">Cliente: </label>
                        <input id="cliente" type="text" name="cliente"/>
                    </div>
                    <div class="row">                        
                        <label for="usuario">Usuário: </label>
                        <input id="usuario" type="text" name="usuario"/>
                        <label for="vendedor">Vendedor: </label>
                        <input id="vendedor" type="text" name="vendedor"/>
                        <input id="btn-buscar" type="submit" value="Buscar">
                    </div>
                </div>
                <div class="session">
                    <table>
                      <tr>
                        <th>Data</th>
                        <th>Loja</th>
                        <th>Vendedor</th>
                        <th>Cliente</th>
                        <th>Produto</th>
                        <th>Qtd.</th>
                        <th>Unitário</th>
                        <th>Total</th>
                      </tr>
                      <c:forEach items="${itens}" var="item">
                        <tr>
                          <td><c:out value="${item.pedidoData}" /></td>
                          <td><c:out value="${item.lojaNome}" /></td>
                          <td><c:out value="${item.vendedorNome}" /></td>
                          <td><c:out value="${item.clienteNome}" /></td>
                          <td><c:out value="${item.produtoNome}" /></td>
                          <td><c:out value="${item.qtdProduto}" /></td>
                          <td><c:out value="${item.valorUnitario}" /></td>
                          <td><c:out value="${item.valorTotal}" /></td>
                        </tr>
                      </c:forEach>
                    </table>                        
                </div>
            </form>
        </div>    
        <jsp:include page="/shared/footer.jsp"></jsp:include>
    </body>
</html>
