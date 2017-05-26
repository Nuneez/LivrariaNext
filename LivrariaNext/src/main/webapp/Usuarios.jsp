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
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="/LivrariaNext/css/usuarios.css">
        <link rel="stylesheet" type="text/css" href="/LivrariaNext/css/lista.css">
        <script type="text/javascript" lang="javascript" src="/LivrariaNext/scripts/lista.js"></script>
        <title>Usuários</title>
    </head>
    <body>
        <jsp:include page="/shared/menu.jsp"></jsp:include>
        <div class="content">            
            <form action="/LivrariaNext/ListarUsuarios" method="get">
                <input type="hidden" id="edit" Value="ManterUsuario"/>
                <div class="session">
                    <div class="row">
                        <label for="nome">Nome: </label>
                        <input id="nome" type="text" name="nome"/>
                        <label for="atividade">Ativo: </label>
                        <select name="ativo" id="ativo">
                            <option value="true" ${ativo ? 'selected' : ''}>Sim</option>
                            <option value="false" ${!ativo ? 'selected' : ''}>Não</option>
                        </select>
                        <label for="perfil">Perfil: </label>
                        <select name="perfil" id="perfil">
                            <option value="0">[Selecione...]</option>
                            <c:forEach items="${perfis}" var="perfil">
                                <option value="${perfil.id}">${perfil.nome}</option>
                            </c:forEach>
                        </select>
                        <input id="btn-buscar" type="submit" value="Buscar">
                    </div>
                </div>
                <div class="session">
                    <table  style="width:900px;">
                      <tr>
                        <th>Nome</th>
                        <th>Sobrenome</th>
                        <th>Ativo</th>
                        <th>Perfil</th>
                        <th></th>
                      </tr>
                      <c:forEach items="${usuarios}" var="usuario">
                        <tr>
                          <td><c:out value="${usuario.nome}" /></td>
                          <td><c:out value="${usuario.sobrenome}" /></td>
                          <td><c:out value="${usuario.ativo?'SIM':'NÃO'}" /></td>
                          <td><c:out value="${usuario.perfil.nome}" /></td>
                          <td><input type="button" class="btn-editar" data-id="${usuario.id}" Value="Editar" /></td>
                          <td><input type="button" class="btn-excluir" data-id="${usuario.id}" Value="Excluir" /></td>
                        </tr>
                      </c:forEach>
                    </table>                        
                </div>
                <div class="session">
                    <div class="row">
                        <input id="btn-novo" type="button" value="Novo Usuário" />
                    </div>
                </div>
            </form>
        </div>    
                        <jsp:include page="/shared/footer.jsp"></jsp:include>
    </body>
</html>
