package MVC;

/**
 * Esta clase representa al avatar, quien puede dominar todos los elementos.
 * Extiende de Personaje e implementa todas las interfaces creadas de cada maestro.
 */
public class CrearAvatar extends Personaje implements IMaestroAgua, IMaestroTierra, IMaestroFuego, IMaestroAire {

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
    public CrearAvatar(String nombre, String nacion, String genero, int edad, boolean estaVivo, int nivelDeDominio, int energia) {
        super(nombre, nacion, genero, edad, estaVivo, nivelDeDominio, energia);
    }

    /**
     * Sobreescritura de controlarAgua() que se encontraba en su interface.
     * El Avatar no solo controla el elemento, lo domina por completo.
     */
    @Override
    public void controlarAgua() {
        System.out.println(nombre + " domina el Agua Control");
    }

    /**
     * Sobreescritura de controlarTierra() que se encontraba en su interface.
     * El Avatar no solo controla el elemento, lo domina por completo.
     */
    @Override
    public void controlarTierra() {
        System.out.println(nombre + " domina la Tierra Control");
    }

    /**
     * Sobreescritura de controlarFuego() que se encontraba en su interface.
     * El Avatar no solo controla el elemento, lo domina por completo.
     */
    @Override
    public void controlarFuego() {
        System.out.println(nombre + " domina el Fuego Control");
    }

    /**
     * Sobreescritura de controlarAire() que se encontraba en su interface.
     * El Avatar no solo controla el elemento, lo domina por completo.
     */
    @Override
    public void controlarAire() {
        System.out.println(nombre + " domina el Aire Control");
    }

    /**
     * Sobreescritura de atacar(), aquí realiza el ataque combinado de los 4 elementos,
     * gastando 10 de energía por ataque, si llega a menos de 10, lanzará la excepción.
     * @throws EnergiaInsuficienteException
     */
    public void ataqueAvatar() throws EnergiaInsuficienteException {
        poseeEnergiaSufiente();
        System.out.println(nombre + " atacó con los 4 elementos naturales.");
        energia -= 10;
    }
}
