/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var init = function(){    
    var btn = document.querySelector("#btn-novo");    
    btn.addEventListener("click", novoUsuario);    
};

var buscarUsuarios = function(){
    var nome = document.querySelector("#nome");
    
    if (nome.Value.trim() == "")
    {
        alert("Preencha o campo nome para buscar.");
        return;        
    }
        
    ajaxGet("http://localhost:8080/LivrariaNext/Usuarios?nome=" + nome.Value, function(){
        
    });    
};

var novoUsuario = function(){
    window.location = "http://localhost:8080/LivrariaNext/ManterUsuario";    
};

window.addEventListener("load", init);

