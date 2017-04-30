/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tadsb.pi3.livrarianext.servicos;

import br.senac.tadsb.pi3.livrarianext.database.Dao;
import br.senac.tadsb.pi3.livrarianext.exceptions.*;

/**
 *
 * @author roger
 * @param <T>
 */
public abstract class Servico<T> {
    
    protected Dao dao;
    
    public Servico(Dao dao){
        this.dao = dao;
    }
        
    protected void incluir(T dominio) throws ServicoException {        
        try
        {
            dao.incluir(dominio);
        }
        catch(DaoException dx)
        {
            dx.printStackTrace();
            throw new ServicoException(dx.getMessage());
        }
    }
    protected void alterar(T dominio) throws ServicoException{
        try
        {
            dao.alterar(dominio);
        }
        catch(DaoException dx)
        {
            dx.printStackTrace();
            throw new ServicoException(dx.getMessage());
        }
    }
    protected void excluir(T dominio) throws ServicoException {
        try
        {
            dao.excluir(dominio);
        }
        catch(DaoException dx)
        {
            dx.printStackTrace();
            throw new ServicoException(dx.getMessage());
        }
    }
}
