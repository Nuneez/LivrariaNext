<%--
    Document   : Cliente
    Created on : 15/04/2017, 20:50:24
    Author     : Thiago
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/cliente.css">
        <script type="text/javascript" lang="javascript" src="/LivrariaNext/scripts/formulario.js"></script>
        <title>Loja</title>
    </head>
    <body>
        <jsp:include page="/shared/menu.jsp"></jsp:include>
        <div class="content">
            <form action="/LivrariaNext/ManterCliente" method="post">
                <div class="session">
                    <div class="row">
                        <label for="codigo">Codigo</label>
                        <input id="codigo" type="text" name="codigo"/>
                        <label for="razao">Razao Social</label>
                        <input id="razao" type="text" name="razao"/>
                    </div>
                    <div class="row">
                        <label for="cnpj">CNPJ</label>
                        <input id="cnpj" type="text" name="cnpj"/>
                        <label for="insc">Inscrição Estadual</label>
                        <input id="insc" type="text" name="insc"/>
                    </div>
                </div>
                <hr>
                <div class="row">
                    <label for="endereco">Endereço</label>
                    <input type="text" name="endereco" id="endereco"/>
                    <label for="numero">N</label>
                    <input type="number" name="numero" id="numero"/>
                </div>
                <hr>
                <div class="row">
                    <input type="submit" value="Enviar">
                    <input type='reset' value='Cancelar'>
                </div>
            </form>
        </div>
        <!-- <div class="content">
              <c:out value="${cliente.getNome()}" />
              <c:out value="${cliente.getSobreNome()}" />
              <c:out value="${cliente.getId()}" />
              <c:out value="${cliente.getCpf()}" />
              <c:out value="${cliente.getRg()}" />
              <c:out value="${cliente.getNascimento()}" />
              <c:out value="${cliente.getEndereco()}" />
              <c:out value="${cliente.getNumero()}" />
              <c:out value="${cliente.getEmail()}" />
              <c:out value="${cliente.getTelefone()}" />
              <c:out value="${cliente.getLoja()}" />
        </div> -->
        <jsp:include page="/shared/footer.jsp"></jsp:include>
    </body>
</html>
