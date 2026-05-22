package MVC;

/**
 * Esta clase representa a un maestro que es capaz de controlar
 * uno de los 4 elementos de Avatar.
 */
public abstract class MaestroUnElemento extends Personaje {

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
    public MaestroUnElemento(String nombre, String nacion, String genero, int edad, boolean estaVivo, int nivelDeDominio, int energia) {
        super(nombre, nacion, genero, edad, estaVivo, nivelDeDominio, energia);
    }

    /**
     * Tiene la opción de atacar, sin embargo, al ser abstracto, no tiene cuerpo.
     * El cuerpo se lo dará la clase que represente un elemento en específico.
     * @throws EnergiaInsuficienteException
     */
    abstract void atacar() throws EnergiaInsuficienteException;

}
