/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tadsb.pi3.livrarianext.database;

import br.senac.tadsb.pi3.livrarianext.models.Perfil;
import br.senac.tadsb.pi3.livrarianext.models.Usuario;
import br.senac.tadsb.pi3.livrarianext.exceptions.DaoException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author roger
 */
public class DaoUsuario extends Dao<Usuario> {
    
    final String queryPadrao = "select u.id, u.nome, u.sobrenome, u.username, u.password, u.email, u.ativo, p.id p_id, p.nome p_nome, p.ativo p_ativo from usuario u inner join perfil p on (p.id = u.perfil_id) ";
    
    public DaoUsuario() throws SQLException, Exception {
        super(new ConnectionUtils());
    }    
    
    public DaoUsuario(ConnectionUtils util) throws SQLException, Exception {
        super(util);
    }
    
    @Override
    public void incluir(Usuario usuario) throws DaoException {     
        try
        {
            usuario.gerarPassword();
        
            PreparedStatement stt = obterStatement("insert into usuario (nome, sobrenome, username, password, email, perfil_id, ativo) values (?,?,?,?,?,?,?)");
            stt.setString(1, usuario.getNome());
            stt.setString(2, usuario.getSobrenome());
            stt.setString(3, usuario.getUsername());
            stt.setString(4, usuario.getPassword());
            stt.setString(5, usuario.getEmail());
            stt.setInt(6, usuario.getPerfil().getId());
            stt.setBoolean(7, usuario.getAtivo());

            stt.execute();
        }
        catch(SQLException sqlex)
        {
            sqlex.printStackTrace();
            throw new DaoException();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            throw new DaoException();
        }
    }
    
    @Override
    public void alterar(Usuario usuario) throws DaoException {        
        try
        {
            PreparedStatement stt = obterStatement("update usuario set nome = ?, sobrenome = ?, username = ?, email = ?, perfil_id = ?, ativo = ? where id = ?");
            stt.setString(1, usuario.getNome());
            stt.setString(2, usuario.getSobrenome());
            stt.setString(3, usuario.getUsername());
            stt.setString(4, usuario.getEmail());
            stt.setInt(5, usuario.getPerfil().getId());
            stt.setBoolean(6, usuario.getAtivo());
            stt.setInt(7, usuario.getId());

            stt.execute();
        }
        catch(SQLException sqlex)
        {
            sqlex.printStackTrace();
            throw new DaoException();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            throw new DaoException();
        }
    }
    
    @Override
    public void excluir(Usuario usuario) throws DaoException {
        try
        {        
            PreparedStatement stt = obterStatement("update usuario set ativo = ? where id = ?");
            stt.setBoolean(1, false);        
            stt.setInt(2, usuario.getId());

            stt.execute();
        }
        catch(SQLException sqlex)
        {
            sqlex.printStackTrace();
            throw new DaoException();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            throw new DaoException();
        }
    }

    @Override
    public Usuario obterPorId(int id) throws DaoException {       
        try
        {            
            ResultSet rs = getList(queryPadrao + " where u.id = " + id);

            while (rs.next())
                return obterDominio(rs);

            return null;        
        }
        catch(SQLException sqlex)
        {
            sqlex.printStackTrace();
            throw new DaoException();
        }
    }
    
    public List<Usuario> obterUsuarios(String nome, Boolean ativos, int perfil) throws DaoException {
        try
        {
            String query = queryPadrao;

            query += " where u.ativo = " + ativos;

            if (nome != null && !nome.isEmpty())
                query += " AND UPPER(u.nome) like ('%" + nome.toUpperCase() + "%')";                    

            if (perfil != 0)
                query += " AND p.id = " + perfil;                    
            
            query += " order by u.nome ";
            
            ResultSet rs = getList(query);

            List<Usuario> usuarios = new ArrayList<>();        

            while (rs.next())            
                usuarios.add(obterDominio(rs));

            return usuarios;        
        }
        catch(SQLException sqlex)
        {
            sqlex.printStackTrace();
            throw new DaoException();
        }     
    }
    
    @Override
    protected Usuario obterDominio(ResultSet rs) throws DaoException {
        try
        {               
            Perfil perfil = new Perfil(rs.getInt("p_id"), rs.getString("p_nome"), rs.getBoolean("p_ativo"), null);
            
            return new Usuario(
                    rs.getInt("id"), 
                    rs.getString("nome"), 
                    rs.getString("sobrenome"), 
                    rs.getString("username"), 
                    rs.getString("password"), 
                    rs.getString("email"), 
                    rs.getBoolean("ativo"),
                    perfil
             );         
        }
        catch(SQLException sqlex)
        {
            sqlex.printStackTrace();
            throw new DaoException();
        }     
    }
}
