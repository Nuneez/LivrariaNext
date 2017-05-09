function mokProdutos() {
  return [
    {
      id: 1,
      descricao: "Produto 1",
      valor:"21.5"
    },
    {
      id: 2,
      descricao: "Produto 2",
      valor:"55.5"
    },
    {
      id: 3,
      descricao: "Produto 3",
      valor:"15"
    }
  ];
}

function mokUsuarios() {
  return [
    {
      id: 1,
      cpf: "111 111 111 11"
    },
    {
      id: 2,
      cpf: "222 222 222 22"
    }
  ];
}

function listarUsuarios(cpf) {
  var resultadosPossiveis = [];
  mokUsuarios().forEach(function(item, index) {
    if(item.cpf.indexOf(cpf) >= 0) {
      resultadosPossiveis.push(item);
    }
  });
  return resultadosPossiveis;
}

function listarProduto(codigo) {
  var resultadosPossiveis = [];
  mokProdutos().forEach(function(item, index) {
    if(item.id.indexOf(codigo) >= 0) {
      resultadosPossiveis.push(item);
    }
  });
  return resultadosPossiveis;
}

function buscarProduto(codigo) {
  var resultados = listarProduto(codigo);
  var field = document.querySelector("#produto");
}
