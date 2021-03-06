<%--
    Document   : Vendas
    Created on : 06/05/2017, 10:21:11
    Author     : Thiago
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>

        <link rel="stylesheet" href="https://code.getmdl.io/1.3.0/material.indigo-pink.min.css">
        <script defer src="https://code.getmdl.io/1.3.0/material.min.js"></script>

        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <script type="text/javascript" lang="javascript" src="/LivrariaNext/scripts/pedido.js"></script>
        <link rel="stylesheet" type="text/css" href="/LivrariaNext/css/lista.css">
        <link rel="stylesheet" type="text/css" href="/LivrariaNext/css/pedido.css">
        <title>Venda</title>
    </head>
    <body>
        <jsp:include page="/shared/menu.jsp"></jsp:include>

            <div class="content">
                <form onsubmit="false">
                    <div class="row">
                        <label for="cliente">CPF Cliente</label>
                        <input id="cliente" name="cliente" maxlength="11" required="required" live-search="cliente"/>
                        <input type="hidden" />
                    </div>

                    <div class="row">
                        <label for="produto">Produto</label>
                        <input name="produto" id="produto" maxlength="20" live-search="produto"/>

                        <label for="quantidade_produto">Quantidade</label>
                        <input type="number" name="quantidade_produto" min="1" id="quantidade_produto" value="1"/>

                        <button type="button" name="add_produto" id="add_produto">Adicionar</button>
                    </div>

                    <div class="content lst-produtos table">
                        <table class="mdl-data-table">
                            <tr>
                                <th>Descrição</th>
                                <th>Quantidade</th>
                                <th>Valor unitario</th>
                                <th>Valor total</th>
                                <th>Remover</th>
                            </tr>
                        </table>
                        <div class="row">
                            <span>Total: <span name="total"></span></span>
                        </div>
                    </div>
                    <div class="row">
                        <div class="right">
                            <button type="button"  name="concluir" id="submit">Concluir</button>
                            <button name="cancel" type="reset" id="reset">Cancelar</button>
                        </div>
                    </div>
                </form>
            </div>
        <jsp:include page="/shared/footer.jsp"></jsp:include>
    </body>
</html>
