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
                <form action="/LivrariaNext/ManterLojas" method="post">
                    <input id="id" name="id" type="hidden" value="${loja.id}" />
                <div class="session">
                    <div class="row">
                        <label for="codigo">Nome</label>
                        <input id="nome" type="text" name="nome" maxlength="50" value="${loja.nome}" />
                        <label for="razao">Razao Social</label>
                        <input id="razao" type="text" name="razaosocial" maxlength="50" value="${loja.razaoSocial}" />
                        <label for="atividade">Ativo</label>
                        <select name="ativo" id="ativo">
                            <option value="true" ${loja.ativo ? 'selected' : ''}>Sim</option>
                            <option value="false" ${!loja.ativo ? 'selected' : ''}>Não</option>
                        </select>
                    </div>
                    <div class="row">
                        <label for="cnpj">CNPJ</label>
                        <input id="cnpj" type="text" name="cnpj" maxlength="14"placeholder="00.000.000/0000-00" value="${loja.cnpj}"/>
                        <label for="insc">Inscrição Estadual</label>
                        <input id="inscricaoestadual" type="text" maxlength="30" name="inscricaoestadual" value="${loja.inscricaoEstadual}" />
                        <label for="atividade">Filial</label>
                        <select name="filial" id="filial">
                            <option value="true" ${loja.ehFilial ? 'selected' : ''}>Sim</option>
                            <option value="false" ${!loja.ehFilial ? 'selected' : ''}>Não</option>
                        </select>
                    </div>
                </div>
                <hr>
                <div class="row">
                    <label for="endereco">Endereço</label>
                    <input type="text" name="endereco" id="endereco" value="${loja.endereco}" />
                    <label for="numero">Numero</label>
                    <input type="text" name="numero" id="numero" placeholder="Nº" value="${loja.numero}"/>
                </div>
                <div class="row">
                    <label for="cidade">Cidade</label>
                    <input type="text" name="cidade" maxlength="30" id="cidade" value="${loja.cidade}" />
                    <label for="estado">UF</label>
                    <input type="text" name="estado" maxlength="2" id="estado" value="${loja.estado}" />
                </div>
                <hr>
                <div class="row">
                    <label for="telefone">Telefone</label>
                    <input type="text" name="telefone" id="telefone" maxlength="11" placeholder="(99)9999-9999" value="${loja.telefone}"/>
                    <label for="email">E-mail</label>
                    <input type="text" name="email" id="email" maxlength="50"placeholder="email@exemplo.com" value="${loja.email}"/>
                </div>
                <hr>
                <div class="row">
                    <input type="submit" value="Enviar">
                    <input id="cancelar" name="cancelar" type='reset' value='Cancelar'>
                </div>
            </form>
        </div>
        <jsp:include page="/shared/footer.jsp"></jsp:include>
    </body>
</html>
