/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tadsb.pi3.livrarianext.servicos;

import br.senac.tadsb.pi3.livrarianext.database.Dao;
import br.senac.tadsb.pi3.livrarianext.database.DaoCliente;
import br.senac.tadsb.pi3.livrarianext.enums.ExceptionTypesEnum;
import br.senac.tadsb.pi3.livrarianext.exceptions.*;
import br.senac.tadsb.pi3.livrarianext.models.Cliente;
import java.util.List;

/**
 *
 * @author roger
 */
public class ServicoCliente extends Servico<Cliente> {

    final DaoCliente dao;
    
    public ServicoCliente(DaoCliente dao) {
        super(dao);
        this.dao = (DaoCliente) dao;
    }
    
    public void incluir(String nome, String sobrenome, String cpf, String rg, String nascimento, String sexo, String email, String telefone, String endereco, String numero, String bairro)  throws ClienteException {
        try
        {
            Cliente novo = new Cliente(nome, sobrenome, cpf, rg, nascimento, sexo, email, telefone, endereco, numero, bairro, true);
            super.incluir(novo);
        }
        catch(ServicoException se)
        {
            throw new ClienteException(ExceptionTypesEnum.SPECIFIC_CRUD);
        }
    }
    
    public void alterar(int id, String nome, String sobrenome, String cpf, String rg, String nascimento, String sexo, String email, String telefone, String endereco, String numero, String bairro) throws ClienteException {
        try
        {
            Cliente cliente = dao.obterPorId(id);
            cliente.setNome(nome);
            cliente.setSobreNome(sobrenome);
            cliente.setCpf(cpf);
            cliente.setRg(rg);
            cliente.setSexo(sexo);
            cliente.setEmail(email);
            cliente.setTelefone(telefone);
            cliente.setEndereco(endereco);
            cliente.setNumero(numero);
            cliente.setBairro(bairro);
            
            super.alterar(cliente);
        }
        catch(DaoException de)
        {
            throw new ClienteException(ExceptionTypesEnum.DATABASE);
        }
        catch(ServicoException se)
        {
            throw new ClienteException(ExceptionTypesEnum.SPECIFIC_CRUD);
        }
    }
    
    public void excluir(int id) throws ClienteException {
        try
        {
            Cliente dominio = dao.obterPorId(id);
            super.excluir(dominio);
        }
        catch(DaoException de)
        {
            throw new ClienteException(ExceptionTypesEnum.DATABASE);
        }
        catch(ServicoException se)
        {
            throw new ClienteException(ExceptionTypesEnum.SPECIFIC_CRUD);
        }
    }
    
    public List<Cliente> obterClientes(String nome, String cpf) throws ClienteException  {
        try
        {
            return dao.obterClientes(nome, cpf);
        }
        catch(DaoException sqlex)
        {
            sqlex.printStackTrace();
            throw new ClienteException(ExceptionTypesEnum.SPECIFIC_SELECT);
        }
    }
    
    public Cliente obterClientePorId(int id) throws ClienteException  {
        try
        {
            return dao.obterPorId(id);
        }
        catch(DaoException sqlex)
        {
            sqlex.printStackTrace();
            throw new ClienteException(ExceptionTypesEnum.SPECIFIC_SELECT);
        }
    }
}
