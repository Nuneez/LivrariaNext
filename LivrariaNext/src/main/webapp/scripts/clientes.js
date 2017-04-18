/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var init = function(){    
    var btnIncluir = document.querySelector("#btn-novo");    
    btnIncluir.addEventListener("click", incluir);    
    
    var btsEditar = document.querySelectorAll(".btn-editar");
    btsEditar.forEach(function(b){ b.addEventListener("click", editar); });;
};

var editar = function(evt){
    var btn = evt.srcElement;    
    window.location = "http://localhost:8080/LivrariaNext/ManterCliente?id=" + btn.getAttribute("data-id");    
};

var excluir = function(){
    //window.location = "http://localhost:8080/LivrariaNext/ManterUsuario";    
};

var incluir = function(){
    window.location = "http://localhost:8080/LivrariaNext/ManterCliente";    
};

window.addEventListener("load", init);