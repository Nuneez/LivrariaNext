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
        
        PreparedStatement stt = obterStatement("insert into usuario (id, nome, sobrenome, ativo) values (?,?,?,?)");
        stt.setInt(0, usuario.getId());
        stt.setString(1, usuario.getNome());
        stt.setString(2, usuario.getSobreNome());
        stt.setBoolean(3, usuario.getAtivo());
        
        stt.execute();
    }
    
    @Override
    public void alterar(Usuario usuario) throws SQLException, Exception {
        PreparedStatement stt = obterStatement("update usuario set nome = ?, sobrenome = ?, ativo = ? where id = ?");
        stt.setString(0, usuario.getNome());
        stt.setString(1, usuario.getSobreNome());
        stt.setBoolean(2, usuario.getAtivo());        
        stt.setInt(3, usuario.getId());
        
        stt.execute();
    }
    
    @Override
    public void excluir(Usuario usuario) throws SQLException, Exception {
        PreparedStatement stt = obterStatement("update usuario set ativo = ? where id = ?");
        stt.setBoolean(0, false);        
        stt.setInt(1, usuario.getId());
        
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

    public List<Perfil> obterPerfis(){
        return new ArrayList<Perfil>();
    }
}
