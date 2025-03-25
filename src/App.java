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
            System.out.println("Ruta de la carpeta seleccionada: ");
            System.out.println("Contenido de la carpeta seleccionada: ");
            System.out.println("Fichero seleccionado: ");

            switch (menu()) {
                case "1" -> {}
                case "2" -> {}
                case "3" -> {}
                case "0" -> {
                    salir = true;
                    System.out.println("¡¡¡ Hasta pronto !!!");
                }
                default -> System.out.println("Opcion no valida");
            }
        } while (!salir);
    }
}