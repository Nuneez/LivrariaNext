/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var init = function(){    
    var btnNovo = document.querySelector("#btn-novo");    
    btnNovo.addEventListener("click", novoUsuario);    
    
    var btsEditar = document.querySelectorAll(".btn-editar");
    btsEditar.forEach(function(b){ b.addEventListener("click", editarUsuario); });;
};

var editarUsuario = function(evt){
    var btn = evt.srcElement;    
    window.location = "http://localhost:8080/LivrariaNext/ManterUsuario?id=" + btn.getAttribute("data-id");    
};

var excluirUsuario = function(){
    //window.location = "http://localhost:8080/LivrariaNext/ManterUsuario";    
};

var novoUsuario = function(){
    window.location = "http://localhost:8080/LivrariaNext/ManterUsuario";    
};

window.addEventListener("load", init);

