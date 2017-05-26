/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tadsb.pi3.livrarianext.database;

import br.senac.tadsb.pi3.livrarianext.exceptions.DaoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author roger
 */
public abstract class Dao<T> {
    
    private ConnectionUtils util;
    private Connection connection;
    
    public Dao(ConnectionUtils util) throws SQLException {        
        this.util = util;
        this.connection = util.getConnection();
    }
    
    public abstract void incluir(T dominio) throws DaoException;
    public abstract void alterar(T dominio) throws DaoException;
    public abstract void excluir(T dominio) throws DaoException;
    public abstract T obterPorId(int id) throws DaoException;
    protected abstract T obterDominio(ResultSet rs) throws DaoException;
    
    protected PreparedStatement obterStatementRetornaId(String command) throws java.sql.SQLException, Exception {
        validarConexao();                
        return connection.prepareStatement(command, Statement.RETURN_GENERATED_KEYS);
    }    
    
    protected PreparedStatement obterStatement(String command) throws java.sql.SQLException, Exception {
        validarConexao();                
        return connection.prepareStatement(command);
    }    
    
    protected void fecharConexao() throws java.sql.SQLException {
        if (connection != null && !connection.isClosed())
            connection.close();
    }
    
    protected void saveOrUpdate(String command) throws java.sql.SQLException {
        validarConexao();        
        Statement statement = connection.createStatement();        
        statement.execute(command);
    }
    
    protected ResultSet getList(String query) throws DaoException {                
        try
        {
            validarConexao();        
            return connection.createStatement().executeQuery(query);
        }
        catch(SQLException sqlex)
        {
            System.out.println(sqlex.getMessage());
            throw new DaoException();
        }
    }
    
    private void validarConexao() throws java.sql.SQLException {
        if (connection == null || connection.isClosed())
            this.connection = util.getConnection();
    }
    
    protected String tratarQuery(String query)
    {
        if (query.toLowerCase().contains("where"))
            return query += " and ";
        
        return query += " where ";
    }
}
