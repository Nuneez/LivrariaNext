/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function init(){
    document.querySelector("#search-produto").addEventListener("keydown", abrirCadastroProduto);
};

function abrirCadastroProduto(){    
    ajaxGet("/")
};

window.addEventListener("load", init);