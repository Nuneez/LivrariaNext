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
    var body = document.querySelector("#div-novo-item table tbody");
    var input = document.querySelector("#div-novo-item input[type='text']");
    input.value = "";
    body.innerHTML = "";
    div.style["display"] = "none";    
};

function incluirProduto(td){
    if (!validarProduto(td))
        return;
    
    var id = document.querySelector("#search-produto").value;
    var nome = document.querySelector("#search-produto").value;
    var saldo = document.querySelector("#qtd-produto").value;
    
    criarLinha(id, nome, saldo);
};

function validarProduto(td){
    /*Acrescentar a regra depois
     *  Deve validar se o produto já pertence a este estoque antes de incluir
     */
    
    return true;
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
    .then(json => json.forEach(p => document.querySelector("#lista-novos-produtos > tbody").appendChild(addLinhaProduto(p))))
        ;
};

function addLinhaProduto(produto){
    
    var tr = createTr(
                    tds =   [ 
                                { input: { type: "checkbox", value: false } },
                                { value: produto.ean }, 
                                { value: produto.nome },
                                { input: { type: "text", value: 0 } }
                            ],
                    attributes= [{ "produto-id": produto.id }]
                );
    
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