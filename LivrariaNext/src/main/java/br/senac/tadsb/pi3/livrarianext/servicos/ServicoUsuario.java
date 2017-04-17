/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tadsb.pi3.livrarianext.servicos;

import br.senac.tadsb.pi3.livrarianext.models.Usuario;
import br.senac.tadsb.pi3.livrarianext.database.DaoUsuario;
import br.senac.tadsb.pi3.livrarianext.enums.ExceptionTypesEnum;
import br.senac.tadsb.pi3.livrarianext.exceptions.UsuarioException;
import br.senac.tadsb.pi3.livrarianext.models.Perfil;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author roger
 */
public class ServicoUsuario extends Servico<Usuario> {

    DaoUsuario dao;
    
    public ServicoUsuario() throws UsuarioException {
        try
        {
            dao = new DaoUsuario();
        }
        catch(SQLException sqlex)
        {
            System.out.println(sqlex.getMessage());
            throw new UsuarioException(sqlex.getMessage(), ExceptionTypesEnum.DISPLAY);
        }
        catch(Exception ex)
        {
            throw new UsuarioException("");
        }
    }
    
    public void incluir(String nome, String sobrenome)  throws UsuarioException {
        Usuario novo = new Usuario(nome, sobrenome, true);
        this.incluir(novo);
    }
    
    protected void incluir(Usuario usuario) throws UsuarioException {
        try
        {
            dao.incluir(usuario);
        }
        catch(SQLException sqlex)
        {
            sqlex.printStackTrace();
            throw new UsuarioException(ExceptionTypesEnum.SPECIFIC_CRUD);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            throw new UsuarioException(ExceptionTypesEnum.GENERAL);
        }
    }
    
    public void alterar(int id, String nome, String sobrenome, Boolean ativo) throws UsuarioException {
        try
        {
            Usuario usuario = dao.obterPorId(id);
            usuario.setNome(nome);
            usuario.setSobreNome(sobrenome);
            usuario.setAtivo(ativo);
            this.alterar(usuario);
        }
        catch(SQLException sqlex)
        {
            sqlex.printStackTrace();
            throw new UsuarioException(ExceptionTypesEnum.SPECIFIC_SELECT);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            throw new UsuarioException(ExceptionTypesEnum.GENERAL);
        }
    }
    
    protected void alterar(Usuario usuario) throws UsuarioException {
        try
        {
            dao.alterar(usuario);
        }
        catch(SQLException sqlex)
        {
            sqlex.printStackTrace();
            throw new UsuarioException(ExceptionTypesEnum.SPECIFIC_CRUD);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            throw new UsuarioException(ExceptionTypesEnum.GENERAL);
        }
    }
    
    public void excluir(int id) throws UsuarioException {
        try
        {
            Usuario usuario = dao.obterPorId(id);
            this.excluir(usuario);
        }
        catch(SQLException sqlex)
        {
            sqlex.printStackTrace();
            throw new UsuarioException(ExceptionTypesEnum.SPECIFIC_SELECT);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            throw new UsuarioException(ExceptionTypesEnum.GENERAL);
        }
    }
    
    protected void excluir(Usuario usuario) throws UsuarioException {
        try
        {
            dao.excluir(usuario);
        }
        catch(SQLException sqlex)
        {
            sqlex.printStackTrace();
            throw new UsuarioException(ExceptionTypesEnum.SPECIFIC_CRUD);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            throw new UsuarioException(ExceptionTypesEnum.GENERAL);
        }
    }    
    
    public List<Usuario> ObterUsuarios(String nome) throws UsuarioException  {
        try
        {
            return dao.obterUsuarios(nome);
        }
        catch(SQLException sqlex)
        {
            sqlex.printStackTrace();
            throw new UsuarioException(ExceptionTypesEnum.SPECIFIC_CRUD);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            throw new UsuarioException(ExceptionTypesEnum.GENERAL);
        }
    }
    
    public List<Perfil> ObterPerfis(){
        return new ArrayList<Perfil>();
    }   
}
