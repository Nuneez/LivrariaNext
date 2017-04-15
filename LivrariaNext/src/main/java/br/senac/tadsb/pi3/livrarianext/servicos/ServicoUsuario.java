/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tadsb.pi3.livrarianext.servicos;

import br.senac.tadsb.pi3.livrarianext.models.Usuario;
import br.senac.tadsb.pi3.livrarianext.database.DaoUsuario;
import br.senac.tadsb.pi3.livrarianext.models.Perfil;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author roger
 */
public class ServicoUsuario extends Servico<Usuario> {

    DaoUsuario dao = new DaoUsuario();
    
    public void incluir(Usuario usuario){
    
    }
    
    public void alterar(Usuario usuario){
    
    }
    
    public void excluir(Usuario usuario){
    
    }    
    
    public List<Usuario> ObterUsuarios(){
        return new ArrayList<Usuario>();
    }
    
    public List<Perfil> ObterPerfis(){
        return new ArrayList<Perfil>();
    }   
}
