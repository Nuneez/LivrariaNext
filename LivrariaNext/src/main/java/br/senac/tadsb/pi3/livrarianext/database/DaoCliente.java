/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tadsb.pi3.livrarianext.database;

import br.senac.tadsb.pi3.livrarianext.models.Cliente;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author roger
 */
public class DaoCliente extends Dao<Cliente>  {
    public DaoCliente() throws SQLException, Exception {
        super(new ConnectionUtils());
    }

    @Override
    public void incluir(Cliente dominio) throws SQLException, Exception {
        PreparedStatement stt = obterStatement("insert into cliente (nome, sobrenome, sexo, cpf, rg, endereco, bairro, numero, email, telefone, ativo) values (?,?,?,?,?,?,?,?,?,?,?)");
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
    public void alterar(Cliente dominio) throws SQLException, Exception {
        PreparedStatement stt = obterStatement("update cliente set nome = ?, sobrenome = ?, sexo = ?,  cpf = ?, rg = ?, endereco = ?, bairro = ?, numero = ?, email = ?, telefone = ?, ativo = ? where id = ?");
        
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
    public void excluir(Cliente dominio) throws SQLException, Exception {
        PreparedStatement stt = obterStatement("update cliente set ativo = ? where id = ?");
        stt.setBoolean(1, false);        
        stt.setInt(2, dominio.getId());
        
        stt.execute();
    }

    @Override
    public Cliente obterPorId(int id) throws SQLException, Exception {
        ResultSet rs = getList("select * from cliente where id = " + id);
        
        Cliente dominio = null;
        
        while (rs.next()) {
             dominio = 
                     new Cliente(
                             rs.getInt("id"), 
                             rs.getString("nome"), 
                             rs.getString("sobrenome"), 
                             rs.getString("cpf"), 
                             null, 
                             rs.getString("sexo"), 
                             rs.getString("email"), 
                             rs.getString("telefone"), 
                             rs.getString("endereco"), 
                             rs.getString("numero"), 
                             rs.getString("bairro"), 
                             null, 
                             rs.getBoolean("ativo")
                     );     
        }
        
        return dominio;
    }

    public List<Cliente> obterClientes(String nome, String cpf) throws SQLException, Exception {
        String query = "select * from cliente c ";
        
        if (nome != null && !nome.isEmpty())
                query = tratarQuery(query) + " UPPER(c.nome) like ('%" + nome.toUpperCase() + "%')";

        if (cpf != null && !cpf.isEmpty())
            query = tratarQuery(query) + " c.cpf like ('%" + cpf + "%')";
            
        ResultSet rs = getList(query);
        
        List<Cliente> clientes = new ArrayList<>();        
        
        while (rs.next()) {
             clientes.add(new Cliente(
                             rs.getInt("id"), 
                             rs.getString("nome"), 
                             rs.getString("sobrenome"), 
                             rs.getString("cpf"), 
                             null, 
                             rs.getString("sexo"), 
                             rs.getString("email"), 
                             rs.getString("telefone"), 
                             rs.getString("endereco"), 
                             rs.getString("numero"), 
                             rs.getString("bairro"), 
                             null, 
                             rs.getBoolean("ativo")
                     ));
        }
        
        return clientes;
    }    
}
