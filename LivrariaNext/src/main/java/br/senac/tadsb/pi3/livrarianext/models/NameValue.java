/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tadsb.pi3.livrarianext.models;

/**
 *
 * @author roger
 */
public class NameValue {
    public String id;
    public String name;
    public String valueProduct;
    
    public NameValue(String id, String name, double value){
        this.id = id;
        this.name = name;
        this.valueProduct = "" + value + "";
    }
    public NameValue(String id, String name){
        this.id = id;
        this.name = name;
    }
}
