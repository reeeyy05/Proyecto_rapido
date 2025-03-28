
/**
 * @author Alejandro Rey Tostado y Alberto Garcia Izquierdo
 */

import java.util.Scanner;

public class App {

    public static final Scanner teclado = new Scanner(System.in);

    public static String menu() {
        System.out.println("--- MENU ---");
        System.out.println("1. Seleccionar carpeta");
        System.out.println("2. Lectura de fichero");
        System.out.println("3. Conversion archivo");
        System.out.println("0. SALIR");

        return teclado.nextLine();
    }

    public static void main(String[] args) throws Exception {
        GestorArchivos gestor = new GestorArchivos();
        boolean salir = false;

        do {
            System.out.println("\nRuta de la carpeta seleccionada: " + gestor.carpetaSeleccionada);
            System.out.println("Fichero seleccionado: " + gestor.archivoSeleccionado);
            System.out.println("Contenido de la carpeta seleccionada: ");
            gestor.mostrarContenidoCarpeta();

            switch (menu()) {
                case "1" -> gestor.seleccionarCarpeta(teclado);
                case "2" -> gestor.leerFichero(teclado);
                case "3" -> gestor.convertirArchivo(teclado);
                case "0" -> {
                    salir = true;
                    System.out.println("¡¡¡ Hasta pronto !!!");
                }
                default -> System.out.println("Opcion no valida");
            }
        } while (!salir);
    }
}