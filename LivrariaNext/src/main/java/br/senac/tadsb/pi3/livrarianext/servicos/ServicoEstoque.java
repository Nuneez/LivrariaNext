/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tadsb.pi3.livrarianext.servicos;

import br.senac.tadsb.pi3.livrarianext.database.*;
import br.senac.tadsb.pi3.livrarianext.enums.*;
import br.senac.tadsb.pi3.livrarianext.models.*;
import br.senac.tadsb.pi3.livrarianext.exceptions.*;

/**
 *
 * @author roger.roliveira
 */
public class ServicoEstoque extends Servico<Estoque> {
    
    final DaoEstoque dao;
    
    public ServicoEstoque(Dao dao) {
        super(dao);
        this.dao = (DaoEstoque)dao;
    }
    
    public Estoque obterEstoquePorLojaId(int lojaId) throws EstoqueException {
        try
        {
            return dao.obterPorLoja(lojaId);
        }
        catch(DaoException de)
        {
            throw new EstoqueException(ExceptionTypesEnum.DATABASE);
        }
    }
    
    public void alterarEstoque(int id, int estoqueId, int produtoId, double saldo, CrudActions acao) throws EstoqueException {
        try
        {
            switch (acao) {
                case INSERT:
                    dao.incluirProduto(estoqueId, produtoId, saldo);
                    break;
                case EDIT:
                    dao.alterarProduto(id, saldo, Boolean.TRUE);
                    break;
                case DELETE:
                    dao.excluirProduto(id);
                    break;    
                default:
                    throw new AssertionError();
            }
        }
        catch(DaoException de)
        {
            throw new EstoqueException(ExceptionTypesEnum.DATABASE);
        }
    }
}
