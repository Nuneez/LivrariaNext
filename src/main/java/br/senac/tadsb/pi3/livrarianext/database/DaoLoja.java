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
            PreparedStatement stt = obterStatementRetornaId("insert into loja (nome, filial, cnpj, razao_social, inscricao_estadual, endereco, numero, cidade, estado, telefone, email, ativo) values (?,?,?,?,?,?,?,?,?,?,?,?)");
            stt.setString(1, dominio.getNome());
            stt.setBoolean(2, dominio.getEhFilial());
            stt.setString(3, dominio.getCnpj());
            stt.setString(4, dominio.getRazaoSocial());
            stt.setString(5, dominio.getInscricaoEstadual());
            stt.setString(6, dominio.getEndereco());
            stt.setString(7, dominio.getNumero());
            stt.setString(8, dominio.getCidade());
            stt.setString(9, dominio.getEstado());
            stt.setString(10, dominio.getTelefone());
            stt.setString(11, dominio.getEmail());
            stt.setBoolean(12, true);

            stt.execute();
            
            ResultSet rs = stt.getGeneratedKeys();
            
            rs.next();                        
            int id = rs.getInt(1);            
            incluirEstoque(id);
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
    
    private void incluirEstoque(int lojaId) throws DaoException { 
        try
        {
            PreparedStatement stt = obterStatement("insert into estoque (id_loja, ativo) values (?,?)");
            stt.setInt(1, lojaId);
            stt.setBoolean(2, true);

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
            PreparedStatement stt = obterStatement("update loja set nome = ?, filial = ?, cnpj = ?,  razao_social = ?, inscricao_estadual = ?, endereco = ?, numero = ?, cidade = ?, estado = ?, telefone = ?, email = ?, ativo = ? where id = ?");

            stt.setString(1, dominio.getNome());
            stt.setBoolean(2, dominio.getEhFilial());
            stt.setString(3, dominio.getCnpj());
            stt.setString(4, dominio.getRazaoSocial());
            stt.setString(5, dominio.getInscricaoEstadual());
            stt.setString(6, dominio.getEndereco());
            stt.setString(7, dominio.getNumero());
            stt.setString(8, dominio.getCidade());
            stt.setString(9, dominio.getEstado());
            stt.setString(10, dominio.getTelefone());
            stt.setString(11, dominio.getEmail());
            stt.setBoolean(12, dominio.getAtivo());

            stt.setInt(13, dominio.getId());

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

    public List<Loja> obterLojas(String nome, String cnpj) throws DaoException {
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
                    rs.getBoolean("filial"),
                    rs.getString("cnpj"),
                    rs.getString("razao_social"),
                    rs.getString("telefone"),
                    rs.getString("endereco"),
                    rs.getString("numero"),
                    rs.getString("cidade"),
                    rs.getString("estado"),
                    rs.getString("email"),
                    rs.getString("inscricao_estadual"),
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
