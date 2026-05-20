package MVC;

public class MaestroDeTierra extends MaestroUnElemento implements IMaestroTierra {

    public MaestroDeTierra(String nombre, String nacion, String genero, int edad, boolean estaVivo, int nivelDeDominio, int energia) {
        super(nombre, nacion, genero, edad, estaVivo, nivelDeDominio, energia);
    }

    @Override
    public void controlarTierra() {
        System.out.println(nombre + " se encuentra bajo Tierra Control");
    }

    @Override
    public void atacar() throws EnergiaInsuficienteException {
        verificarEnergiaSufiente();
        System.out.println(nombre + " atacó con una gran piedra reforzada");
        energia -= 10;
    }
}
