package MVC;

/**
 * Esta clase representa a un personaje del mundo de Avatar.
 * Tiene todos los atributos que caracterizan a uno
 */
public class Personaje {
    protected String nombre;
    protected String nacion;
    protected String genero;
    protected int edad;
    protected boolean estaVivo;
    protected int nivelDeDominio;
    protected int energia;

    /**
     * El constructor principal de la clase permite con la herencia heredar los atributos a las clases hijas.
     * @param nombre Nombre que tendrá el personaje.
     * @param nacion Nación a la que pertenece.
     * @param genero Género del personaje.
     * @param edad Edad que tiene.
     * @param estaVivo Si el personaje está vivo o no (true/false).
     * @param nivelDeDominio Su nivel de dominio (0-100).
     * @param energia Su nivel de energía.
     */
    public Personaje(String nombre, String nacion, String genero, int edad, boolean estaVivo, int nivelDeDominio, int energia) {
        this.nombre = nombre;
        this.nacion = nacion;
        this.genero = genero;
        this.edad = edad;
        this.estaVivo = estaVivo;
        this.nivelDeDominio = nivelDeDominio;
        this.energia = energia;
    }

    /**
     * Constructor génerico que crea a un personaje genérico inventado.
     */
    public Personaje() {
        nombre = "Genérico";
        nacion = "Reino de los cielos";
        genero = "Masculino";
        edad = 20;
        estaVivo = true;
        nivelDeDominio = 5;
        energia = 10;
    }

    /**
     * Presenta al personaje diciendo sus características más importantes (nombre, edad y nación).
     */
    public void presentarPersonaje() {
        System.out.println("¡Hola hola! Mi nombre es " + nombre + ", tengo " + edad + " años y soy de " + nacion + ".");
    }

    /**
     * Verifica que el personaje tenga la energía necesaria como para poder realizar un ataque.
     * @throws EnergiaInsuficienteException En caso de que Personaje no tenga una energía mayor a 10.
     */
    protected void poseeEnergiaSufiente() throws EnergiaInsuficienteException {
        if (this.energia < 10) {
            throw new EnergiaInsuficienteException("\n[Exception Error] ¡El maestro necesita descansar y meditar!");
        }
    }

}
