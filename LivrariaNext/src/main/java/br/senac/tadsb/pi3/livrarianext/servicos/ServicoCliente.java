/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tadsb.pi3.livrarianext.servicos;

import br.senac.tadsb.pi3.livrarianext.database.DaoCliente;
import br.senac.tadsb.pi3.livrarianext.enums.ExceptionTypesEnum;
import br.senac.tadsb.pi3.livrarianext.exceptions.ClienteException;
import br.senac.tadsb.pi3.livrarianext.models.Cliente;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author roger
 */
public class ServicoCliente extends Servico<Cliente> {

    DaoCliente dao;
    
    public ServicoCliente() throws ClienteException {
        try
        {
            dao = new DaoCliente();
        }
        catch(SQLException sqlex)
        {
            System.out.println(sqlex.getMessage());
            throw new ClienteException(sqlex.getMessage(), ExceptionTypesEnum.DISPLAY);
        }
        catch(Exception ex)
        {
            throw new ClienteException("");
        }
    }
    
    public void incluir(String nome, String sobrenome, String cpf, String nascimento, String sexo, String email, String telefone, String endereco, String numero, String bairro)  throws ClienteException {
        Cliente novo = new Cliente(nome, sobrenome, cpf, nascimento, sexo, email, telefone, endereco, numero, bairro, true);
        this.incluir(novo);
    }
    
    @Override
    protected void incluir(Cliente dominio) throws ClienteException {
         try
        {
            dao.incluir(dominio);
        }
        catch(SQLException sqlex)
        {
            sqlex.printStackTrace();
            throw new ClienteException(ExceptionTypesEnum.SPECIFIC_CRUD);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            throw new ClienteException(ExceptionTypesEnum.GENERAL);
        }
    }
    
    public void alterar(int id, String nome, String sobrenome, String cpf, String endereco) throws ClienteException {
        try
        {
            Cliente cliente = dao.obterPorId(id);
            cliente.setNome(nome);
            cliente.setSobreNome(sobrenome);
            cliente.setCpf(cpf);
            cliente.setEndereco(endereco);
            
            this.alterar(cliente);
        }
        catch(SQLException sqlex)
        {
            sqlex.printStackTrace();
            throw new ClienteException(ExceptionTypesEnum.SPECIFIC_SELECT);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            throw new ClienteException(ExceptionTypesEnum.GENERAL);
        }
    }

    @Override
    protected void alterar(Cliente dominio) throws ClienteException {
        try
        {
            dao.alterar(dominio);
        }
        catch(SQLException sqlex)
        {
            sqlex.printStackTrace();
            throw new ClienteException(ExceptionTypesEnum.SPECIFIC_CRUD);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            throw new ClienteException(ExceptionTypesEnum.GENERAL);
        }
    }
    
    public void excluir(int id) throws ClienteException {
        try
        {
            Cliente dominio = dao.obterPorId(id);
            this.excluir(dominio);
        }
        catch(SQLException sqlex)
        {
            sqlex.printStackTrace();
            throw new ClienteException(ExceptionTypesEnum.SPECIFIC_SELECT);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            throw new ClienteException(ExceptionTypesEnum.GENERAL);
        }
    }

    @Override
    protected void excluir(Cliente dominio) throws ClienteException {
        try
        {
            dao.excluir(dominio);
        }
        catch(SQLException sqlex)
        {
            sqlex.printStackTrace();
            throw new ClienteException(ExceptionTypesEnum.SPECIFIC_CRUD);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            throw new ClienteException(ExceptionTypesEnum.GENERAL);
        }
    }
    
    public List<Cliente> obterClientes(String nome, String cpf) throws ClienteException  {
        try
        {
            return dao.obterClientes(nome, cpf);
        }
        catch(SQLException sqlex)
        {
            sqlex.printStackTrace();
            throw new ClienteException(ExceptionTypesEnum.SPECIFIC_CRUD);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            throw new ClienteException(ExceptionTypesEnum.GENERAL);
        }
    }
    
    public Cliente obterClientePorId(int id) throws ClienteException  {
        try
        {
            return dao.obterPorId(id);
        }
        catch(SQLException sqlex)
        {
            sqlex.printStackTrace();
            throw new ClienteException(ExceptionTypesEnum.SPECIFIC_CRUD);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            throw new ClienteException(ExceptionTypesEnum.GENERAL);
        }
    }
}
