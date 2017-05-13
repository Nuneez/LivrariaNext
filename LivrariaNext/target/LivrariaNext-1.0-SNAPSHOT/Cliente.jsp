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
        <div>${erro == null? '': erro }</div>
        <div class="content">
            <form action="/LivrariaNext/ManterClientes" method="post">
                <input type="hidden" name="id" value="${cliente.id}" />
                <div class="session">
                    <div class="row">
                        <label for="nome">Nome</label>
                        <input id="nome" type="text" name="nome" value="${cliente.nome}" />
                        <label for="sobrenome">Sobrenome</label>
                        <input id="sobrenome" type="text" name="sobrenome" value="${cliente.sobreNome}"/>
                    </div>
                    <div class="row">
                        <label for="sexo">Sexo</label>
                        <select name="sexo" id="sexo">
                            <option value="I" ${cliente.sexo == null ? 'Selected' : ''} >[Selecione...]</option>
                            <option value="M" ${cliente.sexo == "M" ? 'Selected' : ''} >Masculino</option>
                            <option value="F" ${cliente.sexo == "F" ? 'Selected' : ''} >Feminino</option>
                        </select>
                    </div>
                    <div class="row">
                        <label for="cpf">CPF</label>
                        <input type="number" name="cpf" id="cpf" value="${cliente.cpf}">
                        <label for="rg">RG</label>
                        <input type="text" name="rg" id="rg" value="${cliente.rg}">
                    </div>
                </div>
                <hr>
                
                <div class="row">
                    <label for="endereco">Endereço</label>
                    <input type="text" name="endereco" id="endereco" value="${cliente.endereco}"/>
                    <label for="numero">Numero</label>
                    <input type="number" name="numero" id="numero" value="${cliente.numero}"/>
                    <label for="bairro">Bairro</label>
                    <input type="text" name="bairro" id="bairro" value="${cliente.bairro}"/>
                    
                </div>
                <div class="row">
                    <label for="email">Email</label>
                    <input type="text" name="email" id="email" value="${cliente.email}"/>
                    <label for="telefone">Telefone</label>
                    <input type="text" name="telefone" id="telefone" value="${cliente.telefone}"/>
                </div>
                <hr>
                <div class="row">
                    <input type="submit" value="Enviar">
                    <input id="cancelar" type='reset' value='Cancelar'>
                </div>
            </form>
        </div>
        <jsp:include page="/shared/footer.jsp"></jsp:include>
    </body>
</html>
