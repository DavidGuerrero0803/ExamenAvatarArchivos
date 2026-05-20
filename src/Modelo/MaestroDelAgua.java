package Modelo;

public class MaestroDelAgua extends MaestroUnElemento implements IMaestroAgua {

    public MaestroDelAgua(String nombre, String nacion, String genero, int edad, boolean estaVivo, int nivelDeDominio, int energia) {
        super(nombre, nacion, genero, edad, estaVivo, nivelDeDominio, energia);
    }

    @Override
    public void controlarAgua() {
        System.out.println(nombre + " se encuentra bajo Agua Control");
    }

    @Override
    public void atacar() throws EnergiaInsuficienteException {
        verificarEnergiaSufiente();
        System.out.println(nombre + " lanzó un poderoso proyectíl de agua");
        energia -= 10;
    }
}
