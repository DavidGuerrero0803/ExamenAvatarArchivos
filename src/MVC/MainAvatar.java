package MVC;

import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Esta clase es la principal, controla y muestra lo creado en las clases anteriores.
 * Gestiona la interfaz de usuario y la persistencia en archivos.
 */
public class MainAvatar {
    private ArrayList<Personaje> personajes;
    private ArrayList<String> lineasSaltadas;
    private Scanner scanner;
    private boolean archivoCargado;
    private final String ARCHIVO;

    /**
     * El constructor inicializa las colecciones de datos,
     * el scanner y la ruta fija del archivo
     * (esto último para evitar repetir la ruta).
     */
    public MainAvatar() {
        this.personajes = new ArrayList<>();
        this.lineasSaltadas = new ArrayList<>();
        this.scanner = new Scanner(System.in);
        this.archivoCargado = false;
        this.ARCHIVO = "src/registro_personajes.txt";
    }

    /**
     * Ejecuta el menú, cerrando la entrada del teclado al terminar.
     */
    public void ejecutarMenu() {
        mostrarMenu();
        scanner.close();
    }

    /**
     * Muestra el menú principal de opciones en consola.
     * Se maneja mediante un ciclo while.
     */
    public void mostrarMenu() {
        // Bandera para avisar al ciclo de que siga corriendo el menú.
        boolean menuActivo = true;
        // Mientras menúActivo sea true, desplegará las 6 opciones.
        while (menuActivo) {
            System.out.println("\nAVATAR | LA LEYENDA DEL ARCHIVO");
            System.out.println("-> 1 Crear Personaje");
            System.out.println("-> 2 Guardar y Salir");
            System.out.println("-> 3 Cargar Datos");
            System.out.println("-> 4 Mostrar Registros");
            System.out.println("-> 5 Hacer Ataque");
            System.out.println("-> 6 Modificar Personaje");
            System.out.print("¿Qué opción quieres hacer? ");

            int opcionElegida = validarEntero();

            // Dependiendo de lo que haya escogido, entrarará a una opción u otra.
            switch (opcionElegida) {
                case 1:
                    // Entra al submenú de creación de personajes.
                    crearUnPersonaje();
                    break;
                case 2:
                    // Entra al manejo de guardado de archivos mediante un boolean.
                    boolean guardadoHecho = asegurarElArchivo();

                    // Si el guardado se ha hecho (true), mostrará un mensaje y cerrará el programa.
                    if (guardadoHecho) {
                        System.out.println("\nEl registro con los personajes se ha guardado en el archivo.");
                        System.out.println("Saliendo del programa.");
                        menuActivo = false;
                    }
                    break;
                case 3:
                    // Carga los registros que se hayan guardado anteriormente en el archivo.
                    cargarLosDatos();
                    break;
                case 4:
                    // Muestra a todos los personajes creados.
                    mostrarAPersonajes();
                    break;
                case 5:
                    // Permite atacar con un personaje en específico.
                    AtacarConPersonaje();
                    break;
                case 6:
                    // Permite modificar atributos de un personaje ya creado.
                    modificarLosAtributos();
                    break;
                default:
                    // En caso de no elegir bien la opción, saltará un mensaje avisando.
                    System.out.println("Debes elegir entre una de las tres opciones.");
            }
        }
    }

