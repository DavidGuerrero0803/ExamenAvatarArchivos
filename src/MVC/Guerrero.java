package MVC;

public class Guerrero extends Personaje {

    public Guerrero(String nombre, String nacion, String genero, int edad, boolean estaVivo, int nivelDeDominio, int energia) {
        super(nombre, nacion, genero, edad, estaVivo, nivelDeDominio, energia);
    }

    public void realizarAtaque() throws EnergiaInsuficienteException {
        verificarEnergiaSufiente();
        System.out.println(nombre + " realizó un ataque con su arma (es muy efectivo)");
        energia -= 10;
    }
}