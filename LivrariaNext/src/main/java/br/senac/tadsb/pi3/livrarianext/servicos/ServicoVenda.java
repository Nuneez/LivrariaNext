/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tadsb.pi3.livrarianext.servicos;

import br.senac.tadsb.pi3.livrarianext.database.Dao;
import br.senac.tadsb.pi3.livrarianext.enums.ExceptionTypesEnum;
import br.senac.tadsb.pi3.livrarianext.exceptions.VendaException;
import br.senac.tadsb.pi3.livrarianext.exceptions.DaoException;
import br.senac.tadsb.pi3.livrarianext.exceptions.ServicoException;
import br.senac.tadsb.pi3.livrarianext.models.*;
import br.senac.tadsb.pi3.livrarianext.servicos.*;
import java.util.List;

/**
 *
 * @author Thiago
 */
public class ServicoVenda extends Servico<Venda> {
    private Venda venda;

    public ServicoVenda(Dao dao) {
        super(dao);
    }
    
    public void incluir( List<Produto> lstProdutos, List<Integer> lstQuantidades, float total, Cliente cliente)  throws VendaException {
        try
        {
            Venda novo = new Venda(lstProdutos, lstQuantidades, cliente, total);
            super.incluir(novo);
        }
        catch(ServicoException se)
        {
            throw new VendaException(ExceptionTypesEnum.SPECIFIC_CRUD);
        }
    }
    
    public void alterar(int id, List<Produto> lstProdutos, List<Integer> lstQuantidades, float total, Cliente cliente) throws VendaException {
        try
        {
            Venda venda = dao.obterPorId(id);
            venda.setProdutos(lstProdutos);
            venda.setQntProdutos(lstQuantidades);
            venda.setTotal(total);
            venda.setCliente(cliente);
            super.alterar(venda);
        }
        catch(DaoException de)
        {
            throw new VendaException(ExceptionTypesEnum.DATABASE);
        }
        catch(ServicoException se)
        {
            throw new VendaException(ExceptionTypesEnum.SPECIFIC_CRUD);
        }
    }
    
    public void excluir(int id) throws VendaException {
        try
        {
            Cliente dominio = dao.obterPorId(id);
            super.excluir(dominio);
        }
        catch(DaoException de)
        {
            throw new VendaException(ExceptionTypesEnum.DATABASE);
        }
        catch(ServicoException se)
        {
            throw new VendaException(ExceptionTypesEnum.SPECIFIC_CRUD);
        }
    }
    
    public List<Cliente> obterClientes(String nome, String cpf) throws VendaException  {
        try
        {
            return dao.obterClientes(nome, cpf);
        }
        catch(DaoException sqlex)
        {
            sqlex.printStackTrace();
            throw new VendaException(ExceptionTypesEnum.SPECIFIC_SELECT);
        }
    }
    
    public Cliente obterClientePorId(int id) throws VendaException  {
        try
        {
            return dao.obterPorId(id);
        }
        catch(DaoException sqlex)
        {
            sqlex.printStackTrace();
            throw new VendaException(ExceptionTypesEnum.SPECIFIC_SELECT);
        }
    }
}
