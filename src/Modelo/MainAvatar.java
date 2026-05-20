package Modelo;

import java.util.ArrayList;
import java.util.Scanner;

public class MainAvatar {
    private ArrayList<Personaje> personajes;
    private Scanner scanner;
    private boolean archivoCargado;

    public MainAvatar() {
        this.personajes = new ArrayList<>();
        this.scanner = new Scanner(System.in);
        this.archivoCargado = false;
    }

    public void ejecutarMenu() {
        mostrarMenu();
        scanner.close();
    }

    public void mostrarMenu() {
        boolean menuActivo = true;
        while (menuActivo) {
            System.out.println("AVATAR | LA LEYENDA DEL ARCHIVO");
            System.out.println("-> 1 Crear Personaje");
            System.out.println("-> 2 Guardar y Salir");
            System.out.println("-> 3 Cargar Datos");
            System.out.println("Escoge una opción: ");
            int opcionElegida = scanner.nextInt();
            scanner.nextLine();

            switch (opcionElegida) {
                case 1:
                    crearUnPersonaje();
                    break;
                case 2:

                    menuActivo = false;
                    break;
                case 3:

                    break;
                default:
                    System.out.println("Debes elegir entre una de las tres opciones.");
            }
        }
    }

    private void crearUnPersonaje() {
        System.out.println("CREADOR DE PERSONAJE");
        System.out.println("¿Qué quieres que sea tu personaje?");
        System.out.println("1. El Avatar");
        System.out.println("2. Maestro de un elemento");
        System.out.println("3. Un Guerrero");

        int personajeSeleccionado = scanner.nextInt();
        scanner.nextLine();

        System.out.print("¿Cómo se llama? ");
        String nombre = scanner.nextLine();
        System.out.print("Nación a la que pertenece: ");
        String nacion = scanner.nextLine();
        System.out.print("¿Es hombre o mujer? ");
        String genero = scanner.nextLine();
        System.out.print("Edad de tu personaje: ");
        int edad = scanner.nextInt();
        System.out.print("¿Está vivo? ");
        boolean estaVivo = scanner.nextBoolean();
        System.out.print("¿Cuál es su nivel de dominio? (1-100): ");
        int nivelDeDominio = scanner.nextInt();
        System.out.print("¿Cuánta energía tiene? ");
        int energia = scanner.nextInt();
        scanner.nextLine();

        if (energia < 0) {
            System.out.println("ADVERTENCIA: Guardar un personaje con energía negativa generará un mensaje de error al cargar el archivo.");
        }

        Personaje personajeCreado = null;
        if(personajeSeleccionado == 1) {
            personajeCreado = new Avatar(nombre, nacion, genero, edad, estaVivo, nivelDeDominio, energia);
        } else if (personajeSeleccionado == 2) {
            System.out.println("Escogiste que sea maestro de un elemento, ¿Pero qué maestro será?");
            System.out.println("[1] Maestr@ del Agua");
            System.out.println("[2] Maestr@ de la Tierra");
            System.out.println("[3] Maestr@ del Fuego");
            System.out.println("[4] Maestr@ del Aire");
            int maestroSeleccionado = scanner.nextInt();
            scanner.nextLine();
            switch (maestroSeleccionado) {
                case 1:
                    personajeCreado = new MaestroDelAgua(nombre, nacion, genero, edad, estaVivo, nivelDeDominio, energia);
                    break;
                case 2:
                    personajeCreado = new MaestroDeTierra(nombre, nacion, genero, edad, estaVivo, nivelDeDominio, energia);
                    break;
                case 3:
                    personajeCreado = new MaestroDelFuego(nombre, nacion, genero, edad, estaVivo, nivelDeDominio, energia);
                    break;
                case 4:
                    personajeCreado = new MaestroDelAire(nombre, nacion, genero, edad, estaVivo, nivelDeDominio, energia);
                    break;
                default:
                    System.out.println("Debes elegir entre una de las cuatro opciones.");
            }
        } else {
            personajeCreado = new Guerrero(nombre, nacion, genero, edad, estaVivo, nivelDeDominio, energia);
        }

        personajes.add(personajeCreado);
        System.out.println("Se ha registrado tu personaje.");
    }

    public static void main(String[] args) {
        MainAvatar ui = new MainAvatar();
        ui.ejecutarMenu();
    }
}
