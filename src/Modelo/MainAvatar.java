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
            System.out.println("> 1 Crear Personaje");
            System.out.println("> 2 Guardar y Salir");
            System.out.println("> 3 Cargar Datos");
            System.out.println("Escoge una opción: ");
            int opcionElegida = scanner.nextInt();
            scanner.nextLine();

            switch (opcionElegida) {
                case 1:

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

    public static void main(String[] args) {
        MainAvatar ui = new MainAvatar();
        ui.ejecutarMenu();
    }
}
