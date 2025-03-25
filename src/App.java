/**
 * @author Alejandro Rey Tostado y Alberto Garcia Izquierdo
 */

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class App {

    public static final Scanner teclado = new Scanner(System.in);

    public static String carpetaSeleccionada = "";
    public static String archivoSeleccionado = "";
    public static Map<String, String> datos = new HashMap<>();

    public static String menu() {
        System.out.println("--- MENU ---");
        System.out.println("1. Seleccionar carpeta");
        System.out.println("2. Lectura de fichero");
        System.out.println("3. Conversion archivo");
        System.out.println("0. SALIR");

        return teclado.nextLine();
    }

    public static void mostrarContenidoCarpeta () {
        File carpeta = new File(carpetaSeleccionada);
        String [] archivos = carpeta.list(); // devuelve un array con el nombre de los archivos

        if (archivos != null) {
            System.out.println("Mostrando el contenido de la carpeta");
            for(String archivo : archivos) {
                System.out.println(" - " + archivo); // lista los archivos de la carpeta
            }
        }
    }

    public static void seleccionarCarpeta(Scanner te) {
        System.out.print("Introduzca la ruta de la carpeta: ");
        String ruta = te.nextLine();
        File carpeta = new File(ruta);

        if (carpeta.exists() && carpeta.isDirectory()) {
            carpetaSeleccionada = ruta;
            System.out.println("La carpeta ha sido seleccionada");
            mostrarContenidoCarpeta();
        } else System.out.println("La ruta ingresada no es valida");        
    }

    public static void leerFichero(Scanner te) {
    }

    public static void conversionArchivo(Scanner te) {

    }


    public static void main(String[] args) throws Exception {
        boolean salir = false;

        do {
            System.out.println("Ruta de la carpeta seleccionada: ");
            System.out.println("Contenido de la carpeta seleccionada: ");
            System.out.println("Fichero seleccionado: ");

            switch (menu()) {
                case "1" -> seleccionarCarpeta(teclado);
                case "2" -> leerFichero(teclado);
                case "3" -> conversionArchivo(teclado);
                case "0" -> {
                    salir = true;
                    System.out.println("¡¡¡ Hasta pronto !!!");
                }
                default -> System.out.println("Opcion no valida");
            }
        } while (!salir);
    }
}