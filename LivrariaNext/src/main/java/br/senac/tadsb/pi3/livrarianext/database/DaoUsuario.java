/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tadsb.pi3.livrarianext.database;

import br.senac.tadsb.pi3.livrarianext.models.Perfil;
import br.senac.tadsb.pi3.livrarianext.models.Usuario;
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
    
    public DaoUsuario() throws SQLException, Exception {
        super(new ConnectionUtils());
    }    
    
    @Override
    public void incluir(Usuario usuario) throws SQLException, Exception {     
        PreparedStatement stt = obterStatement("insert into usuario (nome, sobrenome, ativo) values (?,?,?)");
        //stt.setInt(0, usuario.getId());
        stt.setString(1, usuario.getNome());
        stt.setString(2, usuario.getSobreNome());
        stt.setBoolean(3, usuario.getAtivo());
        
        stt.execute();
    }
    
    @Override
    public void alterar(Usuario usuario) throws SQLException, Exception {
        PreparedStatement stt = obterStatement("update usuario set nome = ?, sobrenome = ?, ativo = ? where id = ?");
        stt.setString(1, usuario.getNome());
        stt.setString(2, usuario.getSobreNome());
        stt.setBoolean(3, usuario.getAtivo());        
        stt.setInt(4, usuario.getId());
        
        stt.execute();
    }
    
    @Override
    public void excluir(Usuario usuario) throws SQLException, Exception {
        PreparedStatement stt = obterStatement("update usuario set ativo = ? where id = ?");
        stt.setBoolean(1, false);        
        stt.setInt(2, usuario.getId());
        
        stt.execute();
    }

    @Override
    public Usuario obterPorId(int id) throws SQLException {
        ResultSet rs = getList("select * from usuario where id = " + id);
        
        Usuario usuario = null;
        
        while (rs.next()) {
             usuario = 
                     new Usuario(
                             rs.getInt("id"), 
                             rs.getString("nome"), 
                             rs.getString("sobrenome"), 
                             rs.getBoolean("ativo")
                     );     
        }
        
        return usuario;
    }
    
    public List<Usuario> obterUsuarios(String nome, Boolean ativos) throws SQLException, Exception {
        
        String query = "select * from usuario u ";
        
        query += " where u.ativo = " + ativos;
        
        if (nome != null && !nome.isEmpty())
            query += " AND UPPER(u.nome) like ('%" + nome.toUpperCase() + "%')";                    
        
        System.out.println(query);
        
        ResultSet rs = getList(query);
        
        List<Usuario> usuarios = new ArrayList<>();        
        
        while (rs.next()) {
             usuarios.add(new Usuario(
                             rs.getInt("id"), 
                             rs.getString("nome"), 
                             rs.getString("sobrenome"), 
                             rs.getBoolean("ativo")
                     ));
        }
        
        return usuarios;
    }

    public List<Perfil> obterPerfis() throws SQLException, Exception {        
        String query = "select * from perfil ";
                
        ResultSet rs = getList(query);
        
        List<Perfil> lista = new ArrayList<>();        
        
        while (rs.next()) {
             lista.add(new Perfil(
                             rs.getInt("id"), 
                             rs.getString("nome"), 
                             rs.getBoolean("ativo"), 
                             null
                     ));
        }
        
        return lista;
    }
}
