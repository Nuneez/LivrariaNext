/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tadsb.pi3.livrarianext.servicos;

import br.senac.tadsb.pi3.livrarianext.database.DaoRelatorio;
import br.senac.tadsb.pi3.livrarianext.enums.ExceptionTypesEnum;
import br.senac.tadsb.pi3.livrarianext.exceptions.*;
import br.senac.tadsb.pi3.livrarianext.models.ItemPedidoDto;
import java.util.Date;
import java.util.List;

/**
 *
 * @author roger
 */
public class ServicoRelatorio {
    DaoRelatorio dao;
    
    public ServicoRelatorio(DaoRelatorio dao) {
        this.dao = dao;
    }
    
    public List<ItemPedidoDto> obterRelatorio(Date inicio, Date fim, String loja, String usuario, String produto) throws RelatorioException {
        try
        {
            return dao.obterRelatorio(inicio, fim, loja, usuario, produto);
        }
        catch(DaoException sqlex)
        {
            sqlex.printStackTrace();
            throw new RelatorioException(ExceptionTypesEnum.SPECIFIC_SELECT);
        }
    }
}
