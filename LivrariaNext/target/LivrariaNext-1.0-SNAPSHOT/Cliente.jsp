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
        <link rel="stylesheet" type="text/css" href="/LivrariaNext/css/cliente.css">
        <script type="text/javascript" lang="javascript" src="/LivrariaNext/scripts/formulario.js"></script>
        <title>Cliente</title>
    </head>
    <body>
        <jsp:include page="/shared/menu.jsp"></jsp:include>
        <div class="content">
            <form action="/LivrariaNext/ManterCliente" method="post">
                <div class="session">
                    <div class="row">
                        <label for="nome">Nome</label>
                        <input id="nome" type="text" name="nome"/>
                        <label for="sobrenome">Sobrenome</label>
                        <input id="sobrenome" type="text" name="sobrenome"/>
                    </div>
                    <div class="row">
                        <label for="sexo">Sexo</label>
                        <select name="sexo" id="sexo">
                            <option value="M">Masculino</option>
                            <option value="F">Feminino</option>
                        </select>
                    </div>
                    <div class="row">
                        <label for="cpf">CPF</label>
                        <input type="number" name="cpf" id="cpf">
                        <label for="rg">RG</label>
                        <input type="number" name="rg" id="rg">
                    </div>
                </div>
                <hr>
                
                <div class="row">
                    <label for="endereco">Endereço</label>
                    <input type="text" name="endereco" id="endereco"/>
                    <label for="bairro">Bairro</label>
                    <input type="text" name="bairro" id="bairro"/>
                    <label for="numero">N</label>
                    <input type="number" name="numero" id="numero"/>
                </div>
                <div class="row">
                    <label for="email">Email</label>
                    <input type="text" name="email" id="email"/>
                    <label for="telefone">Telefone</label>
                    <input type="text" name="telefone" id="telefone"/>
                </div>
                <hr>
                <div class="row">
                    <input type="submit" value="Enviar">
                    <input type='reset' value='Cancelar'>
                </div>
            </form>
        </div>
        <div class="content">
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
        </div>
        <jsp:include page="/shared/footer.jsp"></jsp:include>
    </body>
</html>
