/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tadsb.pi3.livrarianext.database;

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
    
    protected abstract void incluir(T dominio) throws SQLException, Exception;
    protected abstract void alterar(T dominio) throws SQLException, Exception;
    protected abstract void excluir(T dominio) throws SQLException, Exception;
    protected abstract T obterPorId(int id) throws SQLException, Exception;
    
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
    
    protected ResultSet getList(String query) throws java.sql.SQLException {                
        validarConexao();        
        return connection.createStatement().executeQuery(query);
    }
    
    private void validarConexao() throws java.sql.SQLException {
        if (connection == null || connection.isClosed())
            this.connection = util.getConnection();
    }
}
