function init(){    
    document.querySelector("#btn-popup").addEventListener("click", abrirPopup);
    document.querySelector("#btn-add-produto").addEventListener("click", incluirProduto);
    document.querySelector("#btn-fechar-popup").addEventListener("click", fecharPopup);
    document.querySelector("#btn-salvar").addEventListener("click", salvar);    
    document.querySelector("#btn-buscar-novo").addEventListener("click", getProdutos);
};

function abrirPopup(){
    var div = document.querySelector("#div-novo-item");
    div.style["display"] = "block";    
};

function fecharPopup(){
    var div = document.querySelector("#div-novo-item");
    div.style["display"] = "none";    
};

function incluirProduto(){
    if (!validarProduto())
        return;
    
    var id = document.querySelector("#search-produto").value;
    var nome = document.querySelector("#search-produto").value;
    var saldo = document.querySelector("#qtd-produto").value;
    
    criarLinha(id, nome, saldo);
};

function validarProduto(){
    /*Acrescentar a regra depois
     *  Deve validar se o produto já pertence a este estoque antes de incluir
     */
    
    return true;
};

function criarLinha(id, nome, saldo){
    var tbody = document.querySelector("table > tbody");
    var tr = document.createElement("tr");
    tr.setAttribute("data-id", id)
    tr.setAttribute("data-action", "insert");
    
    criarTds(id, nome, saldo).map(td => tr.appendChild(td));
    
    tbody.appendChild(tr);
}

function criarTds(id, nome, saldo){
    var tdId = document.createElement("td");
    var tdNome = document.createElement("td");
    var tdSaldo = document.createElement("td");
    var tdExcluir = document.createElement("td");    
    
    tdId.innerHTML = id;
    tdNome.innerHTML = nome;
    tdSaldo.appendChild(criarInputSaldo(saldo));    
    tdExcluir.appendChild(criarBotaoExcluir());
    
    return [ tdId, tdNome, tdSaldo, tdExcluir ];
};

function criarInputSaldo(value){
    var ipt = document.createElement("input");
    ipt.setAttribute("type", "number");
    ipt.value = value;    
    ipt.addEventListener("keypress", editar);
    return ipt;
};

function criarBotaoExcluir(){
    var btn = document.createElement("input");
    btn.setAttribute("type", "button");
    btn.value = "Excluir";
    btn.addEventListener("click", excluir);    
    return btn;
};

function excluir(evt){
    var tr = evt.srcElement.parentNode.parentNode;
    tr.setAttribute("data-action", "delete");
    tr.style["display"] = "none";
};

function editar(evt){
    var tr = evt.srcElement.parentNode.parentNode;
    tr.setAttribute("data-action", "edit");
};

function getProdutos(){    
    
    document.querySelector("#lista-novos-produtos > tbody").innerHTML = "";
    
    fetch(document.forms[0].action.replace("ManterEstoques", "Produtos") + "?search=" + document.querySelector("#search-produto").value)
    .then(res => res.json())
    .then(json => json.forEach(j => document.querySelector("#lista-novos-produtos > tbody").appendChild(addLinhaProduto(j))))
        ;
};

function addLinhaProduto(produto){
    var tr = document.createElement("tr");
        
    var ean = document.createElement("td");
    var nome = document.createElement("td");
    var saldo = document.createElement("td");
    var excluir = document.createElement("td");
        
    var id = document.createElement("input");
    var inputSaldo = document.createElement("input");
    
    id.setAttribute("type", "hidden");
    id.value = produto.id;
    
    inputSaldo.setAttribute("type", "text");
    
    ean.appendChild(id);
    ean.innerHTML = produto.ean;
    nome.innerHTML = produto.nome;
    saldo.appendChild(inputSaldo);
    
    tr.appendChild(ean);
    tr.appendChild(nome);
    tr.appendChild(saldo);
    tr.appendChild(excluir);
    
    return tr;
};

function salvar(){
    var produtos = obterProdutos();
    var id = document.querySelector("#id").value;
            
    ajaxPost(document.forms[0].action, { "id": id, "produtos": JSON.stringify(produtos) }, function(){  });
    
//    fetch(document.forms[0].action, 
//    { 
//        method: "POST", 
//        dataType: 'json',
//        contentType: 'application/json',
//        body: { "id": 1, "produtos": "teste" }
//    })
//    .then(res => res.json())
//    .then(json => 
//            json.success ? 
//            alert("sucesso!") : 
//            alert(json.erro)
//        )
        ;
};

window.addEventListener("load", init);