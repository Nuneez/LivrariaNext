/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tadsb.pi3.livrarianext.database;

import br.senac.tadsb.pi3.livrarianext.models.Loja;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nuneez
 */
public class DaoLoja extends Dao<Loja> {

    public DaoLoja() throws SQLException, Exception {
        super(new ConnectionUtils());
    }

    @Override
    public void incluir(Loja dominio) throws SQLException, Exception {
        PreparedStatement stt = obterStatement("insert into loja (nome, ehFilial, cnpj, razaoSocial, endereco, numero, cidade, estado, email, telefone, ativo) values (?,?,?,?,?,?,?,?,?,?)");
        //stt.setInt(0, dominio.getId());
        stt.setString(1, dominio.getNome());
        stt.setBoolean(2, dominio.getEhFilial());
        stt.setString(3, dominio.getCnpj());
        stt.setString(4, dominio.getRazaoSocial());
        stt.setString(5, dominio.getEndereco());
        stt.setString(6, dominio.getNumero());
        stt.setString(7, dominio.getCidade());
        stt.setString(8, dominio.getEstado());
        stt.setString(9, dominio.getTelefone());

        stt.setBoolean(10, dominio.getAtivo());

        stt.execute();
    }

    @Override
    public void alterar(Loja dominio) throws SQLException, Exception {
        PreparedStatement stt = obterStatement("update loja set nome = ?, ehFilial = ?, cnpj = ?,  razaoSocial = ?,  endereco = ?, numero = ?, cidade = ?, estado = ?, telefone = ?, ativo = ? where id = ?");

        stt.setString(1, dominio.getNome());
        stt.setBoolean(2, dominio.getEhFilial());
        stt.setString(3, dominio.getCnpj());
        stt.setString(4, dominio.getRazaoSocial());
        stt.setString(5, dominio.getEndereco());
        stt.setString(6, dominio.getNumero());
        stt.setString(7, dominio.getCidade());
        stt.setString(8, dominio.getEstado());
        stt.setString(9, dominio.getTelefone());
        stt.setBoolean(10, dominio.getAtivo());

        stt.setInt(11, dominio.getId());

        stt.execute();
    }

    @Override
    public void excluir(Loja dominio) throws SQLException, Exception {
        PreparedStatement stt = obterStatement("update loja set ativo = ? where id = ?");
        stt.setBoolean(1, false);
        stt.setInt(2, dominio.getId());

        stt.execute();
    }

    @Override
    public Loja obterPorId(int id) throws SQLException, Exception {
        ResultSet rs = getList("select * from loja where id = " + id);

        Loja dominio = null;

        while (rs.next()) {
            dominio
                    = new Loja(
                            rs.getInt("id"),
                            rs.getString("nome"),
                            rs.getBoolean("ehFilial"),
                            rs.getString("cnpj"),
                            rs.getString("razaoSocial"),
                            rs.getString("endereco"),
                            rs.getString("numero"),
                            rs.getString("cidade"),
                            rs.getString("estado"),
                            rs.getString("telefone")
                            
                    );
        }

        return dominio;
    }

    public List<Loja> obterLojas(String nome, String cnpj) throws SQLException, Exception {
        String query = "select * from loja c ";

        if (nome != null && !nome.isEmpty()) {
            query = tratarQuery(query) + " UPPER(c.nome) like ('%" + nome.toUpperCase() + "%')";
        }

        if (cnpj != null && !cnpj.isEmpty()) {
            query = tratarQuery(query) + " c.cnpj like ('%" + cnpj + "%')";
        }

        ResultSet rs = getList(query);

        List<Loja> lojas = new ArrayList<>();

        while (rs.next()) {
            lojas.add(new Loja(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getBoolean("ehFilial"),
                    rs.getString("cnpj"),
                    rs.getString("razaoSocial"),
                    rs.getString("endereco"),
                    rs.getString("numero"),
                    rs.getString("cidade"),
                    rs.getString("estado"),
                    rs.getString("telefone")

            ));
        }

        return lojas;
    }

}
