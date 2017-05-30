/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tadsb.pi3.livrarianext.validar;

/**
 *
 * @author elison.esouza
 */
public class CaracteresEspeciais {
    public static String removerCaracter(String texto){
        texto = texto.replace(" ", ""); //tira espaço em branco
        texto = texto.replace(".", ""); //tira ponto
        texto = texto.replace("/", ""); //tira barra
        texto = texto.replace("-", ""); //tira hífen
        texto = texto.replace("(", "");
        texto = texto.replace(")", "");
        return texto;
    }
}