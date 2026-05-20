package Modelo;

public class MaestroDelFuego extends MaestroUnElemento implements IMaestroFuego {

    public MaestroDelFuego(String nombre, String nacion, String genero, int edad, boolean estaVivo, int nivelDeDominio, int energia) {
        super(nombre, nacion, genero, edad, estaVivo, nivelDeDominio, energia);
    }

    @Override
    public void controlarFuego() {
        System.out.println(nombre + " se encuentra bajo Fuego Control");
    }

    @Override
    public void atacar() throws EnergiaInsuficienteException {
        poseeEnergiaSufiente();
        System.out.println(nombre + " realizó un ataque combinado de fuego y electricidad");
        energia -= 10;
    }
}
