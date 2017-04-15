/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tadsb.pi3.livrarianext.database;

/**
 *
 * @author roger
 */
public abstract class Dao<t> {
    protected abstract void incluir(t dominio);
    protected abstract void alterar(t dominio);
    protected abstract void excluir(t dominio);
}
