/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var estoque = function() { return document.forms[0].action.replace("ListarLojas", "ManterEstoques"); };

var init = function(){    
    var btns = document.querySelectorAll(".btn-estoque");
    btns.forEach(b => b.addEventListener("click", abrirEstoques));
};

var abrirEstoques = function(evt){
    var btn = evt.srcElement;    
    window.location = estoque() + "?id=" + btn.getAttribute("data-id");
};

window.addEventListener("load", init);

