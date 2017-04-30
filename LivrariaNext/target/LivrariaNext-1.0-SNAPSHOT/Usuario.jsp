<%-- 
    Document   : Usuario
    Created on : 16/04/2017, 18:29:39
    Author     : roger
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="/LivrariaNext/css/cliente.css">
        <script type="text/javascript" lang="javascript" src="/LivrariaNext/scripts/formulario.js"></script>
        <title>Usuário</title>
    </head>
    <body>
        <jsp:include page="/shared/menu.jsp"></jsp:include>        
        <div class="content">
            <form action="/LivrariaNext/ManterUsuario" method="post">
                <input id="id" name="id" type="hidden" value="${usuario.id}" />
                <div class="session">
                    <div class="row">
                        <label for="nome">Nome</label>
                        <input id="nome" type="text" name="nome" value="${usuario.nome}"/>
                        <label for="sobrenome">Sobrenome</label>
                        <input id="sobrenome" type="text" name="sobrenome" value="${usuario.sobreNome}"/>                        
                    </div>
                    <div class="row">
                        <label for="atividade">Ativo</label>
                        <select name="ativo" id="ativo">
                            <option value="true" ${usuario.ativo ? 'selected' : ''}>Sim</option>
                            <option value="false" ${!usuario.ativo ? 'selected' : ''}>Não</option>
                        </select>
                    </div>
                </div>
                <hr>
                <div class="row">
                    <input type="submit" value="Enviar">
                    <input id="cancelar" name="cancelar" type='reset' value='Cancelar'>
                </div>
                <hr>
                <div class="row">
                    <c:out value="${erro}" />
                </div>
            </form>
        </div>
    </body>
</html>
