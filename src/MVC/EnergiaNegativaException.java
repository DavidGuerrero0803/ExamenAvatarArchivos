package MVC;

/**
 * Esta clase gestiona una excepción.
 * Extiende de Exception para volverlo personalizado.
 */
public class EnergiaNegativaException extends Exception {
    /**
     * Su mensaje de error se mandará si un personaje posee
     * una energía menor a 0, es decir, energía con valor negativo.
     * @param mensaje_error Mensaje que se lanzará como excepción en la consola.
     */
    public EnergiaNegativaException(String mensaje_error) {
        super(mensaje_error);
    }
}
