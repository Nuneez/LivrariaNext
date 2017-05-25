function init(){    
    
    document.querySelector("#div-novo-item > p").addEventListener("click", toogleNovoItem);
    document.querySelector("#btn-add-produto").addEventListener("click", incluirProduto);
    document.querySelector("#btn-salvar").addEventListener("click", salvar);
    
    window.liveSearch({
        produto: function(element, callback) {
            if (element.value.length >= 3) {        
                var url = document.forms[0].action.replace('ManterEstoques', 'Produtos') + "?search=" + document.querySelector("#search-produto").value;
                fetch(url).then(rsps => rsps.json()).then(json => callback(json, element));
            }
        }
    });
};

function toogleNovoItem(){
    var div = document.querySelector("#div-novo-item > .row");
    div.style["display"] = (div.style["display"] === "none") ? "block" : "none";
    focarNovoProduto();
};

function incluirProduto(){
    if (!validarProduto())
        return;
    
    var id = document.querySelector("#search-produto").value;
    var nome = document.querySelector("#search-produto").value;
    var saldo = document.querySelector("#qtd-produto").value;
    
    criarLinha(id, nome, saldo);
    
    limparInputsNovoProduto();
    focarNovoProduto();
};

function focarNovoProduto(){
    document.querySelector("#search-produto").focus();
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
    tr.setAttribute("data-id", 0);
    tr.setAttribute("data-produtoid", id);
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

function limparInputsNovoProduto(){
    document
        .querySelectorAll("#div-novo-item > .row > input:not([type='button'])")
        .forEach(i => i.value = "");
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
//        ;
};

function obterProdutos(){
    var produtos = [];
    
    document
        .querySelectorAll("tbody > tr")
        .forEach(tr => 
            produtos.push(
            { 
                id: tr.getAttribute("data-id"), 
                produtoId: tr.getAttribute("data-produtoid"), 
                saldo: tr.children[2].children[0].value, 
                action: tr.getAttribute("data-action")  
            }));
            
    return produtos;
};

window.addEventListener("load", init);