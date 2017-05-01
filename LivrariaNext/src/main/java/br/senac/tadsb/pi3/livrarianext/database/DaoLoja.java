/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tadsb.pi3.livrarianext.database;

import br.senac.tadsb.pi3.livrarianext.exceptions.DaoException;
import br.senac.tadsb.pi3.livrarianext.models.Loja;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nuneez
 */
public class DaoLoja extends Dao<Loja> {

    final String queryPadrao = "select * from loja l ";
    
    public DaoLoja() throws SQLException, Exception {
        super(new ConnectionUtils());
    }
    
    public DaoLoja(ConnectionUtils util) throws SQLException, Exception {
        super(util);
    }

    @Override
    public void incluir(Loja dominio) throws DaoException {
        try
        {
            PreparedStatement stt = obterStatement("insert into loja (nome, ehfilial, cnpj, razaosocial, ie, endereco, numero, cidade, estado, telefone, ativo) values (?,?,?,?,?,?,?,?,?,?)");
            stt.setString(1, dominio.getNome());
            stt.setBoolean(2, dominio.getEhFilial());
            stt.setString(3, dominio.getCnpj());
            stt.setString(4, dominio.getRazaoSocial());
            stt.setString(5, dominio.getEndereco());
            stt.setString(6, dominio.getNumero());
            stt.setString(7, dominio.getCidade());
            stt.setString(8, dominio.getEstado());
            stt.setString(9, dominio.getTelefone());

            stt.setBoolean(10, dominio.getAtivo());

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
    public void alterar(Loja dominio) throws DaoException {
        try
        {
            PreparedStatement stt = obterStatement("update loja set nome = ?, ehFilial = ?, cnpj = ?,  razaoSocial = ?,  endereco = ?, numero = ?, cidade = ?, estado = ?, telefone = ?, ativo = ? where id = ?");

            stt.setString(1, dominio.getNome());
            stt.setBoolean(2, dominio.getEhFilial());
            stt.setString(3, dominio.getCnpj());
            stt.setString(4, dominio.getRazaoSocial());
            stt.setString(5, dominio.getEndereco());
            stt.setString(6, dominio.getNumero());
            stt.setString(7, dominio.getCidade());
            stt.setString(8, dominio.getEstado());
            stt.setString(9, dominio.getTelefone());
            stt.setBoolean(10, dominio.getAtivo());

            stt.setInt(11, dominio.getId());

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
    public void excluir(Loja dominio) throws DaoException {
        try
        {
            PreparedStatement stt = obterStatement("update loja set ativo = ? where id = ?");
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
    public Loja obterPorId(int id) throws DaoException {
        try
        {
            ResultSet rs = getList("select * from loja where id = " + id);

            while (rs.next())
                return obterDominio(rs);

            return null;
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

    public List<Loja> obterLojas(String nome, String cnpj) throws SQLException, Exception {
        try
        {
            String query = queryPadrao;

            if (nome != null && !nome.isEmpty())
                query = tratarQuery(query) + " UPPER(c.nome) like ('%" + nome.toUpperCase() + "%')";

            if (cnpj != null && !cnpj.isEmpty())
                query = tratarQuery(query) + " c.cnpj like ('%" + cnpj + "%')";

            ResultSet rs = getList(query);

            List<Loja> lojas = new ArrayList<>();

            while (rs.next())
                lojas.add(obterDominio(rs));

            return lojas;
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
    protected Loja obterDominio(ResultSet rs) throws DaoException {
        try
        {                        
            return new Loja(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getBoolean("ehFilial"),
                    rs.getString("cnpj"),
                    rs.getString("razaoSocial"),
                    rs.getString("endereco"),
                    rs.getString("numero"),
                    rs.getString("cidade"),
                    rs.getString("estado"),
                    rs.getString("telefone")
            );         
        }
        catch(SQLException sqlex)
        {
            sqlex.printStackTrace();
            throw new DaoException();
        }     
    }    
}
