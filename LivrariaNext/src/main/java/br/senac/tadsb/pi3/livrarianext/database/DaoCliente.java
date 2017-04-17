/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tadsb.pi3.livrarianext.database;

import br.senac.tadsb.pi3.livrarianext.models.Cliente;
import br.senac.tadsb.pi3.livrarianext.models.Usuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author roger
 */
public class DaoCliente extends Dao<Cliente>  {
    public DaoCliente() throws SQLException, Exception {
        super(new ConnectionUtils());
    }

    @Override
    protected void incluir(Cliente dominio) throws SQLException, Exception {
        PreparedStatement stt = obterStatement("insert into usuario (nome, sobrenome, sexo, cpf, rg, endereco, bairro, numero, email, telefone, ativo) values (?,?,?,?,?,?,?,?,?,?,?)");
        //stt.setInt(0, usuario.getId());
        stt.setString(1, dominio.getNome());
        stt.setString(2, dominio.getSobreNome());
        stt.setString(3, dominio.getSexo());
        stt.setString(4, dominio.getCpf());
        stt.setString(5, dominio.getRg());
        stt.setString(6, dominio.getEndereco());
        stt.setString(7, dominio.getBairro());
        stt.setString(8, dominio.getNumero());
        stt.setString(9, dominio.getEmail());
        stt.setString(10, dominio.getTelefone());        
        
        stt.setBoolean(11, dominio.getAtivo());
        
        stt.execute();
    }

    @Override
    protected void alterar(Cliente dominio) throws SQLException, Exception {
        PreparedStatement stt = obterStatement("update usuario set nome = ?, sobrenome = ?, sexo = ?,  cpf = ?, rg = ?, endereco = ?, bairro = ?, numero = ?, email = ?, telefone = ?, ativo = ? where id = ?");
        
        stt.setString(1, dominio.getNome());
        stt.setString(2, dominio.getSobreNome());
        stt.setString(3, dominio.getSexo());
        stt.setString(4, dominio.getCpf());
        stt.setString(5, dominio.getRg());
        stt.setString(6, dominio.getEndereco());
        stt.setString(7, dominio.getBairro());
        stt.setString(8, dominio.getNumero());
        stt.setString(9, dominio.getEmail());
        stt.setString(10, dominio.getTelefone());        
        stt.setBoolean(11, dominio.getAtivo());
        
        stt.setInt(12, dominio.getId());        
        
        stt.execute();
    }

    @Override
    protected void excluir(Cliente dominio) throws SQLException, Exception {
        PreparedStatement stt = obterStatement("update cliente set ativo = ? where id = ?");
        stt.setBoolean(0, false);        
        stt.setInt(1, dominio.getId());
        
        stt.execute();
    }

    @Override
    protected Cliente obterPorId(int id) throws SQLException, Exception {
        ResultSet rs = getList("select * from cliente where id = " + id);
        
        Cliente dominio = null;
        
        while (rs.next()) {
//             dominio = 
//                     new Cliente(
//                             rs.getInt("id"), 
//                             rs.getString("nome"), 
//                             rs.getString("sobrenome"), 
//                             rs.getBoolean("ativo")
//                     );     
        }
        
        return dominio;
    }
    
    
}
