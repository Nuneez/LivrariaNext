window.onload = function() {
  window.liveSearch({
    cliente: function(element, callback) {
      if (element.value.length >= 3) {
        var url =  window.location.href.replace('Pedido', 'ListarClientes') + "?search=" + document.querySelector("#cliente").value.trim();
        fetch(url)
                .then(response => response.json())
                .then(json => callback(prepareClient(json), element));
      }
    },
    produto: function(element, callback) {
      if (element.value.length >= 3) {
        var url =  window.location.href.replace('Pedido', 'Produtos') + "?search=" + document.querySelector("#produto").value.trim();
        fetch(url)
                .then(response => response.json())
                .then(json => callback(prepareProduct(json), element));
      }
    }
  });

  document.querySelector('[name="match_client"]').addEventListener('click', function() {
    var cpf = document.querySelector('#cliente').value;
    window.location = '/LivrariaNext/ManterClientes?cpf=' + cpf;
  });

  document.querySelector("#add_produto").addEventListener('click', addProduto);
  document.querySelector("#reset").addEventListener('click', resetData);
  document.querySelector("#submit").addEventListener('click', postData);
};

// Inicializa a lista de produtos do pedido como vazio assim que a tela e carregada
document.listaProdutos = [];

/**
 * Adiciona uma nova entrada na tablea de pedidos baseado no produto enviado no parametro
 * @param {Object} data Dados do produto adicionado ao pedido para ser adicionado a lista de produtos
 */
function addTableRow(data) {
  var removeButton = document.createElement("button");
  var tr = document.createElement("tr");
  var tdNome = document.createElement("td");
  var tdQnt = document.createElement("td");
  var tdValue = document.createElement("td");
  var tdTotal = document.createElement("td");
  var tdRemover = document.createElement("td");

  removeButton.setAttribute("type", "button");
  removeButton.setAttribute("value", data.id);
  removeButton.setAttribute("class", "removeButton");
  removeButton.setAttribute("list-index", data.listIndex);
  removeButton.append("Remover");

  removeButton.addEventListener('click', function() {
    var tr = this.parentNode.parentNode;
    var table = tr.parentNode;
    delete document.listaProdutos[this.getAttribute('list-index')];
    table.removeChild(tr);
    calcTotal();
  });

  tdNome.append(data.nome);
  tr.append(tdNome);
  tdQnt.append(data.qnt);
  tr.append(tdQnt);
  tdValue.append(data.preco);
  tr.append(tdValue);
  tdTotal.append(data.qnt * data.preco);
  tr.append(tdTotal);
  tdRemover.append(removeButton);
  tr.append(tdRemover);
  document.querySelector('tbody').append(tr);
}

/**
 * Atualiza os campos de quantidade e valor total por produto na tabela
 * @param  {Int} index  Index do produto na lista de produos do pedido salvo na memoria
 */
function updateTableRow(index) {
  var tr = document.querySelector('[list-index="' + index + '"]').parentNode.parentNode;
  var tds = tr.children;
  var qnt = tds[1];
  var total = tds[3];
  qnt.innerHTML = document.listaProdutos[index].qnt;
  total.innerHTML = document.listaProdutos[index].preco * document.listaProdutos[index].qnt;
}

/**
 * Adiciona o produto ao pedido, assim ele e listado na tabela e quando o pedido for concluido e enviado junto
 */
function addProduto() {
  var product = document.querySelector('#produto').value;
  var options = document.querySelector('#produto-options').children;
  for(var i = 0; i < options.length; i++) {
    var option = options[i];
    if (option.value === product) {
      var data = {};
      data.id = option.getAttribute('productid');
      data.preco = option.getAttribute('preco');
      data.nome = option.getAttribute('nome');
      data.qnt = parseInt(document.querySelector('#quantidade_produto').value);
      data.total = data.value * data.qnt;
      // var addNew = true;
      // document.listaProdutos.forEach(function(produto, index) {
      //   if (produto.id === data.id) {
      //     addNew = false;
      //     produto.qnt += data.qnt;
      //     produto.total = produto.qnt * produto.preco;
      //     updateTableRow(index);
      //   }
      // });
      // if (addNew) {
        document.listaProdutos.push(data);
        data.listIndex = document.listaProdutos.length - 1;
        addTableRow(data);
      // }
    }
  }
  calcTotal();
}

/**
 * Calcula o preco total da listagem de produtos e atualiza na tabela
 */
function calcTotal() {
  var table = document.querySelector("tbody");
  var total = document.querySelector('[name="total"]');
  var t = 0;
  document.listaProdutos.forEach(function(produto) {
    t += produto.qnt * produto.preco;
  });

  if (total.childElementCount > 0) {
    total.removeChild(total.querySelector('span'))
  }
  var span = document.createElement("span");
  span.append(t);
  total.append(span);
}

/**
 * Envia todos os dados preenchidos para a API
 */
function postData() {
  if (document.listaProdutos.length < 1) {
    alert("Não é possível concluir o pedido! Lista de produtos está vazia");
    return false;
  }
  var cliente = document.querySelector('#cliente').value;
  var produtos = [];
  document.listaProdutos.forEach(function(produto) {
    produtos.push({
      id: produto.id,
      qnt: produto.qnt
    });
  });
  ajaxPost(window.location.href, {
    cliente,
    produtos
  }, function() {
    alert("Venda efetuada com sucesso!");
    window.location.pathname = window.location.pathname;
  });
}

/**
 * Recarrega a tela para limpar todos os dados preenchidos
 */
function resetData() {
  window.location.reload();
}

/**
 * Prepara cada um dos produtos recebidos da api para serem exibidos na lista de opcoes
 * @param  {Array} products Lista de produtos nao tratadas da api
 * @return {Array}         Lista de produtos com tratamento da api
 */
function prepareProduct(products) {
  if (!Array.isArray(products)) {
    return false;
  }
  var newProductList = [];
  for (var i = 0; i < products.length; i++) {
    var product = products[i];
    if (typeof product.id !== 'undefined' && typeof product.nome !== 'undefined') {
      product.productId = product.id;
      product.id = product.nome;
      newProductList.push(product);
    }
  }
  return newProductList;
}

/**
 * Prepara cada um dos clientes recebidos da api para serem exibidos na lista de opcoes
 * @param  {Array} clients Lista de clientes nao tratadas da api
 * @return {Array}         Lista de clientes com tratamento da api
 */
function prepareClient(clients) {
  if (!Array.isArray(clients)) {
    return false;
  }
  var newClientList = [];
  for (var i = 0; i < clients.length; i++) {
    var client = clients[i];
    if (typeof client.id !== 'undefined' && typeof client.cpf !== 'undefined') {
      client.clientId = client.id;
      client.id = client.cpf;
      newClientList.push(client);
    }
  }
  return newClientList;
}
