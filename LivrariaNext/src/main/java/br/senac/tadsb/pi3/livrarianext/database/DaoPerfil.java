/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tadsb.pi3.livrarianext.database;

import br.senac.tadsb.pi3.livrarianext.exceptions.DaoException;
import br.senac.tadsb.pi3.livrarianext.models.Perfil;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author roger
 */
public class DaoPerfil extends Dao<Perfil>  {        
    
    final String queryPadrao = "select * from perfil ";

    public DaoPerfil(ConnectionUtils util) throws SQLException {
        super(util);
    }

    @Override
    public void incluir(Perfil dominio) throws DaoException {
        throw new DaoException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void alterar(Perfil dominio) throws DaoException {
        throw new DaoException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void excluir(Perfil dominio) throws DaoException {
        throw new DaoException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Perfil obterPorId(int id) throws DaoException {
        try
        {
            String query = queryPadrao + " where id = " + id;

            ResultSet rs = getList(query);

            while (rs.next()) {
                 return obterDominio(rs);
            }

            return null;
        }
        catch(SQLException sqlex)
        {
            throw new DaoException();
        }
    }
    
    public List<Perfil> obterPerfis() throws DaoException {                                
        try
        {
            ResultSet rs = getList(queryPadrao + " order by nome ");        
            List<Perfil> lista = new ArrayList<>();        

            while (rs.next())
                 lista.add(obterDominio(rs));

            return lista;
        }
        catch(SQLException sqlex)
        {
            throw new DaoException();
        }
    }
    
    @Override
    protected Perfil obterDominio(ResultSet rs) throws DaoException {
        try
        {
            return new Perfil(
                        rs.getInt("id"), 
                        rs.getString("nome"), 
                        rs.getBoolean("ativo"), 
                        null
                     );
        }
        catch(SQLException sqlex)
        {
            throw new DaoException();
        }
    }
}
