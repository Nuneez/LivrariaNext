/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tadsb.pi3.livrarianext.servicos;

import br.senac.tadsb.pi3.livrarianext.database.*;
import br.senac.tadsb.pi3.livrarianext.enums.ExceptionTypesEnum;
import br.senac.tadsb.pi3.livrarianext.exceptions.*;
import br.senac.tadsb.pi3.livrarianext.models.Loja;
import java.util.List;

/**
 *
 * @author roger
 */
public class ServicoLoja extends Servico<Loja> {
    
    final DaoLoja dao;
    
    public ServicoLoja(Dao dao) {
        super(dao);
        this.dao = (DaoLoja)dao;
    }    
    
    public void incluir(String nome, Boolean ehFilial, String cnpj, String razaoSocial, String telefone, String endereco, String numero, String cidade, String estado, String email, String inscricaoEstadual)  throws LojaException {
        try
        {
            Loja novo = new Loja(nome, ehFilial, cnpj, razaoSocial, telefone, endereco, numero, cidade, estado, email,  inscricaoEstadual);
            super.incluir(novo);
        }
        catch(ServicoException se)
        {
            throw new LojaException(ExceptionTypesEnum.SPECIFIC_CRUD);
        }
    }
    
    public void alterar(int id, String nome, Boolean ehFilial, String cnpj, String razaoSocial, String telefone, String endereco, String numero, String cidade, String estado, String email, String inscricaoEstadual) throws LojaException {
        try
        {
            Loja dominio = dao.obterPorId(id);
            dominio.setNome(nome);
            dominio.setEhFilial(ehFilial);
            dominio.setCnpj(cnpj);
            dominio.setRazaoSocial(razaoSocial);
            dominio.setTelefone(telefone);
            dominio.setEndereco(endereco);
            dominio.setNumero(numero);
            dominio.setCidade(cidade);
            dominio.setEstado(estado);
            dominio.setEmail(email);            
            dominio.setInscricaoEstadual(inscricaoEstadual);           
            
            super.alterar(dominio);
        }
        catch(DaoException de)
        {
            throw new LojaException(ExceptionTypesEnum.DATABASE);
        }
        catch(ServicoException se)
        {
            throw new LojaException(ExceptionTypesEnum.SPECIFIC_CRUD);
        }
    }
    
    public void excluir(int id) throws LojaException {
        try
        {
            Loja dominio = dao.obterPorId(id);
            super.excluir(dominio);
        }
        catch(DaoException de)
        {
            throw new LojaException(ExceptionTypesEnum.DATABASE);
        }
        catch(ServicoException se)
        {
            throw new LojaException(ExceptionTypesEnum.SPECIFIC_CRUD);
        }
    }
    
    public List<Loja> obterLojas(String nome, String cnpj) throws LojaException  {
        try
        {
            return dao.obterLojas(nome, cnpj);
        }
        catch(DaoException sqlex)
        {
            sqlex.printStackTrace();
            throw new LojaException(ExceptionTypesEnum.SPECIFIC_SELECT);
        }
    }
    
    public Loja obterLojaPorId(int id) throws LojaException  {
        try
        {
            return dao.obterPorId(id);
        }
        catch(DaoException sqlex)
        {
            sqlex.printStackTrace();
            throw new LojaException(ExceptionTypesEnum.SPECIFIC_SELECT);
        }
    }
}
