/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tadsb.pi3.livrarianext.models;

import br.senac.tadsb.pi3.livrarianext.database.DaoCliente;
import br.senac.tadsb.pi3.livrarianext.database.DaoLoja;
import br.senac.tadsb.pi3.livrarianext.database.DaoUsuario;
import br.senac.tadsb.pi3.livrarianext.enums.ExceptionTypesEnum;
import br.senac.tadsb.pi3.livrarianext.exceptions.DaoException;
import br.senac.tadsb.pi3.livrarianext.exceptions.ItemPedidoException;
import br.senac.tadsb.pi3.livrarianext.exceptions.ServicoException;
import java.util.List;

/**
 *
 * @author Thiago
 */
public class Pedido {
    private int id;
    private Cliente cliente;
    private Usuario vendedor;
    private Loja loja;
    private float total;
    private List<ItemPedido> itensPedidos;
    private String dataPedido;

    public Pedido(Cliente cliente, Usuario vendedor, Loja loja, List<ItemPedido> itensPedidos) {
        this.cliente = cliente;
        this.vendedor = vendedor;
        this.loja = loja;
        this.total = total;
        this.itensPedidos = itensPedidos;
    }

    public Pedido(int id, Cliente cliente, Usuario vendedor, Loja loja, String dataPedido) {
        this.id = id;
        this.cliente = cliente;
        this.vendedor = vendedor;
        this.loja = loja;
        this.dataPedido = dataPedido;
    }
    
    public Pedido(int id, int cliente, int vendedor, int loja, String dataPedido) throws Exception {
        this.id = id;
        this.dataPedido = dataPedido;

        try {
            DaoCliente dc = new DaoCliente();
            DaoUsuario du = new DaoUsuario();
            DaoLoja dl = new DaoLoja();
            this.cliente = dc.obterPorId(cliente);
            this.vendedor = du.obterPorId(vendedor);
            this.loja = dl.obterPorId(loja) ;
        } catch(DaoException de) {
            throw new ItemPedidoException(ExceptionTypesEnum.DATABASE);
        } catch(ServicoException se) {
            throw new ItemPedidoException(ExceptionTypesEnum.SPECIFIC_CRUD);
        }
    }

    public Pedido() {
        this.total = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Usuario getVendedor() {
        return vendedor;
    }

    public void setVendedor(Usuario vendedor) {
        this.vendedor = vendedor;
    }

    public Loja getLoja() {
        return loja;
    }

    public void setLoja(Loja loja) {
        this.loja = loja;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public List<ItemPedido> getItensPedidos() {
        return itensPedidos;
    }

    public void setItensPedidos(List<ItemPedido> itensPedidos) {
        this.itensPedidos = itensPedidos;
    }
    
}
