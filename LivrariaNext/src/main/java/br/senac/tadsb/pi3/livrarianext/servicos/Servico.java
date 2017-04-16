/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tadsb.pi3.livrarianext.servicos;

/**
 *
 * @author roger
 */
public abstract class Servico<T> {
    protected abstract void incluir(T dominio) throws Exception;
    protected abstract void alterar(T dominio) throws Exception;
    protected abstract void excluir(T dominio) throws Exception;
}
