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
        <title>Usuários</title>
    </head>
    <body>
        <h1>Lista de Usuários</h1>        
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
    </body>
</html>
