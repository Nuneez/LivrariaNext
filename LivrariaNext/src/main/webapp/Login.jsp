<%--
    Document   : Login
    Created on : Jun 4, 2017, 5:35:05 PM
    Author     : thiagomessias
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link href="https://fonts.googleapis.com/css?family=PT+Sans" rel="stylesheet"/>
    <link rel="stylesheet" type="text/css" href="/LivrariaNext/css/lib.css"/>
    <title>Login</title>
  </head>
  <body>
    <div id="header-space">
      <div id="logo-space">
        <a href="/LivrariaNext/"><img src="/LivrariaNext/imgs/logo.png"/></a>
      </div>
    </div>
    <div class="session">
      <c:if test="${erro != null}">
        <p>
          <c:out value="${erro}"></c:out>
        </p>
      </c:if>
      <form action="/LivrariaNext/Sessao" method="post">
        <label for="username">Nome de usuario</label>
        <input type="text" name="username" id="username" required/>
        <label for="senha">Senha</label>
        <input type="password" name="senha" id="senha" required/>
        <input type="submit" value="Login"/>
      </form>
    </div>
  </body>
</html>
