package Modelo;

public class MaestroDelAire extends MaestroUnElemento implements IMaestroAire {

    public MaestroDelAire(String nombre, String nacion, String genero, int edad, boolean estaVivo, int nivelDeDominio, int energia) {
        super(nombre, nacion, genero, edad, estaVivo, nivelDeDominio, energia);
    }

    @Override
    public void controlarAire() {
        System.out.println(nombre + " se encuentra bajo Aire Control");
    }

    @Override
    public void atacar() throws EnergiaInsuficienteException {
        verificarEnergiaSufiente();
        System.out.println(nombre + " hizo una ráfaga de viento una ráfaga de viento y huyó");
        energia -= 10;
    }
}