    /**
     * Solicita los datos por requeridos para crear y agregar
     * un nuevo personaje. Estos datos se capturan con la entrada del teclado.
     */
    private void crearUnPersonaje() {
        // Se muestra un submenú que da a elegir al usuario su tipo de personaje.
        System.out.println("\nCREADOR DE PERSONAJE");
        System.out.println("¿Qué quieres que sea tu personaje?");
        System.out.println("1. El Avatar");
        System.out.println("2. Maestro de un elemento");
        System.out.println("3. Un Guerrero");
        System.out.print("Elige una de las opciones: ");

        int personajeSeleccionado = validarEntero();

        // Si escogió Maestro de un elemento, otro submenú se abre
        // y pregunta ahora qué tipo de maestro será.
        int maestroSeleccionado = 0;
        if (personajeSeleccionado == 2) {
            System.out.println("\n¿QUÉ TIPO DE MAESTRO SERÁ?");
            System.out.println("[1] Maestro del Agua");
            System.out.println("[2] Maestro de la Tierra");
            System.out.println("[3] Maestro del Fuego");
            System.out.println("[4] Maestro del Aire");
            System.out.print("Elige una de las opciones: ");
            maestroSeleccionado = validarEntero();
        }

        // Haya tomado cualquiera de las 3 opciones del primer submenú
        // Empezará a pedirte los datos para tu personaje.
        System.out.print("\n¿Cómo se llama? ");
        String nombre = scanner.nextLine();
        System.out.print("Nación a la que pertenece: ");
        String nacion = scanner.nextLine();
        System.out.print("¿Es hombre o mujer? ");
        String genero = scanner.nextLine();
        System.out.print("Edad de tu personaje: ");
        int edad = validarRango(0, 120);
        System.out.print("¿Está vivo? (true/false) ");
        boolean estaVivo = validarBooleano();
        System.out.print("¿Cuál es su nivel de dominio? (0-100): ");
        int nivelDeDominio = validarRango(0, 100);
        System.out.print("¿Cuánta energía tiene? ");
        int energia = validarEntero();

        // Este es un mensaje de advertencia, NO una excepción, ya que
        // el usuario tiene libertad de ponerle la cantidad de energía que quiera.
        if (energia < 0) {
            // Estará avisado de que más adelante, si guarda y carga los datos, mostrará una excepción.
            System.out.println("\nADVERTENCIA: Guardar un personaje con energía negativa generará un mensaje de error al cargar el archivo.");
        }

        // Ahora dependiendo de lo que escogió, se creará su personaje
        // con los atributos que introdujo anteriormente.
        Personaje personajeCreado = null;
        if(personajeSeleccionado == 1) {
            // Si eligió opción 1, se creará un Avatar.
            personajeCreado = new CrearAvatar(nombre, nacion, genero, edad, estaVivo, nivelDeDominio, energia);
            // Si eligió la opción 2, se creará su respectivo maestro dentro del switch-case
            // con la otra opción que tomó del tipo de maestro.
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
            // En caso de no haber elegió 1 u 2, se creará un Guerrero.
            personajeCreado = new Guerrero(nombre, nacion, genero, edad, estaVivo, nivelDeDominio, energia);
        }

        // El personaje se añade al arreglo, la consola te avisa.
        personajes.add(personajeCreado);
        System.out.println("\nSe ha creado tu personaje.");
        // Cada personaje recién creado está obligado a presentarse.
        personajeCreado.presentarPersonaje();

        // Ahora en otro pequeño submenú, se le ponen 2 opciones:
        // Intentar atacar: usará su respectivo método de atacar.
        // Volver al menú: regresará al menú principal.
        boolean regresarAlMenu = false;
        while (!regresarAlMenu) {
            System.out.println("\n¿QUÉ QUIERES HACER CON EL PERSONAJE " + personajeCreado.nombre.toUpperCase() + "?");
            System.out.println("1. Intentar atacar");
            System.out.println("2. Volver al menú");
            System.out.print("Ingresa la opción: ");
            int opcPersonaje = validarEntero();

            // En caso de haber elegido atacar, lo hará usando el método que tenga en su clase.
            if (opcPersonaje == 1) {
                // Dentro del try se valida que el personaje sea una instancia creada de una clase hija.
                try {
                    if (personajeCreado instanceof CrearAvatar) {
                        ((CrearAvatar) personajeCreado).ataqueAvatar();
                    } else if (personajeCreado instanceof MaestroUnElemento) {
                        ((MaestroUnElemento) personajeCreado).atacar();
                    } else if (personajeCreado instanceof  Guerrero) {
                        ((Guerrero) personajeCreado).realizarAtaque();
                    } else {
                        System.out.println(personajeCreado.nombre + " no tiene técnicas de combate.");
                    }
                    // En caso de que se quede sin energía, captura
                    // y muestra el error de que tiene energía insuficiente.
                } catch (EnergiaInsuficienteException e) {
                    System.out.println(e.getMessage());
                }
                // En caso de haber escogido la opción 2, vuelve al menú principal.
            } else if (opcPersonaje == 2) {
                regresarAlMenu = true;
            } else {
                System.out.println("Intenta de nuevo ingresando una opción válida.");
            }
        }
    }

