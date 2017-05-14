/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tadsb.pi3.livrarianext.validar;

/**
 *
 * @author Nuneez
 */
public class Cpf {

    private String cpf;
    private static final int[] PESO_CPF = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2};

    public static boolean validarCpf(String cpf) {
        //removeCaracterEspecial(cpf);
        if ((cpf == null) || (cpf.length() != 11)) {
            return false;
        }

        Integer digito1 = calcularDigito(cpf.substring(0, 9), PESO_CPF);
        Integer digito2 = calcularDigito(cpf.substring(0, 9) + digito1, PESO_CPF);
        return cpf.equals(cpf.substring(0, 9) + digito1.toString() + digito2.toString());
    }

    private static int calcularDigito(String str, int[] peso) {
        int soma = 0;
        for (int indice = str.length() - 1, digito; indice >= 0; indice--) {
            digito = Integer.parseInt(str.substring(indice, indice + 1));
            soma += digito * peso[peso.length - str.length() + indice];
        }
        soma = 11 - soma % 11;
        return soma > 9 ? 0 : soma;
    }

    public static String removeCaracterEspecial(String texto) {
        return texto.replaceAll("[\\(\\)\\-\\.\\\\]", "");
    }
    
    public Cpf(String cpf) {
        this.cpf = cpf;
    }

    }
