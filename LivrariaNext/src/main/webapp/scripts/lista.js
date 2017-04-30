/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var edit = function() { return document.forms[0].action.replace("Listar", "Manter"); };
var action = function () { return document.forms[0].action; };

var init = function(){    
    var btnNovo = document.querySelector("#btn-novo");    
    btnNovo.addEventListener("click", incluir);    
    
    var btsEditar = document.querySelectorAll(".btn-editar");
    //btsEditar.forEach(function(b){ b.addEventListener("click", editar); });;
    
    for (var x = 0; x < btsEditar.length; x++)
        btsEditar[x].addEventListener("click", editar);
    
    var btsExcluir = document.querySelectorAll(".btn-excluir");
    //btsExcluir.forEach(function(b){ b.addEventListener("click", excluir); });;
    
    for (var x = 0; x < btsExcluir.length; x++)
        btsExcluir[x].addEventListener("click", excluir);
};

var editar = function(evt){
    var btn = evt.srcElement;    
    window.location = edit() + "?id=" + btn.getAttribute("data-id");    
};

var incluir = function(){
    window.location = edit();    
};

var excluir = function(evt){
    var btn = evt.srcElement;    
    ajaxPost(action(), "action=excluir&id=" + btn.getAttribute("data-id"), exclusao);
};

var exclusao = function(r){            
        
    if (r === undefined){
        alert("Erro ao executar a operação.");
        return;
    }
    
    var retorno = JSON.parse(r);
        
    alert(retorno.mensagem);
    
    if (retorno.sucesso){
        var btn = document.querySelector("#btn-buscar");
        btn.click();
    }
};

window.addEventListener("load", init);

