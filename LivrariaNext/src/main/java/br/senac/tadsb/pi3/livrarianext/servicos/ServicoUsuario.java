/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tadsb.pi3.livrarianext.servicos;

import br.senac.tadsb.pi3.livrarianext.database.*;
import br.senac.tadsb.pi3.livrarianext.models.*;
import br.senac.tadsb.pi3.livrarianext.enums.ExceptionTypesEnum;
import br.senac.tadsb.pi3.livrarianext.exceptions.*;
import java.util.List;

/**
 *
 * @author roger
 */
public class ServicoUsuario extends Servico<Usuario> {
    final DaoUsuario dao;
    final DaoPerfil daoPerfil;
    
    public ServicoUsuario(DaoUsuario dao, DaoPerfil daoPerfil) throws UsuarioException {        
        super(dao);
        this.dao = dao;
        this.daoPerfil = daoPerfil;
    }
    
    public void incluir(String nome, String sobrenome, String username, String email, int perfilId)  throws UsuarioException {              
        try
        {
            Usuario novo = new Usuario(nome, sobrenome, username, email, obterPerfil(perfilId));
            super.incluir(novo);        
            enviarSenhaAoUsuario(novo);
        }
        catch(ServicoException se)
        {
            throw new UsuarioException(ExceptionTypesEnum.SPECIFIC_CRUD);
        }
    }
    
    public void alterar(int id, String nome, String sobrenome, Boolean ativo, int perfilId) throws UsuarioException {
        try
        {
            Perfil perfil = daoPerfil.obterPorId(perfilId);
            
            Usuario usuario = dao.obterPorId(id);
            usuario.setNome(nome);
            usuario.setSobreNome(sobrenome);
            usuario.setPerfil(perfil);
            usuario.setAtivo(ativo);
            
            super.alterar(usuario);
        }
        catch(DaoException de)
        {
            throw new UsuarioException(ExceptionTypesEnum.DATABASE);
        }
        catch(ServicoException se)
        {
            throw new UsuarioException(ExceptionTypesEnum.SPECIFIC_CRUD);
        }
    }    
    
    public void excluir(int id) throws UsuarioException {
        try
        {
            Usuario usuario = dao.obterPorId(id);
            super.excluir(usuario);
        }
        catch(DaoException de)
        {
            throw new UsuarioException(ExceptionTypesEnum.DATABASE);
        }
        catch(ServicoException se)
        {
            throw new UsuarioException(ExceptionTypesEnum.SPECIFIC_CRUD);
        }
    }   
    
    private Perfil obterPerfil(int id) throws UsuarioException {
        try
        {            
            return daoPerfil.obterPorId(id);
        }
        catch(DaoException sqlex)
        {
            sqlex.printStackTrace();
            throw new UsuarioException(ExceptionTypesEnum.SPECIFIC_SELECT);
        }
    }
    
    private void enviarSenhaAoUsuario(Usuario usuario){
        //Disparar email ao usuário com a senha gerada
        //usuario.getEmail();
        //usuario.getSenha()
    } 
    
    public List<Usuario> obterUsuarios(String nome, Boolean ativos, int perfil) throws UsuarioException  {
        try
        {
            return dao.obterUsuarios(nome, ativos, perfil);
        }
        catch(DaoException sqlex)
        {
            sqlex.printStackTrace();
            throw new UsuarioException(ExceptionTypesEnum.SPECIFIC_SELECT);
        }
    }
    
    public Usuario obterUsuarioPorId(int id) throws UsuarioException {
        try
        {
            return dao.obterPorId(id);
        }
        catch(DaoException sqlex)
        {
            sqlex.printStackTrace();
            throw new UsuarioException(ExceptionTypesEnum.SPECIFIC_SELECT);
        }
    }
    
    public List<Perfil> obterPerfis() throws UsuarioException {
        try
        {
            return daoPerfil.obterPerfis();
        }
        catch(DaoException sqlex)
        {
            sqlex.printStackTrace();
            throw new UsuarioException(ExceptionTypesEnum.SPECIFIC_SELECT);
        }
    }   
}
