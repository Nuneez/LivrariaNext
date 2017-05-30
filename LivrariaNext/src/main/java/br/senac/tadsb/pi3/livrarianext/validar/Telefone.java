package br.senac.tadsb.pi3.livrarianext.validar;

/**
 *
 * @author Nuneez
 */
public class Telefone {
    public static boolean validar(String telefone) {
        
        if (telefone.trim().isEmpty())
            return false;        
        
        try 
        {
            Long.parseLong(CaracteresEspeciais.removerCaracter(telefone));
            return true;
        } 
        catch (Exception e) 
        {
            return false;
        }
    }
}
