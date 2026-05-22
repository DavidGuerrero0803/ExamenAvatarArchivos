package MVC;

/**
 * Esta clase gestiona una excepción.
 * Extiende de Exception para volverlo personalizado.
 */
public class EnergiaInsuficienteException extends Exception {
    /**
     * Su mensaje de error se mandará si un personaje se queda
     * sin energía suficiente una vez quiera atacar.
     * @param mensaje_error Mensaje que se lanzará como excepción en la consola.
     */
    public EnergiaInsuficienteException(String mensaje_error) {
        super(mensaje_error);
    }
}
