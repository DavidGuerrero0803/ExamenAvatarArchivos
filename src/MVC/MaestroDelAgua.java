package MVC;

/**
 * Esta clase representa específicamente a un maestro del agua.
 * Extiende de MaestroUnElemento e implementa la interface IMaestroAgua
 */
public class MaestroDelAgua extends MaestroUnElemento implements IMaestroAgua {

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
    public MaestroDelAgua(String nombre, String nacion, String genero, int edad, boolean estaVivo, int nivelDeDominio, int energia) {
        super(nombre, nacion, genero, edad, estaVivo, nivelDeDominio, energia);
    }

    /**
     * Aquí se sobreescribe controlarAgua() que se encontraba en su interface.
     * Ahora dentro del cuerpo puede hacer lo que se requiera, en este caso lanzar un mensaje.
     */
    @Override
    public void controlarAgua() {
        System.out.println(nombre + " se encuentra bajo Agua Control");
    }

    /**
     * Sobreescritura de atacar(), aquí realiza su ataque elemental respectivo
     * gastando 10 de energía por ataque, si llega a menos de 10, lanzará la excepción.
     * @throws EnergiaInsuficienteException
     */
    @Override
    public void atacar() throws EnergiaInsuficienteException {
        poseeEnergiaSufiente();
        System.out.println(nombre + " lanzó un poderoso proyectíl de agua");
        energia -= 10;
    }
}
