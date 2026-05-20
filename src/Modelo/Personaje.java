package Modelo;

public class Personaje {
    protected String nombre;
    protected String nacion;
    protected String genero;
    protected int edad;
    protected boolean estaVivo;
    protected int nivelDeDominio;
    protected int energia;

    public Personaje(String nombre, String nacion, String genero, int edad, boolean estaVivo, int nivelDeDominio, int energia) {
        this.nombre = nombre;
        this.nacion = nacion;
        this.genero = genero;
        this.edad = edad;
        this.estaVivo = estaVivo;
        this.nivelDeDominio = nivelDeDominio;
        this.energia = energia;
    }

    public Personaje() {
        nombre = "Genérico";
        nacion = "Tierra";
        genero = "Masculino";
        edad = 20;
        estaVivo = true;
        nivelDeDominio = 5;
        energia = 10;
    }

}
