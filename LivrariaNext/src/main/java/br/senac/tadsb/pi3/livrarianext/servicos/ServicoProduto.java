/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tadsb.pi3.livrarianext.servicos;

import br.senac.tadsb.pi3.livrarianext.database.*;
import br.senac.tadsb.pi3.livrarianext.enums.ExceptionTypesEnum;
import br.senac.tadsb.pi3.livrarianext.exceptions.*;
import br.senac.tadsb.pi3.livrarianext.models.NameValue;
import br.senac.tadsb.pi3.livrarianext.models.Produto;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author roger
 */
public class ServicoProduto extends Servico<Produto> {
    
    final DaoProduto dao;
    
    public ServicoProduto(Dao dao) {
        super(dao);
        this.dao = (DaoProduto)dao;
    }        
    
    public void incluir(String nome, String descricao, Double custo, Double preco, String ean)  throws ProdutoException {
        try
        {
            Produto novo = new Produto(nome, descricao, custo, preco, ean);
            super.incluir(novo);
        }
        catch(ServicoException se)
        {
            throw new ProdutoException(ExceptionTypesEnum.SPECIFIC_CRUD);
        }
    }
    
    public void alterar(int id, String nome, String descricao, Double custo, Double preco, String ean, Boolean ativo) throws ProdutoException {
        try
        {
            Produto dominio = dao.obterPorId(id);
            dominio.setNome(nome);
            dominio.setDescricao(descricao);
            dominio.setCusto(custo);
            dominio.setPreco(preco);
            dominio.setEan(ean);
            dominio.setAtivo(ativo);
            
            super.alterar(dominio);
        }
        catch(DaoException de)
        {
            throw new ProdutoException(ExceptionTypesEnum.DATABASE);
        }
        catch(ServicoException se)
        {
            throw new ProdutoException(ExceptionTypesEnum.SPECIFIC_CRUD);
        }
    }
    
    public void excluir(int id) throws ProdutoException {
        try
        {
            Produto dominio = dao.obterPorId(id);
            super.excluir(dominio);
        }
        catch(DaoException de)
        {
            throw new ProdutoException(ExceptionTypesEnum.DATABASE);
        }
        catch(ServicoException se)
        {
            throw new ProdutoException(ExceptionTypesEnum.SPECIFIC_CRUD);
        }
    }
    
    public List<NameValue> obterProdutos(String texto) throws ProdutoException {
        
        List<Produto> produtos = obterProdutos(texto, null);
        List<NameValue> colecao = new ArrayList<>();

        for (Produto p : produtos)
            colecao.add(new NameValue(String.valueOf(p.getId()), p.getNome(), p.getPreco()));            
        
        return colecao;
    }
    
    public List<Produto> obterProdutos(String nome, String cnpj) throws ProdutoException  {
        try
        {
            return dao.obterProdutos(nome, cnpj);
        }
        catch(DaoException sqlex)
        {
            sqlex.printStackTrace();
            throw new ProdutoException(ExceptionTypesEnum.SPECIFIC_SELECT);
        }
    }
    
    public Produto obterProdutoPorId(int id) throws ProdutoException  {
        try
        {
            return dao.obterPorId(id);
        }
        catch(DaoException sqlex)
        {
            sqlex.printStackTrace();
            throw new ProdutoException(ExceptionTypesEnum.SPECIFIC_SELECT);
        }
    }    
}
