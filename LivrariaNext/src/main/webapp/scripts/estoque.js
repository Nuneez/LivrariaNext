function init(){    
    document.querySelector("#cancelar").addEventListener("click", function(){ window.history.back(); })
    document.querySelector("#btn-popup").addEventListener("click", abrirPopup);//function(){ window.history.back();
    document.querySelector("#btn-add-produto").addEventListener("click", incluirProdutos);
    document.querySelector("#btn-fechar-popup").addEventListener("click", fecharPopup);
    document.querySelector("#btn-salvar").addEventListener("click", salvar);    
    document.querySelector("#btn-buscar-novo").addEventListener("click", getProdutos);
    atribuirEventoExclusao();
    atribuirEventoEdicao();
};

function atribuirEventoEdicao(){    
    document.querySelectorAll("form table:first-of-type tbody tr input[type='number']").forEach(n => n.addEventListener("keyup", editar));
};

function atribuirEventoExclusao(){    
    document.querySelectorAll("form table:first-of-type tbody tr input[type='button']").forEach(b => b.addEventListener("click", excluir));
};

function abrirPopup(){
    var div = document.querySelector("#div-novo-item");
    div.style["display"] = "block";    
};

function fecharPopup(){
    var div = document.querySelector("#div-novo-item");
    div.style["display"] = "none";
    limparPopup();
};

function limparPopup(){
    var body = document.querySelector("#div-novo-item table tbody");
    var input = document.querySelector("#div-novo-item input[type='text']");
    input.value = "";
    body.innerHTML = "";
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
                    attributes= [{ name: "produto-id", value: produto.id }]
                );
    
    return tr;
};

function incluirProdutos(){
    var trs = document.querySelectorAll("#lista-novos-produtos tbody tr");
    
    trs = [].filter.call(trs, tr => tr.children[0].children[0].checked);
    
    if ([].every.call(trs, validarSaldo))
        trs.forEach(l => incluirProduto(l));
    
    limparPopup();
};

function incluirProduto(tr){
    if (!validarSaldo(tr))
        return;
    
    if (validarProdutoExistente(tr))    
        addProdutoExistente(tr);
    else
        addNovoProduto(tr);
};

function addNovoProduto(tr){
    tr.children[0].remove();
    tr.appendChild(createTdWithInput({ type: "button", value: "Excluir", callback: excluir }));

    tr.setAttribute("data-action", "insert");
    tr.setAttribute("data-id", "0");

    document.querySelector("form table:first-of-type tbody").appendChild(tr);    
};

function addProdutoExistente(tr){
    var tre = document.querySelector("form table:first-of-type tbody tr[produto-id='" + tr.getAttribute("produto-id") + "']");
        
    if (tre.getAttribute("data-action") === "none" || tre.getAttribute("data-action") === "delete")
    {
        tre.setAttribute("data-action", "edit");
        tre.style["display"] = "table-row";
    }

    tre.children[2].children[0].value = tr.children[3].children[0].value;       
};

function validarSaldo(tr){
    var saldo = parseInt(tr.children[3].children[0].value);
    
    if (saldo < 0){
        alert("O saldo inicial do produto deve ser igual ou maior que zero.");
        return false;
    }
    
    return true;
};

function validarProdutoExistente(tr){
    var produtoId = tr.getAttribute("produto-id");
        
    var trs = document.querySelectorAll("form table:first-of-type tbody tr");
    
    return [].some.call(trs, tr => tr.getAttribute("produto-id") === produtoId);
};

function salvar(){
    var produtos = obterProdutos();
    var id = document.querySelector("#id").value;
            
    ajaxPost(document.forms[0].action, { "id": id, "produtos": JSON.stringify(produtos) }, function(){ alert("sucesso!") });
};

function obterProdutos(){
    var produtos = [];
    
    document
        .querySelectorAll("form table:first-of-type tbody > tr")
        .forEach(tr => 
            produtos.push(
            { 
                id: tr.getAttribute("data-id"), 
                produtoId: tr.getAttribute("produto-id"), 
                saldo: tr.children[2].children[0].value, 
                action: tr.getAttribute("data-action")  
            }));
            
    return produtos;
};

window.addEventListener("load", init);