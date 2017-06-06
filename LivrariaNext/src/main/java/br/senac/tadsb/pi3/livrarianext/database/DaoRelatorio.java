/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tadsb.pi3.livrarianext.database;

import br.senac.tadsb.pi3.livrarianext.exceptions.DaoException;
import br.senac.tadsb.pi3.livrarianext.models.ItemPedidoDto;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author roger
 */
public class DaoRelatorio extends Dao<ItemPedidoDto> {

    public DaoRelatorio(ConnectionUtils util) throws SQLException {
        super(util);
    }

    @Override
    public void incluir(ItemPedidoDto dominio) throws DaoException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void alterar(ItemPedidoDto dominio) throws DaoException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void excluir(ItemPedidoDto dominio) throws DaoException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ItemPedidoDto obterPorId(int id) throws DaoException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public List<ItemPedidoDto> obterRelatorio(Date inicio, Date fim, String loja, String usuario, String produto) throws DaoException {
        try
        {
            
            String query = 
                        "select " +
                        "p.ID as pedidoId, " +
                        "ip.ID as itemPedidoId, " +
                        "p.DATA_PEDIDO as pedidoData, " +
                        "l.NOME as lojaNome, " +
                        "c.NOME as clienteNome, " +
                        "u.NOME as usuarioNome, " +
                        "pd.NOMECOMUM as produtoNome, " +
                        "ip.QTD_PRODUTO as qtdProduto, " +
                        "ip.VAL_UNITARIO as valorUnitario " +
                        "from " +
                        "pedido p " +
                        "inner join item_pedido ip on (ip.ID_PEDIDO = p.ID) " +
                        "inner join loja l on (l.ID = p.ID_LOJA) " +
                        "inner join cliente c on (c.ID = p.ID_CLIENTE) " +
                        "inner join usuario u on (u.ID = p.ID_VENDEDOR) " +
                        "inner join produto pd on (pd.ID = ip.ID_PRODUTO) " +
                        "where " +
                        "p.DATA_PEDIDO between '?' and '?'";
            
            if (!loja.trim().isEmpty())
                query += " l.NOME LIKE '%" + loja + "%'";
            
            if (!usuario.trim().isEmpty())
                query += " u.NOME LIKE '%" + usuario + "%'";;
            
            if (!produto.trim().isEmpty())
                query += " pd.NOMECOMUM LIKE '%" + produto + "%'";;
            
            PreparedStatement stt = obterStatement(query);
            stt.setDate(1, new java.sql.Date(inicio.getTime()));
            stt.setDate(2, new java.sql.Date(fim.getTime()));            

            ResultSet rs = stt.executeQuery();

            List<ItemPedidoDto> dominios = new ArrayList<>();

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
    protected ItemPedidoDto obterDominio(ResultSet rs) throws DaoException {
        try
        {                        
            return new ItemPedidoDto(
                    rs.getInt("pedidoId"),
                    rs.getInt("itemPedidoId"),
                    rs.getDate("pedidoData"),                    
                    rs.getString("lojaNome"),                    
                    rs.getString("clienteNome"),
                    rs.getString("vendedorNome"),
                    rs.getString("produtoNome"),
                    rs.getDouble("qtdProduto"),
                    rs.getDouble("valorUnitario")
            );         
        }
        catch(SQLException sqlex)
        {
            sqlex.printStackTrace();
            throw new DaoException();
        }
    }
    
}