    /**
     * Guarda los personajes que se hayan creado en la sesión en el archivo de texto.
     */
    private boolean asegurarElArchivo() {
        File file = new File(ARCHIVO);

        // Esta condición existe para que no pueda guardar personajes en una sesión
        // en donde no los ha creado todavía. Tiene que cargar los datos primero.
        if (file.exists() && file.length() > 0 && !archivoCargado) {
            System.out.println("\nEl archivo contiene personajes guardados de sesiones anteriores.");
            System.out.println("Para no borrar tu progreso, carga los datos antes de guardar.");
            return false;
        }

        // Este bloque abre el archivo en modo sobreescritura.
        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(ARCHIVO)))) {

            // Escribe aquellas líneas que contienen personajes que saltarán excepciones.
            for (String lineaSaltable : lineasSaltadas) {
                out.println(lineaSaltable);
            }

            // Recorre cada personaje, verificando que sea instancia de su subclase,
            // dándoles a cada uno una etiqueta en String.
            for (Personaje personaje : personajes) {
                String tipoPersonaje = "Personaje";
                if (personaje instanceof CrearAvatar) {
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

                // La salida hará que imprima mediante comas y pegado todos los datos de un personaje.
                out.println(tipoPersonaje + "," + personaje.nombre + "," + personaje.nacion + ","
                            + personaje.genero + "," + personaje.edad + "," + personaje.estaVivo + ","
                            + personaje.nivelDeDominio + "," + personaje.energia);
            }
            // Se fuerza el vaciado de datos para evitar desbordamientos de memoria.
            out.flush();
            return true;

            // Excepción genérica en caso de que haya ocurrido un error cargando el archivo.
        } catch (IOException e) {
            System.out.println("Ocurrió un error al guardar el archivo " + e.getMessage());
            return false;
        }
    }

    /**
     * Lee línea por línea el archivo y reconstruye los personajes.
     */
    private void cargarLosDatos() {
        // Validación para que no cargue el archivo 2 veces en una misma sesión.
        if (archivoCargado) {
            System.out.println("\nAcabas de cargar los archivos recientemente.");
            return;
        }

        // Se valida que primero haya personajes guardados.
        File file = new File(ARCHIVO);
        if (!file.exists() || file.length() == 0) {
            System.out.println("\nPrimero crea un personaje y guárdalo.");
            return;
        }

        // Limpia las listas antes de rellenarlas.
        personajes.clear();
        lineasSaltadas.clear();
        int excepciones = 0;

        // Este bloque abre el archivo para una lectura secuencial en orden descendente.
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String linea;

            while ((linea = br.readLine()) != null) {
                if (linea.trim().isEmpty()) {
                    continue;
                }

                // Detecta la separación de los datos por coma con split()
                // y guarda cada atributo en un arreglo.
                try {
                    String[] datos = linea.split(",");
                    // Toma los datos de cada posición del arreglo.
                    String personaje = datos[0];
                    String nombre = datos[1];
                    String nacion = datos[2];
                    String genero = datos[3];
                    int edad = Integer.parseInt(datos[4]);
                    boolean estaVivo = Boolean.parseBoolean(datos[5]);
                    int nivelDeDominio = Integer.parseInt(datos[6]);
                    int energia = Integer.parseInt(datos[7]);

                    // Aquí se lanza la excepción 2.
                    // Si detecta que se guardó un personaje con energía negativa.
                    if (energia < 0 ) {
                        lineasSaltadas.add(linea);
                        // La pantalla avisará al usuario de que no puede tener este tipo de personajes.
                        throw new EnergiaNegativaException("\n[Exception Error] " + nombre + " no puede tener energía negativa.");
                    }

                    // Reconstrucción del objeto específico mediante el String usado en asegurarElArchivo().
                    switch (personaje) {
                        case "Avatar":
                            personajes.add(new CrearAvatar(nombre, nacion, genero, edad, estaVivo, nivelDeDominio, energia));
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
                    // Atrapa la excepción en caso de haberla.
                } catch (EnergiaNegativaException e) {
                    // Muestra el error de la línea omitida.
                    System.out.println(e.getMessage());
                    // Cuenta la cantidad de excepciones y salta la línea sin afecta al bucle.
                    excepciones++;
                }
            }

            // Si cargó bien, esta bandera se activa como true por lo que ya no se puede
            // volver a cargar el archivo en la misma sesión.
            archivoCargado = true;

            if (excepciones == 0) {
                System.out.println("\nLos registros de los personajes se han cargado.");
            } else {
                System.out.println("\nCarga finalizada. Se detectaron " + excepciones + " registros con energía negativa.");
            }
            // Excepción genérica en caso de que haya surgido un error al cargar el archivo.
        } catch (IOException e) {
            System.out.println("Ha habido un error al cargar el archivo " + e.getMessage());
        }
    }

    /**
     * Muestra en consola todos los personajes guardados en el ArrayList de personajes.
     */
    private void mostrarAPersonajes() {
        if (!archivoCargado) {
            System.out.println("\nCarga los datos antes para poder acceder a los registros.");
            return;
        }

        if (personajes.isEmpty()) {
            System.out.println("\nNo hay personajes creados.");
            return;
        }

        System.out.println("\nREGISTROS ACTUALES DE LOS PERSONAJES CREADOS");
        for (Personaje personaje : personajes) {
            System.out.println("Nombre: " + personaje.nombre + ", Nación: " + personaje.nacion
                    + ", Género: " + personaje.genero + ", Edad: " + personaje.edad
                    + ", Nivel de Dominio " + personaje.nivelDeDominio + ", Energía " + personaje.energia);
        }
    }

    /**
     * Selecciona un personaje específico y hace un ataque, evaluando EnergíaInsuficienteException.
     */
    private void AtacarConPersonaje() {
        if (!archivoCargado) {
            System.out.println("\nCarga los datos antes para poder acceder a los registros.");
            return;
        }

        if (personajes.isEmpty()) {
            System.out.println("\nNo hay personajes para atacar.");
            return;
        }

        System.out.println("\nESCOGE A TU PERSONAJE PARA REALIZAR UN ATAQUE");
        for (int i = 0; i < personajes.size(); i++) {
            System.out.println("[" + (i + 1) + "] " + personajes.get(i).nombre);
        }
        System.out.print("Opción: ");

        int rangoPersonajes = validarRango(1, personajes.size()) - 1;
        Personaje personaje = personajes.get(rangoPersonajes);

        // Al igual que en crearUnPersonaje(), se abre un try-catch.
        try {
            // Verifica que el personaje sea de una instancia de una clase.
            // Posteriormente, realiza su respectivo ataque.
            if (personaje instanceof CrearAvatar) {
                ((CrearAvatar) personaje).ataqueAvatar();
            } else if (personaje instanceof MaestroUnElemento) {
                ((MaestroUnElemento) personaje).atacar();
            } else if (personaje instanceof Guerrero) {
                ((Guerrero) personaje).realizarAtaque();
            } else {
                System.out.println(personaje.nombre + " no tiene técnicas de combate.");
            }
            // En caso de quedarse sin energía, atrapará la excepción de energía insuficiente.
        } catch (EnergiaInsuficienteException e) {
            // Dicha excepción se mostrará con su mensaje.
            System.out.println(e.getMessage());
        }
    }

    /**
     * Permite modificar los atributos de un personaje ya creado
     * permite evaluar más rápido EnergiaNegativaException.
     */
    private void modificarLosAtributos() {
        if (!archivoCargado) {
            System.out.println("\nCarga los datos antes para poder acceder a los registros.");
            return;
        }

        if (personajes.isEmpty()) {
            System.out.println("No hay personajes para modificar.");
            return;
        }

        // Pregunta por el nombre del personaje que quieres cambiar.
        System.out.print("\nNombre del personaje a modificar: ");
        String nombreBuscado = scanner.nextLine();

        // Busca al personaje sin distinción de mayúsculas o minúsculas.
        Personaje personajeEncontrado = null;
        for (Personaje personaje : personajes) {
            if (personaje.nombre.equalsIgnoreCase(nombreBuscado)) {
                personajeEncontrado = personaje;
                break;
            }
        }

        // En caso de haber buscado mal, te avisará que no existe tal personaje.
        if (personajeEncontrado == null) {
            System.out.println("No existe personaje con el nombre: " + nombreBuscado);
            return;
        }

        // Si existe, se abre submenú de modificación de atributos.
        System.out.println("¿Qué atributo de " + personajeEncontrado.nombre + " vas a modificar?");
        System.out.println("1. Nombre");
        System.out.println("2. Nación");
        System.out.println("3. Edad");
        System.out.println("4. Energía");
        System.out.print("Selecciona una opción: ");
        int atributoSeleccionado = validarRango(1, 4);

        // Se abre el try-catch con un switch-case en su interior.
        try {
            switch (atributoSeleccionado) {
                case 1:
                    System.out.print("\nNuevo nombre: ");
                    personajeEncontrado.nombre = scanner.nextLine();
                    System.out.println("Nombre actualizado.");
                    break;
                case 2:
                    System.out.print("\nNueva nación: ");
                    personajeEncontrado.nacion = scanner.nextLine();
                    System.out.println("Nación actualizada.");
                    break;
                case 3:
                    System.out.print("\nNueva edad: ");
                    personajeEncontrado.edad = validarEntero();
                    System.out.println("Edad actualizada.");
                    break;
                case 4:
                    System.out.print("\nValor nuevo de energía: ");
                    int energiaNueva = validarEntero();

                    // Si llega a cambiar el valor de la energía a negativo,
                    // lanzará la excepción de energía negativa.
                    if (energiaNueva < 0) {
                        throw new EnergiaNegativaException("\n[Exception Error] " + personajeEncontrado.nombre + " no puede tener energía negativa.");
                    }

                    personajeEncontrado.energia = energiaNueva;
                    System.out.println("Energía actualizada.");
                    break;
            }
            // Captura el Exception Error y lo muestra en consola.
        } catch (EnergiaNegativaException e) {
            System.out.println(e.getMessage());
            System.out.println("Los atributos se han mantenido intactos.");
        }
    }

    /**
     * Se encarga de validar la captura de datos enteros
     * se encuentren en un rango numérico dado,
     * y que no capture otra cosa por el teclado.
     */
    private int validarRango(int rango1, int rango2) {
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
                System.out.print("Introduce un número dentro del rango válido: ");
                scanner.nextLine();
            }
        }
    }

    /**
     * Se encarga de validar la captura de datos enteros
     * y que no capture otra cosa por el teclado.
     */
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

    /**
     * Se encarga de validar la captura de datos booleanos
     * y que no capture otra cosa por el teclado.
     */
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
