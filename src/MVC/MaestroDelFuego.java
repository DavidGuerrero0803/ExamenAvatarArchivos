package MVC;

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
        verificarEnergiaSufiente();
        // idea si descarto o no: que controlarFuego() salga como mensaje de preparación antes de atacar.
        System.out.println(nombre + " realizó un ataque combinado de fuego y electricidad");
        energia -= 10;
    }
}
