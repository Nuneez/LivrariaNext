/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tadsb.pi3.livrarianext.database;

import br.senac.tadsb.pi3.livrarianext.exceptions.DaoException;
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
    
    final String queryPadrao = "select * from cliente ";
    
    public DaoCliente() throws SQLException, Exception {
        super(new ConnectionUtils());
    }
    
    public DaoCliente(ConnectionUtils util) throws SQLException, Exception {
        super(util);
    }

    @Override
    public void incluir(Cliente dominio) throws DaoException {        
        try
        {
            PreparedStatement stt = obterStatement("insert into cliente (nome, sobrenome, sexo, cpf, rg, endereco, bairro, numero, email, telefone, ativo) values (?,?,?,?,?,?,?,?,?,?,?)");
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
    public void alterar(Cliente dominio) throws DaoException {
        try
        {        
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
    public void excluir(Cliente dominio) throws DaoException {
        try
        {        
            PreparedStatement stt = obterStatement("update cliente set ativo = ? where id = ?");
            stt.setBoolean(1, false);        
            stt.setInt(2, dominio.getId());

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
    public Cliente obterPorId(int id) throws DaoException {
        try
        {
            ResultSet rs = getList(queryPadrao + " where id = " + id);
                
            while (rs.next())
                 return obterDominio(rs);     

            return null;
        }
        catch(SQLException sqlex)
        {
            throw new DaoException();
        }
    }

    public Cliente obterPorCPF(String cpf) throws DaoException {
        try
        {
            ResultSet rs = getList(queryPadrao + " where cpf LIKE ('"+ cpf.trim() +"')");
                
            while (rs.next())
                 return obterDominio(rs);     

            return null;
        }
        catch(SQLException sqlex)
        {
            throw new DaoException();
        }
    }
    public List<Cliente> obterClientes(String nome, String cpf) throws DaoException {
        try
        {
            String query = "select * from cliente c ";
        
            if (nome != null && !nome.isEmpty())
                query = tratarQuery(query) + " UPPER(c.nome) like ('%" + nome.toUpperCase() + "%')";

            if (cpf != null && !cpf.isEmpty())
                query = tratarQuery(query) + " c.cpf like ('%" + cpf + "%')";

            ResultSet rs = getList(query);

            List<Cliente> clientes = new ArrayList<>();        

            while (rs.next())
                 clientes.add(obterDominio(rs));

            return clientes;
        }
        catch(SQLException sqlex)
        {
            throw new DaoException();
        }
    }    
    
    @Override
    protected Cliente obterDominio(ResultSet rs) throws DaoException {
        try
        {                        
            return new Cliente(
                    rs.getInt("id"), 
                    rs.getString("nome"), 
                    rs.getString("sobrenome"), 
                    rs.getString("cpf"), 
                    rs.getString("rg"), 
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
        catch(SQLException sqlex)
        {
            sqlex.printStackTrace();
            throw new DaoException();
        }     
    }
}
