/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tadsb.pi3.livrarianext.models;

import java.util.Date;

/**
 *
 * @author roger
 */
public class ItemPedidoDto {
    private int pedidoId;
    private int itemPedidoId;
    private Date pedidoData;
    private String lojaNome;
    private String clienteNome;
    private String vendedorNome;
    private String produtoNome;
    private double qtdProduto;
    private double valorUnitario;
    private double valorTotal;
    
    public ItemPedidoDto(int pedidoId, int itemPedidoId, Date pedidoData, String lojaNome, String clienteNome, String vendedorNome, String produtoNome, double qtdProduto, double valorUnitario){
        this.pedidoId = pedidoId;
        this.itemPedidoId = itemPedidoId;
        this.pedidoData = pedidoData;
        this.lojaNome = lojaNome;
        this.clienteNome = clienteNome;
        this.vendedorNome = vendedorNome;
        this.produtoNome = produtoNome;
        this.qtdProduto = qtdProduto;
        this.valorUnitario = valorUnitario;
    }

    /**
     * @return the pedidoId
     */
    public int getPedidoId() {
        return pedidoId;
    }

    /**
     * @param pedidoId the pedidoId to set
     */
    public void setPedidoId(int pedidoId) {
        this.pedidoId = pedidoId;
    }

    /**
     * @return the pedidoData
     */
    public Date getPedidoData() {
        return pedidoData;
    }

    /**
     * @param pedidoData the pedidoData to set
     */
    public void setPedidoData(Date pedidoData) {
        this.pedidoData = pedidoData;
    }

    /**
     * @return the lojaNome
     */
    public String getLojaNome() {
        return lojaNome;
    }

    /**
     * @param lojaNome the lojaNome to set
     */
    public void setLojaNome(String lojaNome) {
        this.lojaNome = lojaNome;
    }

    /**
     * @return the clienteNome
     */
    public String getClienteNome() {
        return clienteNome;
    }

    /**
     * @param clienteNome the clienteNome to set
     */
    public void setClienteNome(String clienteNome) {
        this.clienteNome = clienteNome;
    }

    /**
     * @return the vendedorNome
     */
    public String getVendedorNome() {
        return vendedorNome;
    }

    /**
     * @param vendedorNome the vendedorNome to set
     */
    public void setVendedorNome(String vendedorNome) {
        this.vendedorNome = vendedorNome;
    }

    /**
     * @return the produtoNome
     */
    public String getProdutoNome() {
        return produtoNome;
    }

    /**
     * @param produtoNome the produtoNome to set
     */
    public void setProdutoNome(String produtoNome) {
        this.produtoNome = produtoNome;
    }

    /**
     * @return the qtdProduto
     */
    public double getQtdProduto() {
        return qtdProduto;
    }

    /**
     * @param qtdProduto the qtdProduto to set
     */
    public void setQtdProduto(double qtdProduto) {
        this.qtdProduto = qtdProduto;
    }

    /**
     * @return the valorUnitario
     */
    public double getValorUnitario() {
        return valorUnitario;
    }

    /**
     * @param valorUnitario the valorUnitario to set
     */
    public void setValorUnitario(double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    /**
     * @return the valorTotal
     */
    public double getValorTotal() {
        return valorTotal;
    }

    /**
     * @param valorTotal the valorTotal to set
     */
    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    /**
     * @return the itemPedidoId
     */
    public int getItemPedidoId() {
        return itemPedidoId;
    }

    /**
     * @param itemPedidoId the itemPedidoId to set
     */
    public void setItemPedidoId(int itemPedidoId) {
        this.itemPedidoId = itemPedidoId;
    }
}
