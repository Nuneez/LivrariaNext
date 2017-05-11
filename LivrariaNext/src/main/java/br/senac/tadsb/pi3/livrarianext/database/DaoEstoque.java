/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tadsb.pi3.livrarianext.database;

import br.senac.tadsb.pi3.livrarianext.exceptions.DaoException;
import br.senac.tadsb.pi3.livrarianext.models.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author roger
 */
public class DaoEstoque extends Dao<Estoque> {

    final String queryPadrao = "select "
            + "e.*, l.* from estoque e "
            + "inner join loja l on (l.id == e.id_loja) ";
    
    public DaoEstoque() throws SQLException, Exception {
        super(new ConnectionUtils());
    }
    
    public DaoEstoque(ConnectionUtils util) throws SQLException {
        super(util);
    }

    @Override
    public void incluir(Estoque dominio) throws DaoException {
        try
        {
            PreparedStatement stt = obterStatement("insert into estoque (ID_LOJA, ATIVO) values (?,?)");
            stt.setInt(1, dominio.getLoja().getId());
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
    public void alterar(Estoque dominio) throws DaoException {
        throw new DaoException("Entidade Estoque não pode ser atualizada.");
    }     

    @Override
    public void excluir(Estoque dominio) throws DaoException {
        throw new DaoException("Entidade Estoque não pode ser excluida.");
    }

    @Override
    public Estoque obterPorId(int id) throws DaoException {
        try
        {
            ResultSet rs = getList(queryPadrao + " and id = " + id);

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
    
    public Estoque obterPorLoja(int lojaId) throws DaoException {
        try
        {
            ResultSet rs = getList(queryPadrao + " and l.id = " + lojaId);

            while (rs.next())
            {
                Estoque dominio = obterDominio(rs);                
                dominio.setProdutos(obterProdutos(dominio));                
                return dominio;
            }

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
    
    private List<EstoqueProduto> obterProdutos(Estoque estoque) throws DaoException {
        try
        {            
            String query = 
                    "select"
                    + "ep.id, "
                    + "ep.saldo, "
                    + "p.id as id_produto, "
                    + "p.nome,"
                    + "p.descricao, "
                    + "p.preco, "
                    + "p.ean "
                    + "from "
                    + "left outer join estoque_produto ep on (ep.id_estoque = e.id) "
                    + "left outer join produto p on (p.id = ep.id_produto) "
                    + "where"
                    + "l.id = " + estoque.getLoja().getId();

            ResultSet rs = getList(query);

            List<EstoqueProduto> produtos = new ArrayList<>();

            while (rs.next())
                produtos.add(new EstoqueProduto(
                                rs.getInt("id"),
                                estoque,
                                new Produto(
                                        rs.getInt("id_produto"), 
                                        rs.getString("nome"), 
                                        rs.getString("descricao"), 
                                        0f, 
                                        rs.getDouble("preco"), 
                                        rs.getString("ean"), 
                                        true),
                                rs.getDouble("saldo")
                        ));

            return produtos;
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
    protected Estoque obterDominio(ResultSet rs) throws DaoException {
        try
        {            
            return new Estoque(
                    rs.getInt("id"),
                    new Loja(
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
                    ),
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
