<%--
    Document   : Lojas
    Created on : 01/05/2017, 14:21:14
    Author     : roger
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>

        <link rel="stylesheet" href="https://code.getmdl.io/1.3.0/material.indigo-pink.min.css">

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="/LivrariaNext/css/usuarios.css">
        <link rel="stylesheet" type="text/css" href="/LivrariaNext/css/lista.css">
        <script type="text/javascript" lang="javascript" src="/LivrariaNext/scripts/lista.js"></script>
        <script type="text/javascript" lang="javascript" src="/LivrariaNext/scripts/ListaLoja.js"></script>
        <title>Lojas</title>
    </head>
    <body>
        <jsp:include page="/shared/menu.jsp"></jsp:include>
            <div class="content">
                <form action="/LivrariaNext/ListarLojas" method="get">
                    <div class="session">
                        <div class="row">
                            <label for="nome">Nome</label>
                            <input id="nome" type="text" maxlength="15"name="nome"/>
                            <label for="nome">CNPJ</label>
                            <input id="cnpj" type="text" maxlength="14"name="cnpj"/>
                            <label for="atividade">Ativo: </label>
                            <select name="ativo" id="ativo">
                                <option value="">Ambos</option>
                                <option value="true">Sim</option>
                                <option value="false">Não</option>
                            </select>
                        <input id="btn-buscar" type="submit" value="Buscar">
                    </div>
                    <div class="session">
                        <table class="mdl-data-table">
                            <tr>
                                <th>Nome</th>
                                <th>Cidade</th>
                                <th>CNPJ</th>
                                <th>Ativo</th>
                                <th colspan="3"></th>
                            </tr>
                            <c:forEach items="${lojas}" var="loja">
                                <tr>
                                    <td style="width:300px;"><c:out value="${loja.nome}" /></td>
                                    <td style="width:200px;"><c:out value="${loja.cidade}" /></td>
                                    <td style="width:200px;"><c:out value="${loja.cnpj}" /></td>
                                    <td><c:out value="${loja.ativo?'SIM':'NÃO'}" /></td>
                                    <td><input type="button" class="btn-editar" data-id="${loja.id}" Value="Editar" /></td>
                                    <td><input type="button" class="btn-excluir" data-id="${loja.id}" Value="Excluir" /></td>
                                    <td><input type="button" class="btn-estoque" data-id="${loja.id}" Value="Estoque" /></td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>
                    <div class="session">
                        <div class="row">
                            <input id="btn-novo" type="button" value="Nova Loja" />
                        </div>
                    </div>
                </div>
            </form>
        </div>
        <jsp:include page="/shared/footer.jsp"></jsp:include>
    </body>
</html>
