<%-- 
    Document   : Usuarios
    Created on : 10/04/2017, 11:13:37
    Author     : roger
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="/LivrariaNext/css/usuarios.css">
        <script type="text/javascript" lang="javascript" src="/LivrariaNext/scripts/usuarios.js"></script>
        <title>Usuários</title>
    </head>
    <body>
        <jsp:include page="/shared/menu.jsp"></jsp:include>
        <div class="content">            
            <form action="/LivrariaNext/Usuarios" method="get">
                <div class="session">
                    <div class="row">
                        <label for="nome">Nome</label>
                        <input id="nome" type="text" name="nome"/>
                        <input id="btn-buscar" type="submit" value="Buscar">
                    </div>
                </div>
                <div class="session">
                    <table>
                      <tr>
                        <th>ID</th>
                        <th>Nome</th>
                        <th>Sobrenome</th>
                        <th>Perfil</th>
                      </tr>
                      <c:forEach items="${usuarios}" var="usuario">
                        <tr>
                          <td><c:out value="${usuario.id}" /></td>
                          <td><c:out value="${usuario.nome}" /></td>
                          <td><c:out value="${usuario.sobreNome}" /></td>
                          <td><c:out value="${usuario.perfil.nome}" /></td>
                        </tr>
                      </c:forEach>
                    </table>                        
                </div>
                <div class="session">
                    <div class="row">
                        <input id="btn-novo" type="button" value="Incluir Novo" />
                    </div>
                </div>
            </form>
        </div>    
    </body>
</html>
