package Modelo;

import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MainAvatar {
    private ArrayList<Personaje> personajes;
    private ArrayList<String> lineasSaltadas;
    private Scanner scanner;
    private boolean archivoCargado;
    private final String ARCHIVO;

    public MainAvatar() {
        this.personajes = new ArrayList<>();
        this.lineasSaltadas = new ArrayList<>();
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
            System.out.print("¿Qué opción quieres hacer? ");

            int opcionElegida = validarEntero();

            switch (opcionElegida) {
                case 1:
                    crearUnPersonaje();
                    break;
                case 2:
                    boolean guardadoHecho = asegurarElArchivo();

                    if (guardadoHecho) {
                        System.out.println("El registro con los personajes se ha guardado en el archivo.");
                        System.out.println("Saliendo del programa.");
                        menuActivo = false;
                    }
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
        System.out.print("Elige una de las opciones: ");

        int personajeSeleccionado = validarEntero();

        int maestroSeleccionado = 0;
        if (personajeSeleccionado == 2) {
            System.out.println("¿QUÉ TIPO DE MAESTRO SERÁ?");
            System.out.println("[1] Maestro del Agua");
            System.out.println("[2] Maestro de la Tierra");
            System.out.println("[3] Maestro del Fuego");
            System.out.println("[4] Maestro del Aire");
            System.out.print("Elige una de las opciones: ");
            maestroSeleccionado = validarEntero();
        }

        System.out.print("¿Cómo se llama? ");
        String nombre = scanner.nextLine();
        System.out.print("Nación a la que pertenece: ");
        String nacion = scanner.nextLine();
        System.out.print("¿Es hombre o mujer? ");
        String genero = scanner.nextLine();
        System.out.print("Edad de tu personaje: ");
        int edad = validarEntero();
        System.out.print("¿Está vivo? (true/false) ");
        boolean estaVivo = validarBooleano();
        System.out.print("¿Cuál es su nivel de dominio? (0-100): ");
        int nivelDeDominio = validarRangoDominio(0, 100);
        System.out.print("¿Cuánta energía tiene? ");
        int energia = validarEntero();

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
        personajeCreado.presentarPersonaje();

        boolean regresarAlMenu = false;
        while (!regresarAlMenu) {
            System.out.println("¿QUÉ QUIERES HACER CON EL PERSONAJE " + personajeCreado.nombre.toUpperCase() + "?");
            System.out.println("[1] Intentar atacar");
            System.out.println("[2] Volver al menú");
            System.out.print("Ingresa la opción: ");
            int opcPersonaje = validarEntero();

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

    private boolean asegurarElArchivo() {
        File file = new File(ARCHIVO);

        if (file.exists() && file.length() > 0 && !archivoCargado) {
            System.out.println("El archivo contiene personajes guardados de sesiones anteriores.");
            System.out.println("Para no borrar tu progreso, carga los datos antes de guardar.");
            return false;
        }

        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(ARCHIVO)))) {

            for (String lineaSaltable : lineasSaltadas) {
                out.println(lineaSaltable);
            }

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
            return true;

        } catch (IOException e) {
            System.out.println("Ocurrió un error al guardar el archivo " + e.getMessage());
            return false;
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
        lineasSaltadas.clear();
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
                        lineasSaltadas.add(linea);
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
            } else {
                System.out.println("Carga finalizada. Se detectaron " + excepciones + " registros con energía negativa.");
            }
        } catch (IOException e) {
            System.out.println("Ha habido un error al cargar el archivo " + e.getMessage());
        }
    }

    private int validarRangoDominio(int rango1, int rango2) {
        while (true) {
            try {
                int valor = scanner.nextInt();
                scanner.nextLine();

                if (valor >= rango1 && valor <= rango2) {
                    return valor;
                } else {
                    System.out.print("El valor debe estar entre " + rango1 + " y " + rango2 + ": ");
                }
            } catch (InputMismatchException e) {
                System.out.print("Introduce un número entero dentro del rango válido. ");
                scanner.nextLine();
            }
        }
    }

    private int validarEntero() {
        while (true) {
            try {
                int valor = scanner.nextInt();
                scanner.nextLine();
                return valor;
            } catch (InputMismatchException e) {
                System.out.print("Escribe un número entero: ");
                scanner.nextLine();
            }
        }
    }

    private boolean validarBooleano() {
        while (true) {
            try {
                boolean valor = scanner.nextBoolean();
                scanner.nextLine();
                return valor;
            } catch (InputMismatchException e) {
                System.out.print("Debes escribir true o false: ");
                scanner.nextLine();
            }
        }
    }

    public static void main(String[] args) {
        MainAvatar ui = new MainAvatar();
        ui.ejecutarMenu();
    }
}
