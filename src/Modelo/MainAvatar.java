package Modelo;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class MainAvatar {
    private ArrayList<Personaje> personajes;
    private Scanner scanner;
    private boolean archivoCargado;
    private final String ARCHIVO;

    public MainAvatar() {
        this.personajes = new ArrayList<>();
        this.scanner = new Scanner(System.in);
        this.archivoCargado = false;
        this.ARCHIVO = "src/registro_personajes.txt";
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
                    asegurarElArchivo();
                    menuActivo = false;
                    break;
                case 3:
                    cargarLosDatos();
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

        int maestroSeleccionado = 0;
        if (personajeSeleccionado == 2) {
            System.out.println("Escogiste que sea maestro de un elemento, ¿Pero qué maestro será?");
            System.out.println("[1] Maestr@ del Agua");
            System.out.println("[2] Maestr@ de la Tierra");
            System.out.println("[3] Maestr@ del Fuego");
            System.out.println("[4] Maestr@ del Aire");
            maestroSeleccionado = scanner.nextInt();
            scanner.nextLine();
        }

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
        System.out.println("Se ha creado tu personaje.");

        boolean regresarAlMenu = false;
        while (!regresarAlMenu) {
            System.out.println("¿Qué quieres hacer con el personaje " + personajeCreado.nombre + "?");
            System.out.println("[1] Intentar atacar");
            System.out.println("[2] Volver al menú");
            System.out.println("Ingresa la opción: ");
            int opcPersonaje = scanner.nextInt();
            scanner.nextLine();

            if (opcPersonaje == 1) {
                try {
                    if (personajeCreado instanceof Avatar) {
                        ((Avatar) personajeCreado).ataqueAvatar();
                    } else if (personajeCreado instanceof MaestroUnElemento) {
                        ((MaestroUnElemento) personajeCreado).atacar();
                    } else if (personajeCreado instanceof  Guerrero) {
                        ((Guerrero) personajeCreado).realizarAtaque();
                    } else {
                        System.out.println(personajeCreado.nombre + " parece no tener técnicas de combate.");
                    }
                } catch (EnergiaInsuficienteException e) {
                    System.out.println(e.getMessage());
                }
            } else if (opcPersonaje == 2) {
                regresarAlMenu = true;
            } else {
                System.out.println("Intenta de nuevo ingresando una opción válida.");
            }
        }
    }

    private void asegurarElArchivo() {
        File file = new File(ARCHIVO);

        if (file.exists() && file.length() > 0 && !archivoCargado) {
            System.out.println("El archivo contiene personajes guardados de sesiones anteriores.");
            System.out.println("Para no borrar tu progreso, carga los datos antes de guardar.");
            mostrarMenu();
        }

        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(ARCHIVO)))) {
            for (Personaje personaje : personajes) {
                String tipoPersonaje = "Personaje";
                if (personaje instanceof Avatar) {
                    tipoPersonaje = "Avatar";
                } else if (personaje instanceof MaestroDelAgua) {
                    tipoPersonaje = "Maestro_Agua";
                } else if (personaje instanceof MaestroDeTierra) {
                    tipoPersonaje = "Maestro_Tierra";
                } else if (personaje instanceof MaestroDelFuego) {
                    tipoPersonaje = "Maestro_Fuego";
                } else if (personaje instanceof  MaestroDelAire) {
                    tipoPersonaje = "Maestro_Aire";
                } else if (personaje instanceof Guerrero) {
                    tipoPersonaje = "Guerrero";
                }

                out.println(tipoPersonaje + "," + personaje.nombre + "," + personaje.nacion + ","
                            + personaje.genero + "," + personaje.edad + "," + personaje.estaVivo + ","
                            + personaje.nivelDeDominio + "," + personaje.energia);
            }
            out.flush();
            System.out.println("El registro de personajes se ha guardado en el archivo");

        } catch (IOException e) {
            System.out.println("Ocurrió un error al guardar el archivo " + e.getMessage());
        }
    }

    private void cargarLosDatos() {
        if (archivoCargado) {
            System.out.println("Acabas de cargar los archivos recientemente");
            return;
        }

        File file = new File(ARCHIVO);
        if (!file.exists() || file.length() == 0) {
            System.out.println("Primero crea un personaje y guárdalo.");
            return;
        }

        personajes.clear();
        int excepciones = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String linea;

            while ((linea = br.readLine()) != null) {
                if (linea.trim().isEmpty()) {
                    continue;
                }
                try {
                    String[] datos = linea.split(",");
                    String personaje = datos[0];
                    String nombre = datos[1];
                    String nacion = datos[2];
                    String genero = datos[3];
                    int edad = Integer.parseInt(datos[4]);
                    boolean estaVivo = Boolean.parseBoolean(datos[5]);
                    int nivelDeDominio = Integer.parseInt(datos[6]);
                    int energia = Integer.parseInt(datos[7]);

                    if (energia < 0 ) {
                        throw new EnergiaNegativaException("[Exception Error] Registro detectado: " + nombre + " tiene energía negativa.");
                    }

                    switch (personaje) {
                        case "Avatar":
                            personajes.add(new Avatar(nombre, nacion, genero, edad, estaVivo, nivelDeDominio, energia));
                            break;
                        case "Maestro_Agua":
                            personajes.add(new MaestroDelAgua(nombre, nacion, genero, edad, estaVivo, nivelDeDominio, energia));
                            break;
                        case "Maestro_Tierra":
                            personajes.add(new MaestroDeTierra(nombre, nacion, genero, edad, estaVivo, nivelDeDominio, energia));
                            break;
                        case "Maestro_Fuego":
                            personajes.add(new MaestroDelFuego(nombre, nacion, genero, edad, estaVivo, nivelDeDominio, energia));
                            break;
                        case "Maestro_Aire":
                            personajes.add(new MaestroDelAire(nombre, nacion, genero, edad, estaVivo, nivelDeDominio, energia));
                            break;
                        case "Guerrero":
                            personajes.add(new Guerrero(nombre, nacion, genero, edad, estaVivo, nivelDeDominio, energia));
                    }
                } catch (EnergiaNegativaException e) {
                    System.out.println(e.getMessage());
                    excepciones++;
                }
            }

            archivoCargado = true;

            if (excepciones == 0) {
                System.out.println("Los registros de los personajes se han cargado.");
            }
        } catch (IOException e) {
            System.out.println("Ha habido un error al cargar el archivo " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        MainAvatar ui = new MainAvatar();
        ui.ejecutarMenu();
    }
}
