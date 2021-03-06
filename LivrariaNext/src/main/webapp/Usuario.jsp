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
                <form action="/LivrariaNext/ManterUsuarios" method="post">
                    <input id="id" name="id" type="hidden" value="${usuario.id}" />
                <div class="session">
                    <div class="row">
                        <label for="nome">Nome*</label>
                        <input id="nome" type="text" required="required" name="nome" maxlength="20" value="${usuario.nome}"/>
                        <label for="sobrenome">Sobrenome</label>
                        <input id="sobrenome" type="text" name="sobrenome" maxlength="20"value="${usuario.sobrenome}"/>                        
                    </div>
                    <div class="row">
                        <label for="username">Username*</label>
                        <input id="username" type="text" name="username" required="required" maxlength="15"value="${usuario.username}"/>
                        <label for="email">Email*</label>
                        <input id="email" type="text" name="email" required="required" maxlength="50"placeholder="email@exemplo.com/" value="${usuario.email}">                        
                    </div>
                    <div class="row">
                        <label for="atividade">Ativo</label>
                        <select name="ativo" id="ativo">
                            <option value="true" ${usuario.ativo ? 'selected' : ''}>Sim</option>
                            <option value="false" ${!usuario.ativo ? 'selected' : ''}>Não</option>
                        </select>
                    </div>
                    <div class="row">
                        <label for="perfil">Perfil: </label>
                        <select name="perfil"id="perfil">
                            <option value="0" ${usuario.perfil == null ? 'Selected' : ''} >[Selecione...]</option>
                            <c:forEach items="${perfis}" var="perfil">
                                <option value="${perfil.id}" ${usuario.perfil.id == perfil.id ? 'Selected' : ''} >${perfil.nome}</option>
                            </c:forEach>
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
        <jsp:include page="/shared/footer.jsp"></jsp:include>
    </body>
</html>
