package br.senac.tadsb.pi3.livrarianext.validar;

/**
 *
 * @author Nuneez
 */
public class Telefone {

    private String telefone;

    public Telefone(String telefone) {
        this.telefone = telefone;
    }

    public boolean validarTelefone() {
        if(!"".equals(telefone))
            try {
                //Integer.parseInt(telefone);
                Double.parseDouble(telefone);
                return true;
            } catch (Exception e) {
                return false;
            }
        else
            return true;
    }
}
