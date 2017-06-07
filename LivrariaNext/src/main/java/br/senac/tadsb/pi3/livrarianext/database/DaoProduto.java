/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tadsb.pi3.livrarianext.database;

import br.senac.tadsb.pi3.livrarianext.exceptions.DaoException;
import br.senac.tadsb.pi3.livrarianext.models.Produto;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author roger
 */
public class DaoProduto extends Dao<Produto> {

    final String queryPadrao = "select * from produto p ";
    
    public DaoProduto() throws SQLException, Exception {
        super(new ConnectionUtils());
    }
    
    public DaoProduto(ConnectionUtils util) throws SQLException {
        super(util);
    }

    @Override
    public void incluir(Produto dominio) throws DaoException {
        try
        {
            PreparedStatement stt = obterStatement("insert into produto (NOMECOMUM, DESCRICAO, CUSTO, PRECOMEDIO, EAN, ATIVO) values (?,?,?,?,?,?)");
            stt.setString(1, dominio.getNome());
            stt.setString(2, dominio.getDescricao());
            stt.setDouble(3, dominio.getCusto());
            stt.setDouble(4, dominio.getPreco());
            stt.setString(5, dominio.getEan());
            stt.setBoolean(6, true);

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
    public void alterar(Produto dominio) throws DaoException {
        try
        {
            PreparedStatement stt = obterStatement("update produto set NOMECOMUM = ?, DESCRICAO = ?, CUSTO = ?,  precomedio = ?, EAN = ?, ativo = ? where id = ?");

            stt.setString(1, dominio.getNome());
            stt.setString(2, dominio.getDescricao());
            stt.setDouble(3, dominio.getCusto());
            stt.setDouble(4, dominio.getPreco());
            stt.setString(5, dominio.getEan());
            stt.setBoolean(6, dominio.getAtivo());

            stt.setInt(7, dominio.getId());

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
    public void excluir(Produto dominio) throws DaoException {
        try
        {
            PreparedStatement stt = obterStatement("update produto set ativo = ? where id = ?");
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
    public Produto obterPorId(int id) throws DaoException {
        try
        {
            ResultSet rs = getList(queryPadrao + " where id = " + id);

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
    
    public List<Produto> obterProdutos(String nome, String ean) throws DaoException {
        try
        {
            String query = queryPadrao;

            if (nome != null && !nome.isEmpty())
                query = tratarQuery(query) + " UPPER(p.NOMECOMUM) like ('%" + nome.toUpperCase() + "%')";

            if (ean != null && !ean.isEmpty())
                query = tratarQuery(query) + " p.ean like ('%" + ean + "%')";

            ResultSet rs = getList(query);

            List<Produto> dominios = new ArrayList<>();

            while (rs.next())
                dominios.add(obterDominio(rs));

            return dominios;
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
    protected Produto obterDominio(ResultSet rs) throws DaoException {
        try
        {                        
            return new Produto(
                    rs.getInt("id"),
                    rs.getInt("QUANTIDADE"),
                    rs.getString("NOMECOMUM"),
                    rs.getString("DESCRICAO"),
                    rs.getDouble("CUSTO"),
                    rs.getDouble("PRECOMEDIO"),
                    rs.getString("EAN"),
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
