/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tadsb.pi3.livrarianext.servicos;

import br.senac.tadsb.pi3.livrarianext.database.*;
import br.senac.tadsb.pi3.livrarianext.enums.ExceptionTypesEnum;
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
}
