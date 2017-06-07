/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tadsb.pi3.livrarianext.database;

import br.senac.tadsb.pi3.livrarianext.exceptions.DaoException;
import br.senac.tadsb.pi3.livrarianext.models.Cliente;
import br.senac.tadsb.pi3.livrarianext.models.Pedido;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Thiago
 */
public class DaoPedido extends Dao<Pedido> {
    private String queryPadrao = "SELECT * FROM pedido ";
    
    public DaoPedido() throws SQLException {
        super(new ConnectionUtils());
    }    
    
    public DaoPedido(ConnectionUtils util) throws SQLException, Exception {
        super(util);
    }
    
    @Override
    public void incluir(Pedido pedido) throws DaoException {     
        try {                   
            if (pedido.getCliente() == null) {
            PreparedStatement stt = obterStatement("INSERT INTO pedido (id_loja, id_vendedor, id_cliente) VALUES (?,?, 0)");
                stt.setInt(1, pedido.getLoja().getId());
                stt.setInt(2, pedido.getVendedor().getId());
                stt.execute();   
            } else {
                PreparedStatement stt = obterStatement("INSERT INTO pedido (id_loja, id_vendedor, id_cliente) VALUES (?,?,?)");
                stt.setInt(1, pedido.getLoja().getId());
                stt.setInt(2, pedido.getVendedor().getId());
                stt.setInt(3, pedido.getCliente().getId());
                stt.execute();
            }
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
    public void alterar(Pedido pedido) throws DaoException {        
        try
        {
            PreparedStatement stt = obterStatement("UPDATE pedido SET id_loja = ?, id_vendedor = ?, id_cliente = ? where id = ?");
            stt.setInt(1, pedido.getLoja().getId());
            stt.setInt(2, pedido.getVendedor().getId());
            stt.setInt(3, pedido.getCliente().getId());            
            stt.setInt(4, pedido.getId());

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
    public void excluir(Pedido pedido) throws DaoException {
        try
        {        
            PreparedStatement stt = obterStatement("DELETE FROM pedido WHERE id = ?");
            stt.setInt(1, pedido.getId());

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
    public Pedido obterPorId(int id) throws DaoException {       
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
    }
    
    public List<Pedido> listarPedidosPorCliente(Cliente cliente) throws DaoException, Exception {
        try
        {
            String query = queryPadrao;

            query += " WHERE cliente_id = " + cliente.getId();
            
            ResultSet rs = getList(query);

            List<Pedido> pedidos = new ArrayList<Pedido>();        

            while (rs.next()) {
                pedidos.add(this.extrairPedido(rs));
            }
                

            return pedidos;        
        }
        catch(SQLException sqlex)
        {
            sqlex.printStackTrace();
            throw new DaoException();
        }     
    }
    
    private Pedido extrairPedido(ResultSet rs) throws DaoException, Exception {
        try {
            final int idPedido = rs.getInt("id");
            final int idCliente = rs.getInt("id_cliente");
            final int idVendedor = rs.getInt("id_vendedor");
            final int idLoja = rs.getInt("id_loja");
            final String data = rs.getString("data_pedido");
            return new Pedido(idPedido, idCliente, idVendedor, idLoja, data);
        }  catch(SQLException sqlex) {
            sqlex.printStackTrace();
            throw new DaoException();
        } 
    }
    
    public Pedido getLastPedido() throws DaoException, Exception {
        // SELECT * FROM ROOT.PEDIDO ORDER BY id DESC FETCH FIRST 1 ROWS ONLY;
        String query = "SELECT * FROM PEDIDO ORDER BY id DESC FETCH FIRST 1 ROWS ONLY";
        try
        {            
            ResultSet rs = getList(query);

            while (rs.next())
                return this.extrairPedido(rs);

            return null;        
        }
        catch(SQLException sqlex)
        {
            sqlex.printStackTrace();
            throw new DaoException();
        }
    }

    @Override
    protected Pedido obterDominio(ResultSet rs) throws DaoException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
