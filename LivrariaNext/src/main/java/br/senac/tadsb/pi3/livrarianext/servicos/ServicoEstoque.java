/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tadsb.pi3.livrarianext.servicos;

import br.senac.tadsb.pi3.livrarianext.database.*;
import br.senac.tadsb.pi3.livrarianext.models.*;

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
}