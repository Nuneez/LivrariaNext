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
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <script type="text/javascript" lang="javascript" src="/LivrariaNext/scripts/pedido.js"></script>
        <link rel="stylesheet" type="text/css" href="/LivrariaNext/css/pedido.css">
        <title>Venda</title>
    </head>
    <body>
        <jsp:include page="/shared/menu.jsp"></jsp:include>

        <div class="content">
          <form method="post">
            <div class="row">
              <label for="cliente">CPF Cliente</label>
              <input id="cliente" name="cliente" live-search="cliente"/>
              <button name="procurar_usuario">Procurar</button>
            </div>

            <div class="row">
              <label for="produto">Produto</label>
              <input name="produto" id="produto" live-search="produto"/>

              <label for="quantidade_produto">Quantidade Produto</label>
              <input type="number" name="quantidade_produto" id="quantidade_produto"/>

              <button name="add_produto">Adicionar</button>
            </div>

            <div class="content lst-produtos table">
              <table class="row">
                <tr>
                  <th>Descrição</th>
                  <th>Quantidade</th>
                  <th>Valor unitario</th>
                  <th>Valor total</th>
                </tr>
              </table>
              <div class="row">
                <span>Total: <span name="total"></span></span>
              </div>
            </div>
            <div class="row">
              <div class="right">
                <button name="cancel">Cancelar</button>
                <button name="concluir">Concluir</button>
              </div>
            </div>
          </form>
        </div>
    </body>
</html>
