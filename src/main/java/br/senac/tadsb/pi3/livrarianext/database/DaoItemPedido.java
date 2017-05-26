/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tadsb.pi3.livrarianext.database;

import br.senac.tadsb.pi3.livrarianext.models.Perfil;
import br.senac.tadsb.pi3.livrarianext.models.Usuario;
import br.senac.tadsb.pi3.livrarianext.exceptions.DaoException;
import br.senac.tadsb.pi3.livrarianext.models.Cliente;
import br.senac.tadsb.pi3.livrarianext.models.ItemPedido;
import br.senac.tadsb.pi3.livrarianext.models.Pedido;
import br.senac.tadsb.pi3.livrarianext.models.Produto;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Thiago
 */
public class DaoItemPedido extends Dao<ItemPedido> {
    private String queryPadrao = "SELECT * FROM item_pedido ";
    
    public DaoItemPedido() throws SQLException {
        super(new ConnectionUtils());
    }    
    
    public DaoItemPedido(ConnectionUtils util) throws SQLException, Exception {
        super(util);
    }
    
    @Override
    public void incluir(ItemPedido itemPedido) throws DaoException {     
        try {                   
            PreparedStatement stt = obterStatement("INSERT INTO item_pedido (id_pedido, id_produto, qtd_produto, val_unitario) VALUES (?,?,?,?)");
            stt.setInt(1, itemPedido.getIdVenda());
            stt.setInt(2, itemPedido.getIdProduto());
            stt.setInt(3, itemPedido.getQuantidade());
            stt.setDouble(4, itemPedido.getValor());
            stt.execute();
        } catch (Exception ex) {
            Logger.getLogger(DaoItemPedido.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void alterar(ItemPedido itemPedido) throws DaoException {        
        try
        {
            PreparedStatement stt = obterStatement("UPDATE item_pedido SET id_pedido = ?, id_produto = ?, qtn_produto = ?, val_unitario = ? where id = ?");
            stt.setInt(1, itemPedido.getIdVenda());
            stt.setInt(2, itemPedido.getIdProduto());
            stt.setInt(3, itemPedido.getQuantidade());
            stt.setDouble(4, itemPedido.getValor());
            stt.setInt(5, itemPedido.getId());

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
    
    public void excluir(ItemPedido itemPedido) throws DaoException {
        try
        {        
            PreparedStatement stt = obterStatement("DELETE FROM item_pedido WHERE id = ?");
            stt.setInt(1, itemPedido.getId());

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
    public ItemPedido obterPorId(int id) throws DaoException {       
        try
        {            
            ResultSet rs = getList(queryPadrao + " where id = " + id);

            while (rs.next())
                return extrairItemPedido(rs);

            return null;        
        }
        catch(SQLException sqlex)
        {
            sqlex.printStackTrace();
            throw new DaoException();
        } catch (Exception ex) {
            Logger.getLogger(DaoItemPedido.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public List<ItemPedido> obterPorPedido(int id) throws DaoException {
        List<ItemPedido> items = new ArrayList<ItemPedido>();
        try {
            ResultSet rs = getList(queryPadrao + " WHERE id_pedido = " + id);

            while (rs.next())
                items.add(extrairItemPedido(rs));
        } catch(SQLException sqlex) {
            sqlex.printStackTrace();
            throw new DaoException();
        } catch (Exception ex) {
            Logger.getLogger(DaoItemPedido.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return items;
    }
    
    public List<ItemPedido> obterPorProduto(int id) throws DaoException {
        List<ItemPedido> items = new ArrayList<ItemPedido>();
        try {
            ResultSet rs = getList(queryPadrao + " WHERE id_produto = " + id);

            while (rs.next())
                items.add(extrairItemPedido(rs));
        } catch(SQLException sqlex) {
            sqlex.printStackTrace();
            throw new DaoException();
        } catch (Exception ex) {
            Logger.getLogger(DaoItemPedido.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return items;
    }
    
    private ItemPedido extrairItemPedido(ResultSet rs) throws DaoException, Exception {
        try {
            final int id = rs.getInt("id");
            final int idPedido = rs.getInt("id_pedido");
            final int idProduto = rs.getInt("id_produto");
            final int qtdProduto = rs.getInt("qtn_produto");
            final double val_unitario = rs.getDouble("val_unitario");
            return new ItemPedido(id, idPedido, qtdProduto, idProduto, val_unitario);
        }  catch(SQLException sqlex) {
            sqlex.printStackTrace();
            throw new DaoException();
        } 
    }

    @Override
    protected ItemPedido obterDominio(ResultSet rs) throws DaoException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
