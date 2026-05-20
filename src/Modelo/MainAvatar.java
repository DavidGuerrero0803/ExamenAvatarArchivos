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

    public static void main(String[] args) {

    }
}
