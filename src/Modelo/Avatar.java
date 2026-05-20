package Modelo;

public class Avatar extends Personaje implements IMaestroAgua, IMaestroTierra, IMaestroFuego, IMaestroAire {

    public Avatar(String nombre, String nacion, String genero, int edad, boolean estaVivo, int nivelDeDominio, int energia) {
        super(nombre, nacion, genero, edad, estaVivo, nivelDeDominio, energia);
    }

    @Override
    public void controlarAgua() {
        System.out.println(nombre + " ha dominado el Agua Control");
    }

    @Override
    public void controlarTierra() {
        System.out.println(nombre + " ha dominado la Tierra Control");
    }

    @Override
    public void controlarFuego() {
        System.out.println(nombre + " ha dominado el Fuego Control");
    }

    @Override
    public void controlarAire() {
        System.out.println(nombre + " ha dominado el Aire Control");
    }

    public void ataqueAvatar() throws EnergiaInsuficienteException {
        poseeEnergiaSufiente();
        System.out.println(nombre + " ha hecho un ataque con la fuerza de los 4 elementos");
        energia -= 10;
    }
}
