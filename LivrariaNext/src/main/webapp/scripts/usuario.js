/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


var init = function(){
    
};

var salvar = function(){
    //ajaxPost("http://localhost:8080/LivrariaNext/Usuarios", "action=excluir&usuario_id=", salvo);
};

var salvo = function(r){            
        
    if (r === undefined){
        alert("Erro ao executar a operação.");
        return;
    }
    
    var retorno = JSON.parse(r);
        
    alert(retorno.mensagem);
    
    if (retorno.sucesso){
        var btn = document.querySelector("#btn-voltar");
        btn.click();
    }
};

window.addEventListener("load", init);