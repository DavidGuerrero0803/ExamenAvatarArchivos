package MVC;

/**
 * Esta clase representa a un Guerrero de Avatar.
 * Tiene la característica de no ser un maestro,
 * por lo que no tiene control ni ataque de elementos.
 */
public class Guerrero extends Personaje {

    /**
     * El constructor usa super() para heredar los atributos de la clase Padre.
     * @param nombre Nombre que tendrá el personaje.
     * @param nacion Nación a la que pertenece.
     * @param genero Género del personaje.
     * @param edad Edad que tiene.
     * @param estaVivo Si el personaje está vivo o no (true/false).
     * @param nivelDeDominio Su nivel de dominio (0-100).
     * @param energia Su nivel de energía.
     */
    public Guerrero(String nombre, String nacion, String genero, int edad, boolean estaVivo, int nivelDeDominio, int energia) {
        super(nombre, nacion, genero, edad, estaVivo, nivelDeDominio, energia);
    }

    /**
     * Al no ser un maestro, tendrá un ataque genérico que igualmente usará energía.
     * @throws EnergiaInsuficienteException En caso de que Guerrero no tenga una energía mayor a 10.
     */
    public void realizarAtaque() throws EnergiaInsuficienteException {
        poseeEnergiaSufiente();
        System.out.println(nombre + " realizó un ataque con su arma (es muy efectivo)");
        energia -= 10;
    }
}