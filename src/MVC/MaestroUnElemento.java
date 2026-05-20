package MVC;

public abstract class MaestroUnElemento extends Personaje {

    public MaestroUnElemento(String nombre, String nacion, String genero, int edad, boolean estaVivo, int nivelDeDominio, int energia) {
        super(nombre, nacion, genero, edad, estaVivo, nivelDeDominio, energia);
    }

    abstract void atacar() throws EnergiaInsuficienteException;

}
