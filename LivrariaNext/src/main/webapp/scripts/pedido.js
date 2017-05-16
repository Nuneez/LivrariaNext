window.onload = function() {
  window.liveSearch({
    cliente: function(element, callback) {
      console.log("cliente");
      if (element.value.length >= 3) {
        var url =  window.location.href.replace('Pedido', 'ListarClientes') + "?search=" + document.querySelector("#cliente").value;
        fetch(url)
                .then(response => response.json())
                .then(json => callback(json, element));
      }
    },
    produto: function(element, callback) {
      console.log("produto");
      if (element.value.length >= 3) {
        var url =  window.location.href.replace('Pedido', 'Produtos') + "?search=" + document.querySelector("#produto").value;
        fetch(url)
                .then(response => response.json())
                .then(json => callback(json, element));
      }
    }
  });

  document.querySelector("#add_produto").addEventListener('click', addProduto);
  document.querySelector("#reset").addEventListener('click', resetData);
  document.querySelector("#submit").addEventListener('click', postData);
};

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
  removeButton.append("Remover");

  removeButton.addEventListener('click', function() {
    var tr = this.parentNode.parentNode;
    var table = tr.parentNode;
    table.removeChild(tr);
    calcTotal();
  });

  tdNome.append(data.nome);
  tr.append(tdNome);
  tdQnt.append(data.qnt);
  tr.append(tdQnt);
  tdValue.append(data.value);
  tr.append(tdValue);
  tdTotal.append(data.total);
  tr.append(tdTotal);
  tdRemover.append(removeButton);
  tr.append(tdRemover);
  document.querySelector('tbody').append(tr);
}

function rmTableRow(e) {
  console.log(e);
}

function addProduto() {
  var product = document.querySelector('#produto').value;
  var options = document.querySelector('#produto-options').children;
  for (var i = 0; i < options.length; i++) {
    var option = options[i];
    if (option.value === product) {
      var data = { id: product };
      data.value = option.getAttribute('valueproduct');
      data.nome = option.getAttribute('name');
      data.qnt = document.querySelector('#quantidade_produto').value;
      data.total = data.value * data.qnt;
      addTableRow(data);
    }
  }
  calcTotal();
}

function calcTotal() {
  var table = document.querySelector("tbody");
  var total = document.querySelector('[name="total"]');
  var rows = table.children;
  var t = 0;
  for (var i = 1; i < rows.length; i++) {
    var row = rows[i].children;
    t = parseFloat(t) + parseFloat(row[row.length-2].textContent);
  }
  if (total.childElementCount > 0) {
    total.removeChild(total.querySelector('span'))
  }
  var span = document.createElement("span");
  span.append(t);
  total.append(span);
}

function postData() {
  var cliente = document.querySelector('#cliente').value;
  var produtos = []
  var rows = document.querySelector("tbody").children;
  for (var i = 1; i < rows.length; i++) {
    var row = rows[i].children;
    var qnt = row[1].textContent;
    var id  = row[row.length-1].getAttribute('value');
    produtos.push(id, qnt);
  }

  ajaxPost(window.location.href, {
    cliente,
    produtos
  }, function() {
    alert("Venda efetuada com sucesso!");
  });
}
function resetData() {
  window.location.reload();
}
