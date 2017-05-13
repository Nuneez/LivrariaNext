<%-- 
    Document   : Clientes
    Created on : 18/04/2017, 10:23:42
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
        <script type="text/javascript" lang="javascript" src="/LivrariaNext/scripts/lista.js"></script>
        <title>Clientes</title>
    </head>
    <body>
        <jsp:include page="/shared/menu.jsp"></jsp:include>
        <div class="content">            
            <form action="/LivrariaNext/ListarClientes" method="get">
                <div class="session">
                    <div class="row">
                        <label for="nome">Nome</label>
                        <input id="nome" type="text" name="nome"/>
                        <label for="nome">CPF</label>
                        <input id="cpf" type="text" name="cpf"/>
                        <input id="btn-buscar" type="submit" value="Buscar">
                    </div>
                </div>
                <div class="session">
                    <table>
                      <tr>
                        <th>ID</th>
                        <th>Nome</th>
                        <th>Sobrenome</th>
                        <th>CPF</th>
                        <th>Ativo</th>
                        <th colspan="2"></th>
                      </tr>
                      <c:forEach items="${clientes}" var="cliente">
                        <tr>
                          <td><c:out value="${cliente.id}" /></td>
                          <td><c:out value="${cliente.nome}" /></td>
                          <td><c:out value="${cliente.sobreNome}" /></td>                          
                          <td><c:out value="${cliente.cpf}" /></td>
                          <td><c:out value="${cliente.ativo?'SIM':'NÃO'}" /></td>
                          <td><input type="button" class="btn-editar" data-id="${cliente.id}" Value="Editar" /></td>
                          <td><input type="button" class="btn-excluir" data-id="${cliente.id}" Value="Excluir" /></td>
                        </tr>
                      </c:forEach>
                    </table>                        
                </div>
                <div class="session">
                    <div class="row">
                        <input id="btn-novo" type="button" value="Novo Cliente" />
                    </div>
                </div>
            </form>
        </div>  
        <jsp:include page="/shared/footer.jsp"></jsp:include>
    </body>
</html>
